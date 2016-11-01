package xyz.rytix.roboTuptup.gui.components.scratch.actionBlocks;

import xyz.rytix.roboTuptup.block.BlockRobo.Move;
import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBloco;

public class ScratchBlocoColocarBlocoFrente extends ScratchBlocoAction{

	public ScratchBlocoColocarBlocoFrente(GuiBaseRoboTela gui, int left, int top, boolean exemplo) {
		super(gui, left, top, "Colocar Bloco Frente", exemplo);
		color = 0xFF995BA5;
		borderColor = 0xFF6A3F72;
	}
	
	@Override
	public ScratchBloco action(TileEntityBaseRobo base) {
		base.placeBlock(Move.FRONT);
		return super.action(base);
	}

	@Override
	public ScratchBloco createNewScratchBlock() {
		return new ScratchBlocoColocarBlocoFrente(gui, getLeft(), getTop(), false);
	}

}
