package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.RenderPassDescriptor

class TitlingManager() {
	fun nextFrame() {
		if (value > 255.0) {
			delta = -5.0
		} else if (value < 0) {
			delta = 5.0
		}

		value += delta
	}

	fun reset() {
		delta = 5.0
		value = 0.0
	}

	private var delta = 5.0
	var value = 0.0
		private set
}

class BlueTitlingScene : Application.Scene() {

	private val titlingManager = TitlingManager()

	override fun Application.initialiaze() {
		titlingManager.reset()
	}

	override fun Application.render() = autoClosableContext {
		titlingManager.nextFrame()

		// Clear the canvas with a render pass
		val encoder = device.createCommandEncoder()
			.bind()

		val texture = renderingContext.getCurrentTexture()
			.bind()
		val view = texture.createView()
			.bind()

		val renderPassEncoder = encoder.beginRenderPass(
			RenderPassDescriptor(
				colorAttachments = arrayOf(
					RenderPassDescriptor.ColorAttachment(
						view = view,
						loadOp = "clear",
						clearValue = arrayOf(0, 0, titlingManager.value / 255.0, 1.0),
						storeOp = "store"
					)
				)
			)
		).bind()
		renderPassEncoder.end()

		val commandBuffer = encoder.finish()
			.bind()

		device.queue.submit(arrayOf(commandBuffer))

		renderingContext.present()

	}

}