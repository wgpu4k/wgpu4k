package domain

import com.charleskorn.kaml.YamlList
import com.charleskorn.kaml.YamlNode
import com.charleskorn.kaml.YamlNull
import com.charleskorn.kaml.yamlMap
import com.charleskorn.kaml.yamlScalar
import kotlinx.serialization.Serializable

@Serializable
data class YamlModel(
    val copyright: String,
    val name: String,
    val enum_prefix: String,
    val constants: List<Constant>,
    val typedefs: List<String>,
    val bitflags: List<Bitflag>,
    val structs: List<Struct>,
    val functions: List<Function>,
    val objects: List<Object>,
    val enums: List<Enum>,
    // Used on v22
    val function_types: List<FunctionType> = listOf(),
    // Used on v23
    val callbacks: List<Callback> = listOf(),
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
            objects = (this.objects + loadExtraYaml.objects).distinctBy { it.name },
            function_types = (this.function_types + loadExtraYaml.function_types).distinctBy { it.name },
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
        val returns_async: List<Arg>? = null,
        val returns: Return? = null,
        val callback: String? = null,
    ) {
        @Serializable
        data class Return(
            val type: String,
            val doc: String,
            val passed_with_ownership: Boolean = false,
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
    data class FunctionType(
        val name: String,
        val doc: String,
        var args: List<Arg>
    ) {
        @Serializable
        data class Arg(
            val name: String,
            val doc: String,
            val type: String,
            val pointer: String? = null,
        )
    }

    /**
     * Model v23
     */
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
        val members: List<Member> = listOf(),
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
        val entries: YamlList,
    ) {

        val values: List<Entry>
            get() = entries.items
                .filter { entry -> entry !is YamlNull }
                .map { entry ->
                    val name = entry.yamlMap.get<YamlNode>("name")!!.yamlScalar.content
                    val doc = entry.yamlMap.get<YamlNode>("doc")!!.yamlScalar.content
                    val value = entry.yamlMap.get<YamlNode>("value")?.yamlScalar?.content
                        ?.substringAfter("x")?.toInt(radix = 16)
                    Entry(name, doc, value)
                }

        data class Entry(
            val name: String,
            val doc: String,
            val value: Int? = null,
        )
    }
}