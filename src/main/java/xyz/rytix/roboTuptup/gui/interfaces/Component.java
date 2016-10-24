package xyz.rytix.roboTuptup.gui.interfaces;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;

public abstract class Component {
	/**
	 * A Gui que invocou os componentes
	 */
	protected final Gui GUI;
	
	
	
	public Component(Gui gui) {
		super();
		this.GUI = gui;
	}
	
	/**
	 * Função chamada sempre que o componente precisar ser desenhado na tela.
	 * @param tessellator Objeto que sabe desenhar dentro do jogo, utilizado normalmente com o VertexBuffer
	 * @param left 
	 * @param top
	 */
	public abstract void draw(Tessellator tessellator, int left, int top);
	/**
	 * Utilizada para detectar colisão entre o mouse e o componente
	 * @param mouseX
	 * @param mouseY
	 * @return verdadeiro se houve colisão, falso se não houve.
	 */
	public abstract boolean isMouseInside(int mouseX, int mouseY);
}
