package xyz.rytix.roboTuptup.gui.components;

import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.interfaces.IComponent;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;

public abstract class Component implements IComponent{
	/**
	 * A Gui que invocou os componentes
	 */
	protected GuiBaseRoboTela gui;
	private int left;
	private int top;
	public int width;
	public int height;
	
	public Component(GuiBaseRoboTela gui) {
		super();
		this.gui = gui;
	}
	
	/**
	 * Função chamada sempre que o componente precisar ser desenhado na tela.
	 * @param tessellator Objeto que sabe desenhar dentro do jogo, utilizado normalmente com o VertexBuffer
	 * @param left 
	 * @param top
	 */
	public abstract void draw(Tessellator tessellator);
	/**
	 * Utilizada para detectar colisão entre o mouse e o componente
	 * @param mouseX
	 * @param mouseY
	 * @return verdadeiro se houve colisão, falso se não houve.
	 */
	public abstract boolean isMouseInside(int mouseX, int mouseY);
	
	@Override
	public int getLeft() {
		return left;
	}
	
	@Override
	public int getTop() {
		return top;
	}
	
	@Override
	public int getWidth(){
		return width;
	}
	
	@Override
	public int getHeight(){
		return height;
	}
	
	@Override
	public final int getTrueLeft(){
		return left + gui.getGuiLeft();
	}
	
	@Override
	public final int getTrueTop(){
		return top + gui.getGuiTop();
	}
	
	public void setLeft(int left) {
		this.left = left;
	}
	
	public void setTop(int top) {
		this.top = top;
	}
	@Override
	public void setGui(GuiBaseRoboTela gui) {
		this.gui = gui;
	}
	
}
