package io.ygdrasil.nativeHelper

object JniInterface {
    init {
        System.loadLibrary("native_helper")
    }

    external fun nativeWindowFromSurface(androidSurface: android.view.Surface): Long
}