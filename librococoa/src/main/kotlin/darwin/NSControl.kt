package darwin 

import org.rococoa.ID
import org.rococoa.Selector
import org.rococoa.cocoa.foundation.NSInteger
import org.rococoa.cocoa.foundation.NSRect

/// <i>native declaration : :10</i>
abstract class NSControl : NSView() {
	/**
	 * *native declaration : :29*<br></br>
	 * Conversion Error : /// Original signature : `id initWithFrame(null)`<br></br>
	 * - (id)initWithFrame:(null)frameRect; (Argument frameRect cannot be converted)
	 */
	abstract override fun initWithFrame(frameRect: NSRect?): NSControl

	/**
	 * Original signature : `void sizeToFit()`<br></br>
	 * *native declaration : :30*
	 */
	abstract fun sizeToFit()

	/**
	 * Original signature : `void calcSize()`<br></br>
	 * *native declaration : :31*
	 */
	abstract fun calcSize()

	/**
	 * Original signature : `id cell()`<br></br>
	 * *native declaration : :32*
	 */
	abstract fun cell(): NSCell?

	/**
	 * Original signature : `void setCell(NSCell*)`<br></br>
	 * *native declaration : :33*
	 */
	abstract fun setCell(aCell: NSTextFieldCell?)

	/**
	 * Original signature : `id selectedCell()`<br></br>
	 * *native declaration : :34*
	 */
	abstract fun selectedCell(): NSCell?

	/**
	 * Original signature : `id target()`<br></br>
	 * *native declaration : :35*
	 */
	abstract fun target(): ID?

	/**
	 * Original signature : `void setTarget(id)`<br></br>
	 * *native declaration : :36*
	 */
	abstract fun setTarget(anObject: ID?)

	/**
	 * Original signature : `action()`<br></br>
	 * *native declaration : :37*
	 */
	abstract fun action(): Selector?

	/**
	 * *native declaration : :38*<br></br>
	 * Conversion Error : /// Original signature : `void setAction(null)`<br></br>
	 * - (void)setAction:(null)aSelector; (Argument aSelector cannot be converted)
	 */
	abstract fun setAction(action: Selector?)

	/**
	 * Original signature : `void setTag(NSInteger)`<br></br>
	 * *native declaration : :40*
	 */
	abstract fun setTag(anInt: NSInteger?)

	/**
	 * Original signature : `NSInteger selectedTag()`<br></br>
	 * *native declaration : :41*
	 */
	abstract fun selectedTag(): NSInteger?

	/**
	 * Original signature : `void setIgnoresMultiClick(BOOL)`<br></br>
	 * *native declaration : :42*
	 */
	abstract fun setIgnoresMultiClick(flag: Boolean)

	/**
	 * Original signature : `BOOL ignoresMultiClick()`<br></br>
	 * *native declaration : :43*
	 */
	abstract fun ignoresMultiClick(): Boolean

	/**
	 * Original signature : `NSInteger sendActionOn(NSInteger)`<br></br>
	 * *native declaration : :44*
	 */
	abstract fun sendActionOn(mask: Int): Int

	/**
	 * Original signature : `BOOL isContinuous()`<br></br>
	 * *native declaration : :45*
	 */
	abstract fun isContinuous(): Boolean

	/**
	 * Original signature : `void setContinuous(BOOL)`<br></br>
	 * *native declaration : :46*
	 */
	abstract fun setContinuous(flag: Boolean)

	/**
	 * Original signature : `BOOL isEnabled()`<br></br>
	 * *native declaration : :47*
	 */
	abstract fun isEnabled(): Boolean

	/**
	 * Original signature : `void setEnabled(BOOL)`<br></br>
	 * *native declaration : :48*
	 */
	abstract fun setEnabled(flag: Boolean)

	/**
	 * Original signature : `void setFloatingPointFormat(BOOL, NSUInteger, NSUInteger)`<br></br>
	 * *native declaration : :49*
	 */
	abstract fun setFloatingPointFormat_left_right(autoRange: Boolean, leftDigits: Int, rightDigits: Int)

