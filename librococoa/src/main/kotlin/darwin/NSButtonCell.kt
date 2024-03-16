package darwin 

import org.rococoa.ID
import org.rococoa.ObjCClass
import org.rococoa.cocoa.CGFloat
import org.rococoa.cocoa.foundation.NSUInteger

abstract class NSButtonCell : NSActionCell() {
	interface _Class : ObjCClass {
		open fun alloc(): NSButtonCell
	}

	abstract fun init(): NSButtonCell?

	/**
	 * Original signature : `NSString* title()`<br></br>
	 * *native declaration : :143*
	 */
	abstract override fun title(): String?

	/**
	 * Original signature : `void setTitle(NSString*)`<br></br>
	 * *native declaration : :144*
	 */
	abstract override fun setTitle(aString: String?)

	/**
	 * Original signature : `NSString* alternateTitle()`<br></br>
	 * *native declaration : :145*
	 */
	abstract fun alternateTitle(): String?

	/**
	 * Original signature : `void setAlternateTitle(NSString*)`<br></br>
	 * *native declaration : :146*
	 */
	abstract fun setAlternateTitle(aString: String?)

	/**
	 * Original signature : `NSImage* alternateImage()`<br></br>
	 * *native declaration : :148*
	 */
	abstract fun alternateImage(): NSImage?

	/**
	 * Original signature : `void setAlternateImage(NSImage*)`<br></br>
	 * *native declaration : :149*
	 */
	abstract fun setAlternateImage(image: NSImage?)

	/**
	 * Original signature : `imagePosition()`<br></br>
	 * *native declaration : :150*
	 */
	abstract fun imagePosition(): com.sun.jna.Pointer?
	/**
	 * *native declaration : :151*<br></br>
	 * Conversion Error : /// Original signature : `void setImagePosition(null)`<br></br>
	 * - (void)setImagePosition:(null)aPosition; (Argument aPosition cannot be converted)
	 */
	/**
	 * Original signature : `imageScaling()`<br></br>
	 * *native declaration : :153*
	 */
	abstract fun imageScaling(): com.sun.jna.Pointer?
	/**
	 * *native declaration : :154*<br></br>
	 * Conversion Error : /// Original signature : `void setImageScaling(null)`<br></br>
	 * - (void)setImageScaling:(null)scaling; (Argument scaling cannot be converted)
	 */
	/**
	 * Original signature : `NSInteger highlightsBy()`<br></br>
	 * *native declaration : :157*
	 */
	abstract fun highlightsBy(): Int

	/**
	 * Original signature : `void setHighlightsBy(NSInteger)`<br></br>
	 * *native declaration : :158*
	 */
	abstract fun setHighlightsBy(aType: Int)

	/**
	 * Original signature : `NSInteger showsStateBy()`<br></br>
	 * *native declaration : :159*
	 */
	abstract fun showsStateBy(): Int

	/**
	 * Original signature : `void setShowsStateBy(NSInteger)`<br></br>
	 * *native declaration : :160*
	 */
	abstract fun setShowsStateBy(aType: Int)

	/**
	 * Original signature : `void setButtonType(NSButtonType)`<br></br>
	 * *native declaration : :161*
	 */
	abstract fun setButtonType(aType: Int)

	/**
	 * Original signature : `BOOL isTransparent()`<br></br>
	 * *native declaration : :164*
	 */
	abstract fun isTransparent(): Boolean

	/**
	 * Original signature : `void setTransparent(BOOL)`<br></br>
	 * *native declaration : :165*
	 */
	abstract fun setTransparent(flag: Boolean)

	/**
	 * Original signature : `void setPeriodicDelay(float, float)`<br></br>
	 * *native declaration : :166*
	 */
	abstract fun setPeriodicDelay_interval(delay: Float, interval: Float)

	/**
	 * Original signature : `void setKeyEquivalent(NSString*)`<br></br>
	 * *native declaration : :169*
	 */
	abstract fun setKeyEquivalent(aKeyEquivalent: String?)

	/**
	 * Original signature : `NSUInteger keyEquivalentModifierMask()`<br></br>
	 * *native declaration : :170*
	 */
	abstract fun keyEquivalentModifierMask(): Int

	/**
	 * Original signature : `void setKeyEquivalentModifierMask(NSUInteger)`<br></br>
	 * *native declaration : :171*
	 */
	abstract fun setKeyEquivalentModifierMask(mask: Int)

	/**
	 * Original signature : `NSFont* keyEquivalentFont()`<br></br>
	 * *native declaration : :172*
	 */
	abstract fun keyEquivalentFont(): NSFont?

	/**
	 * Original signature : `void setKeyEquivalentFont(NSFont*)`<br></br>
	 * *native declaration : :173*
	 */
	abstract fun setKeyEquivalentFont(fontObj: NSFont?)

	/**
	 * Original signature : `void setKeyEquivalentFont(NSString*, CGFloat)`<br></br>
	 * *native declaration : :174*
	 */
	abstract fun setKeyEquivalentFont_size(fontName: String?, fontSize: CGFloat?)

