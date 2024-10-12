package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.datagen

import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item.Items
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class ChineseLangProvider(
    val dataGenerator: FabricDataOutput, registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricLanguageProvider(dataGenerator, "zh_cn", registryLookup), LangUtil {
    override fun generateTranslations(
        registryLookup: RegistryWrapper.WrapperLookup, translationBuilder: TranslationBuilder
    ) {
        translationBuilder.add(Items.COOKED_ROTTEN_FLESH, "熟腐肉")
        translationBuilder.add(Items.COOKED_CARROT, "熟胡萝卜")
        translationBuilder.add(Items.BLAHAJ, "柔软玩具鲨鱼")
        translationBuilder.add(Items.KLAPPAR_HAJ, "灰色玩具鲨鱼")
        translationBuilder.add(Items.BLAVINGAD, "柔软玩具蓝鲸")
        translationBuilder.add(Items.BROWN_BEAR, "柔软玩具棕熊")
        translationBuilder.add(Items.BREAD, "面包枕头")
        translationBuilder.add(Items.HAH, "哈?")

        loadExistingFile(dataGenerator, translationBuilder, "zh_cn")
    }
}