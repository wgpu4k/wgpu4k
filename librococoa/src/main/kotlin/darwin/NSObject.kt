package darwin

import org.rococoa.ID
import org.rococoa.Selector
import org.rococoa.cocoa.foundation.NSUInteger 

/// <i>native declaration : :14</i>
abstract class NSObject : org.rococoa.cocoa.foundation.NSObject() {
	abstract fun respondsToSelector(sel: Selector?): Boolean

	abstract fun performSelector(sel: Selector?): NSObject?

	abstract fun hash(): NSUInteger?

	abstract fun isEqual(anObject: ID?): Boolean
}