	/**
	 * Original signature : `void performClick(id)`<br></br>
	 * Significant NSCell override, actually clicks itself.<br></br>
	 * *native declaration : :175*
	 */
	abstract override fun performClick(sender: ID?)
	/**
	 * *native declaration : :178*<br></br>
	 * Conversion Error : /// Original signature : `void drawImage(NSImage*, null, NSView*)`<br></br>
	 * - (void)drawImage:(NSImage*)image withFrame:(null)frame inView:(NSView*)controlView; (Argument frame cannot be converted)
	 */
	/**
	 * *native declaration : :179*<br></br>
	 * Conversion Error : /// Original signature : `drawTitle(NSAttributedString*, null, NSView*)`<br></br>
	 * - (null)drawTitle:(NSAttributedString*)title withFrame:(null)frame inView:(NSView*)controlView; (Argument frame cannot be converted)
	 */
	/**
	 * *native declaration : :180*<br></br>
	 * Conversion Error : /// Original signature : `void drawBezelWithFrame(null, NSView*)`<br></br>
	 * - (void)drawBezelWithFrame:(null)frame inView:(NSView*)controlView; (Argument frame cannot be converted)
	 */
	/**
	 * Original signature : `void setTitleWithMnemonic(NSString*)`<br></br>
	 * *from NSKeyboardUI native declaration : :185*
	 */
	abstract override fun setTitleWithMnemonic(stringWithAmpersand: String?)

	/**
	 * Original signature : `void setAlternateTitleWithMnemonic(NSString*)`<br></br>
	 * *from NSKeyboardUI native declaration : :186*
	 */
	abstract fun setAlternateTitleWithMnemonic(stringWithAmpersand: String?)

	/**
	 * Original signature : `void setAlternateMnemonicLocation(NSUInteger)`<br></br>
	 * *from NSKeyboardUI native declaration : :187*
	 */
	abstract fun setAlternateMnemonicLocation(location: Int)

	/**
	 * Original signature : `NSUInteger alternateMnemonicLocation()`<br></br>
	 * *from NSKeyboardUI native declaration : :188*
	 */
	abstract fun alternateMnemonicLocation(): Int

	/**
	 * Original signature : `NSString* alternateMnemonic()`<br></br>
	 * *from NSKeyboardUI native declaration : :189*
	 */
	abstract fun alternateMnemonic(): String?

	/**
	 * Original signature : `NSGradientType gradientType()`<br></br>
	 * *from NSButtonCellExtensions native declaration : :209*
	 */
	abstract fun gradientType(): Int

	/**
	 * Original signature : `void setGradientType(NSGradientType)`<br></br>
	 * *from NSButtonCellExtensions native declaration : :210*
	 */
	abstract fun setGradientType(type: Int)

	/**
	 * Radio buttons and switches use (imageDimsWhenDisabled == NO) so only their text is dimmed.<br></br>
	 * Original signature : `void setImageDimsWhenDisabled(BOOL)`<br></br>
	 * *from NSButtonCellExtensions native declaration : :214*
	 */
	abstract fun setImageDimsWhenDisabled(flag: Boolean)

	/**
	 * Original signature : `BOOL imageDimsWhenDisabled()`<br></br>
	 * *from NSButtonCellExtensions native declaration : :215*
	 */
	abstract fun imageDimsWhenDisabled(): Boolean

	/**
	 * Original signature : `void setShowsBorderOnlyWhileMouseInside(BOOL)`<br></br>
	 * *from NSButtonCellExtensions native declaration : :217*
	 */
	abstract fun setShowsBorderOnlyWhileMouseInside(show: Boolean)

	/**
	 * Original signature : `BOOL showsBorderOnlyWhileMouseInside()`<br></br>
	 * *from NSButtonCellExtensions native declaration : :218*
	 */
	abstract fun showsBorderOnlyWhileMouseInside(): Boolean

	/**
	 * Original signature : `void mouseEntered(NSEvent*)`<br></br>
	 * *from NSButtonCellExtensions native declaration : :220*
	 */
	abstract fun mouseEntered(event: NSEvent?)

	/**
	 * Original signature : `void mouseExited(NSEvent*)`<br></br>
	 * *from NSButtonCellExtensions native declaration : :221*
	 */
	abstract fun mouseExited(event: NSEvent?)

	/**
	 * Original signature : `NSColor* backgroundColor()`<br></br>
	 * *from NSButtonCellExtensions native declaration : :224*
	 */
	abstract fun backgroundColor(): NSColor?

	/**
	 * Original signature : `void setBackgroundColor(NSColor*)`<br></br>
	 * *from NSButtonCellExtensions native declaration : :225*
	 */
	abstract fun setBackgroundColor(color: NSColor?)

	/**
	 * Original signature : `NSAttributedString* attributedTitle()`<br></br>
	 * *from NSButtonCellAttributedStringMethods native declaration : :231*
	 */
	abstract fun attributedTitle(): NSAttributedString?

