package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.CGFloat
import java.nio.FloatBuffer
import java.nio.IntBuffer

/// <i>native declaration : :71</i>
abstract class NSLayoutManager : NSObject() {
	interface Delegate {
		open fun layoutManager_didCompleteLayoutForTextContainer_atEnd(
			layoutManager: NSLayoutManager?,
			textContainer: NSObject?,
			finished: Boolean
		)
	}

	interface _Class : ObjCClass {
		open fun alloc(): NSLayoutManager
	}

	/**
	 * Original signature : `id init()`<br></br>
	 * *native declaration : :179*
	 */
	abstract fun init(): NSLayoutManager?

	/**
	 * Original signature : `NSTextStorage* textStorage()`<br></br>
	 * *native declaration : :184*
	 */
	abstract fun textStorage(): NSTextStorage?

	/**
	 * Original signature : `void setTextStorage(NSTextStorage*)`<br></br>
	 * *native declaration : :185*
	 */
	abstract fun setTextStorage(textStorage: NSTextStorage?)

	/**
	 * Original signature : `NSAttributedString* attributedString()`<br></br>
	 * *native declaration : :188*
	 */
	abstract fun attributedString(): NSAttributedString?

	/**
	 * Original signature : `void replaceTextStorage(NSTextStorage*)`<br></br>
	 * *native declaration : :191*
	 */
	abstract fun replaceTextStorage(newTextStorage: NSTextStorage?)

	/**
	 * Original signature : `NSGlyphGenerator* glyphGenerator()`<br></br>
	 * *native declaration : :195*
	 */
	abstract fun glyphGenerator(): com.sun.jna.Pointer?

	/**
	 * Original signature : `void setGlyphGenerator(NSGlyphGenerator*)`<br></br>
	 * *native declaration : :196*
	 */
	abstract fun setGlyphGenerator(glyphGenerator: com.sun.jna.Pointer?)

	/**
	 * Original signature : `NSTypesetter* typesetter()`<br></br>
	 * *native declaration : :200*
	 */
	abstract fun typesetter(): com.sun.jna.Pointer?

	/**
	 * Original signature : `void setTypesetter(NSTypesetter*)`<br></br>
	 * *native declaration : :201*
	 */
	abstract fun setTypesetter(typesetter: com.sun.jna.Pointer?)

	/**
	 * Original signature : `id delegate()`<br></br>
	 * *native declaration : :204*
	 */
	abstract fun delegate(): org.rococoa.ID?

	/**
	 * Original signature : `void setDelegate(id)`<br></br>
	 * *native declaration : :205*
	 */
	abstract fun setDelegate(delegate: org.rococoa.ID?)

	/**
	 * Original signature : `NSArray* textContainers()`<br></br>
	 * *native declaration : :210*
	 */
	abstract fun textContainers(): NSArray?

	/**
	 * Original signature : `void addTextContainer(NSTextContainer*)`<br></br>
	 * *native declaration : :212*
	 */
	abstract fun addTextContainer(container: com.sun.jna.Pointer?)

	/**
	 * Add a container to the end of the array.  Must invalidate layout of all glyphs after the previous last container (i.e., glyphs that were not previously laid out because they would not fit anywhere).<br></br>
	 * Original signature : `void insertTextContainer(NSTextContainer*, NSUInteger)`<br></br>
	 * *native declaration : :214*
	 */
	abstract fun insertTextContainer_atIndex(container: com.sun.jna.Pointer?, index: Int)

	/**
	 * Insert a container into the array before the container at index.  Must invalidate layout of all glyphs in the containers from the one previously at index to the last container.<br></br>
	 * Original signature : `void removeTextContainerAtIndex(NSUInteger)`<br></br>
	 * *native declaration : :216*
	 */
	abstract fun removeTextContainerAtIndex(index: Int)

	/**
	 * Original signature : `void textContainerChangedGeometry(NSTextContainer*)`<br></br>
	 * *native declaration : :219*
	 */
	abstract fun textContainerChangedGeometry(container: com.sun.jna.Pointer?)

