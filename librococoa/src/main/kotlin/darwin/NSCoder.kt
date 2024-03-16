package darwin 

import com.sun.jna.Pointer
import org.rococoa.cocoa.foundation.NSInteger

abstract class NSCoder : NSObject() {
	/**
	 * Original signature : `-(void)encodeValueOfObjCType:(const char*) at:(const void*)`<br></br>
	 * *native declaration : NSCoder.h:12*
	 */
	abstract fun encodeValueOfObjCType_at(type: String?, addr: Pointer?)

	/**
	 * Original signature : `-(void)encodeDataObject:(NSData*)`<br></br>
	 * *native declaration : NSCoder.h:13*
	 */
	abstract fun encodeDataObject(data: NSData?)

	/**
	 * Original signature : `-(void)decodeValueOfObjCType:(const char*) at:(void*)`<br></br>
	 * *native declaration : NSCoder.h:14*
	 */
	abstract fun decodeValueOfObjCType_at(type: String?, data: Pointer?)

	/**
	 * Original signature : `-(NSData*)decodeDataObject`<br></br>
	 * *native declaration : NSCoder.h:15*
	 */
	abstract fun decodeDataObject(): NSData?

	/**
	 * Original signature : `-(NSInteger)versionForClassName:(NSString*)`<br></br>
	 * *native declaration : NSCoder.h:16*
	 */
	abstract fun versionForClassName(className: NSString?): NSInteger? /// <i>native declaration : NSCoder.h</i>
}
