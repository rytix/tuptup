package xyz.rytix.roboTuptup;

import org.apache.logging.log4j.Logger;

import xyz.rytix.roboTuptup.proxy.CommonProxy;
import xyz.rytix.roboTuptup.init.robo.Robo;
import xyz.rytix.roboTuptup.item.ItemBaseRobo;
import net.minecraft.block.Block;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
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
	
	@Instance
	public static Tuptup instance;
	
	public static Logger logger;
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	logger = event.getModLog();
    	proxy.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    	proxy.postInit(event);
    }
    
    
}
