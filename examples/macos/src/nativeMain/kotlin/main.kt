@file:OptIn(ExperimentalForeignApi::class, BetaInteropApi::class, ExperimentalForeignApi::class)

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.WGPU.Companion.createInstance
import kotlinx.cinterop.*
import kotlinx.coroutines.runBlocking
import platform.AppKit.*
import platform.Foundation.NSMakeRect
import platform.Foundation.NSNotification
import platform.Foundation.NSRect
import platform.QuartzCore.CAMetalLayer
import platform.darwin.NSObject
import platform.foundation.height
import platform.foundation.width

val windowStyle = NSWindowStyleMaskTitled or NSWindowStyleMaskMiniaturizable or
        NSWindowStyleMaskClosable or NSWindowStyleMaskResizable or NSBackingStoreBuffered

fun main2() {
    Application("hello")
        .run()

}

class Application(
    private val windowTitle: String
) {

    fun run() {
        autoreleasepool {
            val application = NSApplication.sharedApplication()

            val windowRect: CValue<NSRect> = run {
                val frame = NSScreen.mainScreen()!!.frame
                NSMakeRect(
                    .0, .0,
                    frame.width * 0.5,
                    frame.height * 0.5
                )
            }
            val window = NSWindow(windowRect, windowStyle, NSBackingStoreBuffered, false)
            window.contentView()?.setWantsLayer(true)
            val layer = CAMetalLayer.layer()
            window.contentView()?.setLayer(layer)

            application.delegate = object : NSObject(), NSApplicationDelegateProtocol {

                override fun applicationShouldTerminateAfterLastWindowClosed(sender: NSApplication): Boolean {
                    println("applicationShouldTerminateAfterLastWindowClosed")
                    return true
                }

                override fun applicationWillFinishLaunching(notification: NSNotification) {
                    println("applicationWillFinishLaunching")
                }

                override fun applicationDidFinishLaunching(notification: NSNotification) {
                    println("applicationDidFinishLaunching")

                    window.setTitle(windowTitle)

                    window.orderFrontRegardless()
                    window.center()


                    val wgpu = createInstance() ?: error("fail to wgpu instance")
                    val layerPointer: COpaquePointer = interpretCPointer<COpaque>(layer.objcPtr())!!.reinterpret()
                    val surface = wgpu.getSurfaceFromMetalLayer(layerPointer)
                            ?: error("fail to get wgpu surface")

                    val renderingContext = RenderingContext(surface) {
                        window.frame.width.toInt() to window.frame.height.toInt()
                    }

                    val adapter = wgpu.requestAdapter(renderingContext)
                        ?: error("fail to get adapter")

                    val device =  runBlocking { adapter.requestDevice() }
                        ?: error("fail to get device")

                    renderingContext.computeSurfaceCapabilities(adapter)

                    renderingContext.configure(
                        CanvasConfiguration(
                            device = device
                        )
                    )

                    val renderPipeline = device.createRenderPipeline(
                        RenderPipelineDescriptor(
                            vertex = RenderPipelineDescriptor.VertexState(
                                module = device.createShaderModule(
                                    ShaderModuleDescriptor(
                                        code = triangleVertexShader
                                    )
                                )
                            ),
                            fragment = RenderPipelineDescriptor.FragmentState(
                                module = device.createShaderModule(
                                    ShaderModuleDescriptor(
                                        code = redFragmentShader
                                    )
                                ),
                                targets = arrayOf(
                                    RenderPipelineDescriptor.FragmentState.ColorTargetState(
                                        format = renderingContext.textureFormat
                                    )
                                )
                            )
                        )
                    )

                    // Clear the canvas with a render pass
                    val encoder = device.createCommandEncoder()

                    val texture = renderingContext.getCurrentTexture()

                    val renderPassEncoder = encoder.beginRenderPass(
                        RenderPassDescriptor(
                            colorAttachments = arrayOf(
                                RenderPassDescriptor.ColorAttachment(
                                    view =  texture.createView(),
                                    loadOp = LoadOp.clear,
                                    clearValue = arrayOf(0, 0, 0, 1.0),
                                    storeOp = StoreOp.store
                                )
                            )
                        )
                    )

                    renderPassEncoder.setPipeline(renderPipeline)
                    renderPassEncoder.draw(3)
                    renderPassEncoder.end()

                    val commandBuffer = encoder.finish()

                    device.queue.submit(arrayOf(commandBuffer))

                    renderingContext.present()

                }

                override fun applicationWillTerminate(notification: NSNotification) {
                    println("applicationWillTerminate")
                }
            }

            application.run()

        }
    }
}


const val triangleVertexShader = """
@vertex
fn main(
  @builtin(vertex_index) VertexIndex : u32
) -> @builtin(position) vec4f {
  var pos = array<vec2f, 3>(
    vec2(0.0, 0.5),
    vec2(-0.5, -0.5),
    vec2(0.5, -0.5)
  );

  return vec4f(pos[VertexIndex], 0.0, 1.0);
}
"""

const val redFragmentShader = """
@fragment
fn main() -> @location(0) vec4f {
  return vec4(1.0, 0.0, 0.0, 1.0);
}
"""