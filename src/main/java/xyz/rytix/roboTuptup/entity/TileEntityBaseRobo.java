package xyz.rytix.roboTuptup.entity;

import xyz.rytix.roboTuptup.block.BlockRobo;
import xyz.rytix.roboTuptup.block.BlockRobo.Move;
import xyz.rytix.roboTuptup.helper.Initializer;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;

public class TileEntityBaseRobo extends TileEntity implements ITickable, IInventory{	
	private String customName;
	private BlockPos roboPos = null;
	private ItemStack[] roboItemStack = new ItemStack[36];
	public BlockPos getPos_robo() {
		return roboPos;
	}
	public void onBlockPlacedBy(BlockPos pos){
		roboPos = pos.up();
		worldObj.setBlockState(roboPos, Initializer.BLOCK_ROBO.getDefaultState());
		((TileEntityRobo)worldObj.getTileEntity(roboPos)).setBase(pos);
	}
	
	public void onBlockActivated(BlockPos pos){
		roboPos = Initializer.BLOCK_ROBO.moveRobot(worldObj, roboPos, Move.FRONT);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setIntArray("roboPos", new int[]{roboPos.getX(),roboPos.getY(),roboPos.getZ()});
		
		NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.roboItemStack.length; ++i)
        {
            if (this.roboItemStack[i] != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                this.roboItemStack[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }
        compound.setTag("Items", nbttaglist);
		
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		int[] pos_robo = compound.getIntArray("roboPos");
		if(pos_robo.length == 0){
			return;
		}
		
		NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.roboItemStack = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot");
            if (j >= 0 && j < this.roboItemStack.length)
            {
                this.roboItemStack[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }
		
		this.roboPos = new BlockPos(pos_robo[0],pos_robo[1],pos_robo[2]);
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
		return this.hasCustomName() ? this.customName : "container.baseRobo";
	}
	@Override
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}
	@Override
	public int getSizeInventory() {
		return this.roboItemStack.length;
	}
	@Override
	public ItemStack getStackInSlot(int index) {
		return this.roboItemStack[index];
	}
	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack itemstack = ItemStackHelper.getAndSplit(this.roboItemStack, index, count);
		if (itemstack != null)
        {
            this.markDirty();
        }
		return itemstack;
	}
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.roboItemStack, index);
	}
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.roboItemStack[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
		
	}
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	@Override
	public void openInventory(EntityPlayer player) {		
	}
	@Override
	public void closeInventory(EntityPlayer player) {		
	}
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}
	@Override
	public int getField(int id) {
		return 0;
	}
	@Override
	public void setField(int id, int value) {		
	}
	@Override
	public int getFieldCount() {
		return 0;
	}
	@Override
	public void clear() {
		for (int i = 0; i < this.roboItemStack.length; ++i)
        {
            this.roboItemStack[i] = null;
        }
	}
}
