package darwin 

import org.rococoa.cocoa.CGFloat
import org.rococoa.cocoa.foundation.NSInteger
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : :73</i>
abstract class NSOutlineView : NSTableView() {
	interface DataSource {
		open fun outlineView_numberOfChildrenOfItem(view: NSOutlineView?, item: NSObject?): NSInteger?

		open fun outlineView_child_ofItem(outlineView: NSOutlineView?, index: NSInteger?, item: NSObject?): NSObject?

		open fun outlineView_setObjectValue_forTableColumn_byItem(
			outlineView: NSOutlineView?, value: NSObject?,
			tableColumn: NSTableColumn?, item: NSObject?
		)

		open fun outlineView_objectValueForTableColumn_byItem(
			outlineView: NSOutlineView?,
			tableColumn: NSTableColumn?,
			item: NSObject?
		): NSObject?

		open fun outlineView_isItemExpandable(view: NSOutlineView?, item: NSObject?): Boolean

		open fun outlineView_validateDrop_proposedItem_proposedChildIndex(
			outlineView: NSOutlineView?,
			info: NSDraggingInfo?,
			item: NSObject?,
			row: NSInteger?
		): NSUInteger?

		open fun outlineView_acceptDrop_item_childIndex(
			outlineView: NSOutlineView?,
			info: NSDraggingInfo?,
			item: NSObject?,
			row: NSInteger?
		): Boolean

		open fun outlineView_writeItems_toPasteboard(
			outlineView: NSOutlineView?,
			items: NSArray?,
			pboard: NSPasteboard?
		): Boolean

		open fun outlineView_namesOfPromisedFilesDroppedAtDestination_forDraggedItems(
			dropDestination: NSURL?,
			items: NSArray?
		): NSArray?
	}

	interface Delegate {
		open fun outlineView_willDisplayCell_forTableColumn_item(
			view: NSOutlineView?,
			cell: NSTextFieldCell?,
			tableColumn: NSTableColumn?,
			item: NSObject?
		)

		open fun outlineView_shouldExpandItem(view: NSOutlineView?, item: NSObject?): Boolean

		open fun outlineViewItemWillExpand(notification: NSNotification?)

		open fun outlineViewItemDidExpand(notification: NSNotification?)

		open fun outlineViewItemWillCollapse(notification: NSNotification?)

		open fun outlineViewItemDidCollapse(notification: NSNotification?)

		open fun outlineView_isGroupItem(view: NSOutlineView?, item: NSObject?): Boolean
	}

	/**
	 * The 'outlineTableColumn' is the column that displays data in a hierarchical fashion, indented one identationlevel
	 * per level, decorated with indentation marker (disclosure triangle) on rows that are expandable. On MacOS 10.5,
	 * this value is saved in encodeWithCoder: and restored in initWithCoder:;<br></br> Original signature : `void
	 * setOutlineTableColumn(NSTableColumn*)`<br></br>
	 * *native declaration : :103*
	 */
	abstract fun setOutlineTableColumn(outlineTableColumn: NSTableColumn?)

	/**
	 * Original signature : `NSTableColumn* outlineTableColumn()`<br></br>
	 * *native declaration : :104*
	 */
	abstract fun outlineTableColumn(): NSTableColumn?

	/**
	 * Returns YES if 'item' is expandable and can contain other items. May call out to the delegate, if required.<br></br>
	 * Original signature : `BOOL isExpandable(id)`<br></br>
	 * *native declaration : :108*
	 */
	abstract fun isExpandable(item: NSObject?): Boolean

	/**
	 * Expands 'item', if not already expanded, and all children if 'expandChildren' is YES. On 10.5 and higher, passing
	 * 'nil' for 'item' will expand  each item under the root.<br></br> Original signature : `void expandItem(id,
	 * BOOL)`<br></br>
	 * *native declaration : :112*
	 */
	abstract fun expandItem_expandChildren(item: NSObject?, expandChildren: Boolean)

