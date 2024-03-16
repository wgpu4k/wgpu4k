package darwin 

import com.sun.jna.PointerType
import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSUInteger
import java.nio.IntBuffer

/// <i>native declaration : :80</i>
abstract class NSMutableArray : NSArray() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `id arrayWithCapacity(NSUInteger)`<br></br>
		 * *from NSMutableArrayCreation native declaration : :118*
		 */
		open fun arrayWithCapacity(numItems: NSUInteger?): NSMutableArray?

		open fun array(): NSMutableArray?
	}

	/**
	 * Original signature : `void addObject(id)`<br></br>
	 * *native declaration : :82*
	 */
	abstract fun addObject(anObject: NSObject?)

	abstract fun addObject(pointerType: PointerType?)

	abstract fun addObject(anObject: String?)

	/**
	 * Original signature : `void insertObject(id, NSUInteger)`<br></br>
	 * *native declaration : :83*
	 */
	abstract fun insertObject_atIndex(anObject: PointerType?, index: NSUInteger?)

	abstract fun insertObject_atIndex(anObject: NSObject?, index: NSUInteger?)

	abstract fun insertObject_atIndex(anObject: String?, index: NSUInteger?)

	/**
	 * Original signature : `void removeLastObject()`<br></br>
	 * *native declaration : :84*
	 */
	abstract fun removeLastObject()

	/**
	 * Original signature : `void removeObjectAtIndex(NSUInteger)`<br></br>
	 * *native declaration : :85*
	 */
	abstract fun removeObjectAtIndex(index: NSUInteger?)

	/**
	 * Original signature : `void replaceObjectAtIndex(NSUInteger, id)`<br></br>
	 * *native declaration : :86*
	 */
	abstract fun replaceObjectAtIndex_withObject(index: NSUInteger?, anObject: NSObject?)

	/**
	 * Original signature : `void addObjectsFromArray(NSArray*)`<br></br>
	 * *from NSExtendedMutableArray native declaration : :92*
	 */
	abstract fun addObjectsFromArray(otherArray: NSArray?)

	/**
	 * Original signature : `void exchangeObjectAtIndex(NSUInteger, NSUInteger)`<br></br>
	 * *from NSExtendedMutableArray native declaration : :93*
	 */
	abstract fun exchangeObjectAtIndex_withObjectAtIndex(idx1: NSUInteger?, idx2: NSUInteger?)

	/**
	 * Original signature : `void removeAllObjects()`<br></br>
	 * *from NSExtendedMutableArray native declaration : :94*
	 */
	abstract fun removeAllObjects()
	/**
	 * *from NSExtendedMutableArray native declaration : :95*<br></br>
	 * Conversion Error : /// Original signature : `void removeObject(id, null)`<br></br>
	 * - (void)removeObject:(id)anObject inRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `void removeObject(id)`<br></br>
	 * *from NSExtendedMutableArray native declaration : :96*
	 */
	abstract fun removeObject(anObject: NSObject?)
	/**
	 * *from NSExtendedMutableArray native declaration : :97*<br></br>
	 * Conversion Error : /// Original signature : `void removeObjectIdenticalTo(id, null)`<br></br>
	 * - (void)removeObjectIdenticalTo:(id)anObject inRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `void removeObjectIdenticalTo(id)`<br></br>
	 * *from NSExtendedMutableArray native declaration : :98*
	 */
	abstract fun removeObjectIdenticalTo(anObject: NSObject?)

	/**
	 * Original signature : `void removeObjectsFromIndices(NSUInteger*, NSUInteger)`<br></br>
	 * *from NSExtendedMutableArray native declaration : :99*
	 */
	abstract fun removeObjectsFromIndices_numIndices(indices: IntBuffer?, cnt: NSUInteger?)

	/**
	 * Original signature : `void removeObjectsInArray(NSArray*)`<br></br>
	 * *from NSExtendedMutableArray native declaration : :100*
	 */
	abstract fun removeObjectsInArray(otherArray: NSArray?)
	/**
	 * *from NSExtendedMutableArray native declaration : :101*<br></br>
	 * Conversion Error : /// Original signature : `void removeObjectsInRange(null)`<br></br>
	 * - (void)removeObjectsInRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * *from NSExtendedMutableArray native declaration : :102*<br></br>
	 * Conversion Error : /// Original signature : `void replaceObjectsInRange(null, NSArray*, null)`<br></br>
	 * - (void)replaceObjectsInRange:(null)range withObjectsFromArray:(NSArray*)otherArray range:(null)otherRange; (Argument range cannot be converted)
	 */
	/**
	 * *from NSExtendedMutableArray native declaration : :103*<br></br>
	 * Conversion Error : /// Original signature : `void replaceObjectsInRange(null, NSArray*)`<br></br>
	 * - (void)replaceObjectsInRange:(null)range withObjectsFromArray:(NSArray*)otherArray; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `void setArray(NSArray*)`<br></br>
	 * *from NSExtendedMutableArray native declaration : :104*
	 */
	abstract fun setArray(otherArray: NSArray?)
	/**
	 * *from NSExtendedMutableArray native declaration : :106*<br></br>
	 * Conversion Error : /// Original signature : `void sortUsingSelector(null)`<br></br>
	 * - (void)sortUsingSelector:(null)comparator; (Argument comparator cannot be converted)
	 */
	/**
	 * Original signature : `void insertObjects(NSArray*, NSIndexSet*)`<br></br>
	 * *from NSExtendedMutableArray native declaration : :109*
	 */
	abstract fun insertObjects_atIndexes(objects: NSArray?, indexes: com.sun.jna.Pointer?)

	/**
	 * Original signature : `void removeObjectsAtIndexes(NSIndexSet*)`<br></br>
	 * *from NSExtendedMutableArray native declaration : :110*
	 */
	abstract fun removeObjectsAtIndexes(indexes: com.sun.jna.Pointer?)

	/**
	 * Original signature : `void replaceObjectsAtIndexes(NSIndexSet*, NSArray*)`<br></br>
	 * *from NSExtendedMutableArray native declaration : :111*
	 */
	abstract fun replaceObjectsAtIndexes_withObjects(indexes: com.sun.jna.Pointer?, objects: NSArray?)

	/**
	 * Original signature : `id initWithCapacity(NSUInteger)`<br></br>
	 * *from NSMutableArrayCreation native declaration : :119*
	 */
	abstract fun initWithCapacity(numItems: NSUInteger?): NSMutableArray?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSMutableArray", _Class::class.java)

		fun array(): NSMutableArray? {
			return CLASS.array()
		}

		fun arrayWithCapacity(numItems: NSUInteger?): NSMutableArray? {
			return CLASS.arrayWithCapacity(numItems)
		}
	}
}
