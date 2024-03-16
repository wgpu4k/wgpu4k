package darwin 

import com.sun.jna.Pointer
import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSInteger
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : :43</i>
abstract class NSWorkspace : NSObject() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `NSWorkspace* sharedWorkspace)`<br></br>
		 * *native declaration : :54*
		 */
		open fun sharedWorkspace(): NSWorkspace?
	}

	/**
	 * Original signature : `NSNotificationCenter* notificationCenter)`<br></br>
	 * *native declaration : :56*
	 */
	abstract fun notificationCenter(): NSNotificationCenter?

	/**
	 * Original signature : `BOOL openFile(NSString*)`<br></br>
	 * *native declaration : :58*
	 */
	abstract fun openFile(fullPath1: String?): Boolean

	/**
	 * Original signature : `BOOL openFile(NSString*, NSString*)`<br></br>
	 * *native declaration : :59*
	 */
	abstract fun openFile_withApplication(fullPath1: String?, appName2: String?): Boolean

	fun openFile(fullPath1: String?, appName2: String?): Boolean {
		return this.openFile_withApplication(fullPath1, appName2)
	}

	/**
	 * Original signature : `BOOL openFile(NSString*, NSString*, BOOL)`<br></br>
	 * *native declaration : :60*
	 */
	abstract fun openFile_withApplication_andDeactivate(appName2: String?, flag3: Boolean): Boolean

	/**
	 * Original signature : `BOOL openTempFile(NSString*)`<br></br>
	 * *native declaration : :62*
	 */
	abstract fun openTempFile(fullPath1: String?): Boolean
	/**
	 * *native declaration : :64*<br></br>
	 * Conversion Error : NSPoint
	 */
	/**
	 * Original signature : `BOOL openURL(NSURL*)`<br></br>
	 * *native declaration : :66*
	 */
	abstract fun openURL(url1: NSURL?): Boolean

	/**
	 * Original signature : `BOOL launchApplication(NSString*)`<br></br>
	 * *native declaration : :68*
	 */
	abstract fun launchApplication(appName1: String?): Boolean

	/**
	 * Original signature : `BOOL launchApplication(NSString*, BOOL, BOOL)`<br></br>
	 * *native declaration : :69*
	 */
	abstract fun launchApplication_showIcon_autolaunch(
		appName1: String?,
		showIcon2: Boolean,
		autolaunch3: Boolean
	): Boolean

	/**
	 * Original signature : `NSString* fullPathForApplication(NSString*)`<br></br>
	 * *native declaration : :70*
	 */
	abstract fun fullPathForApplication(appName1: String?): String?

	/**
	 * Original signature : `BOOL selectFile(NSString*, NSString*)`<br></br>
	 * *native declaration : :72*
	 */
	abstract fun selectFile_inFileViewerRootedAtPath(fullpath: String?, rootpath: String?): Boolean

	/**
	 * @param fullpath The full path of the file to select.
	 * @param rootpath If a path is specified, a new file viewer is opened. If you specify an
	 * empty string (@"") for this parameter, the file is selected in the main viewer.
	 * @return YES if the file was successfully selected; otherwise, NO.
	 */
	fun selectFile(fullpath: String?, rootpath: String?): Boolean {
		return selectFile_inFileViewerRootedAtPath(fullpath, rootpath)
	}

	/**
	 * Original signature : `void findApplications)`<br></br>
	 * *native declaration : :74*
	 */
	abstract fun findApplications()

	/**
	 * Original signature : `public abstract void noteFileSystemChanged)`<br></br>
	 * *native declaration : :76*
	 */
	abstract fun noteFileSystemChanged()

	/**
	 * Original signature : `public abstract void noteFileSystemChanged(NSString*)`<br></br>
	 * *native declaration : :77*
	 */
	abstract fun noteFileSystemChanged(path1: String?)

	/**
	 * Original signature : `BOOL fileSystemChanged)`<br></br>
	 * *native declaration : :78*
	 */
	abstract fun fileSystemChanged(): Boolean

	/**
	 * Original signature : `public abstract void noteUserDefaultsChanged)`<br></br>
	 * *native declaration : :79*
	 */
	abstract fun noteUserDefaultsChanged()

	/**
	 * Original signature : `BOOL userDefaultsChanged)`<br></br>
	 * *native declaration : :80*
	 */
	abstract fun userDefaultsChanged(): Boolean

	/**
	 * Original signature : `BOOL getInfoForFile(NSString*, NSString**, NSString**)`<br></br>
	 * *native declaration : :82*
	 */
	abstract fun getInfoForFile_application_type(
		fullPath1: String?,
		appName2: com.sun.jna.ptr.PointerByReference?,
		type3: com.sun.jna.ptr.PointerByReference?
	): Boolean

	/**
	 * Original signature : `BOOL isFilePackageAtPath(NSString*)`<br></br>
	 * *native declaration : :83*
	 */
	abstract fun isFilePackageAtPath(fullPath1: String?): Boolean

	/**
	 * Original signature : `NSImage* iconForFile(NSString*)`<br></br>
	 * *native declaration : :85*
	 */
	abstract fun iconForFile(fullPath1: String?): NSImage?

	/**
	 * Original signature : `NSImage* iconForFiles(NSArray*)`<br></br>
	 * *native declaration : :86*
	 */
	abstract fun iconForFiles(fullPaths1: NSArray?): NSImage?

	/**
	 * Original signature : `NSImage* iconForFileType(NSString*)`<br></br>
	 * *native declaration : :87*
	 */
	abstract fun iconForFileType(fileType1: String?): NSImage?

	/**
	 * Original signature : `BOOL setIcon(NSImage*, NSString*, NSWorkspaceIconCreationOptions)`<br></br>
	 * *native declaration : :89*
	 */
	abstract fun setIcon_forFile_options(image1: NSImage?, fullPath2: String?, options3: NSUInteger?): Boolean

	/**
	 * Original signature : `BOOL getFileSystemInfoForPath(NSString*, BOOL*, BOOL*, BOOL*, NSString**, NSString**)`<br></br>
	 * *native declaration : :92*
	 */
	abstract fun getFileSystemInfoForPath_isRemovable_isWritable_isUnmountable_description_type(
		fullPath1: String?,
		removableFlag2: Boolean,
		writableFlag3: Boolean,
		unmountableFlag4: Boolean,
		description5: com.sun.jna.ptr.PointerByReference?,
		fileSystemType6: com.sun.jna.ptr.PointerByReference?
	): Boolean

	/**
	 * Original signature : `BOOL performFileOperation(NSString*, NSString*, NSString*, NSArray*, NSInteger*)`<br></br>
	 * Returned tag < 0 on failure, 0 if sync, > 0 if async<br></br>
	 * *native declaration : :94*
	 */
	abstract fun performFileOperation_source_destination_files_tag(
		operation1: String?,
		source2: String?,
		destination3: String?,
		files4: NSArray?,
		tag5: NSInteger?
	): Boolean

	fun performFileOperation(operation: String?, source: String?, destination: String?, files: NSArray?): Boolean {
		return this.performFileOperation_source_destination_files_tag(
			operation,
			source,
			destination,
			files,
			NSInteger(0)
		)
	}

	/**
	 * Original signature : `BOOL unmountAndEjectDeviceAtPath(NSString*)`<br></br>
	 * *native declaration : :96*
	 */
	abstract fun unmountAndEjectDeviceAtPath(path1: String?): Boolean

	/**
	 * Original signature : `NSInteger extendPowerOffBy(NSInteger)`<br></br>
	 * *native declaration : :97*
	 */
	abstract fun extendPowerOffBy(requested1: NSInteger?): NSInteger?
	/**
	 * *native declaration : :99*<br></br>
	 * Conversion Error : NSPoint
	 */
	/**
	 * Original signature : `public abstract void hideOtherApplications)`<br></br>
	 * *native declaration : :101*
	 */
	abstract fun hideOtherApplications()

	/**
	 * Original signature : `NSArray* mountedLocalVolumePaths)`<br></br>
	 * *native declaration : :103*
	 */
	abstract fun mountedLocalVolumePaths(): NSArray?

	/**
	 * Original signature : `NSArray* mountedRemovableMedia)`<br></br>
	 * *native declaration : :104*
	 */
	abstract fun mountedRemovableMedia(): NSArray?

	/**
	 * Original signature : `NSArray* mountNewRemovableMedia)`<br></br>
	 * *native declaration : :105*
	 */
	abstract fun mountNewRemovableMedia(): NSArray?

	/**
	 * Original signature : `public abstract void checkForRemovableMedia)`<br></br>
	 * *native declaration : :106*
	 */
	abstract fun checkForRemovableMedia()

	/**
	 * Original signature : `NSString* absolutePathForAppBundleWithIdentifier(NSString*)`<br></br>
	 * *native declaration : :110*
	 */
	abstract fun absolutePathForAppBundleWithIdentifier(bundleIdentifier1: String?): String?

	/**
	 * Original signature : `BOOL launchAppWithBundleIdentifier(NSString*, NSWorkspaceLaunchOptions, NSAppleEventDescriptor*, NSNumber**)`<br></br>
	 * *native declaration : :111*
	 */
	abstract fun launchAppWithBundleIdentifier_options_additionalEventParamDescriptor_launchIdentifier(
		bundleIdentifier1: String?,
		options2: Int,
		descriptor3: Pointer?,
		identifier4: com.sun.jna.ptr.PointerByReference?
	): Boolean

	/**
	 * Original signature : `BOOL openURLs(NSArray*, NSString*, NSWorkspaceLaunchOptions, NSAppleEventDescriptor*, NSArray**)`<br></br>
	 * *native declaration : :112*
	 */
	abstract fun openURLs_withAppBundleIdentifier_options_additionalEventParamDescriptor_launchIdentifiers(
		urls1: NSArray?,
		bundleIdentifier2: String?,
		options3: Int,
		descriptor4: Pointer?,
		identifiers5: com.sun.jna.ptr.PointerByReference?
	): Boolean

	/**
	 * Original signature : `NSArray* launchedApplications)`<br></br>
	 * Returns an array of dictionaries, one for each running application.<br></br>
	 * *native declaration : :127*
	 */
	abstract fun launchedApplications(): NSArray?

	/**
	 * Original signature : `NSDictionary* activeApplication)`<br></br>
	 * Returns a dictionary with information about the current active application.<br></br>
	 * *native declaration : :128*
	 */
	abstract fun activeApplication(): NSArray?

	/**
	 * Given an absolute file path, return the uniform type identifier (UTI) of the file, if one can be determined. Otherwise, return nil after setting *outError to an NSError that encapsulates the reason why the file's type could not be determined. If the file at the end of the path is a symbolic link the type of the symbolic link will be returned.<br></br>
	 * You can invoke this method to get the UTI of an existing file.<br></br>
	 * Original signature : `NSString* typeOfFile(NSString*, NSError**)`<br></br>
	 * *native declaration : :138*
	 */
	abstract fun typeOfFile_error(absoluteFilePath1: String?, outError2: com.sun.jna.ptr.PointerByReference?): String?

	/**
	 * Given a UTI, return a string that describes the document type and is fit to present to the user, or nil for failure.<br></br>
	 * You can invoke this method to get the name of a type that must be shown to the user, in an alert about your application's inability to handle the type, for instance.<br></br>
	 * Original signature : `NSString* localizedDescriptionForType(NSString*)`<br></br>
	 * *native declaration : :144*
	 */
	abstract fun localizedDescriptionForType(typeName1: String?): String?

	/**
	 * Given a UTI, return the best file name extension to use when creating a file of that type, or nil for failure.<br></br>
	 * You can invoke this method when your application has only the base name of a file that's being written and it has to append a file name extension so that the file's type can be reliably identified later on.<br></br>
	 * Original signature : `NSString* preferredFilenameExtensionForType(NSString*)`<br></br>
	 * *native declaration : :150*
	 */
	abstract fun preferredFilenameExtensionForType(typeName1: String?): String?

	/**
	 * Given a file name extension and a UTI, return YES if the file name extension is a valid tag for the identified type, NO otherwise.<br></br>
	 * You can invoke this method when your application needs to check if a file name extension can be used to reliably identify the type later on. For example, NSSavePanel uses this method to validate any extension that the user types in the panel's file name field.<br></br>
	 * Original signature : `BOOL filenameExtension(NSString*, NSString*)`<br></br>
	 * *native declaration : :156*
	 */
	abstract fun filenameExtension_isValidForType(filenameExtension1: String?, typeName2: String?): Boolean

	/**
	 * Given two UTIs, return YES if the first "conforms to" to the second in the uniform type identifier hierarchy, NO otherwise. This method will always return YES if the two strings are equal, so you can also use it with other kinds of type name, including those declared in CFBundleTypeName Info.plist entries in apps that don't take advantage of the support for UTIs that was added to Cocoa in Mac OS 10.5.<br></br>
	 * You can invoke this method when your application must determine whether it can handle a file of a known type, returned by -typeOfFile:error: for instance.<br></br>
	 * Use this method instead of merely comparing UTIs for equality.<br></br>
	 * Original signature : `BOOL type(NSString*, NSString*)`<br></br>
	 * *native declaration : :164*
	 */
	abstract fun type_conformsToType(firstTypeName1: String?, secondTypeName2: String?): Boolean

	/**
	 * macOS 12.0+
	 */
	abstract fun setDefaultApplicationAtURL_toOpenURLsWithScheme_completionHandler(
		applicationURL: NSURL?,
		urlScheme: String?,
		completionHandler: Pointer?
	)

	/**
	 * macOS 12.0+
	 */
	abstract fun URLsForApplicationsToOpenURL(url: NSURL?): NSArray?

	/**
	 * Returns the URL to the default app that would be opened.
	 * macOS 10.6+
	 *
	 * @param url The URL of the file to open.
	 * @return The URL of the default app that would open the specified url. Returns nil if no app is able to open the URL, or if the file URL does not exist.
	 */
	abstract fun URLForApplicationToOpenURL(url: NSURL?): NSURL?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSWorkspace", _Class::class.java)

		val WorkspaceDidLaunchApplicationNotification: String? = "NSWorkspaceDidLaunchApplicationNotification"
		val WorkspaceDidMountNotification: String? = "NSWorkspaceDidMountNotification"
		val WorkspaceDidPerformFileOperationNotification: String? = "NSWorkspaceDidPerformFileOperationNotification"
		val WorkspaceDidTerminateApplicationNotification: String? = "NSWorkspaceDidTerminateApplicationNotification"
		val WorkspaceDidUnmountNotification: String? = "NSWorkspaceDidUnmountNotification"
		val WorkspaceDidWakeNotification: String? = "NSWorkspaceDidWakeNotification"
		val WorkspaceWillLaunchApplicationNotification: String? = "NSWorkspaceWillLaunchApplicationNotification"
		val WorkspaceWillPowerOffNotification: String? = "NSWorkspaceWillPowerOffNotification"
		val WorkspaceWillSleepNotification: String? = "NSWorkspaceWillSleepNotification"
		val WorkspaceWillUnmountNotification: String? = "NSWorkspaceWillUnmountNotification"
		val WorkspaceSessionDidBecomeActiveNotification: String? = "NSWorkspaceSessionDidBecomeActiveNotification"
		val WorkspaceSessionDidResignActiveNotification: String? = "NSWorkspaceSessionDidResignActiveNotification"

		/// <i>native declaration : :13</i>
		const val NSWorkspaceLaunchAndPrint: Int = 2

		/// <i>native declaration : :14</i>
		const val NSWorkspaceLaunchInhibitingBackgroundOnly: Int = 128

		/// <i>native declaration : :15</i>
		const val NSWorkspaceLaunchWithoutAddingToRecents: Int = 256

		/// <i>native declaration : :16</i>
		const val NSWorkspaceLaunchWithoutActivation: Int = 512

		/// <i>native declaration : :17</i>
		const val NSWorkspaceLaunchAsync: Int = 65536

		/// <i>native declaration : :18</i>
		const val NSWorkspaceLaunchAllowingClassicStartup: Int = 131072

		/// <i>native declaration : :19</i>
		const val NSWorkspaceLaunchPreferringClassic: Int = 262144

		/// <i>native declaration : :20</i>
		const val NSWorkspaceLaunchNewInstance: Int = 524288

		/// <i>native declaration : :21</i>
		const val NSWorkspaceLaunchAndHide: Int = 1048576

		/// <i>native declaration : :22</i>
		const val NSWorkspaceLaunchAndHideOthers: Int = 2097152

		/**
		 * NSWorkspaceLaunchAndDisplayFailures<br></br>
		 * *native declaration : :24*
		 */
		const val NSWorkspaceLaunchDefault: Int = NSWorkspaceLaunchAsync or NSWorkspaceLaunchAllowingClassicStartup

		/// <i>native declaration : :32</i>
		const val NSExcludeQuickDrawElementsIconCreationOption: Int = 1 shl 1

		/// <i>native declaration : :33</i>
		const val NSExclude10_4ElementsIconCreationOption: Int = 1 shl 2

		val ApplicationName: String? = "NSApplicationName"
		val DevicePath: String? = "NSDevicePath"
		val OperationNumber: String? = "NSOperationNumber"
		val PlainFileType: String? = ""
		val DirectoryFileType: String? = "NXDirectoryFileType"
		val ApplicationFileType: String? = "app"
		val FilesystemFileType: String? = "NXFilesystemFileType"
		val ShellCommandFileType: String? = "NXShellCommandFileType"
		val MoveOperation: String? = "move"
		val CopyOperation: String? = "copy"
		val LinkOperation: String? = "link"
		val CompressOperation: String? = "compress"
		val DecompressOperation: String? = "decompress"
		val EncryptOperation: String? = "encrypt"
		val DecryptOperation: String? = "decrypt"
		val DestroyOperation: String? = "destroy"
		val RecycleOperation: String? = "recycle"
		val DuplicateOperation: String? = "duplicate"

		fun sharedWorkspace(): NSWorkspace? {
			return CLASS.sharedWorkspace()
		}
	}
}
