package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils

import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.datagen.ChineseLangProvider
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.datagen.EnglishLangProvider
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.datagen.JapaneseLangProvider
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.datagen.ModelGenerator
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.datagen.RecipeGenerator
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.datagen.TaiwaneseLangProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object PolymerServerUtilsDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()

        // Languages
        pack.addProvider(::EnglishLangProvider)
        pack.addProvider(::JapaneseLangProvider)
        pack.addProvider(::ChineseLangProvider)
        pack.addProvider(::TaiwaneseLangProvider)

        // Models
        pack.addProvider(::ModelGenerator)
        
        // Recipes
        pack.addProvider(::RecipeGenerator)
    }
}