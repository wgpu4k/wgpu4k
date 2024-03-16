package darwin 

import org.rococoa.ObjCClass
import org.rococoa.ObjCObjectByReference

/// <i>native declaration : :12</i>
abstract class NSBundle : NSObject() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `NSBundle* mainBundle)`<br></br>
		 * *native declaration : :24*
		 */
		open fun mainBundle(): NSBundle?

		/**
		 * Original signature : `NSBundle* bundleWithPath(String*)`<br></br>
		 * *native declaration : :25*
		 */
		open fun bundleWithPath(path1: String?): NSBundle?

		/**
		 * Original signature : `NSBundle* bundleWithIdentifier(String*)`<br></br>
		 * *native declaration : :29*
		 */
		open fun bundleWithIdentifier(identifier1: String?): NSBundle?

		/**
		 * Original signature : `NSArray* allBundles)`<br></br>
		 * *native declaration : :31*
		 */
		open fun allBundles(): NSArray?

		/**
		 * Original signature : `NSArray* allFrameworks)`<br></br>
		 * *native declaration : :32*
		 */
		open fun allFrameworks(): NSArray?

		/**
		 * In the following methods, bundlePath is an absolute path to a bundle, and may not be nil; subpath is a relative path to a subdirectory inside the relevant global or localized resource directory, and should be nil if the resource file in question is not in a subdirectory.<br></br>
		 * Original signature : `String* pathForResource(String*, String*, String*)`<br></br>
		 * *native declaration : :62*
		 */
		open fun pathForResource_ofType_inDirectory(name1: String?, ext2: String?, bundlePath3: String?): String?

		/**
		 * Original signature : `NSArray* pathsForResourcesOfType(String*, String*)`<br></br>
		 * *native declaration : :67*
		 */
		open fun pathsForResourcesOfType_inDirectory(ext1: String?, bundlePath2: String?): NSArray?

		/**
		 * Original signature : `NSArray* preferredLocalizationsFromArray(NSArray*)`<br></br>
		 * *native declaration : :85*
		 */
		open fun preferredLocalizationsFromArray(localizationsArray1: NSArray?): NSArray?

		/**
		 * Original signature : `NSArray* preferredLocalizationsFromArray(NSArray*, NSArray*)`<br></br>
		 * *native declaration : :87*
		 */
		open fun preferredLocalizationsFromArray_forPreferences(
			localizationsArray1: NSArray?,
			preferencesArray2: NSArray?
		): NSArray?

		open fun loadNibNamed_owner(nibName: String?, owner: org.rococoa.ID?): Boolean
	}

	/**
	 * Original signature : `id initWithPath(String*)`<br></br>
	 * *native declaration : :26*
	 */
	abstract fun initWithPath(path1: String?): NSBundle?

	/**
	 * Original signature : `BOOL load)`<br></br>
	 * *native declaration : :34*
	 */
	abstract fun load(): Boolean

	/**
	 * Original signature : `BOOL isLoaded)`<br></br>
	 * *native declaration : :36*
	 */
	abstract fun isLoaded(): Boolean

	/**
	 * Original signature : `BOOL unload)`<br></br>
	 * *native declaration : :37*
	 */
	abstract fun unload(): Boolean

	/**
	 * Original signature : `BOOL preflightAndReturnError(NSError**)`<br></br>
	 * *native declaration : :41*
	 */
	abstract fun preflightAndReturnError(error1: ObjCObjectByReference?): Boolean

	/**
	 * Original signature : `BOOL loadAndReturnError(NSError**)`<br></br>
	 * *native declaration : :42*
	 */
	abstract fun loadAndReturnError(error1: ObjCObjectByReference?): Boolean

	/**
	 * Original signature : `String* bundlePath)`<br></br>
	 * *native declaration : :45*
	 */
	abstract fun bundlePath(): String?

	/**
	 * Original signature : `String* resourcePath)`<br></br>
	 * *native declaration : :46*
	 */
	abstract fun resourcePath(): String?

	/**
	 * Original signature : `String* executablePath)`<br></br>
	 * *native declaration : :47*
	 */
	abstract fun executablePath(): String?

	/**
	 * Original signature : `String* pathForAuxiliaryExecutable(String*)`<br></br>
	 * *native declaration : :48*
	 */
	abstract fun pathForAuxiliaryExecutable(executableName1: String?): String?

	/**
	 * Original signature : `String* privateFrameworksPath)`<br></br>
	 * *native declaration : :50*
	 */
	abstract fun privateFrameworksPath(): String?

	/**
	 * Original signature : `String* sharedFrameworksPath)`<br></br>
	 * *native declaration : :51*
	 */
	abstract fun sharedFrameworksPath(): String?

	/**
	 * Original signature : `String* sharedSupportPath)`<br></br>
	 * *native declaration : :52*
	 */
	abstract fun sharedSupportPath(): String?

	/**
	 * Original signature : `String* builtInPlugInsPath)`<br></br>
	 * *native declaration : :53*
	 */
	abstract fun builtInPlugInsPath(): String?

	/**
	 * Original signature : `String* bundleIdentifier)`<br></br>
	 * *native declaration : :55*
	 */
	abstract fun bundleIdentifier(): String?

	/**
	 * Original signature : `String* pathForResource(String*, String*)`<br></br>
	 * *native declaration : :63*
	 */
	abstract fun pathForResource_ofType(name1: String?, ext2: String?): String?

	/**
	 * Original signature : `String* pathForResource(String*, String*, String*, String*)`<br></br>
	 * *native declaration : :65*
	 */
	abstract fun pathForResource_ofType_inDirectory_forLocalization(
		name1: String?,
		ext2: String?,
		subpath3: String?,
		localizationName4: String?
	): String?

	/**
	 * Original signature : `NSArray* pathsForResourcesOfType(String*, String*, String*)`<br></br>
	 * *native declaration : :69*
	 */
	abstract fun pathsForResourcesOfType_inDirectory_forLocalization(
		ext1: String?,
		subpath2: String?,
		localizationName3: String?
	): NSArray?

	fun localizedString(key: String?, tableName: String?): String? {
		return localizedStringForKey_value_table(key, key, tableName)
	}

	/**
	 * Original signature : `String* localizedStringForKey(String*, String*, String*)`<br></br>
	 * *native declaration : :71*
	 */
	abstract fun localizedStringForKey_value_table(key1: String?, value2: String?, tableName3: String?): String?

	/**
	 * Original signature : `NSDictionary* infoDictionary)`<br></br>
	 * *native declaration : :73*
	 */
	abstract fun infoDictionary(): NSDictionary?

	/**
	 * Original signature : `NSDictionary* localizedInfoDictionary)`<br></br>
	 * *native declaration : :75*
	 */
	abstract fun localizedInfoDictionary(): NSDictionary?

	/**
	 * Original signature : `id objectForInfoDictionaryKey(String*)`<br></br>
	 * *native declaration : :76*
	 */
	abstract fun objectForInfoDictionaryKey(key1: String?): NSObject?

	/**
	 * Original signature : `NSArray* localizations)`<br></br>
	 * *native declaration : :79*
	 */
	abstract fun localizations(): NSArray?

	/**
	 * Original signature : `NSArray* preferredLocalizations)`<br></br>
	 * *native declaration : :80*
	 */
	abstract fun preferredLocalizations(): NSArray?

	/**
	 * Original signature : `String* developmentLocalization)`<br></br>
	 * *native declaration : :82*
	 */
	abstract fun developmentLocalization(): String?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSBundle", _Class::class.java)

		private var mainBundle: NSBundle? = null

		fun mainBundle(): NSBundle? {
			if (null == mainBundle) {
				mainBundle = CLASS.mainBundle()
			}
			return mainBundle
		}

		fun allBundles(): NSArray? {
			return CLASS.allBundles()
		}

		fun loadNibNamed(nibName: String?, owner: org.rococoa.ID?): Boolean {
			return CLASS.loadNibNamed_owner(nibName, owner)
		}

		fun bundleWithPath(path: String?): NSBundle? {
			return CLASS.bundleWithPath(path)
		}
	}
}
