package xyz.rytix.roboTuptup.gui.interfaces;

public interface RightClickDraggable {
	/**
	 * Fun��o chamada constantemente enquanto o usu�rio manter o bot�o
	 * esquerdo do mouse pressionado.
	 * @param mouseX
	 * @param mouseY
	 */
	public void draggableAction(int mouseX, int mouseY);
	/**
	 * Fun��o que ser� chamada uma vez antes de entrar no loop de chamar a fun��o
	 * draggableAction.
	 * @param mouseX
	 * @param mouseY
	 */
	public void draggableInit(int mouseX, int mouseY);
	/**
	 * O componente para ser arrastado, alguns componentes detectam a colis�o
	 * dentro deles, por�m manda outros componentes serem arrastados.
	 * @return O componente para ser arrastado
	 */
	public RightClickDraggable getDraggableComponent();
}
