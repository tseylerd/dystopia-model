package com.dystopia.model.traversal

import com.dystopia.model.PairTransformingTraverser
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.traversal.SketchPairTraversal

object SimpleTraversal: SketchPairTraversal {
    override fun traverse(first: SketchElement, second: SketchElement, traverser: PairTransformingTraverser<*, SketchElement>): SketchElement? {
        return first.reproduce(arrayOf(traverser.traverse(first.child, second.child)!!))
    }
}