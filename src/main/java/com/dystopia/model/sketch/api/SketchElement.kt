package com.dystopia.model.sketch.api

import com.dystopia.model.Element
import com.dystopia.model.serialization.sketch.SerializationSession
import com.dystopia.model.sketch.model.SketchModelBuilder
import com.dystopia.model.sketch.traversal.SketchPairTraversal
import com.dystopia.model.sketch.traversal.Traversals

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