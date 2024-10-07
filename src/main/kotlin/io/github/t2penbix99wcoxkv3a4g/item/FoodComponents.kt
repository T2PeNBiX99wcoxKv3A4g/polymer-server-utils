package io.github.t2penbix99wcoxkv3a4g.item

import net.minecraft.component.type.FoodComponent

object FoodComponents {
    @JvmStatic
    val COOKED_ROTTEN_FLESH: FoodComponent = FoodComponent.Builder().nutrition(3).saturationModifier(0.5f).build()

    @JvmStatic
    val COOKED_CARROT: FoodComponent = FoodComponent.Builder().nutrition(5).saturationModifier(0.6f).build()
}