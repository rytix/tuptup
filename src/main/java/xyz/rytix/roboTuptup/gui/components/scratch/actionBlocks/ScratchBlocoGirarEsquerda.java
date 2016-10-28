package xyz.rytix.roboTuptup.gui.components.scratch.actionBlocks;

import xyz.rytix.roboTuptup.block.BlockRobo.Move;
import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBloco;

public class ScratchBlocoGirarEsquerda extends ScratchBlocoAction{
	private static final String texto = "Girar para Esquerda";
	public ScratchBlocoGirarEsquerda(GuiBaseRoboTela gui, int left, int top, boolean exemplo) {
		super(gui, left, top, texto, exemplo);
	}

	@Override
	public ScratchBloco action(TileEntityBaseRobo base) {
		base.moveRobot(Move.ANTICLOCK);
		return proximoBloco;
	}

	@Override
	public ScratchBloco createNewScratchBlock() {
		return new ScratchBlocoGirarEsquerda(gui, getLeft(), getTop(), false);
	}

}
