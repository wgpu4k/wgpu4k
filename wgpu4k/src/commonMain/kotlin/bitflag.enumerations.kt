// This file has been generated DO NOT EDIT !!!
package io.ygdrasil.webgpu

enum class BufferUsage(override val value: Int, override val uValue: UInt): EnumerationWithValue {
	None(0, 0u),
	MapRead(1, 1u),
	MapWrite(2, 2u),
	CopySrc(4, 4u),
	CopyDst(8, 8u),
	Index(16, 16u),
	Vertex(32, 32u),
	Uniform(64, 64u),
	Storage(128, 128u),
	Indirect(256, 256u),
	QueryResolve(512, 512u),
}
enum class ColorWriteMask(override val value: Int, override val uValue: UInt): EnumerationWithValue {
	None(0, 0u),
	Red(1, 1u),
	Green(2, 2u),
	Blue(4, 4u),
	Alpha(8, 8u),
	All(15, 15u),
}
enum class MapMode(override val value: Int, override val uValue: UInt): EnumerationWithValue {
	None(0, 0u),
	Read(1, 1u),
	Write(2, 2u),
}
enum class ShaderStage(override val value: Int, override val uValue: UInt): EnumerationWithValue {
	None(0, 0u),
	Vertex(1, 1u),
	Fragment(2, 2u),
	Compute(4, 4u),
}
enum class TextureUsage(override val value: Int, override val uValue: UInt): EnumerationWithValue {
	None(0, 0u),
	CopySrc(1, 1u),
	CopyDst(2, 2u),
	TextureBinding(4, 4u),
	StorageBinding(8, 8u),
	RenderAttachment(16, 16u),
}
