package io.ygdrasil.wgpu

class WGPU(val handler: WGPUInstance): AutoCloseable {

    fun requestAdapter(
        renderingContext: RenderingContext,
        powerPreference: WGPUPowerPreference = WGPUPowerPreference_Undefined
    ): Adapter? {
        val options = cValue<WGPURequestAdapterOptions> {
            compatibleSurface = renderingContext.handler
            this.powerPreference = powerPreference
        }

        val handleRequestAdapter:WGPURequestAdapterCallback =
            staticCFunction<WGPURequestAdapterStatus, WGPUAdapter, CPointer<ByteVar>?, COpaquePointer, Unit> { status, adapter, message, _ ->
                if (status == WGPURequestAdapterStatus_Success) {
                    adapterState.update { adapter }
                } else {
                    println("request_adapter status=$status message=${message?.toKStringFromUtf8()}\n")
                }

            }.reinterpret()

        wgpuInstanceRequestAdapter(handler, options, handleRequestAdapter, null)

        return adapterState.value?.let { Adapter(it) }
    }

    fun getSurfaceFromMetalLayer(metalLayer: COpaquePointer): WGPUSurface? = memScoped {
        val surfaceDescriptor = cValue<WGPUSurfaceDescriptor> {
            nextInChain = cValue<WGPUSurfaceDescriptorFromMetalLayer> {
                chain.sType = WGPUSType_SurfaceDescriptorFromMetalLayer
                layer = metalLayer
            }.ptr.reinterpret()
        }

        return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
    }

    companion object {

        fun createInstance(backend: WGPUInstanceBackend? = null): WGPU? = memScoped {
            wgpuCreateInstance(getDescriptor(backend))
                ?.let { WGPU(it) }
        }

        private fun MemScope.getDescriptor(backend: WGPUInstanceBackend?): CValue<WGPUInstanceDescriptor>? {
            if (backend == null) return null

            val descriptor = cValue<WGPUInstanceDescriptor> {
                nextInChain = cValue<WGPUInstanceExtras> {
                    chain.sType = WGPUSType_InstanceExtras
                    backends = backend
                }.ptr.reinterpret()
            }

            return descriptor
        }
    }

    override fun close() {
        wgpuInstanceRelease(handler)
    }
}