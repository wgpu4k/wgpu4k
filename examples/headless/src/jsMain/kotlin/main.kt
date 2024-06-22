import korlibs.io.async.launch
import kotlinx.coroutines.MainScope

fun main() {

    MainScope().launch {
        captureScene()
    }
}