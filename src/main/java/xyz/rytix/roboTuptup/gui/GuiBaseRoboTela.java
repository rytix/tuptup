package xyz.rytix.roboTuptup.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.naming.LimitExceededException;
import javax.swing.JOptionPane;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import sun.java2d.loops.DrawRect;
import xyz.rytix.roboTuptup.Config;
import xyz.rytix.roboTuptup.Tuptup;
import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.gui.helpers.CodeAreaHelper;
import xyz.rytix.roboTuptup.gui.helpers.MouseGlobalHelper;
import xyz.rytix.roboTuptup.gui.model.ScratchBloco;
import xyz.rytix.roboTuptup.gui.model.ScratchBlocoTest;

import com.sun.prism.paint.Color;

import net.java.games.input.Keyboard;
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

	private int[] baseCoordsAndSizeBlockArea = {18,124,84,118};
	CodeAreaHelper codeAreaHelper = new CodeAreaHelper(Mouse.getX(), Mouse.getY());
	
	List<ScratchBloco> scratchBlocos = new ArrayList();
	
	public GuiBaseRoboTela(IInventory playerInv,
			TileEntityBaseRobo baseRoboEntity) {
		super(new ContainerBaseRobo(playerInv, baseRoboEntity));
		
		scratchBlocos.add(new ScratchBlocoTest());
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

		super.actionPerformed(button);
	}

	private void drawThisThing(int left, int top, int right, int bottom, int color){
	
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
        
        removerLimite();
        
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
		this.drawCodeArea(mouseX, mouseY);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}
	
	private void drawCodeAreaBorders(){
		int xDrawningBase = guiLeft+CodeAreaHelper.baseCoordsAndSizeCodeArea[0]+codeAreaHelper.xScroll;
		int yDrawningBase = guiTop+1+codeAreaHelper.yScroll;
		int largura = codeAreaHelper.LARGURA;
		int altura = codeAreaHelper.ALTURA;
		int grossura = codeAreaHelper.GROSSURA;
		
		limitarDesenhosAAreaDeCodigo();
		drawRect(xDrawningBase, yDrawningBase, xDrawningBase+largura, yDrawningBase+grossura, 0xFF990000);
		drawRect(xDrawningBase, yDrawningBase, xDrawningBase+grossura, yDrawningBase+altura, 0xFF990000);
		drawRect(xDrawningBase+largura-grossura, yDrawningBase, xDrawningBase+largura, yDrawningBase+altura, 0xFF990000);
		drawRect(xDrawningBase, yDrawningBase+altura-grossura, xDrawningBase+largura, yDrawningBase+altura, 0xFF990000);
		removerLimite();
	}
	
	private void drawCodeArea(int mouseX, int mouseY){
		if(!Mouse.isButtonDown(0))
			MouseGlobalHelper.falsificate();
		if(Mouse.isButtonDown(0) && !MouseGlobalHelper.holding){
			MouseGlobalHelper.holding = true;
			if(MouseGlobalHelper.holding && mouseX >= guiLeft + CodeAreaHelper.baseCoordsAndSizeCodeArea[0]
				&& mouseX <= guiLeft + CodeAreaHelper.baseCoordsAndSizeCodeArea[0] + CodeAreaHelper.baseCoordsAndSizeCodeArea[2]
				&& mouseY >= guiTop + 1
				&& mouseY <= guiTop + 1 + CodeAreaHelper.baseCoordsAndSizeCodeArea[3]){
				MouseGlobalHelper.scrollingOnCodeArea = true;
			}
		}
		codeAreaHelper.processScroll(mouseX, mouseY, MouseGlobalHelper.scrollingOnCodeArea);
		
		Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        
		drawCodeAreaBorders();

        GlStateManager.pushMatrix();
        GlStateManager.pushAttrib();
        
        GlStateManager.enableBlend(); //Aceita transparencia
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

        limitarDesenhosAAreaDeCodigo();

		for(ScratchBloco sb : scratchBlocos){
			Stack<int[]> stackOfDrawPoints = new Stack();
			sb.fillWithDrawPoints(stackOfDrawPoints);
			
			while(!stackOfDrawPoints.isEmpty()){
				int[] drawPoints = stackOfDrawPoints.pop();
				GlStateManager.color(241/255.0F, 80/255.0F, 132/255.0F, 1.0F);
				vertexbuffer.begin(GL11.GL_POLYGON, DefaultVertexFormats.POSITION);
				int size = drawPoints.length;
				for(int i = 1; i < size; i+=2)
					vertexbuffer.pos((double)drawPoints[i]+guiLeft+CodeAreaHelper.baseCoordsAndSizeCodeArea[0]+codeAreaHelper.xScroll, (double)drawPoints[i+1]+guiTop+1+codeAreaHelper.yScroll, 0.0D).endVertex();
		        tessellator.draw();
			}
		}
		
		removerLimite();
		
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
	}
	
	//Funções que limitam area de desenho
	private void removerLimite(){
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
			//SR = 3 (TVs) NÃO IMPLEMENTADO
			return 0;
		}
	}
	
	private void limitarDesenhosAAreaDeCodigo(){
		int sr = new ScaledResolution(mc).getScaleFactor();	
		int scaledLeft = (guiLeft + CodeAreaHelper.baseCoordsAndSizeCodeArea[0]) * sr; 
		int scaledTop = (guiTop + CodeAreaHelper.baseCoordsAndSizeCodeArea[1]) * sr + doDarkMagicToFixTheTop(sr);
		int scaledRight = CodeAreaHelper.baseCoordsAndSizeCodeArea[2] * sr;
		int scaledBottom = CodeAreaHelper.baseCoordsAndSizeCodeArea[3] * sr;
				
		GL11.glScissor(scaledLeft,scaledTop,scaledRight,scaledBottom);
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
	}
	
	private void limitarDesenhosAAreaDeBlocos(){
		int sr = new ScaledResolution(mc).getScaleFactor();	
		int scaledLeft = (guiLeft + baseCoordsAndSizeBlockArea[0]) * sr; 
		int scaledTop = (guiTop + baseCoordsAndSizeBlockArea[1]) * sr + doDarkMagicToFixTheTop(sr);
		int scaledRight = baseCoordsAndSizeBlockArea[2] * sr;
		int scaledBottom = baseCoordsAndSizeBlockArea[3] * sr;
				
		GL11.glScissor(scaledLeft,scaledTop,scaledRight,scaledBottom);
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
	}

}
