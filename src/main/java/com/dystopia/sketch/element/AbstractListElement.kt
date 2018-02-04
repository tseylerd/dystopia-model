package com.dystopia.sketch.element

import com.dystopia.sketch.serialization.SerializationSession
import com.dystopia.sketch.api.Identifier
import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.model.SketchModelBuilder

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