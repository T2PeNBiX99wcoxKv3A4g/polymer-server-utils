package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.datagen

import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item.Items
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class EnglishLangProvider(
    val dataGenerator: FabricDataOutput, registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricLanguageProvider(dataGenerator, "en_us", registryLookup), LangUtil {
    override fun generateTranslations(
        registryLookup: RegistryWrapper.WrapperLookup, translationBuilder: TranslationBuilder
    ) {
        translationBuilder.add(Items.COOKED_ROTTEN_FLESH, "Cooked Rotten Flesh")
        translationBuilder.add(Items.COOKED_CARROT, "Cooked Carrot")
        translationBuilder.add(Items.BLAHAJ, "Soft Toy Shark")
        translationBuilder.add(Items.KLAPPAR_HAJ, "Gray Toy Shark")
        translationBuilder.add(Items.BLAVINGAD, "Soft Toy Blue Whale")
        translationBuilder.add(Items.BROWN_BEAR, "Soft Toy Brown Bear")
        translationBuilder.add(Items.BREAD, "Bread Pillow")
        translationBuilder.add(Items.HAH, "HAH?")

        loadExistingFile(dataGenerator, translationBuilder, "en_us")
    }
}
