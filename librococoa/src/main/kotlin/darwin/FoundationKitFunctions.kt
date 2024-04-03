package darwin 

import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.platform.mac.CoreFoundation
import org.rococoa.cocoa.foundation.NSPoint
import org.rococoa.cocoa.foundation.NSRect
import org.rococoa.cocoa.foundation.NSSize
import org.rococoa.internal.RococoaTypeMapper
import java.util.*

interface FoundationKitFunctions : Library {
	/**
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/CoreGraphics.framework/Headers/CGGeometry.h:36*<br></br>
	 * enum values
	 */
	interface CGRectEdge {
		companion object {
			const val CGRectMinXEdge: Int = 0
			const val CGRectMinYEdge: Int = 1
			const val CGRectMaxXEdge: Int = 2
			const val CGRectMaxYEdge: Int = 3
		}
	}

	/**
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/CoreGraphics.framework/Headers/CGGeometry.h*<br></br>
	 * enum values
	 */
	interface NSRectEdge {
		companion object {
			const val NSMinXEdge: Int = 0
			const val NSMinYEdge: Int = 1
			const val NSMaxXEdge: Int = 2
			const val NSMaxYEdge: Int = 3
		}
	}

	/**
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSPathUtilities.h*<br></br>
	 * enum values
	 */
	interface NSSearchPathDirectory {
		companion object {
			/// supported applications (Applications)
			const val NSApplicationDirectory: Int = 1

			/// unsupported applications, demonstration versions (Demos)
			const val NSDemoApplicationDirectory: Int = 2

			/// developer applications (Developer/Applications). DEPRECATED - there is no one single Developer directory.
			const val NSDeveloperApplicationDirectory: Int = 3

			/// system and network administration applications (Administration)
			const val NSAdminApplicationDirectory: Int = 4

			/// various user-visible documentation, support, and configuration files, resources (Library)
			const val NSLibraryDirectory: Int = 5

			/// developer resources (Developer) DEPRECATED - there is no one single Developer directory.
			const val NSDeveloperDirectory: Int = 6

			/// user home directories (Users)
			const val NSUserDirectory: Int = 7

			/// documentation (Documentation)
			const val NSDocumentationDirectory: Int = 8

			/// documents (Documents)
			const val NSDocumentDirectory: Int = 9

			/// location of CoreServices directory (System/Library/CoreServices)
			const val NSCoreServiceDirectory: Int = 10

			/// location of autosaved documents (Documents/Autosaved)
			const val NSAutosavedInformationDirectory: Int = 11

			/// location of user's desktop
			const val NSDesktopDirectory: Int = 12

			/// location of discardable cache files (Library/Caches)
			const val NSCachesDirectory: Int = 13

			/// location of application support files (plug-ins, etc) (Library/Application Support)
			const val NSApplicationSupportDirectory: Int = 14

			/// location of the user's "Downloads" directory
			const val NSDownloadsDirectory: Int = 15

			/// input methods (Library/Input Methods)
			const val NSInputMethodsDirectory: Int = 16

			/// location of user's Movies directory (~/Movies)
			const val NSMoviesDirectory: Int = 17

			/// location of user's Music directory (~/Music)
			const val NSMusicDirectory: Int = 18

			/// location of user's Pictures directory (~/Pictures)
			const val NSPicturesDirectory: Int = 19

			/// location of system's PPDs directory (Library/Printers/PPDs)
			const val NSPrinterDescriptionDirectory: Int = 20

			/// location of user's Public sharing directory (~/Public)
			const val NSSharedPublicDirectory: Int = 21

			/// location of the PreferencePanes directory for use with System Preferences (Library/PreferencePanes)
			const val NSPreferencePanesDirectory: Int = 22

			/// For use with NSFileManager's URLForDirectory:inDomain:appropriateForURL:create:error:
			const val NSItemReplacementDirectory: Int = 99

			/// all directories where applications can occur
			const val NSAllApplicationsDirectory: Int = 100

			/// all directories where resources can occur
			const val NSAllLibrariesDirectory: Int = 101
		}
	}

