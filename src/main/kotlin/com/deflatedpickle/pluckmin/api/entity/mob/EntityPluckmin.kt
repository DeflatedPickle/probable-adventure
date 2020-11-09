package com.deflatedpickle.pluckmin.api.entity.mob

import com.deflatedpickle.pluckmin.common.entity.ai.EntityAIWalkWithOwner
import net.minecraft.block.material.EnumPushReaction
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityAgeable
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.ai.*
import net.minecraft.entity.passive.EntityAnimal
import net.minecraft.entity.passive.EntityTameable
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.network.datasync.DataParameter
import net.minecraft.network.datasync.DataSerializers
import net.minecraft.network.datasync.EntityDataManager
import net.minecraft.world.World

abstract class EntityPluckmin(
    private val maxHealth: Double = 2.0,
    private val followRange: Double = 10.0,
    private val movementSpeed: Double = 1.0,
    private val attackDamage: Double = 1.0,
    private val attackSpeed: Double = 1.0,
    private val armour: Double = 0.0,
    private val armourToughness: Double = 0.0,
    world: World
) : EntityTameable(world) {
    companion object {
        val isPlanted: DataParameter<Boolean> = EntityDataManager.createKey(
            EntityPluckmin::class.java,
            DataSerializers.BOOLEAN
        )
    }

    init {
        setSize(1f, 1f)
        enablePersistence()
    }

    override fun entityInit() {
        super.entityInit()

        this.dataManager.register(isPlanted, true)
    }

    override fun initEntityAI() {
        tasks.addTask(1, EntityAIWalkWithOwner(this))

        tasks.addTask(8, EntityAIWatchClosest(this, EntityPlayer::class.java, followRange.toFloat()))
        tasks.addTask(8, EntityAILookIdle(this))

        targetTasks.addTask(1, EntityAIHurtByTarget(this, true, *arrayOfNulls(0)))
    }

    override fun applyEntityAttributes() {
        super.applyEntityAttributes()
        this.attributeMap.registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE)
        this.attributeMap.registerAttribute(SharedMonsterAttributes.ATTACK_SPEED)

        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).baseValue = this.maxHealth
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).baseValue = this.followRange
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).baseValue = this.movementSpeed
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).baseValue = this.attackDamage
        getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).baseValue = this.attackSpeed
        getEntityAttribute(SharedMonsterAttributes.ARMOR).baseValue = this.armour
        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).baseValue = this.armourToughness
    }

    // These make it so we can collide with it, but not push it
    override fun canBePushed() = !this.dataManager.get(isPlanted)
    override fun getPushReaction(): EnumPushReaction = EnumPushReaction.IGNORE
    override fun isBeingRidden() = true

    override fun canDropLoot() = false
    override fun canBeLeashedTo(player: EntityPlayer) = false
    override fun canFitPassenger(passenger: Entity) = false
    override fun canPickUpLoot() = false

    override fun isBreedingItem(stack: ItemStack) = false
    override fun canMateWith(otherAnimal: EntityAnimal) = false
    override fun createChild(ageable: EntityAgeable): EntityAgeable? = null
}