package com.dystopia.sketch.util

import com.dystopia.model.TransformingTraverser
import com.dystopia.model.introspection.sketch.SketchIntrospector
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.model.SketchModel
import com.dystopia.model.sketch.model.SketchModelBuilder
import com.dystopia.model.vfs.Archive
import com.dystopia.model.vfs.FileFixture
import com.dystopia.model.vfs.FileSystemEntry
import com.dystopia.model.vfs.VirtualDirectory
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