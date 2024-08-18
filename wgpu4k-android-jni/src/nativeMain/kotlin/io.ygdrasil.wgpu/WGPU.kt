@file:OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.*
import platform.android.*
import kotlin.experimental.ExperimentalNativeApi

typealias JNIEnv = CPointer<JNIEnvVar>

@CName("Java_io_ygdrasil_wgpu_internal_JniInterfaceV2_wgpuCreateInstance")
fun wgpuCreateInstance(env: JNIEnv, thiz_: jclass, backendHolder: jobject?) : jlong = memScoped {
    return if (backendHolder == null) {
        webgpu.wgpuCreateInstance(null).toLong()
    } else {
        val backend = env.callIntMethodFrom(backendHolder, "getValue")

        val descriptor = alloc<webgpu.WGPUInstanceDescriptor>().apply  {
            nextInChain = alloc<webgpu.WGPUInstanceExtras>().apply {
                chain.sType = webgpu.WGPUSType_InstanceExtras
                backends = backend.toUInt()
            }.ptr.reinterpret()
        }

        webgpu.wgpuCreateInstance(descriptor.ptr).toLong()
    }

}

fun samplestring(env: CPointer<JNIEnvVar>, clazz: jclass, backendHolder: jobject?) {
    memScoped {
        env.pointed.pointed!!.NewStringUTF!!.invoke(env, "This is from Kotlin Native!!".cstr.ptr)!!
    }
}

fun samplecall(env: CPointer<JNIEnvVar>, thiz: jobject): jstring {
    memScoped {
        val jniEnvVal = env.pointed.pointed!!
        val jclass = jniEnvVal.GetObjectClass!!.invoke(env, thiz)
        val methodId = jniEnvVal.GetMethodID!!.invoke(env, jclass, "callFromNative".cstr.ptr, "()Ljava/lang/String;".cstr.ptr)
        return jniEnvVal.CallObjectMethodA!!.invoke(env, thiz, methodId, null) as jstring
    }
}

private fun JNIEnv.callIntMethodFrom(thiz: jobject, methodName: String): Int {
    val jniEnvVal = pointed.pointed!!
    val jclass = getObjectClass(thiz)!!
    val methodId = getMethodID(jclass, methodName, "()I;")!!
    return callObjectMethodA(thiz, methodId)
}

private fun JNIEnv.getObjectClass(thiz: jobject) = pointed.pointed!!.GetObjectClass!!.invoke(this, thiz)
private fun JNIEnv.getMethodID(jclass: jclass, methodName: String, signature: String) = memScoped {
    pointed.pointed!!.GetMethodID!!.invoke(this@getMethodID, jclass, methodName.cstr.ptr, signature.cstr.ptr)
}
private fun JNIEnv.callObjectMethodA(thiz: jobject, methodId: jmethodID) = pointed.pointed!!.CallIntMethodA!!.invoke(this, thiz, methodId, null)