package xyz.rytix.roboTuptup.proxy;

import xyz.rytix.roboTuptup.Config;
import xyz.rytix.roboTuptup.block.BlockRobo;
import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.itemBlock.ItemBlockBaseRobo;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event){
		GameRegistry.registerTileEntity(TileEntityBaseRobo.class, Config.MOD_ID + ":TileEntityBaseRobo");
	}
	
	public void init(FMLInitializationEvent event){
		
	}
	
	public void postInit(FMLPostInitializationEvent event){
		
	}
}
