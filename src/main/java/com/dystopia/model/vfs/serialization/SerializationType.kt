package com.dystopia.model.vfs.serialization

import com.dystopia.model.vfs.*

enum class SerializationType(val type: FileType) {
    ARCHIVE(FileType.ARCHIVE) {
        override fun <T> serialize(entry: FileSystemEntry, serializer: FileSystemSerializer<T>): T {
            return serializer.serializeArchive(entry as Archive)
        }
    },
    BINARY(FileType.BINARY) {
        override fun <T> serialize(entry: FileSystemEntry, serializer: FileSystemSerializer<T>): T {
            return serializer.serializeBinaryFile(entry as BinaryFile)
        }
    },
    TEXT(FileType.TEXT) {
        override fun <T> serialize(entry: FileSystemEntry, serializer: FileSystemSerializer<T>): T {
            return serializer.serializeTextFile(entry as TextFile)
        }
    },
    DIRECTORY(FileType.DIRECTORY) {
        override fun <T> serialize(entry: FileSystemEntry, serializer: FileSystemSerializer<T>): T {
            return serializer.serializeDirectory(entry as Directory)
        }
    };

    abstract fun <T> serialize(entry: FileSystemEntry, serializer: FileSystemSerializer<T>): T

    companion object {
        fun of(entry: FileSystemEntry): SerializationType {
            for (value in values()) {
                if (value.type == entry.type()) return value
            }
            throw IllegalArgumentException("Unresolved serialization type for: " + entry.path() + "/" + entry.name())
        }
    }
}