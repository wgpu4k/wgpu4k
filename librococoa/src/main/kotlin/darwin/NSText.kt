package darwin 


import org.rococoa.ID
import org.rococoa.cocoa.foundation.NSSize

// BridgeSupport v 0.017
abstract class NSText : NSView() {
	/**
	 * Original signature : `NSString* string()`<br></br>
	 * *native declaration : /Users/dkocher/null:70*
	 */
	abstract fun string(): String?

	/**
	 * Original signature : `void setString(NSString*)`<br></br>
	 * *native declaration : /Users/dkocher/null:71*
	 */
	abstract fun setString(string: String?)
	/**
	 * *native declaration : /Users/dkocher/null:73*<br></br>
	 * Conversion Error : /// Original signature : `void replaceCharactersInRange(null, NSString*)`<br></br>
	 * - (void)replaceCharactersInRange:(null)range withString:(NSString*)aString; (Argument range cannot be converted)
	 */
	/**
	 * *native declaration : /Users/dkocher/null:74*<br></br>
	 * Conversion Error : /// Original signature : `void replaceCharactersInRange(null, NSData*)`<br></br>
	 * - (void)replaceCharactersInRange:(null)range withRTF:(NSData*)rtfData; (Argument range cannot be converted)
	 */
	/**
	 * *native declaration : /Users/dkocher/null:75*<br></br>
	 * Conversion Error : /// Original signature : `void replaceCharactersInRange(null, NSData*)`<br></br>
	 * - (void)replaceCharactersInRange:(null)range withRTFD:(NSData*)rtfdData; (Argument range cannot be converted)
	 */
	/**
	 * *native declaration : /Users/dkocher/null:77*<br></br>
	 * Conversion Error : /// Original signature : `NSData* RTFFromRange(null)`<br></br>
	 * - (NSData*)RTFFromRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * *native declaration : /Users/dkocher/null:78*<br></br>
	 * Conversion Error : /// Original signature : `NSData* RTFDFromRange(null)`<br></br>
	 * - (NSData*)RTFDFromRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `BOOL writeRTFDToFile(NSString*, BOOL)`<br></br>
	 * *native declaration : /Users/dkocher/null:80*
	 */
	abstract fun writeRTFDToFile_atomically(path: String?, flag: Boolean): Boolean

	/**
	 * Original signature : `BOOL readRTFDFromFile(NSString*)`<br></br>
	 * *native declaration : /Users/dkocher/null:81*
	 */
	abstract fun readRTFDFromFile(path: String?): Boolean

	/**
	 * Original signature : `id delegate()`<br></br>
	 * *native declaration : /Users/dkocher/null:83*
	 */
	abstract fun delegate(): ID?

	/**
	 * Original signature : `void setDelegate(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:84*
	 */
	abstract fun setDelegate(anObject: ID?)

	/**
	 * Original signature : `BOOL isEditable()`<br></br>
	 * *native declaration : /Users/dkocher/null:86*
	 */
	abstract fun isEditable(): Boolean

	/**
	 * Original signature : `void setEditable(BOOL)`<br></br>
	 * *native declaration : /Users/dkocher/null:87*
	 */
	abstract fun setEditable(flag: Boolean)

	/**
	 * Original signature : `BOOL isSelectable()`<br></br>
	 * *native declaration : /Users/dkocher/null:88*
	 */
	abstract fun isSelectable(): Boolean

	/**
	 * Original signature : `void setSelectable(BOOL)`<br></br>
	 * *native declaration : /Users/dkocher/null:89*
	 */
	abstract fun setSelectable(flag: Boolean)

	/**
	 * Original signature : `BOOL isRichText()`<br></br>
	 * *native declaration : /Users/dkocher/null:90*
	 */
	abstract fun isRichText(): Boolean

	/**
	 * Original signature : `void setRichText(BOOL)`<br></br>
	 * If NO, also clears setImportsGraphics:<br></br>
	 * *native declaration : /Users/dkocher/null:91*
	 */
	abstract fun setRichText(flag: Boolean)

	/**
	 * Original signature : `BOOL importsGraphics()`<br></br>
	 * *native declaration : /Users/dkocher/null:92*
	 */
	abstract fun importsGraphics(): Boolean

	/**
	 * Original signature : `void setImportsGraphics(BOOL)`<br></br>
	 * If YES, also sets setRichText:<br></br>
	 * *native declaration : /Users/dkocher/null:93*
	 */
	abstract fun setImportsGraphics(flag: Boolean)

