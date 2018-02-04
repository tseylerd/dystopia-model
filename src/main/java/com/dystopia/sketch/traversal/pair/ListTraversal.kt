package com.dystopia.sketch.traversal.pair

import com.dystopia.api.PairTransformingTraverser
import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.util.traverseArrays

object ListTraversal : SketchPairTraversal {
    override fun traverse(first: SketchElement, second: SketchElement, traverser: PairTransformingTraverser<*, SketchElement>): SketchElement {
            return first.reproduce(traverseArrays(traverser, first.children, second.children).toTypedArray())
    }
}