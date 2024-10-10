package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.mixin;

import io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.impl.ItemEX;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Item.class)
public class ItemMixin implements ItemEX {
    @Unique
    private boolean polymerServerUtils$isCuddlyItem;

    @Override
    public void polymerServerUtils$setIsCuddlyItem(boolean isCuddly) {
        polymerServerUtils$isCuddlyItem = isCuddly;
    }

    @Override
    public boolean polymerServerUtils$isCuddlyItem() {
        return polymerServerUtils$isCuddlyItem;
    }
}
