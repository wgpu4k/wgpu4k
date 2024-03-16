package darwin 

import org.apache.logging.log4j.LogManager
import org.rococoa.ID
import org.rococoa.ObjCClass
import org.rococoa.Rococoa
import org.rococoa.Selector
import org.rococoa.cocoa.foundation.NSInteger
import org.rococoa.cocoa.foundation.NSUInteger

abstract class NSApplication : NSObject() {
	interface Delegate {
		/**
		 * Tells the delegate to open a single file.
		 *
		 *
		 * Sent directly by theApplication to the delegate. The method should open the file filename, returning YES if
		 * the file is successfully opened, and NO otherwise. If the user started up the application by double-clicking
		 * a file, the delegate receives the application:openFile: message before receiving
		 * applicationDidFinishLaunching:. (applicationWillFinishLaunching: is sent before application:openFile:.)
		 *
		 * @param app      The application object associated with the delegate.
		 * @param filename The name of the file to open.
		 * @return YES if the file was successfully opened or NO if it was not.
		 */
		open fun application_openFile(app: NSApplication?, filename: String?): Boolean

		/**
		 * Tells the delegate to open a temporary file.
		 *
		 *
		 *
		 *
		 * Sent directly by theApplication to the delegate. The method should attempt to open the file filename,
		 * returning YES if the file is successfully opened, and NO otherwise.
		 *
		 *
		 * By design, a file opened through this method is assumed to be temporary—it’s the application’s responsibility
		 * to remove the file at the appropriate time.
		 *
		 * @param app      The application object associated with the delegate.
		 * @param filename The name of the temporary file to open.
		 * @return YES if the file was successfully opened or NO if it was not.
		 */
		open fun application_openTempFile(app: NSApplication?, filename: String?): Boolean

		/**
		 * Invoked immediately before opening an untitled file.
		 *
		 *
		 * Use this method to decide whether the application should open a new, untitled file. Note that
		 * applicationOpenUntitledFile: is invoked if this method returns YES.
		 *
		 * @param sender The application object associated with the delegate.
		 * @return YES if the application should open a new untitled file or NO if it should not.
		 */
		open fun applicationShouldOpenUntitledFile(sender: NSApplication?): Boolean

		/**
		 * Tells the delegate to open an untitled file.
		 *
		 *
		 * Sent directly by theApplication to the delegate to request that a new, untitled file be opened.
		 *
		 * @param sender The application object associated with the delegate.
		 * @return YES if the file was successfully opened or NO if it was not.
		 */
		open fun applicationOpenUntitledFile(sender: NSApplication?): Boolean

		/**
		 * Sent by the application to the delegate prior to default behavior to reopen (rapp) AppleEvents.
		 *
		 *
		 * These events are sent whenever the Finder reactivates an already running application because someone
		 * double-clicked it again or used the dock to activate it.
		 *
		 *
		 * By default the Application Kit will handle this event by checking whether there are any visible NSWindow (not
		 * NSPanel) objects, and, if there are none, it goes through the standard untitled document creation (the same
		 * as it does if theApplication is launched without any document to open). For most document-based applications,
		 * an untitled document will be created.
		 *
		 *
		 * The application delegate will also get a chance to respond to the normal untitled document delegate methods.
		 * If you implement this method in your application delegate, it will be called before any of the default
		 * behavior happens. If you return YES, then NSApplication will proceed as normal. If you return NO, then
		 * NSApplication will do nothing. So, you can either implement this method with a version that does nothing, and
		 * return NO if you do not want anything to happen at all (not recommended), or you can implement this method,
		 * handle the event yourself in some custom way, and return NO.
		 *
		 *
		 * Miniaturized windows, windows in the Dock, are considered visible by this method, and cause flag to return
		 * YES, despite the fact that miniaturized windows return NO when sent an visible message.
		 *
		 * @param app                 The application object.
		 * @param visibleWindowsFound Indicates whether the NSApplication object found any visible windows in your
		 * application. You can use this value as an indication of whether the application
		 * would do anything if you return YES.
		 * @return YES if you want the application to perform its normal tasks or NO if you want the application to do
		 * nothing.
		 */
		open fun applicationShouldHandleReopen_hasVisibleWindows(
			app: NSApplication?,
			visibleWindowsFound: Boolean
		): Boolean

