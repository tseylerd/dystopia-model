package com.dystopia.sketch.util

import com.dystopia.api.TransformingTraverser
import com.dystopia.sketch.introspection.SketchIntrospector
import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.model.SketchModel
import com.dystopia.sketch.model.SketchModelBuilder
import com.dystopia.api.vfs.Archive
import com.dystopia.api.vfs.FileFixture
import com.dystopia.api.vfs.FileSystemEntry
import com.dystopia.api.vfs.VirtualDirectory
import kotlin.test.assertTrue

fun FileSystemEntry.assertArchive(): FileSystemEntry {
    assertTrue { this is Archive }
    return this
}

fun FileSystemEntry.fixture(): FileFixture {
    return FileFixture(this as VirtualDirectory)
}

fun FileFixture.introspect(): SketchModel {
    val builder = SketchModelBuilder()
    val element = SketchIntrospector(this).introspect()
    element.buildModel(builder)
    return builder.build()
}

fun FileFixture.introspectElement(): SketchElement {
    return SketchIntrospector(this).introspect()
}

fun SketchModel.toExpectedElement(): SketchElement {
    return asElement("_expected")
}

fun SketchElement.sort(): SketchElement {
    val traverser: TransformingTraverser<SketchElement, SketchElement> = object : TransformingTraverser<SketchElement, SketchElement> {
        override fun transform(element: SketchElement): SketchElement {
            return if (element.children.isEmpty()) element else element.reproduce(element.children.map { transform(it) }.sortedBy { it.toString() }.toTypedArray())
        }
    }
    return traverser.transform(this)
}