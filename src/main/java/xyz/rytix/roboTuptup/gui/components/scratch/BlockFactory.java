package xyz.rytix.roboTuptup.gui.components.scratch;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.Gui;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBloco;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBlocoInicio;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.components.scratch.actionBlocks.*;
import xyz.rytix.roboTuptup.gui.components.scratch.controlBlocks.ScratchBlocoInstrucoesInternas;
import xyz.rytix.roboTuptup.gui.components.scratch.controlBlocks.ScratchBlocoRepita2;
import xyz.rytix.roboTuptup.gui.components.scratch.controlBlocks.ScratchBlocoRepita3;
import xyz.rytix.roboTuptup.gui.components.scratch.controlBlocks.ScratchBlocoRepita5;
import xyz.rytix.roboTuptup.gui.components.scratch.controlBlocks.ScratchBlocoSempre;

public class BlockFactory {
	private static final int DIF = 20;
	private static int difTemp = 0;
	public static final List<ScratchBloco> createActionBlocks(GuiBaseRoboTela gui,int left, int top){
		List<ScratchBloco> list = new ArrayList<ScratchBloco>();
		top += 5;
		left += 5;
		difTemp = 0;
		add(list, new ScratchBlocoInicio(gui,left,top,true));
		
		add(list, new ScratchBlocoAndarFrente(gui,left,top,true));
		add(list, new ScratchBlocoAndarTras(gui, left, top, true));
		add(list, new ScratchBlocoAndarCima(gui, left, top, true));
		add(list, new ScratchBlocoAndarBaixo(gui, left, top, true));
		
		add(list, new ScratchBlocoGirarEsquerda(gui, left, top, true));
		add(list, new ScratchBlocoGirarDireita(gui, left, top, true));
		
		add(list, new ScratchBlocoColetarBlocoFrente(gui, left, top, true));
		add(list, new ScratchBlocoColetarBlocoBaixo(gui, left, top, true));
		add(list, new ScratchBlocoColocarBlocoFrente(gui, left, top, true));
		add(list, new ScratchBlocoColocarBlocoBaixo(gui, left, top, true));
		
		add(list, new ScratchBlocoSempre(gui, left, top, true));
		add(list, new ScratchBlocoRepita2(gui, left, top, true));
		add(list, new ScratchBlocoRepita3(gui, left, top, true));
		add(list, new ScratchBlocoRepita5(gui, left, top, true));
		
		return list;
	}
	private static void add(List<ScratchBloco> list, ScratchBloco bloco){
		bloco.setTop(bloco.getTop()+difTemp);
		if(bloco instanceof ScratchBlocoInstrucoesInternas){
			difTemp+=DIF*2-10;
		}else{
			difTemp+=DIF;
		}
		list.add(bloco);
		
	}
}
