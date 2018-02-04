package com.dystopia.sketch.introspection

import com.dystopia.api.introspection.Introspector
import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.element.DirectoryElement
import com.dystopia.sketch.element.ListElement
import com.dystopia.sketch.element.MockElement
import com.dystopia.sketch.element.RootElement
import com.dystopia.api.vfs.BinaryFile
import com.dystopia.api.vfs.FileFixture
import com.dystopia.api.vfs.VirtualDirectory
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import java.util.*

class SketchIntrospector(val fixture: FileFixture) : Introspector<SketchElement> {

    var levelStack: LinkedList<IntrospectionLevel> = LinkedList()

    val level: IntrospectionLevel
    get() = levelStack.peek()

    init {
        levelStack.add(ListLevel)
    }

    override fun introspect(): SketchElement {
        return RootElement(
                fixture.root.name(),
                introspect("meta.json"),
                introspect("document.json"),
                introspect("user.json"),
                introspect("previews"),
                introspect("pages")
        )
    }

    fun introspect(fileName: String): SketchElement {
        val file = fixture.file(fileName)
        if (file is VirtualDirectory) return introspectDirectory(file)
        if (file is BinaryFile) return FileElementFactory.of(file.name()).create(file.name(), MockElement())
        val path = fixture.path(fileName)
        val content = fixture.content(fileName, path)
        val json = JsonParser().parse(content)
        val element = fromJsonElement(json)
        return FileElementFactory.of(fileName).create(fileName, element)
    }

    fun fromJsonElement(element: JsonElement): SketchElement {
        return JsonMappingRule.forElement(element).apply(element, this)
    }

    fun fromJsonElement(element: JsonElement, level: IntrospectionLevel): SketchElement {
        return withLevel(level) { JsonMappingRule.forElement(element).apply(element, this) }
    }

    fun fromJsonElement(key: String, element: JsonElement): SketchElement {
        return fromJsonElement(key, element, levelOf(key))
    }

    fun fromJsonElement(key: String, element: JsonElement, level: IntrospectionLevel): SketchElement {
        return withLevel(level) { SemanticsRule.of(key).read(key, element, this) }
    }

    private fun withLevel(level: IntrospectionLevel, constructor: () -> SketchElement): SketchElement {
        levelStack.push(level)
        try {
            return constructor()
        }
        finally {
            levelStack.pop()
        }
    }

    private fun introspectDirectory(directory: VirtualDirectory): SketchElement {
        val array = directory.children().map { introspect(it.name()) }.toTypedArray()
        return DirectoryElement(directory.name(), ListElement(array))
    }

}