package xyz.rytix.roboTuptup.gui.model;

public abstract class Bloco {
	protected final boolean podePorBlocosAposInstrucao; // A instrução aceita blocos após ela (não é uma instrução final)
	protected final boolean podePorBlocosDentroInstrucao; // A instrução aceita blocos dentro dela, como por exemplo o "while"
	
	protected Bloco pai; // Bloco anterior a este
	
	protected final Class[] blocosNaAssinaturaAceitaveis; // Assinatura é a parte aonde possui textos e aceita sub blocos
	protected final Object[] blocosNaAssinatura; // Blocos que estão na assinatura
	
	protected Bloco proximoBlocoDentro; // Caso aceite blocos dentro da instrução, este é o próximo bloco dentro da instrução
	protected Bloco proximoBlocoInterno; // Caso seja uma instrução complexa como um se senão, é o proximo bloco (senão)
	protected Bloco proximoBloco; // Caso aceite blocos após a instrução, este é o próximo bloco
	
	protected int x;
	protected int y;
	
	public Bloco(boolean podePorBlocosAposInstrucao, boolean podePorBlocosDentroInstrucao, Class[] blocosNaAssinaturaAceitaveis) {
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
