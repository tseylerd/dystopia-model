package com.dystopia.model.vfs

class BinaryFile(parent: VirtualDirectory?, name: String, val content: ByteArray): VirtualFile(parent, name) {
    override fun type() = FileType.BINARY

    override fun equals(other: Any?): Boolean {
        return other is BinaryFile && name() == other.name() && path() == other.path()
    }

    override fun hashCode(): Int {
        return name().hashCode() + path().hashCode()
    }

    override fun toString(): String {
        return "Binary file: " + name()
    }

    override fun reparent(directory: VirtualDirectory?): FileSystemEntry = BinaryFile(directory, name(), content)
}