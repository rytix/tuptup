package xyz.rytix.roboTuptup.gui.components.scratch;

import java.util.List;
import java.util.Stack;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import xyz.rytix.roboTuptup.gui.interfaces.Component;

public abstract class ScratchBloco extends Component{
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
