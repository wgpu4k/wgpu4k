import kotlinx.coroutines.runBlocking


fun main() {
    runBlocking {
        try {
            captureScene()
        } catch (error: Throwable) {
            error.printStackTrace()
        }
    }
}