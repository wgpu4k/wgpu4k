package darwin

import org.rococoa.ObjCClass
import org.rococoa.Rococoa

abstract class CAMetalLayer : CALayer() {

	interface _Class : ObjCClass {
		fun layer(): CAMetalLayer
	}

	companion object {
		private val CLASS: CAMetalLayer._Class = Rococoa.createClass("CAMetalLayer", CAMetalLayer._Class::class.java)

		fun layer(): CAMetalLayer {
			return CLASS.layer()
		}

	}
}