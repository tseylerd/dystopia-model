package com.dystopia.sketch.model

import com.dystopia.api.FamilyMember
import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.element.LayerElement
import com.dystopia.sketch.element.NameElement
import com.dystopia.sketch.element.ObjectId
import com.dystopia.sketch.util.SketchAttributes
import com.dystopia.sketch.util.objectElement

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