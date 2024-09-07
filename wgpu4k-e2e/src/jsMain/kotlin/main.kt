
import io.ygdrasil.wgpu.examples.headless.captureScene
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

fun main() {

    MainScope().launch {
        captureScene()
        console.log("render ended")
    }
}