package com.dystopia.sketch.api

import com.dystopia.api.Element
import com.dystopia.sketch.serialization.SerializationSession
import com.dystopia.sketch.model.SketchModelBuilder
import com.dystopia.sketch.traversal.pair.SketchPairTraversal
import com.dystopia.sketch.traversal.pair.Traversals

interface SketchElement : Element<SketchElement> {
    override fun sameAs(another: SketchElement): Boolean

    fun serialize(session: SerializationSession)
    fun reproduce(children: Array<SketchElement>): SketchElement

    override val children: Array<SketchElement>
    override val pairTraversal: SketchPairTraversal
        get() = Traversals.traversalOf(this)

    fun buildModel(builder: SketchModelBuilder) {
        builder.element(this)
    }

    val child: SketchElement
        get() = children.first()
}