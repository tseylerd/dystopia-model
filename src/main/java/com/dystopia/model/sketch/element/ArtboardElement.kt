package com.dystopia.model.sketch.element

import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.model.Artboard
import com.dystopia.model.sketch.model.Layer

open class ArtboardElement(id: String, name: String, children: Array<SketchElement>) : LayerElement(id, name, children) {

    override fun layer(children: Array<SketchElement>): Layer {
        return Artboard(id, name, children)
    }

    override fun reproduce(children: Array<SketchElement>): AbstractListElement {
        return ArtboardElement(id, name, children)
    }
}