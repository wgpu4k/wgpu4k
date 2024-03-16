package darwin

import com.sun.jna.Pointer
import org.rococoa.Foundation
import org.rococoa.ID
import org.rococoa.ObjCClass
import org.rococoa.Rococoa
import org.rococoa.cocoa.CGFloat
import org.rococoa.cocoa.foundation.*

/// <i>native declaration : :119</i>
abstract class NSWindow : NSResponder() {
	/// enum values
	interface NSWindowLevel {
		companion object {
			const val NSNormalWindowLevel: Int = 0
			const val NSFloatingWindowLevel: Int = 3
			const val NSSubmenuWindowLevel: Int = 3
			const val NSTornOffMenuWindowLevel: Int = 3
			const val NSMainMenuWindowLevel: Int = 24
			const val NSStatusWindowLevel: Int = 25
			const val NSModalPanelWindowLevel: Int = 8
			const val NSPopUpMenuWindowLevel: Int = 101
			const val NSScreenSaverWindowLevel: Int = 1000
		}
	}

	/// enum values
	interface NSWindowCollectionBehavior {
		companion object {
			const val NSWindowCollectionBehaviorManaged: Int =
				1 shl 2 // participates in spaces, exposé.  Default behavior if windowLevel == NSNormalWindowLevel
			const val NSWindowCollectionBehaviorTransient: Int =
				1 shl 3 // floats in spaces, hidden by exposé.  Default behavior if windowLevel != NSNormalWindowLevel
			const val NSWindowCollectionBehaviorStationary: Int =
				1 shl 4 // unaffected by exposé.  Stays visible and stationary, like desktop window

			const val NSWindowCollectionBehaviorParticipatesInCycle: Int =
				1 shl 5 // default behavior if windowLevel == NSNormalWindowLevel
			const val NSWindowCollectionBehaviorIgnoresCycle: Int =
				1 shl 6 // default behavior if windowLevel != NSNormalWindowLevel

			const val NSWindowNumberListAllApplications: Int = 1 shl 0
			const val NSWindowNumberListAllSpaces: Int = 1 shl 4

			const val NSWindowCollectionBehaviorFullScreenPrimary: Int =
				1 shl 7 // the frontmost window with this collection behavior will be the fullscreen window.
			const val NSWindowCollectionBehaviorFullScreenAuxiliary: Int =
				1 shl 8 // windows with this collection behavior can be shown with the fullscreen window.
		}
	}

	/// enum values
	interface NSSelectionDirection {
		companion object {
			const val NSDirectSelection: Int = 0
			const val NSSelectingNext: Int = 1
			const val NSSelectingPrevious: Int = 2
		}
	}

	/// enum values
	interface NSWindowButton {
		companion object {
			const val NSWindowCloseButton: Int = 0
			const val NSWindowMiniaturizeButton: Int = 1
			const val NSWindowZoomButton: Int = 2
			const val NSWindowToolbarButton: Int = 3
			const val NSWindowDocumentIconButton: Int = 4
		}
	}

	/// enum values
	interface NSWindowToolbarStyle {
		companion object {
			// The default value. The style will be determined by the window's given configuration
			const val NSWindowToolbarStyleAutomatic: Int = 0

			// The toolbar will appear below the window title
			const val NSWindowToolbarStyleExpanded: Int = 1

			// The toolbar will appear below the window title and the items in the toolbar will attempt to have equal widths when possible
			const val NSWindowToolbarStylePreference: Int = 2

			// The window title will appear inline with the toolbar when visible
			const val NSWindowToolbarStyleUnified: Int = 3

			// Same as NSWindowToolbarStyleUnified, but with reduced margins in the toolbar allowing more focus to be on the contents of the window
			const val NSWindowToolbarStyleUnifiedCompact: Int = 4
		}
	}

	/// enum values
	interface NSTitlebarSeparatorStyle {
		companion object {
			const val NSTitlebarSeparatorStyleAutomatic: Int = 0
			const val NSTitlebarSeparatorStyleNone: Int = 1
			const val NSTitlebarSeparatorStyleLine: Int = 2
			const val NSTitlebarSeparatorStyleShadow: Int = 3
		}
	}

	interface _Class : ObjCClass {
		/**
		 * *native declaration : :217*<br></br>
		 * Conversion Error : NSRect
		 */
		fun contentRectForFrameRect_styleMask(windowFrame: NSRect?, windowStyle: NSUInteger?): NSRect

		/**
		 * *native declaration : :218*<br></br>
		 * Conversion Error : NSRect
		 */
		fun frameRectForContentRect_styleMask(cRect: NSRect?, aStyle: NSUInteger?): NSRect

		/**
		 * Original signature : `CGFloat minFrameWidthWithTitle(NSString*, NSUInteger)`<br></br>
		 * *native declaration : :219*
		 */
		fun minFrameWidthWithTitle_styleMask(aTitle: String?, aStyle: NSUInteger?): CGFloat

		/**
		 * Original signature : `defaultDepthLimit()`<br></br>
		 * *native declaration : :220*
		 */
		fun defaultDepthLimit(): NSObject?

		/**
		 * Original signature : `void removeFrameUsingName(NSString*)`<br></br>
		 * *native declaration : :473*
		 */
		fun removeFrameUsingName(name: String?)

		/**
		 * Original signature : `void menuChanged(NSMenu*)`<br></br>
		 * *native declaration : :504*
		 */
		fun menuChanged(menu: NSMenu?)

		/**
		 * Original signature : `NSButton* standardWindowButton(NSWindowButton, NSUInteger)`<br></br>
		 * *native declaration : :513*
		 */
		fun standardWindowButton_forStyleMask(b: Int, styleMask: Int): NSButton?

		/**
		 * @param automatic A Boolean value that indicates whether the app can automatically organize windows into
		 * tabs.
		 */
		fun setAllowsAutomaticWindowTabbing(automatic: Boolean)

		/**
		 * @return A Boolean value that indicates whether the app can automatically organize windows into tabs.
		 */
		fun allowsAutomaticWindowTabbing(): Boolean
	}

	interface Delegate {
		/**
		 * Tells the delegate that the user has attempted to close a window or the window has received a performClose:
		 * message. This method is optional.
		 *
		 *
		 * This method may not always be called during window closing. Specifically, this method is not called when a
		 * user quits an application. You can find additional information on application termination in Graceful
		 * Application Termination.
		 *
		 * @param sender
		 * @return
		 */
		fun windowShouldClose(sender: NSWindow?): Boolean

		/**
		 * Tells the delegate that the user has attempted to close a window or the window has received a performClose:
		 * message. This method is optional.
		 *
		 *
		 * You can retrieve the NSWindow object in question by sending object to notification.
		 *
		 * @param notification
		 */
		fun windowWillClose(notification: NSNotification?)

		/**
		 * Notifies the delegate that the window is about to open a sheet.
		 *
		 * @param notification
		 */
		fun windowWillBeginSheet(notification: NSNotification?)

		/**
		 * Informs the delegate that the window has become the key window. This method is optional.
		 *
		 *
		 * You can retrieve the window object in question by sending object to notification.
		 *
		 * @param notification
		 */
		fun windowDidBecomeKey(notification: NSNotification?)

		fun windowDidBecomeMain(notification: NSNotification?)

		/**
		 * Informs the delegate that the window has resigned key window status. This method is optional.
		 *
		 *
		 * You can retrieve the window object in question by sending object to notification.
		 *
		 * @param notification
		 */
		fun windowDidResignKey(notification: NSNotification?)

		fun windowDidResignMain(notification: NSNotification?)
	}

	/**
	 * *native declaration : :227*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :228*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Original signature : `NSString* title()`<br></br>
	 * *native declaration : :230*
	 */
	abstract fun title(): String?

