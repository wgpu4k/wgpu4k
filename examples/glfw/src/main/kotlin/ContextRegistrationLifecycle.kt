package io.ygdrasil.webgpu.examples

internal inline fun <C : AutoCloseable, R : AutoCloseable, T> withContextAndRegistration(
    context: C,
    installRegistration: () -> R,
    block: (C) -> T,
): T {
    val registration = try {
        installRegistration()
    } catch (failure: Throwable) {
        try {
            context.close()
        } catch (closeFailure: Throwable) {
            failure.addSuppressed(closeFailure)
        }
        throw failure
    }

    return registration.use {
        context.use(block)
    }
}
