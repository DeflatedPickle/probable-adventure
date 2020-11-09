package com.deflatedpickle.pluckmin

import com.deflatedpickle.pluckmin.api.Proxy
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Suppress("MemberVisibilityCanBePrivate")
@Mod(
    modid = Pluckmin.ID,
    name = Pluckmin.NAME,
    version = Pluckmin.VERSION,
    acceptedMinecraftVersions = Pluckmin.ACCEPTED_VERSIONS,
    dependencies = Pluckmin.DEPENDENCIES,
    modLanguageAdapter = Pluckmin.ADAPTER
)
object Pluckmin {
    const val ID = "pluckmin"
    const val NAME = "Pluckmin"
    const val VERSION = "1.12.2-0.0.0.0"
    const val ACCEPTED_VERSIONS = "[1.12.2]"
    const val DEPENDENCIES = "required-after:forgelin;"
    const val ADAPTER = "net.shadowfacts.forgelin.KotlinAdapter"

    const val CLIENT_PROXY = "com.deflatedpickle.pluckmin.client.ClientProxy"
    const val SERVER_PROXY = "com.deflatedpickle.pluckmin.client.ServerProxy"

    const val RED = 0xfa4226
    const val YELLOW = 0xf5d609
    const val BLUE = 0x5dbbf1

    const val EGG_WHITE = 0xe2e3e5

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
    var proxy: Proxy? = null

    val logger: Logger = LogManager.getLogger()

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) = proxy?.preInit(event)

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) = proxy?.init(event)

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) = proxy?.postInit(event)
}