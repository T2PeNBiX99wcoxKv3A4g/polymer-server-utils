package io.github.t2penbix99wcoxkv3a4g

import io.github.t2penbix99wcoxkv3a4g.datagen.*
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