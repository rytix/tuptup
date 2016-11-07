package xyz.rytix.roboTuptup.gui.components.scratch.actionBlocks;

import xyz.rytix.roboTuptup.block.BlockRobo.Move;
import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBloco;

public class ScratchBlocoColetarBlocoFrente extends ScratchBlocoAction{

	public ScratchBlocoColetarBlocoFrente(GuiBaseRoboTela gui, int left, int top, boolean exemplo) {
		super(gui, left, top, "Coletar Frente", exemplo);
		color = 0xFF995BA5;
		borderColor = 0xFF6A3F72;
	}

	@Override
	public ScratchBloco action(TileEntityBaseRobo base) {
		base.harvestFrom(Move.FRONT);
		return super.action(base);
	}

	@Override
	public ScratchBloco createNewScratchBlock() {
		return new ScratchBlocoColetarBlocoFrente(gui, getLeft(), getTop(), false);
	}
	@Override
	public String toString() {
		return "coletarFrente()";
	}
}
