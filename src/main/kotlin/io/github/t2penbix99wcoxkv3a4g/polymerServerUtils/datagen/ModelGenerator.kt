package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.datagen

import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item.Items
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models

class ModelGenerator(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(blockStateModelGenerator: BlockStateModelGenerator) {
    }

    override fun generateItemModels(itemModelGenerator: ItemModelGenerator) {
        itemModelGenerator.register(Items.COOKED_ROTTEN_FLESH, Models.GENERATED)
        itemModelGenerator.register(Items.COOKED_CARROT, Models.GENERATED)
    }
}