	/**
	 * Original signature : `void textContainerChangedTextView(NSTextContainer*)`<br></br>
	 * *native declaration : :222*
	 */
	abstract fun textContainerChangedTextView(container: com.sun.jna.Pointer?)

	/**
	 * Original signature : `void setBackgroundLayoutEnabled(BOOL)`<br></br>
	 * *native declaration : :227*
	 */
	abstract fun setBackgroundLayoutEnabled(flag: Boolean)

	/**
	 * Original signature : `BOOL backgroundLayoutEnabled()`<br></br>
	 * *native declaration : :228*
	 */
	abstract fun backgroundLayoutEnabled(): Boolean

	/**
	 * Original signature : `void setUsesScreenFonts(BOOL)`<br></br>
	 * *native declaration : :231*
	 */
	abstract fun setUsesScreenFonts(flag: Boolean)

	/**
	 * Original signature : `BOOL usesScreenFonts()`<br></br>
	 * *native declaration : :232*
	 */
	abstract fun usesScreenFonts(): Boolean

	/**
	 * Original signature : `void setShowsInvisibleCharacters(BOOL)`<br></br>
	 * *native declaration : :235*
	 */
	abstract fun setShowsInvisibleCharacters(flag: Boolean)

	/**
	 * Original signature : `BOOL showsInvisibleCharacters()`<br></br>
	 * *native declaration : :236*
	 */
	abstract fun showsInvisibleCharacters(): Boolean

	/**
	 * Original signature : `void setShowsControlCharacters(BOOL)`<br></br>
	 * *native declaration : :239*
	 */
	abstract fun setShowsControlCharacters(flag: Boolean)

	/**
	 * Original signature : `BOOL showsControlCharacters()`<br></br>
	 * *native declaration : :240*
	 */
	abstract fun showsControlCharacters(): Boolean

	/**
	 * Original signature : `void setHyphenationFactor(float)`<br></br>
	 * *native declaration : :243*
	 */
	abstract fun setHyphenationFactor(factor: Float)

	/**
	 * Original signature : `float hyphenationFactor()`<br></br>
	 * *native declaration : :244*
	 */
	abstract fun hyphenationFactor(): Float
	/**
	 * *native declaration : :247*<br></br>
	 * Conversion Error : /// Original signature : `void setDefaultAttachmentScaling(null)`<br></br>
	 * - (void)setDefaultAttachmentScaling:(null)scaling; (Argument scaling cannot be converted)
	 */
	/**
	 * Original signature : `defaultAttachmentScaling()`<br></br>
	 * *native declaration : :248*
	 */
	abstract fun defaultAttachmentScaling(): NSObject?

	/**
	 * Original signature : `void setTypesetterBehavior(NSTypesetterBehavior)`<br></br>
	 * *native declaration : :252*
	 */
	abstract fun setTypesetterBehavior(theBehavior: Int)

	/**
	 * Original signature : `NSTypesetterBehavior typesetterBehavior()`<br></br>
	 * *native declaration : :253*
	 */
	abstract fun typesetterBehavior(): Int

	/**
	 * Original signature : `NSUInteger layoutOptions()`<br></br>
	 * *native declaration : :258*
	 */
	abstract fun layoutOptions(): Int

	/**
	 * Original signature : `void setAllowsNonContiguousLayout(BOOL)`<br></br>
	 * *native declaration : :263*
	 */
	abstract fun setAllowsNonContiguousLayout(flag: Boolean)

	/**
	 * Original signature : `BOOL allowsNonContiguousLayout()`<br></br>
	 * *native declaration : :264*
	 */
	abstract fun allowsNonContiguousLayout(): Boolean

