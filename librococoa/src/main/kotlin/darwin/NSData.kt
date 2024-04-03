package darwin 

import org.rococoa.ObjCClass
import org.rococoa.ObjCObjectByReference
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : :27</i>
abstract class NSData : NSObject() {
	interface _Class : ObjCClass {
		open fun alloc(): NSData

		/**
		 * Original signature : `data()`<br></br>
		 * *from NSDataCreation native declaration : :53*
		 */
		open fun data(): NSData?

		/**
		 * Original signature : `dataWithBytes(const void*, NSUInteger)`<br></br>
		 * *from NSDataCreation native declaration : :54*
		 */
		open fun dataWithBytes_length(bytes: com.sun.jna.Pointer?, length: NSUInteger?): NSData?

		/**
		 * Original signature : `dataWithBytesNoCopy(void*, NSUInteger)`<br></br>
		 * *from NSDataCreation native declaration : :55*
		 */
		open fun dataWithBytesNoCopy_length(bytes: com.sun.jna.Pointer?, length: NSUInteger?): NSData?

		/**
		 * Original signature : `dataWithBytesNoCopy(void*, NSUInteger, BOOL)`<br></br>
		 * *from NSDataCreation native declaration : :57*
		 */
		open fun dataWithBytesNoCopy_length_freeWhenDone(
			bytes: com.sun.jna.Pointer?,
			length: NSUInteger?,
			b: Byte
		): NSData?

		/**
		 * Original signature : `dataWithContentsOfFile(NSString*, NSUInteger, NSError**)`<br></br>
		 * *from NSDataCreation native declaration : :60*
		 */
		open fun dataWithContentsOfFile_options_error(
			path: String?,
			readOptionsMask: Int,
			errorPtr: ObjCObjectByReference?
		): NSData?

		/**
		 * Original signature : `dataWithContentsOfURL(NSURL*, NSUInteger, NSError**)`<br></br>
		 * *from NSDataCreation native declaration : :61*
		 */
		open fun dataWithContentsOfURL_options_error(
			url: NSURL?,
			readOptionsMask: Int,
			errorPtr: ObjCObjectByReference?
		): NSData?

		/**
		 * Original signature : `dataWithContentsOfFile(NSString*)`<br></br>
		 * *from NSDataCreation native declaration : :63*
		 */
		open fun dataWithContentsOfFile(path: String?): NSData?

		/**
		 * Original signature : `dataWithContentsOfURL(NSURL*)`<br></br>
		 * *from NSDataCreation native declaration : :64*
		 */
		open fun dataWithContentsOfURL(url: NSURL?): NSData?

		/**
		 * Original signature : `dataWithContentsOfMappedFile(NSString*)`<br></br>
		 * *from NSDataCreation native declaration : :65*
		 */
		open fun dataWithContentsOfMappedFile(path: String?): NSData?

		/**
		 * NSData+Base64
		 */
		open fun dataWithBase64EncodedString(string: String?): NSData?

		/**
		 * Original signature : `dataWithData(NSData*)`<br></br>
		 * *from NSDataCreation native declaration : :79*
		 */
		open fun dataWithData(data: NSData?): NSData?
	}

	/**
	 * Original signature : `NSUInteger length()`<br></br>
	 * *native declaration : :29*
	 */
	abstract fun length(): NSUInteger?

	/**
	 * Original signature : `const void* bytes()`<br></br>
	 * *native declaration : :30*
	 */
	abstract fun bytes(): com.sun.jna.Pointer?

	/**
	 * Original signature : `NSString* description()`<br></br>
	 * *from NSExtendedData native declaration : :36*
	 */
	abstract override fun description(): String?

	/**
	 * Original signature : `void getBytes(void*)`<br></br>
	 * *from NSExtendedData native declaration : :37*
	 */
	abstract fun getBytes(buffer: com.sun.jna.Pointer?)

	/**
	 * Original signature : `void getBytes(void*, NSUInteger)`<br></br>
	 * *from NSExtendedData native declaration : :38*
	 */
	abstract fun getBytes_length(buffer: com.sun.jna.Pointer?, length: NSUInteger?)
	/**
	 * *from NSExtendedData native declaration : :39*<br></br>
	 * Conversion Error : /// Original signature : `void getBytes(void*, null)`<br></br>
	 * - (void)getBytes:(void*)buffer range:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `BOOL isEqualToData(NSData*)`<br></br>
	 * *from NSExtendedData native declaration : :40*
	 */
	abstract fun isEqualToData(other: NSData?): Boolean
	/**
	 * *from NSExtendedData native declaration : :41*<br></br>
	 * Conversion Error : /// Original signature : `NSData* subdataWithRange(null)`<br></br>
	 * - (NSData*)subdataWithRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `BOOL writeToFile(NSString*, BOOL)`<br></br>
	 * *from NSExtendedData native declaration : :42*
	 */
	abstract fun writeToFile_atomically(path: String?, useAuxiliaryFile: Boolean): Boolean

