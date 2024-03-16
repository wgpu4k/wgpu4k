package darwin 

import org.rococoa.ID
import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSInteger

/// <i>native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:30</i>
abstract class NSSavePanel : NSPanel() {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `NSSavePanel* savePanel()`<br></br>
		 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:67*
		 */
		open fun savePanel(): NSSavePanel?
	}

	/**
	 * Original signature : `NSURL* URL()`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:69*
	 */
	abstract fun URL(): NSURL?


	abstract fun directoryURL(): NSURL?


	abstract fun setDirectoryURL(path: NSURL?)

	/**
	 * A file specified in the save panel is saved with the designated filename and this file type as an extension. This method is equivalent to calling allowedFileTypes and returning the first element of the list of allowed types, or nil if there are none.  It is preferred to use 'allowedFileTypes' over this method.<br></br>
	 * Original signature : `NSString* requiredFileType()`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:78*
	 */
	abstract fun requiredFileType(): String?

	/**
	 * Original signature : `void setRequiredFileType(NSString*)`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:79*
	 */
	abstract fun setRequiredFileType(type: String?)

	/**
	 * An array NSStrings specifying the file types the user can save the file as. The fil type can be a common file extension, or a UTI. A nil value indicates that any file type can be used. If the array is not nil and the array contains no items, an exception will be raised. If the user specifies a type not in the array, and 'allowsOtherFileTypes' is YES, they will be presented with another dialog when prompted to save. The default value is 'nil'.<br></br>
	 * Original signature : `NSArray* allowedFileTypes()`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:84*
	 */
	abstract fun allowedFileTypes(): NSArray?

	/**
	 * Original signature : `void setAllowedFileTypes(NSArray*)`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:85*
	 */
	abstract fun setAllowedFileTypes(types: NSArray?)

	/**
	 * Returns a BOOL value that indicates whether the receiver allows the user to save files with an extension that‚Äôs not in the list of 'allowedFileTypes'.<br></br>
	 * Original signature : `BOOL allowsOtherFileTypes()`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:89*
	 */
	abstract fun allowsOtherFileTypes(): Boolean

	/**
	 * Original signature : `void setAllowsOtherFileTypes(BOOL)`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:90*
	 */
	abstract fun setAllowsOtherFileTypes(flag: Boolean)

	/**
	 * Original signature : `NSView* accessoryView()`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:93*
	 */
	abstract fun accessoryView(): NSView?

	/**
	 * Original signature : `void setAccessoryView(NSView*)`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:94*
	 */
	abstract fun setAccessoryView(view: NSView?)

	/**
	 * Original signature : `BOOL isExpanded()`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:101*
	 */
	abstract fun isExpanded(): Boolean

	/**
	 * Original signature : `BOOL canCreateDirectories()`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:104*
	 */
	abstract fun canCreateDirectories(): Boolean

	/**
	 * Original signature : `void setCanCreateDirectories(BOOL)`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:105*
	 */
	abstract fun setCanCreateDirectories(flag: Boolean)

	/**
	 * Original signature : `BOOL canSelectHiddenExtension()`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:109*
	 */
	abstract fun canSelectHiddenExtension(): Boolean

	/**
	 * Original signature : `void setCanSelectHiddenExtension(BOOL)`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:111*
	 */
	abstract fun setCanSelectHiddenExtension(flag: Boolean)

	/**
	 * Original signature : `BOOL isExtensionHidden()`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:112*
	 */
	abstract fun isExtensionHidden(): Boolean

	/**
	 * Original signature : `void setExtensionHidden(BOOL)`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:113*
	 */
	abstract fun setExtensionHidden(flag: Boolean)

	/**
	 * Original signature : `BOOL treatsFilePackagesAsDirectories()`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:115*
	 */
	abstract fun treatsFilePackagesAsDirectories(): Boolean

	/**
	 * Original signature : `void setTreatsFilePackagesAsDirectories(BOOL)`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:116*
	 */
	abstract fun setTreatsFilePackagesAsDirectories(flag: Boolean)

	/**
	 * Original signature : `NSString* prompt()`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:118*
	 */
	abstract fun prompt(): String?

	/**
	 * Original signature : `void setPrompt(NSString*)`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:119*
	 */
	abstract fun setPrompt(prompt: String?)

	/**
	 * Original signature : `NSString* nameFieldLabel()`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:125*
	 */
	abstract fun nameFieldLabel(): String?

	/**
	 * Original signature : `void setNameFieldLabel(NSString*)`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:126*
	 */
	abstract fun setNameFieldLabel(label: String?)

	/**
	 * Original signature : `NSString* message()`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:130*
	 */
	abstract fun message(): String?

	/**
	 * Original signature : `void setMessage(NSString*)`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:131*
	 */
	abstract fun setMessage(message: String?)

	/**
	 * Original signature : `void validateVisibleColumns()`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:134*
	 */
	abstract fun validateVisibleColumns()

	/**
	 * A method that was deprecated in Mac OS 10.3.  -[NSSavePanel selectText:] does nothing.<br></br>
	 * Original signature : `void selectText(id)`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:138*
	 */
	abstract fun selectText(sender: ID?)

	/**
	 * Original signature : `void ok(id)`<br></br>
	 * *from NSSavePanelRuntime native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:145*
	 */
	abstract fun ok(sender: ID?)

	/**
	 * Original signature : `void cancel(id)`<br></br>
	 * *from NSSavePanelRuntime native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:146*
	 */
	abstract fun cancel(sender: ID?)

	/**
	 * *from NSSavePanelRuntime native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:152*<br></br>
	 * Conversion Error : / **<br></br>
	 * * didEndSelector should have the signature:<br></br>
	 * * - (void)savePanelDidEnd:(NSSavePanel *)sheet returnCode:(NSInteger)returnCode contextInfo:(void *)contextInfo;<br></br>
	 * * The value passed as returnCode will be either NSCancelButton or NSOKButton.<br></br>
	 * * Original signature : `void beginSheetForDirectory(NSString*, NSString*, NSWindow*, id, null, void*)`<br></br>
	 * * /<br></br>
	 * - (void)beginSheetForDirectory:(NSString*)path file:(NSString*)name modalForWindow:(NSWindow*)docWindow modalDelegate:(id)delegate didEndSelector:(null)didEndSelector contextInfo:(void*)contextInfo; (Argument didEndSelector cannot be converted)
	 */
	abstract fun beginSheetForDirectory_file_modalForWindow_modalDelegate_didEndSelector_contextInfo(
		path: String?,
		name: String?,
		docWindow: NSWindow?,
		delegate: ID?,
		didEndSelector: org.rococoa.Selector?,
		contextInfo: ID?
	)

	fun beginSheetForDirectory(
		path: String?,
		name: String?,
		docWindow: NSWindow?,
		delegate: ID?,
		didEndSelector: org.rococoa.Selector?,
		contextInfo: ID?
	) {
		this.beginSheetForDirectory_file_modalForWindow_modalDelegate_didEndSelector_contextInfo(
			path,
			name,
			docWindow,
			delegate,
			didEndSelector,
			contextInfo
		)
	}

	/**
	 * Original signature : `NSInteger runModalForDirectory(NSString*, NSString*)`<br></br>
	 * *from NSSavePanelRuntime native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:154*
	 */
	abstract fun runModalForDirectory_file(path: String?, name: String?): NSInteger?

	fun runModal(directory: String?, file: String?): NSInteger? {
		return this.runModalForDirectory_file(directory, file)
	}

	/**
	 * Original signature : `NSInteger runModal()`<br></br>
	 * *from NSSavePanelRuntime native declaration : /System/Library/Frameworks/AppKit.framework/Headers/NSSavePanel.h:155*
	 */
	abstract fun runModal(): NSInteger?

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSSavePanel", _Class::class.java)

		fun savePanel(): NSSavePanel? {
			return CLASS.savePanel()
		}
	}
}
