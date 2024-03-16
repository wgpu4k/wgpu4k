package darwin 

import org.rococoa.ID
import org.rococoa.cocoa.CGFloat
import org.rococoa.cocoa.foundation.NSInteger
import org.rococoa.cocoa.foundation.NSPoint
import org.rococoa.cocoa.foundation.NSUInteger

/// <i>native declaration : :69</i>
abstract class NSTableView : NSControl() {
	interface DataSource {
		open fun numberOfRowsInTableView(view: NSTableView?): NSInteger?

		open fun tableView_objectValueForTableColumn_row(
			view: NSTableView?,
			tableColumn: NSTableColumn?,
			row: NSInteger?
		): NSObject?

		open fun tableView_setObjectValue_forTableColumn_row(
			view: NSTableView?,
			value: NSObject?,
			tableColumn: NSTableColumn?,
			row: NSInteger?
		)

		open fun tableView_writeRowsWithIndexes_toPasteboard(
			view: NSTableView?,
			rowIndexes: NSIndexSet?,
			pboard: NSPasteboard?
		): Boolean

		open fun tableView_validateDrop_proposedRow_proposedDropOperation(
			view: NSTableView?,
			info: NSDraggingInfo?,
			row: NSInteger?,
			operation: NSUInteger?
		): NSUInteger?

		open fun tableView_acceptDrop_row_dropOperation(
			view: NSTableView?,
			draggingInfo: NSDraggingInfo?,
			row: NSInteger?,
			operation: NSUInteger?
		): Boolean

		open fun tableView_namesOfPromisedFilesDroppedAtDestination_forDraggedRowsWithIndexes(
			view: NSTableView?,
			dropDestination: NSURL?,
			rowIndexes: NSIndexSet?
		): NSArray?
	}

	interface Delegate {
		open fun tableView_willDisplayCell_forTableColumn_row(
			view: NSTableView?,
			cell: NSTextFieldCell?,
			tableColumn: NSTableColumn?,
			row: NSInteger?
		)
	}

	/**
	 * Original signature : `void addTableColumn(NSTableColumn*)`<br></br>
	 * *native declaration : :98*
	 */
	abstract fun addTableColumn(column: NSTableColumn?)

	/**
	 * Original signature : `void setDataSource(id)`<br></br>
	 * *native declaration : :100*
	 */
	abstract fun setDataSource(aSource: ID?)

	/**
	 * Original signature : `id dataSource()`<br></br>
	 * *native declaration : :101*
	 */
	abstract fun dataSource(): NSObject?

	/**
	 * Original signature : `void setDelegate(id)`<br></br>
	 * *native declaration : :102*
	 */
	abstract fun setDelegate(delegate: ID?)

	/**
	 * Original signature : `id delegate()`<br></br>
	 * *native declaration : :103*
	 */
	abstract fun delegate(): ID?

	/**
	 * Original signature : `void setHeaderView(NSTableHeaderView*)`<br></br>
	 * *native declaration : :104*
	 */
	abstract fun setHeaderView(headerView: NSView?)

	/**
	 * Original signature : `NSTableHeaderView* headerView()`<br></br>
	 * *native declaration : :105*
	 */
	abstract fun headerView(): NSView?

	/**
	 * Original signature : `void setCornerView(NSView*)`<br></br>
	 * *native declaration : :107*
	 */
	abstract fun setCornerView(cornerView: NSView?)

	/**
	 * Original signature : `NSView* cornerView()`<br></br>
	 * *native declaration : :108*
	 */
	abstract fun cornerView(): NSView?

	/**
	 * Original signature : `void setAllowsColumnReordering(BOOL)`<br></br>
	 * *native declaration : :110*
	 */
	abstract fun setAllowsColumnReordering(flag: Boolean)

	/**
	 * Original signature : `BOOL allowsColumnReordering()`<br></br>
	 * *native declaration : :111*
	 */
	abstract fun allowsColumnReordering(): Boolean

	/**
	 * Controls whether the user can attemp to resize columns by dragging between headers. If flag is YES the user can
	 * resize columns; if flag is NO the user can't. The default is YES. Columns can only be resized if a column allows
	 * user resizing.  See NSTableColumn setResizingMask: for more details.  You can always change columns
	 * programmatically regardless of this setting.<br></br> Original signature : `void
	 * setAllowsColumnResizing(BOOL)`<br></br>
	 * *native declaration : :115*
	 */
	abstract fun setAllowsColumnResizing(flag: Boolean)

