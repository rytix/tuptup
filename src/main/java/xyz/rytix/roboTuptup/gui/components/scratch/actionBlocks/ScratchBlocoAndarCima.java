package xyz.rytix.roboTuptup.gui.components.scratch.actionBlocks;

import xyz.rytix.roboTuptup.block.BlockRobo.Move;
import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBloco;

public class ScratchBlocoAndarCima extends ScratchBlocoAction{
	private static final String texto = "Andar para Cima";
	public ScratchBlocoAndarCima(GuiBaseRoboTela gui, int left, int top, boolean exemplo) {
		super(gui, left, top, texto, exemplo);
	}

	@Override
	public ScratchBloco action(TileEntityBaseRobo base) {
		base.moveRobot(Move.UP);
		return super.action(base);
	}

	@Override
	public ScratchBloco createNewScratchBlock() {
		return new ScratchBlocoAndarCima(gui, getLeft(), getTop(), false);
	}

}
