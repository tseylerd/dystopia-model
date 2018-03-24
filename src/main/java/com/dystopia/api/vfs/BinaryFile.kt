package com.dystopia.api.vfs

class BinaryFile(name: String, val content: ByteArray): VirtualFile(name) {
    override fun type() = FileType.BINARY

    override fun equals(other: Any?): Boolean {
        return other is BinaryFile && name() == other.name()
    }

    override fun hashCode(): Int {
        return name().hashCode()
    }

    override fun toString(): String {
        return "Binary file: " + name()
    }
}