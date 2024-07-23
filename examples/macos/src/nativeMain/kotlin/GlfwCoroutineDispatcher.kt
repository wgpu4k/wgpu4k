@file:OptIn(ExperimentalForeignApi::class)

import glfw.glfwPostEmptyEvent
import glfw.glfwWaitEvents
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlin.coroutines.CoroutineContext

class GlfwCoroutineDispatcher : CoroutineDispatcher() {
	private val tasks = mutableListOf<Runnable>()
	private val tasksCopy = mutableListOf<Runnable>()
	private var isStopped = false
	private val mutex = SynchronizedObject()

	fun runLoop() {
		while (!isStopped) {
			synchronized(mutex) {
				tasksCopy.addAll(tasks)
				tasks.clear()
			}
			for (runnable in tasksCopy) {
				if (!isStopped) {
					runnable.run()
				}
			}
			tasksCopy.clear()
			glfwWaitEvents()
		}
	}

	fun stop() {
		isStopped = true
	}

	override fun dispatch(context: CoroutineContext, block: Runnable) {
		synchronized(mutex) {
			tasks.add(block)
		}
		glfwPostEmptyEvent()
	}
}