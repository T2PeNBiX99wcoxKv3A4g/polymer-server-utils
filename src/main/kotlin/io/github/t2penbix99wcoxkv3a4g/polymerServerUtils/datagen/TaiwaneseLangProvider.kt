package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.datagen

import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item.Items
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class TaiwaneseLangProvider(
    val dataGenerator: FabricDataOutput, registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricLanguageProvider(dataGenerator, "zh_tw", registryLookup), LangUtil {
    override fun generateTranslations(
        registryLookup: RegistryWrapper.WrapperLookup, translationBuilder: TranslationBuilder
    ) {
        translationBuilder.add(Items.COOKED_ROTTEN_FLESH, "烤腐肉")
        translationBuilder.add(Items.COOKED_CARROT, "烤胡蘿蔔")
        translationBuilder.add(Items.BLAHAJ, "柔軟玩具鯊魚")
        translationBuilder.add(Items.KLAPPAR_HAJ, "灰色玩具鯊魚")
        translationBuilder.add(Items.BLAVINGAD, "柔軟玩具藍鯨")
        translationBuilder.add(Items.BROWN_BEAR, "柔軟玩具棕熊")
        translationBuilder.add(Items.BREAD, "麪包枕頭")
        
        loadExistingFile(dataGenerator, translationBuilder, "zh_tw")
    }
}