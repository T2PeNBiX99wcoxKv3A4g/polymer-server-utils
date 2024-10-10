package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.payload

import eu.pb4.polymer.networking.api.ContextByteBuf
import eu.pb4.polymer.networking.api.PolymerNetworking
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.Utils
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload
import net.minecraft.network.packet.CustomPayload.Id

@JvmRecord
data class VersionHandshakePayload(val version: Int) : CustomPayload {
    companion object {
        //        val ID = CustomPayload.Id<ModInitPayload>(Identifier.of(Utils.MOD_ID, ""))
        val ID = PolymerNetworking.id<VersionHandshakePayload>(Utils.MOD_ID, "handshake")
        val CODEC = PacketCodec.of(VersionHandshakePayload::write, VersionHandshakePayload::read)

        fun read(buf: ContextByteBuf): VersionHandshakePayload {
            return VersionHandshakePayload(buf.readInt())
        }
    }

    fun write(buf: ContextByteBuf) {
        buf.writeInt(version)
    }

    override fun getId(): Id<out CustomPayload> {
        return ID
    }
}