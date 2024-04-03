package darwin 

import org.rococoa.ObjCClass
import org.rococoa.Selector

/// <i>native declaration : :14</i>
abstract class NSMenuItem : NSObject(), NSCopying, NSValidatedUserInterfaceItem {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `void setUsesUserKeyEquivalents(BOOL)`<br></br>
		 * *native declaration : :44*
		 */
		open fun setUsesUserKeyEquivalents(flag: Boolean)

		/**
		 * Original signature : `BOOL usesUserKeyEquivalents()`<br></br>
		 * *native declaration : :45*
		 */
		open fun usesUserKeyEquivalents(): Boolean

		/**
		 * Original signature : `NSMenuItem* separatorItem()`<br></br>
		 * *native declaration : :47*
		 */
		open fun separatorItem(): NSMenuItem?

		open fun alloc(): NSMenuItem
	}

	/**
	 * Original signature : `id initWithTitle(NSString*, SEL, NSString*)`<br></br>
	 * *native declaration : :49*
	 */
	abstract fun initWithTitle_action_keyEquivalent(
		aString: String?,
		aSelector: Selector?,
		charCode: String?
	): NSMenuItem?

	/**
	 * Original signature : `void setMenu(NSMenu*)`<br></br>
	 * *native declaration : :51*
	 */
	abstract fun setMenu(menu: NSMenu?)

	/**
	 * Original signature : `NSMenu* menu()`<br></br>
	 * *native declaration : :52*
	 */
	abstract fun menu(): NSMenu?

	/**
	 * Original signature : `BOOL hasSubmenu()`<br></br>
	 * *native declaration : :56*
	 */
	abstract fun hasSubmenu(): Boolean

	/**
	 * Original signature : `void setSubmenu(NSMenu*)`<br></br>
	 * *native declaration : :57*
	 */
	abstract fun setSubmenu(submenu: NSMenu?)

	/**
	 * Original signature : `NSMenu* submenu()`<br></br>
	 * *native declaration : :58*
	 */
	abstract fun submenu(): NSMenu?

	/**
	 * Original signature : `void setTitle(NSString*)`<br></br>
	 * *native declaration : :60*
	 */
	abstract fun setTitle(aString: String?)

	/**
	 * Original signature : `NSString* title()`<br></br>
	 * *native declaration : :61*
	 */
	abstract fun title(): String?

	/**
	 * Original signature : `void setAttributedTitle(NSAttributedString*)`<br></br>
	 * *native declaration : :63*
	 */
	abstract fun setAttributedTitle(string: NSAttributedString?)

	/**
	 * Original signature : `NSAttributedString* attributedTitle()`<br></br>
	 * *native declaration : :64*
	 */
	abstract fun attributedTitle(): NSAttributedString?

	/**
	 * Original signature : `BOOL isSeparatorItem()`<br></br>
	 * *native declaration : :67*
	 */
	abstract fun isSeparatorItem(): Boolean

	/**
	 * Original signature : `void setKeyEquivalent(NSString*)`<br></br>
	 * *native declaration : :69*
	 */
	abstract fun setKeyEquivalent(aKeyEquivalent: String?)

	/**
	 * Original signature : `NSString* keyEquivalent()`<br></br>
	 * *native declaration : :70*
	 */
	abstract fun keyEquivalent(): String?

	/**
	 * Original signature : `void setKeyEquivalentModifierMask(NSUInteger)`<br></br>
	 * *native declaration : :71*
	 */
	abstract fun setKeyEquivalentModifierMask(mask: Int)

	/**
	 * Original signature : `NSUInteger keyEquivalentModifierMask()`<br></br>
	 * *native declaration : :72*
	 */
	abstract fun keyEquivalentModifierMask(): Int

	/**
	 * Original signature : `NSString* userKeyEquivalent()`<br></br>
	 * *native declaration : :74*
	 */
	abstract fun userKeyEquivalent(): String?

	/**
	 * Original signature : `void setMnemonicLocation(NSUInteger)`<br></br>
	 * *native declaration : :76*
	 */
	abstract fun setMnemonicLocation(location: Int)

	/**
	 * Original signature : `NSUInteger mnemonicLocation()`<br></br>
	 * *native declaration : :77*
	 */
	abstract fun mnemonicLocation(): Int

	/**
	 * Original signature : `NSString* mnemonic()`<br></br>
	 * *native declaration : :78*
	 */
	abstract fun mnemonic(): String?

	/**
	 * Original signature : `void setTitleWithMnemonic(NSString*)`<br></br>
	 * *native declaration : :79*
	 */
	abstract fun setTitleWithMnemonic(stringWithAmpersand: String?)

	/**
	 * Original signature : `void setImage(NSImage*)`<br></br>
	 * *native declaration : :81*
	 */
	abstract fun setImage(menuImage: NSImage?)

	/**
	 * Original signature : `NSImage* image()`<br></br>
	 * *native declaration : :82*
	 */
	abstract fun image(): NSImage?

	/**
	 * Original signature : `void setState(NSInteger)`<br></br>
	 * *native declaration : :84*
	 */
	abstract fun setState(state: Int)

	/**
	 * Original signature : `NSInteger state()`<br></br>
	 * *native declaration : :85*
	 */
	abstract fun state(): Int

	/**
	 * Original signature : `void setOnStateImage(NSImage*)`<br></br>
	 * checkmark by default<br></br>
	 * *native declaration : :86*
	 */
	abstract fun setOnStateImage(image: NSImage?)

	/**
	 * Original signature : `NSImage* onStateImage()`<br></br>
	 * *native declaration : :87*
	 */
	abstract fun onStateImage(): NSImage?

	/**
	 * Original signature : `void setOffStateImage(NSImage*)`<br></br>
	 * none by default<br></br>
	 * *native declaration : :88*
	 */
	abstract fun setOffStateImage(image: NSImage?)

	/**
	 * Original signature : `NSImage* offStateImage()`<br></br>
	 * *native declaration : :89*
	 */
	abstract fun offStateImage(): NSImage?

	/**
	 * Original signature : `void setMixedStateImage(NSImage*)`<br></br>
	 * horizontal line by default?<br></br>
	 * *native declaration : :90*
	 */
	abstract fun setMixedStateImage(image: NSImage?)

	/**
	 * Original signature : `NSImage* mixedStateImage()`<br></br>
	 * *native declaration : :91*
	 */
	abstract fun mixedStateImage(): NSImage?

	/**
	 * Original signature : `void setEnabled(BOOL)`<br></br>
	 * *native declaration : :93*
	 */
	abstract fun setEnabled(flag: Boolean)

	/**
	 * Original signature : `BOOL isEnabled()`<br></br>
	 * *native declaration : :94*
	 */
	abstract fun isEnabled(): Boolean

	/**
	 * Original signature : `void setAlternate(BOOL)`<br></br>
	 * *native declaration : :98*
	 */
	abstract fun setAlternate(isAlternate: Boolean)

	/**
	 * Original signature : `BOOL isAlternate()`<br></br>
	 * *native declaration : :99*
	 */
	abstract fun isAlternate(): Boolean

	/**
	 * Original signature : `void setIndentationLevel(NSInteger)`<br></br>
	 * *native declaration : :101*
	 */
	abstract fun setIndentationLevel(indentationLevel: Int)

	/**
	 * Original signature : `NSInteger indentationLevel()`<br></br>
	 * *native declaration : :102*
	 */
	abstract fun indentationLevel(): Int

	/**
	 * Original signature : `void setTarget(id)`<br></br>
	 * *native declaration : :105*
	 */
	abstract fun setTarget(anObject: org.rococoa.ID?)

	/**
	 * Original signature : `id target()`<br></br>
	 * *native declaration : :106*
	 */
	abstract fun target(): org.rococoa.ID?

	/**
	 * Original signature : `void setAction(SEL)`<br></br>
	 * *native declaration : :107*
	 */
	abstract fun setAction(aSelector: Selector?)

	/**
	 * Original signature : `void setTag(NSInteger)`<br></br>
	 * *native declaration : :110*
	 */
	abstract fun setTag(anInt: Int)

	/**
	 * Original signature : `void setRepresentedObject(id)`<br></br>
	 * *native declaration : :113*
	 */
	abstract fun setRepresentedObject(anObject: String?)

	/**
	 * Original signature : `id representedObject()`<br></br>
	 * *native declaration : :114*
	 */
	abstract fun representedObject(): String?

	/**
	 * Set (and get) the view for a menu item.  By default, a menu item has a nil view.<br></br>
	 * A menu item with a view does not draw its title, state, font, or other standard drawing attributes, and assigns drawing responsibility entirely to the view.  Keyboard equivalents and type-select continue to use the key equivalent and title as normal.<br></br>
	 * A menu item with a view sizes itself according to the view's frame, and the width of the other menu items.  The menu item will always be at least as wide as its view, but it may be wider.  If you want your view to auto-expand to fill the menu item, then make sure that its autoresizing mask has NSViewWidthSizable set; in that case, the view's width at the time setView: is called will be treated as the minimum width for the view.  A menu will resize itself as its containing views change frame size.  Changes to the view's frame during tracking are reflected immediately in the menu.<br></br>
	 * A view in a menu item will receive mouse and keyboard events normally.  During non-sticky menu tracking (manipulating menus with the mouse button held down), a view in a menu item will receive mouseDragged: events.<br></br>
	 * Animation is possible via the usual mechanism (set a timer to call setNeedsDisplay: or display), but because menu tracking occurs in the NSEventTrackingRunLoopMode, you must add the timer to the run loop in that mode.<br></br>
	 * When the menu is opened, the view is added to a window; when the menu is closed the view is removed from the window.  Override viewDidMoveToWindow in your view for a convenient place to start/stop animations, reset tracking rects, etc., but do not attempt to move or otherwise modify the window.<br></br>
	 * When a menu item is copied via NSCopying, any attached view is copied via archiving/unarchiving.  Menu item views are not supported in the Dock menu.<br></br>
	 * Original signature : `void setView(NSView*)`<br></br>
	 * *native declaration : :124*
	 */
	abstract fun setView(view: NSView?)

	/**
	 * Original signature : `NSView* view()`<br></br>
	 * *native declaration : :125*
	 */
	abstract fun view(): NSView?

	/**
	 * Indicates whether the menu item should be drawn highlighted or not.<br></br>
	 * Original signature : `BOOL isHighlighted()`<br></br>
	 * *native declaration : :128*
	 */
	abstract fun isHighlighted(): Boolean

	/**
	 * Set (and get) the visibility of a menu item.  Hidden menu items (or items with a hidden superitem) do not appear in a menu and do not participate in command key matching.  isHiddenOrHasHiddenAncestor returns YES if the item is hidden or any of its superitems are hidden.<br></br>
	 * Original signature : `void setHidden(BOOL)`<br></br>
	 * *native declaration : :131*
	 */
	abstract fun setHidden(hidden: Boolean)

	/**
	 * Original signature : `BOOL isHidden()`<br></br>
	 * *native declaration : :132*
	 */
	abstract fun isHidden(): Boolean

	/**
	 * Original signature : `BOOL isHiddenOrHasHiddenAncestor()`<br></br>
	 * *native declaration : :133*
	 */
	abstract fun isHiddenOrHasHiddenAncestor(): Boolean

	/**
	 * Original signature : `void setToolTip(NSString*)`<br></br>
	 * *native declaration : :138*
	 */
	abstract fun setToolTip(toolTip: String?)

	/**
	 * Original signature : `NSString* toolTip()`<br></br>
	 * *native declaration : :139*
	 */
	abstract fun toolTip(): String?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSMenuItem", _Class::class.java)

		fun separatorItem(): NSMenuItem? {
			return CLASS.separatorItem()
		}

		fun itemWithTitle(title: String?, selector: Selector?, charCode: String?): NSMenuItem? {
			return CLASS.alloc().initWithTitle_action_keyEquivalent(title, selector, charCode)
		}
	}
}
