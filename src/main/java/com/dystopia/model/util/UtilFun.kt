package com.dystopia.model.util

import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.model.SketchModel
import com.dystopia.model.sketch.model.SketchModelBuilder

fun SketchElement.model() : SketchModel {
    val builder = SketchModelBuilder()
    buildModel(builder)
    return builder.build()
}