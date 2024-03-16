package darwin 

import org.rococoa.cocoa.foundation.NSUInteger

interface NSDraggingDestination {
	/**
	 * Original signature : `NSDragOperation draggingEntered(id<NSDraggingInfo>)</NSDraggingInfo>`<br></br>
	 * *native declaration : line 47*
	 */
	open fun draggingEntered(sender: org.rococoa.ID?): NSUInteger?

	/**
	 * Original signature : `NSDragOperation draggingUpdated(org.rococoa.ID)`<br></br>
	 * if the destination responded to draggingEntered: but not to draggingUpdated: the return value from draggingEntered: is used<br></br>
	 * *native declaration : line 48*
	 */
	open fun draggingUpdated(sender: org.rococoa.ID?): NSUInteger?

	/**
	 * Original signature : `void draggingExited(org.rococoa.ID)`<br></br>
	 * *native declaration : line 49*
	 */
	open fun draggingExited(sender: org.rococoa.ID?)

	/**
	 * Original signature : `BOOL prepareForDragOperation(org.rococoa.ID)`<br></br>
	 * *native declaration : line 50*
	 */
	open fun prepareForDragOperation(sender: org.rococoa.ID?): Boolean

	/**
	 * Original signature : `BOOL performDragOperation(org.rococoa.ID)`<br></br>
	 * *native declaration : line 51*
	 */
	open fun performDragOperation(sender: org.rococoa.ID?): Boolean

	/**
	 * Original signature : `void concludeDragOperation(org.rococoa.ID)`<br></br>
	 * *native declaration : line 52*
	 */
	open fun concludeDragOperation(sender: org.rococoa.ID?)

	/**
	 * draggingEnded: is implemented as of Mac OS 10.5<br></br>
	 * Original signature : `void draggingEnded(org.rococoa.ID)`<br></br>
	 * *native declaration : line 54*
	 */
	open fun draggingEnded(sender: org.rococoa.ID?)

	/**
	 * the receiver of -wantsPeriodicDraggingUpdates should return NO if it does not require periodic -draggingUpdated messages (eg. not autoscrolling or otherwise dependent on draggingUpdated: sent while mouse is stationary)<br></br>
	 * Original signature : `BOOL wantsPeriodicDraggingUpdates()`<br></br>
	 * *native declaration : line 57*
	 */
	open fun wantsPeriodicDraggingUpdates(): Boolean
}
