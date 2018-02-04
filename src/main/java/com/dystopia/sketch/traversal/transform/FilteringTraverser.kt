package com.dystopia.sketch.traversal.transform

import com.dystopia.api.TransformingTraverser
import com.dystopia.sketch.api.SketchElement

class FilteringTraverser(private val condition: (SketchElement) -> Boolean) : TransformingTraverser<SketchElement, SketchElement> {
    override fun transform(element: SketchElement): SketchElement {
        return element.reproduce(element.children.filter(condition).map { transform(it) }.toTypedArray())
    }
}