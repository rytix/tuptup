package xyz.rytix.roboTuptup.block;

import xyz.rytix.roboTuptup.helper.Initializer;
import akka.io.Tcp.Register;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockBaseRobo extends Block{

	public BlockBaseRobo(Material materialIn) {
		super(materialIn);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			ItemStack heldItem, EnumFacing side, float hitX, float hitY,
			float hitZ) {
		
		//this.breakBlock(worldIn, pos, state);
		worldIn.destroyBlock(pos, false);
		playerIn.jump();
		// TODO Auto-generated method stub
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem,
				side, hitX, hitY, hitZ);
	}
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer) {
		worldIn.setBlockState(pos.up(), Initializer.BLOCK_ROBO.getDefaultState());
		return super
				.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
	}
}
