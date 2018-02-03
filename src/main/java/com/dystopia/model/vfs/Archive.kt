package com.dystopia.model.vfs

import java.util.*

class Archive(parent: VirtualDirectory?, name: String, childrenFactory: (VirtualDirectory?) -> Array<FileSystemEntry>) : VirtualDirectory(parent, name, childrenFactory) {
    override fun type() = FileType.ARCHIVE

    override fun toString(): String {
        return "Archive: " + name() + ": " + Arrays.toString(children())
    }

    override fun reparent(directory: VirtualDirectory?) = Archive(directory, name(), childrenFactory)
}
