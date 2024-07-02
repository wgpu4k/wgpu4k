
import korlibs.image.bitmap.Bitmap32
import korlibs.image.bitmap.computePsnr
import korlibs.image.format.readBitmap
import korlibs.io.file.std.toVfs
import kotlinx.coroutines.*
import org.gradle.api.logging.Logger
import java.io.File

data class ComparisonResult(
    val actual: File,
    val expected: File,
    val psnr: Double
) {

    val similar get() = psnr >= 30.0

    override fun toString(): String {
        return "psnr: $psnr on actual ${actual.absolutePath} and exoected ${expected.absolutePath}"
    }
}

fun compareImages(basePath: File, logger: Logger): List<ComparisonResult> {
    return runBlocking {
        val expectedDirectory = basePath.resolve("expected")
        val expectedImages = (expectedDirectory.listFiles() ?: error("fail to list file in ${expectedDirectory.absolutePath}"))
            .filter { it.extension == "png" }
        (basePath.listFiles()?: error("fail to list fil in ${expectedDirectory.absolutePath}"))
            // Remove non directory, build directory and expected directory images
            .filter { it.isDirectory && it != expectedDirectory && it.name.startsWith("build").not() }
            .flatMap { directory ->
                logger.info("will compare image on  ${directory.absolutePath}")
                (directory.listFiles() ?: error("fail to list fil in ${directory.absolutePath}"))
                    .filter { it.extension == "png" }
                    .filter { it.name != "gpu.png" } // Filter this image wich is only to debug
                    .map { imageFile -> imageFile to (expectedImages.find { imageFile.name == it.name } ?: error("failed to find miror image of ${imageFile.absolutePath}")) }
                    .map { (actual, expected) ->
                        logger.info("will test $actual, $expected")
                        val bitmap1 = actual.toVfs().readBitmap().toBMP32().premultipliedIfRequired()
                        val bitmap2 = expected.toVfs().readBitmap().toBMP32().premultipliedIfRequired()
                        val psnr = Bitmap32.computePsnr(bitmap1, bitmap2)
                        logger.info("psnr: $psnr")
                        ComparisonResult(
                            actual,
                            expected,
                            psnr
                        )
                    }
            }
    }
}
