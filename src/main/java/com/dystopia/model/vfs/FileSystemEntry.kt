package com.dystopia.model.vfs

interface FileSystemEntry {
    fun parent(): VirtualDirectory?
    fun name(): String
    fun type(): FileType
    fun path(): String
    fun reparent(directory: VirtualDirectory?): FileSystemEntry
}