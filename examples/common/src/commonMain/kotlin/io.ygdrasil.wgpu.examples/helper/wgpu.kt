package io.ygdrasil.wgpu.examples.helper

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.BindGroupDescriptor.BindGroupResource


// Definitions related to bind group cluster
data class BindGroupCluster(
    val bindGroups: List<BindGroup>,
    val bindGroupLayout: BindGroupLayout
)

data class BindGroupClusterDescriptor(
    val bindings: Int,
    val visibilities: Set<ShaderStage>,
    val bindingType: BindGroupLayoutDescriptor.Entry.BindingType,
)

fun Device.createBindGroupCluster(
    inputs: List<BindGroupClusterDescriptor>,
    resources: List<List<BindGroupResource>>,
    label: String
): BindGroupCluster {

    val bindGroupLayout = createBindGroupLayout(
        BindGroupLayoutDescriptor(
            label = "$label.bindGroupLayout",
            entries = inputs.map {
                BindGroupLayoutDescriptor.Entry(
                    it.bindings,
                    it.visibilities,
                    it.bindingType
                )
            }.toTypedArray()
        )
    )

    val bindGroups = resources.mapIndexed { resourceGroupIndex, bindGroupResources ->
        val groupEntries = bindGroupResources.mapIndexed { index, bindGroupResource ->
            BindGroupDescriptor.BindGroupEntry(
                binding = index,
                resource = bindGroupResource
            )
        }
        createBindGroup(
            BindGroupDescriptor(
                bindGroupLayout,
                groupEntries.toTypedArray(),
                "$label.bindGroup$resourceGroupIndex"
            )
        )
    }

    return BindGroupCluster(
        bindGroups,
        bindGroupLayout
    )
}