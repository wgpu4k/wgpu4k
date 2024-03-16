package darwin 

import org.rococoa.ObjCClass
import org.rococoa.ObjCObject
import org.rococoa.ObjCObjectByReference
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : :12</i>
abstract class NSFileManager : NSObject() {
	interface _Class : ObjCClass {
		/**
		 * Returns the default singleton CLASS.<br></br> Original signature : `NSFileManager*
		 * defaultManager()`<br></br>
		 * *native declaration : :16*
		 */
		open fun defaultManager(): NSFileManager?
	}

	/**
	 * *native declaration : :22*<br></br>
	 * Conversion Error : / **<br></br> * CLASSs of NSFileManager may now have delegates. Each CLASS has one delegate, and the
	 * delegate is not retained. In versions of Mac OS X prior to 10.5, the behavior of calling [[NSFileManager alloc]
	 * init] was undefined. In Mac OS X 10.5 "Leopard" and later, calling [[NSFileManager alloc] init] returns a new
	 * CLASS of an NSFileManager.<br></br> * Original signature : `void setDelegate(null)`<br></br> * /<br></br> -
	 * (void)setDelegate:(null)delegate; (Argument delegate cannot be converted)
	 */
	abstract fun setDelegate(delegate: org.rococoa.ID?)

	/**
	 * Original signature : `delegate()`<br></br>
	 * *native declaration : :23*
	 */
	abstract fun delegate(): org.rococoa.ID?

	/**
	 * -mountedVolumeURLsIncludingResourceValuesForKeys:options: returns an NSArray of NSURLs locating the mounted
	 * volumes available on the computer. The property keys that can be requested are available in
	 * <Foundation></Foundation>/NSURL.h>.<br></br> Original signature : `-(NSArray*)mountedVolumeURLsIncludingResourceValuesForKeys:(NSArray*)
	 * options:(NSVolumeEnumerationOptions)`<br></br>
	 * *native declaration : NSFileManager.h:69*
	 */
	abstract fun mountedVolumeURLsIncludingResourceValuesForKeys_options(
		propertyKeys: NSArray?,
		options: NSUInteger?
	): NSArray?

	/**
	 * -contentsOfDirectoryAtURL:includingPropertiesForKeys:options:error: returns an NSArray of NSURLs identifying the
	 * the directory entries. If this method returns nil, an NSError will be returned by reference in the 'error'
	 * parameter. If the directory contains no entries, this method will return the empty array. When an array is
	 * specified for the 'keys' parameter, the specified property values will be pre-fetched and cached with each
	 * enumerated URL.<br></br> This method always does a shallow enumeration of the specified directory (i.e. it always acts
	 * as if NSDirectoryEnumerationSkipsSubdirectoryDescendants has been specified). If you need to perform a deep
	 * enumeration, use +[NSFileManager enumeratorAtURL:includingPropertiesForKeys:options:errorHandler:].<br></br> If you
	 * wish to only receive the URLs and no other attributes, then pass '0' for 'options' and an empty NSArray
	 * ('[NSArray array]') for 'keys'. If you wish to have the property caches of the vended URLs pre-populated with a
	 * default set of attributes, then pass '0' for 'options' and 'nil' for 'keys'.<br></br> Original signature :
	 * `-(NSArray*)contentsOfDirectoryAtURL:(NSURL*) includingPropertiesForKeys:(NSArray*)
	 * options:(NSDirectoryEnumerationOptions) error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:77*
	 */
	abstract fun contentsOfDirectoryAtURL_includingPropertiesForKeys_options_error(
		url: NSURL?,
		keys: NSArray?,
		mask: NSUInteger?,
		error: ObjCObjectByReference?
	): NSArray?
	/**
	 * *native declaration : NSFileManager.h:82*<br></br>
	 * Conversion Error : / **<br></br>
	 * * -URLsForDirectory:inDomains: is analogous to NSSearchPathForDirectoriesInDomains(), but returns an array of NSURL instances for use with URL-taking APIs. This API is suitable when you need to search for a file or files which may live in one of a variety of locations in the domains specified.<br></br>
	 * * Original signature : `-(NSArray*)URLsForDirectory:() inDomains:()`<br></br>
	 * * /<br></br>
	 * - (NSArray*)URLsForDirectory:(null)directory inDomains:(null)domainMask; (Argument directory cannot be converted)
	 */
	/**
	 * *native declaration : NSFileManager.h:88*<br></br>
	 * Conversion Error : / **<br></br>
	 * * -URLForDirectory:inDomain:appropriateForURL:create:error: is a URL-based replacement for FSFindFolder(). It allows for the specification and (optional) creation of a specific directory for a particular purpose (e.g. the replacement of a particular item on disk, or a particular Library directory.<br></br>
	 * * You may pass only one of the values from the NSSearchPathDomainMask enumeration, and you may not pass NSAllDomainsMask.<br></br>
	 * * Original signature : `-(NSURL*)URLForDirectory:() inDomain:() appropriateForURL:(NSURL*) create:(BOOL) error:(NSError**)`<br></br>
	 * * /<br></br>
	 * - (NSURL*)URLForDirectory:(null)directory inDomain:(null)domain appropriateForURL:(NSURL*)url create:(BOOL)shouldCreate error:(NSError**)error; (Argument directory cannot be converted)
	 */
	/**
	 * Instances of NSFileManager may now have delegates. Each instance has one delegate, and the delegate is not
	 * retained. In versions of Mac OS X prior to 10.5, the behavior of calling [[NSFileManager alloc] init] was
	 * undefined. In Mac OS X 10.5 "Leopard" and later, calling [[NSFileManager alloc] init] returns a new instance of
	 * an NSFileManager.<br></br> Original signature : `-(void)setDelegate:(id)`<br></br>
	 * *native declaration : NSFileManager.h:94*
	 */
	abstract fun setDelegate(delegate: ObjCObject?)

