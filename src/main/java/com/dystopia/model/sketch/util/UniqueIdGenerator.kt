package com.dystopia.model.sketch.util

import java.util.*

object UniqueIdGenerator {

    private var generator: Genrator = DefaultGenerator

    fun setGenerator(generator: Genrator) {
        UniqueIdGenerator.generator = generator
    }

    fun generate(): String {
        return generator.generate()
    }

    interface Genrator {
        fun generate(): String
    }

    object DefaultGenerator : Genrator {
        override fun generate(): String {
            return UUID.randomUUID().toString().toUpperCase()
        }
    }
}