		/**
		 * Sent by the default notification center immediately before the application object is initialized.
		 *
		 * @param notification A notification named NSApplicationWillFinishLaunchingNotification. Calling the object
		 * method of this notification returns the NSApplication object itself.
		 */
		fun applicationWillFinishLaunching(notification: NSNotification?) {
			if (log.isDebugEnabled()) {
				log.debug(notification)
			}
		}

		/**
		 * Sent by the default notification center after the application has been launched and initialized but before it
		 * has received its first event.
		 *
		 *
		 * Delegates can implement this method to perform further initialization. This method is called after the
		 * application’s main run loop has been started but before it has processed any events. If the application was
		 * launched by the user opening a file, the delegate’s application:openFile: method is called before this
		 * method. If you want to perform initialization before any files are opened, implement the
		 * applicationWillFinishLaunching: method in your delegate, which is called before application:openFile:.)
		 *
		 * @param notification A notification named NSApplicationDidFinishLaunchingNotification. Calling the object
		 * method of this notification returns the NSApplication object itself.
		 */
		fun applicationDidFinishLaunching(notification: NSNotification?) {
			if (log.isDebugEnabled()) {
				log.debug(notification)
			}
		}

		/**
		 * Sent to notify the delegate that the application is about to terminate.
		 *
		 *
		 * This method is called after the application’s Quit menu item has been selected, or after the terminate:
		 * method has been called. Generally, you should return NSTerminateNow to allow the termination to complete, but
		 * you can cancel the termination process or delay it somewhat as needed. For example, you might delay
		 * termination to finish processing some critical data but then terminate the application as soon as you are
		 * done by calling the replyToApplicationShouldTerminate: method.
		 *
		 * @param app The application object that is about to be terminated.
		 * @return One of the values defined in NSApplicationTerminateReply constants indicating whether the application
		 * should terminate. For compatibility reasons, a return value of NO is equivalent to NSTerminateCancel, and a
		 * return value of YES is equivalent to NSTerminateNow.
		 */
		open fun applicationShouldTerminate(app: NSApplication?): NSUInteger?

		fun applicationWillTerminate(notification: NSNotification?) {
			if (log.isDebugEnabled()) {
				log.debug(notification)
			}
		}

		/**
		 * Invoked when the user closes the last window the application has open.
		 *
		 *
		 * The application sends this message to your delegate when the application’s last window is closed. It sends
		 * this message regardless of whether there are still panels open. (A panel in this case is defined as being an
		 * instance of NSPanel or one of its subclasses.)
		 *
		 *
		 * If your implementation returns NO, control returns to the main event loop and the application is not
		 * terminated. If you return YES, your delegate’s applicationShouldTerminate: method is subsequently invoked to
		 * confirm that the application should be terminated.
		 *
		 * @param app The application object whose last window was closed.
		 * @return NO if the application should not be terminated when its last window is closed; otherwise, YES to
		 * terminate the application.
		 */
		open fun applicationShouldTerminateAfterLastWindowClosed(app: NSApplication?): Boolean

		/**
		 * Sent by the default notification center immediately before the application becomes active.
		 *
		 * @param notification A notification named NSApplicationWillBecomeActiveNotification. Calling the object method
		 * of this notification returns the NSApplication object itself.
		 */
		fun applicationWillBecomeActive(notification: NSNotification?) {
			if (log.isDebugEnabled()) {
				log.debug(notification)
			}
		}

		/**
		 * Sent by the default notification center immediately after the application becomes active.
		 *
		 * @param notification A notification named NSApplicationDidBecomeActiveNotification. Calling the object method
		 * of this notification returns the NSApplication object itself.
		 */
		fun applicationDidBecomeActive(notification: NSNotification?) {
			if (log.isDebugEnabled()) {
				log.debug(notification)
			}
		}

		/**
		 * Sent by the default notification center immediately before the application is deactivated.
		 *
		 * @param notification A notification named NSApplicationWillResignActiveNotification. Calling the object method
		 * of this notification returns the NSApplication object itself.
		 */
		fun applicationWillResignActive(notification: NSNotification?) {
			if (log.isDebugEnabled()) {
				log.debug(notification)
			}
		}

