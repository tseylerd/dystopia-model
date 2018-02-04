package com.dystopia.sketch.introspection

import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.element.*
import com.dystopia.sketch.util.SketchAttributes
import com.google.gson.JsonElement

enum class SemanticsRule(vararg val keys: String) {
    LAYERS(SketchAttributes.layers) {
        override fun read(key: String, value: JsonElement, introspector: SketchIntrospector): SketchElement {
            return LayersElement(key, introspector.fromJsonElement(value))
        }
    },
    IDENTIFIER(SketchAttributes.objectId, SketchAttributes.ref) {
        override fun read(key: String, value: JsonElement, introspector: SketchIntrospector): SketchElement {
            return ObjectId(key, value.asString)
        }
    },
    CLASS(SketchAttributes.clazz) {
        override fun read(key: String, value: JsonElement, introspector: SketchIntrospector): SketchElement {
            return ClassElement(value.asString)
        }
    },
    NAME(SketchAttributes.name) {
        override fun read(key: String, value: JsonElement, introspector: SketchIntrospector): SketchElement {
            return NameElement(value.asString)
        }
    },
    DEFAULT() {
        override fun read(key: String, value: JsonElement, introspector: SketchIntrospector): SketchElement {
            return IdBasedSketchElement(key, introspector.fromJsonElement(value))
        }
    };

    abstract fun read(key: String, value: JsonElement, introspector: SketchIntrospector): SketchElement

    companion object {
        fun of(key: String): SemanticsRule {
            return values().firstOrNull { key in it.keys } ?: DEFAULT
        }
    }
}