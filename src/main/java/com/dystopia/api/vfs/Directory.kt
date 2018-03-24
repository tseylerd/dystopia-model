package com.dystopia.api.vfs

import java.util.*

class Directory(name: String, children: Array<FileSystemEntry>) :
        VirtualDirectory(name, children) {

    override fun type() = FileType.DIRECTORY

    override fun toString(): String {
        return "Directory " + name() + ": " + Arrays.toString(children())
    }
}