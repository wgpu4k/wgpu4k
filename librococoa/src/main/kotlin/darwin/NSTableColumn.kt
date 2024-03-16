package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.CGFloat
import org.rococoa.cocoa.foundation.NSInteger

/// <i>native declaration : :17</i>
abstract class NSTableColumn : NSObject() {
	interface _Class : ObjCClass {
		open fun alloc(): NSTableColumn
	}

	/**
	 * Original signature : `id initWithIdentifier(id)`<br></br>
	 * *native declaration : :40*
	 */
	abstract fun initWithIdentifier(identifier: String?): NSTableColumn?

	/**
	 * Original signature : `void setIdentifier(id)`<br></br>
	 * *native declaration : :42*
	 */
	abstract fun setIdentifier(identifier: String?)

	/**
	 * Original signature : `id identifier()`<br></br>
	 * *native declaration : :43*
	 */
	abstract fun identifier(): String?

	/**
	 * Original signature : `void setTableView(NSTableView*)`<br></br>
	 * *native declaration : :44*
	 */
	abstract fun setTableView(tableView: NSTableView?)

	/**
	 * Original signature : `NSTableView* tableView()`<br></br>
	 * *native declaration : :45*
	 */
	abstract fun tableView(): NSTableView?

	/**
	 * Original signature : `void setWidth(CGFloat)`<br></br>
	 * *native declaration : :46*
	 */
	abstract fun setWidth(width: CGFloat?)

	fun setWidth(width: Double) {
		this.setWidth(CGFloat(width))
	}

	/**
	 * Original signature : `CGFloat width()`<br></br>
	 * *native declaration : :47*
	 */
	abstract fun width(): CGFloat?

	/**
	 * Original signature : `void setMinWidth(CGFloat)`<br></br>
	 * *native declaration : :48*
	 */
	abstract fun setMinWidth(width: CGFloat?)

	fun setMinWidth(width: Double) {
		this.setMinWidth(CGFloat(width))
	}

	/**
	 * Original signature : `CGFloat minWidth()`<br></br>
	 * *native declaration : :49*
	 */
	abstract fun minWidth(): CGFloat?

	/**
	 * Original signature : `void setMaxWidth(CGFloat)`<br></br>
	 * *native declaration : :50*
	 */
	abstract fun setMaxWidth(maxWidth: CGFloat?)

	fun setMaxWidth(width: Double) {
		this.setMaxWidth(CGFloat(width))
	}

	/**
	 * Original signature : `CGFloat maxWidth()`<br></br>
	 * *native declaration : :51*
	 */
	abstract fun maxWidth(): CGFloat?

	/**
	 * Original signature : `void setHeaderCell(NSCell*)`<br></br>
	 * Manage the cell used to draw the header for this column<br></br>
	 * *native declaration : :53*
	 */
	abstract fun setHeaderCell(cell: NSCell?)

	/**
	 * Original signature : `id headerCell()`<br></br>
	 * *native declaration : :54*
	 */
	abstract fun headerCell(): NSCell?

	/**
	 * Manage the cell used to draw the actual values in the column. NSTableView will call -dataCellForRow:. By default, -dataCellForRow: just calls -dataCell.  Subclassers can override -dataCellForRow: if they need to potentially use different cells for different rows. The returned cell should properly implement copyWithZone:, since NSTableView may make copies of the cells.<br></br>
	 * Original signature : `void setDataCell(NSCell*)`<br></br>
	 * *native declaration : :58*
	 */
	abstract fun setDataCell(cell: NSCell?)

	/**
	 * Original signature : `id dataCell()`<br></br>
	 * *native declaration : :59*
	 */
	abstract fun dataCell(): NSCell?

	/**
	 * Original signature : `id dataCellForRow(NSInteger)`<br></br>
	 * *native declaration : :60*
	 */
	abstract fun dataCellForRow(row: NSInteger?): NSCell?

	/**
	 * Original signature : `void setEditable(BOOL)`<br></br>
	 * *native declaration : :62*
	 */
	abstract fun setEditable(flag: Boolean)

	/**
	 * Original signature : `BOOL isEditable()`<br></br>
	 * *native declaration : :63*
	 */
	abstract fun isEditable(): Boolean

	/**
	 * Original signature : `void setHidden(BOOL)`<br></br>
	 * *native declaration : :62*
	 */
	abstract fun setHidden(flag: Boolean)

	/**
	 * Original signature : `BOOL isHidden()`<br></br>
	 * *native declaration : :63*
	 */
	abstract fun isHidden(): Boolean

	/**
	 * Original signature : `void sizeToFit()`<br></br>
	 * *native declaration : :64*
	 */
	abstract fun sizeToFit()

	/**
	 * A column is considered sortable if it has a sortDescriptorPrototype.  This prototype defines several things about the columns sorting.  The prototype's ascending value defines the default sorting direction.  Its key defines an arbitrary attribute which helps clients identify what to sort, while the selector defines how to sort.  Note that, it is not required that the key be the same as the identifier.  However, the key must be unique from the key used by other columns.  The sortDescriptor is archived.<br></br>
	 * Original signature : `void setSortDescriptorPrototype(NSSortDescriptor*)`<br></br>
	 * *native declaration : :70*
	 */
	abstract fun setSortDescriptorPrototype(sortDescriptor: com.sun.jna.Pointer?)

	/**
	 * Original signature : `NSSortDescriptor* sortDescriptorPrototype()`<br></br>
	 * *native declaration : :71*
	 */
	abstract fun sortDescriptorPrototype(): com.sun.jna.Pointer?

	/**
	 * The resizing mask controls the resizability of a table column.  Compatability Note: This method replaces setResizable.<br></br>
	 * Original signature : `void setResizingMask(NSUInteger)`<br></br>
	 * *native declaration : :78*
	 */
	abstract fun setResizingMask(resizingMask: Int)

	/**
	 * Original signature : `NSUInteger resizingMask()`<br></br>
	 * *native declaration : :79*
	 */
	abstract fun resizingMask(): Int

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSTableColumn", _Class::class.java)

		const val NSTableColumnNoResizing: Int = 0 // Disallow any kind of resizing.
		const val NSTableColumnAutoresizingMask: Int = (1 shl 0) // This column can be resized as the table is resized.
		const val NSTableColumnUserResizingMask: Int = (1 shl 1) // The user can resize this column manually.

		fun tableColumnWithIdentifier(identifier: String?): NSTableColumn? {
			return CLASS.alloc().initWithIdentifier(identifier)
		}
	}
}
