package xyz.rytix.roboTuptup.gui.interfaces;

import java.util.List;

import net.minecraft.client.renderer.Tessellator;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;

public interface IComponent {
	//Funções basicas
	/**
	 * 
	 * @param mouseX
	 * @param mouseY
	 * @return true se o mouse está dentro deste componente, false se não.
	 */
	public boolean isMouseInside(int mouseX,int mouseY);
	/**
	 * Metodo para dizer como é desenhado o componente.
	 * @param tessellator
	 */
	public void draw(Tessellator tessellator);
	
	//Funções envolvendo o tamanho do componente
	/**
	 * Começo Relativo.
	 * @return onde começa o componente no eixo X.
	 */
	public int getLeft();
	/**
	 * Define um novo começo para o componente no eixo X
	 * @param left posição nova aonde o componente deve estar no eixo X
	 */
	public void setLeft(int left);
	/**
	 * Começo Relativo.
	 * @return onde começa o componente no eixo Y.
	 */
	public int getTop();
	/**
	 * Define um novo começo para o componente no eixo Y
	 * @param left posição nova aonde o componente deve estar no eixo Y
	 */
	public void setTop(int top);
	/**
	 * Começo verdadeiro é o getLeft + a distancia dada pela gui.
	 * @return distancia verdadeira no eixo X
	 */
	public int getTrueLeft();
	/**
	 * Começo verdadeiro é o getTop + a distancia dada pela gui.
	 * @return distancia verdadeira no eixo Y
	 */
	public int getTrueTop();
	/**
	 * 
	 * @return largura do componente.
	 */
	public int getWidth();
	/**
	 * 
	 * @return altura do componente.
	 */
	public int getHeight();
	
	//Funções envolvendo componentes dentro de componentes
	/**
	 * Este metodo deverá retornar todos os componentes dentro dele.
	 * 
	 * A ordem da lista importa, o componente mais a frente da lista,
	 * deverá ser o mais a frente na tela.
	 * 
	 * @return todos os componentes dentro deste componente.
	 */
	public List<IComponent> getAllComponentsInside();
	/**
	 * Este método deverá adicionar o componente passado por parametro dentro deste
	 * componente.
	 * 
	 * Ao mesmo tempo ele também deverá definir ele mesmo como pai do componente passado
	 * por parametro.
	 * 
	 * @param component
	 * @return true se conseguiu adicionar, false se não.
	 */
	public boolean addComponent(IComponent component);
	/**
	 * Este método deverá remover o componente passado por parametro dentro deste
	 * componente.
	 * 
	 * Ao mesmo tempo ele também deverá remover ele mesmo como pai do componente passado
	 * por parametro.
	 * 
	 * @param component
	 * @return true se conseguiu remover, false se não.
	 */
	public boolean removeComponent(IComponent component);
	/**
	 * 
	 * @return null se não possuir um pai, ou o componente em que este está adicionado.
	 */
	public IComponent getPai();
	/**
	 * @param pai componente que adicionou este componente
	 */
	public void setPai(IComponent pai);
}
