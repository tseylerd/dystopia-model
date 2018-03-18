package com.dystopia.sketch.util

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.streams.toList

class TestUniqueIdGenerator : UniqueIdGenerator.Genrator {
    companion object {
        const val IDS = "/util/uuids.txt"
    }

    private val ids: List<String>

    private var idx = 0

    override fun generate(): String {
        return ids[idx++]
    }

    init {
        ids = BufferedReader(
                InputStreamReader(
                        javaClass.getResourceAsStream(IDS)
                )
        ).use { it.lines().toList() }
    }
}