package io.ygdrasil.webgpu

expect class Queue : GPUQueue {

    override var label: String

    fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: ShortArray,
        dataOffset: GPUSize64 = 0u,
        size: GPUSize64 = data.size.toULong(),
    )

    fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: FloatArray,
        dataOffset: GPUSize64 = 0u,
        size: GPUSize64 = data.size.toULong(),
    )

    fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: IntArray,
        dataOffset: GPUSize64 = 0u,
        size: GPUSize64 = data.size.toULong(),
    )

    fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: ByteArray,
        dataOffset: GPUSize64 = 0u,
        size: GPUSize64 = data.size.toULong(),
    )

    fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: DoubleArray,
        dataOffset: GPUSize64 = 0u,
        size: GPUSize64 = data.size.toULong(),
    )

    fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: LongArray,
        dataOffset: GPUSize64 = 0u,
        size: GPUSize64 = data.size.toULong(),
    )

    override fun submit(commandBuffers: List<GPUCommandBuffer>)
    override suspend fun onSubmittedWorkDone() : Result<Unit>
    override fun writeBuffer(
        buffer: GPUBuffer,
        bufferOffset: GPUSize64,
        data: GPUBufferSource,
        dataOffset: GPUSize64,
        size: GPUSize64
    )

    override fun writeTexture(
        destination: GPUTexelCopyTextureInfo,
        data: GPUBufferSource,
        dataLayout: GPUTexelCopyBufferLayout,
        size: GPUExtent3D
    )

}

expect sealed interface DrawableHolder
expect class ImageBitmapHolder : DrawableHolder, AutoCloseable {
    val width: UInt
    val height: UInt

    override fun close()
}
