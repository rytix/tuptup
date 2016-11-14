package xyz.rytix.roboTuptup.gui.interfaces;

import java.util.List;

import net.minecraft.client.renderer.Tessellator;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;

public interface IComponent {
	//Fun��es basicas
	/**
	 * 
	 * @param mouseX
	 * @param mouseY
	 * @return true se o mouse est� dentro deste componente, false se n�o.
	 */
	public boolean isMouseInside(int mouseX,int mouseY);
	/**
	 * Metodo para dizer como � desenhado o componente.
	 * @param tessellator
	 */
	public void draw(Tessellator tessellator);
	
	//Fun��es envolvendo o tamanho do componente
	/**
	 * Come�o Relativo.
	 * @return onde come�a o componente no eixo X.
	 */
	public int getLeft();
	/**
	 * Define um novo come�o para o componente no eixo X
	 * @param left posi��o nova aonde o componente deve estar no eixo X
	 */
	public void setLeft(int left);
	/**
	 * Come�o Relativo.
	 * @return onde come�a o componente no eixo Y.
	 */
	public int getTop();
	/**
	 * Define um novo come�o para o componente no eixo Y
	 * @param left posi��o nova aonde o componente deve estar no eixo Y
	 */
	public void setTop(int top);
	/**
	 * Come�o verdadeiro � o getLeft + a distancia dada pela gui.
	 * @return distancia verdadeira no eixo X
	 */
	public int getTrueLeft();
	/**
	 * Come�o verdadeiro � o getTop + a distancia dada pela gui.
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
	
	//Fun��es envolvendo componentes dentro de componentes
	/**
	 * Este metodo dever� retornar todos os componentes dentro dele.
	 * 
	 * A ordem da lista importa, o componente mais a frente da lista,
	 * dever� ser o mais a frente na tela.
	 * 
	 * @return todos os componentes dentro deste componente.
	 */
	public List<IComponent> getAllComponentsInside();
	/**
	 * Este m�todo dever� adicionar o componente passado por parametro dentro deste
	 * componente.
	 * 
	 * Ao mesmo tempo ele tamb�m dever� definir ele mesmo como pai do componente passado
	 * por parametro.
	 * 
	 * @param component
	 * @return true se conseguiu adicionar, false se n�o.
	 */
	public boolean addComponent(IComponent component);
	/**
	 * Este m�todo dever� remover o componente passado por parametro dentro deste
	 * componente.
	 * 
	 * Ao mesmo tempo ele tamb�m dever� remover ele mesmo como pai do componente passado
	 * por parametro.
	 * 
	 * @param component
	 * @return true se conseguiu remover, false se n�o.
	 */
	public boolean removeComponent(IComponent component);
	/**
	 * 
	 * @return null se n�o possuir um pai, ou o componente em que este est� adicionado.
	 */
	public IComponent getPai();
	/**
	 * @param pai componente que adicionou este componente
	 */
	public void setPai(IComponent pai);
}
