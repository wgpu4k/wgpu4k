package io.ygdrasil.wgpu.internal.jvm

import com.sun.jna.Structure
import com.sun.jna.Structure.FieldOrder
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.superclasses

var shouldLogNative = false

internal fun logNative(block: () -> Pair<String, List<Any?>>) {
    if (shouldLogNative) {
        val log = StringBuilder()
        val (functionName, arguments) = block()

        arguments.forEachIndexed { index, any ->
            (any as? Structure)?.let { structure ->
                log.append("const")
                log.append((structure::class as KClass<Any>).getName())
                log.append(" *$functionName$index = ")
                log.append(structure.log())
                log.append(";\n")
            }
        }
        log.append("$functionName(")
        arguments.mapIndexed { index, any ->
            if (any is Structure) {
                "$functionName$index"
            } else {
                any?.toString() ?: "NULL"
            }
        }.joinToString(" ,")
            .let(log::append)
        log.append(");")
        println(log)
    }
}


internal fun Structure.log(indentation: UInt = 0u, isPointer: Boolean = true): String {

    val fieldOrder = (this::class as KClass<Any>).getFieldOrder()
    val memberProperties = (this::class as KClass<Any>).memberProperties

    val log = StringBuilder()
    log.addIndentation(indentation)
    log.append(if (isPointer) "&" else "")
    log.append("(const ${(this::class as KClass<Any>).getName()}){\n")

    val fieldOrderValues = fieldOrder.value
            .flatMap { if (it.endsWith("Ptr")) listOf(it, it.removeSuffix("Ptr")) else listOf(it) }

    fieldOrderValues.forEach { name ->
        val property = memberProperties.find { it.name == name } ?: error("fail to find property")
        val value = property.get(this)

        log.addIndentation(indentation + 1u)
        log.append(".${name} = ")
        if (value == null) {
            log.append("NULL,\n")
        } else {
            if (value is Structure) {
                log.append("\n")
                log.append(value.log(indentation + 2u, value is Structure.ByReference))
            } else if (value is Array<*> && value.isNotEmpty()) {
                val arrayName = (value.first()!!::class as KClass<Any>).getName()
                log.append("\n")
                log.addIndentation(indentation + 2u)
                log.append("(const $arrayName[]){\n")
                value
                    .filterIsInstance<Structure>()
                    .forEach { log.append(it.log(indentation + 3u, value is Structure.ByReference)) }


                log.append("\n")
                log.addIndentation(indentation + 2u)
                log.append("}")
            } else if (value is String) {
                log.append("\"${value}\"")
            } else {
                log.append("${value}")
            }
            log.append(",\n")
        }
    }

    log.addIndentation(indentation)
    log.append("}")
    return log.toString()
}

private fun KClass<*>.getName(): String? {
    return if (simpleName == "ByReference") {
        superclasses.mapNotNull { it.simpleName }.firstOrNull() { it.startsWith("WGPU") }
    } else {
        simpleName
    }
}

private fun KClass<Any>.getFieldOrder(): FieldOrder = (findFieldOrder()
    ?: superclasses.mapNotNull { it.findFieldOrder() }.firstOrNull()
    ?: error("fail to find FieldOrder"))

private fun KClass<*>.findFieldOrder(): FieldOrder? = (annotations.find { it is FieldOrder } as? FieldOrder)

private fun java.lang.StringBuilder.addIndentation(indentation: UInt) {
    repeat(indentation.toInt()) { append("    ") }
}
