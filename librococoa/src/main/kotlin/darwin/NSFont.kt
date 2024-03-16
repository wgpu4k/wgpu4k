package darwin 

import org.rococoa.Foundation
import org.rococoa.ObjCClass
import org.rococoa.Rococoa
import org.rococoa.cocoa.CGFloat
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : :45</i>
abstract class NSFont : NSObject(), NSCopying {
	interface _Class : ObjCClass {
		/**
		 * Factory ********<br></br>
		 * Original signature : `NSFont* fontWithName(NSString*, CGFloat)`<br></br>
		 * *native declaration : :62*
		 */
		open fun fontWithName_size(fontName: String?, fontSize: CGFloat?): NSFont?

		/**
		 * Original signature : `NSFont* fontWithName(NSString*, const CGFloat*)`<br></br>
		 * *native declaration : :63*
		 */
		open fun fontWithName_matrix(fontName: String?, fontMatrix: Array<CGFloat?>?): NSFont?

		/**
		 * Original signature : `NSFont* fontWithName(NSString*, const CGFloat*)`<br></br>
		 * *native declaration : :63*
		 */
		open fun fontWithName_matrix(fontName: String?, fontMatrix: CGFloat?): NSFont?

		/**
		 * Instantiates an NSFont object matching fontDescriptor. If fontSize is greater than 0.0, it has precedence over NSFontSizeAttribute in fontDescriptor.<br></br>
		 * Original signature : `NSFont* fontWithDescriptor(NSFontDescriptor*, CGFloat)`<br></br>
		 * *native declaration : :67*
		 */
		open fun fontWithDescriptor_size(fontDescriptor: com.sun.jna.Pointer?, fontSize: CGFloat?): NSFont?

		/**
		 * Instantiates an NSFont object matching fontDescriptor. If textTransform is non-nil, it has precedence over NSFontMatrixAttribute in fontDescriptor.<br></br>
		 * Original signature : `NSFont* fontWithDescriptor(NSFontDescriptor*, NSAffineTransform*)`<br></br>
		 * *native declaration : :71*
		 */
		open fun fontWithDescriptor_textTransform(
			fontDescriptor: com.sun.jna.Pointer?,
			textTransform: com.sun.jna.Pointer?
		): NSFont?

		/**
		 * User font settings<br></br>
		 * Original signature : `NSFont* userFontOfSize(CGFloat)`<br></br>
		 * Aqua Application font<br></br>
		 * *native declaration : :77*
		 */
		open fun userFontOfSize(fontSize: CGFloat?): NSFont?

		/**
		 * Original signature : `NSFont* userFixedPitchFontOfSize(CGFloat)`<br></br>
		 * Aqua fixed-pitch font<br></br>
		 * *native declaration : :78*
		 */
		open fun userFixedPitchFontOfSize(fontSize: CGFloat?): NSFont?

		/**
		 * Original signature : `void setUserFont(NSFont*)`<br></br>
		 * set preference for Application font.<br></br>
		 * *native declaration : :79*
		 */
		open fun setUserFont(aFont: NSFont?)

		/**
		 * Original signature : `void setUserFixedPitchFont(NSFont*)`<br></br>
		 * set preference for fixed-pitch.<br></br>
		 * *native declaration : :80*
		 */
		open fun setUserFixedPitchFont(aFont: NSFont?)

		/**
		 * UI font settings<br></br>
		 * Original signature : `NSFont* systemFontOfSize(CGFloat)`<br></br>
		 * Aqua System font<br></br>
		 * *native declaration : :84*
		 */
		open fun systemFontOfSize(fontSize: CGFloat?): NSFont?

		/**
		 * Original signature : `NSFont* boldSystemFontOfSize(CGFloat)`<br></br>
		 * Aqua System font (emphasized)<br></br>
		 * *native declaration : :85*
		 */
		open fun boldSystemFontOfSize(fontSize: CGFloat?): NSFont?

		open fun monospacedDigitSystemFontOfSize_weight(fontSize: CGFloat?, fontWeight: CGFloat?): NSFont?

		/**
		 * Original signature : `NSFont* labelFontOfSize(CGFloat)`<br></br>
		 * Aqua label font<br></br>
		 * *native declaration : :86*
		 */
		open fun labelFontOfSize(fontSize: CGFloat?): NSFont?

		/**
		 * Original signature : `NSFont* titleBarFontOfSize(CGFloat)`<br></br>
		 * *native declaration : :88*
		 */
		open fun titleBarFontOfSize(fontSize: CGFloat?): NSFont?

