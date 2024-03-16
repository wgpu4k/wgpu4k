package darwin 

import org.rococoa.ID
import org.rococoa.ObjCClass
import org.rococoa.Selector

/// <i>native declaration : :14</i>
abstract class NSOpenPanel : NSSavePanel() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `NSOpenPanel* openPanel()`<br></br>
		 * *native declaration : :19*
		 */
		open fun openPanel(): NSOpenPanel?
	}

	/**
	 * Original signature : `NSArray* URLs()`<br></br>
	 * *native declaration : :21*
	 */
	abstract fun URLs(): NSArray?

	/**
	 * Original signature : `BOOL resolvesAliases()`<br></br>
	 * *native declaration : :24*
	 */
	abstract fun resolvesAliases(): Boolean

	/**
	 * Original signature : `void setResolvesAliases(BOOL)`<br></br>
	 * *native declaration : :25*
	 */
	abstract fun setResolvesAliases(flag: Boolean)

	/**
	 * Original signature : `BOOL canChooseDirectories()`<br></br>
	 * *native declaration : :27*
	 */
	abstract fun canChooseDirectories(): Boolean

	/**
	 * Original signature : `void setCanChooseDirectories(BOOL)`<br></br>
	 * *native declaration : :28*
	 */
	abstract fun setCanChooseDirectories(flag: Boolean)

	/**
	 * Original signature : `BOOL allowsMultipleSelection()`<br></br>
	 * *native declaration : :30*
	 */
	abstract fun allowsMultipleSelection(): Boolean

	/**
	 * Original signature : `void setAllowsMultipleSelection(BOOL)`<br></br>
	 * *native declaration : :31*
	 */
	abstract fun setAllowsMultipleSelection(flag: Boolean)

	/**
	 * Original signature : `BOOL canChooseFiles()`<br></br>
	 * *native declaration : :33*
	 */
	abstract fun canChooseFiles(): Boolean

	/**
	 * Original signature : `void setCanChooseFiles(BOOL)`<br></br>
	 * *native declaration : :34*
	 */
	abstract fun setCanChooseFiles(flag: Boolean)

	/**
	 * Private
	 *
	 * @param show
	 */
	abstract fun setShowsHiddenFiles(show: Boolean)

	/**
	 * *from NSOpenPanelRuntime native declaration : :40*<br></br>
	 * Conversion Error : /// Original signature : `void beginSheetForDirectory(NSString*, NSString*, NSArray*, NSWindow*, null, null, void*)`<br></br>
	 * - (void)beginSheetForDirectory:(NSString*)path file:(NSString*)name types:(NSArray*)fileTypes
	 * modalForWindow:(NSWindow*)docWindow modalDelegate:(null)delegate
	 * didEndSelector:(null)didEndSelector contextInfo:(void*)contextInfo; (Argument delegate cannot be converted)
	 */
	abstract fun beginSheetForDirectory_file_types_modalForWindow_modalDelegate_didEndSelector_contextInfo(
		path: String?,
		name: String?,
		fileTypes: NSArray?,
		docWindow: NSWindow?,
		delegate: NSObject?,
		didEndSelector: Selector?,
		contextInfo: ID?
	)
	/**
	 * *from NSOpenPanelRuntime native declaration : :43*<br></br>
	 * Conversion Error : /// Original signature : `void beginForDirectory(NSString*, NSString*, NSArray*, null, null, void*)`<br></br>
	 * - (void)beginForDirectory:(NSString*)path file:(NSString*)name types:(NSArray*)fileTypes modelessDelegate:(null)delegate didEndSelector:(null)didEndSelector contextInfo:(void*)contextInfo; (Argument delegate cannot be converted)
	 */
	/**
	 * Original signature : `NSInteger runModalForDirectory(NSString*, NSString*, NSArray*)`<br></br>
	 * *from NSOpenPanelRuntime native declaration : :46*
	 */
	abstract fun runModalForDirectory_file_types(path: String?, name: String?, fileTypes: NSArray?): Int

	/**
	 * Original signature : `NSInteger runModalForTypes(NSArray*)`<br></br>
	 * *from NSOpenPanelRuntime native declaration : :47*
	 */
	abstract fun runModalForTypes(fileTypes: NSArray?): Int

	companion object {
		val CLASS: _Class = org.rococoa.Rococoa.createClass("NSOpenPanel", _Class::class.java)

		fun openPanel(): NSOpenPanel? {
			return CLASS.openPanel()
		}
	}
}
