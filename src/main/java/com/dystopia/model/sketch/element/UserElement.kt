package com.dystopia.model.sketch.element

import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.model.SketchModelBuilder

class UserElement(name: String, child: SketchElement) : FileElement(name, child) {
    override fun createElement(name: String, child: SketchElement): FileElement {
        return UserElement(name, child)
    }

    override fun buildModel(builder: SketchModelBuilder) {
        builder.userElement(this)
    }
}