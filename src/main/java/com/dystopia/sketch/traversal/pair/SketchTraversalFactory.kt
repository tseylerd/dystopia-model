package com.dystopia.sketch.traversal.pair

interface SketchTraversalFactory {
    infix fun traversalOf(clazz: Class<*>): SketchPairTraversal
}