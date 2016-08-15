package xyz.rytix.roboTuptup.itemBlock;

import xyz.rytix.roboTuptup.Config;
import xyz.rytix.roboTuptup.helper.Initializer;

import com.sun.org.apache.xml.internal.security.encryption.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBlockBaseRobo extends ItemBlock{
	public static final String REGISTRY_NAME = "baseRobo";
	
	public ItemBlockBaseRobo() {
		super(new Block(Material.CLOTH));
		try {
			Initializer.addToInitialization(getBlock(), REGISTRY_NAME);
			Initializer.addToInitialization(this, REGISTRY_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
