package xyz.rytix.roboTuptup;

import xyz.rytix.roboTuptup.proxy.CommonProxy;
import xyz.rytix.roboTuptup.robo.Robo;
import net.minecraft.block.Block;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Config.MOD_ID, name = Config.MOD_NAME, version = Config.VERSION)
public class Tuptup
{    
	@SidedProxy(clientSide = Config.CLIENT_PROXY_CLASS, serverSide = Config.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	//Robo.init();
    	//Robo.register();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    	
    }
    
    
}