		/**
		 * Sent by the default notification center immediately after the application is deactivated.
		 *
		 * @param notification A notification named NSApplicationDidResignActiveNotification. Calling the object method
		 * of this notification returns the NSApplication object itself.
		 */
		fun applicationDidResignActive(notification: NSNotification?) {
			if (log.isDebugEnabled()) {
				log.debug(notification)
			}
		}
	}

	interface _Class : ObjCClass {
		/**
		 * Returns the application instance, creating it if it doesn’t exist yet.
		 *
		 *
		 * This method also makes a connection to the window server and completes other initialization. Your program
		 * should invoke this method as one of the first statements in main();
		 *
		 * @return The shared application object.
		 */
		open fun sharedApplication(): NSApplication
	}

	abstract fun windows(): NSArray?

	/**
	 * Original signature : `void setDelegate(id)`<br></br>
	 * *native declaration : :108*
	 */
	abstract fun setDelegate(anObject: ID?)

	/**
	 * Original signature : `id delegate()`<br></br>
	 * *native declaration : :109*
	 */
	abstract fun delegate(): ID?

	/**
	 * Original signature : `NSGraphicsContext* context()`<br></br>
	 * *native declaration : :110*
	 */
	abstract fun context(): com.sun.jna.Pointer?

	/**
	 * Original signature : `void hide(id)`<br></br>
	 * *native declaration : :111*
	 */
	abstract fun hide(sender: ID?)

	/**
	 * Original signature : `void unhide(id)`<br></br>
	 * *native declaration : :112*
	 */
	abstract fun unhide(sender: ID?)

	/**
	 * Original signature : `void unhideWithoutActivation()`<br></br>
	 * *native declaration : :113*
	 */
	abstract fun unhideWithoutActivation()

	/**
	 * Original signature : `NSWindow* windowWithWindowNumber(NSInteger)`<br></br>
	 * *native declaration : :114*
	 */
	abstract fun windowWithWindowNumber(windowNum: Int): NSWindow?

	/**
	 * Original signature : `NSWindow* mainWindow()`<br></br>
	 * *native declaration : :115*
	 */
	abstract fun mainWindow(): NSWindow?

	/**
	 * Original signature : `NSWindow* keyWindow()`<br></br>
	 * *native declaration : :116*
	 */
	abstract fun keyWindow(): NSWindow?

	/**
	 * Original signature : `BOOL isActive()`<br></br>
	 * *native declaration : :117*
	 */
	abstract fun isActive(): Boolean

	/**
	 * Original signature : `BOOL isHidden()`<br></br>
	 * *native declaration : :118*
	 */
	abstract fun isHidden(): Boolean

	/**
	 * Original signature : `BOOL isRunning()`<br></br>
	 * *native declaration : :119*
	 */
	abstract fun isRunning(): Boolean

	/**
	 * Original signature : `void deactivate()`<br></br>
	 * *native declaration : :120*
	 */
	abstract fun deactivate()

	/**
	 * Original signature : `void activateIgnoringOtherApps(BOOL)`<br></br>
	 * *native declaration : :121*
	 */
	abstract fun activateIgnoringOtherApps(flag: Boolean)

	/**
	 * Original signature : `void hideOtherApplications(id)`<br></br>
	 * *native declaration : :123*
	 */
	abstract fun hideOtherApplications(sender: ID?)

	/**
	 * Original signature : `void unhideAllApplications(id)`<br></br>
	 * *native declaration : :124*
	 */
	abstract fun unhideAllApplications(sender: ID?)

	/**
	 * Original signature : `void finishLaunching()`<br></br>
	 * *native declaration : :126*
	 */
	abstract fun finishLaunching()

	/**
	 * Original signature : `void run()`<br></br>
	 * *native declaration : :127*
	 */
	abstract fun run()

	/**
	 * Original signature : `NSInteger runModalForWindow(NSWindow*)`<br></br>
	 * *native declaration : :128*
	 */
	abstract fun runModalForWindow(theWindow: NSWindow?): NSInteger?

	/**
	 * Original signature : `void stop(id)`<br></br>
	 * *native declaration : :129*
	 */
	abstract fun stop(sender: ID?)

	/**
	 * Original signature : `void stopModal()`<br></br>
	 * *native declaration : :130*
	 */
	abstract fun stopModal()

