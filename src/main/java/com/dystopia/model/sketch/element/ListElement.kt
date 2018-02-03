package com.dystopia.model.sketch.element

import com.dystopia.model.sketch.api.Identifier
import com.dystopia.model.sketch.api.SketchElement
import com.dystopia.model.util.Ref

open class ListElement(children: Array<SketchElement>): AbstractListElement(children) {
    private var identifier: Ref<Identifier>? = null

    override fun sameAs(another: SketchElement): Boolean {
        if (another !is ListElement) return false
        val myIdentifier = identifier() ?: return false
        val elementIdentifier = another.identifier() ?: return false
        return myIdentifier.identity() == elementIdentifier.identity()
    }

    override fun reproduce(children: Array<SketchElement>) = ListElement(children)

    override fun identifier(): Identifier? {
        if (identifier == null) {
            val identifiers = children.filterIsInstance(Identifier::class.java)
            assert(identifiers.size <= 1) { "Object can has only one identifier" }
            identifier = Ref(identifiers.firstOrNull())
        }
        return identifier!!.value
    }
}