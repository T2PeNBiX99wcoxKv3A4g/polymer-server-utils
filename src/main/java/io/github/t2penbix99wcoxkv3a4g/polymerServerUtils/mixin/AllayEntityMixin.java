package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.mixin;

import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item.CuddlyItem;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AllayEntity.class)
public class AllayEntityMixin {
    @Inject(
            method = "interactMob",
            at = @At("HEAD"),
            cancellable = true
    )
    public void preventTakePlush(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> info) {
        if (player.getStackInHand(hand).getItem() instanceof CuddlyItem) {
            info.setReturnValue(ActionResult.PASS);
            info.cancel();
        }
    }
}
