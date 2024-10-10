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
        translationBuilder.add(Items.BLAHAJ, "柔らかいサメのおもちゃ")
        translationBuilder.add(Items.KLAPPAR_HAJ, "灰色サメのおもちゃ")
        translationBuilder.add(Items.BLAVINGAD, "柔らかい青い鯨のおもちゃ")
        translationBuilder.add(Items.BROWN_BEAR, "柔らかいヒグマのおもちゃ")
        translationBuilder.add(Items.BREAD, "パン枕")

        loadExistingFile(dataGenerator, translationBuilder, "ja_jp")
    }
}