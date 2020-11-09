package com.deflatedpickle.pluckmin.common.init

import com.deflatedpickle.pluckmin.Pluckmin
import com.deflatedpickle.pluckmin.api.entity.mob.EntityPluckmin
import com.deflatedpickle.pluckmin.common.entity.mob.EntityRedPluckmin
import net.minecraft.entity.passive.EntityTameable
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.EntityRegistry
import kotlin.reflect.KClass

@Suppress("SpellCheckingInspection")
object EntityInit {
    private var id = 0

    init {
        register(
            "pluckmin_red",
            EntityRedPluckmin::class,
            Pluckmin.RED
        )
    }

    private fun register(name: String, clazz: KClass<out EntityPluckmin>, eggColour: Int) {
        EntityRegistry.registerModEntity(
            ResourceLocation("${Pluckmin.ID}:$name"),
            clazz.java, name, id++, Pluckmin,
            16, 1, false,
            Pluckmin.EGG_WHITE, eggColour
        )
    }
}