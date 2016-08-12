package xyz.rytix.roboTuptup.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockRobo extends Block{
	
	public BlockRobo(Material materialIn) {
		super(materialIn);
		setCreativeTab(CreativeTabs.REDSTONE);
	}
	
}
