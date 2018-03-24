package com.dystopia.api.vfs.serialization

import com.dystopia.api.vfs.*
import java.nio.file.Paths

object StringSerializer : FileSystemSerializer<String> {
    override fun serialize(entries: Array<FileSystemEntry>): String {
        return serialize("", entries)
    }

    override fun serialize(path: String, entries: Array<FileSystemEntry>): String {
        return entries
                .map { SerializationType.of(it).serialize(path, it, this) }
                .joinToString { it }
    }

    override fun serializeBinaryFile(path: String, entry: BinaryFile): String {
        return indent(path) + "<binary content>" + "\n"
    }

    override fun serializeTextFile(path: String, entry: TextFile): String {
        return indent(path) +  entry.content() + "\n"
    }

    override fun serializeDirectory(path: String, entry: Directory): String {
        return indent(path) + path + "\n" + serialize(path go entry, entry.children())
    }

    override fun serializeArchive(path: String, entry: Archive): String {
        return indent(path) + path + "\n" + serialize(path go entry, entry.children())
    }

    private fun indent(path: String): String {
        val sb = StringBuilder()
        var parent = Paths.get(path)
        while (parent != null) {
            parent = parent.parent
            sb.append("     ")
        }
        return sb.toString()
    }
}