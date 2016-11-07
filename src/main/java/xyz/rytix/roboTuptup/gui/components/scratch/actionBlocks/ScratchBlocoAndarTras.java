package xyz.rytix.roboTuptup.gui.components.scratch.actionBlocks;

import xyz.rytix.roboTuptup.block.BlockRobo.Move;
import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBloco;

public class ScratchBlocoAndarTras extends ScratchBlocoAction{

	private static final String texto = "Andar para tras";
	public ScratchBlocoAndarTras(GuiBaseRoboTela gui, int left, int top, boolean exemplo) {
		super(gui, left, top, texto, exemplo);
	}

	@Override
	public ScratchBloco action(TileEntityBaseRobo base) {
		base.moveRobot(Move.BACK);
		return super.action(base);
	}

	@Override
	public ScratchBloco createNewScratchBlock() {
		return new ScratchBlocoAndarTras(gui, getLeft(), getTop(), false);
	}
	
	@Override
	public String toString() {
		return "andarTras()";
	}
	
}
