package darwin 

import org.rococoa.ID
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : :23</i>
abstract class NSTabView : NSView() {
	interface Delegate {
		open fun tabView_didSelectTabViewItem(tabView: NSTabView?, tabViewItem: NSTabViewItem?)
	}

	/**
	 * Original signature : `void selectTabViewItem(NSTabViewItem*)`<br></br>
	 * *native declaration : :74*
	 */
	abstract fun selectTabViewItem(tabViewItem: NSTabViewItem?)

	/**
	 * Original signature : `void selectTabViewItemAtIndex(NSInteger)`<br></br>
	 * May raise an NSRangeException<br></br>
	 * *native declaration : :75*
	 */
	abstract fun selectTabViewItemAtIndex(index: Int)

	/**
	 * Original signature : `void selectTabViewItemWithIdentifier(id)`<br></br>
	 * May raise an NSRangeException if identifier not found<br></br>
	 * *native declaration : :76*
	 */
	abstract fun selectTabViewItemWithIdentifier(identifier: String?)

	/**
	 * Original signature : `void takeSelectedTabViewItemFromSender(id)`<br></br>
	 * May raise an NSRangeException<br></br>
	 * *native declaration : :77*
	 */
	abstract fun takeSelectedTabViewItemFromSender(sender: ID?)

	/**
	 * Original signature : `void selectFirstTabViewItem(id)`<br></br>
	 * *native declaration : :81*
	 */
	abstract fun selectFirstTabViewItem(sender: NSTabViewItem?)

	/**
	 * Original signature : `void selectLastTabViewItem(id)`<br></br>
	 * *native declaration : :82*
	 */
	abstract fun selectLastTabViewItem(sender: NSTabViewItem?)

	/**
	 * Original signature : `void selectNextTabViewItem(id)`<br></br>
	 * *native declaration : :83*
	 */
	abstract fun selectNextTabViewItem(sender: NSTabViewItem?)

	/**
	 * Original signature : `void selectPreviousTabViewItem(id)`<br></br>
	 * *native declaration : :84*
	 */
	abstract fun selectPreviousTabViewItem(sender: NSTabViewItem?)

	/**
	 * Original signature : `NSTabViewItem* selectedTabViewItem()`<br></br>
	 * return nil if none are selected<br></br>
	 * *native declaration : :88*
	 */
	abstract fun selectedTabViewItem(): NSTabViewItem?

	/**
	 * Original signature : `NSFont* font()`<br></br>
	 * returns font used for all tab labels.<br></br>
	 * *native declaration : :89*
	 */
	abstract fun font(): NSFont?

	/**
	 * Original signature : `NSTabViewType tabViewType()`<br></br>
	 * *native declaration : :90*
	 */
	abstract fun tabViewType(): NSUInteger?

	/**
	 * Original signature : `NSArray* tabViewItems()`<br></br>
	 * *native declaration : :91*
	 */
	abstract fun tabViewItems(): NSArray?

	/**
	 * Original signature : `BOOL allowsTruncatedLabels()`<br></br>
	 * *native declaration : :92*
	 */
	abstract fun allowsTruncatedLabels(): Boolean

	/**
	 * Original signature : `minimumSize()`<br></br>
	 * returns the minimum size of the tab view<br></br>
	 * *native declaration : :93*
	 */
	abstract fun minimumSize(): NSObject?

	/**
	 * Original signature : `BOOL drawsBackground()`<br></br>
	 * only relevant for borderless tab view type<br></br>
	 * *native declaration : :94*
	 */
	abstract fun drawsBackground(): Boolean

	/**
	 * Original signature : `controlTint()`<br></br>
	 * *native declaration : :95*
	 */
	abstract fun controlTint(): NSUInteger?

	/**
	 * Original signature : `controlSize()`<br></br>
	 * *native declaration : :96*
	 */
	abstract fun controlSize(): NSUInteger?

	/**
	 * Original signature : `void setFont(NSFont*)`<br></br>
	 * *native declaration : :100*
	 */
	abstract fun setFont(font: NSFont?)