	/**
	 * Original signature : `void stopModalWithCode(NSInteger)`<br></br>
	 * *native declaration : :131*
	 */
	abstract fun stopModalWithCode(returnCode: Int)

	/**
	 * Original signature : `void abortModal()`<br></br>
	 * *native declaration : :132*
	 */
	abstract fun abortModal()

	/**
	 * Original signature : `NSWindow* modalWindow()`<br></br>
	 * *native declaration : :133*
	 */
	abstract fun modalWindow(): NSWindow?

	/**
	 * Original signature : `NSModalSession beginModalSessionForWindow(NSWindow*)`<br></br>
	 * *native declaration : :134*
	 */
	abstract fun beginModalSessionForWindow(theWindow: NSWindow?): com.sun.jna.Pointer?

	/**
	 * Original signature : `NSInteger runModalSession(NSModalSession)`<br></br>
	 * *native declaration : :135*
	 */
	abstract fun runModalSession(session: com.sun.jna.Pointer?): NSInteger?

	/**
	 * Original signature : `void endModalSession(NSModalSession)`<br></br>
	 * *native declaration : :136*
	 */
	abstract fun endModalSession(session: com.sun.jna.Pointer?)

	/**
	 * Original signature : `void terminate(id)`<br></br>
	 * *native declaration : :137*
	 */
	abstract fun terminate(sender: ID?)

	/**
	 * A key value coding compliant get-accessor for the orderedDocuments to-many-relationship declared in Cocoa's
	 * definition of the Standard Suite.  Return an array of currently open scriptable documents, in a predictable order
	 * that will be meaningful to script writers.  NSApplication's implementation of this method returns pointers to all
	 * NSDocuments in the front-to-back order of each document's frontmost window.  NSDocuments that have no associated
	 * windows are at the end of the array.<br></br> Original signature : `NSArray* orderedDocuments()`<br></br>
	 * *from NSScripting native declaration : :14*
	 */
	abstract fun orderedDocuments(): NSArray?

	/**
	 * A key value coding compliant get-accessor for the orderedWindows to-many-relationship declared in Cocoa's
	 * definition of the Standard Suite.  Return an array of currently open scriptable windows, including hidden
	 * windows, but typically not includings things like panels.<br></br> Original signature : `NSArray*
	 * orderedWindows()`<br></br>
	 * *from NSScripting native declaration : :17*
	 */
	abstract fun orderedWindows(): NSArray?
	/**
	 * *native declaration : :138*<br></br>
	 * Conversion Error : / **<br></br>
	 * * inform the user that this application needs attention - call this method only if your application is not already active<br></br>
	 * * Original signature : `NSInteger requestUserAttention(null)`<br></br>
	 * * /<br></br>
	 * - (NSInteger)requestUserAttention:(null)requestType; (Argument requestType cannot be converted)
	 */
	/**
	 * Original signature : `void cancelUserAttentionRequest(NSInteger)`<br></br>
	 * *native declaration : :139*
	 */
	abstract fun cancelUserAttentionRequest(request: Int)

	/**
	 * *native declaration : :149*<br></br>
	 * Conversion Error : / **<br></br> * *  Present a sheet on the given window.  When the modal session is ended,<br></br> * *
	 * the didEndSelector will be invoked in the modalDelegate.  The didEndSelector<br></br> * * should have the following
	 * signature, and will be invoked when the modal session ends.<br></br> * * This method should dimiss the sheet using
	 * orderOut:<br></br> * * - (void)sheetDidEnd:(NSWindow *)sheet returnCode:(NSInteger)returnCode contextInfo:(void
	 * *)contextInfo;<br></br> * *<br></br> * Original signature : `void beginSheet(NSWindow*, NSWindow*, id, null,
	 * void*)`<br></br> * /<br></br> - (void)beginSheet:(NSWindow*)sheet modalForWindow:(NSWindow*)docWindow
	 * modalDelegate:(id)modalDelegate didEndSelector:(null)didEndSelector contextInfo:(void*)contextInfo; (Argument
	 * didEndSelector cannot be converted)
	 */
	abstract fun beginSheet_modalForWindow_modalDelegate_didEndSelector_contextInfo(
		sheet: NSWindow?,
		docWindow: NSWindow?,
		modalDelegate: ID?,
		didEndSelector: Selector?,
		contextInfo: ID?
	)

