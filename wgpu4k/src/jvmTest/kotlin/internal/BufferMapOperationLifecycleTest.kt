package io.ygdrasil.webgpu.internal

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class BufferMapOperationLifecycleTest : FreeSpec({
    "cancel after native start aborts before cleanup and allows remap" {
        val events = mutableListOf<String>()
        val buffer = BufferLifecycle(release = {})
        val ticket = buffer.registerMap {}.getOrThrow()
        lateinit var operation: BufferMapOperationLifecycle
        operation = BufferMapOperationLifecycle(
            closeResources = {
                events += "cleanup"
                ticket.close()
            },
            abortInline = { error("an already-started map must use protected abort") },
            abortProtected = {
                ticket.runNativeCall {
                    events += "abort"
                    operation.callbackStarted() shouldBe true
                    ticket.complete {
                        operation.callbackFinished(mapped = false, delivered = false)
                    } shouldBe true
                }
            },
        )

        ticket.runNativeCall {
            operation.startNativeCall { events += "start" } shouldBe true
        } shouldBe true
        operation.close()

        events shouldBe listOf("start", "abort", "cleanup")
        buffer.registerMap {}.isSuccess shouldBe true
    }

    "cancel during native start aborts inline after the downcall returns" {
        val events = mutableListOf<String>()
        val buffer = BufferLifecycle(release = {})
        val ticket = buffer.registerMap {}.getOrThrow()
        lateinit var operation: BufferMapOperationLifecycle
        operation = BufferMapOperationLifecycle(
            closeResources = {
                events += "cleanup"
                ticket.close()
            },
            abortInline = {
                events += "abort"
                operation.callbackStarted() shouldBe true
                ticket.complete {
                    operation.callbackFinished(mapped = false, delivered = false)
                } shouldBe true
            },
            abortProtected = { error("abort must be serialized behind the starting downcall") },
        )

        ticket.runNativeCall {
            operation.startNativeCall {
                events += "start"
                operation.close()
            } shouldBe true
        } shouldBe true

        events shouldBe listOf("start", "abort", "cleanup")
        buffer.registerMap {}.isSuccess shouldBe true
    }

    "a delivered mapping result cleans up without unmapping" {
        val events = mutableListOf<String>()
        val buffer = BufferLifecycle(release = {})
        val ticket = buffer.registerMap {}.getOrThrow()
        val operation = BufferMapOperationLifecycle(
            closeResources = {
                events += "cleanup"
                ticket.close()
            },
            abortInline = { events += "unexpected-abort" },
            abortProtected = { error("normal completion must not abort") },
        )

        ticket.runNativeCall {
            operation.startNativeCall { events += "start" } shouldBe true
        } shouldBe true
        operation.callbackStarted() shouldBe true
        ticket.complete {
            operation.close()
            operation.callbackFinished(mapped = true, delivered = true)
        } shouldBe true

        events shouldBe listOf("start", "cleanup")
        buffer.registerMap {}.isSuccess shouldBe true
    }

    "an undelivered successful callback unmaps before cleanup" {
        val events = mutableListOf<String>()
        val buffer = BufferLifecycle(release = {})
        val ticket = buffer.registerMap {}.getOrThrow()
        val operation = BufferMapOperationLifecycle(
            closeResources = {
                events += "cleanup"
                ticket.close()
            },
            abortInline = { events += "abort" },
            abortProtected = { error("the callback owns the protected abort path") },
        )

        ticket.runNativeCall {
            operation.startNativeCall { events += "start" } shouldBe true
        } shouldBe true
        operation.callbackStarted() shouldBe true
        ticket.complete {
            operation.close()
            operation.callbackFinished(mapped = true, delivered = false)
        } shouldBe true

        events shouldBe listOf("start", "abort", "cleanup")
        buffer.registerMap {}.isSuccess shouldBe true
    }

    "cleanup failure cannot escape native callback delivery" {
        val buffer = BufferLifecycle(release = {})
        val ticket = buffer.registerMap {}.getOrThrow()
        val operation = BufferMapOperationLifecycle(
            closeResources = { error("cleanup failed") },
            abortInline = {},
            abortProtected = { error("normal completion must not abort") },
        )

        ticket.runNativeCall {
            operation.startNativeCall {} shouldBe true
        } shouldBe true
        operation.callbackStarted() shouldBe true
        ticket.complete {
            operation.close()
            runCatching {
                operation.callbackFinished(mapped = false, delivered = true)
            }.isSuccess shouldBe true
        } shouldBe true
    }
})
