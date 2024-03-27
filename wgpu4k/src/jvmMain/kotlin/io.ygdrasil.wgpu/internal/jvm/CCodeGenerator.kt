package io.ygdrasil.wgpu.internal.jvm

import com.sun.jna.Structure
import com.sun.jna.Structure.FieldOrder
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

class CCodeGenerator {


}


fun Structure.log(indentation: UInt = 0u, isPointer: Boolean = true): String {

    val fieldOrder = this::class.annotations.find { it is FieldOrder } as? FieldOrder
        ?: error("fail to find FieldOrder")
    val memberProperties = (this::class as KClass<Any>).memberProperties

    val log = StringBuilder()
    log.addIndentation(indentation)
    log.append(if (isPointer) "&" else "")
    log.append("(const ${this::class.simpleName}){\n")

    fieldOrder.value.forEach { name ->
        val property = memberProperties.find { it.name == name } ?: error("fail to find property")
        val value = property.get(this)

        log.addIndentation(indentation + 1u)
        log.append(".${name} = ")
        if (value == null) {
            log.append("NULL,\n")
        } else {
            if (value is Structure) {
                log.append("\n")
                log.append(value.log(indentation + 2u), value !is Structure.ByReference)
            } else if (value is String) {
                log.append("\"${value}\"")
            } else {
                log.append("${value}")
            }
            log.append(",\n")
        }
    }

    log.addIndentation(indentation)
    log.append("}\n")
    return log.toString()
}

private fun java.lang.StringBuilder.addIndentation(indentation: UInt) {
    repeat(indentation.toInt()) { append("    ") }
}
