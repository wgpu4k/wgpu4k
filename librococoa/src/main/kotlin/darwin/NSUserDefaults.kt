package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSInteger

/// <i>native declaration : :14</i>
abstract class NSUserDefaults : NSObject() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `NSUserDefaults* standardUserDefaults()`<br></br>
		 * *native declaration : :20*
		 */
		open fun standardUserDefaults(): NSUserDefaults?

		/**
		 * Original signature : `void resetStandardUserDefaults()`<br></br>
		 * *native declaration : :21*
		 */
		open fun resetStandardUserDefaults()

		open fun alloc(): NSUserDefaults
	}

	/**
	 * Original signature : `id init()`<br></br>
	 * *native declaration : :23*
	 */
	abstract fun init(): NSUserDefaults?

	/**
	 * Original signature : `id initWithUser(NSString*)`<br></br>
	 * *native declaration : :24*
	 */
	abstract fun initWithUser(username: String?): NSUserDefaults?

	/**
	 * Returns an NSUserDefaults object initialized with the defaults for the specified app group.
	 */
	abstract fun initWithSuiteName(group: String?): NSUserDefaults?

	/**
	 * Original signature : `id objectForKey(NSString*)`<br></br>
	 * *native declaration : :26*
	 */
	abstract fun objectForKey(defaultName: String?): NSObject?

	fun setObjectForKey(value: NSObject?, defaultName: String?) {
		this.setObject_forKey(value, defaultName)
	}

	/**
	 * Original signature : `void setObject(id, NSString*)`<br></br>
	 * *native declaration : :27*
	 */
	abstract fun setObject_forKey(value: NSObject?, defaultName: String?)

	/**
	 * Original signature : `void removeObjectForKey(NSString*)`<br></br>
	 * *native declaration : :28*
	 */
	abstract fun removeObjectForKey(defaultName: String?)

	/**
	 * Original signature : `NSString* stringForKey(NSString*)`<br></br>
	 * *native declaration : :30*
	 */
	abstract fun stringForKey(defaultName: String?): String?

	/**
	 * Original signature : `NSArray* arrayForKey(NSString*)`<br></br>
	 * *native declaration : :31*
	 */
	abstract fun arrayForKey(defaultName: String?): NSArray?

	/**
	 * Original signature : `NSDictionary* dictionaryForKey(NSString*)`<br></br>
	 * *native declaration : :32*
	 */
	abstract fun dictionaryForKey(defaultName: String?): NSDictionary?

	/**
	 * Original signature : `NSData* dataForKey(NSString*)`<br></br>
	 * *native declaration : :33*
	 */
	abstract fun dataForKey(defaultName: String?): NSData?

	/**
	 * Original signature : `NSArray* stringArrayForKey(NSString*)`<br></br>
	 * *native declaration : :34*
	 */
	abstract fun stringArrayForKey(defaultName: String?): NSArray?

	/**
	 * Original signature : `NSInteger integerForKey(NSString*)`<br></br>
	 * *native declaration : :35*
	 */
	abstract fun integerForKey(defaultName: String?): NSInteger?

	/**
	 * Original signature : `float floatForKey(NSString*)`<br></br>
	 * *native declaration : :36*
	 */
	abstract fun floatForKey(defaultName: String?): Float

	/**
	 * Original signature : `double doubleForKey(NSString*)`<br></br>
	 * *native declaration : :37*
	 */
	abstract fun doubleForKey(defaultName: String?): Double

	/**
	 * Original signature : `BOOL boolForKey(NSString*)`<br></br>
	 * *native declaration : :38*
	 */
	abstract fun boolForKey(defaultName: String?): Boolean

	/**
	 * Original signature : `void setInteger(NSInteger, NSString*)`<br></br>
	 * *native declaration : :40*
	 */
	abstract fun setInteger_forKey(value: NSInteger?, defaultName: String?)

	/**
	 * Original signature : `void setFloat(float, NSString*)`<br></br>
	 * *native declaration : :41*
	 */
	abstract fun setFloat_forKey(value: Float, defaultName: String?)

	/**
	 * Original signature : `void setDouble(double, NSString*)`<br></br>
	 * *native declaration : :42*
	 */
	abstract fun setDouble_forKey(value: Double, defaultName: String?)

	/**
	 * Original signature : `void setBool(BOOL, NSString*)`<br></br>
	 * *native declaration : :43*
	 */
	abstract fun setBool_forKey(value: Boolean, defaultName: String?)

	/**
	 * Original signature : `void registerDefaults(NSDictionary*)`<br></br>
	 * *native declaration : :45*
	 */
	abstract fun registerDefaults(registrationDictionary: NSDictionary?)

	/**
	 * Original signature : `void addSuiteNamed(NSString*)`<br></br>
	 * *native declaration : :47*
	 */
	abstract fun addSuiteNamed(suiteName: String?)

	/**
	 * Original signature : `void removeSuiteNamed(NSString*)`<br></br>
	 * *native declaration : :48*
	 */
	abstract fun removeSuiteNamed(suiteName: String?)

	/**
	 * Original signature : `NSDictionary* dictionaryRepresentation()`<br></br>
	 * *native declaration : :50*
	 */
	abstract fun dictionaryRepresentation(): NSDictionary?

	/**
	 * Original signature : `NSArray* volatileDomainNames()`<br></br>
	 * *native declaration : :52*
	 */
	abstract fun volatileDomainNames(): NSArray?

	/**
	 * Original signature : `NSDictionary* volatileDomainForName(NSString*)`<br></br>
	 * *native declaration : :53*
	 */
	abstract fun volatileDomainForName(domainName: String?): NSDictionary?

	/**
	 * Original signature : `void setVolatileDomain(NSDictionary*, NSString*)`<br></br>
	 * *native declaration : :54*
	 */
	abstract fun setVolatileDomain_forName(domain: NSDictionary?, domainName: String?)

	/**
	 * Original signature : `void removeVolatileDomainForName(NSString*)`<br></br>
	 * *native declaration : :55*
	 */
	abstract fun removeVolatileDomainForName(domainName: String?)

	/**
	 * Original signature : `NSArray* persistentDomainNames()`<br></br>
	 * *native declaration : :57*
	 */
	abstract fun persistentDomainNames(): NSArray?

	/**
	 * Original signature : `NSDictionary* persistentDomainForName(NSString*)`<br></br>
	 * *native declaration : :58*
	 */
	abstract fun persistentDomainForName(domainName: String?): NSDictionary?

	/**
	 * Original signature : `void setPersistentDomain(NSDictionary*, NSString*)`<br></br>
	 * *native declaration : :59*
	 */
	abstract fun setPersistentDomain_forName(domain: NSDictionary?, domainName: String?)

	/**
	 * Original signature : `void removePersistentDomainForName(NSString*)`<br></br>
	 * *native declaration : :60*
	 */
	abstract fun removePersistentDomainForName(domainName: String?)

	/**
	 * Original signature : `BOOL synchronize()`<br></br>
	 * *native declaration : :62*
	 */
	abstract fun synchronize(): Boolean

	/**
	 * Original signature : `BOOL objectIsForcedForKey(NSString*)`<br></br>
	 * *native declaration : :65*
	 */
	abstract fun objectIsForcedForKey(key: String?): Boolean

	/**
	 * Original signature : `BOOL objectIsForcedForKey(NSString*, NSString*)`<br></br>
	 * *native declaration : :66*
	 */
	abstract fun objectIsForcedForKey_inDomain(key: String?, domain: String?): Boolean

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSUserDefaults", _Class::class.java)

		fun standardUserDefaults(): NSUserDefaults? {
			return CLASS.standardUserDefaults()
		}

		fun sharedUserDefaults(group: String?): NSUserDefaults? {
			return CLASS.alloc().initWithSuiteName(group)
		}

		fun resetStandardUserDefaults() {
			CLASS.resetStandardUserDefaults()
		}
	}
}