		/**
		 * Original signature : `NSFont* menuFontOfSize(CGFloat)`<br></br>
		 * *native declaration : :89*
		 */
		open fun menuFontOfSize(fontSize: CGFloat?): NSFont?

		/**
		 * Original signature : `NSFont* menuBarFontOfSize(CGFloat)`<br></br>
		 * *native declaration : :91*
		 */
		open fun menuBarFontOfSize(fontSize: CGFloat?): NSFont?

		/**
		 * Original signature : `NSFont* messageFontOfSize(CGFloat)`<br></br>
		 * *native declaration : :93*
		 */
		open fun messageFontOfSize(fontSize: CGFloat?): NSFont?

		/**
		 * Original signature : `NSFont* paletteFontOfSize(CGFloat)`<br></br>
		 * *native declaration : :94*
		 */
		open fun paletteFontOfSize(fontSize: CGFloat?): NSFont?

		/**
		 * Original signature : `NSFont* toolTipsFontOfSize(CGFloat)`<br></br>
		 * *native declaration : :95*
		 */
		open fun toolTipsFontOfSize(fontSize: CGFloat?): NSFont?

		/**
		 * Original signature : `NSFont* controlContentFontOfSize(CGFloat)`<br></br>
		 * *native declaration : :96*
		 */
		open fun controlContentFontOfSize(fontSize: CGFloat?): NSFont?

		/**
		 * UI font size settings<br></br>
		 * Original signature : `CGFloat systemFontSize()`<br></br>
		 * size of the standard System font.<br></br>
		 * *native declaration : :100*
		 */
		open fun systemFontSize(): CGFloat

		/**
		 * Original signature : `CGFloat smallSystemFontSize()`<br></br>
		 * size of standard small System font.<br></br>
		 * *native declaration : :101*
		 */
		open fun smallSystemFontSize(): CGFloat

		/**
		 * Original signature : `CGFloat labelFontSize()`<br></br>
		 * size of the standard Label Font.<br></br>
		 * *native declaration : :102*
		 */
		open fun labelFontSize(): CGFloat
		/**
		 * *native declaration : :105*<br></br>
		 * Conversion Error : /// Original signature : `CGFloat systemFontSizeForControlSize(null)`<br></br>
		 * + (CGFloat)systemFontSizeForControlSize:(null)controlSize; (Argument controlSize cannot be converted)
		 */
		/**
		 * Original signature : `void useFont(NSString*)`<br></br>
		 * This is now automatically handled by Quartz.<br></br>
		 * *from NSFontDeprecated native declaration : :211*
		 */
		open fun useFont(fontName: NSFont?)

		/**
		 * Original signature : `NSArray* preferredFontNames()`<br></br>
		 * NSFontCascadeListAttribute offers more powerful font substitution management<br></br>
		 * *from NSFontDeprecated native declaration : :217*
		 */
		open fun preferredFontNames(): NSArray?

		/**
		 * Original signature : `void setPreferredFontNames(NSArray*)`<br></br>
		 * *from NSFontDeprecated native declaration : :218*
		 */
		open fun setPreferredFontNames(fontNameArray: String?)
	}

	/**
	 * Core font attribute ********<br></br>
	 * Original signature : `NSString* fontName()`<br></br>
	 * *native declaration : :109*
	 */
	abstract fun fontName(): String?

	/**
	 * Original signature : `CGFloat pointSize()`<br></br>
	 * *native declaration : :110*
	 */
	abstract fun pointSize(): CGFloat?

	/**
	 * Original signature : `const CGFloat* matrix()`<br></br>
	 * *native declaration : :111*
	 */
	abstract fun matrix(): com.sun.jna.ptr.FloatByReference?

	/**
	 * Original signature : `NSString* familyName()`<br></br>
	 * *native declaration : :112*
	 */
	abstract fun familyName(): String?

	/**
	 * Original signature : `NSString* displayName()`<br></br>
	 * *native declaration : :113*
	 */
	abstract fun displayName(): String?

	/**
	 * Original signature : `NSFontDescriptor* fontDescriptor()`<br></br>
	 * *native declaration : :115*
	 */
	abstract fun fontDescriptor(): com.sun.jna.Pointer?

	/**
	 * Original signature : `NSAffineTransform* textTransform()`<br></br>
	 * *native declaration : :118*
	 */
	abstract fun textTransform(): com.sun.jna.Pointer?

