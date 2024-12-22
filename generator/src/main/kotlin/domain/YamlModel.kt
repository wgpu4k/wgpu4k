package domain

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class YamlModel(
    val copyright: String,
    val name: String,
    val enum_prefix: String,
    val constants: List<Constant>,
    val typedefs: List<String>,
    val bitflags: List<Bitflag>,
    val structs: List<Struct>,
    val callbacks: List<Callback>,
    val functions: List<Function>,
    val objects: List<Object>,
    val enums: List<Enum>,
) {
    fun merge(loadExtraYaml: YamlModel): YamlModel {
        return YamlModel(
            copyright = this.copyright,
            name = this.name,
            enum_prefix = this.enum_prefix,
            constants = (this.constants + loadExtraYaml.constants).distinctBy { it.name },
            typedefs = (this.typedefs + loadExtraYaml.typedefs).distinct(),
            enums = (this.enums + loadExtraYaml.enums).distinctBy { it.name },
            bitflags = (this.bitflags + loadExtraYaml.bitflags).distinctBy { it.name },
            structs = (this.structs + loadExtraYaml.structs).distinctBy { it.name },
            callbacks = (this.callbacks + loadExtraYaml.callbacks).distinctBy { it.name },
            functions = (this.functions + loadExtraYaml.functions).distinctBy { it.name },
            objects = (this.objects + loadExtraYaml.objects).distinctBy { it.name }
        )
    }

    @Serializable
    data class Object(
        val name: String,
        val doc: String,
        val methods: List<Function>,
    )

    @Serializable
    data class Function(
        val name: String,
        val doc: String,
        val args: List<Arg> = listOf(),
        val returns: Return? = null,
        val callback: String? = null,
    ) {
        @Serializable
        data class Return(
            val type: String,
            val doc: String,
            val passed_with_ownership:Boolean = false,
            val pointer: String? = null,
        ) {
            val isMutable: Boolean
                get() = passed_with_ownership || pointer == "mutable"
        }

        @Serializable
        data class Arg(
            val name: String,
            val doc: String,
            val type: String,
            val pointer: String? = null,
            val optional: Boolean = false,
        )
    }

    @Serializable
    data class Callback(
        val name: String,
        val doc: String,
        val style: String,
        val args: List<Arg>,
    ) {
        @Serializable
        data class Arg(
            val name: String,
            val doc: String,
            val type: String,
            val pointer: String? = null,
        )
    }

    @Serializable
    data class Struct(
        val name: String,
        val doc: String,
        val type: String,
        val members: List<Member>,
        val free_members: Boolean = false,
        val extends: List<String> = listOf()
    ) {
        @Serializable
        data class Member(
            val name: String,
            val doc: String,
            val type: String,
            val optional: Boolean = false,
            val pointer: String? = null
        )
    }

    @Serializable
    data class Bitflag(
        val name: String,
        val doc: String,
        val entries: List<Entry>,
    ) {
        @Serializable
        data class Entry(
            val name: String,
            val doc: String,
            val value_combination: List<String>? = null,
        )
    }

    @Serializable
    data class Constant(
        val name: String,
        val value: String,
        val doc: String
    )

    @Serializable
    data class Enum(
        val name: String,
        val doc: String,
        @Transient val entries: List<Entry> = listOf(),
    ) {
        @Serializable
        data class Entry(
            val name: String,
            val doc: String,
            val value: Int? = null,
        )
    }
}