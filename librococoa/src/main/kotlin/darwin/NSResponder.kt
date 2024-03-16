package darwin

import com.sun.jna.Pointer
import org.rococoa.ID
import org.rococoa.cocoa.foundation.NSError

/// <i>native declaration : :11</i>
abstract class NSResponder : NSObject() {
	/**
	 * Original signature : `NSResponder* nextResponder()`<br></br>
	 * *native declaration : :17*
	 */
	abstract fun NSResponder_nextResponder(): NSResponder?

	/**
	 * Original signature : `void setNextResponder(NSResponder*)`<br></br>
	 * *native declaration : :18*
	 */
	abstract fun setNextResponder(aResponder: NSResponder?)
	/**
	 * *native declaration : :19*<br></br>
	 * Conversion Error : /// Original signature : `BOOL tryToPerform(null, id)`<br></br>
	 * - (BOOL)tryToPerform:(null)anAction with:(id)anObject; (Argument anAction cannot be converted)
	 */
	/**
	 * Original signature : `BOOL performKeyEquivalent(NSEvent*)`<br></br>
	 * *native declaration : :20*
	 */
	abstract fun performKeyEquivalent(event: NSEvent?): Boolean

	/**
	 * Original signature : `id validRequestorForSendType(NSString*, NSString*)`<br></br>
	 * *native declaration : :21*
	 */
	abstract fun validRequestorForSendType_returnType(sendType: String?, returnType: String?): ID?

	/**
	 * Original signature : `void mouseDown(NSEvent*)`<br></br>
	 * *native declaration : :22*
	 */
	abstract fun mouseDown(event: NSEvent?)

	/**
	 * Original signature : `void rightMouseDown(NSEvent*)`<br></br>
	 * *native declaration : :23*
	 */
	abstract fun rightMouseDown(event: NSEvent?)

	/**
	 * Original signature : `void otherMouseDown(NSEvent*)`<br></br>
	 * *native declaration : :24*
	 */
	abstract fun otherMouseDown(event: NSEvent?)

	/**
	 * Original signature : `void mouseUp(NSEvent*)`<br></br>
	 * *native declaration : :25*
	 */
	abstract fun mouseUp(event: NSEvent?)

	/**
	 * Original signature : `void rightMouseUp(NSEvent*)`<br></br>
	 * *native declaration : :26*
	 */
	abstract fun rightMouseUp(event: NSEvent?)

	/**
	 * Original signature : `void otherMouseUp(NSEvent*)`<br></br>
	 * *native declaration : :27*
	 */
	abstract fun otherMouseUp(event: NSEvent?)

	/**
	 * Original signature : `void mouseMoved(NSEvent*)`<br></br>
	 * *native declaration : :28*
	 */
	abstract fun mouseMoved(event: NSEvent?)

	/**
	 * Original signature : `void mouseDragged(NSEvent*)`<br></br>
	 * *native declaration : :29*
	 */
	abstract fun mouseDragged(event: NSEvent?)

	/**
	 * Original signature : `void scrollWheel(NSEvent*)`<br></br>
	 * *native declaration : :30*
	 */
	abstract fun scrollWheel(event: NSEvent?)

	/**
	 * Original signature : `void rightMouseDragged(NSEvent*)`<br></br>
	 * *native declaration : :31*
	 */
	abstract fun rightMouseDragged(event: NSEvent?)

	/**
	 * Original signature : `void otherMouseDragged(NSEvent*)`<br></br>
	 * *native declaration : :32*
	 */
	abstract fun otherMouseDragged(event: NSEvent?)

	/**
	 * Original signature : `void mouseEntered(NSEvent*)`<br></br>
	 * *native declaration : :33*
	 */
	abstract fun mouseEntered(event: NSEvent?)

	/**
	 * Original signature : `void mouseExited(NSEvent*)`<br></br>
	 * *native declaration : :34*
	 */
	abstract fun mouseExited(event: NSEvent?)

	/**
	 * Original signature : `void keyDown(NSEvent*)`<br></br>
	 * *native declaration : :35*
	 */
	abstract fun keyDown(event: NSEvent?)

	/**
	 * Original signature : `void keyUp(NSEvent*)`<br></br>
	 * *native declaration : :36*
	 */
	abstract fun keyUp(event: NSEvent?)

	/**
	 * Original signature : `void flagsChanged(NSEvent*)`<br></br>
	 * *native declaration : :37*
	 */
	abstract fun flagsChanged(event: NSEvent?)