	/**
	 * If YES, then the layout manager may perform glyph generation and layout for a given portion of the text, without having glyphs or layout for preceding portions.  The default is NO.  Turning this setting on will significantly alter which portions of the text will have glyph generation or layout performed when a given generation-causing method is invoked.  It also gives significant performance benefits, especially for large documents.<br></br>
	 * Original signature : `BOOL hasNonContiguousLayout()`<br></br>
	 * *native declaration : :266*
	 */
	abstract fun hasNonContiguousLayout(): Boolean
	/**
	 * *native declaration : :272*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :276*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :278*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :281*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :282*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :285*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :291*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :292*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :293*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :294*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * Original signature : `void ensureLayoutForTextContainer(NSTextContainer*)`<br></br>
	 * *native declaration : :295*
	 */
	abstract fun ensureLayoutForTextContainer(container: com.sun.jna.Pointer?)
	/**
	 * *native declaration : :296*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Original signature : `void insertGlyphs(const NSGlyph*, NSUInteger, NSUInteger, NSUInteger)`<br></br>
	 * *native declaration : :305*
	 */
	abstract fun insertGlyphs_length_forStartingGlyphAtIndex_characterIndex(
		glyphs: com.sun.jna.Pointer?,
		length: Int,
		glyphIndex: Int,
		charIndex: Int
	)
	/**
	 * *native declaration : :309*<br></br>
	 * Conversion Error : NSGlyph
	 */
	/**
	 * *native declaration : :312*<br></br>
	 * Conversion Error : NSGlyph
	 */
	/**
	 * *native declaration : :315*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * Original signature : `void setCharacterIndex(NSUInteger, NSUInteger)`<br></br>
	 * *native declaration : :318*
	 */
	abstract fun setCharacterIndex_forGlyphAtIndex(charIndex: Int, glyphIndex: Int)

	/**
	 * Original signature : `void setIntAttribute(NSInteger, NSInteger, NSUInteger)`<br></br>
	 * *native declaration : :321*
	 */
	abstract fun setIntAttribute_value_forGlyphAtIndex(attributeTag: Int, `val`: Int, glyphIndex: Int)
	/**
	 * *native declaration : :325*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * Original signature : `NSUInteger numberOfGlyphs()`<br></br>
	 * *native declaration : :331*
	 */
	abstract fun numberOfGlyphs(): Int
	/**
	 * *native declaration : :334*<br></br>
	 * Conversion Error : NSGlyph
	 */
	/**
	 * *native declaration : :335*<br></br>
	 * Conversion Error : NSGlyph
	 */
	/**
	 * Original signature : `BOOL isValidGlyphIndex(NSUInteger)`<br></br>
	 * *native declaration : :336*
	 */
	abstract fun isValidGlyphIndex(glyphIndex: Int): Boolean

	/**
	 * Original signature : `NSUInteger characterIndexForGlyphAtIndex(NSUInteger)`<br></br>
	 * *native declaration : :340*
	 */
	abstract fun characterIndexForGlyphAtIndex(glyphIndex: Int): Int

	/**
	 * Original signature : `NSUInteger glyphIndexForCharacterAtIndex(NSUInteger)`<br></br>
	 * *native declaration : :344*
	 */
	abstract fun glyphIndexForCharacterAtIndex(charIndex: Int): Int

	/**
	 * Original signature : `NSInteger intAttribute(NSInteger, NSUInteger)`<br></br>
	 * *native declaration : :348*
	 */
	abstract fun intAttribute_forGlyphAtIndex(attributeTag: Int, glyphIndex: Int): Int
	/**
	 * *native declaration : :351*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :353*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :357*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :364*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :367*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :370*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :373*<br></br>
	 * Conversion Error : NSPoint
	 */
	/**
	 * *native declaration : :377*<br></br>
	 * Conversion Error : /// Original signature : `void setLocations(null, NSUInteger*, NSUInteger, NSRange)`<br></br>
	 * - (void)setLocations:(null)locations startingGlyphIndexes:(NSUInteger*)glyphIndexes count:(NSUInteger)count forGlyphRange:(NSRange)glyphRange; (Argument locations cannot be converted)
	 */
	/**
	 * Original signature : `void setNotShownAttribute(BOOL, NSUInteger)`<br></br>
	 * *native declaration : :381*
	 */
	abstract fun setNotShownAttribute_forGlyphAtIndex(flag: Boolean, glyphIndex: Int)

