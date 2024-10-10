package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item

import net.minecraft.component.type.FoodComponent

object FoodComponents {
    @JvmField
    val COOKED_ROTTEN_FLESH: FoodComponent = FoodComponent.Builder().nutrition(3).saturationModifier(0.5f).build()

    @JvmField
    val COOKED_CARROT: FoodComponent = FoodComponent.Builder().nutrition(5).saturationModifier(0.6f).build()
}