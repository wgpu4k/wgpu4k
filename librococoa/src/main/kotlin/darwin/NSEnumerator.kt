package darwin

/// <i>native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSEnumerator.h:33</i>
abstract class NSEnumerator : NSObject() {
	/**
	 * Original signature : `id nextObject()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSEnumerator.h:35*
	 */
	abstract fun nextObject(): NSObject?

	/**
	 * Original signature : `NSArray* allObjects()`<br></br>
	 * *from NSExtendedEnumerator native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSEnumerator.h:41*
	 */
	abstract fun allObjects(): NSArray?
}
