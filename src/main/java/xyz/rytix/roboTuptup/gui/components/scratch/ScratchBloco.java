package xyz.rytix.roboTuptup.gui.components.scratch;

import java.util.List;
import java.util.Stack;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import xyz.rytix.roboTuptup.gui.interfaces.Component;

public abstract class ScratchBloco extends Component{
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
	
	protected int x;
	protected int y;
		
	public ScratchBloco(boolean podePorBlocosAposInstrucao, 
			boolean podePorBlocosDentroInstrucao,
			boolean podePorBlocosAntesInstrucao,
			boolean ehUmBlocoExemplo,
			Class[] blocosNaAssinaturaAceitaveis, Gui gui) {
		super(gui);
		this.podePorBlocosAposInstrucao = podePorBlocosAposInstrucao;
		this.podePorBlocosDentroInstrucao = podePorBlocosDentroInstrucao;
		this.podePorBlocosAntesInstrucao = podePorBlocosAntesInstrucao;
		this.ehUmBlocoExemplo = ehUmBlocoExemplo;
		this.blocosNaAssinaturaAceitaveis = blocosNaAssinaturaAceitaveis;
		if(blocosNaAssinaturaAceitaveis == null){
			this.blocosNaAssinatura = null;
		}else{
			this.blocosNaAssinatura = new Object[blocosNaAssinaturaAceitaveis.length];
		}
	}	
	
	public abstract void action();
	
	public boolean isPodePorBlocosAposInstrucao() {
		return podePorBlocosAposInstrucao;
	}
	public boolean isPodePorBlocosDentroInstrucao() {
		return podePorBlocosDentroInstrucao;
	}
	public boolean isPodePorBlocosAntesInstrucao() {
		return podePorBlocosAntesInstrucao;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
