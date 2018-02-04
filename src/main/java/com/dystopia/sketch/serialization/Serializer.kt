package com.dystopia.sketch.serialization

import com.dystopia.api.Element
import com.dystopia.api.vfs.FileSystemEntry

interface Serializer<T: Element<T>> {
    fun serialize(name: String, element: T): FileSystemEntry
}