package com.dystopia.sketch.element

import com.dystopia.sketch.api.SketchElement

class SymbolElement(id: String, name: String, children: Array<SketchElement>): ArtboardElement(id, name, children) {
    override fun reproduce(children: Array<SketchElement>): AbstractListElement {
        return SymbolElement(id, name, children)
    }
}