package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.impl

import net.minecraft.item.Item

@Suppress("FunctionName")
interface ItemEX {
    companion object {
        @JvmStatic
        fun of(item: Item): ItemEX {
            return item as ItemEX
        }
    }

    fun `polymerServerUtils$setIsCuddlyItem`(isCuddly: Boolean)
    fun `polymerServerUtils$isCuddlyItem`(): Boolean
}