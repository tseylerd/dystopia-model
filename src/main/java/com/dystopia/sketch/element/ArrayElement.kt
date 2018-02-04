package com.dystopia.sketch.element

import com.dystopia.sketch.serialization.SerializationSession
import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.model.SketchModelBuilder

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