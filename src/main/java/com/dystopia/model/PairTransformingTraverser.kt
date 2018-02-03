package com.dystopia.model

interface PairTransformingTraverser<A: Atom<*>, T: Element<*>> {
    fun traverse(first: T?, second: T?): T?
    fun atoms(first: A, second: A): T?
}