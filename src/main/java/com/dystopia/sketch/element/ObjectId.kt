package com.dystopia.sketch.element

import com.dystopia.sketch.api.Identifier

class ObjectId(id: String, value: String): PropertyElement(id, value), Identifier {
    override fun identity(): String = value
}