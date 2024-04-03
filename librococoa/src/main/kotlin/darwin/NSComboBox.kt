package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.CGFloat
import org.rococoa.cocoa.foundation.NSInteger
import org.rococoa.cocoa.foundation.NSRect

/// <i>native declaration : :16</i>
abstract class NSComboBox : NSTextField() {
	interface DataSource {
		open fun numberOfItemsInComboBox(combo: NSComboBox?): NSInteger?

		open fun comboBox_objectValueForItemAtIndex(sender: NSComboBox?, row: NSInteger?): NSObject?
	}

	interface _Class : ObjCClass {
		open fun alloc(): NSComboBox
	}

	@Override
	abstract override fun initWithFrame(frameRect: NSRect?): NSComboBox

	/**
	 * Original signature : `BOOL hasVerticalScroller()`<br></br>
	 * *native declaration : :21*
	 */
	abstract fun hasVerticalScroller(): Boolean

	/**
	 * Original signature : `void setHasVerticalScroller(BOOL)`<br></br>
	 * *native declaration : :22*
	 */
	abstract fun setHasVerticalScroller(flag: Boolean)

	/**
	 * Original signature : `intercellSpacing()`<br></br>
	 * *native declaration : :23*
	 */
	abstract fun intercellSpacing(): NSObject?
	/**
	 * *native declaration : :24*<br></br>
	 * Conversion Error : /// Original signature : `void setIntercellSpacing(null)`<br></br>
	 * - (void)setIntercellSpacing:(null)aSize; (Argument aSize cannot be converted)
	 */
	/**
	 * Original signature : `CGFloat itemHeight()`<br></br>
	 * *native declaration : :25*
	 */
	abstract fun itemHeight(): CGFloat?

	/**
	 * Original signature : `void setItemHeight(CGFloat)`<br></br>
	 * *native declaration : :26*
	 */
	abstract fun setItemHeight(itemHeight: CGFloat?)

	/**
	 * Original signature : `NSInteger numberOfVisibleItems()`<br></br>
	 * *native declaration : :27*
	 */
	abstract fun numberOfVisibleItems(): NSInteger?

	/**
	 * Original signature : `void setNumberOfVisibleItems(NSInteger)`<br></br>
	 * *native declaration : :28*
	 */
	abstract fun setNumberOfVisibleItems(visibleItems: NSInteger?)

	/**
	 * Original signature : `void setButtonBordered(BOOL)`<br></br>
	 * *native declaration : :31*
	 */
	abstract fun setButtonBordered(flag: Boolean)

	/**
	 * Original signature : `BOOL isButtonBordered()`<br></br>
	 * *native declaration : :32*
	 */
	abstract fun isButtonBordered(): Boolean

	/**
	 * Original signature : `void reloadData()`<br></br>
	 * *native declaration : :35*
	 */
	abstract fun reloadData()

	/**
	 * Original signature : `void noteNumberOfItemsChanged()`<br></br>
	 * *native declaration : :36*
	 */
	abstract fun noteNumberOfItemsChanged()

	/**
	 * Original signature : `void setUsesDataSource(BOOL)`<br></br>
	 * *native declaration : :38*
	 */
	abstract fun setUsesDataSource(flag: Boolean)

	/**
	 * Original signature : `BOOL usesDataSource()`<br></br>
	 * *native declaration : :39*
	 */
	abstract fun usesDataSource(): Boolean

	/**
	 * Original signature : `void scrollItemAtIndexToTop(NSInteger)`<br></br>
	 * *native declaration : :41*
	 */
	abstract fun scrollItemAtIndexToTop(index: NSInteger?)

	/**
	 * Original signature : `void scrollItemAtIndexToVisible(NSInteger)`<br></br>
	 * *native declaration : :42*
	 */
	abstract fun scrollItemAtIndexToVisible(index: NSInteger?)

	/**
	 * Original signature : `void selectItemAtIndex(NSInteger)`<br></br>
	 * *native declaration : :44*
	 */
	abstract fun selectItemAtIndex(index: NSInteger?)

	/**
	 * Original signature : `void deselectItemAtIndex(NSInteger)`<br></br>
	 * *native declaration : :45*
	 */
	abstract fun deselectItemAtIndex(index: NSInteger?)

