package org.example.smoke

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.io.File
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Modifier
import kotlin.test.assertTrue
import kotlin.test.fail

abstract class BaseMainSmokeTest(
    private val packagePrefix: String,
    private val expectedMainExceptions: Map<String, Class<out Throwable>> = emptyMap()
) {

    @TestFactory
    fun `main entrypoints should run according to expectations`(): List<DynamicTest> {
        val classLoader = Thread.currentThread().contextClassLoader
        val mainClasses = discoverMainClasses(classLoader)

        assertTrue(
            mainClasses.isNotEmpty(),
            "Fant ingen main-entrypoints i package '$packagePrefix'."
        )

        return mainClasses.map { className ->
            DynamicTest.dynamicTest("run $className.main") {
                try {
                    invokeMain(classLoader, className)
                    if (expectedMainExceptions.containsKey(className)) {
                        fail("$className forventes å kaste en exception, men returnerte normalt.")
                    }
                } catch (e: Throwable) {
                    val expected = expectedMainExceptions[className]
                    if (expected == null) {
                        throw e
                    }
                    if (!expected.isInstance(e)) {
                        fail(
                            "$className kastet ${e::class.java.name}, forventet ${expected.name}",
                            e
                        )
                    }
                }
            }
        }
    }

    private fun discoverMainClasses(classLoader: ClassLoader): List<String> {
        val classpathEntries = System.getProperty("java.class.path")
            .split(File.pathSeparator)
            .map(::File)

        val compiledClassesDir = classpathEntries.firstOrNull { entry ->
            entry.isDirectory &&
                entry.name.equals("classes", ignoreCase = true) &&
                entry.parentFile?.name.equals("target", ignoreCase = true)
        } ?: return emptyList()

        return compiledClassesDir
            .walkTopDown()
            .filter { file ->
                file.isFile &&
                    file.extension == "class" &&
                    !file.name.contains('$')
            }
            .map { file ->
                file.relativeTo(compiledClassesDir).path
                    .replace(File.separatorChar, '.')
                    .removeSuffix(".class")
            }
            .filter { className -> className.startsWith(packagePrefix) }
            .filter { className ->
                try {
                    val clazz = classLoader.loadClass(className)
                    clazz.methods.any { method ->
                        method.name == "main" && Modifier.isStatic(method.modifiers)
                    }
                } catch (_: Throwable) {
                    false
                }
            }
            .sorted()
            .toList()
    }

    private fun invokeMain(classLoader: ClassLoader, className: String) {
        val clazz = classLoader.loadClass(className)
        val noArgsMain = clazz.methods.firstOrNull { method ->
            method.name == "main" &&
                Modifier.isStatic(method.modifiers) &&
                method.parameterCount == 0
        }

        try {
            if (noArgsMain != null) {
                noArgsMain.invoke(null)
                return
            }

            val argsMain = clazz.methods.firstOrNull { method ->
                method.name == "main" &&
                    Modifier.isStatic(method.modifiers) &&
                    method.parameterCount == 1 &&
                    method.parameterTypes[0].isArray &&
                    method.parameterTypes[0].componentType == String::class.java
            } ?: error("Ingen støttet main-signatur funnet for $className")

            argsMain.invoke(null, emptyArray<String>())
        } catch (e: InvocationTargetException) {
            throw e.targetException ?: e
        }
    }
}
