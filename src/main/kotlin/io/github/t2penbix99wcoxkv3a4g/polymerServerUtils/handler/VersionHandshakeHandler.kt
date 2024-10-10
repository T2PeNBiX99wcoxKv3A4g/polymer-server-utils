package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.handler

import eu.pb4.polymer.networking.api.server.PolymerServerPacketHandler
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.Utils
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.impl.ClientConnectionEX
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.payload.VersionHandshakePayload
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler

class VersionHandshakeHandler : PolymerServerPacketHandler<ServerPlayNetworkHandler, VersionHandshakePayload> {
    companion object {
        private var _instance: VersionHandshakeHandler? = null

        @JvmStatic
        val Instance: VersionHandshakeHandler
            get() {
                if (_instance == null)
                    _instance = VersionHandshakeHandler()
                return _instance!!
            }
    }

    override fun onPacket(
        server: MinecraftServer,
        handler: ServerPlayNetworkHandler,
        packet: VersionHandshakePayload
    ) {
        Utils.logger.debug("Get Version ${packet.version}")
        ClientConnectionEX.of(handler).`polymerServerUtils$setVersion`(packet.version)
    }
}