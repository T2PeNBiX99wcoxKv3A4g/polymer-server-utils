package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item

import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.Utils
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import net.minecraft.util.Identifier

object ItemGroups {
    @JvmField
    var MOD_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Utils.MOD_ID, "item-group"))

    @JvmField
    var MOD_GROUP = PolymerItemGroupUtils.builder()
        .displayName(Text.translatable("item-group.${Utils.MOD_ID}.item-group"))
        .icon { ItemStack(Items.COOKED_ROTTEN_FLESH) }
        .entries { _: ItemGroup.DisplayContext?, entries: ItemGroup.Entries ->
            entries.add(Items.COOKED_ROTTEN_FLESH)
            entries.add(Items.COOKED_CARROT)
            entries.add(Items.KLAPPAR_HAJ)
            entries.add(Items.BLAHAJ)
            entries.add(Items.BLAVINGAD)
            entries.add(Items.BREAD)
            entries.add(Items.BROWN_BEAR)
        }
        .build()
    
    internal fun register() {
        PolymerItemGroupUtils.registerPolymerItemGroup(MOD_GROUP_KEY, MOD_GROUP)
        Utils.logger.info("Register Items")
    }
}