	/**
	 * Original signature : `void setTitle(NSString*)`<br></br>
	 * *native declaration : :231*
	 */
	abstract fun setTitle(aString: String?)

	/**
	 * @return A secondary line of text that appears in the title bar of the window.
	 */
	abstract fun subtitle(): String?

	/**
	 * When this property is the empty string, the system removes the subtitle from the window layout.
	 *
	 * @param aString A secondary line of text that appears in the title bar of the window.
	 */
	abstract fun setSubtitle(aString: String?)

	/**
	 * The default value is NSTitlebarSeparatorStyleAutomatic. Changing this value overrides any preference by
	 * NSSplitViewItem.
	 *
	 * @param style The type of separator that the UI displays between the window’s titlebar and its content.
	 */
	abstract fun setTitlebarSeparatorStyle(style: Int)

	/**
	 * setRepresentedURL:<br></br> If url is not nil and its path is not empty, the window will show a document icon in the
	 * titlebar.  <br></br> If the url represents a filename or other resource with a known icon, that icon will be used as
	 * the document icon.  Otherwise the default document icon will be used.  The icon can be customized using
	 * [[NSWindow standardWindowButton:NSWindowDocumentIconButton] setImage:customImage].  If url is not nil and its
	 * path is not empty, the window will have a pop-up menu which can be shown via command-click on the area containing
	 * the document icon and title.  By default, this menu will display the path components of the url.  The presence
	 * and contents of this menu can be controlled by the delegate method window:shouldPopUpDocumentPathMenu:If the url
	 * is nil or has an empty path, the window will not show a document icon and will not have a pop-up menu available
	 * via command-click.<br></br> Original signature : `void setRepresentedURL(NSURL*)`<br></br>
	 * *native declaration : :237*
	 */
	abstract fun setRepresentedURL(url: NSURL?)

	/**
	 * Original signature : `NSURL* representedURL()`<br></br>
	 * *native declaration : :238*
	 */
	abstract fun representedURL(): NSURL?

	/**
	 * Original signature : `NSString* representedFilename()`<br></br>
	 * *native declaration : :240*
	 */
	abstract fun representedFilename(): String?

	/**
	 * Original signature : `void setRepresentedFilename(NSString*)`<br></br>
	 * *native declaration : :241*
	 */
	abstract fun setRepresentedFilename(aString: String?)

	/**
	 * Original signature : `void setTitleWithRepresentedFilename(NSString*)`<br></br>
	 * *native declaration : :242*
	 */
	abstract fun setTitleWithRepresentedFilename(filename: String?)

	/**
	 * Original signature : `BOOL isExcludedFromWindowsMenu()`<br></br>
	 * *native declaration : :244*
	 */
	/**
	 * Original signature : `void setExcludedFromWindowsMenu(BOOL)`<br></br>
	 * *native declaration : :243*
	 */
	abstract var isExcludedFromWindowsMenu: Boolean

	/**
	 * Original signature : `void setContentView(NSView*)`<br></br>
	 * *native declaration : :245*
	 */
	abstract fun setContentView(aView: NSView?)

	/**
	 * Original signature : `id contentView()`<br></br>
	 * *native declaration : :246*
	 */
	abstract fun contentView(): NSView?

	/**
	 * Original signature : `void setDelegate(id)`<br></br>
	 * *native declaration : :247*
	 */
	abstract fun setDelegate(anObject: ID?)

	/**
	 * Original signature : `id delegate()`<br></br>
	 * *native declaration : :248*
	 */
	abstract fun delegate(): ID?

	/**
	 * Original signature : `NSInteger windowNumber()`<br></br>
	 * *native declaration : :249*
	 */
	abstract fun windowNumber(): NSInteger?

	/**
	 * Original signature : `NSUInteger styleMask()`<br></br>
	 * *native declaration : :250*
	 */
	abstract fun styleMask(): NSUInteger?

	abstract fun setStyleMask(mask: NSUInteger?)

	/**
	 * Original signature : `NSText* fieldEditor(BOOL, id)`<br></br>
	 * *native declaration : :251*
	 */
	abstract fun fieldEditor_forObject(createFlag: Boolean, anObject: NSObject?): NSText?

	/**
	 * Original signature : `void endEditingFor(id)`<br></br>
	 * *native declaration : :252*
	 */
	abstract fun endEditingFor(anObject: NSObject?)
	/**
	 * *native declaration : :254*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :255*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :256*<br></br>
	 * Conversion Error : NSSize
	 */
	/**
	 * *native declaration : :257*<br></br>
	 * Conversion Error : /// Original signature : `void setFrameOrigin(null)`<br></br> -
	 * (void)setFrameOrigin:(null)aPoint; (Argument aPoint cannot be converted)
	 */
	abstract fun setFrameOrigin(aPoint: NSPoint?)

	/**
	 * *native declaration : :258*<br></br>
	 * Conversion Error : /// Original signature : `void setFrameTopLeftPoint(null)`<br></br> -
	 * (void)setFrameTopLeftPoint:(null)aPoint; (Argument aPoint cannot be converted)
	 */
	abstract fun setFrameTopLeftPoint(aPoint: NSPoint?)

	/**
	 * *native declaration : :259*<br></br>
	 * Conversion Error : /// Original signature : `cascadeTopLeftFromPoint(null)`<br></br> -
	 * (null)cascadeTopLeftFromPoint:(null)topLeftPoint; (Argument topLeftPoint cannot be converted)
	 */
	abstract fun cascadeTopLeftFromPoint(topLeftPoint: NSPoint?): NSPoint?

	/**
	 * *native declaration : :260*<br></br>
	 * Conversion Error : NSRect
	 */
	abstract fun frame(): NSRect

	/**
	 * *native declaration : :265*<br></br>
	 * Conversion Error : NSRect
	 */
	abstract fun setFrame_display_animate(frameRect: NSRect?, displayFlag: Boolean, animateFlag: Boolean)
	/**
	 * *native declaration : :268*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * show/hide resize corner (does not affect whether window is resizable)<br></br> Original signature : `void
	 * setShowsResizeIndicator(BOOL)`<br></br>
	 * *native declaration : :271*
	 */
	abstract fun setShowsResizeIndicator(show: Boolean)

	/**
	 * Original signature : `BOOL showsResizeIndicator()`<br></br>
	 * *native declaration : :272*
	 */
	abstract fun showsResizeIndicator(): Boolean
	/**
	 * *native declaration : :274*<br></br>
	 * Conversion Error : NSSize
	 */
	/**
	 * *native declaration : :275*<br></br>
	 * Conversion Error : NSSize
	 */
	/**
	 * *native declaration : :276*<br></br>
	 * Conversion Error : NSSize
	 */
	/**
	 * *native declaration : :277*<br></br>
	 * Conversion Error : NSSize
	 */
	/**
	 * *native declaration : :280*<br></br>
	 * Conversion Error : NSSize
	 */
	/**
	 * *native declaration : :281*<br></br>
	 * Conversion Error : NSSize
	 */
	/**
	 * *native declaration : :282*<br></br>
	 * Conversion Error : NSSize
	 */
	/**
	 * *native declaration : :283*<br></br>
	 * Conversion Error : NSSize
	 */
	/**
	 * Original signature : `void useOptimizedDrawing(BOOL)`<br></br>
	 * *native declaration : :286*
	 */
	abstract fun useOptimizedDrawing(flag: Boolean)

	/**
	 * Original signature : `void disableFlushWindow()`<br></br>
	 * *native declaration : :287*
	 */
	abstract fun disableFlushWindow()

