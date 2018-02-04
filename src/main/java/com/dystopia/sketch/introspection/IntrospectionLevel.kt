package com.dystopia.sketch.introspection

import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.element.*
import com.dystopia.sketch.util.SketchAttributes
import com.google.gson.JsonElement

private enum class LevelMarker(private vararg val markers: String) {
    LIST(SketchAttributes.pagesAndArtboards) {
        override fun getLevel() = ListLevel
    },
    PAGE() {
        override fun getLevel() = PageLevel
    },
    ARRAY(SketchAttributes.borders, SketchAttributes.fills, SketchAttributes.points) {
        override fun getLevel() = ArrayLevel
    },
    LAYERS(SketchAttributes.layers) {
        override fun getLevel() = LayersLevel()
    },
    PAGES(SketchAttributes.pages) {
        override fun getLevel() = PagesLevel
    };

    abstract fun getLevel(): IntrospectionLevel

    infix fun likes(marker: String) = markers.contains(marker)

    companion object {
        infix fun of(marker: String): LevelMarker {
            for (value in values()) {
                if (value likes marker) return value
            }
            return LIST
        }
    }
}

sealed class IntrospectionLevel {
    abstract fun listOfElements(elements: Array<SketchElement>): SketchElement
    abstract fun element(key: String, value: JsonElement, introspector: SketchIntrospector): SketchElement
    abstract fun element(value: JsonElement, introspector: SketchIntrospector): SketchElement
}

abstract class GenericLevel : IntrospectionLevel() {
    override fun listOfElements(elements: Array<SketchElement>): SketchElement {
        val element = elements.classElement()
        return when (element?.value) {
            SketchAttributes.page -> {
                val data = elements.layerData()
                PageElement(data.id, data.name, elements)
            }
            else -> if (shouldConstructArray()) ArrayElement(elements) else ListElement(elements)
        }
    }

    abstract fun shouldConstructArray(): Boolean

    override fun element(key: String, value: JsonElement, introspector: SketchIntrospector): SketchElement {
        return introspector.fromJsonElement(key, value)
    }

    override fun element(value: JsonElement, introspector: SketchIntrospector): SketchElement {
        return introspector.fromJsonElement(value)
    }
}

object ListLevel : GenericLevel() {
    override fun shouldConstructArray() = false
}

object PageLevel : GenericLevel() {
    override fun shouldConstructArray() = false
}

@Suppress("unused")
object ArrayLevel: GenericLevel() {
    override fun shouldConstructArray() = true
}

@Suppress("unused")
class LayersLevel : GenericLevel() {
    override fun shouldConstructArray(): Boolean {
        return false
    }

    override fun element(value: JsonElement, introspector: SketchIntrospector): SketchElement {
        return introspector.fromJsonElement(value, TransparentLevel())
    }

    private class TransparentLevel : GenericLevel() {
        private lateinit var layerData: LayerData

        override fun shouldConstructArray(): Boolean {
            return false
        }

        override fun listOfElements(elements: Array<SketchElement>): SketchElement {
            layerData = elements.layerData()
            return when (layerData.clazz) {
                SketchAttributes.artboard -> ArtboardElement(layerData.id, layerData.name, elements)
                SketchAttributes.symbolMaster -> SymbolElement(layerData.id, layerData.name, elements)
                else -> LayerElement(layerData.id, layerData.name, elements)
            }
        }
    }
}

@Suppress("unused")
object PagesLevel : GenericLevel() {
    override fun listOfElements(elements: kotlin.Array<SketchElement>): SketchElement {
        return PagesArray(elements)
    }

    override fun shouldConstructArray(): Boolean {
        return true
    }
}

fun levelOf(key: String) = (LevelMarker of key).getLevel()

private data class LayerData(
        val id: String,
        val name: String,
        val clazz: String
)

private fun Array<SketchElement>.layerData(): LayerData {
    val id = filterIsInstance(ObjectId::class.java).first().value
    val name = filterIsInstance(NameElement::class.java).first().value
    val clazz = filterIsInstance(ClassElement::class.java).first().value
    return LayerData(id, name, clazz)
}

private fun Array<SketchElement>.classElement(): ClassElement? {
    return filterIsInstance(ClassElement::class.java).firstOrNull()
}