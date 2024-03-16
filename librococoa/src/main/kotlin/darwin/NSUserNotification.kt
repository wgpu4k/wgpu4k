package darwin 

import org.rococoa.ObjCClass

abstract class NSUserNotification : NSObject() {
	interface _Class : ObjCClass {
		open fun alloc(): NSUserNotification
	}

	abstract fun init(): NSUserNotification?

	abstract fun getTitle(): String?

	abstract fun setTitle(title: String?)

	abstract fun getSubtitle(): String?

	abstract fun setSubtitle(subtitle: String?)

	abstract fun getInformativeText(): String?

	abstract fun setInformativeText(informativeText: String?)

	/**
	 * Available in OS X v10.9 and later.
	 */
	abstract fun getContentImage(): NSImage?

	/**
	 * Available in OS X v10.9 and later.
	 */
	abstract fun setContentImage(contentImage: NSImage?)

	/**
	 * Available in OS X v10.9 and later.
	 */
	abstract fun identifier(): String?

	/**
	 * This identifier is unique to a notification. A notification delivered with the same identifier
	 * as an existing notification will replace that notification, rather then display a new one.
	 *
	 *
	 * Available in OS X v10.9 and later.
	 */
	abstract fun setIdentifier(identifier: String?)

	/**
	 * Application-specific user info that can be attached to the notification.
	 */
	abstract fun setUserInfo(userInfo: NSDictionary?)

	abstract fun userInfo(): NSDictionary?

	abstract fun setHasActionButton(flag: Boolean)

	abstract fun setActionButtonTitle(title: String?)

	abstract fun setOtherButtonTitle(title: String?)

	abstract fun activationType(): Int

	enum class ActivationType {
		NSUserNotificationActivationTypeNone,
		NSUserNotificationActivationTypeContentsClicked,
		NSUserNotificationActivationTypeActionButtonClicked,
		NSUserNotificationActivationTypeReplied,
		NSUserNotificationActivationTypeAdditionalActionClicked
	}

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSUserNotification", _Class::class.java)

		fun notification(): NSUserNotification? {
			return CLASS.alloc().init()
		}
	}
}
