package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSSize

/// <i>native declaration : /Users/dkocher/null:10</i>
abstract class NSValue : NSObject() {
	interface _Class : ObjCClass {
		open fun valueWithSize(size: NSSize?): NSValue?
	}

	/**
	 * Original signature : `BOOL isEqualToValue(NSValue*)`<br></br>
	 * *from NSValueExtensionMethods native declaration : /Users/dkocher/null:33*
	 */
	abstract fun isEqualToValue(value: NSValue?): Byte

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSValue", _Class::class.java)

		fun valueWithSize(size: NSSize?): NSValue? {
			return CLASS.valueWithSize(size)
		}
	}
}
