package io.ygdrasil.webgpu

suspend fun <T> autoClosableContext(block: suspend AutoClosableContext.() -> T): T = AutoClosableContext()
	.use { it.block() }

class AutoClosableContext : AutoCloseable {

	private val subjects: MutableList<AutoCloseable> = mutableListOf()

	fun <T : AutoCloseable> T.bind(): T = also { subjects.add(it) }

	override fun close() {
		subjects.reversed()
			.forEach { it.close() }
		subjects.clear()
	}
}