	/**
	 * Original signature : `void setAttributedTitle(NSAttributedString*)`<br></br>
	 * *from NSButtonCellAttributedStringMethods native declaration : :232*
	 */
	abstract fun setAttributedTitle(obj: NSAttributedString?)

	/**
	 * Original signature : `NSAttributedString* attributedAlternateTitle()`<br></br>
	 * *from NSButtonCellAttributedStringMethods native declaration : :233*
	 */
	abstract fun attributedAlternateTitle(): NSAttributedString?

	/**
	 * Original signature : `void setAttributedAlternateTitle(NSAttributedString*)`<br></br>
	 * *from NSButtonCellAttributedStringMethods native declaration : :234*
	 */
	abstract fun setAttributedAlternateTitle(obj: String?)

	/**
	 * Original signature : `void setBezelStyle(NSBezelStyle)`<br></br>
	 * *from NSButtonCellBezelStyles native declaration : :239*
	 */
	abstract fun setBezelStyle(bezelStyle: NSUInteger?)

	/**
	 * Original signature : `NSBezelStyle bezelStyle()`<br></br>
	 * *from NSButtonCellBezelStyles native declaration : :240*
	 */
	abstract fun bezelStyle(): NSUInteger?

	/**
	 * Original signature : `void setSound(NSSound*)`<br></br>
	 * *from NSButtonCellSoundExtensions native declaration : :245*
	 */
	abstract fun setSound(aSound: com.sun.jna.Pointer?)

	/**
	 * Original signature : `NSSound* sound()`<br></br>
	 * *from NSButtonCellSoundExtensions native declaration : :246*
	 */
	abstract fun sound(): com.sun.jna.Pointer?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSButtonCell", _Class::class.java)

		fun buttonCell(): NSButtonCell? {
			return CLASS.alloc().init()
		}

		/**
		 * was NSMomentaryPushButton<br></br>
		 * *native declaration : :12*
		 */
		const val NSMomentaryLightButton: Int = 0

		/// <i>native declaration : :13</i>
		const val NSPushOnPushOffButton: Int = 1

		/// <i>native declaration : :14</i>
		const val NSToggleButton: Int = 2

		/// <i>native declaration : :15</i>
		const val NSSwitchButton: Int = 3

		/// <i>native declaration : :16</i>
		const val NSRadioButton: Int = 4

		/// <i>native declaration : :17</i>
		const val NSMomentaryChangeButton: Int = 5

		/// <i>native declaration : :18</i>
		const val NSOnOffButton: Int = 6

		/**
		 * was NSMomentaryLight<br></br>
		 * *native declaration : :19*
		 */
		const val NSMomentaryPushInButton: Int = 7

		/// <i>native declaration : :24</i>
		const val NSMomentaryPushButton: Int = 0

		/// <i>native declaration : :25</i>
		const val NSMomentaryLight: Int = 7

		/// <i>native declaration : :32</i>
		const val NSRoundedBezelStyle: Int = 1

		/// <i>native declaration : :33</i>
		const val NSRegularSquareBezelStyle: Int = 2

		/// <i>native declaration : :34</i>
		const val NSThickSquareBezelStyle: Int = 3

		/// <i>native declaration : :35</i>
		const val NSThickerSquareBezelStyle: Int = 4

		/// <i>native declaration : :37</i>
		const val NSDisclosureBezelStyle: Int = 5

		/// <i>native declaration : :39</i>
		const val NSShadowlessSquareBezelStyle: Int = 6

		/// <i>native declaration : :40</i>
		const val NSCircularBezelStyle: Int = 7

		/// <i>native declaration : :42</i>
		const val NSTexturedSquareBezelStyle: Int = 8

		/// <i>native declaration : :43</i>
		const val NSHelpButtonBezelStyle: Int = 9

		/// <i>native declaration : :46</i>
		const val NSSmallSquareBezelStyle: Int = 10

		/// <i>native declaration : :47</i>
		const val NSTexturedRoundedBezelStyle: Int = 11

		/// <i>native declaration : :48</i>
		const val NSRoundRectBezelStyle: Int = 12

		/// <i>native declaration : :49</i>
		const val NSRecessedBezelStyle: Int = 13

		/// <i>native declaration : :50</i>
		const val NSRoundedDisclosureBezelStyle: Int = 14

		/// <i>native declaration : :55</i>
		const val NSSmallIconButtonBezelStyle: Int = 2

		/// <i>native declaration : :200</i>
		const val NSGradientNone: Int = 0

		/// <i>native declaration : :201</i>
		const val NSGradientConcaveWeak: Int = 1

		/// <i>native declaration : :202</i>
		const val NSGradientConcaveStrong: Int = 2

		/// <i>native declaration : :203</i>
		const val NSGradientConvexWeak: Int = 3

		/// <i>native declaration : :204</i>
		const val NSGradientConvexStrong: Int = 4
	}
}