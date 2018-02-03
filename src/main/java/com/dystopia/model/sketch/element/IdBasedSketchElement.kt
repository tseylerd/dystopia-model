package com.dystopia.model.sketch.element

import com.dystopia.model.serialization.sketch.SerializationSession
import com.dystopia.model.sketch.api.IdBasedElement
import com.dystopia.model.sketch.api.OneChildElement
import com.dystopia.model.sketch.api.SketchElement

open class IdBasedSketchElement(val id: String, override val child: SketchElement): OneChildElement, IdBasedElement {
    override fun serialize(session: SerializationSession) {
        session.json {
            it.name(id)
            child.serialize(session)
        }
    }

    override fun id(): String = id

    override fun sameAs(another: SketchElement): Boolean = another is IdBasedSketchElement && another.id == id

    override fun equals(other: Any?): Boolean {
        return other is IdBasedSketchElement && id == other.id && child == other.child
    }

    override fun hashCode(): Int = id.hashCode() + child.hashCode()

    override fun reproduce(child: SketchElement): IdBasedSketchElement {
        return IdBasedSketchElement(id, child)
    }

    override fun toString(): String {
        return id
    }
}