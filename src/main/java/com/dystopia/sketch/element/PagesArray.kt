package com.dystopia.sketch.element

import com.dystopia.sketch.api.SketchElement

class PagesArray(children: Array<SketchElement>): ArrayElement(children) {
    val pages: MutableList<String> = ArrayList()

    override fun reproduce(children: Array<SketchElement>): ArrayElement {
        return PagesArray(children)
    }
}