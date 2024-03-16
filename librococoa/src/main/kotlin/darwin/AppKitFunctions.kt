package darwin 

import com.sun.jna.Library
import com.sun.jna.Native

interface AppKitFunctions : Library {
	/**
	 * Original signature : `void NSBeep()`<br></br>
	 * *native declaration : /System/Library/Frameworks/AppKit.framework/Headers/AppKitDefines.h:194*
	 */
	open fun NSBeep()

	companion object {
		val instance: AppKitFunctions = Native.load("AppKit", AppKitFunctions::class.java)
	}
}
