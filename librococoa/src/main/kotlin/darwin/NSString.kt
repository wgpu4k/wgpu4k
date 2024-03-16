package darwin 

import org.rococoa.Foundation
import org.rococoa.ObjCClass
import org.rococoa.ObjCObjectByReference
import org.rococoa.cocoa.foundation.NSInteger
import org.rococoa.cocoa.foundation.NSUInteger
import java.nio.IntBuffer

/// <i>native declaration : :85</i>
abstract class NSString : NSObject(), NSCopying {
	@Override
	override fun hashCode(): Int {
		return this.toString().hashCode()
	}


	override fun equals(other: Any?): Boolean {
		if (null == other) {
			return false
		}
		if (other is NSString) {
			return this.toString().equals(other.toString())
		}
		return false
	}

	@Override
	override fun toString(): String {
		return Foundation.toString(this.id())
	}

	interface _Class : ObjCClass {
		open fun alloc(): NSString

		/**
		 * User-dependent encoding who value is derived from user's default language and potentially other factors. The use of this encoding might sometimes be needed when interpreting user documents with unknown encodings, in the absence of other hints.  This encoding should be used rarely, if at all. Note that some potential values here might result in unexpected encoding conversions of even fairly straightforward NSString content --- for instance, punctuation characters with a bidirectional encoding.<br></br>
		 * Original signature : `NSStringEncoding defaultCStringEncoding()`<br></br>
		 * Should be rarely used<br></br>
		 * *from NSStringExtensionMethods native declaration : :242*
		 */
		open fun defaultCStringEncoding(): NSUInteger?

		/**
		 * Original signature : `const NSStringEncoding* availableStringEncodings()`<br></br>
		 * *from NSStringExtensionMethods native declaration : :244*
		 */
		open fun availableStringEncodings(): com.sun.jna.ptr.IntByReference?

		/**
		 * Original signature : `NSString* localizedNameOfStringEncoding(NSStringEncoding)`<br></br>
		 * *from NSStringExtensionMethods native declaration : :245*
		 */
		open fun localizedNameOfStringEncoding(encoding: NSUInteger?): NSString?

		/**
		 * Original signature : `string()`<br></br>
		 * *from NSStringExtensionMethods native declaration : :266*
		 */
		open fun string(): String?

		/**
		 * Original signature : `stringWithString(NSString*)`<br></br>
		 * *from NSStringExtensionMethods native declaration : :267*
		 */
		open fun stringWithString(string: String?): NSString

		/**
		 * Original signature : `stringWithCharacters(const unichar*, NSUInteger)`<br></br>
		 * *from NSStringExtensionMethods native declaration : :268*
		 */
		open fun stringWithCharacters_length(characters: CharArray?, length: NSUInteger?): NSString?

		/**
		 * Original signature : `stringWithUTF8String(const char*)`<br></br>
		 * *from NSStringExtensionMethods native declaration : :269*
		 */
		open fun stringWithUTF8String(nullTerminatedCString: String?): NSString?

		/**
		 * Original signature : `stringWithFormat(NSString*, null)`<br></br>
		 * *from NSStringExtensionMethods native declaration : :270*
		 */
		open fun stringWithFormat(format: NSString?, vararg varargs: NSObject?): NSString?

		/**
		 * Original signature : `localizedStringWithFormat(NSString*, null)`<br></br>
		 * *from NSStringExtensionMethods native declaration : :271*
		 */
		open fun localizedStringWithFormat(format: NSString?, vararg varargs: NSObject?): NSString?

		/**
		 * Original signature : `stringWithCString(const char*, NSStringEncoding)`<br></br>
		 * *from NSStringExtensionMethods native declaration : :275*
		 */
		open fun stringWithCString_encoding(cString: String?, enc: NSUInteger?): NSString?

		/**
		 * Original signature : `stringWithContentsOfURL(NSURL*, NSStringEncoding, NSError**)`<br></br>
		 * *from NSStringExtensionMethods native declaration : :281*
		 */
		open fun stringWithContentsOfURL_encoding_error(
			url: NSURL?,
			enc: NSUInteger?,
			error: ObjCObjectByReference?
		): NSString?

		/**
		 * Original signature : `stringWithContentsOfFile(NSString*, NSStringEncoding, NSError**)`<br></br>
		 * *from NSStringExtensionMethods native declaration : :282*
		 */
		open fun stringWithContentsOfFile_encoding_error(
			path: NSString?,
			enc: NSUInteger?,
			error: ObjCObjectByReference?
		): NSString?

		/**
		 * Original signature : `stringWithContentsOfURL(NSURL*, NSStringEncoding*, NSError**)`<br></br>
		 * *from NSStringExtensionMethods native declaration : :288*
		 */
		open fun stringWithContentsOfURL_usedEncoding_error(
			url: NSURL?,
			enc: IntBuffer?,
			error: ObjCObjectByReference?
		): NSString?

		/**
		 * Original signature : `stringWithContentsOfFile(NSString*, NSStringEncoding*, NSError**)`<br></br>
		 * *from NSStringExtensionMethods native declaration : :289*
		 */
		open fun stringWithContentsOfFile_usedEncoding_error(
			path: NSString?,
			enc: IntBuffer?,
			error: ObjCObjectByReference?
		): NSString?

