package darwin

import com.sun.jna.Pointer
import org.rococoa.ObjCClass
import org.rococoa.Rococoa
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : :10</i>
abstract class NSDictionary : NSObject() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `id dictionary()`<br></br>
		 * *from NSDictionaryCreation native declaration : :40*
		 */
		fun dictionary(): NSDictionary?

		/**
		 * Original signature : `id dictionaryWithObject(id, id)`<br></br>
		 * *from NSDictionaryCreation native declaration : :41*
		 */
		fun dictionaryWithObject_forKey(`object`: NSObject?, key: NSObject?): NSDictionary?

		/**
		 * Original signature : `id dictionaryWithObjects(id*, id*, NSUInteger)`<br></br>
		 * *from NSDictionaryCreation native declaration : :42*
		 */
		fun dictionaryWithObjects_forKeys_count(objects: NSObject?, keys: NSObject?, cnt: NSUInteger?): NSDictionary?

		/**
		 * Original signature : `id dictionaryWithObjectsAndKeys(id, null)`<br></br>
		 * *from NSDictionaryCreation native declaration : :43*
		 */
		fun dictionaryWithObjectsAndKeys(firstObject: NSObject?, vararg varargs: NSObject?): NSDictionary?

		/**
		 * Original signature : `id dictionaryWithDictionary(NSDictionary*)`<br></br>
		 * *from NSDictionaryCreation native declaration : :44*
		 */
		fun dictionaryWithDictionary(dict: NSDictionary?): NSDictionary?

		/**
		 * Original signature : `id dictionaryWithObjects(NSArray*, NSArray*)`<br></br>
		 * *from NSDictionaryCreation native declaration : :45*
		 */
		fun dictionaryWithObjects_forKeys(objects: NSArray?, keys: NSArray?): NSDictionary

		/**
		 * Original signature : `id dictionaryWithContentsOfFile(NSString*)`<br></br>
		 * *from NSDictionaryCreation native declaration : :53*
		 */
		fun dictionaryWithContentsOfFile(path: String?): NSDictionary

		/**
		 * Original signature : `id dictionaryWithContentsOfURL(NSURL*)`<br></br>
		 * *from NSDictionaryCreation native declaration : :54*
		 */
		fun dictionaryWithContentsOfURL(url: NSURL?): NSDictionary
	}

	/**
	 * Original signature : `NSUInteger count()`<br></br>
	 * *native declaration : :12*
	 */
	abstract fun count(): NSUInteger?

	/**
	 * *native declaration : :13*<br></br>
	 * Conversion Error : /// Original signature : `objectForKey(null)`<br></br>
	 * - (null)objectForKey:(null)aKey; (Argument aKey cannot be converted)
	 */
	abstract fun objectForKey(key: String?): NSObject?


	/**
	 * Original signature : `NSEnumerator* keyEnumerator()`<br></br>
	 * *native declaration : :14*
	 */
	abstract fun keyEnumerator(): NSEnumerator?

	/**
	 * Original signature : `NSArray* allKeys()`<br></br>
	 * *from NSExtendedDictionary native declaration : :20*
	 */
	abstract fun allKeys(): NSArray?
	/**
	 * *from NSExtendedDictionary native declaration : :21*<br></br>
	 * Conversion Error : /// Original signature : `NSArray* allKeysForObject(null)`<br></br>
	 * - (NSArray*)allKeysForObject:(null)anObject; (Argument anObject cannot be converted)
	 */
	/**
	 * Original signature : `NSArray* allValues()`<br></br>
	 * *from NSExtendedDictionary native declaration : :22*
	 */
	abstract fun allValues(): NSArray?

	/**
	 * Original signature : `NSString* descriptionInStringsFileFormat()`<br></br>
	 * *from NSExtendedDictionary native declaration : :24*
	 */
	abstract fun descriptionInStringsFileFormat(): Pointer?
	/**
	 * *from NSExtendedDictionary native declaration : :25*<br></br>
	 * Conversion Error : /// Original signature : `NSString* descriptionWithLocale(null)`<br></br>
	 * - (NSString*)descriptionWithLocale:(null)locale; (Argument locale cannot be converted)
	 */
	/**
	 * *from NSExtendedDictionary native declaration : :26*<br></br>
	 * Conversion Error : /// Original signature : `NSString* descriptionWithLocale(null, NSUInteger)`<br></br>
	 * - (NSString*)descriptionWithLocale:(null)locale indent:(NSUInteger)level; (Argument locale cannot be converted)
	 */
	/**
	 * Original signature : `BOOL isEqualToDictionary(NSDictionary*)`<br></br>
	 * *from NSExtendedDictionary native declaration : :27*
	 */
	abstract fun isEqualToDictionary(otherDictionary: NSDictionary?): Boolean

	/**
	 * Original signature : `NSEnumerator* objectEnumerator()`<br></br>
	 * *from NSExtendedDictionary native declaration : :28*
	 */
	abstract fun objectEnumerator(): NSEnumerator?
	/**
	 * *from NSExtendedDictionary native declaration : :29*<br></br>
	 * Conversion Error : /// Original signature : `NSArray* objectsForKeys(NSArray*, null)`<br></br>
	 * - (NSArray*)objectsForKeys:(NSArray*)keys notFoundMarker:(null)marker; (Argument marker cannot be converted)
	 */
	/**
	 * Original signature : `BOOL writeToFile(NSString*, BOOL)`<br></br>
	 * *from NSExtendedDictionary native declaration : :30*
	 */
	abstract fun writeToFile_atomically(path: String?, useAuxiliaryFile: Boolean): Boolean

	fun writeToFile(path: String?): Boolean {
		return this.writeToFile_atomically(path, true)
	}

	/**
	 * Original signature : `BOOL writeToURL(NSURL*, BOOL)`<br></br>
	 * the atomically flag is ignored if url of a type that cannot be written atomically.<br></br>
	 * *from NSExtendedDictionary native declaration : :31*
	 */
	abstract fun writeToURL_atomically(url: NSURL?, atomically: Boolean): Boolean

	fun writeToURL(url: NSURL?): Boolean {
		return this.writeToURL_atomically(url, true)
	}

	/**
	 * *from NSExtendedDictionary native declaration : :33*<br></br>
	 * Conversion Error : /// Original signature : `NSArray* keysSortedByValueUsingSelector(null)`<br></br>
	 * - (NSArray*)keysSortedByValueUsingSelector:(null)comparator; (Argument comparator cannot be converted)
	 */
	/**
	 * Original signature : `void getObjects(id*, id*)`<br></br>
	 * *from NSExtendedDictionary native declaration : :34*
	 */
	abstract fun getObjects_andKeys(objects: NSObject?, keys: NSObject?)

	/**
	 * Original signature : `id initWithObjects(id*, id*, NSUInteger)`<br></br>
	 * *from NSDictionaryCreation native declaration : :47*
	 */
	abstract fun initWithObjects_forKeys_count(objects: NSObject?, keys: NSObject?, cnt: NSUInteger?): NSDictionary?

	/**
	 * Original signature : `id initWithObjectsAndKeys(id, null)`<br></br>
	 * *from NSDictionaryCreation native declaration : :48*
	 */
	abstract fun initWithObjectsAndKeys(firstObject: NSObject?, vararg varargs: NSObject?): NSDictionary?

	/**
	 * Original signature : `id initWithDictionary(NSDictionary*)`<br></br>
	 * *from NSDictionaryCreation native declaration : :49*
	 */
	abstract fun initWithDictionary(otherDictionary: NSDictionary?): NSDictionary?

	/**
	 * Original signature : `id initWithDictionary(NSDictionary*, BOOL)`<br></br>
	 * *from NSDictionaryCreation native declaration : :50*
	 */
	abstract fun initWithDictionary_copyItems(otherDictionary: NSDictionary?, flag: Boolean): NSDictionary?

	/**
	 * Original signature : `id initWithObjects(NSArray*, NSArray*)`<br></br>
	 * *from NSDictionaryCreation native declaration : :51*
	 */
	abstract fun initWithObjects_forKeys(objects: NSArray?, keys: NSArray?): NSDictionary?

	/**
	 * Original signature : `id initWithContentsOfFile(NSString*)`<br></br>
	 * *from NSDictionaryCreation native declaration : :55*
	 */
	abstract fun initWithContentsOfFile(path: String?): NSDictionary?

	/**
	 * Original signature : `id initWithContentsOfURL(NSURL*)`<br></br>
	 * *from NSDictionaryCreation native declaration : :56*
	 */
	abstract fun initWithContentsOfURL(url: NSURL?): NSDictionary?

	companion object {
		val CLASS: _Class = Rococoa.createClass("NSDictionary", _Class::class.java)

		fun dictionaryWithObjectsForKeys(objects: NSArray?, keys: NSArray?): NSDictionary {
			return CLASS.dictionaryWithObjects_forKeys(objects, keys)
		}

		fun dictionaryWithContentsOfURL(url: NSURL?): NSDictionary {
			return CLASS.dictionaryWithContentsOfURL(url)
		}

		fun dictionaryWithContentsOfFile(path: String?): NSDictionary {
			return CLASS.dictionaryWithContentsOfFile(path)
		}
	}
}
