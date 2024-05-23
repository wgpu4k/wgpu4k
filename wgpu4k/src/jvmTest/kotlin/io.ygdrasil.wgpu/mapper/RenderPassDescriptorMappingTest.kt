package io.ygdrasil.wgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.ygdrasil.wgpu.LoadOp
import io.ygdrasil.wgpu.RenderPassDescriptor
import io.ygdrasil.wgpu.StoreOp
import io.ygdrasil.wgpu.TextureView
import io.ygdrasil.wgpu.internal.jvm.WGPURenderPassDescriptor
import java.lang.foreign.MemorySegment

class RenderPassDescriptorMappingTest: FreeSpec( {


    "test mapping" {
        // Given
        val renderPassDescriptor = RenderPassDescriptor(
            colorAttachments = arrayOf(
                RenderPassDescriptor.ColorAttachment(
                    view = TextureView(MemorySegment.NULL),
                    loadOp = LoadOp.clear,
                    clearValue = arrayOf(0.5, 0.6, 0.7, 1.0),
                    storeOp = StoreOp.store,
                )
            ),
            depthStencilAttachment = RenderPassDescriptor.RenderPassDepthStencilAttachment(
                view = TextureView(MemorySegment.NULL),
                depthClearValue = 1.0f,
                depthLoadOp = LoadOp.clear,
                depthStoreOp = StoreOp.store
            )
        )

        // When
        val result: WGPURenderPassDescriptor = renderPassDescriptorMapper.map<RenderPassDescriptor, WGPURenderPassDescriptor>(renderPassDescriptor)
            // And
            .also(WGPURenderPassDescriptor::write)

        // Then
        result.apply {
            colorAttachmentCount?.toLong() shouldBe 1
            colorAttachments?.size shouldBe 1
            colorAttachments?.get(0)?.apply {
                view shouldNotBe null
                loadOp shouldBe LoadOp.clear.value
                storeOp shouldBe StoreOp.store.value
                clearValue shouldNotBe null
                clearValue?.apply {
                    r shouldBe .5
                    g shouldBe .6
                    b shouldBe .7
                    a shouldBe 1.0
                }
            }
            depthStencilAttachment shouldNotBe null
            depthStencilAttachment?.apply {
                view shouldNotBe null
                depthClearValue shouldBe 1f
                depthLoadOp shouldBe LoadOp.clear.value
                depthStoreOp shouldBe StoreOp.store.value
            }

        }


    }

})