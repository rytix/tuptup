package xyz.rytix.roboTuptup.gui.components.scratch.controlBlocks;

import java.util.List;
import java.util.Stack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.components.Component;
import xyz.rytix.roboTuptup.gui.components.ScrollPanelComponent;
import xyz.rytix.roboTuptup.gui.components.scratch.DrawHelper;
import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBloco;
import xyz.rytix.roboTuptup.gui.interfaces.IComponent;
import xyz.rytix.roboTuptup.gui.interfaces.RightClickDraggable;
import xyz.rytix.roboTuptup.helper.TheObliteratorCustomFont;

public abstract class ScratchBlocoInstrucoesInternas extends ScratchBloco{
	protected final boolean podePorBlocosDentroInstrucao; // A instrução aceita blocos dentro dela, como por exemplo o "while"
	
	protected ScratchBloco proximoBlocoDentro; // Caso aceite blocos dentro da instrução, este é o próximo bloco dentro da instrução
	//protected ScratchBloco proximoBlocoInterno; // Caso seja uma instrução complexa como um se senão, é o proximo bloco (senão)
		
	private int signatureHeight = 0;
	private int bodyHeight = 0;
	
	private final int NO_BLOCK_BASE_HEIGHT = 15;
	
	public ScratchBlocoInstrucoesInternas(
			GuiBaseRoboTela gui,
			int left, int top,
			Class[] blocosNaAssinaturaAceitaveis,
			boolean podePorBlocosAntesInstrucao,
			boolean podePorBlocosAposInstrucao, 
			boolean podePorBlocosDentroInstrucao,
			boolean ehUmBlocoExemplo){
		super(gui,left,top,blocosNaAssinaturaAceitaveis,podePorBlocosAntesInstrucao,podePorBlocosAposInstrucao,ehUmBlocoExemplo);
		this.podePorBlocosDentroInstrucao = podePorBlocosDentroInstrucao;
	}	
	
	public abstract ScratchBlocoInstrucoesInternas action(TileEntityBaseRobo base);
	
	@Override
	public void draw(Tessellator tessellator) {
		VertexBuffer vertexBuffer = tessellator.getBuffer();
	    int left = getTrueLeft();
	    int top = getTrueTop();
	    
		GlStateManager.pushMatrix();
	    GlStateManager.pushAttrib();
        GlStateManager.disableTexture2D();
        
		//DrawHelper.drawInstructionScratchBlock(tessellator, left, top, getWidth(), getHeight(), podePorBlocosAntesInstrucao, podePorBlocosAposInstrucao);
		DrawHelper.drawInstructionScratchWithBlocksBlock(tessellator, left, top, getWidth(), signatureHeight, bodyHeight, podePorBlocosAntesInstrucao, podePorBlocosAposInstrucao);
		for(ScratchBloco bloco: blocosNaAssinatura){
			bloco.draw(tessellator);
		}
		
		
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();	
        
        ScratchBloco[] blocos = getAllScratchBlocks();
        
		for(ScratchBloco sb: blocos){
			if(sb != null)
				sb.draw(tessellator);
		}
	}
	
	@Override
	public IComponent getComponentOn(int mouseX, int mouseY){
		if(proximoBlocoDentro != null && proximoBlocoDentro.isMouseInside(mouseX, mouseY))
			return proximoBlocoDentro.getComponentOn(mouseX, mouseY);
		return super.getComponentOn(mouseX, mouseY);
	}
	
	//Add New Blocos
	public void refreshAll(){
		super.setWidthHeight();
		signatureHeight = getHeight();
		bodyHeight = proximoBlocoDentro != null ? proximoBlocoDentro.getTotalHeight() : 15;
		setHeight(signatureHeight+bodyHeight);
		
		if(proximoBlocoDentro != null){
			proximoBlocoDentro.setTop(signatureHeight+getTop());
			proximoBlocoDentro.setLeft(getLeft()+DrawHelper.GROSSURA_BORDA_INSTRUCAO_INTERNA);
		}
		
		if(proximoBloco != null){
			proximoBloco.setTop(getHeight()+getTop());
			proximoBloco.setLeft(getLeft());
		}
		if(pai != null && pai instanceof ScratchBloco){
			((ScratchBloco)pai).refreshAll();
		}
	}
	
	public boolean setProximoBlocoDentro(ScratchBloco proximoBlocoDentro) {
		if(proximoBlocoDentro == null || this.proximoBloco == null){
			this.proximoBlocoDentro = proximoBlocoDentro;
			this.proximoBlocoDentro.setPai(this);
			refreshAll();
			return true;
		}
		return false;
	}
	
	@Override
	public boolean addBloco(ScratchBloco bloco, int mouseX, int mouseY){
		if(mouseY < getTrueTop()+(getHeight()/2))
			return setProximoBlocoDentro(bloco);
		else
			return setProximoBloco(bloco);
	}
	//Getters
//	public boolean setProximoBloco(ScratchBloco proximoBloco) {
//		if(proximoBloco == null || this.proximoBloco == null){
//			this.proximoBloco = proximoBloco;
//			this.proximoBloco.setPai(this);
//			refreshAll();
//			return true;
//		}
//		return false;
//	}
	
	public boolean isPodePorBlocosDentroInstrucao() {
		return podePorBlocosDentroInstrucao;
	}
	
	@Override
	protected ScratchBloco[] getAllScratchBlocks(){
		ScratchBloco[] blocos = super.getAllScratchBlocks();
		ScratchBloco[] blocos2 = new ScratchBloco[blocos.length+1];
		int qtd = blocos.length;
		int i = 0;
		for(i = 0; i < qtd; i++){
			blocos2[i] = blocos[i];
		}
		blocos2[i] = proximoBlocoDentro;
		
		return blocos2;
	}
	
	public void setPai(IComponent pai) {
		this.pai = pai;
	}
	
	public void setEhUmBlocoExemplo(boolean ehUmBlocoExemplo) {
		this.ehUmBlocoExemplo = ehUmBlocoExemplo;
	}
}