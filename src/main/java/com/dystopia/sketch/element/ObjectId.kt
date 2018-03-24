package com.dystopia.sketch.element

import com.dystopia.sketch.api.IdElement
import com.dystopia.sketch.api.Identifier
import com.dystopia.sketch.api.SketchElement

class ObjectId(id: String, value: String): PropertyElement(id, value), Identifier, IdElement {
    override fun withId(id: String): SketchElement = ObjectId(key, id)

    override fun identity(): String = value
}