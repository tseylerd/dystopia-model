package com.dystopia.model.sketch.element

import com.dystopia.model.sketch.api.Identifier

class ObjectId(id: String, value: String): PropertyElement(id, value), Identifier {
    override fun identity(): String = value
}