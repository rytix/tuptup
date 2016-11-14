package xyz.rytix.roboTuptup.gui.dragAndDrop.interfaces;

import java.util.Stack;

import xyz.rytix.roboTuptup.gui.interfaces.IComponent;

/**
 * Interface para todos os componentes que podem ter arrastaveis.
 * @author Paulo
 *
 */
public interface IDroppable extends IComponent{
	/**
	 * Verifica se o arrastavel esta flutuando este componente
	 * @param draggable arrastavel
	 * @return true se o arrastavel esta flutuando o componente e false se não.
	 */
	public boolean isDraggableHoveringInside(IDraggable draggable);
	/**
	 * Metodo que pode ser sobrescrito caso este componente queira fazer algo
	 * quando um arrastavel flutua sobre ele.
	 * 
	 * Metodo chamado constantemente enquanto o arrastavel estiver sobre o componente.
	 * 
	 * @param draggable arrastavel que esta flutuando sobre ele.
	 */
	public void draggableHoverIn(IDraggable draggable);
	/**
	 * Metodo que pode ser sobrescrito caso este componente queira fazer algo
	 * quando um arrastavel flutua para fora dele.
	 * @param draggable arrastavel que flutuou para fora.
	 */
	public void draggableHoverOut(IDraggable draggable);
	/**
	 * Metodo que deve ser sobrescrito para o componente lidar com um arrastavel
	 * sendo derrubado dentro dele.
	 * @param draggable
	 */
	public void draggableDropped(IDraggable draggable);
}
