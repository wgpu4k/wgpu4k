package darwin

import org.rococoa.ID
import org.rococoa.ObjCClass
import org.rococoa.Rococoa
import org.rococoa.cocoa.foundation.NSInteger
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : :17</i>
abstract class NSToolbar : NSObject() {
	interface _Class : ObjCClass {
		fun alloc(): NSToolbar
	}

	interface Delegate {
		fun validateToolbarItem(item: NSToolbarItem?): Boolean

		/**
		 * Original signature : `NSToolbarItem* toolbar(NSToolbar*, NSString*, BOOL)`<br></br>
		 * *native declaration : :149*
		 */
		fun toolbar_itemForItemIdentifier_willBeInsertedIntoToolbar(
			toolbar: NSToolbar?,
			itemIdentifier: String?,
			flag: Boolean
		): NSToolbarItem?

		/**
		 * Original signature : `NSArray* toolbarDefaultItemIdentifiers(NSToolbar*)`<br></br>
		 * *native declaration : :152*
		 */
		fun toolbarDefaultItemIdentifiers(toolbar: NSToolbar?): NSArray?

		/**
		 * Original signature : `NSArray* toolbarAllowedItemIdentifiers(NSToolbar*)`<br></br>
		 * *native declaration : :155*
		 */
		fun toolbarAllowedItemIdentifiers(toolbar: NSToolbar?): NSArray?

		/**
		 * Original signature : `NSArray* toolbarSelectableItemIdentifiers(NSToolbar*)`<br></br>
		 * *native declaration : :159*
		 */
		fun toolbarSelectableItemIdentifiers(toolbar: NSToolbar?): NSArray?
	}

	/**
	 * Original signature : `id initWithIdentifier(NSString*)`<br></br>
	 * *native declaration : :68*
	 */
	abstract fun initWithIdentifier(identifier: String?): NSToolbar

	/**
	 * Original signature : `void insertItemWithItemIdentifier(NSString*, NSInteger)`<br></br>
	 * *native declaration : :71*
	 */
	abstract fun insertItemWithItemIdentifier_atIndex(itemIdentifier: String?, index: NSInteger?)

	/**
	 * Original signature : `void removeItemAtIndex(NSInteger)`<br></br>
	 * *native declaration : :72*
	 */
	abstract fun removeItemAtIndex(index: NSInteger?)

	/**
	 * Original signature : `void setDelegate(id)`<br></br>
	 * *native declaration : :75*
	 */
	abstract fun setDelegate(delegate: ID?)

	/**
	 * Original signature : `id delegate()`<br></br>
	 * *native declaration : :76*
	 */
	abstract fun delegate(): ID?

	/**
	 * Original signature : `BOOL isVisible()`<br></br>
	 * *native declaration : :80*
	 */
	/**
	 * Original signature : `void setVisible(BOOL)`<br></br>
	 * *native declaration : :79*
	 */
	abstract var isVisible: Boolean

	/**
	 * Original signature : `void runCustomizationPalette(id)`<br></br>
	 * *native declaration : :83*
	 */
	abstract fun runCustomizationPalette(sender: ID?)

	/**
	 * Original signature : `BOOL customizationPaletteIsRunning()`<br></br>
	 * *native declaration : :84*
	 */
	abstract fun customizationPaletteIsRunning(): Boolean

	/**
	 * Original signature : `void setDisplayMode(NSToolbarDisplayMode)`<br></br>
	 * *native declaration : :90*
	 */
	abstract fun setDisplayMode(displayMode: NSUInteger?)

	/**
	 * Original signature : `NSToolbarDisplayMode displayMode()`<br></br>
	 * *native declaration : :91*
	 */
	abstract fun displayMode(): NSUInteger?

	/**
	 * Original signature : `void setSelectedItemIdentifier(NSString*)`<br></br>
	 * *native declaration : :94*
	 */
	abstract fun setSelectedItemIdentifier(itemIdentifier: String?)

	/**
	 * Original signature : `NSString* selectedItemIdentifier()`<br></br>
	 * *native declaration : :95*
	 */
	abstract fun selectedItemIdentifier(): String?

