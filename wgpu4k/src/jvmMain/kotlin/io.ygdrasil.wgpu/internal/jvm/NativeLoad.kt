package io.ygdrasil.wgpu.internal.jvm

import com.sun.jna.FunctionMapper
import com.sun.jna.Library
import com.sun.jna.Native

annotation class NativeName(val name: String) {
	companion object {
		val options = mapOf(
			Library.OPTION_FUNCTION_MAPPER to FunctionMapper { _, method ->
				method.getAnnotation(NativeName::class.java)?.name ?: method.name
			}
		)
	}
}

inline fun <reified T : Library> NativeLoad(name: String): T =
	Native.load(name, T::class.java, NativeName.options) as T