	/**
	 * Original signature : `void tabletPoint(NSEvent*)`<br></br>
	 * *native declaration : :39*
	 */
	abstract fun tabletPoint(event: NSEvent?)

	/**
	 * Original signature : `void tabletProximity(NSEvent*)`<br></br>
	 * *native declaration : :40*
	 */
	abstract fun tabletProximity(event: NSEvent?)

	/**
	 * Original signature : `void cursorUpdate(NSEvent*)`<br></br>
	 * *native declaration : :43*
	 */
	abstract fun cursorUpdate(event: NSEvent?)
	/**
	 * *native declaration : :45*<br></br>
	 * Conversion Error : /// Original signature : `void noResponderFor(null)`<br></br>
	 * - (void)noResponderFor:(null)eventSelector; (Argument eventSelector cannot be converted)
	 */
	/**
	 * Original signature : `BOOL acceptsFirstResponder()`<br></br>
	 * *native declaration : :46*
	 */
	abstract fun acceptsFirstResponder(): Boolean

	/**
	 * Original signature : `BOOL becomeFirstResponder()`<br></br>
	 * *native declaration : :47*
	 */
	abstract fun becomeFirstResponder(): Boolean

	/**
	 * Original signature : `BOOL resignFirstResponder()`<br></br>
	 * *native declaration : :48*
	 */
	abstract fun resignFirstResponder(): Boolean

	/**
	 * Original signature : `void interpretKeyEvents(NSArray*)`<br></br>
	 * *native declaration : :50*
	 */
	abstract fun interpretKeyEvents(eventArray: NSEvent?)

	/**
	 * Original signature : `void flushBufferedKeyEvents()`<br></br>
	 * *native declaration : :51*
	 */
	abstract fun flushBufferedKeyEvents()

	/**
	 * Original signature : `void setMenu(NSMenu*)`<br></br>
	 * *native declaration : :53*
	 */
	abstract fun setMenu(menu: NSMenu?)

	/**
	 * Original signature : `NSMenu* menu()`<br></br>
	 * *native declaration : :54*
	 */
	abstract fun menu(): NSMenu?

	/**
	 * Original signature : `void showContextHelp(id)`<br></br>
	 * *native declaration : :56*
	 */
	abstract fun showContextHelp(sender: ID?)

	/**
	 * Original signature : `void helpRequested(NSEvent*)`<br></br>
	 * *native declaration : :58*
	 */
	abstract fun helpRequested(eventPtr: NSEvent?)

	/**
	 * Original signature : `BOOL shouldBeTreatedAsInkEvent(NSEvent*)`<br></br>
	 * *native declaration : :61*
	 */
	abstract fun shouldBeTreatedAsInkEvent(event: NSEvent?): Boolean

	/**
	 * Original signature : `BOOL performMnemonic(NSString*)`<br></br>
	 * *from NSKeyboardUI native declaration : :66*
	 */
	abstract fun performMnemonic(theString: String?): Boolean

	/**
	 * Original signature : `void insertText(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :75*
	 */
	abstract fun insertText(insertString: NSObject?)
	/**
	 * *from NSStandardKeyBindingMethods native declaration : :78*<br></br>
	 * Conversion Error : /// Original signature : `void doCommandBySelector(null)`<br></br>
	 * - (void)doCommandBySelector:(null)aSelector; (Argument aSelector cannot be converted)
	 */
	/**
	 * Original signature : `void moveForward(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :85*
	 */
	abstract fun moveForward(sender: ID?)

	/**
	 * Original signature : `void moveRight(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :86*
	 */
	abstract fun moveRight(sender: ID?)

	/**
	 * Original signature : `void moveBackward(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :87*
	 */
	abstract fun moveBackward(sender: ID?)

	/**
	 * Original signature : `void moveLeft(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :88*
	 */
	abstract fun moveLeft(sender: ID?)

	/**
	 * Original signature : `void moveUp(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :89*
	 */
	abstract fun moveUp(sender: ID?)

	/**
	 * Original signature : `void moveDown(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :90*
	 */
	abstract fun moveDown(sender: ID?)

	/**
	 * Original signature : `void moveWordForward(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :91*
	 */
	abstract fun moveWordForward(sender: ID?)

	/**
	 * Original signature : `void moveWordBackward(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :92*
	 */
	abstract fun moveWordBackward(sender: ID?)

	/**
	 * Original signature : `void moveToBeginningOfLine(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :93*
	 */
	abstract fun moveToBeginningOfLine(sender: ID?)

