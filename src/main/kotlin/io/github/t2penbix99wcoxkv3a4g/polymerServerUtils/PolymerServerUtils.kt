package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils

import eu.pb4.polymer.networking.api.PolymerNetworking
import eu.pb4.polymer.networking.api.server.PolymerServerNetworking
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.handler.VersionHandshakeHandler
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item.DataComponentTypes
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item.ItemGroups
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item.Items
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.payload.IsCuddlyItemPayload
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.payload.VersionHandshakePayload
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.fabricmc.fabric.api.`object`.builder.v1.trade.TradeOfferHelper
import net.minecraft.item.ItemStack
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTables
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.util.Identifier
import net.minecraft.village.TradeOffer
import net.minecraft.village.TradedItem
import net.minecraft.village.VillagerProfession
import net.minecraft.item.Items as MCItems

object PolymerServerUtils : ModInitializer {
    override fun onInitialize() {
        DataComponentTypes
        Items
        ItemGroups.register()

        LootTableEvents.MODIFY.register(
            Identifier.of(
                Utils.MOD_ID,
                "loot_table_event"
            )
        ) { key, tableBuilder, source, registries ->
            if (!source.isBuiltin) return@register

            when (key.value) {
                LootTables.STRONGHOLD_CROSSING_CHEST, LootTables.STRONGHOLD_CORRIDOR_CHEST -> {
                    val pb = LootPool.builder()
                        .with(ItemEntry.builder(Items.KLAPPAR_HAJ).weight(5))
                        .with(ItemEntry.builder(MCItems.AIR).weight(100))
                    tableBuilder.pool(pb)
                }

                LootTables.VILLAGE_PLAINS_CHEST -> {
                    val pb = LootPool.builder()
                        .with(ItemEntry.builder(Items.KLAPPAR_HAJ))
                        .with(ItemEntry.builder(MCItems.AIR).weight(43))
                    tableBuilder.pool(pb)
                }

                LootTables.VILLAGE_TAIGA_HOUSE_CHEST, LootTables.VILLAGE_SNOWY_HOUSE_CHEST -> {
                    val pb = LootPool.builder()
                        .with(ItemEntry.builder(Items.KLAPPAR_HAJ).weight(5))
                        .with(ItemEntry.builder(MCItems.AIR).weight(54))
                    tableBuilder.pool(pb)
                }
            }
        }

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.SHEPHERD, 5) {
            it.add { entity, random ->
                return@add TradeOffer(TradedItem(MCItems.EMERALD, 15), ItemStack(Items.KLAPPAR_HAJ), 2, 30, 0.1f)
            }
        }

        PolymerNetworking.registerC2SVersioned(VersionHandshakePayload.ID, Utils.version, VersionHandshakePayload.CODEC)
        PolymerNetworking.registerS2CVersioned(IsCuddlyItemPayload.ID, Utils.version, IsCuddlyItemPayload.CODEC)

        PolymerServerNetworking.registerPlayHandler<VersionHandshakePayload>(
            VersionHandshakePayload::class.java,
            VersionHandshakeHandler.Instance
        )

        if (PolymerResourcePackUtils.addModAssets(Utils.MOD_ID))
            Utils.logger.info("Successfully added mod assets for " + Utils.MOD_ID)
        else
            Utils.logger.error("Failed to add mod assets for " + Utils.MOD_ID)

        PolymerResourcePackUtils.markAsRequired()
    }
}