	/**
	 * setAttributes:ofItemAtPath:error: returns YES when the attributes specified in the 'attributes' dictionary are
	 * set successfully on the item specified by 'path'. If this method returns NO, a presentable NSError will be
	 * provided by-reference in the 'error' parameter. If no error is required, you may pass 'nil' for the error.<br></br>
	 * This method replaces changeFileAttributes:atPath:.<br></br> Original signature : `-(BOOL)setAttributes:(NSDictionary*)
	 * ofItemAtPath:(String*) error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:101*
	 */
	abstract fun setAttributes_ofItemAtPath_error(
		attributes: NSDictionary?,
		path: String?,
		error: ObjCObjectByReference?
	): Boolean

	/**
	 * createDirectoryAtPath:withIntermediateDirectories:attributes:error: creates a directory at the specified path. If
	 * you pass 'NO' for createIntermediates, the directory must not exist at the time this call is made. Passing 'YES'
	 * for 'createIntermediates' will create any necessary intermediate directories. This method returns YES if all
	 * directories specified in 'path' were created and attributes were set. Directories are created with attributes
	 * specified by the dictionary passed to 'attributes'. If no dictionary is supplied, directories are created
	 * according to the umask of the process. This method returns NO if a failure occurs at any stage of the operation.
	 * If an error parameter was provided, a presentable NSError will be returned by reference.<br></br> This method replaces
	 * createDirectoryAtPath:attributes:<br></br> Original signature : `-(BOOL)createDirectoryAtPath:(String*)
	 * withIntermediateDirectories:(BOOL) attributes:(NSDictionary*) error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:107*
	 */
	abstract fun createDirectoryAtPath_withIntermediateDirectories_attributes_error(
		path: String?,
		createIntermediates: Boolean,
		attributes: NSDictionary?,
		error: ObjCObjectByReference?
	): Boolean

	/**
	 * contentsOfDirectoryAtPath:error: returns an NSArray of Strings representing the filenames of the items in the
	 * directory. If this method returns 'nil', an NSError will be returned by reference in the 'error' parameter. If
	 * the directory contains no items, this method will return the empty array.<br></br> This method replaces
	 * directoryContentsAtPath:<br></br> Original signature : `-(NSArray*)contentsOfDirectoryAtPath:(String*)
	 * error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:113*
	 */
	abstract fun contentsOfDirectoryAtPath_error(path: String?, error: ObjCObjectByReference?): NSArray?