		/**
		 * Original signature : `stringWithContentsOfFile(NSString*)`<br></br>
		 * *from NSStringDeprecated native declaration : :358*
		 */
		open fun stringWithContentsOfFile(path: NSString?): NSString?

		/**
		 * Original signature : `stringWithContentsOfURL(NSURL*)`<br></br>
		 * *from NSStringDeprecated native declaration : :359*
		 */
		open fun stringWithContentsOfURL(url: NSURL?): NSString?

		/**
		 * Original signature : `stringWithCString(const char*, NSUInteger)`<br></br>
		 * *from NSStringDeprecated native declaration : :364*
		 */
		open fun stringWithCString_length(bytes: String?, length: NSUInteger?): NSString?

		/**
		 * Original signature : `stringWithCString(const char*)`<br></br>
		 * *from NSStringDeprecated native declaration : :365*
		 */
		open fun stringWithCString(bytes: String?): NSString?
	}

	/**
	 * NSString primitive (funnel) methods. A minimal subclass of NSString just needs to implement these, although we also recommend getCharacters:range:. See below for the other methods.<br></br>
	 * Original signature : `NSUInteger length()`<br></br>
	 * *native declaration : :89*
	 */
	abstract fun length(): NSUInteger?

	/**
	 * Original signature : `unichar characterAtIndex(NSUInteger)`<br></br>
	 * *native declaration : :90*
	 */
	abstract fun characterAtIndex(index: NSUInteger?): Char

	/**
	 * Original signature : `void getCharacters(unichar*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :96*
	 */
	abstract fun getCharacters(buffer: Char)
	/**
	 * *from NSStringExtensionMethods native declaration : :97*<br></br>
	 * Conversion Error : /// Original signature : `void getCharacters(unichar*, null)`<br></br>
	 * - (void)getCharacters:(unichar*)buffer range:(null)aRange; (Argument aRange cannot be converted)
	 */
	/**
	 * Original signature : `NSString* substringFromIndex(NSUInteger)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :99*
	 */
	abstract fun substringFromIndex(from: NSUInteger?): NSString?

	/**
	 * Original signature : `NSString* substringToIndex(NSUInteger)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :100*
	 */
	abstract fun substringToIndex(to: NSUInteger?): NSString?
	/**
	 * *from NSStringExtensionMethods native declaration : :101*<br></br>
	 * Conversion Error : /// Original signature : `NSString* substringWithRange(null)`<br></br>
	 * - (NSString*)substringWithRange:(null)range; // Hint: Use with rangeOfComposedCharacterSequencesForRange: to avoid breaking up composed characters<br></br>
	 * (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `compare(NSString*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :103*
	 */
	abstract fun compare(string: NSString?): com.sun.jna.Pointer?

	/**
	 * Original signature : `compare(NSString*, NSStringCompareOptions)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :104*
	 */
	abstract fun compare_options(string: NSString?, mask: Int): com.sun.jna.Pointer?
	/**
	 * *from NSStringExtensionMethods native declaration : :105*<br></br>
	 * Conversion Error : /// Original signature : `compare(NSString*, NSStringCompareOptions, null)`<br></br>
	 * - (null)compare:(NSString*)string options:(NSStringCompareOptions)mask range:(null)compareRange; (Argument compareRange cannot be converted)
	 */
	/**
	 * *from NSStringExtensionMethods native declaration : :106*<br></br>
	 * Conversion Error : /// Original signature : `compare(NSString*, NSStringCompareOptions, null, null)`<br></br>
	 * - (null)compare:(NSString*)string options:(NSStringCompareOptions)mask range:(null)compareRange locale:(null)locale; // locale arg used to be a dictionary pre-Leopard. We now accepts NSLocale. Assumes the current locale if non-nil and non-NSLocale.<br></br>
	 * (Argument compareRange cannot be converted)
	 */
	/**
	 * Original signature : `caseInsensitiveCompare(NSString*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :107*
	 */
	abstract fun caseInsensitiveCompare(string: NSString?): com.sun.jna.Pointer?

	/**
	 * Original signature : `localizedCompare(NSString*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :108*
	 */
	abstract fun localizedCompare(string: NSString?): com.sun.jna.Pointer?

	/**
	 * Original signature : `localizedCaseInsensitiveCompare(NSString*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :109*
	 */
	abstract fun localizedCaseInsensitiveCompare(string: NSString?): com.sun.jna.Pointer?

	/**
	 * Original signature : `BOOL isEqualToString(NSString*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :111*
	 */
	abstract fun isEqualToString(aString: String?): Boolean

	/**
	 * Original signature : `BOOL hasPrefix(NSString*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :113*
	 */
	abstract fun hasPrefix(aString: NSString?): Boolean

	/**
	 * Original signature : `BOOL hasSuffix(NSString*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :114*
	 */
	abstract fun hasSuffix(aString: NSString?): Boolean

	/**
	 * These methods return length==0 if the target string is not found. So, to check for containment: ([str rangeOfString:@"target"].length > 0).  Note that the length of the range returned by these methods might be different than the length of the target string, due composed characters and such.<br></br>
	 * Original signature : `rangeOfString(NSString*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :118*
	 */
	abstract fun rangeOfString(aString: NSString?): NSRange?

