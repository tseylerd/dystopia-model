package com.dystopia.model.sketch.api

import com.dystopia.model.Atom
import com.dystopia.model.provider.DiffProvider
import com.dystopia.model.provider.MergeProvider

interface SketchAtom : SketchElement, Atom<SketchElement> {
    override val children: Array<SketchElement>
        get() = emptyArray()

    override fun diff(another: SketchElement, provider: DiffProvider<SketchElement>): SketchElement
    override fun merge(another: SketchElement, provider: MergeProvider<SketchElement>): SketchElement

    override fun reproduce(children: Array<SketchElement>): SketchElement {
        return this
    }
}