	/**
	 * Original signature : `alignment()`<br></br>
	 * *native declaration : :50*
	 */
	abstract fun alignment(): NSObject?
	/**
	 * *native declaration : :51*<br></br>
	 * Conversion Error : /// Original signature : `void setAlignment(null)`<br></br>
	 * - (void)setAlignment:(null)mode; (Argument mode cannot be converted)
	 */
	/**
	 * Original signature : `NSFont* font()`<br></br>
	 * *native declaration : :52*
	 */
	abstract fun font(): NSFont?

	/**
	 * Original signature : `void setFont(NSFont*)`<br></br>
	 * *native declaration : :53*
	 */
	abstract fun setFont(fontObj: NSFont?)

	/**
	 * Original signature : `void setFormatter(NSFormatter*)`<br></br>
	 * *native declaration : :54*
	 */
	abstract fun setFormatter(newFormatter: NSFormatter?)

	/**
	 * Original signature : `id formatter()`<br></br>
	 * *native declaration : :55*
	 */
	abstract fun formatter(): NSObject?
	/**
	 * *native declaration : :56*<br></br>
	 * Conversion Error : id<NSCopying>
	</NSCopying> */
	/**
	 * Original signature : `void setStringValue(NSString*)`<br></br>
	 * *native declaration : :57*
	 */
	abstract fun setStringValue(aString: String?)

	/**
	 * Original signature : `void setIntValue(int)`<br></br>
	 * *native declaration : :58*
	 */
	abstract fun setIntValue(anInt: Int)

	/**
	 * Original signature : `void setFloatValue(float)`<br></br>
	 * *native declaration : :59*
	 */
	abstract fun setFloatValue(aFloat: Float)

	/**
	 * Original signature : `void setDoubleValue(double)`<br></br>
	 * *native declaration : :60*
	 */
	abstract fun setDoubleValue(aDouble: Double)

	/**
	 * Original signature : `id objectValue()`<br></br>
	 * *native declaration : :61*
	 */
	abstract fun objectValue(): NSObject?

	/**
	 * Original signature : `NSString* stringValue()`<br></br>
	 * *native declaration : :62*
	 */
	abstract fun stringValue(): String?

	/**
	 * Original signature : `int intValue()`<br></br>
	 * *native declaration : :63*
	 */
	abstract fun intValue(): Int

	/**
	 * Original signature : `float floatValue()`<br></br>
	 * *native declaration : :64*
	 */
	abstract fun floatValue(): Float

	/**
	 * Original signature : `double doubleValue()`<br></br>
	 * *native declaration : :65*
	 */
	abstract fun doubleValue(): Double

	/**
	 * Original signature : `void setNeedsDisplay()`<br></br>
	 * *native declaration : :66*
	 */
	abstract fun setNeedsDisplay()

	/**
	 * Original signature : `void updateCell(NSCell*)`<br></br>
	 * *native declaration : :67*
	 */
	abstract fun updateCell(aCell: NSCell?)

	/**
	 * Original signature : `void updateCellInside(NSCell*)`<br></br>
	 * *native declaration : :68*
	 */
	abstract fun updateCellInside(aCell: NSCell?)

	/**
	 * Original signature : `void drawCellInside(NSCell*)`<br></br>
	 * *native declaration : :69*
	 */
	abstract fun drawCellInside(aCell: NSCell?)

	/**
	 * Original signature : `void drawCell(NSCell*)`<br></br>
	 * *native declaration : :70*
	 */
	abstract fun drawCell(aCell: NSCell?)

	/**
	 * Original signature : `void selectCell(NSCell*)`<br></br>
	 * *native declaration : :71*
	 */
	abstract fun selectCell(aCell: NSCell?)
	/**
	 * *native declaration : :73*<br></br>
	 * Conversion Error : /// Original signature : `BOOL sendAction(null, id)`<br></br>
	 * - (BOOL)sendAction:(null)theAction to:(id)theTarget; (Argument theAction cannot be converted)
	 */
	/**
	 * Original signature : `void takeIntValueFrom(id)`<br></br>
	 * *native declaration : :74*
	 */
	abstract fun takeIntValueFrom(sender: ID?)

