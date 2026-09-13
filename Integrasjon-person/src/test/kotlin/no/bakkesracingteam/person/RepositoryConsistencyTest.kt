package no.bakkesracingteam.person

import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.extension
import kotlin.io.path.invariantSeparatorsPathString
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RepositoryConsistencyTest {

    private val projectRoot: Path = Path.of("").toAbsolutePath()

    @Test
    fun `modulen bruker autoritativt groupId og samsvarende pakkesti`() {
        val pom = Files.readString(projectRoot.resolve("pom.xml"))
        val kotlinFiles = Files.walk(projectRoot.resolve("src"))
            .use { paths -> paths.filter { it.extension == "kt" }.toList() }

        assertTrue(pom.contains("<groupId>no.bakkesracingteam</groupId>"))
        kotlinFiles.forEach { file ->
            val source = Files.readString(file)
            val packageName = Regex("""^package\s+([\w.]+)""").find(source)!!.groupValues[1]
            val sourceRoot = if (file.startsWith(projectRoot.resolve("src/main"))) {
                projectRoot.resolve("src/main/kotlin")
            } else {
                projectRoot.resolve("src/test/kotlin")
            }
            val packagePath = sourceRoot.relativize(file.parent).invariantSeparatorsPathString

            assertTrue(
                packageName.startsWith("no.bakkesracingteam.person"),
                "Feil namespace i ${file.invariantSeparatorsPathString}",
            )
            assertEquals(packageName.replace('.', '/'), packagePath)
        }
    }
}