	fun beginSheet(
		sheet: NSWindow?,
		docWindow: NSWindow?,
		modalDelegate: ID?,
		didEndSelector: Selector?,
		contextInfo: ID?
	) {
		this.beginSheet_modalForWindow_modalDelegate_didEndSelector_contextInfo(
			sheet,
			docWindow,
			modalDelegate,
			didEndSelector,
			contextInfo
		)
	}

	/**
	 * Original signature : `void endSheet(NSWindow*)`<br></br>
	 * *native declaration : :150*
	 */
	abstract fun endSheet(sheet: NSWindow?)

	/**
	 * Original signature : `void endSheet(NSWindow*, NSInteger)`<br></br>
	 * *native declaration : :151*
	 */
	abstract fun endSheet_returnCode(sheet: NSWindow?, returnCode: Int)

	fun endSheet(sheet: NSWindow?, returnCode: Int) {
		this.endSheet_returnCode(sheet, returnCode)
	}

	/**
	 * * runModalForWindow:relativeToWindow: is deprecated.  <br></br> * Please use beginSheet:modalForWindow:modalDelegate:didEndSelector:contextInfo:<br></br>
	 * Original signature : `NSInteger runModalForWindow(NSWindow*, NSWindow*)`<br></br>
	 * *native declaration : :157*
	 */
	abstract fun runModalForWindow_relativeToWindow(theWindow: NSWindow?, docWindow: NSWindow?): NSInteger?

	/**
	 * * beginModalSessionForWindow:relativeToWindow: is deprecated.<br></br> * Please use
	 * beginSheet:modalForWindow:modalDelegate:didEndSelector:contextInfo:<br></br> Original signature : `NSModalSession
	 * beginModalSessionForWindow(NSWindow*, NSWindow*)`<br></br>
	 * *native declaration : :163*
	 */
	abstract fun beginModalSessionForWindow_relativeToWindow(
		theWindow: NSWindow?,
		docWindow: NSWindow?
	): com.sun.jna.Pointer?

	/**
	 * Original signature : `NSEvent* nextEventMatchingMask(NSUInteger, NSDate*, NSString*, BOOL)`<br></br>
	 * *native declaration : :164*
	 */
	abstract fun nextEventMatchingMask_untilDate_inMode_dequeue(
		mask: Int,
		expiration: NSDate?,
		mode: String?,
		deqFlag: Boolean
	): com.sun.jna.Pointer?

	/**
	 * Original signature : `void discardEventsMatchingMask(NSUInteger, NSEvent*)`<br></br>
	 * *native declaration : :165*
	 */
	abstract fun discardEventsMatchingMask_beforeEvent(mask: Int, lastEvent: com.sun.jna.Pointer?)

	/**
	 * Original signature : `void postEvent(NSEvent*, BOOL)`<br></br>
	 * *native declaration : :166*
	 */
	abstract fun postEvent_atStart(event: NSEvent?, flag: Boolean)

	/**
	 * Original signature : `NSEvent* currentEvent()`<br></br>
	 * *native declaration : :167*
	 */
	abstract fun currentEvent(): NSEvent?

	/**
	 * Original signature : `void sendEvent(NSEvent*)`<br></br>
	 * *native declaration : :169*
	 */
	abstract fun sendEvent(event: NSEvent?)

	/**
	 * Original signature : `void preventWindowOrdering()`<br></br>
	 * *native declaration : :170*
	 */
	abstract fun preventWindowOrdering()

	/**
	 * *native declaration : :171*<br></br>
	 * Conversion Error : /// Original signature : `NSWindow* makeWindowsPerform(null, BOOL)`<br></br>
	 * - (NSWindow*)makeWindowsPerform:(null)aSelector inOrder:(BOOL)flag; (Argument aSelector cannot be converted)
	 */
	/**
	 * Original signature : `void setWindowsNeedUpdate(BOOL)`<br></br>
	 * *native declaration : :173*
	 */
	abstract fun setWindowsNeedUpdate(needUpdate: Boolean)

	/**
	 * Original signature : `void updateWindows()`<br></br>
	 * *native declaration : :174*
	 */
	abstract fun updateWindows()

