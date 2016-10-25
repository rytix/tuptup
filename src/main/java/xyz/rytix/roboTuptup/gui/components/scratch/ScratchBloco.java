package xyz.rytix.roboTuptup.gui.components.scratch;

import java.util.List;
import java.util.Stack;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.components.ScrollPanelComponent;
import xyz.rytix.roboTuptup.gui.interfaces.Component;
import xyz.rytix.roboTuptup.gui.interfaces.RightClickDraggable;

public abstract class ScratchBloco extends Component implements RightClickDraggable{
	protected final boolean podePorBlocosAposInstrucao; // A instrução aceita blocos após ela (não é uma instrução final)
	protected final boolean podePorBlocosDentroInstrucao; // A instrução aceita blocos dentro dela, como por exemplo o "while"
	protected final boolean podePorBlocosAntesInstrucao; // A instrução aceita blocos antes da instrução
	protected final boolean ehUmBlocoExemplo;
	
	protected ScratchBloco pai; // Bloco anterior a este
	
	protected final Class[] blocosNaAssinaturaAceitaveis; // Assinatura é a parte aonde possui textos e aceita sub blocos
	protected final Object[] blocosNaAssinatura; // Blocos que estão na assinatura
	
	protected ScratchBloco proximoBlocoDentro; // Caso aceite blocos dentro da instrução, este é o próximo bloco dentro da instrução
	protected ScratchBloco proximoBlocoInterno; // Caso seja uma instrução complexa como um se senão, é o proximo bloco (senão)
	protected ScratchBloco proximoBloco; // Caso aceite blocos após a instrução, este é o próximo bloco
		
	private int oldMouseX = 0;
	private int oldMouseY = 0;
	private int panelWidth;
	private int panelHeight;
	
	public ScratchBloco(boolean podePorBlocosAposInstrucao, 
			boolean podePorBlocosDentroInstrucao,
			boolean podePorBlocosAntesInstrucao,
			boolean ehUmBlocoExemplo,
			Class[] blocosNaAssinaturaAceitaveis, GuiBaseRoboTela gui,
			int panelWidth, int panelHeight,
			int left, int top) {
		super(gui);
		this.left = left;
		this.top = top;
		this.podePorBlocosAposInstrucao = podePorBlocosAposInstrucao;
		this.podePorBlocosDentroInstrucao = podePorBlocosDentroInstrucao;
		this.podePorBlocosAntesInstrucao = podePorBlocosAntesInstrucao;
		this.ehUmBlocoExemplo = ehUmBlocoExemplo;
		this.blocosNaAssinaturaAceitaveis = blocosNaAssinaturaAceitaveis;
		this.panelWidth = panelWidth;
		this.panelHeight = panelHeight;
		if(blocosNaAssinaturaAceitaveis == null){
			this.blocosNaAssinatura = null;
		}else{
			this.blocosNaAssinatura = new Object[blocosNaAssinaturaAceitaveis.length];
		}
	}	
	
	public abstract void action();
	
	@Override
	public void draggablePre(int mouseX, int mouseY) {
		oldMouseX = mouseX;
		oldMouseY = mouseY;
		
	}
	
	@Override
	public void draggableAction(int mouseX, int mouseY) {
		left = mouseX - GUI.getGuiLeft();
		top = mouseY - GUI.getGuiTop();		
	}
	
	public boolean isPodePorBlocosAposInstrucao() {
		return podePorBlocosAposInstrucao;
	}
	public boolean isPodePorBlocosDentroInstrucao() {
		return podePorBlocosDentroInstrucao;
	}
	public boolean isPodePorBlocosAntesInstrucao() {
		return podePorBlocosAntesInstrucao;
	}
}
