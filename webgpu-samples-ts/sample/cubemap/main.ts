import {io} from "../../out/kotlin-libs/wgpu4k-webgpu-samples-ts";
import jsApplication = io.ygdrasil.wgpu.examples.jsApplication;
import CubemapScene = io.ygdrasil.wgpu.examples.scenes.basic.CubemapScene;

const canvas = document.querySelector('canvas') as HTMLCanvasElement;
const application = await jsApplication(canvas, '../../assets/img/')
const scene = new CubemapScene();
application.changeScene(scene)


const device = application.device.handler;


const imageBitmaps = [
    application.cubemapPosx,
    application.cubemapNegx,
    application.cubemapPosy,
    application.cubemapNegy,
    application.cubemapPosz,
    application.cubemapNegz,
];

const cubemapTexture = (scene.cubemapTexture as any).handler_1;

/*for (let i = 0; i < imageBitmaps.length; i++) {
    const imageBitmap = imageBitmaps[i].handler_1;
    device.queue.copyExternalImageToTexture(
        {source: imageBitmap},
        {texture: cubemapTexture, origin: [0, 0, i]},
        [imageBitmap.width, imageBitmap.height]
    );
}*/


function frame() {
    application.frame += 5;
    scene.render(application)
    requestAnimationFrame(frame);
}

requestAnimationFrame(frame);