	/**
	 * Original signature : `void enableFlushWindow()`<br></br>
	 * *native declaration : :288*
	 */
	abstract fun enableFlushWindow()

	/**
	 * Original signature : `BOOL isFlushWindowDisabled()`<br></br>
	 * *native declaration : :289*
	 */
	abstract val isFlushWindowDisabled: Boolean

	/**
	 * Original signature : `void flushWindow()`<br></br>
	 * *native declaration : :290*
	 */
	abstract fun flushWindow()

	/**
	 * Original signature : `void flushWindowIfNeeded()`<br></br>
	 * *native declaration : :291*
	 */
	abstract fun flushWindowIfNeeded()

	/**
	 * Original signature : `void setViewsNeedDisplay(BOOL)`<br></br>
	 * *native declaration : :292*
	 */
	abstract fun setViewsNeedDisplay(flag: Boolean)

	/**
	 * Original signature : `BOOL viewsNeedDisplay()`<br></br>
	 * *native declaration : :293*
	 */
	abstract fun viewsNeedDisplay(): Boolean

	/**
	 * Original signature : `void displayIfNeeded()`<br></br>
	 * *native declaration : :294*
	 */
	abstract fun displayIfNeeded()

	/**
	 * Original signature : `void display()`<br></br>
	 * *native declaration : :295*
	 */
	abstract fun display()

	/**
	 * Original signature : `BOOL isAutodisplay()`<br></br>
	 * *native declaration : :297*
	 */
	/**
	 * Original signature : `void setAutodisplay(BOOL)`<br></br>
	 * *native declaration : :296*
	 */
	abstract var isAutodisplay: Boolean

	/**
	 * Original signature : `BOOL preservesContentDuringLiveResize()`<br></br>
	 * *native declaration : :300*
	 */
	abstract fun preservesContentDuringLiveResize(): Boolean

	/**
	 * Original signature : `void setPreservesContentDuringLiveResize(BOOL)`<br></br>
	 * *native declaration : :301*
	 */
	abstract fun setPreservesContentDuringLiveResize(flag: Boolean)

	/**
	 * Original signature : `void update()`<br></br>
	 * *native declaration : :304*
	 */
	abstract fun update()

	/**
	 * Original signature : `BOOL makeFirstResponder(NSResponder*)`<br></br>
	 * *native declaration : :305*
	 */
	abstract fun makeFirstResponder(aResponder: NSResponder?): Boolean

	/**
	 * Original signature : `NSResponder* firstResponder()`<br></br>
	 * *native declaration : :306*
	 */
	abstract fun firstResponder(): NSResponder?

	/**
	 * Original signature : `NSInteger resizeFlags()`<br></br>
	 * *native declaration : :307*
	 */
	abstract fun resizeFlags(): Int

	/**
	 * Original signature : `void close()`<br></br>
	 * *native declaration : :309*
	 */
	abstract fun close()

	/**
	 * Original signature : `BOOL isReleasedWhenClosed()`<br></br>
	 * *native declaration : :311*
	 */
	/**
	 * Original signature : `void setReleasedWhenClosed(BOOL)`<br></br>
	 * *native declaration : :310*
	 */
	abstract var isReleasedWhenClosed: Boolean

	/**
	 * Original signature : `void miniaturize(id)`<br></br>
	 * *native declaration : :312*
	 */
	abstract fun miniaturize(sender: ID?)

	/**
	 * Original signature : `void deminiaturize(id)`<br></br>
	 * *native declaration : :313*
	 */
	abstract fun deminiaturize(sender: ID?)

	/**
	 * Original signature : `BOOL isZoomed()`<br></br>
	 * *native declaration : :314*
	 */
	abstract val isZoomed: Boolean

	/**
	 * Original signature : `void zoom(id)`<br></br>
	 * *native declaration : :315*
	 */
	abstract fun zoom(sender: ID?)

	/**
	 * Original signature : `BOOL isMiniaturized()`<br></br>
	 * *native declaration : :316*
	 */
	abstract val isMiniaturized: Boolean
	/**
	 * *native declaration : :317*<br></br>
	 * Conversion Error : /// Original signature : `BOOL tryToPerform(null, id)`<br></br>
	 * - (BOOL)tryToPerform:(null)anAction with:(id)anObject; (Argument anAction cannot be converted)
	 */
	/**
	 * Original signature : `id validRequestorForSendType(NSString*, NSString*)`<br></br>
	 * *native declaration : :318*
	 */
	abstract fun validRequestorForSendType_returnType(sendType: Pointer?, returnType: Pointer?): NSObject?

	/**
	 * Original signature : `void setBackgroundColor(NSColor*)`<br></br>
	 * *native declaration : :319*
	 */
	abstract fun setBackgroundColor(color: NSColor?)

	/**
	 * Original signature : `NSColor* backgroundColor()`<br></br>
	 * *native declaration : :320*
	 */
	abstract fun backgroundColor(): NSColor?
	/**
	 * *native declaration : :323*<br></br>
	 * Conversion Error : /// Original signature : `void setContentBorderThickness(CGFloat, null)`<br></br>
	 * - (void)setContentBorderThickness:(CGFloat)thickness forEdge:(null)edge; (Argument edge cannot be converted)
	 */
	/**
	 * *native declaration : :324*<br></br>
	 * Conversion Error : /// Original signature : `CGFloat contentBorderThicknessForEdge(null)`<br></br>
	 * - (CGFloat)contentBorderThicknessForEdge:(null)edge; (Argument edge cannot be converted)
	 */
	/**
	 * *native declaration : :326*<br></br>
	 * Conversion Error : /// Original signature : `void setAutorecalculatesContentBorderThickness(BOOL, null)`<br></br>
	 * - (void)setAutorecalculatesContentBorderThickness:(BOOL)flag forEdge:(null)edge; (Argument edge cannot be converted)
	 */
	/**
	 * *native declaration : :327*<br></br>
	 * Conversion Error : /// Original signature : `BOOL autorecalculatesContentBorderThicknessForEdge(null)`<br></br>
	 * - (BOOL)autorecalculatesContentBorderThicknessForEdge:(null)edge; (Argument edge cannot be converted)
	 */

	/**
	 * Original signature : `BOOL isMovableByWindowBackground()`<br></br>
	 * *native declaration : :332*
	 */
	/**
	 * Original signature : `void setMovableByWindowBackground(BOOL)`<br></br>
	 * *native declaration : :331*
	 */
	abstract var isMovableByWindowBackground: Boolean

	/**
	 * Original signature : `void setHidesOnDeactivate(BOOL)`<br></br>
	 * *native declaration : :335*
	 */
	abstract fun setHidesOnDeactivate(flag: Boolean)

	/**
	 * Original signature : `BOOL hidesOnDeactivate()`<br></br>
	 * *native declaration : :336*
	 */
	abstract fun hidesOnDeactivate(): Boolean

	/**
	 * indicate whether a window can be hidden during -[NSApplication hide:].  Default is YES<br></br> Original signature :
	 * `void setCanHide(BOOL)`<br></br>
	 * *native declaration : :339*
	 */
	abstract fun setCanHide(flag: Boolean)

	/**
	 * Original signature : `BOOL canHide()`<br></br>
	 * *native declaration : :340*
	 */
	abstract fun canHide(): Boolean

	/**
	 * Original signature : `void center()`<br></br>
	 * *native declaration : :342*
	 */
	abstract fun center()

	/**
	 * Original signature : `void makeKeyAndOrderFront(id)`<br></br>
	 * *native declaration : :343*
	 */
	abstract fun makeKeyAndOrderFront(sender: ID?)

	/**
	 * Original signature : `void orderFront(id)`<br></br>
	 * *native declaration : :344*
	 */
	abstract fun orderFront(sender: ID?)

