package xyz.rytix.roboTuptup.gui.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import xyz.rytix.roboTuptup.gui.components.scratch.ScratchBloco;
import xyz.rytix.roboTuptup.gui.components.scratch.ScratchBlocoTest;
import xyz.rytix.roboTuptup.gui.interfaces.Component;
import xyz.rytix.roboTuptup.gui.interfaces.RightClickDraggable;

public class ScrollPanelComponent extends Component implements RightClickDraggable{
	public int oldXMouse;
	public int oldYMouse;
	public int xScroll;
	public int yScroll;
	public boolean scrollingOnArea = false;

	public final int RELATIVE_LEFT;
	public final int RELATIVE_RIGHT;
	public final int RELATIVE_TOP;
	public final int RELATIVE_OPEN_GL_SCISSOR_TOP;
	public final int RELATIVE_BOTTOM;
	
	public final int WIDTH;
	public final int HEIGHT;
	
	public int left;
	public int right;
	public int top;
	public int guiTopForDarkMagic;
	public int openGLScissorTop;
	public int bottom;
	
	public final int SCROLL_WIDTH;
	public final int SCROLL_HEIGHT;	
	
	List<ScratchBloco> scratchBlocos = new ArrayList(); // Vai para o robô
	
	public ScrollPanelComponent(int relativeLeft, int relativeTop,
			int openGlScissorTop, int width, int height,
			int SCROLL_WIDTH, int SCROLL_HEIGHT, int guiLeft,
			int guiTop, Gui gui) 
	{
		super(gui);
		scratchBlocos.add(new ScratchBlocoTest(gui));
		
		this.RELATIVE_LEFT = relativeLeft;
		this.RELATIVE_RIGHT = relativeLeft+width;
		this.RELATIVE_TOP = relativeTop;
		this.RELATIVE_OPEN_GL_SCISSOR_TOP = openGlScissorTop;
		this.RELATIVE_BOTTOM = relativeTop+height;
		
		this.WIDTH = width;
		this.HEIGHT = height;
		
		this.SCROLL_WIDTH = SCROLL_WIDTH;
		this.SCROLL_HEIGHT = SCROLL_HEIGHT;
		
		this.xScroll = 0;
		this.yScroll = 0;
		this.scrollingOnArea = false;
		
		this.oldXMouse = 0;
		this.oldYMouse = 0;
		
		refreshGuiPosition(guiLeft, guiTop);
	}
	
	public void refreshGuiPosition(int guiLeft, int guiTop){
		left = RELATIVE_LEFT + guiLeft;
		right = RELATIVE_RIGHT + guiLeft;
		top =  RELATIVE_TOP + guiTop;
		openGLScissorTop = RELATIVE_OPEN_GL_SCISSOR_TOP + guiTop;
		bottom = RELATIVE_BOTTOM + guiTop;
		
		guiTopForDarkMagic = guiTop;
	}
	
	@Override
	public boolean isMouseInside(int mouseX, int mouseY){
		if(mouseX >= left && mouseX <= right && mouseY >= top && mouseY <= bottom)
			return true;
		return false;
	}
	
	@Override
	public RightClickDraggable getDraggableComponent() {
		return this;
	}
	
	@Override
	public void draggableInit(int mouseX, int mouseY) {
		oldXMouse = mouseX;
		oldYMouse = mouseY;
	}
	
	@Override
	public void draggableAction(int mouseX,int mouseY){
		//TODO if em nenhum outro componente dentro
		processScroll(mouseX, mouseY);
	}
	
	//*** Funções para desenhar na tela
	@Override
	public void draw(Tessellator tessellator, int guiLeft, int guiTop){
		refreshGuiPosition(guiLeft, guiTop);
		
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		
        ativarGLScissors();
		drawAreaBorders();
		removerLimite();
		
        ativarGLScissors();

		for(ScratchBloco sb : scratchBlocos){
			sb.draw(tessellator, left+xScroll, top+yScroll);
		}
		
		removerLimite();
	}
	
	private void ativarGLScissors(){
		int sr = new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor();	
		int scaledLeft = left * sr; 
		int scaledTop = openGLScissorTop * sr + doDarkMagicToFixTheTop(sr);
		int scaledWidth = WIDTH * sr;
		int scaledHeight = HEIGHT * sr;
		
		GL11.glScissor(scaledLeft,scaledTop,scaledWidth,scaledHeight);
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
	}
	
	//Funções que limitam area de desenho
	private void removerLimite(){
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
	}
	
	//Gambiarra para fazer as funções do OpenGL desenharem no lugar certo, enquanto não se acha como o minecraft as desenha.
	private int doDarkMagicToFixTheTop(int sr){
		//XXX
		Minecraft mc = Minecraft.getMinecraft();
		if(sr == 1){
			if(mc.displayHeight % 2 == 0){
				return -1;
			}else{
				if(guiTopForDarkMagic <= 0){
					return -2;
				}else{
					return 0;
				}
			}
		}else if (sr == 2){
			if(guiTopForDarkMagic > 0){
				return - (3 - ((mc.displayHeight + 1) % 4));
			}else if(guiTopForDarkMagic == 0){
				return - (486 - mc.displayHeight); 
			}else{
				return - 2;
			}
		}else{
			//SR = 3 (TVs) NÃO IMPLEMENTADO
			return 0;
		}
	}
	private void drawAreaBorders(){
		drawAreaBorders(
				left + xScroll,
				top + yScroll,
				SCROLL_WIDTH,SCROLL_HEIGHT);
	}
	
	private void drawAreaBorders(int x, int y, int width, int height){
		int grossura = 3;
		Gui.drawRect(0, 0, 0, 0, 0xFF990000); // FIXME OPENGL RESET FUNCTION GAMBI
		Gui.drawRect(x, y, x+width, y+grossura, 0xFF990000);
		Gui.drawRect(x, y, x+grossura, y+height, 0xFF990000);
		Gui.drawRect(x+width-grossura, y, x+width, y+height, 0xFF990000);
		Gui.drawRect(x, y+height-grossura, x+width, y+height, 0xFF990000);
	}
	//***Fim funções desenhar na tela
	public void processScroll(int x,int y){
		this.xScroll += (x - this.oldXMouse)*2;
		this.yScroll += (y - this.oldYMouse)*2;
		
		if(xScroll > 0){
			xScroll = 0;
		}else if(xScroll < -SCROLL_WIDTH + WIDTH){
			xScroll = -SCROLL_WIDTH + WIDTH;
		}
		if(yScroll > 0){
			yScroll = 0;
		}else if(yScroll < -SCROLL_HEIGHT + HEIGHT){
			yScroll = -SCROLL_HEIGHT + HEIGHT;
		}
		
		this.oldXMouse = x;
		this.oldYMouse = y;
	}
}
