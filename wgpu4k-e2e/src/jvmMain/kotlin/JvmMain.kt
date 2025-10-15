import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.examples.headless.captureScene
import kotlinx.coroutines.runBlocking

private val logger = KotlinLogging.logger {}


fun main() {
    runBlocking {
        scenes.forEach { (name, frames) ->
            frames.forEach { frame ->
                System.setProperty("scene", name)
                System.setProperty("frame", "$frame")

                try {
                    captureScene()
                } catch (error: Throwable) {
                    logger.error(error) { "Failed to capture scene" }
                }
            }
        }
    }
}