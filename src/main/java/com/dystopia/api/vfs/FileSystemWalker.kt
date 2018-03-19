package com.dystopia.api.vfs

import java.nio.file.*

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
        val zipFileSystem = zipFs(uri)
        val root = zipFileSystem.getPath("/")
        return walkDirectory(parent, root)
    }
}