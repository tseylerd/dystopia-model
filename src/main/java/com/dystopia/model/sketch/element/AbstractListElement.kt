package com.dystopia.model.sketch.element

import com.dystopia.model.serialization.sketch.SerializationSession
import com.dystopia.model.sketch.api.Identifier
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.model.SketchModelBuilder

abstract class AbstractListElement(override val children: Array<SketchElement>) : SketchElement {

    override fun serialize(session: SerializationSession) {
        children.forEach { element -> element.serialize(session) }
    }

    override fun buildModel(builder: SketchModelBuilder) {
        for (child in children) {
            child.buildModel(builder)
        }
    }

    override fun sameAs(another: SketchElement): Boolean {
        if (another !is AbstractListElement) return false
        val myIdentifier = identifier() ?: return false
        val elementIdentifier = another.identifier() ?: return false
        return myIdentifier.identity() == elementIdentifier.identity()
    }

    abstract fun identifier(): Identifier?

    override fun toString(): String {
        return children.joinToString { it.toString() }
    }
}