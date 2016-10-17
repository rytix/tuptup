package xyz.rytix.roboTuptup.gui;

import java.io.IOException;

import javax.naming.LimitExceededException;
import javax.swing.JOptionPane;

import org.lwjgl.input.Mouse;
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

public class GuiBaseRoboTela extends GuiContainer {

	GuiButton act;
	
	public GuiBaseRoboTela(IInventory playerInv,
			TileEntityBaseRobo baseRoboEntity) {
		super(new ContainerBaseRobo(playerInv, baseRoboEntity));
		// TODO Auto-generated constructor stub

		this.xSize = 256;
		this.ySize = 242;
	}

	@Override
	public void initGui() {
		// this.buttonList.add(this.act = new GuiButton(0, this.width/2 -100,
		// this.height/2 - 24, "Button A"));
		super.initGui();
	}
	
	
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button == this.act) {

		}
		super.actionPerformed(button);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		// Bugged
		// sthis.drawRect(guiLeft,guiTop, guiLeft+87, guiTop+23, 0xFF990000);
	};

	private void drawThisThing(int left, int top, int right, int bottom, int color){
		ScaledResolution scaledResolution = new ScaledResolution(mc);	
		int scaledLeft = left * scaledResolution.getScaleFactor(); 
		int scaledTop = top * scaledResolution.getScaleFactor();
		int scaledRight = right * scaledResolution.getScaleFactor();
		int scaledBottom = bottom * scaledResolution.getScaleFactor();
				
	    Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        
        GlStateManager.pushMatrix();
        GlStateManager.pushAttrib();
        
        GlStateManager.enableBlend(); //Aceita transparencia
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(255/255.0F, 230/255.0F, 53/255.0F, 1.0F);
        
        limitarDesenhosAAreaDeBlocos();
        
        vertexbuffer.begin(GL11.GL_POLYGON, DefaultVertexFormats.POSITION);
        vertexbuffer.pos((double)left-50, (double)top, 0.0D).endVertex();
        vertexbuffer.pos((double)left + 50, (double)top + 50, 0.0D).endVertex();
        vertexbuffer.pos((double)left + 50, (double)top, 0.0D).endVertex();
        tessellator.draw();
        
        limitarDesenhosAAreaDeCodigo();
        
        this.drawRect(0,0, width, height, 0xFF990000);
        
        desativarLimite();
        
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
	}
	
	//Função de desenho
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks,
			int mouseX, int mouseY) {

		mc.renderEngine.bindTexture(new ResourceLocation(Config.MOD_ID,
				"textures/gui/baseRobo.png"));
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		this.drawThisThing(guiLeft, guiTop, xSize, ySize, 0xFF990000);

		//this.drawRect(guiLeft+1,guiTop+1, guiLeft + 16, guiTop + 16, 0xFF990000);
	}
	
	//Funções que limitam area de desenho
	private void desativarLimite(){
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
	}
	
	//Gambiarra para fazer as funções do OpenGL desenharem no lugar certo, enquanto não se acha como o minecraft as desenha.
	private int doDarkMagicToFixTheTop(int sr){
		//XXX
		if(sr == 1){
			if(mc.displayHeight % 2 == 0){
				return -1;
			}else{
				if(guiTop <= 0){
					return -2;
				}else{
					return 0;
				}
			}
		}else if (sr == 2){
			if(guiTop > 0){
				return - (3 - ((mc.displayHeight + 1) % 4));
			}else if(guiTop == 0){
				return - (486 - mc.displayHeight); 
			}else{
				return - 2;
			}
		}else{
			//SF = 3 (TVs) NÃO IMPLEMENTADO
			return 0;
		}
	}
	
	private void limitarDesenhosAAreaDeCodigo(){
		int sr = new ScaledResolution(mc).getScaleFactor();	
		int scaledLeft = (guiLeft + 103) * sr; 
		int scaledTop = (guiTop + 124) * sr + doDarkMagicToFixTheTop(sr);
		int scaledRight = 152 * sr;
		int scaledBottom = 118 * sr;
				
		GL11.glScissor(scaledLeft,scaledTop,scaledRight,scaledBottom);
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
	}
	
	private void limitarDesenhosAAreaDeBlocos(){
		int sr = new ScaledResolution(mc).getScaleFactor();	
		int scaledLeft = (guiLeft + 18) * sr; 
		int scaledTop = (guiTop + 124) * sr + doDarkMagicToFixTheTop(sr);
		int scaledRight = 84 * sr;
		int scaledBottom = 118 * sr;
				
		GL11.glScissor(scaledLeft,scaledTop,scaledRight,scaledBottom);
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
	}

}
