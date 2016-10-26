package xyz.rytix.roboTuptup.gui.components.scratch;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.interfaces.RightClickDraggable;
import xyz.rytix.roboTuptup.gui.interfaces.ScratchDrawable;
import xyz.rytix.roboTuptup.helper.TheObliteratorCustomFont;

public class ScratchBlocoString extends ScratchBloco implements ScratchDrawable{
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
		cf.drawString(GUI, STRING, left+LEFT_RIGHT_SPACE, top+TOP_BOTTOM_SPACE, 0xFFECF0F1);
	}

	@Override
	public int getScratchThingWidth() {
		return cf.getStringWidth(STRING) + LEFT_RIGHT_SPACE*2;
	}

	@Override
	public int getScratchThingHeight() {
		return cf.getStringHeight(STRING) + TOP_BOTTOM_SPACE*2;
	}

	@Override
	public void draggablePos(int mouseX, int mouseY) {}

	@Override
	public RightClickDraggable getDraggableObject(int mouseX, int mouseY) {return null;}

	@Override
	public void action() {}

	@Override
	public boolean isMouseInside(int mouseX, int mouseY) {
		return false;
	}
	
}
