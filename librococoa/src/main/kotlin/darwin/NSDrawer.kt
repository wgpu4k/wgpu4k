package darwin 

import org.rococoa.ID
import org.rococoa.ObjCClass
import org.rococoa.cocoa.CGFloat
import org.rococoa.cocoa.foundation.NSSize

/// <i>native declaration : :31</i>
abstract class NSDrawer : NSObject() {
	interface _Class : ObjCClass {
		open fun alloc(): NSDrawer?
	}
	/**
	 * *native declaration : :57*<br></br>
	 * Conversion Error : /// Original signature : `id initWithContentSize(null, NSRectEdge)`<br></br>
	 * - (id)initWithContentSize:(null)contentSize preferredEdge:(NSRectEdge)edge; (Argument contentSize cannot be converted)
	 */
	/**
	 * Original signature : `void setParentWindow(NSWindow*)`<br></br>
	 * *native declaration : :59*
	 */
	abstract fun setParentWindow(parent: NSWindow?)

	/**
	 * Original signature : `NSWindow* parentWindow()`<br></br>
	 * *native declaration : :60*
	 */
	abstract fun parentWindow(): NSWindow?

	/**
	 * Original signature : `void setContentView(NSView*)`<br></br>
	 * *native declaration : :61*
	 */
	abstract fun setContentView(aView: NSView?)

	/**
	 * Original signature : `NSView* contentView()`<br></br>
	 * *native declaration : :62*
	 */
	abstract fun contentView(): NSView?
	/**
	 * *native declaration : :63*<br></br>
	 * Conversion Error : NSRectEdge
	 */
	/**
	 * *native declaration : :64*<br></br>
	 * Conversion Error : NSRectEdge
	 */
	/**
	 * Original signature : `void setDelegate(id)`<br></br>
	 * *native declaration : :65*
	 */
	abstract fun setDelegate(anObject: ID?)

	/**
	 * Original signature : `id delegate()`<br></br>
	 * *native declaration : :66*
	 */
	abstract fun delegate(): ID?

	/**
	 * Original signature : `void open()`<br></br>
	 * *native declaration : :68*
	 */
	abstract fun open()
	/**
	 * *native declaration : :69*<br></br>
	 * Conversion Error : NSRectEdge
	 */
	/**
	 * Original signature : `void close()`<br></br>
	 * *native declaration : :70*
	 */
	abstract fun close()

	/**
	 * Original signature : `void open(id)`<br></br>
	 * *native declaration : :72*
	 */
	abstract fun open(sender: ID?)

	/**
	 * Original signature : `void close(id)`<br></br>
	 * *native declaration : :73*
	 */
	abstract fun close(sender: ID?)

	/**
	 * Original signature : `void toggle(id)`<br></br>
	 * *native declaration : :74*
	 */
	abstract fun toggle(sender: ID?)

	/**
	 * Original signature : `NSInteger state()`<br></br>
	 * *native declaration : :76*
	 */
	abstract fun state(): Int
	/**
	 * *native declaration : :77*<br></br>
	 * Conversion Error : NSRectEdge
	 */
	/**
	 * *native declaration : :79*<br></br>
	 * Conversion Error : /// Original signature : `void setContentSize(null)`<br></br>
	 * - (void)setContentSize:(null)size; (Argument size cannot be converted)
	 */
	abstract fun setContentSize(size: NSSize?)

	/**
	 * Original signature : `contentSize()`<br></br>
	 * *native declaration : :80*
	 */
	abstract fun contentSize(): NSSize?
	/**
	 * *native declaration : :81*<br></br>
	 * Conversion Error : /// Original signature : `void setMinContentSize(null)`<br></br>
	 * - (void)setMinContentSize:(null)size; (Argument size cannot be converted)
	 */
	/**
	 * Original signature : `minContentSize()`<br></br>
	 * *native declaration : :82*
	 */
	abstract fun minContentSize(): NSSize?
	/**
	 * *native declaration : :83*<br></br>
	 * Conversion Error : /// Original signature : `void setMaxContentSize(null)`<br></br>
	 * - (void)setMaxContentSize:(null)size; (Argument size cannot be converted)
	 */
	/**
	 * Original signature : `maxContentSize()`<br></br>
	 * *native declaration : :84*
	 */
	abstract fun maxContentSize(): NSSize?

	/**
	 * Original signature : `void setLeadingOffset(CGFloat)`<br></br>
	 * *native declaration : :86*
	 */
	abstract fun setLeadingOffset(offset: CGFloat?)

	/**
	 * Original signature : `CGFloat leadingOffset()`<br></br>
	 * *native declaration : :87*
	 */
	abstract fun leadingOffset(): CGFloat?

	/**
	 * Original signature : `void setTrailingOffset(CGFloat)`<br></br>
	 * *native declaration : :88*
	 */
	abstract fun setTrailingOffset(offset: CGFloat?)

	/**
	 * Original signature : `CGFloat trailingOffset()`<br></br>
	 * *native declaration : :89*
	 */
	abstract fun trailingOffset(): CGFloat?

	companion object {
		const val NSDrawerClosedState: Int = 0
		const val NSDrawerOpeningState: Int = 1
		const val NSDrawerOpenState: Int = 2
		const val NSDrawerClosingState: Int = 3
		const val ClosedState: Int = 0
		const val OpeningState: Int = 1
		const val OpenState: Int = 2
		const val ClosingState: Int = 3
		val DrawerWillOpenNotification: String? = "NSDrawerWillOpenNotification"
		val DrawerDidOpenNotification: String? = "NSDrawerDidOpenNotification"
		val DrawerWillCloseNotification: String? = "NSDrawerWillCloseNotification"
		val DrawerDidCloseNotification: String? = "NSDrawerDidCloseNotification"
	}
}