	/**
	 * Original signature : `rangeOfString(NSString*, NSStringCompareOptions)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :119*
	 */
	abstract fun rangeOfString_options(aString: NSString?, mask: Int): NSRange?
	/**
	 * *from NSStringExtensionMethods native declaration : :120*<br></br>
	 * Conversion Error : /// Original signature : `rangeOfString(NSString*, NSStringCompareOptions, null)`<br></br>
	 * - (null)rangeOfString:(NSString*)aString options:(NSStringCompareOptions)mask range:(null)searchRange; (Argument searchRange cannot be converted)
	 */
	/**
	 * *from NSStringExtensionMethods native declaration : :122*<br></br>
	 * Conversion Error : /// Original signature : `rangeOfString(NSString*, NSStringCompareOptions, null, NSLocale*)`<br></br>
	 * - (null)rangeOfString:(NSString*)aString options:(NSStringCompareOptions)mask range:(null)searchRange locale:(NSLocale*)locale; (Argument searchRange cannot be converted)
	 */
	/**
	 * These return the range of the first character from the set in the string, not the range of a sequence of characters.<br></br>
	 * Original signature : `rangeOfCharacterFromSet(NSCharacterSet*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :127*
	 */
	abstract fun rangeOfCharacterFromSet(aSet: com.sun.jna.Pointer?): NSRange?

	/**
	 * Original signature : `rangeOfCharacterFromSet(NSCharacterSet*, NSStringCompareOptions)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :128*
	 */
	abstract fun rangeOfCharacterFromSet_options(aSet: com.sun.jna.Pointer?, mask: Int): NSRange?
	/**
	 * *from NSStringExtensionMethods native declaration : :129*<br></br>
	 * Conversion Error : /// Original signature : `rangeOfCharacterFromSet(NSCharacterSet*, NSStringCompareOptions, null)`<br></br>
	 * - (null)rangeOfCharacterFromSet:(NSCharacterSet*)aSet options:(NSStringCompareOptions)mask range:(null)searchRange; (Argument searchRange cannot be converted)
	 */
	/**
	 * Original signature : `rangeOfComposedCharacterSequenceAtIndex(NSUInteger)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :131*
	 */
	abstract fun rangeOfComposedCharacterSequenceAtIndex(index: NSUInteger?): NSRange?
	/**
	 * *from NSStringExtensionMethods native declaration : :133*<br></br>
	 * Conversion Error : /// Original signature : `rangeOfComposedCharacterSequencesForRange(null)`<br></br>
	 * - (null)rangeOfComposedCharacterSequencesForRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `NSString* stringByAppendingString(NSString*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :136*
	 */
	abstract fun stringByAppendingString(aString: NSString?): NSString?

	/**
	 * Original signature : `NSString* stringByAppendingFormat(NSString*, null)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :137*
	 */
	abstract fun stringByAppendingFormat(format: NSString?, vararg varargs: NSObject?): NSString?

	/**
	 * The following convenience methods all skip initial space characters (whitespaceSet) and ignore trailing characters. NSScanner can be used for more "exact" parsing of numbers.<br></br>
	 * Original signature : `double doubleValue()`<br></br>
	 * *from NSStringExtensionMethods native declaration : :141*
	 */
	abstract fun doubleValue(): Double

	/**
	 * Original signature : `float floatValue()`<br></br>
	 * *from NSStringExtensionMethods native declaration : :142*
	 */
	abstract fun floatValue(): Float

	/**
	 * Original signature : `int intValue()`<br></br>
	 * *from NSStringExtensionMethods native declaration : :143*
	 */
	abstract fun intValue(): Int

	/**
	 * Original signature : `NSInteger integerValue()`<br></br>
	 * *from NSStringExtensionMethods native declaration : :145*
	 */
	abstract fun integerValue(): NSInteger?

	/**
	 * Original signature : `long long longLongValue()`<br></br>
	 * *from NSStringExtensionMethods native declaration : :146*
	 */
	abstract fun longLongValue(): Long

	/**
	 * Original signature : `BOOL boolValue()`<br></br>
	 * Skips initial space characters (whitespaceSet), or optional -/+ sign followed by zeroes. Returns YES on encountering one of "Y", "y", "T", "t", or a digit 1-9. It ignores any trailing characters.<br></br>
	 * *from NSStringExtensionMethods native declaration : :147*
	 */
	abstract fun boolValue(): Boolean

	/**
	 * Original signature : `NSArray* componentsSeparatedByString(NSString*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :150*
	 */
	abstract fun componentsSeparatedByString(separator: NSString?): NSArray?

	/**
	 * Original signature : `NSArray* componentsSeparatedByCharactersInSet(NSCharacterSet*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :152*
	 */
	abstract fun componentsSeparatedByCharactersInSet(separator: com.sun.jna.Pointer?): NSArray?

	/**
	 * Original signature : `NSString* commonPrefixWithString(NSString*, NSStringCompareOptions)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :155*
	 */
	abstract fun commonPrefixWithString_options(aString: NSString?, mask: Int): NSString?

