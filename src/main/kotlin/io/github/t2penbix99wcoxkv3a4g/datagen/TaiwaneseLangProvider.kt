package io.github.t2penbix99wcoxkv3a4g.datagen

import io.github.t2penbix99wcoxkv3a4g.item.Items
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class TaiwaneseLangProvider(
    val dataGenerator: FabricDataOutput, registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricLanguageProvider(dataGenerator, "zh_tw", registryLookup) {
    override fun generateTranslations(
        registryLookup: RegistryWrapper.WrapperLookup, translationBuilder: TranslationBuilder
    ) {
        translationBuilder.add(Items.COOKED_ROTTEN_FLESH, "烤腐肉")
        translationBuilder.add(Items.COOKED_CARROT, "烤胡蘿蔔")

        // Load an existing language file.
        try {
            val existingFilePath =
                dataGenerator.modContainer.findPath("assets/polymer-server-utils/lang_gen/zh_tw.existing.json").get()
            translationBuilder.add(existingFilePath)
        } catch (e: Exception) {
            throw RuntimeException("Failed to add existing language file!", e)
        }
    }
}