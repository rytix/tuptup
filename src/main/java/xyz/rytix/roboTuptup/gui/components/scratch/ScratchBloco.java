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
	protected final boolean podePorBlocosAposInstrucao; // A instru��o aceita blocos ap�s ela (n�o � uma instru��o final)
	protected final boolean podePorBlocosDentroInstrucao; // A instru��o aceita blocos dentro dela, como por exemplo o "while"
	protected final boolean podePorBlocosAntesInstrucao; // A instru��o aceita blocos antes da instru��o
	protected final boolean ehUmBlocoExemplo;
	
	protected ScratchBloco pai; // Bloco anterior a este
	
	protected final Class[] blocosNaAssinaturaAceitaveis; // Assinatura � a parte aonde possui textos e aceita sub blocos
	protected final Object[] blocosNaAssinatura; // Blocos que est�o na assinatura
	
	protected ScratchBloco proximoBlocoDentro; // Caso aceite blocos dentro da instru��o, este � o pr�ximo bloco dentro da instru��o
	protected ScratchBloco proximoBlocoInterno; // Caso seja uma instru��o complexa como um se sen�o, � o proximo bloco (sen�o)
	protected ScratchBloco proximoBloco; // Caso aceite blocos ap�s a instru��o, este � o pr�ximo bloco
		
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
