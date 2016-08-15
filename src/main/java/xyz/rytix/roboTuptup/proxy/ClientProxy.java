package xyz.rytix.roboTuptup.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.rytix.roboTuptup.helper.Initializer;
import xyz.rytix.roboTuptup.itemBlock.ItemBlockBaseRobo;
import xyz.rytix.roboTuptup.itemBlock.ItemBlockRobo;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void preInit(FMLPreInitializationEvent event){
		super.preInit(event);
		
		//Inits
		Initializer.initAll();
	}
	
	@Override
	public void init(FMLInitializationEvent event){
		super.init(event);
		
		//RegisterRenders
		Initializer.registerAllRenders();
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event){
		super.postInit(event);
		
	}
}
