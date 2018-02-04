package com.dystopia.sketch.traversal.pair

import com.dystopia.api.PairTransformingTraverser
import com.dystopia.sketch.api.SketchElement

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