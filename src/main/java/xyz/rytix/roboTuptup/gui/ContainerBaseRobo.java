package xyz.rytix.roboTuptup.gui;

import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBaseRobo extends Container{
	private TileEntityBaseRobo baseRoboEntity;
	
	private int generateSide(TileEntityBaseRobo baseRoboEntity, int x, int idStart){
        this.addSlotToContainer(new Slot(baseRoboEntity, idStart, x,125));
        idStart++;
        this.addSlotToContainer(new Slot(baseRoboEntity, idStart, x + 18,125));
        idStart++;
        
        for(int k = 0; k < 3; k++){
        	for(int l = 0; l < 2; l++){
        		this.addSlotToContainer(new Slot(baseRoboEntity, idStart, x + l*18,145 + k*18));
        		idStart++;
        	}
        }
        
        this.addSlotToContainer(new Slot(baseRoboEntity, idStart, x,201));
        idStart++;
        this.addSlotToContainer(new Slot(baseRoboEntity, idStart, x + 18,201));
        idStart++;
        
        this.addSlotToContainer(new Slot(baseRoboEntity, idStart, x,221));
        idStart++;
        this.addSlotToContainer(new Slot(baseRoboEntity, idStart, x + 18,221));
        idStart++;
        return idStart;
	}
	
	public ContainerBaseRobo(IInventory playerInv, TileEntityBaseRobo baseRoboEntity) {
		this.baseRoboEntity = baseRoboEntity;
		// TODO Auto-generated constructor stub
		 // Tile Entity, Slot 0 - 11 (Side Left)
	    int idRobot = generateSide(baseRoboEntity, 11, 0);
	    // Tile Entity, Slot 12 - 23 (Side Right)
	    idRobot = generateSide(baseRoboEntity, 213, idRobot);
	    // Tile Entity, Slot 24 - 41 (Up and Down)
	    for(int k = 125; k <= 221; k+=96){
	    	for(int l = 0; l < 9; l++){
	            this.addSlotToContainer(new Slot(baseRoboEntity, idRobot, 49 + l*18, k));
	            idRobot++;
	    	}
	    }
	    

	    // Player Inventory, Slot 9-35, Slot IDs 9-35
	    for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 9; ++x) {
	            this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 49 + x * 18, 145 + y * 18));
	        }
	    }

	    // Player Inventory, Slot 0-8, Slot IDs 36-44
	    for (int x = 0; x < 9; ++x) {
	        this.addSlotToContainer(new Slot(playerInv, x, 49 + x * 18, 201));
	    }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return this.baseRoboEntity.isUseableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack previous = null;
	    Slot slot = (Slot) this.inventorySlots.get(index);

	    if (slot != null && slot.getHasStack()) {
	        ItemStack current = slot.getStack();
	        previous = current.copy();

	        if (index < 42) {
	            // From TE Inventory to Player Inventory
	            if (!this.mergeItemStack(current, 42, 78, true))
	                return null;
	        } else {
	            // From Player Inventory to TE Inventory
	            if (!this.mergeItemStack(current, 0, 42, false))
	                return null;
	        }

	        if (current.stackSize == 0)
	            slot.putStack((ItemStack) null);
	        else
	            slot.onSlotChanged();

	        if (current.stackSize == previous.stackSize)
	            return null;
	        slot.onPickupFromSlot(playerIn, current);
	    }
	    return previous;
	}
	
	
	
}
