package com.dystopia.sketch.serialization

import com.dystopia.sketch.api.SketchElement
import com.dystopia.api.vfs.FileSystemEntry

class SketchSerializer: Serializer<SketchElement> {
    override fun serialize(name: String, element: SketchElement): FileSystemEntry {
        val session = SerializationSession.ArchiveSession(name)
        element.serialize(session)
        return session.toVfsEntry(null)
    }
}