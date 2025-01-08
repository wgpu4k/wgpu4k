import java.io.File

object Paths {
    private val base = File(".")
    private val sourceBase = base
        .resolve("wgpu4k")
        .resolve("src")

    val commonMain = sourceBase
        .resolve("commonMain")
        .resolve("kotlin")

    val commonNativeMain = sourceBase
        .resolve("commonNativeMain")
        .resolve("kotlin")

    val commonWebMain = sourceBase
        .resolve("commonWebMain")
        .resolve("kotlin")
}