@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.internal

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cstr
import kotlinx.cinterop.invoke
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed
import platform.android.JNIEnvVar
import platform.android.jclass
import platform.android.jmethodID
import platform.android.jobject
import platform.android.jstring

typealias JNIEnvPointer = CPointer<JNIEnvVar>

internal fun JNIEnvPointer.callIntMethodFrom(thiz: jobject, methodName: String): Int = memScoped{
    val jclass = getObjectClass(thiz) ?: error("fail to get class of $thiz")
    val methodId = getMethodID(jclass, methodName, "()I") ?: error("fail to get method of $methodName")
    return callObjectMethodA(thiz, methodId)
}

internal fun JNIEnvPointer.getObjectClass(thiz: jobject) = pointed.pointed!!.GetObjectClass!!.invoke(this, thiz)
internal fun JNIEnvPointer.getMethodID(jclass: jclass, methodName: String, signature: String) = memScoped {
    return@memScoped pointed.pointed!!.GetMethodID!!.invoke(this@getMethodID, jclass, methodName.cstr.ptr, signature.cstr.ptr)
}
internal fun JNIEnvPointer.callObjectMethodA(thiz: jobject, methodId: jmethodID) = pointed.pointed!!.CallIntMethodA!!.invoke(this, thiz, methodId, null)



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
