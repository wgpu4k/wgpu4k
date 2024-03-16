package darwin 

import org.rococoa.ObjCClass
import org.rococoa.ObjCObjectByReference
import org.rococoa.cocoa.foundation.NSInteger
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSAttributedString.h:9</i>
abstract class NSAttributedString : NSObject() {
	interface _Class : ObjCClass {
		/**
		 * Methods to determine what types can be loaded as NSAttributedStrings.<br></br>
		 * Original signature : `NSArray* textTypes()`<br></br>
		 * *from NSAttributedStringKitAdditions native declaration : :183*
		 */
		open fun textTypes(): NSArray?

		/**
		 * Original signature : `NSArray* textUnfilteredTypes()`<br></br>
		 * *from NSAttributedStringKitAdditions native declaration : :184*
		 */
		open fun textUnfilteredTypes(): NSArray?

		/**
		 * Methods that were deprecated in Mac OS 10.5. You can now use +textTypes and +textUnfilteredTypes to get arrays of Uniform Type Identifiers (UTIs).<br></br>
		 * Original signature : `NSArray* textFileTypes()`<br></br>
		 * *from NSDeprecatedKitAdditions native declaration : :249*
		 */
		open fun textFileTypes(): NSArray?

		/**
		 * Original signature : `NSArray* textPasteboardTypes()`<br></br>
		 * *from NSDeprecatedKitAdditions native declaration : :250*
		 */
		open fun textPasteboardTypes(): NSArray?

		/**
		 * Original signature : `NSArray* textUnfilteredFileTypes()`<br></br>
		 * *from NSDeprecatedKitAdditions native declaration : :251*
		 */
		open fun textUnfilteredFileTypes(): NSArray?

		/**
		 * Original signature : `NSArray* textUnfilteredPasteboardTypes()`<br></br>
		 * *from NSDeprecatedKitAdditions native declaration : :252*
		 */
		open fun textUnfilteredPasteboardTypes(): NSArray?

		open fun alloc(): NSAttributedString
	}

	/**
	 * Original signature : `NSString* string()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSAttributedString.h:11*
	 */
	abstract fun string(): String?
	/**
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSAttributedString.h:12*<br></br>
	 * Conversion Error : /// Original signature : `NSDictionary* attributesAtIndex(NSUInteger, null)`<br></br>
	 * - (NSDictionary*)attributesAtIndex:(NSUInteger)location effectiveRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `NSUInteger length()`<br></br>
	 * *from NSExtendedAttributedString native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSAttributedString.h:18*
	 */
	abstract fun length(): NSUInteger?
	/**
	 * *from NSExtendedAttributedString native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSAttributedString.h:19*<br></br>
	 * Conversion Error : /// Original signature : `attribute(NSString*, NSUInteger, null)`<br></br>
	 * - (null)attribute:(NSString*)attrName atIndex:(NSUInteger)location effectiveRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * *from NSExtendedAttributedString native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSAttributedString.h:20*<br></br>
	 * Conversion Error : /// Original signature : `NSAttributedString* attributedSubstringFromRange(null)`<br></br>
	 * - (NSAttributedString*)attributedSubstringFromRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * *from NSExtendedAttributedString native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSAttributedString.h:22*<br></br>
	 * Conversion Error : /// Original signature : `NSDictionary* attributesAtIndex(NSUInteger, null, null)`<br></br>
	 * - (NSDictionary*)attributesAtIndex:(NSUInteger)location longestEffectiveRange:(null)range inRange:(null)rangeLimit; (Argument range cannot be converted)
	 */
	/**
	 * *from NSExtendedAttributedString native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSAttributedString.h:23*<br></br>
	 * Conversion Error : /// Original signature : `attribute(NSString*, NSUInteger, null, null)`<br></br>
	 * - (null)attribute:(NSString*)attrName atIndex:(NSUInteger)location longestEffectiveRange:(null)range inRange:(null)rangeLimit; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `BOOL isEqualToAttributedString(NSAttributedString*)`<br></br>
	 * *from NSExtendedAttributedString native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSAttributedString.h:25*
	 */
	abstract fun isEqualToAttributedString(other: NSAttributedString?): Byte

	/**
	 * Original signature : `initWithString(NSString*)`<br></br>
	 * *from NSExtendedAttributedString native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSAttributedString.h:27*
	 */
	abstract fun initWithString(str: String?): NSAttributedString?

	/**
	 * Original signature : `initWithString(String*, NSDictionary*)`<br></br>
	 * *from NSExtendedAttributedString native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSAttributedString.h:28*
	 */
	abstract fun initWithString_attributes(str: String?, attrs: NSDictionary?): NSAttributedString?

