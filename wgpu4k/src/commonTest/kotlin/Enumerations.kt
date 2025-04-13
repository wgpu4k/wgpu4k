package io.ygdrasil.webgpu

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class EnumerationsTest : FreeSpec({

    "test flag to int without flag" {
        // Given
        val noflag = setOf<GPUBufferUsage>()

        // When
        val intFlag = noflag.toFlagULong()

        // Then

        intFlag shouldBe 0uL
    }

    "test flag to int with one flag" {
        // Given
        val withFlags = setOf(GPUBufferUsage.Vertex)

        // When
        val intFlag = withFlags.toFlagULong()

        // Then

        intFlag shouldBe GPUBufferUsage.Vertex.value
    }

    "test flag to int with multiple flags" {
        // Given
        val withFlags = setOf(GPUBufferUsage.Vertex, GPUBufferUsage.CopyDst, GPUBufferUsage.Uniform)

        // When
        val intFlag = withFlags.toFlagULong()

        // Then

        intFlag shouldBe (GPUBufferUsage.Vertex.value or GPUBufferUsage.CopyDst.value or GPUBufferUsage.Uniform.value)
    }
})