	/**
	 * Glyph coverage ********<br></br>
	 * Original signature : `NSUInteger numberOfGlyphs()`<br></br>
	 * *native declaration : :122*
	 */
	abstract fun numberOfGlyphs(): NSUInteger?

	/**
	 * Original signature : `mostCompatibleStringEncoding()`<br></br>
	 * *native declaration : :123*
	 */
	abstract fun mostCompatibleStringEncoding(): com.sun.jna.Pointer?

	/**
	 * Original signature : `NSGlyph glyphWithName(NSString*)`<br></br>
	 * *native declaration : :124*
	 */
	abstract fun glyphWithName(aName: String?): Int

	/**
	 * Original signature : `NSCharacterSet* coveredCharacterSet()`<br></br>
	 * *native declaration : :126*
	 */
	abstract fun coveredCharacterSet(): com.sun.jna.Pointer?

	/**
	 * These methods return scaled numbers.  If the font was created with a matrix, the matrix is applied automatically; otherwise the coordinates are multiplied by size.<br></br>
	 * Original signature : `boundingRectForFont()`<br></br>
	 * *native declaration : :132*
	 */
	abstract fun boundingRectForFont(): com.sun.jna.Pointer?

	/**
	 * Original signature : `maximumAdvancement()`<br></br>
	 * *native declaration : :133*
	 */
	abstract fun maximumAdvancement(): com.sun.jna.Pointer?

	/**
	 * Original signature : `CGFloat ascender()`<br></br>
	 * *native declaration : :135*
	 */
	abstract fun ascender(): CGFloat?

	/**
	 * Original signature : `CGFloat descender()`<br></br>
	 * *native declaration : :136*
	 */
	abstract fun descender(): CGFloat?

	/**
	 * Original signature : `CGFloat leading()`<br></br>
	 * *native declaration : :138*
	 */
	abstract fun leading(): CGFloat?

	/**
	 * Original signature : `CGFloat underlinePosition()`<br></br>
	 * *native declaration : :141*
	 */
	abstract fun underlinePosition(): CGFloat?

	/**
	 * Original signature : `CGFloat underlineThickness()`<br></br>
	 * *native declaration : :142*
	 */
	abstract fun underlineThickness(): CGFloat?

	/**
	 * Original signature : `CGFloat italicAngle()`<br></br>
	 * *native declaration : :143*
	 */
	abstract fun italicAngle(): CGFloat?

	/**
	 * Original signature : `CGFloat capHeight()`<br></br>
	 * *native declaration : :144*
	 */
	abstract fun capHeight(): CGFloat?

	/**
	 * Original signature : `CGFloat xHeight()`<br></br>
	 * *native declaration : :145*
	 */
	abstract fun xHeight(): CGFloat?

	/**
	 * Original signature : `BOOL isFixedPitch()`<br></br>
	 * *native declaration : :146*
	 */
	abstract fun isFixedPitch(): Boolean

	/**
	 * Glyph metrics ********<br></br>
	 * Original signature : `boundingRectForGlyph(NSGlyph)`<br></br>
	 * *native declaration : :149*
	 */
	abstract fun boundingRectForGlyph(aGlyph: Int): com.sun.jna.Pointer?

	/**
	 * Original signature : `advancementForGlyph(NSGlyph)`<br></br>
	 * *native declaration : :150*
	 */
	abstract fun advancementForGlyph(ag: Int): com.sun.jna.Pointer?
	/**
	 * *native declaration : :154*<br></br>
	 * Conversion Error : /// Original signature : `void getBoundingRects(null, const NSGlyph*, NSUInteger)`<br></br>
	 * - (void)getBoundingRects:(null)bounds forGlyphs:(const NSGlyph*)glyphs count:(NSUInteger)glyphCount; (Argument bounds cannot be converted)
	 */
	/**
	 * *native declaration : :155*<br></br>
	 * Conversion Error : /// Original signature : `void getAdvancements(null, const NSGlyph*, NSUInteger)`<br></br>
	 * - (void)getAdvancements:(null)advancements forGlyphs:(const NSGlyph*)glyphs count:(NSUInteger)glyphCount; (Argument advancements cannot be converted)
	 */
	/**
	 * *native declaration : :156*<br></br>
	 * Conversion Error : /// Original signature : `void getAdvancements(null, const void*, NSUInteger)`<br></br>
	 * - (void)getAdvancements:(null)advancements forPackedGlyphs:(const void*)packedGlyphs length:(NSUInteger)length; // only supports NSNativeShortGlyphPacking<br></br>
	 * (Argument advancements cannot be converted)
	 */
	/**
	 * NSGraphicsContext-related ********<br></br>
	 * Original signature : `void set()`<br></br>
	 * *native declaration : :160*
	 */
	abstract fun set()

