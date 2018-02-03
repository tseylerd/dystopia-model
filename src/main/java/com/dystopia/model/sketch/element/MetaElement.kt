package com.dystopia.model.sketch.element

import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.model.SketchModelBuilder
import com.dystopia.model.sketch.traversal.transform.FilteringTraverser
import com.dystopia.model.sketch.util.SketchAttributes

class MetaElement(name: String, child: SketchElement) : FileElement(name, child) {
    override fun createElement(name: String, child: SketchElement): FileElement {
        return MetaElement(name, child)
    }

    override fun buildModel(builder: SketchModelBuilder) {
        builder.metaFile {
            FilteringTraverser({
                !(it is IdBasedSketchElement && it.id == SketchAttributes.pagesAndArtboards)
            }).transform(child).buildModel(builder)
        }
    }
}