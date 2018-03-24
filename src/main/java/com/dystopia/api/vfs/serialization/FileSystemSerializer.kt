package com.dystopia.api.vfs.serialization

import com.dystopia.api.vfs.*

interface FileSystemSerializer<out T> {
    fun serialize(entries: Array<FileSystemEntry>): T
    fun serialize(path: String, entries: Array<FileSystemEntry>): T
    fun serializeBinaryFile(path: String, entry: BinaryFile): T
    fun serializeTextFile(path: String, entry: TextFile): T
    fun serializeDirectory(path: String, entry: Directory): T
    fun serializeArchive(path: String, entry: Archive): T
}