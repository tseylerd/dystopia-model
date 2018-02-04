package com.dystopia.sketch.element

import com.dystopia.sketch.serialization.SerializationSession
import com.dystopia.sketch.api.OneChildElement
import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.model.SketchModelBuilder

class DirectoryElement(val name: String, override val child: SketchElement): OneChildElement {
    override val children: Array<SketchElement>
        get() = arrayOf(child)

    override fun serialize(session: SerializationSession) {
        session.inDirectory(name) { child.serialize(it) }
    }

    override fun sameAs(another: SketchElement): Boolean {
        return another is DirectoryElement && another.name == name
    }

    override fun reproduce(child: SketchElement): SketchElement {
        return DirectoryElement(name, child)
    }

    override fun buildModel(builder: SketchModelBuilder) {
        child.buildModel(builder)
    }

    override fun toString(): String {
        return name
    }
}
