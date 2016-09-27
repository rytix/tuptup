package xyz.rytix.roboTuptup.gui;

import org.lwjgl.opengl.GL11;

import com.sun.prism.paint.Color;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public class GuiBaseRoboTela extends GuiContainer{

	public GuiBaseRoboTela(Container inventorySlotsIn) {
		super(inventorySlotsIn);
		// TODO Auto-generated constructor stub
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
