package xyz.rytix.roboTuptup.gui;

import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class TuptupGuiHandler implements IGuiHandler{
	
	public static final int BASE_GUI = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == BASE_GUI)
			return new ContainerBaseRobo(player.inventory,(TileEntityBaseRobo) world.getTileEntity(new BlockPos(x,y,z))); // SUBCLASS CONTAINER
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == BASE_GUI)
			return new GuiBaseRoboTela(player.inventory, (TileEntityBaseRobo) world.getTileEntity(new BlockPos(x,y,z))); // SUBCLASS GUICONTAINER
		
		return null;
	}

}
