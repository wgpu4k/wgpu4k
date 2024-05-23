

package io.ygdrasil.wgpu

expect class CommandBuffer : AutoCloseable {

    override fun close()
}