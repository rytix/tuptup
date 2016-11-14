package xyz.rytix.roboTuptup.gui.interfaces;

import java.util.List;

/**
 * Todos os componentes que podem ter componentes dentro deles
 * precisam implementar esta classe.
 * @author Paulo
 *
 */
public interface ICanHaveComponentsInside extends IComponent{

	/**
	 * Este metodo deverá retornar todos os componentes dentro dele.
	 * 
	 * A ordem da lista importa, o componente mais a frente da lista,
	 * deverá ser o mais a frente na tela.
	 * 
	 * @return todos os componentes dentro deste componente.
	 */
	public List<IComponent> getAllComponentsInside();
}
