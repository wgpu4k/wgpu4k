import {Transform} from './engine/core/transform.js';
import {Camera} from './engine/core/camera.js';
import {AmbientLight, DirectionalLight, ShadowCastingLight} from './engine/core/light.js';
import {GltfLoader} from './engine/loaders/gltf.js';
import {WebGPUWorld} from './engine/webgpu/webgpu-world.js';
import {MouseState} from './engine/core/input.js';

import {BallSystem} from './ball.js';
import {GameState, PlayerSystem} from './player.js';
import {StageSystem} from './stage.js';
import {Physics2DSystem} from './physics-2d.js';
import {ImpactDamageSystem} from './impact-damage.js';
import {DeadSystem, LifetimeHealthSystem} from './lifetime.js';

import {quat} from 'gl-matrix';

export {
  BloomBlendFragmentSource,
  BloomBlurHorizontalFragmentSource,
  BloomBlurVerticalFragmentSource,
  TextureAtlasAllocator
} from '../wgpu4k-root-examples-spookyball.mjs'


const canvas = document.querySelector('canvas');

const world = new WebGPUWorld(canvas)
  .registerSystem(Physics2DSystem)
  .registerSystem(ImpactDamageSystem)
  .registerSystem(LifetimeHealthSystem)
  .registerSystem(DeadSystem)
  ;

world.singleton.add(new GameState());

let mouse = world.singleton.get(MouseState);
mouse.element = canvas;

let renderer;
try {
  renderer = await world.renderer();
} catch(error) {
  document.querySelector('.container').classList.add('error');
  const errorElement = document.querySelector('#score-display');

  errorElement.innerHTML = `Your browser doesn't appear to support WebGPU. (Scary!)<br>
This game requires WebGPU support.`;

  throw error;
}

const gltfLoader = new GltfLoader(renderer);

world.registerRenderSystem(StageSystem, gltfLoader);
world.registerRenderSystem(BallSystem, gltfLoader);
world.registerRenderSystem(PlayerSystem, gltfLoader);

const projection = new Camera();
projection.zNear = 1;
projection.zFar = 1024;

const cameraOrientation = quat.create();
quat.rotateX(cameraOrientation, cameraOrientation, Math.PI * -0.42);

const camera = world.create(
  new Transform({ position: [0, 36, 12], orientation: cameraOrientation }),
  projection
);

// Add some lights
const moonlight = new DirectionalLight({
  direction: [0.3, 0.3, 0.5],
  color: [0.75, 0.8, 1.0],
  intensity: 1.8
});

world.create(
  // Spooky moonlight
  moonlight,
  new ShadowCastingLight({ textureSize: 1024, width: 75, height: 50, zNear: 0.1, up: [0, 1, 0] }),
  new Transform({ position: [26, 25, 36] }),
  new AmbientLight(0.075, 0.075, 0.075)
);

function onFrame(t) {
  requestAnimationFrame(onFrame);

  world.execute();
}
requestAnimationFrame(onFrame);