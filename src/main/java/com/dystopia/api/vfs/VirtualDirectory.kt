package com.dystopia.api.vfs

import java.util.*

abstract class VirtualDirectory(parent: VirtualDirectory?, name: String, protected val childrenFactory: (VirtualDirectory?) -> Array<FileSystemEntry>) :
        BaseFileSystemEntry(parent, name) {
    private var children: Array<FileSystemEntry>? = null

    fun children(): Array<FileSystemEntry> {
        if (children == null) {
            children = childrenFactory(this)
        }
        return children!!
    }

    override fun equals(other: Any?): Boolean {
        return other is VirtualDirectory &&
                name() == other.name() &&
                path() == other.path() &&
                Arrays.equals(children(), other.children())
    }

    override fun hashCode(): Int {
        return name().hashCode() + path().hashCode() + Arrays.hashCode(children())
    }
}
