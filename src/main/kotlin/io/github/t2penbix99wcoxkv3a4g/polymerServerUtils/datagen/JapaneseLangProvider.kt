package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.datagen

import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item.Items
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class JapaneseLangProvider(
    val dataGenerator: FabricDataOutput, registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricLanguageProvider(dataGenerator, "ja_jp", registryLookup), LangUtil {
    override fun generateTranslations(
        registryLookup: RegistryWrapper.WrapperLookup, translationBuilder: TranslationBuilder
    ) {
        translationBuilder.add(Items.COOKED_ROTTEN_FLESH, "焼き腐った肉")
        translationBuilder.add(Items.COOKED_CARROT, "焼きニンジン")
        translationBuilder.add(Items.BLAHAJ, "青いサメのおもちゃ")
        translationBuilder.add(Items.KLAPPAR_HAJ, "灰色のサメのおもちゃ")
        translationBuilder.add(Items.BLAVINGAD, "クジラのおもちゃ")
        translationBuilder.add(Items.BROWN_BEAR, "クマのおもちゃ")
        translationBuilder.add(Items.BREAD, "パンの抱き枕")
        translationBuilder.add(Items.HAH, "は？")

        loadExistingFile(dataGenerator, translationBuilder, "ja_jp")
    }
}