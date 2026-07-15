package io.ygdrasil.webgpu.examples

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.FreeSpec

class GlfwMainInitializationTest : FreeSpec({
    "loading the main class does not perform a native downcall" {
        shouldNotThrowAny {
            Class.forName("io.ygdrasil.webgpu.examples.GlfwMainKt")
        }
    }
})
