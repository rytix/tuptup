package xyz.rytix.roboTuptup.entity;

import xyz.rytix.roboTuptup.Tuptup;
import xyz.rytix.roboTuptup.block.BlockRobo;
import xyz.rytix.roboTuptup.block.BlockRobo.Move;
import xyz.rytix.roboTuptup.gui.TuptupGuiHandler;
import xyz.rytix.roboTuptup.gui.components.scratch.controlBlocks.ScratchBlocoInstrucoesInternas;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBloco;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBlocoInicio;
import xyz.rytix.roboTuptup.helper.Initializer;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;
import net.minecraft.world.World;

public class TileEntityBaseRobo extends TileEntity implements ITickable, IInventory{	
	private String customName;
	private BlockPos roboPos = null;
	private ItemStack[] roboItemStack = new ItemStack[42];
	
	public static final List<PseudoPacket> packets = new ArrayList<PseudoPacket>();
	
	private List<ScratchBloco> blocos = new ArrayList();
	private ScratchBloco atualExecBloco;
	
	private long milis = 0;
	
	public BlockPos getPos_robo() {
		return roboPos;
	}
	public void onBlockPlacedBy(BlockPos pos){
		roboPos = pos.up();
		worldObj.setBlockState(roboPos, Initializer.BLOCK_ROBO.getDefaultState());
		((TileEntityRobo)worldObj.getTileEntity(roboPos)).setBase(pos);
	}
	
	public void moveRobot(Move move){
		roboPos = Initializer.BLOCK_ROBO.moveRobot(worldObj, roboPos, move);
	}
	
	public boolean placeBlock(Move move){
		int lenght = roboItemStack.length;
		for(int i = 0; i < lenght; i++){
			if(roboItemStack[i] != null){
				Item item = roboItemStack[i].getItem();
				if(item instanceof ItemBlock){
					if(Initializer.BLOCK_ROBO.placeItemBlock(worldObj,(ItemBlock)item,roboPos,move)){
						roboItemStack[i].stackSize--;
						if(roboItemStack[i].stackSize <= 0){
							removeStackFromSlot(i);
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public void harvestFrom(Move move){
		ItemStack itemStack = new ItemStack(Initializer.BLOCK_ROBO.harvestBlockOn(worldObj, roboPos, move));
		autoAddInventory(itemStack);
	}
	
	public void onBlockActivated(EntityPlayer playerIn, World worldIn, BlockPos pos){
		if(worldIn.isRemote){
			return;
		}
		playerIn.openGui(Tuptup.instance, TuptupGuiHandler.BASE_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
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
		if(System.currentTimeMillis() > milis + 50 && !worldObj.isRemote){//&& atualExecBloco != null){
			BlockPos pos = this.getPos();
			for(PseudoPacket packet:packets){
				if(packet.x == pos.getX() && packet.y == pos.getY() && packet.z == pos.getZ()){
					blocos.clear();
					blocos.addAll(packet.blocos);
					packets.remove(packet);
					for(ScratchBloco bloco: blocos){
						if(bloco instanceof ScratchBlocoInicio){
							moveRobot(Move.TELEPORT);
							atualExecBloco = bloco;
							ScratchBloco aux = atualExecBloco;
							ScratchBloco aux2 = atualExecBloco;
							StringBuilder builder = new StringBuilder();
							
							while(aux!=null){
								
								builder.append(aux.toString());
								builder.append("\n");
								if(aux instanceof ScratchBlocoInstrucoesInternas){
									aux2=((ScratchBlocoInstrucoesInternas)aux).getProximoDentro();
									while(aux2!=null){
										builder.append(aux2.toString());
										builder.append("\n");
										aux2=aux2.getProximo();
									}
									builder.append("}");
									builder.append("\n");
								}
								aux = aux.getProximo();
								
							}
							builder.append("} \n}");
							System.out.println(builder.toString());
							//JOptionPane.showMessageDialog(null, builder.toString());
							break;
						}
					}
					break;
				}
			}

			if(atualExecBloco != null){
				atualExecBloco = atualExecBloco.action(this);
		        worldObj.playEvent(null, 1008, roboPos, 0);
			}
			milis = System.currentTimeMillis();
		}
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
	public void saveAndResetRobot(List<ScratchBloco> blocos) {
		packets.add(new PseudoPacket(pos.getX(), pos.getY(), pos.getZ(), blocos));
		this.blocos.clear();
		this.blocos.addAll(blocos);
		for(ScratchBloco bloco: this.blocos){
			if(bloco instanceof ScratchBlocoInicio){
				atualExecBloco = bloco;
				break;
			}
		}
	}
	public void autoAddInventory(ItemStack stack){
		if(stack.getItem() == null)
			return;
		int sizeLimit = getInventoryStackLimit();
		int fieldCount = roboItemStack.length;
		for(int i = 0; i < fieldCount; i++){
			if(roboItemStack[i] == null ){
				roboItemStack[i] = stack;
				return;
			}else if(roboItemStack[i].getItem() == stack.getItem()){
				int resto;
				if(roboItemStack[i].stackSize + stack.stackSize < sizeLimit){
					roboItemStack[i].stackSize += stack.stackSize;
					return;
				}else{
					resto = roboItemStack[i].stackSize + stack.stackSize - sizeLimit;
					roboItemStack[i].stackSize = sizeLimit;
					stack.stackSize = resto;
				}
			}
		}
	}
	
	public List<ScratchBloco> getBlocos() {
		return blocos;
	}
}
