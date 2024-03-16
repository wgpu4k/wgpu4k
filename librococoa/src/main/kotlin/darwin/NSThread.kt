package darwin 

import org.rococoa.ID
import org.rococoa.ObjCClass
import org.rococoa.Selector
import org.rococoa.cocoa.foundation.NSUInteger

object NSThread : NSObject() {
	private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSThread", _Class::class.java)

	fun isMainThread(): Boolean {
		return CLASS.isMainThread()
	}

	interface _Class : ObjCClass {
		/**
		 * Returns a Boolean value that indicates whether the current thread is the main thread.
		 *
		 * @return
		 */
		open fun isMainThread(): Boolean
	}

	override fun respondsToSelector(sel: Selector?): Boolean {
		TODO("Not yet implemented")
	}

	override fun performSelector(sel: Selector?): NSObject? {
		TODO("Not yet implemented")
	}

	override fun hash(): NSUInteger? {
		TODO("Not yet implemented")
	}

	override fun isEqual(anObject: ID?): Boolean {
		TODO("Not yet implemented")
	}

	override fun id(): ID {
		TODO("Not yet implemented")
	}

	override fun retain(): org.rococoa.cocoa.foundation.NSObject {
		TODO("Not yet implemented")
	}

	override fun release() {
		TODO("Not yet implemented")
	}

	override fun retainCount(): NSUInteger {
		TODO("Not yet implemented")
	}

	override fun isKindOfClass(p0: ObjCClass?): Boolean {
		TODO("Not yet implemented")
	}

	override fun isKindOfClass(p0: ID?): Boolean {
		TODO("Not yet implemented")
	}

	override fun description(): String {
		TODO("Not yet implemented")
	}
}
