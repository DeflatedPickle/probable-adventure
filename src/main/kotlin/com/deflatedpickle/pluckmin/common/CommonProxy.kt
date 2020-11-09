package com.deflatedpickle.pluckmin.common

import com.deflatedpickle.pluckmin.api.Proxy
import com.deflatedpickle.pluckmin.common.init.EntityInit
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

open class CommonProxy : Proxy {
    override fun preInit(event: FMLPreInitializationEvent) {
    }

    override fun init(event: FMLInitializationEvent) {
        EntityInit
    }

    override fun postInit(event: FMLPostInitializationEvent) {
    }
}