package sketch

import com.dystopia.model.serialization.sketch.SketchSerializer
import org.junit.Test
import sketch.util.assertArchive
import sketch.util.fixture
import sketch.util.introspectElement

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
