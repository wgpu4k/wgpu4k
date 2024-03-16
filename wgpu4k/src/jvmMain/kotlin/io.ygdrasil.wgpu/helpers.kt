package io.ygdrasil.wgpu

import com.sun.jna.NativeLong
import com.sun.jna.Structure
import dev.krud.shapeshift.ShapeShiftBuilder
import dev.krud.shapeshift.dsl.KotlinDslMappingDefinitionBuilder
import dev.krud.shapeshift.enums.AutoMappingStrategy
import dev.krud.shapeshift.transformer.base.MappingTransformer
import dev.krud.shapeshift.transformer.base.MappingTransformerContext
import io.ygdrasil.wgpu.internal.jvm.WGPUBindGroupLayout
import io.ygdrasil.wgpu.internal.jvm.WGPUExtent3D

internal fun Long.toNativeLong(): NativeLong = let(::NativeLong)
internal fun Int.toNativeLong(): NativeLong = toLong()
	.let(::NativeLong)

class EnumerationTransformer<T : EnumerationWithValue> : MappingTransformer<T, Int> {
	override fun transform(context: MappingTransformerContext<out T>): Int? {
		return context.originalValue?.value
	}
}

class GPUExtent3DDictStrictTransformer : MappingTransformer<GPUExtent3DDictStrict, WGPUExtent3D> {
	override fun transform(context: MappingTransformerContext<out GPUExtent3DDictStrict>): WGPUExtent3D? =
		context.originalValue?.let {
			WGPUExtent3D().apply {
				width = it.width
				height = it.height
				depthOrArrayLayers = it.depthOrArrayLayers ?: 1
			}
		}
}

class BindGroupLayoutTransformer : MappingTransformer<BindGroupLayout, WGPUBindGroupLayout> {
	override fun transform(context: MappingTransformerContext<out BindGroupLayout>): WGPUBindGroupLayout? =
		context.originalValue?.handler
}

inline fun <reified From : Any, reified To : Any> mapper(block: KotlinDslMappingDefinitionBuilder<From, To>.() -> Unit) =
	ShapeShiftBuilder().withMapping<From, To> {
		autoMap(AutoMappingStrategy.BY_NAME)
		block()
	}.build()


inline fun <T : Any, reified B : Structure> Array<T>.toStructureArray(updateFrom: B.(T) -> Unit): Array<B> {
	val instance = (B::class.constructors.find { it.parameters.isEmpty() }?.call()
		?: B::class.constructors.find { it.parameters.size == 1 }?.call(null))
		?: error("fail to find suitable constructor of type ${B::class}")
	return (instance.toArray(size) as Array<B>)
		.also { forEachIndexed { index, original -> it[index].updateFrom(original) } }
}