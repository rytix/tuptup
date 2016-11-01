package xyz.rytix.roboTuptup.gui.components.scratch.actionBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.lwjgl.opengl.GL11;

import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBloco;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBlocoString;
import xyz.rytix.roboTuptup.gui.interfaces.RightClickDraggable;
import xyz.rytix.roboTuptup.helper.TheObliteratorCustomFont;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public abstract class ScratchBlocoAction extends ScratchBloco{
	
	public ScratchBlocoAction(GuiBaseRoboTela gui, int left, int top, String texto, boolean exemplo) {
		super(gui,left,top,new Class[]{ScratchBlocoString.class},true,true,exemplo);
		color = 0xFF5BA58C;
		borderColor = 0xFF3F7261;
		addBlocoNaAssinatura(new ScratchBlocoString(this, gui, texto, left, top),0);
	}
	
	@Override
	public ScratchBloco action(TileEntityBaseRobo base) {
		if(proximoBloco != null){
			return proximoBloco;
		}
		return returnAction(base);
	}
	@Override
	public void draw(Tessellator tessellator) {
		super.draw(tessellator);
	}
}
