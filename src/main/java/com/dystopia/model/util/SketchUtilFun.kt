package com.dystopia.model.util

import com.dystopia.model.PairTransformingTraverser
import com.dystopia.model.sketch.api.FamilyMember
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.model.SketchModel
import com.dystopia.model.sketch.model.SketchModelBuilder
import java.util.*

fun traverseArrays(traverser: PairTransformingTraverser<*, SketchElement>, first: Array<SketchElement>, second: Array<SketchElement>): List<SketchElement> {
    return iterate(first, second, { e1, e2 -> listOf(traverser.traverse(e1, e2)!!) }, { e1, e2 -> e1 sameAs e2 })
}

fun <T: FamilyMember<T>> mergeArrays(first: Array<T>, second: Array<T>, merger: Merger<T>): List<T> {
    return iterate(first, second, { m1, m2 -> if (m1 == null) listOf(m2!!) else if (m2 == null) listOf(m1) else listOf(merger.merge(m1, m2)) }, { m1, m2 -> m1 sameAs m2 })
}

fun <T: FamilyMember<T>> diffArrays(first: Array<T>, second: Array<T>, differ: Differ<T>): List<T> {
    return iterate(first, second, { m1, m2 -> if (m1 == null) listOf(m2!!) else if (m2 == null) listOf(m1) else listOf(differ.diff(m1, m2)) }, { m1, m2 -> m1 sameAs m2 })
}

fun <T> iterate(first: Array<T>, second: Array<T>, processor: (T?, T?) -> List<T>, comparator: (T, T) -> Boolean): List<T> {
    val result = ArrayList<T>()
    for (child in first) {
        val sameElement = second.find { comparator(it, child) }
        result.addAll(processor(child, sameElement))
    }

    for (child in second) {
        val sameElement = first.find { comparator(it, child) }
        if (sameElement == null) result.addAll(processor(null, child))
    }
    return result
}

interface Merger<T> {
    fun merge(first: T, second: T): T
}

interface Differ<T> {
    fun diff(first: T, second: T): T
}

fun SketchElement.model() : SketchModel {
    val builder = SketchModelBuilder()
    buildModel(builder)
    return builder.build()
}