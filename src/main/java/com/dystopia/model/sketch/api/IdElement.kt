package com.dystopia.model.sketch.api

interface IdElement : SketchElement {
    fun withId(id: String): SketchElement
}