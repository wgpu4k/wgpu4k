import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.examples.headless.captureScene
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

private val logger = KotlinLogging.logger {}

fun main() {

    MainScope().launch {
        try {
            captureScene()
        } catch (error: Throwable) {
            logger.error(error) { "Failed to capture scene" }
        }
        println("render ended")
    }
}
