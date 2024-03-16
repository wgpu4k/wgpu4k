package darwin 

import org.rococoa.ObjCClass
import org.rococoa.ObjCObjectByReference

/// <i>native declaration : :15</i>
abstract class NSURL : NSObject() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `fileURLWithPath(NSString*, BOOL)`<br></br>
		 * *native declaration : :28*
		 */
		fun fileURLWithPath_isDirectory(path: String?, isDir: Boolean): NSURL?

		/**
		 * Original signature : `fileURLWithPath(String*)`<br></br> Better to use fileURLWithPath:isDirectory: if
		 * you know if the path is a file vs directory, as it saves an i/o.<br></br>
		 * *native declaration : :29*
		 */
		fun fileURLWithPath(path: String?): NSURL

		/**
		 * Original signature : `URLWithString(String*)`<br></br>
		 * *native declaration : :34*
		 */
		fun URLWithString(URLString: String?): NSURL

		/**
		 * Original signature : `URLWithString(String*, NSURL*)`<br></br>
		 * *native declaration : :35*
		 */
		fun URLWithString_relativeToURL(URLString: String?, baseURL: NSURL?): NSURL?

		/**
		 * Returns a new URL made by resolving bookmark data.
		 *
		 * @param bookmarkData The bookmark data the URL is derived from.
		 * @param options      Options taken into account when resolving the bookmark data. To resolve a security-scoped
		 * bookmark to support App Sandbox, you must include (by way of bitwise OR operators with
		 * any other options in this parameter) the NSURLBookmarkResolutionWithSecurityScope
		 * option.
		 * @param relativeURL  The base URL that the bookmark data is relative to. To resolve an app-scoped bookmark,
		 * use a value of nil. To resolve a document-scoped bookmark, use the absolute path (despite
		 * this parameter’s name) to the document from which you retrieved the bookmark.
		 * @param isStale      On return, if YES, the bookmark data is stale. Your app should create a new bookmark
		 * using the returned URL and use it in place of any stored copies of the existing
		 * bookmark.
		 * @param error        The error that occurred in the case that the URL cannot be created.
		 * @return A new URL made by resolving bookmarkData.
		 */
		fun URLByResolvingBookmarkData_options_relativeToURL_bookmarkDataIsStale_error(
			bookmarkData: NSData?, options: Int, relativeURL: NSURL?, isStale: Boolean, error: ObjCObjectByReference?
		): NSURL
	}

	/**
	 * Convenience initializers<br></br> Original signature : `initWithScheme(String*, String*, String*)`<br></br>
	 * *native declaration : :24*
	 */
	abstract fun initWithScheme_host_path(scheme: String?, host: String?, path: String?): NSURL?

	/**
	 * Original signature : `initFileURLWithPath(String*, BOOL)`<br></br>
	 * *native declaration : :25*
	 */
	abstract fun initFileURLWithPath_isDirectory(path: String?, isDir: Boolean): NSURL?

	/**
	 * Original signature : `initFileURLWithPath(String*)`<br></br> Better to use initFileURLWithPath:isDirectory:
	 * if you know if the path is a file vs directory, as it saves an i/o.<br></br>
	 * *native declaration : :26*
	 */
	abstract fun initFileURLWithPath(path: String?): NSURL?

	/**
	 * These methods expect their string arguments to contain any percent escape codes that are necessary<br></br> Original
	 * signature : `initWithString(String*)`<br></br>
	 * *native declaration : :32*
	 */
	abstract fun initWithString(URLString: String?): NSURL?

	/**
	 * Original signature : `initWithString(String*, NSURL*)`<br></br> It is an error for URLString to be nil<br></br>
	 * *native declaration : :33*
	 */
	abstract fun initWithString_relativeToURL(URLString: String?, baseURL: NSURL?): NSURL?

	/**
	 * Original signature : `String* absoluteString()`<br></br>
	 * *native declaration : :37*
	 */
	abstract fun absoluteString(): String?

	/**
	 * Original signature : `String* relativeString()`<br></br> The relative portion of a URL.  If baseURL is nil,
	 * or if the receiver is itself absolute, this is the same as absoluteString<br></br>
	 * *native declaration : :38*
	 */
	abstract fun relativeString(): String?

	/**
	 * Original signature : `NSURL* baseURL()`<br></br> may be nil.<br></br>
	 * *native declaration : :39*
	 */
	abstract fun baseURL(): NSURL?

	/**
	 * Original signature : `NSURL* absoluteURL()`<br></br> if the receiver is itself absolute, this will return
	 * self.<br></br>
	 * *native declaration : :40*
	 */
	abstract fun absoluteURL(): NSURL?

	/**
	 * Any URL is composed of these two basic pieces.  The full URL would be the concatenation of [myURL scheme], ':',
	 * [myURL resourceSpecifier]<br></br> Original signature : `String* scheme()`<br></br>
	 * *native declaration : :43*
	 */
	abstract fun scheme(): String?

	/**
	 * Original signature : `String* resourceSpecifier()`<br></br>
	 * *native declaration : :44*
	 */
	abstract fun resourceSpecifier(): String?

	/**
	 * If the URL conforms to rfc 1808 (the most common form of URL), the following accessors will return the various
	 * components; otherwise they return nil.  The litmus test for conformance is as recommended in RFC 1808 - whether
	 * the first two characters of resourceSpecifier is @"//".  In all cases, they return the component's value after
	 * resolving the receiver against its base URL.<br></br> Original signature : `String* host()`<br></br>
	 * *native declaration : :47*
	 */
	abstract fun host(): String?

	/**
	 * Original signature : `NSNumber* port()`<br></br>
	 * *native declaration : :48*
	 */
	abstract fun port(): NSNumber?

	/**
	 * Original signature : `String* user()`<br></br>
	 * *native declaration : :49*
	 */
	abstract fun user(): String?

	/**
	 * Original signature : `String* password()`<br></br>
	 * *native declaration : :50*
	 */
	abstract fun password(): String?

	/**
	 * Original signature : `String* path()`<br></br>
	 * *native declaration : :51*
	 */
	abstract fun path(): String?

	/**
	 * Original signature : `String* fragment()`<br></br>
	 * *native declaration : :52*
	 */
	abstract fun fragment(): String?

	/**
	 * Original signature : `String* parameterString()`<br></br>
	 * *native declaration : :53*
	 */
	abstract fun parameterString(): String?

	/**
	 * Original signature : `String* query()`<br></br>
	 * *native declaration : :54*
	 */
	abstract fun query(): String?

	/**
	 * Original signature : `String* relativePath()`<br></br> The same as path if baseURL is nil<br></br>
	 * *native declaration : :55*
	 */
	abstract fun relativePath(): String?

	/**
	 * Original signature : `BOOL isFileURL()`<br></br> Whether the scheme is file:; if [myURL isFileURL] is YES,
	 * then [myURL path] is suitable for input into NSFileManager or NSPathUtilities.<br></br>
	 * *native declaration : :57*
	 */
	abstract val isFileURL: Boolean

	/**
	 * Original signature : `NSURL* standardizedURL()`<br></br>
	 * *native declaration : :59*
	 */
	abstract fun standardizedURL(): NSURL?

	/**
	 * File reference URLs use a URL path syntax that identifies a file system object by reference, not by path. This
	 * form of file URL remains valid when the file system path of the URL’s underlying resource changes.
	 *
	 *
	 * If the original URL is a file path URL, this method converts it to a file reference URL. If the original URL is a
	 * file reference URL, the returned URL is identical. If the original URL is not a file URL, this method returns
	 * nil.
	 *
	 *
	 * File reference URLs cannot be created to file system objects which do not exist or are not reachable.
	 *
	 *
	 * In some areas of the file system hierarchy, file reference URLs cannot be generated to the leaf node of the URL
	 * path.
	 *
	 * @return Returns a new file reference URL that points to the same resource as the original URL.
	 */
	abstract fun fileReferenceURL(): NSURL?

	/**
	 * @return Returns whether the URL is a file reference URL.
	 */
	abstract val isFileReferenceURL: Boolean

	/**
	 * Original signature : `NSData* resourceDataUsingCache(BOOL)`<br></br> Blocks to load the data if necessary.
	 * If shouldUseCache is YES, then if an equivalent URL has already been loaded and cached, its resource data will be
	 * returned immediately.  If shouldUseCache is NO, a new load will be started<br></br>
	 * *from NSURLLoading native declaration : :84*
	 */
	abstract fun resourceDataUsingCache(shouldUseCache: Boolean): NSData?
	/**
	 * *from NSURLLoading native declaration : :85*<br></br>
	 * Conversion Error : /// Original signature : `void loadResourceDataNotifyingClient(null, BOOL)`<br></br>
	 * - (void)loadResourceDataNotifyingClient:(null)client usingCache:(BOOL)shouldUseCache; // Starts an asynchronous load of the data, registering delegate to receive notification.  Only one such background load can proceed at a time.<br></br>
	 * (Argument client cannot be converted)
	 */
	/**
	 * Original signature : `propertyForKey(String*)`<br></br>
	 * *from NSURLLoading native declaration : :86*
	 */
	abstract fun propertyForKey(propertyKey: String?): NSObject?

	/**
	 * These attempt to write the given arguments for the resource specified by the URL; they return success or
	 * failure<br></br> Original signature : `BOOL setResourceData(NSData*)`<br></br>
	 * *from NSURLLoading native declaration : :89*
	 */
	abstract fun setResourceData(data: NSData?): Boolean

	/**
	 * In an app that has adopted App Sandbox, makes the resource pointed to by a security-scoped URL available to the
	 * app.
	 *
	 * @return YES if the request to access the resource succeeded; otherwise, NO.
	 */
	abstract fun startAccessingSecurityScopedResource(): Boolean

	/**
	 * In an app that adopts App Sandbox, revokes access to the resource pointed to by a security-scoped URL.
	 */
	abstract fun stopAccessingSecurityScopedResource()

	/**
	 * This method returns bookmark data that can later be resolved into a URL object for a file even if the user moves
	 * or renames it (if the volume format on which the file resides supports doing so).
	 *
	 * @param options     Options taken into account when creating the bookmark for the URL. To create a security-scoped
	 * bookmark to support App Sandbox, include the NSURLBookmarkCreationWithSecurityScope flag.
	 * @param keys        An array of names of URL resource properties.
	 * @param relativeURL The URL that the bookmark data will be relative to. To create an app-scoped bookmark, use a
	 * value of nil. To create a document-scoped bookmark, use the absolute path (despite this
	 * parameter’s name) to the document file that is to own the new security-scoped bookmark.
	 * @param error       The error that occurred in the case that the bookmark data cannot be created.
	 * @return Returns a bookmark for the URL, created with specified options and resource values
	 */
	abstract fun bookmarkDataWithOptions_includingResourceValuesForKeys_relativeToURL_error(
		options: Int, keys: NSArray?, relativeURL: NSURL?, error: ObjCObjectByReference?
	): NSData?

	interface NSURLBookmarkCreationOptions {
		companion object {
			/**
			 * Specifies that the bookmark data should include properties required to create Finder alias files
			 */
			const val NSURLBookmarkCreationSuitableForBookmarkFile: Int = (1 shl 10)

			/**
			 * Specifies that you want to create a security-scoped bookmark that, when resolved, provides a security-scoped
			 * URL allowing read/write access to a file-system resource; for use in an app that adopts App Sandbox. For more
			 * information, see App Sandbox Design Guide. Note that this flag cannot be used in conjunction with either
			 * NSURLBookmarkCreationMinimalBookmark or NSURLBookmarkCreationSuitableForBookmarkFile.
			 *
			 *
			 * Available in OS X v10.7 and later.
			 */
			const val NSURLBookmarkCreationWithSecurityScope: Int = (1 shl 11)

			/**
			 * When combined with the NSURLBookmarkCreationWithSecurityScope option, specifies that you want to create a
			 * security-scoped bookmark that, when resolved, provides a security-scoped URL allowing read-only access to a
			 * file-system resource; for use in an app that adopts App Sandbox. For more information, see App Sandbox Design
			 * Guide.
			 *
			 *
			 * Available in OS X v10.7 and later.
			 */
			const val NSURLBookmarkCreationSecurityScopeAllowOnlyReadAccess: Int = (1 shl 12)
		}
	}

	interface NSURLBookmarkResolutionOptions {
		companion object {
			/**
			 * Specifies that the security scope, applied to the bookmark when it was created, should be used during
			 * resolution of the bookmark data. Available in OS X v10.7 and later.
			 */
			const val NSURLBookmarkResolutionWithSecurityScope: Int = (1 shl 10)
		}
	}

	/**
	 * This method first checks if the URL object already caches the specified resource values. If so, it returns the
	 * cached resource values to the caller. If not, then this method synchronously obtains the resource values from the
	 * backing store, adds the resource values to the URL object's cache, and returns the resource values to the
	 * caller.
	 *
	 *
	 * The type of the returned resource value varies by resource property; for details, see the documentation for the
	 * key you want to access.
	 *
	 *
	 * If the result dictionary does not contain a resource value for one or more of the requested resource keys, it
	 * means those resource properties are not available for the URL, and no errors occurred when determining those
	 * resource properties were not available.
	 *
	 * @param keys  An array of property keys for the desired resource properties.
	 * @param error The error that occurred if one or more resource values could not be retrieved. This parameter is
	 * optional. If you are not interested in receiving error information, you can pass nil.
	 * @return If an error occurs, this method returns nil and populates the object pointer referenced by error with
	 * additional information.
	 */
	abstract fun resourceValuesForKeys_error(keys: NSArray?, error: ObjCObjectByReference?): NSDictionary?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSURL", _Class::class.java)

		fun URLWithString(URLString: String?): NSURL {
			return CLASS.URLWithString(URLString)
		}

		fun fileURLWithPath(URLString: String?): NSURL {
			return CLASS.fileURLWithPath(URLString)
		}

		fun URLByResolvingBookmarkData(bookmark: NSData?, options: Int, error: ObjCObjectByReference?): NSURL {
			return CLASS.URLByResolvingBookmarkData_options_relativeToURL_bookmarkDataIsStale_error(
				bookmark,
				options, null, false, error
			)
		}
	}
}
