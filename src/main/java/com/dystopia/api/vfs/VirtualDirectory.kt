package com.dystopia.api.vfs

import java.util.*

abstract class VirtualDirectory(name: String, protected val children: Array<FileSystemEntry>) : BaseFileSystemEntry(name) {
    fun children(): Array<FileSystemEntry> {
        return children
    }

    override fun equals(other: Any?): Boolean {
        return other is VirtualDirectory &&
                name() == other.name() &&
                Arrays.equals(children(), other.children())
    }

    override fun hashCode(): Int {
        return name().hashCode() + Arrays.hashCode(children())
    }
}
