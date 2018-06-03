package me.novaskin.tutorial

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.util.*

const val modid = "tutorial"

@Mod(modid = modid, name = "Tutorial", version = "0.0", modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter")
internal object GradleStart

val tutItem = object : Item() {
    init {
        unlocalizedName = "test"
        registryName = ResourceLocation(modid, "test")
        creativeTab = CreativeTabs.MISC
    }
}

val tutBlock = object : Block(Material.ROCK) {
    init {
        unlocalizedName = "test_block"
        registryName = ResourceLocation(modid, "test_block")
        setCreativeTab(CreativeTabs.MISC)
    }

    override fun getItemDropped(state: IBlockState?, rand: Random?, fortune: Int): Item {
        return tutItem
    }
}

val tutItemBlock = ItemBlock(tutBlock)

@Mod.EventBusSubscriber(modid = modid)
object RegistryHandler {
    @JvmStatic
    @SubscribeEvent
    fun onItemRegister(event: RegistryEvent.Register<Item>) {
        tutItemBlock.registryName = tutBlock.registryName
        event.registry.registerAll(tutItem, tutItemBlock)
    }

    @JvmStatic
    @SubscribeEvent
    fun onBlockRegister(event: RegistryEvent.Register<Block>) {
        event.registry.register(tutBlock)
    }
}