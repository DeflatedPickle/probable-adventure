package com.deflatedpickle.pluckmin.common.entity.mob

import com.deflatedpickle.pluckmin.api.entity.mob.EntityPluckmin
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumHand
import net.minecraft.util.EnumParticleTypes
import net.minecraft.world.World

/**
 * Red Pluckmin are immune to fire and more powerful than the others
 */
class EntityRedPluckmin(world: World) : EntityPluckmin(
    attackDamage = 2.0,
    world = world
) {
    init {
        this.isImmuneToFire = true
    }

    /**
     * If planted and grabbed with the off hand, we uproot it
     * If not planted and grabbed with the off hand, we pick it up
     */
    override fun processInteract(
        player: EntityPlayer,
        hand: EnumHand
    ) = when {
        // Uproot it
        this.dataManager.get(isPlanted) && hand == EnumHand.OFF_HAND -> {
            this.ownerId = player.uniqueID
            this.dataManager.set(isPlanted, false)

            player.world.spawnParticle(
                EnumParticleTypes.HEART,
                this.posX, this.posY + 0.6, this.posZ,
                0.0, 0.3, 0.0
            )

            true
        }
        // Pick it up
        !this.dataManager.get(isPlanted) && hand == EnumHand.OFF_HAND -> {
            this.setDead()

            player.world.spawnParticle(
                EnumParticleTypes.EXPLOSION_NORMAL,
                this.posX, this.posY + 0.6, this.posZ,
                0.0, 0.0, 0.0
            )

            true
        }
        else -> false
    }
}