package darwin 

import org.rococoa.ObjCClass

/// <i>native declaration : :12</i>
abstract class NSNotification : NSObject(), NSCopying {
	interface _Class : ObjCClass {
		/**
		 * *from NSNotificationCreation native declaration : :22*<br></br>
		 * Conversion Error : /// Original signature : `notificationWithName(NSString*, null)`<br></br>
		 * + (null)notificationWithName:(NSString*)aName object:(null)anObject; (Argument anObject cannot be converted)
		 */
		open fun notificationWithName_object(notificationName: String?, `object`: org.rococoa.ID?): NSNotification?

		/**
		 * *from NSNotificationCreation native declaration : :23*<br></br>
		 * Conversion Error : /// Original signature : `notificationWithName(NSString*, null, NSDictionary*)`<br></br>
		 * + (null)notificationWithName:(NSString*)aName object:(null)anObject userInfo:(NSDictionary*)aUserInfo; (Argument anObject cannot be converted)
		 */
		open fun alloc(): NSNotification?
	}

	/**
	 * Original signature : `NSString* name()`<br></br>
	 * *native declaration : :14*
	 */
	abstract fun name(): String?

	/**
	 * Original signature : `object()`<br></br>
	 * *native declaration : :15*
	 */
	abstract fun `object`(): NSObject?

	/**
	 * Original signature : `NSDictionary* userInfo()`<br></br>
	 * *native declaration : :16*
	 */
	abstract fun userInfo(): NSDictionary?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSNotification", _Class::class.java)

		fun notificationWithName(notificationName: String?, `object`: org.rococoa.ID?): NSNotification? {
			return CLASS.notificationWithName_object(notificationName, `object`)
		}

		fun notificationWithName(notificationName: String?, `object`: String?): NSNotification? {
			return CLASS.notificationWithName_object(notificationName, NSString.stringWithString(`object`).id())
		}
	}
}
