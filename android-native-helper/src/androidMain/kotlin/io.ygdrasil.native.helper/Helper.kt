package io.ygdrasil.nativeHelper

object Helper {
    init {
        System.loadLibrary("native_helper")
    }

    external fun nativeWindowFromSurface(androidSurface: android.view.Surface): Long
}