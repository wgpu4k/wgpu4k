@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu.examples

fun <T> autoClosableContext(block: AutoClosableContext.() -> T): T = AutoClosableContext()
	.use { it.block() }

class AutoClosableContext : AutoCloseable {

	private val subjects: MutableList<AutoCloseable> = mutableListOf()

	fun <T : AutoCloseable> T.bind(): T = also { subjects.add(it) }

	override fun close() {
		subjects.reversed()
			.forEach { it.close() }
	}
}