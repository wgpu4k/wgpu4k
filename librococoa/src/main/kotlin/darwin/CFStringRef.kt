package darwin 

import com.sun.jna.platform.mac.CoreFoundation

object CFStringRef : CoreFoundation.CFTypeRef() {
	fun toCFString(s: String?): CFStringRef? {
		val chars: CharArray = s?.toCharArray() ?: CharArray(0)
		val length = chars.size
		return FoundationKitFunctions.library.CFStringCreateWithCharacters(null, chars, CFIndex.valueOf(length))
	}
}
