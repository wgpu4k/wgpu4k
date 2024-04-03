package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSInteger

abstract class NSTitlebarAccessoryViewController : NSResponder() {
	interface _Class : ObjCClass {
		open fun alloc(): NSTitlebarAccessoryViewController
	}

	abstract fun init(): NSTitlebarAccessoryViewController

	abstract fun removeFromParentViewController()

	abstract fun setLayoutAttribute(layout: NSInteger?)

	abstract fun setAutomaticallyAdjustsSize(value: Boolean)

	abstract fun setView(view: NSView?)

	companion object {
		private val CLASS: _Class =
			org.rococoa.Rococoa.createClass("NSTitlebarAccessoryViewController", _Class::class.java)

		val NSLayoutAttributeLeft: NSInteger? = NSInteger(1)
		val NSLayoutAttributeRight: NSInteger? = NSInteger(2)
		val NSLayoutAttributeBottom: NSInteger? = NSInteger(4)

		fun create(): NSTitlebarAccessoryViewController? {
			return CLASS.alloc().init()
		}
	}
}