	/**
	 * subpathsOfDirectoryAtPath:error: returns an NSArray of Strings represeting the filenames of the items in the
	 * specified directory and all its subdirectories recursively. If this method returns 'nil', an NSError will be
	 * returned by reference in the 'error' parameter. If the directory contains no items, this method will return the
	 * empty array.<br></br> This method replaces subpathsAtPath:<br></br> Original signature :
	 * `-(NSArray*)subpathsOfDirectoryAtPath:(String*) error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:119*
	 */
	abstract fun subpathsOfDirectoryAtPath_error(path: String?, error: ObjCObjectByReference?): NSArray?

	/**
	 * attributesOfItemAtPath:error: returns an NSDictionary of key/value pairs containing the attributes of the item
	 * (file, directory, symlink, etc.) at the path in question. If this method returns 'nil', an NSError will be
	 * returned by reference in the 'error' parameter. This method does not traverse a terminal symlink.<br></br> This method
	 * replaces fileAttributesAtPath:traverseLink:.<br></br> Original signature : `-(NSDictionary*)attributesOfItemAtPath:(String*)
	 * error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:125*
	 */
	abstract fun attributesOfItemAtPath_error(path: String?, error: ObjCObjectByReference?): NSDictionary?

	/**
	 * attributesOfFileSystemForPath:error: returns an NSDictionary of key/value pairs containing the attributes of the
	 * filesystem containing the provided path. If this method returns 'nil', an NSError will be returned by reference
	 * in the 'error' parameter. This method does not traverse a terminal symlink.<br></br> This method replaces
	 * fileSystemAttributesAtPath:.<br></br> Original signature : `-(NSDictionary*)attributesOfFileSystemForPath:(String*)
	 * error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:131*
	 */
	abstract fun attributesOfFileSystemForPath_error(path: String?, error: ObjCObjectByReference?): NSDictionary?

	/**
	 * createSymbolicLinkAtPath:withDestination:error: returns YES if the symbolic link that point at 'destPath' was
	 * able to be created at the location specified by 'path'. If this method returns NO, the link was unable to be
	 * created and an NSError will be returned by reference in the 'error' parameter. This method does not traverse a
	 * terminal symlink.<br></br> This method replaces createSymbolicLinkAtPath:pathContent:<br></br> Original signature :
	 * `-(BOOL)createSymbolicLinkAtPath:(String*) withDestinationPath:(String*) error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:137*
	 */
	abstract fun createSymbolicLinkAtPath_withDestinationPath_error(
		path: String?,
		destPath: String?,
		error: ObjCObjectByReference?
	): Boolean

	/**
	 * destinationOfSymbolicLinkAtPath:error: returns an String containing the path of the item pointed at by the
	 * symlink specified by 'path'. If this method returns 'nil', an NSError will be returned by reference in the
	 * 'error' parameter.<br></br> This method replaces pathContentOfSymbolicLinkAtPath:<br></br> Original signature :
	 * `-(String*)destinationOfSymbolicLinkAtPath:(String*) error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:143*
	 */
	abstract fun destinationOfSymbolicLinkAtPath_error(path: String?, error: ObjCObjectByReference?): String?

	/**
	 * These methods replace their non-error returning counterparts below. See the NSFileManagerFileOperationAdditions
	 * category below for methods that are dispatched to the NSFileManager instance's delegate.<br></br> Original signature
	 * :
	 * `-(BOOL)copyItemAtPath:(String*) toPath:(String*) error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:147*
	 */
	abstract fun copyItemAtPath_toPath_error(srcPath: String?, dstPath: String?, error: ObjCObjectByReference?): Boolean

	/**
	 * Original signature : `-(BOOL)moveItemAtPath:(String*) toPath:(String*) error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:148*
	 */
	abstract fun moveItemAtPath_toPath_error(srcPath: String?, dstPath: String?, error: ObjCObjectByReference?): Boolean

	/**
	 * Original signature : `-(BOOL)linkItemAtPath:(String*) toPath:(String*) error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:149*
	 */
	abstract fun linkItemAtPath_toPath_error(srcPath: String?, dstPath: String?, error: ObjCObjectByReference?): Boolean

