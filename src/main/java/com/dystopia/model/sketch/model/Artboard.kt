package com.dystopia.model.sketch.model

import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.element.ArtboardElement
import com.dystopia.model.sketch.element.LayerElement
import com.dystopia.model.sketch.element.PropertyElement
import com.dystopia.model.sketch.util.SketchAttributes
import com.dystopia.model.sketch.util.objectWithId

class Artboard(id: String, name: String, element: Array<SketchElement>): Layer(id, name, element) {
    override fun reproduce(id: String, name: String, child: Array<SketchElement>): Layer {
        return Artboard(id, name, child)
    }

    override fun createElement(children: Array<SketchElement>): LayerElement {
        return ArtboardElement(id, name, children)
    }

    fun metaElement() : SketchElement {
        return objectWithId(id) { PropertyElement(SketchAttributes.name, name) }
    }
}