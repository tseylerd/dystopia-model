package com.dystopia.model.introspection

import com.dystopia.model.Element

interface Introspector<T: Element<T>> {
    fun introspect(): T
}