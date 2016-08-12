package xyz.rytix.roboTuptup.init.robo;

import xyz.rytix.roboTuptup.Config;
import xyz.rytix.roboTuptup.block.BlockRobo;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Robo{
	public static String REGISTRY_NAME = "robo";
	public static Block blockRobo;
	public static ItemBlock itemBlockRobo;
	public static void init(){
		blockRobo = registerBlock(new BlockRobo(Material.GROUND), REGISTRY_NAME).setUnlocalizedName(REGISTRY_NAME);
		itemBlockRobo = registerItemBlock(new ItemBlock(blockRobo)).setUnlocalizedName(REGISTRY_NAME);
	}
	
	//Registro dos renders\\
	public static void registerRenders(){
		registerRender(blockRobo);
	}
	
	public static void registerRender(Block block){
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Config.MOD_ID + ":" + REGISTRY_NAME, "inventory"));
	}
	//Fim Registro dos renders\\
	//Registro do ItemBock\\
	public static ItemBlock registerItemBlock(ItemBlock item){
		return registerItemBlock(item,null);
	}
	
	public static ItemBlock registerItemBlock(ItemBlock itemBlock, CreativeTabs tab){
		GameRegistry.register(itemBlock, new ResourceLocation(Config.MOD_ID, REGISTRY_NAME));
		return itemBlock;
	}
	//Fim Registro ItemBlock\\
	//Registro do Block\\
	public static Block registerBlock(Block block, String name){
		return registerBlock(block,name,null);
	}
	
	public static Block registerBlock(Block block, String name, CreativeTabs tab){
		GameRegistry.register(block, new ResourceLocation(Config.MOD_ID, name));
		return block;
	}
	//Fim Registro Block\\
}