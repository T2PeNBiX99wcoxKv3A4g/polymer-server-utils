package io.github.t2penbix99wcoxkv3a4g.item

import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils
import io.github.t2penbix99wcoxkv3a4g.Utils
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier

object ItemGroups {
    @JvmStatic
    var MOD_GROUP: ItemGroup = PolymerItemGroupUtils.builder()
        .displayName(Text.translatable("item-group.${Utils.MOD_ID}.item-group"))
        .icon { ItemStack(Items.COOKED_ROTTEN_FLESH) }
        .entries { _: ItemGroup.DisplayContext?, entries: ItemGroup.Entries ->
            entries.add(Items.COOKED_ROTTEN_FLESH)
        }
        .build()

    @JvmStatic
    fun register() {
        PolymerItemGroupUtils.registerPolymerItemGroup(Identifier.of(Utils.MOD_ID, "item-group"), MOD_GROUP)
        Utils.logger.info("Register Items.")
    }
}