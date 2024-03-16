package darwin

import org.rococoa.ID
import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSRect

abstract class NSPathControl : NSControl() {
	interface _Class : ObjCClass {
		open fun alloc(): NSPathControl
	}

	@Override
	abstract override fun initWithFrame(frameRect: NSRect?): NSPathControl

	abstract fun URL(): NSURL?

	abstract fun setURL(aString: NSURL?)

	abstract fun setDelegate(delegate: ID?)

	interface Delegate {
		open fun pathControl_willDisplayOpenPanel(control: NSPathControl?, panel: NSOpenPanel?)
	}

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSPathControl", _Class::class.java)

		fun pathControlWithFrame(frameRect: NSRect?): NSPathControl? {
			return CLASS.alloc().initWithFrame(frameRect)
		}
	}
}
