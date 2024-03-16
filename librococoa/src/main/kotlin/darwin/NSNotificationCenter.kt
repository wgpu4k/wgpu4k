package darwin 

import org.rococoa.ID
import org.rococoa.ObjCClass
import org.rococoa.Selector

/// <i>native declaration : :29</i>
abstract class NSNotificationCenter : NSObject() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `defaultCenter()`<br></br>
		 * *native declaration : :36*
		 */
		open fun defaultCenter(): NSNotificationCenter?
	}

	fun addObserver(
		notificationObserver: ID?,
		notificationSelector: Selector?,
		notificationName: String?,
		notificationSender: ID?
	) {
		this.addObserver_selector_name_object(
			notificationObserver,
			notificationSelector,
			notificationName,
			notificationSender
		)
	}

	/**
	 * *native declaration : :38*<br></br>
	 * Conversion Error : /// Original signature : `void addObserver(null, null, NSString*, null)`<br></br>
	 * - (void)addObserver:(null)observer selector:(null)aSelector name:(NSString*)aName object:(null)anObject; (Argument observer cannot be converted)
	 */
	abstract fun addObserver_selector_name_object(
		notificationObserver: ID?,
		notificationSelector: Selector?,
		notificationName: String?,
		notificationSender: ID?
	)

	/**
	 * Original signature : `void postNotification(NSNotification*)`<br></br>
	 * *native declaration : :40*
	 */
	abstract fun postNotification(notification: NSNotification?)
	/**
	 * *native declaration : :41*<br></br>
	 * Conversion Error : /// Original signature : `void postNotificationName(NSString*, null)`<br></br>
	 * - (void)postNotificationName:(NSString*)aName object:(null)anObject; (Argument anObject cannot be converted)
	 */
	/**
	 * *native declaration : :42*<br></br>
	 * Conversion Error : /// Original signature : `void postNotificationName(NSString*, null, NSDictionary*)`<br></br>
	 * - (void)postNotificationName:(NSString*)aName object:(null)anObject userInfo:(NSDictionary*)aUserInfo; (Argument anObject cannot be converted)
	 */
	/**
	 * *native declaration : :44*<br></br>
	 * Conversion Error : /// Original signature : `void removeObserver(null)`<br></br>
	 * - (void)removeObserver:(null)observer; (Argument observer cannot be converted)
	 */
	abstract fun removeObserver(notificationObserver: ID?)

	/**
	 * *native declaration : :45*<br></br>
	 * Conversion Error : /// Original signature : `void removeObserver(null, NSString*, null)`<br></br>
	 * - (void)removeObserver:(null)observer name:(NSString*)aName object:(null)anObject; (Argument observer cannot be converted)
	 */
	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSNotificationCenter", _Class::class.java)

		fun defaultCenter(): NSNotificationCenter? {
			return CLASS.defaultCenter()
		}
	}
}
