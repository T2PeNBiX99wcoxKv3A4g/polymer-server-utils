package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.datagen

import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.Utils
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item.Items
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
import net.minecraft.item.Item
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier
import java.util.concurrent.CompletableFuture
import net.minecraft.item.Items as MCItems

class RecipeGenerator(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
    FabricRecipeProvider(output, registriesFuture) {
    override fun generate(exporter: RecipeExporter) {
        campfireRecipe(exporter, MCItems.ROTTEN_FLESH, Items.COOKED_ROTTEN_FLESH, 0.15f)
        smokingRecipe(exporter, MCItems.ROTTEN_FLESH, Items.COOKED_ROTTEN_FLESH, 0.15f)
        smeltingRecipe(exporter, MCItems.ROTTEN_FLESH, Items.COOKED_ROTTEN_FLESH, 0.15f)

        campfireRecipe(exporter, MCItems.CARROT, Items.COOKED_CARROT, 0.35f)
        smokingRecipe(exporter, MCItems.CARROT, Items.COOKED_CARROT, 0.35f)
        smeltingRecipe(exporter, MCItems.CARROT, Items.COOKED_CARROT, 0.35f)

        blastingRecipe(exporter, MCItems.COBBLESTONE, MCItems.STONE, 0.1f, RecipeCategory.BUILDING_BLOCKS)

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BLAHAJ)
            .pattern(" # ")
            .pattern("###")
            .pattern("PW ")
            .input('#', MCItems.LIGHT_BLUE_WOOL)
            .input('P', MCItems.PINK_DYE)
            .input('W', MCItems.WHITE_WOOL)
            .criterion(hasItem(MCItems.LIGHT_BLUE_WOOL), conditionsFromItem(MCItems.LIGHT_BLUE_WOOL))
            .criterion(hasItem(MCItems.PINK_DYE), conditionsFromItem(MCItems.PINK_DYE))
            .criterion(hasItem(MCItems.WHITE_WOOL), conditionsFromItem(MCItems.WHITE_WOOL))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BLAVINGAD)
            .pattern(" # ")
            .pattern("###")
            .pattern("GW ")
            .input('#', MCItems.BLUE_WOOL)
            .input('G', MCItems.LIGHT_GRAY_WOOL)
            .input('W', MCItems.WHITE_WOOL)
            .criterion(hasItem(MCItems.BLUE_WOOL), conditionsFromItem(MCItems.BLUE_WOOL))
            .criterion(hasItem(MCItems.LIGHT_GRAY_WOOL), conditionsFromItem(MCItems.LIGHT_GRAY_WOOL))
            .criterion(hasItem(MCItems.WHITE_WOOL), conditionsFromItem(MCItems.WHITE_WOOL))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BREAD)
            .pattern("OOO")
            .pattern("OOO")
            .input('O', MCItems.ORANGE_WOOL)
            .criterion(hasItem(MCItems.ORANGE_WOOL), conditionsFromItem(MCItems.ORANGE_WOOL))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BROWN_BEAR)
            .pattern(" # ")
            .pattern("#@#")
            .pattern("###")
            .input('#', MCItems.BROWN_WOOL)
            .input('@', MCItems.BLACK_WOOL)
            .criterion(hasItem(MCItems.BROWN_WOOL), conditionsFromItem(MCItems.BROWN_WOOL))
            .criterion(hasItem(MCItems.BLACK_WOOL), conditionsFromItem(MCItems.BLACK_WOOL))
            .offerTo(exporter)
    }

    private fun campfireRecipe(
        exporter: RecipeExporter,
        input: Item,
        output: Item,
        experience: Float,
        recipeCategory: RecipeCategory = RecipeCategory.FOOD,
        cookingTime: Int = 600
    ) {
        CookingRecipeJsonBuilder.createCampfireCooking(
            Ingredient.ofItems(input), recipeCategory, output, experience, cookingTime
        ).criterion(hasItem(input), conditionsFromItem(input)).offerTo(
            exporter,
            Identifier.of(Utils.MOD_ID, "${getRecipeName(output)}_from_campfire_cooking_${getRecipeName(input)}")
        )
    }

    private fun smokingRecipe(
        exporter: RecipeExporter,
        input: Item,
        output: Item,
        experience: Float,
        recipeCategory: RecipeCategory = RecipeCategory.FOOD,
        cookingTime: Int = 100
    ) {
        CookingRecipeJsonBuilder.createSmoking(
            Ingredient.ofItems(input), recipeCategory, output, experience, cookingTime
        ).criterion(hasItem(input), conditionsFromItem(input)).offerTo(
            exporter, Identifier.of(Utils.MOD_ID, "${getRecipeName(output)}_from_smoking_${getRecipeName(input)}")
        )
    }

    private fun blastingRecipe(
        exporter: RecipeExporter,
        input: Item,
        output: Item,
        experience: Float,
        recipeCategory: RecipeCategory = RecipeCategory.FOOD,
        cookingTime: Int = 100
    ) {
        CookingRecipeJsonBuilder.createBlasting(
            Ingredient.ofItems(input), recipeCategory, output, experience, cookingTime
        ).criterion(hasItem(input), conditionsFromItem(input)).offerTo(
            exporter, Identifier.of(Utils.MOD_ID, "${getRecipeName(output)}_from_blasting_${getRecipeName(input)}")
        )
    }

    private fun smeltingRecipe(
        exporter: RecipeExporter,
        input: Item,
        output: Item,
        experience: Float,
        recipeCategory: RecipeCategory = RecipeCategory.FOOD,
        cookingTime: Int = 200
    ) {
        CookingRecipeJsonBuilder.createSmelting(
            Ingredient.ofItems(input), recipeCategory, output, experience, cookingTime
        ).criterion(hasItem(input), conditionsFromItem(input)).offerTo(
            exporter, Identifier.of(Utils.MOD_ID, "${getRecipeName(output)}_from_smelting_${getRecipeName(input)}")
        )
    }
}