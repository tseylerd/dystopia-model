package com.dystopia.model.sketch.model

import com.dystopia.model.sketch.api.FamilyMember
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.element.FileElement
import com.dystopia.model.sketch.element.IdBasedSketchElement
import com.dystopia.model.sketch.element.LayersElement
import com.dystopia.model.sketch.element.PropertyElement
import com.dystopia.model.sketch.util.*

class Page(val id: String, val name: String, val layers: Array<Layer>, val elements: Array<SketchElement>): FamilyMember<Page> {

    override fun sameAs(another: Page): Boolean {
        return id == another.id
    }

    fun documentElement(): SketchElement {
        return objectList {
            arrayOf(
                    PropertyElement("_class", "MSJSONFileReference"),
                    PropertyElement("_ref_class", "MSImmutablePage"),
                    PropertyElement("_ref", "pages/$id")
            )
        }
    }

    fun fileElement(): SketchElement {
        return FileElement(
                id + ".json",
                objectList {
                    concatenationOf {
                        elements
                    }.with {
                        LayersElement(
                                "layers",
                                array {
                                    layers.map { it.element() }.toTypedArray()
                                }
                        )
                    }
                }
        )
    }

    fun metaElement(): SketchElement {
        return IdBasedSketchElement(
                id,
                objectList {
                    concatenationOfElement {
                        PropertyElement(SketchAttributes.name, name)
                    }.with {
                        objectWithId(SketchAttributes.artboards) {
                            listElement {
                                layers.filterIsInstance<Artboard>().map { it.metaElement() }.toTypedArray()
                            }
                        }
                    }
                }
        )
    }
}