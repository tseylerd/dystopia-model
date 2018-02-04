package com.dystopia.sketch.traversal.pair

import com.dystopia.api.PairTransformingTraverser
import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.element.RootElement

object RootTraversal: SketchPairTraversal {
    override fun traverse(first: SketchElement, second: SketchElement, traverser: PairTransformingTraverser<*, SketchElement>): SketchElement? {
        val firstRoot = first as RootElement
        val secondRoot = second as RootElement
        return first.reproduce(
                arrayOf(
                        traverser.traverse(firstRoot.meta, secondRoot.meta)!!,
                        traverser.traverse(firstRoot.document, secondRoot.document)!!,
                        traverser.traverse(firstRoot.user, secondRoot.user)!!,
                        traverser.traverse(firstRoot.previews, secondRoot.previews)!!,
                        traverser.traverse(firstRoot.pages, secondRoot.pages)!!
                )
        )
    }
}