package xyz.rytix.roboTuptup.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class TuptupGuiHandler implements IGuiHandler{
	
	public static final int BASE_GUI = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == BASE_GUI){
			return null; // SUBCLASS CONTAINER
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		if (ID == BASE_GUI){
			return null; // SUBCLASS GUICONTAINER
		}
		return null;
	}

}
