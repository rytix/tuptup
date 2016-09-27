package xyz.rytix.roboTuptup.proxy;

import xyz.rytix.roboTuptup.Config;
import xyz.rytix.roboTuptup.Tuptup;
import xyz.rytix.roboTuptup.block.BlockRobo;
import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.entity.TileEntityRobo;
import xyz.rytix.roboTuptup.gui.TuptupGuiHandler;
import xyz.rytix.roboTuptup.itemBlock.ItemBlockBaseRobo;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event){
		GameRegistry.registerTileEntity(TileEntityBaseRobo.class, Config.MOD_ID + ":TileEntityBaseRobo");
		GameRegistry.registerTileEntity(TileEntityRobo.class, Config.MOD_ID + ":TileEntityRobo");
	}
	
	public void init(FMLInitializationEvent event){
		NetworkRegistry.INSTANCE.registerGuiHandler(Tuptup.instance, new TuptupGuiHandler());
	}
	
	public void postInit(FMLPostInitializationEvent event){
		
	}
}
