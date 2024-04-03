package darwin 

import org.rococoa.ID
import org.rococoa.ObjCClass
import org.rococoa.Selector
import org.rococoa.cocoa.foundation.NSInteger

abstract class NSMenu : NSObject(), NSCopying {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `void popUpContextMenu(NSMenu*, NSEvent*, NSView*)`<br></br>
		 * *native declaration : :44*
		 */
		open fun popUpContextMenu_withEvent_forView(menu: NSMenu?, event: NSEvent?, view: NSView?)

		/**
		 * Original signature : `void popUpContextMenu(NSMenu*, NSEvent*, NSView*, NSFont*)`<br></br>
		 * *native declaration : :46*
		 */
		open fun popUpContextMenu_withEvent_forView_withFont(
			menu: NSMenu?,
			event: NSEvent?,
			view: NSView?,
			font: NSFont?
		)

		/**
		 * Original signature : `void setMenuBarVisible(BOOL)`<br></br>
		 * *native declaration : :50*
		 */
		open fun setMenuBarVisible(visible: Boolean)

		/**
		 * Original signature : `BOOL menuBarVisible()`<br></br>
		 * *native declaration : :51*
		 */
		open fun menuBarVisible(): Boolean

		open fun alloc(): NSMenu
	}

	interface Validation {
		open fun validateMenuItem(item: NSMenuItem?): Boolean
	}

	interface Delegate {
		/**
		 * @param menu
		 * @return If you return a positive value, the menu is resized by either removing or adding items.
		 * Newly created items are blank. After the menu is resized, your menu:updateItem:atIndex:shouldCancel: method
		 * is called for each item. If you return a negative value, the number of items is left unchanged
		 * and menu:updateItem:atIndex:shouldCancel: is not called. If you can populate the menu quickly,
		 * you can implement menuNeedsUpdate: instead of numberOfItemsInMenu: and menu:updateItem:atIndex:shouldCancel:.
		 */
		open fun numberOfItemsInMenu(menu: NSMenu?): NSInteger?

		open fun menu_updateItem_atIndex_shouldCancel(
			menu: NSMenu?,
			item: NSMenuItem?,
			index: NSInteger?,
			shouldCancel: Boolean
		): Boolean
	}

	abstract fun init(): NSMenu?

	/**
	 * Original signature : `id initWithTitle(NSString*)`<br></br>
	 * *native declaration : :54*
	 */
	abstract fun initWithTitle(aTitle: String?): NSMenu?

	/**
	 * Original signature : `void setTitle(NSString*)`<br></br>
	 * *native declaration : :56*
	 */
	abstract fun setTitle(aString: String?)

	/**
	 * Original signature : `NSString* title()`<br></br>
	 * *native declaration : :57*
	 */
	abstract fun title(): String?

	/**
	 * Original signature : `void setSupermenu(NSMenu*)`<br></br>
	 * *native declaration : :59*
	 */
	abstract fun setSupermenu(supermenu: NSMenu?)

	/**
	 * Original signature : `NSMenu* supermenu()`<br></br>
	 * *native declaration : :60*
	 */
	abstract fun supermenu(): NSMenu?

	/**
	 * Original signature : `void insertItem(NSMenuItem*, NSInteger)`<br></br>
	 * *native declaration : :63*
	 */
	abstract fun insertItem_atIndex(newItem: NSMenuItem?, index: NSInteger?)

	/**
	 * Original signature : `void addItem(NSMenuItem*)`<br></br>
	 * *native declaration : :64*
	 */
	abstract fun addItem(newItem: NSMenuItem?)

	/**
	 * *native declaration : :65*<br></br>
	 * Conversion Error : /// Original signature : `NSMenuItem* insertItemWithTitle(NSString*, null, NSString*, NSInteger)`<br></br>
	 * - (NSMenuItem*)insertItemWithTitle:(NSString*)aString action:(null)aSelector keyEquivalent:(NSString*)charCode atIndex:(NSInteger)index; (Argument aSelector cannot be converted)
	 */
	abstract fun insertItemWithTitle_action_keyEquivalent_atIndex(
		title: String?,
		action: Selector?,
		charCode: String?,
		index: NSInteger?
	): NSMenuItem?

	/**
	 * *native declaration : :66*<br></br>
	 * Conversion Error : /// Original signature : `NSMenuItem* addItemWithTitle(NSString*, null, NSString*)`<br></br>
	 * - (NSMenuItem*)addItemWithTitle:(NSString*)aString action:(null)aSelector keyEquivalent:(NSString*)charCode; (Argument aSelector cannot be converted)
	 */
	abstract fun addItemWithTitle_action_keyEquivalent(
		title: String?,
		action: Selector?,
		charCode: String?
	): NSMenuItem?

	/**
	 * Original signature : `void removeItemAtIndex(NSInteger)`<br></br>
	 * *native declaration : :67*
	 */
	abstract fun removeItemAtIndex(index: NSInteger?)

	/**
	 * Original signature : `void removeItem(NSMenuItem*)`<br></br>
	 * *native declaration : :68*
	 */
	abstract fun removeItem(item: NSMenuItem?)

	abstract fun removeAllItems()

	/**
	 * Original signature : `void setSubmenu(NSMenu*, NSMenuItem*)`<br></br>
	 * *native declaration : :69*
	 */
	abstract fun setSubmenu_forItem(aMenu: NSMenu?, anItem: NSMenuItem?)

	/**
	 * Original signature : `NSArray* itemArray()`<br></br>
	 * *native declaration : :71*
	 */
	abstract fun itemArray(): NSArray?

	/**
	 * Original signature : `NSInteger numberOfItems()`<br></br>
	 * *native declaration : :72*
	 */
	abstract fun numberOfItems(): NSInteger?

	/**
	 * Original signature : `NSInteger indexOfItem(NSMenuItem*)`<br></br>
	 * *native declaration : :74*
	 */
	abstract fun indexOfItem(index: NSMenuItem?): NSInteger?

	/**
	 * Original signature : `NSInteger indexOfItemWithTitle(NSString*)`<br></br>
	 * *native declaration : :75*
	 */
	abstract fun indexOfItemWithTitle(aTitle: String?): NSInteger?

	/**
	 * Original signature : `NSInteger indexOfItemWithTag(NSInteger)`<br></br>
	 * *native declaration : :76*
	 */
	abstract fun indexOfItemWithTag(aTag: NSInteger?): NSInteger?

	/**
	 * Original signature : `NSInteger indexOfItemWithRepresentedObject(id)`<br></br>
	 * *native declaration : :77*
	 */
	abstract fun indexOfItemWithRepresentedObject(`object`: String?): NSInteger?

	/**
	 * Original signature : `NSInteger indexOfItemWithSubmenu(NSMenu*)`<br></br>
	 * *native declaration : :78*
	 */
	abstract fun indexOfItemWithSubmenu(submenu: NSMenu?): NSInteger?
	/**
	 * *native declaration : :79*<br></br>
	 * Conversion Error : /// Original signature : `NSInteger indexOfItemWithTarget(id, null)`<br></br>
	 * - (NSInteger)indexOfItemWithTarget:(id)target andAction:(null)actionSelector; (Argument actionSelector cannot be converted)
	 */
	/**
	 * Original signature : `NSMenuItem* itemAtIndex(NSInteger)`<br></br>
	 * *native declaration : :81*
	 */
	abstract fun itemAtIndex(index: NSInteger?): NSMenuItem?

	/**
	 * Original signature : `NSMenuItem* itemWithTitle(NSString*)`<br></br>
	 * *native declaration : :82*
	 */
	abstract fun itemWithTitle(aTitle: String?): NSMenuItem?

	/**
	 * Original signature : `NSMenuItem* itemWithTag(NSInteger)`<br></br>
	 * *native declaration : :83*
	 */
	abstract fun itemWithTag(tag: NSInteger?): NSMenuItem?

	/**
	 * Original signature : `void setAutoenablesItems(BOOL)`<br></br>
	 * *native declaration : :85*
	 */
	abstract fun setAutoenablesItems(flag: Boolean)

	/**
	 * Original signature : `BOOL autoenablesItems()`<br></br>
	 * *native declaration : :86*
	 */
	abstract fun autoenablesItems(): Boolean

	/**
	 * Original signature : `BOOL performKeyEquivalent(NSEvent*)`<br></br>
	 * *native declaration : :88*
	 */
	abstract fun performKeyEquivalent(event: NSEvent?): Boolean

	/**
	 * Original signature : `void update()`<br></br>
	 * *native declaration : :89*
	 */
	abstract fun update()

	/**
	 * Original signature : `void setMenuChangedMessagesEnabled(BOOL)`<br></br>
	 * *native declaration : :91*
	 */
	abstract fun setMenuChangedMessagesEnabled(flag: Boolean)

	/**
	 * Original signature : `BOOL menuChangedMessagesEnabled()`<br></br>
	 * *native declaration : :92*
	 */
	abstract fun menuChangedMessagesEnabled(): Boolean

	/**
	 * Original signature : `void itemChanged(NSMenuItem*)`<br></br>
	 * *native declaration : :94*
	 */
	abstract fun itemChanged(item: NSMenuItem?)

	/**
	 * Original signature : `void helpRequested(NSEvent*)`<br></br>
	 * *native declaration : :96*
	 */
	abstract fun helpRequested(eventPtr: NSEvent?)

	/**
	 * Original signature : `void setMenuRepresentation(id)`<br></br>
	 * *native declaration : :98*
	 */
	abstract fun setMenuRepresentation(menuRep: ID?)

	/**
	 * Original signature : `id menuRepresentation()`<br></br>
	 * *native declaration : :99*
	 */
	abstract fun menuRepresentation(): ID?

	/**
	 * Original signature : `void setContextMenuRepresentation(id)`<br></br>
	 * *native declaration : :101*
	 */
	abstract fun setContextMenuRepresentation(menuRep: ID?)

	/**
	 * Original signature : `id contextMenuRepresentation()`<br></br>
	 * *native declaration : :102*
	 */
	abstract fun contextMenuRepresentation(): ID?

	/**
	 * Original signature : `void setTearOffMenuRepresentation(id)`<br></br>
	 * *native declaration : :104*
	 */
	abstract fun setTearOffMenuRepresentation(menuRep: ID?)

	/**
	 * Original signature : `id tearOffMenuRepresentation()`<br></br>
	 * *native declaration : :105*
	 */
	abstract fun tearOffMenuRepresentation(): ID?

	/**
	 * Original signature : `BOOL isTornOff()`<br></br>
	 * *native declaration : :107*
	 */
	abstract fun isTornOff(): Boolean

	/**
	 * These methods are platform specific.  They really make little sense on Windows.  Their use is discouraged.<br></br>
	 * Original signature : `NSMenu* attachedMenu()`<br></br>
	 * *native declaration : :110*
	 */
	abstract fun attachedMenu(): NSMenu?

	/**
	 * Original signature : `BOOL isAttached()`<br></br>
	 * *native declaration : :111*
	 */
	abstract fun isAttached(): Boolean

	/**
	 * Original signature : `void sizeToFit()`<br></br>
	 * *native declaration : :112*
	 */
	abstract fun sizeToFit()

	/**
	 * Original signature : `locationForSubmenu(NSMenu*)`<br></br>
	 * *native declaration : :113*
	 */
	abstract fun locationForSubmenu(aSubmenu: NSMenu?): NSObject?

	/**
	 * Original signature : `void performActionForItemAtIndex(NSInteger)`<br></br>
	 * *native declaration : :115*
	 */
	abstract fun performActionForItemAtIndex(index: NSInteger?)

	/**
	 * Original signature : `void setDelegate(id)`<br></br>
	 * *native declaration : :118*
	 */
	abstract fun setDelegate(anObject: ID?)

	/**
	 * Original signature : `id delegate()`<br></br>
	 * *native declaration : :119*
	 */
	abstract fun delegate(): ID?

	/**
	 * Original signature : `CGFloat menuBarHeight()`<br></br>
	 * *native declaration : :123*
	 */
	abstract fun menuBarHeight(): Float

	/**
	 * Dismisses the menu and ends all menu tracking<br></br>
	 * Original signature : `void cancelTracking()`<br></br>
	 * *native declaration : :128*
	 */
	abstract fun cancelTracking()

	/**
	 * Returns the highlighted item in the menu, or nil if no item in the menu is highlighted<br></br>
	 * Original signature : `NSMenuItem* highlightedItem()`<br></br>
	 * *native declaration : :131*
	 */
	abstract fun highlightedItem(): NSMenuItem?

	/**
	 * Original signature : `void setShowsStateColumn(BOOL)`<br></br>
	 * *native declaration : :133*
	 */
	abstract fun setShowsStateColumn(showsState: Boolean)

	/**
	 * Original signature : `BOOL showsStateColumn()`<br></br>
	 * *native declaration : :134*
	 */
	abstract fun showsStateColumn(): Boolean

	/**
	 * Original signature : `void submenuAction(id)`<br></br>
	 * *from NSSubmenuAction native declaration : :140*
	 */
	abstract fun submenuAction(sender: ID?)

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSMenu", _Class::class.java)

		fun menu(): NSMenu? {
			return CLASS.alloc().init()
		}

		fun menuWithTitle(title: String?): NSMenu? {
			return CLASS.alloc().initWithTitle(title)
		}
	}
}