	/**
	 * Original signature : `void moveToEndOfLine(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :94*
	 */
	abstract fun moveToEndOfLine(sender: ID?)

	/**
	 * Original signature : `void moveToBeginningOfParagraph(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :95*
	 */
	abstract fun moveToBeginningOfParagraph(sender: ID?)

	/**
	 * Original signature : `void moveToEndOfParagraph(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :96*
	 */
	abstract fun moveToEndOfParagraph(sender: ID?)

	/**
	 * Original signature : `void moveToEndOfDocument(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :97*
	 */
	abstract fun moveToEndOfDocument(sender: ID?)

	/**
	 * Original signature : `void moveToBeginningOfDocument(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :98*
	 */
	abstract fun moveToBeginningOfDocument(sender: ID?)

	/**
	 * Original signature : `void pageDown(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :99*
	 */
	abstract fun pageDown(sender: ID?)

	/**
	 * Original signature : `void pageUp(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :100*
	 */
	abstract fun pageUp(sender: ID?)

	/**
	 * Original signature : `void centerSelectionInVisibleArea(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :101*
	 */
	abstract fun centerSelectionInVisibleArea(sender: ID?)

	/**
	 * Original signature : `void moveBackwardAndModifySelection(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :103*
	 */
	abstract fun moveBackwardAndModifySelection(sender: ID?)

	/**
	 * Original signature : `void moveForwardAndModifySelection(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :104*
	 */
	abstract fun moveForwardAndModifySelection(sender: ID?)

	/**
	 * Original signature : `void moveWordForwardAndModifySelection(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :105*
	 */
	abstract fun moveWordForwardAndModifySelection(sender: ID?)

	/**
	 * Original signature : `void moveWordBackwardAndModifySelection(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :106*
	 */
	abstract fun moveWordBackwardAndModifySelection(sender: ID?)

	/**
	 * Original signature : `void moveUpAndModifySelection(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :107*
	 */
	abstract fun moveUpAndModifySelection(sender: ID?)

	/**
	 * Original signature : `void moveDownAndModifySelection(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :108*
	 */
	abstract fun moveDownAndModifySelection(sender: ID?)

	/**
	 * Original signature : `void moveWordRight(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :111*
	 */
	abstract fun moveWordRight(sender: ID?)

	/**
	 * Original signature : `void moveWordLeft(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :112*
	 */
	abstract fun moveWordLeft(sender: ID?)

	/**
	 * Original signature : `void moveRightAndModifySelection(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :113*
	 */
	abstract fun moveRightAndModifySelection(sender: ID?)

	/**
	 * Original signature : `void moveLeftAndModifySelection(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :114*
	 */
	abstract fun moveLeftAndModifySelection(sender: ID?)

	/**
	 * Original signature : `void moveWordRightAndModifySelection(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :115*
	 */
	abstract fun moveWordRightAndModifySelection(sender: ID?)

	/**
	 * Original signature : `void moveWordLeftAndModifySelection(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :116*
	 */
	abstract fun moveWordLeftAndModifySelection(sender: ID?)

	/**
	 * Original signature : `void scrollPageUp(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :119*
	 */
	abstract fun scrollPageUp(sender: ID?)

	/**
	 * Original signature : `void scrollPageDown(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :120*
	 */
	abstract fun scrollPageDown(sender: ID?)

	/**
	 * Original signature : `void scrollLineUp(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :121*
	 */
	abstract fun scrollLineUp(sender: ID?)

	/**
	 * Original signature : `void scrollLineDown(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :122*
	 */
	abstract fun scrollLineDown(sender: ID?)

	/**
	 * Original signature : `void transpose(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :126*
	 */
	abstract fun transpose(sender: ID?)

	/**
	 * Original signature : `void transposeWords(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :127*
	 */
	abstract fun transposeWords(sender: ID?)

	/**
	 * Original signature : `void selectAll(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :131*
	 */
	abstract fun selectAll(sender: ID?)

	/**
	 * Original signature : `void selectParagraph(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :132*
	 */
	abstract fun selectParagraph(sender: ID?)

	/**
	 * Original signature : `void selectLine(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :133*
	 */
	abstract fun selectLine(sender: ID?)

	/**
	 * Original signature : `void selectWord(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :134*
	 */
	abstract fun selectWord(sender: ID?)

	/**
	 * Original signature : `void indent(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :138*
	 */
	abstract fun indent(sender: ID?)

