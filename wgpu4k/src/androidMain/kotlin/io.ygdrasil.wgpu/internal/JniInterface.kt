package io.ygdrasil.wgpu.internal

class JniInterface  {
    init {
        System.loadLibrary("wgpu4k")
    }

    external fun createWgpuInstance2(): Long


    companion object {

        val instance by lazy { JniInterface() }

    }
}