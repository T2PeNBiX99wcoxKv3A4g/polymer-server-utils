package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ForgingScreenHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ForgingScreenHandler.class)
public class ForgingScreenHandlerMixin {
    @Shadow
    @Final
    protected PlayerEntity player;
}
