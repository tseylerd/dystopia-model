package com.dystopia.model.sketch.traversal

import com.dystopia.model.PairTransformingTraverser
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.traversal.SimpleTraversalFactory

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