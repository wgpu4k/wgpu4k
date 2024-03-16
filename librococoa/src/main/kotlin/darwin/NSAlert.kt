package darwin 

import org.rococoa.ID
import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSError

/// <i>native declaration : :20</i>
abstract class NSAlert : NSObject() {
	interface _Class : ObjCClass {
		open fun alloc(): NSAlert

		/**
		 * Given an NSError, create an NSAlert that can be used to present the error to the user. The error's localized description, recovery suggestion, and recovery options will be used to set the alert's message text, informative text, and button titles, respectively.<br></br>
		 * Original signature : `NSAlert* alertWithError(NSError*)`<br></br>
		 * *native declaration : :60*
		 */
		open fun alertWithError(error: NSError?): NSAlert?

		/**
		 * the following class method is for use by apps migrating from the C-based API.  Note that this returns an NSAlert that is equivalent to the one created in NSRunAlertPanel, so the layout, button return values, and key equivalents are the same as for the C-based API.<br></br>
		 * Original signature : `NSAlert* alertWithMessageText(NSString*, NSString*, NSString*, NSString*, NSString*, null)`<br></br>
		 * *native declaration : :65*
		 */
		open fun alertWithMessageText_defaultButton_alternateButton_otherButton_informativeTextWithFormat(
			message: String?, defaultButton: String?, alternateButton: String?, otherButton: String?, format: String?
		): NSAlert?
	}

	abstract fun init(): NSAlert

	/**
	 * Original signature : `void setMessageText(NSString*)`<br></br>
	 * *native declaration : :67*
	 */
	abstract fun setMessageText(messageText: String?)

	/**
	 * Original signature : `void setInformativeText(NSString*)`<br></br>
	 * *native declaration : :68*
	 */
	abstract fun setInformativeText(informativeText: String?)

	/**
	 * Original signature : `NSString* messageText()`<br></br>
	 * *native declaration : :70*
	 */
	abstract fun messageText(): String?

	/**
	 * Original signature : `NSString* informativeText()`<br></br>
	 * *native declaration : :71*
	 */
	abstract fun informativeText(): String?

	/**
	 * customize the icon.  By default uses the image named NSApplicationIcon<br></br>
	 * Original signature : `void setIcon(NSImage*)`<br></br>
	 * *native declaration : :74*
	 */
	abstract fun setIcon(icon: NSImage?)

	/**
	 * Original signature : `NSImage* icon()`<br></br>
	 * *native declaration : :75*
	 */
	abstract fun icon(): NSImage?

	/**
	 * buttons are added from right to left (for left to right languages)<br></br>
	 * Original signature : `NSButton* addButtonWithTitle(NSString*)`<br></br>
	 * *native declaration : :79*
	 */
	abstract fun addButtonWithTitle(title: String?): NSButton?

	/**
	 * get the buttons, where the rightmost button is at index 0<br></br>
	 * Original signature : `NSArray* buttons()`<br></br>
	 * *native declaration : :81*
	 */
	abstract fun buttons(): NSArray?

	/**
	 * -setShowsHelp:YES adds a help button to the alert panel. When the help button is pressed, the delegate is first consulted.  If the delegate does not implement alertShowHelp: or returns NO, then -[NSHelpManager openHelpAnchor:inBook:] is called with a nil book and the anchor specified by -setHelpAnchor:, if any.  An exception will be raised if the delegate returns NO and there is no help anchor set.<br></br>
	 * Original signature : `void setShowsHelp(BOOL)`<br></br>
	 * *native declaration : :99*
	 */
	abstract fun setShowsHelp(showsHelp: Boolean)

	/**
	 * Original signature : `BOOL showsHelp()`<br></br>
	 * *native declaration : :100*
	 */
	abstract fun showsHelp(): Boolean

	/**
	 * Original signature : `void setHelpAnchor(NSString*)`<br></br>
	 * *native declaration : :102*
	 */
	abstract fun setHelpAnchor(anchor: String?)

	/**
	 * Original signature : `NSString* helpAnchor()`<br></br>
	 * *native declaration : :103*
	 */
	abstract fun helpAnchor(): String?

	/**
	 * Original signature : `void setAlertStyle(NSAlertStyle)`<br></br>
	 * *native declaration : :105*
	 */
	abstract fun setAlertStyle(style: Int)

	/**
	 * Original signature : `NSAlertStyle alertStyle()`<br></br>
	 * *native declaration : :106*
	 */
	abstract fun alertStyle(): Int

	/**
	 * Original signature : `void setDelegate(id)`<br></br>
	 * *native declaration : :108*
	 */
	abstract fun setDelegate(delegate: ID?)

	/**
	 * Original signature : `id delegate()`<br></br>
	 * *native declaration : :109*
	 */
	abstract fun delegate(): ID?

