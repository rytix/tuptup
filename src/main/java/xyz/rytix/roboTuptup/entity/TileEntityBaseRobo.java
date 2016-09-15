package xyz.rytix.roboTuptup.entity;

import xyz.rytix.roboTuptup.helper.Initializer;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileEntityBaseRobo extends TileEntity{	
	boolean tem_robo = false;
	BlockPos pos_robo;
	
	public void onBlockActivated(BlockPos pos){
		if(!tem_robo){
			pos_robo = pos.up();
			worldObj.setBlockState(pos_robo, Initializer.BLOCK_ROBO.getDefaultState());
			tem_robo = true;
		}else{
			worldObj.setBlockToAir(pos_robo);
			pos_robo = pos_robo.north();
			worldObj.setBlockState(pos_robo, Initializer.BLOCK_ROBO.getDefaultState());
		}
		

	}
}
