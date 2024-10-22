package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.mixin;

import net.minecraft.client.MinecraftClient;
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

// https://github.com/JuaanP/CreeperBackguard/blob/main/src/main/java/com/juaanp/creeperbackguard/mixin/CreeperIgniteGoalMixinClient.java
@Mixin(CreeperIgniteGoal.class)
public class CreeperIgniteGoalMixinClient {
    @Shadow
    private LivingEntity target;

    @Shadow
    @Final
    private CreeperEntity creeper;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void creeperIgnitionGoal(CallbackInfo ci) {
        if (this.target instanceof PlayerEntity player) {
            var creeper = this.creeper;
            var minecraftClient = MinecraftClient.getInstance();
            var gameOptions = minecraftClient.options;

            // TODO: Add override fov
            var fovScale = 70d;
            if (gameOptions != null)
                fovScale = Math.min(gameOptions.getFov().getValue(), 80);

            // For now don't have any client config, then the mod not even made for client
            // CreeperBackguardConfig.igniteOnThirdPerson

            double cameraYaw = MathHelper.wrapDegrees(minecraftClient.gameRenderer.getCamera().getYaw() + 180.0);
            double cameraPitch = MathHelper.wrapDegrees(minecraftClient.gameRenderer.getCamera().getPitch());

            double deltaX = creeper.getX() - minecraftClient.gameRenderer.getCamera().getPos().x;
            double deltaY = creeper.getEyeY() - minecraftClient.gameRenderer.getCamera().getPos().y;
            double deltaZ = creeper.getZ() - minecraftClient.gameRenderer.getCamera().getPos().z;

            double yawToCreeper = MathHelper.atan2(deltaZ, deltaX) * (180.0 / Math.PI) - 90.0;
            double pitchToCreeper = -MathHelper.atan2(deltaY, MathHelper.sqrt((float) (deltaX * deltaX + deltaZ * deltaZ))) * (180.0 / Math.PI);

            double yawDiff = MathHelper.wrapDegrees(cameraYaw - yawToCreeper);
            double pitchDiff = cameraPitch - pitchToCreeper;

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
