package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item

import com.mojang.serialization.Codec
import eu.pb4.polymer.core.api.other.PolymerComponent
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.Utils
import net.minecraft.component.ComponentType
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object DataComponentTypes {
    @JvmField
    val OWNER = registerString("cuddly_item_owner")

    @JvmField
    val IS_CUDDLY_ITEM = registerBoolean("is_cuddly_item")
    
    @JvmField
    val ITEM = registerItemStack("item")

    private fun registerString(id: String): ComponentType<String> {
        val ret = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Utils.MOD_ID, id),
            ComponentType.builder<String>().codec(Codec.STRING).build()
        )

        PolymerComponent.registerDataComponent(ret)

        return ret
    }

    private fun registerBoolean(id: String): ComponentType<Boolean> {
        val ret = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Utils.MOD_ID, id),
            ComponentType.builder<Boolean>().codec(Codec.BOOL).build()
        )

        PolymerComponent.registerDataComponent(ret)

        return ret
    }

    private fun registerItemStack(id: String): ComponentType<ItemStack> {
        val ret = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Utils.MOD_ID, id),
            ComponentType.builder<ItemStack>().codec(ItemStack.CODEC).build()
        )

        PolymerComponent.registerDataComponent(ret)

        return ret
    }
}