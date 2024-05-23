package io.ygdrasil.wgpu.internal.jvm

import com.sun.jna.Memory
import com.sun.jna.Structure
import com.sun.jna.Structure.FieldOrder
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.superclasses


var shouldLogNative = false

internal fun registerNative(block: () -> Any?) {
    if (shouldLogNative) {
        block()?.let { findInstanceOf(it) }
    }
}

internal fun logNative(block: () -> Triple<String, List<Any?>, KClass<out Any>?>) {
    if (shouldLogNative) {
        val log = StringBuilder()
        val (functionName, arguments, returnType) = block()

        arguments.forEachIndexed { index, any ->
            (any as? Structure)?.let { structure ->
                log.append("const ")
                @Suppress("UNCHECKED_CAST")
                log.append((structure::class as KClass<Any>).getName())
                log.append(" *$functionName$index = ")
                log.append(structure.log())
                log.append(";\n")
            }
        }

        if (returnType != null) {
            log.append("${returnType.simpleName} ${returnType.assignableName}${countInstances.getOrElse(returnType) { 0 }} = ")
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

internal fun logUnitNative(block: () -> Pair<String, List<Any?>>) {
    if (shouldLogNative) {
        val (first, second) = block()
        val tripleBlock: () -> Triple<String, List<Any?>, KClass<Any>?> = { Triple(first, second, null) }
        logNative(tripleBlock)
    }
}


private val reference: MutableMap<Any, String> = hashMapOf()
private val countInstances: MutableMap<KClass<out Any>, Int> = mutableMapOf()

internal fun Structure.log(indentation: UInt = 0u, isPointer: Boolean = true): String {

    @Suppress("UNCHECKED_CAST") val fieldOrder = (this::class as KClass<Any>).getFieldOrder()
    @Suppress("UNCHECKED_CAST") val memberProperties = (this::class as KClass<Any>).memberProperties

    val log = StringBuilder()
    log.addIndentation(indentation)
    log.append(if (isPointer) "&" else "")
    @Suppress("UNCHECKED_CAST")
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
                    .forEach {
                        @Suppress("KotlinConstantConditions")
                        log.append(it.log(indentation + 3u, (value as? Structure.ByReference) != null))
                    }


                log.append("\n")
                log.addIndentation(indentation + 2u)
                log.append("}")
            } else if (value is String) {
                log.append("\"${value}\"")
            } else if (value is Number) {
                log.append("${value}")
            } else if (value is Memory) {
                log.append("Memory of ${value.size()} bytes")
            } else {
                log.append(findInstanceOf(value))

            }
            log.append(",\n")
        }
    }

    log.addIndentation(indentation)
    log.append("}")
    return log.toString()
}

private fun findInstanceOf(value: Any) = reference.getOrPut(value) {
    val simpleName = value::class.assignableName
    val index = countInstances.getOrElse(value::class) { 0 }
    countInstances[value::class] = index + 1
    "$simpleName${countInstances[value::class]}"
}

private val KClass<out Any>.assignableName: String
    get() = simpleName?.removePrefix("WGPU")?.lowercaseOnFirstCharacter() ?: error("fail to get name")

private fun String.lowercaseOnFirstCharacter(): String = when {
    isNotEmpty() -> first().lowercase() + substring(1)
    else -> this
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
