@file:OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)

package io.ygdrasil.native.helper

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toLong
import platform.android.ANativeWindow_fromSurface
import platform.android.JNIEnvVar
import platform.android.jclass
import platform.android.jlong
import platform.android.jobject
import kotlin.experimental.ExperimentalNativeApi

typealias JNIEnvPointer = CPointer<JNIEnvVar>

@CName("Java_io_ygdrasil_nativeHelper_JniInterface_nativeWindowFromSurface")
fun nativeWindowFromSurface(env: JNIEnvPointer, thiz: jclass, surface: jobject) : jlong = memScoped {
    return ANativeWindow_fromSurface(env.reinterpret(), surface)
        .toLong()
}
