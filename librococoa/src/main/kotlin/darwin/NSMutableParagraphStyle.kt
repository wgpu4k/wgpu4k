package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.CGFloat
import org.rococoa.cocoa.foundation.NSInteger

/// <i>native declaration : :150</i>
abstract class NSMutableParagraphStyle : NSParagraphStyle() {
	interface _Class : ObjCClass {
		open fun alloc(): NSMutableParagraphStyle
	}

	abstract fun init(): NSMutableParagraphStyle?

	/**
	 * Original signature : `void setLineSpacing(CGFloat)`<br></br>
	 * *native declaration : :152*
	 */
	abstract fun setLineSpacing(aFloat: CGFloat?)

	/**
	 * Original signature : `public abstract void setParagraphSpacing(CGFloat)`<br></br>
	 * *native declaration : :153*
	 */
	abstract fun setParagraphSpacing(aFloat: CGFloat?)

	/**
	 * *native declaration : :154*<br></br>
	 * Conversion Error : /// Original signature : `public abstract void setAlignment(null)`<br></br>
	 * - (void)setAlignment:(null)alignment; (Argument alignment cannot be converted)
	 */
	abstract fun setAlignment(alignment: Int)

	/**
	 * Original signature : `public abstract void setFirstLineHeadIndent(CGFloat)`<br></br>
	 * *native declaration : :155*
	 */
	abstract fun setFirstLineHeadIndent(aFloat: CGFloat?)

	/**
	 * Original signature : `public abstract void setHeadIndent(CGFloat)`<br></br>
	 * *native declaration : :156*
	 */
	abstract fun setHeadIndent(aFloat: CGFloat?)

	/**
	 * Original signature : `public abstract void setTailIndent(CGFloat)`<br></br>
	 * *native declaration : :157*
	 */
	abstract fun setTailIndent(aFloat: CGFloat?)

	/**
	 * Original signature : `public abstract void setLineBreakMode(NSLineBreakMode)`<br></br>
	 * *native declaration : :158*
	 */
	abstract fun setLineBreakMode(mode: Int)

	/**
	 * Original signature : `public abstract void setMinimumLineHeight(CGFloat)`<br></br>
	 * *native declaration : :159*
	 */
	abstract fun setMinimumLineHeight(aFloat: CGFloat?)

	/**
	 * Original signature : `public abstract void setMaximumLineHeight(CGFloat)`<br></br>
	 * *native declaration : :160*
	 */
	abstract fun setMaximumLineHeight(aFloat: CGFloat?)
	/**
	 * Original signature : `public abstract void addTabStop(NSTextTab*)`<br></br>
	 * *native declaration : :161*
	 */
	//	public abstract void addTabStop(NSTextTab anObject);
	/**
	 * Original signature : `public abstract void removeTabStop(NSTextTab*)`<br></br>
	 * *native declaration : :162*
	 */
	//	public abstract void removeTabStop(NSTextTab anObject);
	/**
	 * Original signature : `public abstract void setTabStops(NSArray*)`<br></br>
	 * *native declaration : :163*
	 */
	abstract fun setTabStops(array: NSArray?)

	/**
	 * Original signature : `public abstract void setParagraphStyle(NSParagraphStyle*)`<br></br>
	 * *native declaration : :164*
	 */
	abstract fun setParagraphStyle(obj: NSParagraphStyle?)
	/**
	 * *native declaration : :166*<br></br>
	 * Conversion Error : /// Original signature : `public abstract void setBaseWritingDirection(null)`<br></br>
	 * - (void)setBaseWritingDirection:(null)writingDirection; (Argument writingDirection cannot be converted)
	 */
	/**
	 * Original signature : `public abstract void setLineHeightMultiple(CGFloat)`<br></br>
	 * *native declaration : :169*
	 */
	abstract fun setLineHeightMultiple(aFloat: CGFloat?)

	/**
	 * Original signature : `public abstract void setParagraphSpacingBefore(CGFloat)`<br></br>
	 * *native declaration : :170*
	 */
	abstract fun setParagraphSpacingBefore(aFloat: CGFloat?)

	/**
	 * Original signature : `public abstract void setDefaultTabInterval(CGFloat)`<br></br>
	 * *native declaration : :171*
	 */
	abstract fun setDefaultTabInterval(aFloat: CGFloat?)

	/**
	 * Original signature : `public abstract void setTextBlocks(NSArray*)`<br></br>
	 * *native declaration : :174*
	 */
	abstract fun setTextBlocks(array: NSArray?)

	/**
	 * Original signature : `public abstract void setTextLists(NSArray*)`<br></br>
	 * *native declaration : :175*
	 */
	abstract fun setTextLists(array: NSArray?)

	/**
	 * Original signature : `public abstract void setHyphenationFactor(float)`<br></br>
	 * *native declaration : :176*
	 */
	abstract fun setHyphenationFactor(aFactor: Float)

	/**
	 * Original signature : `public abstract void setTighteningFactorForTruncation(float)`<br></br>
	 * *native declaration : :177*
	 */
	abstract fun setTighteningFactorForTruncation(aFactor: Float)

	/**
	 * Original signature : `public abstract void setHeaderLevel(NSInteger)`<br></br>
	 * *native declaration : :178*
	 */
	abstract fun setHeaderLevel(level: NSInteger?)

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSMutableParagraphStyle", _Class::class.java)

		fun paragraphStyle(): NSMutableParagraphStyle? {
			return CLASS.alloc().init()
		}
	}
}
