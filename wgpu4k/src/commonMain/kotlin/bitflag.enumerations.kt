// This file has been generated DO NOT EDIT !!!
package io.ygdrasil.webgpu

enum class BufferUsage(override val value: ULong): EnumerationWithValue {
	None(0uL),
	MapRead(1uL),
	MapWrite(2uL),
	CopySrc(4uL),
	CopyDst(8uL),
	Index(16uL),
	Vertex(32uL),
	Uniform(64uL),
	Storage(128uL),
	Indirect(256uL),
	QueryResolve(512uL),
}
enum class ColorWriteMask(override val value: ULong): EnumerationWithValue {
	None(0uL),
	Red(1uL),
	Green(2uL),
	Blue(4uL),
	Alpha(8uL),
	All(15uL),
}
enum class MapMode(override val value: ULong): EnumerationWithValue {
	None(0uL),
	Read(1uL),
	Write(2uL),
}
enum class ShaderStage(override val value: ULong): EnumerationWithValue {
	None(0uL),
	Vertex(1uL),
	Fragment(2uL),
	Compute(4uL),
}
enum class TextureUsage(override val value: ULong): EnumerationWithValue {
	None(0uL),
	CopySrc(1uL),
	CopyDst(2uL),
	TextureBinding(4uL),
	StorageBinding(8uL),
	RenderAttachment(16uL),
}
