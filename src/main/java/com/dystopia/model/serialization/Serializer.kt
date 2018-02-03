package com.dystopia.model.serialization

import com.dystopia.model.Element
import com.dystopia.model.vfs.FileSystemEntry

interface Serializer<T: Element<T>> {
    fun serialize(name: String, element: T): FileSystemEntry
}