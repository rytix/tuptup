package xyz.rytix.roboTuptup.gui.components.scratch;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.lwjgl.opengl.GL11;

import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.interfaces.RightClickDraggable;
import xyz.rytix.roboTuptup.helper.TheObliteratorCustomFont;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class ScratchBlocoTest extends ScratchBloco{


	public ScratchBlocoTest(GuiBaseRoboTela gui, int panelWidth, int panelHeight,int left,int top) {
		super(false, false, false, false, null, gui, panelWidth, panelHeight,left,top);
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Tessellator tessellator) {		
		VertexBuffer vertexBuffer = tessellator.getBuffer();
        TheObliteratorCustomFont cf = new TheObliteratorCustomFont(Minecraft.getMinecraft(), "Arial", 9);
		
        int left = getTrueLeft();
        int top = getTrueTop();
        
		GlStateManager.pushMatrix();
	    GlStateManager.pushAttrib();
	    
        GlStateManager.disableTexture2D();
		GlStateManager.color(41/255.0F, 128/255.0F, 185/255.0F, 1.0F);
		DrawHelper.drawTopConnector(tessellator, left, top);
		DrawHelper.drawInstructionScratchBlock(tessellator, left, top, cf.getStringWidth("Andar para frente") + 10);
		
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
        
		cf.drawString(GUI, "Andar para Frente", left+6, top+6, 0xFFECF0F1);
	}

	@Override
	public boolean isMouseInside(int mouseX, int mouseY) {
		if(mouseX > getTrueLeft() && mouseX < getTrueLeft() + 100){
			if(mouseY > getTrueTop() && mouseY < getTrueTop() + 35){
				return true;
			}
		}
		return false;
	}

	@Override
	public void draggablePos(int mouseX, int mouseY) {}
	
	@Override
	public RightClickDraggable getDraggableObject(int mouseX, int mouseY) {
		return this;
	}

}
