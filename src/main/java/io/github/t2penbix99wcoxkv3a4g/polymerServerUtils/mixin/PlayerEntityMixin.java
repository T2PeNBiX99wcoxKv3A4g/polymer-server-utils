package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.mixin;

import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item.Items;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
class PlayerEntityMixin {
    @Unique
    private final ItemStack hahItemStack = new ItemStack(Items.HAH);
    @Shadow
    private ItemStack selectedItem;
    @Unique
    private boolean prevAllowFlying;
    
    @Unique
    private boolean prevFlying;
    
    @Unique
    private boolean isChangeBefore;
    
    @Inject(method = "tick", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/player/PlayerEntity;isSpectator()Z",
            shift = At.Shift.AFTER))
    private void onTick(CallbackInfo ci) {
        var self = (PlayerEntity)(Object)this;
        var abilities = self.getAbilities();

        if (ItemStack.areItemsEqual(selectedItem, hahItemStack)) {
            if (!isChangeBefore) {
                prevAllowFlying = abilities.allowFlying;
                prevFlying = abilities.flying;
            }

            abilities.allowFlying = true;
            self.sendAbilitiesUpdate();
            isChangeBefore = true;
        }
        else {
            if (isChangeBefore) {
                abilities.allowFlying = prevAllowFlying;
                abilities.flying = prevFlying;
                self.sendAbilitiesUpdate();
                isChangeBefore = false;
            }
        }
    }
}
