package com.dystopia.sketch.traversal.pair

import com.dystopia.sketch.api.OneChildElement
import com.dystopia.sketch.api.SketchAtom
import com.dystopia.sketch.element.AbstractListElement
import com.dystopia.sketch.element.ArrayElement
import com.dystopia.sketch.element.RootElement
import com.dystopia.sketch.element.WrapperElement

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