	/**
	 * Original signature : `BOOL isFieldEditor()`<br></br>
	 * *native declaration : /Users/dkocher/null:94*
	 */
	abstract fun isFieldEditor(): Boolean

	/**
	 * Original signature : `void setFieldEditor(BOOL)`<br></br>
	 * Indicates whether to end editing on CR, TAB, etc.<br></br>
	 * *native declaration : /Users/dkocher/null:95*
	 */
	abstract fun setFieldEditor(flag: Boolean)

	/**
	 * Original signature : `BOOL usesFontPanel()`<br></br>
	 * *native declaration : /Users/dkocher/null:96*
	 */
	abstract fun usesFontPanel(): Boolean

	/**
	 * Original signature : `void setUsesFontPanel(BOOL)`<br></br>
	 * *native declaration : /Users/dkocher/null:97*
	 */
	abstract fun setUsesFontPanel(flag: Boolean)

	/**
	 * Original signature : `BOOL drawsBackground()`<br></br>
	 * *native declaration : /Users/dkocher/null:98*
	 */
	abstract fun drawsBackground(): Boolean

	/**
	 * Original signature : `void setDrawsBackground(BOOL)`<br></br>
	 * *native declaration : /Users/dkocher/null:99*
	 */
	abstract fun setDrawsBackground(flag: Boolean)

	/**
	 * Original signature : `NSColor* backgroundColor()`<br></br>
	 * *native declaration : /Users/dkocher/null:100*
	 */
	abstract fun backgroundColor(): NSColor?

	/**
	 * Original signature : `void setBackgroundColor(NSColor*)`<br></br>
	 * *native declaration : /Users/dkocher/null:101*
	 */
	abstract fun setBackgroundColor(color: NSColor?)

	/**
	 * Original signature : `BOOL isRulerVisible()`<br></br>
	 * *native declaration : /Users/dkocher/null:103*
	 */
	abstract fun isRulerVisible(): Boolean

	/**
	 * Original signature : `selectedRange()`<br></br>
	 * *native declaration : /Users/dkocher/null:105*
	 */
	abstract fun selectedRange(): NSRange?

	/**
	 * *native declaration : /Users/dkocher/null:106*<br></br>
	 * Conversion Error : /// Original signature : `void setSelectedRange(null)`<br></br> -
	 * (void)setSelectedRange:(null)range; (Argument range cannot be converted)
	 */
	abstract fun setSelectedRange(range: NSRange?)

	/**
	 * *native declaration : /Users/dkocher/null:108*<br></br>
	 * Conversion Error : /// Original signature : `void scrollRangeToVisible(null)`<br></br>
	 * - (void)scrollRangeToVisible:(null)range; (Argument range cannot be converted)
	 */
	abstract fun scrollRangeToVisible(range: NSRange?)

	/**
	 * Original signature : `void setFont(NSFont*)`<br></br>
	 * *native declaration : /Users/dkocher/null:110*
	 */
	abstract fun setFont(obj: NSFont?)

	/**
	 * Original signature : `NSFont* font()`<br></br>
	 * *native declaration : /Users/dkocher/null:111*
	 */
	abstract fun font(): NSFont?

	/**
	 * Original signature : `void setTextColor(NSColor*)`<br></br>
	 * *native declaration : /Users/dkocher/null:112*
	 */
	abstract fun setTextColor(color: NSColor?)

	/**
	 * Original signature : `NSColor* textColor()`<br></br>
	 * *native declaration : /Users/dkocher/null:113*
	 */
	abstract fun textColor(): NSColor?

	/**
	 * Original signature : `NSTextAlignment alignment()`<br></br>
	 * *native declaration : /Users/dkocher/null:114*
	 */
	abstract fun alignment(): Int

	/**
	 * Original signature : `void setAlignment(NSTextAlignment)`<br></br>
	 * *native declaration : /Users/dkocher/null:115*
	 */
	abstract fun setAlignment(mode: Int)

	/**
	 * Original signature : `NSWritingDirection baseWritingDirection()`<br></br>
	 * *native declaration : /Users/dkocher/null:117*
	 */
	abstract fun baseWritingDirection(): Int