	/**
	 * Original signature : `void setInContext(NSGraphicsContext*)`<br></br>
	 * *native declaration : :162*
	 */
	abstract fun setInContext(graphicsContext: com.sun.jna.Pointer?)

	/**
	 * Rendering mode ********<br></br>
	 * Original signature : `NSFont* printerFont()`<br></br>
	 * *native declaration : :166*
	 */
	abstract fun printerFont(): NSFont?

	/**
	 * Original signature : `NSFont* screenFont()`<br></br>
	 * Same as screenFontWithRenderingMode:NSFontDefaultRenderingMode<br></br>
	 * *native declaration : :167*
	 */
	abstract fun screenFont(): NSFont?

	/**
	 * Original signature : `NSFont* screenFontWithRenderingMode(NSFontRenderingMode)`<br></br>
	 * *native declaration : :169*
	 */
	abstract fun screenFontWithRenderingMode(renderingMode: Int): NSFont?

	/**
	 * Original signature : `NSFontRenderingMode renderingMode()`<br></br>
	 * *native declaration : :170*
	 */
	abstract fun renderingMode(): Int

	/**
	 * Original signature : `CGFloat widthOfString(NSString*)`<br></br>
	 * This API never returns correct value. Use NSStringDrawing API instead.<br></br>
	 * *from NSFontDeprecated native declaration : :212*
	 */
	abstract fun widthOfString(string: com.sun.jna.Pointer?): CGFloat?

	/**
	 * Original signature : `BOOL isBaseFont()`<br></br>
	 * *from NSFontDeprecated native declaration : :213*
	 */
	abstract fun isBaseFont(): Boolean

	/**
	 * Original signature : `NSDictionary* afmDictionary()`<br></br>
	 * *from NSFontDeprecated native declaration : :214*
	 */
	abstract fun afmDictionary(): NSDictionary?

	/**
	 * Original signature : `BOOL glyphIsEncoded(NSGlyph)`<br></br>
	 * Can be deduced by aGlyph < [NSFont numberOfGlyphs] since only NSNativeShortGlyphPacking is supported.<br></br>
	 * *from NSFontDeprecated native declaration : :215*
	 */
	abstract fun glyphIsEncoded(aGlyph: Int): Boolean

	/**
	 * Original signature : `CGFloat defaultLineHeightForFont()`<br></br>
	 * Use -[NSLayoutManager defaultLineHeightForFont:] instead.<br></br>
	 * *from NSFontDeprecated native declaration : :216*
	 */
	abstract fun defaultLineHeightForFont(): CGFloat?

	/**
	 * Original signature : `NSString* encodingScheme()`<br></br>
	 * *from NSFontDeprecated native declaration : :219*
	 */
	abstract fun encodingScheme(): String?

	/**
	 * Original signature : `NSMultibyteGlyphPacking glyphPacking()`<br></br>
	 * *from NSFontDeprecated native declaration : :220*
	 */
	abstract fun glyphPacking(): Int