	/**
	 * Original signature : `void setDrawsOutsideLineFragment(BOOL, NSUInteger)`<br></br>
	 * *native declaration : :384*
	 */
	abstract fun setDrawsOutsideLineFragment_forGlyphAtIndex(flag: Boolean, glyphIndex: Int)

	/**
	 * *native declaration : :387*<br></br>
	 * Conversion Error : /// Original signature : `void setAttachmentSize(null, NSRange)`<br></br>
	 * - (void)setAttachmentSize:(null)attachmentSize forGlyphRange:(NSRange)glyphRange; (Argument attachmentSize cannot be converted)
	 */
	/**
	 * Original signature : `void getFirstUnlaidCharacterIndex(NSUInteger*, NSUInteger*)`<br></br>
	 * *native declaration : :394*
	 */
	abstract fun getFirstUnlaidCharacterIndex_glyphIndex(charIndex: IntBuffer?, glyphIndex: IntBuffer?)

	/**
	 * Original signature : `NSUInteger firstUnlaidCharacterIndex()`<br></br>
	 * *native declaration : :395*
	 */
	abstract fun firstUnlaidCharacterIndex(): Int

	/**
	 * Original signature : `NSUInteger firstUnlaidGlyphIndex()`<br></br>
	 * *native declaration : :396*
	 */
	abstract fun firstUnlaidGlyphIndex(): Int
	/**
	 * *native declaration : :402*<br></br>
	 * Conversion Error : /// Original signature : `NSTextContainer* textContainerForGlyphAtIndex(NSUInteger, null)`<br></br>
	 * - (NSTextContainer*)textContainerForGlyphAtIndex:(NSUInteger)glyphIndex effectiveRange:(null)effectiveGlyphRange; (Argument effectiveGlyphRange cannot be converted)
	 */
	/**
	 * *native declaration : :405*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :408*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :411*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :415*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :416*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :417*<br></br>
	 * Conversion Error : /// Original signature : `NSTextContainer* textContainerForGlyphAtIndex(NSUInteger, null, BOOL)`<br></br>
	 * - (NSTextContainer*)textContainerForGlyphAtIndex:(NSUInteger)glyphIndex effectiveRange:(null)effectiveGlyphRange withoutAdditionalLayout:(BOOL)flag; (Argument effectiveGlyphRange cannot be converted)
	 */
	/**
	 * *native declaration : :421*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :422*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Original signature : `NSTextContainer* extraLineFragmentTextContainer()`<br></br>
	 * *native declaration : :423*
	 */
	abstract fun extraLineFragmentTextContainer(): com.sun.jna.Pointer?
	/**
	 * *native declaration : :426*<br></br>
	 * Conversion Error : NSPoint
	 */
	/**
	 * Original signature : `BOOL notShownAttributeForGlyphAtIndex(NSUInteger)`<br></br>
	 * *native declaration : :429*
	 */
	abstract fun notShownAttributeForGlyphAtIndex(glyphIndex: Int): Boolean

	/**
	 * Original signature : `BOOL drawsOutsideLineFragmentForGlyphAtIndex(NSUInteger)`<br></br>
	 * *native declaration : :432*
	 */
	abstract fun drawsOutsideLineFragmentForGlyphAtIndex(glyphIndex: Int): Boolean

	/**
	 * Original signature : `attachmentSizeForGlyphAtIndex(NSUInteger)`<br></br>
	 * *native declaration : :435*
	 */
	abstract fun attachmentSizeForGlyphAtIndex(glyphIndex: Int): NSObject?

