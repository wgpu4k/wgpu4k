import kotlinx.coroutines.runBlocking


fun main() {
    runBlocking {
        try {
            render()
        } catch (error: Throwable) {
            error.printStackTrace()
        }
    }
}