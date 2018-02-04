package com.dystopia.api.provider

interface DiffProvider<E> {
    fun different(first: E, second: E): E
    fun same(element: E): E
}