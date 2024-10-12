package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils.item

import eu.pb4.polymer.core.api.item.SimplePolymerItem
import net.minecraft.block.BlockState
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.explosion.Explosion
import net.minecraft.item.Items as MCItems

class HahItem() : SimplePolymerItem(
    Settings()
        .maxCount(1)
        .fireproof()
        .attributeModifiers(
            AttributeModifiersComponent.builder()
                .add(
                    EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    EntityAttributeModifier(
                        BASE_ATTACK_DAMAGE_MODIFIER_ID, 5.toDouble(), EntityAttributeModifier.Operation.ADD_VALUE
                    ),
                    AttributeModifierSlot.ANY
                )
                .add(
                    EntityAttributes.GENERIC_ATTACK_SPEED,
                    EntityAttributeModifier(
                        BASE_ATTACK_SPEED_MODIFIER_ID,
                        0.0,
                        EntityAttributeModifier.Operation.ADD_VALUE
                    ),
                    AttributeModifierSlot.ANY
                )
                .build()
        ), MCItems.BLAZE_ROD
) {
    var isChanged = false

    fun explosion(world: World, attacker: LivingEntity, target: LivingEntity, power: Float = 50f) {
        world.createExplosion(
            attacker,
            Explosion.createDamageSource(world, attacker),
            null,
            target.x,
            target.y,
            target.z,
            power,
            true,
            World.ExplosionSourceType.MOB
        )
    }

    fun explosion(world: World, attacker: LivingEntity, x: Double, y: Double, z: Double, power: Float = 50f) {
        world.createExplosion(
            attacker,
            Explosion.createDamageSource(world, attacker),
            null,
            x,
            y,
            z,
            power,
            true,
            World.ExplosionSourceType.MOB
        )
    }

    fun explosion(world: World, attacker: LivingEntity, x: Int, y: Int, z: Int, power: Float = 50f) {
        explosion(world, attacker, x.toDouble(), y.toDouble(), z.toDouble(), power)
    }

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        val mob = entity as LivingEntity

        if (!selected) {
            if (isChanged) {
                if (mob.noClip)
                    mob.noClip = false
                if (mob.isPlayer) {
                    val player = mob as PlayerEntity
                    if (player.abilities.allowFlying) {
                        player.abilities.flying = false
                        player.abilities.allowFlying = false
                    }
                }
            }
            return
        }

        if (!mob.noClip)
            mob.noClip = true
        if (mob.isPlayer) {
            val player = mob as PlayerEntity
            player.abilities.flying = true
            player.abilities.allowFlying = true
        }
        isChanged = true
        // lazy
        if (mob.health >= mob.maxHealth) return
        mob.health = mob.maxHealth
    }

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        if (context.player == null)
            return ActionResult.FAIL
        explosion(context.world, context.player!!, context.hitPos.x, context.hitPos.y, context.hitPos.z)
        return ActionResult.SUCCESS
    }

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        return super.use(world, user, hand)
    }

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        if (!attacker.isAlive)
            return super.postHit(stack, target, attacker)
        explosion(attacker.world, attacker, target)
        return true
    }

    override fun postMine(
        stack: ItemStack,
        world: World,
        state: BlockState,
        pos: BlockPos,
        miner: LivingEntity
    ): Boolean {
        if (!miner.isAlive)
            return super.postMine(stack, world, state, pos, miner)
        explosion(world, miner, pos.x, pos.y, pos.z)
        return true
    }

    override fun useOnEntity(
        stack: ItemStack,
        user: PlayerEntity,
        entity: LivingEntity,
        hand: Hand
    ): ActionResult {
        if (!entity.isAlive)
            return ActionResult.FAIL
        entity.kill()
        return ActionResult.SUCCESS
    }
}