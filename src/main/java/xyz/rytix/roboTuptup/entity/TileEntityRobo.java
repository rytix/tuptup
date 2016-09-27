package xyz.rytix.roboTuptup.entity;

import xyz.rytix.roboTuptup.helper.Initializer;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityRobo extends TileEntity implements ITickable{	
	private BlockPos base;
	
	public TileEntityRobo() {

	}
	
	public void setBase(BlockPos base) {
		this.base = base;
	}
	
	public BlockPos getBase() {
		return base;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setIntArray("base", new int[]{base.getX(),base.getY(),base.getZ()});
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		int[] base = compound.getIntArray("base");
		if(base.length == 0){
			return;
		}
		this.base = new BlockPos(base[0],base[1],base[2]);
		super.readFromNBT(compound);
	}
	
	///ITickable Interface
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