	/**
	 * Original signature : `BOOL allowsColumnResizing()`<br></br>
	 * *native declaration : :116*
	 */
	abstract fun allowsColumnResizing(): Boolean

	/**
	 * *native declaration : :120*<br></br>
	 * Conversion Error : /// Original signature : `void setColumnAutoresizingStyle(null)`<br></br> -
	 * (void)setColumnAutoresizingStyle:(null)style; (Argument style cannot be converted)
	 */
	abstract fun setColumnAutoresizingStyle(style: NSUInteger?)

	/**
	 * Original signature : `columnAutoresizingStyle()`<br></br>
	 * *native declaration : :121*
	 */
	abstract fun columnAutoresizingStyle(): NSUInteger?

	/**
	 * Original signature : `void setGridStyleMask(NSUInteger)`<br></br>
	 * *native declaration : :125*
	 */
	abstract fun setGridStyleMask(gridType: NSUInteger?)

	/**
	 * Original signature : `NSUInteger gridStyleMask()`<br></br>
	 * *native declaration : :126*
	 */
	abstract fun gridStyleMask(): NSUInteger?
	/**
	 * *native declaration : :128*<br></br>
	 * Conversion Error : NSSize
	 */
	/**
	 * *native declaration : :129*<br></br>
	 * Conversion Error : NSSize
	 */
	/**
	 * Configures the table to use either the standard alternating row colors, or a solid color for its background.<br></br>
	 * Original signature : `void setUsesAlternatingRowBackgroundColors(BOOL)`<br></br>
	 * *native declaration : :133*
	 */
	abstract fun setUsesAlternatingRowBackgroundColors(useAlternatingRowColors: Boolean)

	/**
	 * Original signature : `BOOL usesAlternatingRowBackgroundColors()`<br></br>
	 * *native declaration : :134*
	 */
	abstract fun usesAlternatingRowBackgroundColors(): Boolean

	/**
	 * The backgroundColor defaults to [NSColor controlBackgroundColor]. On Mac OS 10.5 and higher, the alpha portion of
	 * 'color' is properly used when drawing the backgroundColor. To have a transparent tableView, set the
	 * backgroundColor to [NSColor clearColor], and set the enclosing NSScrollView to not draw its background with:
	 * [[tableView enclosingScrollView] setDrawsBackground:NO]. NSTableView uses NSCompositeSourceOver when drawing the
	 * background color.<br></br> Original signature : `void setBackgroundColor(NSColor*)`<br></br>
	 * *native declaration : :138*
	 */
	abstract fun setBackgroundColor(color: NSColor?)

	/**
	 * Original signature : `NSColor* backgroundColor()`<br></br>
	 * *native declaration : :139*
	 */
	abstract fun backgroundColor(): NSColor?

	/**
	 * Original signature : `void setGridColor(NSColor*)`<br></br>
	 * *native declaration : :141*
	 */
	abstract fun setGridColor(color: NSColor?)

	/**
	 * Original signature : `NSColor* gridColor()`<br></br>
	 * *native declaration : :142*
	 */
	abstract fun gridColor(): NSColor?

	/**
	 * Original signature : `void setRowHeight(CGFloat)`<br></br>
	 * *native declaration : :144*
	 */
	abstract fun setRowHeight(rowHeight: CGFloat?)

	/**
	 * Original signature : `CGFloat rowHeight()`<br></br>
	 * *native declaration : :145*
	 */
	abstract fun rowHeight(): CGFloat?

	/**
	 * If the delegate implements -tableView:heightOfRow:, this method immediately re-tiles the table view using row
	 * heights it provides.<br></br> Original signature : `void noteHeightOfRowsWithIndexesChanged(NSIndexSet*)`<br></br>
	 * *native declaration : :149*
	 */
	abstract fun noteHeightOfRowsWithIndexesChanged(indexSet: NSIndexSet?)

	/**
	 * Original signature : `NSArray* tableColumns()`<br></br>
	 * *native declaration : :151*
	 */
	abstract fun tableColumns(): NSArray?

	/**
	 * Original signature : `NSInteger numberOfColumns()`<br></br>
	 * *native declaration : :152*
	 */
	abstract fun numberOfColumns(): NSInteger?

	/**
	 * Original signature : `NSInteger numberOfRows()`<br></br>
	 * *native declaration : :153*
	 */
	abstract fun numberOfRows(): NSInteger?

