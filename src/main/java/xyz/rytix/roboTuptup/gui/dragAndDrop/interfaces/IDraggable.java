package xyz.rytix.roboTuptup.gui.dragAndDrop.interfaces;

import xyz.rytix.roboTuptup.gui.interfaces.IComponent;

/**
 * Interface para todos os componentes que podem ser arrastados pela tela.
 * 
 * Enquanto o componente estiver sendo arrastado pela tela, o responsavel por
 * desenhar o componente na tela é o DragAndDropController.
 * 
 * @author Paulo
 *
 */
public interface IDraggable extends IComponent{
	/**
	 * Função que será chamada uma vez antes da chamada da função dragging
	 * 
	 * @param mouseX posição x do mouse
	 * @param mouseY posição y do mouse
	 */
	public void draggablePre(int mouseX,int mouseY);
	/**
	 * Função que será chamada enquanto o mouse estiver segurado.
	 * 
	 * @param mouseX posição x do mouse
	 * @param mouseY posição y do mouse
	 */
	public void dragging(int mouseX, int mouseY);
	/**
	 * Função que será chamada uma vez, quando o mouse estiver solto, após a ultima
	 * chamada da função dragging
	 * 
	 * @param mouseX posição x do mouse
	 * @param mouseY posição y do mouse
	 */
	public void draggablePos(int mouseX, int mouseY);

}
