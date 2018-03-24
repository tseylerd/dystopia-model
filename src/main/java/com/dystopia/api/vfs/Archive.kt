package com.dystopia.api.vfs

import java.util.*

class Archive(name: String, children: Array<FileSystemEntry>) : VirtualDirectory(name, children) {
    override fun type() = FileType.ARCHIVE

    override fun toString(): String {
        return "Archive: " + name() + ": " + Arrays.toString(children())
    }
}
