package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSInteger
import org.rococoa.cocoa.foundation.NSRect

/// <i>native declaration : :10</i>
abstract class NSPopUpButton : NSButton() {
	interface _Class : ObjCClass {
		open fun alloc(): NSPopUpButton
	}

	abstract fun initWithFrame_pullsDown(frameRect: NSRect?, flag: Boolean): NSPopUpButton?

	/**
	 * Behavior settings<br></br>
	 * Original signature : `void setPullsDown(BOOL)`<br></br>
	 * *native declaration : :29*
	 */
	abstract fun setPullsDown(flag: Boolean)

	/**
	 * Original signature : `BOOL pullsDown()`<br></br>
	 * *native declaration : :30*
	 */
	abstract fun pullsDown(): Boolean

	/**
	 * Original signature : `void setAutoenablesItems(BOOL)`<br></br>
	 * *native declaration : :32*
	 */
	abstract fun setAutoenablesItems(flag: Boolean)

	/**
	 * Original signature : `BOOL autoenablesItems()`<br></br>
	 * *native declaration : :33*
	 */
	abstract fun autoenablesItems(): Boolean

	/**
	 * Adding and removing items<br></br>
	 * Original signature : `void addItemWithTitle(NSString*)`<br></br>
	 * *native declaration : :40*
	 */
	abstract fun addItemWithTitle(title: String?)

	/**
	 * Original signature : `void addItemsWithTitles(NSArray*)`<br></br>
	 * *native declaration : :41*
	 */
	abstract fun addItemsWithTitles(itemTitles: NSArray?)

	/**
	 * Original signature : `void insertItemWithTitle(NSString*, NSInteger)`<br></br>
	 * *native declaration : :42*
	 */
	abstract fun insertItemWithTitle_atIndex(title: String?, index: NSInteger?)

	/**
	 * Original signature : `void removeItemWithTitle(NSString*)`<br></br>
	 * *native declaration : :44*
	 */
	abstract fun removeItemWithTitle(title: String?)

	/**
	 * Original signature : `void removeItemAtIndex(NSInteger)`<br></br>
	 * *native declaration : :45*
	 */
	abstract fun removeItemAtIndex(index: NSInteger?)

	/**
	 * Original signature : `void removeAllItems()`<br></br>
	 * *native declaration : :46*
	 */
	abstract fun removeAllItems()

	/**
	 * Accessing the items<br></br>
	 * Original signature : `NSArray* itemArray()`<br></br>
	 * *native declaration : :50*
	 */
	abstract fun itemArray(): NSArray?

	/**
	 * Original signature : `NSInteger numberOfItems()`<br></br>
	 * *native declaration : :51*
	 */
	abstract fun numberOfItems(): NSInteger?

	/**
	 * Original signature : `NSInteger indexOfItem(NSMenuItem*)`<br></br>
	 * *native declaration : :53*
	 */
	abstract fun indexOfItem(item: NSMenuItem?): NSInteger?

	/**
	 * Original signature : `NSInteger indexOfItemWithTitle(NSString*)`<br></br>
	 * *native declaration : :54*
	 */
	abstract fun indexOfItemWithTitle(title: String?): NSInteger?

	/**
	 * Original signature : `NSInteger indexOfItemWithTag(NSInteger)`<br></br>
	 * *native declaration : :55*
	 */
	abstract fun indexOfItemWithTag(tag: NSInteger?): NSInteger?

	/**
	 * Original signature : `NSInteger indexOfItemWithRepresentedObject(null)`<br></br>
	 * - (NSInteger)indexOfItemWithRepresentedObject:(null)obj; (Argument obj cannot be converted)
	 */
	abstract fun indexOfItemWithRepresentedObject(`object`: String?): NSInteger?
	/**
	 * *native declaration : :57*<br></br>
	 * Conversion Error : /// Original signature : `NSInteger indexOfItemWithTarget(null, null)`<br></br>
	 * - (NSInteger)indexOfItemWithTarget:(null)target andAction:(null)actionSelector; (Argument target cannot be converted)
	 */
	/**
	 * Original signature : `NSMenuItem* itemAtIndex(NSInteger)`<br></br>
	 * *native declaration : :59*
	 */
	abstract fun itemAtIndex(index: NSInteger?): NSMenuItem?

	/**
	 * Original signature : `NSMenuItem* itemWithTitle(NSString*)`<br></br>
	 * *native declaration : :60*
	 */
	abstract fun itemWithTitle(title: String?): NSMenuItem?

	/**
	 * Original signature : `NSMenuItem* lastItem()`<br></br>
	 * *native declaration : :61*
	 */
	abstract fun lastItem(): NSMenuItem?

	/**
	 * Dealing with selection<br></br>
	 * Original signature : `void selectItem(NSMenuItem*)`<br></br>
	 * *native declaration : :65*
	 */
	abstract fun selectItem(item: NSMenuItem?)

	/**
	 * Original signature : `void selectItemAtIndex(NSInteger)`<br></br>
	 * *native declaration : :66*
	 */
	abstract fun selectItemAtIndex(index: NSInteger?)

	/**
	 * Original signature : `void selectItemWithTitle(NSString*)`<br></br>
	 * *native declaration : :67*
	 */
	abstract fun selectItemWithTitle(title: String?)

	/**
	 * Original signature : `BOOL selectItemWithTag(NSInteger)`<br></br>
	 * *native declaration : :69*
	 */
	abstract fun selectItemWithTag(tag: NSInteger?): Boolean

	/**
	 * Original signature : `NSMenuItem* selectedItem()`<br></br>
	 * *native declaration : :73*
	 */
	abstract fun selectedItem(): NSMenuItem?

	/**
	 * Original signature : `NSInteger indexOfSelectedItem()`<br></br>
	 * *native declaration : :74*
	 */
	abstract fun indexOfSelectedItem(): NSInteger?

	/**
	 * Original signature : `void synchronizeTitleAndSelectedItem()`<br></br>
	 * *native declaration : :75*
	 */
	abstract fun synchronizeTitleAndSelectedItem()

	/**
	 * Title conveniences<br></br>
	 * Original signature : `NSString* itemTitleAtIndex(NSInteger)`<br></br>
	 * *native declaration : :78*
	 */
	abstract fun itemTitleAtIndex(index: NSInteger?): String?

	/**
	 * Original signature : `NSArray* itemTitles()`<br></br>
	 * *native declaration : :79*
	 */
	abstract fun itemTitles(): NSArray?

	/**
	 * Original signature : `NSString* titleOfSelectedItem()`<br></br>
	 * *native declaration : :80*
	 */
	abstract fun titleOfSelectedItem(): String?

	companion object {
		val PopUpButtonWillPopUpNotification: String? = "NSPopUpButtonWillPopUpNotification"

		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSPopUpButton", _Class::class.java)

		fun buttonWithFrame(frameRect: NSRect?): NSPopUpButton? {
			return CLASS.alloc().initWithFrame_pullsDown(frameRect, false)
		}
	}
}
