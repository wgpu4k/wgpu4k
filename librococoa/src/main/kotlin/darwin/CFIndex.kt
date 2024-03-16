package darwin 

import com.sun.jna.NativeLong

class CFIndex : NativeLong() {

	companion object {
		fun valueOf(i: Int): CFIndex? {
			val idx: CFIndex = CFIndex()
			idx.setValue(i.toLong())
			return idx
		}
	}


	override fun toByte(): Byte {
		TODO("Not yet implemented")
	}

	override fun toShort(): Short {
		TODO("Not yet implemented")
	}
}
