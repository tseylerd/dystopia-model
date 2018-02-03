package com.dystopia.model.traversal

import com.dystopia.model.PairTransformingTraverser
import com.dystopia.model.sketch.api.SketchAtom
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.traversal.SketchPairTraversal

object AtomTraversal : SketchPairTraversal {
    override fun traverse(first: SketchElement, second: SketchElement, traverser: PairTransformingTraverser<*, SketchElement>): SketchElement? {
        @Suppress("UNCHECKED_CAST")
        return (traverser as PairTransformingTraverser<SketchAtom, SketchElement>)
                .atoms(first as SketchAtom, second as SketchAtom)
    }
}