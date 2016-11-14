package xyz.rytix.roboTuptup.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.naming.LimitExceededException;
import javax.swing.JOptionPane;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import xyz.rytix.roboTuptup.Config;
import xyz.rytix.roboTuptup.Tuptup;
import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.gui.components.scratch.actionBlocks.ScratchBlocoAction;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBloco;
import xyz.rytix.roboTuptup.gui.dragAndDrop.DragAndDropController;
import xyz.rytix.roboTuptup.gui.components.Component;
import xyz.rytix.roboTuptup.gui.components.ScrollPanelComponent;
import xyz.rytix.roboTuptup.gui.components.ScrollPanelComponentLeft;
import xyz.rytix.roboTuptup.gui.interfaces.IComponent;
import xyz.rytix.roboTuptup.helper.TheObliteratorCustomFont;

import net.java.games.input.Keyboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.GuiScrollingList;

public class GuiBaseRoboTela extends GuiContainer {
	
	List<IComponent> components;
	TileEntityBaseRobo baseRoboEntity;
	DragAndDropController dragAndDropController;
	
	public GuiBaseRoboTela(IInventory playerInv,
			TileEntityBaseRobo baseRoboEntity) {
		super(new ContainerBaseRobo(playerInv, baseRoboEntity));
		
		this.dragAndDropController = new DragAndDropController();
		
		this.xSize = 256;
		this.ySize = 242;
		this.baseRoboEntity = baseRoboEntity;
		
		components = new ArrayList();
		components.add(new ScrollPanelComponentLeft(18,1,84,118,500,500,this));
		ScrollPanelComponent sp2 = new ScrollPanelComponent(103,1,152,118,500,500,this);
		for(ScratchBloco bloco: baseRoboEntity.getBlocos()){
			sp2.components.add(bloco);
			bloco.setGui(this);
		}
		components.add(sp2);
	}
	@Override
	public void onGuiClosed() {
		List<ScratchBloco> listaBlocos =  new ArrayList<ScratchBloco>();
		List<IComponent> components = ((ScrollPanelComponent)this.components.get(1)).components; 
		for(IComponent component: components){
			if(component instanceof ScratchBloco)
				listaBlocos.add((ScratchBloco) component);
		}
		baseRoboEntity.saveAndResetRobot(listaBlocos);
		super.onGuiClosed();
	}

	//Função de desenho principal A.K.A Main
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks,
			int mouseX, int mouseY) {

		mc.renderEngine.bindTexture(new ResourceLocation(Config.MOD_ID,
				"textures/gui/baseRobo.png"));
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		this.drawComponents(mouseX, mouseY);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		dragAndDropController.mouseReleased(mouseX, mouseY, state);
		super.mouseReleased(mouseX, mouseY, state);
	}
	
	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		if(Mouse.isButtonDown(0) && holdObject == null){
			IComponent component = getComponentOn(mouseX, mouseY);
			if(component instanceof RightClickDraggable){
				holdObject = ((RightClickDraggable)component).getDraggableObject(mouseX, mouseY);
				holdObject.draggablePre(mouseX, mouseY);
				getPanelOn(mouseX, mouseY).components.remove(holdObject); // XXX
			}
			
		}else if(holdObject != null){
			holdObject.draggableAction(mouseX, mouseY);
		}
		
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
	}
	
	public IComponent getComponentOn(int mouseX, int mouseY){
		for(IComponent component: components){
			if(component.isMouseInside(mouseX, mouseY)){
				return component.getComponentOn(mouseX, mouseY);
			}
		}
		return null;
	}
	public ScrollPanelComponent getPanelOn(int mouseX, int mouseY){
		for(IComponent component: components){
			if(component instanceof ScrollPanelComponent && component.isMouseInside(mouseX, mouseY)){
				return (ScrollPanelComponent)component;
			}
		}
		return null;
	}
		
	private void drawComponents(int mouseX, int mouseY){
		Tessellator tessellator = Tessellator.getInstance();
		for(IComponent component: components){
	        component.draw(tessellator);
		}
		if(holdObject != null){
			holdObject.draw(tessellator);
		}
	}
	
	
	public int getGuiLeft(){
		return guiLeft;
	}
	
	public int getGuiTop(){
		return guiTop;
	}
}
