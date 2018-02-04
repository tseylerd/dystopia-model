package com.dystopia.sketch.element

import com.dystopia.sketch.serialization.SerializationSession
import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.model.SketchModelBuilder

class RootElement(
        val name: String,
        val meta: SketchElement,
        val document: SketchElement,
        val user: SketchElement,
        val previews: SketchElement,
        val pages: SketchElement
) : SketchElement {

    override val children: Array<SketchElement>
        get() = arrayOf(meta, document, user, previews, pages)

    override fun serialize(session: SerializationSession) {
        meta.serialize(session)
        document.serialize(session)
        user.serialize(session)
        previews.serialize(session)
        pages.serialize(session)
    }

    override fun sameAs(another: SketchElement) = another is RootElement

    override fun reproduce(children: Array<SketchElement>): SketchElement {
        return RootElement(
                name,
                children[0],
                children[1],
                children[2],
                children[3],
                children[4]
        )
    }

    override fun buildModel(builder: SketchModelBuilder) {
        meta.buildModel(builder)
        document.buildModel(builder)
        user.buildModel(builder)
        pages.buildModel(builder)
        builder.previewsElement(previews)
    }
}