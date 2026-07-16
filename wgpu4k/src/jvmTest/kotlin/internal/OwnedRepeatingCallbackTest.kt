package io.ygdrasil.webgpu.internal

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
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

    "close defers registration cleanup until an active callback returns" {
        val callbackEntered = CountDownLatch(1)
        val callbackMayReturn = CountDownLatch(1)
        val registrationCloses = AtomicInteger(0)
        val quiescentActions = AtomicInteger(0)
        val executor = Executors.newSingleThreadExecutor()
        val callback = OwnedRepeatingCallback<Unit> {
            callbackEntered.countDown()
            check(callbackMayReturn.await(5, TimeUnit.SECONDS))
        }
        callback.attach(AutoCloseable { registrationCloses.incrementAndGet() })
        val dispatch = executor.submit<Boolean> { callback.dispatch(Unit) }

        try {
            callbackEntered.await(5, TimeUnit.SECONDS) shouldBe true
            callback.closeWhenQuiescent {
                registrationCloses.get() shouldBe 1
                quiescentActions.incrementAndGet()
            }

            callback.dispatch(Unit) shouldBe false
            registrationCloses.get() shouldBe 0
            quiescentActions.get() shouldBe 0
        } finally {
            callbackMayReturn.countDown()
            dispatch.get(5, TimeUnit.SECONDS) shouldBe true
            executor.shutdown()
            executor.awaitTermination(5, TimeUnit.SECONDS) shouldBe true
        }

        registrationCloses.get() shouldBe 1
        quiescentActions.get() shouldBe 1
    }

    "close also waits for native value conversion before user delivery" {
        val conversionEntered = CountDownLatch(1)
        val conversionMayReturn = CountDownLatch(1)
        val registrationCloses = AtomicInteger(0)
        val baseReleases = AtomicInteger(0)
        val executor = Executors.newSingleThreadExecutor()
        val callback = OwnedRepeatingCallback<Int> {}
        callback.attach(AutoCloseable { registrationCloses.incrementAndGet() })
        val dispatch = executor.submit<Boolean> {
            callback.dispatchWhileActive { deliver ->
                conversionEntered.countDown()
                check(conversionMayReturn.await(5, TimeUnit.SECONDS))
                deliver(1)
            }
        }

        try {
            conversionEntered.await(5, TimeUnit.SECONDS) shouldBe true
            callback.closeWhenQuiescent { baseReleases.incrementAndGet() }

            registrationCloses.get() shouldBe 0
            baseReleases.get() shouldBe 0
        } finally {
            conversionMayReturn.countDown()
            dispatch.get(5, TimeUnit.SECONDS) shouldBe true
            executor.shutdown()
            executor.awaitTermination(5, TimeUnit.SECONDS) shouldBe true
        }

        registrationCloses.get() shouldBe 1
        baseReleases.get() shouldBe 1
    }
})
