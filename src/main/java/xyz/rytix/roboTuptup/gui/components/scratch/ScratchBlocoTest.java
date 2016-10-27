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


	public ScratchBlocoTest(GuiBaseRoboTela gui, int left,int top, String texto, boolean antes) {
		super(true, false, antes, false, new Class[]{ScratchBlocoString.class}, gui, left,top);
		addBlocoNaAssinatura(new ScratchBlocoString(gui, texto, left, top),0);
	}
	
	@Override
	public void draw(Tessellator tessellator) {
		super.draw(tessellator);
	}
	
	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public RightClickDraggable getDraggableObject(int mouseX, int mouseY) {
		return this;
	}

}