	/**
	 * Original signature : `-(BOOL)removeItemAtPath:(String*) error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:150*
	 */
	abstract fun removeItemAtPath_error(path: String?, error: ObjCObjectByReference?): Boolean

	/**
	 * These methods are URL-taking equivalents of the four methods above. Their delegate methods are defined in the
	 * NSFileManagerFileOperationAdditions category below.<br></br> Original signature : `-(BOOL)copyItemAtURL:(NSURL*)
	 * toURL:(NSURL*) error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:156*
	 */
	abstract fun copyItemAtURL_toURL_error(srcURL: NSURL?, dstURL: NSURL?, error: ObjCObjectByReference?): Boolean

	/**
	 * Original signature : `-(BOOL)moveItemAtURL:(NSURL*) toURL:(NSURL*) error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:157*
	 */
	abstract fun moveItemAtURL_toURL_error(srcURL: NSURL?, dstURL: NSURL?, error: ObjCObjectByReference?): Boolean

	/**
	 * Original signature : `-(BOOL)linkItemAtURL:(NSURL*) toURL:(NSURL*) error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:158*
	 */
	abstract fun linkItemAtURL_toURL_error(srcURL: NSURL?, dstURL: NSURL?, error: ObjCObjectByReference?): Boolean

	/**
	 * Original signature : `-(BOOL)removeItemAtURL:(NSURL*) error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:159*
	 */
	abstract fun removeItemAtURL_error(URL: NSURL?, error: ObjCObjectByReference?): Boolean

	/**
	 * The following methods are deprecated on Mac OS X 10.5. Their URL-based and/or error-returning replacements are
	 * listed above.<br></br> Original signature : `-(NSDictionary*)fileAttributesAtPath:(String*)
	 * traverseLink:(BOOL)`<br></br>
	 * *native declaration : NSFileManager.h:163*
	 */
	abstract fun fileAttributesAtPath_traverseLink(path: String?, yorn: Boolean): NSDictionary?

	/**
	 * Original signature : `-(BOOL)changeFileAttributes:(NSDictionary*) atPath:(String*)`<br></br>
	 * *native declaration : NSFileManager.h:164*
	 */
	abstract fun changeFileAttributes_atPath(attributes: NSDictionary?, path: String?): Boolean

	/**
	 * Original signature : `-(NSArray*)directoryContentsAtPath:(String*)`<br></br>
	 * *native declaration : NSFileManager.h:165*
	 */
	abstract fun directoryContentsAtPath(path: String?): NSArray?

	/**
	 * Original signature : `-(NSDictionary*)fileSystemAttributesAtPath:(String*)`<br></br>
	 * *native declaration : NSFileManager.h:166*
	 */
	abstract fun fileSystemAttributesAtPath(path: String?): NSDictionary?

	/**
	 * Original signature : `-(String*)pathContentOfSymbolicLinkAtPath:(String*)`<br></br>
	 * *native declaration : NSFileManager.h:167*
	 */
	abstract fun pathContentOfSymbolicLinkAtPath(path: String?): String?

	/**
	 * Original signature : `-(BOOL)createSymbolicLinkAtPath:(String*) pathContent:(String*)`<br></br>
	 * *native declaration : NSFileManager.h:168*
	 */
	abstract fun createSymbolicLinkAtPath_pathContent(path: String?, otherpath: String?): Boolean

	/**
	 * Original signature : `-(BOOL)createDirectoryAtPath:(String*) attributes:(NSDictionary*)`<br></br>
	 * *native declaration : NSFileManager.h:169*
	 */
	abstract fun createDirectoryAtPath_attributes(path: String?, attributes: NSDictionary?): Boolean

	/**
	 * Original signature : `-(BOOL)linkPath:(String*) toPath:(String*) handler:(id)`<br></br>
	 * *native declaration : NSFileManager.h:172*
	 */
	abstract fun linkPath_toPath_handler(src: String?, dest: String?, handler: ObjCObject?): Boolean