	/**
	 * The context-sensitive inter-glyph spacing is now performed at the typesetting stage.<br></br>
	 * Original signature : `positionOfGlyph(NSGlyph, NSGlyph, BOOL*)`<br></br>
	 * *from NSFontDeprecated native declaration : :223*
	 */
	abstract fun positionOfGlyph_precededByGlyph_isNominal(
		curGlyph: Int,
		prevGlyph: Int,
		nominal: Boolean
	): com.sun.jna.Pointer?
	/**
	 * *from NSFontDeprecated native declaration : :224*<br></br>
	 * Conversion Error : /// Original signature : `NSInteger positionsForCompositeSequence(NSGlyph*, NSInteger, null)`<br></br>
	 * - (NSInteger)positionsForCompositeSequence:(NSGlyph*)someGlyphs numberOfGlyphs:(NSInteger)numGlyphs pointArray:(null)points; (Argument points cannot be converted)
	 */
	/**
	 * Original signature : `positionOfGlyph(NSGlyph, NSGlyph, BOOL*)`<br></br>
	 * *from NSFontDeprecated native declaration : :225*
	 */
	abstract fun positionOfGlyph_struckOverGlyph_metricsExist(
		curGlyph: Int,
		prevGlyph: Int,
		exist: Boolean
	): com.sun.jna.Pointer?
	/**
	 * *from NSFontDeprecated native declaration : :226*<br></br>
	 * Conversion Error : /// Original signature : `positionOfGlyph(NSGlyph, null, BOOL*)`<br></br>
	 * - (null)positionOfGlyph:(NSGlyph)aGlyph struckOverRect:(null)aRect metricsExist:(BOOL*)exist; (Argument aRect cannot be converted)
	 */
	/**
	 * *from NSFontDeprecated native declaration : :227*<br></br>
	 * Conversion Error : /// Original signature : `positionOfGlyph(NSGlyph, unichar, null)`<br></br>
	 * - (null)positionOfGlyph:(NSGlyph)aGlyph forCharacter:(unichar)aChar struckOverRect:(null)aRect; (Argument aRect cannot be converted)
	 */
	/**
	 * *from NSFontDeprecated native declaration : :228*<br></br>
	 * Conversion Error : /// Original signature : `positionOfGlyph(NSGlyph, NSGlyphRelation, NSGlyph, null, BOOL*)`<br></br>
	 * - (null)positionOfGlyph:(NSGlyph)thisGlyph withRelation:(NSGlyphRelation)rel toBaseGlyph:(NSGlyph)baseGlyph totalAdvancement:(null)adv metricsExist:(BOOL*)exist; (Argument adv cannot be converted)
	 */
	companion object {
		private val CLASS: _Class = Rococoa.createClass("NSFont", _Class::class.java)

		const val NSFontWeightUltraLight: Double = -0.80
		const val NSFontWeightThin: Double = -0.60
		const val NSFontWeightLight: Double = -0.40
		const val NSFontWeightRegular: Double = 0.00
		const val NSFontWeightMedium: Double = 0.23
		const val NSFontWeightSemibold: Double = 0.30
		const val NSFontWeightBold: Double = 0.40
		const val NSFontWeightHeavy: Double = 0.56
		const val NSFontWeightBlack: Double = 0.62

		/**
		 * User font settings<br></br>
		 * Original signature : `NSFont* userFontOfSize(CGFloat)`<br></br>
		 * Aqua Application font<br></br>
		 * *native declaration : :77*
		 */
		fun userFontOfSize(fontSize: Double): NSFont? {
			return CLASS.userFontOfSize(CGFloat(fontSize))
		}

		/**
		 * Original signature : `NSFont* userFixedPitchFontOfSize(CGFloat)`<br></br>
		 * Aqua fixed-pitch font<br></br>
		 * *native declaration : :78*
		 */
		fun userFixedPitchFontOfSize(fontSize: Double): NSFont? {
			return CLASS.userFixedPitchFontOfSize(CGFloat(fontSize))
		}

		/**
		 * UI font settings<br></br>
		 * Original signature : `NSFont* systemFontOfSize(CGFloat)`<br></br>
		 * Aqua System font<br></br>
		 * *native declaration : :84*
		 */
		fun systemFontOfSize(fontSize: Double): NSFont? {
			return CLASS.systemFontOfSize(CGFloat(fontSize))
		}

		/**
		 * Original signature : `NSFont* boldSystemFontOfSize(CGFloat)`<br></br>
		 * Aqua System font (emphasized)<br></br>
		 * *native declaration : :85*
		 */
		fun boldSystemFontOfSize(fontSize: Double): NSFont? {
			return CLASS.boldSystemFontOfSize(CGFloat(fontSize))
		}

		fun menuFontOfSize(fontSize: Double): NSFont? {
			return CLASS.menuFontOfSize(CGFloat(fontSize))
		}

		fun monospacedDigitSystemFontOfSize(fontSize: Double): NSFont? {
			if (Rococoa.cast(CLASS, NSObject::class.java).respondsToSelector(
					Foundation.selector("monospacedDigitSystemFontOfSize:weight:")
				)
			) {
				return CLASS.monospacedDigitSystemFontOfSize_weight(CGFloat(fontSize), CGFloat(NSFontWeightRegular))
			}
			return systemFontOfSize(fontSize)
		}

		fun smallSystemFontSize(): Double {
			return CLASS.smallSystemFontSize().toDouble()
		}

		fun systemFontSize(): Double {
			return CLASS.systemFontSize().toDouble()
		}

		fun labelFontSize(): Double {
			return CLASS.labelFontSize().toDouble()
		}
	}
}
