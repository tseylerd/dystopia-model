package com.dystopia.model.sketch.element

import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.model.SketchModelBuilder

class LayersElement(id: String, child: SketchElement) : IdBasedSketchElement(id, child) {
    override fun reproduce(child: SketchElement): IdBasedSketchElement {
        return LayersElement(id, child)
    }

    override fun buildModel(builder: SketchModelBuilder) {
        child.buildModel(builder)
    }
}