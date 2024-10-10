package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item

import eu.pb4.polymer.core.api.item.PolymerItem
import eu.pb4.polymer.networking.impl.NetworkHandlerExtension
import eu.pb4.polymer.resourcepack.api.PolymerModelData
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.Utils
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.impl.ClientConnectionEX
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier

open class CustomPolymerItem(settings: Settings, item: Item, modelid: String) : Item(settings), PolymerItem {
    val polymerModel: PolymerModelData =
        PolymerResourcePackUtils.requestModel(item, Identifier.of(Utils.MOD_ID, modelid).withPrefixedPath("item/"))

    open val needVersion: Int = 1

    @Suppress("UnstableApiUsage")
    fun getSupportedVersion(player: ServerPlayerEntity?): Int {
//        val connection = ExtClientConnection.of(NetworkHandlerExtension.of(player).`polymerNet$getConnection`())
        val connection = ClientConnectionEX.of(NetworkHandlerExtension.of(player).`polymerNet$getConnection`())

        return connection.`polymerServerUtils$version`()
//        return PolymerServerNetworking.getSupportedVersion(player!!.networkHandler, Identifier.of(Utils.MOD_ID, "handshake"))
    }

    override fun getPolymerItem(itemStack: ItemStack, player: ServerPlayerEntity?): Item {
        // break
//        Utils.logger.debug("Item: {}", polymerModel.item())
//        if (getSupportedVersion(player) >= needVersion)
//            return this
        return polymerModel.item()
    }

    override fun getPolymerCustomModelData(itemStack: ItemStack, player: ServerPlayerEntity?): Int {
        // break
//        Utils.logger.debug("Custom Model Data ID: {}", polymerModel.value())
//        if (getSupportedVersion(player) >= needVersion)
//            return -1
        return polymerModel.value()
    }

//    override fun getPolymerItemStack(
//        itemStack: ItemStack,
//        tooltipType: TooltipType,
//        lookup: RegistryWrapper.WrapperLookup,
//        player: ServerPlayerEntity?
//    ): ItemStack {
//        if (getSupportedVersion(player) >= needVersion)
//            return ItemStack(this)
//        return super.getPolymerItemStack(itemStack, tooltipType, lookup, player)
//    }
//
//    override fun getPolymerReplacement(player: ServerPlayerEntity): Item {
//        if (getSupportedVersion(player) >= needVersion)
//            return this
//        return super.getPolymerReplacement(player)
//    }
}