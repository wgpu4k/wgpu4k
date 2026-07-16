package io.ygdrasil.webgpu.examples

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ContextRegistrationLifecycleTest : FreeSpec({
    "installs the registration before creating the context and closes them in reverse order" {
        val events = mutableListOf<String>()

        val result = withContextAndRegistration(
            installRegistration = {
                events += "install-registration"
                AutoCloseable { events += "close-registration" }
            },
            createContext = {
                events += "create-context"
                AutoCloseable { events += "close-context" }
            },
        ) {
            events += "body"
            42
        }

        result shouldBe 42
        events shouldBe listOf(
            "install-registration",
            "create-context",
            "body",
            "close-context",
            "close-registration",
        )
    }

    "does not create the context when callback installation fails" {
        val events = mutableListOf<String>()
        val failure = IllegalStateException("install failed")

        val thrown = shouldThrow<IllegalStateException> {
            withContextAndRegistration(
                installRegistration = {
                    events += "install-registration"
                    throw failure
                },
                createContext = {
                    events += "create-context"
                    AutoCloseable { events += "close-context" }
                },
            ) {
                events += "body"
            }
        }

        thrown shouldBe failure
        events shouldBe listOf("install-registration")
    }

    "closes the registration when context creation fails" {
        val events = mutableListOf<String>()
        val failure = IllegalStateException("context creation failed")

        val thrown = shouldThrow<IllegalStateException> {
            withContextAndRegistration(
                installRegistration = {
                    events += "install-registration"
                    AutoCloseable { events += "close-registration" }
                },
                createContext = {
                    events += "create-context"
                    throw failure
                },
            ) {
                events += "body"
            }
        }

        thrown shouldBe failure
        events shouldBe listOf(
            "install-registration",
            "create-context",
            "close-registration",
        )
    }

    "closes both resources in order when the body fails" {
        val events = mutableListOf<String>()
        val failure = IllegalStateException("body failed")

        val thrown = shouldThrow<IllegalStateException> {
            withContextAndRegistration(
                installRegistration = { AutoCloseable { events += "close-registration" } },
                createContext = { AutoCloseable { events += "close-context" } },
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

        val thrown = shouldThrow<IllegalStateException> {
            withContextAndRegistration(
                installRegistration = { AutoCloseable { events += "close-registration" } },
                createContext = {
                    AutoCloseable {
                        events += "close-context"
                        throw failure
                    }
                },
            ) {
                events += "body"
            }
        }

        thrown shouldBe failure
        events shouldBe listOf("body", "close-context", "close-registration")
    }
})
