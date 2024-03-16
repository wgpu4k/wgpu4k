package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : :81</i>
abstract class NSMutableData : NSData() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `dataWithCapacity(NSUInteger)`<br></br>
		 * *from NSMutableDataCreation native declaration : :104*
		 */
		open fun dataWithCapacity(aNumItems: NSUInteger?): NSMutableData?

		/**
		 * Original signature : `dataWithLength(NSUInteger)`<br></br>
		 * *from NSMutableDataCreation native declaration : :105*
		 */
		open fun dataWithLength(length: NSUInteger?): NSMutableData?
	}

	/**
	 * Original signature : `void* mutableBytes()`<br></br>
	 * *native declaration : :83*
	 */
	abstract fun mutableBytes(): com.sun.jna.Pointer?

	/**
	 * Original signature : `void setLength(NSUInteger)`<br></br>
	 * *native declaration : :84*
	 */
	abstract fun setLength(length: NSUInteger?)

	/**
	 * Original signature : `void appendBytes(const void*, NSUInteger)`<br></br>
	 * *from NSExtendedMutableData native declaration : :90*
	 */
	abstract fun appendBytes_length(bytes: com.sun.jna.Pointer?, length: NSUInteger?)

	/**
	 * Original signature : `void appendData(NSData*)`<br></br>
	 * *from NSExtendedMutableData native declaration : :91*
	 */
	abstract fun appendData(other: NSData?)

	/**
	 * Original signature : `void increaseLengthBy(NSUInteger)`<br></br>
	 * *from NSExtendedMutableData native declaration : :92*
	 */
	abstract fun increaseLengthBy(extraLength: NSUInteger?)
	/**
	 * *from NSExtendedMutableData native declaration : :93*<br></br>
	 * Conversion Error : /// Original signature : `void replaceBytesInRange(null, const void*)`<br></br>
	 * - (void)replaceBytesInRange:(null)range withBytes:(const void*)bytes; (Argument range cannot be converted)
	 */
	/**
	 * *from NSExtendedMutableData native declaration : :94*<br></br>
	 * Conversion Error : /// Original signature : `void resetBytesInRange(null)`<br></br>
	 * - (void)resetBytesInRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `void setData(NSData*)`<br></br>
	 * *from NSExtendedMutableData native declaration : :95*
	 */
	abstract fun setData(data: NSData?)
	/**
	 * *from NSExtendedMutableData native declaration : :97*<br></br>
	 * Conversion Error : /// Original signature : `void replaceBytesInRange(null, const void*, NSUInteger)`<br></br>
	 * - (void)replaceBytesInRange:(null)range withBytes:(const void*)replacementBytes length:(NSUInteger)replacementLength; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `initWithCapacity(NSUInteger)`<br></br>
	 * *from NSMutableDataCreation native declaration : :106*
	 */
	abstract fun initWithCapacity(capacity: NSUInteger?): NSMutableData?

	/**
	 * Original signature : `initWithLength(NSUInteger)`<br></br>
	 * *from NSMutableDataCreation native declaration : :107*
	 */
	abstract fun initWithLength(length: NSUInteger?): NSMutableData?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSMutableData", _Class::class.java)

		fun dataWithCapacity(aNumItems: NSUInteger?): NSMutableData? {
			return CLASS.dataWithCapacity(aNumItems)
		}

		fun dataWithLength(length: NSUInteger?): NSMutableData? {
			return CLASS.dataWithLength(length)
		}
	}
}
