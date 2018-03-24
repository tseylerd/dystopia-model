package com.dystopia.api.vfs

interface FileSystemEntry {
    fun name(): String
    fun type(): FileType
}