	/**
	 * Original signature : `NSString* uppercaseString()`<br></br>
	 * *from NSStringExtensionMethods native declaration : :157*
	 */
	abstract fun uppercaseString(): NSString?

	/**
	 * Original signature : `NSString* lowercaseString()`<br></br>
	 * *from NSStringExtensionMethods native declaration : :158*
	 */
	abstract fun lowercaseString(): NSString?

	/**
	 * Original signature : `NSString* capitalizedString()`<br></br>
	 * *from NSStringExtensionMethods native declaration : :159*
	 */
	abstract fun capitalizedString(): NSString?

	/**
	 * Original signature : `NSString* stringByTrimmingCharactersInSet(NSCharacterSet*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :162*
	 */
	abstract fun stringByTrimmingCharactersInSet(set: com.sun.jna.Pointer?): NSString?

	/**
	 * Original signature : `NSString* stringByPaddingToLength(NSUInteger, NSString*, NSUInteger)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :163*
	 */
	abstract fun stringByPaddingToLength_withString_startingAtIndex(
		newLength: NSUInteger?,
		padString: NSString?,
		padIndex: NSUInteger?
	): NSString?
	/**
	 * *from NSStringExtensionMethods native declaration : :166*<br></br>
	 * Conversion Error : /// Original signature : `void getLineStart(NSUInteger*, NSUInteger*, NSUInteger*, null)`<br></br>
	 * - (void)getLineStart:(NSUInteger*)startPtr end:(NSUInteger*)lineEndPtr contentsEnd:(NSUInteger*)contentsEndPtr forRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * *from NSStringExtensionMethods native declaration : :167*<br></br>
	 * Conversion Error : /// Original signature : `lineRangeForRange(null)`<br></br>
	 * - (null)lineRangeForRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * *from NSStringExtensionMethods native declaration : :170*<br></br>
	 * Conversion Error : /// Original signature : `void getParagraphStart(NSUInteger*, NSUInteger*, NSUInteger*, null)`<br></br>
	 * - (void)getParagraphStart:(NSUInteger*)startPtr end:(NSUInteger*)parEndPtr contentsEnd:(NSUInteger*)contentsEndPtr forRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * *from NSStringExtensionMethods native declaration : :171*<br></br>
	 * Conversion Error : /// Original signature : `paragraphRangeForRange(null)`<br></br>
	 * - (null)paragraphRangeForRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `NSString* description()`<br></br>
	 * *from NSStringExtensionMethods native declaration : :174*
	 */
	abstract override fun description(): String?

	/**
	 * If two objects are equal (as determined by the isEqual: method), they must have the same hash value. This
	 * last point is particularly important if you define hash in a subclass and intend to put
	 * instances of that subclass into a collection.
	 *
	 *
	 * If a mutable object is added to a collection that uses hash values to determine the object’s
	 * position in the collection, the value returned by the hash method of the object must not change
	 * while the object is in the collection. Therefore, either the hash method must not rely on any of
	 * the object’s internal state information or you must make sure the object’s internal state information
	 * does not change while the object is in the collection. Thus, for example, a mutable dictionary can be
	 * put in a hash table but you must not change it while it is in there. (Note that it can be difficult to
	 * know whether or not a given object is in a collection.)
	 *
	 *
	 * Original signature : `NSUInteger hash()`<br></br>
	 * *from NSStringExtensionMethods native declaration : :176*
	 *
	 * @return An integer that can be used as a table address in a hash table structure.
	 */
	abstract override fun hash(): NSUInteger?

	/**
	 * Original signature : `NSStringEncoding fastestEncoding()`<br></br>
	 * Result in O(1) time; a rough estimate<br></br>
	 * *from NSStringExtensionMethods native declaration : :180*
	 */
	abstract fun fastestEncoding(): NSUInteger?

	/**
	 * Original signature : `NSStringEncoding smallestEncoding()`<br></br>
	 * Result in O(n) time; the encoding in which the string is most compact<br></br>
	 * *from NSStringExtensionMethods native declaration : :181*
	 */
	abstract fun smallestEncoding(): NSUInteger?

	/**
	 * Original signature : `NSData* dataUsingEncoding(NSStringEncoding, BOOL)`<br></br>
	 * External representation<br></br>
	 * *from NSStringExtensionMethods native declaration : :183*
	 */
	abstract fun dataUsingEncoding_allowLossyConversion(encoding: NSUInteger?, lossy: Boolean): NSData?

	/**
	 * Original signature : `NSData* dataUsingEncoding(NSStringEncoding)`<br></br>
	 * External representation<br></br>
	 * *from NSStringExtensionMethods native declaration : :184*
	 */
	abstract fun dataUsingEncoding(encoding: NSUInteger?): NSData?

	/**
	 * Original signature : `BOOL canBeConvertedToEncoding(NSStringEncoding)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :186*
	 */
	abstract fun canBeConvertedToEncoding(encoding: NSUInteger?): Boolean

