package sketch.util

import com.dystopia.model.sketch.util.UniqueIdGenerator
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

class TestUniqueIdGenerator : UniqueIdGenerator.Genrator {
    private val ids: List<String>

    private var idx = 0

    override fun generate(): String {
        return ids[idx++]
    }

    init {
        val stringPath = javaClass.getResource("/util/uuids.txt").toURI().path
        val path = Paths.get(stringPath)
        ids = Files.lines(path).toList()
    }
}