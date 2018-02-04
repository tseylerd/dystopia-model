package com.dystopia.sketch.element

import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.util.SketchAttributes

class NameElement(value: String): PropertyElement(SketchAttributes.name, value) {
    fun withName(id: String): SketchElement {
        return NameElement(id)
    }
}