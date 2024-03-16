package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSRect

abstract class NSSecureTextField : NSTextField() {
	interface _Class : ObjCClass {
		open fun alloc(): NSSecureTextField
	}

	@Override
	abstract override fun initWithFrame(frameRect: NSRect?): NSSecureTextField

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSSecureTextField", _Class::class.java)

		fun textfieldWithFrame(frameRect: NSRect?): NSSecureTextField? {
			return CLASS.alloc().initWithFrame(frameRect)
		}
	}
}
