package org.example.repo

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors
import kotlin.test.Test
import kotlin.test.assertEquals
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

    @Test
    fun `pedagogical files should follow the documented header contract`() {
        val sourceRoot = projectRoot.resolve("src/main/kotlin")
        val sourceFiles = Files.walk(sourceRoot)
            .use { paths -> paths.filter { it.toString().endsWith(".kt") }.toList() }

        sourceFiles.forEach { file ->
            val source = Files.readString(file)
            val header = source.substringAfter("/**", "").substringBefore("*/", "")
            val relativePath = sourceRoot.relativize(file)

            assertTrue(header.isNotEmpty(), "$relativePath mangler blokk-kommentar")
            assertTrue(header.contains("Dekker:"), "$relativePath mangler Dekker")
            assertTrue(header.contains("Bruk når:"), "$relativePath mangler Bruk når")
            assertTrue(
                header.contains("NB:") || header.contains("Tip:"),
                "$relativePath mangler NB eller Tip",
            )
            assertTrue(
                header.contains("https://kotlinlang.org/"),
                "$relativePath mangler lenke til offisiell Kotlin-dokumentasjon",
            )
        }
    }

    @Test
    fun `documentation should match configured main class and JDK target`() {
        val pom = Files.readString(projectRoot.resolve("pom.xml"))
        val mainClass = Regex("<mainClass>([^<]+)</mainClass>").find(pom)!!.groupValues[1]
        val jvmTarget = Regex("<kotlin.compiler.jvmTarget>([^<]+)</kotlin.compiler.jvmTarget>")
            .find(pom)!!
            .groupValues[1]

        listOf("README.md", "AGENTS.md", "CLAUDE.md").forEach { fileName ->
            val documentation = Files.readString(projectRoot.resolve(fileName))
            assertTrue(
                documentation.contains(mainClass),
                "$fileName må omtale konfigurert main class $mainClass",
            )
        }

        val readme = Files.readString(projectRoot.resolve("README.md"))
        val documentedJdk = Regex("""JDK\s+(\d+)""").find(readme)!!.groupValues[1]
        assertEquals(jvmTarget, documentedJdk)
    }
}
