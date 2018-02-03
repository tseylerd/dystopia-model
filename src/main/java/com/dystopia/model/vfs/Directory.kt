package com.dystopia.model.vfs

import java.util.*

class Directory(parent: VirtualDirectory?, name: String, childrenFactory: (VirtualDirectory?) -> Array<FileSystemEntry>) :
        VirtualDirectory(parent, name, childrenFactory) {

    override fun type() = FileType.DIRECTORY

    override fun toString(): String {
        return "Directory " + name() + ": " + Arrays.toString(children())
    }

    override fun reparent(directory: VirtualDirectory?) = Directory(directory, name(), childrenFactory)
}