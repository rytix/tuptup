package xyz.rytix.roboTuptup.gui.components.scratch.core;

import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;

public class ScratchBlocoInicio extends ScratchBloco{
	
	private final static int COLOR = 0xFFA5745B;
	private final static int BORDER_COLOR = 0xFF72503F;

	public ScratchBlocoInicio(GuiBaseRoboTela gui, int left, int top, boolean ehUmBlocoExemplo) {
		super(gui,left,top,new Class[]{ScratchBlocoString.class},false,true,ehUmBlocoExemplo);
		color = COLOR;
		borderColor = BORDER_COLOR;
		addBlocoNaAssinatura(new ScratchBlocoString(this, gui, "Inicio", left, top), 0);
	}

	@Override
	public ScratchBloco action(TileEntityBaseRobo base) {
		return proximoBloco;
	}

	@Override
	public ScratchBloco createNewScratchBlock() {
		return new ScratchBlocoInicio(gui, getLeft(), getTop(),false);
	}
	@Override
	public String toString() {
		return "programa{ \n funcao inicio() {";
	}

}
