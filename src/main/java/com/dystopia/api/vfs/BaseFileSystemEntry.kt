package com.dystopia.api.vfs

abstract class BaseFileSystemEntry(private val name: String): FileSystemEntry {
    override fun name() = name
}