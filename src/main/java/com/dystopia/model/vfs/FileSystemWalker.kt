package com.dystopia.model.vfs

import java.net.URI
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path

object FileSystemWalker {
    fun walk(path: Path): FileSystemEntry = FileType.of(path).create(null, path, this)

    fun walkDirectory(parent: VirtualDirectory?, path: Path): Array<FileSystemEntry> {
        return Files.walk(path, 1)
                .filter { it != path }
                .map { FileType.of(it).create(parent, it, this) }
                .toArray({ size -> arrayOfNulls<FileSystemEntry>(size) })
    }


    fun walkZip(parent: VirtualDirectory?, path: Path): Array<FileSystemEntry> {
        val uri = "jar:file:" + path.toString()
        val zipFileSystem = FileSystems.newFileSystem(URI.create(uri), mapOf<String, Any>())
        val root = zipFileSystem.getPath("/")
        return walkDirectory(parent, root)
    }
}