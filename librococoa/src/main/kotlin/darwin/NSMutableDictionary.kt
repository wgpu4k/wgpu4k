package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : :62</i>
abstract class NSMutableDictionary : NSDictionary() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `id dictionaryWithCapacity(NSUInteger)`<br></br>
		 * *from NSMutableDictionaryCreation native declaration : :80*
		 */
		open fun dictionaryWithCapacity(numItems: NSUInteger?): NSMutableDictionary?

		open fun dictionaryWithDictionary(dict: NSDictionary?): NSMutableDictionary?
	}

	/**
	 * Original signature : `void removeObjectForKey(id)`<br></br>
	 * *native declaration : :64*
	 */
	abstract fun removeObjectForKey(aKey: String?)

	/**
	 * If aKey already exists in the receiver, the receiver’s previous value
	 * object for that key is sent a release message and anObject takes its place.
	 *
	 * @param anObject The object receives a retain message before being added to
	 * the receiver. This value must not be nil.
	 * @param aKey     The key is copied (using copyWithZone:; keys must conform to the NSCopying protocol). The key must not be nil.
	 */
	fun setObjectForKey(anObject: String?, aKey: String?) {
		this.setObjectForKey(NSString.stringWithString(anObject), aKey)
	}

	fun setObjectForKey(anObject: NSObject?, aKey: String?) {
		this.setObject_forKey(anObject, NSString.stringWithString(aKey))
	}

	/**
	 * Original signature : `public abstract void setObject(id, id)`<br></br>
	 * *native declaration : :65*
	 */
	abstract fun setObject_forKey(anObject: NSObject?, aKey: NSObject?)

	/**
	 * Original signature : `public abstract void addEntriesFromDictionary(NSDictionary*)`<br></br>
	 * *from NSExtendedMutableDictionary native declaration : :71*
	 */
	abstract fun addEntriesFromDictionary(otherDictionary: NSDictionary?)

	/**
	 * Original signature : `public abstract void removeAllObjects()`<br></br>
	 * *from NSExtendedMutableDictionary native declaration : :72*
	 */
	abstract fun removeAllObjects()

	/**
	 * Original signature : `public abstract void removeObjectsForKeys(NSArray*)`<br></br>
	 * *from NSExtendedMutableDictionary native declaration : :73*
	 */
	abstract fun removeObjectsForKeys(keyArray: NSArray?)

	/**
	 * Original signature : `public abstract void setDictionary(NSDictionary*)`<br></br>
	 * *from NSExtendedMutableDictionary native declaration : :74*
	 */
	abstract fun setDictionary(otherDictionary: NSDictionary?)

	/**
	 * Original signature : `id initWithCapacity(NSUInteger)`<br></br>
	 * *from NSMutableDictionaryCreation native declaration : :81*
	 */
	abstract fun initWithCapacity(numItems: NSUInteger?): NSMutableDictionary?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSMutableDictionary", _Class::class.java)

		fun dictionary(): NSMutableDictionary? {
			return CLASS.dictionaryWithCapacity(NSUInteger(0))
		}

		fun dictionaryWithCapacity(numItems: NSUInteger?): NSMutableDictionary? {
			return CLASS.dictionaryWithCapacity(numItems)
		}

		fun dictionaryWithDictionary(dictionary: NSDictionary?): NSMutableDictionary? {
			return CLASS.dictionaryWithDictionary(dictionary)
		}
	}
}