	/**
	 * Original signature : `-(BOOL)copyPath:(String*) toPath:(String*) handler:(id)`<br></br>
	 * *native declaration : NSFileManager.h:173*
	 */
	abstract fun copyPath_toPath_handler(src: String?, dest: String?, handler: ObjCObject?): Boolean

	/**
	 * Original signature : `-(BOOL)movePath:(String*) toPath:(String*) handler:(id)`<br></br>
	 * *native declaration : NSFileManager.h:174*
	 */
	abstract fun movePath_toPath_handler(src: String?, dest: String?, handler: ObjCObject?): Boolean

	/**
	 * Original signature : `-(BOOL)removeFileAtPath:(String*) handler:(id)`<br></br>
	 * *native declaration : NSFileManager.h:175*
	 */
	abstract fun removeFileAtPath_handler(path: String?, handler: ObjCObject?): Boolean

	/**
	 * Process working directory management. Despite the fact that these are instance methods on NSFileManager, these
	 * methods report and change (respectively) the working directory for the entire process. Developers are cautioned
	 * that doing so is fraught with peril.<br></br> Original signature : `-(String*)currentDirectoryPath`<br></br>
	 * *native declaration : NSFileManager.h:180*
	 */
	abstract fun currentDirectoryPath(): String?

	/**
	 * Original signature : `-(BOOL)changeCurrentDirectoryPath:(String*)`<br></br>
	 * *native declaration : NSFileManager.h:181*
	 */
	abstract fun changeCurrentDirectoryPath(path: String?): Boolean

	/**
	 * The following methods are of limited utility. Attempting to predicate behavior based on the current state of the
	 * filesystem or a particular file on the filesystem is encouraging odd behavior in the face of filesystem race
	 * conditions. It's far better to attempt an operation (like loading a file or creating a directory) and handle the
	 * error gracefully than it is to try to figure out ahead of time whether the operation will succeed.<br></br> Original
	 * signature : `-(BOOL)fileExistsAtPath:(String*)`<br></br>
	 * *native declaration : NSFileManager.h:185*
	 */
	abstract fun fileExistsAtPath(path: String?): Boolean

	/**
	 * Original signature : `-(BOOL)fileExistsAtPath:(String*) isDirectory:(BOOL*)`<br></br>
	 * *native declaration : NSFileManager.h:186*
	 */
	abstract fun fileExistsAtPath_isDirectory(path: String?, isDirectory: Boolean): Boolean

	/**
	 * Original signature : `-(BOOL)isReadableFileAtPath:(String*)`<br></br>
	 * *native declaration : NSFileManager.h:187*
	 */
	abstract fun isReadableFileAtPath(path: String?): Boolean

	/**
	 * Original signature : `-(BOOL)isWritableFileAtPath:(String*)`<br></br>
	 * *native declaration : NSFileManager.h:188*
	 */
	abstract fun isWritableFileAtPath(path: String?): Boolean

	/**
	 * Original signature : `-(BOOL)isExecutableFileAtPath:(String*)`<br></br>
	 * *native declaration : NSFileManager.h:189*
	 */
	abstract fun isExecutableFileAtPath(path: String?): Boolean

	/**
	 * Original signature : `-(BOOL)isDeletableFileAtPath:(String*)`<br></br>
	 * *native declaration : NSFileManager.h:190*
	 */
	abstract fun isDeletableFileAtPath(path: String?): Boolean

	/**
	 * -contentsEqualAtPath:andPath: does not take into account data stored in the resource fork or filesystem extended
	 * attributes.<br></br> Original signature : `-(BOOL)contentsEqualAtPath:(String*) andPath:(String*)`<br></br>
	 * *native declaration : NSFileManager.h:194*
	 */
	abstract fun contentsEqualAtPath_andPath(path1: String?, path2: String?): Boolean

	/**
	 * displayNameAtPath: returns an String suitable for presentation to the user. For directories which have
	 * localization information, this will return the appropriate localized string. This string is not suitable for
	 * passing to anything that must interact with the filesystem.<br></br> Original signature :
	 * `-(String*)displayNameAtPath:(String*)`<br></br>
	 * *native declaration : NSFileManager.h:198*
	 */
	abstract fun displayNameAtPath(path: String?): String?

