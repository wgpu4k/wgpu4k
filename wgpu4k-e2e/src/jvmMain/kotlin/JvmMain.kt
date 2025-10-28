import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.examples.headless.captureScene
import io.ygdrasil.webgpu.examples.headless.findWithName
import io.ygdrasil.webgpu.examples.headless.getHeadlessContext
import io.ygdrasil.webgpu.examples.headless.initLog
import io.ygdrasil.webgpu.examples.loadScenes
import kotlinx.coroutines.runBlocking

private val logger = KotlinLogging.logger {}

fun main() {
    runBlocking {
        val context = getHeadlessContext()
        initLog()
        val screenshotPath = System.getProperty("screenshotPath")
        val renderingContext = context.renderingContext
        val availableScenes = loadScenes(context)
        scenes.forEach { (sceneName, frames) ->
            availableScenes.findWithName(sceneName).let { scene ->
                scene.initialize()
                frames.forEach { frame ->
                    logger.info { "Rendering frame $frame of $sceneName" }
                    captureScene(context, scene, frame, renderingContext, screenshotPath, sceneName)
                }
                scene.close()
            }
        }
        context.close()
    }
}