	/**
	 * Original signature : `void insertTab(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :139*
	 */
	abstract fun insertTab(sender: ID?)

	/**
	 * Original signature : `void insertBacktab(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :140*
	 */
	abstract fun insertBacktab(sender: ID?)

	/**
	 * Original signature : `void insertNewline(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :141*
	 */
	abstract fun insertNewline(sender: ID?)

	/**
	 * Original signature : `void insertParagraphSeparator(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :142*
	 */
	abstract fun insertParagraphSeparator(sender: ID?)

	/**
	 * Original signature : `void insertNewlineIgnoringFieldEditor(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :143*
	 */
	abstract fun insertNewlineIgnoringFieldEditor(sender: ID?)

	/**
	 * Original signature : `void insertTabIgnoringFieldEditor(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :144*
	 */
	abstract fun insertTabIgnoringFieldEditor(sender: ID?)

	/**
	 * Original signature : `void insertLineBreak(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :146*
	 */
	abstract fun insertLineBreak(sender: ID?)

	/**
	 * Original signature : `void insertContainerBreak(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :147*
	 */
	abstract fun insertContainerBreak(sender: ID?)

	/**
	 * Original signature : `void changeCaseOfLetter(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :152*
	 */
	abstract fun changeCaseOfLetter(sender: ID?)

	/**
	 * Original signature : `void uppercaseWord(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :153*
	 */
	abstract fun uppercaseWord(sender: ID?)

	/**
	 * Original signature : `void lowercaseWord(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :154*
	 */
	abstract fun lowercaseWord(sender: ID?)

	/**
	 * Original signature : `void capitalizeWord(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :155*
	 */
	abstract fun capitalizeWord(sender: ID?)

	/**
	 * Original signature : `void deleteForward(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :159*
	 */
	abstract fun deleteForward(sender: ID?)

	/**
	 * Original signature : `void deleteBackward(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :160*
	 */
	abstract fun deleteBackward(sender: ID?)

	/**
	 * Original signature : `void deleteBackwardByDecomposingPreviousCharacter(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :162*
	 */
	abstract fun deleteBackwardByDecomposingPreviousCharacter(sender: ID?)

	/**
	 * Original signature : `void deleteWordForward(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :164*
	 */
	abstract fun deleteWordForward(sender: ID?)

	/**
	 * Original signature : `void deleteWordBackward(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :165*
	 */
	abstract fun deleteWordBackward(sender: ID?)

	/**
	 * Original signature : `void deleteToBeginningOfLine(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :166*
	 */
	abstract fun deleteToBeginningOfLine(sender: ID?)

	/**
	 * Original signature : `void deleteToEndOfLine(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :167*
	 */
	abstract fun deleteToEndOfLine(sender: ID?)

	/**
	 * Original signature : `void deleteToBeginningOfParagraph(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :168*
	 */
	abstract fun deleteToBeginningOfParagraph(sender: ID?)

	/**
	 * Original signature : `void deleteToEndOfParagraph(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :169*
	 */
	abstract fun deleteToEndOfParagraph(sender: ID?)

	/**
	 * Original signature : `void yank(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :171*
	 */
	abstract fun yank(sender: ID?)

	/**
	 * Original signature : `void complete(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :175*
	 */
	abstract fun complete(sender: ID?)

	/**
	 * Original signature : `void setMark(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :179*
	 */
	abstract fun setMark(sender: ID?)

	/**
	 * Original signature : `void deleteToMark(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :180*
	 */
	abstract fun deleteToMark(sender: ID?)

	/**
	 * Original signature : `void selectToMark(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :181*
	 */
	abstract fun selectToMark(sender: ID?)

	/**
	 * Original signature : `void swapWithMark(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :182*
	 */
	abstract fun swapWithMark(sender: ID?)

	/**
	 * Original signature : `void cancelOperation(id)`<br></br>
	 * *from NSStandardKeyBindingMethods native declaration : :187*
	 */
	abstract fun cancelOperation(sender: ID?)

