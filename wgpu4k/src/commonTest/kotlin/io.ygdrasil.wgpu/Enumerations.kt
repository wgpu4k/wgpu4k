package io.ygdrasil.wgpu

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class EnumerationsTest : FreeSpec ({

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
        val withFlags = setOf(BufferUsage.vertex)


        // When
        val intFlag = withFlags.toFlagInt()

        // Then

        intFlag shouldBe BufferUsage.vertex.value
    }


    "test flag to int with multiple flags" {
        // Given
        val withFlags = setOf(BufferUsage.vertex, BufferUsage.copydst, BufferUsage.uniform)


        // When
        val intFlag = withFlags.toFlagInt()

        // Then

        intFlag shouldBe (BufferUsage.vertex.value or BufferUsage.copydst.value or BufferUsage.uniform.value)
    }
})