	/**
	 * componentsToDisplayForPath: returns an NSArray of display names for the path provided. Localization will occur as
	 * in displayNameAtPath: above. This array cannot and should not be reassembled into an usable filesystem path for
	 * any kind of access.<br></br> Original signature : `-(NSArray*)componentsToDisplayForPath:(String*)`<br></br>
	 * *native declaration : NSFileManager.h:203*
	 */
	abstract fun componentsToDisplayForPath(path: String?): NSArray?

	/**
	 * subpathsAtPath: returns an NSArray of all contents and subpaths recursively from the provided path. This may be
	 * very expensive to compute for deep filesystem hierarchies, and should probably be avoided.<br></br> Original signature
	 * : `-(NSArray*)subpathsAtPath:(String*)`<br></br>
	 * *native declaration : NSFileManager.h:220*
	 */
	abstract fun subpathsAtPath(path: String?): NSArray?

	/**
	 * These methods are provided here for compatibility. The corresponding methods on NSData which return NSErrors
	 * should be regarded as the primary method of creating a file from an NSData or retrieving the contents of a file
	 * as an NSData.<br></br> Original signature : `-(NSData*)contentsAtPath:(String*)`<br></br>
	 * *native declaration : NSFileManager.h:224*
	 */
	abstract fun contentsAtPath(path: String?): NSData?

	/**
	 * Original signature : `-(BOOL)createFileAtPath:(String*) contents:(NSData*)
	 * attributes:(NSDictionary*)`<br></br>
	 * *native declaration : NSFileManager.h:225*
	 */
	abstract fun createFileAtPath_contents_attributes(path: String?, data: NSData?, attr: NSDictionary?): Boolean

	/**
	 * fileSystemRepresentationWithPath: returns an array of characters suitable for passing to lower-level POSIX style
	 * APIs. The string is provided in the representation most appropriate for the filesystem in question.<br></br> Original
	 * signature : `-(const char*)fileSystemRepresentationWithPath:(String*)`<br></br>
	 * *native declaration : NSFileManager.h:229*
	 */
	abstract fun fileSystemRepresentationWithPath(path: String?): String?

	/**
	 * stringWithFileSystemRepresentation:length: returns an String created from an array of bytes that are in the
	 * filesystem representation.<br></br> Original signature : `-(String*)stringWithFileSystemRepresentation:(const
	 * char*) length:(NSUInteger)`<br></br>
	 * *native declaration : NSFileManager.h:233*
	 */
	abstract fun stringWithFileSystemRepresentation_length(str: String?, len: NSUInteger?): String?

	/**
	 * -replaceItemAtURL:withItemAtURL:backupItemName:options:resultingItemURL:error: is for developers who wish to
	 * perform a safe-save without using the full NSDocument machinery that is available in the AppKit.<br></br> The
	 * `originalItemURL` is the item being replaced.<br></br> `newItemURL` is the item which will replace the original item.
	 * This item should be placed in a temporary directory as provided by the OS, or in a uniquely named directory
	 * placed in the same directory as the original item if the temporary directory is not available.<br></br> If
	 * `backupItemName` is provided, that name will be used to create a backup of the original item. The backup is
	 * placed in the same directory as the original item. If an error occurs during the creation of the backup item, the
	 * operation will fail. If there is already an item with the same name as the backup item, that item will be
	 * removed. The backup item will be removed in the event of success unless the `NSFileManagerItemReplacementWithoutDeletingBackupItem`
	 * option is provided in `options`.<br></br> For `options`, pass `0` to get the default behavior, which uses only the
	 * metadata from the new item while adjusting some properties using values from the original item. Pass
	 * `NSFileManagerItemReplacementUsingNewMetadataOnly` in order to use all possible metadata from the new item.<br></br>
	 * Original signature : `-(BOOL)replaceItemAtURL:(NSURL*) withItemAtURL:(NSURL*) backupItemName:(String*)
	 * options:(NSFileManagerItemReplacementOptions) resultingItemURL:(NSURL**) error:(NSError**)`<br></br>
	 * *native declaration : NSFileManager.h:242*
	 */
	abstract fun replaceItemAtURL_withItemAtURL_backupItemName_options_resultingItemURL_error(
		originalItemURL: NSURL?,
		newItemURL: NSURL?,
		backupItemName: String?,
		options: NSUInteger?,
		resultingURL: ObjCObjectByReference?,
		error: ObjCObjectByReference?
	): Boolean

