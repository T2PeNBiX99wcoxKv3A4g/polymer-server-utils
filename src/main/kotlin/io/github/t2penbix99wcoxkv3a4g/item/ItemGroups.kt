package io.github.t2penbix99wcoxkv3a4g.item

import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils
import io.github.t2penbix99wcoxkv3a4g.Utils
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import net.minecraft.util.Identifier

object ItemGroups {
    @JvmStatic
    var MOD_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Utils.MOD_ID, "item-group"))
    
    @JvmStatic
    var MOD_GROUP = PolymerItemGroupUtils.builder()
        .displayName(Text.translatable("item-group.${Utils.MOD_ID}.item-group"))
        .icon { ItemStack(Items.COOKED_ROTTEN_FLESH) }
        .entries { _: ItemGroup.DisplayContext?, entries: ItemGroup.Entries ->
            entries.add(Items.COOKED_ROTTEN_FLESH)
            entries.add(Items.COOKED_CARROT)
        }
        .build()

    @JvmStatic
    fun register() {
        PolymerItemGroupUtils.registerPolymerItemGroup(MOD_GROUP_KEY, MOD_GROUP)
        Utils.logger.info("Register Items")
    }
}