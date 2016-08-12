package xyz.rytix.roboTuptup.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.rytix.roboTuptup.init.robo.Robo;
import xyz.rytix.roboTuptup.item.ItemBaseRobo;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void preInit(FMLPreInitializationEvent event){
		super.preInit(event);
		
		//Inits
		Robo.init();
    	ItemBaseRobo.init();
	}
	
	@Override
	public void init(FMLInitializationEvent event){
		super.init(event);
		
		//RegisterRenders
		Robo.registerRenders();
		ItemBaseRobo.registerRenders();
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event){
		super.postInit(event);
		
	}
}
