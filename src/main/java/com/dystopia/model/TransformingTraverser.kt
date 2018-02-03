package com.dystopia.model

interface TransformingTraverser<T: Element<T>, out O> {
    fun transform(element: T): O
}