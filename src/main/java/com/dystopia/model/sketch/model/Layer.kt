package com.dystopia.model.sketch.model

import com.dystopia.model.sketch.api.FamilyMember
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.element.LayerElement
import com.dystopia.model.sketch.element.NameElement
import com.dystopia.model.sketch.element.ObjectId
import com.dystopia.model.sketch.util.SketchAttributes
import com.dystopia.model.sketch.util.objectElement

open class Layer(val id: String, val name: String, val children: Array<SketchElement>): FamilyMember<Layer> {

    override fun sameAs(another: Layer): Boolean {
        return id == another.id
    }

    fun element(): SketchElement {
        return objectElement {
            createElement(children.plus(ObjectId(SketchAttributes.objectId, id)).plus(NameElement(name)))
        }
    }

    protected open fun createElement(children: Array<SketchElement>) = LayerElement(id, name, children)

    open fun reproduce(id: String, name: String, child: Array<SketchElement>): Layer {
        return Layer(id, name, child)
    }
}