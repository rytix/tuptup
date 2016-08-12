package xyz.rytix.roboTuptup.item;

import xyz.rytix.roboTuptup.Config;

import com.sun.org.apache.xml.internal.security.encryption.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBaseRobo {
	public static String REGISTRY_NAME = "baseRobo";
	public static Item baseRoboItem;
	
	public static void init(){
		baseRoboItem = registerItem(new Item(), REGISTRY_NAME).setUnlocalizedName(REGISTRY_NAME);
	}
	
	//Registro dos renders\\
	public static void registerRenders(){
		registerRender(baseRoboItem);
	}
	
	public static void registerRender(Item item){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Config.MOD_ID + ":" + REGISTRY_NAME, "inventory"));
	}
	//Fim Registro dos renders\\
	//Registro do Item\\
	public static Item registerItem(Item item, String name){
		return registerItem(item,name,null);
	}
	
	public static Item registerItem(Item item, String name, CreativeTabs tab){
		GameRegistry.register(item, new ResourceLocation(Config.MOD_ID, name));
		return item;
	}
	//Fim Registro Item\\
	
	
}
