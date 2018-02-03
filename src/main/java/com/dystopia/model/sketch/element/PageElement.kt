package com.dystopia.model.sketch.element

import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.model.SketchModelBuilder
import com.dystopia.model.sketch.util.SketchAttributes

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