	/**
	 * Original signature : `initWithAttributedString(NSAttributedString*)`<br></br>
	 * *from NSExtendedAttributedString native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSAttributedString.h:29*
	 */
	abstract fun initWithAttributedString(attrStr: NSAttributedString?): NSAttributedString?
	/**
	 * *from NSAttributedStringKitAdditions native declaration : :156*<br></br>
	 * Conversion Error : / **<br></br>
	 * * Attributes which should be copied/pasted with "copy font".<br></br>
	 * * Original signature : `NSDictionary* fontAttributesInRange(null)`<br></br>
	 * * /<br></br>
	 * - (NSDictionary*)fontAttributesInRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * *from NSAttributedStringKitAdditions native declaration : :160*<br></br>
	 * Conversion Error : / **<br></br>
	 * * Attributes which should be copied/pasted with "copy ruler".<br></br>
	 * * Original signature : `NSDictionary* rulerAttributesInRange(null)`<br></br>
	 * * /<br></br>
	 * - (NSDictionary*)rulerAttributesInRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `BOOL containsAttachments()`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :162*
	 */
	abstract fun containsAttachments(): Byte
	/**
	 * *from NSAttributedStringKitAdditions native declaration : :166*<br></br>
	 * Conversion Error : / **<br></br>
	 * * Returns NSNotFound if no line break location found in the specified range; otherwise returns the index of the first character that should go on the NEXT line.<br></br>
	 * * Original signature : `NSUInteger lineBreakBeforeIndex(NSUInteger, null)`<br></br>
	 * * /<br></br>
	 * - (NSUInteger)lineBreakBeforeIndex:(NSUInteger)location withinRange:(null)aRange; (Argument aRange cannot be converted)
	 */
	/**
	 * *from NSAttributedStringKitAdditions native declaration : :168*<br></br>
	 * Conversion Error : /// Original signature : `NSUInteger lineBreakByHyphenatingBeforeIndex(NSUInteger, null)`<br></br>
	 * - (NSUInteger)lineBreakByHyphenatingBeforeIndex:(NSUInteger)location withinRange:(null)aRange; (Argument aRange cannot be converted)
	 */
	/**
	 * Original signature : `doubleClickAtIndex(NSUInteger)`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :171*
	 */
	abstract fun doubleClickAtIndex(location: NSUInteger?): NSObject?

	/**
	 * Original signature : `NSUInteger nextWordFromIndex(NSUInteger, BOOL)`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :172*
	 */
	abstract fun nextWordFromIndex_forward(location: NSUInteger?, isForward: Byte): NSUInteger?
	/**
	 * *from NSAttributedStringKitAdditions native declaration : :177*<br></br>
	 * Conversion Error : / **<br></br>
	 * * Returns a URL either from a link attribute or from text at the given location that appears to be a URL string, for use in automatic link detection.  The effective range is the range of the link attribute or URL string.<br></br>
	 * * Original signature : `NSURL* URLAtIndex(NSUInteger, null)`<br></br>
	 * * /<br></br>
	 * - (NSURL*)URLAtIndex:(NSUInteger)location effectiveRange:(null)effectiveRange; (Argument effectiveRange cannot be converted)
	 */
	/**
	 * Convenience methods for calculating the range of an individual text block, range of an entire table, range of a list, and the index within a list.<br></br>
	 * Original signature : `rangeOfTextBlock(NSTextBlock*, NSUInteger)`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :190*
	 */
	abstract fun rangeOfTextBlock_atIndex(block: com.sun.jna.Pointer?, location: NSUInteger?): NSObject?

	/**
	 * Original signature : `rangeOfTextTable(NSTextTable*, NSUInteger)`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :191*
	 */
	abstract fun rangeOfTextTable_atIndex(table: com.sun.jna.Pointer?, location: NSUInteger?): NSObject?

	/**
	 * Original signature : `rangeOfTextList(NSTextList*, NSUInteger)`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :192*
	 */
	abstract fun rangeOfTextList_atIndex(list: com.sun.jna.Pointer?, location: NSUInteger?): NSObject?

	/**
	 * Original signature : `NSInteger itemNumberInTextList(NSTextList*, NSUInteger)`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :193*
	 */
	abstract fun itemNumberInTextList_atIndex(list: com.sun.jna.Pointer?, location: NSUInteger?): NSInteger?

	/**
	 * These first two general methods supersede the previous versions shown below.  They take a dictionary of options to specify how the document should be loaded.  The various possible options are specified above, as NS...DocumentOption.  If NSDocumentTypeDocumentOption is specified, the document will be treated as being in the specified format.  If NSDocumentTypeDocumentOption is not specified, these methods will examine the document and do their best to load it using whatever format it seems to contain.<br></br>
	 * Original signature : `initWithURL(NSURL*, NSDictionary*, NSDictionary**, NSError**)`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :201*
	 */
	abstract fun initWithURL_options_documentAttributes_error(
		url: com.sun.jna.Pointer?,
		options: NSDictionary?,
		dict: ObjCObjectByReference?,
		error: ObjCObjectByReference?
	): NSAttributedString?