	/**
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSPathUtilities.h*<br></br>
	 * enum values
	 */
	interface NSSearchPathDomainMask {
		companion object {
			/// user's home directory --- place to install user's personal items (~)
			const val NSUserDomainMask: Int = 1

			/// local to the current machine --- place to install items available to everyone on this machine (/Library)
			const val NSLocalDomainMask: Int = 2

			/// publically available location in the local area network --- place to install items available on the network (/Network)
			const val NSNetworkDomainMask: Int = 4

			/// provided by Apple, unmodifiable (/System)
			const val NSSystemDomainMask: Int = 8

			/// all domains: all of the above and future items
			const val NSAllDomainsMask: Int = 65535
		}
	}

	/**
	 * Original signature : `BOOL NSEqualPoints(NSPoint, NSPoint)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/CoreGraphics.framework/Headers/CGGeometry.h:447*
	 */
	open fun NSEqualPoints(aPoint: NSPoint?, bPoint: NSPoint?): Boolean

	/**
	 * Original signature : `BOOL NSEqualSizes(NSSize, NSSize)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:448*
	 */
	open fun NSEqualSizes(aSize: NSSize?, bSize: NSSize?): Boolean

	/**
	 * Original signature : `BOOL NSEqualRects(NSRect, NSRect)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:449*
	 */
	open fun NSEqualRects(aRect: NSRect?, bRect: NSRect?): Boolean

	/**
	 * Original signature : `BOOL NSIsEmptyRect(NSRect)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:450*
	 */
	open fun NSIsEmptyRect(aRect: NSRect?): Boolean

	/**
	 * Original signature : `NSRect NSInsetRect(NSRect, CGFloat, CGFloat)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:452*
	 */
	open fun NSInsetRect(aRect: NSRect?, dX: org.rococoa.cocoa.CGFloat?, dY: org.rococoa.cocoa.CGFloat?): NSRect?

	/**
	 * Original signature : `NSRect NSIntegralRect(NSRect)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:453*
	 */
	open fun NSIntegralRect(aRect: NSRect?): NSRect?

	/**
	 * Original signature : `NSRect NSUnionRect(NSRect, NSRect)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:454*
	 */
	open fun NSUnionRect(aRect: NSRect?, bRect: NSRect?): NSRect?

	/**
	 * Original signature : `NSRect NSIntersectionRect(NSRect, NSRect)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:455*
	 */
	open fun NSIntersectionRect(aRect: NSRect?, bRect: NSRect?): NSRect?

	/**
	 * Original signature : `NSRect NSOffsetRect(NSRect, CGFloat, CGFloat)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:456*
	 */
	open fun NSOffsetRect(aRect: NSRect?, dX: org.rococoa.cocoa.CGFloat?, dY: org.rococoa.cocoa.CGFloat?): NSRect?

	/**
	 * Original signature : `void NSDivideRect(NSRect, NSRect*, NSRect*, CGFloat, NSRectEdge)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:457*<br></br>
	 *
	 * @param edge @see NSRectEdge
	 */
	open fun NSDivideRect(inRect: NSRect?, slice: NSRect?, rem: NSRect?, amount: org.rococoa.cocoa.CGFloat?, edge: Int)

	/**
	 * Original signature : `BOOL NSPointInRect(NSPoint, NSRect)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:458*
	 */
	open fun NSPointInRect(aPoint: NSPoint?, aRect: NSRect?): Boolean

	/**
	 * Original signature : `BOOL NSMouseInRect(NSPoint, NSRect, BOOL)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:459*
	 */
	open fun NSMouseInRect(aPoint: NSPoint?, aRect: NSRect?, flipped: Boolean): Boolean

