package com.dystopia.model.sketch.element

import com.dystopia.model.sketch.api.Identifier
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.model.Layer
import com.dystopia.model.sketch.model.SketchModelBuilder
import com.dystopia.model.sketch.util.SketchAttributes

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