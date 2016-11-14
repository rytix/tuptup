package xyz.rytix.roboTuptup.gui.interfaces;

@Deprecated
/**
 * Esta interface será removida e substituida pela IDraggable.
 * 
 * Outra interface para areas que podem receber IDraggables foi criada chamada
 * de IDroppable. 
 * 
 * @author Paulo
 *
 */
public interface RightClickDraggable extends IComponent{
	/**
	 * Função que será chamada uma vez antes de entrar no loop de chamar a função
	 * draggableAction.
	 * @param mouseX
	 * @param mouseY
	 */
	public void draggablePre(int mouseX, int mouseY);
	/**
	 * Função chamada constantemente enquanto o usuário manter o botão
	 * esquerdo do mouse pressionado.
	 * @param mouseX
	 * @param mouseY
	 */
	public void draggableAction(int mouseX, int mouseY);
	/**
	 * Função chamada quando o botão direito do mouse for solto.
	 * @param mouseX
	 * @param mouseY
	 */
	public void draggablePos(int mouseX, int mouseY);
	
	@Deprecated
	/**
	 * A atribuição de capturar os objetos internos será dada ao IComponent
	 */
	public RightClickDraggable getDraggableObject(int mouseX, int mouseY);
}
