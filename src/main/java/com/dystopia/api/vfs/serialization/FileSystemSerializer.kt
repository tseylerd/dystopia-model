package com.dystopia.api.vfs.serialization

import com.dystopia.api.vfs.*

interface FileSystemSerializer<T> {
    fun serialize(entries: Array<FileSystemEntry>): T
    fun serializeBinaryFile(entry: BinaryFile): T
    fun serializeTextFile(entry: TextFile): T
    fun serializeDirectory(entry: Directory): T
    fun serializeArchive(entry: Archive): T
}