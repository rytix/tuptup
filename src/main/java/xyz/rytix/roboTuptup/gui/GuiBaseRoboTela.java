package xyz.rytix.roboTuptup.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.naming.LimitExceededException;
import javax.swing.JOptionPane;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import sun.java2d.loops.DrawRect;
import xyz.rytix.roboTuptup.Config;
import xyz.rytix.roboTuptup.Tuptup;
import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.gui.components.scratch.ScratchBloco;
import xyz.rytix.roboTuptup.gui.components.scratch.ScratchBlocoTest;
import xyz.rytix.roboTuptup.gui.components.ScrollPanelComponent;
import xyz.rytix.roboTuptup.gui.interfaces.Component;
import xyz.rytix.roboTuptup.gui.interfaces.RightClickDraggable;
import xyz.rytix.roboTuptup.helper.TheObliteratorCustomFont;

import com.sun.prism.paint.Color;

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

	public static RightClickDraggable holdObject = null;
	
	List<Component> components;
	List<ScratchBloco> scratchBlocos = new ArrayList(); // Vai para o robô
	

	public GuiBaseRoboTela(IInventory playerInv,
			TileEntityBaseRobo baseRoboEntity) {
		super(new ContainerBaseRobo(playerInv, baseRoboEntity));
				
		scratchBlocos.add(new ScratchBlocoTest(this));
		this.xSize = 256;
		this.ySize = 242;
		
		components = new ArrayList();
		components.add(new ScrollPanelComponent(103,1,124,152,118,500,500,guiLeft,guiTop,this));
		components.add(new ScrollPanelComponent(18,1,124,84,118,500,500,guiLeft,guiTop,this));
	}

	@Override
	public void initGui() {
		// this.buttonList.add(this.act = new GuiButton(0, this.width/2 -100,
		// this.height/2 - 24, "Button A"));
		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {

		super.actionPerformed(button);
	}

	//Função de desenho principal A.K.A Main
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks,
			int mouseX, int mouseY) {

		mc.renderEngine.bindTexture(new ResourceLocation(Config.MOD_ID,
				"textures/gui/baseRobo.png"));
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		this.resolverBotaoEsquerdoSegurado(mouseX,mouseY);
		this.drawComponents(mouseX, mouseY);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}
	
	private void resolverBotaoEsquerdoSegurado(int mouseX,int mouseY){
		if(!Mouse.isButtonDown(0)){
			holdObject = null;
		}else if(Mouse.isButtonDown(0) && holdObject == null){
			for(Component component: components){
				if(component.isMouseInside(mouseX, mouseY) && 
						component instanceof RightClickDraggable){
					holdObject = ((RightClickDraggable)component).getDraggableComponent();
					holdObject.draggableInit(mouseX, mouseY);
				}
			}
		}else if(holdObject != null){
			holdObject.draggableAction(mouseX, mouseY);
		}
	}
	
	private void drawComponents(int mouseX, int mouseY){
		Tessellator tessellator = Tessellator.getInstance();
		for(Component component: components){
	        component.draw(tessellator,guiLeft,guiTop);
		}
	}
}