	/**
	 * Original signature : `void setSizeMode(NSToolbarSizeMode)`<br></br>
	 * *native declaration : :100*
	 */
	abstract fun setSizeMode(sizeMode: NSUInteger?)

	/**
	 * Original signature : `NSToolbarSizeMode sizeMode()`<br></br>
	 * *native declaration : :101*
	 */
	abstract fun sizeMode(): NSUInteger?

	/**
	 * Use this API to hide the baseline NSToolbar draws between itself and the main window contents.  The default is YES.  This method should only be used before the toolbar is attached to its window (-[NSWindow setToolbar:]).<br></br>
	 * Original signature : `void setShowsBaselineSeparator(BOOL)`<br></br>
	 * *native declaration : :107*
	 */
	abstract fun setShowsBaselineSeparator(flag: Boolean)

	/**
	 * Original signature : `BOOL showsBaselineSeparator()`<br></br>
	 * *native declaration : :108*
	 */
	abstract fun showsBaselineSeparator(): Boolean

	/**
	 * Original signature : `void setAllowsUserCustomization(BOOL)`<br></br>
	 * *native declaration : :111*
	 */
	abstract fun setAllowsUserCustomization(allowCustomization: Boolean)

	/**
	 * Original signature : `BOOL allowsUserCustomization()`<br></br>
	 * *native declaration : :112*
	 */
	abstract fun allowsUserCustomization(): Boolean

	/**
	 * Original signature : `NSString* identifier()`<br></br>
	 * *native declaration : :118*
	 */
	abstract fun identifier(): String?

	/**
	 * Original signature : `NSArray* items()`<br></br>
	 * *native declaration : :121*
	 */
	abstract fun items(): NSArray?

	/**
	 * Original signature : `NSArray* visibleItems()`<br></br>
	 * *native declaration : :124*
	 */
	abstract fun visibleItems(): NSArray?

	/**
	 * Original signature : `void setAutosavesConfiguration(BOOL)`<br></br>
	 * *native declaration : :130*
	 */
	abstract fun setAutosavesConfiguration(flag: Boolean)

	/**
	 * Original signature : `BOOL autosavesConfiguration()`<br></br>
	 * *native declaration : :131*
	 */
	abstract fun autosavesConfiguration(): Boolean

	/**
	 * Original signature : `void setConfigurationFromDictionary(NSDictionary*)`<br></br>
	 * *native declaration : :134*
	 */
	abstract fun setConfigurationFromDictionary(configDict: NSDictionary?)

	/**
	 * Original signature : `NSDictionary* configurationDictionary()`<br></br>
	 * *native declaration : :135*
	 */
	abstract fun configurationDictionary(): NSDictionary?

	/**
	 * Original signature : `void validateVisibleItems()`<br></br>
	 * *native declaration : :141*
	 */
	abstract fun validateVisibleItems()

	companion object {
		private val CLASS: _Class = Rococoa.createClass("NSToolbar", _Class::class.java)

		/// <i>native declaration : :12</i>
		val NSToolbarDisplayModeDefault: NSUInteger = NSUInteger(0)

		/// <i>native declaration : :12</i>
		val NSToolbarDisplayModeIconAndLabel: NSUInteger = NSUInteger(1)

		/// <i>native declaration : :12</i>
		val NSToolbarDisplayModeIconOnly: NSUInteger = NSUInteger(2)

		/// <i>native declaration : :12</i>
		val NSToolbarDisplayModeLabelOnly: NSUInteger = NSUInteger(3)

		/// <i>native declaration : :15</i>
		val NSToolbarSizeModeDefault: NSUInteger = NSUInteger(0)

		/// <i>native declaration : :15</i>
		val NSToolbarSizeModeRegular: NSUInteger = NSUInteger(1)

		/// <i>native declaration : :15</i>
		val NSToolbarSizeModeSmall: NSUInteger = NSUInteger(2)

		fun toolbarWithIdentifier(identifier: String?): NSToolbar {
			return CLASS.alloc().initWithIdentifier(identifier)
		}
	}
}
