package com.dystopia.model.sketch.util

import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.element.ArrayElement
import com.dystopia.model.sketch.element.IdBasedSketchElement
import com.dystopia.model.sketch.element.ListElement
import com.dystopia.model.sketch.element.WrapperElement

fun objectList(elements: () -> Array<SketchElement>): SketchElement {
    return objectElement { ListElement(elements()) }
}

fun objectElement(element: () -> SketchElement): SketchElement {
    return WrapperElement.Object(element())
}

fun arrayList(elements: () -> Array<SketchElement>): SketchElement {
    return WrapperElement.Array(ListElement(elements()))
}

fun array(elements: () -> Array<SketchElement>): SketchElement {
    return WrapperElement.Array(ArrayElement(elements()))
}

fun idBased(id: String, element: () -> SketchElement): SketchElement {
    return IdBasedSketchElement(id, element())
}

fun objectWithId(id: String, element: () -> SketchElement): SketchElement {
    return idBased(id) { WrapperElement.Object(element()) }
}

fun listElement(elements: () -> Array<SketchElement>): SketchElement {
    return ListElement(elements())
}

fun concatenationOf(arrays: () -> Array<SketchElement>): Concatenation {
    return Concatenation(arrays())
}

fun concatenationOfElement(element: () -> SketchElement): Concatenation {
    return Concatenation(element())
}

class Concatenation {
    val elements: Array<SketchElement>

    constructor(elements: Array<SketchElement>) {
        this.elements = elements
    }

    constructor(element: SketchElement) {
        this.elements = arrayOf(element)
    }

    fun with(element: () -> SketchElement): Array<SketchElement> {
        return elements.plus(element())
    }
}