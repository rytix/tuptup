package xyz.rytix.roboTuptup.gui.model;

import java.util.List;

public abstract class ScratchBloco {
	protected final boolean podePorBlocosAposInstrucao; // A instru��o aceita blocos ap�s ela (n�o � uma instru��o final)
	protected final boolean podePorBlocosDentroInstrucao; // A instru��o aceita blocos dentro dela, como por exemplo o "while"
	
	protected ScratchBloco pai; // Bloco anterior a este
	
	protected final Class[] blocosNaAssinaturaAceitaveis; // Assinatura � a parte aonde possui textos e aceita sub blocos
	protected final Object[] blocosNaAssinatura; // Blocos que est�o na assinatura
	
	protected ScratchBloco proximoBlocoDentro; // Caso aceite blocos dentro da instru��o, este � o pr�ximo bloco dentro da instru��o
	protected ScratchBloco proximoBlocoInterno; // Caso seja uma instru��o complexa como um se sen�o, � o proximo bloco (sen�o)
	protected ScratchBloco proximoBloco; // Caso aceite blocos ap�s a instru��o, este � o pr�ximo bloco
	
	protected int x;
	protected int y;
	
	int[] colisionBox;
	
	public ScratchBloco(boolean podePorBlocosAposInstrucao, boolean podePorBlocosDentroInstrucao, Class[] blocosNaAssinaturaAceitaveis) {
		this.podePorBlocosAposInstrucao = podePorBlocosAposInstrucao;
		this.podePorBlocosDentroInstrucao = podePorBlocosDentroInstrucao;
		this.blocosNaAssinaturaAceitaveis = blocosNaAssinaturaAceitaveis;
		this.blocosNaAssinatura = new Object[blocosNaAssinaturaAceitaveis.length];
	}
	
	public abstract void draw();
	public abstract void action();
	
	public boolean isPodePorBlocosAposInstrucao() {
		return podePorBlocosAposInstrucao;
	}
	public boolean isPodePorBlocosDentroInstrucao() {
		return podePorBlocosDentroInstrucao;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
