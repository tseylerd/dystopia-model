package com.dystopia.model.sketch.element

import com.dystopia.model.sketch.api.SketchElement

class PagesArray(children: Array<SketchElement>): ArrayElement(children) {
    val pages: MutableList<String> = ArrayList()

    override fun reproduce(children: Array<SketchElement>): ArrayElement {
        return PagesArray(children)
    }
}