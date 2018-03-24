package com.dystopia.api.vfs

class TextFile(name: String, private val content: String) : VirtualFile(name) {
    override fun type(): FileType = FileType.TEXT

    fun content() = content

    override fun equals(other: Any?): Boolean {
        return other is TextFile && other.name() == name() && other.content() == content
    }

    override fun hashCode(): Int {
        return name().hashCode() + content.hashCode()
    }

    override fun toString(): String {
        return "Text file ${name()}: $content"
    }
}