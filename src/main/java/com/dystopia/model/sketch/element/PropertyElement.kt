package com.dystopia.model.sketch.element

import com.dystopia.model.provider.DiffProvider
import com.dystopia.model.provider.MergeProvider
import com.dystopia.model.serialization.sketch.SerializationSession
import com.dystopia.model.sketch.api.IdBasedElement
import com.dystopia.model.sketch.api.SketchAtom
import com.dystopia.model.sketch.api.SketchElement

open class PropertyElement(private val key: String, val value: String) : IdBasedElement, SketchAtom {
    override fun id(): String = key

    override fun sameAs(another: SketchElement): Boolean = another is PropertyElement && another.key == key

    override fun diff(another: SketchElement, provider: DiffProvider<SketchElement>): SketchElement {
        return if (another == this) provider.same(this) else provider.different(this, another)
    }

    override fun reproduce(children: Array<SketchElement>): SketchElement {
        return this
    }

    override fun merge(another: SketchElement, provider: MergeProvider<SketchElement>): SketchElement {
        return provider.zip(this, another)
    }

    override fun serialize(session: SerializationSession) {
        session.json { it.name(key).value(value) }
    }

    override fun equals(other: Any?): Boolean = other is PropertyElement && value == other.value && key == other.key

    override fun hashCode(): Int = key.hashCode() + value.hashCode()

    override fun toString(): String {
        return key + ": " + value
    }
}