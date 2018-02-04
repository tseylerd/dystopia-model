package com.dystopia.sketch.element

import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.model.SketchModelBuilder

class UserElement(name: String, child: SketchElement) : FileElement(name, child) {
    override fun createElement(name: String, child: SketchElement): FileElement {
        return UserElement(name, child)
    }

    override fun buildModel(builder: SketchModelBuilder) {
        builder.userElement(this)
    }
}