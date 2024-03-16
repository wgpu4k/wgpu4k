package darwin

import org.rococoa.ObjCClass
import org.rococoa.ObjCObjectByReference
import org.rococoa.Rococoa
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : :14</i>
abstract class NSArray : NSObject() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `id array()`<br></br>
		 * *from NSArrayCreation native declaration : :60*
		 */
		fun array(): NSArray

		/**
		 * Original signature : `id arrayWithObject(id)`<br></br>
		 * *from NSArrayCreation native declaration : :61*
		 */
		fun arrayWithObject(anObject: NSObject?): NSArray

		fun arrayWithObject(anObject: String?): NSArray?

		/**
		 * Original signature : `id arrayWithObjects(const id*, NSUInteger)`<br></br>
		 * *from NSArrayCreation native declaration : :62*
		 */
		fun arrayWithObjects_count(objects: NSObject?, cnt: NSUInteger?): NSArray?

		/**
		 * Original signature : `id arrayWithObjects(id, null)`<br></br>
		 * *from NSArrayCreation native declaration : :63*
		 */
		fun arrayWithObjects(vararg varargs: NSObject?): NSArray

		fun arrayWithObjects(vararg varargs: String?): NSArray

		/**
		 * Original signature : `id arrayWithArray(NSArray*)`<br></br>
		 * *from NSArrayCreation native declaration : :64*
		 */
		fun arrayWithArray(array: NSArray?): NSArray?

		/**
		 * Original signature : `id arrayWithContentsOfFile(NSString*)`<br></br>
		 * *from NSArrayCreation native declaration : :71*
		 */
		fun arrayWithContentsOfFile(path: String?): NSArray

		/**
		 * Original signature : `id arrayWithContentsOfURL(NSURL*)`<br></br>
		 * *from NSArrayCreation native declaration : :72*
		 */
		fun arrayWithContentsOfURL(url: NSURL?): NSArray
	}

	abstract fun init(): NSArray?

	/**
	 * Original signature : `NSUInteger count()`<br></br>
	 * *native declaration : :16*
	 */
	abstract fun count(): NSUInteger?

	/**
	 * Original signature : `objectAtIndex(NSUInteger)`<br></br>
	 * *native declaration : :17*
	 */
	abstract fun objectAtIndex(index: NSUInteger?): NSObject?

	/**
	 * *from NSExtendedArray native declaration : :23*<br></br>
	 * Conversion Error : /// Original signature : `NSArray* arrayByAddingObject(null)`<br></br>
	 * - (NSArray*)arrayByAddingObject:(null)anObject; (Argument anObject cannot be converted)
	 */
	/**
	 * Original signature : `NSArray* arrayByAddingObjectsFromArray(NSArray*)`<br></br>
	 * *from NSExtendedArray native declaration : :24*
	 */
	abstract fun arrayByAddingObjectsFromArray(otherArray: NSArray?): NSArray?

	/**
	 * Original signature : `NSString* componentsJoinedByString(NSString*)`<br></br>
	 * *from NSExtendedArray native declaration : :25*
	 */
	abstract fun componentsJoinedByString(separator: String?): String?
	/**
	 * *from NSExtendedArray native declaration : :26*<br></br>
	 * Conversion Error : /// Original signature : `BOOL containsObject(null)`<br></br>
	 * - (BOOL)containsObject:(null)anObject; (Argument anObject cannot be converted)
	 */
	/**
	 * *from NSExtendedArray native declaration : :28*<br></br>
	 * Conversion Error : /// Original signature : `NSString* descriptionWithLocale(null)`<br></br>
	 * - (NSString*)descriptionWithLocale:(null)locale; (Argument locale cannot be converted)
	 */
	/**
	 * *from NSExtendedArray native declaration : :29*<br></br>
	 * Conversion Error : /// Original signature : `NSString* descriptionWithLocale(null, NSUInteger)`<br></br>
	 * - (NSString*)descriptionWithLocale:(null)locale indent:(NSUInteger)level; (Argument locale cannot be converted)
	 */
	/**
	 * Original signature : `firstObjectCommonWithArray(NSArray*)`<br></br>
	 * *from NSExtendedArray native declaration : :30*
	 */
	abstract fun firstObjectCommonWithArray(otherArray: NSArray?): NSObject?

	/**
	 * Original signature : `void getObjects(id*)`<br></br>
	 * *from NSExtendedArray native declaration : :31*
	 */
	abstract fun getObjects(objects: ObjCObjectByReference?)
	/**
	 * *from NSExtendedArray native declaration : :32*<br></br>
	 * Conversion Error : /// Original signature : `void getObjects(id*, null)`<br></br>
	 * - (void)getObjects:(id*)objects range:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `NSUInteger indexOfObject(id)`<br></br>
	 * *from NSExtendedArray native declaration : :33*
	 */
	abstract fun indexOfObject(anObject: NSObject?): NSUInteger?
	/**
	 * *from NSExtendedArray native declaration : :34*<br></br>
	 * Conversion Error : /// Original signature : `NSUInteger indexOfObject(id, null)`<br></br>
	 * - (NSUInteger)indexOfObject:(id)anObject inRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `NSUInteger indexOfObjectIdenticalTo(id)`<br></br>
	 * *from NSExtendedArray native declaration : :35*
	 */
	abstract fun indexOfObjectIdenticalTo(anObject: NSObject?): NSUInteger?
	/**
	 * *from NSExtendedArray native declaration : :36*<br></br>
	 * Conversion Error : /// Original signature : `NSUInteger indexOfObjectIdenticalTo(id, null)`<br></br>
	 * - (NSUInteger)indexOfObjectIdenticalTo:(id)anObject inRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `BOOL isEqualToArray(NSArray*)`<br></br>
	 * *from NSExtendedArray native declaration : :37*
	 */
	abstract fun isEqualToArray(otherArray: NSArray?): Byte

	/**
	 * Original signature : `id lastObject()`<br></br>
	 * *from NSExtendedArray native declaration : :38*
	 */
	abstract fun lastObject(): NSObject?

	/**
	 * Original signature : `NSEnumerator* objectEnumerator()`<br></br>
	 * *from NSExtendedArray native declaration : :39*
	 */
	abstract fun objectEnumerator(): NSEnumerator?

	/**
	 * Original signature : `NSEnumerator* reverseObjectEnumerator()`<br></br>
	 * *from NSExtendedArray native declaration : :40*
	 */
	abstract fun reverseObjectEnumerator(): NSEnumerator?

	/**
	 * Original signature : `NSData* sortedArrayHint()`<br></br>
	 * *from NSExtendedArray native declaration : :41*
	 */
	abstract fun sortedArrayHint(): NSData?

	/**
	 * *from NSExtendedArray native declaration : :44*<br></br>
	 * Conversion Error : /// Original signature : `NSArray* sortedArrayUsingSelector(null)`<br></br>
	 * - (NSArray*)sortedArrayUsingSelector:(null)comparator; (Argument comparator cannot be converted)
	 */
	/**
	 * *from NSExtendedArray native declaration : :45*<br></br>
	 * Conversion Error : /// Original signature : `NSArray* subarrayWithRange(null)`<br></br>
	 * - (NSArray*)subarrayWithRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `BOOL writeToFile(NSString*, BOOL)`<br></br>
	 * *from NSExtendedArray native declaration : :46*
	 */
	abstract fun writeToFile_atomically(path: String?, useAuxiliaryFile: Boolean): Boolean

	fun writeToFile(path: String?): Boolean {
		return this.writeToFile_atomically(path, true)
	}

	/**
	 * Original signature : `BOOL writeToURL(NSURL*, BOOL)`<br></br>
	 * *from NSExtendedArray native declaration : :47*
	 */
	abstract fun writeToURL_atomically(url: NSURL?, atomically: Boolean): Boolean

	fun writeToURL(url: NSURL?): Boolean {
		return this.writeToURL_atomically(url, true)
	}

	/**
	 * *from NSExtendedArray native declaration : :49*<br></br>
	 * Conversion Error : /// Original signature : `void makeObjectsPerformSelector(null)`<br></br>
	 * - (void)makeObjectsPerformSelector:(null)aSelector; (Argument aSelector cannot be converted)
	 */
	/**
	 * *from NSExtendedArray native declaration : :50*<br></br>
	 * Conversion Error : /// Original signature : `void makeObjectsPerformSelector(null, id)`<br></br>
	 * - (void)makeObjectsPerformSelector:(null)aSelector withObject:(id)argument; (Argument aSelector cannot be converted)
	 */
	/**
	 * Original signature : `NSArray* objectsAtIndexes(NSIndexSet*)`<br></br>
	 * *from NSExtendedArray native declaration : :53*
	 */
	abstract fun objectsAtIndexes(indexes: NSIndexSet?): NSArray?

	/**
	 * Original signature : `id initWithObjects(const id*, NSUInteger)`<br></br>
	 * *from NSArrayCreation native declaration : :66*
	 */
	abstract fun initWithObjects_count(objects: ObjCObjectByReference?, cnt: NSUInteger?): NSArray?

	/**
	 * Original signature : `id initWithObjects(id, null)`<br></br>
	 * *from NSArrayCreation native declaration : :67*
	 */
	abstract fun initWithObjects(vararg varargs: NSObject?): NSArray?

	abstract fun initWithObjects(vararg varargs: String?): NSArray?

	/**
	 * Original signature : `id initWithArray(NSArray*)`<br></br>
	 * *from NSArrayCreation native declaration : :68*
	 */
	abstract fun initWithArray(array: NSArray?): NSArray?

	/**
	 * Original signature : `id initWithArray(NSArray*, BOOL)`<br></br>
	 * *from NSArrayCreation native declaration : :69*
	 */
	abstract fun initWithArray_copyItems(array: NSArray?, flag: Byte): NSArray?

	/**
	 * Original signature : `id initWithContentsOfFile(NSString*)`<br></br>
	 * *from NSArrayCreation native declaration : :73*
	 */
	abstract fun initWithContentsOfFile(path: String?): NSArray?

	/**
	 * Original signature : `id initWithContentsOfURL(NSURL*)`<br></br>
	 * *from NSArrayCreation native declaration : :74*
	 */
	abstract fun initWithContentsOfURL(url: NSURL?): NSArray?

	companion object {
		val CLASS: _Class = Rococoa.createClass("NSArray", _Class::class.java)

		fun array(): NSArray {
			return CLASS.array()
		}

		fun arrayWithContentsOfFile(path: String?): NSArray {
			return CLASS.arrayWithContentsOfFile(path)
		}

		fun arrayWithContentsOfURL(url: NSURL?): NSArray {
			return CLASS.arrayWithContentsOfURL(url)
		}

		fun arrayWithObject(anObject: NSObject?): NSArray {
			return CLASS.arrayWithObject(anObject)
		}

		fun arrayWithObject(anObject: String?): NSArray {
			return CLASS.arrayWithObject(NSString.stringWithString(anObject))
		}

		fun arrayWithObjects(vararg arrayWithObjects: NSObject?): NSArray {
			return CLASS.arrayWithObjects(*arrayWithObjects)
		}

		fun arrayWithObjects(vararg arrayWithObjects: String?): NSArray {
			return CLASS.arrayWithObjects(*arrayWithObjects)
		}
	}
}