	/**
	 * Calls expandItem:expandChildren with 'expandChildren == NO'<br></br> Original signature : `void
	 * expandItem(id)`<br></br>
	 * *native declaration : :116*
	 */
	abstract fun expandItem(item: NSObject?)

	/**
	 * Collapses 'item' and all children if 'collapseChildren' is YES. On 10.5 and higher, passing 'nil' for 'item' will
	 * collapse each item under the root.<br></br> Original signature : `void collapseItem(id, BOOL)`<br></br>
	 * *native declaration : :120*
	 */
	abstract fun collapseItem_collapseChildren(item: NSObject?, collapseChildren: Boolean)

	/**
	 * Calls collapseItem:collapseChildren with 'collapseChildren == NO'<br></br> Original signature : `void
	 * collapseItem(id)`<br></br>
	 * *native declaration : :124*
	 */
	abstract fun collapseItem(item: NSObject?)

	/**
	 * Reloads 'item' and all children if 'reloadChildren' is YES. On 10.5 and higher, passing 'nil' for 'item' will
	 * reload everything under the root item.<br></br> Original signature : `void reloadItem(id, BOOL)`<br></br>
	 * *native declaration : :128*
	 */
	abstract fun reloadItem_reloadChildren(item: NSObject?, reloadChildren: Boolean)

	/**
	 * Calls reloadItem:reloadChildren with 'reloadChildren == NO'<br></br> Original signature : `void
	 * reloadItem(id)`<br></br>
	 * *native declaration : :132*
	 */
	abstract fun reloadItem(item: NSObject?)

	/**
	 * Returns the parent for 'item', or nil, if the parent is the root.<br></br> Original signature : `id
	 * parentForItem(id)`<br></br>
	 * *native declaration : :138*
	 */
	abstract fun parentForItem(item: NSObject?): NSObject?

	/**
	 * Item/Row translation<br></br> Original signature : `id itemAtRow(NSInteger)`<br></br>
	 * *native declaration : :144*
	 */
	abstract fun itemAtRow(row: NSInteger?): NSObject?

	/**
	 * Original signature : `NSInteger rowForItem(id)`<br></br>
	 * *native declaration : :145*
	 */
	abstract fun rowForItem(item: NSObject?): NSInteger?

	/**
	 * Indentation<br></br> Original signature : `NSInteger levelForItem(id)`<br></br>
	 * *native declaration : :149*
	 */
	abstract fun levelForItem(item: NSObject?): NSInteger?

	/**
	 * Original signature : `NSInteger levelForRow(NSInteger)`<br></br>
	 * *native declaration : :150*
	 */
	abstract fun levelForRow(row: NSInteger?): NSInteger?

	/**
	 * Original signature : `BOOL isItemExpanded(id)`<br></br>
	 * *native declaration : :151*
	 */
	abstract fun isItemExpanded(item: NSObject?): Boolean

	/**
	 * The indentation amount per level defaults to 16.0.<br></br> Original signature : `void
	 * setIndentationPerLevel(CGFloat)`<br></br>
	 * *native declaration : :155*
	 */
	abstract fun setIndentationPerLevel(indentationPerLevel: CGFloat?)

	/**
	 * Original signature : `CGFloat indentationPerLevel()`<br></br>
	 * *native declaration : :156*
	 */
	abstract fun indentationPerLevel(): CGFloat?

	/**
	 * The indentation marker is the visual indicator that shows an item is expandable (i.e. disclosure triangle). The
	 * default value is YES.<br></br> Original signature : `void setIndentationMarkerFollowsCell(BOOL)`<br></br>
	 * *native declaration : :160*
	 */
	abstract fun setIndentationMarkerFollowsCell(drawInCell: Boolean)

	/**
	 * Original signature : `BOOL indentationMarkerFollowsCell()`<br></br>
	 * *native declaration : :161*
	 */
	abstract fun indentationMarkerFollowsCell(): Boolean

	/**
	 * Original signature : `void setAutoresizesOutlineColumn(BOOL)`<br></br>
	 * *native declaration : :163*
	 */
	abstract fun setAutoresizesOutlineColumn(resize: Boolean)