	/**
	 * Original signature : `BOOL NSContainsRect(NSRect, NSRect)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:460*
	 */
	open fun NSContainsRect(aRect: NSRect?, bRect: NSRect?): Boolean

	/**
	 * Original signature : `BOOL NSIntersectsRect(NSRect, NSRect)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:461*
	 */
	open fun NSIntersectsRect(aRect: NSRect?, bRect: NSRect?): Boolean

	/**
	 * Original signature : `NSString* NSStringFromPoint(NSPoint)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:463*
	 */
	open fun NSStringFromPoint(aPoint: NSPoint?): String?

	/**
	 * Original signature : `NSString* NSStringFromSize(NSSize)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:464*
	 */
	open fun NSStringFromSize(aSize: NSSize?): String?

	/**
	 * Original signature : `NSString* NSStringFromRect(NSRect)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:465*
	 */
	open fun NSStringFromRect(aRect: NSRect?): String?

	/**
	 * Original signature : `NSPoint NSPointFromString(NSString*)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:466*
	 */
	open fun NSPointFromString(aString: String?): NSPoint?

	/**
	 * Original signature : `NSSize NSSizeFromString(NSString*)`<br></br>
	 * *native declaration : /System/Library/Frameworks/ApplicationServices.framework/Headers/../Frameworks/framework/Headers/CGGeometry.h:467*
	 */
	open fun NSSizeFromString(aString: String?): NSSize?

	/**
	 * Original signature : `NSString* NSUserName()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSPathUtilities.h:46*
	 */
	open fun NSUserName(): String?

	/**
	 * Original signature : `NSString* NSFullUserName()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSPathUtilities.h:47*
	 */
	open fun NSFullUserName(): String?

	/**
	 * Original signature : `NSString* NSHomeDirectory()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSPathUtilities.h:49*
	 */
	open fun NSHomeDirectory(): String?

	/**
	 * Original signature : `NSString* NSHomeDirectoryForUser(String*)`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSPathUtilities.h:50*
	 */
	open fun NSHomeDirectoryForUser(userName: String?): String?

	/**
	 * Original signature : `NSString* NSTemporaryDirectory()`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSPathUtilities.h:52*
	 */
	open fun NSTemporaryDirectory(): String?

	/**
	 * Original signature : `NSArray* NSSearchPathForDirectoriesInDomains(NSSearchPathDirectory,
	 * NSSearchPathDomainMask, BOOL)`<br></br>
	 * *native declaration : /System/Library/Frameworks/Foundation.framework/Headers/NSPathUtilities.h:106*<br></br>
	 *
	 * @param directory  @see NSSearchPathDirectory<br></br>
	 * @param domainMask @see NSSearchPathDomainMask
	 */
	open fun NSSearchPathForDirectoriesInDomains(directory: Int, domainMask: Int, expandTilde: Boolean): NSArray?

	/**
	 * Logs an error message to the Apple System Log facility.
	 *
	 * @param format Statement
	 */
	open fun NSLog(format: String?, vararg args: String?)

	open fun CFStringCreateWithCharacters(allocator: CFAllocatorRef?, chars: CharArray?, index: CFIndex?): CFStringRef?

	/**
	 * Releases a Core Foundation object. If the retain count of cf becomes zero the memory allocated to the object is
	 * deallocated and the object is destroyed. If you create, copy, or explicitly retain (see the CFRetain function) a
	 * Core Foundation object, you are responsible for releasing it when you no longer need it (see Memory Management
	 * Programming Guide for Core Foundation).
	 *
	 * @param ref A CFType object to release. This value must not be NULL.
	 */
	open fun CFRelease(ref: CoreFoundation.CFTypeRef?)

	companion object {
		val library: FoundationKitFunctions = Native.load(
			"Foundation",
			FoundationKitFunctions::class.java,
			Collections.singletonMap(Library.OPTION_TYPE_MAPPER, RococoaTypeMapper())
		)
	}
}

