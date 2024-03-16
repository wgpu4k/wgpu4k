package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSInteger
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:37</i>
abstract class NSNumber : NSValue() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `NSNumber* numberWithChar(char)`<br></br>
		 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:87*
		 */
		open fun numberWithChar(value: Byte): NSNumber?

		/**
		 * Original signature : `NSNumber* numberWithUnsignedChar(unsigned char)`<br></br>
		 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:88*
		 */
		open fun numberWithUnsignedChar(value: Byte): NSNumber?

		/**
		 * Original signature : `NSNumber* numberWithShort(short)`<br></br>
		 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:89*
		 */
		open fun numberWithShort(value: Short): NSNumber?

		/**
		 * Original signature : `NSNumber* numberWithUnsignedShort(unsigned short)`<br></br>
		 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:90*
		 */
		open fun numberWithUnsignedShort(value: Short): NSNumber?

		/**
		 * Original signature : `NSNumber* numberWithInt(int)`<br></br>
		 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:91*
		 */
		open fun numberWithInt(value: Int): NSNumber?

		/**
		 * Original signature : `NSNumber* numberWithUnsignedInt(unsigned int)`<br></br>
		 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:92*
		 */
		open fun numberWithUnsignedInt(value: Int): NSNumber?

		/**
		 * Original signature : `NSNumber* numberWithLong(long)`<br></br>
		 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:93*
		 */
		open fun numberWithLong(value: com.sun.jna.NativeLong?): NSNumber?

		/**
		 * Original signature : `NSNumber* numberWithUnsignedLong(unsigned long)`<br></br>
		 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:94*
		 */
		open fun numberWithUnsignedLong(value: com.sun.jna.NativeLong?): NSNumber?

		/**
		 * Original signature : `NSNumber* numberWithLongLong(long long)`<br></br>
		 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:95*
		 */
		open fun numberWithLongLong(value: Long): NSNumber?

		/**
		 * Original signature : `NSNumber* numberWithUnsignedLongLong(unsigned long long)`<br></br>
		 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:96*
		 */
		open fun numberWithUnsignedLongLong(value: Long): NSNumber?

		/**
		 * Original signature : `NSNumber* numberWithFloat(float)`<br></br>
		 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:97*
		 */
		open fun numberWithFloat(value: Float): NSNumber?

		/**
		 * Original signature : `NSNumber* numberWithDouble(double)`<br></br>
		 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:98*
		 */
		open fun numberWithDouble(value: Double): NSNumber?

		/**
		 * Original signature : `NSNumber* numberWithBool(BOOL)`<br></br>
		 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:99*
		 */
		open fun numberWithBool(value: Boolean): NSNumber?

		/**
		 * Original signature : `NSNumber* numberWithInteger(NSInteger)`<br></br>
		 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:101*
		 */
		open fun numberWithInteger(value: NSNumber?): NSNumber?

		/**
		 * Original signature : `NSNumber* numberWithUnsignedInteger(NSUInteger)`<br></br>
		 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:102*
		 */
		open fun numberWithUnsignedInteger(value: NSUInteger?): NSNumber?
	}

	/**
	 * Original signature : `char charValue()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:39*
	 */
	abstract fun charValue(): Byte

	/**
	 * Original signature : `unsigned char unsignedCharValue()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:40*
	 */
	abstract fun unsignedCharValue(): Byte

	/**
	 * Original signature : `short shortValue()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:41*
	 */
	abstract fun shortValue(): Short

	/**
	 * Original signature : `unsigned short unsignedShortValue()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:42*
	 */
	abstract fun unsignedShortValue(): Short

	/**
	 * Original signature : `int intValue()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:43*
	 */
	abstract fun intValue(): Int

	/**
	 * Original signature : `unsigned int unsignedIntValue()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:44*
	 */
	abstract fun unsignedIntValue(): Int

	/**
	 * Original signature : `long longValue()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:45*
	 */
	abstract fun longValue(): Long

	/**
	 * Original signature : `unsigned long unsignedLongValue()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:46*
	 */
	abstract fun unsignedLongValue(): Long

	/**
	 * Original signature : `float floatValue()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:49*
	 */
	abstract fun floatValue(): Float

	/**
	 * Original signature : `double doubleValue()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:50*
	 */
	abstract fun doubleValue(): Double

	/**
	 * Original signature : `BOOL boolValue()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:51*
	 */
	abstract fun boolValue(): Boolean

	/**
	 * Original signature : `NSInteger integerValue()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:53*
	 */
	abstract fun integerValue(): NSInteger?

	/**
	 * Original signature : `NSUInteger unsignedIntegerValue()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:54*
	 */
	abstract fun unsignedIntegerValue(): Int

	/**
	 * Original signature : `NSString* stringValue()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:57*
	 */
	abstract fun stringValue(): String?

	/**
	 * Original signature : `compare(NSNumber*)`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:59*
	 */
	abstract fun compare(otherNumber: NSNumber?): NSObject?

	/**
	 * Original signature : `BOOL isEqualToNumber(NSNumber*)`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:61*
	 */
	abstract fun isEqualToNumber(number: NSNumber?): Byte
	/**
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:63*<br></br>
	 * Conversion Error : /// Original signature : `NSString* descriptionWithLocale(null)`<br></br>
	 * - (NSString*)descriptionWithLocale:(null)locale; (Argument locale cannot be converted)
	 */
	/**
	 * Original signature : `initWithChar(char)`<br></br>
	 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:69*
	 */
	abstract fun initWithChar(value: Byte): NSNumber?

	/**
	 * Original signature : `initWithUnsignedChar(unsigned char)`<br></br>
	 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:70*
	 */
	abstract fun initWithUnsignedChar(value: Byte): NSNumber?

	/**
	 * Original signature : `initWithShort(short)`<br></br>
	 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:71*
	 */
	abstract fun initWithShort(value: Short): NSNumber?

	/**
	 * Original signature : `initWithUnsignedShort(unsigned short)`<br></br>
	 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:72*
	 */
	abstract fun initWithUnsignedShort(value: Short): NSNumber?

	/**
	 * Original signature : `initWithInt(int)`<br></br>
	 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:73*
	 */
	abstract fun initWithInt(value: Int): NSNumber?

	/**
	 * Original signature : `initWithUnsignedInt(unsigned int)`<br></br>
	 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:74*
	 */
	abstract fun initWithUnsignedInt(value: Int): NSNumber?

	/**
	 * Original signature : `initWithLong(long)`<br></br>
	 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:75*
	 */
	abstract fun initWithLong(value: com.sun.jna.NativeLong?): NSNumber?

	/**
	 * Original signature : `initWithUnsignedLong(unsigned long)`<br></br>
	 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:76*
	 */
	abstract fun initWithUnsignedLong(value: com.sun.jna.NativeLong?): NSNumber?

	/**
	 * Original signature : `initWithLongLong(long long)`<br></br>
	 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:77*
	 */
	abstract fun initWithLongLong(value: Long): NSNumber?

	/**
	 * Original signature : `initWithUnsignedLongLong(unsigned long long)`<br></br>
	 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:78*
	 */
	abstract fun initWithUnsignedLongLong(value: Long): NSNumber?

	/**
	 * Original signature : `initWithFloat(float)`<br></br>
	 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:79*
	 */
	abstract fun initWithFloat(value: Float): NSNumber?

	/**
	 * Original signature : `initWithDouble(double)`<br></br>
	 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:80*
	 */
	abstract fun initWithDouble(value: Double): NSNumber?

	/**
	 * Original signature : `initWithBool(BOOL)`<br></br>
	 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:81*
	 */
	abstract fun initWithBool(value: Boolean): NSNumber?

	/**
	 * Original signature : `initWithInteger(NSInteger)`<br></br>
	 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:83*
	 */
	abstract fun initWithInteger(value: NSInteger?): NSNumber?

	/**
	 * Original signature : `initWithUnsignedInteger(NSUInteger)`<br></br>
	 * *from NSNumberCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSValue.h:84*
	 */
	abstract fun initWithUnsignedInteger(value: Int): NSNumber?

	/**
	 * Original signature : `decimalValue()`<br></br>
	 * *from NSDecimalNumberExtensions native declaration : :141*
	 */
	abstract fun decimalValue(): NSObject?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSNumber", _Class::class.java)

		fun numberWithInt(value: Int): NSNumber? {
			return CLASS.numberWithInt(value)
		}

		fun numberWithDouble(value: Double): NSNumber? {
			return CLASS.numberWithDouble(value)
		}

		fun numberWithFloat(value: Float): NSNumber? {
			return CLASS.numberWithFloat(value)
		}

		fun numberWithBoolean(value: Boolean): NSNumber? {
			return CLASS.numberWithBool(value)
		}
	}
}
