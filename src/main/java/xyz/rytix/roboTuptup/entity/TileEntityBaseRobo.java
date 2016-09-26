package xyz.rytix.roboTuptup.entity;

import xyz.rytix.roboTuptup.block.BlockRobo;
import xyz.rytix.roboTuptup.block.BlockRobo.Move;
import xyz.rytix.roboTuptup.helper.Initializer;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityBaseRobo extends TileEntity implements ITickable, IInventory{	
	private BlockPos pos_robo = null;
	
	public BlockPos getPos_robo() {
		return pos_robo;
	}
	public void onBlockPlacedBy(BlockPos pos){
		pos_robo = pos.up();
		worldObj.setBlockState(pos_robo, Initializer.BLOCK_ROBO.getDefaultState());
		((TileEntityRobo)worldObj.getTileEntity(pos_robo)).setBase(pos);
	}
	
	public void onBlockActivated(BlockPos pos){
		pos_robo = Initializer.BLOCK_ROBO.moveRobot(worldObj, pos_robo, Move.FRONT);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setIntArray("pos_robo", new int[]{pos_robo.getX(),pos_robo.getY(),pos_robo.getZ()});
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		int[] pos_robo = compound.getIntArray("pos_robo");
		if(pos_robo.length == 0){
			return;
		}
		this.pos_robo = new BlockPos(pos_robo[0],pos_robo[1],pos_robo[2]);
		super.readFromNBT(compound);
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
