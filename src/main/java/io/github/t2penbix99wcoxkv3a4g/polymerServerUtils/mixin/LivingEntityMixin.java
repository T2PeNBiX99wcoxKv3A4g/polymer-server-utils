package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.mixin;

import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item.Items;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
class LivingEntityMixin {
    @Unique
    private final ItemStack hahItemStack = new ItemStack(Items.HAH);
    
    @Inject(method = "canTakeDamage", at = @At("HEAD"), cancellable = true)
    private void canTakeDamage(CallbackInfoReturnable<Boolean> cir) {
        var self = (LivingEntity)(Object)this;
        var main = self.getStackInHand(Hand.MAIN_HAND);
        var off = self.getStackInHand(Hand.OFF_HAND);
        
        if (ItemStack.areItemsEqual(main, hahItemStack) || ItemStack.areItemsEqual(off, hahItemStack)) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
    
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        var self = (LivingEntity)(Object)this;
        var main = self.getStackInHand(Hand.MAIN_HAND);
        var off = self.getStackInHand(Hand.OFF_HAND);

        if (ItemStack.areItemsEqual(main, hahItemStack) || ItemStack.areItemsEqual(off, hahItemStack)) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
