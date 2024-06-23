import com.microsoft.playwright.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import java.io.File
import java.util.*


val scenes = listOf(
    "HelloTriangleScene" to listOf(0),
    "HelloTriangleMSAAScene" to listOf(0),
    "HelloTriangleRotatingScene" to listOf(0, 10, 50, 100),
    "RotatingCubeScene" to listOf(0, 10, 50, 100),
    "TwoCubesScene" to listOf(0, 10, 50, 100),
    "CubemapScene" to listOf(0, 10, 50, 100),
    "InstancedCubeScene" to listOf(0, 10, 50, 100),
    "TexturedCubeScene" to listOf(0, 10, 50, 100),
)

fun endToEndWebserver(basePath: String): NettyApplicationEngine {
    val pagePath = "$basePath/build/dist/js/productionExecutable"
    println("serve page at $pagePath")
    return embeddedServer(Netty, port = 9000) {

        routing {
            staticFiles(
                "/",
                File(pagePath)
            )
        }
    }.start(wait = false)
}


fun browser(projectDir: File) {
    println("start to browser")

    Playwright.create().use { playwright ->
        val browserTypes: List<BrowserType> = Arrays.asList(
            playwright.chromium(),
            // Not yet suported
            // playwright.webkit(),
            // Not yet suported
            //playwright.firefox()
        )
        for (browserType in browserTypes) {
            browserType.launch().use { browser ->
                val context: BrowserContext = browser.newContext(
                    Browser.NewContextOptions()
                        .setViewportSize(256, 256)
                )
                val page: Page = context.newPage()
                context.onConsoleMessage {
                    println(it.text())
                }
                scenes.forEach { (sceneName, frames) ->
                    frames.forEach { frame ->
                        page.navigate("http://localhost:9000/index.html?scene=$sceneName&frame=$frame")
                        Thread.sleep(1000)
                        page.screenshot(
                            Page.ScreenshotOptions()
                                .setPath(
                                    projectDir
                                        .resolve("js-${browserType.name().toString()}")
                                        .also { it.mkdirs() }
                                        .resolve("$sceneName-$frame.png")
                                        .toPath()
                                )
                        )
                    }
                }


            }
        }
    }

}

