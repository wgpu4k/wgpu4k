package darwin 

import org.rococoa.ObjCClass

abstract class NSLocale : NSObject() {
	interface _Class : ObjCClass {
		/// <i>native declaration : NSLocale.h</i>
		open fun alloc(): NSLocale

		/**
		 * Original signature : `NSArray* availableLocaleIdentifiers()`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:40*
		 */
		open fun availableLocaleIdentifiers(): NSArray?

		/**
		 * Original signature : `NSArray* ISOLanguageCodes()`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:41*
		 */
		open fun ISOLanguageCodes(): NSArray?

		/**
		 * Original signature : `NSArray* ISOCountryCodes()`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:42*
		 */
		open fun ISOCountryCodes(): NSArray?

		/**
		 * Original signature : `NSArray* ISOCurrencyCodes()`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:43*
		 */
		open fun ISOCurrencyCodes(): NSArray?

		/**
		 * Original signature : `NSArray* commonISOCurrencyCodes()`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:44*
		 */
		open fun commonISOCurrencyCodes(): NSArray?

		/**
		 * Original signature : `NSArray* preferredLanguages()`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:45*
		 */
		open fun preferredLanguages(): NSArray?

		/**
		 * Original signature : `NSDictionary* componentsFromLocaleIdentifier(NSString*)`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:47*
		 */
		open fun componentsFromLocaleIdentifier(string: String?): NSDictionary?

		/**
		 * Original signature : `NSString* localeIdentifierFromComponents(NSDictionary*)`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:48*
		 */
		open fun localeIdentifierFromComponents(dict: NSDictionary?): String?

		/**
		 * Original signature : `NSString* canonicalLocaleIdentifierFromString(NSString*)`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:50*
		 */
		open fun canonicalLocaleIdentifierFromString(string: String?): String?

		/**
		 * Original signature : `systemLocale()`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleCreation native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:30*
		 */
		open fun systemLocale(): NSLocale?

		/**
		 * Original signature : `currentLocale()`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleCreation native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:31*
		 */
		open fun currentLocale(): NSLocale?
	}

	/**
	 * Original signature : `initWithLocaleIdentifier(NSString*)`<br></br>
	 * From category NSLocale<br></br>
	 * *from NSLocaleCreation native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:34*
	 */
	abstract fun initWithLocaleIdentifier(string: String?): NSLocale?

	/**
	 * Original signature : `NSString* localeIdentifier()`<br></br>
	 * From category NSLocale<br></br>
	 * same as NSLocaleIdentifier<br></br>
	 * *from NSExtendedLocale native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:24*
	 */
	abstract fun localeIdentifier(): String?

	/**
	 * Original signature : `-(NSString*)displayNameForKey:(id) value:(id)`<br></br>
	 * *native declaration : NSLocale.h:19*
	 */
	abstract fun displayNameForKey_value(key: String?, value: String?): String?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSLocale", _Class::class.java)

		/**
		 * Original signature : `NSArray* availableLocaleIdentifiers()`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:40*
		 */
		fun availableLocaleIdentifiers(): NSArray? {
			return CLASS.availableLocaleIdentifiers()
		}

		/**
		 * Original signature : `NSArray* ISOLanguageCodes()`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:41*
		 */
		fun ISOLanguageCodes(): NSArray? {
			return CLASS.ISOLanguageCodes()
		}

		/**
		 * Original signature : `NSArray* ISOCountryCodes()`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:42*
		 */
		fun ISOCountryCodes(): NSArray? {
			return CLASS.ISOCountryCodes()
		}

		/**
		 * Original signature : `NSArray* ISOCurrencyCodes()`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:43*
		 */
		fun ISOCurrencyCodes(): NSArray? {
			return CLASS.ISOCurrencyCodes()
		}

		/**
		 * Original signature : `NSArray* commonISOCurrencyCodes()`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:44*
		 */
		fun commonISOCurrencyCodes(): NSArray? {
			return CLASS.commonISOCurrencyCodes()
		}

		/**
		 * Original signature : `NSArray* preferredLanguages()`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:45*
		 */
		fun preferredLanguages(): NSArray? {
			return CLASS.preferredLanguages()
		}

		/**
		 * Original signature : `NSDictionary* componentsFromLocaleIdentifier(NSString*)`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:47*
		 */
		fun componentsFromLocaleIdentifier(string: String?): NSDictionary? {
			return CLASS.componentsFromLocaleIdentifier(string)
		}

		/**
		 * Original signature : `NSString* localeIdentifierFromComponents(NSDictionary*)`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:48*
		 */
		fun localeIdentifierFromComponents(dict: NSDictionary?): String? {
			return CLASS.localeIdentifierFromComponents(dict)
		}

		/**
		 * Original signature : `NSString* canonicalLocaleIdentifierFromString(NSString*)`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleGeneralInfo native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:50*
		 */
		fun canonicalLocaleIdentifierFromString(string: String?): String? {
			return CLASS.canonicalLocaleIdentifierFromString(string)
		}

		/**
		 * Original signature : `systemLocale()`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleCreation native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:30*
		 */
		fun systemLocale(): NSLocale? {
			return CLASS.systemLocale()
		}

		/**
		 * Original signature : `currentLocale()`<br></br>
		 * From category NSLocale<br></br>
		 * *from NSLocaleCreation native declaration : /System/Library/Frameworks/framework/Headers/NSLocale.h:31*
		 */
		fun currentLocale(): NSLocale? {
			return CLASS.currentLocale()
		}

		/**
		 * Factory method<br></br>
		 *
		 * @see .initWithLocaleIdentifier
		 */
		fun createWithLocaleIdentifier(string: String?): NSLocale? {
			return CLASS.alloc().initWithLocaleIdentifier(string)
		}
	}
}
