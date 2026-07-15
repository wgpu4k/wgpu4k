package io.ygdrasil.webgpu.examples.headless

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicReference
import kotlin.concurrent.thread

class OwnedRegistrationSlotTest : FreeSpec({
    "publishes a replacement before closing the previous registration" {
        val events = mutableListOf<String>()
        val slot = OwnedRegistrationSlot<AutoCloseable>()
        slot.replace {
            AutoCloseable {
                events += "close-old"
                error("old close failed")
            }
        }

        shouldThrow<IllegalStateException> {
            slot.replace {
                events += "install-new"
                AutoCloseable { events += "close-new" }
            }
        }
        slot.replace {
            events += "install-third"
            AutoCloseable { events += "close-third" }
        }

        events shouldBe listOf("install-new", "close-old", "install-third", "close-new")
    }

    "keeps the previous registration when installation fails" {
        var previousCloses = 0
        val slot = OwnedRegistrationSlot<AutoCloseable>()
        slot.replace { AutoCloseable { previousCloses++ } }

        shouldThrow<IllegalStateException> {
            slot.replace { error("install failed") }
        }

        previousCloses shouldBe 0
        slot.replace { AutoCloseable {} }
        previousCloses shouldBe 1
    }

    "serializes concurrent replacement operations" {
        val firstInstallEntered = CountDownLatch(1)
        val releaseFirstInstall = CountDownLatch(1)
        val secondStarted = CountDownLatch(1)
        val secondInstallEntered = CountDownLatch(1)
        val failure = AtomicReference<Throwable?>(null)
        var baseCloses = 0
        var firstCloses = 0
        val slot = OwnedRegistrationSlot<AutoCloseable>()
        slot.replace { AutoCloseable { baseCloses++ } }

        val first = thread(start = true) {
            runCatching {
                slot.replace {
                    firstInstallEntered.countDown()
                    check(releaseFirstInstall.await(5, TimeUnit.SECONDS))
                    AutoCloseable { firstCloses++ }
                }
            }.exceptionOrNull()?.let(failure::set)
        }
        check(firstInstallEntered.await(5, TimeUnit.SECONDS))
        val second = thread(start = true) {
            runCatching {
                secondStarted.countDown()
                slot.replace {
                    secondInstallEntered.countDown()
                    AutoCloseable {}
                }
            }.exceptionOrNull()?.let(failure::set)
        }

        check(secondStarted.await(5, TimeUnit.SECONDS))
        secondInstallEntered.await(100, TimeUnit.MILLISECONDS) shouldBe false
        releaseFirstInstall.countDown()
        first.join(5_000)
        second.join(5_000)

        first.isAlive shouldBe false
        second.isAlive shouldBe false
        secondInstallEntered.count shouldBe 0L
        failure.get() shouldBe null
        baseCloses shouldBe 1
        firstCloses shouldBe 1
    }
})
