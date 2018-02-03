package com.dystopia.model.sketch.model

import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.sketch.element.*
import com.dystopia.model.sketch.util.*

class SketchModel(val pages: Array<Page>,
                  val metaElements: Array<SketchElement>,
                  val documentElements: Array<SketchElement>,
                  val user: SketchElement,
                  val previews: SketchElement) {

    fun asElement(name: String): SketchElement {
        return RootElement(name,
                generateMeta(),
                generateDocument(),
                user,
                previews,
                DirectoryElement("pages", ListElement(
                        pages.map { it.fileElement() }.toTypedArray()
                ))
        )
    }

    private fun generateDocument(): SketchElement {
        return DocumentElement(
                "document.json",
                objectList {
                    concatenationOf {
                        documentElements
                    }.with {
                        idBased("pages") {
                            arrayList {
                                pages.map { it.documentElement() }.toTypedArray()
                            }
                        }
                    }
                }
        )
    }

    private fun generateMeta(): SketchElement {
        return MetaElement(
                "meta.json",
                objectList {
                    concatenationOf {
                        metaElements
                    }.with {
                        objectWithId(SketchAttributes.pagesAndArtboards) {
                            listElement {
                                pages.map { it.metaElement() }.toTypedArray()
                            }
                        }
                    }
                }
        )
    }
}