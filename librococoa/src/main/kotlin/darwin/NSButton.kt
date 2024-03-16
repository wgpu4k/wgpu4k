package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSRect
import org.rococoa.cocoa.foundation.NSUInteger
import java.nio.FloatBuffer

/// <i>native declaration : :15</i>
abstract class NSButton : NSControl() {
	interface _Class : ObjCClass {
		open fun alloc(): NSButton
	}

	@Override
	abstract override fun initWithFrame(frameRect: NSRect?): NSButton

	/**
	 * Original signature : `NSString* title()`<br></br>
	 * *native declaration : :17*
	 */
	abstract fun title(): String?

	/**
	 * Original signature : `void setTitle(NSString*)`<br></br>
	 * *native declaration : :18*
	 */
	abstract fun setTitle(aString: String?)

	/**
	 * Original signature : `NSString* alternateTitle()`<br></br>
	 * *native declaration : :19*
	 */
	abstract fun alternateTitle(): String?

	/**
	 * Original signature : `void setAlternateTitle(NSString*)`<br></br>
	 * *native declaration : :20*
	 */
	abstract fun setAlternateTitle(aString: String?)

	/**
	 * Original signature : `NSImage* image()`<br></br>
	 * *native declaration : :21*
	 */
	abstract fun image(): NSImage?

	/**
	 * Original signature : `void setImage(NSImage*)`<br></br>
	 * *native declaration : :22*
	 */
	abstract fun setImage(image: NSImage?)

	/**
	 * Original signature : `NSImage* alternateImage()`<br></br>
	 * *native declaration : :23*
	 */
	abstract fun alternateImage(): NSImage?

	/**
	 * Original signature : `void setAlternateImage(NSImage*)`<br></br>
	 * *native declaration : :24*
	 */
	abstract fun setAlternateImage(image: NSImage?)

	/**
	 * Original signature : `imagePosition()`<br></br>
	 * *native declaration : :25*
	 */
	abstract fun imagePosition(): com.sun.jna.Pointer?

	/**
	 * *native declaration : :26*<br></br>
	 * Conversion Error : /// Original signature : `void setImagePosition(null)`<br></br>
	 * - (void)setImagePosition:(null)aPosition; (Argument aPosition cannot be converted)
	 */
	abstract fun setImagePosition(position: Int)

	/**
	 * *native declaration : :27*<br></br>
	 * Conversion Error : /// Original signature : `void setButtonType(null)`<br></br>
	 * - (void)setButtonType:(null)aType; (Argument aType cannot be converted)
	 */
	abstract fun setButtonType(type: Int)

	/**
	 * Original signature : `NSInteger state()`<br></br>
	 * *native declaration : :28*
	 */
	abstract fun state(): Int

	/**
	 * Original signature : `void setState(NSInteger)`<br></br>
	 * *native declaration : :29*
	 */
	abstract fun setState(value: Int)

	/**
	 * Original signature : `BOOL isBordered()`<br></br>
	 * *native declaration : :30*
	 */
	abstract fun isBordered(): Boolean

	/**
	 * Original signature : `void setBordered(BOOL)`<br></br>
	 * *native declaration : :31*
	 */
	abstract fun setBordered(flag: Boolean)

	/**
	 * Original signature : `BOOL isTransparent()`<br></br>
	 * *native declaration : :32*
	 */
	abstract fun isTransparent(): Boolean

	/**
	 * Original signature : `void setTransparent(BOOL)`<br></br>
	 * *native declaration : :33*
	 */
	abstract fun setTransparent(flag: Boolean)

	/**
	 * Original signature : `void setPeriodicDelay(float, float)`<br></br>
	 * *native declaration : :34*
	 */
	abstract fun setPeriodicDelay_interval(delay: Float, interval: Float)

	/**
	 * Original signature : `void getPeriodicDelay(float*, float*)`<br></br>
	 * *native declaration : :35*
	 */
	abstract fun getPeriodicDelay_interval(delay: FloatBuffer?, interval: FloatBuffer?)

	/**
	 * Original signature : `NSString* keyEquivalent()`<br></br>
	 * *native declaration : :36*
	 */
	abstract fun keyEquivalent(): String?

	/**
	 * Original signature : `void setKeyEquivalent(NSString*)`<br></br>
	 * *native declaration : :37*
	 */
	abstract fun setKeyEquivalent(charCode: String?)

	/**
	 * Original signature : `NSUInteger keyEquivalentModifierMask()`<br></br>
	 * *native declaration : :38*
	 */
	abstract fun keyEquivalentModifierMask(): NSUInteger?

	/**
	 * Original signature : `void setKeyEquivalentModifierMask(NSUInteger)`<br></br>
	 * *native declaration : :39*
	 */
	abstract fun setKeyEquivalentModifierMask(mask: NSUInteger?)

