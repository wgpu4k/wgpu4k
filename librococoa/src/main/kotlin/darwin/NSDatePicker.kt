package darwin

import org.rococoa.ObjCClass

abstract class NSDatePicker : NSControl() {
	interface _Class : ObjCClass {
		open fun alloc(): NSDatePicker?
	}

	abstract fun dateValue(): NSDate?

	abstract fun setDateValue(value: NSDate?)

	companion object {
		private val CLASS: NSButton._Class =
			org.rococoa.Rococoa.createClass("NSDatePicker", NSButton._Class::class.java)
	}
}
