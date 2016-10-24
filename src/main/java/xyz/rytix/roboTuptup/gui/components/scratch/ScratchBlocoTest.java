package xyz.rytix.roboTuptup.gui.components.scratch;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.lwjgl.opengl.GL11;

import xyz.rytix.roboTuptup.helper.TheObliteratorCustomFont;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class ScratchBlocoTest extends ScratchBloco{


	public ScratchBlocoTest(Gui gui) {
		super(false, false, false, false, null, gui);
		this.x = 50;
		this.y = 25;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Tessellator tessellator, int left, int top) {
		VertexBuffer vertexBuffer = tessellator.getBuffer();
        TheObliteratorCustomFont cf = new TheObliteratorCustomFont(Minecraft.getMinecraft(), "Arial", 9);
		
		GlStateManager.pushMatrix();
	    GlStateManager.pushAttrib();
	    
        GlStateManager.disableTexture2D();
		GlStateManager.color(41/255.0F, 128/255.0F, 185/255.0F, 1.0F);
		DrawHelper.drawTopConnector(tessellator, left+x, top+y);
		DrawHelper.drawInstructionScratchBlock(tessellator, left+x, top+y, cf.getStringWidth("Andar para frente") + 10);
		
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
        
		cf.drawString(GUI, "Andar para Frente", left+x+5, top+y+6, 0xFFECF0F1);
	}

	@Override
	public boolean isMouseInside(int mouseX, int mouseY) {
		return false;
	}

}