	/**
	 * Original signature : `void removeTableColumn(NSTableColumn*)`<br></br>
	 * *native declaration : :156*
	 */
	abstract fun removeTableColumn(column: NSTableColumn?)

	/**
	 * Original signature : `NSInteger columnWithIdentifier(id)`<br></br>
	 * *native declaration : :157*
	 */
	abstract fun columnWithIdentifier(identifier: String?): NSInteger?

	/**
	 * Original signature : `NSTableColumn* tableColumnWithIdentifier(id)`<br></br>
	 * *native declaration : :158*
	 */
	abstract fun tableColumnWithIdentifier(identifier: String?): NSTableColumn?

	/**
	 * Original signature : `void tile()`<br></br>
	 * *native declaration : :160*
	 */
	abstract fun tile()

	/**
	 * Original signature : `void sizeLastColumnToFit()`<br></br>
	 * *native declaration : :162*
	 */
	abstract fun sizeLastColumnToFit()

	/**
	 * Original signature : `void scrollRowToVisible(NSInteger)`<br></br>
	 * *native declaration : :163*
	 */
	abstract fun scrollRowToVisible(row: NSInteger?)

	/**
	 * Original signature : `void scrollColumnToVisible(NSInteger)`<br></br>
	 * *native declaration : :164*
	 */
	abstract fun scrollColumnToVisible(column: NSInteger?)

	/**
	 * Original signature : `void moveColumn(NSInteger, NSInteger)`<br></br>
	 * *native declaration : :165*
	 */
	abstract fun moveColumn_toColumn(column: NSInteger?, newIndex: NSInteger?)

	/**
	 * Original signature : `void reloadData()`<br></br>
	 * *native declaration : :167*
	 */
	abstract fun reloadData()

	/**
	 * Original signature : `- (void)reloadDataForRowIndexes:(NSIndexSet *)rowIndexes columnIndexes:(NSIndexSet
	 * *)columnIndexes`<br></br>
	 * *native declaration : :167*
	 */
	abstract fun reloadDataForRowIndexes_columnIndexes(rowIndexes: NSIndexSet?, columnIndexes: NSIndexSet?)

	/**
	 * Typically, identifier is associated with a cell view that’s contained in a table’s Nib file. When this method is
	 * called, the table view automatically instantiates the cell view with the specified owner, which is usually the
	 * table view’s delegate. (The owner is useful in setting up outlets and target/actions from the view.) Note that a
	 * cell view’s identifier must be the same as its table column’s identifier for bindings to work. If you’re using
	 * bindings, it’s recommended that you use the Automatic identifier setting in Interface Builder.
	 *
	 * @param identifier The view identifier. Must not be nil.
	 * @param owner      The owner of the NIB that may be loaded and instantiated to create a new view with the
	 * specified identifier.
	 * @return A view for the row.
	 */
	abstract fun makeViewWithIdentifier_owner(identifier: String?, owner: ID?): NSTableCellView?

	/**
	 * Original signature : `void noteNumberOfRowsChanged()`<br></br>
	 * *native declaration : :168*
	 */
	abstract fun noteNumberOfRowsChanged()

	/**
	 * Original signature : `NSInteger editedColumn()`<br></br>
	 * *native declaration : :170*
	 */
	abstract fun editedColumn(): NSInteger?

	/**
	 * Original signature : `NSInteger editedRow()`<br></br>
	 * *native declaration : :171*
	 */
	abstract fun editedRow(): NSInteger?

	/**
	 * Original signature : `NSInteger clickedColumn()`<br></br>
	 * *native declaration : :172*
	 */
	abstract fun clickedColumn(): NSInteger?

	/**
	 * Original signature : `NSInteger clickedRow()`<br></br>
	 * *native declaration : :173*
	 */
	abstract fun clickedRow(): NSInteger?

	/**
	 * Original signature : `void setDoubleAction(SEL)`<br></br>
	 * *native declaration : :175*
	 */
	abstract fun setDoubleAction(aSelector: org.rococoa.Selector?)

	/**
	 * Original signature : `SEL doubleAction()`<br></br>
	 * *native declaration : :176*
	 */
	abstract fun doubleAction(): org.rococoa.Selector?

	/**
	 * Sorting Support<br></br> The array of sort descriptors is archived.  Sort descriptors will persist along with other
	 * column information if an autosave name is set.<br></br> Original signature : `void
	 * setSortDescriptors(NSArray*)`<br></br>
	 * *native declaration : :182*
	 */
	abstract fun setSortDescriptors(array: NSArray?)

