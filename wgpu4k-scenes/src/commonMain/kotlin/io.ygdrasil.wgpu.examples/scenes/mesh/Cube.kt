package io.ygdrasil.wgpu.examples.scenes.mesh

object Cube {

	val cubeVertexSize = 4uL * 10uL // Byte size of one cube vertex.
	val cubePositionOffset = 0uL
	val cubeColorOffset = 4 * 4 // Byte offset of cube vertex color attribute.
	val cubeUVOffset = 4uL * 8uL
	val cubeVertexCount = 36u

	val cubeVertexArray = arrayOf(
		// float4 position, float4 color, float2 uv,
		1, -1, 1, 1, 1, 0, 1, 1, 0, 1,
		-1, -1, 1, 1, 0, 0, 1, 1, 1, 1,
		-1, -1, -1, 1, 0, 0, 0, 1, 1, 0,
		1, -1, -1, 1, 1, 0, 0, 1, 0, 0,
		1, -1, 1, 1, 1, 0, 1, 1, 0, 1,
		-1, -1, -1, 1, 0, 0, 0, 1, 1, 0,

		1, 1, 1, 1, 1, 1, 1, 1, 0, 1,
		1, -1, 1, 1, 1, 0, 1, 1, 1, 1,
		1, -1, -1, 1, 1, 0, 0, 1, 1, 0,
		1, 1, -1, 1, 1, 1, 0, 1, 0, 0,
		1, 1, 1, 1, 1, 1, 1, 1, 0, 1,
		1, -1, -1, 1, 1, 0, 0, 1, 1, 0,

		-1, 1, 1, 1, 0, 1, 1, 1, 0, 1,
		1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
		1, 1, -1, 1, 1, 1, 0, 1, 1, 0,
		-1, 1, -1, 1, 0, 1, 0, 1, 0, 0,
		-1, 1, 1, 1, 0, 1, 1, 1, 0, 1,
		1, 1, -1, 1, 1, 1, 0, 1, 1, 0,

		-1, -1, 1, 1, 0, 0, 1, 1, 0, 1,
		-1, 1, 1, 1, 0, 1, 1, 1, 1, 1,
		-1, 1, -1, 1, 0, 1, 0, 1, 1, 0,
		-1, -1, -1, 1, 0, 0, 0, 1, 0, 0,
		-1, -1, 1, 1, 0, 0, 1, 1, 0, 1,
		-1, 1, -1, 1, 0, 1, 0, 1, 1, 0,

		1, 1, 1, 1, 1, 1, 1, 1, 0, 1,
		-1, 1, 1, 1, 0, 1, 1, 1, 1, 1,
		-1, -1, 1, 1, 0, 0, 1, 1, 1, 0,
		-1, -1, 1, 1, 0, 0, 1, 1, 1, 0,
		1, -1, 1, 1, 1, 0, 1, 1, 0, 0,
		1, 1, 1, 1, 1, 1, 1, 1, 0, 1,

		1, -1, -1, 1, 1, 0, 0, 1, 0, 1,
		-1, -1, -1, 1, 0, 0, 0, 1, 1, 1,
		-1, 1, -1, 1, 0, 1, 0, 1, 1, 0,
		1, 1, -1, 1, 1, 1, 0, 1, 0, 0,
		1, -1, -1, 1, 1, 0, 0, 1, 0, 1,
		-1, 1, -1, 1, 0, 1, 0, 1, 1, 0,
	).let { FloatArray(it.size) { index -> it[index].toFloat() } }

}