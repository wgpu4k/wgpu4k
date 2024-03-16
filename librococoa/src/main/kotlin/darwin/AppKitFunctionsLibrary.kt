package darwin

object AppKitFunctionsLibrary {
	fun beep() {
		AppKitFunctions.instance.NSBeep()
	}
}

