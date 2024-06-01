import {mat4} from 'gl-matrix';
import {DEFAULT_SCALE, MyVector3, vec3TransformMat4} from "../../spookyball.js";

const DEFAULT_ORIENTATION = new Float32Array(0, 0, 0, 0);

export class Transform {
  #storage;
  #position;
  #orientation;
  #scale;
  #localMatrix;
  #worldMatrix;

  #localMatrixDirty = true;
  #worldMatrixDirty = true;
  #parent = null;
  #children;

  constructor(options = {}) {
    let buffer;
    let offset = 0;
    // Allocate storage for all the transform elements
    if (options.externalStorage) {
      buffer = options.externalStorage.buffer;
      offset = options.externalStorage.offset;
    } else {
      buffer = new Float32Array(42).buffer;
    }

    this.#position = new Float32Array(buffer, offset, 3);
    this.#orientation = new Float32Array(buffer, offset + 3 * Float32Array.BYTES_PER_ELEMENT, 4);
    this.#scale = new Float32Array(buffer, offset + 7 * Float32Array.BYTES_PER_ELEMENT, 3);
    this.#localMatrix = new Float32Array(buffer, offset + 10 * Float32Array.BYTES_PER_ELEMENT, 16);
    this.#worldMatrix = new Float32Array(buffer, offset + 26 * Float32Array.BYTES_PER_ELEMENT, 16);

    if (options.transform) {
      const storage = new Float32Array(this.#position.buffer, this.#position.byteOffset, 42);
      storage.set(new Float32Array(options.transform.#position.buffer, options.transform.#position.byteOffset, 42));
      this.#localMatrixDirty = options.transform.#localMatrixDirty;
    } else {
      if (options.position) {
        this.#position.set(options.position);
      }
      this.#orientation.set(options.orientation ? options.orientation : DEFAULT_ORIENTATION);
      this.#scale.set(options.scale ? options.scale : DEFAULT_SCALE.get().toJS32Array());
    }

    if (options.parent) {
      options.parent.addChild(this);
    }
  }

  get position() {
    this.#makeDirty();
    return this.#position;
  }
  set position(value) {
    this.#makeDirty();
    this.#position.set(value);
  }

  getWorldPosition(out, position) {
    if (position) {
      if (position != out) {
        out.set(position);
      }
    } else {
      new MyVector3(0, 0, 0).into(out);
    }
    vec3TransformMat4(out, out, this.worldMatrix);
  }

  get orientation() {
    this.#makeDirty();
    return this.#orientation;
  }
  set orientation(value) {
    this.#makeDirty();
    this.#orientation.set(value);
  }

  get scale() {
    this.#makeDirty();
    return this.#scale;
  }
  set scale(value) {
    this.#makeDirty();
    this.#scale.set(value);
  }

  get worldMatrix() {
    return this.#resolveWorldMatrix();
  }

  addChild(transform) {
    if (transform.parent && transform.parent != this) {
      transform.parent.removeChild(transform);
    }

    if (!this.#children) { this.#children = new Set(); }
    this.#children.add(transform);
    transform.#parent = this;
    transform.#makeDirty(false);
  }

  removeChild(transform) {
    const removed = this.#children?.delete(transform);
    if (removed) {
      transform.#parent = null;
      transform.#makeDirty(false);
    }
  }

  get children() {
    return this.#children?.values() || [];
  }

  get parent() {
    return this.#parent;
  }

  #makeDirty(markLocalDirty = true) {
    if (markLocalDirty) { this.#localMatrixDirty = true; }
    if (this.#worldMatrixDirty) { return; }
    this.#worldMatrixDirty = true;

    if (this.#children) {
      for (const child of this.#children) {
        child.#makeDirty(false);
      }
    }
  }

  #resolveLocalMatrix() {
    const wasDirty = this.#localMatrixDirty;
    if (this.#localMatrixDirty) {
      mat4.fromRotationTranslationScale(this.#localMatrix,
        this.#orientation,
        this.#position,
        this.#scale);
      this.#localMatrixDirty = false;
    }
    return this.#localMatrix;
  }

  #resolveWorldMatrix() {
    if (this.#worldMatrixDirty) {
      if (!this.parent) {
        this.#worldMatrix.set(this.#resolveLocalMatrix());
      } else {
        mat4.mul(this.#worldMatrix, this.parent.worldMatrix, this.#resolveLocalMatrix());
      }
      this.#worldMatrixDirty = false;
    }

    return this.#worldMatrix;
  }
}

export class TransformPool {
  #buffer;
  #transforms = [];

  constructor(size) {
    this.#buffer = new Float32Array(42 * size).buffer;

    for (let i = 0; i < size; ++i) {
      this.#transforms[i] = new Transform({
        externalStorage: {
          buffer: this.#buffer,
          offset: (i * 42 * Float32Array.BYTES_PER_ELEMENT),
        }
      });
    }
  }

  get size() {
    return this.#transforms.length;
  }

  getTransform(index) {
    return this.#transforms[index];
  }

  clone() {
    const out = new TransformPool(this.size);
    // Copy the entire buffer from this pool to the new one.
    new Float32Array(out.#buffer).set(new Float32Array(this.#buffer));
    return out;
  }
}
