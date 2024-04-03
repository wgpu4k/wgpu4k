import {io} from "../../out/kotlin-libs/wgpu4k-webgpu-samples-ts";
import jsApplication = io.ygdrasil.wgpu.examples.jsApplication;
import CubemapScene = io.ygdrasil.wgpu.examples.scenes.basic.CubemapScene;

const canvas = document.querySelector('canvas') as HTMLCanvasElement;
const application = await jsApplication(canvas, '../../assets/img/')
application.changeScene(new CubemapScene())
application.run()
