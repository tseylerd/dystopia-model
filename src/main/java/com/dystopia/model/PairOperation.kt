package com.dystopia.model

interface PairOperation<in A: Atom<*>, T: Element<*>> {
    fun perform(first: A, second: A): T
    fun first(element: T): T
    fun second(element: T): T
}