package darwin 

import org.rococoa.ObjCObjectByReference

abstract class NSFormatter : NSObject() {
	/**
	 * *native declaration : :15*<br></br>
	 * Conversion Error : /// Original signature : `NSString* stringForObjectValue(null)`<br></br>
	 * - (NSString*)stringForObjectValue:(null)obj; (Argument obj cannot be converted)
	 */
	abstract fun stringForObjectValue(obj: NSObject?): String?
	/**
	 * *native declaration : :17*<br></br>
	 * Conversion Error : /// Original signature : `NSAttributedString* attributedStringForObjectValue(null, NSDictionary*)`<br></br>
	 * - (NSAttributedString*)attributedStringForObjectValue:(null)obj withDefaultAttributes:(NSDictionary*)attrs; (Argument obj cannot be converted)
	 */
	/**
	 * *native declaration : :19*<br></br>
	 * Conversion Error : /// Original signature : `NSString* editingStringForObjectValue(null)`<br></br>
	 * - (NSString*)editingStringForObjectValue:(null)obj; (Argument obj cannot be converted)
	 */
	/**
	 * Original signature : `BOOL getObjectValue(id*, NSString*, NSString**)`<br></br>
	 * *native declaration : :21*
	 */
	abstract fun getObjectValue_forString_errorDescription(
		obj: NSObject?,
		string: String?,
		error: ObjCObjectByReference?
	): Boolean

	/**
	 * Original signature : `BOOL isPartialStringValid(NSString*, NSString**, NSString**)`<br></br>
	 * *native declaration : :23*
	 */
	abstract fun isPartialStringValid_newEditingString_errorDescription(
		partialString: String?,
		newString: ObjCObjectByReference?,
		error: ObjCObjectByReference?
	): Boolean
	/**
	 * *native declaration : :26*<br></br>
	 * Conversion Error : /// Original signature : `BOOL isPartialStringValid(NSString**, null, NSString*, null, NSString**)`<br></br>
	 * - (BOOL)isPartialStringValid:(NSString**)partialStringPtr proposedSelectedRange:(null)proposedSelRangePtr originalString:(NSString*)origString originalSelectedRange:(null)origSelRange errorDescription:(NSString**)error; (Argument proposedSelRangePtr cannot be converted)
	 */
}