	/**
	 * Original signature : `void setMainMenu(NSMenu*)`<br></br>
	 * *native declaration : :176*
	 */
	abstract fun setMainMenu(aMenu: NSMenu?)

	/**
	 * Original signature : `NSMenu* mainMenu()`<br></br>
	 * *native declaration : :177*
	 */
	abstract fun mainMenu(): NSMenu?

	/**
	 * Original signature : `void setApplicationIconImage(NSImage*)`<br></br>
	 * *native declaration : :179*
	 */
	abstract fun setApplicationIconImage(image: NSImage?)

	/**
	 * Original signature : `NSImage* applicationIconImage()`<br></br>
	 * *native declaration : :180*
	 */
	abstract fun applicationIconImage(): NSImage?

	/**
	 * Original signature : `NSDockTile* dockTile()`<br></br>
	 * *native declaration : :183*
	 */
	abstract fun dockTile(): NSDockTile?
	/**
	 * *native declaration : :186*<br></br>
	 * Conversion Error : /// Original signature : `BOOL sendAction(null, id, id)`<br></br>
	 * - (BOOL)sendAction:(null)theAction to:(id)theTarget from:(id)sender; (Argument theAction cannot be converted)
	 */
	/**
	 * *native declaration : :187*<br></br>
	 * Conversion Error : /// Original signature : `id targetForAction(null)`<br></br>
	 * - (id)targetForAction:(null)theAction; (Argument theAction cannot be converted)
	 */
	/**
	 * *native declaration : :188*<br></br>
	 * Conversion Error : /// Original signature : `id targetForAction(null, id, id)`<br></br>
	 * - (id)targetForAction:(null)theAction to:(id)theTarget from:(id)sender; (Argument theAction cannot be converted)
	 */
	/**
	 * *native declaration : :189*<br></br>
	 * Conversion Error : /// Original signature : `BOOL tryToPerform(null, id)`<br></br>
	 * - (BOOL)tryToPerform:(null)anAction with:(id)anObject; (Argument anAction cannot be converted)
	 */
	/**
	 * Original signature : `id validRequestorForSendType(NSString*, NSString*)`<br></br>
	 * *native declaration : :190*
	 */
	abstract fun validRequestorForSendType_returnType(sendType: String?, returnType: String?): ID?

	/**
	 * Original signature : `void reportException(NSException*)`<br></br>
	 * *native declaration : :192*
	 */
	abstract fun reportException(theException: com.sun.jna.Pointer?)

	/**
	 * If an application delegate returns NSTerminateLater from -applicationShouldTerminate:,
	 * -replyToApplicationShouldTerminate: must be called with YES or NO once the application decides if it can
	 * terminate<br></br> Original signature : `void replyToApplicationShouldTerminate(BOOL)`<br></br>
	 * *native declaration : :196*
	 */
	abstract fun replyToApplicationShouldTerminate(shouldTerminate: Boolean)

	/**
	 * *native declaration : :200*<br></br>
	 * Conversion Error : / **<br></br>
	 * * If an application delegate encounters an error while handling -application:openFiles: or -application:printFiles:, -replyToOpenOrPrint: should be called with NSApplicationDelegateReplyFailure.  If the user cancels the operation, NSApplicationDelegateReplyCancel should be used, and if the operation succeeds, NSApplicationDelegateReplySuccess should be used<br></br>
	 * * Original signature : `void replyToOpenOrPrint(null)`<br></br>
	 * * /<br></br>
	 * - (void)replyToOpenOrPrint:(null)reply; (Argument reply cannot be converted)
	 */
	/**
	 * Opens the character palette<br></br> Original signature : `void orderFrontCharacterPalette(id)`<br></br>
	 * *native declaration : :204*
	 */
	abstract fun orderFrontCharacterPalette(sender: ID?)

	/**
	 * Original signature : `void setWindowsMenu(NSMenu*)`<br></br>
	 * *from NSWindowsMenu native declaration : :209*
	 */
	abstract fun setWindowsMenu(aMenu: NSMenu?)

	/**
	 * Original signature : `NSMenu* windowsMenu()`<br></br>
	 * *from NSWindowsMenu native declaration : :210*
	 */
	abstract fun windowsMenu(): NSMenu?