	/**
	 * Methods to convert NSString to a NULL-terminated cString using the specified encoding. Note, these are the "new" cString methods, and are not deprecated like the older cString methods which do not take encoding arguments.<br></br>
	 * Original signature : `const char* cStringUsingEncoding(NSStringEncoding)`<br></br>
	 * "Autoreleased"; NULL return if encoding conversion not possible; for performance reasons, lifetime of this should not be considered longer than the lifetime of the receiving string (if the receiver string is freed, this might go invalid then, before the end of the autorelease scope)<br></br>
	 * *from NSStringExtensionMethods native declaration : :191*
	 */
	abstract fun cStringUsingEncoding(encoding: NSUInteger?): com.sun.jna.ptr.ByteByReference?

	/**
	 * Original signature : `BOOL getCString(char*, NSUInteger, NSStringEncoding)`<br></br>
	 * NO return if conversion not possible due to encoding errors or too small of a buffer. The buffer should include room for maxBufferCount bytes; this number should accomodate the expected size of the return value plus the NULL termination character, which this method adds. (So note that the maxLength passed to this method is one more than the one you would have passed to the deprecated getCString:maxLength:.)<br></br>
	 * *from NSStringExtensionMethods native declaration : :192*
	 */
	abstract fun getCString_maxLength_encoding(
		buffer: java.nio.ByteBuffer?,
		maxBufferCount: NSUInteger?,
		encoding: NSUInteger?
	): Boolean
	/**
	 * *from NSStringExtensionMethods native declaration : :205*<br></br>
	 * Conversion Error : / **<br></br>
	 * * Use this to convert string section at a time into a fixed-size buffer, without any allocations.  Does not NULL-terminate. <br></br>
	 * * buffer is the buffer to write to; if NULL, this method can be used to computed size of needed buffer.<br></br>
	 * * maxBufferCount is the length of the buffer in bytes. It's a good idea to make sure this is at least enough to hold one character's worth of conversion. <br></br>
	 * * usedBufferCount is the length of the buffer used up by the current conversion. Can be NULL.<br></br>
	 * * encoding is the encoding to convert to.<br></br>
	 * * options specifies the options to apply.<br></br>
	 * * range is the range to convert.<br></br>
	 * * leftOver is the remaining range. Can be NULL.<br></br>
	 * * YES return indicates some characters were converted. Conversion might usually stop when the buffer fills, <br></br>
	 * * but it might also stop when the conversion isn't possible due to the chosen encoding.<br></br>
	 * * Original signature : `BOOL getBytes(void*, NSUInteger, NSUInteger*, NSStringEncoding, NSStringEncodingConversionOptions, null, null)`<br></br>
	 * * /<br></br>
	 * - (BOOL)getBytes:(void*)buffer maxLength:(NSUInteger)maxBufferCount usedLength:(NSUInteger*)usedBufferCount encoding:(NSStringEncoding)encoding options:(NSStringEncodingConversionOptions)options range:(null)range remainingRange:(null)leftover; (Argument range cannot be converted)
	 */
	/**
	 * These return the maximum and exact number of bytes needed to store the receiver in the specified encoding in non-external representation. The first one is O(1), while the second one is O(n). These do not include space for a terminating null.<br></br>
	 * Original signature : `NSUInteger maximumLengthOfBytesUsingEncoding(NSStringEncoding)`<br></br>
	 * Result in O(1) time; the estimate may be way over what's needed<br></br>
	 * *from NSStringExtensionMethods native declaration : :209*
	 */
	abstract fun maximumLengthOfBytesUsingEncoding(enc: Int): NSUInteger?

	/**
	 * Original signature : `NSUInteger lengthOfBytesUsingEncoding(NSStringEncoding)`<br></br>
	 * Result in O(n) time; the result is exact<br></br>
	 * *from NSStringExtensionMethods native declaration : :210*
	 */
	abstract fun lengthOfBytesUsingEncoding(enc: Int): NSUInteger?

	/**
	 * Original signature : `NSString* decomposedStringWithCanonicalMapping()`<br></br>
	 * *from NSStringExtensionMethods native declaration : :214*
	 */
	abstract fun decomposedStringWithCanonicalMapping(): NSString?

	/**
	 * Original signature : `NSString* precomposedStringWithCanonicalMapping()`<br></br>
	 * *from NSStringExtensionMethods native declaration : :215*
	 */
	abstract fun precomposedStringWithCanonicalMapping(): NSString?

	/**
	 * Original signature : `NSString* decomposedStringWithCompatibilityMapping()`<br></br>
	 * *from NSStringExtensionMethods native declaration : :216*
	 */
	abstract fun decomposedStringWithCompatibilityMapping(): NSString?

	/**
	 * Original signature : `NSString* precomposedStringWithCompatibilityMapping()`<br></br>
	 * *from NSStringExtensionMethods native declaration : :217*
	 */
	abstract fun precomposedStringWithCompatibilityMapping(): NSString?

