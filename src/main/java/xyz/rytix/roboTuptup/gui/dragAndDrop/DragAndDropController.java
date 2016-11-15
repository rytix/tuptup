package xyz.rytix.roboTuptup.gui.dragAndDrop;

import java.util.List;

import org.lwjgl.input.Mouse;

import xyz.rytix.roboTuptup.gui.dragAndDrop.interfaces.IDraggable;
import xyz.rytix.roboTuptup.gui.dragAndDrop.interfaces.IDroppable;
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
	IDroppable lastDroppableFloated;
	
	IComponent mainWindowComponent;
	
	public DragAndDropController(IComponent mainWindowComponent) {
		this.mainWindowComponent = mainWindowComponent;
	}
	
	public void mouseReleased(int mouseX, int mouseY, int state){
		if(holdedDraggable != null){
			holdedDraggable.draggablePos(mouseX, mouseY);
			holdedDraggable = null;
		}
	}
	
	private IComponent findTheInnerComponentWhereTheMouseIsOn(IComponent start, int mouseX, int mouseY){		
		List<IComponent> innerComponents;
		mainLoop:
		while(true){
			innerComponents = start.getAllComponentsInside();
			if(innerComponents != null && !innerComponents.isEmpty()){
				for(IComponent innerComponent : innerComponents){
					if(innerComponent.isMouseInside(mouseX, mouseY)){
						start = innerComponent;
						continue mainLoop;
					}
				}
			}
			return start;
		}
	}
	private IDraggable returnUntilFindAIDraggableOrNull(IComponent start){
		while(start != null && !(start instanceof IDraggable)){
			start = start.getPai();
		}
		return start != null ? (IDraggable) start : null;
	}
	private IDraggable findInnerValidIDraggable(IComponent start, int mouseX, int mouseY){
		return returnUntilFindAIDraggableOrNull(findTheInnerComponentWhereTheMouseIsOn(start, mouseX, mouseY));
	}
	private IComponent findTheInnerComponentThatThisDraggableIsHovering(IComponent start, IDraggable draggableOnHold, int mouseX, int mouseY){		
		List<IComponent> innerComponents;
		mainLoop:
		while(true){
			innerComponents = start.getAllComponentsInside();
			if(innerComponents != null && !innerComponents.isEmpty()){
				
				for(IComponent innerComponent : innerComponents){
					if(innerComponent instanceof IDroppable){
						if(((IDroppable) innerComponent).isDraggableHoveringInside(draggableOnHold)){
							start = innerComponent;
							continue mainLoop;
						}
					}else if(innerComponent.isMouseInside(mouseX, mouseY)){
						start = innerComponent;
						continue mainLoop;
					}
				}
				
			}
			return start;
		}
	}
	private IDroppable returnUntilFindAIDroppableOrNull(IComponent start){
		while(start != null && !(start instanceof IDroppable)){
			start = start.getPai();
		}
		return start != null ? (IDroppable) start : null;
	}
	private IDroppable findInnerValidIDroppable (IComponent start, IDraggable draggableOnHold, int mouseX, int mouseY){
		return returnUntilFindAIDroppableOrNull(findTheInnerComponentThatThisDraggableIsHovering(start, draggableOnHold, mouseX, mouseY));
	}
	
	public void mouseClickMove(int mouseX, int mouseY){
		if(holdedDraggable == null && mainWindowComponent.isMouseInside(mouseX, mouseY)){
			IDraggable iDraggableFinded = findInnerValidIDraggable(mainWindowComponent, mouseX, mouseY);
			if(iDraggableFinded != null){
				holdedDraggable = iDraggableFinded;
				holdedDraggable.draggablePre(mouseX, mouseY);
				holdedDraggable.getPai().removeComponent(holdedDraggable);
			}
		}else if(holdedDraggable != null){
			IDroppable iDroppableFinded;
			holdedDraggable.dragging(mouseX, mouseY);
			
			iDroppableFinded = findInnerValidIDroppable(mainWindowComponent, holdedDraggable, mouseX, mouseY);
			if(iDroppableFinded != lastDroppableFloated){
				if(lastDroppableFloated != null){
					lastDroppableFloated.draggableHoverOut(holdedDraggable);
				}
				lastDroppableFloated = iDroppableFinded;
				
				if(iDroppableFinded != null){
					iDroppableFinded.draggableHoverIn(holdedDraggable);
				}
			}
		}
	}
}
