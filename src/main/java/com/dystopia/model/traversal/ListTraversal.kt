package com.dystopia.model.traversal

import com.dystopia.model.PairTransformingTraverser
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.traversal.SketchPairTraversal
import com.dystopia.model.util.traverseArrays

object ListTraversal : SketchPairTraversal {
    override fun traverse(first: SketchElement, second: SketchElement, traverser: PairTransformingTraverser<*, SketchElement>): SketchElement {
            return first.reproduce(traverseArrays(traverser, first.children, second.children).toTypedArray())
    }
}