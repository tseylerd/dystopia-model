package com.dystopia.model.vfs

abstract class BaseFileSystemEntry(private val parent: VirtualDirectory?, private val name: String): FileSystemEntry {
    override fun parent() = parent
    override fun name() = name
    override fun path(): String {
        val parent = parent() ?: return name
        return parent.path() + "/" + name
    }
}