package com.dystopia.model.sketch.element

import com.dystopia.model.provider.DiffProvider
import com.dystopia.model.provider.MergeProvider
import com.dystopia.model.serialization.sketch.SerializationSession
import com.dystopia.model.sketch.api.SketchAtom
import com.dystopia.model.sketch.api.SketchElement

class MockElement: SketchAtom {
    override fun merge(another: SketchElement, provider: MergeProvider<SketchElement>): SketchElement {
        return this
    }

    override fun diff(another: SketchElement, provider: DiffProvider<SketchElement>): SketchElement {
        return this
    }

    override fun serialize(session: SerializationSession) {
        session.plain("<binary content>")
    }

    override fun sameAs(another: SketchElement): Boolean {
        return true
    }
}