package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item

import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.Utils
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object Items {
    @JvmField
    val COOKED_ROTTEN_FLESH = register(
        "cooked_rotten_flesh",
        CustomPolymerItem(
            Item.Settings().food(FoodComponents.COOKED_ROTTEN_FLESH),
            Items.ROTTEN_FLESH,
            "cooked_rotten_flesh"
        )
    )

    @JvmField
    val COOKED_CARROT = register(
        "cooked_carrot",
        CustomPolymerItem(Item.Settings().food(FoodComponents.COOKED_CARROT), Items.CARROT, "cooked_carrot")
    )

    @JvmField
    val KLAPPAR_HAJ = register(
        "gray_shark",
        CuddlyItem(Item.Settings().maxCount(1), "gray_shark")
    )

    @JvmField
    val BLAHAJ = register("blue_shark", CuddlyItem(Item.Settings().maxCount(1), "blue_shark"))

    @JvmField
    val BLAVINGAD = register("blue_whale", ItemContainerCuddlyItem(Item.Settings().maxCount(1), "blue_whale"))

    @JvmField
    val BREAD = register("bread", CuddlyItem(Item.Settings().maxCount(1), "bread", false))

    @JvmField
    val BROWN_BEAR = register("brown_bear", CuddlyItem(Item.Settings().maxCount(1), "brown_bear"))
    
    @JvmField
    val HAH = register("hah", HahItem())

    private fun register(id: String, item: Item): Item {
        return Registry.register(Registries.ITEM, Identifier.of(Utils.MOD_ID, id), item)
    }
}