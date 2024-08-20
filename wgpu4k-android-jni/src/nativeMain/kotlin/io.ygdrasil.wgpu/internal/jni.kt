@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.internal

import kotlinx.cinterop.ArenaBase
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.UByteVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.cstr
import kotlinx.cinterop.invoke
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed
import kotlinx.cinterop.ptr
import platform.android.JNIEnvVar
import platform.android.jclass
import platform.android.jmethodID
import platform.android.jobject
import platform.android.jstring
import platform.android.jvalue

/**
 * https://docs.oracle.com/javase/8/docs/technotes/guides/jni/spec/types.html
 *
 * Java VM Type Signatures
 * Type Signature	Java Type
 * Z	boolean
 * B	byte
 * C	char
 * S	short
 * I	int
 * J	long
 * F	float
 * D	double
 * L fully-qualified-class ;	fully-qualified-class
 * [ type	type[]
 * ( arg-types ) ret-type	method type
 */

typealias JNIEnvPointer = CPointer<JNIEnvVar>

internal fun JNIEnvPointer.callBooleanMethodFrom(thiz: jobject, methodName: String): Boolean = memScoped {
    val jclass = getObjectClass(thiz) ?: error("fail to get class of $thiz")
    val methodId = getMethodID(jclass, methodName, "()Z") ?: error("fail to get method of $methodName")
    return callBooleanMethodA(thiz, methodId) == 1.toUByte()
}

internal fun JNIEnvPointer.callFloatMethodFrom(thiz: jobject, methodName: String): Float = memScoped {
    val jclass = getObjectClass(thiz) ?: error("fail to get class of $thiz")
    val methodId = getMethodID(jclass, methodName, "()F") ?: error("fail to get method of $methodName")
    return callFloatMethodA(thiz, methodId)
}

internal fun JNIEnvPointer.callIntMethodFrom(thiz: jobject, methodName: String): Int = memScoped {
    val jclass = getObjectClass(thiz) ?: error("fail to get class of $thiz")
    val methodId = getMethodID(jclass, methodName, "()I") ?: error("fail to get method of $methodName")
    return callIntMethodA(thiz, methodId)
}

internal fun JNIEnvPointer.callLongMethodFrom(thiz: jobject, methodName: String): Long = memScoped {
    val jclass = getObjectClass(thiz) ?: error("fail to get class of $thiz")
    val methodId = getMethodID(jclass, methodName, "()J") ?: error("fail to get method of $methodName")
    return callLongMethodA(thiz, methodId)
}

internal fun JNIEnvPointer.callStringMethodFrom(thiz: jobject, methodName: String): jstring? =
    callObjectMethodFrom(thiz, methodName, "java/lang/String")

internal fun JNIEnvPointer.callListMethodFrom(thiz: jobject, methodName: String): JniList? =
    callObjectMethodFrom(thiz, methodName, "java/util/List")?.let { JniList(it) }


internal fun JNIEnvPointer.callObjectMethodFrom(thiz: jobject, methodName: String, returnType: String, args: CPointer<jvalue>? = null, argsType: String = ""): jobject? =
    memScoped {
        val jclass = getObjectClass(thiz) ?: error("fail to get class of $thiz")
        val methodId = getMethodID(jclass, methodName, "($argsType)L$returnType;") ?: error("fail to get method of $methodName with return type $returnType and args $argsType")
        return callObjectMethodA(thiz, methodId, args)
    }

internal fun JNIEnvPointer.getObjectClass(thiz: jobject) = pointed.pointed!!.GetObjectClass!!.invoke(this, thiz)
internal fun JNIEnvPointer.getMethodID(jclass: jclass, methodName: String, signature: String): jmethodID? = memScoped {
    return pointed.pointed!!.GetMethodID!!.invoke(this@getMethodID, jclass, methodName.cstr.ptr, signature.cstr.ptr)
}

internal fun JNIEnvPointer.callIntMethodA(thiz: jobject, methodId: jmethodID) =
    pointed.pointed?.CallIntMethodA!!.invoke(this, thiz, methodId, null)
internal fun JNIEnvPointer.callLongMethodA(thiz: jobject, methodId: jmethodID) =
    pointed.pointed?.CallLongMethodA!!.invoke(this, thiz, methodId, null)
internal fun JNIEnvPointer.callBooleanMethodA(thiz: jobject, methodId: jmethodID) =
    pointed.pointed?.CallBooleanMethodA!!.invoke(this, thiz, methodId, null)
internal fun JNIEnvPointer.callFloatMethodA(thiz: jobject, methodId: jmethodID) =
    pointed.pointed?.CallFloatMethodA!!.invoke(this, thiz, methodId, null)

internal fun JNIEnvPointer.callObjectMethodA(thiz: jobject, methodId: jmethodID, args: CPointer<jvalue>?) =
    pointed.pointed?.CallObjectMethodA!!.invoke(this, thiz, methodId, args)

internal fun jstring.toCString(env: JNIEnvPointer, arena: ArenaBase): CPointer<ByteVar>? {
    return env.pointed.pointed
        ?.GetStringUTFChars
        ?.invoke(env, this, arena.alloc<UByteVar>().ptr)
}

fun Boolean.toUInt() = if (true) 1u else 0u

fun samplestring(env: CPointer<JNIEnvVar>, clazz: jclass, backendHolder: jobject?) {
    memScoped {
        env.pointed.pointed!!.NewStringUTF!!.invoke(env, "This is from Kotlin Native!!".cstr.ptr)!!
    }
}

fun samplecall(env: CPointer<JNIEnvVar>, thiz: jobject): jstring {
    memScoped {
        val jniEnvVal = env.pointed.pointed!!
        val jclass = jniEnvVal.GetObjectClass!!.invoke(env, thiz)
        val methodId =
            jniEnvVal.GetMethodID!!.invoke(env, jclass, "callFromNative".cstr.ptr, "()Ljava/lang/String;".cstr.ptr)
        return jniEnvVal.CallObjectMethodA!!.invoke(env, thiz, methodId, null) as jstring
    }
}
