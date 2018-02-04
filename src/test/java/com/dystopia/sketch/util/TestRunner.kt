package com.dystopia.sketch.util

import com.dystopia.sketch.util.UniqueIdGenerator
import org.junit.runner.notification.RunNotifier
import org.junit.runners.BlockJUnit4ClassRunner

class TestRunner(clazz: Class<*>) : BlockJUnit4ClassRunner(clazz) {
    override fun run(notifier: RunNotifier?) {
        UniqueIdGenerator.setGenerator(TestUniqueIdGenerator())
        super.run(notifier)
    }
}