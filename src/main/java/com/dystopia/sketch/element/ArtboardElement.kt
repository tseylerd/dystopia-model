package com.dystopia.sketch.element

import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.model.Artboard
import com.dystopia.sketch.model.Layer

open class ArtboardElement(id: String, name: String, children: Array<SketchElement>) : LayerElement(id, name, children) {

    override fun layer(children: Array<SketchElement>): Layer {
        return Artboard(id, name, children)
    }

    override fun reproduce(children: Array<SketchElement>): AbstractListElement {
        return ArtboardElement(id, name, children)
    }
}