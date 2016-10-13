package xyz.rytix.roboTuptup.gui;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.lwjgl.opengl.GL11;

import sun.java2d.loops.DrawRect;
import xyz.rytix.roboTuptup.Config;
import xyz.rytix.roboTuptup.Tuptup;
import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;

import com.sun.prism.paint.Color;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
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
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		//Bugged
		//sthis.drawRect(guiLeft,guiTop, guiLeft+87, guiTop+23, 0xFF990000);
	};
	
	private void drawThisThing(int left, int top, int right, int bottom, int color){
		    Tessellator tessellator = Tessellator.getInstance();
	        VertexBuffer vertexbuffer = tessellator.getBuffer();
	        
	        GlStateManager.pushMatrix();
	        GlStateManager.pushAttrib();
	        
	        GlStateManager.enableBlend(); //Aceita transparencia
	        GlStateManager.disableTexture2D();
	        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	        GlStateManager.color(255/255.0F, 230/255.0F, 53/255.0F, 1.0F);
	        
	        //GlStateManager.matrixMode(GL11.GL_PROJECTION);
	        GlStateManager.viewport(0, 0, width, height);
	        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
	        
	        vertexbuffer.begin(GL11.GL_POLYGON, DefaultVertexFormats.POSITION);
	        vertexbuffer.pos((double)left, (double)bottom, 0.0D).endVertex();
	        vertexbuffer.pos((double)right, (double)bottom, 0.0D).endVertex();
	        vertexbuffer.pos((double)right, (double)top, 0.0D).endVertex();
	        
	        tessellator.draw();
	        
	        GlStateManager.popAttrib();
	        GlStateManager.popMatrix();
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks,
			int mouseX, int mouseY) {
		
		mc.renderEngine.bindTexture(new ResourceLocation(Config.MOD_ID,"textures/gui/baseRobo.png"));
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		//this.drawRect(guiLeft,guiTop, guiLeft+87, guiTop+23, 0xFF990000);
		this.drawThisThing(guiLeft,guiTop, guiLeft+87, guiTop+23, 0xFF990000);
	}
		
	
}
