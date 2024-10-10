package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils

import eu.pb4.polymer.networking.api.PolymerNetworking
import eu.pb4.polymer.networking.api.client.PolymerClientNetworking
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.handler.IsCuddlyItemHandler
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.payload.IsCuddlyItemPayload
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.payload.VersionHandshakePayload
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import net.minecraft.util.Identifier

object PolymerServerUtilsClient : ClientModInitializer {
    override fun onInitializeClient() {
        PolymerNetworking.registerC2SVersioned(VersionHandshakePayload.ID, Utils.version, VersionHandshakePayload.CODEC)
        PolymerNetworking.registerS2CVersioned(IsCuddlyItemPayload.ID, Utils.version, IsCuddlyItemPayload.CODEC)

        // NO ;;;;
        Utils.addCuddlyItem("gray_shark")
        Utils.addCuddlyItem("blue_shark")
        Utils.addCuddlyItem("blue_whale")
        Utils.addCuddlyItem("bread")
        Utils.addCuddlyItem("brown_bear")

        PolymerClientNetworking.registerPlayHandler<IsCuddlyItemPayload>(
            IsCuddlyItemPayload::class.java,
            IsCuddlyItemHandler.Instance
        )

        ClientPlayConnectionEvents.JOIN.register(
            Identifier.of(
                Utils.MOD_ID,
                "join_server"
            )
        ) { handler, sender, client ->
            Utils.logger.debug("Send Version Handshake Payload")
            sender.sendPacket(VersionHandshakePayload(Utils.version))
        }
    }
}