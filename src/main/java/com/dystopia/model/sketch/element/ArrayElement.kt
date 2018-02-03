package com.dystopia.model.sketch.element

import com.dystopia.model.serialization.sketch.SerializationSession
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.model.SketchModelBuilder

open class ArrayElement(override val children: Array<SketchElement>): SketchElement {
    override fun buildModel(builder: SketchModelBuilder) {
        for (child in children) {
            child.buildModel(builder)
        }
    }

    override fun serialize(session: SerializationSession) {
        for (child in children) {
            child.serialize(session)
        }
    }

    override fun sameAs(another: SketchElement): Boolean = false

    override fun reproduce(children: Array<SketchElement>): ArrayElement {
        return ArrayElement(children)
    }

    override fun toString(): String {
        return children.joinToString { it.toString() }
    }
}