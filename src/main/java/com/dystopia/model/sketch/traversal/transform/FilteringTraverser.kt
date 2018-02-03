package com.dystopia.model.sketch.traversal.transform

import com.dystopia.model.TransformingTraverser
import com.dystopia.model.sketch.api.SketchElement

class FilteringTraverser(private val condition: (SketchElement) -> Boolean) : TransformingTraverser<SketchElement, SketchElement> {
    override fun transform(element: SketchElement): SketchElement {
        return element.reproduce(element.children.filter(condition).map { transform(it) }.toTypedArray())
    }
}