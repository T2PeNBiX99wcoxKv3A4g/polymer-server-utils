package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item

import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.Utils
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.world.World
import net.minecraft.component.DataComponentTypes as MCDataComponentTypes

// From: https://github.com/hibiii/Blahaj/blob/main/platform-quilt-1.20.4/src/main/java/hibi/blahaj/CuddlyItem.java
class CuddlyItem(settings: Settings, modelid: String, hasTooltio: Boolean = true) :
    CustomPolymerItem(settings, Items.WHITE_WOOL, modelid) {
    val subtitle: Text? = if (hasTooltio) Text.translatable("item.${Utils.MOD_ID}.${modelid}.tooltip")
        .formatted(Formatting.GRAY) else null

    override fun getName(stack: ItemStack): Text {
        val isCuddly = stack.get(DataComponentTypes.IS_CUDDLY_ITEM)

        if (isCuddly == null)
            stack.set(DataComponentTypes.IS_CUDDLY_ITEM, true)

        return super.getName(stack)
    }

    override fun appendTooltip(
        stack: ItemStack, context: TooltipContext, tooltip: MutableList<Text>, type: TooltipType
    ) {
        if (subtitle != null) tooltip.add(subtitle)

        val owner = stack.get(DataComponentTypes.OWNER)

        if (owner.isNullOrEmpty()) return

        val customName = stack.get(MCDataComponentTypes.CUSTOM_NAME)

        if (customName != null)
            tooltip.add(
                Text.translatable("tooltip.${Utils.MOD_ID}.owner.rename", customName, Text.literal(owner))
                    .formatted(Formatting.GRAY)
            )
        else
            tooltip.add(
                Text.translatable("tooltip.${Utils.MOD_ID}.owner.craft", Text.literal(owner)).formatted(Formatting.GRAY)
            )
    }

    override fun onCraftByPlayer(stack: ItemStack, world: World, player: PlayerEntity) {
        stack.set(DataComponentTypes.OWNER, player.name.string)
        super.onCraftByPlayer(stack, world, player)
    }

    override fun getMiningSpeed(stack: ItemStack, state: BlockState): Float {
        return .25f
    }

    override fun getPolymerItem(itemStack: ItemStack, player: ServerPlayerEntity?): Item {
//        ServerPlayNetworking.send(
//            player!!,
//            IsCuddlyItemPayload(PolymerItemUtils.getClientItemStack(itemStack, player), true)
//        )
        return super.getPolymerItem(itemStack, player)
    }
}