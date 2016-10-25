package xyz.rytix.roboTuptup.gui.interfaces;

import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;

public abstract class Component {
	/**
	 * A Gui que invocou os componentes
	 */
	protected final GuiBaseRoboTela GUI;
	protected int left;
	protected int top;
	
	
	public Component(GuiBaseRoboTela gui) {
		super();
		this.GUI = gui;
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
	
	public final int getTrueLeft(){
		return left + GUI.getGuiLeft();
	}
	
	public final int getTrueTop(){
		return top + GUI.getGuiTop();
	}
}
