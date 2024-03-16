package darwin 

import org.rococoa.ID
import org.rococoa.ObjCClass
import org.rococoa.Selector
import org.rococoa.cocoa.foundation.NSSize

/// <i>native declaration : :10</i>
abstract class NSToolbarItem : NSObject(), NSCopying, NSValidatedUserInterfaceItem {
	interface _Class : ObjCClass {
		open fun alloc(): NSToolbarItem
	}

	/**
	 * Original signature : `id initWithItemIdentifier(NSString*)`<br></br>
	 * *native declaration : :62*
	 */
	abstract fun initWithItemIdentifier(itemIdentifier: String?): NSToolbarItem?

	/**
	 * Original signature : `NSString* itemIdentifier()`<br></br>
	 * *native declaration : :65*
	 */
	abstract fun itemIdentifier(): String?

	/**
	 * Original signature : `NSToolbar* toolbar()`<br></br>
	 * *native declaration : :66*
	 */
	abstract fun toolbar(): NSToolbar?

	/**
	 * Original signature : `void setLabel(NSString*)`<br></br>
	 * *native declaration : :71*
	 */
	abstract fun setLabel(label: String?)

	/**
	 * Original signature : `NSString* label()`<br></br>
	 * *native declaration : :72*
	 */
	abstract fun label(): String?

	/**
	 * Original signature : `void setPaletteLabel(NSString*)`<br></br>
	 * *native declaration : :75*
	 */
	abstract fun setPaletteLabel(paletteLabel: String?)

	/**
	 * Original signature : `NSString* paletteLabel()`<br></br>
	 * *native declaration : :76*
	 */
	abstract fun paletteLabel(): String?

	/**
	 * Original signature : `void setToolTip(NSString*)`<br></br>
	 * *native declaration : :79*
	 */
	abstract fun setToolTip(toolTip: String?)

	/**
	 * Original signature : `NSString* toolTip()`<br></br>
	 * *native declaration : :80*
	 */
	abstract fun toolTip(): String?

	/**
	 * Original signature : `void setMenuFormRepresentation(NSMenuItem*)`<br></br>
	 * *native declaration : :83*
	 */
	abstract fun setMenuFormRepresentation(menuItem: NSMenuItem?)

	/**
	 * Original signature : `NSMenuItem* menuFormRepresentation()`<br></br>
	 * *native declaration : :84*
	 */
	abstract fun menuFormRepresentation(): NSMenuItem?

	/**
	 * Original signature : `void setTag(NSInteger)`<br></br>
	 * *native declaration : :87*
	 */
	abstract fun setTag(tag: Int)

	/**
	 * Original signature : `void setTarget(id)`<br></br>
	 * *native declaration : :91*
	 */
	abstract fun setTarget(target: ID?)

	/**
	 * Original signature : `id target()`<br></br>
	 * *native declaration : :92*
	 */
	abstract fun target(): ID?

	/**
	 * *native declaration : :95*<br></br>
	 */
	abstract fun setAction(action: Selector?)

	/**
	 * Original signature : `void setEnabled(BOOL)`<br></br>
	 * *native declaration : :99*
	 */
	abstract fun setEnabled(enabled: Boolean)

	/**
	 * Original signature : `BOOL isEnabled()`<br></br>
	 * *native declaration : :100*
	 */
	abstract fun isEnabled(): Boolean

	/**
	 * Original signature : `void setImage(NSImage*)`<br></br>
	 * *native declaration : :103*
	 */
	abstract fun setImage(image: NSImage?)

	/**
	 * Original signature : `NSImage* image()`<br></br>
	 * *native declaration : :104*
	 */
	abstract fun image(): NSImage?

	/**
	 * Original signature : `void setView(NSView*)`<br></br>
	 * *native declaration : :107*
	 */
	abstract fun setView(view: NSView?)

	/**
	 * Original signature : `NSView* view()`<br></br>
	 * *native declaration : :108*
	 */
	abstract fun view(): NSView?

	/**
	 * *native declaration : :111*<br></br>
	 */
	abstract fun setMinSize(size: NSSize?)

