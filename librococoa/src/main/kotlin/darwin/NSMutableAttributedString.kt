package darwin 

import org.rococoa.ObjCClass
import org.rococoa.Rococoa
import org.rococoa.cocoa.foundation.NSUInteger

abstract class NSMutableAttributedString : NSAttributedString() {
	interface _Class : ObjCClass {
		open fun alloc(): NSMutableAttributedString
	}

	/**
	 * *native declaration : :32*<br></br>
	 * Conversion Error : /// Original signature : `void replaceCharactersInRange(null, NSString*)`<br></br>
	 * - (void)replaceCharactersInRange:(null)range withString:(NSString*)str; (Argument range cannot be converted)
	 */
	abstract fun replaceCharactersInRange_withString(range: NSRange?, str: String?)

	fun replaceCharactersInRange(range: NSRange?, attrString: String?) {
		this.replaceCharactersInRange_withString(range, attrString)
	}

	/**
	 * *native declaration : :33*<br></br>
	 * Conversion Error : /// Original signature : `void setAttributes(NSDictionary*, null)`<br></br>
	 * - (void)setAttributes:(NSDictionary*)attrs range:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `NSMutableString* mutableString()`<br></br>
	 * *from NSExtendedMutableAttributedString native declaration : :39*
	 */
	abstract fun mutableString(): com.sun.jna.Pointer?

	/**
	 * *from NSExtendedMutableAttributedString native declaration : :41*<br></br>
	 * Conversion Error : /// Original signature : `void addAttribute(NSString*, null, null)`<br></br>
	 * - (void)addAttribute:(NSString*)name value:(null)value range:(null)range; (Argument value cannot be converted)
	 */
	abstract fun addAttribute_value_range(name: String?, value: NSObject?, range: NSRange?)

	fun addAttributeInRange(name: String?, value: NSObject?, range: NSRange?) {
		this.addAttribute_value_range(name, value, range)
	}

	fun addAttributeInRange(name: String?, value: String?, range: NSRange?) {
		this.addAttribute_value_range(name, NSString.stringWithString(value), range)
	}

	/**
	 * *from NSExtendedMutableAttributedString native declaration : :42*<br></br>
	 * Conversion Error : /// Original signature : `void addAttributes(NSDictionary*, null)`<br></br>
	 * - (void)addAttributes:(NSDictionary*)attrs range:(null)range; (Argument range cannot be converted)
	 */
	abstract fun addAttributes_range(attrs: NSDictionary?, range: NSRange?)

	fun addAttributesInRange(attrs: NSDictionary?, range: NSRange?) {
		this.addAttributes_range(attrs, range)
	}

	/**
	 * *from NSExtendedMutableAttributedString native declaration : :43*<br></br>
	 * Conversion Error : /// Original signature : `void removeAttribute(NSString*, null)`<br></br>
	 * - (void)removeAttribute:(NSString*)name range:(null)range; (Argument range cannot be converted)
	 */
	abstract fun removeAttribute_range(name: String?, range: NSRange?)

	fun removeAttributeInRange(name: String?, range: NSRange?) {
		this.removeAttribute_range(name, range)
	}

	/**
	 * *from NSExtendedMutableAttributedString native declaration : :45*<br></br>
	 * Conversion Error : /// Original signature : `void replaceCharactersInRange(null, NSAttributedString*)`<br></br>
	 * - (void)replaceCharactersInRange:(null)range withAttributedString:(NSAttributedString*)attrString; (Argument range cannot be converted)
	 */
	abstract fun replaceCharactersInRange_withAttributedString(range: NSRange?, attrString: NSAttributedString?)

	fun replaceCharactersInRange(range: NSRange?, attrString: NSAttributedString?) {
		this.replaceCharactersInRange_withAttributedString(range, attrString)
	}

	/**
	 * Original signature : `void insertAttributedString(NSAttributedString*, NSUInteger)`<br></br>
	 * *from NSExtendedMutableAttributedString native declaration : :46*
	 */
	abstract fun insertAttributedString_atIndex(attrString: NSAttributedString?, loc: NSUInteger?)

	/**
	 * Original signature : `void appendAttributedString(NSAttributedString*)`<br></br>
	 * *from NSExtendedMutableAttributedString native declaration : :47*
	 */
	abstract fun appendAttributedString(attrString: NSAttributedString?)
	/**
	 * *from NSExtendedMutableAttributedString native declaration : :48*<br></br>
	 * Conversion Error : /// Original signature : `void deleteCharactersInRange(null)`<br></br>
	 * - (void)deleteCharactersInRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `void setAttributedString(NSAttributedString*)`<br></br>
	 * *from NSExtendedMutableAttributedString native declaration : :49*
	 */
	abstract fun setAttributedString(attrString: NSAttributedString?)

	/**
	 * Original signature : `void beginEditing()`<br></br>
	 * *from NSExtendedMutableAttributedString native declaration : :51*
	 */
	abstract fun beginEditing()

	/**
	 * Original signature : `void endEditing()`<br></br>
	 * *from NSExtendedMutableAttributedString native declaration : :52*
	 */
	abstract fun endEditing()

	companion object {
		private val CLASS: _Class = Rococoa.createClass("NSMutableAttributedString", _Class::class.java)

		fun create(str: String?): NSMutableAttributedString? {
			var str = str
			if (null == str) {
				str = ""
			}
			return Rococoa.cast(CLASS.alloc().initWithString(str), NSMutableAttributedString::class.java)
		}

		fun create(str: String?, attrs: NSDictionary?): NSMutableAttributedString? {
			var str = str
			if (null == str) {
				str = ""
			}
			return Rococoa.cast(
				CLASS.alloc().initWithString_attributes(str, attrs),
				NSMutableAttributedString::class.java
			)
		}
	}
}
