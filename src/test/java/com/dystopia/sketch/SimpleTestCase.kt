package com.dystopia.sketch

import com.dystopia.sketch.serialization.SketchSerializer
import com.dystopia.sketch.api.SketchElement
import com.dystopia.api.vfs.FileSystemEntry
import com.dystopia.api.vfs.FileSystemWalker
import com.dystopia.api.vfs.VirtualDirectory
import com.dystopia.api.vfs.serialization.HardDiskSerializer
import com.dystopia.api.vfs.serialization.StringSerializer
import org.junit.Assert
import org.junit.Rule
import org.junit.rules.TestName
import org.junit.runner.RunWith
import com.dystopia.sketch.util.TestRunner
import com.dystopia.sketch.util.sort
import java.nio.file.Paths
import java.util.*

@RunWith(TestRunner::class)
abstract class SimpleTestCase {
    private val directoryToSave: String? = System.getProperty("sketch.test.directory.to.save")

    @Suppress("RedundantVisibilityModifier")
    @Rule
    @JvmField
    public val testName = TestName()

    fun loadVirtualDirectory(): FileSystemEntry {
        return loadVirtualDirectory(testDataName())
    }

    fun loadVirtualDirectory(file: String): FileSystemEntry {
        val path = javaClass.getResource("/" + testDataFolder() + "/" + file + "." + fileExtension()).path
        return FileSystemWalker.walk(Paths.get(path))
    }

    abstract fun testDataFolder(): String
    abstract fun fileExtension(): String

    protected fun testDataName(): String {
        val methodName = testName.methodName!!
        return methodName.substring(0, 1).toLowerCase() + methodName.substring(1)
    }


    protected fun assertModelsEquals(expected: SketchElement, actual: SketchElement) {
        val expectedFs = expected.sort().toExpectedFsEntry()
        val actualFs = actual.sort().toExpectedFsEntry()
        assertModelsEquals(expectedFs, actualFs)
    }

    protected fun assertModelsEquals(expected: FileSystemEntry, actual: FileSystemEntry) {
        if (directoryToSave != null) HardDiskSerializer(directoryToSave).serialize(arrayOf(actual))

        sort(expected)
        sort(actual)
        val expectedString = StringSerializer.serialize(arrayOf(expected)).unescape()
        val actualString = StringSerializer.serialize(arrayOf(actual)).unescape()
        Assert.assertEquals("Expected:$expectedString but was: $actualString", expectedString, actualString)
    }

    private fun sort(expected: FileSystemEntry) {
        if (expected is VirtualDirectory) {
            expected.children().forEach { sort(it) }
            Arrays.sort(expected.children(), { o1, o2 -> o1.name().compareTo(o2.name()) })
        }
    }

    private fun String.unescape() = replace("\\/", "/")

    private fun SketchElement.toExpectedFsEntry(): FileSystemEntry {
        return SketchSerializer().serialize(testDataName() + "_expected.sketch", this)
    }
}