package io.github.t2penbix99wcoxkv3a4g.datagen

import io.github.t2penbix99wcoxkv3a4g.Utils
import io.github.t2penbix99wcoxkv3a4g.item.Items
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.item.Item
import net.minecraft.item.Items as MCItems
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier
import java.util.concurrent.CompletableFuture

class RecipeGenerator(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
    FabricRecipeProvider(output, registriesFuture) {
    override fun generate(exporter: RecipeExporter) {
        campfireRecipe(exporter, MCItems.ROTTEN_FLESH, Items.COOKED_ROTTEN_FLESH, 0.15f, 600)
        smokingRecipe(exporter, MCItems.ROTTEN_FLESH, Items.COOKED_ROTTEN_FLESH, 0.15f, 100)
        smeltingRecipe(exporter, MCItems.ROTTEN_FLESH, Items.COOKED_ROTTEN_FLESH, 0.15f, 200)
    }

    private fun campfireRecipe(
        exporter: RecipeExporter,
        input: Item,
        output: Item,
        experience: Float,
        cookingTime: Int
    ) {
        CookingRecipeJsonBuilder.createCampfireCooking(
            Ingredient.ofItems(input),
            RecipeCategory.FOOD,
            output,
            experience,
            cookingTime
        )
            .criterion(hasItem(input), conditionsFromItem(input))
            .offerTo(exporter, Identifier.of(Utils.MOD_ID, "${getRecipeName(output)}_from_campfire_cooking"))
    }

    private fun smokingRecipe(
        exporter: RecipeExporter,
        input: Item,
        output: Item,
        experience: Float,
        cookingTime: Int
    ) {
        CookingRecipeJsonBuilder.createSmoking(
            Ingredient.ofItems(input),
            RecipeCategory.FOOD,
            output,
            experience,
            cookingTime
        )
            .criterion(hasItem(input), conditionsFromItem(input))
            .offerTo(exporter, Identifier.of(Utils.MOD_ID, "${getRecipeName(output)}_from_smoking"))
    }

    private fun smeltingRecipe(
        exporter: RecipeExporter,
        input: Item,
        output: Item,
        experience: Float,
        cookingTime: Int
    ) {
        CookingRecipeJsonBuilder.createSmelting(
            Ingredient.ofItems(input),
            RecipeCategory.FOOD,
            output,
            experience,
            cookingTime
        )
            .criterion(hasItem(input), conditionsFromItem(input))
            .offerTo(exporter, Identifier.of(Utils.MOD_ID, getRecipeName(output)))
    }
}