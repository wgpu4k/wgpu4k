import kotlinx.coroutines.runBlocking
import java.io.File


fun main() {
    val prefixPath = System.getProperty("prefixPath")

    val projectDir = System.getProperty("projectDir")
        .let { File(it) }
    val jsPagePath = System.getProperty("jsPagePath")
        .let { File(it) }

    projectDir.resolve("$prefixPath-chromium").mkdir()
    val server = runWebserverWithFilesAt(jsPagePath)

    val port = runBlocking { server.resolvedConnectors().first().port }

    runBrowserAndCaptureScreenshot(projectDir, prefixPath, port)
    server.stop()
}