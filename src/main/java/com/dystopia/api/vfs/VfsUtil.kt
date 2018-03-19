package com.dystopia.api.vfs

import java.net.URI
import java.nio.file.FileSystem
import java.nio.file.FileSystemAlreadyExistsException
import java.nio.file.FileSystems

fun zipFs(path: String): FileSystem { //todo bad way
    return try {
        FileSystems.newFileSystem(URI.create(path), mapOf<String, Any>())
    } catch (e: FileSystemAlreadyExistsException) {
        FileSystems.getFileSystem(URI.create(path))
    }
}