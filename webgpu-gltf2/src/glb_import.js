import {
    GLTFBuffer,
    GLTFBufferView,
    GLTFMaterial,
    GLTFSampler,
    GLTFTexture,
    uploadGLBModelKt
} from "../build/compileSync/js/main/developmentExecutable/kotlin/wgpu4k-root-webgpu-gltf2.mjs"

const GLTFRenderMode = {
    POINTS: 0,
    LINE: 1,
    LINE_LOOP: 2,
    LINE_STRIP: 3,
    TRIANGLES: 4,
    TRIANGLE_STRIP: 5,
    // Note: fans are not supported in WebGPU, use should be
    // an error or converted into a list/strip
    TRIANGLE_FAN: 6,
};

// Upload a GLB model and return it
export async function uploadGLBModel(buffer, device) {
    // The file header and chunk 0 header
    // TODO: It sounds like the spec does allow for multiple binary chunks,
    // so then how do you know which chunk a buffer exists in? Maybe the buffer
    // id corresponds to the binary chunk ID? Would have to find info in the
    // spec or an example file to check this
    var header = new Uint32Array(buffer, 0, 5);
    if (header[0] != 0x46546C67) {
        alert('This does not appear to be a glb file?');
        return;
    }
    var glbJsonData =
        JSON.parse(new TextDecoder('utf-8').decode(new Uint8Array(buffer, 20, header[3])));

    var binaryHeader = new Uint32Array(buffer, 20 + header[3], 2);
    var glbBuffer = new GLTFBuffer(buffer, binaryHeader[0], 28 + header[3]);

    if (28 + header[3] + binaryHeader[0] != buffer.byteLength) {
        console.log('TODO: Multiple binary chunks in file');
    }

    // TODO: Later could look at merging buffers and actually using the starting
    // offsets, but want to avoid uploading the entire buffer since it may
    // contain packed images
    var bufferViews = [];
    for (var i = 0; i < glbJsonData.bufferViews.length; ++i) {
        bufferViews.push(new GLTFBufferView(glbBuffer, glbJsonData.bufferViews[i]));
    }

    var images = [];
    if (glbJsonData['images'] !== undefined) {
        for (var i = 0; i < glbJsonData['images'].length; ++i) {
            var imgJson = glbJsonData['images'][i];
            var imageView = new GLTFBufferView(
                glbBuffer, glbJsonData['bufferViews'][imgJson['bufferView']]);
            var imgBlob = new Blob([imageView.buffer], {type: imgJson['mime/type']});
            var img = await createImageBitmap(imgBlob);

            // TODO: For glTF we need to look at where an image is used to know
            // if it should be srgb or not. We basically need to pass through
            // the material list and find if the texture which uses this image
            // is used by a metallic/roughness param
            var gpuImg = device.createTexture({
                size: [img.width, img.height, 1],
                format: 'rgba8unorm-srgb',
                usage: GPUTextureUsage.TEXTURE_BINDING | GPUTextureUsage.COPY_DST |
                    GPUTextureUsage.RENDER_ATTACHMENT,
            });

            var src = {source: img};
            var dst = {texture: gpuImg};
            device.queue.copyExternalImageToTexture(src, dst, [img.width, img.height, 1]);

            images.push(gpuImg);
        }
    }

    var defaultSampler = new GLTFSampler({}, device);
    var samplers = [];
    if (glbJsonData['samplers'] !== undefined) {
        for (var i = 0; i < glbJsonData['samplers'].length; ++i) {
            samplers.push(new GLTFSampler(glbJsonData['samplers'][i], device));
        }
    }

    var textures = [];
    if (glbJsonData['textures'] !== undefined) {
        for (var i = 0; i < glbJsonData['textures'].length; ++i) {
            var tex = glbJsonData['textures'][i];
            var sampler =
                tex['sampler'] !== undefined ? samplers[tex['sampler']] : defaultSampler;
            textures.push(new GLTFTexture(sampler, images[tex['source']]));
        }
    }

    var defaultMaterial = new GLTFMaterial({});
    var materials = [];
    for (var i = 0; i < glbJsonData['materials'].length; ++i) {
        materials.push(new GLTFMaterial(glbJsonData['materials'][i], textures));
    }

    return uploadGLBModelKt(glbJsonData, device, materials, defaultMaterial, bufferViews)
}
