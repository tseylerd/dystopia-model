package com.dystopia.api.vfs

import java.nio.file.*

object FileSystemWalker {
    fun walk(path: Path): FileSystemEntry = FileType.of(path).create(path, this)

    fun walkDirectory(path: Path): Array<FileSystemEntry> {
        return Files.walk(path, 1)
                .filter { it != path }
                .map { FileType.of(it).create(it, this) }
                .toArray { size -> arrayOfNulls<FileSystemEntry>(size) }
    }

    fun walkZip(path: Path): Array<FileSystemEntry> {
        val uri = "jar:file:" + path.toString()
        return inFileSystem(uri) { walkDirectory(it.getPath("/")) }
    }
}