	/**
	 * Original signature : `void orderBack(id)`<br></br>
	 * *native declaration : :345*
	 */
	abstract fun orderBack(sender: ID?)

	/**
	 * Original signature : `void orderOut(id)`<br></br>
	 * *native declaration : :346*
	 */
	abstract fun orderOut(sender: ID?)
	/**
	 * *native declaration : :347*<br></br>
	 * Conversion Error : /// Original signature : `void orderWindow(null, NSInteger)`<br></br>
	 * - (void)orderWindow:(null)place relativeTo:(NSInteger)otherWin; (Argument place cannot be converted)
	 */
	/**
	 * Original signature : `void orderFrontRegardless()`<br></br>
	 * *native declaration : :348*
	 */
	abstract fun orderFrontRegardless()

	/**
	 * Original signature : `void setMiniwindowImage(NSImage*)`<br></br>
	 * *native declaration : :350*
	 */
	abstract fun setMiniwindowImage(image: NSImage?)

	/**
	 * Original signature : `void setMiniwindowTitle(NSString*)`<br></br>
	 * *native declaration : :351*
	 */
	abstract fun setMiniwindowTitle(title: String?)

	/**
	 * Original signature : `NSImage* miniwindowImage()`<br></br>
	 * *native declaration : :352*
	 */
	abstract fun miniwindowImage(): NSImage?

	/**
	 * Original signature : `NSString* miniwindowTitle()`<br></br>
	 * *native declaration : :353*
	 */
	abstract fun miniwindowTitle(): String?

	/**
	 * Original signature : `NSDockTile* dockTile()`<br></br>
	 * *native declaration : :356*
	 */
	abstract fun dockTile(): NSDockTile?

	/**
	 * Original signature : `BOOL isDocumentEdited()`<br></br>
	 * *native declaration : :360*
	 */
	/**
	 * Original signature : `void setDocumentEdited(BOOL)`<br></br>
	 * *native declaration : :359*
	 */
	abstract var isDocumentEdited: Boolean

	/**
	 * Original signature : `BOOL isVisible()`<br></br>
	 * *native declaration : :361*
	 */
	abstract val isVisible: Boolean

	/**
	 * Original signature : `BOOL isKeyWindow()`<br></br>
	 * *native declaration : :362*
	 */
	abstract val isKeyWindow: Boolean

	/**
	 * Original signature : `BOOL isMainWindow()`<br></br>
	 * *native declaration : :363*
	 */
	abstract val isMainWindow: Boolean

	/**
	 * Original signature : `BOOL canBecomeKeyWindow()`<br></br>
	 * *native declaration : :364*
	 */
	abstract fun canBecomeKeyWindow(): Boolean

	/**
	 * Original signature : `BOOL canBecomeMainWindow()`<br></br>
	 * *native declaration : :365*
	 */
	abstract fun canBecomeMainWindow(): Boolean

	/**
	 * Original signature : `void makeKeyWindow()`<br></br>
	 * *native declaration : :366*
	 */
	abstract fun makeKeyWindow()

	/**
	 * Original signature : `void makeMainWindow()`<br></br>
	 * *native declaration : :367*
	 */
	abstract fun makeMainWindow()

	/**
	 * Original signature : `void becomeKeyWindow()`<br></br>
	 * *native declaration : :368*
	 */
	abstract fun becomeKeyWindow()

	/**
	 * Original signature : `void resignKeyWindow()`<br></br>
	 * *native declaration : :369*
	 */
	abstract fun resignKeyWindow()

	/**
	 * Original signature : `void becomeMainWindow()`<br></br>
	 * *native declaration : :370*
	 */
	abstract fun becomeMainWindow()

	/**
	 * Original signature : `void resignMainWindow()`<br></br>
	 * *native declaration : :371*
	 */
	abstract fun resignMainWindow()

	/**
	 * Original signature : `BOOL worksWhenModal()`<br></br>
	 * *native declaration : :373*
	 */
	abstract fun worksWhenModal(): Boolean
	/**
	 * *native declaration : :374*<br></br>
	 * Conversion Error : /// Original signature : `convertBaseToScreen(null)`<br></br>
	 * - (null)convertBaseToScreen:(null)aPoint; (Argument aPoint cannot be converted)
	 */
	/**
	 * *native declaration : :375*<br></br>
	 * Conversion Error : /// Original signature : `convertScreenToBase(null)`<br></br>
	 * - (null)convertScreenToBase:(null)aPoint; (Argument aPoint cannot be converted)
	 */
	/**
	 * Original signature : `void performClose(id)`<br></br>
	 * *native declaration : :376*
	 */
	abstract fun performClose(sender: ID?)

	/**
	 * Original signature : `void performMiniaturize(id)`<br></br>
	 * *native declaration : :377*
	 */
	abstract fun performMiniaturize(sender: ID?)

	/**
	 * Original signature : `void performZoom(id)`<br></br>
	 * *native declaration : :378*
	 */
	abstract fun performZoom(sender: ID?)

	/**
	 * Original signature : `NSInteger gState()`<br></br>
	 * *native declaration : :379*
	 */
	abstract fun gState(): Int

	/**
	 * Original signature : `BOOL isOneShot()`<br></br>
	 * *native declaration : :381*
	 */
	/**
	 * Original signature : `void setOneShot(BOOL)`<br></br>
	 * *native declaration : :380*
	 */
	abstract var isOneShot: Boolean
	/**
	 * *native declaration : :382*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :383*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Original signature : `void print(id)`<br></br>
	 * *native declaration : :384*
	 */
	abstract fun print(sender: ID?)

	/**
	 * Original signature : `void disableCursorRects()`<br></br>
	 * *native declaration : :386*
	 */
	abstract fun disableCursorRects()

	/**
	 * Original signature : `void enableCursorRects()`<br></br>
	 * *native declaration : :387*
	 */
	abstract fun enableCursorRects()

	/**
	 * Original signature : `void discardCursorRects()`<br></br>
	 * *native declaration : :388*
	 */
	abstract fun discardCursorRects()

	/**
	 * Original signature : `BOOL areCursorRectsEnabled()`<br></br>
	 * *native declaration : :389*
	 */
	abstract fun areCursorRectsEnabled(): Boolean

	/**
	 * Original signature : `void invalidateCursorRectsForView(NSView*)`<br></br>
	 * *native declaration : :390*
	 */
	abstract fun invalidateCursorRectsForView(aView: Pointer?)

	/**
	 * Original signature : `void resetCursorRects()`<br></br>
	 * *native declaration : :391*
	 */
	abstract fun resetCursorRects()

	/**
	 * Original signature : `void setAllowsToolTipsWhenApplicationIsInactive(BOOL)`<br></br>
	 * *native declaration : :394*
	 */
	abstract fun setAllowsToolTipsWhenApplicationIsInactive(allowWhenInactive: Boolean)

	/**
	 * Original signature : `BOOL allowsToolTipsWhenApplicationIsInactive()`<br></br>
	 * *native declaration : :397*
	 */
	abstract fun allowsToolTipsWhenApplicationIsInactive(): Boolean
	/**
	 * *native declaration : :401*<br></br>
	 * Conversion Error : /// Original signature : `void setBackingType(null)`<br></br>
	 * - (void)setBackingType:(null)bufferingType; (Argument bufferingType cannot be converted)
	 */
	/**
	 * Original signature : `backingType()`<br></br>
	 * *native declaration : :402*
	 */
	abstract fun backingType(): NSObject?

	/**
	 * Original signature : `void setLevel(NSInteger)`<br></br>
	 * *native declaration : :403*
	 */
	abstract fun setLevel(newLevel: Int)

