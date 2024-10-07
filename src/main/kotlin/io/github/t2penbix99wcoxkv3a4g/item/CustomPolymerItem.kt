package io.github.t2penbix99wcoxkv3a4g.item

import eu.pb4.polymer.core.api.item.PolymerItem
import eu.pb4.polymer.resourcepack.api.PolymerModelData
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils
import io.github.t2penbix99wcoxkv3a4g.Utils
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier

class CustomPolymerItem(settings: Settings, item: Item, modelid: String) : Item(settings), PolymerItem {
    val polymerModel: PolymerModelData =
        PolymerResourcePackUtils.requestModel(item, Identifier.of(Utils.MOD_ID, modelid).withPrefixedPath("item/"))

    override fun getPolymerItem(itemStack: ItemStack, player: ServerPlayerEntity?): Item {
        return polymerModel.item()
    }

    override fun getPolymerCustomModelData(itemStack: ItemStack, player: ServerPlayerEntity?): Int {
        return polymerModel.value()
    }
}