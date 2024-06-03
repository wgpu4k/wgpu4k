import {convertGLBToJSONAndBinary, GLTFSkin} from './glbUtils';
import gltfWGSL from './gltf.wgsl';
import {Mat4, mat4, Quat, vec3} from 'wgpu-matrix';
import {createBindGroupCluster} from '../bitonicSort/utils';

const MAT4X4_BYTES = 64;

interface BoneObject {
    transforms: Mat4[];
    bindPoses: Mat4[];
    bindPosesInv: Mat4[];
}

// Copied from toji/gl-matrix
const getRotation = (mat: Mat4): Quat => {
    // Initialize our output quaternion
    const out = [0, 0, 0, 0];
    // Extract the scaling factor from the final matrix transformation
    // to normalize our rotation;
    const scaling = mat4.getScaling(mat);
    const is1 = 1 / scaling[0];
    const is2 = 1 / scaling[1];
    const is3 = 1 / scaling[2];

    // Scale the matrix elements by the scaling factors
    const sm11 = mat[0] * is1;
    const sm12 = mat[1] * is2;
    const sm13 = mat[2] * is3;
    const sm21 = mat[4] * is1;
    const sm22 = mat[5] * is2;
    const sm23 = mat[6] * is3;
    const sm31 = mat[8] * is1;
    const sm32 = mat[9] * is2;
    const sm33 = mat[10] * is3;

    // The trace of a square matrix is the sum of its diagonal entries
    // While the matrix trace has many interesting mathematical properties,
    // the primary purpose of the trace is to assess the characteristics of the rotation.
    const trace = sm11 + sm22 + sm33;
    let S = 0;

    // If all matrix elements contribute equally to the rotation.
    if (trace > 0) {
        S = Math.sqrt(trace + 1.0) * 2;
        out[3] = 0.25 * S;
        out[0] = (sm23 - sm32) / S;
        out[1] = (sm31 - sm13) / S;
        out[2] = (sm12 - sm21) / S;
        // If the rotation is primarily around the x-axis
    } else if (sm11 > sm22 && sm11 > sm33) {
        S = Math.sqrt(1.0 + sm11 - sm22 - sm33) * 2;
        out[3] = (sm23 - sm32) / S;
        out[0] = 0.25 * S;
        out[1] = (sm12 + sm21) / S;
        out[2] = (sm31 + sm13) / S;
        // If rotation is primarily around the y-axis
    } else if (sm22 > sm33) {
        S = Math.sqrt(1.0 + sm22 - sm11 - sm33) * 2;
        out[3] = (sm31 - sm13) / S;
        out[0] = (sm12 + sm21) / S;
        out[1] = 0.25 * S;
        out[2] = (sm23 + sm32) / S;
        // If the rotation is primarily around the z-axis
    } else {
        S = Math.sqrt(1.0 + sm33 - sm11 - sm22) * 2;
        out[3] = (sm12 - sm21) / S;
        out[0] = (sm31 + sm13) / S;
        out[1] = (sm23 + sm32) / S;
        out[2] = 0.25 * S;
    }

    return out;
};

//Normal setup
const canvas = document.querySelector('canvas') as HTMLCanvasElement;
const adapter = await navigator.gpu.requestAdapter();
const device = await adapter.requestDevice();

const context = canvas.getContext('webgpu') as GPUCanvasContext;

const devicePixelRatio = window.devicePixelRatio || 1;
canvas.width = canvas.clientWidth * devicePixelRatio;
canvas.height = canvas.clientHeight * devicePixelRatio;
const presentationFormat = navigator.gpu.getPreferredCanvasFormat();

context.configure({
    device,
    format: presentationFormat,
    alphaMode: 'premultiplied',
});

const settings = {
    cameraX: 0,
    cameraY: -5.1,
    cameraZ: -14.6,
    objectScale: 1,
    angle: 0.2,
    speed: 50,
};

const depthTexture = device.createTexture({
    size: [canvas.width, canvas.height],
    format: 'depth24plus',
    usage: GPUTextureUsage.RENDER_ATTACHMENT,
});

const cameraBuffer = device.createBuffer({
    size: MAT4X4_BYTES * 3,
    usage: GPUBufferUsage.UNIFORM | GPUBufferUsage.COPY_DST,
});

const cameraBGCluster = createBindGroupCluster(
    [0],
    [GPUShaderStage.VERTEX],
    ['buffer'],
    [{type: 'uniform'}],
    [[{buffer: cameraBuffer}]],
    'Camera',
    device
);

const generalUniformsBuffer = device.createBuffer({
    size: Uint32Array.BYTES_PER_ELEMENT * 2,
    usage: GPUBufferUsage.UNIFORM | GPUBufferUsage.COPY_DST,
});

const generalUniformsBGCLuster = createBindGroupCluster(
    [0],
    [GPUShaderStage.VERTEX | GPUShaderStage.FRAGMENT],
    ['buffer'],
    [{type: 'uniform'}],
    [[{buffer: generalUniformsBuffer}]],
    'General',
    device
);

// Same bindGroupLayout as in main file.
const nodeUniformsBindGroupLayout = device.createBindGroupLayout({
    label: 'NodeUniforms.bindGroupLayout',
    entries: [
        {
            binding: 0,
            buffer: {
                type: 'uniform',
            },
            visibility: GPUShaderStage.VERTEX,
        },
    ],
});

// Fetch whale resources from the glb file
const whaleScene = await fetch('../../assets/gltf/whale.glb')
    .then((res) => res.arrayBuffer())
    .then((buffer) => convertGLBToJSONAndBinary(buffer, device));

