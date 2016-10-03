package xyz.rytix.roboTuptup.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import xyz.rytix.roboTuptup.Config;
import xyz.rytix.roboTuptup.Tuptup;
import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;

import com.sun.prism.paint.Color;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiBaseRoboTela extends GuiContainer{
	
	GuiButton act;
	
	public GuiBaseRoboTela(IInventory playerInv, TileEntityBaseRobo baseRoboEntity) {
		super(new ContainerBaseRobo(playerInv, baseRoboEntity));
		// TODO Auto-generated constructor stub

		this.xSize = 256;
		this.ySize = 242;
	}
	
	@Override
	public void initGui() {
		//this.buttonList.add(this.act = new GuiButton(0, this.width/2 -100, this.height/2 - 24, "Button A"));
		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button == this.act){
			
		}
		super.actionPerformed(button);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		//drawDefaultBackground();
		//drawGradientRect(20, 20, width-20, height - 20, 0x60bb0000, 0xa077055);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks,
			int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		//drawCenteredString(fontRendererObj, "BLKA", width/2, 45, 0x440011);

		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		
		mc.renderEngine.bindTexture(new ResourceLocation(Config.MOD_ID,"textures/gui/baseRobo.png"));
		this.drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
		
	
}
