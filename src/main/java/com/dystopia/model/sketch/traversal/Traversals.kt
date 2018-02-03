package com.dystopia.model.sketch.traversal

import com.dystopia.model.PairTransformingTraverser
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.traversal.SimpleTraversalFactory

object Traversals {
    private var factory: SketchTraversalFactory = MockFactory

    private object MockFactory: SketchTraversalFactory {
        override fun traversalOf(clazz: Class<*>): SketchPairTraversal {
            return MockTraversal
        }
    }

    object MockTraversal: SketchPairTraversal {
        override fun traverse(first: SketchElement, second: SketchElement, traverser: PairTransformingTraverser<*, SketchElement>): SketchElement? {
            throw NotImplementedError()
        }
    }

    fun setFactory(factory: SketchTraversalFactory) {
        this.factory = factory
    }

    fun traversalOf(element: SketchElement): SketchPairTraversal {
        setFactory(SimpleTraversalFactory)
        return factory.traversalOf(element::class.java)
    }
}