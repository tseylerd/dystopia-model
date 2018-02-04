package com.dystopia.sketch.element

import com.dystopia.sketch.api.Identifier
import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.model.Layer
import com.dystopia.sketch.model.SketchModelBuilder
import com.dystopia.sketch.util.SketchAttributes

open class LayerElement(val id: String, val name: String, children: Array<SketchElement>): AbstractListElement(children) {
    override fun identifier(): Identifier? {
        return ObjectId(SketchAttributes.objectId, id)
    }

    override fun reproduce(children: Array<SketchElement>): AbstractListElement {
        return LayerElement(id, name, children)
    }

    open fun layer(children: Array<SketchElement>): Layer {
        return Layer(id, name, children)
    }

    override fun buildModel(builder: SketchModelBuilder) {
        builder.layer(layer(children.filter { it !is ObjectId && it !is NameElement }.toTypedArray()))
    }

    override fun toString(): String {
        return "layer"
    }
}