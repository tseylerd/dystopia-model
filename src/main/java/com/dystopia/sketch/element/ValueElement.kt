package com.dystopia.sketch.element

import com.dystopia.api.provider.DiffProvider
import com.dystopia.api.provider.MergeProvider
import com.dystopia.sketch.serialization.SerializationSession
import com.dystopia.sketch.api.SketchAtom
import com.dystopia.sketch.api.SketchElement
import com.google.gson.stream.JsonWriter

abstract class ValueElement<T>(val value: T) : SketchElement, SketchAtom {
    override fun serialize(session: SerializationSession) {
        session.json { write(it, value) }
    }

    protected abstract fun write(it: JsonWriter, value: T)

    override fun merge(another: SketchElement, provider: MergeProvider<SketchElement>): SketchElement {
        return provider.zip(this, another)
    }

    override fun diff(another: SketchElement, provider: DiffProvider<SketchElement>): SketchElement {
        return if (another == this) provider.same(another) else provider.different(this, another)
    }

    override fun sameAs(another: SketchElement): Boolean = another is ValueElement<*> && another.value == value

    override fun equals(other: Any?): Boolean {
        return other is ValueElement<*> && value == other.value
    }

    override fun hashCode(): Int {
        return value!!.hashCode()
    }

    override fun reproduce(children: Array<SketchElement>): SketchElement {
        return this
    }

    class StringElement(value: String): ValueElement<String>(value) {
        override fun write(it: JsonWriter, value: String) {
            it.value(value)
        }
    }

    class BoolElement(value: Boolean): ValueElement<Boolean>(value) {
        override fun write(it: JsonWriter, value: Boolean) {
            it.value(value)
        }
    }

    class NumberElement(value: Number): ValueElement<Number>(value) {
        override fun write(it: JsonWriter, value: Number) {
            it.value(value)
        }
    }

    class NullElement: ValueElement<Unit>(Unit) {
        override fun write(it: JsonWriter, value: Unit) {
            it.nullValue()
        }
    }

    override fun toString(): String {
        return value.toString()
    }
}