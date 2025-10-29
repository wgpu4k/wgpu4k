// This file has been generated DO NOT EDIT !!!
package io.ygdrasil.webgpu

actual enum class CompositeAlphaMode(val value: String) {
	Auto("auto"),
	Opaque("opaque"),
	Premultiplied("premultiplied"),
	Unpremultiplied("unpremultiplied"),
	Inherit("inherit"),
	;

	companion object {
		fun of(value: String): CompositeAlphaMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class PresentMode(val value: String) {
	Fifo("fifo"),
	FifoRelaxed("fifo-relaxed"),
	Immediate("immediate"),
	Mailbox("mailbox"),
	;

	companion object {
		fun of(value: String): PresentMode? {
			return entries.find { it.value == value }
		}
	}
}
