package com.dystopia.api.vfs

class TextFile(parent: VirtualDirectory?, name: String, private val content: String) : VirtualFile(parent, name) {
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

    override fun reparent(directory: VirtualDirectory?) = TextFile(directory, name(), content)
}