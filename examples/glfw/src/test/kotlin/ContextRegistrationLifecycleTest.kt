package io.ygdrasil.webgpu.examples

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ContextRegistrationLifecycleTest : FreeSpec({
    "closes the context before the callback registration" {
        val events = mutableListOf<String>()
        val context = AutoCloseable { events += "close-context" }

        val result = withContextAndRegistration(
            context = context,
            installRegistration = {
                events += "install-registration"
                AutoCloseable { events += "close-registration" }
            },
        ) {
            events += "body"
            42
        }

        result shouldBe 42
        events shouldBe listOf(
            "install-registration",
            "body",
            "close-context",
            "close-registration",
        )
    }

    "closes the context when callback installation fails" {
        val events = mutableListOf<String>()
        val failure = IllegalStateException("install failed")
        val context = AutoCloseable { events += "close-context" }

        val thrown = shouldThrow<IllegalStateException> {
            withContextAndRegistration(
                context = context,
                installRegistration = {
                    events += "install-registration"
                    throw failure
                },
            ) {
                events += "body"
            }
        }

        thrown shouldBe failure
        events shouldBe listOf("install-registration", "close-context")
    }

    "closes both resources in order when the body fails" {
        val events = mutableListOf<String>()
        val failure = IllegalStateException("body failed")
        val context = AutoCloseable { events += "close-context" }

        val thrown = shouldThrow<IllegalStateException> {
            withContextAndRegistration(
                context = context,
                installRegistration = { AutoCloseable { events += "close-registration" } },
            ) {
                events += "body"
                throw failure
            }
        }

        thrown shouldBe failure
        events shouldBe listOf("body", "close-context", "close-registration")
    }

    "closes the registration when closing the context fails" {
        val events = mutableListOf<String>()
        val failure = IllegalStateException("context close failed")
        val context = AutoCloseable {
            events += "close-context"
            throw failure
        }

        val thrown = shouldThrow<IllegalStateException> {
            withContextAndRegistration(
                context = context,
                installRegistration = { AutoCloseable { events += "close-registration" } },
            ) {
                events += "body"
            }
        }

        thrown shouldBe failure
        events shouldBe listOf("body", "close-context", "close-registration")
    }
})
