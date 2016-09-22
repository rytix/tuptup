package xyz.rytix.roboTuptup.entity;

import xyz.rytix.roboTuptup.helper.Initializer;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityRobo extends TileEntity implements ITickable, IInventory{	
	private BlockPos base;
	
	public TileEntityRobo() {

	}
	
	public void setBase(BlockPos base) {
		this.base = base;
	}
	
	public BlockPos getBase() {
		return base;
	}
	
	///ITickable Interface
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	//IInventory Interface
	@Override
	public String getName() {
		return null;
	}
	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ItemStack getStackInSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ItemStack decrStackSize(int index, int count) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
