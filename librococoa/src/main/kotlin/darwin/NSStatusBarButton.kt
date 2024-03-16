package darwin

abstract class NSStatusBarButton : NSButton() {
	abstract fun appearsDisabled(): Boolean

	abstract fun setAppearsDisabled(appearsDisabled: Boolean)
}
