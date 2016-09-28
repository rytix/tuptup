package xyz.rytix.roboTuptup.gui;

import org.lwjgl.opengl.GL11;

import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;

import com.sun.prism.paint.Color;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class GuiBaseRoboTela extends GuiContainer{

	public GuiBaseRoboTela(IInventory playerInv, TileEntityBaseRobo baseRoboEntity) {
		super(new ContainerBaseRobo(playerInv, baseRoboEntity));
		// TODO Auto-generated constructor stub
		this.xSize = 176;
		this.ySize = 166;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		
		
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks,
			int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawRect(k, l, 50, 50, 16776960);
	}
	
	
}
