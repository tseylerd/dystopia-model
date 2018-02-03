package com.dystopia.model.sketch.api

interface OneChildElement: SketchElement {
    override val children: Array<SketchElement>
        get() = arrayOf(child)

    override fun reproduce(children: Array<SketchElement>): SketchElement {
        return reproduce(children.first())
    }

    fun reproduce(child: SketchElement): SketchElement
}