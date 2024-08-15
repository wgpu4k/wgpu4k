@file:OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import platform.android.JNIEnvVar
import platform.android.jclass
import kotlin.experimental.ExperimentalNativeApi

@CName("Java_org_jonnyzzz_jni_java_NativeHost_callInt")
fun hello(env: CPointer<JNIEnvVar>, clazz: jclass,) {
    println("hello")
}