	/// <i>native declaration : NSFileManager.h</i>
	/**
	 * As explained in App Sandbox Design Guide, groups of sandboxed apps that need to share files and other information
	 * can request a container directory as part of their entitlements. These directories are stored in ~/Library/Group
	 * Containers/.
	 *
	 *
	 *
	 * @return When called with a valid group identifier, this method returns the location of that directory as a n
	 * NSURL object. This method also creates the directory if it does not yet exist.
	 */
	abstract fun containerURLForSecurityApplicationGroupIdentifier(groupIdentifier: String?): NSURL?

	/**
	 * Moves an item to the trash.
	 *
	 * @param url             The item to move to the trash.
	 * @param outResultingURL On input, a pointer to a URL object. On output, this pointer is set to the item’s location
	 * in the trash. The actual name of the item may be changed when moving it to the trash, so
	 * use this URL to access it. You may specify nil for this parameter if you do not want the
	 * information.
	 * @param error           On input, a pointer to an error object. If an error occurs, this pointer is set to an
	 * actual error object containing the error information. You may specify nil for this
	 * parameter if you do not want the error information.
	 * @return YES if the item at url was successfully moved to the trash, or NO if the item was not moved to the trash.
	 */
	abstract fun trashItemAtURL_resultingItemURL_error(
		url: NSURL?,
		outResultingURL: NSURL?,
		error: ObjCObjectByReference?
	): Boolean

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSFileManager", _Class::class.java)

		fun defaultManager(): NSFileManager? {
			return CLASS.defaultManager()
		}

		val NSFileType: String? = "NSFileType"
		val NSFileTypeDirectory: String? = "NSFileTypeDirectory"
		val NSFileTypeRegular: String? = "NSFileTypeRegular"
		val NSFileTypeSymbolicLink: String? = "NSFileTypeSymbolicLink"
		val NSFileTypeSocket: String? = "NSFileTypeSocket"
		val NSFileTypeCharacterSpecial: String? = "NSFileTypeCharacterSpecial"
		val NSFileTypeBlockSpecial: String? = "NSFileTypeBlockSpecial"
		val NSFileTypeUnknown: String? = "NSFileTypeUnknown"
		val NSFileSize: String? = "NSFileSize"
		val NSFileModificationDate: String? = "NSFileModificationDate"
		val NSFileReferenceCount: String? = "NSFileReferenceCount"
		val NSFileDeviceIdentifier: String? = "NSFileDeviceIdentifier"
		val NSFileOwnerAccountName: String? = "NSFileOwnerAccountName"
		val NSFileGroupOwnerAccountName: String? = "NSFileGroupOwnerAccountName"
		val NSFilePosixPermissions: String? = "NSFilePosixPermissions"
		val NSFileSystemNumber: String? = "NSFileSystemNumber"
		val NSFileSystemFileNumber: String? = "NSFileSystemFileNumber"
		val NSFileExtensionHidden: String? = "NSFileExtensionHidden"
		val NSFileHFSCreatorCode: String? = "NSFileHFSCreatorCode"
		val NSFileHFSTypeCode: String? = "NSFileHFSTypeCode"
		val NSFileImmutable: String? = "NSFileImmutable"
		val NSFileAppendOnly: String? = "NSFileAppendOnly"
		val NSFileCreationDate: String? = "NSFileCreationDate"
		val NSFileOwnerAccountID: String? = "NSFileOwnerAccountID"
		val NSFileGroupOwnerAccountID: String? = "NSFileGroupOwnerAccountID"
		val NSFileSystemSize: String? = "NSFileSystemSize"
		val NSFileSystemFreeSize: String? = "NSFileSystemFreeSize"
		val NSFileSystemNodes: String? = "NSFileSystemNodes"
		val NSFileSystemFreeNodes: String? = "NSFileSystemFreeNodes"
	}
}
