package com.dystopia.model.sketch.traversal

interface SketchTraversalFactory {
    infix fun traversalOf(clazz: Class<*>): SketchPairTraversal
}