	/**
	 * *native declaration : :112*<br></br>
	 */
	abstract fun minSize(): NSSize?

	/**
	 * *native declaration : :115*<br></br>
	 */
	abstract fun setMaxSize(size: NSSize?)

	/**
	 * *native declaration : :116*<br></br>
	 */
	abstract fun maxSize(): NSSize?

	/**
	 * Original signature : `void setVisibilityPriority(NSInteger)`<br></br>
	 * *native declaration : :123*
	 */
	abstract fun setVisibilityPriority(visibilityPriority: Int)

	/**
	 * Original signature : `NSInteger visibilityPriority()`<br></br>
	 * *native declaration : :124*
	 */
	abstract fun visibilityPriority(): Int

	/**
	 * Original signature : `void validate()`<br></br>
	 * *native declaration : :131*
	 */
	abstract fun validate()

	/**
	 * Original signature : `void setAutovalidates(BOOL)`<br></br>
	 * *native declaration : :136*
	 */
	abstract fun setAutovalidates(resistance: Boolean)

	/**
	 * Original signature : `BOOL autovalidates()`<br></br>
	 * *native declaration : :137*
	 */
	abstract fun autovalidates(): Boolean

	/**
	 * Original signature : `BOOL allowsDuplicatesInToolbar()`<br></br>
	 * *native declaration : :145*
	 */
	abstract fun allowsDuplicatesInToolbar(): Boolean

	/**
	 * The system can position navigation items outside of the normal list of items in the toolbar. You specify the
	 * order of the items using toolbarDefaultItemIdentifiers:.
	 *
	 * @return A Boolean value that indicates whether the item behaves as a navigation item in the toolbar.
	 */
	abstract fun isNavigational(): Boolean

	/**
	 * The system can position navigation items outside of the normal list of items in the toolbar. You specify the
	 * order of the items using toolbarDefaultItemIdentifiers:.
	 *
	 * @param value A Boolean value that indicates whether the item behaves as a navigation item in the toolbar.
	 */
	abstract fun setNavigational(value: Boolean)

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSToolbarItem", _Class::class.java)

		val NSToolbarFlexibleItemIdentifier: String? = "NSToolbarFlexibleSpaceItem"

		/**
		 * In macOS 10.7 and later the separator icon has been removed from the toolbar and customization palettes. This
		 * constant is ignored.
		 */
		val NSToolbarSeparatorItemIdentifier: String? = "NSToolbarSeparatorItem"

		/**
		 * Creates a new NSTrackingSeparatorToolbarItem and automatically configures it to track the divider of the sidebar
		 * if one is discovered.
		 */
		val NSToolbarSidebarTrackingSeparatorItemIdentifier: String? = "NSToolbarSidebarTrackingSeparatorItem"
		val NSToolbarSpaceItemIdentifier: String? = "NSToolbarSpaceItem"
		val NSToolbarFlexibleSpaceItemIdentifier: String? = "NSToolbarFlexibleSpaceItem"
		val NSToolbarShowColorsItemIdentifier: String? = "NSToolbarShowColorsItem"
		val NSToolbarShowFontsItemIdentifier: String? = "NSToolbarShowFontsItem"
		val NSToolbarCustomizeToolbarItemIdentifier: String? = "NSToolbarCustomizeToolbarItem"
		val NSToolbarPrintItemIdentifier: String? = "NSToolbarPrintItem"
		val NSToolbarToggleSidebarItemIdentifier: String? = "NSToolbarToggleSidebarItem"
		val NSToolbarCloudSharingItemIdentifier: String? = "NSToolbarCloudSharingItem"

		const val VisibilityPriorityStandard: Int = 0
		const val VisibilityPriorityLow: Int = -1000
		const val VisibilityPriorityHigh: Int = 1000
		const val VisibilityPriorityUser: Int = 2000

		fun itemWithIdentifier(itemIdentifier: String?): NSToolbarItem? {
			return CLASS.alloc().initWithItemIdentifier(itemIdentifier)
		}
	}
}