	/**
	 * Returns a string with the character folding options applied. theOptions is a mask of compare flags with *InsensitiveSearch suffix.<br></br>
	 * Original signature : `NSString* stringByFoldingWithOptions(NSStringCompareOptions, NSLocale*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :223*
	 */
	abstract fun stringByFoldingWithOptions_locale(options: Int, locale: com.sun.jna.Pointer?): NSString?
	/**
	 * *from NSStringExtensionMethods native declaration : :227*<br></br>
	 * Conversion Error : / **<br></br>
	 * * Replace all occurrences of the target string in the specified range with replacement. Specified compare options are used for matching target.<br></br>
	 * * Original signature : `NSString* stringByReplacingOccurrencesOfString(NSString*, NSString*, NSStringCompareOptions, null)`<br></br>
	 * * /<br></br>
	 * - (NSString*)stringByReplacingOccurrencesOfString:(NSString*)target withString:(NSString*)replacement options:(NSStringCompareOptions)options range:(null)searchRange; (Argument searchRange cannot be converted)
	 */
	/**
	 * Replace all occurrences of the target string with replacement. Invokes the above method with 0 options and range of the whole string.<br></br>
	 * Original signature : `NSString* stringByReplacingOccurrencesOfString(NSString*, NSString*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :231*
	 */
	abstract fun stringByReplacingOccurrencesOfString_withString(target: NSString?, replacement: NSString?): NSString?
	/**
	 * *from NSStringExtensionMethods native declaration : :235*<br></br>
	 * Conversion Error : / **<br></br>
	 * * Replace characters in range with the specified string, returning new string.<br></br>
	 * * Original signature : `NSString* stringByReplacingCharactersInRange(null, NSString*)`<br></br>
	 * * /<br></br>
	 * - (NSString*)stringByReplacingCharactersInRange:(null)range withString:(NSString*)replacement; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `const char* UTF8String()`<br></br>
	 * Convenience to return null-terminated UTF8 representation<br></br>
	 * *from NSStringExtensionMethods native declaration : :238*
	 */
	abstract fun UTF8String(): com.sun.jna.ptr.ByteByReference?

	/**
	 * In general creation methods in NSString do not apply to subclassers, as subclassers are assumed to provide their own init methods which create the string in the way the subclass wishes.  Designated initializers of NSString are thus init and initWithCoder:.<br></br>
	 * Original signature : `init()`<br></br>
	 * *from NSStringExtensionMethods native declaration : :251*
	 */
	abstract fun init(): NSString?

	/**
	 * Original signature : `initWithCharactersNoCopy(unichar*, NSUInteger, BOOL)`<br></br>
	 * "NoCopy" is a hint<br></br>
	 * *from NSStringExtensionMethods native declaration : :252*
	 */
	abstract fun initWithCharactersNoCopy_length_freeWhenDone(
		characters: Char,
		length: NSUInteger?,
		freeBuffer: Boolean
	): NSString?

	/**
	 * Original signature : `initWithCharacters(const unichar*, NSUInteger)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :253*
	 */
	abstract fun initWithCharacters_length(characters: CharArray?, length: NSUInteger?): NSString?

	/**
	 * Original signature : `initWithUTF8String(const char*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :254*
	 */
	abstract fun initWithUTF8String(nullTerminatedCString: String?): NSString?

	/**
	 * Original signature : `initWithString(NSString*)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :255*
	 */
	abstract fun initWithString(aString: String?): NSString

	/**
	 * Original signature : `initWithFormat(NSString*, null)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :256*
	 */
	abstract fun initWithFormat(format: String?, vararg varargs: NSObject?): NSString?
	/**
	 * *from NSStringExtensionMethods native declaration : :257*<br></br>
	 * Conversion Error : /// Original signature : `initWithFormat(NSString*, null)`<br></br>
	 * - (null)initWithFormat:(NSString*)format arguments:(null)argList; (Argument argList cannot be converted)
	 */
	/**
	 * *from NSStringExtensionMethods native declaration : :258*<br></br>
	 * Conversion Error : /// Original signature : `initWithFormat(NSString*, null, null)`<br></br>
	 * - (null)initWithFormat:(NSString*)format locale:(null)locale, ...; (Argument locale cannot be converted)
	 */
	/**
	 * *from NSStringExtensionMethods native declaration : :259*<br></br>
	 * Conversion Error : /// Original signature : `initWithFormat(NSString*, null, null)`<br></br>
	 * - (null)initWithFormat:(NSString*)format locale:(null)locale arguments:(null)argList; (Argument locale cannot be converted)
	 */
	/**
	 * Original signature : `initWithData(NSData*, NSStringEncoding)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :260*
	 */
	abstract fun initWithData_encoding(data: NSData?, encoding: NSUInteger?): NSString?

	/**
	 * Original signature : `initWithBytes(const void*, NSUInteger, NSStringEncoding)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :261*
	 */
	abstract fun initWithBytes_length_encoding(
		bytes: com.sun.jna.Pointer?,
		len: NSUInteger?,
		encoding: NSUInteger?
	): NSString?

	/**
	 * Original signature : `initWithBytesNoCopy(void*, NSUInteger, NSStringEncoding, BOOL)`<br></br>
	 * "NoCopy" is a hint<br></br>
	 * *from NSStringExtensionMethods native declaration : :263*
	 */
	abstract fun initWithBytesNoCopy_length_encoding_freeWhenDone(
		bytes: com.sun.jna.Pointer?,
		len: NSUInteger?,
		encoding: NSUInteger?,
		freeBuffer: Boolean
	): NSString?