	/**
	 * Original signature : `void setBaseWritingDirection(NSWritingDirection)`<br></br>
	 * *native declaration : /Users/dkocher/null:118*
	 */
	abstract fun setBaseWritingDirection(writingDirection: Int)
	/**
	 * *native declaration : /Users/dkocher/null:121*<br></br>
	 * Conversion Error : /// Original signature : `void setTextColor(NSColor*, null)`<br></br>
	 * - (void)setTextColor:(NSColor*)color range:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * *native declaration : /Users/dkocher/null:122*<br></br>
	 * Conversion Error : /// Original signature : `void setFont(NSFont*, null)`<br></br>
	 * - (void)setFont:(NSFont*)font range:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `maxSize()`<br></br>
	 * *native declaration : /Users/dkocher/null:124*
	 */
	abstract fun maxSize(): NSSize?
	/**
	 * *native declaration : /Users/dkocher/null:125*<br></br>
	 * Conversion Error : /// Original signature : `void setMaxSize(null)`<br></br>
	 * - (void)setMaxSize:(null)newMaxSize; (Argument newMaxSize cannot be converted)
	 */
	/**
	 * Original signature : `minSize()`<br></br>
	 * *native declaration : /Users/dkocher/null:126*
	 */
	abstract fun minSize(): NSSize?
	/**
	 * *native declaration : /Users/dkocher/null:127*<br></br>
	 * Conversion Error : /// Original signature : `void setMinSize(null)`<br></br>
	 * - (void)setMinSize:(null)newMinSize; (Argument newMinSize cannot be converted)
	 */
	/**
	 * Original signature : `BOOL isHorizontallyResizable()`<br></br>
	 * *native declaration : /Users/dkocher/null:129*
	 */
	abstract fun isHorizontallyResizable(): Boolean

	/**
	 * Original signature : `void setHorizontallyResizable(BOOL)`<br></br>
	 * *native declaration : /Users/dkocher/null:130*
	 */
	abstract fun setHorizontallyResizable(flag: Boolean)

	/**
	 * Original signature : `BOOL isVerticallyResizable()`<br></br>
	 * *native declaration : /Users/dkocher/null:131*
	 */
	abstract fun isVerticallyResizable(): Boolean

	/**
	 * Original signature : `void setVerticallyResizable(BOOL)`<br></br>
	 * *native declaration : /Users/dkocher/null:132*
	 */
	abstract fun setVerticallyResizable(flag: Boolean)

	/**
	 * Original signature : `void sizeToFit()`<br></br>
	 * *native declaration : /Users/dkocher/null:134*
	 */
	abstract fun sizeToFit()

	/**
	 * Original signature : `void copy(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:136*
	 */
	abstract fun copy(sender: ID?)

	/**
	 * Original signature : `void copyFont(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:137*
	 */
	abstract fun copyFont(sender: ID?)

	/**
	 * Original signature : `void copyRuler(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:138*
	 */
	abstract fun copyRuler(sender: ID?)

	/**
	 * Original signature : `void cut(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:139*
	 */
	abstract fun cut(sender: ID?)

	/**
	 * Original signature : `void delete(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:140*
	 */
	abstract fun delete(sender: ID?)

	/**
	 * Original signature : `void paste(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:141*
	 */
	abstract fun paste(sender: ID?)

	/**
	 * Original signature : `void pasteFont(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:142*
	 */
	abstract fun pasteFont(sender: ID?)

	/**
	 * Original signature : `void pasteRuler(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:143*
	 */
	abstract fun pasteRuler(sender: ID?)

	/**
	 * Original signature : `void selectAll(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:144*
	 */
	abstract override fun selectAll(sender: ID?)

	/**
	 * Original signature : `void changeFont(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:145*
	 */
	abstract fun changeFont(sender: ID?)

	/**
	 * Original signature : `void alignLeft(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:146*
	 */
	abstract fun alignLeft(sender: ID?)

	/**
	 * Original signature : `void alignRight(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:147*
	 */
	abstract fun alignRight(sender: ID?)

	/**
	 * Original signature : `void alignCenter(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:148*
	 */
	abstract fun alignCenter(sender: ID?)

	/**
	 * Original signature : `void subscript(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:149*
	 */
	abstract fun subscript(sender: ID?)

	/**
	 * Original signature : `void superscript(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:150*
	 */
	abstract fun superscript(sender: ID?)

	/**
	 * Original signature : `void underline(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:151*
	 */
	abstract fun underline(sender: ID?)

	/**
	 * Original signature : `void unscript(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:152*
	 */
	abstract fun unscript(sender: ID?)

	/**
	 * Original signature : `void showGuessPanel(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:153*
	 */
	abstract fun showGuessPanel(sender: ID?)

	/**
	 * Original signature : `void checkSpelling(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:154*
	 */
	abstract fun checkSpelling(sender: ID?)

