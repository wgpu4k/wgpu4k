import {
    GLTFAccessor,
    GLTFBuffer,
    GLTFBufferView,
    GLTFMaterial,
    GLTFMesh,
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

const GLTFComponentType = {
    BYTE: 5120,
    UNSIGNED_BYTE: 5121,
    SHORT: 5122,
    UNSIGNED_SHORT: 5123,
    INT: 5124,
    UNSIGNED_INT: 5125,
    FLOAT: 5126,
    DOUBLE: 5130,
};

const GLTFTextureFilter = {
    NEAREST: 9728,
    LINEAR: 9729,
    NEAREST_MIPMAP_NEAREST: 9984,
    LINEAR_MIPMAP_NEAREST: 9985,
    NEAREST_MIPMAP_LINEAR: 9986,
    LINEAR_MIPMAP_LINEAR: 9987,
};

export class GLTFPrimitive {
    constructor(indices, positions, normals, texcoords, material, topology) {
        this.indices = indices;
        this.positions = positions;
        this.normals = normals;
        this.texcoords = texcoords;
        this.material = material;
        this.topology = topology;
    }

    // Build the primitive render commands into the bundle
    buildRenderBundle(
        device, shaderCache, bindGroupLayouts, bundleEncoder, swapChainFormat, depthFormat) {
        var shaderModule = shaderCache.getShader(
            this.normals, this.texcoords.length > 0, this.material.baseColorTexture);

        var vertexBuffers = [{
            arrayStride: this.positions.byteStride,
            attributes: [{format: 'float32x3', offset: 0, shaderLocation: 0}]
        }];

        if (this.normals) {
            vertexBuffers.push({
                arrayStride: this.normals.byteStride,
                attributes: [{format: 'float32x3', offset: 0, shaderLocation: 1}]
            });
        }

        // TODO: Multi-texturing
        if (this.texcoords.length > 0) {
            vertexBuffers.push({
                arrayStride: this.texcoords[0].byteStride,
                attributes: [{format: 'float32x2', offset: 0, shaderLocation: 2}]
            });
        }

        var layout = device.createPipelineLayout({
            bindGroupLayouts:
                [bindGroupLayouts[0], bindGroupLayouts[1], this.material.bindGroupLayout],
        });

        var vertexStage = {
            module: shaderModule,
            entryPoint: 'vertex_main',
            buffers: vertexBuffers
        };
        var fragmentStage = {
            module: shaderModule,
            entryPoint: 'fragment_main',
            targets: [{format: swapChainFormat}]
        };

        var primitive = {topology: 'triangle-list'};
        if (this.topology == GLTFRenderMode.TRIANGLE_STRIP) {
            primitive.topology = 'triangle-strip';
            primitive.stripIndexFormat =
                this.indices.componentType == GLTFComponentType.UNSIGNED_SHORT ? 'uint16'
                    : 'uint32';
        }

        var pipelineDescriptor = {
            layout: layout,
            vertex: vertexStage,
            fragment: fragmentStage,
            primitive: primitive,
            depthStencil: {format: depthFormat, depthWriteEnabled: true, depthCompare: 'less'}
        };

        var renderPipeline = device.createRenderPipeline(pipelineDescriptor);

        bundleEncoder.setBindGroup(2, this.material.bindGroup);
        bundleEncoder.setPipeline(renderPipeline);
        bundleEncoder.setVertexBuffer(0,
            this.positions.view.gpuBuffer,
            this.positions.byteOffset,
            this.positions.length);
        if (this.normals) {
            bundleEncoder.setVertexBuffer(
                1, this.normals.view.gpuBuffer, this.normals.byteOffset, this.normals.length);
        }
        if (this.texcoords.length > 0) {
            bundleEncoder.setVertexBuffer(2,
                this.texcoords[0].view.gpuBuffer,
                this.texcoords[0].byteOffset,
                this.texcoords[0].length);
        }
        if (this.indices) {
            var indexFormat = this.indices.componentType == GLTFComponentType.UNSIGNED_SHORT
                ? 'uint16'
                : 'uint32';
            bundleEncoder.setIndexBuffer(this.indices.view.gpuBuffer,
                indexFormat,
                this.indices.byteOffset,
                this.indices.length);
            bundleEncoder.drawIndexed(this.indices.count);
        } else {
            bundleEncoder.draw(this.positions.count);
        }
    }
}

export class GLTFMaterial2 {
    constructor(material, textures) {
        this.baseColorFactor = [1, 1, 1, 1];
        this.baseColorTexture = null;
        // padded to float4
        this.emissiveFactor = [0, 0, 0, 1];
        this.metallicFactor = 1.0;
        this.roughnessFactor = 1.0;

        if (material['pbrMetallicRoughness'] !== undefined) {
            var pbr = material['pbrMetallicRoughness'];
            if (pbr['baseColorFactor'] !== undefined) {
                this.baseColorFactor = pbr['baseColorFactor'];
            }
            if (pbr['baseColorTexture'] !== undefined) {
                // TODO multiple texcoords
                this.baseColorTexture = textures[pbr['baseColorTexture']['index']];
            }
            if (pbr['metallicFactor'] !== undefined) {
                this.metallicFactor = pbr['metallicFactor'];
            }
            if (pbr['roughnessFactor'] !== undefined) {
                this.roughnessFactor = pbr['roughnessFactor'];
            }
        }
        if (material['emissiveFactor'] !== undefined) {
            this.emissiveFactor[0] = material['emissiveFactor'][0];
            this.emissiveFactor[1] = material['emissiveFactor'][1];
            this.emissiveFactor[2] = material['emissiveFactor'][2];
        }

        this.gpuBuffer = null;
        this.bindGroupLayout = null;
        this.bindGroup = null;
    }

    upload(device) {
        var buf = device.createBuffer(
            {size: 3 * 4 * 4, usage: GPUBufferUsage.UNIFORM, mappedAtCreation: true});
        var mappingView = new Float32Array(buf.getMappedRange());
        mappingView.set(this.baseColorFactor);
        mappingView.set(this.emissiveFactor, 4);
        mappingView.set([this.metallicFactor, this.roughnessFactor], 8);
        buf.unmap();
        this.gpuBuffer = buf;

        var layoutEntries =
            [{binding: 0, visibility: GPUShaderStage.FRAGMENT, buffer: {type: 'uniform'}}];
        var bindGroupEntries = [{
            binding: 0,
            resource: {
                buffer: this.gpuBuffer,
            }
        }];

        if (this.baseColorTexture) {
            // Defaults for sampler and texture are fine, just make the objects
            // exist to pick them up
            layoutEntries.push({binding: 1, visibility: GPUShaderStage.FRAGMENT, sampler: {}});
            layoutEntries.push({binding: 2, visibility: GPUShaderStage.FRAGMENT, texture: {}});

            bindGroupEntries.push({
                binding: 1,
                resource: this.baseColorTexture.sampler,
            });
            bindGroupEntries.push({
                binding: 2,
                resource: this.baseColorTexture.imageView,
            });
        }

        this.bindGroupLayout = device.createBindGroupLayout({entries: layoutEntries});

        this.bindGroup = device.createBindGroup({
            layout: this.bindGroupLayout,
            entries: bindGroupEntries,
        });
    }
}

export class GLTFSampler {
    constructor(sampler, device) {
        var magFilter = sampler['magFilter'] === undefined ||
            sampler['magFilter'] == GLTFTextureFilter.LINEAR
            ? 'linear'
            : 'nearest';
        var minFilter = sampler['minFilter'] === undefined ||
            sampler['minFilter'] == GLTFTextureFilter.LINEAR
            ? 'linear'
            : 'nearest';

        var wrapS = 'repeat';
        if (sampler['wrapS'] !== undefined) {
            if (sampler['wrapS'] == GLTFTextureFilter.REPEAT) {
                wrapS = 'repeat';
            } else if (sampler['wrapS'] == GLTFTextureFilter.CLAMP_TO_EDGE) {
                wrapS = 'clamp-to-edge';
            } else {
                wrapS = 'mirror-repeat';
            }
        }

        var wrapT = 'repeat';
        if (sampler['wrapT'] !== undefined) {
            if (sampler['wrapT'] == GLTFTextureFilter.REPEAT) {
                wrapT = 'repeat';
            } else if (sampler['wrapT'] == GLTFTextureFilter.CLAMP_TO_EDGE) {
                wrapT = 'clamp-to-edge';
            } else {
                wrapT = 'mirror-repeat';
            }
        }

        this.sampler = device.createSampler({
            magFilter: magFilter,
            minFilter: minFilter,
            addressModeU: wrapS,
            addressModeV: wrapT,
        });
    }
}

export class GLTFTexture {
    constructor(sampler, image) {
        this.gltfsampler = sampler;
        this.sampler = sampler.sampler;
        this.image = image;
        this.imageView = image.createView();
    }
}


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
        materials.push(new GLTFMaterial2(glbJsonData['materials'][i], textures));
    }

    var meshes = [];
    for (var i = 0; i < glbJsonData.meshes.length; ++i) {
        var mesh = glbJsonData.meshes[i];

        var primitives = [];
        for (var j = 0; j < mesh.primitives.length; ++j) {
            var prim = mesh.primitives[j];
            var topology = prim['mode'];
            // Default is triangles if mode specified
            if (topology === undefined) {
                topology = GLTFRenderMode.TRIANGLES;
            }
            if (topology != GLTFRenderMode.TRIANGLES &&
                topology != GLTFRenderMode.TRIANGLE_STRIP) {
                alert('Ignoring primitive with unsupported mode ' + prim['mode']);
                continue;
            }

            var indices = null;
            if (glbJsonData['accessors'][prim['indices']] !== undefined) {
                var accessor = glbJsonData['accessors'][prim['indices']];
                var viewID = accessor['bufferView'];
                bufferViews[viewID].needsUpload = true;
                bufferViews[viewID].addUsage(GPUBufferUsage.INDEX);
                indices = new GLTFAccessor(bufferViews[viewID], accessor);
            }

            var positions = null;
            var normals = null;
            var texcoords = [];
            for (var attr in prim['attributes']) {
                var accessor = glbJsonData['accessors'][prim['attributes'][attr]];
                var viewID = accessor['bufferView'];
                bufferViews[viewID].needsUpload = true;
                bufferViews[viewID].addUsage(GPUBufferUsage.VERTEX);
                if (attr == 'POSITION') {
                    positions = new GLTFAccessor(bufferViews[viewID], accessor);
                } else if (attr == 'NORMAL') {
                    normals = new GLTFAccessor(bufferViews[viewID], accessor);
                } else if (attr.startsWith('TEXCOORD')) {
                    texcoords.push(new GLTFAccessor(bufferViews[viewID], accessor));
                }
            }

            var material = null;
            if (prim['material'] !== undefined) {
                material = materials[prim['material']];
            } else {
                material = defaultMaterial;
            }

            var gltfPrim =
                new GLTFPrimitive(indices, positions, normals, texcoords, material, topology);
            primitives.push(gltfPrim);
        }
        meshes.push(new GLTFMesh(mesh['name'], primitives));
    }

    // Upload the different views used by meshes
    for (var i = 0; i < bufferViews.length; ++i) {
        if (bufferViews[i].needsUpload) {
            bufferViews[i].upload(device);
        }
    }

    defaultMaterial.upload(device);
    for (var i = 0; i < materials.length; ++i) {
        materials[i].upload(device);
    }

    return uploadGLBModelKt(glbJsonData, meshes, device)
}
