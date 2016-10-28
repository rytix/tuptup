package xyz.rytix.roboTuptup.gui.components;

import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.components.scratch.BlockFactory;
import xyz.rytix.roboTuptup.gui.components.scratch.actionBlocks.ScratchBlocoAndarFrente;
import xyz.rytix.roboTuptup.gui.interfaces.IComponent;

public class ScrollPanelComponentLeft extends ScrollPanelComponent{

	public ScrollPanelComponentLeft(int left, int top, int width, int height, int SCROLL_WIDTH, int SCROLL_HEIGHT,
			GuiBaseRoboTela gui) {
		super(left, top, width, height, SCROLL_WIDTH, SCROLL_HEIGHT, gui);

		components.addAll(BlockFactory.createActionBlocks(gui, left, top));
	}
	
	@Override
	public IComponent getComponentOn(int mouseX, int mouseY) {
		return null;
	}
	
}