	/**
	 * Original signature : `void toggleRuler(id)`<br></br>
	 * *native declaration : /Users/dkocher/null:155*
	 */
	abstract fun toggleRuler(sender: ID?)

	companion object {
		/// <i>native declaration : /Users/dkocher/null:15</i>
		const val NSEnterCharacter: Int = 3

		/// <i>native declaration : /Users/dkocher/null:16</i>
		const val NSBackspaceCharacter: Int = 8

		/// <i>native declaration : /Users/dkocher/null:17</i>
		const val NSTabCharacter: Int = 9

		/// <i>native declaration : /Users/dkocher/null:18</i>
		const val NSNewlineCharacter: Int = 10

		/// <i>native declaration : /Users/dkocher/null:19</i>
		const val NSFormFeedCharacter: Int = 12

		/// <i>native declaration : /Users/dkocher/null:20</i>
		const val NSCarriageReturnCharacter: Int = 13

		/// <i>native declaration : /Users/dkocher/null:21</i>
		const val NSBackTabCharacter: Int = 25

		/// <i>native declaration : /Users/dkocher/null:22</i>
		const val NSDeleteCharacter: Int = 127

		/// <i>native declaration : /Users/dkocher/null:23</i>
		const val NSLineSeparatorCharacter: Int = 8232

		/// <i>native declaration : /Users/dkocher/null:24</i>
		const val NSParagraphSeparatorCharacter: Int = 8233

		/**
		 * Visually left aligned<br></br>
		 * *native declaration : /Users/dkocher/null:29*
		 */
		const val NSTextAlignmentLeft: Int = 0

		/**
		 * Visually right aligned<br></br>
		 * *native declaration : /Users/dkocher/null:30*
		 */
		const val NSRightTextAlignment: Int = 1
		const val NSTextAlignmentRight: Int = 2

		/**
		 * Visually centered<br></br>
		 * *native declaration : /Users/dkocher/null:31*
		 */
		const val NSCenterTextAlignment: Int = 2
		const val NSTextAlignmentCenter: Int = 1

		/**
		 * Fully-justified. The last line in a paragraph is natural-aligned.<br></br>
		 * *native declaration : /Users/dkocher/null:32*
		 */
		const val NSTextAlignmentJustified: Int = 3

		/**
		 * Indicates the default alignment for script<br></br>
		 * *native declaration : /Users/dkocher/null:33*
		 */
		const val NSTextAlignmentNatural: Int = 4

		/**
		 * Determines direction using the Unicode Bidi Algorithm rules P2 and P3<br></br>
		 * *native declaration : /Users/dkocher/null:40*
		 */
		const val NSWritingDirectionNatural: Int = -1

		/**
		 * Left to right writing direction<br></br>
		 * *native declaration : /Users/dkocher/null:42*
		 */
		const val NSWritingDirectionLeftToRight: Int = 0

		/**
		 * Right to left writing direction<br></br>
		 * *native declaration : /Users/dkocher/null:43*
		 */
		const val NSWritingDirectionRightToLeft: Int = 1

		/// <i>native declaration : /Users/dkocher/null:50</i>
		const val NSIllegalTextMovement: Int = 0

		/// <i>native declaration : /Users/dkocher/null:51</i>
		const val NSReturnTextMovement: Int = 16

		/// <i>native declaration : /Users/dkocher/null:52</i>
		const val NSTabTextMovement: Int = 17

		/// <i>native declaration : /Users/dkocher/null:53</i>
		const val NSBacktabTextMovement: Int = 18

		/// <i>native declaration : /Users/dkocher/null:54</i>
		const val NSLeftTextMovement: Int = 19

		/// <i>native declaration : /Users/dkocher/null:55</i>
		const val NSRightTextMovement: Int = 20

		/// <i>native declaration : /Users/dkocher/null:56</i>
		const val NSUpTextMovement: Int = 21

		/// <i>native declaration : /Users/dkocher/null:57</i>
		const val NSDownTextMovement: Int = 22

		/// <i>native declaration : /Users/dkocher/null:60</i>
		const val NSCancelTextMovement: Int = 23

		/// <i>native declaration : /Users/dkocher/null:61</i>
		const val NSOtherTextMovement: Int = 0

		val TextDidBeginEditingNotification: String? = "NSTextDidBeginEditingNotification"
		val TextDidEndEditingNotification: String? = "NSTextDidEndEditingNotification"
		val TextDidChangeNotification: String? = "NSTextDidChangeNotification"
	}
}
