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
        return env.callIntMethodFrom(handler, "getSize")
    }

    fun getObject(index: Int, env: JNIEnvPointer, returnType: String): jobject? = memScoped {
        println("getObject at $index with return type $returnType and handler $handler")
        return env.callObjectMethodFrom(
            handler, "get", returnType,
            alloc<jvalue>().also {
                it.i = index
            }.ptr
        )
    }
}