package xyz.rytix.roboTuptup.helper;

import java.util.ArrayList;
import java.util.List;

import xyz.rytix.roboTuptup.Config;
import xyz.rytix.roboTuptup.itemBlock.ItemBlockBaseRobo;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

public final class Initializer
{
	public static final List<ObjectHolder<IForgeRegistryEntry<?>>> entrys = new ArrayList();
	
	public static <K extends IForgeRegistryEntry<?>> void addToInitialization(K object,String registryName) throws Exception
	{
		for(ObjectHolder<IForgeRegistryEntry<?>> entryItem : entrys)
		{
			if(entryItem.OBJECT.equals(object))
				throw new Exception("Objeto de registro duplicado");
		}
		entrys.add(new ObjectHolder<IForgeRegistryEntry<?>>(object, registryName));
	}
	
	public static void initAll(){
		for(ObjectHolder<IForgeRegistryEntry<?>> entryItem : entrys)
		{
			GameRegistry.register(entryItem.OBJECT, new ResourceLocation(Config.MOD_ID, entryItem.REGISTRY_NAME));
		}	
	}
	
	public static void registerAllRenders (){
		for(ObjectHolder<IForgeRegistryEntry<?>> entryItem : entrys)
		{
			if(entryItem.OBJECT instanceof Item)
				Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register((Item) entryItem.OBJECT, 0, new ModelResourceLocation(Config.MOD_ID + ":" + entryItem.REGISTRY_NAME, "inventory"));
		}	
	}
	
	static class ObjectHolder<K extends IForgeRegistryEntry<?>>
	{
		public final K OBJECT;
		public final String REGISTRY_NAME;
		
		public ObjectHolder(K object, String registryName){
			OBJECT = object;
			REGISTRY_NAME = registryName;
		}
	}
}