	/**
	 * Original signature : `NSInteger level()`<br></br>
	 * *native declaration : :404*
	 */
	abstract fun level(): Int
	/**
	 * *native declaration : :405*<br></br>
	 * Conversion Error : /// Original signature : `void setDepthLimit(null)`<br></br>
	 * - (void)setDepthLimit:(null)limit; (Argument limit cannot be converted)
	 */
	/**
	 * Original signature : `depthLimit()`<br></br>
	 * *native declaration : :406*
	 */
	abstract fun depthLimit(): NSObject?

	/**
	 * Original signature : `void setDynamicDepthLimit(BOOL)`<br></br>
	 * *native declaration : :407*
	 */
	abstract fun setDynamicDepthLimit(flag: Boolean)

	/**
	 * Original signature : `BOOL hasDynamicDepthLimit()`<br></br>
	 * *native declaration : :408*
	 */
	abstract fun hasDynamicDepthLimit(): Boolean

	/**
	 * Original signature : `NSScreen* screen()`<br></br>
	 * *native declaration : :409*
	 */
	abstract fun screen(): Pointer?

	/**
	 * Original signature : `NSScreen* deepestScreen()`<br></br>
	 * *native declaration : :410*
	 */
	abstract fun deepestScreen(): Pointer?

	/**
	 * Original signature : `BOOL canStoreColor()`<br></br>
	 * *native declaration : :411*
	 */
	abstract fun canStoreColor(): Boolean

	/**
	 * Original signature : `void setHasShadow(BOOL)`<br></br>
	 * *native declaration : :412*
	 */
	abstract fun setHasShadow(hasShadow: Boolean)

	/**
	 * Original signature : `BOOL hasShadow()`<br></br>
	 * *native declaration : :413*
	 */
	abstract fun hasShadow(): Boolean

	/**
	 * Original signature : `void invalidateShadow()`<br></br>
	 * *native declaration : :415*
	 */
	abstract fun invalidateShadow()

	/**
	 * Original signature : `void setAlphaValue(CGFloat)`<br></br>
	 * *native declaration : :417*
	 */
	abstract fun setAlphaValue(windowAlpha: CGFloat?)

	/**
	 * Original signature : `CGFloat alphaValue()`<br></br>
	 * *native declaration : :418*
	 */
	abstract fun alphaValue(): CGFloat?

	/**
	 * Original signature : `BOOL isOpaque()`<br></br>
	 * *native declaration : :420*
	 */
	/**
	 * Original signature : `void setOpaque(BOOL)`<br></br>
	 * *native declaration : :419*
	 */
	abstract var isOpaque: Boolean

	/**
	 * -setSharingType: specifies whether the window content can be read and/or written from another process.  The
	 * default sharing type is NSWindowSharingReadOnly, which means other processes can read the window content (eg. for
	 * window capture) but cannot modify it.  If you set your window sharing type to NSWindowSharingNone, so that the
	 * content cannot be captured, your window will also not be able to participate in a number of system services, so
	 * this setting should be used with caution.  If you set your window sharing type to NSWindowSharingReadWrite, other
	 * processes can both read and modify the window content.<br></br> Original signature : `void
	 * setSharingType(NSWindowSharingType)`<br></br>
	 * *native declaration : :426*
	 */
	abstract fun setSharingType(type: Int)

	/**
	 * Original signature : `NSWindowSharingType sharingType()`<br></br>
	 * *native declaration : :427*
	 */
	abstract fun sharingType(): Int

	/**
	 * -setPreferredBackingLocation: sets the preferred location for the window backing store.  In general, you should
	 * not use this API unless indicated by performance measurement.<br></br> Original signature : `void
	 * setPreferredBackingLocation(NSWindowBackingLocation)`<br></br>
	 * *native declaration : :431*
	 */
	abstract fun setPreferredBackingLocation(backingLocation: Int)

	/**
	 * -preferredBackingLocation gets the preferred location for the window backing store.  This may be different from
	 * the actual location.<br></br> Original signature : `NSWindowBackingLocation preferredBackingLocation()`<br></br>
	 * *native declaration : :434*
	 */
	abstract fun preferredBackingLocation(): Int

	/**
	 * -backingLocation gets the current location of the window backing store.<br></br> Original signature :
	 * `NSWindowBackingLocation backingLocation()`<br></br>
	 * *native declaration : :437*
	 */
	abstract fun backingLocation(): Int

	/**
	 * Original signature : `BOOL displaysWhenScreenProfileChanges()`<br></br>
	 * *native declaration : :442*
	 */
	abstract fun displaysWhenScreenProfileChanges(): Boolean

	/**
	 * Original signature : `void setDisplaysWhenScreenProfileChanges(BOOL)`<br></br>
	 * *native declaration : :443*
	 */
	abstract fun setDisplaysWhenScreenProfileChanges(flag: Boolean)

	/**
	 * Original signature : `void disableScreenUpdatesUntilFlush()`<br></br>
	 * *native declaration : :445*
	 */
	abstract fun disableScreenUpdatesUntilFlush()

	/**
	 * This API controls whether the receiver is permitted onscreen before the user has logged in.  This property is off
	 * by default.  Alert panels and windows presented by input managers are examples of windows which should have this
	 * property set.<br></br> Original signature : `BOOL canBecomeVisibleWithoutLogin()`<br></br>
	 * *native declaration : :451*
	 */
	abstract fun canBecomeVisibleWithoutLogin(): Boolean

	/**
	 * Original signature : `void setCanBecomeVisibleWithoutLogin(BOOL)`<br></br>
	 * *native declaration : :452*
	 */
	abstract fun setCanBecomeVisibleWithoutLogin(flag: Boolean)

	/**
	 * Original signature : `void setCollectionBehavior(NSWindowCollectionBehavior)`<br></br>
	 * *native declaration : :455*
	 */
	abstract fun setCollectionBehavior(behavior: Int)

	/**
	 * Original signature : `NSWindowCollectionBehavior collectionBehavior()`<br></br>
	 * *native declaration : :456*
	 */
	abstract fun collectionBehavior(): Int

	/**
	 * -setCanBeVisibleOnAllSpaces: controls whether a window can be visible on all spaces (YES) or is associated with
	 * one space at a time (NO).  The default setting is NO.<br></br> Original signature : `BOOL
	 * canBeVisibleOnAllSpaces()`<br></br>
	 * *native declaration : :462*
	 */
	abstract fun canBeVisibleOnAllSpaces(): Boolean

	/**
	 * Original signature : `void setCanBeVisibleOnAllSpaces(BOOL)`<br></br>
	 * *native declaration : :463*
	 */
	abstract fun setCanBeVisibleOnAllSpaces(flag: Boolean)

	/**
	 * Original signature : `NSString* stringWithSavedFrame()`<br></br>
	 * *native declaration : :465*
	 */
	abstract fun stringWithSavedFrame(): String?

	/**
	 * Original signature : `void setFrameFromString(NSString*)`<br></br>
	 * *native declaration : :466*
	 */
	abstract fun setFrameFromString(string: String?)

	/**
	 * Original signature : `void saveFrameUsingName(NSString*)`<br></br>
	 * *native declaration : :467*
	 */
	abstract fun saveFrameUsingName(name: String?)

	/**
	 * Set force=YES to use setFrameUsingName on a non-resizable window<br></br> Original signature : `BOOL
	 * setFrameUsingName(NSString*, BOOL)`<br></br>
	 * *native declaration : :469*
	 */
	abstract fun setFrameUsingName_force(name: String?, force: Boolean): Boolean

	/**
	 * Original signature : `BOOL setFrameUsingName(NSString*)`<br></br>
	 * *native declaration : :470*
	 */
	abstract fun setFrameUsingName(name: String?): Boolean

