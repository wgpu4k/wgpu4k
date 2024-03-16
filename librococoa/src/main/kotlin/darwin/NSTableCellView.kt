package darwin

abstract class NSTableCellView : NSView() {
	abstract fun objectValue(): NSObject?

	abstract fun imageView(): NSImageView?

	abstract fun textField(): NSTextField?
}
