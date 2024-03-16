package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:13</i>
abstract class NSSet : NSObject() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `set()`<br></br>
		 * *from NSSetCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:43*
		 */
		open fun set(): NSSet?
		/**
		 * *from NSSetCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:44*<br></br>
		 * Conversion Error : /// Original signature : `setWithObject(null)`<br></br>
		 * + (null)setWithObject:(null)object; (Argument object cannot be converted)
		 */
		/**
		 * Original signature : `setWithObjects(id*, NSUInteger)`<br></br>
		 * *from NSSetCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:45*
		 */
		open fun setWithObjects_count(objects: NSObject?, cnt: NSUInteger?): NSSet?

		/**
		 * Original signature : `id setWithObjects(id, null)`<br></br>
		 * *from NSSetCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:46*
		 */
		open fun setWithObjects(firstObj: NSObject?, vararg varargs: NSObject?): NSSet?

		/**
		 * Original signature : `id setWithSet(NSSet*)`<br></br>
		 * *from NSSetCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:47*
		 */
		open fun setWithSet(set: NSSet?): NSSet?

		/**
		 * Original signature : `id setWithArray(NSArray*)`<br></br>
		 * *from NSSetCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:48*
		 */
		open fun setWithArray(array: NSArray?): NSSet?
	}

	/**
	 * Original signature : `NSUInteger count()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:15*
	 */
	abstract fun count(): NSUInteger?
	/**
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:16*<br></br>
	 * Conversion Error : /// Original signature : `member(null)`<br></br>
	 * - (null)member:(null)object; (Argument object cannot be converted)
	 */
	/**
	 * Original signature : `NSEnumerator* objectEnumerator()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:17*
	 */
	abstract fun objectEnumerator(): NSEnumerator?

	/**
	 * Original signature : `NSArray* allObjects()`<br></br>
	 * *from NSExtendedSet native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:23*
	 */
	abstract fun allObjects(): NSArray?

	/**
	 * Original signature : `anyObject()`<br></br>
	 * *from NSExtendedSet native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:24*
	 */
	abstract fun anyObject(): NSObject?
	/**
	 * *from NSExtendedSet native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:25*<br></br>
	 * Conversion Error : /// Original signature : `BOOL containsObject(null)`<br></br>
	 * - (BOOL)containsObject:(null)anObject; (Argument anObject cannot be converted)
	 */
	/**
	 * Original signature : `NSString* description()`<br></br>
	 * *from NSExtendedSet native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:26*
	 */
	abstract override fun description(): String?
	/**
	 * *from NSExtendedSet native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:27*<br></br>
	 * Conversion Error : /// Original signature : `NSString* descriptionWithLocale(null)`<br></br>
	 * - (NSString*)descriptionWithLocale:(null)locale; (Argument locale cannot be converted)
	 */
	/**
	 * Original signature : `BOOL intersectsSet(NSSet*)`<br></br>
	 * *from NSExtendedSet native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:28*
	 */
	abstract fun intersectsSet(otherSet: NSSet?): Boolean

	/**
	 * Original signature : `BOOL isEqualToSet(NSSet*)`<br></br>
	 * *from NSExtendedSet native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:29*
	 */
	abstract fun isEqualToSet(otherSet: NSSet?): Boolean

	/**
	 * Original signature : `BOOL isSubsetOfSet(NSSet*)`<br></br>
	 * *from NSExtendedSet native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:30*
	 */
	abstract fun isSubsetOfSet(otherSet: NSSet?): Boolean
	/**
	 * *from NSExtendedSet native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:32*<br></br>
	 * Conversion Error : /// Original signature : `void makeObjectsPerformSelector(null)`<br></br>
	 * - (void)makeObjectsPerformSelector:(null)aSelector; (Argument aSelector cannot be converted)
	 */
	/**
	 * *from NSExtendedSet native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:33*<br></br>
	 * Conversion Error : /// Original signature : `void makeObjectsPerformSelector(null, null)`<br></br>
	 * - (void)makeObjectsPerformSelector:(null)aSelector withObject:(null)argument; (Argument aSelector cannot be converted)
	 */
	/**
	 * *from NSExtendedSet native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:35*<br></br>
	 * Conversion Error : /// Original signature : `NSSet* setByAddingObject(null)`<br></br>
	 * - (NSSet*)setByAddingObject:(null)anObject; (Argument anObject cannot be converted)
	 */
	/**
	 * Original signature : `NSSet* setByAddingObjectsFromSet(NSSet*)`<br></br>
	 * *from NSExtendedSet native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:36*
	 */
	abstract fun setByAddingObjectsFromSet(other: NSSet?): NSSet?

	/**
	 * Original signature : `NSSet* setByAddingObjectsFromArray(NSArray*)`<br></br>
	 * *from NSExtendedSet native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:37*
	 */
	abstract fun setByAddingObjectsFromArray(other: NSArray?): NSSet?

	/**
	 * Original signature : `id initWithObjects(id*, NSUInteger)`<br></br>
	 * *from NSSetCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:50*
	 */
	abstract fun initWithObjects_count(objects: NSObject?, cnt: NSUInteger?): NSSet?

	/**
	 * Original signature : `id initWithObjects(id, null)`<br></br>
	 * *from NSSetCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:51*
	 */
	abstract fun initWithObjects(firstObj: NSObject?, vararg varargs: NSObject?): NSSet?

	/**
	 * Original signature : `id initWithSet(NSSet*)`<br></br>
	 * *from NSSetCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:52*
	 */
	abstract fun initWithSet(set: NSSet?): NSSet?

	/**
	 * Original signature : `id initWithSet(NSSet*, BOOL)`<br></br>
	 * *from NSSetCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:53*
	 */
	abstract fun initWithSet_copyItems(set: NSSet?, flag: Boolean): NSSet?

	/**
	 * Original signature : `id initWithArray(NSArray*)`<br></br>
	 * *from NSSetCreation native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSSet.h:54*
	 */
	abstract fun initWithArray(array: NSArray?): NSSet?

	/**
	 * Return a set containing the results of invoking -valueForKey: on each of the receiver's members. The returned set might not have the same number of members as the receiver. The returned set will not contain any elements corresponding to instances of -valueForKey: returning nil (in contrast with -[NSArray(NSKeyValueCoding) valueForKey:], which may put NSNulls in the arrays it returns).<br></br>
	 * Original signature : `id valueForKey(NSString*)`<br></br>
	 * *from NSKeyValueCoding native declaration : :191*
	 */
	abstract fun valueForKey(key: String?): NSObject?

	/**
	 * Invoke -setValue:forKey: on each of the receiver's members.<br></br>
	 * Original signature : `void setValue(id, NSString*)`<br></br>
	 * *from NSKeyValueCoding native declaration : :195*
	 */
	abstract fun setValue_forKey(value: NSObject?, key: String?)
}
