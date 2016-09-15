package xyz.rytix.roboTuptup.proxy;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.rytix.roboTuptup.Config;
import xyz.rytix.roboTuptup.block.BlockRobo;
import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.helper.Initializer;
import xyz.rytix.roboTuptup.itemBlock.ItemBlockBaseRobo;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void preInit(FMLPreInitializationEvent event){
		super.preInit(event);
		Initializer.preInitAll();
		//Inits
		Initializer.registerBlocksAndItemBlocks();
		
	}
	
	@Override
	public void init(FMLInitializationEvent event){
		super.init(event);
		
		//RegisterRenders
		Initializer.registerBlocksAndItemBlocksRenders();
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event){
		super.postInit(event);
		
	}
}