	/**
	 * Original signature : `NSInteger indexOfSelectedItem()`<br></br>
	 * *native declaration : :46*
	 */
	abstract fun indexOfSelectedItem(): NSInteger?

	/**
	 * Original signature : `NSInteger numberOfItems()`<br></br>
	 * *native declaration : :47*
	 */
	abstract fun numberOfItems(): NSInteger?

	/**
	 * Original signature : `BOOL completes()`<br></br>
	 * *native declaration : :49*
	 */
	abstract fun completes(): Boolean

	/**
	 * Original signature : `void setCompletes(BOOL)`<br></br>
	 * *native declaration : :50*
	 */
	abstract fun setCompletes(completes: Boolean)

	/**
	 * These two methods can only be used when usesDataSource is YES<br></br>
	 * Original signature : `id dataSource()`<br></br>
	 * *native declaration : :53*
	 */
	abstract fun dataSource(): org.rococoa.ID?

	/**
	 * Original signature : `void setDataSource(id)`<br></br>
	 * *native declaration : :54*
	 */
	abstract fun setDataSource(aSource: org.rococoa.ID?)

	/**
	 * These methods can only be used when usesDataSource is NO<br></br>
	 * Original signature : `void addItemWithObjectValue(id)`<br></br>
	 * *native declaration : :57*
	 */
	abstract fun addItemWithObjectValue(`object`: NSObject?)

	/**
	 * Original signature : `void addItemsWithObjectValues(NSArray*)`<br></br>
	 * *native declaration : :58*
	 */
	abstract fun addItemsWithObjectValues(objects: NSArray?)

	/**
	 * Original signature : `void insertItemWithObjectValue(id, NSInteger)`<br></br>
	 * *native declaration : :59*
	 */
	abstract fun insertItemWithObjectValue_atIndex(`object`: NSObject?, index: NSInteger?)

	/**
	 * Original signature : `void removeItemWithObjectValue(id)`<br></br>
	 * *native declaration : :60*
	 */
	abstract fun removeItemWithObjectValue(`object`: NSObject?)

	/**
	 * Original signature : `void removeItemAtIndex(NSInteger)`<br></br>
	 * *native declaration : :61*
	 */
	abstract fun removeItemAtIndex(index: NSInteger?)

	/**
	 * Original signature : `void removeAllItems()`<br></br>
	 * *native declaration : :62*
	 */
	abstract fun removeAllItems()

	/**
	 * Original signature : `void selectItemWithObjectValue(id)`<br></br>
	 * *native declaration : :63*
	 */
	abstract fun selectItemWithObjectValue(`object`: NSObject?)

	/**
	 * Original signature : `id itemObjectValueAtIndex(NSInteger)`<br></br>
	 * *native declaration : :64*
	 */
	abstract fun itemObjectValueAtIndex(index: NSInteger?): NSObject?

	/**
	 * Original signature : `id objectValueOfSelectedItem()`<br></br>
	 * *native declaration : :65*
	 */
	abstract fun objectValueOfSelectedItem(): NSObject?

	/**
	 * Original signature : `NSInteger indexOfItemWithObjectValue(id)`<br></br>
	 * *native declaration : :66*
	 */
	abstract fun indexOfItemWithObjectValue(`object`: NSObject?): NSInteger?

	/**
	 * Original signature : `NSArray* objectValues()`<br></br>
	 * *native declaration : :67*
	 */
	abstract fun objectValues(): NSArray?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSComboBox", _Class::class.java)

		fun textfieldWithFrame(frameRect: NSRect?): NSComboBox? {
			return CLASS.alloc().initWithFrame(frameRect)
		}

		val ComboBoxWillPopUpNotification: String? = "NSComboBoxWillPopUpNotification"
		val ComboBoxWillDismissNotification: String? = "NSComboBoxWillDismissNotification"
		val ComboBoxSelectionDidChangeNotification: String? = "NSComboBoxSelectionDidChangeNotification"
		val ComboBoxSelectionIsChangingNotification: String? = "NSComboBoxSelectionIsChangingNotification"
	}
}
