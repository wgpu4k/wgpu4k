package io.ygdrasil.webgpu.internal

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.util.concurrent.atomic.AtomicInteger

class OwnedRepeatingCallbackTest : FreeSpec({
    "repeating callbacks are delivered until close and their registration is closed once" {
        val delivered = mutableListOf<Int>()
        val registrationCloses = AtomicInteger(0)
        val callback = OwnedRepeatingCallback<Int> { delivered += it }
        callback.attach(AutoCloseable { registrationCloses.incrementAndGet() })

        callback.dispatch(1) shouldBe true
        callback.dispatch(2) shouldBe true
        callback.close()
        callback.close()
        callback.dispatch(3) shouldBe false

        delivered shouldBe listOf(1, 2)
        registrationCloses.get() shouldBe 1
    }

    "a registration attached after close is closed immediately" {
        val registrationCloses = AtomicInteger(0)
        val callback = OwnedRepeatingCallback<Unit> {}

        callback.close()
        callback.attach(AutoCloseable { registrationCloses.incrementAndGet() }) shouldBe false

        registrationCloses.get() shouldBe 1
    }
})