	/**
	 * Original signature : `NSArray* sortDescriptors()`<br></br>
	 * *native declaration : :183*
	 */
	abstract fun sortDescriptors(): NSArray?

	/**
	 * Support for little "indicator" images in table header cells.<br></br> Original signature : `void
	 * setIndicatorImage(NSImage*, NSTableColumn*)`<br></br>
	 * *native declaration : :187*
	 */
	abstract fun setIndicatorImage_inTableColumn(anImage: NSImage?, tc: NSTableColumn?)

	/**
	 * Original signature : `NSImage* indicatorImageInTableColumn(NSTableColumn*)`<br></br>
	 * *native declaration : :188*
	 */
	abstract fun indicatorImageInTableColumn(tc: NSTableColumn?): NSImage?

	/**
	 * Support for highlightable column header, for use with row selection.<br></br> Original signature : `void
	 * setHighlightedTableColumn(NSTableColumn*)`<br></br>
	 * *native declaration : :192*
	 */
	abstract fun setHighlightedTableColumn(tc: NSTableColumn?)

	/**
	 * Original signature : `NSTableColumn* highlightedTableColumn()`<br></br>
	 * *native declaration : :193*
	 */
	abstract fun highlightedTableColumn(): NSTableColumn?

	/**
	 * Original signature : `void setVerticalMotionCanBeginDrag(BOOL)`<br></br>
	 * *native declaration : :199*
	 */
	abstract fun setVerticalMotionCanBeginDrag(flag: Boolean)

	/**
	 * Original signature : `BOOL verticalMotionCanBeginDrag()`<br></br>
	 * *native declaration : :200*
	 */
	abstract fun verticalMotionCanBeginDrag(): Boolean
	/**
	 * *native declaration : :206*<br></br>
	 * Conversion Error : / **<br></br>
	 * * The return value indicates whether the receiver can attempt to initiate a row drag at 'mouseDownPoint'. Return NO to disallow initiating drags at the given location. <br></br>
	 * * For applications linked on and after Leopard, NSCell hit testing will determine if a row can be dragged or not. Custom cells should properly implement [NSCell(NSCellHitTest) hitTestForEvent:inRect:ofView]; see NSCell.h for more information. NSTableView will not begin a drag if cell returns NSCellHitTrackableArea.<br></br>
	 * * Original signature : `BOOL canDragRowsWithIndexes(NSIndexSet*, null)`<br></br>
	 * * /<br></br>
	 * - (BOOL)canDragRowsWithIndexes:(NSIndexSet*)rowIndexes atPoint:(null)mouseDownPoint; (Argument mouseDownPoint cannot be converted)
	 */
	/**
	 * *native declaration : :212*<br></br>
	 * Conversion Error : / **<br></br>
	 * * This method computes and returns an image to use for dragging.  Override this to return a custom image.  'dragRows' represents the rows participating in the drag.  'tableColumns' represent the columns that should be in the output image.  Note that drawing may be clipped to the visible rows, and columns.  'dragEvent' is a reference to the mouse down event that began the drag.  'dragImageOffset' is an in/out parameter.  This method will be called with dragImageOffset set to NSZeroPoint, but it can be modified to re-position the returned image.  A dragImageOffset of NSZeroPoint will cause the image to be centered under the mouse.<br></br>
	 * * Compatability Note: This method replaces dragImageForRows:event:dragImageOffset:.  If present, this is used instead of the deprecated method.<br></br>
	 * * Original signature : `NSImage* dragImageForRowsWithIndexes(NSIndexSet*, NSArray*, NSEvent*, null)`<br></br>
	 * * /<br></br>
	 * - (NSImage*)dragImageForRowsWithIndexes:(NSIndexSet*)dragRows tableColumns:(NSArray*)tableColumns event:(NSEvent*)dragEvent offset:(null)dragImageOffset; (Argument dragImageOffset cannot be converted)
	 */
	/**
	 * *native declaration : :216*<br></br>
	 * Conversion Error : / **<br></br>
	 * * Configures the default value returned from -draggingSourceOperationMaskForLocal:.  An isLocal value of YES indicates that 'mask' applies when the destination object is in the same application.  A isLocal value of NO indicates that 'mask' applies when the destination object in an application outside the receiver's application.  NSTableView will archive the values you set for each isLocal setting.<br></br>
	 * * Original signature : `void setDraggingSourceOperationMask(null, BOOL)`<br></br>
	 * * /<br></br>
	 * - (void)setDraggingSourceOperationMask:(null)mask forLocal:(BOOL)isLocal; (Argument mask cannot be converted)
	 */
	/**
	 * To be used from validateDrop: if you wish to "re-target" the proposed drop. To specify a drop on the second row,
	 * one would specify row=2, and op=NSTableViewDropOn. To specify a drop below the last row, one would specify
	 * row=[tv numberOfRows], and op=NSTableViewDropAbove. To specify a drop on the entire tableview, one would specify
	 * row=-1 and op=NSTableViewDropOn.<br></br> Original signature : `void setDropRow(NSInteger,
	 * NSTableViewDropOperation)`<br></br>
	 * *native declaration : :220*
	 */
	abstract fun setDropRow_dropOperation(row: NSInteger?, op: NSUInteger?)