	/**
	 * *native declaration : :441*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :442*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :443*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :444*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :446*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :447*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :455*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :458*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :463*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :466*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :469*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :470*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :474*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :477*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :478*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :481*<br></br>
	 * Conversion Error : NSPoint
	 */
	/**
	 * *native declaration : :482*<br></br>
	 * Conversion Error : NSPoint
	 */
	/**
	 * *native declaration : :483*<br></br>
	 * Conversion Error : NSPoint
	 */
	/**
	 * Original signature : `NSUInteger getLineFragmentInsertionPointsForCharacterAtIndex(NSUInteger, BOOL, BOOL, CGFloat*, NSUInteger*)`<br></br>
	 * *native declaration : :488*
	 */
	abstract fun getLineFragmentInsertionPointsForCharacterAtIndex_alternatePositions_inDisplayOrder_positions_characterIndexes(
		charIndex: Int,
		aFlag: Boolean,
		dFlag: Boolean,
		positions: FloatBuffer?,
		charIndexes: IntBuffer?
	): Int
	/**
	 * *native declaration : :494*<br></br>
	 * Conversion Error : /// Original signature : `NSDictionary* temporaryAttributesAtCharacterIndex(NSUInteger, null)`<br></br>
	 * - (NSDictionary*)temporaryAttributesAtCharacterIndex:(NSUInteger)charIndex effectiveRange:(null)effectiveCharRange; (Argument effectiveCharRange cannot be converted)
	 */
	/**
	 * *native declaration : :495*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :496*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :497*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :501*<br></br>
	 * Conversion Error : /// Original signature : `id temporaryAttribute(NSString*, NSUInteger, null)`<br></br>
	 * - (id)temporaryAttribute:(NSString*)attrName atCharacterIndex:(NSUInteger)location effectiveRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * *native declaration : :502*<br></br>
	 * Conversion Error : /// Original signature : `id temporaryAttribute(NSString*, NSUInteger, null, NSRange)`<br></br>
	 * - (id)temporaryAttribute:(NSString*)attrName atCharacterIndex:(NSUInteger)location longestEffectiveRange:(null)range inRange:(NSRange)rangeLimit; (Argument range cannot be converted)
	 */
	/**
	 * *native declaration : :503*<br></br>
	 * Conversion Error : /// Original signature : `NSDictionary* temporaryAttributesAtCharacterIndex(NSUInteger, null, NSRange)`<br></br>
	 * - (NSDictionary*)temporaryAttributesAtCharacterIndex:(NSUInteger)location longestEffectiveRange:(null)range inRange:(NSRange)rangeLimit; (Argument range cannot be converted)
	 */
	/**
	 * *native declaration : :504*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * Original signature : `NSFont* substituteFontForFont(NSFont*)`<br></br>
	 * *native declaration : :510*
	 */
	abstract fun substituteFontForFont(originalFont: com.sun.jna.Pointer?): com.sun.jna.Pointer?

	/**
	 * Original signature : `CGFloat defaultLineHeightForFont(NSFont*)`<br></br>
	 * *native declaration : :514*
	 */
	abstract fun defaultLineHeightForFont(theFont: NSFont?): CGFloat?

	/**
	 * Returns the default line height specified by the layout manager's typesetter behavior for the given font.<br></br>
	 * Original signature : `CGFloat defaultBaselineOffsetForFont(NSFont*)`<br></br>
	 * *native declaration : :516*
	 */
	abstract fun defaultBaselineOffsetForFont(theFont: NSFont?): CGFloat?

	/**
	 * Returns the default baseline offset specified by the layout manager's typesetter behavior for the given font.<br></br>
	 * Original signature : `BOOL usesFontLeading()`<br></br>
	 * *native declaration : :518*
	 */
	abstract fun usesFontLeading(): Boolean

	/**
	 * Original signature : `void setUsesFontLeading(BOOL)`<br></br>
	 * *native declaration : :519*
	 */
	abstract fun setUsesFontLeading(flag: Boolean)