	/**
	 * Original signature : `initWithData(NSData*, NSDictionary*, NSDictionary**, NSError**)`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :202*
	 */
	abstract fun initWithData_options_documentAttributes_error(
		data: com.sun.jna.Pointer?,
		options: NSDictionary?,
		dict: ObjCObjectByReference?,
		error: ObjCObjectByReference?
	): NSAttributedString?

	/**
	 * These two superseded methods are similar to the first listed above except that they lack the options dictionary and error return arguments.  They will always attempt to determine the format from the document.<br></br>
	 * Original signature : `initWithPath(String*, NSDictionary**)`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :207*
	 */
	abstract fun initWithPath_documentAttributes(path: String?, dict: ObjCObjectByReference?): NSAttributedString?

	/**
	 * Original signature : `initWithURL(NSURL*, NSDictionary**)`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :208*
	 */
	abstract fun initWithURL_documentAttributes(
		url: com.sun.jna.Pointer?,
		dict: ObjCObjectByReference?
	): NSAttributedString?

	/**
	 * The following methods should now be considered as conveniences for various common document types.<br></br>
	 * Original signature : `initWithRTF(NSData*, NSDictionary**)`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :212*
	 */
	abstract fun initWithRTF_documentAttributes(
		data: com.sun.jna.Pointer?,
		dict: ObjCObjectByReference?
	): NSAttributedString?

	/**
	 * Original signature : `initWithRTFD(NSData*, NSDictionary**)`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :213*
	 */
	abstract fun initWithRTFD_documentAttributes(
		data: com.sun.jna.Pointer?,
		dict: ObjCObjectByReference?
	): NSAttributedString?

	/**
	 * Original signature : `initWithHTML(NSData*, NSDictionary**)`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :214*
	 */
	abstract fun initWithHTML_documentAttributes(
		data: com.sun.jna.Pointer?,
		dict: ObjCObjectByReference?
	): NSAttributedString?

	/**
	 * Original signature : `initWithHTML(NSData*, NSURL*, NSDictionary**)`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :215*
	 */
	abstract fun initWithHTML_baseURL_documentAttributes(
		data: com.sun.jna.Pointer?,
		base: com.sun.jna.Pointer?,
		dict: ObjCObjectByReference?
	): NSAttributedString?

	/**
	 * Original signature : `initWithDocFormat(NSData*, NSDictionary**)`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :217*
	 */
	abstract fun initWithDocFormat_documentAttributes(
		data: com.sun.jna.Pointer?,
		dict: ObjCObjectByReference?
	): NSAttributedString?

	/**
	 * Original signature : `initWithHTML(NSData*, NSDictionary*, NSDictionary**)`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :218*
	 */
	abstract fun initWithHTML_options_documentAttributes(
		data: com.sun.jna.Pointer?,
		options: NSDictionary?,
		dict: ObjCObjectByReference?
	): NSAttributedString?

	/**
	 * A separate method is available for initializing from an RTFD file wrapper.  No options apply in this case.<br></br>
	 * Original signature : `initWithRTFDFileWrapper(NSFileWrapper*, NSDictionary**)`<br></br>
	 * *from NSAttributedStringKitAdditions native declaration : :223*
	 */
	abstract fun initWithRTFDFileWrapper_documentAttributes(
		wrapper: com.sun.jna.Pointer?,
		dict: ObjCObjectByReference?
	): NSAttributedString?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSAttributedString", _Class::class.java)

		fun attributedString(str: String?): NSAttributedString? {
			var str = str
			if (null == str) {
				str = ""
			}
			return CLASS.alloc().initWithString(str)
		}

		fun attributedStringWithAttributes(str: String?, attrs: NSDictionary?): NSAttributedString? {
			var str = str
			if (null == str) {
				str = ""
			}
			return CLASS.alloc().initWithString_attributes(str, attrs)
		}

