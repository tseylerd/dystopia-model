package com.dystopia.sketch.traversal.pair

import com.dystopia.api.PairTransformingTraverser
import com.dystopia.sketch.api.SketchAtom
import com.dystopia.sketch.api.SketchElement

object AtomTraversal : SketchPairTraversal {
    override fun traverse(first: SketchElement, second: SketchElement, traverser: PairTransformingTraverser<*, SketchElement>): SketchElement? {
        @Suppress("UNCHECKED_CAST")
        return (traverser as PairTransformingTraverser<SketchAtom, SketchElement>)
                .atoms(first as SketchAtom, second as SketchAtom)
    }
}