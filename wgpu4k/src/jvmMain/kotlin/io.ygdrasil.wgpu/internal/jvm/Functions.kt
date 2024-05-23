package io.ygdrasil.wgpu.`internal`.jvm

import com.sun.jna.Library
import com.sun.jna.Pointer

public val libWGPULibrary: WGPULibrary by lazy {
	NativeLoad<WGPULibrary>("WGPU")
}

public interface WGPULibrary : Library {

	/**
	 * @param callback mapped from WGPULogCallback
	 * @param userdata mapped from (Void)*
	 */
	public fun wgpuSetLogCallback(callback: WGPULogCallback?, userdata: Pointer?)

	/**
	 * @param level mapped from WGPULogLevel
	 */
	public fun wgpuSetLogLevel(level: Int)


}

/**
 * @param callback mapped from WGPULogCallback
 * @param userdata mapped from (Void)*
 */
public fun wgpuSetLogCallback(callback: WGPULogCallback?, userdata: Pointer?): Unit =
	libWGPULibrary.wgpuSetLogCallback(callback, userdata)

/**
 * @param level mapped from WGPULogLevel
 */
public fun wgpuSetLogLevel(level: Int): Unit = libWGPULibrary.wgpuSetLogLevel(level)

