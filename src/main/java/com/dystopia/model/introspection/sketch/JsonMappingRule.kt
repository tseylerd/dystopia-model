package com.dystopia.model.introspection.sketch

import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.element.ValueElement
import com.dystopia.model.sketch.element.WrapperElement
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

enum class JsonMappingRule {
    OBJECT {
        override fun apply(element: JsonElement, introspector: SketchIntrospector): SketchElement {
            return WrapperElement.Object(
                    introspector.level.listOfElements(
                            element.asJsonObject.entrySet()
                                    .map {
                                        introspector.level.element(it.key, it.value, introspector)
                                    }.toTypedArray()
                    )
            )
        }

        override fun isSuitableForElement(element: JsonElement): Boolean = element is JsonObject
    },
    PRIMITIVE {
        override fun apply(element: JsonElement, introspector: SketchIntrospector): SketchElement {
            return when {
                element.isJsonNull                 -> ValueElement.NullElement()
                element.asJsonPrimitive.isBoolean  -> ValueElement.BoolElement(element.asBoolean)
                element.asJsonPrimitive.isNumber   -> ValueElement.NumberElement(element.asNumber)
                else                               -> ValueElement.StringElement(element.asString)
            }
        }

        override fun isSuitableForElement(element: JsonElement): Boolean {
          return element.isJsonPrimitive || element.isJsonNull
        }
    },
    ARRAY {
        override fun apply(element: JsonElement, introspector: SketchIntrospector): SketchElement =
                WrapperElement.Array(introspector.level.listOfElements(
                        element.asJsonArray.map {
                            introspector.level.element(it, introspector)
                        }.toTypedArray()
                ))

        override fun isSuitableForElement(element: JsonElement): Boolean = element is JsonArray
    };

    abstract fun apply(element: JsonElement, introspector: SketchIntrospector): SketchElement
    abstract fun isSuitableForElement(element: JsonElement): Boolean

    companion object {
        fun forElement(element: JsonElement): JsonMappingRule = values().first { it.isSuitableForElement(element) }
    }
}