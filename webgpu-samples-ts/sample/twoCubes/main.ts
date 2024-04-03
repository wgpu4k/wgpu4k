import {io} from "../../out/kotlin-libs/wgpu4k-webgpu-samples-ts";
import SceneClass = io.ygdrasil.wgpu.examples.scenes.basic.TwoCubesScene;
import jsApplication = io.ygdrasil.wgpu.examples.jsApplication;

const canvas = document.querySelector('canvas') as HTMLCanvasElement;
const application = await jsApplication(canvas, '../../assets/img/')
application.changeScene(new SceneClass())
application.run()
