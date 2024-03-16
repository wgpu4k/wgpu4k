package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.CGFloat

/// <i>native declaration : :74</i>
abstract class NSParagraphStyle : NSObject(), NSCopying {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `NSParagraphStyle* defaultParagraphStyle()`<br></br>
		 * *native declaration : :100*
		 */
		open fun defaultParagraphStyle(): NSParagraphStyle?

		/**
		 * Original signature : `defaultWritingDirectionForLanguage(NSString*)`<br></br>
		 * languageName is in ISO lang region format<br></br>
		 * *native declaration : :103*
		 */
		open fun defaultWritingDirectionForLanguage(languageName: com.sun.jna.Pointer?): com.sun.jna.Pointer?
	}

	/**
	 * Original signature : `CGFloat lineSpacing()`<br></br>
	 * "Leading": distance between the bottom of one line fragment and top of next (applied between lines in the same container). Can't be negative. This value is included in the line fragment heights in layout manager.<br></br>
	 * *native declaration : :106*
	 */
	abstract fun lineSpacing(): CGFloat?

	/**
	 * Original signature : `CGpublic abstract float paragraphSpacing()`<br></br>
	 * Distance between the bottom of this paragraph and top of next (or the beginning of its paragraphSpacingBefore, if any).<br></br>
	 * *native declaration : :107*
	 */
	abstract fun paragraphSpacing(): Float

	/**
	 * Original signature : `alignment()`<br></br>
	 * *native declaration : :108*
	 */
	abstract fun alignment(): com.sun.jna.Pointer?

	/**
	 * Original signature : `CGpublic abstract float headIndent()`<br></br>
	 * Distance from margin to front edge of paragraph<br></br>
	 * *native declaration : :112*
	 */
	abstract fun headIndent(): Float

	/**
	 * Original signature : `CGpublic abstract float tailIndent()`<br></br>
	 * Distance from margin to back edge of paragraph; if negative or 0, from other margin<br></br>
	 * *native declaration : :113*
	 */
	abstract fun tailIndent(): Float

	/**
	 * Original signature : `CGpublic abstract float firstLineHeadIndent()`<br></br>
	 * Distance from margin to edge appropriate for text direction<br></br>
	 * *native declaration : :114*
	 */
	abstract fun firstLineHeadIndent(): Float

	/**
	 * Original signature : `NSArray* tabStops()`<br></br>
	 * Distance from margin to tab stops<br></br>
	 * *native declaration : :115*
	 */
	abstract fun tabStops(): com.sun.jna.Pointer?

	/**
	 * Original signature : `CGpublic abstract float minimumLineHeight()`<br></br>
	 * Line height is the distance from bottom of descenders to top of ascenders; basically the line fragment height. Does not include lineSpacing (which is added after this computation).<br></br>
	 * *native declaration : :117*
	 */
	abstract fun minimumLineHeight(): Float

	/**
	 * Original signature : `CGpublic abstract float maximumLineHeight()`<br></br>
	 * 0 implies no maximum.<br></br>
	 * *native declaration : :118*
	 */
	abstract fun maximumLineHeight(): Float

	/**
	 * Original signature : `NSLineBreakMode lineBreakMode()`<br></br>
	 * *native declaration : :120*
	 */
	abstract fun lineBreakMode(): Int

	/**
	 * Original signature : `baseWritingDirection()`<br></br>
	 * *native declaration : :123*
	 */
	abstract fun baseWritingDirection(): com.sun.jna.Pointer?

	/**
	 * Original signature : `CGpublic abstract float lineHeightMultiple()`<br></br>
	 * Natural line height is multiplied by this factor (if positive) before being constrained by minimum and maximum line height.<br></br>
	 * *native declaration : :127*
	 */
	abstract fun lineHeightMultiple(): Float

	/**
	 * Original signature : `CGpublic abstract float paragraphSpacingBefore()`<br></br>
	 * Distance between the bottom of the previous paragraph (or the end of its paragraphSpacing, if any) and the top of this paragraph.<br></br>
	 * *native declaration : :128*
	 */
	abstract fun paragraphSpacingBefore(): Float

	/**
	 * Original signature : `CGpublic abstract float defaultTabInterval()`<br></br>
	 * Tabs after the last specified in tabStops are placed at integral multiples of this distance (if positive).<br></br>
	 * *native declaration : :129*
	 */
	abstract fun defaultTabInterval(): Float

	/**
	 * Original signature : `NSArray* textBlocks()`<br></br>
	 * Array to specify the text blocks containing the paragraph, nested from outermost to innermost.<br></br>
	 * *native declaration : :133*
	 */
	abstract fun textBlocks(): com.sun.jna.Pointer?

	/**
	 * Original signature : `NSArray* textLists()`<br></br>
	 * Array to specify the text lists containing the paragraph, nested from outermost to innermost.<br></br>
	 * *native declaration : :134*
	 */
	abstract fun textLists(): com.sun.jna.Pointer?

	/**
	 * Specifies the threshold for hyphenation.  Valid values lie between 0.0 and 1.0 inclusive.  Hyphenation will be attempted when the ratio of the text width as broken without hyphenation to the width of the line fragment is less than the hyphenation factor.  When this takes on its default value of 0.0, the layout manager's hyphenation factor is used instead.  When both are 0.0, hyphenation is disabled.<br></br>
	 * Original signature : `public abstract float hyphenationFactor()`<br></br>
	 * *native declaration : :138*
	 */
	abstract fun hyphenationFactor(): Float

	/**
	 * Specifies the threshold for using tightening as an alternative to truncation.  When the line break mode specifies truncation, the text system will attempt to tighten inter-character spacing as an alternative to truncation, provided that the ratio of the text width to the line fragment width does not exceed 1.0 + tighteningFactorForTruncation.  Otherwise the text will be truncated at a location determined by the line break mode.  The default value is 0.05.<br></br>
	 * Original signature : `public abstract float tighteningFactorForTruncation()`<br></br>
	 * *native declaration : :142*
	 */
	abstract fun tighteningFactorForTruncation(): Float

	/**
	 * Specifies whether the paragraph is to be treated as a header for purposes of HTML generation.  Should be set to 0 (the default value) if the paragraph is not a header, or from 1 through 6 if the paragraph is to be treated as a header.<br></br>
	 * Original signature : `NSInteger headerLevel()`<br></br>
	 * *native declaration : :145*
	 */
	abstract fun headerLevel(): Int

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSParagraphStyle", _Class::class.java)

		/// <i>native declaration : :12</i>
		const val NSLeftTabStopType: Int = 0

		/// <i>native declaration : :13</i>
		const val NSRightTabStopType: Int = 1

		/// <i>native declaration : :14</i>
		const val NSCenterTabStopType: Int = 2

		/// <i>native declaration : :15</i>
		const val NSDecimalTabStopType: Int = 3

		/**
		 * Wrap at word boundaries, default<br></br>
		 * *native declaration : :20*
		 */
		const val NSLineBreakByWordWrapping: Int = 0

		/**
		 * Wrap at character boundaries<br></br>
		 * *native declaration : :21*
		 */
		const val NSLineBreakByCharWrapping: Int = 1

		/**
		 * Simply clip<br></br>
		 * *native declaration : :22*
		 */
		const val NSLineBreakByClipping: Int = 2

		/**
		 * Truncate at head of line: "...wxyz"<br></br>
		 * *native declaration : :23*
		 */
		const val NSLineBreakByTruncatingHead: Int = 3

		/**
		 * Truncate at tail of line: "abcd..."<br></br>
		 * *native declaration : :24*
		 */
		const val NSLineBreakByTruncatingTail: Int = 4

		/**
		 * Truncate middle of line:  "ab...yz"<br></br>
		 * *native declaration : :25*
		 */
		const val NSLineBreakByTruncatingMiddle: Int = 5

		fun defaultParagraphStyle(): NSParagraphStyle? {
			return CLASS.defaultParagraphStyle()
		}
	}
}
