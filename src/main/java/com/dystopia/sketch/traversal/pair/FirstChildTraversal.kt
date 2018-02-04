package com.dystopia.sketch.traversal.pair

import com.dystopia.api.PairTransformingTraverser
import com.dystopia.sketch.api.SketchElement

object FirstChildTraversal : SketchPairTraversal {
    override fun traverse(first: SketchElement, second: SketchElement, traverser: PairTransformingTraverser<*, SketchElement>): SketchElement? {
        return first.reproduce(arrayOf(traverser.traverse(first.children.first(), second.children.first())!!))
    }
}