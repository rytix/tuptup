package xyz.rytix.roboTuptup.gui.components.scratch.controlBlocks;

import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBloco;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBlocoString;

public class ScratchBlocoSempre extends ScratchBlocoInstrucoesInternas{

	public ScratchBlocoSempre(GuiBaseRoboTela gui, int left, int top, boolean ehUmBlocoExemplo) {
		super(gui, left, top, new Class[]{ScratchBlocoString.class}, true, true, true, ehUmBlocoExemplo);
		addBlocoNaAssinatura(new ScratchBlocoString(this, gui, "Sempre", left, top), 0);
	}

	@Override
	public ScratchBloco action(TileEntityBaseRobo base) {
		return proximoBlocoDentro;
	}
	
	@Override
	public ScratchBloco returnAction(TileEntityBaseRobo base) {
		return proximoBlocoDentro;
	}

	@Override
	public ScratchBloco createNewScratchBlock() {
		return new ScratchBlocoSempre(gui, getLeft(), getTop(), false);
	}
	
}
