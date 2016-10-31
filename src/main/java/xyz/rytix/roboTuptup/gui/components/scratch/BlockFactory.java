package xyz.rytix.roboTuptup.gui.components.scratch;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.Gui;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBloco;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBlocoInicio;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.components.scratch.actionBlocks.*;
import xyz.rytix.roboTuptup.gui.components.scratch.controlBlocks.ScratchBlocoSempre;

public class BlockFactory {
	public static final List<ScratchBloco> createActionBlocks(GuiBaseRoboTela gui,int left, int top){
		List<ScratchBloco> list = new ArrayList<ScratchBloco>();
		top += 5;
		left += 5;
		int dif = 25;
		list.add(new ScratchBlocoInicio(gui,left,top,true));
		list.add(new ScratchBlocoAndarFrente(gui,left,top+dif,true));
		list.add(new ScratchBlocoAndarTras(gui, left, top+dif*2, true));
		list.add(new ScratchBlocoGirarEsquerda(gui, left, top+dif*3, true));
		list.add(new ScratchBlocoGirarDireita(gui, left, top+dif*4, true));
		list.add(new ScratchBlocoColetarBlocoFrente(gui, left, top+dif*5, true));
		list.add(new ScratchBlocoSempre(gui, left, top+dif*6, true));
		
		return list;
	}
}
