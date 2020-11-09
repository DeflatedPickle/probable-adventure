package com.deflatedpickle.pluckmin.common.entity.ai

import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.ai.EntityAIBase
import net.minecraft.entity.passive.EntityTameable

class EntityAIWalkWithOwner(
    private val entityIn: EntityTameable
) : EntityAIBase() {
    private lateinit var owner: EntityLivingBase

    override fun shouldExecute(): Boolean {
        val entityLivingBase = this.entityIn.owner

        return if (entityLivingBase != null) {
            owner = entityLivingBase
            true
        }
        else {
            false
        }
    }

    override fun updateTask() {
        if (this.entityIn.getDistanceSq(this.owner) > 3) {
            this.entityIn.moveRelative(
                this.owner.posX.toFloat() - this.entityIn.posX.toFloat(),
                this.owner.posY.toFloat() - this.entityIn.posY.toFloat(),
                this.owner.posZ.toFloat() - this.entityIn.posZ.toFloat(),
                0.2f
            )
        }
    }
}