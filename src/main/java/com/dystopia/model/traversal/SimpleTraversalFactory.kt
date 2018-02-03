package com.dystopia.model.traversal

import com.dystopia.model.sketch.api.OneChildElement
import com.dystopia.model.sketch.api.SketchAtom
import com.dystopia.model.sketch.element.AbstractListElement
import com.dystopia.model.sketch.element.ArrayElement
import com.dystopia.model.sketch.element.RootElement
import com.dystopia.model.sketch.element.WrapperElement
import com.dystopia.model.sketch.traversal.SketchPairTraversal
import com.dystopia.model.sketch.traversal.SketchTraversalFactory
import com.dystopia.model.sketch.traversal.Traversals

object SimpleTraversalFactory: SketchTraversalFactory {
    private val mapping = mapOf(
            AbstractListElement::class.java to ListTraversal,
            ArrayElement::class.java to ArrayTraversal,
            SketchAtom::class.java to AtomTraversal,
            OneChildElement::class.java to FirstChildTraversal,
            RootElement::class.java to RootTraversal,
            WrapperElement::class.java to SimpleTraversal
    )

    override fun traversalOf(clazz: Class<*>): SketchPairTraversal {
        for (keyClass in mapping.keys) {
            if (keyClass.isAssignableFrom(clazz)) return mapping[keyClass]!!
        }
        return Traversals.MockTraversal
    }
}