package com.dystopia.api.vfs.serialization

import com.dystopia.api.vfs.*
import java.nio.file.FileSystem
import java.nio.file.Files
import java.nio.file.Paths

class HardDiskSerializer(val path: String) : FileSystemSerializer<Unit> {
    override fun serialize(entries: Array<FileSystemEntry>) {
        serialize(path, entries)
    }

    override fun serialize(path: String, entries: Array<FileSystemEntry>) {
        entries.forEach { SerializationType.of(it).serialize(path, it, this) }
    }

    override fun serializeBinaryFile(path: String, entry: BinaryFile) {
        val p = Paths.get(path, entry.name())
        Files.write(p, entry.content)
    }

    override fun serializeTextFile(path: String, entry: TextFile) {
        val p = Paths.get(path, entry.name())
        Files.write(p, listOf(entry.content()))
    }

    override fun serializeDirectory(path: String, entry: Directory) {
        val relativePath = path go entry
        Files.deleteIfExists(Paths.get(relativePath))
        inFs(relativePath, entry)
    }

    override fun serializeArchive(path: String, entry: Archive) {
        Files.deleteIfExists(Paths.get(path go entry))
        inFs("jar:file:$path" go entry, entry)
    }

    private fun inFs(path: String, directory: VirtualDirectory) {
        val map = HashMap<String, String>()
        map["create"] = "true"
        map["replace"] = "true"
        inFileSystem(path, map) {
            val serializer = FSSerializer(it)
            for (child in directory.children()) {
                SerializationType.of(child).serialize(FSSerializer.ROOT, child, serializer)
            }
        }
    }

    class FSSerializer(private val fs: FileSystem) : FileSystemSerializer<Unit> {
        companion object {
            const val ROOT = ""
        }

        override fun serialize(path: String, entries: Array<FileSystemEntry>) {
            entries.forEach { SerializationType.of(it).serialize(path, it, this) }
        }

        override fun serialize(entries: Array<FileSystemEntry>) {
            serialize(ROOT, entries)
        }

        override fun serializeBinaryFile(path: String, entry: BinaryFile) {
            val filePath = path go entry
            val p = fs.getPath(filePath)
            Files.write(p, entry.content)
        }

        override fun serializeTextFile(path: String, entry: TextFile) {
            val filePath = path go entry
            val p = fs.getPath(filePath)
            Files.write(p, entry.content().split("\n"))
        }

        override fun serializeDirectory(path: String, entry: Directory) {
            val relative = path go entry
            val p = fs.getPath(relative)
            if (!Files.exists(p)) Files.createDirectory(p)
            for (child in entry.children()) {
                SerializationType.of(child).serialize(relative, child, this)
            }
        }

        override fun serializeArchive(path: String, entry: Archive) {
            throw UnsupportedOperationException()
        }
    }
}