	/**
	 * @param row
	 * @param op  operation
	 */
	fun setDropRow(row: NSInteger?, op: NSUInteger?) {
		this.setDropRow_dropOperation(row, op)
	}

	/**
	 * Selection<br></br> Original signature : `void setAllowsMultipleSelection(BOOL)`<br></br>
	 * *native declaration : :226*
	 */
	abstract fun setAllowsMultipleSelection(flag: Boolean)

	/**
	 * Original signature : `BOOL allowsMultipleSelection()`<br></br>
	 * *native declaration : :227*
	 */
	abstract fun allowsMultipleSelection(): Boolean

	/**
	 * Original signature : `void setAllowsEmptySelection(BOOL)`<br></br>
	 * *native declaration : :228*
	 */
	abstract fun setAllowsEmptySelection(flag: Boolean)

	/**
	 * Original signature : `BOOL allowsEmptySelection()`<br></br>
	 * *native declaration : :229*
	 */
	abstract fun allowsEmptySelection(): Boolean

	/**
	 * Original signature : `void setAllowsColumnSelection(BOOL)`<br></br>
	 * *native declaration : :230*
	 */
	abstract fun setAllowsColumnSelection(flag: Boolean)

	/**
	 * Original signature : `BOOL allowsColumnSelection()`<br></br>
	 * *native declaration : :231*
	 */
	abstract fun allowsColumnSelection(): Boolean

	/**
	 * Original signature : `void deselectAll(id)`<br></br>
	 * *native declaration : :233*
	 */
	abstract fun deselectAll(sender: ID?)

	/**
	 * Sets the column selection using the indexes.  Selection is set/extended based on the extend flag. <br></br>
	 * Compatability Note: This method replaces selectColumn:byExtendingSelection:<br></br> If a subclasser implements only
	 * the deprecated single-index method (selectColumn:byExtendingSelection:), the single-index method will be invoked
	 * for each index.  If a subclasser implements the multi-index method (selectColumnIndexes:byExtendingSelection:),
	 * the deprecated single-index version method will not be used.  This allows subclassers already overriding the
	 * single-index method to still receive a selection message.  Note: to avoid cycles, subclassers of this method and
	 * single-index method should not call each other.<br></br> Original signature : `void
	 * selectColumnIndexes(NSIndexSet*, BOOL)`<br></br>
	 * *native declaration : :241*
	 */
	abstract fun selectColumnIndexes_byExtendingSelection(indexes: NSIndexSet?, extend: Boolean)

	/**
	 * Sets the row selection using 'indexes'. Selection is set/extended based on the extend flag. On 10.5 and greater,
	 * selectRowIndexes:byExtendingSelection: will allow you to progrmatically select more than one index, regardless of
	 * the allowsMultipleSelection and allowsEmptySelection options set on the table.<br></br> Compatability Note: This
	 * method replaces selectRow:byExtendingSelection:<br></br> If a subclasser implements only the deprecated single-index
	 * method (selectRow:byExtendingSelection:), the single-index method will be invoked for each index.  If a
	 * subclasser implements the multi-index method (selectRowIndexes:byExtendingSelection:), the deprecated
	 * single-index version method will not be used.  This allows subclassers already overriding the single-index method
	 * to still receive a selection message.  Note: to avoid cycles, subclassers of this method and single-index method
	 * should not call each other.<br></br> Original signature : `void selectRowIndexes(NSIndexSet*, BOOL)`<br></br>
	 * *native declaration : :248*
	 */
	abstract fun selectRowIndexes_byExtendingSelection(indexes: NSIndexSet?, extend: Boolean)

