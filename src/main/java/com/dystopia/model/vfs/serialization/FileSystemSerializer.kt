package com.dystopia.model.vfs.serialization

import com.dystopia.model.vfs.*

interface FileSystemSerializer<T> {
    fun serialize(entries: Array<FileSystemEntry>): T
    fun serializeBinaryFile(entry: BinaryFile): T
    fun serializeTextFile(entry: TextFile): T
    fun serializeDirectory(entry: Directory): T
    fun serializeArchive(entry: Archive): T
}