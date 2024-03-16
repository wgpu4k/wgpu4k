package darwin 

import org.rococoa.cocoa.foundation.NSInteger

abstract class NSTextStorage : NSMutableAttributedString() {
	/**
	 * These methods manage the list of layout managers.<br></br>
	 * Original signature : `void addLayoutManager(NSLayoutManager*)`<br></br>
	 * Retains & calls setTextStorage: on the item<br></br>
	 * *native declaration : :45*
	 */
	abstract fun addLayoutManager(obj: NSLayoutManager?)

	/**
	 * Original signature : `void removeLayoutManager(NSLayoutManager*)`<br></br>
	 * *native declaration : :46*
	 */
	abstract fun removeLayoutManager(obj: NSLayoutManager?)

	/**
	 * Original signature : `NSArray* layoutManagers()`<br></br>
	 * *native declaration : :47*
	 */
	abstract fun layoutManagers(): NSArray?
	/**
	 * *native declaration : :51*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * This is called from edited:range:changeInLength: or endEditing. This method sends out NSTextStorageWillProcessEditing, then fixes the attributes, then sends out NSTextStorageDidProcessEditing, and finally notifies the layout managers of change with the textStorage:edited:range:changeInLength:invalidatedRange: method.<br></br>
	 * Original signature : `void processEditing()`<br></br>
	 * *native declaration : :55*
	 */
	abstract fun processEditing()
	/**
	 * *native declaration : :58*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * *native declaration : :61*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * Original signature : `BOOL fixesAttributesLazily()`<br></br>
	 * *native declaration : :65*
	 */
	abstract fun fixesAttributesLazily(): Boolean

	/**
	 * These methods return information about the editing status. Especially useful when there are outstanding beginEditing calls or during processEditing... editedRange.location will be NSNotFound if nothing has been edited.<br></br>
	 * Original signature : `NSUInteger editedMask()`<br></br>
	 * *native declaration : :69*
	 */
	abstract fun editedMask(): Int
	/**
	 * *native declaration : :70*<br></br>
	 * Conversion Error : NSRange
	 */
	/**
	 * Original signature : `NSInteger changeInLength()`<br></br>
	 * *native declaration : :71*
	 */
	abstract fun changeInLength(): NSInteger?

	/**
	 * Set/get the delegate<br></br>
	 * Original signature : `void setDelegate(id)`<br></br>
	 * *native declaration : :75*
	 */
	abstract fun setDelegate(delegate: org.rococoa.ID?)

	/**
	 * Original signature : `id delegate()`<br></br>
	 * *native declaration : :76*
	 */
	abstract fun delegate(): org.rococoa.ID? /// <i>native declaration : :30</i>
}

