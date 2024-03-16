package darwin 

import com.sun.jna.Native
import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSInteger
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : :28</i>
abstract class NSIndexSet : NSObject() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `indexSet()`<br></br>
		 * *native declaration : :30*
		 */
		open fun indexSet(): NSIndexSet?

		/**
		 * Original signature : `indexSetWithIndex(NSUInteger)`<br></br>
		 * *native declaration : :31*
		 */
		open fun indexSetWithIndex(value: NSInteger?): NSIndexSet?

		/**
		 * *native declaration : :32*<br></br>
		 * Conversion Error : /// Original signature : `indexSetWithIndexesInRange(null)`<br></br>
		 * + (null)indexSetWithIndexesInRange:(null)range; (Argument range cannot be converted)
		 */
		open fun indexSetWithIndexesInRange(range: NSRange?): NSIndexSet?
	}

	/**
	 * Original signature : `init()`<br></br>
	 * *native declaration : :34*
	 */
	abstract fun init(): NSIndexSet?

	/**
	 * Original signature : `initWithIndex(NSUInteger)`<br></br>
	 * *native declaration : :35*
	 */
	abstract fun initWithIndex(value: NSUInteger?): NSIndexSet?
	/**
	 * *native declaration : :36*<br></br>
	 * Conversion Error : /// Original signature : `initWithIndexesInRange(null)`<br></br>
	 * - (null)initWithIndexesInRange:(null)range; // designated initializer<br></br>
	 * (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `initWithIndexSet(NSIndexSet*)`<br></br>
	 * designated initializer<br></br>
	 * *native declaration : :37*
	 */
	abstract fun initWithIndexSet(indexSet: NSIndexSet?): NSIndexSet?

	/**
	 * Original signature : `BOOL isEqualToIndexSet(NSIndexSet*)`<br></br>
	 * *native declaration : :39*
	 */
	abstract fun isEqualToIndexSet(indexSet: NSIndexSet?): Boolean

	/**
	 * Original signature : `NSUInteger count()`<br></br>
	 * *native declaration : :41*
	 */
	abstract fun count(): NSUInteger?

	/**
	 * The following six methods will return NSNotFound if there is no index in the set satisfying the query.<br></br>
	 * Original signature : `NSUInteger firstIndex()`<br></br>
	 * *native declaration : :45*
	 */
	abstract fun firstIndex(): NSUInteger?

	/**
	 * Original signature : `NSUInteger lastIndex()`<br></br>
	 * *native declaration : :46*
	 */
	abstract fun lastIndex(): NSUInteger?

	/**
	 * Original signature : `NSUInteger indexGreaterThanIndex(NSUInteger)`<br></br>
	 * *native declaration : :47*
	 */
	abstract fun indexGreaterThanIndex(value: NSUInteger?): NSUInteger?

	/**
	 * Original signature : `NSUInteger indexLessThanIndex(NSUInteger)`<br></br>
	 * *native declaration : :48*
	 */
	abstract fun indexLessThanIndex(value: NSUInteger?): NSUInteger?

	/**
	 * Original signature : `NSUInteger indexGreaterThanOrEqualToIndex(NSUInteger)`<br></br>
	 * *native declaration : :49*
	 */
	abstract fun indexGreaterThanOrEqualToIndex(value: NSUInteger?): NSUInteger?

	/**
	 * Original signature : `NSUInteger indexLessThanOrEqualToIndex(NSUInteger)`<br></br>
	 * *native declaration : :50*
	 */
	abstract fun indexLessThanOrEqualToIndex(value: NSUInteger?): NSUInteger?
	/**
	 * *native declaration : :54*<br></br>
	 * Conversion Error : / **<br></br>
	 * * Fills up to bufferSize indexes in the specified range into the buffer and returns the number of indexes actually placed in the buffer; also modifies the optional range passed in by pointer to be "positioned" after the last index filled into the buffer.Example: if the index set contains the indexes 0, 2, 4, ..., 98, 100, for a buffer of size 10 and the range (20, 80) the buffer would contain 20, 22, ..., 38 and the range would be modified to (40, 60).<br></br>
	 * * Original signature : `NSUInteger getIndexes(NSUInteger*, NSUInteger, null)`<br></br>
	 * * /<br></br>
	 * - (NSUInteger)getIndexes:(NSUInteger*)indexBuffer maxCount:(NSUInteger)bufferSize inIndexRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * *native declaration : :57*<br></br>
	 * Conversion Error : /// Original signature : `NSUInteger countOfIndexesInRange(null)`<br></br>
	 * - (NSUInteger)countOfIndexesInRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `BOOL containsIndex(NSUInteger)`<br></br>
	 * *native declaration : :60*
	 */
	abstract fun containsIndex(value: NSUInteger?): Boolean
	/**
	 * *native declaration : :61*<br></br>
	 * Conversion Error : /// Original signature : `BOOL containsIndexesInRange(null)`<br></br>
	 * - (BOOL)containsIndexesInRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `BOOL containsIndexes(NSIndexSet*)`<br></br>
	 * *native declaration : :62*
	 */
	abstract fun containsIndexes(indexSet: NSIndexSet?): Boolean

	/**
	 * *native declaration : :64*<br></br>
	 * Conversion Error : /// Original signature : `BOOL intersectsIndexesInRange(null)`<br></br>
	 * - (BOOL)intersectsIndexesInRange:(null)range; (Argument range cannot be converted)
	 */
	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSIndexSet", _Class::class.java)

		fun indexSet(): NSIndexSet? {
			return CLASS.indexSet()
		}

		fun indexSetWithIndex(value: NSInteger?): NSIndexSet? {
			return CLASS.indexSetWithIndex(value)
		}

		fun indexSetWithIndexesInRange(range: NSRange?): NSIndexSet? {
			return CLASS.indexSetWithIndexesInRange(range)
		}

		/**
		 * NSNotFound is set to LONG_MAX in NSObjCRuntime.h, which has different values on 32-bit and 64-bit
		 */
		val NSNotFound: NSUInteger =
			if (Native.LONG_SIZE === 4) NSUInteger(Integer.MAX_VALUE.toLong()) else NSUInteger(Long.MAX_VALUE)
	}
}
