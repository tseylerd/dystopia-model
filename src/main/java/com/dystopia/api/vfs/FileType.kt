package com.dystopia.api.vfs

import java.nio.file.Files
import java.nio.file.Path

enum class FileType {
    ARCHIVE {
        override fun isSuitable(path: Path) = path.toString().endsWith("zip") || path.toString().endsWith("sketch")
        override fun create(path: Path, walker: FileSystemWalker): FileSystemEntry {
            return Archive(path.fileName.toString(), walker.walkZip(path))
        }
    },
    BINARY {
        override fun isSuitable(path: Path) = !Files.isDirectory(path) && path.toString().endsWith("png")
        override fun create(path: Path, walker: FileSystemWalker): FileSystemEntry {
            return BinaryFile(path.fileName.toString(), Files.readAllBytes(path))
        }
    },
    TEXT {
        override fun isSuitable(path: Path) = !Files.isDirectory(path)
        override fun create(path: Path, walker: FileSystemWalker) = TextFile(path.fileName.toString(), content(path))

        private fun content(path: Path) = Files.readAllLines(path).joinToString("")
    },
    DIRECTORY {
        override fun isSuitable(path: Path) = Files.isDirectory(path)
        override fun create(path: Path, walker: FileSystemWalker): FileSystemEntry {
            return Directory(path.fileName.toString().trim('/'), walker.walkDirectory(path))
        }
    };

    abstract fun isSuitable(path: Path): Boolean
    abstract fun create(path: Path, walker: FileSystemWalker): FileSystemEntry

    companion object {
        fun of(path: Path): FileType {
            for (value in values()) {
                if (value.isSuitable(path)) return value
            }
            throw IllegalArgumentException("Unresolved file type for: " + path.toString())
        }
    }
}