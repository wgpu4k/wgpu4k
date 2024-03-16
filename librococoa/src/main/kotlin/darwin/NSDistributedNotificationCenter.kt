package darwin 

import org.rococoa.ObjCClass

/// <i>native declaration : :27</i>
abstract class NSDistributedNotificationCenter : NSNotificationCenter() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `NSDistributedNotificationCenter* notificationCenterForType(NSString*)`<br></br>
		 * *native declaration : :29*
		 */
		open fun notificationCenterForType(notificationCenterType: String?): NSDistributedNotificationCenter?

		/**
		 * Original signature : `defaultCenter()`<br></br>
		 * *native declaration : :32*
		 */
		open fun defaultCenter(): NSDistributedNotificationCenter?
	}

	/**
	 * Original signature : `void postNotificationName(NSString*, NSString*, NSDictionary*, BOOL)`<br></br>
	 * *native declaration : :38*
	 */
	abstract fun postNotificationName_object_userInfo_deliverImmediately(
		name: String?,
		`object`: String?,
		userInfo: NSDictionary?,
		deliverImmediately: Boolean
	)

	companion object {
		private val CLASS: _Class =
			org.rococoa.Rococoa.createClass("NSDistributedNotificationCenter", _Class::class.java)

		fun defaultCenter(): NSNotificationCenter? {
			return CLASS.defaultCenter()
		}
	}
}
