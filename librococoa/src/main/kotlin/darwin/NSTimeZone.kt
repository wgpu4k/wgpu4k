package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSInteger

abstract class NSTimeZone : NSObject() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `NSTimeZone* systemTimeZone()`<br></br>
		 * *from NSExtendedTimeZone native declaration : :22*
		 */
		open fun systemTimeZone(): NSTimeZone?

		/**
		 * Original signature : `void resetSystemTimeZone()`<br></br>
		 * *from NSExtendedTimeZone native declaration : :23*
		 */
		open fun resetSystemTimeZone()

		/**
		 * Original signature : `NSTimeZone* defaultTimeZone()`<br></br>
		 * *from NSExtendedTimeZone native declaration : :25*
		 */
		open fun defaultTimeZone(): NSTimeZone?

		open fun timeZoneWithName(tzName: String?): NSTimeZone?

		/**
		 * Original signature : `void setDefaultTimeZone(NSTimeZone*)`<br></br>
		 * *from NSExtendedTimeZone native declaration : :26*
		 */
		open fun setDefaultTimeZone(aTimeZone: NSTimeZone?)

		/**
		 * Original signature : `NSTimeZone* localTimeZone()`<br></br>
		 * *from NSExtendedTimeZone native declaration : :28*
		 */
		open fun localTimeZone(): NSTimeZone?

		/**
		 * Original signature : `NSArray* knownTimeZoneNames()`<br></br>
		 * *from NSExtendedTimeZone native declaration : :30*
		 */
		open fun knownTimeZoneNames(): NSArray?

		/**
		 * Original signature : `NSDictionary* abbreviationDictionary()`<br></br>
		 * *from NSExtendedTimeZone native declaration : :32*
		 */
		open fun abbreviationDictionary(): NSDictionary?
	}

	/**
	 * Original signature : `NSString* name()`<br></br>
	 * *native declaration : :9*
	 */
	abstract fun name(): String?

	/**
	 * Original signature : `NSData* data()`<br></br>
	 * *native declaration : :10*
	 */
	abstract fun data(): NSData?

	/**
	 * Original signature : `NSInteger secondsFromGMTForDate(NSDate*)`<br></br>
	 * *native declaration : :12*
	 */
	abstract fun secondsFromGMTForDate(aDate: NSDate?): NSInteger?

	/**
	 * Original signature : `NSString* abbreviationForDate(NSDate*)`<br></br>
	 * *native declaration : :13*
	 */
	abstract fun abbreviationForDate(aDate: NSDate?): String?

	/**
	 * Original signature : `BOOL isDaylightSavingTimeForDate(NSDate*)`<br></br>
	 * *native declaration : :14*
	 */
	abstract fun isDaylightSavingTimeForDate(aDate: NSDate?): Boolean

	/**
	 * Original signature : `daylightSavingTimeOffsetForDate(NSDate*)`<br></br>
	 * *native declaration : :15*
	 */
	abstract fun daylightSavingTimeOffsetForDate(aDate: com.sun.jna.Pointer?): com.sun.jna.Pointer?

	/**
	 * Original signature : `NSDate* nextDaylightSavingTimeTransitionAfterDate(NSDate*)`<br></br>
	 * *native declaration : :16*
	 */
	abstract fun nextDaylightSavingTimeTransitionAfterDate(aDate: NSDate?): NSDate?

	/**
	 * Original signature : `NSInteger secondsFromGMT()`<br></br>
	 * *from NSExtendedTimeZone native declaration : :34*
	 */
	abstract fun secondsFromGMT(): NSInteger?

	/**
	 * Original signature : `NSString* abbreviation()`<br></br>
	 * *from NSExtendedTimeZone native declaration : :35*
	 */
	abstract fun abbreviation(): String?

	/**
	 * Original signature : `BOOL isDaylightSavingTime()`<br></br>
	 * *from NSExtendedTimeZone native declaration : :36*
	 */
	abstract fun isDaylightSavingTime(): Boolean

	/**
	 * Original signature : `daylightSavingTimeOffset()`<br></br>
	 * for current instant<br></br>
	 * *from NSExtendedTimeZone native declaration : :37*
	 */
	abstract fun daylightSavingTimeOffset(): com.sun.jna.Pointer?

	/**
	 * Original signature : `NSDate* nextDaylightSavingTimeTransition()`<br></br>
	 * after current instant<br></br>
	 * *from NSExtendedTimeZone native declaration : :38*
	 */
	abstract fun nextDaylightSavingTimeTransition(): NSDate?

	/**
	 * Original signature : `BOOL isEqualToTimeZone(NSTimeZone*)`<br></br>
	 * *from NSExtendedTimeZone native declaration : :42*
	 */
	abstract fun isEqualToTimeZone(aTimeZone: NSTimeZone?): Boolean

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSTimeZone", _Class::class.java)

		fun defaultTimeZone(): NSTimeZone? {
			return CLASS.defaultTimeZone()
		}

		fun systemTimeZone(): NSTimeZone? {
			return CLASS.systemTimeZone()
		}

		fun timeZoneWithName(tzName: String?): NSTimeZone? {
			return CLASS.timeZoneWithName(tzName)
		}
	}
}
