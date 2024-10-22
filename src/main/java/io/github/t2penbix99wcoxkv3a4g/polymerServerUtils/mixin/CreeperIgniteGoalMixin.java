package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.CreeperIgniteGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// https://github.com/JuaanP/CreeperBackguard/blob/main/src/main/java/com/juaanp/creeperbackguard/mixin/CreeperIgniteGoalMixinServer.java
@Mixin(CreeperIgniteGoal.class)
class CreeperIgniteGoalMixin {
    @Shadow
    private LivingEntity target;

    @Shadow
    @Final
    private CreeperEntity creeper;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void creeperIgnitionGoal(CallbackInfo ci) {
        if (target instanceof PlayerEntity player) {
            var creeper = this.creeper;

            // TODO: Add override fov
            var fovScale = 70d;

            // CreeperBackguardConfig.igniteOnThirdPerson

            var cameraYaw = MathHelper.wrapDegrees(player.getCameraPosVec(1.0f).z + 180.0);
            var cameraPitch = MathHelper.wrapDegrees(player.getCameraPosVec(1.0f).y);

            var deltaX = creeper.getX() - player.getCameraPosVec(1.0f).x;
            var deltaY = creeper.getEyeY() - player.getCameraPosVec(1.0f).y;
            var deltaZ = creeper.getZ() - player.getCameraPosVec(1.0f).z;

            var yawToCreeper = MathHelper.atan2(deltaZ, deltaX) * (180.0 / Math.PI) - 90.0;
            var pitchToCreeper = -MathHelper.atan2(deltaY, MathHelper.sqrt((float) (deltaX * deltaX + deltaZ * deltaZ))) * (180.0 / Math.PI);

            var yawDiff = MathHelper.wrapDegrees(cameraYaw - yawToCreeper);
            var pitchDiff = cameraPitch - pitchToCreeper;

            if (Math.abs(yawDiff) < fovScale && Math.abs(pitchDiff) < fovScale) {
                ci.cancel();
            }

//            var targetPos = player.getCameraPosVec(1.0f);
//            var creeperPos = creeper.getCameraPosVec(1.0f);
//            var distance = creeperPos.subtract(targetPos);
//            if (!(player.getRotationVec(1.0f).dotProduct(distance.normalize()) >= Math.cos(Math.toRadians(fovScale)))) {
//                ci.cancel();
//            }
        }
    }
}
