package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.mixin.client;

import eu.pb4.polymer.core.api.item.PolymerItemUtils;
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.Utils;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Inject(
            method = "getArmPose(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;",
            at = @At("TAIL"),
            cancellable = true
    )
    private static void cuddleBlahaj(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> ci) {
        var stack = player.getStackInHand(hand);
        var id = PolymerItemUtils.getPolymerIdentifier(stack);

        if (id != null && Utils.getCuddlyItemList().contains(id.toString())) {
            ci.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
            ci.cancel();
        }
    }
}