// Builds a render pipeline for our whale mesh
// Since we are building a lightweight gltf parser around a gltf scene with a known
// quantity of meshes, we only build a renderPipeline for the singular mesh present
// within our scene. A more robust gltf parser would loop through all the meshes,
// cache replicated pipelines, and perform other optimizations.
whaleScene.meshes[0].buildRenderPipeline(
    device,
    gltfWGSL,
    gltfWGSL,
    presentationFormat,
    depthTexture.format,
    [
        cameraBGCluster.bindGroupLayout,
        generalUniformsBGCLuster.bindGroupLayout,
        nodeUniformsBindGroupLayout,
        GLTFSkin.skinBindGroupLayout,
    ]
);

// Global Calc
const aspect = canvas.width / canvas.height;
const perspectiveProjection = mat4.perspective(
    (2 * Math.PI) / 5,
    aspect,
    0.1,
    100.0
);

function getProjectionMatrix() {
    return perspectiveProjection as Float32Array;
}

function getViewMatrix() {
    const viewMatrix = mat4.identity();
    mat4.translate(
        viewMatrix,
        vec3.fromValues(settings.cameraX, settings.cameraY, settings.cameraZ),
        viewMatrix
    );
    return viewMatrix as Float32Array;
}

function getModelMatrix() {
    const modelMatrix = mat4.identity();
    const scaleVector = vec3.fromValues(
        settings.objectScale,
        settings.objectScale,
        settings.objectScale
    );
    mat4.scale(modelMatrix, scaleVector, modelMatrix);
    mat4.rotateY(modelMatrix, (Date.now() / 1000) * 0.5, modelMatrix);
    return modelMatrix as Float32Array;
}

// Pass Descriptor for GLTFs
const gltfRenderPassDescriptor: GPURenderPassDescriptor = {
    colorAttachments: [
        {
            view: undefined, // Assigned later

            clearValue: {r: 0.3, g: 0.3, b: 0.3, a: 1.0},
            loadOp: 'clear',
            storeOp: 'store',
        },
    ],
    depthStencilAttachment: {
        view: depthTexture.createView(),
        depthLoadOp: 'clear',
        depthClearValue: 1.0,
        depthStoreOp: 'store',
    },
};

// A map that maps a joint index to the original matrix transformation of a bone
const origMatrices = new Map<number, Mat4>();
const animWhaleSkin = (skin: GLTFSkin, angle: number) => {
    for (let i = 0; i < skin.joints.length; i++) {
        // Index into the current joint
        const joint = skin.joints[i];
        // If our map does
        if (!origMatrices.has(joint)) {
            origMatrices.set(joint, whaleScene.nodes[joint].source.getMatrix());
        }
        // Get the original position, rotation, and scale of the current joint
        const origMatrix = origMatrices.get(joint);
        let m = mat4.create();
        // Depending on which bone we are accessing, apply a specific rotation to the bone's original
        // transformation to animate it
        if (joint === 1 || joint === 0) {
            m = mat4.rotateY(origMatrix, -angle);
        } else if (joint === 3 || joint === 4) {
            m = mat4.rotateX(origMatrix, joint === 3 ? angle : -angle);
        } else {
            m = mat4.rotateZ(origMatrix, angle);
        }
        // Apply the current transformation to the transform values within the relevant nodes
        // (these nodes, of course, each being nodes that represent joints/bones)
        whaleScene.nodes[joint].source.position = mat4.getTranslation(m);
        whaleScene.nodes[joint].source.scale = mat4.getScaling(m);
        whaleScene.nodes[joint].source.rotation = getRotation(m);
    }
};

function frame() {
    // Calculate camera matrices
    const projectionMatrix = getProjectionMatrix();
    const viewMatrix = getViewMatrix();
    const modelMatrix = getModelMatrix();

    // Write to mvp to camera buffer
    device.queue.writeBuffer(
        cameraBuffer,
        0,
        projectionMatrix.buffer,
        projectionMatrix.byteOffset,
        projectionMatrix.byteLength
    );

    device.queue.writeBuffer(
        cameraBuffer,
        64,
        viewMatrix.buffer,
        viewMatrix.byteOffset,
        viewMatrix.byteLength
    );

    device.queue.writeBuffer(
        cameraBuffer,
        128,
        modelMatrix.buffer,
        modelMatrix.byteOffset,
        modelMatrix.byteLength
    );

    // Difference between these two render passes is just the presence of depthTexture
    gltfRenderPassDescriptor.colorAttachments[0].view = context
        .getCurrentTexture()
        .createView();

    // Update node matrixes
    for (const scene of whaleScene.scenes) {
        scene.root.updateWorldMatrix(device);
    }

    // Calculate bone transformation
    const t = (Date.now() / 20000) * settings.speed;
    // Updates skins (we index into skins in the renderer, which is not the best approach but hey)
    animWhaleSkin(whaleScene.skins[0], Math.sin(t) * settings.angle);
    // Node 6 should be the only node with a drawable mesh so hopefully this works fine
    whaleScene.skins[0].update(device, 6, whaleScene.nodes);

    const commandEncoder = device.createCommandEncoder();

    const passEncoder = commandEncoder.beginRenderPass(
        gltfRenderPassDescriptor
    );
    for (const scene of whaleScene.scenes) {
        scene.root.renderDrawables(passEncoder, [
            cameraBGCluster.bindGroups[0],
            generalUniformsBGCLuster.bindGroups[0],
        ]);
    }
    passEncoder.end();

    device.queue.submit([commandEncoder.finish()]);

    requestAnimationFrame(frame);
}

requestAnimationFrame(frame);