	fun selectRowIndexes(indexes: NSIndexSet?, extend: Boolean) {
		this.selectRowIndexes_byExtendingSelection(indexes, extend)
	}

	/**
	 * Original signature : `NSIndexSet* selectedColumnIndexes()`<br></br>
	 * *native declaration : :250*
	 */
	abstract fun selectedColumnIndexes(): NSIndexSet?

	/**
	 * Original signature : `NSIndexSet* selectedRowIndexes()`<br></br>
	 * *native declaration : :251*
	 */
	abstract fun selectedRowIndexes(): NSIndexSet?

	/**
	 * Original signature : `void deselectColumn(NSInteger)`<br></br>
	 * *native declaration : :254*
	 */
	abstract fun deselectColumn(column: NSInteger?)

	/**
	 * Original signature : `void deselectRow(NSInteger)`<br></br>
	 * *native declaration : :255*
	 */
	abstract fun deselectRow(row: NSInteger?)

	/**
	 * Original signature : `NSInteger selectedColumn()`<br></br>
	 * *native declaration : :256*
	 */
	abstract fun selectedColumn(): NSInteger?

	/**
	 * Original signature : `NSInteger selectedRow()`<br></br>
	 * *native declaration : :257*
	 */
	abstract fun selectedRow(): NSInteger?

	/**
	 * Original signature : `BOOL isColumnSelected(NSInteger)`<br></br>
	 * *native declaration : :258*
	 */
	abstract fun isColumnSelected(column: NSInteger?): Boolean

	/**
	 * Original signature : `BOOL isRowSelected(NSInteger)`<br></br>
	 * *native declaration : :259*
	 */
	abstract fun isRowSelected(row: NSInteger?): Boolean

	/**
	 * Original signature : `NSInteger numberOfSelectedColumns()`<br></br>
	 * *native declaration : :260*
	 */
	abstract fun numberOfSelectedColumns(): NSInteger?

	/**
	 * Original signature : `NSInteger numberOfSelectedRows()`<br></br>
	 * *native declaration : :261*
	 */
	abstract fun numberOfSelectedRows(): NSInteger?

	/**
	 * Original signature : `BOOL allowsTypeSelect()`<br></br>
	 * *native declaration : :269*
	 */
	abstract fun allowsTypeSelect(): Boolean

	/**
	 * Original signature : `void setAllowsTypeSelect(BOOL)`<br></br>
	 * *native declaration : :270*
	 */
	abstract fun setAllowsTypeSelect(value: Boolean)

	/**
	 * Gets and sets the current selection highlight style. Defaults to NSTableViewSelectionHighlightStyleRegular.<br></br>
	 * Original signature : `selectionHighlightStyle()`<br></br>
	 * *native declaration : :288*
	 */
	abstract fun selectionHighlightStyle(): NSObject?

	/**
	 * *native declaration : :289*<br></br>
	 * Conversion Error : /// Original signature : `void setSelectionHighlightStyle(null)`<br></br> -
	 * (void)setSelectionHighlightStyle:(null)selectionHighlightStyle; (Argument selectionHighlightStyle cannot be
	 * converted)
	 */
	abstract fun setSelectionHighlightStyle(selectionHighlightStyle: NSInteger?)
	/**
	 * *native declaration : :295*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :297*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :302*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :305*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :307*<br></br>
	 * Conversion Error : /// Original signature : `NSInteger columnAtPoint(null)`<br></br> -
	 * (NSInteger)columnAtPoint:(null)point; (Argument point cannot be converted)
	 */
	abstract fun columnAtPoint(point: NSPoint?): NSInteger?

	/**
	 * *native declaration : :309*<br></br>
	 * Conversion Error : /// Original signature : `NSInteger rowAtPoint(null)`<br></br> -
	 * (NSInteger)rowAtPoint:(null)point; (Argument point cannot be converted)
	 */
	abstract fun rowAtPoint(point: NSPoint?): NSInteger?
	/**
	 * *native declaration : :313*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Returns the fully prepared cell that the view will normally use for drawing or any processing. The value for the
	 * cell will be correctly set, and the delegate method 'willDisplayCell:' will have be called. You can override this
	 * method to do any additional setting up of the cell that is required, or call it to retrieve a cell that will have
	 * its contents properly set for the particular column and row.<br></br> Original signature : `NSCell*
	 * preparedCellAtColumn(NSInteger, NSInteger)`<br></br>
	 * *native declaration : :319*
	 */
	abstract fun preparedCellAtColumn_row(column: NSInteger?, row: NSInteger?): NSCell?

