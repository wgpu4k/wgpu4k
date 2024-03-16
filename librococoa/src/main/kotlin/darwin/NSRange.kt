package darwin 

import org.rococoa.cocoa.CFIndex
import org.rococoa.cocoa.CFRange
import org.rococoa.cocoa.foundation.NSUInteger

class NSRange : CFRange {
	constructor()

	constructor(location: CFIndex?, length: CFIndex?) : super(location, length)

	companion object {
		fun NSMakeRange(loc: NSUInteger, len: NSUInteger): NSRange? {
			val cfLocation: CFIndex = CFIndex()
			cfLocation.setValue(loc.toLong())
			val cfLength: CFIndex = CFIndex()
			cfLength.setValue(len.toLong())
			return NSRange(cfLocation, cfLength)
		}
	}
}
