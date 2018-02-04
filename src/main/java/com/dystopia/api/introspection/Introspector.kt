package com.dystopia.api.introspection

import com.dystopia.api.Element

interface Introspector<T: Element<T>> {
    fun introspect(): T
}