	/**
	 * Original signature : `initWithCString(const char*, NSStringEncoding)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :274*
	 */
	abstract fun initWithCString_encoding(nullTerminatedCString: String?, encoding: NSUInteger?): NSString?

	/**
	 * These use the specified encoding.  If nil is returned, the optional error return indicates problem that was encountered (for instance, file system or encoding errors).<br></br>
	 * Original signature : `initWithContentsOfURL(NSURL*, NSStringEncoding, NSError**)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :279*
	 */
	abstract fun initWithContentsOfURL_encoding_error(
		url: NSURL?,
		enc: NSUInteger?,
		error: ObjCObjectByReference?
	): NSString?

	/**
	 * Original signature : `initWithContentsOfFile(NSString*, NSStringEncoding, NSError**)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :280*
	 */
	abstract fun initWithContentsOfFile_encoding_error(
		path: NSString?,
		enc: NSUInteger?,
		error: ObjCObjectByReference?
	): NSString?

	/**
	 * These try to determine the encoding, and return the encoding which was used.  Note that these methods might get "smarter" in subsequent releases of the system, and use additional techniques for recognizing encodings. If nil is returned, the optional error return indicates problem that was encountered (for instance, file system or encoding errors).<br></br>
	 * Original signature : `initWithContentsOfURL(NSURL*, NSStringEncoding*, NSError**)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :286*
	 */
	abstract fun initWithContentsOfURL_usedEncoding_error(
		url: NSURL?,
		enc: IntBuffer?,
		error: ObjCObjectByReference?
	): NSString?

	/**
	 * Original signature : `initWithContentsOfFile(NSString*, NSStringEncoding*, NSError**)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :287*
	 */
	abstract fun initWithContentsOfFile_usedEncoding_error(
		path: NSString?,
		enc: IntBuffer?,
		error: ObjCObjectByReference?
	): NSString?

	/**
	 * Write to specified url or path using the specified encoding.  The optional error return is to indicate file system or encoding errors.<br></br>
	 * Original signature : `BOOL writeToURL(NSURL*, BOOL, NSStringEncoding, NSError**)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :293*
	 */
	abstract fun writeToURL_atomically_encoding_error(
		url: NSURL?,
		useAuxiliaryFile: Boolean,
		enc: NSUInteger?,
		error: ObjCObjectByReference?
	): Boolean

	/**
	 * Original signature : `BOOL writeToFile(NSString*, BOOL, NSStringEncoding, NSError**)`<br></br>
	 * *from NSStringExtensionMethods native declaration : :294*
	 */
	abstract fun writeToFile_atomically_encoding_error(
		path: NSString?,
		useAuxiliaryFile: Boolean,
		enc: NSUInteger?,
		error: ObjCObjectByReference?
	): Boolean

	/**
	 * Original signature : `propertyList()`<br></br>
	 * *from NSExtendedStringPropertyListParsing native declaration : :335*
	 */
	abstract fun propertyList(): com.sun.jna.Pointer?

	/**
	 * Original signature : `NSDictionary* propertyListFromStringsFileFormat()`<br></br>
	 * *from NSExtendedStringPropertyListParsing native declaration : :336*
	 */
	abstract fun propertyListFromStringsFileFormat(): NSDictionary?

	/**
	 * The methods in this category are deprecated and will be removed from this header file in the near future. These methods use [NSString defaultCStringEncoding] as the encoding to convert to, which means the results depend on the user's language and potentially other settings. This might be appropriate in some cases, but often these methods are misused, resulting in issues when running in languages other then English. UTF8String in general is a much better choice when converting arbitrary NSStrings into 8-bit representations. Additional potential replacement methods are being introduced in NSString as appropriate.<br></br>
	 * Original signature : `const char* cString()`<br></br>
	 * *from NSStringDeprecated native declaration : :346*
	 */
	abstract fun cString(): com.sun.jna.ptr.ByteByReference?

	/**
	 * Original signature : `const char* lossyCString()`<br></br>
	 * *from NSStringDeprecated native declaration : :347*
	 */
	abstract fun lossyCString(): com.sun.jna.ptr.ByteByReference?

	/**
	 * Original signature : `NSUInteger cStringLength()`<br></br>
	 * *from NSStringDeprecated native declaration : :348*
	 */
	abstract fun cStringLength(): NSUInteger?

	/**
	 * Original signature : `void getCString(char*)`<br></br>
	 * *from NSStringDeprecated native declaration : :349*
	 */
	abstract fun getCString(bytes: java.nio.ByteBuffer?)

	/**
	 * Original signature : `void getCString(char*, NSUInteger)`<br></br>
	 * *from NSStringDeprecated native declaration : :350*
	 */
	abstract fun getCString_maxLength(bytes: java.nio.ByteBuffer?, maxLength: NSUInteger?)
	/**
	 * *from NSStringDeprecated native declaration : :351*<br></br>
	 * Conversion Error : /// Original signature : `void getCString(char*, NSUInteger, null, null)`<br></br>
	 * - (void)getCString:(char*)bytes maxLength:(NSUInteger)maxLength range:(null)aRange remainingRange:(null)leftoverRange; (Argument aRange cannot be converted)
	 */
	/**
	 * Original signature : `BOOL writeToFile(NSString*, BOOL)`<br></br>
	 * *from NSStringDeprecated native declaration : :353*
	 */
	abstract fun writeToFile_atomically(path: NSString?, useAuxiliaryFile: Boolean): Boolean

