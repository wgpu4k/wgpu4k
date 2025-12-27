package io.ygdrasil.webgpu

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class EnumerationsTest : FreeSpec({

    "test flag to int without flag" {
        // Given
        val noflag = GPUBufferUsage.None

        // When
        val intFlag = noflag.value

        // Then

        intFlag shouldBe 0uL
    }

    "test flag to int with one flag" {
        // Given
        val withFlags = GPUBufferUsage.Vertex

        // When
        val intFlag = withFlags.value

        // Then

        intFlag shouldBe GPUBufferUsage.Vertex.value
    }

    "test flag to int with multiple flags" {
        // Given
        val withFlags = GPUBufferUsage.Vertex or GPUBufferUsage.CopyDst or GPUBufferUsage.Uniform

        // When
        val intFlag = withFlags.value

        // Then
        intFlag shouldBe (GPUBufferUsage.Vertex.value or GPUBufferUsage.CopyDst.value or GPUBufferUsage.Uniform.value)
    }
})