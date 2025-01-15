package io.ygdrasil.webgpu

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class EnumerationsTest : FreeSpec({

    "test flag to int without flag" {
        // Given
        val noflag = setOf<BufferUsage>()

        // When
        val intFlag = noflag.toFlagInt()

        // Then

        intFlag shouldBe 0
    }

    "test flag to int with one flag" {
        // Given
        val withFlags = setOf(BufferUsage.Vertex)

        // When
        val intFlag = withFlags.toFlagInt()

        // Then

        intFlag shouldBe BufferUsage.Vertex.value
    }

    "test flag to int with multiple flags" {
        // Given
        val withFlags = setOf(BufferUsage.Vertex, BufferUsage.CopyDst, BufferUsage.Uniform)

        // When
        val intFlag = withFlags.toFlagInt()

        // Then

        intFlag shouldBe (BufferUsage.Vertex.value or BufferUsage.CopyDst.value or BufferUsage.Uniform.value)
    }
})