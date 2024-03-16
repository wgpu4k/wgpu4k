package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSRect

abstract class NSTokenField : NSTextField() {
	interface _Class : ObjCClass {
		open fun alloc(): NSTokenField
	}

	@Override
	abstract override fun initWithFrame(frameRect: NSRect?): NSTokenField

	abstract fun setObjectValue(value: NSObject?)

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSTokenField", _Class::class.java)

		fun textfieldWithFrame(frameRect: NSRect?): NSTokenField? {
			return CLASS.alloc().initWithFrame(frameRect)
		}
	}
}