	/**
	 * Original signature : `void arrangeInFront(id)`<br></br>
	 * *from NSWindowsMenu native declaration : :211*
	 */
	abstract fun arrangeInFront(sender: ID?)

	/**
	 * Original signature : `void removeWindowsItem(NSWindow*)`<br></br>
	 * *from NSWindowsMenu native declaration : :212*
	 */
	abstract fun removeWindowsItem(win: NSWindow?)

	/**
	 * Original signature : `void addWindowsItem(NSWindow*, NSString*, BOOL)`<br></br>
	 * *from NSWindowsMenu native declaration : :213*
	 */
	abstract fun addWindowsItem_title_filename(win: NSWindow?, aString: String?, isFilename: Boolean)

	/**
	 * Original signature : `void changeWindowsItem(NSWindow*, NSString*, BOOL)`<br></br>
	 * *from NSWindowsMenu native declaration : :214*
	 */
	abstract fun changeWindowsItem_title_filename(win: NSWindow?, aString: String?, isFilename: Boolean)

	/**
	 * Original signature : `void updateWindowsItem(NSWindow*)`<br></br>
	 * *from NSWindowsMenu native declaration : :215*
	 */
	abstract fun updateWindowsItem(win: NSWindow?)

	/**
	 * Original signature : `void miniaturizeAll(id)`<br></br>
	 * *from NSWindowsMenu native declaration : :216*
	 */
	abstract fun miniaturizeAll(sender: ID?)

	/**
	 * Original signature : `void setServicesMenu(NSMenu*)`<br></br>
	 * *from NSServicesMenu native declaration : :275*
	 */
	abstract fun setServicesMenu(aMenu: NSMenu?)

	/**
	 * Original signature : `NSMenu* servicesMenu()`<br></br>
	 * *from NSServicesMenu native declaration : :276*
	 */
	abstract fun servicesMenu(): NSMenu?

	/**
	 * Original signature : `void registerServicesMenuSendTypes(NSArray*, NSArray*)`<br></br>
	 * *from NSServicesMenu native declaration : :277*
	 */
	abstract fun registerServicesMenuSendTypes_returnTypes(sendTypes: NSArray?, returnTypes: NSArray?)

	/**
	 * Original signature : `void setServicesProvider(id)`<br></br>
	 * *from NSServicesHandling native declaration : :286*
	 */
	abstract fun setServicesProvider(provider: ID?)

	/**
	 * Original signature : `id servicesProvider()`<br></br>
	 * *from NSServicesHandling native declaration : :287*
	 */
	abstract fun servicesProvider(): NSObject?

	/**
	 * Original signature : `void orderFrontStandardAboutPanel(id)`<br></br>
	 * *from NSStandardAboutPanel native declaration : :291*
	 */
	abstract fun orderFrontStandardAboutPanel(sender: ID?)

	/**
	 * Original signature : `void orderFrontStandardAboutPanelWithOptions(NSDictionary*)`<br></br>
	 * *from NSStandardAboutPanel native declaration : :292*
	 */
	abstract fun orderFrontStandardAboutPanelWithOptions(optionsDictionary: NSDictionary?)

	abstract fun setActivationPolicy(activationPolicy: Int)

	abstract fun activationPolicy(): Int

	enum class NSApplicationActivationPolicy {
		/* The application is an ordinary app that appears in the Dock and may have a user interface.  This is the
		 default for bundled apps, unless overridden in the Info.plist. */
		NSApplicationActivationPolicyRegular,

		/* The application does not appear in the Dock and does not have a menu bar, but it may be activated
		programmatically or by clicking on one of its windows.  This corresponds to LSUIElement=1 in the Info.plist. */
		NSApplicationActivationPolicyAccessory
	}

	companion object {
		private val CLASS: _Class = Rococoa.createClass("NSApplication", _Class::class.java) //$NON-NLS-1$

		private val log = LogManager.getLogger(NSApplication::class.java)

		val NSTerminateCancel: NSUInteger? = NSUInteger(0)
		val NSTerminateNow: NSUInteger? = NSUInteger(1)
		val NSTerminateLater: NSUInteger? = NSUInteger(2)

		fun sharedApplication(): NSApplication {
			return CLASS.sharedApplication()
		}
	}
}
