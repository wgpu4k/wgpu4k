package darwin

import org.rococoa.ObjCClass
import org.rococoa.Rococoa

abstract class CALayer : NSObject() {

	abstract fun init(): CALayer

	interface _Class : ObjCClass {
		fun alloc(): CALayer
	}

	companion object {
		private val CLASS: CALayer._Class = Rococoa.createClass("CALayer", CALayer._Class::class.java)

		fun create(): CALayer {
			return CLASS.alloc().init()
		}

	}
}