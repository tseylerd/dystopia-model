package com.dystopia.api.vfs

class FileFixture(val root: VirtualDirectory) {
    fun file(name: String): FileSystemEntry? {
        return file(name, root)
    }

    fun content(name: String, path: FilePath): String? {
        return content(if (path.path.isEmpty()) root else path.path.last(), name)
    }

    fun path(name: String): FilePath {
        val filePath = FilePath()
        path(root, name, filePath)
        return filePath
    }

    private fun content(root: VirtualDirectory, name: String): String? {
        for (child in root.children()) {
            if (child is VirtualDirectory) return content(child, name) ?: continue
            if (child is TextFile && child.name() == name) return child.content()
        }
        return null
    }

    private fun path(directory: VirtualDirectory, name: String, path: FilePath): Boolean {
        for (child in directory.children()) {
            if (child is VirtualDirectory && path.inDirectory(child) { path(child, name, path) }) return true
            if (child is TextFile && child.name() == name) return true
        }
        return false
    }

    private fun file(name: String, directory: VirtualDirectory): FileSystemEntry? {
        for (child in directory.children()) {
            if (child.name() == name) return child
        }
        for (child in directory.children()) {
            if (child is VirtualDirectory) {
                val file = file(name, child)
                if (file != null) return file
            }
        }
        return null
    }
}