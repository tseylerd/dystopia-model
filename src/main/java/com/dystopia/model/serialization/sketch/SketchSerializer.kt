package com.dystopia.model.serialization.sketch

import com.dystopia.model.serialization.Serializer
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.vfs.FileSystemEntry

class SketchSerializer: Serializer<SketchElement> {
    override fun serialize(name: String, element: SketchElement): FileSystemEntry {
        val session = SerializationSession.ArchiveSession(name)
        element.serialize(session)
        return session.toVfsEntry(null)
    }
}