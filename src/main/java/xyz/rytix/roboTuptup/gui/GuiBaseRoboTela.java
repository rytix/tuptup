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
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.GuiScrollingList;

public class GuiBaseRoboTela extends GuiContainer{
	
	GuiButton act;
	GuiScrollingList scrolling;

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
	        
	        ScaledResolution scaledResolution = new ScaledResolution(mc);
	        GL11.glScissor(
	        		left * scaledResolution.getScaleFactor(), 
	        		top * scaledResolution.getScaleFactor(), 
	        		right * scaledResolution.getScaleFactor(), 
	        		bottom * scaledResolution.getScaleFactor());
	        
	        GL11.glEnable(GL11.GL_SCISSOR_TEST);
	        vertexbuffer.begin(GL11.GL_POLYGON, DefaultVertexFormats.POSITION);
	        vertexbuffer.pos((double)left, (double)bottom, 0.0D).endVertex();
	        vertexbuffer.pos((double)right, (double)bottom, 0.0D).endVertex();
	        vertexbuffer.pos((double)right, (double)top, 0.0D).endVertex();
	        tessellator.draw();
	        
	        this.drawRect(0,0, width, height, 0xFF990000);
	        
	        GL11.glDisable(GL11.GL_SCISSOR_TEST);
	        GlStateManager.popAttrib();
	        GlStateManager.popMatrix();
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks,
			int mouseX, int mouseY) {
		
		mc.renderEngine.bindTexture(new ResourceLocation(Config.MOD_ID,"textures/gui/baseRobo.png"));

		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		this.drawThisThing(guiLeft,guiTop, xSize, ySize, 0xFF990000);

		//this.drawRect(guiLeft,guiTop, xSize, ySize, 0xFF990000);
	}
		
	
}
