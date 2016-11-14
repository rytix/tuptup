package xyz.rytix.roboTuptup.gui.dragAndDrop.interfaces;

import xyz.rytix.roboTuptup.gui.interfaces.IComponent;

/**
 * Interface para todos os componentes que podem ser arrastados pela tela.
 * 
 * Enquanto o componente estiver sendo arrastado pela tela, o responsavel por
 * desenhar o componente na tela � o DragAndDropController.
 * 
 * @author Paulo
 *
 */
public interface IDraggable extends IComponent{
	/**
	 * Fun��o que ser� chamada uma vez antes da chamada da fun��o dragging
	 * 
	 * @param mouseX posi��o x do mouse
	 * @param mouseY posi��o y do mouse
	 */
	public void draggablePre(int mouseX,int mouseY);
	/**
	 * Fun��o que ser� chamada enquanto o mouse estiver segurado.
	 * 
	 * @param mouseX posi��o x do mouse
	 * @param mouseY posi��o y do mouse
	 */
	public void dragging(int mouseX, int mouseY);
	/**
	 * Fun��o que ser� chamada uma vez, quando o mouse estiver solto, ap�s a ultima
	 * chamada da fun��o dragging
	 * 
	 * @param mouseX posi��o x do mouse
	 * @param mouseY posi��o y do mouse
	 */
	public void draggablePos(int mouseX, int mouseY);

}
