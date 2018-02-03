package com.dystopia.model.sketch.element

import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.model.SketchModelBuilder
import com.dystopia.model.sketch.traversal.transform.FilteringTraverser
import com.dystopia.model.sketch.util.SketchAttributes

class DocumentElement(name: String, child: SketchElement) : FileElement(name, child) {
    override fun createElement(name: String, child: SketchElement): FileElement {
        return DocumentElement(name, child)
    }

    override fun buildModel(builder: SketchModelBuilder) {
        builder.documentFile {
            FilteringTraverser({ !(it is IdBasedSketchElement && it.id == SketchAttributes.pages) }).transform(child).buildModel(builder)
        }
    }
}