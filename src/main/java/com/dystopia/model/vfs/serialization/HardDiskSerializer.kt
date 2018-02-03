package com.dystopia.model.vfs.serialization

import com.dystopia.model.vfs.*
import java.net.URI
import java.nio.file.FileSystem
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Paths

class HardDiskSerializer(val path: String) : FileSystemSerializer<Unit> {
    override fun serialize(entries: Array<FileSystemEntry>) {
        entries.forEach { SerializationType.of(it).serialize(it, this) }
    }

    override fun serializeBinaryFile(entry: BinaryFile) {
        val path = Paths.get(path, entry.name())
        Files.write(path, entry.content)
    }

    override fun serializeTextFile(entry: TextFile) {
        val path = Paths.get(path, entry.name())
        Files.write(path, listOf(entry.content()))
    }

    override fun serializeDirectory(entry: Directory) {
        inFs("$path${entry.path()}", entry)
    }

    override fun serializeArchive(entry: Archive) {
        inFs("jar:file:$path${entry.path()}", entry)
    }

    private fun inFs(path: String, directory: VirtualDirectory) {
        val map = HashMap<String, String>()
        map.put("create", "true")
        map.put("replace", "true")
        FileSystems.newFileSystem(URI.create(path), map).use {
            val serializer = FSSerializer(it)
            for (child in directory.children().map { it.reparent(null) }) {
                SerializationType.of(child).serialize(child, serializer)
            }
        }
    }

    class FSSerializer(private val fs: FileSystem) : FileSystemSerializer<Unit> {
        override fun serialize(entries: Array<FileSystemEntry>) {
            entries.forEach { SerializationType.of(it).serialize(it, this) }
        }

        override fun serializeBinaryFile(entry: BinaryFile) {
            val stringPath = entry.path()
            val path = fs.getPath(stringPath)
            Files.write(path, entry.content)
        }

        override fun serializeTextFile(entry: TextFile) {
            val stringPath = entry.path()
            val path = fs.getPath(stringPath)
            Files.deleteIfExists(path)
            Files.write(path, entry.content().split("\n"))
        }

        override fun serializeDirectory(entry: Directory) {
            val path = fs.getPath(entry.path())
            if (!Files.exists(path)) Files.createDirectory(path)
            for (child in entry.children()) {
                SerializationType.of(child).serialize(child, this)
            }
        }

        override fun serializeArchive(entry: Archive) {
            throw UnsupportedOperationException()
        }
    }
}
