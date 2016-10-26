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
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.components.scratch.ScratchBloco;
import xyz.rytix.roboTuptup.gui.components.scratch.ScratchBlocoTest;
import xyz.rytix.roboTuptup.gui.interfaces.RightClickDraggable;

public class ScrollPanelComponent extends Component implements RightClickDraggable{
	public int oldXMouse;
	public int oldYMouse;
	public int xScroll;
	public int yScroll;
	
	public final int WIDTH;
	public final int HEIGHT;
		
	public final int SCROLL_WIDTH;
	public final int SCROLL_HEIGHT;	
	
	List<Component> scratchBlocos = new ArrayList(); // Vai para o robô
	
	public ScrollPanelComponent(int left, int top,
			int width, int height,
			int SCROLL_WIDTH, int SCROLL_HEIGHT, GuiBaseRoboTela gui) 
	{
		super(gui);
		
		this.left = left;
		this.top = top;
		
		this.WIDTH = width;
		this.HEIGHT = height;
		
		this.SCROLL_WIDTH = SCROLL_WIDTH;
		this.SCROLL_HEIGHT = SCROLL_HEIGHT;
		
		this.xScroll = 0;
		this.yScroll = 0;
		
		this.oldXMouse = 0;
		this.oldYMouse = 0;
				
		scratchBlocos.add(new ScratchBlocoTest(gui,this.left,this.top));
		scratchBlocos.add(new ScratchBlocoTest(gui,this.left,this.top+20));
	}
	
	@Override
	public boolean isMouseInside(int mouseX, int mouseY){
		int left = getTrueLeft();
		int top = getTrueTop();
		
		if(mouseX >= left && mouseX <= left + WIDTH && mouseY >= top && mouseY <= top + WIDTH)
			return true;
		return false;
	}
	
	@Override
	public void draggablePre(int mouseX, int mouseY) {
		oldXMouse = mouseX;
		oldYMouse = mouseY;
	}
	
	@Override
	public void draggableAction(int mouseX,int mouseY){
		processScroll(mouseX, mouseY);
	}
	
	@Override
	public void draggablePos(int mouseX, int mouseY) {};
	
	@Override
	public RightClickDraggable getDraggableObject(int mouseX, int mouseY) {
		for(Component sb: scratchBlocos){
			if(sb instanceof RightClickDraggable){
				RightClickDraggable sbrcd = (RightClickDraggable) sb;
				if(sb.isMouseInside(mouseX, mouseY)){
					sbrcd = sbrcd.getDraggableObject(mouseX, mouseY);
					sbrcd.draggablePre(mouseX, mouseY);
					scratchBlocos.remove(sbrcd);
					return sbrcd;
				}
			}
		}
		return this;
	}
	
	//*** Funções para desenhar na tela
	@Override
	public void draw(Tessellator tessellator){
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		
        ativarGLScissors();
		drawAreaBorders();
		removerLimite();
		
        ativarGLScissors();

		for(Component sb : scratchBlocos){
			sb.draw(tessellator);
		}
		
		removerLimite();
	}
	
	private void ativarGLScissors(){
		int sr = new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor();	
		int scaledLeft = getTrueLeft() * sr; 
		int scaledTop = (124 + GUI.getGuiTop()) * sr + doDarkMagicToFixTheTop(sr);
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
		int guiTopForDarkMagic = GUI.getGuiTop();
		
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
				getTrueLeft() + xScroll,
				getTrueTop() + yScroll,
				SCROLL_WIDTH,SCROLL_HEIGHT);
	}
	
	private void drawAreaBorders(int x, int y, int width, int height){
		int grossura = 3;

		GL11.glColor4f(255/255.0F, 1/255.0F, 1/255.0F, 1.0F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		Gui.drawRect(x, y, x+width, y+grossura, 0xFF990000);
		Gui.drawRect(x, y, x+grossura, y+height, 0xFF990000);
		Gui.drawRect(x+width-grossura, y, x+width, y+height, 0xFF990000);
		Gui.drawRect(x, y+height-grossura, x+width, y+height, 0xFF990000);
	}
	//***Fim funções desenhar na tela
	public void processScroll(int x,int y){
		int xScrollDiff = (x - this.oldXMouse)*2;
		int yScrollDiff = (y - this.oldYMouse)*2;
		
		int prevXScroll = xScroll + xScrollDiff;
		int prevYScroll = yScroll + yScrollDiff;
		if(prevXScroll > 0){
			xScrollDiff = 0;
		}else if(prevXScroll < -SCROLL_WIDTH + WIDTH){
			xScrollDiff = 0;
		}
		if(prevYScroll > 0){
			yScrollDiff = 0;
		}else if(prevYScroll < -SCROLL_HEIGHT + HEIGHT){
			yScrollDiff = 0;
		}
		
		this.xScroll += xScrollDiff;
		this.yScroll += yScrollDiff;
		
		for(Component component: scratchBlocos){
			component.left += xScrollDiff;
			component.top += yScrollDiff;
		}
		
		this.oldXMouse = x;
		this.oldYMouse = y;
	}

	@Override
	public void drop(RightClickDraggable rcd, int mouseX, int mouseY) {
		for(Component comp :scratchBlocos){
			if(comp instanceof RightClickDraggable && isMouseInside(mouseX, mouseY)){
				((RightClickDraggable)comp).drop(rcd, mouseX, mouseY);
				return;
			}
		}
		if(rcd instanceof Component)
			scratchBlocos.add((Component)rcd);
	}

	@Override
	public void floatingBeforeDrop(RightClickDraggable component, int mouseX,
			int mouseY) {
		if(isMouseInside(mouseX, mouseY)){
			for(Component comp :scratchBlocos){
				if(comp instanceof RightClickDraggable){
					((RightClickDraggable)comp).floatingBeforeDrop(component, mouseX, mouseY);
					return;
				}
			}
		}
	}
}
