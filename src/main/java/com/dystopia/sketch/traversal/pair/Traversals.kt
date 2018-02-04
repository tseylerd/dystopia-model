package com.dystopia.sketch.traversal.pair

import com.dystopia.api.PairTransformingTraverser
import com.dystopia.sketch.api.SketchElement

object Traversals {
    private var factory: SketchTraversalFactory = SimpleTraversalFactory

    object MockTraversal: SketchPairTraversal {
        override fun traverse(first: SketchElement, second: SketchElement, traverser: PairTransformingTraverser<*, SketchElement>): SketchElement? {
            throw NotImplementedError()
        }
    }

    fun traversalOf(element: SketchElement): SketchPairTraversal {
        return factory.traversalOf(element::class.java)
    }
}