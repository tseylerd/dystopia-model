package com.dystopia.sketch.element

import com.dystopia.api.provider.DiffProvider
import com.dystopia.api.provider.MergeProvider
import com.dystopia.sketch.serialization.SerializationSession
import com.dystopia.sketch.api.SketchAtom
import com.dystopia.sketch.api.SketchElement

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