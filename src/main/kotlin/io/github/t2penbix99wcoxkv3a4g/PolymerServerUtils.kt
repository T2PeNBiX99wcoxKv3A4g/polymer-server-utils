package io.github.t2penbix99wcoxkv3a4g

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils
import io.github.t2penbix99wcoxkv3a4g.item.ItemGroups
import io.github.t2penbix99wcoxkv3a4g.item.Items
import net.fabricmc.api.ModInitializer

object PolymerServerUtils : ModInitializer {
    override fun onInitialize() {
        Items
        ItemGroups.register()

        if (PolymerResourcePackUtils.addModAssets(Utils.MOD_ID))
            Utils.logger.info("Successfully added mod assets for " + Utils.MOD_ID)
        else
            Utils.logger.error("Failed to add mod assets for " + Utils.MOD_ID)

        PolymerResourcePackUtils.markAsRequired()
    }
}