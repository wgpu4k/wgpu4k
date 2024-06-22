import com.microsoft.playwright.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File
import java.nio.file.Paths
import java.util.*


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


fun browser() {
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
                page.navigate("http://localhost:9000")
                page.screenshot(
                    Page.ScreenshotOptions()
                        .setPath(
                            File("js")
                                .resolve(browserType.name().toString())
                                .also { it.mkdirs() }
                                .resolve("screenshot.png")
                                .toPath()
                        )
                )

            }
        }
    }

}

