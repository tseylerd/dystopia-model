package com.dystopia.model.sketch.element

import com.dystopia.model.serialization.sketch.SerializationSession
import com.dystopia.model.sketch.api.OneChildElement
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.model.SketchModelBuilder

abstract class WrapperElement(val wrappee: SketchElement) : OneChildElement {
    override val children: kotlin.Array<SketchElement>
        get() = arrayOf(wrappee)

    override fun serialize(session: SerializationSession) {
        wrappee.serialize(session)
    }

    override fun sameAs(another: SketchElement): Boolean {
        return another is WrapperElement && wrappee.sameAs(another.wrappee)
    }

    override fun equals(other: Any?): Boolean {
        return other is WrapperElement && wrappee == other.wrappee
    }

    override fun hashCode(): Int {
        return wrappee.hashCode()
    }

    override fun buildModel(builder: SketchModelBuilder) {
        wrappee.buildModel(builder)
    }

    override fun toString(): String {
        return wrappee.toString()
    }

    open class Object(wrappee: SketchElement) : WrapperElement(wrappee) {

        override fun serialize(session: SerializationSession) {
            session.json {
                it.beginObject()
                super.serialize(session)
                it.endObject()
            }
        }

        override fun reproduce(child: SketchElement): SketchElement = Object(child)
    }

    open class Array(wrappee: SketchElement): WrapperElement(wrappee) {
        override fun serialize(session: SerializationSession) {
            session.json {
                it.beginArray()
                super.serialize(session)
                it.endArray()
            }
        }

        override fun reproduce(child: SketchElement): SketchElement = Array(child)
    }
}