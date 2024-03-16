package darwin 

import org.rococoa.Selector

/// <i>native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSUserInterfaceValidation.h:69</i>
interface NSValidatedUserInterfaceItem {
	/**
	 * Original signature : `action()`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSUserInterfaceValidation.h:70*
	 */
	open fun action(): Selector?

	/**
	 * Original signature : `NSInteger tag()`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSUserInterfaceValidation.h:71*
	 */
	open fun tag(): Int
}
