package io.ygdrasil.webgpu.examples

internal inline fun <C : AutoCloseable, R : AutoCloseable, T> withContextAndRegistration(
    installRegistration: () -> R,
    createContext: () -> C,
    block: (C) -> T,
): T {
    return installRegistration().use {
        createContext().use(block)
    }
}
