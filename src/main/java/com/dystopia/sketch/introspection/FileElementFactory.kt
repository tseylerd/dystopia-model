package com.dystopia.sketch.introspection

import com.dystopia.sketch.api.SketchElement
import com.dystopia.sketch.element.DocumentElement
import com.dystopia.sketch.element.FileElement
import com.dystopia.sketch.element.MetaElement
import com.dystopia.sketch.element.UserElement

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