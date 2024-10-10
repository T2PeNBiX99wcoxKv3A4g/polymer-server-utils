package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.impl

import eu.pb4.polymer.networking.impl.NetworkHandlerExtension
import net.minecraft.network.ClientConnection
import net.minecraft.server.network.ServerCommonNetworkHandler

@Suppress("FunctionName")
interface ClientConnectionEX {
    companion object {
        @JvmStatic
        fun of(connection: ClientConnection): ClientConnectionEX{
            return connection as ClientConnectionEX
        }
        
        @Suppress("UnstableApiUsage")
        @JvmStatic
        fun of(networkHandler: ServerCommonNetworkHandler): ClientConnectionEX {
            return of(NetworkHandlerExtension.of(networkHandler).`polymerNet$getConnection`())
        }
    }

    fun `polymerServerUtils$setVersion`(version: Int)
    fun `polymerServerUtils$version`(): Int
}