package com.deflatedpickle.pluckmin.api

import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

interface Proxy {
    fun preInit(event: FMLPreInitializationEvent)
    fun init(event: FMLInitializationEvent)
    fun postInit(event: FMLPostInitializationEvent)
}