	/**
	 * -setShowsSuppressionButton: indicates whether or not the alert should contain a suppression checkbox.  The default is NO.  This checkbox is typically used to give the user an option to not show this alert again.  If shown, the suppression button will have a default localized title similar to @"Do not show this message again".  You can customize this title using [[alert suppressionButton] setTitle:].  When the alert is dismissed, you can get the state of the suppression button, using [[alert suppressionButton] state] and store the result in user defaults, for example.  This setting can then be checked before showing the alert again.  By default, the suppression button is positioned below the informative text, and above the accessory view (if any) and the alert buttons, and left-aligned with the informative text.  However do not count on the placement of this button, since it might be moved if the alert panel user interface is changed in the future. If you need a checkbox for purposes other than suppression text, it is recommended you create your own using an accessory view.<br></br>
	 * Original signature : `void setShowsSuppressionButton(BOOL)`<br></br>
	 * *native declaration : :114*
	 */
	abstract fun setShowsSuppressionButton(flag: Boolean)

	/**
	 * Original signature : `BOOL showsSuppressionButton()`<br></br>
	 * *native declaration : :115*
	 */
	abstract fun showsSuppressionButton(): Boolean

	/**
	 * -suppressionButton returns a suppression button which may be customized, including the title and the initial state.  You can also use this method to get the state of the button after the alert is dismissed, which may be stored in user defaults and checked before showing the alert again.  In order to show the suppression button in the alert panel, you must call -setShowsSuppressionButton:YES.<br></br>
	 * Original signature : `NSButton* suppressionButton()`<br></br>
	 * *native declaration : :119*
	 */
	abstract fun suppressionButton(): NSButton?

	/**
	 * -setAccessoryView: sets the accessory view displayed in the alert panel.  By default, the accessory view is positioned below the informative text and the suppression button (if any) and above the alert buttons, left-aligned with the informative text.  If you want to customize the location of the accessory view, you must first call -layout.  See the discussion of -layout for more information.<br></br>
	 * Original signature : `void setAccessoryView(NSView*)`<br></br>
	 * *native declaration : :124*
	 */
	abstract fun setAccessoryView(view: NSView?)

	/**
	 * Original signature : `NSView* accessoryView()`<br></br>
	 * *native declaration : :125*
	 */
	abstract fun accessoryView(): NSView?

	/**
	 * -layout can be used to indicate that the alert panel should do immediate layout, overriding the default behavior of laying out lazily just before showing panel.  You should only call this method if you want to do your own custom layout after it returns.  You should call this method only after you have finished with NSAlert customization, including setting message and informative text, and adding buttons and an accessory view if needed.  You can make layout changes after this method returns, in particular to adjust the frame of an accessory view.  Note that the standard layout of the alert may change in the future, so layout customization should be done with caution.<br></br>
	 * Original signature : `void layout()`<br></br>
	 * *native declaration : :129*
	 */
	abstract fun layout()

	/**
	 * Run the alert as an application-modal panel and return the result<br></br>
	 * Original signature : `NSInteger runModal()`<br></br>
	 * *native declaration : :134*
	 */
	abstract fun runModal(): Int

	/**
	 * Original signature : `void beginSheetModalForWindow(NSWindow*, id, SEL, void*)`<br></br>
	 * *native declaration : :139*
	 */
	abstract fun beginSheetModalForWindow_modalDelegate_didEndSelector_contextInfo(
		window: NSWindow?,
		delegate: ID?,
		didEndSelector: org.rococoa.Selector?,
		contextInfo: ID?
	)

	fun beginSheet(window: NSWindow?, delegate: ID?, didEndSelector: org.rococoa.Selector?, contextInfo: ID?) {
		this.beginSheetModalForWindow_modalDelegate_didEndSelector_contextInfo(
			window,
			delegate,
			didEndSelector,
			contextInfo
		)
	}

	/**
	 * return the application-modal panel or the document-modal sheet corresponding to this alert<br></br>
	 * Original signature : `id window()`<br></br>
	 * *native declaration : :142*
	 */
	abstract fun window(): NSWindow?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSAlert", _Class::class.java)

		const val NSAlertFirstButtonReturn: Int = 1000
		const val NSAlertSecondButtonReturn: Int = 1001
		const val NSAlertThirdButtonReturn: Int = 1002

		/// <i>native declaration : :54</i>
		const val NSAlertDefaultReturn: Int = 1

		/// <i>native declaration : :55</i>
		const val NSAlertAlternateReturn: Int = 0

		/// <i>native declaration : :56</i>
		const val NSAlertOtherReturn: Int = -1

		/// <i>native declaration : :57</i>
		const val NSAlertErrorReturn: Int = -2

		/// <i>native declaration : line 12</i>
		const val NSWarningAlertStyle: Int = 0

		/// <i>native declaration : line 13</i>
		const val NSInformationalAlertStyle: Int = 1

		/// <i>native declaration : line 14</i>
		const val NSCriticalAlertStyle: Int = 2

		fun alert(): NSAlert {
			return CLASS.alloc().init()
		}

		fun alertWithError(error: NSError?): NSAlert? {
			return CLASS.alertWithError(error)
		}

		fun alert(
			title: String?,
			message: String?,
			defaultButton: String?,
			alternateButton: String?,
			otherButton: String?
		): NSAlert? {
			val alert = alert()
			alert.setMessageText(title)
			alert.setInformativeText(message)
			if (defaultButton !== "") {
				// OK
				alert.addButtonWithTitle(defaultButton)
			}
			if (otherButton !== "") {
				// Cancel
				alert.addButtonWithTitle(otherButton)
			}
			if (alternateButton !== "") {
				alert.addButtonWithTitle(alternateButton)
			}
			return alert
		}
	}
}
