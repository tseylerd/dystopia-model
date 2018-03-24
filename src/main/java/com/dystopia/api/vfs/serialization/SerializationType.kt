package com.dystopia.api.vfs.serialization

import com.dystopia.api.vfs.*

enum class SerializationType(val type: FileType) {
    ARCHIVE(FileType.ARCHIVE) {
        override fun <T> serialize(path: String, entry: FileSystemEntry, serializer: FileSystemSerializer<T>): T {
            return serializer.serializeArchive(path, entry as Archive)
        }
    },
    BINARY(FileType.BINARY) {
        override fun <T> serialize(path: String, entry: FileSystemEntry, serializer: FileSystemSerializer<T>): T {
            return serializer.serializeBinaryFile(path, entry as BinaryFile)
        }
    },
    TEXT(FileType.TEXT) {
        override fun <T> serialize(path: String, entry: FileSystemEntry, serializer: FileSystemSerializer<T>): T {
            return serializer.serializeTextFile(path, entry as TextFile)
        }
    },
    DIRECTORY(FileType.DIRECTORY) {
        override fun <T> serialize(path: String, entry: FileSystemEntry, serializer: FileSystemSerializer<T>): T {
            return serializer.serializeDirectory(path, entry as Directory)
        }
    };

    abstract fun <T> serialize(path: String, entry: FileSystemEntry, serializer: FileSystemSerializer<T>): T

    companion object {
        fun of(entry: FileSystemEntry): SerializationType {
            for (value in values()) {
                if (value.type == entry.type()) return value
            }
            throw IllegalArgumentException("Unresolved serialization type for: " + entry.name())
        }
    }
}