	/**
	 * Original signature : `BOOL autoresizesOutlineColumn()`<br></br>
	 * *native declaration : :164*
	 */
	abstract fun autoresizesOutlineColumn(): Boolean
	/**
	 * *native declaration : :170*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * To be used from validateDrop: in order to "re-target" the proposed drop.  To specify a drop on an item I, one
	 * would specify item=I, and index=NSOutlineViewDropOnItemIndex.  To specify a drop between child 2 and 3 of an item
	 * I, on would specify item=I, and index=3 (children are zero-base indexed).  To specify a drop on an un-expandable
	 * item I, one would specify item=I, and index=NSOutlineViewDropOnItemIndex.<br></br> Original signature : `void
	 * setDropItem(id, NSInteger)`<br></br>
	 * *native declaration : :179*
	 */
	abstract fun setDropItem_dropChildIndex(item: NSObject?, index: NSInteger?)

	fun setDropItem(item: NSObject?, index: NSInteger?) {
		this.setDropItem_dropChildIndex(item, index)
	}

	/**
	 * This method returns YES to indicate that auto expanded items should return to their original collapsed state.
	 * Override this method to provide custom behavior.  'deposited' tells wether or not the drop terminated due to a
	 * successful drop (as indicated by the return value from acceptDrop:).  Note that exiting the view will be treated
	 * the same as a failed drop.<br></br> Original signature : `BOOL shouldCollapseAutoExpandedItemsForDeposited(BOOL)`<br></br>
	 * *native declaration : :183*
	 */
	abstract fun shouldCollapseAutoExpandedItemsForDeposited(deposited: Boolean): Boolean

	/**
	 * Persistence. The value for autosaveExpandedItems is saved out in the nib file on Mac OS 10.5 or higher. The
	 * default value is NO. Calling setAutosaveExpandedItems:YES requires you to implement
	 * outlineView:itemForPersistentObject: and outlineView:persistentObjectForItem:.<br></br> Original signature :
	 * `BOOL autosaveExpandedItems()`<br></br>
	 * *native declaration : :187*
	 */
	abstract fun autosaveExpandedItems(): Boolean

	/**
	 * Original signature : `void setAutosaveExpandedItems(BOOL)`<br></br>
	 * *native declaration : :188*
	 */
	abstract fun setAutosaveExpandedItems(save: Boolean)

	/**
	 * When the value of this property is YES, the outline view retains and releases the objects returned to it from
	 * dataSource. When the value is NO, the outline view treats the objects as opaque items and assumes that the client
	 * has a retain on them. The default value is YES for applications linked on macOS 10.12 and later, and NO for
	 * applications linked on earlier versions of macOS. If you require the legacy behavior and your app links in macOS
	 * 10.12 or later, the value of this property must be explicitly set toNO in code, because it is not encoded in the
	 * nib. In general, this is required if the items themselves create a retain cycle.
	 *
	 * @return A Boolean value that indicates whether the outline view retains and releases the objects returned from
	 * its data source.
	 */
	abstract fun stronglyReferencesItems(): Boolean

	/**
	 * When the value of this property is YES, the outline view retains and releases the objects returned to it from
	 * dataSource. When the value is NO, the outline view treats the objects as opaque items and assumes that the client
	 * has a retain on them. The default value is YES for applications linked on macOS 10.12 and later, and NO for
	 * applications linked on earlier versions of macOS. If you require the legacy behavior and your app links in macOS
	 * 10.12 or later, the value of this property must be explicitly set toNO in code, because it is not encoded in the
	 * nib. In general, this is required if the items themselves create a retain cycle.
	 *
	 * @param value A Boolean value that indicates whether the outline view retains and releases the objects returned
	 * from its data source.
	 */
	abstract fun setStronglyReferencesItems(value: Boolean)

	companion object {
		val NSOutlineViewDropOnItemIndex: NSInteger? = NSInteger(-1)
	}
}
