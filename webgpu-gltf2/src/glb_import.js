import {
    GLTFBuffer,
    uploadGLBModelKt
} from "../build/compileSync/js/main/developmentExecutable/kotlin/wgpu4k-root-webgpu-gltf2.mjs"


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

    return await uploadGLBModelKt(glbJsonData, device, glbBuffer, buffer)
}
