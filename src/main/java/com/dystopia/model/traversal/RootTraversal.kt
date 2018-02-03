package com.dystopia.model.traversal

import com.dystopia.model.PairTransformingTraverser
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.element.RootElement
import com.dystopia.model.sketch.traversal.SketchPairTraversal

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