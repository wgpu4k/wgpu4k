import {
    renderContext,
    renderKt
} from "../build/compileSync/js/main/developmentExecutable/kotlin/wgpu4k-root-webgpu-gltf2.mjs"

(async () => {

    var file = "https://cdn.willusher.io/glb/DamagedHelmet.glb"
    var glbFile = await fetch(file)
        .then(res => res.arrayBuffer());

    var context = await renderContext(glbFile);

    const renderJs = async () => {
        renderKt(context)
        requestAnimationFrame(renderJs);
    };
    requestAnimationFrame(renderJs);
})();