	/**
	 * Original signature : `void takeFloatValueFrom(id)`<br></br>
	 * *native declaration : :75*
	 */
	abstract fun takeFloatValueFrom(sender: ID?)

	/**
	 * Original signature : `void takeDoubleValueFrom(id)`<br></br>
	 * *native declaration : :76*
	 */
	abstract fun takeDoubleValueFrom(sender: ID?)

	/**
	 * Original signature : `void takeStringValueFrom(id)`<br></br>
	 * *native declaration : :77*
	 */
	abstract fun takeStringValueFrom(sender: ID?)

	/**
	 * Original signature : `void takeObjectValueFrom(id)`<br></br>
	 * *native declaration : :78*
	 */
	abstract fun takeObjectValueFrom(sender: ID?)

	/**
	 * Original signature : `NSText* currentEditor()`<br></br>
	 * *native declaration : :79*
	 */
	abstract fun currentEditor(): NSText?

	/**
	 * Original signature : `BOOL abortEditing()`<br></br>
	 * *native declaration : :80*
	 */
	abstract fun abortEditing(): Boolean

	/**
	 * Original signature : `void validateEditing()`<br></br>
	 * *native declaration : :81*
	 */
	abstract fun validateEditing()

	/**
	 * Original signature : `void mouseDown(NSEvent*)`<br></br>
	 * *native declaration : :82*
	 */
	abstract override fun mouseDown(event: NSEvent?)

	/**
	 * Original signature : `baseWritingDirection()`<br></br>
	 * *native declaration : :85*
	 */
	abstract fun baseWritingDirection(): NSObject?
	/**
	 * *native declaration : :86*<br></br>
	 * Conversion Error : /// Original signature : `void setBaseWritingDirection(null)`<br></br>
	 * - (void)setBaseWritingDirection:(null)writingDirection; (Argument writingDirection cannot be converted)
	 */
	/**
	 * Original signature : `NSInteger integerValue()`<br></br>
	 * *native declaration : :90*
	 */
	abstract fun integerValue(): Int

	/**
	 * Original signature : `void setIntegerValue(NSInteger)`<br></br>
	 * *native declaration : :91*
	 */
	abstract fun setIntegerValue(anInteger: Int)

	/**
	 * Original signature : `void takeIntegerValueFrom(id)`<br></br>
	 * *native declaration : :92*
	 */
	abstract fun takeIntegerValueFrom(sender: ID?)

	/**
	 * *from NSKeyboardUI native declaration : :98*<br></br>
	 * Conversion Error : /// Original signature : `void performClick(null)`<br></br>
	 * - (void)performClick:(null)sender; (Argument sender cannot be converted)
	 */
	abstract fun performClick(sender: ID?)

	/**
	 * Original signature : `void setRefusesFirstResponder(BOOL)`<br></br>
	 * *from NSKeyboardUI native declaration : :99*
	 */
	abstract fun setRefusesFirstResponder(flag: Boolean)

	/**
	 * Original signature : `BOOL refusesFirstResponder()`<br></br>
	 * *from NSKeyboardUI native declaration : :100*
	 */
	abstract fun refusesFirstResponder(): Boolean

	/**
	 * Original signature : `NSAttributedString* attributedStringValue()`<br></br>
	 * *from NSControlAttributedStringMethods native declaration : :135*
	 */
	abstract fun attributedStringValue(): NSAttributedString?

	/**
	 * Original signature : `void setAttributedStringValue(NSAttributedString*)`<br></br>
	 * *from NSControlAttributedStringMethods native declaration : :136*
	 */
	abstract fun setAttributedStringValue(obj: NSAttributedString?)

	companion object {
		val NSControlTextDidBeginEditingNotification: String? = "NSControlTextDidBeginEditingNotification"
		val NSControlTextDidEndEditingNotification: String? = "NSControlTextDidEndEditingNotification"
		val NSControlTextDidChangeNotification: String? = "NSControlTextDidChangeNotification"
	}
}
