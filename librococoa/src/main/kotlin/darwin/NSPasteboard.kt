package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSInteger

/// <i>native declaration : :52</i>
abstract class NSPasteboard : NSObject() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `NSPasteboard* generalPasteboard()`<br></br>
		 * *native declaration : :65*
		 */
		open fun generalPasteboard(): NSPasteboard?

		/**
		 * Original signature : `NSPasteboard* pasteboardWithName(NSString*)`<br></br>
		 * *native declaration : :66*
		 */
		open fun pasteboardWithName(name: String?): NSPasteboard?

		/**
		 * Original signature : `NSPasteboard* pasteboardWithUniqueName()`<br></br>
		 * *native declaration : :67*
		 */
		open fun pasteboardWithUniqueName(): NSPasteboard?

		/**
		 * Original signature : `NSArray* typesFilterableTo(NSString*)`<br></br>
		 * *native declaration : :69*
		 */
		open fun typesFilterableTo(type: String?): NSArray?

		/**
		 * Original signature : `NSPasteboard* pasteboardByFilteringFile(NSString*)`<br></br>
		 * *native declaration : :71*
		 */
		open fun pasteboardByFilteringFile(filename: String?): NSPasteboard?

		/**
		 * Original signature : `NSPasteboard* pasteboardByFilteringData(NSData*, NSString*)`<br></br>
		 * *native declaration : :72*
		 */
		open fun pasteboardByFilteringData_ofType(data: NSData?, type: String?): NSPasteboard?

		/**
		 * Original signature : `NSPasteboard* pasteboardByFilteringTypesInPasteboard(NSPasteboard*)`<br></br>
		 * *native declaration : :73*
		 */
		open fun pasteboardByFilteringTypesInPasteboard(pboard: NSPasteboard?): NSPasteboard?
	}

	/**
	 * Original signature : `NSString* name()`<br></br>
	 * *native declaration : :75*
	 */
	abstract fun name(): String?

	/**
	 * Original signature : `void releaseGlobally()`<br></br>
	 * *native declaration : :77*
	 */
	abstract fun releaseGlobally()

	fun declareTypes(newTypes: NSArray?, newOwner: org.rococoa.ID?): Int {
		return this.declareTypes_owner(newTypes, newOwner)
	}

	/**
	 * Original signature : `NSInteger declareTypes(NSArray*, id)`<br></br>
	 * *native declaration : :79*
	 */
	abstract fun declareTypes_owner(newTypes: NSArray?, newOwner: org.rococoa.ID?): Int

	fun addTypes(newTypes: NSArray?, newOwner: org.rococoa.ID?): NSInteger? {
		return this.addTypes_owner(newTypes, newOwner)
	}

	/**
	 * Original signature : `NSInteger addTypes(NSArray*, id)`<br></br>
	 * *native declaration : :80*
	 */
	abstract fun addTypes_owner(newTypes: NSArray?, newOwner: org.rococoa.ID?): NSInteger?

	/**
	 * Original signature : `NSInteger changeCount()`<br></br>
	 * *native declaration : :81*
	 */
	abstract fun changeCount(): NSInteger?

	/**
	 * Original signature : `NSArray* types()`<br></br>
	 * *native declaration : :83*
	 */
	abstract fun types(): NSArray?

	/**
	 * Original signature : `NSString* availableTypeFromArray(NSArray*)`<br></br>
	 * *native declaration : :84*
	 */
	abstract fun availableTypeFromArray(types: NSArray?): String?

	/**
	 * Original signature : `BOOL setData(NSData*, NSString*)`<br></br>
	 * *native declaration : :86*
	 */
	abstract fun setData_forType(data: NSData?, dataType: String?): Boolean

	/**
	 * Original signature : `NSData* dataForType(NSString*)`<br></br>
	 * *native declaration : :87*
	 */
	abstract fun dataForType(dataType: String?): NSData?

	/**
	 * Original signature : `BOOL setPropertyList(id, NSString*)`<br></br>
	 * *native declaration : :89*
	 */
	abstract fun setPropertyList_forType(plist: NSObject?, dataType: String?): Boolean

	fun setPropertyListForType(plist: NSObject?, dataType: String?): Boolean {
		return this.setPropertyList_forType(plist, dataType)
	}

	/**
	 * Original signature : `id propertyListForType(NSString*)`<br></br>
	 * *native declaration : :90*
	 */
	abstract fun propertyListForType(dataType: String?): NSObject?

	fun setStringForType(string: String?, dataType: String?): Boolean {
		return this.setString_forType(string, dataType)
	}

	/**
	 * Original signature : `BOOL setString(NSString*, NSString*)`<br></br>
	 * *native declaration : :92*
	 */
	abstract fun setString_forType(string: String?, dataType: String?): Boolean

	/**
	 * Original signature : `NSString* stringForType(NSString*)`<br></br>
	 * *native declaration : :93*
	 */
	abstract fun stringForType(dataType: String?): String?

	/**
	 * Original signature : `BOOL writeFileContents(NSString*)`<br></br>
	 * *from NSFileContents native declaration : :98*
	 */
	abstract fun writeFileContents(filename: String?): Boolean

	/**
	 * Original signature : `NSString* readFileContentsType(NSString*, NSString*)`<br></br>
	 * *from NSFileContents native declaration : :99*
	 */
	abstract fun readFileContentsType_toFile(type: String?, filename: String?): String?

	/**
	 * Original signature : `BOOL writeFileWrapper(NSFileWrapper*)`<br></br>
	 * *from NSFileContents native declaration : :101*
	 */
	abstract fun writeFileWrapper(wrapper: com.sun.jna.Pointer?): Boolean

	/**
	 * Original signature : `NSFileWrapper* readFileWrapper()`<br></br>
	 * *from NSFileContents native declaration : :102*
	 */
	abstract fun readFileWrapper(): com.sun.jna.Pointer?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSPasteboard", _Class::class.java)

		fun generalPasteboard(): NSPasteboard? {
			return CLASS.generalPasteboard()
		}

		fun pasteboardWithName(name: String?): NSPasteboard? {
			return CLASS.pasteboardWithName(name)
		}

		val ColorPboardType: String? = "NSColor pasteboard type"
		val FileContentsPboardType: String? = "NXFileContentsPboardType"
		val FilenamesPboardType: String? = "NSFilenamesPboardType"
		val FontPboardType: String? = "NeXT font pasteboard type"
		val PostScriptPboardType: String? = "NeXT Encapsulated PostScript v1.2 pasteboard type"
		val RulerPboardType: String? = "NeXT ruler pasteboard type"
		val RTFPboardType: String? = "NeXT Rich Text Format v1.0 pasteboard type"
		val RTFDPboardType: String? = "NeXT RTFD pasteboard type"
		val PICTPboardType: String? = "Apple PICT pasteboard type"
		val StringPboardType: String? = "NSStringPboardType"
		val TabularTextPboardType: String? = "NeXT tabular text pasteboard type"
		val TIFFPboardType: String? = "NeXT TIFF v4.0 pasteboard type"
		val URLPboardType: String? = "Apple URL pasteboard type"
		val PDFPboardType: String? = "Apple PDF pasteboard type"
		val HTMLPboardType: String? = "Apple HTML pasteboard type"
		val VCardPboardType: String? = "Apple VCard pasteboard type"
		val FilesPromisePboardType: String? = "Apple files promise pasteboard type"
		val GeneralPboard: String? = "Apple CFPasteboard general"
		val FontPboard: String? = "Apple CFPasteboard font"
		val RulerPboard: String? = "Apple CFPasteboard ruler"
		val FindPboard: String? = "Apple CFPasteboard find"
		val DragPboard: String? = "Apple CFPasteboard drag"
	}
}