	/**
	 * Original signature : `BOOL writeToURL(NSURL*, BOOL)`<br></br>
	 * *from NSStringDeprecated native declaration : :354*
	 */
	abstract fun writeToURL_atomically(url: NSURL?, atomically: Boolean): Boolean

	/**
	 * Original signature : `initWithContentsOfFile(NSString*)`<br></br>
	 * *from NSStringDeprecated native declaration : :356*
	 */
	abstract fun initWithContentsOfFile(path: NSString?): NSString?

	/**
	 * Original signature : `initWithContentsOfURL(NSURL*)`<br></br>
	 * *from NSStringDeprecated native declaration : :357*
	 */
	abstract fun initWithContentsOfURL(url: NSURL?): NSString?

	/**
	 * Original signature : `initWithCStringNoCopy(char*, NSUInteger, BOOL)`<br></br>
	 * *from NSStringDeprecated native declaration : :361*
	 */
	abstract fun initWithCStringNoCopy_length_freeWhenDone(
		bytes: java.nio.ByteBuffer?,
		length: NSUInteger?,
		freeBuffer: Boolean
	): NSString?

	/**
	 * Original signature : `initWithCString(const char*, NSUInteger)`<br></br>
	 * *from NSStringDeprecated native declaration : :362*
	 */
	abstract fun initWithCString_length(bytes: String?, length: NSUInteger?): NSString?

	/**
	 * Original signature : `initWithCString(const char*)`<br></br>
	 * *from NSStringDeprecated native declaration : :363*
	 */
	abstract fun initWithCString(bytes: String?): NSString?

	/**
	 * Original signature : `NSArray* pathComponents()`<br></br>
	 * *native declaration : :20*
	 */
	abstract fun pathComponents(): NSArray?

	/**
	 * Original signature : `BOOL isAbsolutePath()`<br></br>
	 * *native declaration : :22*
	 */
	abstract fun isAbsolutePath(): Boolean

	/**
	 * Original signature : `NSString* lastPathComponent()`<br></br>
	 * *native declaration : :24*
	 */
	abstract fun lastPathComponent(): NSString?

	/**
	 * Original signature : `NSString* stringByDeletingLastPathComponent()`<br></br>
	 * *native declaration : :25*
	 */
	abstract fun stringByDeletingLastPathComponent(): NSString?

	/**
	 * Original signature : `NSString* stringByAppendingPathComponent(NSString*)`<br></br>
	 * *native declaration : :26*
	 */
	abstract fun stringByAppendingPathComponent(str: NSString?): NSString?

	/**
	 * Original signature : `NSString* pathExtension()`<br></br>
	 * *native declaration : :28*
	 */
	abstract fun pathExtension(): NSString?

	/**
	 * Original signature : `NSString* stringByDeletingPathExtension()`<br></br>
	 * *native declaration : :29*
	 */
	abstract fun stringByDeletingPathExtension(): NSString?

	/**
	 * Original signature : `NSString* stringByAppendingPathExtension(NSString*)`<br></br>
	 * *native declaration : :30*
	 */
	abstract fun stringByAppendingPathExtension(str: NSString?): NSString?

	/**
	 * Original signature : `NSString* stringByAbbreviatingWithTildeInPath()`<br></br>
	 * *native declaration : :32*
	 */
	abstract fun stringByAbbreviatingWithTildeInPath(): NSString?

	/**
	 * Original signature : `NSString* stringByExpandingTildeInPath()`<br></br>
	 * *native declaration : :33*
	 */
	abstract fun stringByExpandingTildeInPath(): NSString?

	/**
	 * Original signature : `NSString* stringByStandardizingPath()`<br></br>
	 * *native declaration : :35*
	 */
	abstract fun stringByStandardizingPath(): NSString?

	/**
	 * Original signature : `NSString* stringByResolvingSymlinksInPath()`<br></br>
	 * *native declaration : :37*
	 */
	abstract fun stringByResolvingSymlinksInPath(): NSString?

	/**
	 * Original signature : `NSArray* stringsByAppendingPaths(NSArray*)`<br></br>
	 * *native declaration : :39*
	 */
	abstract fun stringsByAppendingPaths(paths: NSArray?): NSArray?

	/**
	 * Original signature : `NSUInteger completePathIntoString(NSString**, BOOL, NSArray**, NSArray*)`<br></br>
	 * *native declaration : :41*
	 */
	companion object {
		val CLASS: _Class = org.rococoa.Rococoa.createClass("NSString", _Class::class.java)

		fun stringWithString(string: String?): NSString {
			return CLASS.stringWithString(string)
		}

		fun stringByAbbreviatingWithTildeInPath(string: String?): String? {
			return CLASS.alloc().initWithString(string).stringByAbbreviatingWithTildeInPath().toString()
		}

		fun stringByExpandingTildeInPath(string: String?): String? {
			return CLASS.alloc().initWithString(string).stringByExpandingTildeInPath().toString()
		}
	}
}

