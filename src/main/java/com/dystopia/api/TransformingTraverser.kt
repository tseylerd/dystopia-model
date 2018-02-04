package com.dystopia.api

interface TransformingTraverser<T: Element<T>, out O> {
    fun transform(element: T): O
}