	/**
	 * Text delegate methods<br></br> Original signature : `BOOL textShouldBeginEditing(NSText*)`<br></br>
	 * *native declaration : :326*
	 */
	abstract fun textShouldBeginEditing(textObject: NSText?): Boolean

	/**
	 * Original signature : `BOOL textShouldEndEditing(NSText*)`<br></br>
	 * *native declaration : :327*
	 */
	abstract fun textShouldEndEditing(textObject: NSText?): Boolean

	/**
	 * Original signature : `void textDidBeginEditing(NSNotification*)`<br></br>
	 * *native declaration : :328*
	 */
	abstract fun textDidBeginEditing(notification: NSNotification?)

	/**
	 * Original signature : `void textDidEndEditing(NSNotification*)`<br></br>
	 * *native declaration : :329*
	 */
	abstract fun textDidEndEditing(notification: NSNotification?)

	/**
	 * Original signature : `void textDidChange(NSNotification*)`<br></br>
	 * *native declaration : :330*
	 */
	abstract fun textDidChange(notification: NSNotification?)

	/**
	 * Persistence methods<br></br> Original signature : `void setAutosaveName(NSString*)`<br></br>
	 * *native declaration : :335*
	 */
	abstract fun setAutosaveName(name: String?)

	/**
	 * Original signature : `NSString* autosaveName()`<br></br>
	 * *native declaration : :336*
	 */
	abstract fun autosaveName(): String?

	/**
	 * On Mac OS 10.4 and higher, the NSTableColumn width and location is saved. On Mac OS 10.5 and higher, the
	 * NSTableColumn 'isHidden' state is also saved. The 'autosaveName' must be set for 'autosaveTableColumns' to take
	 * effect.<br></br> Original signature : `void setAutosaveTableColumns(BOOL)`<br></br>
	 * *native declaration : :340*
	 */
	abstract fun setAutosaveTableColumns(save: Boolean)

	/**
	 * Original signature : `BOOL autosaveTableColumns()`<br></br>
	 * *native declaration : :341*
	 */
	abstract fun autosaveTableColumns(): Boolean

	/**
	 * For subclassers<br></br> Original signature : `void editColumn(NSInteger, NSInteger, NSEvent*, BOOL)`<br></br>
	 * *native declaration : :346*
	 */
	abstract fun editColumn_row_withEvent_select(
		column: NSInteger?,
		row: NSInteger?,
		theEvent: NSEvent?,
		select: Boolean
	)

	fun editRow(column: NSInteger?, row: NSInteger?, select: Boolean) {
		this.editColumn_row_withEvent_select(column, row, NSApplication.sharedApplication().currentEvent(), select)
	}

	/**
	 * *native declaration : :347*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :348*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :349*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :351*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Deprecated in Mac OS 10.3.  Calls setGridStyleMask:, setting grid style to either None, or vertical and horizonal
	 * solid grid lines as appropriate.<br></br> Original signature : `void setDrawsGrid(BOOL)`<br></br>
	 * *from NSDeprecated native declaration : :506*
	 */
	abstract fun setDrawsGrid(flag: Boolean)

	/**
	 * Deprecated in Mac OS 10.3.  Returns YES if gridStyleMask returns anything other than NSTableViewGridNone.<br></br>
	 * Original signature : `BOOL drawsGrid()`<br></br>
	 * *from NSDeprecated native declaration : :508*
	 */
	abstract fun drawsGrid(): Boolean

	/**
	 * Deprecated in Mac OS 10.3.  You should use selectColumnIndexes:byExtendingSelection: instead.  See that method
	 * for more details.<br></br> Original signature : `void selectColumn(NSInteger, BOOL)`<br></br>
	 * *from NSDeprecated native declaration : :511*
	 */
	abstract fun selectColumn_byExtendingSelection(column: NSInteger?, extend: Boolean)

	/**
	 * Deprecated in Mac OS 10.3.  You should use selectedColumnIndexes instead.<br></br> Original signature :
	 * `NSEnumerator* selectedColumnEnumerator()`<br></br>
	 * *from NSDeprecated native declaration : :515*
	 */
	abstract fun selectedColumnEnumerator(): NSEnumerator?

