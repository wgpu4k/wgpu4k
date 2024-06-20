import io.ygdrasil.wgpu.autoClosableContext
import io.ygdrasil.wgpu.examples.scenes.basic.HelloTriangleScene
import korlibs.io.async.launch
import kotlinx.coroutines.MainScope

fun main() {

    MainScope().launch {
        val context = getHeadlessContext()
        HelloTriangleScene(context).apply {
            autoClosableContext {
                render()

                val commandEncoder = context.device.createCommandEncoder().bind()
                commandEncoder.copyTextureToTexture()
                context.renderingContext.getCurrentTexture()
            }
        }




    }
}