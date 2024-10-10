package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.payload

import eu.pb4.polymer.networking.api.ContextByteBuf
import eu.pb4.polymer.networking.api.PolymerNetworking
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.Utils
import net.minecraft.item.ItemStack
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload

@JvmRecord
data class IsCuddlyItemPayload(val itemStack: ItemStack, val isCuddly: Boolean) : CustomPayload {
    companion object {
        val ID = PolymerNetworking.id<IsCuddlyItemPayload>(Utils.MOD_ID, "is_cuddly")
        val CODEC = PacketCodec.of(IsCuddlyItemPayload::write, IsCuddlyItemPayload::read)

        fun read(buf: ContextByteBuf): IsCuddlyItemPayload {
            return IsCuddlyItemPayload(ItemStack.OPTIONAL_PACKET_CODEC.decode(buf), buf.readBoolean())
        }
    }

    fun write(buf: ContextByteBuf) {
        ItemStack.OPTIONAL_PACKET_CODEC.encode(buf, itemStack)
        buf.writeBoolean(isCuddly)
    }

    override fun getId(): CustomPayload.Id<out CustomPayload> {
        return ID
    }
}