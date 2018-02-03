package com.dystopia.model.vfs

import java.nio.file.Files
import java.nio.file.Path

enum class FileType {
    ARCHIVE {
        override fun isSuitable(path: Path) = path.toString().endsWith("zip") || path.toString().endsWith("sketch")
        override fun create(parent: VirtualDirectory?, path: Path, walker: FileSystemWalker): FileSystemEntry {
            return Archive(parent, path.fileName.toString(), { walker.walkZip(it, path) } )
        }
    },
    BINARY {
        override fun isSuitable(path: Path) = !Files.isDirectory(path) && path.toString().endsWith("png")

        override fun create(parent: VirtualDirectory?, path: Path, walker: FileSystemWalker) = BinaryFile(parent, path.fileName.toString(), Files.readAllBytes(path))
    },
    TEXT {
        override fun isSuitable(path: Path) = !Files.isDirectory(path)

        override fun create(parent: VirtualDirectory?, path: Path, walker: FileSystemWalker) = TextFile(parent, path.fileName.toString(), content(path))

        private fun content(path: Path) = Files.readAllLines(path).joinToString("")
    },
    DIRECTORY {
        override fun isSuitable(path: Path) = Files.isDirectory(path)
        override fun create(parent: VirtualDirectory?, path: Path, walker: FileSystemWalker): FileSystemEntry {
            return Directory(parent, path.fileName.toString().replace("/", ""), {walker.walkDirectory(it, path)})
        }
    };

    abstract fun isSuitable(path: Path): Boolean
    abstract fun create(parent: VirtualDirectory?, path: Path, walker: FileSystemWalker): FileSystemEntry

    companion object {
        fun of(path: Path): FileType {
            for (value in values()) {
                if (value.isSuitable(path)) return value
            }
            throw IllegalArgumentException("Unresolved file type for: " + path.toString())
        }
    }
}