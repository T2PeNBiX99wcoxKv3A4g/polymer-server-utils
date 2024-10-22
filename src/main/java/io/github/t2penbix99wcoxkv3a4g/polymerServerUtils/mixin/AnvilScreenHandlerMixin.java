package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item.CuddlyItem;
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
class AnvilScreenHandlerMixin extends ForgingScreenHandlerMixin {
    @Inject(
            method = "updateResult()V",
            at = {
                    @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/item/ItemStack;getName()Lnet/minecraft/text/Text;"
                    ),
                    @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/item/ItemStack;remove(Lnet/minecraft/component/ComponentType;)Ljava/lang/Object;"
                    )
            },
            expect = 2,
            require = 2
    )
    private void setName(CallbackInfo ci, @Local(ordinal = 1) ItemStack itemStack2) {
        if(itemStack2.getItem() instanceof CuddlyItem) {
            itemStack2.set(DataComponentTypes.OWNER, player.getName().getString());
        }
    }
}
