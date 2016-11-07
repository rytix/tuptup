package xyz.rytix.roboTuptup.gui.components.scratch.controlBlocks;

import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBloco;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBlocoString;
//XXX
public class ScratchBlocoRepita3 extends ScratchBlocoInstrucoesInternas{
	private int repita = 0;
	private final int REPITCOUNT = 3;
	public ScratchBlocoRepita3(GuiBaseRoboTela gui, int left, int top, boolean ehUmBlocoExemplo) {
		super(gui, left, top, new Class[]{ScratchBlocoString.class}, true, true, true, ehUmBlocoExemplo);
		addBlocoNaAssinatura(new ScratchBlocoString(this, gui, "Repita 3 vezes", left, top), 0);
	}

	@Override
	public ScratchBloco action(TileEntityBaseRobo base) {
		repita = 1;
		return proximoBlocoDentro;
	}
	
	@Override
	public ScratchBloco returnAction(TileEntityBaseRobo base) {
		if(repita < REPITCOUNT){
			repita++;
			return proximoBlocoDentro;
		}else if(repita == REPITCOUNT){
			repita++;
			if(proximoBloco != null){
				return proximoBloco;
			}else{
				return super.returnAction(base);
			}
		}else{
			return super.returnAction(base);
		}
	}

	@Override
	public ScratchBloco createNewScratchBlock() {
		return new ScratchBlocoRepita3(gui, getLeft(), getTop(), false);
	}
	
	@Override
	public String toString() {
		return "para(inteiro i=0; i<3;i++){";
	}
	
}
