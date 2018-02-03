package com.dystopia.model.traversal

import com.dystopia.model.PairTransformingTraverser
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.traversal.SketchPairTraversal

object ArrayTraversal: SketchPairTraversal {
    override fun traverse(first: SketchElement, second: SketchElement, traverser: PairTransformingTraverser<*, SketchElement>): SketchElement {
        val result = ArrayList<SketchElement>()
        for (i in 0..maxOf(first.children.size, second.children.size)) {
            val firstChild = first.children.getOrNull(i)
            val secondChild = second.children.getOrNull(i)
            val resultElement = traverser.traverse(firstChild, secondChild) ?: continue
            result.add(resultElement)
        }
        return first.reproduce(result.toTypedArray())
    }
}