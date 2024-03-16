package darwin 

import org.rococoa.ObjCClass

/// <i>native declaration : :10</i>
abstract class NSActionCell : NSCell() {
	interface _Class : ObjCClass {
		open fun alloc(): NSActionCell?
	}

	/**
	 * Original signature : `id target()`<br></br>
	 * *native declaration : :30*
	 */
	abstract fun target(): org.rococoa.ID?

	/**
	 * Original signature : `void setTarget(id)`<br></br>
	 * *native declaration : :31*
	 */
	abstract fun setTarget(anObject: org.rococoa.ID?)
}
