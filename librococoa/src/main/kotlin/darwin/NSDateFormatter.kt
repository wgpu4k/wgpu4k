package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSUInteger

abstract class NSDateFormatter : NSFormatter() {
	interface _Class : ObjCClass {
		open fun alloc(): NSDateFormatter
	}

	/**
	 * Original signature : `init()`<br></br>
	 * *native declaration : :18*
	 */
	abstract fun init(): NSDateFormatter?

	abstract fun setDoesRelativeDateFormatting(relative: Boolean)


	/**
	 * Original signature : `NSString* stringFromDate(NSDate*)`<br></br>
	 * *native declaration : :29*
	 */
	abstract fun stringFromDate(date: NSDate?): String?

	/**
	 * Original signature : `NSDate* dateFromString(NSString*)`<br></br>
	 * *native declaration : :30*
	 */
	abstract fun dateFromString(string: String?): String?

	/**
	 * Original signature : `NSString* dateFormat()`<br></br>
	 * *native declaration : :36*
	 */
	abstract fun dateFormat(): String?

	/**
	 * Original signature : `dateStyle()`<br></br>
	 * *native declaration : :41*
	 */
	abstract fun dateStyle(): NSUInteger?

	/**
	 * *native declaration : :42*<br></br>
	 * Conversion Error : /// Original signature : `void setDateStyle(null)`<br></br>
	 * - (void)setDateStyle:(null)style; (Argument style cannot be converted)
	 */
	abstract fun setDateStyle(style: NSUInteger?)

	/**
	 * Original signature : `timeStyle()`<br></br>
	 * *native declaration : :44*
	 */
	abstract fun timeStyle(): NSUInteger?

	/**
	 * *native declaration : :45*<br></br>
	 * Conversion Error : /// Original signature : `void setTimeStyle(null)`<br></br>
	 */
	abstract fun setTimeStyle(style: NSUInteger?)

	/**
	 * Original signature : `NSLocale* locale()`<br></br>
	 * *native declaration : :47*
	 */
	abstract fun locale(): com.sun.jna.Pointer?

	/**
	 * Original signature : `void setLocale(NSLocale*)`<br></br>
	 * *native declaration : :48*
	 */
	abstract fun setLocale(locale: NSLocale?)

	/**
	 * Original signature : `BOOL generatesCalendarDates()`<br></br>
	 * *native declaration : :50*
	 */
	abstract fun generatesCalendarDates(): Boolean

	/**
	 * Original signature : `void setGeneratesCalendarDates(BOOL)`<br></br>
	 * *native declaration : :51*
	 */
	abstract fun setGeneratesCalendarDates(b: Boolean)

	/**
	 * Original signature : `-(NSTimeZone*)timeZone`<br></br>
	 * *native declaration : /System/Library/Frameworks/CoreFoundation.framework/Headers/CFDateFormatter.h:55*
	 */
	abstract fun timeZone(): NSTimeZone?

	/**
	 * Original signature : `-(void)setTimeZone:(NSTimeZone*)`<br></br>
	 * *native declaration : /System/Library/Frameworks/CoreFoundation.framework/Headers/CFDateFormatter.h:56*
	 */
	abstract fun setTimeZone(tz: NSTimeZone?)

	/**
	 * Original signature : `-(BOOL)isLenient`<br></br>
	 * *native declaration : /System/Library/Frameworks/CoreFoundation.framework/Headers/CFDateFormatter.h:61*
	 */
	abstract fun isLenient(): Boolean

	/**
	 * Original signature : `-(void)setLenient:(BOOL)`<br></br>
	 * *native declaration : /System/Library/Frameworks/CoreFoundation.framework/Headers/CFDateFormatter.h:62*
	 */
	abstract fun setLenient(b: Boolean)

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSDateFormatter", _Class::class.java)

		/// <i>native declaration : :24</i>
		val kCFDateFormatterNoStyle: NSUInteger? = NSUInteger(0)

		/// <i>native declaration : :25</i>
		val kCFDateFormatterShortStyle: NSUInteger? = NSUInteger(1)

		/// <i>native declaration : :26</i>
		val kCFDateFormatterMediumStyle: NSUInteger? = NSUInteger(2)

		/// <i>native declaration : :27</i>
		val kCFDateFormatterLongStyle: NSUInteger? = NSUInteger(3)

		/// <i>native declaration : :28</i>
		val kCFDateFormatterFullStyle: NSUInteger? = NSUInteger(4)

		fun dateFormatter(): NSDateFormatter? {
			return CLASS.alloc().init()
		}
	}
}
