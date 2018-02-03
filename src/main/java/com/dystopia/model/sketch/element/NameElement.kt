package com.dystopia.model.sketch.element

import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.util.SketchAttributes

class NameElement(value: String): PropertyElement(SketchAttributes.name, value) {
    fun withName(id: String): SketchElement {
        return NameElement(id)
    }
}