	fun writeToFile(path: String?): Boolean {
		return this.writeToFile_atomically(path, true)
	}

	/**
	 * Original signature : `BOOL writeToURL(NSURL*, BOOL)`<br></br>
	 * the atomically flag is ignored if the url is not of a type the supports atomic writes<br></br>
	 * *from NSExtendedData native declaration : :43*
	 */
	abstract fun writeToURL_atomically(url: NSURL?, atomically: Boolean): Boolean

	fun writeToURL(url: NSURL?): Boolean {
		return this.writeToURL_atomically(url, true)
	}

	/**
	 * Original signature : `BOOL writeToFile(NSString*, NSUInteger, NSError**)`<br></br>
	 * *from NSExtendedData native declaration : :45*
	 */
	abstract fun writeToFile_options_error(
		path: String?,
		writeOptionsMask: Int,
		errorPtr: ObjCObjectByReference?
	): Boolean

	/**
	 * Original signature : `BOOL writeToURL(NSURL*, NSUInteger, NSError**)`<br></br>
	 * *from NSExtendedData native declaration : :46*
	 */
	abstract fun writeToURL_options_error(url: NSURL?, writeOptionsMask: Int, errorPtr: ObjCObjectByReference?): Boolean

	/**
	 * Original signature : `initWithBytes(const void*, NSUInteger)`<br></br>
	 * *from NSDataCreation native declaration : :66*
	 */
	abstract fun initWithBytes_length(bytes: com.sun.jna.Pointer?, length: NSUInteger?): NSData?

	/**
	 * Original signature : `initWithBytesNoCopy(void*, NSUInteger)`<br></br>
	 * *from NSDataCreation native declaration : :67*
	 */
	abstract fun initWithBytesNoCopy_length(bytes: com.sun.jna.Pointer?, length: Int): NSData?

	/**
	 * Original signature : `initWithBytesNoCopy(void*, NSUInteger, BOOL)`<br></br>
	 * *from NSDataCreation native declaration : :69*
	 */
	abstract fun initWithBytesNoCopy_length_freeWhenDone(
		bytes: com.sun.jna.Pointer?,
		length: NSUInteger?,
		b: Byte
	): NSData?

	/**
	 * Original signature : `initWithContentsOfFile(NSString*, NSUInteger, NSError**)`<br></br>
	 * *from NSDataCreation native declaration : :72*
	 */
	abstract fun initWithContentsOfFile_options_error(
		path: String?,
		readOptionsMask: Int,
		errorPtr: ObjCObjectByReference?
	): NSData?

	/**
	 * Original signature : `initWithContentsOfURL(NSURL*, NSUInteger, NSError**)`<br></br>
	 * *from NSDataCreation native declaration : :73*
	 */
	abstract fun initWithContentsOfURL_options_error(
		url: NSURL?,
		readOptionsMask: Int,
		errorPtr: ObjCObjectByReference?
	): NSData?

	/**
	 * Original signature : `initWithContentsOfFile(NSString*)`<br></br>
	 * *from NSDataCreation native declaration : :75*
	 */
	abstract fun initWithContentsOfFile(path: String?): NSData?

	/**
	 * Original signature : `initWithContentsOfURL(NSURL*)`<br></br>
	 * *from NSDataCreation native declaration : :76*
	 */
	abstract fun initWithContentsOfURL(url: NSURL?): NSData?

	/**
	 * Original signature : `initWithContentsOfMappedFile(NSString*)`<br></br>
	 * *from NSDataCreation native declaration : :77*
	 */
	abstract fun initWithContentsOfMappedFile(path: String?): NSData?

	/**
	 * Original signature : `initWithData(NSData*)`<br></br>
	 * *from NSDataCreation native declaration : :78*
	 */
	abstract fun initWithData(data: NSData?): NSData?

	/**
	 * Returns a data object initialized with the given Base-64 encoded string
	 */
	abstract fun initWithBase64Encoding(base64String: String?): NSData?

	/**
	 * Create a Base-64 encoded NSString from the receiver's contents
	 */
	abstract fun base64Encoding(): String?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSData", _Class::class.java)

		fun dataWithContentsOfURL(url: NSURL?): NSData? {
			return CLASS.dataWithContentsOfURL(url)
		}

		fun dataWithBase64EncodedString(base64String: String?): NSData? {
			return CLASS.alloc().initWithBase64Encoding(base64String)
		}
	}
}

