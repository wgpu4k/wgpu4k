package darwin 

import org.rococoa.ObjCClass

/// <i>native declaration : :18</i>
abstract class NSTabViewItem : NSObject() {
	interface _Class : ObjCClass {
		open fun alloc(): NSTabViewItem
	}

	/**
	 * Original signature : `id initWithIdentifier(id)`<br></br>
	 * *native declaration : :52*
	 */
	abstract fun initWithIdentifier(identifier: String?): NSTabViewItem?

	/**
	 * Original signature : `id identifier()`<br></br>
	 * *native declaration : :56*
	 */
	abstract fun identifier(): String?

	/**
	 * Original signature : `id view()`<br></br>
	 * *native declaration : :57*
	 */
	abstract fun view(): NSView?

	/**
	 * Original signature : `id initialFirstResponder()`<br></br>
	 * *native declaration : :58*
	 */
	abstract fun initialFirstResponder(): NSView?

	/**
	 * Original signature : `NSString* label()`<br></br>
	 * *native declaration : :59*
	 */
	abstract fun label(): String?

	/**
	 * Original signature : `NSColor* color()`<br></br>
	 * *native declaration : :60*
	 */
	abstract fun color(): NSColor?

	/**
	 * Original signature : `NSTabState tabState()`<br></br>
	 * *native declaration : :61*
	 */
	abstract fun tabState(): Int

	/**
	 * Original signature : `NSTabView* tabView()`<br></br>
	 * *native declaration : :62*
	 */
	abstract fun tabView(): NSTabView?

	/**
	 * Original signature : `void setIdentifier(id)`<br></br>
	 * *native declaration : :66*
	 */
	abstract fun setIdentifier(identifier: String?)

	/**
	 * Original signature : `void setLabel(NSString*)`<br></br>
	 * *native declaration : :67*
	 */
	abstract fun setLabel(label: String?)

	/**
	 * Original signature : `void setColor(NSColor*)`<br></br>
	 * *native declaration : :68*
	 */
	abstract fun setColor(color: NSColor?)

	/**
	 * Original signature : `void setView(NSView*)`<br></br>
	 * *native declaration : :69*
	 */
	abstract fun setView(view: NSView?)

	/**
	 * Original signature : `void setInitialFirstResponder(NSView*)`<br></br>
	 * *native declaration : :70*
	 */
	abstract fun setInitialFirstResponder(view: NSView?)
	/**
	 * *native declaration : :76*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :80*<br></br>
	 * Conversion Error : NSSize
	 */
	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSTabViewItem", _Class::class.java)

		fun itemWithIdentifier(identifier: String?): NSTabViewItem? {
			return CLASS.alloc().initWithIdentifier(identifier)
		}
	}
}
