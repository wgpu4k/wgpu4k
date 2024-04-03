package darwin 

import org.rococoa.ObjCClass

/// <i>native declaration : :21</i>
abstract class NSTextFieldCell : NSActionCell() {
	interface _Class : ObjCClass {
		open fun alloc(): NSTextFieldCell
	}

	abstract fun init(): NSTextFieldCell?

	/**
	 * Original signature : `void setBackgroundColor(NSColor*)`<br></br>
	 * *native declaration : :40*
	 */
	abstract fun setBackgroundColor(color: NSColor?)

	/**
	 * Original signature : `NSColor* backgroundColor()`<br></br>
	 * *native declaration : :41*
	 */
	abstract fun backgroundColor(): NSColor?

	/**
	 * Original signature : `void setDrawsBackground(BOOL)`<br></br>
	 * *native declaration : :42*
	 */
	abstract fun setDrawsBackground(flag: Boolean)

	/**
	 * Original signature : `BOOL drawsBackground()`<br></br>
	 * *native declaration : :43*
	 */
	abstract fun drawsBackground(): Boolean

	/**
	 * Original signature : `void setTextColor(NSColor*)`<br></br>
	 * *native declaration : :44*
	 */
	abstract fun setTextColor(color: NSColor?)

	/**
	 * Original signature : `NSColor* textColor()`<br></br>
	 * *native declaration : :45*
	 */
	abstract fun textColor(): NSColor?

	/**
	 * Original signature : `NSText* setUpFieldEditorAttributes(NSText*)`<br></br>
	 * *native declaration : :46*
	 */
	abstract fun setUpFieldEditorAttributes(textObj: NSText?): NSText?

	/**
	 * Original signature : `void setBezelStyle(NSTextFieldBezelStyle)`<br></br>
	 * *native declaration : :49*
	 */
	abstract fun setBezelStyle(style: Int)

	/**
	 * Original signature : `NSTextFieldBezelStyle bezelStyle()`<br></br>
	 * *native declaration : :50*
	 */
	abstract fun bezelStyle(): Int

	/**
	 * Original signature : `void setPlaceholderString(NSString*)`<br></br>
	 * *native declaration : :54*
	 */
	abstract fun setPlaceholderString(string: String?)

	/**
	 * Original signature : `NSString* placeholderString()`<br></br>
	 * *native declaration : :55*
	 */
	abstract fun placeholderString(): String?

	/**
	 * Original signature : `void setPlaceholderAttributedString(NSAttributedString*)`<br></br>
	 * *native declaration : :56*
	 */
	abstract fun setPlaceholderAttributedString(string: NSAttributedString?)

	/**
	 * Original signature : `NSAttributedString* placeholderAttributedString()`<br></br>
	 * *native declaration : :57*
	 */
	abstract fun placeholderAttributedString(): NSAttributedString?

	/**
	 * Original signature : `void setWantsNotificationForMarkedText(BOOL)`<br></br>
	 * *native declaration : :61*
	 */
	abstract fun setWantsNotificationForMarkedText(flag: Boolean)

	/**
	 * Returns an array of locale identifiers representing input sources allowed to be enabled when the receiver has the keyboard focus.<br></br>
	 * Original signature : `NSArray* allowedInputSourceLocales()`<br></br>
	 * *native declaration : :65*
	 */
	abstract fun allowedInputSourceLocales(): NSArray?

	/**
	 * Original signature : `void setAllowedInputSourceLocales(NSArray*)`<br></br>
	 * *native declaration : :66*
	 */
	abstract fun setAllowedInputSourceLocales(localeIdentifiers: NSArray?)

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSTextFieldCell", _Class::class.java)

		fun textFieldCell(): NSTextFieldCell? {
			return CLASS.alloc().init()
		}
	}
}
