package com.dystopia.sketch.api

interface IdElement : SketchElement {
    fun withId(id: String): SketchElement
}