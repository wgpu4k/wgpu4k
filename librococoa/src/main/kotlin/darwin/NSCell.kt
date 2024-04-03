package darwin 

import org.rococoa.ID
import org.rococoa.ObjCClass
import org.rococoa.Selector
import org.rococoa.cocoa.foundation.NSInteger
import org.rococoa.cocoa.foundation.NSSize
import org.rococoa.cocoa.foundation.NSUInteger
import java.nio.FloatBuffer

abstract class NSCell : NSObject(), NSCopying {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `BOOL prefersTrackingUntilMouseUp()`<br></br>
		 * *native declaration : :175*
		 */
		open fun prefersTrackingUntilMouseUp(): Boolean

		/**
		 * Original signature : `NSMenu* defaultMenu()`<br></br>
		 * *native declaration : :280*
		 */
		open fun defaultMenu(): NSMenu?

		/**
		 * Original signature : `defaultFocusRingType()`<br></br>
		 * *from NSKeyboardUI native declaration : :323*
		 */
		open fun defaultFocusRingType(): com.sun.jna.Pointer?

		open fun alloc(): NSCell?
	}

	/**
	 * Original signature : `id initTextCell(NSString*)`<br></br>
	 * *native declaration : :178*
	 */
	abstract fun initTextCell(aString: NSString?): NSCell?

	/**
	 * Original signature : `id initImageCell(NSImage*)`<br></br>
	 * *native declaration : :179*
	 */
	abstract fun initImageCell(image: NSImage?): NSCell?

	/**
	 * Original signature : `NSView* controlView()`<br></br>
	 * *native declaration : :181*
	 */
	abstract fun controlView(): NSView?

	/**
	 * Original signature : `void setControlView(NSView*)`<br></br>
	 * *native declaration : :183*
	 */
	abstract fun setControlView(view: NSView?)

	/**
	 * Original signature : `NSCellType type()`<br></br>
	 * *native declaration : :185*
	 */
	abstract fun type(): Int

	/**
	 * Original signature : `void setType(NSCellType)`<br></br>
	 * *native declaration : :186*
	 */
	abstract fun setType(aType: Int)

	/**
	 * Original signature : `NSInteger state()`<br></br>
	 * *native declaration : :187*
	 */
	abstract fun state(): Int

	/**
	 * Original signature : `void setState(NSInteger)`<br></br>
	 * *native declaration : :188*
	 */
	abstract fun setState(value: Int)

	/**
	 * Original signature : `action()`<br></br>
	 * *native declaration : :191*
	 */
	abstract fun action(): Selector?

	/**
	 * *native declaration : :192*<br></br>
	 * Conversion Error : /// Original signature : `void setAction(null)`<br></br>
	 * - (void)setAction:(null)aSelector; (Argument aSelector cannot be converted)
	 */
	abstract fun setAction(action: Selector?)

	/**
	 * Original signature : `NSInteger tag()`<br></br>
	 * *native declaration : :193*
	 */
	abstract fun tag(): Int

	/**
	 * Original signature : `void setTag(NSInteger)`<br></br>
	 * *native declaration : :194*
	 */
	abstract fun setTag(anInt: Int)

	/**
	 * Original signature : `NSString* title()`<br></br>
	 * *native declaration : :195*
	 */
	abstract fun title(): String?

	/**
	 * Original signature : `void setTitle(NSString*)`<br></br>
	 * *native declaration : :196*
	 */
	abstract fun setTitle(aString: String?)

	/**
	 * Original signature : `BOOL isOpaque()`<br></br>
	 * *native declaration : :197*
	 */
	abstract fun isOpaque(): Boolean

	/**
	 * Original signature : `BOOL isEnabled()`<br></br>
	 * *native declaration : :198*
	 */
	abstract fun isEnabled(): Boolean

	/**
	 * Original signature : `void setEnabled(BOOL)`<br></br>
	 * *native declaration : :199*
	 */
	abstract fun setEnabled(flag: Boolean)

	/**
	 * Original signature : `NSInteger sendActionOn(NSInteger)`<br></br>
	 * *native declaration : :200*
	 */
	abstract fun sendActionOn(mask: Int): Int

	/**
	 * Original signature : `BOOL isContinuous()`<br></br>
	 * *native declaration : :201*
	 */
	abstract fun isContinuous(): Boolean

	/**
	 * Original signature : `void setContinuous(BOOL)`<br></br>
	 * *native declaration : :202*
	 */
	abstract fun setContinuous(flag: Boolean)

	/**
	 * Original signature : `BOOL isEditable()`<br></br>
	 * *native declaration : :203*
	 */
	abstract fun isEditable(): Boolean

	/**
	 * Original signature : `void setEditable(BOOL)`<br></br>
	 * *native declaration : :204*
	 */
	abstract fun setEditable(flag: Boolean)

	/**
	 * Original signature : `BOOL isSelectable()`<br></br>
	 * *native declaration : :205*
	 */
	abstract fun isSelectable(): Boolean

	/**
	 * Original signature : `void setSelectable(BOOL)`<br></br>
	 * *native declaration : :206*
	 */
	abstract fun setSelectable(flag: Boolean)

	/**
	 * Original signature : `BOOL isBordered()`<br></br>
	 * *native declaration : :207*
	 */
	abstract fun isBordered(): Boolean

	/**
	 * Original signature : `void setBordered(BOOL)`<br></br>
	 * *native declaration : :208*
	 */
	abstract fun setBordered(flag: Boolean)

	/**
	 * Original signature : `BOOL isBezeled()`<br></br>
	 * *native declaration : :209*
	 */
	abstract fun isBezeled(): Boolean

	/**
	 * Original signature : `void setBezeled(BOOL)`<br></br>
	 * *native declaration : :210*
	 */
	abstract fun setBezeled(flag: Boolean)

	/**
	 * Original signature : `BOOL isScrollable()`<br></br>
	 * *native declaration : :211*
	 */
	abstract fun isScrollable(): Boolean

	/**
	 * Original signature : `void setScrollable(BOOL)`<br></br>
	 * If YES, sets wraps to NO<br></br>
	 * *native declaration : :212*
	 */
	abstract fun setScrollable(flag: Boolean)

	/**
	 * Original signature : `BOOL isHighlighted()`<br></br>
	 * *native declaration : :213*
	 */
	abstract fun isHighlighted(): Boolean

	/**
	 * Original signature : `void setHighlighted(BOOL)`<br></br>
	 * *native declaration : :214*
	 */
	abstract fun setHighlighted(flag: Boolean)

	/**
	 * Original signature : `alignment()`<br></br>
	 * *native declaration : :215*
	 */
	abstract fun alignment(): com.sun.jna.Pointer?

	/**
	 * *native declaration : :216*<br></br>
	 * Conversion Error : /// Original signature : `void setAlignment(null)`<br></br>
	 * - (void)setAlignment:(null)mode; (Argument mode cannot be converted)
	 */
	abstract fun setAlignment(mode: Int)

	/**
	 * Original signature : `BOOL wraps()`<br></br>
	 * *native declaration : :217*
	 */
	abstract fun wraps(): Boolean

	/**
	 * Original signature : `void setWraps(BOOL)`<br></br>
	 * If YES, sets scrollable to NO<br></br>
	 * *native declaration : :218*
	 */
	abstract fun setWraps(flag: Boolean)

	/**
	 * Original signature : `NSFont* font()`<br></br>
	 * *native declaration : :219*
	 */
	abstract fun font(): NSFont?

	/**
	 * Original signature : `void setFont(NSFont*)`<br></br>
	 * *native declaration : :220*
	 */
	abstract fun setFont(fontObj: NSFont?)

	/**
	 * Original signature : `NSInteger entryType()`<br></br>
	 * *native declaration : :221*
	 */
	abstract fun entryType(): Int

	/**
	 * Original signature : `void setEntryType(NSInteger)`<br></br>
	 * *native declaration : :222*
	 */
	abstract fun setEntryType(aType: Int)

	/**
	 * Original signature : `BOOL isEntryAcceptable(NSString*)`<br></br>
	 * *native declaration : :223*
	 */
	abstract fun isEntryAcceptable(aString: String?): Boolean

	/**
	 * Original signature : `void setFloatingPointFormat(BOOL, NSUInteger, NSUInteger)`<br></br>
	 * *native declaration : :224*
	 */
	abstract fun setFloatingPointFormat_left_right(autoRange: Boolean, leftDigits: Int, rightDigits: Int)

	/**
	 * Original signature : `NSString* keyEquivalent()`<br></br>
	 * *native declaration : :225*
	 */
	abstract fun keyEquivalent(): String?

	/**
	 * Original signature : `void setFormatter(NSFormatter*)`<br></br>
	 * *native declaration : :226*
	 */
	abstract fun setFormatter(newFormatter: com.sun.jna.Pointer?)

	/**
	 * Original signature : `id formatter()`<br></br>
	 * *native declaration : :227*
	 */
	abstract fun formatter(): ID?

	/**
	 * Original signature : `id objectValue()`<br></br>
	 * *native declaration : :228*
	 */
	abstract fun objectValue(): NSObject?

	/**
	 * *native declaration : :229*<br></br>
	 * Conversion Error : id<NSCopying>
	</NSCopying> */
	abstract fun setObjectValue(value: NSObject?)

	/**
	 * Original signature : `BOOL hasValidObjectValue()`<br></br>
	 * *native declaration : :230*
	 */
	abstract fun hasValidObjectValue(): Boolean

	/**
	 * Original signature : `NSString* stringValue()`<br></br>
	 * *native declaration : :231*
	 */
	abstract fun stringValue(): String?

	/**
	 * Original signature : `void setStringValue(NSString*)`<br></br>
	 * *native declaration : :232*
	 */
	abstract fun setStringValue(aString: String?)

	/**
	 * Original signature : `compare(id)`<br></br>
	 * *native declaration : :233*
	 */
	abstract fun compare(otherCell: NSObject?): com.sun.jna.Pointer?

	/**
	 * Original signature : `int intValue()`<br></br>
	 * *native declaration : :234*
	 */
	abstract fun intValue(): Int

	/**
	 * Original signature : `void setIntValue(int)`<br></br>
	 * *native declaration : :235*
	 */
	abstract fun setIntValue(anInt: Int)

	/**
	 * Original signature : `float floatValue()`<br></br>
	 * *native declaration : :236*
	 */
	abstract fun floatValue(): Float

	/**
	 * Original signature : `void setFloatValue(float)`<br></br>
	 * *native declaration : :237*
	 */
	abstract fun setFloatValue(aFloat: Float)

	/**
	 * Original signature : `double doubleValue()`<br></br>
	 * *native declaration : :238*
	 */
	abstract fun doubleValue(): Double

	/**
	 * Original signature : `void setDoubleValue(double)`<br></br>
	 * *native declaration : :239*
	 */
	abstract fun setDoubleValue(aDouble: Double)

	/**
	 * Original signature : `void takeIntValueFrom(id)`<br></br>
	 * *native declaration : :240*
	 */
	abstract fun takeIntValueFrom(sender: ID?)

	/**
	 * Original signature : `void takeFloatValueFrom(id)`<br></br>
	 * *native declaration : :241*
	 */
	abstract fun takeFloatValueFrom(sender: ID?)

	/**
	 * Original signature : `void takeDoubleValueFrom(id)`<br></br>
	 * *native declaration : :242*
	 */
	abstract fun takeDoubleValueFrom(sender: ID?)

	/**
	 * Original signature : `void takeStringValueFrom(id)`<br></br>
	 * *native declaration : :243*
	 */
	abstract fun takeStringValueFrom(sender: ID?)

	/**
	 * Original signature : `void takeObjectValueFrom(id)`<br></br>
	 * *native declaration : :244*
	 */
	abstract fun takeObjectValueFrom(sender: ID?)

	/**
	 * Original signature : `NSImage* image()`<br></br>
	 * *native declaration : :245*
	 */
	abstract fun image(): NSImage?

	/**
	 * Original signature : `void setImage(NSImage*)`<br></br>
	 * *native declaration : :246*
	 */
	abstract fun setImage(image: NSImage?)

	/**
	 * Original signature : `void setControlTint(NSControlTint)`<br></br>
	 * *native declaration : :247*
	 */
	abstract fun setControlTint(controlTint: NSUInteger?)

	/**
	 * Original signature : `NSControlTint controlTint()`<br></br>
	 * *native declaration : :248*
	 */
	abstract fun controlTint(): NSUInteger?

	/**
	 * Original signature : `void setControlSize(NSControlSize)`<br></br>
	 * *native declaration : :249*
	 */
	abstract fun setControlSize(size: Int)

	/**
	 * Original signature : `NSControlSize controlSize()`<br></br>
	 * *native declaration : :250*
	 */
	abstract fun controlSize(): Int

	/**
	 * Original signature : `id representedObject()`<br></br>
	 * *native declaration : :251*
	 */
	abstract fun representedObject(): ID?

	/**
	 * Original signature : `void setRepresentedObject(id)`<br></br>
	 * *native declaration : :252*
	 */
	abstract fun setRepresentedObject(anObject: ID?)

	/**
	 * Original signature : `NSInteger cellAttribute(NSCellAttribute)`<br></br>
	 * *native declaration : :253*
	 */
	abstract fun cellAttribute(aParameter: Int): Int

	/**
	 * Original signature : `void setCellAttribute(NSCellAttribute, NSInteger)`<br></br>
	 * *native declaration : :254*
	 */
	abstract fun setCellAttribute_to(aParameter: Int, value: Int)
	/**
	 * *native declaration : :255*<br></br>
	 * Conversion Error : /// Original signature : `imageRectForBounds(null)`<br></br>
	 * - (null)imageRectForBounds:(null)theRect; (Argument theRect cannot be converted)
	 */
	/**
	 * *native declaration : :256*<br></br>
	 * Conversion Error : /// Original signature : `titleRectForBounds(null)`<br></br>
	 * - (null)titleRectForBounds:(null)theRect; (Argument theRect cannot be converted)
	 */
	/**
	 * *native declaration : :257*<br></br>
	 * Conversion Error : /// Original signature : `drawingRectForBounds(null)`<br></br>
	 * - (null)drawingRectForBounds:(null)theRect; (Argument theRect cannot be converted)
	 */
	/**
	 * Original signature : `cellSize()`<br></br>
	 * *native declaration : :258*
	 */
	abstract fun cellSize(): NSSize?
	/**
	 * *native declaration : :259*<br></br>
	 * Conversion Error : /// Original signature : `cellSizeForBounds(null)`<br></br>
	 * - (null)cellSizeForBounds:(null)aRect; (Argument aRect cannot be converted)
	 */
	/**
	 * *native declaration : :260*<br></br>
	 * Conversion Error : /// Original signature : `NSColor* highlightColorWithFrame(null, NSView*)`<br></br>
	 * - (NSColor*)highlightColorWithFrame:(null)cellFrame inView:(NSView*)controlView; (Argument cellFrame cannot be converted)
	 */
	/**
	 * *native declaration : :261*<br></br>
	 * Conversion Error : /// Original signature : `void calcDrawInfo(null)`<br></br>
	 * - (void)calcDrawInfo:(null)aRect; (Argument aRect cannot be converted)
	 */
	/**
	 * Original signature : `NSText* setUpFieldEditorAttributes(NSText*)`<br></br>
	 * *native declaration : :262*
	 */
	abstract fun setUpFieldEditorAttributes(textObj: com.sun.jna.Pointer?): NSText?
	/**
	 * *native declaration : :263*<br></br>
	 * Conversion Error : /// Original signature : `void drawInteriorWithFrame(null, NSView*)`<br></br>
	 * - (void)drawInteriorWithFrame:(null)cellFrame inView:(NSView*)controlView; (Argument cellFrame cannot be converted)
	 */
	/**
	 * *native declaration : :264*<br></br>
	 * Conversion Error : /// Original signature : `void drawWithFrame(null, NSView*)`<br></br>
	 * - (void)drawWithFrame:(null)cellFrame inView:(NSView*)controlView; (Argument cellFrame cannot be converted)
	 */
	/**
	 * *native declaration : :265*<br></br>
	 * Conversion Error : /// Original signature : `void highlight(BOOL, null, NSView*)`<br></br>
	 * - (void)highlight:(BOOL)flag withFrame:(null)cellFrame inView:(NSView*)controlView; (Argument cellFrame cannot be converted)
	 */
	/**
	 * Original signature : `NSInteger mouseDownFlags()`<br></br>
	 * *native declaration : :266*
	 */
	abstract fun mouseDownFlags(): Int

	/**
	 * Original signature : `void getPeriodicDelay(float*, float*)`<br></br>
	 * *native declaration : :267*
	 */
	abstract fun getPeriodicDelay_interval(delay: FloatBuffer?, interval: FloatBuffer?)
	/**
	 * *native declaration : :268*<br></br>
	 * Conversion Error : /// Original signature : `BOOL startTrackingAt(null, NSView*)`<br></br>
	 * - (BOOL)startTrackingAt:(null)startPoint inView:(NSView*)controlView; (Argument startPoint cannot be converted)
	 */
	/**
	 * *native declaration : :269*<br></br>
	 * Conversion Error : /// Original signature : `BOOL continueTracking(null, null, NSView*)`<br></br>
	 * - (BOOL)continueTracking:(null)lastPoint at:(null)currentPoint inView:(NSView*)controlView; (Argument lastPoint cannot be converted)
	 */
	/**
	 * *native declaration : :270*<br></br>
	 * Conversion Error : /// Original signature : `void stopTracking(null, null, NSView*, BOOL)`<br></br>
	 * - (void)stopTracking:(null)lastPoint at:(null)stopPoint inView:(NSView*)controlView mouseIsUp:(BOOL)flag; (Argument lastPoint cannot be converted)
	 */
	/**
	 * *native declaration : :271*<br></br>
	 * Conversion Error : /// Original signature : `BOOL trackMouse(NSEvent*, null, NSView*, BOOL)`<br></br>
	 * - (BOOL)trackMouse:(NSEvent*)theEvent inRect:(null)cellFrame ofView:(NSView*)controlView untilMouseUp:(BOOL)flag; (Argument cellFrame cannot be converted)
	 */
	/**
	 * *native declaration : :272*<br></br>
	 * Conversion Error : /// Original signature : `void editWithFrame(null, NSView*, NSText*, id, NSEvent*)`<br></br>
	 * - (void)editWithFrame:(null)aRect inView:(NSView*)controlView editor:(NSText*)textObj delegate:(id)anObject event:(NSEvent*)theEvent; (Argument aRect cannot be converted)
	 */
	/**
	 * *native declaration : :273*<br></br>
	 * Conversion Error : /// Original signature : `void selectWithFrame(null, NSView*, NSText*, id, NSInteger, NSInteger)`<br></br>
	 * - (void)selectWithFrame:(null)aRect inView:(NSView*)controlView editor:(NSText*)textObj delegate:(id)anObject start:(NSInteger)selStart length:(NSInteger)selLength; (Argument aRect cannot be converted)
	 */
	/**
	 * Original signature : `void endEditing(NSText*)`<br></br>
	 * *native declaration : :274*
	 */
	abstract fun endEditing(textObj: NSText?)
	/**
	 * *native declaration : :275*<br></br>
	 * Conversion Error : /// Original signature : `void resetCursorRect(null, NSView*)`<br></br>
	 * - (void)resetCursorRect:(null)cellFrame inView:(NSView*)controlView; (Argument cellFrame cannot be converted)
	 */
	/**
	 * Original signature : `void setMenu(NSMenu*)`<br></br>
	 * *native declaration : :277*
	 */
	abstract fun setMenu(aMenu: NSMenu?)

	/**
	 * Original signature : `NSMenu* menu()`<br></br>
	 * *native declaration : :278*
	 */
	abstract fun menu(): NSMenu?
	/**
	 * *native declaration : :279*<br></br>
	 * Conversion Error : /// Original signature : `NSMenu* menuForEvent(NSEvent*, null, NSView*)`<br></br>
	 * - (NSMenu*)menuForEvent:(NSEvent*)event inRect:(null)cellFrame ofView:(NSView*)view; (Argument cellFrame cannot be converted)
	 */
	/**
	 * Original signature : `void setSendsActionOnEndEditing(BOOL)`<br></br>
	 * *native declaration : :282*
	 */
	abstract fun setSendsActionOnEndEditing(flag: Boolean)

	/**
	 * Original signature : `BOOL sendsActionOnEndEditing()`<br></br>
	 * *native declaration : :283*
	 */
	abstract fun sendsActionOnEndEditing(): Boolean

	/**
	 * Original signature : `baseWritingDirection()`<br></br>
	 * *native declaration : :286*
	 */
	abstract fun baseWritingDirection(): Int
	/**
	 * *native declaration : :287*<br></br>
	 * Conversion Error : /// Original signature : `void setBaseWritingDirection(null)`<br></br>
	 * - (void)setBaseWritingDirection:(null)writingDirection; (Argument writingDirection cannot be converted)
	 */
	/**
	 * *native declaration : :289*<br></br>
	 * Conversion Error : /// Original signature : `void setLineBreakMode(null)`<br></br>
	 * - (void)setLineBreakMode:(null)mode; (Argument mode cannot be converted)
	 */
	/**
	 * Original signature : `lineBreakMode()`<br></br>
	 * *native declaration : :290*
	 */
	abstract fun lineBreakMode(): com.sun.jna.Pointer?

	/**
	 * Original signature : `void setAllowsUndo(BOOL)`<br></br>
	 * *native declaration : :292*
	 */
	abstract fun setAllowsUndo(allowsUndo: Boolean)

	/**
	 * Original signature : `BOOL allowsUndo()`<br></br>
	 * *native declaration : :293*
	 */
	abstract fun allowsUndo(): Boolean

	/**
	 * Original signature : `NSInteger integerValue()`<br></br>
	 * *native declaration : :297*
	 */
	abstract fun integerValue(): NSInteger?

	/**
	 * Original signature : `void setIntegerValue(NSInteger)`<br></br>
	 * *native declaration : :298*
	 */
	abstract fun setIntegerValue(anInteger: NSInteger?)

	/**
	 * Original signature : `void takeIntegerValueFrom(id)`<br></br>
	 * *native declaration : :299*
	 */
	abstract fun takeIntegerValueFrom(sender: ID?)

	/**
	 * Truncates and adds the ellipsis character to the last visible line if the text doesn't fit into the cell bounds. The setting is ignored if -lineBreakMode is neither NSLineBreakByWordWrapping nor NSLineBreakByCharWrapping.<br></br>
	 * Original signature : `BOOL truncatesLastVisibleLine()`<br></br>
	 * *native declaration : :304*
	 */
	abstract fun truncatesLastVisibleLine(): Boolean

	/**
	 * Original signature : `void setTruncatesLastVisibleLine(BOOL)`<br></br>
	 * *native declaration : :305*
	 */
	abstract fun setTruncatesLastVisibleLine(flag: Boolean)

	/**
	 * Original signature : `void setRefusesFirstResponder(BOOL)`<br></br>
	 * *from NSKeyboardUI native declaration : :309*
	 */
	abstract fun setRefusesFirstResponder(flag: Boolean)

	/**
	 * Original signature : `BOOL refusesFirstResponder()`<br></br>
	 * *from NSKeyboardUI native declaration : :310*
	 */
	abstract fun refusesFirstResponder(): Boolean

	/**
	 * Original signature : `BOOL acceptsFirstResponder()`<br></br>
	 * *from NSKeyboardUI native declaration : :311*
	 */
	abstract fun acceptsFirstResponder(): Boolean

	/**
	 * Original signature : `void setShowsFirstResponder(BOOL)`<br></br>
	 * *from NSKeyboardUI native declaration : :312*
	 */
	abstract fun setShowsFirstResponder(showFR: Boolean)

	/**
	 * Original signature : `BOOL showsFirstResponder()`<br></br>
	 * *from NSKeyboardUI native declaration : :313*
	 */
	abstract fun showsFirstResponder(): Boolean

	/**
	 * Original signature : `void setMnemonicLocation(NSUInteger)`<br></br>
	 * *from NSKeyboardUI native declaration : :314*
	 */
	abstract fun setMnemonicLocation(location: Int)

	/**
	 * Original signature : `NSUInteger mnemonicLocation()`<br></br>
	 * *from NSKeyboardUI native declaration : :315*
	 */
	abstract fun mnemonicLocation(): Int

	/**
	 * Original signature : `NSString* mnemonic()`<br></br>
	 * *from NSKeyboardUI native declaration : :316*
	 */
	abstract fun mnemonic(): String?

	/**
	 * Original signature : `void setTitleWithMnemonic(NSString*)`<br></br>
	 * *from NSKeyboardUI native declaration : :317*
	 */
	abstract fun setTitleWithMnemonic(stringWithAmpersand: String?)

	/**
	 * Original signature : `void performClick(id)`<br></br>
	 * *from NSKeyboardUI native declaration : :318*
	 */
	abstract fun performClick(sender: ID?)
	/**
	 * *from NSKeyboardUI native declaration : :321*<br></br>
	 * Conversion Error : /// Original signature : `void setFocusRingType(null)`<br></br>
	 * - (void)setFocusRingType:(null)focusRingType; (Argument focusRingType cannot be converted)
	 */
	/**
	 * Original signature : `focusRingType()`<br></br>
	 * *from NSKeyboardUI native declaration : :322*
	 */
	abstract fun focusRingType(): com.sun.jna.Pointer?

	/**
	 * Original signature : `BOOL wantsNotificationForMarkedText()`<br></br>
	 * If the receiver returns YES, the field editor initiated by it posts text change notifications (i.e. NSTextDidChangeNotification) while editing marked text; otherwise, they are delayed until the marked text confirmation. The NSCell's implementation returns NO.<br></br>
	 * *from NSKeyboardUI native declaration : :326*
	 */
	abstract fun wantsNotificationForMarkedText(): Boolean

	/**
	 * Original signature : `NSAttributedString* attributedStringValue()`<br></br>
	 * *from NSCellAttributedStringMethods native declaration : :331*
	 */
	abstract fun attributedStringValue(): NSAttributedString?

	/**
	 * Original signature : `void setAttributedStringValue(NSAttributedString*)`<br></br>
	 * *from NSCellAttributedStringMethods native declaration : :332*
	 */
	abstract fun setAttributedStringValue(obj: NSAttributedString?)

	/**
	 * These methods determine whether the user can modify text attributes and import graphics in a rich cell.  Note that whatever these flags are, cells can still contain attributed text if programmatically set.<br></br>
	 * Original signature : `BOOL allowsEditingTextAttributes()`<br></br>
	 * *from NSCellAttributedStringMethods native declaration : :334*
	 */
	abstract fun allowsEditingTextAttributes(): Boolean

	/**
	 * Original signature : `void setAllowsEditingTextAttributes(BOOL)`<br></br>
	 * If NO, also clears setImportsGraphics:<br></br>
	 * *from NSCellAttributedStringMethods native declaration : :335*
	 */
	abstract fun setAllowsEditingTextAttributes(flag: Boolean)

	/**
	 * Original signature : `BOOL importsGraphics()`<br></br>
	 * *from NSCellAttributedStringMethods native declaration : :336*
	 */
	abstract fun importsGraphics(): Boolean

	/**
	 * Original signature : `void setImportsGraphics(BOOL)`<br></br>
	 * If YES, also sets setAllowsEditingTextAttributes:<br></br>
	 * *from NSCellAttributedStringMethods native declaration : :337*
	 */
	abstract fun setImportsGraphics(flag: Boolean)

	/**
	 * Original signature : `void setAllowsMixedState(BOOL)`<br></br>
	 * allow button to have mixed state value<br></br>
	 * *from NSCellMixedState native declaration : :341*
	 */
	abstract fun setAllowsMixedState(flag: Boolean)

	/**
	 * Original signature : `BOOL allowsMixedState()`<br></br>
	 * *from NSCellMixedState native declaration : :342*
	 */
	abstract fun allowsMixedState(): Boolean

	/**
	 * Original signature : `NSInteger nextState()`<br></br>
	 * get next state state in cycle<br></br>
	 * *from NSCellMixedState native declaration : :343*
	 */
	abstract fun nextState(): NSInteger?

	/**
	 * Original signature : `void setNextState()`<br></br>
	 * toggle/cycle through states<br></br>
	 * *from NSCellMixedState native declaration : :344*
	 */
	abstract fun setNextState()
	/**
	 * *from NSCellHitTest native declaration : :382*<br></br>
	 * Conversion Error : / **<br></br>
	 * * Return hit testing information for the cell. Use a bit-wise mask to look for a specific value when calling the method. Generally, this should be overridden by custom NSCell subclasses to return the correct result. Currently, it is called by some multi-cell views, such as NSTableView.<br></br>
	 * * By default, NSCell will look at the cell type and do the following:<br></br>
	 * * NSImageCellType: <br></br>
	 * * If the image exists, and the event point is in the image return NSCellHitContentArea, else NSCellHitNone.<br></br>
	 * * NSTextCellType (also applies to NSTextFieldCell): <br></br>
	 * * If there is text:<br></br>
	 * * If the event point hits in the text, return NSCellHitContentArea. Additionally, if the cell is enabled return NSCellHitContentArea | NSCellHitEditableTextArea.<br></br>
	 * * If there is not text:<br></br>
	 * * Returns NSCellHitNone.<br></br>
	 * * NSNullCellType (this is the default that applies to non text or image cells who don't override hitTestForEvent:):<br></br>
	 * * Return NSCellHitContentArea by default.<br></br>
	 * * If the cell not disabled, and it would track, return NSCellHitContentArea | NSCellHitTrackableArea.<br></br>
	 * * Original signature : `NSUInteger hitTestForEvent(NSEvent*, null, NSView*)`<br></br>
	 * * /<br></br>
	 * - (NSUInteger)hitTestForEvent:(NSEvent*)event inRect:(null)cellFrame ofView:(NSView*)controlView; (Argument cellFrame cannot be converted)
	 */
	/**
	 * *from NSCellExpansion native declaration : :388*<br></br>
	 * Conversion Error : / **<br></br>
	 * * Allows the cell to return an expansion cell frame if cellFrame is too small for the entire contents in the view. When the mouse is hovered over the cell in certain controls, the full cell contents will be shown in a special floating tool tip view. If the frame is not too small, return an empty rect, and no expansion tool tip view will be shown. By default, NSCell returns NSZeroRect, while some subclasses (such as NSTextFieldCell) will return the proper frame when required.<br></br>
	 * * Original signature : `expansionFrameWithFrame(null, NSView*)`<br></br>
	 * * /<br></br>
	 * - (null)expansionFrameWithFrame:(null)cellFrame inView:(NSView*)view; (Argument cellFrame cannot be converted)
	 */
	/**
	 * *from NSCellExpansion native declaration : :392*<br></br>
	 * Conversion Error : / **<br></br>
	 * * Allows the cell to perform custom expansion tool tip drawing. Note that the view may be different from the original view that the cell appeared in. By default, NSCell simply calls drawWithFrame:inView:.<br></br>
	 * * Original signature : `void drawWithExpansionFrame(null, NSView*)`<br></br>
	 * * /<br></br>
	 * - (void)drawWithExpansionFrame:(null)cellFrame inView:(NSView*)view; (Argument cellFrame cannot be converted)
	 */
	/**
	 * Describes the surface the cell is drawn onto in -[NSCell drawWithFrame:inView:]. A control typically sets this before it asks the cell to draw. A cell may draw differently based on background characteristics. For example, a tableview drawing a cell in a selected row might call [cell setBackgroundStyle:NSBackgroundStyleDark]. A text cell might decide to render its text white as a result. A rating-style level indicator might draw its stars white instead of gray.<br></br>
	 * Original signature : `NSBackgroundStyle backgroundStyle()`<br></br>
	 * *from NSCellBackgroundStyle native declaration : :407*
	 */
	abstract fun backgroundStyle(): NSUInteger?

	/**
	 * Original signature : `void setBackgroundStyle(NSBackgroundStyle)`<br></br>
	 * *from NSCellBackgroundStyle native declaration : :408*
	 */
	abstract fun setBackgroundStyle(style: NSUInteger?)

	/**
	 * Describes the surface drawn onto in -[NSCell drawInteriorWithFrame:inView:]. This is often the same as the backgroundStyle, but a button that draws a bezel would have a different interiorBackgroundStyle.  <br></br>
	 * This is both an override point and a useful method to call. A button that draws a custom bezel would override this to describe that surface. A cell that has custom interior drawing might query this method to help pick an image that looks good on the cell. Calling this method gives you some independence from changes in framework art style.<br></br>
	 * Original signature : `NSBackgroundStyle interiorBackgroundStyle()`<br></br>
	 * *from NSCellBackgroundStyle native declaration : :415*
	 */
	abstract fun interiorBackgroundStyle(): NSUInteger?

	companion object {
		/// <i>native declaration : :11</i>
		const val NSAnyType: Int = 0

		/// <i>native declaration : :12</i>
		const val NSIntType: Int = 1

		/// <i>native declaration : :13</i>
		const val NSPositiveIntType: Int = 2

		/// <i>native declaration : :14</i>
		const val NSFloatType: Int = 3

		/// <i>native declaration : :15</i>
		const val NSPositiveFloatType: Int = 4

		/// <i>native declaration : :16</i>
		const val NSDoubleType: Int = 6

		/// <i>native declaration : :17</i>
		const val NSPositiveDoubleType: Int = 7

		/// <i>native declaration : :21</i>
		const val NSNullCellType: Int = 0

		/// <i>native declaration : :22</i>
		const val NSTextCellType: Int = 1

		/// <i>native declaration : :23</i>
		const val NSImageCellType: Int = 2

		/// <i>native declaration : :28</i>
		const val NSCellDisabled: Int = 0

		/// <i>native declaration : :29</i>
		const val NSCellState: Int = 1

		/// <i>native declaration : :30</i>
		const val NSPushInCell: Int = 2

		/// <i>native declaration : :31</i>
		const val NSCellEditable: Int = 3

		/// <i>native declaration : :32</i>
		const val NSChangeGrayCell: Int = 4

		/// <i>native declaration : :33</i>
		const val NSCellHighlighted: Int = 5

		/// <i>native declaration : :34</i>
		const val NSCellLightsByContents: Int = 6

		/// <i>native declaration : :35</i>
		const val NSCellLightsByGray: Int = 7

		/// <i>native declaration : :36</i>
		const val NSChangeBackgroundCell: Int = 8

		/// <i>native declaration : :37</i>
		const val NSCellLightsByBackground: Int = 9

		/// <i>native declaration : :38</i>
		const val NSCellIsBordered: Int = 10

		/// <i>native declaration : :39</i>
		const val NSCellHasOverlappingImage: Int = 11

		/// <i>native declaration : :40</i>
		const val NSCellHasImageHorizontal: Int = 12

		/// <i>native declaration : :41</i>
		const val NSCellHasImageOnLeftOrBottom: Int = 13

		/// <i>native declaration : :42</i>
		const val NSCellChangesContents: Int = 14

		/// <i>native declaration : :43</i>
		const val NSCellIsInsetButton: Int = 15

		/// <i>native declaration : :44</i>
		const val NSCellAllowsMixedState: Int = 16

		/// <i>native declaration : :49</i>
		const val NSNoImage: Int = 0

		/// <i>native declaration : :50</i>
		const val NSImageOnly: Int = 1

		/// <i>native declaration : :51</i>
		const val NSImageLeft: Int = 2

		/// <i>native declaration : :52</i>
		const val NSImageRight: Int = 3

		/// <i>native declaration : :53</i>
		const val NSImageBelow: Int = 4

		/// <i>native declaration : :54</i>
		const val NSImageAbove: Int = 5

		/// <i>native declaration : :55</i>
		const val NSImageOverlaps: Int = 6

		/**
		 * Deprecated. Use NSScaleProportionallyDown<br></br>
		 * *native declaration : :60*
		 */
		const val NSScaleProportionally: Int = 0

		/**
		 * Deprecated. Use NSScaleAxesIndependently<br></br>
		 * *native declaration : :61*
		 */
		const val NSScaleToFit: Int = 1

		/**
		 * Deprecated. Use NSImageScaleNone<br></br>
		 * *native declaration : :62*
		 */
		const val NSScaleNone: Int = 2

		/**
		 * Scale image down if it is too large for destination. Preserve aspect ratio.<br></br>
		 * *native declaration : :67*
		 */
		const val NSImageScaleProportionallyDown: Int = 0

		/**
		 * Scale each dimension to exactly fit destination. Do not preserve aspect ratio.<br></br>
		 * *native declaration : :68*
		 */
		const val NSImageScaleAxesIndependently: Int = 1

		/**
		 * Do not scale.<br></br>
		 * *native declaration : :69*
		 */
		const val NSImageScaleNone: Int = 2

		/**
		 * Scale image to maximum possible dimensions while (1) staying within destination area (2) preserving aspect ratio<br></br>
		 * *native declaration : :70*
		 */
		const val NSImageScaleProportionallyUpOrDown: Int = 3

		/// <i>native declaration : :76</i>
		const val NSMixedState: Int = -1

		/// <i>native declaration : :77</i>
		const val NSOffState: Int = 0

		/// <i>native declaration : :78</i>
		const val NSOnState: Int = 1

		/// <i>native declaration : :85</i>
		const val NSNoCellMask: Int = 0

		/// <i>native declaration : :86</i>
		const val NSContentsCellMask: Int = 1

		/// <i>native declaration : :87</i>
		const val NSPushInCellMask: Int = 2

		/// <i>native declaration : :88</i>
		const val NSChangeGrayCellMask: Int = 4

		/// <i>native declaration : :89</i>
		const val NSChangeBackgroundCellMask: Int = 8

		/**
		 * system 'default'<br></br>
		 * *native declaration : :93*
		 */
		const val NSDefaultControlTint: Int = 0

		/// <i>native declaration : :95</i>
		const val NSBlueControlTint: Int = 1

		/// <i>native declaration : :96</i>
		const val NSGraphiteControlTint: Int = 6

		/// <i>native declaration : :98</i>
		const val NSClearControlTint: Int = 7

		/// <i>native declaration : :103</i>
		const val NSRegularControlSize: Int = 0

		/// <i>native declaration : :104</i>
		const val NSSmallControlSize: Int = 1

		/// <i>native declaration : :106</i>
		const val NSMiniControlSize: Int = 2

		/**
		 * An empty area, or did not hit in the cell<br></br>
		 * *native declaration : :355*
		 */
		const val NSCellHitNone: Int = 0

		/**
		 * A content area in the cell<br></br>
		 * *native declaration : :357*
		 */
		const val NSCellHitContentArea: Int = 1 shl 0

		/**
		 * An editable text area of the cell<br></br>
		 * *native declaration : :359*
		 */
		const val NSCellHitEditableTextArea: Int = 1 shl 1

		/**
		 * A trackable area in the cell<br></br>
		 * *native declaration : :361*
		 */
		const val NSCellHitTrackableArea: Int = 1 shl 2

		/**
		 * The background is a light color. Dark content contrasts well with this background.<br></br>
		 * *native declaration : :396*
		 */
		const val NSBackgroundStyleLight: Int = 0

		/**
		 * The background is a dark color. Light content contrasts well with this background.<br></br>
		 * *native declaration : :397*
		 */
		const val NSBackgroundStyleDark: Int = 1

		/**
		 * The background is intended to appear higher than the content drawn on it. Content might need to be inset.<br></br>
		 * *native declaration : :398*
		 */
		const val NSBackgroundStyleRaised: Int = 2

		/**
		 * The background is intended to appear lower than the content drawn on it. Content might need to be embossed.<br></br>
		 * *native declaration : :399*
		 */
		const val NSBackgroundStyleLowered: Int = 3
	}
}
