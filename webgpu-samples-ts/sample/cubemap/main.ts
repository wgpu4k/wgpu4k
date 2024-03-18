import {cubePositionOffset, cubeUVOffset, cubeVertexArray, cubeVertexCount, cubeVertexSize,} from '../../meshes/cube';

import basicVertWGSL from '../../shaders/basic.vert.wgsl';
import sampleCubemapWGSL from './sampleCubemap.frag.wgsl';
import {io} from "../../out/kotlin-libs/wgpu4k-webgpu-samples-ts";
import jsApplication = io.ygdrasil.wgpu.examples.jsApplication;
import CubemapScene = io.ygdrasil.wgpu.examples.scenes.basic.CubemapScene;

const canvas = document.querySelector('canvas') as HTMLCanvasElement;
const application = await jsApplication(canvas, '../../assets/img/')
const scene = new CubemapScene();
application.changeScene(scene)


const device = application.device.handler;

const context = canvas.getContext('webgpu') as GPUCanvasContext;

const presentationFormat = navigator.gpu.getPreferredCanvasFormat();


// Create a vertex buffer from the cube data.
const verticesBuffer = device.createBuffer({
    size: cubeVertexArray.byteLength,
    usage: GPUBufferUsage.VERTEX,
    mappedAtCreation: true,
});
new Float32Array(verticesBuffer.getMappedRange()).set(cubeVertexArray);
verticesBuffer.unmap();

const pipeline = device.createRenderPipeline({
    layout: 'auto',
    vertex: {
        module: device.createShaderModule({
            code: basicVertWGSL,
        }),
        buffers: [
            {
                arrayStride: cubeVertexSize,
                attributes: [
                    {
                        // position
                        shaderLocation: 0,
                        offset: cubePositionOffset,
                        format: 'float32x4',
                    },
                    {
                        // uv
                        shaderLocation: 1,
                        offset: cubeUVOffset,
                        format: 'float32x2',
                    },
                ],
            },
        ],
    },
    fragment: {
        module: device.createShaderModule({
            code: sampleCubemapWGSL,
        }),
        targets: [
            {
                format: presentationFormat,
            },
        ],
    },
    primitive: {
        topology: 'triangle-list',

        // Since we are seeing from inside of the cube
        // and we are using the regular cube geomtry data with outward-facing normals,
        // the cullMode should be 'front' or 'none'.
        cullMode: 'none',
    },

    // Enable depth testing so that the fragment closest to the camera
    // is rendered in front.
    depthStencil: {
        depthWriteEnabled: true,
        depthCompare: 'less',
        format: 'depth24plus',
    },
});

const depthTexture = device.createTexture({
    size: [canvas.width, canvas.height],
    format: 'depth24plus',
    usage: GPUTextureUsage.RENDER_ATTACHMENT,
});

// Fetch the 6 separate images for negative/positive x, y, z axis of a cubemap
// and upload it into a GPUTexture.
let cubemapTexture: GPUTexture;
{
    // The order of the array layers is [+X, -X, +Y, -Y, +Z, -Z]
    const imgSrcs = [
        '../../assets/img/cubemap/posx.jpg',
        '../../assets/img/cubemap/negx.jpg',
        '../../assets/img/cubemap/posy.jpg',
        '../../assets/img/cubemap/negy.jpg',
        '../../assets/img/cubemap/posz.jpg',
        '../../assets/img/cubemap/negz.jpg',
    ];
    const promises = imgSrcs.map(async (src) => {
        const response = await fetch(src);
        return createImageBitmap(await response.blob());
    });
    const imageBitmaps = await Promise.all(promises);

    cubemapTexture = device.createTexture({
        dimension: '2d',
        // Create a 2d array texture.
        // Assume each image has the same size.
        size: [imageBitmaps[0].width, imageBitmaps[0].height, 6],
        format: 'rgba8unorm',
        usage:
            GPUTextureUsage.TEXTURE_BINDING |
            GPUTextureUsage.COPY_DST |
            GPUTextureUsage.RENDER_ATTACHMENT,
    });

    for (let i = 0; i < imageBitmaps.length; i++) {
        const imageBitmap = imageBitmaps[i];
        device.queue.copyExternalImageToTexture(
            {source: imageBitmap},
            {texture: cubemapTexture, origin: [0, 0, i]},
            [imageBitmap.width, imageBitmap.height]
        );
    }
}

const uniformBufferSize = 4 * 16; // 4x4 matrix
const uniformBuffer = (scene.uniformBuffer as any).handler_1;

const sampler = device.createSampler({
    magFilter: 'linear',
    minFilter: 'linear',
});

const uniformBindGroup = device.createBindGroup({
    layout: pipeline.getBindGroupLayout(0),
    entries: [
        {
            binding: 0,
            resource: {
                buffer: uniformBuffer,
                offset: 0,
                size: uniformBufferSize,
            },
        },
        {
            binding: 1,
            resource: sampler,
        },
        {
            binding: 2,
            resource: cubemapTexture.createView({
                dimension: 'cube',
            }),
        },
    ],
});

const renderPassDescriptor: GPURenderPassDescriptor = {
    colorAttachments: [
        {
            view: undefined, // Assigned later
            loadOp: 'clear',
            storeOp: 'store',
        },
    ],
    depthStencilAttachment: {
        view: depthTexture.createView(),

        depthClearValue: 1.0,
        depthLoadOp: 'clear',
        depthStoreOp: 'store',
    },
};


function frame() {
    application.frame += 10;
    const transformationMatrix = scene.getTransformationMatrix(
        application.frame / 100.0,
        scene.projectionMatrix
    )
    application.device.queue.writeBuffer(
        scene.uniformBuffer,
        0,
        transformationMatrix,
        0,
        transformationMatrix.length
    )

    renderPassDescriptor.colorAttachments[0].view = context
        .getCurrentTexture()
        .createView();

    const commandEncoder = device.createCommandEncoder();
    const passEncoder = commandEncoder.beginRenderPass(renderPassDescriptor);
    passEncoder.setPipeline(pipeline);
    passEncoder.setVertexBuffer(0, verticesBuffer);
    passEncoder.setBindGroup(0, uniformBindGroup);
    passEncoder.draw(cubeVertexCount);
    passEncoder.end();
    device.queue.submit([commandEncoder.finish()]);

    requestAnimationFrame(frame);
}

requestAnimationFrame(frame);
