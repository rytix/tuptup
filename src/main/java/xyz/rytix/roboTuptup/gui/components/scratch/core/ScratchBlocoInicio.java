package xyz.rytix.roboTuptup.gui.components.scratch.core;

import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;

public class ScratchBlocoInicio extends ScratchBloco{

	public ScratchBlocoInicio(GuiBaseRoboTela gui, int left, int top, boolean ehUmBlocoExemplo) {
		super(gui,left,top,new Class[]{ScratchBlocoString.class},false,true,ehUmBlocoExemplo);
	}

	@Override
	public ScratchBloco action(TileEntityBaseRobo base) {
		return proximoBloco;
	}

	@Override
	public ScratchBloco createNewScratchBlock() {
		// TODO Auto-generated method stub
		return new ScratchBlocoInicio(gui, getLeft(), getTop(),false);
	}

}
