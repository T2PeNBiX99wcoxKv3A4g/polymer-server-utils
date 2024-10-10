package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder

interface LangUtil {
    fun loadExistingFile(dataGenerator: FabricDataOutput, translationBuilder: TranslationBuilder, id: String) {
        // Load an existing language file.
        try {
            val existingFilePath =
                dataGenerator.modContainer.findPath("assets/polymer-server-utils/lang_gen/${id}.existing.json").get()
            translationBuilder.add(existingFilePath)
        } catch (e: Exception) {
            throw RuntimeException("Failed to add existing language file!", e)
        }
    }
}