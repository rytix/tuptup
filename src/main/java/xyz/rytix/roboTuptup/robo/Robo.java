package xyz.rytix.roboTuptup.robo;

import xyz.rytix.roboTuptup.Config;
import xyz.rytix.roboTuptup.blocks.BlocoRobo;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Robo{
	public static String UNLOC_NAME = "blocoRobo";
	public static Block blocoRobo;
	
	public static void init(){
		blocoRobo = new BlocoRobo(Material.GROUND).setUnlocalizedName(UNLOC_NAME);
	}
	
	public static void register(){
		blocoRobo.setRegistryName(UNLOC_NAME);
		GameRegistry.register(blocoRobo);
		GameRegistry.register(new ItemBlock(blocoRobo).setRegistryName(blocoRobo.getRegistryName()));
	}
	
	public static void registerRenders(){
		registerRender(blocoRobo);
	}
	
	public static void registerRender(Block block){
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Config.MOD_ID + ":" + UNLOC_NAME, "inventory"));
	}
	public Robo() {
		
	}
}