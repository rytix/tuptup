package xyz.rytix.roboTuptup.gui.interfaces;

public interface RightClickDraggable {
	/**
	 * Fun��o que ser� chamada uma vez antes de entrar no loop de chamar a fun��o
	 * draggableAction.
	 * @param mouseX
	 * @param mouseY
	 */
	public void draggablePre(int mouseX, int mouseY);
	/**
	 * Fun��o chamada constantemente enquanto o usu�rio manter o bot�o
	 * esquerdo do mouse pressionado.
	 * @param mouseX
	 * @param mouseY
	 */
	public void draggableAction(int mouseX, int mouseY);
	/**
	 * Fun��o chamada quando o bot�o direito do mouse for solto.
	 * @param mouseX
	 * @param mouseY
	 */
	public void draggablePos(int mouseX, int mouseY);
	
	public RightClickDraggable getDraggableObject(int mouseX, int mouseY);
}
