package xyz.rytix.roboTuptup.gui.dragAndDrop;

import java.util.List;

import org.lwjgl.input.Mouse;

import xyz.rytix.roboTuptup.gui.RightClickDraggable;
import xyz.rytix.roboTuptup.gui.dragAndDrop.interfaces.IDraggable;
import xyz.rytix.roboTuptup.gui.interfaces.ICanHaveComponentsInside;
import xyz.rytix.roboTuptup.gui.interfaces.IComponent;

/**
 * Esta classe esta responsavel por controlar o Drag and Drop da GUI.
 * 
 * Enquanto o IDraggable esta sendo arrastado pela tela, é esta classe que
 * fica com a responsabilidade de manter a existencia do IDraggable e chamar
 * sua função draw()
 * 
 * Após o mouse ser solto, se nenhum IDroppable ficar com componente arrastavel,
 * ele se vai para o limbo do java (GarbageCollector).
 * 
 * @author Paulo
 *
 */
public class DragAndDropController {
	//TODO Criar o controller e refatorar os demais componentes.
	IDraggable holdedDraggable;
	ICanHaveComponentsInside mainWindowComponent;
	
	public DragAndDropController(ICanHaveComponentsInside mainWindowComponent) {
		this.mainWindowComponent = mainWindowComponent;
	}
	
	public void mouseReleased(int mouseX, int mouseY, int state){
		if(holdedDraggable != null){
			holdedDraggable.draggablePos(mouseX, mouseY);
			holdedDraggable = null;
		}
	}
	
	public void mouseClickMove(int mouseX, int mouseY){
		if(holdedDraggable == null){
			List<IComponent> componentsInside = mainWindowComponent.getAllComponentsInside();
			for(IComponent componentInside : componentsInside){
				if(componentInside instanceof IDraggable 
						&& componentInside.isMouseInside(mouseX, mouseY)){
					
				}
			}
			IComponent component = getComponentOn(mouseX, mouseY);
			if(component instanceof RightClickDraggable){
				holdedDraggable = ((RightClickDraggable)component).getDraggableObject(mouseX, mouseY);
				holdedDraggable.draggablePre(mouseX, mouseY);
				getPanelOn(mouseX, mouseY).components.remove(holdObject); // XXX
			}
		}else{
			holdedDraggable.draggablePos(mouseX, mouseY);
		}
	}
}
