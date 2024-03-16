package darwin 

import org.rococoa.ID
import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSRect
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : :8</i>
abstract class NSTextField : NSControl() {
	interface _Class : ObjCClass {
		open fun alloc(): NSTextField
	}

	@Override
	abstract override fun initWithFrame(frameRect: NSRect?): NSTextField

	/**
	 * Original signature : `void setBackgroundColor(NSColor*)`<br></br>
	 * *native declaration : :15*
	 */
	abstract fun setBackgroundColor(color: NSColor?)

	/**
	 * Original signature : `NSColor* backgroundColor()`<br></br>
	 * *native declaration : :16*
	 */
	abstract fun backgroundColor(): NSColor?

	/**
	 * Original signature : `void setDrawsBackground(BOOL)`<br></br>
	 * *native declaration : :17*
	 */
	abstract fun setDrawsBackground(flag: Boolean)

	/**
	 * Original signature : `BOOL drawsBackground()`<br></br>
	 * *native declaration : :18*
	 */
	abstract fun drawsBackground(): Boolean

	/**
	 * Original signature : `void setTextColor(NSColor*)`<br></br>
	 * *native declaration : :19*
	 */
	abstract fun setTextColor(color: NSColor?)

	/**
	 * Original signature : `NSColor* textColor()`<br></br>
	 * *native declaration : :20*
	 */
	abstract fun textColor(): NSColor?

	/**
	 * Original signature : `BOOL isBordered()`<br></br>
	 * *native declaration : :21*
	 */
	abstract fun isBordered(): Boolean

	/**
	 * Original signature : `void setBordered(BOOL)`<br></br>
	 * *native declaration : :22*
	 */
	abstract fun setBordered(flag: Boolean)

	/**
	 * Original signature : `BOOL isBezeled()`<br></br>
	 * *native declaration : :23*
	 */
	abstract fun isBezeled(): Boolean

	/**
	 * Original signature : `void setBezeled(BOOL)`<br></br>
	 * *native declaration : :24*
	 */
	abstract fun setBezeled(flag: Boolean)

	/**
	 * Original signature : `BOOL isEditable()`<br></br>
	 * *native declaration : :25*
	 */
	abstract fun isEditable(): Boolean

	/**
	 * Original signature : `void setEditable(BOOL)`<br></br>
	 * *native declaration : :26*
	 */
	abstract fun setEditable(flag: Boolean)

	/**
	 * Original signature : `BOOL isSelectable()`<br></br>
	 * *native declaration : :27*
	 */
	abstract fun isSelectable(): Boolean

	/**
	 * Original signature : `void setSelectable(BOOL)`<br></br>
	 * *native declaration : :28*
	 */
	abstract fun setSelectable(flag: Boolean)

	/**
	 * Original signature : `void selectText(id)`<br></br>
	 * *native declaration : :29*
	 */
	abstract fun selectText(sender: ID?)

	/**
	 * Original signature : `id delegate()`<br></br>
	 * *native declaration : :30*
	 */
	abstract fun delegate(): ID?

	/**
	 * Original signature : `void setDelegate(id)`<br></br>
	 * *native declaration : :31*
	 */
	abstract fun setDelegate(id: ID?)

	/**
	 * Original signature : `BOOL textShouldBeginEditing(NSText*)`<br></br>
	 * *native declaration : :32*
	 */
	abstract fun textShouldBeginEditing(textObject: NSText?): Boolean

	/**
	 * Original signature : `BOOL textShouldEndEditing(NSText*)`<br></br>
	 * *native declaration : :33*
	 */
	abstract fun textShouldEndEditing(textObject: NSText?): Boolean

	/**
	 * Original signature : `void textDidBeginEditing(NSNotification*)`<br></br>
	 * *native declaration : :34*
	 */
	abstract fun textDidBeginEditing(notification: NSNotification?)

	/**
	 * Original signature : `void textDidEndEditing(NSNotification*)`<br></br>
	 * *native declaration : :35*
	 */
	abstract fun textDidEndEditing(notification: NSNotification?)

	/**
	 * Original signature : `void textDidChange(NSNotification*)`<br></br>
	 * *native declaration : :36*
	 */
	abstract fun textDidChange(notification: NSNotification?)

	/**
	 * *native declaration : :40*<br></br>
	 * Conversion Error : /// Original signature : `void setBezelStyle(null)`<br></br>
	 * - (void)setBezelStyle:(null)style; (Argument style cannot be converted)
	 */
	abstract fun setBezelStyle(style: NSUInteger?)

	/**
	 * Original signature : `bezelStyle()`<br></br>
	 * *native declaration : :41*
	 */
	abstract fun bezelStyle(): NSUInteger?

	/**
	 * Original signature : `void setTitleWithMnemonic(NSString*)`<br></br>
	 * *from NSKeyboardUI native declaration : :46*
	 */
	abstract fun setTitleWithMnemonic(stringWithAmpersand: String?)

	/**
	 * Original signature : `BOOL allowsEditingTextAttributes()`<br></br>
	 * *from NSTextFieldAttributedStringMethods native declaration : :50*
	 */
	abstract fun allowsEditingTextAttributes(): Boolean

	/**
	 * Original signature : `void setAllowsEditingTextAttributes(BOOL)`<br></br>
	 * *from NSTextFieldAttributedStringMethods native declaration : :51*
	 */
	abstract fun setAllowsEditingTextAttributes(flag: Boolean)

	/**
	 * Original signature : `BOOL importsGraphics()`<br></br>
	 * *from NSTextFieldAttributedStringMethods native declaration : :52*
	 */
	abstract fun importsGraphics(): Boolean

	/**
	 * Original signature : `void setImportsGraphics(BOOL)`<br></br>
	 * *from NSTextFieldAttributedStringMethods native declaration : :53*
	 */
	abstract fun setImportsGraphics(flag: Boolean)

	abstract override fun cell(): NSTextFieldCell?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSTextField", _Class::class.java)

		fun textfieldWithFrame(frameRect: NSRect?): NSTextField? {
			return CLASS.alloc().initWithFrame(frameRect)
		}
	}
}
