package xyz.rytix.roboTuptup.gui.interfaces;

public interface RightClickDraggable {
	/**
	 * Função chamada constantemente enquanto o usuário manter o botão
	 * esquerdo do mouse pressionado.
	 * @param mouseX
	 * @param mouseY
	 */
	public void draggableAction(int mouseX, int mouseY);
	/**
	 * Função que será chamada uma vez antes de entrar no loop de chamar a função
	 * draggableAction.
	 * @param mouseX
	 * @param mouseY
	 */
	public void draggableInit(int mouseX, int mouseY);
	/**
	 * O componente para ser arrastado, alguns componentes detectam a colisão
	 * dentro deles, porém manda outros componentes serem arrastados.
	 * @return O componente para ser arrastado
	 */
	public RightClickDraggable getDraggableComponent();
}
