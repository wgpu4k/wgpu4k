package io.ygdrasil.wgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUColor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPURenderPassColorAttachment
import io.ygdrasil.wgpu.internal.jvm.panama.WGPURenderPassDepthStencilAttachment
import io.ygdrasil.wgpu.internal.jvm.panama.WGPURenderPassDescriptor
import java.lang.foreign.MemorySegment

class RenderPassDescriptorMappingTest : FreeSpec({

    "test mapping" {

        confined { arena ->
            // Given
            val renderPassDescriptor = RenderPassDescriptor(
                label = "RenderPassDescriptor",
                colorAttachments = listOf(
                    RenderPassDescriptor.ColorAttachment(
                        view = TextureView(MemorySegment.NULL),
                        loadOp = LoadOp.clear,
                        clearValue = Color(0.5, 0.6, 0.7, 1.0),
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
            val result: MemorySegment = arena.map(renderPassDescriptor)

            // Then
            WGPURenderPassDescriptor.label(result).getString(0) shouldBe "RenderPassDescriptor"
            WGPURenderPassDescriptor.colorAttachmentCount(result) shouldBe 1
            WGPURenderPassDescriptor.colorAttachments(result).let { colorAttachments ->
                WGPURenderPassColorAttachment.asSlice(colorAttachments, 0).let { colorAttachment ->
                    WGPURenderPassColorAttachment.view(colorAttachment) shouldBe MemorySegment.NULL
                    WGPURenderPassColorAttachment.clearValue(colorAttachment).let { color ->
                        WGPUColor.r(color) shouldBe 0.5
                        WGPUColor.g(color) shouldBe 0.6
                        WGPUColor.b(color) shouldBe 0.7
                        WGPUColor.a(color) shouldBe 1.0

                    }
                    WGPURenderPassColorAttachment.loadOp(colorAttachment) shouldBe LoadOp.clear.value
                    WGPURenderPassColorAttachment.storeOp(colorAttachment) shouldBe StoreOp.store.value
                }
            }

            WGPURenderPassDescriptor.depthStencilAttachment(result) shouldNotBe MemorySegment.NULL
            WGPURenderPassDescriptor.depthStencilAttachment(result).let { depthStencilAttachment ->
                WGPURenderPassDepthStencilAttachment.view(depthStencilAttachment) shouldBe MemorySegment.NULL
                WGPURenderPassDepthStencilAttachment.depthClearValue(depthStencilAttachment) shouldBe 1f
                WGPURenderPassDepthStencilAttachment.depthLoadOp(depthStencilAttachment) shouldBe LoadOp.clear.value
                WGPURenderPassDepthStencilAttachment.depthStoreOp(depthStencilAttachment) shouldBe StoreOp.store.value
            }
        }

    }

})