package com.dystopia.sketch

import com.dystopia.model.serialization.sketch.SketchSerializer
import org.junit.Test
import com.dystopia.sketch.util.assertArchive
import com.dystopia.sketch.util.fixture
import com.dystopia.sketch.util.introspectElement

class SerializationTest : SimpleTestCase() {
    override fun testDataFolder(): String {
        return "test/serialization"
    }

    override fun fileExtension(): String {
        return "sketch"
    }

    @Test
    fun serialization() {
        doTest()
    }

    @Test
    fun symbol() {
        doTest()
    }

    private fun doTest() {
        val directory = loadVirtualDirectory()
        val element = directory.assertArchive().fixture().introspectElement()
        val serialized = SketchSerializer().serialize(directory.name(), element)
        assertModelsEquals(directory, serialized)
    }
}
