package xyz.rytix.roboTuptup.gui.interfaces;

@Deprecated
/**
 * Esta interface ser� removida e substituida pela IDraggable.
 * 
 * Outra interface para areas que podem receber IDraggables foi criada chamada
 * de IDroppable. 
 * 
 * @author Paulo
 *
 */
public interface RightClickDraggable extends IComponent{
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
	
	@Deprecated
	/**
	 * A atribui��o de capturar os objetos internos ser� dada ao IComponent
	 */
	public RightClickDraggable getDraggableObject(int mouseX, int mouseY);
}
