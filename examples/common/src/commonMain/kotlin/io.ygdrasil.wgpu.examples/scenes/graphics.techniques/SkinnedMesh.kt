package io.ygdrasil.wgpu.examples.scenes.graphics.techniques

import io.ygdrasil.wgpu.examples.Application


class WhaleScene : Application.Scene() {

    data class Settings(
        val cameraX: Double = 0.0,
        val cameraY: Double = -5.1,
        val cameraZ: Double = -14.6,
        val objectScale: Int = 1,
        val angle: Double = 0.2,
        val speed: Int = 50,
        val renderMode: String = "NORMAL",
        val skinMode: String = "ON"
    )

    override fun Application.initialiaze() {
        TODO("Not yet implemented")
    }

    override fun Application.render() {
        TODO("Not yet implemented")
    }

}