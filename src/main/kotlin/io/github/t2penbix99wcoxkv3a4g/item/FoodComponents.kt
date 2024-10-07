package io.github.t2penbix99wcoxkv3a4g.item

import net.minecraft.component.type.FoodComponent

class FoodComponents {
    companion object {
        val COOKED_ROTTEN_FLESH: FoodComponent = FoodComponent.Builder().nutrition(3).saturationModifier(0.5f).build()
    }
}