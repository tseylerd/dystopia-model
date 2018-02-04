package com.dystopia.sketch.element

import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.model.SketchModelBuilder
import com.dystopia.sketch.traversal.transform.FilteringTraverser
import com.dystopia.sketch.util.SketchAttributes

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