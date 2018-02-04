package com.dystopia.api.vfs.serialization

import com.dystopia.api.vfs.*

object StringSerializer : FileSystemSerializer<String> {
    override fun serialize(entries: Array<FileSystemEntry>): String {
        return entries
                .map { SerializationType.of(it).serialize(it, this) }
                .joinToString { it }
    }

    override fun serializeBinaryFile(entry: BinaryFile): String {
        return entry.indent() + "<binary content>" + "\n"
    }

    override fun serializeTextFile(entry: TextFile): String {
        return entry.indent() +  entry.content() + "\n"
    }

    override fun serializeDirectory(entry: Directory): String {
        return entry.indent() + entry.path() + "\n" + serialize(entry.children())
    }

    override fun serializeArchive(entry: Archive): String {
        return entry.indent() + entry.path() + "\n" + serialize(entry.children())
    }

    fun FileSystemEntry.indent(): String {
        val sb = StringBuilder()
        var parent = parent()
        while (parent != null) {
            parent = parent.parent()
            sb.append("     ")
        }
        return sb.toString()
    }
}