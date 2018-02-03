package com.dystopia.model.traversal

import com.dystopia.model.PairTransformingTraverser
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.traversal.SketchPairTraversal

@Suppress("unused")
object UnsupportedTraversal: SketchPairTraversal {
    override fun traverse(first: SketchElement, second: SketchElement, traverser: PairTransformingTraverser<*, SketchElement>): SketchElement? {
        throw UnsupportedOperationException("Unsupported traversal")
    }
}