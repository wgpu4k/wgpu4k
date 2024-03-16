package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSSize

abstract class NSDockTile : NSObject() {
	interface _Class : ObjCClass {
		/// <i>native declaration : NSDockTile.h</i>
		open fun alloc(): NSDockTile?

		/// <i>native declaration : NSDockTile.h</i>
		open fun create(): NSDockTile?
	}

	/**
	 * get the size of the dock tile, in screen coordinates<br></br> Original signature : `-(NSSize)size`<br></br>
	 * *native declaration : NSDockTile.h:37*
	 */
	abstract fun size(): NSSize?

	/**
	 * set the content view to view.  view should be height and width resizable.  In order to initiate drawing in view,
	 * you must call -[NSDockTile display].<br></br> Original signature : `-(void)setContentView:(NSView*)`<br></br>
	 * *native declaration : NSDockTile.h:41*
	 */
	abstract fun setContentView(view: NSView?)

	/**
	 * Original signature : `-(NSView*)contentView`<br></br>
	 * *native declaration : NSDockTile.h:42*
	 */
	abstract fun contentView(): NSView?

	/**
	 * cause the dock tile to be redrawn.  The contentView and any subviews will be sent drawRect: messages.<br></br>
	 * Original signature : `-(void)display`<br></br>
	 * *native declaration : NSDockTile.h:46*
	 */
	abstract fun display()

	/**
	 * setShowsApplicationBadge: sets whether or not the dock tile should be badged with the application icon.  Default
	 * is YES for NSWindow dock tiles, NO for the NSApplication dock tile.<br></br> Original signature :
	 * `-(void)setShowsApplicationBadge:(BOOL)`<br></br>
	 * *native declaration : NSDockTile.h:50*
	 */
	abstract fun setShowsApplicationBadge(flag: Boolean)

	/**
	 * Original signature : `-(BOOL)showsApplicationBadge`<br></br>
	 * *native declaration : NSDockTile.h:51*
	 */
	abstract fun showsApplicationBadge(): Boolean

	/**
	 * Assign an image to this property when you want to temporarily change the app icon in the dock app tile. The image
	 * you provide is scaled as needed so that it fits in the tile. To restore your app’s original icon, set this
	 * property to nil.
	 *
	 * @param image The image used for the app’s icon.
	 */
	abstract fun setApplicationIconImage(image: NSImage?)

	/**
	 * Badge the dock icon with a localized string.  The badge appearance is system defined.  This is often used to show
	 * an unread count in the application dock icon.<br></br> Original signature : `-(void)setBadgeLabel:(NSString*)`<br></br>
	 * *native declaration : NSDockTile.h:55*
	 */
	abstract fun setBadgeLabel(string: String?)

	/**
	 * Original signature : `-(NSString*)badgeLabel`<br></br>
	 * *native declaration : NSDockTile.h:56*
	 */
	abstract fun badgeLabel(): String?

	/**
	 * -owner will return NSApp for the application dock tile, or the NSWindow for a mini window dock tile.<br></br> Original
	 * signature : `-(id)owner`<br></br>
	 * *native declaration : NSDockTile.h:60*
	 */
	abstract fun owner(): NSObject?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSDockTile", _Class::class.java)

		/// <i>native declaration : NSDockTile.h</i>
		fun alloc(): NSDockTile? {
			return CLASS.alloc()
		}

		/// <i>native declaration : NSDockTile.h</i>
		fun create(): NSDockTile? {
			return CLASS.create()
		}
	}
}
