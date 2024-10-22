package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.mixin.client;

import eu.pb4.polymer.core.api.item.PolymerItemUtils;
import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.Utils;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public class BipedEntityModelMixin {
    @Shadow
    public @Final ModelPart rightArm;

    @Shadow
    public @Final ModelPart leftArm;

    @Inject(
            method = {"positionRightArm", "positionLeftArm"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/model/CrossbowPosing;hold(Lnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/model/ModelPart;Z)V",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    public void poseArms(LivingEntity entity, CallbackInfo ci) {
        var mainStack = entity.getMainHandStack();
        var offStack = entity.getOffHandStack();
        var mainId = PolymerItemUtils.getPolymerIdentifier(mainStack);
        var offId = PolymerItemUtils.getPolymerIdentifier(offStack);

        if (mainId != null && Utils.getCuddlyItemList().contains(mainId.toString()) || offId != null && Utils.getCuddlyItemList().contains(offId.toString())) {
            this.rightArm.pitch = -0.95F;
            this.rightArm.yaw = (float) (-Math.PI / 8);
            this.leftArm.pitch = -0.90F;
            this.leftArm.yaw = (float) (Math.PI / 8);
            ci.cancel();
        }
    }
}
