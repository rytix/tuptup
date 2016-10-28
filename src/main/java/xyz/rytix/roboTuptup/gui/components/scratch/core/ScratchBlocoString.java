package xyz.rytix.roboTuptup.gui.components.scratch.core;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.interfaces.RightClickDraggable;
import xyz.rytix.roboTuptup.helper.TheObliteratorCustomFont;

public class ScratchBlocoString extends ScratchBloco{
	private final String STRING;
    private final TheObliteratorCustomFont cf;

	private final int TOP_BOTTOM_SPACE = 4;
	private final int LEFT_RIGHT_SPACE = 4;

	public ScratchBlocoString(GuiBaseRoboTela gui, String string, int left, int top) {
		super(false,false,false,false,null,gui,left,top);
		this.cf = new TheObliteratorCustomFont(Minecraft.getMinecraft(), "Arial", 9);
		this.STRING = string;
	}
	
	@Override
	public void draw(Tessellator t) {
		cf.drawString(gui, STRING, getTrueLeft()+LEFT_RIGHT_SPACE, getTrueTop()+TOP_BOTTOM_SPACE, 0xFFECF0F1);
	}

	@Override
	public int getWidth() {
		return cf.getStringWidth(STRING) + LEFT_RIGHT_SPACE*2;
	}

	@Override
	public int getHeight() {
		return cf.getStringHeight(STRING) + TOP_BOTTOM_SPACE*2;
	}

	@Override
	public void draggablePos(int mouseX, int mouseY) {}

	@Override
	public RightClickDraggable getDraggableObject(int mouseX, int mouseY) {return null;}

	@Override
	public boolean isMouseInside(int mouseX, int mouseY) {
		return false;
	}

	@Override
	public ScratchBloco createNewScratchBlock() {
		return new ScratchBlocoString(gui,STRING,getLeft(),getTop());
	}

	@Override
	public ScratchBloco action(TileEntityBaseRobo base) {
		return null;
	}
	
}
