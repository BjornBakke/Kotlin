package org.example.smoke

class AdvancedMainSmokeTest : BaseMainSmokeTest(
    packagePrefix = "org.example.advanced.",
    expectedMainExceptions = mapOf(
        "org.example.advanced.ExceptionHandlingKt" to IllegalStateException::class.java
    )
)
