package darwin 

import org.rococoa.cocoa.foundation.NSPoint
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : :32</i>
abstract class NSDraggingInfo : NSObject() {
	/**
	 * Original signature : `NSWindow* draggingDestinationWindow()`<br></br>
	 * *native declaration : :33*
	 */
	abstract fun draggingDestinationWindow(): NSWindow?

	/**
	 * Original signature : `NSDragOperation draggingSourceOperationMask()`<br></br>
	 * *native declaration : :34*
	 */
	abstract fun draggingSourceOperationMask(): NSUInteger?

	/**
	 * Original signature : `draggingLocation()`<br></br>
	 * *native declaration : :35*
	 */
	abstract fun draggingLocation(): NSPoint?

	/**
	 * Original signature : `draggedImageLocation()`<br></br>
	 * *native declaration : :36*
	 */
	abstract fun draggedImageLocation(): NSPoint?

	/**
	 * Original signature : `NSImage* draggedImage()`<br></br>
	 * *native declaration : :37*
	 */
	abstract fun draggedImage(): NSImage?

	/**
	 * Original signature : `NSPasteboard* draggingPasteboard()`<br></br>
	 * *native declaration : :38*
	 */
	abstract fun draggingPasteboard(): NSPasteboard?

	/**
	 * Original signature : `draggingSource()`<br></br>
	 * *native declaration : :39*
	 */
	abstract fun draggingSource(): NSObject?

	/**
	 * Original signature : `NSInteger draggingSequenceNumber()`<br></br>
	 * *native declaration : :40*
	 */
	abstract fun draggingSequenceNumber(): Int
	/**
	 * *native declaration : :41*<br></br>
	 * Conversion Error : /// Original signature : `void slideDraggedImageTo(null)`<br></br>
	 * - (void)slideDraggedImageTo:(null)screenPoint; (Argument screenPoint cannot be converted)
	 */
	/**
	 * Original signature : `NSArray* namesOfPromisedFilesDroppedAtDestination(NSURL*)`<br></br>
	 * *native declaration : :43*
	 */
	abstract fun namesOfPromisedFilesDroppedAtDestination(dropDestination: NSURL?): NSArray?

	companion object {
		/// <i>native declaration : line 15</i>
		val NSDragOperationNone: NSUInteger? = NSUInteger(0)

		/// <i>native declaration : line 16</i>
		val NSDragOperationCopy: NSUInteger? = NSUInteger(1)

		/// <i>native declaration : line 17</i>
		val NSDragOperationLink: NSUInteger? = NSUInteger(2)

		/// <i>native declaration : line 18</i>
		val NSDragOperationGeneric: NSUInteger? = NSUInteger(4)

		/// <i>native declaration : line 19</i>
		val NSDragOperationPrivate: NSUInteger? = NSUInteger(8)

		/// <i>native declaration : line 21</i>
		val NSDragOperationMove: NSUInteger? = NSUInteger(16)

		/// <i>native declaration : line 22</i>
		val NSDragOperationDelete: NSUInteger? = NSUInteger(32)
	}
}
