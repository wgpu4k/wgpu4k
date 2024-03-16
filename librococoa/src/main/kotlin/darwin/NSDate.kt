package darwin

import org.rococoa.ObjCClass 
import org.rococoa.Rococoa

abstract class NSDate : NSObject() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `date()`<br></br>
		 * *from NSDateCreation native declaration : :41*
		 */
		fun date(): NSDate

		/**
		 * Original signature : `dateWithTimeIntervalSinceNow(NSTimeInterval)`<br></br>
		 * *from NSDateCreation native declaration : :43*
		 */
		fun dateWithTimeIntervalSinceNow(secs: Double): NSDate

		/**
		 * Original signature : `dateWithTimeIntervalSinceReferenceDate(NSTimeInterval)`<br></br>
		 * *from NSDateCreation native declaration : :44*
		 */
		fun dateWithTimeIntervalSinceReferenceDate(secs: Double): NSDate

		/**
		 * Original signature : `dateWithTimeIntervalSince1970(NSTimeInterval)`<br></br>
		 * *from NSDateCreation native declaration : :45*
		 */
		fun dateWithTimeIntervalSince1970(secs: Double): NSDate

		/**
		 * Original signature : `distantFuture()`<br></br>
		 * *from NSDateCreation native declaration : :47*
		 */
		fun distantFuture(): NSDate

		/**
		 * Original signature : `distantPast()`<br></br>
		 * *from NSDateCreation native declaration : :48*
		 */
		fun distantPast(): NSDate
	}

	/**
	 * Original signature : `NSTimeInterval timeIntervalSinceReferenceDate()`<br></br>
	 * *native declaration : :16*
	 */
	abstract fun timeIntervalSinceReferenceDate(): Double

	/**
	 * Original signature : `NSTimeInterval timeIntervalSinceDate(NSDate*)`<br></br>
	 * *from NSExtendedDate native declaration : :22*
	 */
	abstract fun timeIntervalSinceDate(anotherDate: NSDate?): Double

	/**
	 * Original signature : `NSTimeInterval timeIntervalSinceNow()`<br></br>
	 * *from NSExtendedDate native declaration : :23*
	 */
	abstract fun timeIntervalSinceNow(): Double

	/**
	 * Original signature : `NSTimeInterval timeIntervalSince1970()`<br></br>
	 * *from NSExtendedDate native declaration : :24*
	 */
	abstract fun timeIntervalSince1970(): Double

	/**
	 * Original signature : `addTimeInterval(NSTimeInterval)`<br></br>
	 * *from NSExtendedDate native declaration : :26*
	 */
	abstract fun addTimeInterval(seconds: Double): NSDate?

	/**
	 * Original signature : `NSDate* earlierDate(NSDate*)`<br></br>
	 * *from NSExtendedDate native declaration : :28*
	 */
	abstract fun earlierDate(anotherDate: NSDate?): NSDate?

	/**
	 * Original signature : `NSDate* laterDate(NSDate*)`<br></br>
	 * *from NSExtendedDate native declaration : :29*
	 */
	abstract fun laterDate(anotherDate: NSDate?): NSDate?

	/**
	 * Original signature : `compare(NSDate*)`<br></br>
	 * *from NSExtendedDate native declaration : :30*
	 */
	abstract fun compare(other: NSDate?): NSObject?

	/**
	 * Original signature : `NSString* description()`<br></br>
	 * *from NSExtendedDate native declaration : :32*
	 */
	abstract override fun description(): String

	/**
	 * Original signature : `BOOL isEqualToDate(NSDate*)`<br></br>
	 * *from NSExtendedDate native declaration : :33*
	 */
	abstract fun isEqualToDate(otherDate: NSDate?): Byte

	/**
	 * Original signature : `init()`<br></br>
	 * *from NSDateCreation native declaration : :50*
	 */
	abstract fun init(): NSDate?

	/**
	 * Original signature : `initWithTimeIntervalSinceReferenceDate(NSTimeInterval)`<br></br>
	 * *from NSDateCreation native declaration : :51*
	 */
	abstract fun initWithTimeIntervalSinceReferenceDate(secsToBeAdded: Double): NSDate?

	/**
	 * Original signature : `initWithTimeInterval(NSTimeInterval, NSDate*)`<br></br>
	 * *from NSDateCreation native declaration : :52*
	 */
	abstract fun initWithTimeInterval_sinceDate(secsToBeAdded: Double, anotherDate: NSDate?): NSDate?

	/**
	 * Original signature : `initWithTimeIntervalSinceNow(NSTimeInterval)`<br></br>
	 * *from NSDateCreation native declaration : :53*
	 */
	abstract fun initWithTimeIntervalSinceNow(secsToBeAddedToNow: Double): NSDate?

	companion object {
		private val CLASS: _Class = Rococoa.createClass("NSDate", _Class::class.java)

		fun date(): NSDate {
			return CLASS.date()
		}

		fun dateWithTimeIntervalSinceNow(secs: Double): NSDate {
			return CLASS.dateWithTimeIntervalSinceNow(secs)
		}

		fun dateWithTimeIntervalSinceReferenceDate(secs: Double): NSDate {
			return CLASS.dateWithTimeIntervalSinceReferenceDate(secs)
		}

		fun dateWithTimeIntervalSince1970(secs: Double): NSDate {
			return CLASS.dateWithTimeIntervalSince1970(secs)
		}

		fun distantFuture(): NSDate {
			return CLASS.distantFuture()
		}

		fun distantPast(): NSDate {
			return CLASS.distantPast()
		}
	}
}
