package com.dystopia.model.provider

interface DiffProvider<E> {
    fun different(first: E, second: E): E
    fun same(element: E): E
}