	/**
	 * Original signature : `BOOL setFrameAutosaveName(NSString*)`<br></br>
	 * *native declaration : :471*
	 */
	abstract fun setFrameAutosaveName(name: String?): Boolean

	/**
	 * Original signature : `NSString* frameAutosaveName()`<br></br>
	 * *native declaration : :472*
	 */
	abstract fun frameAutosaveName(): String?
	/**
	 * *native declaration : :476*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Original signature : `void restoreCachedImage()`<br></br>
	 * *native declaration : :477*
	 */
	abstract fun restoreCachedImage()

	/**
	 * Original signature : `void discardCachedImage()`<br></br>
	 * *native declaration : :478*
	 */
	abstract fun discardCachedImage()

	/**
	 * *native declaration : :480*<br></br>
	 * Conversion Error : NSSize
	 */
	abstract fun minSize(): NSSize?

	/**
	 * *native declaration : :481*<br></br>
	 * Conversion Error : NSSize
	 */
	abstract fun maxSize(): NSSize?

	/**
	 * *native declaration : :482*<br></br>
	 * Conversion Error : NSSize
	 */
	abstract fun setMinSize(size: NSSize?)

	/**
	 * *native declaration : :483*<br></br>
	 * Conversion Error : NSSize
	 */
	abstract fun setMaxSize(size: NSSize?)

	/**
	 * *native declaration : :485*<br></br>
	 * Conversion Error : NSSize
	 */
	abstract fun setContentMinSize(size: NSSize?)

	/**
	 * *native declaration : :486*<br></br>
	 * Conversion Error : NSSize
	 */
	abstract fun setContentMaxSize(size: NSSize?)
	/**
	 * *native declaration : :487*<br></br>
	 * Conversion Error : NSSize
	 */
	/**
	 * *native declaration : :488*<br></br>
	 * Conversion Error : NSSize
	 */
	/**
	 * Original signature : `NSEvent* nextEventMatchingMask(NSUInteger)`<br></br>
	 * *native declaration : :490*
	 */
	abstract fun nextEventMatchingMask(mask: Int): NSEvent?

	/**
	 * Original signature : `NSEvent* nextEventMatchingMask(NSUInteger, NSDate*, NSString*, BOOL)`<br></br>
	 * *native declaration : :491*
	 */
	abstract fun nextEventMatchingMask_untilDate_inMode_dequeue(
		mask: Int,
		expiration: NSDate?,
		mode: String?,
		deqFlag: Boolean
	): NSEvent?

	/**
	 * Original signature : `void discardEventsMatchingMask(NSUInteger, NSEvent*)`<br></br>
	 * *native declaration : :492*
	 */
	abstract fun discardEventsMatchingMask_beforeEvent(mask: Int, lastEvent: Pointer?)

	/**
	 * Original signature : `void postEvent(NSEvent*, BOOL)`<br></br>
	 * *native declaration : :493*
	 */
	abstract fun postEvent_atStart(event: NSEvent?, flag: Boolean)

	/**
	 * Original signature : `NSEvent* currentEvent()`<br></br>
	 * *native declaration : :494*
	 */
	abstract fun currentEvent(): NSEvent?

	/**
	 * Original signature : `void setAcceptsMouseMovedEvents(BOOL)`<br></br>
	 * *native declaration : :495*
	 */
	abstract fun setAcceptsMouseMovedEvents(flag: Boolean)

	/**
	 * Original signature : `BOOL acceptsMouseMovedEvents()`<br></br>
	 * *native declaration : :496*
	 */
	abstract fun acceptsMouseMovedEvents(): Boolean

	/**
	 * Original signature : `void setIgnoresMouseEvents(BOOL)`<br></br>
	 * *native declaration : :498*
	 */
	abstract fun setIgnoresMouseEvents(flag: Boolean)

	/**
	 * Original signature : `BOOL ignoresMouseEvents()`<br></br>
	 * *native declaration : :499*
	 */
	abstract fun ignoresMouseEvents(): Boolean

	/**
	 * Original signature : `NSDictionary* deviceDescription()`<br></br>
	 * *native declaration : :501*
	 */
	abstract fun deviceDescription(): NSDictionary?

	/**
	 * Original signature : `void sendEvent(NSEvent*)`<br></br>
	 * *native declaration : :502*
	 */
	abstract fun sendEvent(theEvent: NSEvent?)

	/**
	 * Original signature : `mouseLocationOutsideOfEventStream()`<br></br>
	 * *native declaration : :503*
	 */
	abstract fun mouseLocationOutsideOfEventStream(): NSObject?

	/**
	 * Original signature : `id windowController()`<br></br>
	 * *native declaration : :506*
	 */
	abstract fun windowController(): NSObject?

	/**
	 * Original signature : `void setWindowController(NSWindowController*)`<br></br>
	 * *native declaration : :507*
	 */
	abstract fun setWindowController(windowController: NSObject?)

	/**
	 * Original signature : `BOOL isSheet()`<br></br>
	 * *native declaration : :509*
	 */
	abstract val isSheet: Boolean

	/**
	 * Original signature : `NSWindow* attachedSheet()`<br></br>
	 * *native declaration : :510*
	 */
	abstract fun attachedSheet(): NSWindow?

	/**
	 * Original signature : `NSButton* standardWindowButton(NSWindowButton)`<br></br>
	 * *native declaration : :514*
	 */
	abstract fun standardWindowButton(b: Int): NSButton?
	/**
	 * *native declaration : :518*<br></br>
	 * Conversion Error : /// Original signature : `void addChildWindow(NSWindow*, null)`<br></br>
	 * - (void)addChildWindow:(NSWindow*)childWin ordered:(null)place; (Argument place cannot be converted)
	 */
	/**
	 * Original signature : `void removeChildWindow(NSWindow*)`<br></br>
	 * *native declaration : :519*
	 */
	abstract fun removeChildWindow(childWin: NSWindow?)

	/**
	 * Original signature : `NSArray* childWindows()`<br></br>
	 * *native declaration : :520*
	 */
	abstract fun childWindows(): NSArray?

	/**
	 * Original signature : `NSWindow* parentWindow()`<br></br>
	 * *native declaration : :522*
	 */
	abstract fun parentWindow(): NSWindow?

	/**
	 * Original signature : `void setParentWindow(NSWindow*)`<br></br>
	 * *native declaration : :523*
	 */
	abstract fun setParentWindow(window: NSWindow?)

	/**
	 * Returns NSGraphicsContext used to render the receiver's content on the screen for the calling thread.<br></br>
	 * Original signature : `NSGraphicsContext* graphicsContext()`<br></br>
	 * *native declaration : :529*
	 */
	abstract fun graphicsContext(): Pointer?

	/**
	 * Returns scale factor applied to view coordinate system to get to base coordinate system of window<br></br> Original
	 * signature : `CGFloat userSpaceScaleFactor()`<br></br>
	 * *native declaration : :533*
	 */
	abstract fun userSpaceScaleFactor(): CGFloat?

	/**
	 * Original signature : `void setInitialFirstResponder(NSView*)`<br></br>
	 * *from NSKeyboardUI native declaration : :539*
	 */
	abstract fun setInitialFirstResponder(view: NSView?)

	/**
	 * Original signature : `NSView* initialFirstResponder()`<br></br>
	 * *from NSKeyboardUI native declaration : :540*
	 */
	abstract fun initialFirstResponder(): NSView?

	/**
	 * Original signature : `void selectNextKeyView(id)`<br></br>
	 * *from NSKeyboardUI native declaration : :541*
	 */
	abstract fun selectNextKeyView(sender: ID?)

