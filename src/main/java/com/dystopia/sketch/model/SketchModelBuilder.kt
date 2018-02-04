package com.dystopia.sketch.model

import com.dystopia.sketch.api.SketchElement

class SketchModelBuilder {
    private val pages = ArrayList<Page>()
    private val metaElements = ArrayList<SketchElement>()
    private val documentElements = ArrayList<SketchElement>()


    private var pageBuilder: PageBuilder? = null
    private var elementsArranger: ElementsArranger? = null

    private var userElement: SketchElement? = null
    private var previewsElement: SketchElement? = null


    fun inPage(id: String, name: String, callback: () -> Unit) {
        pageBuilder = PageBuilder(id, name)
        elementsArranger = ElementsArranger(pageBuilder!!.elements)
        callback()
        pages.add(pageBuilder!!.build())
        pageBuilder = null
    }

    fun metaFile(callback: () -> Unit) {
        elementsArranger = ElementsArranger(metaElements)
        callback()
        elementsArranger = null
    }

    fun documentFile(callback: () -> Unit) {
        elementsArranger = ElementsArranger(documentElements)
        callback()
        elementsArranger = null
    }

    fun element(element: SketchElement) {
        elementsArranger!!.element(element)
    }

    fun userElement(element: SketchElement) {
        userElement = element
    }

    fun previewsElement(element: SketchElement) {
        previewsElement = element
    }

    fun layer(layer: Layer) {
        pageBuilder!!.layer(layer)
    }

    fun build() : SketchModel {
        return SketchModel(pages.toTypedArray(), metaElements.toTypedArray(), documentElements.toTypedArray(), userElement!!, previewsElement!!)
    }

    private class PageBuilder(private val id: String, private val name: String) {
        private var layers: MutableList<Layer> = ArrayList()

        var elements: MutableList<SketchElement> = ArrayList()

        fun build(): Page {
            return Page(id, name, layers.toTypedArray(), elements.toTypedArray())
        }

        fun layer(layer: Layer) {
            layers.add(layer)
        }
    }

    private class ElementsArranger(val list: MutableList<SketchElement>) {
        fun element(element: SketchElement) {
            list.add(element)
        }
    }
}