	/**
	 * Original signature : `void setTabViewType(NSTabViewType)`<br></br>
	 * *native declaration : :101*
	 */
	abstract fun setTabViewType(tabViewType: NSUInteger?)

	/**
	 * Original signature : `void setAllowsTruncatedLabels(BOOL)`<br></br>
	 * *native declaration : :102*
	 */
	abstract fun setAllowsTruncatedLabels(allowTruncatedLabels: Boolean)

	/**
	 * Original signature : `void setDrawsBackground(BOOL)`<br></br>
	 * only relevant for borderless tab view type<br></br>
	 * *native declaration : :103*
	 */
	abstract fun setDrawsBackground(flag: Boolean)
	/**
	 * *native declaration : :104*<br></br>
	 * Conversion Error : /// Original signature : `void setControlTint(null)`<br></br>
	 * - (void)setControlTint:(null)controlTint; (Argument controlTint cannot be converted)
	 */
	/**
	 * *native declaration : :105*<br></br>
	 * Conversion Error : /// Original signature : `void setControlSize(null)`<br></br>
	 * - (void)setControlSize:(null)controlSize; (Argument controlSize cannot be converted)
	 */
	/**
	 * Original signature : `void addTabViewItem(NSTabViewItem*)`<br></br>
	 * Add tab at the end.<br></br>
	 * *native declaration : :109*
	 */
	abstract fun addTabViewItem(tabViewItem: NSTabViewItem?)

	/**
	 * Original signature : `void insertTabViewItem(NSTabViewItem*, NSInteger)`<br></br>
	 * May raise an NSRangeException<br></br>
	 * *native declaration : :110*
	 */
	abstract fun insertTabViewItem_atIndex(tabViewItem: NSTabViewItem?, index: Int)

	/**
	 * Original signature : `void removeTabViewItem(NSTabViewItem*)`<br></br>
	 * tabViewItem must be an existing tabViewItem<br></br>
	 * *native declaration : :111*
	 */
	abstract fun removeTabViewItem(tabViewItem: NSTabViewItem?)

	/**
	 * Original signature : `void setDelegate(id)`<br></br>
	 * *native declaration : :115*
	 */
	abstract fun setDelegate(anObject: ID?)

	/**
	 * Original signature : `id delegate()`<br></br>
	 * *native declaration : :116*
	 */
	abstract fun delegate(): ID?
	/**
	 * *native declaration : :120*<br></br>
	 * Conversion Error : /// Original signature : `NSTabViewItem* tabViewItemAtPoint(null)`<br></br>
	 * - (NSTabViewItem*)tabViewItemAtPoint:(null)point; // point in local coordinates. returns nil if none.<br></br>
	 * (Argument point cannot be converted)
	 */
	/**
	 * Original signature : `contentRect()`<br></br>
	 * Return the rect available for a "page".<br></br>
	 * *native declaration : :124*
	 */
	abstract fun contentRect(): NSObject?

	/**
	 * Original signature : `NSInteger numberOfTabViewItems()`<br></br>
	 * *native declaration : :128*
	 */
	abstract fun numberOfTabViewItems(): Int

	/**
	 * Original signature : `NSInteger indexOfTabViewItem(NSTabViewItem*)`<br></br>
	 * NSNotFound if not found<br></br>
	 * *native declaration : :129*
	 */
	abstract fun indexOfTabViewItem(tabViewItem: NSTabViewItem?): Int

	/**
	 * Original signature : `NSTabViewItem* tabViewItemAtIndex(NSInteger)`<br></br>
	 * May raise an NSRangeException<br></br>
	 * *native declaration : :130*
	 */
	abstract fun tabViewItemAtIndex(index: Int): NSTabViewItem?

	/**
	 * Original signature : `NSInteger indexOfTabViewItemWithIdentifier(id)`<br></br>
	 * NSNotFound if not found<br></br>
	 * *native declaration : :131*
	 */
	abstract fun indexOfTabViewItemWithIdentifier(identifier: String?): Int
}