	/**
	 * Original signature : `void selectPreviousKeyView(id)`<br></br>
	 * *from NSKeyboardUI native declaration : :542*
	 */
	abstract fun selectPreviousKeyView(sender: ID?)

	/**
	 * Original signature : `void selectKeyViewFollowingView(NSView*)`<br></br>
	 * *from NSKeyboardUI native declaration : :543*
	 */
	abstract fun selectKeyViewFollowingView(aView: NSView?)

	/**
	 * Original signature : `void selectKeyViewPrecedingView(NSView*)`<br></br>
	 * *from NSKeyboardUI native declaration : :544*
	 */
	abstract fun selectKeyViewPrecedingView(aView: NSView?)

	/**
	 * Original signature : `NSSelectionDirection keyViewSelectionDirection()`<br></br>
	 * *from NSKeyboardUI native declaration : :545*
	 */
	abstract fun keyViewSelectionDirection(): Int

	/**
	 * Original signature : `void setDefaultButtonCell(NSButtonCell*)`<br></br>
	 * *from NSKeyboardUI native declaration : :546*
	 */
	abstract fun setDefaultButtonCell(defButt: NSButtonCell?)

	/**
	 * Original signature : `NSButtonCell* defaultButtonCell()`<br></br>
	 * *from NSKeyboardUI native declaration : :547*
	 */
	abstract fun defaultButtonCell(): NSButtonCell?

	/**
	 * Original signature : `void disableKeyEquivalentForDefaultButtonCell()`<br></br>
	 * *from NSKeyboardUI native declaration : :548*
	 */
	abstract fun disableKeyEquivalentForDefaultButtonCell()

	/**
	 * Original signature : `void enableKeyEquivalentForDefaultButtonCell()`<br></br>
	 * *from NSKeyboardUI native declaration : :549*
	 */
	abstract fun enableKeyEquivalentForDefaultButtonCell()

	/**
	 * Original signature : `void setAutorecalculatesKeyViewLoop(BOOL)`<br></br>
	 * *from NSKeyboardUI native declaration : :551*
	 */
	abstract fun setAutorecalculatesKeyViewLoop(flag: Boolean)

	/**
	 * Original signature : `BOOL autorecalculatesKeyViewLoop()`<br></br>
	 * *from NSKeyboardUI native declaration : :552*
	 */
	abstract fun autorecalculatesKeyViewLoop(): Boolean

	/**
	 * Original signature : `void recalculateKeyViewLoop()`<br></br>
	 * *from NSKeyboardUI native declaration : :553*
	 */
	abstract fun recalculateKeyViewLoop()

	/**
	 * Original signature : `void setToolbar(NSToolbar*)`<br></br>
	 * *from NSToolbarSupport native declaration : :558*
	 */
	abstract fun setToolbar(toolbar: NSToolbar?)

	/**
	 * The style of the titlebar area when the window displays a toolbar.
	 *
	 * @param toolbarStyle [NSWindowToolbarStyle]
	 */
	abstract fun setToolbarStyle(toolbarStyle: Int)

	/**
	 * Original signature : `NSToolbar* toolbar()`<br></br>
	 * *from NSToolbarSupport native declaration : :559*
	 */
	abstract fun toolbar(): NSToolbar?

	/**
	 * Original signature : `void toggleToolbarShown(id)`<br></br>
	 * *from NSToolbarSupport native declaration : :560*
	 */
	abstract fun toggleToolbarShown(sender: ID?)

	/**
	 * Original signature : `void runToolbarCustomizationPalette(id)`<br></br>
	 * *from NSToolbarSupport native declaration : :561*
	 */
	abstract fun runToolbarCustomizationPalette(sender: ID?)

	/**
	 * Original signature : `void setShowsToolbarButton(BOOL)`<br></br>
	 * *from NSToolbarSupport native declaration : :563*
	 */
	abstract fun setShowsToolbarButton(show: Boolean)

	/**
	 * Original signature : `BOOL showsToolbarButton()`<br></br>
	 * *from NSToolbarSupport native declaration : :564*
	 */
	abstract fun showsToolbarButton(): Boolean
	/**
	 * *from NSDrag native declaration : :569*<br></br>
	 * Conversion Error : /// Original signature : `void dragImage(NSImage*, null, NSSize, NSEvent*, NSPasteboard*, id, BOOL)`<br></br>
	 * - (void)dragImage:(NSImage*)anImage at:(null)baseLocation offset:(NSSize)initialOffset event:(NSEvent*)event pasteboard:(NSPasteboard*)pboard source:(id)sourceObj slideBack:(BOOL)slideFlag; (Argument baseLocation cannot be converted)
	 */
	/**
	 * Original signature : `void registerForDraggedTypes(NSArray*)`<br></br>
	 * *from NSDrag native declaration : :571*
	 */
	abstract fun registerForDraggedTypes(types: NSArray?)

	/**
	 * Original signature : `void unregisterDraggedTypes()`<br></br>
	 * *from NSDrag native declaration : :572*
	 */
	abstract fun unregisterDraggedTypes()

	abstract fun addTitlebarAccessoryViewController(controller: NSTitlebarAccessoryViewController?)

	/**
	 * @return A value that allows a group of related windows.
	 */
	abstract fun tabbingIdentifier(): String?

	/**
	 * By default, a window generates a tabbing identifier from inherent window properties, such as the window class
	 * name, the delegate class name, the window controller class name, and some additional state. Group windows
	 * together by using the same tabbing identifier.
	 *
	 * @param identifier A value that allows a group of related windows.
	 */
	abstract fun setTabbingIdentifier(identifier: String?)

	/**
	 * @return A Boolean value that indicates whether the window prevents application termination when modal. The value
	 * of this property is YES if the window prevents application termination when modal; otherwise, NO. The default
	 * value is YES.
	 */
	abstract fun preventsApplicationTerminationWhenModal(): Boolean

	/**
	 * Usually, application termination is prevented when a modal window or sheet is open, without consulting the
	 * application delegate. Some windows may wish not to prevent termination, however. Setting this property to NO
	 * overrides the default behavior and allows termination to proceed even if the window is open, either through the
	 * sudden termination path if enabled, or after consulting the application delegate.
	 *
	 * @param value A Boolean value that indicates whether the window prevents application termination when modal.
	 */
	abstract fun setPreventsApplicationTerminationWhenModal(value: Boolean)

	/**
	 * When the value of this property is true, the title bar does not draw its background, which allows all content
	 * underneath it to show through. It only makes sense to set this property to true when
	 * NSFullSizeContentViewWindowMask is also set.
	 *
	 * @param value A Boolean value that indicates whether the title bar draws its background.
	 */
	abstract fun setTitlebarAppearsTransparent(value: Boolean)

