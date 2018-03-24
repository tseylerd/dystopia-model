package com.dystopia.api.vfs

import java.io.File
import java.net.URI
import java.nio.file.FileSystem
import java.nio.file.FileSystems

fun <T> inFileSystem(path: String, callback: (FileSystem) -> T): T {
    return inFileSystem(path, false, callback)
}

fun <T> inFileSystem(path: String, create: Boolean, callback: (FileSystem) -> T): T {
    return inFileSystem(
            path,
            if (create) mapOf() else mapOf(
                    "create" to "true",
                    "replace" to "true"
            ),
            callback
    )
}

fun <T> inFileSystem(path: String, env: Map<String, String>, callback: (FileSystem) -> T): T {
    val newFileSystem = FileSystems.newFileSystem(URI.create(path), env)
    return newFileSystem.use(callback)
}

infix fun String.go(next: VirtualDirectory): String {
    return this + next.name() + File.separator
}

infix fun String.go(next: VirtualFile): String {
    return this + next.name()
}