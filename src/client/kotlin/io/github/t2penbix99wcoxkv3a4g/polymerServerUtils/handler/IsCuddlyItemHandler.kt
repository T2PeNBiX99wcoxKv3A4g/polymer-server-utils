package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.handler

import eu.pb4.polymer.networking.api.client.PolymerClientPacketHandler
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.Utils
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.impl.ItemEX
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.payload.IsCuddlyItemPayload
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayNetworkHandler

class IsCuddlyItemHandler : PolymerClientPacketHandler<ClientPlayNetworkHandler, IsCuddlyItemPayload> {
    companion object {
        private var _instance: IsCuddlyItemHandler? = null

        @JvmStatic
        val Instance: IsCuddlyItemHandler
            get() {
                if (_instance == null)
                    _instance = IsCuddlyItemHandler()
                return _instance!!
            }
    }

    override fun onPacket(
        client: MinecraftClient,
        handler: ClientPlayNetworkHandler,
        packet: IsCuddlyItemPayload
    ) {
        val item = packet.itemStack.item
        val itemEX = ItemEX.of(item)
        Utils.logger.debug("Client item set: {}", item)
        itemEX.`polymerServerUtils$setIsCuddlyItem`(true)
    }
}