package com.dystopia.model.introspection.sketch

import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.element.DocumentElement
import com.dystopia.model.sketch.element.FileElement
import com.dystopia.model.sketch.element.MetaElement
import com.dystopia.model.sketch.element.UserElement

enum class FileElementFactory(val fileName: String) {
    META("meta.json") {
        override fun create(name: String, child: SketchElement) : FileElement {
            return MetaElement(name, child)
        }
    },
    DOCUMENT("document.json") {
        override fun create(name: String, child: SketchElement): FileElement {
            return DocumentElement(name, child)
        }
    },
    USER("user.json") {
        override fun create(name: String, child: SketchElement): FileElement {
            return UserElement(name, child)
        }
    },
    DEFAULT("") {
        override fun create(name: String, child: SketchElement): FileElement {
            return FileElement(name, child)
        }
    };

    abstract fun create(name: String, child: SketchElement) : FileElement

    companion object {
        fun of(fileName: String) : FileElementFactory {
            for (value in values()) {
                if (value.fileName == fileName) return value
            }
            return DEFAULT
        }
    }
}