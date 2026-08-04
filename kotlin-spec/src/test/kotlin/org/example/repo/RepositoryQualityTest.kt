package org.example.repo

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RepositoryQualityTest {

    private val projectRoot: Path = Path.of("").toAbsolutePath()

    @Test
    fun `intermediate should not contain placeholder or malformed filenames`() {
        val names = Files.list(projectRoot.resolve("src/main/kotlin/Intermediate"))
            .use { stream -> stream.map { it.fileName.toString() }.collect(Collectors.toList()) }

        assertFalse("exer.kt" in names)
        assertFalse(names.any { it.contains('\uFEFF') })
        assertTrue("Also.kt" in names)
        assertTrue("Apply.kt" in names)
    }

    @Test
    fun `readme should describe start here and learning path`() {
        val readme = Files.readString(projectRoot.resolve("README.md"))

        assertTrue(readme.contains("## Start her"))
        assertTrue(readme.contains("Anbefalt læringssti"))
        assertTrue(readme.contains("basis -> oop -> intermediate -> functional -> advanced"))
    }

    @Test
    fun `repo should expose dedicated files for data classes and generics`() {
        assertTrue(Files.exists(projectRoot.resolve("src/main/kotlin/basis/DataClasses.kt")))
        assertTrue(Files.exists(projectRoot.resolve("src/main/kotlin/Intermediate/Generics.kt")))
    }
}
