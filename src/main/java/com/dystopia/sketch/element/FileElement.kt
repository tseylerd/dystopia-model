package com.dystopia.sketch.element

import com.dystopia.sketch.serialization.SerializationSession
import com.dystopia.sketch.api.IdElement
import com.dystopia.sketch.api.OneChildElement
import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.model.SketchModelBuilder

open class FileElement(val name: String, override val child: SketchElement): OneChildElement, IdElement {
    override fun sameAs(another: SketchElement): Boolean {
        return another is FileElement && another.name == name
    }

    override fun serialize(session: SerializationSession) {
        session.inFile(name) { child.serialize(it) }
    }

    override fun reproduce(child: SketchElement): SketchElement {
        return createElement(name, child)
    }

    override fun withId(id: String): SketchElement {
        return createElement(id + ".json", child)
    }

    open fun createElement(name: String, child: SketchElement) : FileElement {
        return FileElement(name, child)
    }

    override fun buildModel(builder: SketchModelBuilder) {
        child.buildModel(builder)
    }

    override fun toString(): String {
        return name
    }
}