		/**
		 * *from NSAttributedStringKitAdditions native declaration : :230*<br></br>
		 * Conversion Error : / **<br></br>
		 * * These first two methods generalize on the more specific previous versions shown below.  They require a document attributes dict specifying at least the NSDocumentTypeDocumentAttribute to determine the format to be written.  The file wrapper method will return a directory file wrapper for those document types for which it is appropriate, otherwise a regular-file file wrapper.<br></br>
		 * * Original signature : `NSData* dataFromRange(null, NSDictionary*, NSError**)`<br></br>
		 * * /<br></br>
		 * - (NSData*)dataFromRange:(null)range documentAttributes:(NSDictionary*)dict error:(NSError**)error; (Argument range cannot be converted)
		 */
		/**
		 * *from NSAttributedStringKitAdditions native declaration : :231*<br></br>
		 * Conversion Error : /// Original signature : `NSFileWrapper* fileWrapperFromRange(null, NSDictionary*, NSError**)`<br></br>
		 * - (NSFileWrapper*)fileWrapperFromRange:(null)range documentAttributes:(NSDictionary*)dict error:(NSError**)error; (Argument range cannot be converted)
		 */
		/**
		 * *from NSAttributedStringKitAdditions native declaration : :236*<br></br>
		 * Conversion Error : / **<br></br>
		 * * The following methods should now be considered as conveniences for various common document types.  In these methods the document attributes dictionary is optional.<br></br>
		 * * Original signature : `NSData* RTFFromRange(null, NSDictionary*)`<br></br>
		 * * /<br></br>
		 * - (NSData*)RTFFromRange:(null)range documentAttributes:(NSDictionary*)dict; (Argument range cannot be converted)
		 */
		/**
		 * *from NSAttributedStringKitAdditions native declaration : :237*<br></br>
		 * Conversion Error : /// Original signature : `NSData* RTFDFromRange(null, NSDictionary*)`<br></br>
		 * - (NSData*)RTFDFromRange:(null)range documentAttributes:(NSDictionary*)dict; (Argument range cannot be converted)
		 */
		/**
		 * *from NSAttributedStringKitAdditions native declaration : :238*<br></br>
		 * Conversion Error : /// Original signature : `NSFileWrapper* RTFDFileWrapperFromRange(null, NSDictionary*)`<br></br>
		 * - (NSFileWrapper*)RTFDFileWrapperFromRange:(null)range documentAttributes:(NSDictionary*)dict; (Argument range cannot be converted)
		 */
		/**
		 * *from NSAttributedStringKitAdditions native declaration : :240*<br></br>
		 * Conversion Error : /// Original signature : `NSData* docFormatFromRange(null, NSDictionary*)`<br></br>
		 * - (NSData*)docFormatFromRange:(null)range documentAttributes:(NSDictionary*)dict; (Argument range cannot be converted)
		 */
		val FontAttributeName: String? = "NSFont"
		val ParagraphStyleAttributeName: String? = "NSParagraphStyle"
		val ForegroundColorAttributeName: String? = "NSColor"
		val UnderlineStyleAttributeName: String? = "NSUnderline"
		val SuperscriptAttributeName: String? = "NSSuperScript"
		val BackgroundColorAttributeName: String? = "NSBackgroundColor"
		val AttachmentAttributeName: String? = "NSAttachment"
		val LigatureAttributeName: String? = "NSLigature"
		val BaselineOffsetAttributeName: String? = "NSBaselineOffset"
		val KernAttributeName: String? = "NSKern"
		val LinkAttributeName: String? = "NSLink"
		val CharacterShapeAttributeName: String? = "NSCharacterShape"
		val StrokeWidthAttributeName: String? = "NSStrokeWidth"
		val StrokeColorAttributeName: String? = "NSStrokeColor"
		val UnderlineColorAttributeName: String? = "NSUnderlineColor"
		val StrikethroughStyleAttributeName: String? = "NSStrikethrough"
		val StrikethroughColorAttributeName: String? = "NSStrikethroughColor"
		val ShadowAttributeName: String? = "NSShadow"
		val ObliquenessAttributeName: String? = "NSObliqueness"
		val ExpansionAttributeName: String? = "NSExpansion"
		val CursorAttributeName: String? = "NSCursor"
		val ToolTipAttributeName: String? = "NSToolTip"
		val NSPlainTextDocumentType: String? = "NSPlainText"
		val NSRTFTextDocumentType: String? = "NSRTF"
		val NSRTFDTextDocumentType: String? = "NSRTFD"
		val NSMacSimpleTextDocumentType: String? = "NSMacSimpleText"
		val NSHTMLTextDocumentType: String? = "NSHTML"
		val NSDocFormatTextDocumentType: String? = "NSDocFormat"
		val NSWordMLTextDocumentType: String? = "NSWordML"
		const val UnderlineStyleNone: Int = 0
		const val UnderlineStyleSingle: Int = 1
		const val UnderlineStyleThick: Int = 2
		const val UnderlineStyleDouble: Int = 9
		const val UnderlinePatternSolid: Int = 0
		const val UnderlinePatternDot: Int = 256
		const val UnderlinePatternDash: Int = 512
		const val UnderlinePatternDashDot: Int = 768
		const val UnderlinePatternDashDotDot: Int = 1024
		const val UnderlineByWordMask: Int = 32768
		const val NoUnderlineStyle: Int = 0
		const val SingleUnderlineStyle: Int = 1
		const val UnderlineStrikethroughMask: Int = 16384
	}
}
