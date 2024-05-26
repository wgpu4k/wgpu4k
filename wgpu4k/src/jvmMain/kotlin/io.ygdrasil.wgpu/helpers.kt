package io.ygdrasil.wgpu

import com.sun.jna.NativeLong
import com.sun.jna.Structure

internal fun Int.toNativeLong(): NativeLong = toLong()
	.let(::NativeLong)

internal fun Boolean.toInt() = when(this) {
	true -> 1
	false -> 0
}

inline fun <T : Any, reified B : Structure> Array<T>.toStructureArray(updateFrom: B.(T) -> Unit): Array<B> {
	val instance = (B::class.constructors.find { it.parameters.isEmpty() }?.call()
		?: B::class.constructors.find { it.parameters.size == 1 }?.call(null))
		?: error("fail to find suitable constructor of type ${B::class}")
	@Suppress("UNCHECKED_CAST")
	return when (isNotEmpty()) {
		true -> (instance.toArray(size) as Array<B>)
			.also { forEachIndexed { index, original -> it[index].updateFrom(original) } }
		else -> arrayOf()
	}

}
