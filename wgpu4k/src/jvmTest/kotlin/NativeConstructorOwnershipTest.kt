@file:OptIn(io.ygdrasil.webgpu.WGPULowLevelApi::class)

package io.ygdrasil.webgpu

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility

class NativeConstructorOwnershipTest : FreeSpec({
    "native constructors cannot bypass lifecycle ownership" {
        fun KClass<*>.ownedConstructorVisibility(): KVisibility? =
            constructors.single { it.parameters.size == 3 }.visibility

        Device::class.ownedConstructorVisibility() shouldBe KVisibility.INTERNAL
        Queue::class.ownedConstructorVisibility() shouldBe KVisibility.INTERNAL
        Device::class.constructors.none { it.visibility == KVisibility.PUBLIC } shouldBe true
        Queue::class.constructors.none { it.visibility == KVisibility.PUBLIC } shouldBe true
    }

    "device queue acquisition is eager rather than a post-close lazy downcall" {
        Device::class.java.declaredFields.any { it.name == "queue\$delegate" } shouldBe false
    }
})
