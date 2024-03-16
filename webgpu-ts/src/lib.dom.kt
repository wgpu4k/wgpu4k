@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
package tsstdlib

import kotlin.js.*
import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*

external interface AesCbcParams : Algorithm {
    var iv: dynamic /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */
        get() = definedExternally
        set(value) = definedExternally
}

external interface AesCtrParams : Algorithm {
    var counter: dynamic /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */
        get() = definedExternally
        set(value) = definedExternally
    var length: Number
}

external interface AesDerivedKeyParams : Algorithm {
    var length: Number
}

external interface AesGcmParams : Algorithm {
    var additionalData: dynamic /* Int8Array? | Int16Array? | Int32Array? | Uint8Array? | Uint16Array? | Uint32Array? | Uint8ClampedArray? | Float32Array? | Float64Array? | DataView? | ArrayBuffer? */
        get() = definedExternally
        set(value) = definedExternally
    var iv: dynamic /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */
        get() = definedExternally
        set(value) = definedExternally
    var tagLength: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface AesKeyAlgorithm : KeyAlgorithm {
    var length: Number
}

external interface AesKeyGenParams : Algorithm {
    var length: Number
}

external interface Algorithm {
    var name: String
}

external interface AnimationEventInit : EventInit {
    var animationName: String?
        get() = definedExternally
        set(value) = definedExternally
    var elapsedTime: Number?
        get() = definedExternally
        set(value) = definedExternally
    var pseudoElement: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface AnimationPlaybackEventInit : EventInit {
    var currentTime: Number?
        get() = definedExternally
        set(value) = definedExternally
    var timelineTime: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface AudioBufferOptions {
    var length: Number
    var numberOfChannels: Number?
        get() = definedExternally
        set(value) = definedExternally
    var sampleRate: Number
}

external interface AudioProcessingEventInit : EventInit {
    var inputBuffer: AudioBuffer
    var outputBuffer: AudioBuffer
    var playbackTime: Number
}

external interface AuthenticationExtensionsClientInputs {
    var appid: String?
        get() = definedExternally
        set(value) = definedExternally
    var authnSel: AuthenticatorSelectionList?
        get() = definedExternally
        set(value) = definedExternally
    var exts: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var loc: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var txAuthGeneric: txAuthGenericArg?
        get() = definedExternally
        set(value) = definedExternally
    var txAuthSimple: String?
        get() = definedExternally
        set(value) = definedExternally
    var uvi: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var uvm: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface AuthenticatorSelectionCriteria {
    var authenticatorAttachment: String? /* "cross-platform" | "platform" */
        get() = definedExternally
        set(value) = definedExternally
    var requireResidentKey: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var userVerification: String? /* "discouraged" | "preferred" | "required" */
        get() = definedExternally
        set(value) = definedExternally
}

external interface ClipboardEventInit : EventInit {
    var clipboardData: DataTransfer?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ComputedEffectTiming : EffectTiming {
    var activeDuration: Number?
        get() = definedExternally
        set(value) = definedExternally
    var currentIteration: Number?
        get() = definedExternally
        set(value) = definedExternally
    var endTime: Number?
        get() = definedExternally
        set(value) = definedExternally
    var localTime: Number?
        get() = definedExternally
        set(value) = definedExternally
    var progress: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ConfirmSiteSpecificExceptionsInformation : ExceptionInformation {
    var arrayOfDomainStrings: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ConstrainBooleanParameters {
    var exact: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var ideal: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ConstrainDOMStringParameters {
    var exact: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var ideal: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
}

external interface ConstrainDoubleRange : DoubleRange {
    var exact: Number?
        get() = definedExternally
        set(value) = definedExternally
    var ideal: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ConstrainULongRange : ULongRange {
    var exact: Number?
        get() = definedExternally
        set(value) = definedExternally
    var ideal: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface CredentialCreationOptions {
    var publicKey: PublicKeyCredentialCreationOptions?
        get() = definedExternally
        set(value) = definedExternally
    var signal: AbortSignal?
        get() = definedExternally
        set(value) = definedExternally
}

external interface CredentialRequestOptions {
    var mediation: String? /* "optional" | "required" | "silent" */
        get() = definedExternally
        set(value) = definedExternally
    var publicKey: PublicKeyCredentialRequestOptions?
        get() = definedExternally
        set(value) = definedExternally
    var signal: AbortSignal?
        get() = definedExternally
        set(value) = definedExternally
}

external interface DOMMatrix2DInit {
    var a: Number?
        get() = definedExternally
        set(value) = definedExternally
    var b: Number?
        get() = definedExternally
        set(value) = definedExternally
    var c: Number?
        get() = definedExternally
        set(value) = definedExternally
    var d: Number?
        get() = definedExternally
        set(value) = definedExternally
    var e: Number?
        get() = definedExternally
        set(value) = definedExternally
    var f: Number?
        get() = definedExternally
        set(value) = definedExternally
    var m11: Number?
        get() = definedExternally
        set(value) = definedExternally
    var m12: Number?
        get() = definedExternally
        set(value) = definedExternally
    var m21: Number?
        get() = definedExternally
        set(value) = definedExternally
    var m22: Number?
        get() = definedExternally
        set(value) = definedExternally
    var m41: Number?
        get() = definedExternally
        set(value) = definedExternally
    var m42: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface DOMMatrixInit : DOMMatrix2DInit {
    var is2D: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var m13: Number?
        get() = definedExternally
        set(value) = definedExternally
    var m14: Number?
        get() = definedExternally
        set(value) = definedExternally
    var m23: Number?
        get() = definedExternally
        set(value) = definedExternally
    var m24: Number?
        get() = definedExternally
        set(value) = definedExternally
    var m31: Number?
        get() = definedExternally
        set(value) = definedExternally
    var m32: Number?
        get() = definedExternally
        set(value) = definedExternally
    var m33: Number?
        get() = definedExternally
        set(value) = definedExternally
    var m34: Number?
        get() = definedExternally
        set(value) = definedExternally
    var m43: Number?
        get() = definedExternally
        set(value) = definedExternally
    var m44: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface DeviceLightEventInit : EventInit {
    var value: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface DeviceMotionEventAccelerationInit {
    var x: Number?
        get() = definedExternally
        set(value) = definedExternally
    var y: Number?
        get() = definedExternally
        set(value) = definedExternally
    var z: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface DeviceMotionEventInit : EventInit {
    var acceleration: DeviceMotionEventAccelerationInit?
        get() = definedExternally
        set(value) = definedExternally
    var accelerationIncludingGravity: DeviceMotionEventAccelerationInit?
        get() = definedExternally
        set(value) = definedExternally
    var interval: Number?
        get() = definedExternally
        set(value) = definedExternally
    var rotationRate: DeviceMotionEventRotationRateInit?
        get() = definedExternally
        set(value) = definedExternally
}

external interface DeviceMotionEventRotationRateInit {
    var alpha: Number?
        get() = definedExternally
        set(value) = definedExternally
    var beta: Number?
        get() = definedExternally
        set(value) = definedExternally
    var gamma: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface DeviceOrientationEventInit : EventInit {
    var absolute: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var alpha: Number?
        get() = definedExternally
        set(value) = definedExternally
    var beta: Number?
        get() = definedExternally
        set(value) = definedExternally
    var gamma: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface DevicePermissionDescriptor : PermissionDescriptor {
    var deviceId: String?
        get() = definedExternally
        set(value) = definedExternally
    override var name: String /* "camera" | "microphone" | "speaker" */
}

external interface DocumentTimelineOptions {
    var originTime: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface DoubleRange {
    var max: Number?
        get() = definedExternally
        set(value) = definedExternally
    var min: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface EcKeyGenParams : Algorithm {
    var namedCurve: NamedCurve
}

external interface EcKeyImportParams : Algorithm {
    var namedCurve: NamedCurve
}

external interface EcdhKeyDeriveParams : Algorithm {
    var public: CryptoKey
}

external interface EcdsaParams : Algorithm {
    var hash: dynamic /* typealias HashAlgorithmIdentifier = dynamic */
        get() = definedExternally
        set(value) = definedExternally
}

external interface EffectTiming {
    var delay: Number?
        get() = definedExternally
        set(value) = definedExternally
    var direction: String? /* "alternate" | "alternate-reverse" | "normal" | "reverse" */
        get() = definedExternally
        set(value) = definedExternally
    var duration: dynamic /* Number? | String? */
        get() = definedExternally
        set(value) = definedExternally
    var easing: String?
        get() = definedExternally
        set(value) = definedExternally
    var endDelay: Number?
        get() = definedExternally
        set(value) = definedExternally
    var fill: String? /* "auto" | "backwards" | "both" | "forwards" | "none" */
        get() = definedExternally
        set(value) = definedExternally
    var iterationStart: Number?
        get() = definedExternally
        set(value) = definedExternally
    var iterations: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ExceptionInformation {
    var domain: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface FocusNavigationEventInit : EventInit {
    var navigationReason: String?
        get() = definedExternally
        set(value) = definedExternally
    var originHeight: Number?
        get() = definedExternally
        set(value) = definedExternally
    var originLeft: Number?
        get() = definedExternally
        set(value) = definedExternally
    var originTop: Number?
        get() = definedExternally
        set(value) = definedExternally
    var originWidth: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface FocusNavigationOrigin {
    var originHeight: Number?
        get() = definedExternally
        set(value) = definedExternally
    var originLeft: Number?
        get() = definedExternally
        set(value) = definedExternally
    var originTop: Number?
        get() = definedExternally
        set(value) = definedExternally
    var originWidth: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface FocusOptions {
    var preventScroll: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface FullscreenOptions {
    var navigationUI: String? /* "auto" | "hide" | "show" */
        get() = definedExternally
        set(value) = definedExternally
}

external interface GamepadEventInit : EventInit {
    var gamepad: Gamepad
}

external interface HmacImportParams : Algorithm {
    var hash: dynamic /* typealias HashAlgorithmIdentifier = dynamic */
        get() = definedExternally
        set(value) = definedExternally
    var length: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface HmacKeyGenParams : Algorithm {
    var hash: dynamic /* typealias HashAlgorithmIdentifier = dynamic */
        get() = definedExternally
        set(value) = definedExternally
    var length: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface IDBIndexParameters {
    var multiEntry: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var unique: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface IDBObjectStoreParameters {
    var autoIncrement: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var keyPath: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
}

external interface IDBVersionChangeEventInit : EventInit {
    var newVersion: Number?
        get() = definedExternally
        set(value) = definedExternally
    var oldVersion: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ImageEncodeOptions {
    var quality: Number?
        get() = definedExternally
        set(value) = definedExternally
    var type: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface JsonWebKey {
    var alg: String?
        get() = definedExternally
        set(value) = definedExternally
    var crv: String?
        get() = definedExternally
        set(value) = definedExternally
    var d: String?
        get() = definedExternally
        set(value) = definedExternally
    var dp: String?
        get() = definedExternally
        set(value) = definedExternally
    var dq: String?
        get() = definedExternally
        set(value) = definedExternally
    var e: String?
        get() = definedExternally
        set(value) = definedExternally
    var ext: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var k: String?
        get() = definedExternally
        set(value) = definedExternally
    var key_ops: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var kty: String?
        get() = definedExternally
        set(value) = definedExternally
    var n: String?
        get() = definedExternally
        set(value) = definedExternally
    var oth: Array<RsaOtherPrimesInfo>?
        get() = definedExternally
        set(value) = definedExternally
    var p: String?
        get() = definedExternally
        set(value) = definedExternally
    var q: String?
        get() = definedExternally
        set(value) = definedExternally
    var qi: String?
        get() = definedExternally
        set(value) = definedExternally
    var use: String?
        get() = definedExternally
        set(value) = definedExternally
    var x: String?
        get() = definedExternally
        set(value) = definedExternally
    var y: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface KeyAlgorithm {
    var name: String
}

external interface Keyframe {
    var composite: String? /* "accumulate" | "add" | "auto" | "replace" */
        get() = definedExternally
        set(value) = definedExternally
    var easing: String?
        get() = definedExternally
        set(value) = definedExternally
    var offset: Number?
        get() = definedExternally
        set(value) = definedExternally
    @nativeGetter
    operator fun get(property: String): dynamic /* String? | Number? */
    @nativeSetter
    operator fun set(property: String, value: String?)
    @nativeSetter
    operator fun set(property: String, value: Number?)
}

external interface KeyframeAnimationOptions : KeyframeEffectOptions {
    var id: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface KeyframeEffectOptions : EffectTiming {
    var composite: String? /* "accumulate" | "add" | "replace" */
        get() = definedExternally
        set(value) = definedExternally
    var iterationComposite: String? /* "accumulate" | "replace" */
        get() = definedExternally
        set(value) = definedExternally
}

external interface MediaEncryptedEventInit : EventInit {
    var initData: ArrayBuffer?
        get() = definedExternally
        set(value) = definedExternally
    var initDataType: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface MediaKeyMessageEventInit : EventInit {
    var message: ArrayBuffer
    var messageType: String /* "individualization-request" | "license-release" | "license-renewal" | "license-request" */
}

external interface MediaKeySystemConfiguration {
    var audioCapabilities: Array<MediaKeySystemMediaCapability>?
        get() = definedExternally
        set(value) = definedExternally
    var distinctiveIdentifier: String? /* "not-allowed" | "optional" | "required" */
        get() = definedExternally
        set(value) = definedExternally
    var initDataTypes: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var label: String?
        get() = definedExternally
        set(value) = definedExternally
    var persistentState: String? /* "not-allowed" | "optional" | "required" */
        get() = definedExternally
        set(value) = definedExternally
    var sessionTypes: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var videoCapabilities: Array<MediaKeySystemMediaCapability>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface MediaKeySystemMediaCapability {
    var contentType: String?
        get() = definedExternally
        set(value) = definedExternally
    var robustness: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface MediaStreamConstraints {
    var audio: dynamic /* Boolean? | MediaTrackConstraints? */
        get() = definedExternally
        set(value) = definedExternally
    var peerIdentity: String?
        get() = definedExternally
        set(value) = definedExternally
    var video: dynamic /* Boolean? | MediaTrackConstraints? */
        get() = definedExternally
        set(value) = definedExternally
}

external interface MediaStreamErrorEventInit : EventInit {
    var error: MediaStreamError?
        get() = definedExternally
        set(value) = definedExternally
}

external interface MediaStreamEventInit : EventInit {
    var stream: MediaStream?
        get() = definedExternally
        set(value) = definedExternally
}

external interface MediaStreamTrackEventInit : EventInit {
    var track: MediaStreamTrack
}

external interface MediaTrackCapabilities {
    var aspectRatio: DoubleRange?
        get() = definedExternally
        set(value) = definedExternally
    var autoGainControl: Array<Boolean>?
        get() = definedExternally
        set(value) = definedExternally
    var channelCount: ULongRange?
        get() = definedExternally
        set(value) = definedExternally
    var deviceId: String?
        get() = definedExternally
        set(value) = definedExternally
    var echoCancellation: Array<Boolean>?
        get() = definedExternally
        set(value) = definedExternally
    var facingMode: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var frameRate: DoubleRange?
        get() = definedExternally
        set(value) = definedExternally
    var groupId: String?
        get() = definedExternally
        set(value) = definedExternally
    var height: ULongRange?
        get() = definedExternally
        set(value) = definedExternally
    var latency: DoubleRange?
        get() = definedExternally
        set(value) = definedExternally
    var noiseSuppression: Array<Boolean>?
        get() = definedExternally
        set(value) = definedExternally
    var resizeMode: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var sampleRate: ULongRange?
        get() = definedExternally
        set(value) = definedExternally
    var sampleSize: ULongRange?
        get() = definedExternally
        set(value) = definedExternally
    var width: ULongRange?
        get() = definedExternally
        set(value) = definedExternally
}

external interface MediaTrackConstraintSet {
    var aspectRatio: dynamic /* Number? | ConstrainDoubleRange? */
        get() = definedExternally
        set(value) = definedExternally
    var autoGainControl: dynamic /* Boolean? | ConstrainBooleanParameters? */
        get() = definedExternally
        set(value) = definedExternally
    var channelCount: dynamic /* Number? | ConstrainULongRange? */
        get() = definedExternally
        set(value) = definedExternally
    var deviceId: dynamic /* String? | Array<String>? | ConstrainDOMStringParameters? */
        get() = definedExternally
        set(value) = definedExternally
    var echoCancellation: dynamic /* Boolean? | ConstrainBooleanParameters? */
        get() = definedExternally
        set(value) = definedExternally
    var facingMode: dynamic /* String? | Array<String>? | ConstrainDOMStringParameters? */
        get() = definedExternally
        set(value) = definedExternally
    var frameRate: dynamic /* Number? | ConstrainDoubleRange? */
        get() = definedExternally
        set(value) = definedExternally
    var groupId: dynamic /* String? | Array<String>? | ConstrainDOMStringParameters? */
        get() = definedExternally
        set(value) = definedExternally
    var height: dynamic /* Number? | ConstrainULongRange? */
        get() = definedExternally
        set(value) = definedExternally
    var latency: dynamic /* Number? | ConstrainDoubleRange? */
        get() = definedExternally
        set(value) = definedExternally
    var noiseSuppression: dynamic /* Boolean? | ConstrainBooleanParameters? */
        get() = definedExternally
        set(value) = definedExternally
    var resizeMode: dynamic /* String? | Array<String>? | ConstrainDOMStringParameters? */
        get() = definedExternally
        set(value) = definedExternally
    var sampleRate: dynamic /* Number? | ConstrainULongRange? */
        get() = definedExternally
        set(value) = definedExternally
    var sampleSize: dynamic /* Number? | ConstrainULongRange? */
        get() = definedExternally
        set(value) = definedExternally
    var width: dynamic /* Number? | ConstrainULongRange? */
        get() = definedExternally
        set(value) = definedExternally
}

external interface MediaTrackConstraints : MediaTrackConstraintSet {
    var advanced: Array<MediaTrackConstraintSet>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface MediaTrackSettings {
    var aspectRatio: Number?
        get() = definedExternally
        set(value) = definedExternally
    var autoGainControl: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var channelCount: Number?
        get() = definedExternally
        set(value) = definedExternally
    var deviceId: String?
        get() = definedExternally
        set(value) = definedExternally
    var echoCancellation: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var facingMode: String?
        get() = definedExternally
        set(value) = definedExternally
    var frameRate: Number?
        get() = definedExternally
        set(value) = definedExternally
    var groupId: String?
        get() = definedExternally
        set(value) = definedExternally
    var height: Number?
        get() = definedExternally
        set(value) = definedExternally
    var latency: Number?
        get() = definedExternally
        set(value) = definedExternally
    var noiseSuppression: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var resizeMode: String?
        get() = definedExternally
        set(value) = definedExternally
    var sampleRate: Number?
        get() = definedExternally
        set(value) = definedExternally
    var sampleSize: Number?
        get() = definedExternally
        set(value) = definedExternally
    var width: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface MediaTrackSupportedConstraints {
    var aspectRatio: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var autoGainControl: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var channelCount: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var deviceId: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var echoCancellation: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var facingMode: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var frameRate: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var groupId: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var height: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var latency: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var noiseSuppression: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var resizeMode: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var sampleRate: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var sampleSize: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var width: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface MidiPermissionDescriptor : PermissionDescriptor {
    override var name: String /* "midi" */
    var sysex: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface MultiCacheQueryOptions : CacheQueryOptions {
    var cacheName: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface NavigationPreloadState {
    var enabled: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var headerValue: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface OfflineAudioCompletionEventInit : EventInit {
    var renderedBuffer: AudioBuffer
}

external interface OptionalEffectTiming {
    var delay: Number?
        get() = definedExternally
        set(value) = definedExternally
    var direction: String? /* "alternate" | "alternate-reverse" | "normal" | "reverse" */
        get() = definedExternally
        set(value) = definedExternally
    var duration: dynamic /* Number? | String? */
        get() = definedExternally
        set(value) = definedExternally
    var easing: String?
        get() = definedExternally
        set(value) = definedExternally
    var endDelay: Number?
        get() = definedExternally
        set(value) = definedExternally
    var fill: String? /* "auto" | "backwards" | "both" | "forwards" | "none" */
        get() = definedExternally
        set(value) = definedExternally
    var iterationStart: Number?
        get() = definedExternally
        set(value) = definedExternally
    var iterations: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface PaymentCurrencyAmount {
    var currency: String
    var currencySystem: String?
        get() = definedExternally
        set(value) = definedExternally
    var value: String
}

external interface PaymentDetailsBase {
    var displayItems: Array<PaymentItem>?
        get() = definedExternally
        set(value) = definedExternally
    var modifiers: Array<PaymentDetailsModifier>?
        get() = definedExternally
        set(value) = definedExternally
    var shippingOptions: Array<PaymentShippingOption>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface PaymentDetailsModifier {
    var additionalDisplayItems: Array<PaymentItem>?
        get() = definedExternally
        set(value) = definedExternally
    var data: Any?
        get() = definedExternally
        set(value) = definedExternally
    var supportedMethods: dynamic /* String | Array<String> */
        get() = definedExternally
        set(value) = definedExternally
    var total: PaymentItem?
        get() = definedExternally
        set(value) = definedExternally
}

external interface PaymentDetailsUpdate : PaymentDetailsBase {
    var error: String?
        get() = definedExternally
        set(value) = definedExternally
    var total: PaymentItem?
        get() = definedExternally
        set(value) = definedExternally
}

external interface PaymentItem {
    var amount: PaymentCurrencyAmount
    var label: String
    var pending: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface PaymentRequestUpdateEventInit : EventInit

external interface PaymentShippingOption {
    var amount: PaymentCurrencyAmount
    var id: String
    var label: String
    var selected: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Pbkdf2Params : Algorithm {
    var hash: dynamic /* typealias HashAlgorithmIdentifier = dynamic */
        get() = definedExternally
        set(value) = definedExternally
    var iterations: Number
    var salt: dynamic /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */
        get() = definedExternally
        set(value) = definedExternally
}

external interface PermissionDescriptor {
    var name: String /* "accelerometer" | "ambient-light-sensor" | "background-sync" | "bluetooth" | "camera" | "clipboard" | "device-info" | "geolocation" | "gyroscope" | "magnetometer" | "microphone" | "midi" | "notifications" | "persistent-storage" | "push" | "speaker" */
}

external interface PipeOptions {
    var preventAbort: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var preventCancel: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var preventClose: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var signal: AbortSignal?
        get() = definedExternally
        set(value) = definedExternally
}

external interface PointerEventInit : MouseEventInit {
    var height: Number?
        get() = definedExternally
        set(value) = definedExternally
    var isPrimary: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var pointerId: Number?
        get() = definedExternally
        set(value) = definedExternally
    var pointerType: String?
        get() = definedExternally
        set(value) = definedExternally
    var pressure: Number?
        get() = definedExternally
        set(value) = definedExternally
    var tangentialPressure: Number?
        get() = definedExternally
        set(value) = definedExternally
    var tiltX: Number?
        get() = definedExternally
        set(value) = definedExternally
    var tiltY: Number?
        get() = definedExternally
        set(value) = definedExternally
    var twist: Number?
        get() = definedExternally
        set(value) = definedExternally
    var width: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface PositionOptions {
    var enableHighAccuracy: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var maximumAge: Number?
        get() = definedExternally
        set(value) = definedExternally
    var timeout: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface PostMessageOptions {
    var transfer: Array<Any>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface PropertyIndexedKeyframes {
    var composite: dynamic /* "accumulate" | "add" | "auto" | "replace" | Array<String /* "accumulate" | "add" | "auto" | "replace" */>? */
        get() = definedExternally
        set(value) = definedExternally
    var easing: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var offset: dynamic /* Number? | Array<Number?>? */
        get() = definedExternally
        set(value) = definedExternally
    @nativeGetter
    operator fun get(property: String): dynamic /* String? | Array<String>? | Number? | Array<Number?>? */
    @nativeSetter
    operator fun set(property: String, value: String?)
    @nativeSetter
    operator fun set(property: String, value: Array<String>?)
    @nativeSetter
    operator fun set(property: String, value: Number?)
    @nativeSetter
    operator fun set(property: String, value: Array<Number?>?)
}

external interface PublicKeyCredentialCreationOptions {
    var attestation: String? /* "direct" | "indirect" | "none" */
        get() = definedExternally
        set(value) = definedExternally
    var authenticatorSelection: AuthenticatorSelectionCriteria?
        get() = definedExternally
        set(value) = definedExternally
    var challenge: dynamic /* ArrayBufferView | ArrayBuffer */
        get() = definedExternally
        set(value) = definedExternally
    var excludeCredentials: Array<PublicKeyCredentialDescriptor>?
        get() = definedExternally
        set(value) = definedExternally
    var extensions: AuthenticationExtensionsClientInputs?
        get() = definedExternally
        set(value) = definedExternally
    var pubKeyCredParams: Array<PublicKeyCredentialParameters>
    var rp: PublicKeyCredentialRpEntity
    var timeout: Number?
        get() = definedExternally
        set(value) = definedExternally
    var user: PublicKeyCredentialUserEntity
}

external interface PublicKeyCredentialDescriptor {
    var id: dynamic /* ArrayBufferView | ArrayBuffer */
        get() = definedExternally
        set(value) = definedExternally
    var transports: Array<String? /* "ble" | "internal" | "nfc" | "usb" */>?
        get() = definedExternally
        set(value) = definedExternally
    var type: String /* "public-key" */
}

external interface PublicKeyCredentialEntity {
    var icon: String?
        get() = definedExternally
        set(value) = definedExternally
    var name: String
}

external interface PublicKeyCredentialParameters {
    var alg: COSEAlgorithmIdentifier
    var type: String /* "public-key" */
}

external interface PublicKeyCredentialRequestOptions {
    var allowCredentials: Array<PublicKeyCredentialDescriptor>?
        get() = definedExternally
        set(value) = definedExternally
    var challenge: dynamic /* ArrayBufferView | ArrayBuffer */
        get() = definedExternally
        set(value) = definedExternally
    var extensions: AuthenticationExtensionsClientInputs?
        get() = definedExternally
        set(value) = definedExternally
    var rpId: String?
        get() = definedExternally
        set(value) = definedExternally
    var timeout: Number?
        get() = definedExternally
        set(value) = definedExternally
    var userVerification: String? /* "discouraged" | "preferred" | "required" */
        get() = definedExternally
        set(value) = definedExternally
}

external interface PublicKeyCredentialRpEntity : PublicKeyCredentialEntity {
    var id: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface PublicKeyCredentialUserEntity : PublicKeyCredentialEntity {
    var displayName: String
    var id: dynamic /* ArrayBufferView | ArrayBuffer */
        get() = definedExternally
        set(value) = definedExternally
}

external interface PushPermissionDescriptor : PermissionDescriptor {
    override var name: String /* "push" */
    var userVisibleOnly: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface PushSubscriptionJSON {
    var endpoint: String?
        get() = definedExternally
        set(value) = definedExternally
    var expirationTime: Number?
        get() = definedExternally
        set(value) = definedExternally
    var keys: Record<String, String>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface PushSubscriptionOptionsInit {
    var applicationServerKey: dynamic /* ArrayBufferView? | ArrayBuffer? | String? */
        get() = definedExternally
        set(value) = definedExternally
    var userVisibleOnly: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface QueuingStrategy<T> {
    var highWaterMark: Number?
        get() = definedExternally
        set(value) = definedExternally
    var size: QueuingStrategySizeCallback<T>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RTCDTMFToneChangeEventInit : EventInit {
    var tone: String
}

external interface RTCDataChannelEventInit : EventInit {
    var channel: RTCDataChannel
}

external interface RTCErrorEventInit : EventInit {
    var error: RTCError
}

external interface RTCErrorInit {
    var errorDetail: String /* "data-channel-failure" | "dtls-failure" | "fingerprint-failure" | "hardware-encoder-error" | "hardware-encoder-not-available" | "idp-bad-script-failure" | "idp-execution-failure" | "idp-load-failure" | "idp-need-login" | "idp-timeout" | "idp-tls-failure" | "idp-token-expired" | "idp-token-invalid" | "sctp-failure" | "sdp-syntax-error" */
    var httpRequestStatusCode: Number?
        get() = definedExternally
        set(value) = definedExternally
    var receivedAlert: Number?
        get() = definedExternally
        set(value) = definedExternally
    var sctpCauseCode: Number?
        get() = definedExternally
        set(value) = definedExternally
    var sdpLineNumber: Number?
        get() = definedExternally
        set(value) = definedExternally
    var sentAlert: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RTCIceCandidateComplete

external interface RTCIceCandidateDictionary {
    var foundation: String?
        get() = definedExternally
        set(value) = definedExternally
    var ip: String?
        get() = definedExternally
        set(value) = definedExternally
    var msMTurnSessionId: String?
        get() = definedExternally
        set(value) = definedExternally
    var port: Number?
        get() = definedExternally
        set(value) = definedExternally
    var priority: Number?
        get() = definedExternally
        set(value) = definedExternally
    var protocol: String? /* "tcp" | "udp" */
        get() = definedExternally
        set(value) = definedExternally
    var relatedAddress: String?
        get() = definedExternally
        set(value) = definedExternally
    var relatedPort: Number?
        get() = definedExternally
        set(value) = definedExternally
    var tcpType: String? /* "active" | "passive" | "so" */
        get() = definedExternally
        set(value) = definedExternally
    var type: String? /* "host" | "prflx" | "relay" | "srflx" */
        get() = definedExternally
        set(value) = definedExternally
}

external interface RTCIceCandidateInit {
    var candidate: String?
        get() = definedExternally
        set(value) = definedExternally
    var sdpMLineIndex: Number?
        get() = definedExternally
        set(value) = definedExternally
    var sdpMid: String?
        get() = definedExternally
        set(value) = definedExternally
    var usernameFragment: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RTCIceCandidatePair {
    var local: RTCIceCandidate?
        get() = definedExternally
        set(value) = definedExternally
    var remote: RTCIceCandidate?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RTCIceParameters {
    var password: String?
        get() = definedExternally
        set(value) = definedExternally
    var usernameFragment: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RTCPeerConnectionIceErrorEventInit : EventInit {
    var errorCode: Number
    var hostCandidate: String?
        get() = definedExternally
        set(value) = definedExternally
    var statusText: String?
        get() = definedExternally
        set(value) = definedExternally
    var url: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RTCPeerConnectionIceEventInit : EventInit {
    var candidate: RTCIceCandidate?
        get() = definedExternally
        set(value) = definedExternally
    var url: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RTCRtcpParameters {
    var cname: String?
        get() = definedExternally
        set(value) = definedExternally
    var reducedSize: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RTCRtpCapabilities {
    var codecs: Array<RTCRtpCodecCapability>
    var headerExtensions: Array<RTCRtpHeaderExtensionCapability>
}

external interface RTCRtpCodecCapability {
    var channels: Number?
        get() = definedExternally
        set(value) = definedExternally
    var clockRate: Number
    var mimeType: String
    var sdpFmtpLine: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RTCRtpCodecParameters {
    var channels: Number?
        get() = definedExternally
        set(value) = definedExternally
    var clockRate: Number
    var mimeType: String
    var payloadType: Number
    var sdpFmtpLine: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RTCRtpCodingParameters {
    var rid: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RTCRtpContributingSource {
    var audioLevel: Number?
        get() = definedExternally
        set(value) = definedExternally
    var rtpTimestamp: Number
    var source: Number
    var timestamp: Number
}

external interface RTCRtpDecodingParameters : RTCRtpCodingParameters

external interface RTCRtpEncodingParameters : RTCRtpCodingParameters {
    var active: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var codecPayloadType: Number?
        get() = definedExternally
        set(value) = definedExternally
    var dtx: String? /* "disabled" | "enabled" */
        get() = definedExternally
        set(value) = definedExternally
    var maxBitrate: Number?
        get() = definedExternally
        set(value) = definedExternally
    var maxFramerate: Number?
        get() = definedExternally
        set(value) = definedExternally
    var ptime: Number?
        get() = definedExternally
        set(value) = definedExternally
    var scaleResolutionDownBy: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RTCRtpHeaderExtensionCapability {
    var uri: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RTCRtpHeaderExtensionParameters {
    var encrypted: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var id: Number
    var uri: String
}

external interface RTCRtpParameters {
    var codecs: Array<RTCRtpCodecParameters>
    var headerExtensions: Array<RTCRtpHeaderExtensionParameters>
    var rtcp: RTCRtcpParameters
}

external interface RTCRtpReceiveParameters : RTCRtpParameters {
    var encodings: Array<RTCRtpDecodingParameters>
}

external interface RTCRtpSendParameters : RTCRtpParameters {
    var degradationPreference: String? /* "balanced" | "maintain-framerate" | "maintain-resolution" */
        get() = definedExternally
        set(value) = definedExternally
    var encodings: Array<RTCRtpEncodingParameters>
    var priority: String? /* "high" | "low" | "medium" | "very-low" */
        get() = definedExternally
        set(value) = definedExternally
    var transactionId: String
}

external interface RTCRtpSynchronizationSource : RTCRtpContributingSource {
    var voiceActivityFlag: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RTCStatsEventInit : EventInit {
    var report: RTCStatsReport
}

external interface RTCStatsReport : ReadonlyMap<String, Any> {
    fun forEach(callbackfn: (value: Any, key: String, parent: RTCStatsReport) -> Unit, thisArg: Any = definedExternally)
}

external interface RTCTrackEventInit : EventInit {
    var receiver: RTCRtpReceiver
    var streams: Array<MediaStream>?
        get() = definedExternally
        set(value) = definedExternally
    var track: MediaStreamTrack
    var transceiver: RTCRtpTransceiver
}

external interface ReadableStreamReadDoneResult<T> {
    var done: Boolean
    var value: T?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ReadableStreamReadValueResult<T> {
    var done: Boolean
    var value: T
}

external interface RsaHashedImportParams : Algorithm {
    var hash: dynamic /* typealias HashAlgorithmIdentifier = dynamic */
        get() = definedExternally
        set(value) = definedExternally
}

external interface RsaHashedKeyGenParams : RsaKeyGenParams {
    var hash: dynamic /* typealias HashAlgorithmIdentifier = dynamic */
        get() = definedExternally
        set(value) = definedExternally
}

external interface RsaKeyGenParams : Algorithm {
    var modulusLength: Number
    var publicExponent: BigInteger
}

external interface RsaOaepParams : Algorithm {
    var label: dynamic /* Int8Array? | Int16Array? | Int32Array? | Uint8Array? | Uint16Array? | Uint32Array? | Uint8ClampedArray? | Float32Array? | Float64Array? | DataView? | ArrayBuffer? */
        get() = definedExternally
        set(value) = definedExternally
}

external interface RsaOtherPrimesInfo {
    var d: String?
        get() = definedExternally
        set(value) = definedExternally
    var r: String?
        get() = definedExternally
        set(value) = definedExternally
    var t: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RsaPssParams : Algorithm {
    var saltLength: Number
}

external interface SecurityPolicyViolationEventInit : EventInit {
    var blockedURI: String?
        get() = definedExternally
        set(value) = definedExternally
    var columnNumber: Number?
        get() = definedExternally
        set(value) = definedExternally
    var documentURI: String?
        get() = definedExternally
        set(value) = definedExternally
    var effectiveDirective: String?
        get() = definedExternally
        set(value) = definedExternally
    var lineNumber: Number?
        get() = definedExternally
        set(value) = definedExternally
    var originalPolicy: String?
        get() = definedExternally
        set(value) = definedExternally
    var referrer: String?
        get() = definedExternally
        set(value) = definedExternally
    var sourceFile: String?
        get() = definedExternally
        set(value) = definedExternally
    var statusCode: Number?
        get() = definedExternally
        set(value) = definedExternally
    var violatedDirective: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ShareData {
    var text: String?
        get() = definedExternally
        set(value) = definedExternally
    var title: String?
        get() = definedExternally
        set(value) = definedExternally
    var url: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface SpeechSynthesisErrorEventInit : SpeechSynthesisEventInit {
    var error: String /* "audio-busy" | "audio-hardware" | "canceled" | "interrupted" | "invalid-argument" | "language-unavailable" | "network" | "not-allowed" | "synthesis-failed" | "synthesis-unavailable" | "text-too-long" | "voice-unavailable" */
}

external interface SpeechSynthesisEventInit : EventInit {
    var charIndex: Number?
        get() = definedExternally
        set(value) = definedExternally
    var charLength: Number?
        get() = definedExternally
        set(value) = definedExternally
    var elapsedTime: Number?
        get() = definedExternally
        set(value) = definedExternally
    var name: String?
        get() = definedExternally
        set(value) = definedExternally
    var utterance: SpeechSynthesisUtterance
}

external interface StorageEstimate {
    var quota: Number?
        get() = definedExternally
        set(value) = definedExternally
    var usage: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface StoreExceptionsInformation : ExceptionInformation {
    var detailURI: String?
        get() = definedExternally
        set(value) = definedExternally
    var explanationString: String?
        get() = definedExternally
        set(value) = definedExternally
    var siteName: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface StoreSiteSpecificExceptionsInformation : StoreExceptionsInformation {
    var arrayOfDomainStrings: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface TouchEventInit : EventModifierInit {
    var changedTouches: Array<Touch>?
        get() = definedExternally
        set(value) = definedExternally
    var targetTouches: Array<Touch>?
        get() = definedExternally
        set(value) = definedExternally
    var touches: Array<Touch>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface TouchInit {
    var altitudeAngle: Number?
        get() = definedExternally
        set(value) = definedExternally
    var azimuthAngle: Number?
        get() = definedExternally
        set(value) = definedExternally
    var clientX: Number?
        get() = definedExternally
        set(value) = definedExternally
    var clientY: Number?
        get() = definedExternally
        set(value) = definedExternally
    var force: Number?
        get() = definedExternally
        set(value) = definedExternally
    var identifier: Number
    var pageX: Number?
        get() = definedExternally
        set(value) = definedExternally
    var pageY: Number?
        get() = definedExternally
        set(value) = definedExternally
    var radiusX: Number?
        get() = definedExternally
        set(value) = definedExternally
    var radiusY: Number?
        get() = definedExternally
        set(value) = definedExternally
    var rotationAngle: Number?
        get() = definedExternally
        set(value) = definedExternally
    var screenX: Number?
        get() = definedExternally
        set(value) = definedExternally
    var screenY: Number?
        get() = definedExternally
        set(value) = definedExternally
    var target: EventTarget
    var touchType: String? /* "direct" | "stylus" */
        get() = definedExternally
        set(value) = definedExternally
}

external interface TransitionEventInit : EventInit {
    var elapsedTime: Number?
        get() = definedExternally
        set(value) = definedExternally
    var propertyName: String?
        get() = definedExternally
        set(value) = definedExternally
    var pseudoElement: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ULongRange {
    var max: Number?
        get() = definedExternally
        set(value) = definedExternally
    var min: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface UnderlyingByteSource {
    var autoAllocateChunkSize: Number?
        get() = definedExternally
        set(value) = definedExternally
    var cancel: ReadableStreamErrorCallback?
        get() = definedExternally
        set(value) = definedExternally
    var pull: ReadableByteStreamControllerCallback?
        get() = definedExternally
        set(value) = definedExternally
    var start: ReadableByteStreamControllerCallback?
        get() = definedExternally
        set(value) = definedExternally
    var type: String /* "bytes" */
}

external interface UnderlyingSink<W> {
    var abort: WritableStreamErrorCallback?
        get() = definedExternally
        set(value) = definedExternally
    var close: WritableStreamDefaultControllerCloseCallback?
        get() = definedExternally
        set(value) = definedExternally
    var start: WritableStreamDefaultControllerStartCallback?
        get() = definedExternally
        set(value) = definedExternally
    var type: Any?
        get() = definedExternally
        set(value) = definedExternally
    var write: WritableStreamDefaultControllerWriteCallback<W>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface UnderlyingSource<R> {
    var cancel: ReadableStreamErrorCallback?
        get() = definedExternally
        set(value) = definedExternally
    var pull: ReadableStreamDefaultControllerCallback<R>?
        get() = definedExternally
        set(value) = definedExternally
    var start: ReadableStreamDefaultControllerCallback<R>?
        get() = definedExternally
        set(value) = definedExternally
    var type: Any?
        get() = definedExternally
        set(value) = definedExternally
}

external interface VRDisplayEventInit : EventInit {
    var display: VRDisplay
    var reason: String? /* "mounted" | "navigation" | "requested" | "unmounted" */
        get() = definedExternally
        set(value) = definedExternally
}

external interface VRLayer {
    var leftBounds: dynamic /* Array<Number>? | Float32Array? */
        get() = definedExternally
        set(value) = definedExternally
    var rightBounds: dynamic /* Array<Number>? | Float32Array? */
        get() = definedExternally
        set(value) = definedExternally
    var source: HTMLCanvasElement?
        get() = definedExternally
        set(value) = definedExternally
}

external interface VRStageParameters {
    var sittingToStandingTransform: Float32Array?
        get() = definedExternally
        set(value) = definedExternally
    var sizeX: Number?
        get() = definedExternally
        set(value) = definedExternally
    var sizeY: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface txAuthGenericArg {
    var content: ArrayBuffer
    var contentType: String
}

external interface `T$2` {
    fun lookupNamespaceURI(prefix: String?): String?
}

external interface ANGLE_instanced_arrays {
    fun drawArraysInstancedANGLE(mode: GLenum, first: GLint, count: GLsizei, primcount: GLsizei)
    fun drawElementsInstancedANGLE(mode: GLenum, count: GLsizei, type: GLenum, offset: GLintptr, primcount: GLsizei)
    fun vertexAttribDivisorANGLE(index: GLuint, divisor: GLuint)
    var VERTEX_ATTRIB_ARRAY_DIVISOR_ANGLE: GLenum
}

external interface AbortSignalEventMap {
    var abort: Event
}

external interface AbortSignal : EventTarget {
    var aborted: Boolean
    var onabort: ((self: AbortSignal, ev: Event) -> Any)?
    fun <K : String> addEventListener(type: K, listener: (self: AbortSignal, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: AbortSignal, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: AbortSignal, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: AbortSignal, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: AbortSignal, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: AbortSignal, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface AbstractRange {
    var collapsed: Boolean
    var endContainer: Node
    var endOffset: Number
    var startContainer: Node
    var startOffset: Number
}

external interface AbstractWorkerEventMap {
    var error: ErrorEvent
}

external interface AesCfbParams : Algorithm {
    var iv: dynamic /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */
        get() = definedExternally
        set(value) = definedExternally
}

external interface AesCmacParams : Algorithm {
    var length: Number
}

external interface Animatable {
    fun animate(keyframes: Array<Keyframe>?, options: Number = definedExternally): Animation
    fun animate(keyframes: Array<Keyframe>?): Animation
    fun animate(keyframes: Array<Keyframe>?, options: KeyframeAnimationOptions = definedExternally): Animation
    fun animate(keyframes: PropertyIndexedKeyframes?, options: Number = definedExternally): Animation
    fun animate(keyframes: PropertyIndexedKeyframes?): Animation
    fun animate(keyframes: PropertyIndexedKeyframes?, options: KeyframeAnimationOptions = definedExternally): Animation
    fun getAnimations(): Array<Animation>
}

external interface AnimationEventMap {
    var cancel: AnimationPlaybackEvent
    var finish: AnimationPlaybackEvent
}

external interface Animation : EventTarget {
    var currentTime: Number?
    var effect: AnimationEffect?
    var finished: Promise<Animation>
    var id: String
    var oncancel: ((self: Animation, ev: AnimationPlaybackEvent) -> Any)?
    var onfinish: ((self: Animation, ev: AnimationPlaybackEvent) -> Any)?
    var pending: Boolean
    var playState: String /* "finished" | "idle" | "paused" | "running" */
    var playbackRate: Number
    var ready: Promise<Animation>
    var startTime: Number?
    var timeline: AnimationTimeline?
    fun cancel()
    fun finish()
    fun pause()
    fun play()
    fun reverse()
    fun updatePlaybackRate(playbackRate: Number)
    fun <K : String> addEventListener(type: K, listener: (self: Animation, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: Animation, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: Animation, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: Animation, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: Animation, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: Animation, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface AnimationEffect {
    fun getComputedTiming(): ComputedEffectTiming
    fun getTiming(): EffectTiming
    fun updateTiming(timing: OptionalEffectTiming = definedExternally)
}

external interface AnimationEvent : Event {
    var animationName: String
    var elapsedTime: Number
    var pseudoElement: String
}

external interface AnimationFrameProvider {
    fun cancelAnimationFrame(handle: Number)
    fun requestAnimationFrame(callback: FrameRequestCallback): Number
}

external interface AnimationPlaybackEvent : Event {
    var currentTime: Number?
    var timelineTime: Number?
}

external interface AnimationTimeline {
    var currentTime: Number?
}

external interface ApplicationCacheEventMap {
    var cached: Event
    var checking: Event
    var downloading: Event
    var error: Event
    var noupdate: Event
    var obsolete: Event
    var progress: ProgressEvent<ApplicationCache>
    var updateready: Event
}

external interface AudioBuffer {
    var duration: Number
    var length: Number
    var numberOfChannels: Number
    var sampleRate: Number
    fun copyFromChannel(destination: Float32Array, channelNumber: Number, bufferOffset: Number = definedExternally)
    fun copyToChannel(source: Float32Array, channelNumber: Number, bufferOffset: Number = definedExternally)
    fun getChannelData(channel: Number): Float32Array
}

external interface AudioProcessingEvent : Event {
    var inputBuffer: AudioBuffer
    var outputBuffer: AudioBuffer
    var playbackTime: Number
}

external interface CSSRule {
    var cssText: String
    var parentRule: CSSRule?
    var parentStyleSheet: CSSStyleSheet?
    var type: Number
    var CHARSET_RULE: Number
    var FONT_FACE_RULE: Number
    var IMPORT_RULE: Number
    var KEYFRAMES_RULE: Number
    var KEYFRAME_RULE: Number
    var MEDIA_RULE: Number
    var NAMESPACE_RULE: Number
    var PAGE_RULE: Number
    var STYLE_RULE: Number
    var SUPPORTS_RULE: Number
}

external interface CSSRuleList {
    var length: Number
    fun item(index: Number): CSSRule?
    @nativeGetter
    operator fun get(index: Number): CSSRule?
    @nativeSetter
    operator fun set(index: Number, value: CSSRule)
}

external interface CSSStyleDeclaration {
    var alignContent: String
    var alignItems: String
    var alignSelf: String
    var alignmentBaseline: String
    var all: String
    var animation: String
    var animationDelay: String
    var animationDirection: String
    var animationDuration: String
    var animationFillMode: String
    var animationIterationCount: String
    var animationName: String
    var animationPlayState: String
    var animationTimingFunction: String
    var backfaceVisibility: String
    var background: String
    var backgroundAttachment: String
    var backgroundClip: String
    var backgroundColor: String
    var backgroundImage: String
    var backgroundOrigin: String
    var backgroundPosition: String
    var backgroundPositionX: String
    var backgroundPositionY: String
    var backgroundRepeat: String
    var backgroundSize: String
    var baselineShift: String
    var blockSize: String
    var border: String
    var borderBlockEnd: String
    var borderBlockEndColor: String
    var borderBlockEndStyle: String
    var borderBlockEndWidth: String
    var borderBlockStart: String
    var borderBlockStartColor: String
    var borderBlockStartStyle: String
    var borderBlockStartWidth: String
    var borderBottom: String
    var borderBottomColor: String
    var borderBottomLeftRadius: String
    var borderBottomRightRadius: String
    var borderBottomStyle: String
    var borderBottomWidth: String
    var borderCollapse: String
    var borderColor: String
    var borderImage: String
    var borderImageOutset: String
    var borderImageRepeat: String
    var borderImageSlice: String
    var borderImageSource: String
    var borderImageWidth: String
    var borderInlineEnd: String
    var borderInlineEndColor: String
    var borderInlineEndStyle: String
    var borderInlineEndWidth: String
    var borderInlineStart: String
    var borderInlineStartColor: String
    var borderInlineStartStyle: String
    var borderInlineStartWidth: String
    var borderLeft: String
    var borderLeftColor: String
    var borderLeftStyle: String
    var borderLeftWidth: String
    var borderRadius: String
    var borderRight: String
    var borderRightColor: String
    var borderRightStyle: String
    var borderRightWidth: String
    var borderSpacing: String
    var borderStyle: String
    var borderTop: String
    var borderTopColor: String
    var borderTopLeftRadius: String
    var borderTopRightRadius: String
    var borderTopStyle: String
    var borderTopWidth: String
    var borderWidth: String
    var bottom: String
    var boxShadow: String
    var boxSizing: String
    var breakAfter: String
    var breakBefore: String
    var breakInside: String
    var captionSide: String
    var caretColor: String
    var clear: String
    var clip: String
    var clipPath: String
    var clipRule: String
    var color: String
    var colorInterpolation: String
    var colorInterpolationFilters: String
    var columnCount: String
    var columnFill: String
    var columnGap: String
    var columnRule: String
    var columnRuleColor: String
    var columnRuleStyle: String
    var columnRuleWidth: String
    var columnSpan: String
    var columnWidth: String
    var columns: String
    var content: String
    var counterIncrement: String
    var counterReset: String
    var cssFloat: String
    var cssText: String
    var cursor: String
    var direction: String
    var display: String
    var dominantBaseline: String
    var emptyCells: String
    var fill: String
    var fillOpacity: String
    var fillRule: String
    var filter: String
    var flex: String
    var flexBasis: String
    var flexDirection: String
    var flexFlow: String
    var flexGrow: String
    var flexShrink: String
    var flexWrap: String
    var float: String
    var floodColor: String
    var floodOpacity: String
    var font: String
    var fontFamily: String
    var fontFeatureSettings: String
    var fontKerning: String
    var fontSize: String
    var fontSizeAdjust: String
    var fontStretch: String
    var fontStyle: String
    var fontSynthesis: String
    var fontVariant: String
    var fontVariantCaps: String
    var fontVariantEastAsian: String
    var fontVariantLigatures: String
    var fontVariantNumeric: String
    var fontVariantPosition: String
    var fontWeight: String
    var gap: String
    var glyphOrientationVertical: String
    var grid: String
    var gridArea: String
    var gridAutoColumns: String
    var gridAutoFlow: String
    var gridAutoRows: String
    var gridColumn: String
    var gridColumnEnd: String
    var gridColumnGap: String
    var gridColumnStart: String
    var gridGap: String
    var gridRow: String
    var gridRowEnd: String
    var gridRowGap: String
    var gridRowStart: String
    var gridTemplate: String
    var gridTemplateAreas: String
    var gridTemplateColumns: String
    var gridTemplateRows: String
    var height: String
    var hyphens: String
    var imageOrientation: String
    var imageRendering: String
    var inlineSize: String
    var justifyContent: String
    var justifyItems: String
    var justifySelf: String
    var left: String
    var length: Number
    var letterSpacing: String
    var lightingColor: String
    var lineBreak: String
    var lineHeight: String
    var listStyle: String
    var listStyleImage: String
    var listStylePosition: String
    var listStyleType: String
    var margin: String
    var marginBlockEnd: String
    var marginBlockStart: String
    var marginBottom: String
    var marginInlineEnd: String
    var marginInlineStart: String
    var marginLeft: String
    var marginRight: String
    var marginTop: String
    var marker: String
    var markerEnd: String
    var markerMid: String
    var markerStart: String
    var mask: String
    var maskComposite: String
    var maskImage: String
    var maskPosition: String
    var maskRepeat: String
    var maskSize: String
    var maskType: String
    var maxBlockSize: String
    var maxHeight: String
    var maxInlineSize: String
    var maxWidth: String
    var minBlockSize: String
    var minHeight: String
    var minInlineSize: String
    var minWidth: String
    var objectFit: String
    var objectPosition: String
    var opacity: String
    var order: String
    var orphans: String
    var outline: String
    var outlineColor: String
    var outlineOffset: String
    var outlineStyle: String
    var outlineWidth: String
    var overflow: String
    var overflowAnchor: String
    var overflowWrap: String
    var overflowX: String
    var overflowY: String
    var padding: String
    var paddingBlockEnd: String
    var paddingBlockStart: String
    var paddingBottom: String
    var paddingInlineEnd: String
    var paddingInlineStart: String
    var paddingLeft: String
    var paddingRight: String
    var paddingTop: String
    var pageBreakAfter: String
    var pageBreakBefore: String
    var pageBreakInside: String
    var paintOrder: String
    var parentRule: CSSRule?
    var perspective: String
    var perspectiveOrigin: String
    var placeContent: String
    var placeItems: String
    var placeSelf: String
    var pointerEvents: String
    var position: String
    var quotes: String
    var resize: String
    var right: String
    var rotate: String
    var rowGap: String
    var rubyAlign: String
    var rubyPosition: String
    var scale: String
    var scrollBehavior: String
    var shapeRendering: String
    var stopColor: String
    var stopOpacity: String
    var stroke: String
    var strokeDasharray: String
    var strokeDashoffset: String
    var strokeLinecap: String
    var strokeLinejoin: String
    var strokeMiterlimit: String
    var strokeOpacity: String
    var strokeWidth: String
    var tabSize: String
    var tableLayout: String
    var textAlign: String
    var textAlignLast: String
    var textAnchor: String
    var textCombineUpright: String
    var textDecoration: String
    var textDecorationColor: String
    var textDecorationLine: String
    var textDecorationStyle: String
    var textEmphasis: String
    var textEmphasisColor: String
    var textEmphasisPosition: String
    var textEmphasisStyle: String
    var textIndent: String
    var textJustify: String
    var textOrientation: String
    var textOverflow: String
    var textRendering: String
    var textShadow: String
    var textTransform: String
    var textUnderlinePosition: String
    var top: String
    var touchAction: String
    var transform: String
    var transformBox: String
    var transformOrigin: String
    var transformStyle: String
    var transition: String
    var transitionDelay: String
    var transitionDuration: String
    var transitionProperty: String
    var transitionTimingFunction: String
    var translate: String
    var unicodeBidi: String
    var userSelect: String
    var verticalAlign: String
    var visibility: String
    var webkitAlignContent: String
    var webkitAlignItems: String
    var webkitAlignSelf: String
    var webkitAnimation: String
    var webkitAnimationDelay: String
    var webkitAnimationDirection: String
    var webkitAnimationDuration: String
    var webkitAnimationFillMode: String
    var webkitAnimationIterationCount: String
    var webkitAnimationName: String
    var webkitAnimationPlayState: String
    var webkitAnimationTimingFunction: String
    var webkitAppearance: String
    var webkitBackfaceVisibility: String
    var webkitBackgroundClip: String
    var webkitBackgroundOrigin: String
    var webkitBackgroundSize: String
    var webkitBorderBottomLeftRadius: String
    var webkitBorderBottomRightRadius: String
    var webkitBorderRadius: String
    var webkitBorderTopLeftRadius: String
    var webkitBorderTopRightRadius: String
    var webkitBoxAlign: String
    var webkitBoxFlex: String
    var webkitBoxOrdinalGroup: String
    var webkitBoxOrient: String
    var webkitBoxPack: String
    var webkitBoxShadow: String
    var webkitBoxSizing: String
    var webkitFilter: String
    var webkitFlex: String
    var webkitFlexBasis: String
    var webkitFlexDirection: String
    var webkitFlexFlow: String
    var webkitFlexGrow: String
    var webkitFlexShrink: String
    var webkitFlexWrap: String
    var webkitJustifyContent: String
    var webkitLineClamp: String
    var webkitMask: String
    var webkitMaskBoxImage: String
    var webkitMaskBoxImageOutset: String
    var webkitMaskBoxImageRepeat: String
    var webkitMaskBoxImageSlice: String
    var webkitMaskBoxImageSource: String
    var webkitMaskBoxImageWidth: String
    var webkitMaskClip: String
    var webkitMaskComposite: String
    var webkitMaskImage: String
    var webkitMaskOrigin: String
    var webkitMaskPosition: String
    var webkitMaskRepeat: String
    var webkitMaskSize: String
    var webkitOrder: String
    var webkitPerspective: String
    var webkitPerspectiveOrigin: String
    var webkitTapHighlightColor: String
    var webkitTextFillColor: String
    var webkitTextSizeAdjust: String
    var webkitTextStroke: String
    var webkitTextStrokeColor: String
    var webkitTextStrokeWidth: String
    var webkitTransform: String
    var webkitTransformOrigin: String
    var webkitTransformStyle: String
    var webkitTransition: String
    var webkitTransitionDelay: String
    var webkitTransitionDuration: String
    var webkitTransitionProperty: String
    var webkitTransitionTimingFunction: String
    var webkitUserSelect: String
    var whiteSpace: String
    var widows: String
    var width: String
    var willChange: String
    var wordBreak: String
    var wordSpacing: String
    var wordWrap: String
    var writingMode: String
    var zIndex: String
    var zoom: String
    fun getPropertyPriority(property: String): String
    fun getPropertyValue(property: String): String
    fun item(index: Number): String
    fun removeProperty(property: String): String
    fun setProperty(property: String, value: String?, priority: String = definedExternally)
    @nativeGetter
    operator fun get(index: Number): String?
    @nativeSetter
    operator fun set(index: Number, value: String)
}

external interface CSSStyleSheet : StyleSheet {
    var cssRules: CSSRuleList
    var ownerRule: CSSRule?
    var rules: CSSRuleList
    fun addRule(selector: String = definedExternally, style: String = definedExternally, index: Number = definedExternally): Number
    fun deleteRule(index: Number)
    fun insertRule(rule: String, index: Number = definedExternally): Number
    fun removeRule(index: Number = definedExternally)
}

external interface Clipboard : EventTarget {
    fun readText(): Promise<String>
    fun writeText(data: String): Promise<Unit>
}

external interface ClipboardEvent : Event {
    var clipboardData: DataTransfer?
}

external interface ConcatParams : Algorithm {
    var algorithmId: Uint8Array
    var hash: dynamic /* String? | Algorithm? */
        get() = definedExternally
        set(value) = definedExternally
    var partyUInfo: Uint8Array
    var partyVInfo: Uint8Array
    var privateInfo: Uint8Array?
        get() = definedExternally
        set(value) = definedExternally
    var publicInfo: Uint8Array?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Coordinates {
    var accuracy: Number
    var altitude: Number?
    var altitudeAccuracy: Number?
    var heading: Number?
    var latitude: Number
    var longitude: Number
    var speed: Number?
}

external interface Credential {
    var id: String
    var type: String
}

external interface CredentialsContainer {
    fun create(options: CredentialCreationOptions = definedExternally): Promise<Credential?>
    fun get(options: CredentialRequestOptions = definedExternally): Promise<Credential?>
    fun preventSilentAccess(): Promise<Unit>
    fun store(credential: Credential): Promise<Credential>
}

external interface Crypto {
    var subtle: SubtleCrypto
    fun <T> getRandomValues(array: T): T
}

external interface CryptoKey {
    var algorithm: KeyAlgorithm
    var extractable: Boolean
    var type: String /* "private" | "public" | "secret" */
    var usages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>
}

external interface CryptoKeyPair {
    var privateKey: CryptoKey
    var publicKey: CryptoKey
}

external interface CustomEvent__0 : CustomEvent<Any>

external interface DOMException {
    var code: Number
    var message: String
    var name: String
    var ABORT_ERR: Number
    var DATA_CLONE_ERR: Number
    var DOMSTRING_SIZE_ERR: Number
    var HIERARCHY_REQUEST_ERR: Number
    var INDEX_SIZE_ERR: Number
    var INUSE_ATTRIBUTE_ERR: Number
    var INVALID_ACCESS_ERR: Number
    var INVALID_CHARACTER_ERR: Number
    var INVALID_MODIFICATION_ERR: Number
    var INVALID_NODE_TYPE_ERR: Number
    var INVALID_STATE_ERR: Number
    var NAMESPACE_ERR: Number
    var NETWORK_ERR: Number
    var NOT_FOUND_ERR: Number
    var NOT_SUPPORTED_ERR: Number
    var NO_DATA_ALLOWED_ERR: Number
    var NO_MODIFICATION_ALLOWED_ERR: Number
    var QUOTA_EXCEEDED_ERR: Number
    var SECURITY_ERR: Number
    var SYNTAX_ERR: Number
    var TIMEOUT_ERR: Number
    var TYPE_MISMATCH_ERR: Number
    var URL_MISMATCH_ERR: Number
    var VALIDATION_ERR: Number
    var WRONG_DOCUMENT_ERR: Number
}

external interface DOML2DeprecatedColorProperty {
    var color: String
}

typealias SVGMatrix = DOMMatrix

typealias SVGPoint = DOMPoint

typealias SVGRect = DOMRect

external interface DOMStringList {
    var length: Number
    fun contains(string: String): Boolean
    fun item(index: Number): String?
    @nativeGetter
    operator fun get(index: Number): String?
    @nativeSetter
    operator fun set(index: Number, value: String)
}

external interface DeferredPermissionRequest {
    var id: Number
    var type: String /* "geolocation" | "media" | "pointerlock" | "unlimitedIndexedDBQuota" | "webnotifications" */
    var uri: String
    fun allow()
    fun deny()
}

external interface DeviceLightEvent : Event {
    var value: Number
}

external interface DeviceMotionEvent : Event {
    var acceleration: DeviceMotionEventAcceleration?
    var accelerationIncludingGravity: DeviceMotionEventAcceleration?
    var interval: Number
    var rotationRate: DeviceMotionEventRotationRate?
}

external interface DeviceMotionEventAcceleration {
    var x: Number?
    var y: Number?
    var z: Number?
}

external interface DeviceMotionEventRotationRate {
    var alpha: Number?
    var beta: Number?
    var gamma: Number?
}

external interface DeviceOrientationEvent : Event {
    var absolute: Boolean
    var alpha: Number?
    var beta: Number?
    var gamma: Number?
}

external interface DhImportKeyParams : Algorithm {
    var generator: Uint8Array
    var prime: Uint8Array
}

external interface DhKeyDeriveParams : Algorithm {
    var public: CryptoKey
}

external interface DhKeyGenParams : Algorithm {
    var generator: Uint8Array
    var prime: Uint8Array
}

external interface DocumentEventMap : GlobalEventHandlersEventMap, DocumentAndElementEventHandlersEventMap {
    var fullscreenchange: Event
    var fullscreenerror: Event
    var pointerlockchange: Event
    var pointerlockerror: Event
    var readystatechange: Event
    var visibilitychange: Event
}

external interface DocumentAndElementEventHandlersEventMap {
    var copy: ClipboardEvent
    var cut: ClipboardEvent
    var paste: ClipboardEvent
}

external interface DocumentEvent {
    fun createEvent(eventInterface: String /* "AnimationEvent" | "AnimationPlaybackEvent" | "AudioProcessingEvent" | "BeforeUnloadEvent" | "ClipboardEvent" | "CloseEvent" | "CompositionEvent" | "CustomEvent" | "DeviceLightEvent" | "DeviceMotionEvent" | "DeviceOrientationEvent" | "DragEvent" | "ErrorEvent" | "Event" | "Events" | "FocusEvent" | "FocusNavigationEvent" | "GamepadEvent" | "HashChangeEvent" | "IDBVersionChangeEvent" | "InputEvent" | "KeyboardEvent" | "ListeningStateChangedEvent" | "MSGestureEvent" | "MSMediaKeyMessageEvent" | "MSMediaKeyNeededEvent" | "MSPointerEvent" | "MediaEncryptedEvent" | "MediaKeyMessageEvent" | "MediaQueryListEvent" | "MediaStreamErrorEvent" | "MediaStreamEvent" | "MediaStreamTrackEvent" | "MessageEvent" | "MouseEvent" | "MouseEvents" | "MutationEvent" | "MutationEvents" | "OfflineAudioCompletionEvent" | "OverflowEvent" | "PageTransitionEvent" | "PaymentRequestUpdateEvent" | "PermissionRequestedEvent" | "PointerEvent" | "PopStateEvent" | "ProgressEvent" | "PromiseRejectionEvent" | "RTCDTMFToneChangeEvent" | "RTCDataChannelEvent" | "RTCDtlsTransportStateChangedEvent" | "RTCErrorEvent" | "RTCIceCandidatePairChangedEvent" | "RTCIceGathererEvent" | "RTCIceTransportStateChangedEvent" | "RTCPeerConnectionIceErrorEvent" | "RTCPeerConnectionIceEvent" | "RTCSsrcConflictEvent" | "RTCStatsEvent" | "RTCTrackEvent" | "SVGZoomEvent" | "SVGZoomEvents" | "SecurityPolicyViolationEvent" | "ServiceWorkerMessageEvent" | "SpeechRecognitionEvent" | "SpeechSynthesisErrorEvent" | "SpeechSynthesisEvent" | "StorageEvent" | "TextEvent" | "TouchEvent" | "TrackEvent" | "TransitionEvent" | "UIEvent" | "UIEvents" | "VRDisplayEvent" | "VRDisplayEvent " | "WebGLContextEvent" | "WheelEvent" */): dynamic /* Event */
}

external interface DocumentTimeline : AnimationTimeline

external interface EXT_blend_minmax {
    var MAX_EXT: GLenum
    var MIN_EXT: GLenum
}

external interface EXT_frag_depth

external interface EXT_sRGB {
    var FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING_EXT: GLenum
    var SRGB8_ALPHA8_EXT: GLenum
    var SRGB_ALPHA_EXT: GLenum
    var SRGB_EXT: GLenum
}

external interface EXT_shader_texture_lod

external interface EXT_texture_filter_anisotropic {
    var MAX_TEXTURE_MAX_ANISOTROPY_EXT: GLenum
    var TEXTURE_MAX_ANISOTROPY_EXT: GLenum
}

external interface ElementEventMap {
    var fullscreenchange: Event
    var fullscreenerror: Event
}

external interface ElementCSSInlineStyle {
    var style: CSSStyleDeclaration
}

external interface EventListenerObject {
    fun handleEvent(evt: Event)
}

external interface ExtensionScriptApis {
    fun extensionIdToShortId(extensionId: String): Number
    fun fireExtensionApiTelemetry(functionName: String, isSucceeded: Boolean, isSupported: Boolean, errorString: String)
    fun genericFunction(routerAddress: Any, parameters: String = definedExternally, callbackId: Number = definedExternally)
    fun genericSynchronousFunction(functionId: Number, parameters: String = definedExternally): String
    fun genericWebRuntimeCallout(to: Any, from: Any, payload: String)
    fun getExtensionId(): String
    fun registerGenericFunctionCallbackHandler(callbackHandler: Function<*>)
    fun registerGenericPersistentCallbackHandler(callbackHandler: Function<*>)
    fun registerWebRuntimeCallbackHandler(handler: Function<*>): Any
}

external interface FocusNavigationEvent : Event {
    var navigationReason: String /* "down" | "left" | "right" | "up" */
    var originHeight: Number
    var originLeft: Number
    var originTop: Number
    var originWidth: Number
    fun requestFocus()
}

external interface Gamepad {
    var axes: Array<Number>
    var buttons: Array<GamepadButton>
    var connected: Boolean
    var hand: String /* "" | "left" | "right" */
    var hapticActuators: Array<GamepadHapticActuator>
    var id: String
    var index: Number
    var mapping: String /* "" | "standard" */
    var pose: GamepadPose?
    var timestamp: Number
}

external interface GamepadButton {
    var pressed: Boolean
    var touched: Boolean
    var value: Number
}

external interface GamepadEvent : Event {
    var gamepad: Gamepad
}

external interface GamepadHapticActuator {
    var type: String /* "vibration" */
    fun pulse(value: Number, duration: Number): Promise<Boolean>
}

external interface GamepadPose {
    var angularAcceleration: Float32Array?
    var angularVelocity: Float32Array?
    var hasOrientation: Boolean
    var hasPosition: Boolean
    var linearAcceleration: Float32Array?
    var linearVelocity: Float32Array?
    var orientation: Float32Array?
    var position: Float32Array?
}

external interface Geolocation {
    fun clearWatch(watchId: Number)
    fun getCurrentPosition(successCallback: PositionCallback, errorCallback: PositionErrorCallback = definedExternally, options: PositionOptions = definedExternally)
    fun watchPosition(successCallback: PositionCallback, errorCallback: PositionErrorCallback = definedExternally, options: PositionOptions = definedExternally): Number
}

external interface GlobalEventHandlersEventMap {
    var abort: UIEvent
    var animationcancel: AnimationEvent
    var animationend: AnimationEvent
    var animationiteration: AnimationEvent
    var animationstart: AnimationEvent
    var auxclick: MouseEvent
    var blur: FocusEvent
    var cancel: Event
    var canplay: Event
    var canplaythrough: Event
    var change: Event
    var click: MouseEvent
    var close: Event
    var contextmenu: MouseEvent
    var cuechange: Event
    var dblclick: MouseEvent
    var drag: DragEvent
    var dragend: DragEvent
    var dragenter: DragEvent
    var dragexit: Event
    var dragleave: DragEvent
    var dragover: DragEvent
    var dragstart: DragEvent
    var drop: DragEvent
    var durationchange: Event
    var emptied: Event
    var ended: Event
    var error: ErrorEvent
    var focus: FocusEvent
    var focusin: FocusEvent
    var focusout: FocusEvent
    var gotpointercapture: PointerEvent
    var input: Event
    var invalid: Event
    var keydown: KeyboardEvent
    var keypress: KeyboardEvent
    var keyup: KeyboardEvent
    var load: Event
    var loadeddata: Event
    var loadedmetadata: Event
    var loadstart: Event
    var lostpointercapture: PointerEvent
    var mousedown: MouseEvent
    var mouseenter: MouseEvent
    var mouseleave: MouseEvent
    var mousemove: MouseEvent
    var mouseout: MouseEvent
    var mouseover: MouseEvent
    var mouseup: MouseEvent
    var pause: Event
    var play: Event
    var playing: Event
    var pointercancel: PointerEvent
    var pointerdown: PointerEvent
    var pointerenter: PointerEvent
    var pointerleave: PointerEvent
    var pointermove: PointerEvent
    var pointerout: PointerEvent
    var pointerover: PointerEvent
    var pointerup: PointerEvent
    var progress: ProgressEvent__0
    var ratechange: Event
    var reset: Event
    var resize: UIEvent
    var scroll: Event
    var securitypolicyviolation: SecurityPolicyViolationEvent
    var seeked: Event
    var seeking: Event
    var select: Event
    var selectionchange: Event
    var selectstart: Event
    var stalled: Event
    var submit: Event
    var suspend: Event
    var timeupdate: Event
    var toggle: Event
    var touchcancel: TouchEvent
    var touchend: TouchEvent
    var touchmove: TouchEvent
    var touchstart: TouchEvent
    var transitioncancel: TransitionEvent
    var transitionend: TransitionEvent
    var transitionrun: TransitionEvent
    var transitionstart: TransitionEvent
    var volumechange: Event
    var waiting: Event
    var wheel: WheelEvent
}

external interface HTMLBaseFontElement : HTMLElement, DOML2DeprecatedColorProperty {
    var face: String
    var size: Number
    fun <K : Nothing?> addEventListener(type: K, listener: (self: HTMLBaseFontElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: HTMLBaseFontElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: HTMLBaseFontElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: HTMLBaseFontElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: HTMLBaseFontElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: HTMLBaseFontElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface HTMLBodyElementEventMap : HTMLElementEventMap, WindowEventHandlersEventMap {
    var orientationchange: Event
}

external interface HTMLCollectionBase {
    var length: Number
    fun item(index: Number): Element?
    @nativeGetter
    operator fun get(index: Number): Element?
    @nativeSetter
    operator fun set(index: Number, value: Element)
}

external interface HTMLCollectionOf<T : Element> : HTMLCollectionBase {
    fun item(index: Number): T?
    fun namedItem(name: String): T?
    @nativeGetter
    operator fun get(index: Number): T?
    @nativeSetter
    override operator fun set(index: Number, value: T)
}

external interface HTMLElementEventMap : ElementEventMap, GlobalEventHandlersEventMap, DocumentAndElementEventHandlersEventMap

external interface HTMLFrameSetElementEventMap : HTMLElementEventMap, WindowEventHandlersEventMap

external interface HTMLMarqueeElementEventMap : HTMLElementEventMap {
    var bounce: Event
    var finish: Event
    var start: Event
}

external interface HTMLMediaElementEventMap : HTMLElementEventMap {
    var encrypted: MediaEncryptedEvent
    var waitingforkey: Event
}

external interface HTMLOrSVGElement {
    var autofocus: Boolean
    var dataset: DOMStringMap
    var nonce: String?
        get() = definedExternally
        set(value) = definedExternally
    var tabIndex: Number
    fun blur()
    fun focus(options: FocusOptions = definedExternally)
}

external interface HTMLTableDataCellElement : HTMLTableCellElement {
    fun <K : Nothing?> addEventListener(type: K, listener: (self: HTMLTableDataCellElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: HTMLTableDataCellElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: HTMLTableDataCellElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: HTMLTableDataCellElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: HTMLTableDataCellElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: HTMLTableDataCellElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface HTMLTableHeaderCellElement : HTMLTableCellElement {
    override var scope: String
    fun <K : Nothing?> addEventListener(type: K, listener: (self: HTMLTableHeaderCellElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: HTMLTableHeaderCellElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: HTMLTableHeaderCellElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: HTMLTableHeaderCellElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: HTMLTableHeaderCellElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: HTMLTableHeaderCellElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface HkdfCtrParams : Algorithm {
    var context: dynamic /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */
        get() = definedExternally
        set(value) = definedExternally
    var hash: dynamic /* String | Algorithm */
        get() = definedExternally
        set(value) = definedExternally
    var label: dynamic /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */
        get() = definedExternally
        set(value) = definedExternally
}

typealias IDBArrayKey = Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>

external interface IDBCursor {
    var direction: String /* "next" | "nextunique" | "prev" | "prevunique" */
    var key: dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */
        get() = definedExternally
        set(value) = definedExternally
    var primaryKey: dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */
        get() = definedExternally
        set(value) = definedExternally
    var source: dynamic /* IDBObjectStore | IDBIndex */
        get() = definedExternally
        set(value) = definedExternally
    fun advance(count: Number)
    fun `continue`(key: Number = definedExternally)
    fun `continue`()
    fun `continue`(key: String = definedExternally)
    fun `continue`(key: Date = definedExternally)
    fun `continue`(key: ArrayBufferView = definedExternally)
    fun `continue`(key: ArrayBuffer = definedExternally)
    fun `continue`(key: IDBArrayKey = definedExternally)
    fun continuePrimaryKey(key: Number, primaryKey: Any /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */)
    fun continuePrimaryKey(key: String, primaryKey: Any /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */)
    fun continuePrimaryKey(key: Date, primaryKey: Any /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */)
    fun continuePrimaryKey(key: ArrayBufferView, primaryKey: Any /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */)
    fun continuePrimaryKey(key: ArrayBuffer, primaryKey: Any /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */)
    fun continuePrimaryKey(key: IDBArrayKey, primaryKey: Any /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */)
    fun delete(): IDBRequest<Nothing?>
    fun update(value: Any): IDBRequest<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>
}

external interface IDBCursorWithValue : IDBCursor {
    var value: Any
}

external interface IDBDatabaseEventMap {
    var abort: Event
    var close: Event
    var error: Event
    var versionchange: IDBVersionChangeEvent
}

external interface IDBDatabase : EventTarget {
    var name: String
    var objectStoreNames: DOMStringList
    var onabort: ((self: IDBDatabase, ev: Event) -> Any)?
    var onclose: ((self: IDBDatabase, ev: Event) -> Any)?
    var onerror: ((self: IDBDatabase, ev: Event) -> Any)?
    var onversionchange: ((self: IDBDatabase, ev: IDBVersionChangeEvent) -> Any)?
    var version: Number
    fun close()
    fun createObjectStore(name: String, optionalParameters: IDBObjectStoreParameters = definedExternally): IDBObjectStore
    fun deleteObjectStore(name: String)
    fun transaction(storeNames: String, mode: String /* "readonly" | "readwrite" | "versionchange" */ = definedExternally): IDBTransaction
    fun transaction(storeNames: String): IDBTransaction
    fun transaction(storeNames: Array<String>, mode: String /* "readonly" | "readwrite" | "versionchange" */ = definedExternally): IDBTransaction
    fun transaction(storeNames: Array<String>): IDBTransaction
    fun <K : String> addEventListener(type: K, listener: (self: IDBDatabase, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: IDBDatabase, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: IDBDatabase, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: IDBDatabase, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: IDBDatabase, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: IDBDatabase, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface IDBFactory {
    fun cmp(first: Any, second: Any): Number
    fun deleteDatabase(name: String): IDBOpenDBRequest
    fun open(name: String, version: Number = definedExternally): IDBOpenDBRequest
}

external interface IDBIndex {
    var keyPath: dynamic /* String | Array<String> */
        get() = definedExternally
        set(value) = definedExternally
    var multiEntry: Boolean
    var name: String
    var objectStore: IDBObjectStore
    var unique: Boolean
    fun count(key: Number = definedExternally): IDBRequest<Number>
    fun count(): IDBRequest<Number>
    fun count(key: String = definedExternally): IDBRequest<Number>
    fun count(key: Date = definedExternally): IDBRequest<Number>
    fun count(key: ArrayBufferView = definedExternally): IDBRequest<Number>
    fun count(key: ArrayBuffer = definedExternally): IDBRequest<Number>
    fun count(key: IDBArrayKey = definedExternally): IDBRequest<Number>
    fun count(key: IDBKeyRange = definedExternally): IDBRequest<Number>
    fun get(key: Number): IDBRequest<Any?>
    fun get(key: String): IDBRequest<Any?>
    fun get(key: Date): IDBRequest<Any?>
    fun get(key: ArrayBufferView): IDBRequest<Any?>
    fun get(key: ArrayBuffer): IDBRequest<Any?>
    fun get(key: IDBArrayKey): IDBRequest<Any?>
    fun get(key: IDBKeyRange): IDBRequest<Any?>
    fun getAll(query: Number? = definedExternally, count: Number = definedExternally): IDBRequest<Array<Any>>
    fun getAll(): IDBRequest<Array<Any>>
    fun getAll(query: Number? = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: String? = definedExternally, count: Number = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: String? = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: Date? = definedExternally, count: Number = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: Date? = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: ArrayBufferView? = definedExternally, count: Number = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: ArrayBufferView? = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: ArrayBuffer? = definedExternally, count: Number = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: ArrayBuffer? = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: IDBArrayKey? = definedExternally, count: Number = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: IDBArrayKey? = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: IDBKeyRange? = definedExternally, count: Number = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: IDBKeyRange? = definedExternally): IDBRequest<Array<Any>>
    fun getAllKeys(query: Number? = definedExternally, count: Number = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: Number? = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: String? = definedExternally, count: Number = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: String? = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: Date? = definedExternally, count: Number = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: Date? = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: ArrayBufferView? = definedExternally, count: Number = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: ArrayBufferView? = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: ArrayBuffer? = definedExternally, count: Number = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: ArrayBuffer? = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: IDBArrayKey? = definedExternally, count: Number = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: IDBArrayKey? = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: IDBKeyRange? = definedExternally, count: Number = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: IDBKeyRange? = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getKey(key: Number): IDBRequest<dynamic /* Number? | String? | Date? | ArrayBufferView? | ArrayBuffer? | IDBArrayKey? */>
    fun getKey(key: String): IDBRequest<dynamic /* Number? | String? | Date? | ArrayBufferView? | ArrayBuffer? | IDBArrayKey? */>
    fun getKey(key: Date): IDBRequest<dynamic /* Number? | String? | Date? | ArrayBufferView? | ArrayBuffer? | IDBArrayKey? */>
    fun getKey(key: ArrayBufferView): IDBRequest<dynamic /* Number? | String? | Date? | ArrayBufferView? | ArrayBuffer? | IDBArrayKey? */>
    fun getKey(key: ArrayBuffer): IDBRequest<dynamic /* Number? | String? | Date? | ArrayBufferView? | ArrayBuffer? | IDBArrayKey? */>
    fun getKey(key: IDBArrayKey): IDBRequest<dynamic /* Number? | String? | Date? | ArrayBufferView? | ArrayBuffer? | IDBArrayKey? */>
    fun getKey(key: IDBKeyRange): IDBRequest<dynamic /* Number? | String? | Date? | ArrayBufferView? | ArrayBuffer? | IDBArrayKey? */>
    fun openCursor(query: Number? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: Number? = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: String? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: String? = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: Date? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: Date? = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: ArrayBufferView? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: ArrayBufferView? = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: ArrayBuffer? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: ArrayBuffer? = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: IDBArrayKey? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: IDBArrayKey? = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: IDBKeyRange? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: IDBKeyRange? = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openKeyCursor(query: Number? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: Number? = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: String? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: String? = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: Date? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: Date? = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: ArrayBufferView? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: ArrayBufferView? = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: ArrayBuffer? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: ArrayBuffer? = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: IDBArrayKey? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: IDBArrayKey? = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: IDBKeyRange? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: IDBKeyRange? = definedExternally): IDBRequest<IDBCursor?>
}

external interface IDBKeyRange {
    var lower: Any
    var lowerOpen: Boolean
    var upper: Any
    var upperOpen: Boolean
    fun includes(key: Any): Boolean
}

external interface IDBObjectStore {
    fun createIndex(name: String, keyPath: String, options: IDBIndexParameters = definedExternally): IDBIndex
    fun createIndex(name: String, keyPath: String): IDBIndex
    fun createIndex(name: String, keyPath: Iterable<String>, options: IDBIndexParameters = definedExternally): IDBIndex
    fun createIndex(name: String, keyPath: Iterable<String>): IDBIndex
    var autoIncrement: Boolean
    var indexNames: DOMStringList
    var keyPath: dynamic /* String | Array<String> */
        get() = definedExternally
        set(value) = definedExternally
    var name: String
    var transaction: IDBTransaction
    fun add(value: Any, key: Number = definedExternally): IDBRequest<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>
    fun add(value: Any): IDBRequest<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>
    fun add(value: Any, key: String = definedExternally): IDBRequest<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>
    fun add(value: Any, key: Date = definedExternally): IDBRequest<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>
    fun add(value: Any, key: ArrayBufferView = definedExternally): IDBRequest<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>
    fun add(value: Any, key: ArrayBuffer = definedExternally): IDBRequest<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>
    fun add(value: Any, key: IDBArrayKey = definedExternally): IDBRequest<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>
    fun clear(): IDBRequest<Nothing?>
    fun count(key: Number = definedExternally): IDBRequest<Number>
    fun count(): IDBRequest<Number>
    fun count(key: String = definedExternally): IDBRequest<Number>
    fun count(key: Date = definedExternally): IDBRequest<Number>
    fun count(key: ArrayBufferView = definedExternally): IDBRequest<Number>
    fun count(key: ArrayBuffer = definedExternally): IDBRequest<Number>
    fun count(key: IDBArrayKey = definedExternally): IDBRequest<Number>
    fun count(key: IDBKeyRange = definedExternally): IDBRequest<Number>
    fun createIndex(name: String, keyPath: Array<String>, options: IDBIndexParameters = definedExternally): IDBIndex
    fun createIndex(name: String, keyPath: Array<String>): IDBIndex
    fun delete(key: Number): IDBRequest<Nothing?>
    fun delete(key: String): IDBRequest<Nothing?>
    fun delete(key: Date): IDBRequest<Nothing?>
    fun delete(key: ArrayBufferView): IDBRequest<Nothing?>
    fun delete(key: ArrayBuffer): IDBRequest<Nothing?>
    fun delete(key: IDBArrayKey): IDBRequest<Nothing?>
    fun delete(key: IDBKeyRange): IDBRequest<Nothing?>
    fun deleteIndex(name: String)
    fun get(query: Number): IDBRequest<Any?>
    fun get(query: String): IDBRequest<Any?>
    fun get(query: Date): IDBRequest<Any?>
    fun get(query: ArrayBufferView): IDBRequest<Any?>
    fun get(query: ArrayBuffer): IDBRequest<Any?>
    fun get(query: IDBArrayKey): IDBRequest<Any?>
    fun get(query: IDBKeyRange): IDBRequest<Any?>
    fun getAll(query: Number? = definedExternally, count: Number = definedExternally): IDBRequest<Array<Any>>
    fun getAll(): IDBRequest<Array<Any>>
    fun getAll(query: Number? = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: String? = definedExternally, count: Number = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: String? = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: Date? = definedExternally, count: Number = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: Date? = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: ArrayBufferView? = definedExternally, count: Number = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: ArrayBufferView? = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: ArrayBuffer? = definedExternally, count: Number = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: ArrayBuffer? = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: IDBArrayKey? = definedExternally, count: Number = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: IDBArrayKey? = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: IDBKeyRange? = definedExternally, count: Number = definedExternally): IDBRequest<Array<Any>>
    fun getAll(query: IDBKeyRange? = definedExternally): IDBRequest<Array<Any>>
    fun getAllKeys(query: Number? = definedExternally, count: Number = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: Number? = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: String? = definedExternally, count: Number = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: String? = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: Date? = definedExternally, count: Number = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: Date? = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: ArrayBufferView? = definedExternally, count: Number = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: ArrayBufferView? = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: ArrayBuffer? = definedExternally, count: Number = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: ArrayBuffer? = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: IDBArrayKey? = definedExternally, count: Number = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: IDBArrayKey? = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: IDBKeyRange? = definedExternally, count: Number = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getAllKeys(query: IDBKeyRange? = definedExternally): IDBRequest<Array<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>>
    fun getKey(query: Number): IDBRequest<dynamic /* Number? | String? | Date? | ArrayBufferView? | ArrayBuffer? | IDBArrayKey? */>
    fun getKey(query: String): IDBRequest<dynamic /* Number? | String? | Date? | ArrayBufferView? | ArrayBuffer? | IDBArrayKey? */>
    fun getKey(query: Date): IDBRequest<dynamic /* Number? | String? | Date? | ArrayBufferView? | ArrayBuffer? | IDBArrayKey? */>
    fun getKey(query: ArrayBufferView): IDBRequest<dynamic /* Number? | String? | Date? | ArrayBufferView? | ArrayBuffer? | IDBArrayKey? */>
    fun getKey(query: ArrayBuffer): IDBRequest<dynamic /* Number? | String? | Date? | ArrayBufferView? | ArrayBuffer? | IDBArrayKey? */>
    fun getKey(query: IDBArrayKey): IDBRequest<dynamic /* Number? | String? | Date? | ArrayBufferView? | ArrayBuffer? | IDBArrayKey? */>
    fun getKey(query: IDBKeyRange): IDBRequest<dynamic /* Number? | String? | Date? | ArrayBufferView? | ArrayBuffer? | IDBArrayKey? */>
    fun index(name: String): IDBIndex
    fun openCursor(query: Number? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: Number? = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: String? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: String? = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: Date? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: Date? = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: ArrayBufferView? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: ArrayBufferView? = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: ArrayBuffer? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: ArrayBuffer? = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: IDBArrayKey? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: IDBArrayKey? = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: IDBKeyRange? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openCursor(query: IDBKeyRange? = definedExternally): IDBRequest<IDBCursorWithValue?>
    fun openKeyCursor(query: Number? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: Number? = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: String? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: String? = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: Date? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: Date? = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: ArrayBufferView? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: ArrayBufferView? = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: ArrayBuffer? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: ArrayBuffer? = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: IDBArrayKey? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: IDBArrayKey? = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: IDBKeyRange? = definedExternally, direction: String /* "next" | "nextunique" | "prev" | "prevunique" */ = definedExternally): IDBRequest<IDBCursor?>
    fun openKeyCursor(query: IDBKeyRange? = definedExternally): IDBRequest<IDBCursor?>
    fun put(value: Any, key: Number = definedExternally): IDBRequest<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>
    fun put(value: Any): IDBRequest<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>
    fun put(value: Any, key: String = definedExternally): IDBRequest<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>
    fun put(value: Any, key: Date = definedExternally): IDBRequest<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>
    fun put(value: Any, key: ArrayBufferView = definedExternally): IDBRequest<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>
    fun put(value: Any, key: ArrayBuffer = definedExternally): IDBRequest<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>
    fun put(value: Any, key: IDBArrayKey = definedExternally): IDBRequest<dynamic /* Number | String | Date | ArrayBufferView | ArrayBuffer | IDBArrayKey */>
}

external interface IDBOpenDBRequestEventMap : IDBRequestEventMap {
    var blocked: Event
    var upgradeneeded: IDBVersionChangeEvent
}

external interface IDBOpenDBRequest : IDBRequest<IDBDatabase> {
    var onblocked: ((self: IDBOpenDBRequest, ev: Event) -> Any)?
    var onupgradeneeded: ((self: IDBOpenDBRequest, ev: IDBVersionChangeEvent) -> Any)?
    fun <K : String> addEventListener(type: K, listener: (self: IDBOpenDBRequest, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: IDBOpenDBRequest, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: IDBOpenDBRequest, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: IDBOpenDBRequest, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: IDBOpenDBRequest, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: IDBOpenDBRequest, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface IDBRequestEventMap {
    var error: Event
    var success: Event
}

external interface IDBRequest<T> : EventTarget {
    var error: DOMException?
    var onerror: ((self: IDBRequest<T>, ev: Event) -> Any)?
    var onsuccess: ((self: IDBRequest<T>, ev: Event) -> Any)?
    var readyState: String /* "done" | "pending" */
    var result: T
    var source: dynamic /* IDBObjectStore | IDBIndex | IDBCursor */
        get() = definedExternally
        set(value) = definedExternally
    var transaction: IDBTransaction?
    fun <K : String> addEventListener(type: K, listener: (self: IDBRequest<T>, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: IDBRequest<T>, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: IDBRequest<T>, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: IDBRequest<T>, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: IDBRequest<T>, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: IDBRequest<T>, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface IDBTransactionEventMap {
    var abort: Event
    var complete: Event
    var error: Event
}

external interface IDBTransaction : EventTarget {
    var db: IDBDatabase
    var error: DOMException
    var mode: String /* "readonly" | "readwrite" | "versionchange" */
    var objectStoreNames: DOMStringList
    var onabort: ((self: IDBTransaction, ev: Event) -> Any)?
    var oncomplete: ((self: IDBTransaction, ev: Event) -> Any)?
    var onerror: ((self: IDBTransaction, ev: Event) -> Any)?
    fun abort()
    fun objectStore(name: String): IDBObjectStore
    fun <K : String> addEventListener(type: K, listener: (self: IDBTransaction, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: IDBTransaction, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: IDBTransaction, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: IDBTransaction, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: IDBTransaction, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: IDBTransaction, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface IDBVersionChangeEvent : Event {
    var newVersion: Number?
    var oldVersion: Number
}

external interface InnerHTML {
    var innerHTML: String
}

external interface LinkStyle {
    var sheet: CSSStyleSheet?
}

external interface ListeningStateChangedEvent : Event {
    var label: String
    var state: String /* "active" | "disambiguation" | "inactive" */
}

external interface MSFileSaver {
    fun msSaveBlob(blob: Any, defaultName: String = definedExternally): Boolean
    fun msSaveOrOpenBlob(blob: Any, defaultName: String = definedExternally): Boolean
}

external interface MSGestureEvent : UIEvent {
    var clientX: Number
    var clientY: Number
    var expansion: Number
    var gestureObject: Any
    var hwTimestamp: Number
    var offsetX: Number
    var offsetY: Number
    var rotation: Number
    var scale: Number
    var screenX: Number
    var screenY: Number
    var translationX: Number
    var translationY: Number
    var velocityAngular: Number
    var velocityExpansion: Number
    var velocityX: Number
    var velocityY: Number
    fun initGestureEvent(typeArg: String, canBubbleArg: Boolean, cancelableArg: Boolean, viewArg: Window, detailArg: Number, screenXArg: Number, screenYArg: Number, clientXArg: Number, clientYArg: Number, offsetXArg: Number, offsetYArg: Number, translationXArg: Number, translationYArg: Number, scaleArg: Number, expansionArg: Number, rotationArg: Number, velocityXArg: Number, velocityYArg: Number, velocityExpansionArg: Number, velocityAngularArg: Number, hwTimestampArg: Number)
    var MSGESTURE_FLAG_BEGIN: Number
    var MSGESTURE_FLAG_CANCEL: Number
    var MSGESTURE_FLAG_END: Number
    var MSGESTURE_FLAG_INERTIA: Number
    var MSGESTURE_FLAG_NONE: Number
}

external interface MSMediaKeyMessageEvent : Event {
    var destinationURL: String?
    var message: Uint8Array
}

external interface MSMediaKeyNeededEvent : Event {
    var initData: Uint8Array?
}

external interface MSNavigatorDoNotTrack {
    fun confirmSiteSpecificTrackingException(args: ConfirmSiteSpecificExceptionsInformation): Boolean
    fun confirmWebWideTrackingException(args: ExceptionInformation): Boolean
    fun removeSiteSpecificTrackingException(args: ExceptionInformation)
    fun removeWebWideTrackingException(args: ExceptionInformation)
    fun storeSiteSpecificTrackingException(args: StoreSiteSpecificExceptionsInformation)
    fun storeWebWideTrackingException(args: StoreExceptionsInformation)
}

external interface MSPointerEvent : MouseEvent {
    var currentPoint: Any
    var height: Number
    var hwTimestamp: Number
    var intermediatePoints: Any
    var isPrimary: Boolean
    var pointerId: Number
    var pointerType: Any
    var pressure: Number
    var rotation: Number
    var tiltX: Number
    var tiltY: Number
    var width: Number
    fun getCurrentPoint(element: Element)
    fun getIntermediatePoints(element: Element)
    fun initPointerEvent(typeArg: String, canBubbleArg: Boolean, cancelableArg: Boolean, viewArg: Window, detailArg: Number, screenXArg: Number, screenYArg: Number, clientXArg: Number, clientYArg: Number, ctrlKeyArg: Boolean, altKeyArg: Boolean, shiftKeyArg: Boolean, metaKeyArg: Boolean, buttonArg: Number, relatedTargetArg: EventTarget, offsetXArg: Number, offsetYArg: Number, widthArg: Number, heightArg: Number, pressure: Number, rotation: Number, tiltX: Number, tiltY: Number, pointerIdArg: Number, pointerType: Any, hwTimestampArg: Number, isPrimary: Boolean)
}

external interface MediaDeviceInfo {
    var deviceId: String
    var groupId: String
    var kind: String /* "audioinput" | "audiooutput" | "videoinput" */
    var label: String
    fun toJSON(): Any
}

external interface MediaDevicesEventMap {
    var devicechange: Event
}

external interface MediaDevices : EventTarget {
    var ondevicechange: ((self: MediaDevices, ev: Event) -> Any)?
    fun enumerateDevices(): Promise<Array<MediaDeviceInfo>>
    fun getSupportedConstraints(): MediaTrackSupportedConstraints
    fun getUserMedia(constraints: MediaStreamConstraints = definedExternally): Promise<MediaStream>
    fun <K : String> addEventListener(type: K, listener: (self: MediaDevices, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: MediaDevices, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: MediaDevices, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: MediaDevices, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: MediaDevices, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: MediaDevices, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface MediaEncryptedEvent : Event {
    var initData: ArrayBuffer?
    var initDataType: String
}

external interface MediaKeyMessageEvent : Event {
    var message: ArrayBuffer
    var messageType: String /* "individualization-request" | "license-release" | "license-renewal" | "license-request" */
}

external interface MediaKeySessionEventMap {
    var keystatuseschange: Event
    var message: MediaKeyMessageEvent
}

external interface MediaKeySession : EventTarget {
    var closed: Promise<Unit>
    var expiration: Number
    var keyStatuses: MediaKeyStatusMap
    var onkeystatuseschange: ((self: MediaKeySession, ev: Event) -> Any)?
    var onmessage: ((self: MediaKeySession, ev: MediaKeyMessageEvent) -> Any)?
    var sessionId: String
    fun close(): Promise<Unit>
    fun generateRequest(initDataType: String, initData: ArrayBufferView): Promise<Unit>
    fun generateRequest(initDataType: String, initData: ArrayBuffer): Promise<Unit>
    fun load(sessionId: String): Promise<Boolean>
    fun remove(): Promise<Unit>
    fun update(response: ArrayBufferView): Promise<Unit>
    fun update(response: ArrayBuffer): Promise<Unit>
    fun <K : String> addEventListener(type: K, listener: (self: MediaKeySession, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: MediaKeySession, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: MediaKeySession, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: MediaKeySession, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: MediaKeySession, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: MediaKeySession, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface MediaKeyStatusMap {
    fun entries(): IterableIterator<dynamic /* JsTuple<dynamic, String> */>
    fun keys(): IterableIterator<dynamic /* ArrayBufferView | ArrayBuffer */>
    fun values(): IterableIterator<String /* "expired" | "internal-error" | "output-downscaled" | "output-restricted" | "released" | "status-pending" | "usable" */>
    var size: Number
    fun get(keyId: ArrayBufferView): String /* "expired" | "internal-error" | "output-downscaled" | "output-restricted" | "released" | "status-pending" | "usable" */
    fun get(keyId: ArrayBuffer): String /* "expired" | "internal-error" | "output-downscaled" | "output-restricted" | "released" | "status-pending" | "usable" */
    fun has(keyId: ArrayBufferView): Boolean
    fun has(keyId: ArrayBuffer): Boolean
    fun forEach(callbackfn: (value: String /* "expired" | "internal-error" | "output-downscaled" | "output-restricted" | "released" | "status-pending" | "usable" */, key: Any /* ArrayBufferView | ArrayBuffer */, parent: MediaKeyStatusMap) -> Unit, thisArg: Any = definedExternally)
}

external interface MediaKeySystemAccess {
    var keySystem: String
    fun createMediaKeys(): Promise<MediaKeys>
    fun getConfiguration(): MediaKeySystemConfiguration
}

external interface MediaKeys {
    fun createSession(sessionType: String /* "persistent-license" | "temporary" */ = definedExternally): MediaKeySession
    fun setServerCertificate(serverCertificate: ArrayBufferView): Promise<Boolean>
    fun setServerCertificate(serverCertificate: ArrayBuffer): Promise<Boolean>
}

external interface MediaList {
    var length: Number
    var mediaText: String
    override fun toString(): String
    fun appendMedium(medium: String)
    fun deleteMedium(medium: String)
    fun item(index: Number): String?
    @nativeGetter
    operator fun get(index: Number): String?
    @nativeSetter
    operator fun set(index: Number, value: String)
}

external interface MediaQueryListEventMap {
    var change: MediaQueryListEvent
}

external interface MediaSourceEventMap {
    var sourceclose: Event
    var sourceended: Event
    var sourceopen: Event
}

external interface MediaSource : EventTarget {
    var activeSourceBuffers: SourceBufferList
    var duration: Number
    var onsourceclose: ((self: MediaSource, ev: Event) -> Any)?
    var onsourceended: ((self: MediaSource, ev: Event) -> Any)?
    var onsourceopen: ((self: MediaSource, ev: Event) -> Any)?
    var readyState: String /* "closed" | "ended" | "open" */
    var sourceBuffers: SourceBufferList
    fun addSourceBuffer(type: String): SourceBuffer
    fun clearLiveSeekableRange()
    fun endOfStream(error: String /* "decode" | "network" */ = definedExternally)
    fun removeSourceBuffer(sourceBuffer: SourceBuffer)
    fun setLiveSeekableRange(start: Number, end: Number)
    fun <K : String> addEventListener(type: K, listener: (self: MediaSource, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: MediaSource, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: MediaSource, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: MediaSource, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: MediaSource, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: MediaSource, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface MediaStreamEventMap {
    var addtrack: MediaStreamTrackEvent
    var removetrack: MediaStreamTrackEvent
}

external interface MediaStream : EventTarget {
    var active: Boolean
    var id: String
    var onaddtrack: ((self: MediaStream, ev: MediaStreamTrackEvent) -> Any)?
    var onremovetrack: ((self: MediaStream, ev: MediaStreamTrackEvent) -> Any)?
    fun addTrack(track: MediaStreamTrack)
    fun clone(): MediaStream
    fun getAudioTracks(): Array<MediaStreamTrack>
    fun getTrackById(trackId: String): MediaStreamTrack?
    fun getTracks(): Array<MediaStreamTrack>
    fun getVideoTracks(): Array<MediaStreamTrack>
    fun removeTrack(track: MediaStreamTrack)
    fun <K : String> addEventListener(type: K, listener: (self: MediaStream, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: MediaStream, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: MediaStream, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: MediaStream, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: MediaStream, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: MediaStream, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface MediaStreamError {
    var constraintName: String?
    var message: String?
    var name: String
}

external interface MediaStreamErrorEvent : Event {
    var error: MediaStreamError?
}

external interface MediaStreamEvent : Event {
    var stream: MediaStream?
}

external interface MediaStreamTrackEventMap {
    var ended: Event
    var isolationchange: Event
    var mute: Event
    var unmute: Event
}

external interface MediaStreamTrack : EventTarget {
    var enabled: Boolean
    var id: String
    var isolated: Boolean
    var kind: String
    var label: String
    var muted: Boolean
    var onended: ((self: MediaStreamTrack, ev: Event) -> Any)?
    var onisolationchange: ((self: MediaStreamTrack, ev: Event) -> Any)?
    var onmute: ((self: MediaStreamTrack, ev: Event) -> Any)?
    var onunmute: ((self: MediaStreamTrack, ev: Event) -> Any)?
    var readyState: String /* "ended" | "live" */
    fun applyConstraints(constraints: MediaTrackConstraints = definedExternally): Promise<Unit>
    fun clone(): MediaStreamTrack
    fun getCapabilities(): MediaTrackCapabilities
    fun getConstraints(): MediaTrackConstraints
    fun getSettings(): MediaTrackSettings
    fun stop()
    fun <K : String> addEventListener(type: K, listener: (self: MediaStreamTrack, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: MediaStreamTrack, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: MediaStreamTrack, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: MediaStreamTrack, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: MediaStreamTrack, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: MediaStreamTrack, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface MediaStreamTrackEvent : Event {
    var track: MediaStreamTrack
}

external interface MessagePortEventMap {
    var message: MessageEvent
    var messageerror: MessageEvent
}

external interface MutationEvent : Event {
    var attrChange: Number
    var attrName: String
    var newValue: String
    var prevValue: String
    var relatedNode: Node
    fun initMutationEvent(typeArg: String, canBubbleArg: Boolean, cancelableArg: Boolean, relatedNodeArg: Node, prevValueArg: String, newValueArg: String, attrNameArg: String, attrChangeArg: Number)
    var ADDITION: Number
    var MODIFICATION: Number
    var REMOVAL: Number
}

external interface NavigationPreloadManager {
    fun disable(): Promise<Unit>
    fun enable(): Promise<Unit>
    fun getState(): Promise<NavigationPreloadState>
    fun setHeaderValue(value: String): Promise<Unit>
}

external interface NavigatorAutomationInformation {
    var webdriver: Boolean
}

external interface NavigatorBeacon {
    fun sendBeacon(url: String, data: Blob? = definedExternally): Boolean
    fun sendBeacon(url: String): Boolean
    fun sendBeacon(url: String, data: Int8Array? = definedExternally): Boolean
    fun sendBeacon(url: String, data: Int16Array? = definedExternally): Boolean
    fun sendBeacon(url: String, data: Int32Array? = definedExternally): Boolean
    fun sendBeacon(url: String, data: Uint8Array? = definedExternally): Boolean
    fun sendBeacon(url: String, data: Uint16Array? = definedExternally): Boolean
    fun sendBeacon(url: String, data: Uint32Array? = definedExternally): Boolean
    fun sendBeacon(url: String, data: Uint8ClampedArray? = definedExternally): Boolean
    fun sendBeacon(url: String, data: Float32Array? = definedExternally): Boolean
    fun sendBeacon(url: String, data: Float64Array? = definedExternally): Boolean
    fun sendBeacon(url: String, data: DataView? = definedExternally): Boolean
    fun sendBeacon(url: String, data: ArrayBuffer? = definedExternally): Boolean
    fun sendBeacon(url: String, data: FormData? = definedExternally): Boolean
    fun sendBeacon(url: String, data: String? = definedExternally): Boolean
}

external interface NavigatorStorage {
    var storage: StorageManager
}

external interface NodeListOf<TNode : Node> : NodeList {
    override fun entries(): IterableIterator<dynamic /* JsTuple<Number, TNode> */>
    override fun keys(): IterableIterator<Number>
    override fun values(): IterableIterator<TNode>
    override var length: Number
    fun item(index: Number): TNode
    fun forEach(callbackfn: (value: TNode, key: Number, parent: NodeListOf<TNode>) -> Unit, thisArg: Any = definedExternally)
    @nativeGetter
    operator fun get(index: Number): TNode?
    @nativeSetter
    override operator fun set(index: Number, value: TNode)
}

external interface NotificationEventMap {
    var click: Event
    var close: Event
    var error: Event
    var show: Event
}

external interface OES_element_index_uint

external interface OES_standard_derivatives {
    var FRAGMENT_SHADER_DERIVATIVE_HINT_OES: GLenum
}

external interface OES_texture_float

external interface OES_texture_float_linear

external interface OES_texture_half_float {
    var HALF_FLOAT_OES: GLenum
}

external interface OES_texture_half_float_linear

external interface OES_vertex_array_object {
    fun bindVertexArrayOES(arrayObject: WebGLVertexArrayObjectOES?)
    fun createVertexArrayOES(): WebGLVertexArrayObjectOES?
    fun deleteVertexArrayOES(arrayObject: WebGLVertexArrayObjectOES?)
    fun isVertexArrayOES(arrayObject: WebGLVertexArrayObjectOES?): GLboolean
    var VERTEX_ARRAY_BINDING_OES: GLenum
}

external interface OfflineAudioCompletionEvent : Event {
    var renderedBuffer: AudioBuffer
}

external interface OffscreenCanvas : EventTarget {
    fun getContext(contextId: String /* "webgpu" | "2d" | "bitmaprenderer" | "webgl" | "webgl2" | "2d" | "bitmaprenderer" | "webgl" | "webgl2" */): dynamic /* WebGL2RenderingContext | OffscreenCanvasRenderingContext2D? | ImageBitmapRenderingContext? | WebGLRenderingContext? | WebGL2RenderingContext? */
    var height: Number
    var width: Number
    fun convertToBlob(options: ImageEncodeOptions = definedExternally): Promise<Blob>
    fun getContext(contextId: String /* "2d" */, options: CanvasRenderingContext2DSettings = definedExternally): OffscreenCanvasRenderingContext2D?
    fun getContext(contextId: String /* "bitmaprenderer" */, options: ImageBitmapRenderingContextSettings = definedExternally): ImageBitmapRenderingContext?
    fun getContext(contextId: String /* "webgl" | "webgl2" */, options: WebGLContextAttributes = definedExternally): dynamic /* WebGLRenderingContext | WebGL2RenderingContext */
    fun getContext(contextId: String /* "2d" | "bitmaprenderer" | "webgl" | "webgl2" */, options: Any = definedExternally): dynamic /* OffscreenCanvasRenderingContext2D? | ImageBitmapRenderingContext? | WebGLRenderingContext? | WebGL2RenderingContext? */
    fun transferToImageBitmap(): ImageBitmap
}

external interface OffscreenCanvasRenderingContext2D : CanvasCompositing, CanvasDrawImage, CanvasDrawPath, CanvasFillStrokeStyles, CanvasFilters, CanvasImageData, CanvasImageSmoothing, CanvasPath, CanvasPathDrawingStyles, CanvasRect, CanvasShadowStyles, CanvasState, CanvasText, CanvasTextDrawingStyles, CanvasTransform {
    var canvas: OffscreenCanvas
    fun commit()
}

external interface OverflowEvent : UIEvent {
    var horizontalOverflow: Boolean
    var orient: Number
    var verticalOverflow: Boolean
    var BOTH: Number
    var HORIZONTAL: Number
    var VERTICAL: Number
}

external interface PaymentRequestUpdateEvent : Event {
    fun updateWith(detailsPromise: PaymentDetailsUpdate)
    fun updateWith(detailsPromise: Promise<PaymentDetailsUpdate>)
}

external interface PerformanceEventMap {
    var resourcetimingbufferfull: Event
}

external interface PerformanceEntry {
    var duration: Number
    var entryType: String
    var name: String
    var startTime: Number
    fun toJSON(): Any
}

external interface PermissionRequest : DeferredPermissionRequest {
    var state: String /* "allow" | "defer" | "deny" | "unknown" */
    fun defer()
}

external interface PermissionRequestedEvent : Event {
    var permissionRequest: PermissionRequest
}

external interface PermissionStatusEventMap {
    var change: Event
}

external interface PermissionStatus : EventTarget {
    var onchange: ((self: PermissionStatus, ev: Event) -> Any)?
    var state: String /* "denied" | "granted" | "prompt" */
    fun <K : String> addEventListener(type: K, listener: (self: PermissionStatus, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: PermissionStatus, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: PermissionStatus, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: PermissionStatus, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: PermissionStatus, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: PermissionStatus, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface Permissions {
    fun query(permissionDesc: PermissionDescriptor): Promise<PermissionStatus>
    fun query(permissionDesc: DevicePermissionDescriptor): Promise<PermissionStatus>
    fun query(permissionDesc: MidiPermissionDescriptor): Promise<PermissionStatus>
    fun query(permissionDesc: PushPermissionDescriptor): Promise<PermissionStatus>
}

external interface PointerEvent : MouseEvent {
    var height: Number
    var isPrimary: Boolean
    var pointerId: Number
    var pointerType: String
    var pressure: Number
    var tangentialPressure: Number
    var tiltX: Number
    var tiltY: Number
    var twist: Number
    var width: Number
}

external interface Position {
    var coords: Coordinates
    var timestamp: Number
}

external interface PositionError {
    var code: Number
    var message: String
    var PERMISSION_DENIED: Number
    var POSITION_UNAVAILABLE: Number
    var TIMEOUT: Number
}

external interface ProgressEvent__0 : ProgressEvent<EventTarget>

external interface PushManager {
    fun getSubscription(): Promise<PushSubscription?>
    fun permissionState(options: PushSubscriptionOptionsInit = definedExternally): Promise<String /* "denied" | "granted" | "prompt" */>
    fun subscribe(options: PushSubscriptionOptionsInit = definedExternally): Promise<PushSubscription>
}

external interface PushSubscription {
    var endpoint: String
    var expirationTime: Number?
    var options: PushSubscriptionOptions
    fun getKey(name: String /* "auth" | "p256dh" */): ArrayBuffer?
    fun toJSON(): PushSubscriptionJSON
    fun unsubscribe(): Promise<Boolean>
}

external interface PushSubscriptionOptions {
    var applicationServerKey: ArrayBuffer?
    var userVisibleOnly: Boolean
}

external interface RTCDTMFSenderEventMap {
    var tonechange: RTCDTMFToneChangeEvent
}

external interface RTCDTMFSender : EventTarget {
    var canInsertDTMF: Boolean
    var ontonechange: ((self: RTCDTMFSender, ev: RTCDTMFToneChangeEvent) -> Any)?
    var toneBuffer: String
    fun insertDTMF(tones: String, duration: Number = definedExternally, interToneGap: Number = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: RTCDTMFSender, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: RTCDTMFSender, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: RTCDTMFSender, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: RTCDTMFSender, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: RTCDTMFSender, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: RTCDTMFSender, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface RTCDTMFToneChangeEvent : Event {
    var tone: String
}

external interface RTCDataChannelEventMap {
    var bufferedamountlow: Event
    var close: Event
    var error: RTCErrorEvent
    var message: MessageEvent
    var open: Event
}

external interface RTCDataChannel : EventTarget {
    var binaryType: String
    var bufferedAmount: Number
    var bufferedAmountLowThreshold: Number
    var id: Number?
    var label: String
    var maxPacketLifeTime: Number?
    var maxRetransmits: Number?
    var negotiated: Boolean
    var onbufferedamountlow: ((self: RTCDataChannel, ev: Event) -> Any)?
    var onclose: ((self: RTCDataChannel, ev: Event) -> Any)?
    var onerror: ((self: RTCDataChannel, ev: RTCErrorEvent) -> Any)?
    var onmessage: ((self: RTCDataChannel, ev: MessageEvent) -> Any)?
    var onopen: ((self: RTCDataChannel, ev: Event) -> Any)?
    var ordered: Boolean
    var priority: String /* "high" | "low" | "medium" | "very-low" */
    var protocol: String
    var readyState: String /* "closed" | "closing" | "connecting" | "open" */
    fun close()
    fun send(data: String)
    fun send(data: Blob)
    fun send(data: ArrayBuffer)
    fun send(data: ArrayBufferView)
    fun <K : String> addEventListener(type: K, listener: (self: RTCDataChannel, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: RTCDataChannel, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: RTCDataChannel, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: RTCDataChannel, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: RTCDataChannel, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: RTCDataChannel, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface RTCDataChannelEvent : Event {
    var channel: RTCDataChannel
}

external interface RTCDtlsTransportEventMap {
    var error: RTCErrorEvent
    var statechange: Event
}

external interface RTCDtlsTransport : EventTarget {
    var iceTransport: RTCIceTransport
    var onerror: ((self: RTCDtlsTransport, ev: RTCErrorEvent) -> Any)?
    var onstatechange: ((self: RTCDtlsTransport, ev: Event) -> Any)?
    var state: String /* "closed" | "connected" | "connecting" | "failed" | "new" */
    fun getRemoteCertificates(): Array<ArrayBuffer>
    fun <K : String> addEventListener(type: K, listener: (self: RTCDtlsTransport, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: RTCDtlsTransport, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: RTCDtlsTransport, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: RTCDtlsTransport, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: RTCDtlsTransport, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: RTCDtlsTransport, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface RTCDtlsTransportStateChangedEvent : Event {
    var state: String /* "closed" | "connected" | "connecting" | "failed" | "new" */
}

external interface RTCError : DOMException {
    var errorDetail: String /* "data-channel-failure" | "dtls-failure" | "fingerprint-failure" | "hardware-encoder-error" | "hardware-encoder-not-available" | "idp-bad-script-failure" | "idp-execution-failure" | "idp-load-failure" | "idp-need-login" | "idp-timeout" | "idp-tls-failure" | "idp-token-expired" | "idp-token-invalid" | "sctp-failure" | "sdp-syntax-error" */
    var httpRequestStatusCode: Number?
    var receivedAlert: Number?
    var sctpCauseCode: Number?
    var sdpLineNumber: Number?
    var sentAlert: Number?
}

external interface RTCErrorEvent : Event {
    var error: RTCError
}

external interface RTCIceCandidate {
    var candidate: String
    var component: String /* "rtcp" | "rtp" */
    var foundation: String?
    var port: Number?
    var priority: Number?
    var protocol: String /* "tcp" | "udp" */
    var relatedAddress: String?
    var relatedPort: Number?
    var sdpMLineIndex: Number?
    var sdpMid: String?
    var tcpType: String /* "active" | "passive" | "so" */
    var type: String /* "host" | "prflx" | "relay" | "srflx" */
    var usernameFragment: String?
    fun toJSON(): RTCIceCandidateInit
}

external interface RTCIceCandidatePairChangedEvent : Event {
    var pair: RTCIceCandidatePair
}

external interface RTCIceGathererEvent : Event {
    var candidate: dynamic /* RTCIceCandidateDictionary | RTCIceCandidateComplete */
        get() = definedExternally
        set(value) = definedExternally
}

external interface RTCIceTransportEventMap {
    var gatheringstatechange: Event
    var selectedcandidatepairchange: Event
    var statechange: Event
}

external interface RTCIceTransport : EventTarget {
    var component: String /* "rtcp" | "rtp" */
    var gatheringState: String /* "complete" | "gathering" | "new" */
    var ongatheringstatechange: ((self: RTCIceTransport, ev: Event) -> Any)?
    var onselectedcandidatepairchange: ((self: RTCIceTransport, ev: Event) -> Any)?
    var onstatechange: ((self: RTCIceTransport, ev: Event) -> Any)?
    var role: String /* "controlled" | "controlling" | "unknown" */
    var state: String /* "checking" | "closed" | "completed" | "connected" | "disconnected" | "failed" | "new" */
    fun getLocalCandidates(): Array<RTCIceCandidate>
    fun getLocalParameters(): RTCIceParameters?
    fun getRemoteCandidates(): Array<RTCIceCandidate>
    fun getRemoteParameters(): RTCIceParameters?
    fun getSelectedCandidatePair(): RTCIceCandidatePair?
    fun <K : String> addEventListener(type: K, listener: (self: RTCIceTransport, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: RTCIceTransport, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: RTCIceTransport, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: RTCIceTransport, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: RTCIceTransport, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: RTCIceTransport, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface RTCIceTransportStateChangedEvent : Event {
    var state: String /* "checking" | "closed" | "completed" | "connected" | "disconnected" | "failed" | "new" */
}

external interface RTCPeerConnectionIceErrorEvent : Event {
    var errorCode: Number
    var errorText: String
    var hostCandidate: String
    var url: String
}

external interface RTCPeerConnectionIceEvent : Event {
    var candidate: RTCIceCandidate?
    var url: String?
}

external interface RTCRtpReceiver {
    var rtcpTransport: RTCDtlsTransport?
    var track: MediaStreamTrack
    var transport: RTCDtlsTransport?
    fun getContributingSources(): Array<RTCRtpContributingSource>
    fun getParameters(): RTCRtpReceiveParameters
    fun getStats(): Promise<RTCStatsReport>
    fun getSynchronizationSources(): Array<RTCRtpSynchronizationSource>
}

external interface RTCRtpSender {
    var dtmf: RTCDTMFSender?
    var rtcpTransport: RTCDtlsTransport?
    var track: MediaStreamTrack?
    var transport: RTCDtlsTransport?
    fun getParameters(): RTCRtpSendParameters
    fun getStats(): Promise<RTCStatsReport>
    fun replaceTrack(withTrack: MediaStreamTrack?): Promise<Unit>
    fun setParameters(parameters: RTCRtpSendParameters): Promise<Unit>
    fun setStreams(vararg streams: MediaStream)
}

external interface RTCRtpTransceiver {
    fun setCodecPreferences(codecs: Iterable<RTCRtpCodecCapability>)
    var currentDirection: String /* "inactive" | "recvonly" | "sendonly" | "sendrecv" | "stopped" */
    var direction: String /* "inactive" | "recvonly" | "sendonly" | "sendrecv" | "stopped" */
    var mid: String?
    var receiver: RTCRtpReceiver
    var sender: RTCRtpSender
    fun setCodecPreferences(codecs: Array<RTCRtpCodecCapability>)
    fun stop()
}

external interface RTCSsrcConflictEvent : Event {
    var ssrc: Number
}

external interface RTCStatsEvent : Event {
    var report: RTCStatsReport
}

external interface RTCTrackEvent : Event {
    var receiver: RTCRtpReceiver
    var streams: Array<MediaStream>
    var track: MediaStreamTrack
    var transceiver: RTCRtpTransceiver
}

external interface ReadableByteStreamController {
    var byobRequest: ReadableStreamBYOBRequest?
    var desiredSize: Number?
    fun close()
    fun enqueue(chunk: ArrayBufferView)
    fun error(error: Any = definedExternally)
}

external interface `T$0` {
    var mode: String /* "byob" */
}

external interface `T$1`<R, T> {
    var writable: WritableStream<R>
    var readable: ReadableStream<T>
}

external interface ReadableStream<R> {
    var locked: Boolean
    fun cancel(reason: Any = definedExternally): Promise<Unit>
    fun getReader(options: `T$0`): ReadableStreamBYOBReader
    fun getReader(): ReadableStreamDefaultReader<R>
    fun <T> pipeThrough(__0: `T$1`<R, T>, options: PipeOptions = definedExternally): ReadableStream<T>
    fun pipeTo(dest: WritableStream<R>, options: PipeOptions = definedExternally): Promise<Unit>
    fun tee(): dynamic /* JsTuple<ReadableStream<R>, ReadableStream<R>> */
}

external interface ReadableStream__0 : ReadableStream<Any>

external interface ReadableStreamBYOBReader {
    var closed: Promise<Unit>
    fun cancel(reason: Any = definedExternally): Promise<Unit>
    fun <T : ArrayBufferView> read(view: T): Promise<dynamic /* ReadableStreamReadValueResult<T> | ReadableStreamReadDoneResult<T> */>
    fun releaseLock()
}

external interface ReadableStreamBYOBRequest {
    var view: ArrayBufferView
    fun respond(bytesWritten: Number)
    fun respondWithNewView(view: ArrayBufferView)
}

external interface ReadableStreamDefaultController<R> {
    var desiredSize: Number?
    fun close()
    fun enqueue(chunk: R)
    fun error(error: Any = definedExternally)
}

external interface ReadableStreamDefaultReader<R> {
    var closed: Promise<Unit>
    fun cancel(reason: Any = definedExternally): Promise<Unit>
    fun read(): Promise<dynamic /* ReadableStreamReadValueResult<R> | ReadableStreamReadDoneResult<R> */>
    fun releaseLock()
}

external interface SVGClipPathElement : SVGElement {
    var clipPathUnits: SVGAnimatedEnumeration
    var transform: SVGAnimatedTransformList
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGClipPathElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGClipPathElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGClipPathElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGClipPathElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGClipPathElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGClipPathElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGComponentTransferFunctionElement : SVGElement {
    var amplitude: SVGAnimatedNumber
    var exponent: SVGAnimatedNumber
    var intercept: SVGAnimatedNumber
    var offset: SVGAnimatedNumber
    var slope: SVGAnimatedNumber
    var tableValues: SVGAnimatedNumberList
    var type: SVGAnimatedEnumeration
    var SVG_FECOMPONENTTRANSFER_TYPE_DISCRETE: Number
    var SVG_FECOMPONENTTRANSFER_TYPE_GAMMA: Number
    var SVG_FECOMPONENTTRANSFER_TYPE_IDENTITY: Number
    var SVG_FECOMPONENTTRANSFER_TYPE_LINEAR: Number
    var SVG_FECOMPONENTTRANSFER_TYPE_TABLE: Number
    var SVG_FECOMPONENTTRANSFER_TYPE_UNKNOWN: Number
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGComponentTransferFunctionElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGComponentTransferFunctionElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGComponentTransferFunctionElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGComponentTransferFunctionElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGComponentTransferFunctionElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGComponentTransferFunctionElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGElementEventMap : ElementEventMap, GlobalEventHandlersEventMap, DocumentAndElementEventHandlersEventMap

external interface SVGFEBlendElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    var in1: SVGAnimatedString
    var in2: SVGAnimatedString
    var mode: SVGAnimatedEnumeration
    var SVG_FEBLEND_MODE_COLOR: Number
    var SVG_FEBLEND_MODE_COLOR_BURN: Number
    var SVG_FEBLEND_MODE_COLOR_DODGE: Number
    var SVG_FEBLEND_MODE_DARKEN: Number
    var SVG_FEBLEND_MODE_DIFFERENCE: Number
    var SVG_FEBLEND_MODE_EXCLUSION: Number
    var SVG_FEBLEND_MODE_HARD_LIGHT: Number
    var SVG_FEBLEND_MODE_HUE: Number
    var SVG_FEBLEND_MODE_LIGHTEN: Number
    var SVG_FEBLEND_MODE_LUMINOSITY: Number
    var SVG_FEBLEND_MODE_MULTIPLY: Number
    var SVG_FEBLEND_MODE_NORMAL: Number
    var SVG_FEBLEND_MODE_OVERLAY: Number
    var SVG_FEBLEND_MODE_SATURATION: Number
    var SVG_FEBLEND_MODE_SCREEN: Number
    var SVG_FEBLEND_MODE_SOFT_LIGHT: Number
    var SVG_FEBLEND_MODE_UNKNOWN: Number
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEBlendElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEBlendElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEBlendElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEBlendElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEBlendElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEBlendElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEColorMatrixElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    var in1: SVGAnimatedString
    var type: SVGAnimatedEnumeration
    var values: SVGAnimatedNumberList
    var SVG_FECOLORMATRIX_TYPE_HUEROTATE: Number
    var SVG_FECOLORMATRIX_TYPE_LUMINANCETOALPHA: Number
    var SVG_FECOLORMATRIX_TYPE_MATRIX: Number
    var SVG_FECOLORMATRIX_TYPE_SATURATE: Number
    var SVG_FECOLORMATRIX_TYPE_UNKNOWN: Number
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEColorMatrixElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEColorMatrixElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEColorMatrixElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEColorMatrixElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEColorMatrixElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEColorMatrixElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEComponentTransferElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    var in1: SVGAnimatedString
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEComponentTransferElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEComponentTransferElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEComponentTransferElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEComponentTransferElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEComponentTransferElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEComponentTransferElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFECompositeElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    var in1: SVGAnimatedString
    var in2: SVGAnimatedString
    var k1: SVGAnimatedNumber
    var k2: SVGAnimatedNumber
    var k3: SVGAnimatedNumber
    var k4: SVGAnimatedNumber
    var operator: SVGAnimatedEnumeration
    var SVG_FECOMPOSITE_OPERATOR_ARITHMETIC: Number
    var SVG_FECOMPOSITE_OPERATOR_ATOP: Number
    var SVG_FECOMPOSITE_OPERATOR_IN: Number
    var SVG_FECOMPOSITE_OPERATOR_OUT: Number
    var SVG_FECOMPOSITE_OPERATOR_OVER: Number
    var SVG_FECOMPOSITE_OPERATOR_UNKNOWN: Number
    var SVG_FECOMPOSITE_OPERATOR_XOR: Number
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFECompositeElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFECompositeElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFECompositeElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFECompositeElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFECompositeElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFECompositeElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEConvolveMatrixElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    var bias: SVGAnimatedNumber
    var divisor: SVGAnimatedNumber
    var edgeMode: SVGAnimatedEnumeration
    var in1: SVGAnimatedString
    var kernelMatrix: SVGAnimatedNumberList
    var kernelUnitLengthX: SVGAnimatedNumber
    var kernelUnitLengthY: SVGAnimatedNumber
    var orderX: SVGAnimatedInteger
    var orderY: SVGAnimatedInteger
    var preserveAlpha: SVGAnimatedBoolean
    var targetX: SVGAnimatedInteger
    var targetY: SVGAnimatedInteger
    var SVG_EDGEMODE_DUPLICATE: Number
    var SVG_EDGEMODE_NONE: Number
    var SVG_EDGEMODE_UNKNOWN: Number
    var SVG_EDGEMODE_WRAP: Number
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEConvolveMatrixElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEConvolveMatrixElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEConvolveMatrixElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEConvolveMatrixElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEConvolveMatrixElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEConvolveMatrixElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEDiffuseLightingElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    var diffuseConstant: SVGAnimatedNumber
    var in1: SVGAnimatedString
    var kernelUnitLengthX: SVGAnimatedNumber
    var kernelUnitLengthY: SVGAnimatedNumber
    var surfaceScale: SVGAnimatedNumber
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEDiffuseLightingElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEDiffuseLightingElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEDiffuseLightingElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEDiffuseLightingElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEDiffuseLightingElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEDiffuseLightingElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEDisplacementMapElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    var in1: SVGAnimatedString
    var in2: SVGAnimatedString
    var scale: SVGAnimatedNumber
    var xChannelSelector: SVGAnimatedEnumeration
    var yChannelSelector: SVGAnimatedEnumeration
    var SVG_CHANNEL_A: Number
    var SVG_CHANNEL_B: Number
    var SVG_CHANNEL_G: Number
    var SVG_CHANNEL_R: Number
    var SVG_CHANNEL_UNKNOWN: Number
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEDisplacementMapElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEDisplacementMapElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEDisplacementMapElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEDisplacementMapElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEDisplacementMapElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEDisplacementMapElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEDistantLightElement : SVGElement {
    var azimuth: SVGAnimatedNumber
    var elevation: SVGAnimatedNumber
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEDistantLightElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEDistantLightElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEDistantLightElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEDistantLightElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEDistantLightElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEDistantLightElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEFloodElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEFloodElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEFloodElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEFloodElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEFloodElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEFloodElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEFloodElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEFuncAElement : SVGComponentTransferFunctionElement {
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEFuncAElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEFuncAElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEFuncAElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEFuncAElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEFuncAElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEFuncAElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEFuncBElement : SVGComponentTransferFunctionElement {
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEFuncBElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEFuncBElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEFuncBElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEFuncBElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEFuncBElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEFuncBElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEFuncGElement : SVGComponentTransferFunctionElement {
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEFuncGElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEFuncGElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEFuncGElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEFuncGElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEFuncGElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEFuncGElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEFuncRElement : SVGComponentTransferFunctionElement {
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEFuncRElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEFuncRElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEFuncRElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEFuncRElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEFuncRElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEFuncRElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEGaussianBlurElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    var in1: SVGAnimatedString
    var stdDeviationX: SVGAnimatedNumber
    var stdDeviationY: SVGAnimatedNumber
    fun setStdDeviation(stdDeviationX: Number, stdDeviationY: Number)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEGaussianBlurElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEGaussianBlurElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEGaussianBlurElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEGaussianBlurElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEGaussianBlurElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEGaussianBlurElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEImageElement : SVGElement, SVGFilterPrimitiveStandardAttributes, SVGURIReference {
    var preserveAspectRatio: SVGAnimatedPreserveAspectRatio
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEImageElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEImageElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEImageElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEImageElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEImageElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEImageElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEMergeElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEMergeElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEMergeElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEMergeElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEMergeElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEMergeElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEMergeElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEMergeNodeElement : SVGElement {
    var in1: SVGAnimatedString
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEMergeNodeElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEMergeNodeElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEMergeNodeElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEMergeNodeElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEMergeNodeElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEMergeNodeElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEMorphologyElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    var in1: SVGAnimatedString
    var operator: SVGAnimatedEnumeration
    var radiusX: SVGAnimatedNumber
    var radiusY: SVGAnimatedNumber
    var SVG_MORPHOLOGY_OPERATOR_DILATE: Number
    var SVG_MORPHOLOGY_OPERATOR_ERODE: Number
    var SVG_MORPHOLOGY_OPERATOR_UNKNOWN: Number
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEMorphologyElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEMorphologyElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEMorphologyElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEMorphologyElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEMorphologyElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEMorphologyElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEOffsetElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    var dx: SVGAnimatedNumber
    var dy: SVGAnimatedNumber
    var in1: SVGAnimatedString
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEOffsetElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEOffsetElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEOffsetElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEOffsetElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEOffsetElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEOffsetElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFEPointLightElement : SVGElement {
    var x: SVGAnimatedNumber
    var y: SVGAnimatedNumber
    var z: SVGAnimatedNumber
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEPointLightElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEPointLightElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFEPointLightElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEPointLightElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEPointLightElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFEPointLightElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFESpecularLightingElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    var in1: SVGAnimatedString
    var kernelUnitLengthX: SVGAnimatedNumber
    var kernelUnitLengthY: SVGAnimatedNumber
    var specularConstant: SVGAnimatedNumber
    var specularExponent: SVGAnimatedNumber
    var surfaceScale: SVGAnimatedNumber
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFESpecularLightingElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFESpecularLightingElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFESpecularLightingElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFESpecularLightingElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFESpecularLightingElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFESpecularLightingElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFESpotLightElement : SVGElement {
    var limitingConeAngle: SVGAnimatedNumber
    var pointsAtX: SVGAnimatedNumber
    var pointsAtY: SVGAnimatedNumber
    var pointsAtZ: SVGAnimatedNumber
    var specularExponent: SVGAnimatedNumber
    var x: SVGAnimatedNumber
    var y: SVGAnimatedNumber
    var z: SVGAnimatedNumber
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFESpotLightElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFESpotLightElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFESpotLightElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFESpotLightElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFESpotLightElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFESpotLightElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFETileElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    var in1: SVGAnimatedString
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFETileElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFETileElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFETileElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFETileElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFETileElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFETileElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFETurbulenceElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    var baseFrequencyX: SVGAnimatedNumber
    var baseFrequencyY: SVGAnimatedNumber
    var numOctaves: SVGAnimatedInteger
    var seed: SVGAnimatedNumber
    var stitchTiles: SVGAnimatedEnumeration
    var type: SVGAnimatedEnumeration
    var SVG_STITCHTYPE_NOSTITCH: Number
    var SVG_STITCHTYPE_STITCH: Number
    var SVG_STITCHTYPE_UNKNOWN: Number
    var SVG_TURBULENCE_TYPE_FRACTALNOISE: Number
    var SVG_TURBULENCE_TYPE_TURBULENCE: Number
    var SVG_TURBULENCE_TYPE_UNKNOWN: Number
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFETurbulenceElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFETurbulenceElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFETurbulenceElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFETurbulenceElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFETurbulenceElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFETurbulenceElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFilterElement : SVGElement, SVGURIReference {
    var filterUnits: SVGAnimatedEnumeration
    var height: SVGAnimatedLength
    var primitiveUnits: SVGAnimatedEnumeration
    var width: SVGAnimatedLength
    var x: SVGAnimatedLength
    var y: SVGAnimatedLength
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFilterElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFilterElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGFilterElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFilterElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFilterElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGFilterElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGFilterPrimitiveStandardAttributes {
    var height: SVGAnimatedLength
    var result: SVGAnimatedString
    var width: SVGAnimatedLength
    var x: SVGAnimatedLength
    var y: SVGAnimatedLength
}

external interface SVGMaskElement : SVGElement {
    var height: SVGAnimatedLength
    var maskContentUnits: SVGAnimatedEnumeration
    var maskUnits: SVGAnimatedEnumeration
    var width: SVGAnimatedLength
    var x: SVGAnimatedLength
    var y: SVGAnimatedLength
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGMaskElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGMaskElement, ev: Any) -> Any)
    fun <K : Nothing?> addEventListener(type: K, listener: (self: SVGMaskElement, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener, options: Boolean)
    override fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions)
    override fun addEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGMaskElement, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGMaskElement, ev: Any) -> Any)
    fun <K : Nothing?> removeEventListener(type: K, listener: (self: SVGMaskElement, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, listener: EventListener, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean)
    override fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions)
}

external interface SVGPathSeg {
    var pathSegType: Number
    var pathSegTypeAsLetter: String
    var PATHSEG_ARC_ABS: Number
    var PATHSEG_ARC_REL: Number
    var PATHSEG_CLOSEPATH: Number
    var PATHSEG_CURVETO_CUBIC_ABS: Number
    var PATHSEG_CURVETO_CUBIC_REL: Number
    var PATHSEG_CURVETO_CUBIC_SMOOTH_ABS: Number
    var PATHSEG_CURVETO_CUBIC_SMOOTH_REL: Number
    var PATHSEG_CURVETO_QUADRATIC_ABS: Number
    var PATHSEG_CURVETO_QUADRATIC_REL: Number
    var PATHSEG_CURVETO_QUADRATIC_SMOOTH_ABS: Number
    var PATHSEG_CURVETO_QUADRATIC_SMOOTH_REL: Number
    var PATHSEG_LINETO_ABS: Number
    var PATHSEG_LINETO_HORIZONTAL_ABS: Number
    var PATHSEG_LINETO_HORIZONTAL_REL: Number
    var PATHSEG_LINETO_REL: Number
    var PATHSEG_LINETO_VERTICAL_ABS: Number
    var PATHSEG_LINETO_VERTICAL_REL: Number
    var PATHSEG_MOVETO_ABS: Number
    var PATHSEG_MOVETO_REL: Number
    var PATHSEG_UNKNOWN: Number
}

external interface SVGPathSegArcAbs : SVGPathSeg {
    var angle: Number
    var largeArcFlag: Boolean
    var r1: Number
    var r2: Number
    var sweepFlag: Boolean
    var x: Number
    var y: Number
}

external interface SVGPathSegArcRel : SVGPathSeg {
    var angle: Number
    var largeArcFlag: Boolean
    var r1: Number
    var r2: Number
    var sweepFlag: Boolean
    var x: Number
    var y: Number
}

external interface SVGPathSegClosePath : SVGPathSeg

external interface SVGPathSegCurvetoCubicAbs : SVGPathSeg {
    var x: Number
    var x1: Number
    var x2: Number
    var y: Number
    var y1: Number
    var y2: Number
}

external interface SVGPathSegCurvetoCubicRel : SVGPathSeg {
    var x: Number
    var x1: Number
    var x2: Number
    var y: Number
    var y1: Number
    var y2: Number
}

external interface SVGPathSegCurvetoCubicSmoothAbs : SVGPathSeg {
    var x: Number
    var x2: Number
    var y: Number
    var y2: Number
}

external interface SVGPathSegCurvetoCubicSmoothRel : SVGPathSeg {
    var x: Number
    var x2: Number
    var y: Number
    var y2: Number
}

external interface SVGPathSegCurvetoQuadraticAbs : SVGPathSeg {
    var x: Number
    var x1: Number
    var y: Number
    var y1: Number
}

external interface SVGPathSegCurvetoQuadraticRel : SVGPathSeg {
    var x: Number
    var x1: Number
    var y: Number
    var y1: Number
}

external interface SVGPathSegCurvetoQuadraticSmoothAbs : SVGPathSeg {
    var x: Number
    var y: Number
}

external interface SVGPathSegCurvetoQuadraticSmoothRel : SVGPathSeg {
    var x: Number
    var y: Number
}

external interface SVGPathSegLinetoAbs : SVGPathSeg {
    var x: Number
    var y: Number
}

external interface SVGPathSegLinetoHorizontalAbs : SVGPathSeg {
    var x: Number
}

external interface SVGPathSegLinetoHorizontalRel : SVGPathSeg {
    var x: Number
}

external interface SVGPathSegLinetoRel : SVGPathSeg {
    var x: Number
    var y: Number
}

external interface SVGPathSegLinetoVerticalAbs : SVGPathSeg {
    var y: Number
}

external interface SVGPathSegLinetoVerticalRel : SVGPathSeg {
    var y: Number
}

external interface SVGPathSegList {
    var numberOfItems: Number
    fun appendItem(newItem: SVGPathSeg): SVGPathSeg
    fun clear()
    fun getItem(index: Number): SVGPathSeg
    fun initialize(newItem: SVGPathSeg): SVGPathSeg
    fun insertItemBefore(newItem: SVGPathSeg, index: Number): SVGPathSeg
    fun removeItem(index: Number): SVGPathSeg
    fun replaceItem(newItem: SVGPathSeg, index: Number): SVGPathSeg
}

external interface SVGPathSegMovetoAbs : SVGPathSeg {
    var x: Number
    var y: Number
}

external interface SVGPathSegMovetoRel : SVGPathSeg {
    var x: Number
    var y: Number
}

external interface SVGSVGElementEventMap : SVGElementEventMap {
    var SVGUnload: Event
    var SVGZoom: SVGZoomEvent
}

external interface SVGZoomEvent : UIEvent {
    var newScale: Number
    var newTranslate: SVGPoint
    var previousScale: Number
    var previousTranslate: SVGPoint
    var zoomRectScreen: SVGRect
}

external interface ScreenOrientationEventMap {
    var change: Event
}

external interface ScreenOrientation : EventTarget {
    var angle: Number
    var onchange: ((self: ScreenOrientation, ev: Event) -> Any)?
    var type: String /* "landscape-primary" | "landscape-secondary" | "portrait-primary" | "portrait-secondary" */
    fun lock(orientation: String /* "any" | "landscape" | "landscape-primary" | "landscape-secondary" | "natural" | "portrait" | "portrait-primary" | "portrait-secondary" */): Promise<Unit>
    fun unlock()
    fun <K : String> addEventListener(type: K, listener: (self: ScreenOrientation, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: ScreenOrientation, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: ScreenOrientation, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: ScreenOrientation, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: ScreenOrientation, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: ScreenOrientation, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface SecurityPolicyViolationEvent : Event {
    var blockedURI: String
    var columnNumber: Number
    var documentURI: String
    var effectiveDirective: String
    var lineNumber: Number
    var originalPolicy: String
    var referrer: String
    var sourceFile: String
    var statusCode: Number
    var violatedDirective: String
}

external interface Selection {
    var anchorNode: Node?
    var anchorOffset: Number
    var focusNode: Node?
    var focusOffset: Number
    var isCollapsed: Boolean
    var rangeCount: Number
    var type: String
    fun addRange(range: Range)
    fun collapse(node: Node?, offset: Number = definedExternally)
    fun collapseToEnd()
    fun collapseToStart()
    fun containsNode(node: Node, allowPartialContainment: Boolean = definedExternally): Boolean
    fun deleteFromDocument()
    fun empty()
    fun extend(node: Node, offset: Number = definedExternally)
    fun getRangeAt(index: Number): Range
    fun removeAllRanges()
    fun removeRange(range: Range)
    fun selectAllChildren(node: Node)
    fun setBaseAndExtent(anchorNode: Node, anchorOffset: Number, focusNode: Node, focusOffset: Number)
    fun setPosition(node: Node?, offset: Number = definedExternally)
    override fun toString(): String
}

external interface ServiceWorkerEventMap : AbstractWorkerEventMap {
    var statechange: Event
}

external interface ServiceWorkerContainerEventMap {
    var controllerchange: Event
    var message: MessageEvent
    var messageerror: MessageEvent
}

external interface ServiceWorkerRegistrationEventMap {
    var updatefound: Event
}

external interface SourceBufferEventMap {
    var abort: Event
    var error: Event
    var update: Event
    var updateend: Event
    var updatestart: Event
}

external interface SourceBuffer : EventTarget {
    var appendWindowEnd: Number
    var appendWindowStart: Number
    var buffered: TimeRanges
    var mode: String /* "segments" | "sequence" */
    var onabort: ((self: SourceBuffer, ev: Event) -> Any)?
    var onerror: ((self: SourceBuffer, ev: Event) -> Any)?
    var onupdate: ((self: SourceBuffer, ev: Event) -> Any)?
    var onupdateend: ((self: SourceBuffer, ev: Event) -> Any)?
    var onupdatestart: ((self: SourceBuffer, ev: Event) -> Any)?
    var timestampOffset: Number
    var updating: Boolean
    fun abort()
    fun appendBuffer(data: ArrayBufferView)
    fun appendBuffer(data: ArrayBuffer)
    fun remove(start: Number, end: Number)
    fun <K : String> addEventListener(type: K, listener: (self: SourceBuffer, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: SourceBuffer, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: SourceBuffer, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: SourceBuffer, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: SourceBuffer, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: SourceBuffer, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface SourceBufferListEventMap {
    var addsourcebuffer: Event
    var removesourcebuffer: Event
}

external interface SourceBufferList : EventTarget {
    var length: Number
    var onaddsourcebuffer: ((self: SourceBufferList, ev: Event) -> Any)?
    var onremovesourcebuffer: ((self: SourceBufferList, ev: Event) -> Any)?
    fun <K : String> addEventListener(type: K, listener: (self: SourceBufferList, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: SourceBufferList, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: SourceBufferList, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: SourceBufferList, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: SourceBufferList, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: SourceBufferList, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
    @nativeGetter
    operator fun get(index: Number): SourceBuffer?
    @nativeSetter
    operator fun set(index: Number, value: SourceBuffer)
}

external interface SpeechRecognitionAlternative {
    var confidence: Number
    var transcript: String
}

external interface SpeechRecognitionEvent : Event {
    var resultIndex: Number
    var results: SpeechRecognitionResultList
}

external interface SpeechRecognitionResult {
    var isFinal: Boolean
    var length: Number
    fun item(index: Number): SpeechRecognitionAlternative
    @nativeGetter
    operator fun get(index: Number): SpeechRecognitionAlternative?
    @nativeSetter
    operator fun set(index: Number, value: SpeechRecognitionAlternative)
}

external interface SpeechRecognitionResultList {
    var length: Number
    fun item(index: Number): SpeechRecognitionResult
    @nativeGetter
    operator fun get(index: Number): SpeechRecognitionResult?
    @nativeSetter
    operator fun set(index: Number, value: SpeechRecognitionResult)
}

external interface SpeechSynthesisEventMap {
    var voiceschanged: Event
}

external interface SpeechSynthesis : EventTarget {
    var onvoiceschanged: ((self: SpeechSynthesis, ev: Event) -> Any)?
    var paused: Boolean
    var pending: Boolean
    var speaking: Boolean
    fun cancel()
    fun getVoices(): Array<SpeechSynthesisVoice>
    fun pause()
    fun resume()
    fun speak(utterance: SpeechSynthesisUtterance)
    fun <K : String> addEventListener(type: K, listener: (self: SpeechSynthesis, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: SpeechSynthesis, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: SpeechSynthesis, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: SpeechSynthesis, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: SpeechSynthesis, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: SpeechSynthesis, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface SpeechSynthesisErrorEvent : SpeechSynthesisEvent {
    var error: String /* "audio-busy" | "audio-hardware" | "canceled" | "interrupted" | "invalid-argument" | "language-unavailable" | "network" | "not-allowed" | "synthesis-failed" | "synthesis-unavailable" | "text-too-long" | "voice-unavailable" */
}

external interface SpeechSynthesisEvent : Event {
    var charIndex: Number
    var charLength: Number
    var elapsedTime: Number
    var name: String
    var utterance: SpeechSynthesisUtterance
}

external interface SpeechSynthesisUtteranceEventMap {
    var boundary: SpeechSynthesisEvent
    var end: SpeechSynthesisEvent
    var error: SpeechSynthesisErrorEvent
    var mark: SpeechSynthesisEvent
    var pause: SpeechSynthesisEvent
    var resume: SpeechSynthesisEvent
    var start: SpeechSynthesisEvent
}

external interface SpeechSynthesisUtterance : EventTarget {
    var lang: String
    var onboundary: ((self: SpeechSynthesisUtterance, ev: SpeechSynthesisEvent) -> Any)?
    var onend: ((self: SpeechSynthesisUtterance, ev: SpeechSynthesisEvent) -> Any)?
    var onerror: ((self: SpeechSynthesisUtterance, ev: SpeechSynthesisErrorEvent) -> Any)?
    var onmark: ((self: SpeechSynthesisUtterance, ev: SpeechSynthesisEvent) -> Any)?
    var onpause: ((self: SpeechSynthesisUtterance, ev: SpeechSynthesisEvent) -> Any)?
    var onresume: ((self: SpeechSynthesisUtterance, ev: SpeechSynthesisEvent) -> Any)?
    var onstart: ((self: SpeechSynthesisUtterance, ev: SpeechSynthesisEvent) -> Any)?
    var pitch: Number
    var rate: Number
    var text: String
    var voice: SpeechSynthesisVoice?
    var volume: Number
    fun <K : String> addEventListener(type: K, listener: (self: SpeechSynthesisUtterance, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> addEventListener(type: K, listener: (self: SpeechSynthesisUtterance, ev: Any) -> Any)
    fun <K : String> addEventListener(type: K, listener: (self: SpeechSynthesisUtterance, ev: Any) -> Any, options: AddEventListenerOptions = definedExternally)
    fun addEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: Boolean)
    fun addEventListener(type: String, listener: EventListener)
    override fun addEventListener(type: String, listener: EventListener?)
    fun addEventListener(type: String, listener: EventListener, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions)
    fun addEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: Boolean)
    fun addEventListener(type: String, listener: EventListenerObject)
    override fun addEventListener(type: String, listener: EventListenerObject?)
    fun addEventListener(type: String, listener: EventListenerObject, options: AddEventListenerOptions = definedExternally)
    override fun addEventListener(type: String, listener: EventListenerObject?, options: AddEventListenerOptions)
    fun <K : String> removeEventListener(type: K, listener: (self: SpeechSynthesisUtterance, ev: Any) -> Any, options: Boolean = definedExternally)
    fun <K : String> removeEventListener(type: K, listener: (self: SpeechSynthesisUtterance, ev: Any) -> Any)
    fun <K : String> removeEventListener(type: K, listener: (self: SpeechSynthesisUtterance, ev: Any) -> Any, options: EventListenerOptions = definedExternally)
    fun removeEventListener(type: String, listener: EventListener, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListener)
    override fun removeEventListener(type: String, callback: EventListener?)
    fun removeEventListener(type: String, listener: EventListener, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListener?, options: EventListenerOptions)
    fun removeEventListener(type: String, listener: EventListenerObject, options: Boolean = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: Boolean)
    fun removeEventListener(type: String, listener: EventListenerObject)
    override fun removeEventListener(type: String, callback: EventListenerObject?)
    fun removeEventListener(type: String, listener: EventListenerObject, options: EventListenerOptions = definedExternally)
    override fun removeEventListener(type: String, callback: EventListenerObject?, options: EventListenerOptions)
}

external interface SpeechSynthesisVoice {
    var default: Boolean
    var lang: String
    var localService: Boolean
    var name: String
    var voiceURI: String
}

external interface StorageManager {
    fun estimate(): Promise<StorageEstimate>
    fun persist(): Promise<Boolean>
    fun persisted(): Promise<Boolean>
}

external interface StyleMedia {
    var type: String
    fun matchMedium(mediaquery: String): Boolean
}

external interface StyleSheet {
    var disabled: Boolean
    var href: String?
    var media: MediaList
    var ownerNode: dynamic /* Element? | ProcessingInstruction? */
        get() = definedExternally
        set(value) = definedExternally
    var parentStyleSheet: CSSStyleSheet?
    var title: String?
    var type: String
}

external interface StyleSheetList {
    var length: Number
    fun item(index: Number): CSSStyleSheet?
    @nativeGetter
    operator fun get(index: Number): CSSStyleSheet?
    @nativeSetter
    operator fun set(index: Number, value: CSSStyleSheet)
}

external interface SubtleCrypto {
    fun decrypt(algorithm: String, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun decrypt(algorithm: Algorithm, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun decrypt(algorithm: RsaOaepParams, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun decrypt(algorithm: AesCtrParams, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun decrypt(algorithm: AesCbcParams, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun decrypt(algorithm: AesCmacParams, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun decrypt(algorithm: AesGcmParams, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun decrypt(algorithm: AesCfbParams, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun deriveBits(algorithm: String, baseKey: CryptoKey, length: Number): PromiseLike<ArrayBuffer>
    fun deriveBits(algorithm: Algorithm, baseKey: CryptoKey, length: Number): PromiseLike<ArrayBuffer>
    fun deriveBits(algorithm: EcdhKeyDeriveParams, baseKey: CryptoKey, length: Number): PromiseLike<ArrayBuffer>
    fun deriveBits(algorithm: DhKeyDeriveParams, baseKey: CryptoKey, length: Number): PromiseLike<ArrayBuffer>
    fun deriveBits(algorithm: ConcatParams, baseKey: CryptoKey, length: Number): PromiseLike<ArrayBuffer>
    fun deriveBits(algorithm: HkdfCtrParams, baseKey: CryptoKey, length: Number): PromiseLike<ArrayBuffer>
    fun deriveBits(algorithm: Pbkdf2Params, baseKey: CryptoKey, length: Number): PromiseLike<ArrayBuffer>
    fun deriveKey(algorithm: String, baseKey: CryptoKey, derivedKeyType: Any /* String | AesDerivedKeyParams | HmacImportParams | ConcatParams | HkdfCtrParams | Pbkdf2Params */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun deriveKey(algorithm: Algorithm, baseKey: CryptoKey, derivedKeyType: Any /* String | AesDerivedKeyParams | HmacImportParams | ConcatParams | HkdfCtrParams | Pbkdf2Params */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun deriveKey(algorithm: EcdhKeyDeriveParams, baseKey: CryptoKey, derivedKeyType: Any /* String | AesDerivedKeyParams | HmacImportParams | ConcatParams | HkdfCtrParams | Pbkdf2Params */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun deriveKey(algorithm: DhKeyDeriveParams, baseKey: CryptoKey, derivedKeyType: Any /* String | AesDerivedKeyParams | HmacImportParams | ConcatParams | HkdfCtrParams | Pbkdf2Params */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun deriveKey(algorithm: ConcatParams, baseKey: CryptoKey, derivedKeyType: Any /* String | AesDerivedKeyParams | HmacImportParams | ConcatParams | HkdfCtrParams | Pbkdf2Params */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun deriveKey(algorithm: HkdfCtrParams, baseKey: CryptoKey, derivedKeyType: Any /* String | AesDerivedKeyParams | HmacImportParams | ConcatParams | HkdfCtrParams | Pbkdf2Params */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun deriveKey(algorithm: Pbkdf2Params, baseKey: CryptoKey, derivedKeyType: Any /* String | AesDerivedKeyParams | HmacImportParams | ConcatParams | HkdfCtrParams | Pbkdf2Params */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun digest(algorithm: String, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun digest(algorithm: Algorithm, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun encrypt(algorithm: String, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun encrypt(algorithm: Algorithm, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun encrypt(algorithm: RsaOaepParams, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun encrypt(algorithm: AesCtrParams, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun encrypt(algorithm: AesCbcParams, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun encrypt(algorithm: AesCmacParams, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun encrypt(algorithm: AesGcmParams, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun encrypt(algorithm: AesCfbParams, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun exportKey(format: String /* "jwk" | "raw" | "pkcs8" | "spki" */, key: CryptoKey): dynamic /* PromiseLike */
    fun generateKey(algorithm: String, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<dynamic /* CryptoKeyPair | CryptoKey */>
    fun generateKey(algorithm: Algorithm, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<dynamic /* CryptoKeyPair | CryptoKey */>
    fun generateKey(algorithm: RsaHashedKeyGenParams, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKeyPair>
    fun generateKey(algorithm: EcKeyGenParams, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKeyPair>
    fun generateKey(algorithm: DhKeyGenParams, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKeyPair>
    fun generateKey(algorithm: AesKeyGenParams, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun generateKey(algorithm: HmacKeyGenParams, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun generateKey(algorithm: Pbkdf2Params, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "jwk" */, keyData: JsonWebKey, algorithm: String, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "jwk" */, keyData: JsonWebKey, algorithm: Algorithm, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "jwk" */, keyData: JsonWebKey, algorithm: RsaHashedImportParams, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "jwk" */, keyData: JsonWebKey, algorithm: EcKeyImportParams, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "jwk" */, keyData: JsonWebKey, algorithm: HmacImportParams, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "jwk" */, keyData: JsonWebKey, algorithm: DhImportKeyParams, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "jwk" */, keyData: JsonWebKey, algorithm: AesKeyAlgorithm, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "raw" | "pkcs8" | "spki" */, keyData: Int8Array, algorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "raw" | "pkcs8" | "spki" */, keyData: Int16Array, algorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "raw" | "pkcs8" | "spki" */, keyData: Int32Array, algorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "raw" | "pkcs8" | "spki" */, keyData: Uint8Array, algorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "raw" | "pkcs8" | "spki" */, keyData: Uint16Array, algorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "raw" | "pkcs8" | "spki" */, keyData: Uint32Array, algorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "raw" | "pkcs8" | "spki" */, keyData: Uint8ClampedArray, algorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "raw" | "pkcs8" | "spki" */, keyData: Float32Array, algorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "raw" | "pkcs8" | "spki" */, keyData: Float64Array, algorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "raw" | "pkcs8" | "spki" */, keyData: DataView, algorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String /* "raw" | "pkcs8" | "spki" */, keyData: ArrayBuffer, algorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun importKey(format: String, keyData: JsonWebKey, algorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun sign(algorithm: String, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun sign(algorithm: Algorithm, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun sign(algorithm: RsaPssParams, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun sign(algorithm: EcdsaParams, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun sign(algorithm: AesCmacParams, key: CryptoKey, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<ArrayBuffer>
    fun unwrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, wrappedKey: Int8Array, unwrappingKey: CryptoKey, unwrapAlgorithm: Any /* String | Algorithm | RsaOaepParams | AesCtrParams | AesCbcParams | AesCmacParams | AesGcmParams | AesCfbParams */, unwrappedKeyAlgorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun unwrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, wrappedKey: Int16Array, unwrappingKey: CryptoKey, unwrapAlgorithm: Any /* String | Algorithm | RsaOaepParams | AesCtrParams | AesCbcParams | AesCmacParams | AesGcmParams | AesCfbParams */, unwrappedKeyAlgorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun unwrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, wrappedKey: Int32Array, unwrappingKey: CryptoKey, unwrapAlgorithm: Any /* String | Algorithm | RsaOaepParams | AesCtrParams | AesCbcParams | AesCmacParams | AesGcmParams | AesCfbParams */, unwrappedKeyAlgorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun unwrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, wrappedKey: Uint8Array, unwrappingKey: CryptoKey, unwrapAlgorithm: Any /* String | Algorithm | RsaOaepParams | AesCtrParams | AesCbcParams | AesCmacParams | AesGcmParams | AesCfbParams */, unwrappedKeyAlgorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun unwrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, wrappedKey: Uint16Array, unwrappingKey: CryptoKey, unwrapAlgorithm: Any /* String | Algorithm | RsaOaepParams | AesCtrParams | AesCbcParams | AesCmacParams | AesGcmParams | AesCfbParams */, unwrappedKeyAlgorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun unwrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, wrappedKey: Uint32Array, unwrappingKey: CryptoKey, unwrapAlgorithm: Any /* String | Algorithm | RsaOaepParams | AesCtrParams | AesCbcParams | AesCmacParams | AesGcmParams | AesCfbParams */, unwrappedKeyAlgorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun unwrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, wrappedKey: Uint8ClampedArray, unwrappingKey: CryptoKey, unwrapAlgorithm: Any /* String | Algorithm | RsaOaepParams | AesCtrParams | AesCbcParams | AesCmacParams | AesGcmParams | AesCfbParams */, unwrappedKeyAlgorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun unwrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, wrappedKey: Float32Array, unwrappingKey: CryptoKey, unwrapAlgorithm: Any /* String | Algorithm | RsaOaepParams | AesCtrParams | AesCbcParams | AesCmacParams | AesGcmParams | AesCfbParams */, unwrappedKeyAlgorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun unwrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, wrappedKey: Float64Array, unwrappingKey: CryptoKey, unwrapAlgorithm: Any /* String | Algorithm | RsaOaepParams | AesCtrParams | AesCbcParams | AesCmacParams | AesGcmParams | AesCfbParams */, unwrappedKeyAlgorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun unwrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, wrappedKey: DataView, unwrappingKey: CryptoKey, unwrapAlgorithm: Any /* String | Algorithm | RsaOaepParams | AesCtrParams | AesCbcParams | AesCmacParams | AesGcmParams | AesCfbParams */, unwrappedKeyAlgorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun unwrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, wrappedKey: ArrayBuffer, unwrappingKey: CryptoKey, unwrapAlgorithm: Any /* String | Algorithm | RsaOaepParams | AesCtrParams | AesCbcParams | AesCmacParams | AesGcmParams | AesCfbParams */, unwrappedKeyAlgorithm: Any /* String | Algorithm | RsaHashedImportParams | EcKeyImportParams | HmacImportParams | DhImportKeyParams | AesKeyAlgorithm */, extractable: Boolean, keyUsages: Array<String /* "decrypt" | "deriveBits" | "deriveKey" | "encrypt" | "sign" | "unwrapKey" | "verify" | "wrapKey" */>): PromiseLike<CryptoKey>
    fun verify(algorithm: String, key: CryptoKey, signature: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<Boolean>
    fun verify(algorithm: Algorithm, key: CryptoKey, signature: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<Boolean>
    fun verify(algorithm: RsaPssParams, key: CryptoKey, signature: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<Boolean>
    fun verify(algorithm: EcdsaParams, key: CryptoKey, signature: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<Boolean>
    fun verify(algorithm: AesCmacParams, key: CryptoKey, signature: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */, data: Any /* Int8Array | Int16Array | Int32Array | Uint8Array | Uint16Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | DataView | ArrayBuffer */): PromiseLike<Boolean>
    fun wrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, key: CryptoKey, wrappingKey: CryptoKey, wrapAlgorithm: String): PromiseLike<ArrayBuffer>
    fun wrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, key: CryptoKey, wrappingKey: CryptoKey, wrapAlgorithm: Algorithm): PromiseLike<ArrayBuffer>
    fun wrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, key: CryptoKey, wrappingKey: CryptoKey, wrapAlgorithm: RsaOaepParams): PromiseLike<ArrayBuffer>
    fun wrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, key: CryptoKey, wrappingKey: CryptoKey, wrapAlgorithm: AesCtrParams): PromiseLike<ArrayBuffer>
    fun wrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, key: CryptoKey, wrappingKey: CryptoKey, wrapAlgorithm: AesCbcParams): PromiseLike<ArrayBuffer>
    fun wrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, key: CryptoKey, wrappingKey: CryptoKey, wrapAlgorithm: AesCmacParams): PromiseLike<ArrayBuffer>
    fun wrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, key: CryptoKey, wrappingKey: CryptoKey, wrapAlgorithm: AesGcmParams): PromiseLike<ArrayBuffer>
    fun wrapKey(format: String /* "raw" | "pkcs8" | "spki" | "jwk" | String */, key: CryptoKey, wrappingKey: CryptoKey, wrapAlgorithm: AesCfbParams): PromiseLike<ArrayBuffer>
}

external interface SyncManager {
    fun getTags(): Promise<Array<String>>
    fun register(tag: String): Promise<Unit>
}

external interface TextEvent : UIEvent {
    var data: String
    fun initTextEvent(typeArg: String, canBubbleArg: Boolean, cancelableArg: Boolean, viewArg: Window, dataArg: String, inputMethod: Number, locale: String)
    var DOM_INPUT_METHOD_DROP: Number
    var DOM_INPUT_METHOD_HANDWRITING: Number
    var DOM_INPUT_METHOD_IME: Number
    var DOM_INPUT_METHOD_KEYBOARD: Number
    var DOM_INPUT_METHOD_MULTIMODAL: Number
    var DOM_INPUT_METHOD_OPTION: Number
    var DOM_INPUT_METHOD_PASTE: Number
    var DOM_INPUT_METHOD_SCRIPT: Number
    var DOM_INPUT_METHOD_UNKNOWN: Number
    var DOM_INPUT_METHOD_VOICE: Number
}

external interface TextTrackEventMap {
    var cuechange: Event
}

external interface TextTrackCueEventMap {
    var enter: Event
    var exit: Event
}

external interface TextTrackListEventMap {
    var addtrack: TrackEvent
    var change: Event
    var removetrack: TrackEvent
}

external interface TransitionEvent : Event {
    var elapsedTime: Number
    var propertyName: String
    var pseudoElement: String
}

external interface VRDisplay : EventTarget {
    fun requestPresent(layers: Iterable<VRLayer>): Promise<Unit>
    var capabilities: VRDisplayCapabilities
    var depthFar: Number
    var depthNear: Number
    var displayId: Number
    var displayName: String
    var isConnected: Boolean
    var isPresenting: Boolean
    var stageParameters: VRStageParameters?
    fun cancelAnimationFrame(handle: Number)
    fun exitPresent(): Promise<Unit>
    fun getEyeParameters(whichEye: String): VREyeParameters
    fun getFrameData(frameData: VRFrameData): Boolean
    fun getLayers(): Array<VRLayer>
    fun getPose(): VRPose
    fun requestAnimationFrame(callback: FrameRequestCallback): Number
    fun requestPresent(layers: Array<VRLayer>): Promise<Unit>
    fun resetPose()
    fun submitFrame(pose: VRPose = definedExternally)
}

external interface VRDisplayCapabilities {
    var canPresent: Boolean
    var hasExternalDisplay: Boolean
    var hasOrientation: Boolean
    var hasPosition: Boolean
    var maxLayers: Number
}

external interface VRDisplayEvent : Event {
    var display: VRDisplay
    var reason: String /* "mounted" | "navigation" | "requested" | "unmounted" */
}

external interface VREyeParameters {
    var fieldOfView: VRFieldOfView
    var offset: Float32Array
    var renderHeight: Number
    var renderWidth: Number
}

external interface VRFieldOfView {
    var downDegrees: Number
    var leftDegrees: Number
    var rightDegrees: Number
    var upDegrees: Number
}

external interface VRFrameData {
    var leftProjectionMatrix: Float32Array
    var leftViewMatrix: Float32Array
    var pose: VRPose
    var rightProjectionMatrix: Float32Array
    var rightViewMatrix: Float32Array
    var timestamp: Number
}

external interface VRPose {
    var angularAcceleration: Float32Array?
    var angularVelocity: Float32Array?
    var linearAcceleration: Float32Array?
    var linearVelocity: Float32Array?
    var orientation: Float32Array?
    var position: Float32Array?
    var timestamp: Number
}

external interface VideoPlaybackQuality {
    var creationTime: Number
    var droppedVideoFrames: Number
    var totalVideoFrames: Number
}

external interface WEBGL_color_buffer_float {
    var FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE_EXT: GLenum
    var RGBA32F_EXT: GLenum
    var UNSIGNED_NORMALIZED_EXT: GLenum
}

external interface WEBGL_compressed_texture_astc {
    fun getSupportedProfiles(): Array<String>
    var COMPRESSED_RGBA_ASTC_10x10_KHR: GLenum
    var COMPRESSED_RGBA_ASTC_10x5_KHR: GLenum
    var COMPRESSED_RGBA_ASTC_10x6_KHR: GLenum
    var COMPRESSED_RGBA_ASTC_10x8_KHR: GLenum
    var COMPRESSED_RGBA_ASTC_12x10_KHR: GLenum
    var COMPRESSED_RGBA_ASTC_12x12_KHR: GLenum
    var COMPRESSED_RGBA_ASTC_4x4_KHR: GLenum
    var COMPRESSED_RGBA_ASTC_5x4_KHR: GLenum
    var COMPRESSED_RGBA_ASTC_5x5_KHR: GLenum
    var COMPRESSED_RGBA_ASTC_6x5_KHR: GLenum
    var COMPRESSED_RGBA_ASTC_6x6_KHR: GLenum
    var COMPRESSED_RGBA_ASTC_8x5_KHR: GLenum
    var COMPRESSED_RGBA_ASTC_8x6_KHR: GLenum
    var COMPRESSED_RGBA_ASTC_8x8_KHR: GLenum
    var COMPRESSED_SRGB8_ALPHA8_ASTC_10x10_KHR: GLenum
    var COMPRESSED_SRGB8_ALPHA8_ASTC_10x5_KHR: GLenum
    var COMPRESSED_SRGB8_ALPHA8_ASTC_10x6_KHR: GLenum
    var COMPRESSED_SRGB8_ALPHA8_ASTC_10x8_KHR: GLenum
    var COMPRESSED_SRGB8_ALPHA8_ASTC_12x10_KHR: GLenum
    var COMPRESSED_SRGB8_ALPHA8_ASTC_12x12_KHR: GLenum
    var COMPRESSED_SRGB8_ALPHA8_ASTC_4x4_KHR: GLenum
    var COMPRESSED_SRGB8_ALPHA8_ASTC_5x4_KHR: GLenum
    var COMPRESSED_SRGB8_ALPHA8_ASTC_5x5_KHR: GLenum
    var COMPRESSED_SRGB8_ALPHA8_ASTC_6x5_KHR: GLenum
    var COMPRESSED_SRGB8_ALPHA8_ASTC_6x6_KHR: GLenum
    var COMPRESSED_SRGB8_ALPHA8_ASTC_8x5_KHR: GLenum
    var COMPRESSED_SRGB8_ALPHA8_ASTC_8x6_KHR: GLenum
    var COMPRESSED_SRGB8_ALPHA8_ASTC_8x8_KHR: GLenum
}

external interface WEBGL_compressed_texture_s3tc {
    var COMPRESSED_RGBA_S3TC_DXT1_EXT: GLenum
    var COMPRESSED_RGBA_S3TC_DXT3_EXT: GLenum
    var COMPRESSED_RGBA_S3TC_DXT5_EXT: GLenum
    var COMPRESSED_RGB_S3TC_DXT1_EXT: GLenum
}

external interface WEBGL_compressed_texture_s3tc_srgb {
    var COMPRESSED_SRGB_ALPHA_S3TC_DXT1_EXT: GLenum
    var COMPRESSED_SRGB_ALPHA_S3TC_DXT3_EXT: GLenum
    var COMPRESSED_SRGB_ALPHA_S3TC_DXT5_EXT: GLenum
    var COMPRESSED_SRGB_S3TC_DXT1_EXT: GLenum
}

external interface WEBGL_debug_renderer_info {
    var UNMASKED_RENDERER_WEBGL: GLenum
    var UNMASKED_VENDOR_WEBGL: GLenum
}

external interface WEBGL_debug_shaders {
    fun getTranslatedShaderSource(shader: WebGLShader): String
}

external interface WEBGL_depth_texture {
    var UNSIGNED_INT_24_8_WEBGL: GLenum
}

external interface WEBGL_draw_buffers {
    fun drawBuffersWEBGL(buffers: Iterable<GLenum>)
    fun drawBuffersWEBGL(buffers: Array<GLenum>)
    var COLOR_ATTACHMENT0_WEBGL: GLenum
    var COLOR_ATTACHMENT10_WEBGL: GLenum
    var COLOR_ATTACHMENT11_WEBGL: GLenum
    var COLOR_ATTACHMENT12_WEBGL: GLenum
    var COLOR_ATTACHMENT13_WEBGL: GLenum
    var COLOR_ATTACHMENT14_WEBGL: GLenum
    var COLOR_ATTACHMENT15_WEBGL: GLenum
    var COLOR_ATTACHMENT1_WEBGL: GLenum
    var COLOR_ATTACHMENT2_WEBGL: GLenum
    var COLOR_ATTACHMENT3_WEBGL: GLenum
    var COLOR_ATTACHMENT4_WEBGL: GLenum
    var COLOR_ATTACHMENT5_WEBGL: GLenum
    var COLOR_ATTACHMENT6_WEBGL: GLenum
    var COLOR_ATTACHMENT7_WEBGL: GLenum
    var COLOR_ATTACHMENT8_WEBGL: GLenum
    var COLOR_ATTACHMENT9_WEBGL: GLenum
    var DRAW_BUFFER0_WEBGL: GLenum
    var DRAW_BUFFER10_WEBGL: GLenum
    var DRAW_BUFFER11_WEBGL: GLenum
    var DRAW_BUFFER12_WEBGL: GLenum
    var DRAW_BUFFER13_WEBGL: GLenum
    var DRAW_BUFFER14_WEBGL: GLenum
    var DRAW_BUFFER15_WEBGL: GLenum
    var DRAW_BUFFER1_WEBGL: GLenum
    var DRAW_BUFFER2_WEBGL: GLenum
    var DRAW_BUFFER3_WEBGL: GLenum
    var DRAW_BUFFER4_WEBGL: GLenum
    var DRAW_BUFFER5_WEBGL: GLenum
    var DRAW_BUFFER6_WEBGL: GLenum
    var DRAW_BUFFER7_WEBGL: GLenum
    var DRAW_BUFFER8_WEBGL: GLenum
    var DRAW_BUFFER9_WEBGL: GLenum
    var MAX_COLOR_ATTACHMENTS_WEBGL: GLenum
    var MAX_DRAW_BUFFERS_WEBGL: GLenum
}

external interface WEBGL_lose_context {
    fun loseContext()
    fun restoreContext()
}

external interface WebGL2RenderingContext : WebGL2RenderingContextBase, WebGL2RenderingContextOverloads, WebGLRenderingContextBase

external interface WebGL2RenderingContextBase {
    fun clearBufferfv(buffer: GLenum, drawbuffer: GLint, values: Iterable<GLfloat>, srcOffset: GLuint = definedExternally)
    fun clearBufferfv(buffer: GLenum, drawbuffer: GLint, values: Iterable<GLfloat>)
    fun clearBufferiv(buffer: GLenum, drawbuffer: GLint, values: Iterable<GLint>, srcOffset: GLuint = definedExternally)
    fun clearBufferiv(buffer: GLenum, drawbuffer: GLint, values: Iterable<GLint>)
    fun clearBufferuiv(buffer: GLenum, drawbuffer: GLint, values: Iterable<GLuint>, srcOffset: GLuint = definedExternally)
    fun clearBufferuiv(buffer: GLenum, drawbuffer: GLint, values: Iterable<GLuint>)
    fun drawBuffers(buffers: Iterable<GLenum>)
    fun getActiveUniforms(program: WebGLProgram, uniformIndices: Iterable<GLuint>, pname: GLenum): Any
    fun getUniformIndices(program: WebGLProgram, uniformNames: Iterable<String>): Iterable<GLuint>?
    fun invalidateFramebuffer(target: GLenum, attachments: Iterable<GLenum>)
    fun invalidateSubFramebuffer(target: GLenum, attachments: Iterable<GLenum>, x: GLint, y: GLint, width: GLsizei, height: GLsizei)
    fun transformFeedbackVaryings(program: WebGLProgram, varyings: Iterable<String>, bufferMode: GLenum)
    fun uniform1uiv(location: WebGLUniformLocation?, data: Iterable<GLuint>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform1uiv(location: WebGLUniformLocation?, data: Iterable<GLuint>)
    fun uniform1uiv(location: WebGLUniformLocation?, data: Iterable<GLuint>, srcOffset: GLuint = definedExternally)
    fun uniform2uiv(location: WebGLUniformLocation?, data: Iterable<GLuint>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform2uiv(location: WebGLUniformLocation?, data: Iterable<GLuint>)
    fun uniform2uiv(location: WebGLUniformLocation?, data: Iterable<GLuint>, srcOffset: GLuint = definedExternally)
    fun uniform3uiv(location: WebGLUniformLocation?, data: Iterable<GLuint>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform3uiv(location: WebGLUniformLocation?, data: Iterable<GLuint>)
    fun uniform3uiv(location: WebGLUniformLocation?, data: Iterable<GLuint>, srcOffset: GLuint = definedExternally)
    fun uniform4uiv(location: WebGLUniformLocation?, data: Iterable<GLuint>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform4uiv(location: WebGLUniformLocation?, data: Iterable<GLuint>)
    fun uniform4uiv(location: WebGLUniformLocation?, data: Iterable<GLuint>, srcOffset: GLuint = definedExternally)
    fun uniformMatrix2x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix2x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>)
    fun uniformMatrix2x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniformMatrix2x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix2x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>)
    fun uniformMatrix2x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniformMatrix3x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix3x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>)
    fun uniformMatrix3x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniformMatrix3x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix3x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>)
    fun uniformMatrix3x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniformMatrix4x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix4x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>)
    fun uniformMatrix4x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniformMatrix4x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix4x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>)
    fun uniformMatrix4x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally)
    fun vertexAttribI4iv(index: GLuint, values: Iterable<GLint>)
    fun vertexAttribI4uiv(index: GLuint, values: Iterable<GLuint>)
    fun beginQuery(target: GLenum, query: WebGLQuery)
    fun beginTransformFeedback(primitiveMode: GLenum)
    fun bindBufferBase(target: GLenum, index: GLuint, buffer: WebGLBuffer?)
    fun bindBufferRange(target: GLenum, index: GLuint, buffer: WebGLBuffer?, offset: GLintptr, size: GLsizeiptr)
    fun bindSampler(unit: GLuint, sampler: WebGLSampler?)
    fun bindTransformFeedback(target: GLenum, tf: WebGLTransformFeedback?)
    fun bindVertexArray(array: WebGLVertexArrayObject?)
    fun blitFramebuffer(srcX0: GLint, srcY0: GLint, srcX1: GLint, srcY1: GLint, dstX0: GLint, dstY0: GLint, dstX1: GLint, dstY1: GLint, mask: GLbitfield, filter: GLenum)
    fun clearBufferfi(buffer: GLenum, drawbuffer: GLint, depth: GLfloat, stencil: GLint)
    fun clearBufferfv(buffer: GLenum, drawbuffer: GLint, values: Float32Array, srcOffset: GLuint = definedExternally)
    fun clearBufferfv(buffer: GLenum, drawbuffer: GLint, values: Float32Array)
    fun clearBufferfv(buffer: GLenum, drawbuffer: GLint, values: Array<GLfloat>, srcOffset: GLuint = definedExternally)
    fun clearBufferfv(buffer: GLenum, drawbuffer: GLint, values: Array<GLfloat>)
    fun clearBufferiv(buffer: GLenum, drawbuffer: GLint, values: Int32Array, srcOffset: GLuint = definedExternally)
    fun clearBufferiv(buffer: GLenum, drawbuffer: GLint, values: Int32Array)
    fun clearBufferiv(buffer: GLenum, drawbuffer: GLint, values: Array<GLint>, srcOffset: GLuint = definedExternally)
    fun clearBufferiv(buffer: GLenum, drawbuffer: GLint, values: Array<GLint>)
    fun clearBufferuiv(buffer: GLenum, drawbuffer: GLint, values: Uint32Array, srcOffset: GLuint = definedExternally)
    fun clearBufferuiv(buffer: GLenum, drawbuffer: GLint, values: Uint32Array)
    fun clearBufferuiv(buffer: GLenum, drawbuffer: GLint, values: Array<GLuint>, srcOffset: GLuint = definedExternally)
    fun clearBufferuiv(buffer: GLenum, drawbuffer: GLint, values: Array<GLuint>)
    fun clientWaitSync(sync: WebGLSync, flags: GLbitfield, timeout: GLuint64): GLenum
    fun compressedTexImage3D(target: GLenum, level: GLint, internalformat: GLenum, width: GLsizei, height: GLsizei, depth: GLsizei, border: GLint, imageSize: GLsizei, offset: GLintptr)
    fun compressedTexImage3D(target: GLenum, level: GLint, internalformat: GLenum, width: GLsizei, height: GLsizei, depth: GLsizei, border: GLint, srcData: ArrayBufferView, srcOffset: GLuint = definedExternally, srcLengthOverride: GLuint = definedExternally)
    fun compressedTexImage3D(target: GLenum, level: GLint, internalformat: GLenum, width: GLsizei, height: GLsizei, depth: GLsizei, border: GLint, srcData: ArrayBufferView)
    fun compressedTexImage3D(target: GLenum, level: GLint, internalformat: GLenum, width: GLsizei, height: GLsizei, depth: GLsizei, border: GLint, srcData: ArrayBufferView, srcOffset: GLuint = definedExternally)
    fun compressedTexSubImage3D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, imageSize: GLsizei, offset: GLintptr)
    fun compressedTexSubImage3D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, srcData: ArrayBufferView, srcOffset: GLuint = definedExternally, srcLengthOverride: GLuint = definedExternally)
    fun compressedTexSubImage3D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, srcData: ArrayBufferView)
    fun compressedTexSubImage3D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, srcData: ArrayBufferView, srcOffset: GLuint = definedExternally)
    fun copyBufferSubData(readTarget: GLenum, writeTarget: GLenum, readOffset: GLintptr, writeOffset: GLintptr, size: GLsizeiptr)
    fun copyTexSubImage3D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, zoffset: GLint, x: GLint, y: GLint, width: GLsizei, height: GLsizei)
    fun createQuery(): WebGLQuery?
    fun createSampler(): WebGLSampler?
    fun createTransformFeedback(): WebGLTransformFeedback?
    fun createVertexArray(): WebGLVertexArrayObject?
    fun deleteQuery(query: WebGLQuery?)
    fun deleteSampler(sampler: WebGLSampler?)
    fun deleteSync(sync: WebGLSync?)
    fun deleteTransformFeedback(tf: WebGLTransformFeedback?)
    fun deleteVertexArray(vertexArray: WebGLVertexArrayObject?)
    fun drawArraysInstanced(mode: GLenum, first: GLint, count: GLsizei, instanceCount: GLsizei)
    fun drawBuffers(buffers: Array<GLenum>)
    fun drawElementsInstanced(mode: GLenum, count: GLsizei, type: GLenum, offset: GLintptr, instanceCount: GLsizei)
    fun drawRangeElements(mode: GLenum, start: GLuint, end: GLuint, count: GLsizei, type: GLenum, offset: GLintptr)
    fun endQuery(target: GLenum)
    fun endTransformFeedback()
    fun fenceSync(condition: GLenum, flags: GLbitfield): WebGLSync?
    fun framebufferTextureLayer(target: GLenum, attachment: GLenum, texture: WebGLTexture?, level: GLint, layer: GLint)
    fun getActiveUniformBlockName(program: WebGLProgram, uniformBlockIndex: GLuint): String?
    fun getActiveUniformBlockParameter(program: WebGLProgram, uniformBlockIndex: GLuint, pname: GLenum): Any
    fun getActiveUniforms(program: WebGLProgram, uniformIndices: Array<GLuint>, pname: GLenum): Any
    fun getBufferSubData(target: GLenum, srcByteOffset: GLintptr, dstBuffer: ArrayBufferView, dstOffset: GLuint = definedExternally, length: GLuint = definedExternally)
    fun getFragDataLocation(program: WebGLProgram, name: String): GLint
    fun getIndexedParameter(target: GLenum, index: GLuint): Any
    fun getInternalformatParameter(target: GLenum, internalformat: GLenum, pname: GLenum): Any
    fun getQuery(target: GLenum, pname: GLenum): WebGLQuery?
    fun getQueryParameter(query: WebGLQuery, pname: GLenum): Any
    fun getSamplerParameter(sampler: WebGLSampler, pname: GLenum): Any
    fun getSyncParameter(sync: WebGLSync, pname: GLenum): Any
    fun getTransformFeedbackVarying(program: WebGLProgram, index: GLuint): WebGLActiveInfo?
    fun getUniformBlockIndex(program: WebGLProgram, uniformBlockName: String): GLuint
    fun getUniformIndices(program: WebGLProgram, uniformNames: Array<String>): Array<GLuint>?
    fun invalidateFramebuffer(target: GLenum, attachments: Array<GLenum>)
    fun invalidateSubFramebuffer(target: GLenum, attachments: Array<GLenum>, x: GLint, y: GLint, width: GLsizei, height: GLsizei)
    fun isQuery(query: WebGLQuery?): GLboolean
    fun isSampler(sampler: WebGLSampler?): GLboolean
    fun isSync(sync: WebGLSync?): GLboolean
    fun isTransformFeedback(tf: WebGLTransformFeedback?): GLboolean
    fun isVertexArray(vertexArray: WebGLVertexArrayObject?): GLboolean
    fun pauseTransformFeedback()
    fun readBuffer(src: GLenum)
    fun renderbufferStorageMultisample(target: GLenum, samples: GLsizei, internalformat: GLenum, width: GLsizei, height: GLsizei)
    fun resumeTransformFeedback()
    fun samplerParameterf(sampler: WebGLSampler, pname: GLenum, param: GLfloat)
    fun samplerParameteri(sampler: WebGLSampler, pname: GLenum, param: GLint)
    fun texImage3D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, border: GLint, format: GLenum, type: GLenum, pboOffset: GLintptr)
    fun texImage3D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, border: GLint, format: GLenum, type: GLenum, source: ImageBitmap)
    fun texImage3D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, border: GLint, format: GLenum, type: GLenum, source: ImageData)
    fun texImage3D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, border: GLint, format: GLenum, type: GLenum, source: HTMLImageElement)
    fun texImage3D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, border: GLint, format: GLenum, type: GLenum, source: HTMLCanvasElement)
    fun texImage3D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, border: GLint, format: GLenum, type: GLenum, source: HTMLVideoElement)
    fun texImage3D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, border: GLint, format: GLenum, type: GLenum, source: OffscreenCanvas)
    fun texImage3D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, border: GLint, format: GLenum, type: GLenum, srcData: ArrayBufferView?)
    fun texImage3D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, border: GLint, format: GLenum, type: GLenum, srcData: ArrayBufferView, srcOffset: GLuint)
    fun texStorage2D(target: GLenum, levels: GLsizei, internalformat: GLenum, width: GLsizei, height: GLsizei)
    fun texStorage3D(target: GLenum, levels: GLsizei, internalformat: GLenum, width: GLsizei, height: GLsizei, depth: GLsizei)
    fun texSubImage3D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, pboOffset: GLintptr)
    fun texSubImage3D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, source: ImageBitmap)
    fun texSubImage3D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, source: ImageData)
    fun texSubImage3D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, source: HTMLImageElement)
    fun texSubImage3D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, source: HTMLCanvasElement)
    fun texSubImage3D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, source: HTMLVideoElement)
    fun texSubImage3D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, source: OffscreenCanvas)
    fun texSubImage3D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, srcData: ArrayBufferView?, srcOffset: GLuint = definedExternally)
    fun texSubImage3D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, srcData: ArrayBufferView?)
    fun transformFeedbackVaryings(program: WebGLProgram, varyings: Array<String>, bufferMode: GLenum)
    fun uniform1ui(location: WebGLUniformLocation?, v0: GLuint)
    fun uniform1uiv(location: WebGLUniformLocation?, data: Uint32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform1uiv(location: WebGLUniformLocation?, data: Uint32Array)
    fun uniform1uiv(location: WebGLUniformLocation?, data: Uint32Array, srcOffset: GLuint = definedExternally)
    fun uniform1uiv(location: WebGLUniformLocation?, data: Array<GLuint>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform1uiv(location: WebGLUniformLocation?, data: Array<GLuint>)
    fun uniform1uiv(location: WebGLUniformLocation?, data: Array<GLuint>, srcOffset: GLuint = definedExternally)
    fun uniform2ui(location: WebGLUniformLocation?, v0: GLuint, v1: GLuint)
    fun uniform2uiv(location: WebGLUniformLocation?, data: Uint32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform2uiv(location: WebGLUniformLocation?, data: Uint32Array)
    fun uniform2uiv(location: WebGLUniformLocation?, data: Uint32Array, srcOffset: GLuint = definedExternally)
    fun uniform2uiv(location: WebGLUniformLocation?, data: Array<GLuint>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform2uiv(location: WebGLUniformLocation?, data: Array<GLuint>)
    fun uniform2uiv(location: WebGLUniformLocation?, data: Array<GLuint>, srcOffset: GLuint = definedExternally)
    fun uniform3ui(location: WebGLUniformLocation?, v0: GLuint, v1: GLuint, v2: GLuint)
    fun uniform3uiv(location: WebGLUniformLocation?, data: Uint32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform3uiv(location: WebGLUniformLocation?, data: Uint32Array)
    fun uniform3uiv(location: WebGLUniformLocation?, data: Uint32Array, srcOffset: GLuint = definedExternally)
    fun uniform3uiv(location: WebGLUniformLocation?, data: Array<GLuint>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform3uiv(location: WebGLUniformLocation?, data: Array<GLuint>)
    fun uniform3uiv(location: WebGLUniformLocation?, data: Array<GLuint>, srcOffset: GLuint = definedExternally)
    fun uniform4ui(location: WebGLUniformLocation?, v0: GLuint, v1: GLuint, v2: GLuint, v3: GLuint)
    fun uniform4uiv(location: WebGLUniformLocation?, data: Uint32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform4uiv(location: WebGLUniformLocation?, data: Uint32Array)
    fun uniform4uiv(location: WebGLUniformLocation?, data: Uint32Array, srcOffset: GLuint = definedExternally)
    fun uniform4uiv(location: WebGLUniformLocation?, data: Array<GLuint>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform4uiv(location: WebGLUniformLocation?, data: Array<GLuint>)
    fun uniform4uiv(location: WebGLUniformLocation?, data: Array<GLuint>, srcOffset: GLuint = definedExternally)
    fun uniformBlockBinding(program: WebGLProgram, uniformBlockIndex: GLuint, uniformBlockBinding: GLuint)
    fun uniformMatrix2x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix2x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array)
    fun uniformMatrix2x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally)
    fun uniformMatrix2x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix2x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>)
    fun uniformMatrix2x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniformMatrix2x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix2x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array)
    fun uniformMatrix2x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally)
    fun uniformMatrix2x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix2x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>)
    fun uniformMatrix2x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniformMatrix3x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix3x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array)
    fun uniformMatrix3x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally)
    fun uniformMatrix3x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix3x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>)
    fun uniformMatrix3x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniformMatrix3x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix3x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array)
    fun uniformMatrix3x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally)
    fun uniformMatrix3x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix3x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>)
    fun uniformMatrix3x4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniformMatrix4x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix4x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array)
    fun uniformMatrix4x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally)
    fun uniformMatrix4x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix4x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>)
    fun uniformMatrix4x2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniformMatrix4x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix4x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array)
    fun uniformMatrix4x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally)
    fun uniformMatrix4x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix4x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>)
    fun uniformMatrix4x3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally)
    fun vertexAttribDivisor(index: GLuint, divisor: GLuint)
    fun vertexAttribI4i(index: GLuint, x: GLint, y: GLint, z: GLint, w: GLint)
    fun vertexAttribI4iv(index: GLuint, values: Int32Array)
    fun vertexAttribI4iv(index: GLuint, values: Array<GLint>)
    fun vertexAttribI4ui(index: GLuint, x: GLuint, y: GLuint, z: GLuint, w: GLuint)
    fun vertexAttribI4uiv(index: GLuint, values: Uint32Array)
    fun vertexAttribI4uiv(index: GLuint, values: Array<GLuint>)
    fun vertexAttribIPointer(index: GLuint, size: GLint, type: GLenum, stride: GLsizei, offset: GLintptr)
    fun waitSync(sync: WebGLSync, flags: GLbitfield, timeout: GLint64)
    var ACTIVE_UNIFORM_BLOCKS: GLenum
    var ALREADY_SIGNALED: GLenum
    var ANY_SAMPLES_PASSED: GLenum
    var ANY_SAMPLES_PASSED_CONSERVATIVE: GLenum
    var COLOR: GLenum
    var COLOR_ATTACHMENT1: GLenum
    var COLOR_ATTACHMENT10: GLenum
    var COLOR_ATTACHMENT11: GLenum
    var COLOR_ATTACHMENT12: GLenum
    var COLOR_ATTACHMENT13: GLenum
    var COLOR_ATTACHMENT14: GLenum
    var COLOR_ATTACHMENT15: GLenum
    var COLOR_ATTACHMENT2: GLenum
    var COLOR_ATTACHMENT3: GLenum
    var COLOR_ATTACHMENT4: GLenum
    var COLOR_ATTACHMENT5: GLenum
    var COLOR_ATTACHMENT6: GLenum
    var COLOR_ATTACHMENT7: GLenum
    var COLOR_ATTACHMENT8: GLenum
    var COLOR_ATTACHMENT9: GLenum
    var COMPARE_REF_TO_TEXTURE: GLenum
    var CONDITION_SATISFIED: GLenum
    var COPY_READ_BUFFER: GLenum
    var COPY_READ_BUFFER_BINDING: GLenum
    var COPY_WRITE_BUFFER: GLenum
    var COPY_WRITE_BUFFER_BINDING: GLenum
    var CURRENT_QUERY: GLenum
    var DEPTH: GLenum
    var DEPTH24_STENCIL8: GLenum
    var DEPTH32F_STENCIL8: GLenum
    var DEPTH_COMPONENT24: GLenum
    var DEPTH_COMPONENT32F: GLenum
    var DRAW_BUFFER0: GLenum
    var DRAW_BUFFER1: GLenum
    var DRAW_BUFFER10: GLenum
    var DRAW_BUFFER11: GLenum
    var DRAW_BUFFER12: GLenum
    var DRAW_BUFFER13: GLenum
    var DRAW_BUFFER14: GLenum
    var DRAW_BUFFER15: GLenum
    var DRAW_BUFFER2: GLenum
    var DRAW_BUFFER3: GLenum
    var DRAW_BUFFER4: GLenum
    var DRAW_BUFFER5: GLenum
    var DRAW_BUFFER6: GLenum
    var DRAW_BUFFER7: GLenum
    var DRAW_BUFFER8: GLenum
    var DRAW_BUFFER9: GLenum
    var DRAW_FRAMEBUFFER: GLenum
    var DRAW_FRAMEBUFFER_BINDING: GLenum
    var DYNAMIC_COPY: GLenum
    var DYNAMIC_READ: GLenum
    var FLOAT_32_UNSIGNED_INT_24_8_REV: GLenum
    var FLOAT_MAT2x3: GLenum
    var FLOAT_MAT2x4: GLenum
    var FLOAT_MAT3x2: GLenum
    var FLOAT_MAT3x4: GLenum
    var FLOAT_MAT4x2: GLenum
    var FLOAT_MAT4x3: GLenum
    var FRAGMENT_SHADER_DERIVATIVE_HINT: GLenum
    var FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE: GLenum
    var FRAMEBUFFER_ATTACHMENT_BLUE_SIZE: GLenum
    var FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING: GLenum
    var FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE: GLenum
    var FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE: GLenum
    var FRAMEBUFFER_ATTACHMENT_GREEN_SIZE: GLenum
    var FRAMEBUFFER_ATTACHMENT_RED_SIZE: GLenum
    var FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE: GLenum
    var FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER: GLenum
    var FRAMEBUFFER_DEFAULT: GLenum
    var FRAMEBUFFER_INCOMPLETE_MULTISAMPLE: GLenum
    var HALF_FLOAT: GLenum
    var INTERLEAVED_ATTRIBS: GLenum
    var INT_2_10_10_10_REV: GLenum
    var INT_SAMPLER_2D: GLenum
    var INT_SAMPLER_2D_ARRAY: GLenum
    var INT_SAMPLER_3D: GLenum
    var INT_SAMPLER_CUBE: GLenum
    var INVALID_INDEX: GLenum
    var MAX: GLenum
    var MAX_3D_TEXTURE_SIZE: GLenum
    var MAX_ARRAY_TEXTURE_LAYERS: GLenum
    var MAX_CLIENT_WAIT_TIMEOUT_WEBGL: GLenum
    var MAX_COLOR_ATTACHMENTS: GLenum
    var MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS: GLenum
    var MAX_COMBINED_UNIFORM_BLOCKS: GLenum
    var MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS: GLenum
    var MAX_DRAW_BUFFERS: GLenum
    var MAX_ELEMENTS_INDICES: GLenum
    var MAX_ELEMENTS_VERTICES: GLenum
    var MAX_ELEMENT_INDEX: GLenum
    var MAX_FRAGMENT_INPUT_COMPONENTS: GLenum
    var MAX_FRAGMENT_UNIFORM_BLOCKS: GLenum
    var MAX_FRAGMENT_UNIFORM_COMPONENTS: GLenum
    var MAX_PROGRAM_TEXEL_OFFSET: GLenum
    var MAX_SAMPLES: GLenum
    var MAX_SERVER_WAIT_TIMEOUT: GLenum
    var MAX_TEXTURE_LOD_BIAS: GLenum
    var MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS: GLenum
    var MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS: GLenum
    var MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS: GLenum
    var MAX_UNIFORM_BLOCK_SIZE: GLenum
    var MAX_UNIFORM_BUFFER_BINDINGS: GLenum
    var MAX_VARYING_COMPONENTS: GLenum
    var MAX_VERTEX_OUTPUT_COMPONENTS: GLenum
    var MAX_VERTEX_UNIFORM_BLOCKS: GLenum
    var MAX_VERTEX_UNIFORM_COMPONENTS: GLenum
    var MIN: GLenum
    var MIN_PROGRAM_TEXEL_OFFSET: GLenum
    var OBJECT_TYPE: GLenum
    var PACK_ROW_LENGTH: GLenum
    var PACK_SKIP_PIXELS: GLenum
    var PACK_SKIP_ROWS: GLenum
    var PIXEL_PACK_BUFFER: GLenum
    var PIXEL_PACK_BUFFER_BINDING: GLenum
    var PIXEL_UNPACK_BUFFER: GLenum
    var PIXEL_UNPACK_BUFFER_BINDING: GLenum
    var QUERY_RESULT: GLenum
    var QUERY_RESULT_AVAILABLE: GLenum
    var R11F_G11F_B10F: GLenum
    var R16F: GLenum
    var R16I: GLenum
    var R16UI: GLenum
    var R32F: GLenum
    var R32I: GLenum
    var R32UI: GLenum
    var R8: GLenum
    var R8I: GLenum
    var R8UI: GLenum
    var R8_SNORM: GLenum
    var RASTERIZER_DISCARD: GLenum
    var READ_BUFFER: GLenum
    var READ_FRAMEBUFFER: GLenum
    var READ_FRAMEBUFFER_BINDING: GLenum
    var RED: GLenum
    var RED_INTEGER: GLenum
    var RENDERBUFFER_SAMPLES: GLenum
    var RG: GLenum
    var RG16F: GLenum
    var RG16I: GLenum
    var RG16UI: GLenum
    var RG32F: GLenum
    var RG32I: GLenum
    var RG32UI: GLenum
    var RG8: GLenum
    var RG8I: GLenum
    var RG8UI: GLenum
    var RG8_SNORM: GLenum
    var RGB10_A2: GLenum
    var RGB10_A2UI: GLenum
    var RGB16F: GLenum
    var RGB16I: GLenum
    var RGB16UI: GLenum
    var RGB32F: GLenum
    var RGB32I: GLenum
    var RGB32UI: GLenum
    var RGB8: GLenum
    var RGB8I: GLenum
    var RGB8UI: GLenum
    var RGB8_SNORM: GLenum
    var RGB9_E5: GLenum
    var RGBA16F: GLenum
    var RGBA16I: GLenum
    var RGBA16UI: GLenum
    var RGBA32F: GLenum
    var RGBA32I: GLenum
    var RGBA32UI: GLenum
    var RGBA8: GLenum
    var RGBA8I: GLenum
    var RGBA8UI: GLenum
    var RGBA8_SNORM: GLenum
    var RGBA_INTEGER: GLenum
    var RGB_INTEGER: GLenum
    var RG_INTEGER: GLenum
    var SAMPLER_2D_ARRAY: GLenum
    var SAMPLER_2D_ARRAY_SHADOW: GLenum
    var SAMPLER_2D_SHADOW: GLenum
    var SAMPLER_3D: GLenum
    var SAMPLER_BINDING: GLenum
    var SAMPLER_CUBE_SHADOW: GLenum
    var SEPARATE_ATTRIBS: GLenum
    var SIGNALED: GLenum
    var SIGNED_NORMALIZED: GLenum
    var SRGB: GLenum
    var SRGB8: GLenum
    var SRGB8_ALPHA8: GLenum
    var STATIC_COPY: GLenum
    var STATIC_READ: GLenum
    var STENCIL: GLenum
    var STREAM_COPY: GLenum
    var STREAM_READ: GLenum
    var SYNC_CONDITION: GLenum
    var SYNC_FENCE: GLenum
    var SYNC_FLAGS: GLenum
    var SYNC_FLUSH_COMMANDS_BIT: GLenum
    var SYNC_GPU_COMMANDS_COMPLETE: GLenum
    var SYNC_STATUS: GLenum
    var TEXTURE_2D_ARRAY: GLenum
    var TEXTURE_3D: GLenum
    var TEXTURE_BASE_LEVEL: GLenum
    var TEXTURE_BINDING_2D_ARRAY: GLenum
    var TEXTURE_BINDING_3D: GLenum
    var TEXTURE_COMPARE_FUNC: GLenum
    var TEXTURE_COMPARE_MODE: GLenum
    var TEXTURE_IMMUTABLE_FORMAT: GLenum
    var TEXTURE_IMMUTABLE_LEVELS: GLenum
    var TEXTURE_MAX_LEVEL: GLenum
    var TEXTURE_MAX_LOD: GLenum
    var TEXTURE_MIN_LOD: GLenum
    var TEXTURE_WRAP_R: GLenum
    var TIMEOUT_EXPIRED: GLenum
    var TIMEOUT_IGNORED: GLint64
    var TRANSFORM_FEEDBACK: GLenum
    var TRANSFORM_FEEDBACK_ACTIVE: GLenum
    var TRANSFORM_FEEDBACK_BINDING: GLenum
    var TRANSFORM_FEEDBACK_BUFFER: GLenum
    var TRANSFORM_FEEDBACK_BUFFER_BINDING: GLenum
    var TRANSFORM_FEEDBACK_BUFFER_MODE: GLenum
    var TRANSFORM_FEEDBACK_BUFFER_SIZE: GLenum
    var TRANSFORM_FEEDBACK_BUFFER_START: GLenum
    var TRANSFORM_FEEDBACK_PAUSED: GLenum
    var TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN: GLenum
    var TRANSFORM_FEEDBACK_VARYINGS: GLenum
    var UNIFORM_ARRAY_STRIDE: GLenum
    var UNIFORM_BLOCK_ACTIVE_UNIFORMS: GLenum
    var UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES: GLenum
    var UNIFORM_BLOCK_BINDING: GLenum
    var UNIFORM_BLOCK_DATA_SIZE: GLenum
    var UNIFORM_BLOCK_INDEX: GLenum
    var UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER: GLenum
    var UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER: GLenum
    var UNIFORM_BUFFER: GLenum
    var UNIFORM_BUFFER_BINDING: GLenum
    var UNIFORM_BUFFER_OFFSET_ALIGNMENT: GLenum
    var UNIFORM_BUFFER_SIZE: GLenum
    var UNIFORM_BUFFER_START: GLenum
    var UNIFORM_IS_ROW_MAJOR: GLenum
    var UNIFORM_MATRIX_STRIDE: GLenum
    var UNIFORM_OFFSET: GLenum
    var UNIFORM_SIZE: GLenum
    var UNIFORM_TYPE: GLenum
    var UNPACK_IMAGE_HEIGHT: GLenum
    var UNPACK_ROW_LENGTH: GLenum
    var UNPACK_SKIP_IMAGES: GLenum
    var UNPACK_SKIP_PIXELS: GLenum
    var UNPACK_SKIP_ROWS: GLenum
    var UNSIGNALED: GLenum
    var UNSIGNED_INT_10F_11F_11F_REV: GLenum
    var UNSIGNED_INT_24_8: GLenum
    var UNSIGNED_INT_2_10_10_10_REV: GLenum
    var UNSIGNED_INT_5_9_9_9_REV: GLenum
    var UNSIGNED_INT_SAMPLER_2D: GLenum
    var UNSIGNED_INT_SAMPLER_2D_ARRAY: GLenum
    var UNSIGNED_INT_SAMPLER_3D: GLenum
    var UNSIGNED_INT_SAMPLER_CUBE: GLenum
    var UNSIGNED_INT_VEC2: GLenum
    var UNSIGNED_INT_VEC3: GLenum
    var UNSIGNED_INT_VEC4: GLenum
    var UNSIGNED_NORMALIZED: GLenum
    var VERTEX_ARRAY_BINDING: GLenum
    var VERTEX_ATTRIB_ARRAY_DIVISOR: GLenum
    var VERTEX_ATTRIB_ARRAY_INTEGER: GLenum
    var WAIT_FAILED: GLenum
}

external interface WebGL2RenderingContextOverloads {
    fun uniform1fv(location: WebGLUniformLocation?, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform1fv(location: WebGLUniformLocation?, data: Iterable<GLfloat>)
    fun uniform1fv(location: WebGLUniformLocation?, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniform1iv(location: WebGLUniformLocation?, data: Iterable<GLint>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform1iv(location: WebGLUniformLocation?, data: Iterable<GLint>)
    fun uniform1iv(location: WebGLUniformLocation?, data: Iterable<GLint>, srcOffset: GLuint = definedExternally)
    fun uniform2fv(location: WebGLUniformLocation?, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform2fv(location: WebGLUniformLocation?, data: Iterable<GLfloat>)
    fun uniform2fv(location: WebGLUniformLocation?, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniform2iv(location: WebGLUniformLocation?, data: Iterable<GLint>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform2iv(location: WebGLUniformLocation?, data: Iterable<GLint>)
    fun uniform2iv(location: WebGLUniformLocation?, data: Iterable<GLint>, srcOffset: GLuint = definedExternally)
    fun uniform3fv(location: WebGLUniformLocation?, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform3fv(location: WebGLUniformLocation?, data: Iterable<GLfloat>)
    fun uniform3fv(location: WebGLUniformLocation?, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniform3iv(location: WebGLUniformLocation?, data: Iterable<GLint>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform3iv(location: WebGLUniformLocation?, data: Iterable<GLint>)
    fun uniform3iv(location: WebGLUniformLocation?, data: Iterable<GLint>, srcOffset: GLuint = definedExternally)
    fun uniform4fv(location: WebGLUniformLocation?, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform4fv(location: WebGLUniformLocation?, data: Iterable<GLfloat>)
    fun uniform4fv(location: WebGLUniformLocation?, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniform4iv(location: WebGLUniformLocation?, data: Iterable<GLint>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform4iv(location: WebGLUniformLocation?, data: Iterable<GLint>)
    fun uniform4iv(location: WebGLUniformLocation?, data: Iterable<GLint>, srcOffset: GLuint = definedExternally)
    fun uniformMatrix2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>)
    fun uniformMatrix2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniformMatrix3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>)
    fun uniformMatrix3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniformMatrix4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>)
    fun uniformMatrix4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Iterable<GLfloat>, srcOffset: GLuint = definedExternally)
    fun bufferData(target: GLenum, size: GLsizeiptr, usage: GLenum)
    fun bufferData(target: GLenum, srcData: ArrayBufferView?, usage: GLenum)
    fun bufferData(target: GLenum, srcData: ArrayBuffer?, usage: GLenum)
    fun bufferData(target: GLenum, srcData: ArrayBufferView, usage: GLenum, srcOffset: GLuint, length: GLuint = definedExternally)
    fun bufferData(target: GLenum, srcData: ArrayBufferView, usage: GLenum, srcOffset: GLuint)
    fun bufferSubData(target: GLenum, dstByteOffset: GLintptr, srcData: ArrayBufferView)
    fun bufferSubData(target: GLenum, dstByteOffset: GLintptr, srcData: ArrayBuffer)
    fun bufferSubData(target: GLenum, dstByteOffset: GLintptr, srcData: ArrayBufferView, srcOffset: GLuint, length: GLuint = definedExternally)
    fun bufferSubData(target: GLenum, dstByteOffset: GLintptr, srcData: ArrayBufferView, srcOffset: GLuint)
    fun compressedTexImage2D(target: GLenum, level: GLint, internalformat: GLenum, width: GLsizei, height: GLsizei, border: GLint, imageSize: GLsizei, offset: GLintptr)
    fun compressedTexImage2D(target: GLenum, level: GLint, internalformat: GLenum, width: GLsizei, height: GLsizei, border: GLint, srcData: ArrayBufferView, srcOffset: GLuint = definedExternally, srcLengthOverride: GLuint = definedExternally)
    fun compressedTexImage2D(target: GLenum, level: GLint, internalformat: GLenum, width: GLsizei, height: GLsizei, border: GLint, srcData: ArrayBufferView)
    fun compressedTexImage2D(target: GLenum, level: GLint, internalformat: GLenum, width: GLsizei, height: GLsizei, border: GLint, srcData: ArrayBufferView, srcOffset: GLuint = definedExternally)
    fun compressedTexSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: GLenum, imageSize: GLsizei, offset: GLintptr)
    fun compressedTexSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: GLenum, srcData: ArrayBufferView, srcOffset: GLuint = definedExternally, srcLengthOverride: GLuint = definedExternally)
    fun compressedTexSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: GLenum, srcData: ArrayBufferView)
    fun compressedTexSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: GLenum, srcData: ArrayBufferView, srcOffset: GLuint = definedExternally)
    fun readPixels(x: GLint, y: GLint, width: GLsizei, height: GLsizei, format: GLenum, type: GLenum, dstData: ArrayBufferView?)
    fun readPixels(x: GLint, y: GLint, width: GLsizei, height: GLsizei, format: GLenum, type: GLenum, offset: GLintptr)
    fun readPixels(x: GLint, y: GLint, width: GLsizei, height: GLsizei, format: GLenum, type: GLenum, dstData: ArrayBufferView, dstOffset: GLuint)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, border: GLint, format: GLenum, type: GLenum, pixels: ArrayBufferView?)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, format: GLenum, type: GLenum, source: ImageBitmap)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, format: GLenum, type: GLenum, source: ImageData)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, format: GLenum, type: GLenum, source: HTMLImageElement)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, format: GLenum, type: GLenum, source: HTMLCanvasElement)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, format: GLenum, type: GLenum, source: HTMLVideoElement)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, format: GLenum, type: GLenum, source: OffscreenCanvas)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, border: GLint, format: GLenum, type: GLenum, pboOffset: GLintptr)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, border: GLint, format: GLenum, type: GLenum, source: ImageBitmap)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, border: GLint, format: GLenum, type: GLenum, source: ImageData)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, border: GLint, format: GLenum, type: GLenum, source: HTMLImageElement)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, border: GLint, format: GLenum, type: GLenum, source: HTMLCanvasElement)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, border: GLint, format: GLenum, type: GLenum, source: HTMLVideoElement)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, border: GLint, format: GLenum, type: GLenum, source: OffscreenCanvas)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, border: GLint, format: GLenum, type: GLenum, srcData: ArrayBufferView, srcOffset: GLuint)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: GLenum, type: GLenum, pixels: ArrayBufferView?)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, format: GLenum, type: GLenum, source: ImageBitmap)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, format: GLenum, type: GLenum, source: ImageData)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, format: GLenum, type: GLenum, source: HTMLImageElement)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, format: GLenum, type: GLenum, source: HTMLCanvasElement)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, format: GLenum, type: GLenum, source: HTMLVideoElement)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, format: GLenum, type: GLenum, source: OffscreenCanvas)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: GLenum, type: GLenum, pboOffset: GLintptr)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: GLenum, type: GLenum, source: ImageBitmap)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: GLenum, type: GLenum, source: ImageData)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: GLenum, type: GLenum, source: HTMLImageElement)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: GLenum, type: GLenum, source: HTMLCanvasElement)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: GLenum, type: GLenum, source: HTMLVideoElement)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: GLenum, type: GLenum, source: OffscreenCanvas)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: GLenum, type: GLenum, srcData: ArrayBufferView, srcOffset: GLuint)
    fun uniform1fv(location: WebGLUniformLocation?, data: Float32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform1fv(location: WebGLUniformLocation?, data: Float32Array)
    fun uniform1fv(location: WebGLUniformLocation?, data: Float32Array, srcOffset: GLuint = definedExternally)
    fun uniform1fv(location: WebGLUniformLocation?, data: Array<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform1fv(location: WebGLUniformLocation?, data: Array<GLfloat>)
    fun uniform1fv(location: WebGLUniformLocation?, data: Array<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniform1iv(location: WebGLUniformLocation?, data: Int32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform1iv(location: WebGLUniformLocation?, data: Int32Array)
    fun uniform1iv(location: WebGLUniformLocation?, data: Int32Array, srcOffset: GLuint = definedExternally)
    fun uniform1iv(location: WebGLUniformLocation?, data: Array<GLint>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform1iv(location: WebGLUniformLocation?, data: Array<GLint>)
    fun uniform1iv(location: WebGLUniformLocation?, data: Array<GLint>, srcOffset: GLuint = definedExternally)
    fun uniform2fv(location: WebGLUniformLocation?, data: Float32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform2fv(location: WebGLUniformLocation?, data: Float32Array)
    fun uniform2fv(location: WebGLUniformLocation?, data: Float32Array, srcOffset: GLuint = definedExternally)
    fun uniform2fv(location: WebGLUniformLocation?, data: Array<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform2fv(location: WebGLUniformLocation?, data: Array<GLfloat>)
    fun uniform2fv(location: WebGLUniformLocation?, data: Array<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniform2iv(location: WebGLUniformLocation?, data: Int32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform2iv(location: WebGLUniformLocation?, data: Int32Array)
    fun uniform2iv(location: WebGLUniformLocation?, data: Int32Array, srcOffset: GLuint = definedExternally)
    fun uniform2iv(location: WebGLUniformLocation?, data: Array<GLint>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform2iv(location: WebGLUniformLocation?, data: Array<GLint>)
    fun uniform2iv(location: WebGLUniformLocation?, data: Array<GLint>, srcOffset: GLuint = definedExternally)
    fun uniform3fv(location: WebGLUniformLocation?, data: Float32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform3fv(location: WebGLUniformLocation?, data: Float32Array)
    fun uniform3fv(location: WebGLUniformLocation?, data: Float32Array, srcOffset: GLuint = definedExternally)
    fun uniform3fv(location: WebGLUniformLocation?, data: Array<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform3fv(location: WebGLUniformLocation?, data: Array<GLfloat>)
    fun uniform3fv(location: WebGLUniformLocation?, data: Array<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniform3iv(location: WebGLUniformLocation?, data: Int32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform3iv(location: WebGLUniformLocation?, data: Int32Array)
    fun uniform3iv(location: WebGLUniformLocation?, data: Int32Array, srcOffset: GLuint = definedExternally)
    fun uniform3iv(location: WebGLUniformLocation?, data: Array<GLint>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform3iv(location: WebGLUniformLocation?, data: Array<GLint>)
    fun uniform3iv(location: WebGLUniformLocation?, data: Array<GLint>, srcOffset: GLuint = definedExternally)
    fun uniform4fv(location: WebGLUniformLocation?, data: Float32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform4fv(location: WebGLUniformLocation?, data: Float32Array)
    fun uniform4fv(location: WebGLUniformLocation?, data: Float32Array, srcOffset: GLuint = definedExternally)
    fun uniform4fv(location: WebGLUniformLocation?, data: Array<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform4fv(location: WebGLUniformLocation?, data: Array<GLfloat>)
    fun uniform4fv(location: WebGLUniformLocation?, data: Array<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniform4iv(location: WebGLUniformLocation?, data: Int32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform4iv(location: WebGLUniformLocation?, data: Int32Array)
    fun uniform4iv(location: WebGLUniformLocation?, data: Int32Array, srcOffset: GLuint = definedExternally)
    fun uniform4iv(location: WebGLUniformLocation?, data: Array<GLint>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniform4iv(location: WebGLUniformLocation?, data: Array<GLint>)
    fun uniform4iv(location: WebGLUniformLocation?, data: Array<GLint>, srcOffset: GLuint = definedExternally)
    fun uniformMatrix2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array)
    fun uniformMatrix2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally)
    fun uniformMatrix2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>)
    fun uniformMatrix2fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniformMatrix3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array)
    fun uniformMatrix3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally)
    fun uniformMatrix3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>)
    fun uniformMatrix3fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally)
    fun uniformMatrix4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array)
    fun uniformMatrix4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Float32Array, srcOffset: GLuint = definedExternally)
    fun uniformMatrix4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally, srcLength: GLuint = definedExternally)
    fun uniformMatrix4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>)
    fun uniformMatrix4fv(location: WebGLUniformLocation?, transpose: GLboolean, data: Array<GLfloat>, srcOffset: GLuint = definedExternally)
}

external interface WebGLQuery : WebGLObject

external interface WebGLRenderingContextOverloads {
    fun uniform1fv(location: WebGLUniformLocation?, v: Iterable<GLfloat>)
    fun uniform1iv(location: WebGLUniformLocation?, v: Iterable<GLint>)
    fun uniform2fv(location: WebGLUniformLocation?, v: Iterable<GLfloat>)
    fun uniform2iv(location: WebGLUniformLocation?, v: Iterable<GLint>)
    fun uniform3fv(location: WebGLUniformLocation?, v: Iterable<GLfloat>)
    fun uniform3iv(location: WebGLUniformLocation?, v: Iterable<GLint>)
    fun uniform4fv(location: WebGLUniformLocation?, v: Iterable<GLfloat>)
    fun uniform4iv(location: WebGLUniformLocation?, v: Iterable<GLint>)
    fun uniformMatrix2fv(location: WebGLUniformLocation?, transpose: GLboolean, value: Iterable<GLfloat>)
    fun uniformMatrix3fv(location: WebGLUniformLocation?, transpose: GLboolean, value: Iterable<GLfloat>)
    fun uniformMatrix4fv(location: WebGLUniformLocation?, transpose: GLboolean, value: Iterable<GLfloat>)
    fun bufferData(target: GLenum, size: GLsizeiptr, usage: GLenum)
    fun bufferData(target: GLenum, data: ArrayBufferView?, usage: GLenum)
    fun bufferData(target: GLenum, data: ArrayBuffer?, usage: GLenum)
    fun bufferSubData(target: GLenum, offset: GLintptr, data: ArrayBufferView)
    fun bufferSubData(target: GLenum, offset: GLintptr, data: ArrayBuffer)
    fun compressedTexImage2D(target: GLenum, level: GLint, internalformat: GLenum, width: GLsizei, height: GLsizei, border: GLint, data: ArrayBufferView)
    fun compressedTexSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: GLenum, data: ArrayBufferView)
    fun readPixels(x: GLint, y: GLint, width: GLsizei, height: GLsizei, format: GLenum, type: GLenum, pixels: ArrayBufferView?)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, width: GLsizei, height: GLsizei, border: GLint, format: GLenum, type: GLenum, pixels: ArrayBufferView?)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, format: GLenum, type: GLenum, source: ImageBitmap)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, format: GLenum, type: GLenum, source: ImageData)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, format: GLenum, type: GLenum, source: HTMLImageElement)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, format: GLenum, type: GLenum, source: HTMLCanvasElement)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, format: GLenum, type: GLenum, source: HTMLVideoElement)
    fun texImage2D(target: GLenum, level: GLint, internalformat: GLint, format: GLenum, type: GLenum, source: OffscreenCanvas)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: GLenum, type: GLenum, pixels: ArrayBufferView?)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, format: GLenum, type: GLenum, source: ImageBitmap)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, format: GLenum, type: GLenum, source: ImageData)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, format: GLenum, type: GLenum, source: HTMLImageElement)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, format: GLenum, type: GLenum, source: HTMLCanvasElement)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, format: GLenum, type: GLenum, source: HTMLVideoElement)
    fun texSubImage2D(target: GLenum, level: GLint, xoffset: GLint, yoffset: GLint, format: GLenum, type: GLenum, source: OffscreenCanvas)
    fun uniform1fv(location: WebGLUniformLocation?, v: Float32Array)
    fun uniform1fv(location: WebGLUniformLocation?, v: Array<GLfloat>)
    fun uniform1iv(location: WebGLUniformLocation?, v: Int32Array)
    fun uniform1iv(location: WebGLUniformLocation?, v: Array<GLint>)
    fun uniform2fv(location: WebGLUniformLocation?, v: Float32Array)
    fun uniform2fv(location: WebGLUniformLocation?, v: Array<GLfloat>)
    fun uniform2iv(location: WebGLUniformLocation?, v: Int32Array)
    fun uniform2iv(location: WebGLUniformLocation?, v: Array<GLint>)
    fun uniform3fv(location: WebGLUniformLocation?, v: Float32Array)
    fun uniform3fv(location: WebGLUniformLocation?, v: Array<GLfloat>)
    fun uniform3iv(location: WebGLUniformLocation?, v: Int32Array)
    fun uniform3iv(location: WebGLUniformLocation?, v: Array<GLint>)
    fun uniform4fv(location: WebGLUniformLocation?, v: Float32Array)
    fun uniform4fv(location: WebGLUniformLocation?, v: Array<GLfloat>)
    fun uniform4iv(location: WebGLUniformLocation?, v: Int32Array)
    fun uniform4iv(location: WebGLUniformLocation?, v: Array<GLint>)
    fun uniformMatrix2fv(location: WebGLUniformLocation?, transpose: GLboolean, value: Float32Array)
    fun uniformMatrix2fv(location: WebGLUniformLocation?, transpose: GLboolean, value: Array<GLfloat>)
    fun uniformMatrix3fv(location: WebGLUniformLocation?, transpose: GLboolean, value: Float32Array)
    fun uniformMatrix3fv(location: WebGLUniformLocation?, transpose: GLboolean, value: Array<GLfloat>)
    fun uniformMatrix4fv(location: WebGLUniformLocation?, transpose: GLboolean, value: Float32Array)
    fun uniformMatrix4fv(location: WebGLUniformLocation?, transpose: GLboolean, value: Array<GLfloat>)
}

external interface WebGLSampler : WebGLObject

external interface WebGLSync : WebGLObject

external interface WebGLTransformFeedback : WebGLObject

external interface WebGLVertexArrayObject : WebGLObject

external interface WebGLVertexArrayObjectOES : WebGLObject

external interface WebKitPoint {
    var x: Number
    var y: Number
}

external interface WindowEventMap : GlobalEventHandlersEventMap, WindowEventHandlersEventMap {
    override var abort: UIEvent
    override var afterprint: Event
    override var beforeprint: Event
    override var beforeunload: BeforeUnloadEvent
    override var blur: FocusEvent
    override var canplay: Event
    override var canplaythrough: Event
    override var change: Event
    override var click: MouseEvent
    var compassneedscalibration: Event
    override var contextmenu: MouseEvent
    override var dblclick: MouseEvent
    var devicelight: DeviceLightEvent
    var devicemotion: DeviceMotionEvent
    var deviceorientation: DeviceOrientationEvent
    var deviceorientationabsolute: DeviceOrientationEvent
    override var drag: DragEvent
    override var dragend: DragEvent
    override var dragenter: DragEvent
    override var dragleave: DragEvent
    override var dragover: DragEvent
    override var dragstart: DragEvent
    override var drop: DragEvent
    override var durationchange: Event
    override var emptied: Event
    override var ended: Event
    override var error: ErrorEvent
    override var focus: FocusEvent
    override var hashchange: HashChangeEvent
    override var input: Event
    override var invalid: Event
    override var keydown: KeyboardEvent
    override var keypress: KeyboardEvent
    override var keyup: KeyboardEvent
    override var load: Event
    override var loadeddata: Event
    override var loadedmetadata: Event
    override var loadstart: Event
    override var message: MessageEvent
    override var mousedown: MouseEvent
    override var mouseenter: MouseEvent
    override var mouseleave: MouseEvent
    override var mousemove: MouseEvent
    override var mouseout: MouseEvent
    override var mouseover: MouseEvent
    override var mouseup: MouseEvent
    var mousewheel: Event
    var MSGestureChange: Event
    var MSGestureDoubleTap: Event
    var MSGestureEnd: Event
    var MSGestureHold: Event
    var MSGestureStart: Event
    var MSGestureTap: Event
    var MSInertiaStart: Event
    var MSPointerCancel: Event
    var MSPointerDown: Event
    var MSPointerEnter: Event
    var MSPointerLeave: Event
    var MSPointerMove: Event
    var MSPointerOut: Event
    var MSPointerOver: Event
    var MSPointerUp: Event
    override var offline: Event
    override var online: Event
    var orientationchange: Event
    override var pagehide: PageTransitionEvent
    override var pageshow: PageTransitionEvent
    override var pause: Event
    override var play: Event
    override var playing: Event
    override var popstate: PopStateEvent
    override var ratechange: Event
    var readystatechange: ProgressEvent<Window>
    override var reset: Event
    override var resize: UIEvent
    override var scroll: Event
    override var seeked: Event
    override var seeking: Event
    override var select: Event
    override var stalled: Event
    override var storage: StorageEvent
    override var submit: Event
    override var suspend: Event
    override var timeupdate: Event
    override var unload: Event
    override var volumechange: Event
    var vrdisplayactivate: Event
    var vrdisplayblur: Event
    var vrdisplayconnect: Event
    var vrdisplaydeactivate: Event
    var vrdisplaydisconnect: Event
    var vrdisplayfocus: Event
    var vrdisplaypointerrestricted: Event
    var vrdisplaypointerunrestricted: Event
    var vrdisplaypresentchange: Event
    override var waiting: Event
}

external interface WindowEventHandlersEventMap {
    var afterprint: Event
    var beforeprint: Event
    var beforeunload: BeforeUnloadEvent
    var hashchange: HashChangeEvent
    var languagechange: Event
    var message: MessageEvent
    var messageerror: MessageEvent
    var offline: Event
    var online: Event
    var pagehide: PageTransitionEvent
    var pageshow: PageTransitionEvent
    var popstate: PopStateEvent
    var rejectionhandled: PromiseRejectionEvent
    var storage: StorageEvent
    var unhandledrejection: PromiseRejectionEvent
    var unload: Event
}

external interface WritableStream<W> {
    var locked: Boolean
    fun abort(reason: Any = definedExternally): Promise<Unit>
    fun getWriter(): WritableStreamDefaultWriter<W>
}

external interface WritableStreamDefaultController {
    fun error(error: Any = definedExternally)
}

external interface WritableStreamDefaultWriter<W> {
    var closed: Promise<Unit>
    var desiredSize: Number?
    var ready: Promise<Unit>
    fun abort(reason: Any = definedExternally): Promise<Unit>
    fun close(): Promise<Unit>
    fun releaseLock()
    fun write(chunk: W): Promise<Unit>
}

external interface XPathEvaluatorBase {
    fun createExpression(expression: String, resolver: ((prefix: String?) -> String?)? = definedExternally): XPathExpression
    fun createExpression(expression: String): XPathExpression
    fun createExpression(expression: String, resolver: `T$2`? = definedExternally): XPathExpression
    fun createNSResolver(nodeResolver: Node): dynamic /* (prefix: String?) -> String? | `T$2` */
    fun evaluate(expression: String, contextNode: Node, resolver: ((prefix: String?) -> String?)? = definedExternally, type: Number = definedExternally, result: XPathResult? = definedExternally): XPathResult
    fun evaluate(expression: String, contextNode: Node): XPathResult
    fun evaluate(expression: String, contextNode: Node, resolver: ((prefix: String?) -> String?)? = definedExternally): XPathResult
    fun evaluate(expression: String, contextNode: Node, resolver: ((prefix: String?) -> String?)? = definedExternally, type: Number = definedExternally): XPathResult
    fun evaluate(expression: String, contextNode: Node, resolver: `T$2`? = definedExternally, type: Number = definedExternally, result: XPathResult? = definedExternally): XPathResult
    fun evaluate(expression: String, contextNode: Node, resolver: `T$2`? = definedExternally): XPathResult
    fun evaluate(expression: String, contextNode: Node, resolver: `T$2`? = definedExternally, type: Number = definedExternally): XPathResult
}

external interface XPathExpression {
    fun evaluate(contextNode: Node, type: Number = definedExternally, result: XPathResult? = definedExternally): XPathResult
}

external interface XPathResult {
    var booleanValue: Boolean
    var invalidIteratorState: Boolean
    var numberValue: Number
    var resultType: Number
    var singleNodeValue: Node?
    var snapshotLength: Number
    var stringValue: String
    fun iterateNext(): Node?
    fun snapshotItem(index: Number): Node?
    var ANY_TYPE: Number
    var ANY_UNORDERED_NODE_TYPE: Number
    var BOOLEAN_TYPE: Number
    var FIRST_ORDERED_NODE_TYPE: Number
    var NUMBER_TYPE: Number
    var ORDERED_NODE_ITERATOR_TYPE: Number
    var ORDERED_NODE_SNAPSHOT_TYPE: Number
    var STRING_TYPE: Number
    var UNORDERED_NODE_ITERATOR_TYPE: Number
    var UNORDERED_NODE_SNAPSHOT_TYPE: Number
}

external interface BlobCallback {
    @nativeInvoke
    operator fun invoke(blob: Blob?)
}

external interface CustomElementConstructor

external interface FrameRequestCallback {
    @nativeInvoke
    operator fun invoke(time: Number)
}

external interface FunctionStringCallback {
    @nativeInvoke
    operator fun invoke(data: String)
}

external interface MSLaunchUriCallback {
    @nativeInvoke
    operator fun invoke()
}

external interface NavigatorUserMediaErrorCallback {
    @nativeInvoke
    operator fun invoke(error: MediaStreamError)
}

external interface NavigatorUserMediaSuccessCallback {
    @nativeInvoke
    operator fun invoke(stream: MediaStream)
}

external interface NotificationPermissionCallback {
    @nativeInvoke
    operator fun invoke(permission: String /* "default" | "denied" | "granted" */)
}

external interface OnErrorEventHandlerNonNull {
    @nativeInvoke
    operator fun invoke(event: Event, source: String = definedExternally, lineno: Number = definedExternally, colno: Number = definedExternally, error: Error = definedExternally): Any
    @nativeInvoke
    operator fun invoke(event: Event): Any
    @nativeInvoke
    operator fun invoke(event: Event, source: String = definedExternally): Any
    @nativeInvoke
    operator fun invoke(event: Event, source: String = definedExternally, lineno: Number = definedExternally): Any
    @nativeInvoke
    operator fun invoke(event: Event, source: String = definedExternally, lineno: Number = definedExternally, colno: Number = definedExternally): Any
    @nativeInvoke
    operator fun invoke(event: String, source: String = definedExternally, lineno: Number = definedExternally, colno: Number = definedExternally, error: Error = definedExternally): Any
    @nativeInvoke
    operator fun invoke(event: String): Any
    @nativeInvoke
    operator fun invoke(event: String, source: String = definedExternally): Any
    @nativeInvoke
    operator fun invoke(event: String, source: String = definedExternally, lineno: Number = definedExternally): Any
    @nativeInvoke
    operator fun invoke(event: String, source: String = definedExternally, lineno: Number = definedExternally, colno: Number = definedExternally): Any
}

external interface PositionCallback {
    @nativeInvoke
    operator fun invoke(position: Position)
}

external interface PositionErrorCallback {
    @nativeInvoke
    operator fun invoke(positionError: PositionError)
}

external interface QueuingStrategySizeCallback<T> {
    @nativeInvoke
    operator fun invoke(chunk: T): Number
}

external interface ReadableByteStreamControllerCallback {
    @nativeInvoke
    operator fun invoke(controller: ReadableByteStreamController): dynamic /* Unit | PromiseLike<Unit> */
}

external interface ReadableStreamDefaultControllerCallback<R> {
    @nativeInvoke
    operator fun invoke(controller: ReadableStreamDefaultController<R>): dynamic /* Unit | PromiseLike<Unit> */
}

external interface ReadableStreamErrorCallback {
    @nativeInvoke
    operator fun invoke(reason: Any): dynamic /* Unit | PromiseLike<Unit> */
}

external interface VoidFunction {
    @nativeInvoke
    operator fun invoke()
}

external interface WritableStreamDefaultControllerCloseCallback {
    @nativeInvoke
    operator fun invoke(): dynamic /* Unit | PromiseLike<Unit> */
}

external interface WritableStreamDefaultControllerStartCallback {
    @nativeInvoke
    operator fun invoke(controller: WritableStreamDefaultController): dynamic /* Unit | PromiseLike<Unit> */
}

external interface WritableStreamDefaultControllerWriteCallback<W> {
    @nativeInvoke
    operator fun invoke(chunk: W, controller: WritableStreamDefaultController): dynamic /* Unit | PromiseLike<Unit> */
}

external interface WritableStreamErrorCallback {
    @nativeInvoke
    operator fun invoke(reason: Any): dynamic /* Unit | PromiseLike<Unit> */
}

external interface HTMLElementTagNameMap {
    var a: HTMLAnchorElement
    var abbr: HTMLElement
    var address: HTMLElement
    var applet: HTMLAppletElement
    var area: HTMLAreaElement
    var article: HTMLElement
    var aside: HTMLElement
    var audio: HTMLAudioElement
    var b: HTMLElement
    var base: HTMLBaseElement
    var basefont: HTMLBaseFontElement
    var bdi: HTMLElement
    var bdo: HTMLElement
    var blockquote: HTMLQuoteElement
    var body: HTMLBodyElement
    var br: HTMLBRElement
    var button: HTMLButtonElement
    var canvas: HTMLCanvasElement
    var caption: HTMLTableCaptionElement
    var cite: HTMLElement
    var code: HTMLElement
    var col: HTMLTableColElement
    var colgroup: HTMLTableColElement
    var data: HTMLDataElement
    var datalist: HTMLDataListElement
    var dd: HTMLElement
    var del: HTMLModElement
    var details: HTMLDetailsElement
    var dfn: HTMLElement
    var dialog: HTMLDialogElement
    var dir: HTMLDirectoryElement
    var div: HTMLDivElement
    var dl: HTMLDListElement
    var dt: HTMLElement
    var em: HTMLElement
    var embed: HTMLEmbedElement
    var fieldset: HTMLFieldSetElement
    var figcaption: HTMLElement
    var figure: HTMLElement
    var font: HTMLFontElement
    var footer: HTMLElement
    var form: HTMLFormElement
    var frame: HTMLFrameElement
    var frameset: HTMLFrameSetElement
    var h1: HTMLHeadingElement
    var h2: HTMLHeadingElement
    var h3: HTMLHeadingElement
    var h4: HTMLHeadingElement
    var h5: HTMLHeadingElement
    var h6: HTMLHeadingElement
    var head: HTMLHeadElement
    var header: HTMLElement
    var hgroup: HTMLElement
    var hr: HTMLHRElement
    var html: HTMLHtmlElement
    var i: HTMLElement
    var iframe: HTMLIFrameElement
    var img: HTMLImageElement
    var input: HTMLInputElement
    var ins: HTMLModElement
    var kbd: HTMLElement
    var label: HTMLLabelElement
    var legend: HTMLLegendElement
    var li: HTMLLIElement
    var link: HTMLLinkElement
    var main: HTMLElement
    var map: HTMLMapElement
    var mark: HTMLElement
    var marquee: HTMLMarqueeElement
    var menu: HTMLMenuElement
    var meta: HTMLMetaElement
    var meter: HTMLMeterElement
    var nav: HTMLElement
    var noscript: HTMLElement
    var `object`: HTMLObjectElement
    var ol: HTMLOListElement
    var optgroup: HTMLOptGroupElement
    var option: HTMLOptionElement
    var output: HTMLOutputElement
    var p: HTMLParagraphElement
    var param: HTMLParamElement
    var picture: HTMLPictureElement
    var pre: HTMLPreElement
    var progress: HTMLProgressElement
    var q: HTMLQuoteElement
    var rp: HTMLElement
    var rt: HTMLElement
    var ruby: HTMLElement
    var s: HTMLElement
    var samp: HTMLElement
    var script: HTMLScriptElement
    var section: HTMLElement
    var select: HTMLSelectElement
    var slot: HTMLSlotElement
    var small: HTMLElement
    var source: HTMLSourceElement
    var span: HTMLSpanElement
    var strong: HTMLElement
    var style: HTMLStyleElement
    var sub: HTMLElement
    var summary: HTMLElement
    var sup: HTMLElement
    var table: HTMLTableElement
    var tbody: HTMLTableSectionElement
    var td: HTMLTableDataCellElement
    var template: HTMLTemplateElement
    var textarea: HTMLTextAreaElement
    var tfoot: HTMLTableSectionElement
    var th: HTMLTableHeaderCellElement
    var thead: HTMLTableSectionElement
    var time: HTMLTimeElement
    var title: HTMLTitleElement
    var tr: HTMLTableRowElement
    var track: HTMLTrackElement
    var u: HTMLElement
    var ul: HTMLUListElement
    var `var`: HTMLElement
    var video: HTMLVideoElement
    var wbr: HTMLElement
}

external interface HTMLElementDeprecatedTagNameMap {
    var listing: HTMLPreElement
    var xmp: HTMLPreElement
}

external interface SVGElementTagNameMap {
    var a: SVGAElement
    var circle: SVGCircleElement
    var clipPath: SVGClipPathElement
    var defs: SVGDefsElement
    var desc: SVGDescElement
    var ellipse: SVGEllipseElement
    var feBlend: SVGFEBlendElement
    var feColorMatrix: SVGFEColorMatrixElement
    var feComponentTransfer: SVGFEComponentTransferElement
    var feComposite: SVGFECompositeElement
    var feConvolveMatrix: SVGFEConvolveMatrixElement
    var feDiffuseLighting: SVGFEDiffuseLightingElement
    var feDisplacementMap: SVGFEDisplacementMapElement
    var feDistantLight: SVGFEDistantLightElement
    var feFlood: SVGFEFloodElement
    var feFuncA: SVGFEFuncAElement
    var feFuncB: SVGFEFuncBElement
    var feFuncG: SVGFEFuncGElement
    var feFuncR: SVGFEFuncRElement
    var feGaussianBlur: SVGFEGaussianBlurElement
    var feImage: SVGFEImageElement
    var feMerge: SVGFEMergeElement
    var feMergeNode: SVGFEMergeNodeElement
    var feMorphology: SVGFEMorphologyElement
    var feOffset: SVGFEOffsetElement
    var fePointLight: SVGFEPointLightElement
    var feSpecularLighting: SVGFESpecularLightingElement
    var feSpotLight: SVGFESpotLightElement
    var feTile: SVGFETileElement
    var feTurbulence: SVGFETurbulenceElement
    var filter: SVGFilterElement
    var foreignObject: SVGForeignObjectElement
    var g: SVGGElement
    var image: SVGImageElement
    var line: SVGLineElement
    var linearGradient: SVGLinearGradientElement
    var marker: SVGMarkerElement
    var mask: SVGMaskElement
    var metadata: SVGMetadataElement
    var path: SVGPathElement
    var pattern: SVGPatternElement
    var polygon: SVGPolygonElement
    var polyline: SVGPolylineElement
    var radialGradient: SVGRadialGradientElement
    var rect: SVGRectElement
    var script: SVGScriptElement
    var stop: SVGStopElement
    var style: SVGStyleElement
    var svg: SVGSVGElement
    var switch: SVGSwitchElement
    var symbol: SVGSymbolElement
    var text: SVGTextElement
    var textPath: SVGTextPathElement
    var title: SVGTitleElement
    var tspan: SVGTSpanElement
    var use: SVGUseElement
    var view: SVGViewElement
}

typealias PerformanceEntryList = Array<PerformanceEntry>

typealias COSEAlgorithmIdentifier = Number

typealias AuthenticatorSelectionList = Array<dynamic /* typealias AAGUID = dynamic */>

typealias BigInteger = Uint8Array

typealias NamedCurve = String

typealias GLenum = Number

typealias GLboolean = Boolean

typealias GLbitfield = Number

typealias GLint = Number

typealias GLsizei = Number

typealias GLintptr = Number

typealias GLsizeiptr = Number

typealias GLuint = Number

typealias GLfloat = Number

typealias GLclampf = Number

typealias GLint64 = Number

typealias GLuint64 = Number

typealias WindowProxy = Window