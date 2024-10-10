package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.mixin;

import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.impl.ClientConnectionEX;
import net.minecraft.network.ClientConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin implements ClientConnectionEX {
    @Unique
    private int polymerServerUtils$version = -1;

    @Override
    public void polymerServerUtils$setVersion(int version) {
        polymerServerUtils$version = version;
    }

    @Override
    public int polymerServerUtils$version() {
        return polymerServerUtils$version;
    }
}
