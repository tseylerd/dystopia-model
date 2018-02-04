package com.dystopia.sketch.element

import com.dystopia.sketch.serialization.SerializationSession
import com.dystopia.sketch.api.IdBasedElement
import com.dystopia.sketch.api.OneChildElement
import com.dystopia.sketch.api.SketchElement

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