import {
    getProjectionMatrix,
    getTransformationMatrix,
    GLBShaderCache,
    uploadGLBModel
} from "../build/compileSync/js/main/developmentExecutable/kotlin/wgpu4k-root-webgpu-gltf2.mjs"

(async () => {

    var adapter = await navigator.gpu.requestAdapter();
    var device = await adapter.requestDevice();


    var file = "https://cdn.willusher.io/glb/DamagedHelmet.glb"
    //file = "Box2.glb"
    var glbFile =
        await fetch(
            file)
            .then(res => res.arrayBuffer().then(buf => uploadGLBModel(device, buf)));

    var canvas = document.getElementById("webgpu-canvas");
    var context = canvas.getContext("webgpu");
    var swapChainFormat = "bgra8unorm";
    context.configure(
        {device: device, format: swapChainFormat, usage: GPUTextureUsage.RENDER_ATTACHMENT});

    var depthTexture = device.createTexture({
        size: {width: canvas.width, height: canvas.height, depthOrArrayLayers: 1},
        format: "depth24plus-stencil8",
        usage: GPUTextureUsage.RENDER_ATTACHMENT
    });

    var renderPassDesc = {
        colorAttachments: [{view: undefined, loadOp: "clear", clearValue: [0.3, 0.3, 0.3, 1], storeOp: "store"}],
        depthStencilAttachment: {
            view: depthTexture.createView(),
            depthLoadOp: "clear",
            depthClearValue: 1,
            depthStoreOp: "store",
            stencilLoadOp: "clear",
            stencilClearValue: 0,
            stencilStoreOp: "store"
        }
    };

    var viewParamsLayout = device.createBindGroupLayout({
        entries: [{binding: 0, visibility: GPUShaderStage.VERTEX, buffer: {type: "uniform"}}]
    });

    var viewParamBuf = device.createBuffer(
        {size: 4 * 4 * 4, usage: GPUBufferUsage.UNIFORM | GPUBufferUsage.COPY_DST});
    var viewParamsBindGroup = device.createBindGroup(
        {layout: viewParamsLayout, entries: [{binding: 0, resource: {buffer: viewParamBuf}}]});

    var shaderCache = new GLBShaderCache(device);

    var renderBundles = glbFile.buildRenderBundles(
        device, shaderCache, viewParamsLayout, viewParamsBindGroup, swapChainFormat);

    var projectionMatrix = getProjectionMatrix(canvas.width, canvas.height)

    var frame = 0

    const render = async () => {
        frame = frame + 1
        renderPassDesc.colorAttachments[0].view = context.getCurrentTexture().createView();

        var commandEncoder = device.createCommandEncoder();

        var transformationMatrix = getTransformationMatrix(
            frame / 120,
            projectionMatrix
        )

        device.queue.writeBuffer(
            viewParamBuf,
            0,
            transformationMatrix,
            0,
            transformationMatrix.size
        )

        var renderPass = commandEncoder.beginRenderPass(renderPassDesc);
        renderPass.executeBundles(renderBundles);

        renderPass.end();
        device.queue.submit([commandEncoder.finish()]);

        requestAnimationFrame(render);
    };
    requestAnimationFrame(render);
})();

