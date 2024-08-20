package io.ygdrasil.wgpu.internal

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import platform.android.jobject
import platform.android.jvalue

@OptIn(ExperimentalForeignApi::class)
value class JniList(val handler: jobject) {

    fun getSize(env: JNIEnvPointer): Int {
        println("getSize with handler $handler")
        return env.callIntMethodFrom(handler, "size")
    }

    fun getObject(index: Int, env: JNIEnvPointer): jobject? = memScoped {
        println("getObject at $index and handler $handler")
        return env.callObjectMethodFrom(
            handler, "get", "java/lang/Object",
            args = alloc<jvalue>().also {
                it.i = index
            }.ptr,
            argsType = "I"
        )
    }
}