	/**
	 * Original signature : `void highlight(BOOL)`<br></br>
	 * *native declaration : :40*
	 */
	abstract fun highlight(flag: Boolean)

	/**
	 * Original signature : `void setTitleWithMnemonic(NSString*)`<br></br>
	 * *from NSKeyboardUI native declaration : :46*
	 */
	abstract fun setTitleWithMnemonic(stringWithAmpersand: String?)

	/**
	 * Original signature : `NSAttributedString* attributedTitle()`<br></br>
	 * *from NSButtonAttributedStringMethods native declaration : :50*
	 */
	abstract fun attributedTitle(): NSAttributedString?

	/**
	 * Original signature : `void setAttributedTitle(NSAttributedString*)`<br></br>
	 * *from NSButtonAttributedStringMethods native declaration : :51*
	 */
	abstract fun setAttributedTitle(aString: NSAttributedString?)

	/**
	 * Original signature : `NSAttributedString* attributedAlternateTitle()`<br></br>
	 * *from NSButtonAttributedStringMethods native declaration : :52*
	 */
	abstract fun attributedAlternateTitle(): NSAttributedString?

	/**
	 * Original signature : `void setAttributedAlternateTitle(NSAttributedString*)`<br></br>
	 * *from NSButtonAttributedStringMethods native declaration : :53*
	 */
	abstract fun setAttributedAlternateTitle(obj: NSAttributedString?)

	/**
	 * *from NSButtonBezelStyles native declaration : :57*<br></br>
	 * Conversion Error : /// Original signature : `void setBezelStyle(null)`<br></br>
	 * - (void)setBezelStyle:(null)bezelStyle; (Argument bezelStyle cannot be converted)
	 */
	abstract fun setBezelStyle(style: Int)

	/**
	 * Original signature : `bezelStyle()`<br></br>
	 * *from NSButtonBezelStyles native declaration : :58*
	 */
	abstract fun bezelStyle(): Int

	/**
	 * Original signature : `void setAllowsMixedState(BOOL)`<br></br>
	 * *from NSButtonMixedState native declaration : :62*
	 */
	abstract fun setAllowsMixedState(flag: Boolean)

	/**
	 * Original signature : `BOOL allowsMixedState()`<br></br>
	 * *from NSButtonMixedState native declaration : :63*
	 */
	abstract fun allowsMixedState(): Boolean

	/**
	 * Original signature : `void setNextState()`<br></br>
	 * *from NSButtonMixedState native declaration : :64*
	 */
	abstract fun setNextState()

	/**
	 * Original signature : `void setShowsBorderOnlyWhileMouseInside(BOOL)`<br></br>
	 * *from NSButtonBorder native declaration : :68*
	 */
	abstract fun setShowsBorderOnlyWhileMouseInside(show: Boolean)

	/**
	 * Original signature : `BOOL showsBorderOnlyWhileMouseInside()`<br></br>
	 * *from NSButtonBorder native declaration : :69*
	 */
	abstract fun showsBorderOnlyWhileMouseInside(): Boolean

	/**
	 * Original signature : `void setSound(NSSound*)`<br></br>
	 * *from NSButtonSoundExtensions native declaration : :73*
	 */
	abstract fun setSound(aSound: com.sun.jna.Pointer?)

	/**
	 * Original signature : `NSSound* sound()`<br></br>
	 * *from NSButtonSoundExtensions native declaration : :74*
	 */
	abstract fun sound(): com.sun.jna.Pointer?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSButton", _Class::class.java)

		fun buttonWithFrame(frameRect: NSRect?): NSButton? {
			return CLASS.alloc().initWithFrame(frameRect)
		}

		const val NSMomentaryLightButton: Int = 0 // was NSMomentaryPushButton
		const val NSMomentaryPushButtonButton: Int = 1
		const val NSToggleButton: Int = 2
		const val NSSwitchButton: Int = 3
		const val NSRadioButton: Int = 4
		const val NSMomentaryChangeButton: Int = 5
		const val NSOnOffButton: Int = 6
		const val NSMomentaryPushInButton: Int = 7 // was NSMomentaryLight

		const val NSRoundedBezelStyle: Int = 1
		const val NSRegularSquareBezelStyle: Int = 2
		const val NSThickSquareBezelStyle: Int = 3
		const val NSThickerSquareBezelStyle: Int = 4
		const val NSDisclosureBezelStyle: Int = 5
		const val NSShadowlessSquareBezelStyle: Int = 6
		const val NSCircularBezelStyle: Int = 7
		const val NSTexturedSquareBezelStyle: Int = 8
		const val NSHelpButtonBezelStyle: Int = 9
		const val NSSmallSquareBezelStyle: Int = 10
		const val NSTexturedRoundedBezelStyle: Int = 11
		const val NSRoundRectBezelStyle: Int = 12
		const val NSRecessedBezelStyle: Int = 13
		const val NSRoundedDisclosureBezelStyle: Int = 14
	}
}