	/**
	 * Original signature : `NSArray* rulerMarkersForTextView(NSTextView*, NSParagraphStyle*, NSRulerView*)`<br></br>
	 * *from NSTextViewSupport native declaration : :529*
	 */
	abstract fun rulerMarkersForTextView_paragraphStyle_ruler(
		view: com.sun.jna.Pointer?,
		style: com.sun.jna.Pointer?,
		ruler: com.sun.jna.Pointer?
	): NSArray?

	/**
	 * Original signature : `NSView* rulerAccessoryViewForTextView(NSTextView*, NSParagraphStyle*, NSRulerView*, BOOL)`<br></br>
	 * *from NSTextViewSupport native declaration : :530*
	 */
	abstract fun rulerAccessoryViewForTextView_paragraphStyle_ruler_enabled(
		view: com.sun.jna.Pointer?,
		style: com.sun.jna.Pointer?,
		ruler: com.sun.jna.Pointer?,
		isEnabled: Boolean
	): NSView?

	/**
	 * Original signature : `BOOL layoutManagerOwnsFirstResponderInWindow(NSWindow*)`<br></br>
	 * *from NSTextViewSupport native declaration : :535*
	 */
	abstract fun layoutManagerOwnsFirstResponderInWindow(window: com.sun.jna.Pointer?): Boolean

	/**
	 * Original signature : `NSTextView* firstTextView()`<br></br>
	 * *from NSTextViewSupport native declaration : :538*
	 */
	abstract fun firstTextView(): NSTextView?

	/**
	 * Original signature : `NSTextView* textViewForBeginningOfSelection()`<br></br>
	 * *from NSTextViewSupport native declaration : :540*
	 */
	abstract fun textViewForBeginningOfSelection(): NSTextView?
	/**
	 * *from NSTextViewSupport native declaration : :545*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *from NSTextViewSupport native declaration : :546*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *from NSTextViewSupport native declaration : :549*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *from NSTextViewSupport native declaration : :552*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *from NSTextViewSupport native declaration : :555*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *from NSTextViewSupport native declaration : :556*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *from NSTextViewSupport native declaration : :560*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *from NSTextViewSupport native declaration : :561*<br></br>
	 * Conversion Error : NSRange
	 */
	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSLayoutManager", _Class::class.java)

		fun layoutManager(): NSLayoutManager? {
			return CLASS.alloc().init()
		}

		/// <i>native declaration : :36</i>
		const val NSGlyphAttributeSoft: Int = 0

		/// <i>native declaration : :37</i>
		const val NSGlyphAttributeElastic: Int = 1

		/// <i>native declaration : :39</i>
		const val NSGlyphAttributeBidiLevel: Int = 2

		/// <i>native declaration : :41</i>
		const val NSGlyphAttributeInscribe: Int = 5

		/// <i>native declaration : :46</i>
		const val NSGlyphInscribeBase: Int = 0

		/// <i>native declaration : :47</i>
		const val NSGlyphInscribeBelow: Int = 1

		/// <i>native declaration : :48</i>
		const val NSGlyphInscribeAbove: Int = 2

		/// <i>native declaration : :49</i>
		const val NSGlyphInscribeOverstrike: Int = 3

		/// <i>native declaration : :50</i>
		const val NSGlyphInscribeOverBelow: Int = 4

		/// <i>native declaration : :57</i>
		const val NSTypesetterLatestBehavior: Int = -1

		/**
		 * Mac OS X versions 10.0 and 10.1 (uses NSSimpleHorizontalTypesetter)<br></br>
		 * *native declaration : :58*
		 */
		const val NSTypesetterOriginalBehavior: Int = 0

		/**
		 * 10.2 with backward compatibility layout (uses new ATS-based typestter)<br></br>
		 * *native declaration : :59*
		 */
		const val NSTypesetterBehavior_10_2_WithCompatibility: Int = 1

		/// <i>native declaration : :60</i>
		const val NSTypesetterBehavior_10_2: Int = 2

		/// <i>native declaration : :61</i>
		const val NSTypesetterBehavior_10_3: Int = 3

		/// <i>native declaration : :64</i>
		const val NSTypesetterBehavior_10_4: Int = 4
	}
}
