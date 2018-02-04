package com.dystopia.api

interface PairTraversal<T: Element<T>> {
    fun traverse(first: T, second: T, traverser: PairTransformingTraverser<*, T>): T?
}