	companion object {
		private val CLASS: _Class = Rococoa.createClass("NSWindow", _Class::class.java)

		/// <i>native declaration : line 22</i>
		const val NSBorderlessWindowMask: Int = 0

		/// <i>native declaration : line 23</i>
		const val NSTitledWindowMask: Int = 1 shl 0

		/// <i>native declaration : line 24</i>
		const val NSClosableWindowMask: Int = 1 shl 1

		/// <i>native declaration : line 25</i>
		const val NSMiniaturizableWindowMask: Int = 1 shl 2

		/// <i>native declaration : line 26</i>
		const val NSResizableWindowMask: Int = 1 shl 3

		/**
		 * Specifies a window with textured background (eg. metal)<br></br>
		 * *native declaration : line 34*
		 */
		const val NSTexturedBackgroundWindowMask: Int = 1 shl 8

		/**
		 * Specifies a window that ignores the userSpaceScaleFactor of the NSScreen on which it is created.  Currently
		 * restricted to borderless windows (NSBorderlessWindowMask)<br></br>
		 * *native declaration : line 42*
		 */
		const val NSUnscaledWindowMask: Int = 1 shl 11

		/**
		 * Specifies a window whose titlebar and toolbar have a unified look - that is, a continuous background<br></br>
		 * *native declaration : line 48*
		 */
		const val NSUnifiedTitleAndToolbarWindowMask: Int = 1 shl 12

		/**
		 * used with NSRunLoop's performSelector:target:argument:order:modes:<br></br>
		 * *native declaration : line 55*
		 */
		const val NSDisplayWindowRunLoopOrdering: Int = 600000

		/**
		 * used with NSRunLoop's performSelector:target:argument:order:modes:<br></br>
		 * *native declaration : line 56*
		 */
		const val NSResetCursorRectsRunLoopOrdering: Int = 700000

		/**
		 * Window contents may not be read by another process<br></br>
		 * *native declaration : line 62*
		 */
		const val NSWindowSharingNone: Int = 0

		/**
		 * Window contents may be read but not modified by another process<br></br>
		 * *native declaration : line 63*
		 */
		const val NSWindowSharingReadOnly: Int = 1

		/**
		 * Window contents may be read or modified by another process<br></br>
		 * *native declaration : line 64*
		 */
		const val NSWindowSharingReadWrite: Int = 2

		/**
		 * System determines if window backing store is in VRAM or main memory<br></br>
		 * *native declaration : line 70*
		 */
		const val NSWindowBackingLocationDefault: Int = 0

		/**
		 * Window backing store is in VRAM<br></br>
		 * *native declaration : line 71*
		 */
		const val NSWindowBackingLocationVideoMemory: Int = 1

		/**
		 * Window backing store is in main memory<br></br>
		 * *native declaration : line 72*
		 */
		const val NSWindowBackingLocationMainMemory: Int = 2

		/// <i>native declaration : line 78</i>
		const val NSWindowCollectionBehaviorDefault: Int = 0

		/// <i>native declaration : line 79</i>
		const val NSWindowCollectionBehaviorCanJoinAllSpaces: Int = 1 shl 0

		/// <i>native declaration : line 80</i>
		const val NSWindowCollectionBehaviorMoveToActiveSpace: Int = 1 shl 1

		/**
		 * You may specify at most one of NSWindowCollectionBehaviorManaged, NSWindowCollectionBehaviorTransient, or
		 * NSWindowCollectionBehaviorStationary.  If unspecified, the window gets the default behavior determined by its
		 * window level<br></br> participates in spaces, expos\u00e9.  Default behavior if windowLevel ==
		 * NSNormalWindowLevel<br></br>
		 * *native declaration : line 86*
		 */
		const val NSWindowCollectionBehaviorManaged: Int = 1 shl 2

		/**
		 * You may specify at most one of NSWindowCollectionBehaviorManaged, NSWindowCollectionBehaviorTransient, or
		 * NSWindowCollectionBehaviorStationary.  If unspecified, the window gets the default behavior determined by its
		 * window level<br></br> floats in spaces, hidden by expos\u00e9.  Default behavior if windowLevel !=
		 * NSNormalWindowLevel<br></br>
		 * *native declaration : line 87*
		 */
		const val NSWindowCollectionBehaviorTransient: Int = 1 shl 3

		/**
		 * You may specify at most one of NSWindowCollectionBehaviorManaged, NSWindowCollectionBehaviorTransient, or
		 * NSWindowCollectionBehaviorStationary.  If unspecified, the window gets the default behavior determined by its
		 * window level<br></br> unaffected by expos\u00e9.  Stays visible and stationary, like desktop window<br></br>
		 * *native declaration : line 88*
		 */
		const val NSWindowCollectionBehaviorStationary: Int = 1 shl 4

		/**
		 * You may specify at most one of NSWindowCollectionBehaviorParticipatesInCycle or
		 * NSWindowCollectionBehaviorIgnoresCycle.  If unspecified, the window gets the default behavior determined by its
		 * window level<br></br> default behavior if windowLevel == NSNormalWindowLevel<br></br>
		 * *native declaration : line 93*
		 */
		const val NSWindowCollectionBehaviorParticipatesInCycle: Int = 1 shl 5

		/**
		 * You may specify at most one of NSWindowCollectionBehaviorParticipatesInCycle or
		 * NSWindowCollectionBehaviorIgnoresCycle.  If unspecified, the window gets the default behavior determined by its
		 * window level<br></br> default behavior if windowLevel != NSNormalWindowLevel<br></br>
		 * *native declaration : line 94*
		 */
		const val NSWindowCollectionBehaviorIgnoresCycle: Int = 1 shl 6

		fun setAllowsAutomaticWindowTabbing(automatic: Boolean) {
			if (Rococoa.cast(CLASS, NSObject::class.java).respondsToSelector(
					Foundation.selector("setAllowsAutomaticWindowTabbing:")
				)
			) {
				CLASS.setAllowsAutomaticWindowTabbing(automatic)
			}
		}

		/**
		 * *native declaration : :223*<br></br>
		 * Conversion Error : NSRect
		 */
		fun minFrameWidthWithTitle_styleMask(aTitle: String?, aStyle: NSUInteger?): CGFloat {
			return CLASS.minFrameWidthWithTitle_styleMask(aTitle, aStyle)
		}

		/**
		 * Original signature : `+(CGFloat)minFrameWidthWithTitle:(NSString*) styleMask:(NSUInteger)`<br></br>
		 * *native declaration : line 248*
		 */
		fun contentRectForFrameRect_styleMask(windowFrame: NSRect?, windowStyle: NSUInteger?): NSRect {
			return CLASS.contentRectForFrameRect_styleMask(windowFrame, windowStyle)
		}

		/**
		 * *native declaration : :224*<br></br>
		 * Conversion Error : NSRect
		 */
		fun frameRectForContentRect_styleMask(cRect: NSRect?, aStyle: NSUInteger?): NSRect {
			return CLASS.frameRectForContentRect_styleMask(cRect, aStyle)
		}

		const val WindowDidBecomeKeyNotification: String = "NSWindowDidBecomeKeyNotification"
		const val WindowDidBecomeMainNotification: String = "NSWindowDidBecomeMainNotification"
		const val WindowDidChangeScreenNotification: String = "NSWindowDidChangeScreenNotification"
		const val WindowDidChangeScreenProfileNotification: String = "NSWindowDidChangeScreenProfileNotification"
		const val WindowDidDeminiaturizeNotification: String = "NSWindowDidDeminiaturizeNotification"
		const val WindowDidEndSheetNotification: String = "NSWindowDidEndSheetNotification"
		const val WindowDidExposeNotification: String = "NSWindowDidExposeNotification"
		const val WindowDidMiniaturizeNotification: String = "NSWindowDidMiniaturizeNotification"
		const val WindowDidMoveNotification: String = "NSWindowDidMoveNotification"
		const val WindowDidResignKeyNotification: String = "NSWindowDidResignKeyNotification"
		const val WindowDidResignMainNotification: String = "NSWindowDidResignMainNotification"
		const val WindowDidResizeNotification: String = "NSWindowDidResizeNotification"
		const val WindowDidUpdateNotification: String = "NSWindowDidUpdateNotification"
		const val WindowWillBeginSheetNotification: String = "NSWindowWillBeginSheetNotification"
		const val WindowWillCloseNotification: String = "NSWindowWillCloseNotification"
		const val WindowWillMiniaturizeNotification: String = "NSWindowWillMiniaturizeNotification"
		const val WindowWillMoveNotification: String = "NSWindowWillMoveNotification"
	}
}
