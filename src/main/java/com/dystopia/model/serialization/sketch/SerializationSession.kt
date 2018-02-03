package com.dystopia.model.serialization.sketch

import com.dystopia.model.vfs.*
import com.google.gson.stream.JsonWriter
import java.io.StringWriter

interface SerializationSession {
    fun inDirectory(name: String, callback: (SerializationSession) -> Unit)
    fun json(callback: (JsonWriter) -> Unit)
    fun plain(value: String)
    fun inFile(name: String, callback: (SerializationSession) -> Unit)
    fun toVfsEntry(parent: VirtualDirectory?): FileSystemEntry
    fun inArchive(name: String, callback: (SerializationSession) -> Unit)
    fun toVfsEntry() = toVfsEntry(null)

    abstract class AbstractDirectorySession(val name: String): SerializationSession {
        internal val children: MutableList<(VirtualDirectory?) -> FileSystemEntry> = ArrayList()

        override fun inDirectory(name: String, callback: (SerializationSession) -> Unit) {
            inSession(DirectorySession(name), callback)
        }

        override fun json(callback: (JsonWriter) -> Unit) {
            throw IllegalStateException("Can't write text in directory session")
        }

        override fun plain(value: String) {
            throw IllegalStateException("Can't write text in directory session")
        }

        override fun inFile(name: String, callback: (SerializationSession) -> Unit) {
            inSession(FileSession(name, this), callback)
        }

        override fun inArchive(name: String, callback: (SerializationSession) -> Unit) {
            inSession(ArchiveSession(name), callback)
        }

        private fun inSession(session: SerializationSession, callback: (SerializationSession) -> Unit) {
            callback.invoke(session)
            children.add { session.toVfsEntry(it) }
        }

        protected fun toChildren(parent: VirtualDirectory?) = children.map { it(parent) }.toTypedArray()
    }

    class DirectorySession(name: String): AbstractDirectorySession(name) {
        override fun toVfsEntry(parent: VirtualDirectory?): FileSystemEntry {
            return Directory(parent, name, { toChildren(it) } )
        }
    }

    class ArchiveSession(name: String): AbstractDirectorySession(name) {
        override fun toVfsEntry(parent: VirtualDirectory?): FileSystemEntry {
            return Archive(parent, name, { toChildren(it) })
        }
    }

    class FileSession(val name: String, private val dirSession: AbstractDirectorySession): SerializationSession {
        private val writer: StringWriter = StringWriter()
        private val builder: JsonWriter = JsonWriter(writer)

        override fun inDirectory(name: String, callback: (SerializationSession) -> Unit) {
            dirSession.inDirectory(name, callback)
        }

        override fun plain(value: String) {
            writer.write(value)
        }

        override fun json(callback: (JsonWriter) -> Unit) {
            callback(builder)
        }

        override fun inFile(name: String, callback: (SerializationSession) -> Unit) {
            throw IllegalStateException("Can't create file inside file")
        }

        override fun toVfsEntry(parent: VirtualDirectory?): FileSystemEntry {
            return TextFile(parent, name, writer.toString())
        }

        override fun inArchive(name: String, callback: (SerializationSession) -> Unit) {
            dirSession.inArchive(name, callback)
        }
    }
}