package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item

import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.Utils
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.StackReference
import net.minecraft.item.BundleItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.screen.slot.Slot
import net.minecraft.text.Text
import net.minecraft.util.ClickType
import net.minecraft.item.Items as MCItems

//import net.minecraft.component.DataComponentTypes as MCDataComponentTypes

class ItemContainerCuddlyItem(
    settings: Settings,
    modelid: String,
    hasTooltio: Boolean = true,
    item: Item = MCItems.BUNDLE
) :
    CuddlyItem(settings, modelid, hasTooltio, item) {
    companion object {
        internal fun canHold(otherStack: ItemStack): Boolean {
            if (!otherStack.item.canBeNested() || otherStack.item is ItemContainerCuddlyItem || otherStack.item is BundleItem)
                return false
            return true
        }

        internal fun storeItemStack(stack: ItemStack, otherStack: ItemStack?) {
            if (otherStack == null || otherStack.isEmpty)
                stack.remove(DataComponentTypes.ITEM)
            else {
                stack.set(DataComponentTypes.ITEM, otherStack)
                otherStack.count = 0
            }
        }

        internal fun mergeStacks(dest: ItemStack, source: ItemStack): Boolean {
            if (!ItemStack.areItemsAndComponentsEqual(dest, source))
                return false
            val destCount = dest.count
            val sourceCount = source.count
            val destMax = dest.maxCount
            dest.increment(destCount + sourceCount)
            val surplus = destCount + sourceCount - destMax
            source.count = surplus
            return source.isEmpty
        }
    }

    override fun onStackClicked(
        stack: ItemStack,
        slot: Slot,
        clickType: ClickType,
        player: PlayerEntity
    ): Boolean {
        Utils.logger.debug("onStackClicked {}", clickType)
        if (clickType != ClickType.RIGHT)
            return false

        val otherStack = slot.stack
        val bundleContentsComponent = stack.get(DataComponentTypes.ITEM)

        if (bundleContentsComponent != null) {
            if (!otherStack.isEmpty)
                return false
            if (!slot.canInsert(bundleContentsComponent))
                return false
            slot.insertStack(bundleContentsComponent, DEFAULT_MAX_COUNT)
            return true
        } else {
            if (otherStack.isEmpty)
                return false
            if (!canHold(otherStack))
                return false
            storeItemStack(stack, otherStack)
            return true
        }
    }

    override fun onClicked(
        stack: ItemStack,
        otherStack: ItemStack,
        slot: Slot,
        clickType: ClickType,
        player: PlayerEntity,
        cursorStackReference: StackReference
    ): Boolean {
        Utils.logger.debug("onClicked {}", clickType)
        if (clickType != ClickType.RIGHT || otherStack.isEmpty)
            return false
        val storedItem = stack.get(DataComponentTypes.ITEM)

        if (storedItem != null)
            return false
        else {
            if (!canHold(otherStack))
                return false
            storeItemStack(stack, otherStack)
            return true
        }
    }

    override fun appendTooltip(
        stack: ItemStack,
        context: TooltipContext,
        tooltip: MutableList<Text>,
        type: TooltipType
    ) {
        super.appendTooltip(stack, context, tooltip, type)
        val storedItem = stack.get(DataComponentTypes.ITEM)
        if (storedItem == null)
            return
        val text = storedItem.name.copy()
        text.append(" x").append(storedItem.count.toString())
        tooltip.add(text)
    }
}