	/**
	 * Original signature : `NSUndoManager* undoManager()`<br></br>
	 * *from NSUndoSupport native declaration : :192*
	 */
	abstract fun undoManager(): Pointer?
	/**
	 * *from NSErrorPresentation native declaration : :222*<br></br>
	 * Conversion Error : / **<br></br>
	 * * Present an error alert to the user, as a document-modal panel. When the user has dismissed the alert and any recovery possible for the error and chosen by the user has been attempted, send the selected message to the specified delegate. The method selected by didPresentSelector must have the same signature as:<br></br>
	 * * - (void)didPresentErrorWithRecovery:(BOOL)didRecover contextInfo:(void *)contextInfo;<br></br>
	 * * The default implementation of this method always invokes [self willPresentError:error] to give subclassers an opportunity to customize error presentation. It then forwards the message, passing the customized error, to the next responder or, if there is no next responder, NSApp. NSApplication's override of this method invokes [[NSAlert alertWithError:theErrorToPresent] beginSheetModalForWindow:window modalDelegate:self didEndSelector:selectorForAPrivateMethod contextInfo:privateContextInfo]. When the user has dismissed the alert, the error's recovery attempter is sent an -attemptRecoveryFromError:optionIndex:delegate:didRecoverSelector:contextInfo: message, if the error had recovery options and a recovery delegate.<br></br>
	 * * Errors for which ([[error domain] isEqualToString:NSCocoaErrorDomain] && [error code]==NSUserCancelledError) are a special case,  because they do not actually represent errors and should not be presented as such to the user. NSApplication's override of this method does not present an alert to the user for these kinds of errors. Instead it merely invokes the delegate specifying didRecover==NO.<br></br>
	 * * Between the responder chain in a typical application and various overrides of this method in AppKit classes, objects are given the opportunity to present errors in orders like these:<br></br>
	 * * For windows owned by documents:<br></br>
	 * * view -> superviews -> window -> window controller -> document -> document controller -> application<br></br>
	 * * For windows that have window controllers but aren't associated with documents:<br></br>
	 * * view -> superviews -> window -> window controller -> application<br></br>
	 * * For windows that have no window controller at all:<br></br>
	 * * view -> superviews -> window -> application<br></br>
	 * * You can invoke this method to present error alert sheets. For example, Cocoa's own -[NSDocument saveToURL:ofType:forSaveOperation:delegate:didSaveSelector:contextInfo:] invokes this method when it's just invoked -saveToURL:ofType:forSaveOperation:error: and that method has returned NO.<br></br>
	 * * You probably shouldn't override this method, because you have no way of reliably predicting whether this method vs. -presentError will be invoked for any particular error. You should instead override the -willPresentError: method described below.<br></br>
	 * * Original signature : `void presentError(NSError*, NSWindow*, id, null, void*)`<br></br>
	 * * /<br></br>
	 * - (void)presentError:(NSError*)error modalForWindow:(NSWindow*)window delegate:(id)delegate didPresentSelector:(null)didPresentSelector contextInfo:(void*)contextInfo; (Argument didPresentSelector cannot be converted)
	 */
	/**
	 * Present an error alert to the user, as an application-modal panel, and return YES if error recovery was done, NO otherwise. This method behaves much like the previous one except it does not return until the user has dismissed the alert and, if the error had recovery options and a recovery delegate, the error's recovery delegate has been sent an -attemptRecoveryFromError:optionIndex: message.<br></br>
	 * You can invoke this method to present error alert dialog boxes. For example, Cocoa's own [NSDocumentController openDocument:] invokes this method when it's just invoked -openDocumentWithContentsOfURL:display:error: and that method has returned nil.<br></br>
	 * You probably shouldn't override this method, because you have no way of reliably predicting whether this method vs. -presentError:modalForWindow:delegate:didPresentSelector:contextInfo: will be invoked for any particular error. You should instead override the -willPresentError: method described below.<br></br>
	 * Original signature : `BOOL presentError(NSError*)`<br></br>
	 * *from NSErrorPresentation native declaration : :230*
	 */
	abstract fun presentError(error: NSError?): Boolean

	/**
	 * Given that the receiver is about to present an error (perhaps by just forwarding it to the next responder), return the error that should actually be presented. The default implementation of this method merely returns the passed-in error.<br></br>
	 * You can override this method to customize the presentation of errors by examining the passed-in error and if, for example, its localized description or recovery information is unsuitably generic, returning a more specific one. When you override this method always check the NSError's domain and code to discriminate between errors whose presentation you want to customize and those you don't. For those you don't just return [super willPresentError:error]. Don't make decisions based on the NSError's localized description, recovery suggestion, or recovery options because it's usually not a good idea to try to parse localized text.<br></br>
	 * Original signature : `NSError* willPresentError(NSError*)`<br></br>
	 * *from NSErrorPresentation native declaration : :236*
	 */
	abstract fun willPresentError(error: NSError?): NSError?
}
