package io.github.t2penbix99wcoxkv3a4g.item

import io.github.t2penbix99wcoxkv3a4g.Utils
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object Items {
    @JvmStatic
    val COOKED_ROTTEN_FLESH: Item = register(
        "cooked_rotten_flesh",
        CustomPolymerItem(
            Item.Settings().food(FoodComponents.COOKED_ROTTEN_FLESH),
            Items.ROTTEN_FLESH,
            "cooked_rotten_flesh"
        )
    )

    @JvmStatic
    val COOKED_CARROT: Item = register(
        "cooked_carrot",
        CustomPolymerItem(Item.Settings().food(FoodComponents.COOKED_CARROT), Items.CARROT, "cooked_carrot")
    )

    @JvmStatic
    private fun register(id: String, item: Item): Item {
        return Registry.register(Registries.ITEM, Identifier.of(Utils.MOD_ID, id), item)
    }
}