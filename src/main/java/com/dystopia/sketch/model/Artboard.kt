package com.dystopia.sketch.model

import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.element.ArtboardElement
import com.dystopia.sketch.element.LayerElement
import com.dystopia.sketch.element.PropertyElement
import com.dystopia.sketch.util.SketchAttributes
import com.dystopia.sketch.util.objectWithId

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