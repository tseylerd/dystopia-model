package com.dystopia.sketch.element

import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.model.SketchModelBuilder
import com.dystopia.sketch.util.SketchAttributes

class PageElement(val id: String, val name: String, children: Array<SketchElement>): AbstractListElement(children) {
    override fun reproduce(children: Array<SketchElement>): AbstractListElement {
        return PageElement(id, name, children)
    }

    override fun identifier(): ObjectId? {
        return ObjectId(SketchAttributes.objectId, id)
    }

    override fun buildModel(builder: SketchModelBuilder) {
        builder.inPage(id, name) { children.forEach { it.buildModel(builder) } } // todo sorting of pages
    }

    override fun toString(): String {
        return id
    }
}