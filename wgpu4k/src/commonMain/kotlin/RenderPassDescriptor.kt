package io.ygdrasil.webgpu

expect class RenderPassTimestampWrites : GPURenderPassTimestampWrites {
    override val beginningOfPassWriteIndex: GPUSize32?
    override val endOfPassWriteIndex: GPUSize32?
    override val querySet: GPUQuerySet
}


