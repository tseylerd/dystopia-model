package com.dystopia.sketch.element

import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.model.SketchModelBuilder

class LayersElement(id: String, child: SketchElement) : IdBasedSketchElement(id, child) {
    override fun reproduce(child: SketchElement): IdBasedSketchElement {
        return LayersElement(id, child)
    }

    override fun buildModel(builder: SketchModelBuilder) {
        child.buildModel(builder)
    }
}