	/**
	 * Deprecated in Mac OS 10.3.  You should use selectedRowIndexes instead.<br></br> Original signature :
	 * `NSEnumerator* selectedRowEnumerator()`<br></br>
	 * *from NSDeprecated native declaration : :517*
	 */
	abstract fun selectedRowEnumerator(): NSEnumerator?
	/**
	 * *from NSDeprecated native declaration : :520*<br></br>
	 * Conversion Error : / **<br></br>
	 * * Deprecated in Mac OS 10.4.  You should use / override dragImageForRowsWithIndexes:tableColumns:event:dragImageOffset: instead.<br></br>
	 * * Original signature : `NSImage* dragImageForRows(NSArray*, NSEvent*, null)`<br></br>
	 * * /<br></br>
	 * - (NSImage*)dragImageForRows:(NSArray*)dragRows event:(NSEvent*)dragEvent dragImageOffset:(null)dragImageOffset; (Argument dragImageOffset cannot be converted)
	 */
	/**
	 * Deprecated in Mac OS 10.4.  You should use setColumnAutoresizingStyle: instead.  To preserve compatibility, if
	 * flag is YES, This method calls setColumnAutoresizingStyle:NSTableViewUniformColumnAutoresizingStyle.  If flag is
	 * NO, this method calls setColumnAutoresizingStyle:NSTableViewLastColumnOnlyAutoresizingStyle.<br></br> Original
	 * signature : `void setAutoresizesAllColumnsToFit(BOOL)`<br></br>
	 * *from NSDeprecated native declaration : :523*
	 */
	abstract fun setAutoresizesAllColumnsToFit(flag: Boolean)

	/**
	 * Original signature : `BOOL autoresizesAllColumnsToFit()`<br></br>
	 * *from NSDeprecated native declaration : :524*
	 */
	abstract fun autoresizesAllColumnsToFit(): Boolean

	/**
	 * *from NSDeprecated native declaration : :528*<br></br>
	 * Conversion Error : NSRect
	 */
	companion object {
		/// <i>native declaration : :60</i>
		val NSTableViewDropOn: NSUInteger? = NSUInteger(0)

		/// <i>native declaration : :60</i>
		val NSTableViewDropAbove: NSUInteger? = NSUInteger(1)

		val NSTableViewSelectionDidChangeNotification: String? = "NSTableViewSelectionDidChangeNotification"
		val NSTableViewColumnDidMoveNotification: String? = "NSTableViewColumnDidMoveNotification"
		val NSTableViewColumnDidResizeNotification: String? = "NSTableViewColumnDidResizeNotification"
		val NSTableViewSelectionIsChangingNotification: String? = "NSTableViewSelectionIsChangingNotification"

		val NSTableViewGridNone: NSUInteger? = NSUInteger(0)
		val NSTableViewSolidVerticalGridLineMask: NSUInteger? = NSUInteger(1)
		val NSTableViewSolidHorizontalGridLineMask: NSUInteger? = NSUInteger(2)

		val NSTableViewNoColumnAutoresizing: NSUInteger? = NSUInteger(0)
		val NSTableViewUniformColumnAutoresizingStyle: NSUInteger? = NSUInteger(1)
		val NSTableViewSequentialColumnAutoresizingStyle: NSUInteger? = NSUInteger(2)
		val NSTableViewReverseSequentialColumnAutoresizingStyle: NSUInteger? = NSUInteger(3)
		val NSTableViewLastColumnOnlyAutoresizingStyle: NSUInteger? = NSUInteger(4)
		val NSTableViewFirstColumnOnlyAutoresizingStyle: NSUInteger? = NSUInteger(5)

		/**
		 * The regular highlight style of NSTableView. On 10.5, a light blue ([NSColor alternateSelectedControlColor]) or
		 * light gray color ([NSColor secondarySelectedControlColor]) is used to highlight selected rows.
		 */
		val NSTableViewSelectionHighlightStyleRegular: NSInteger? = NSInteger(0)

		/**
		 * The source list style of NSTableView. On 10.5, a light blue gradient is used to highlight selected rows. Note:
		 * Cells that have a drawsBackground property should have it set to NO. Otherwise, they will draw over the
		 * highlighting that NSTableView does. Setting this style will have the side effect of setting the background color
		 * to the "source list" background color. Additionally in NSOutlineView, the following properties are changed to get
		 * the standard "source list" look: indentationPerLevel, rowHeight and intercellSpacing. After calling
		 * setSelectionHighlightStyle: one can change any of the other properties as required.
		 */
		val NSTableViewSelectionHighlightStyleSourceList: NSInteger? = NSInteger(1)
	}
}
