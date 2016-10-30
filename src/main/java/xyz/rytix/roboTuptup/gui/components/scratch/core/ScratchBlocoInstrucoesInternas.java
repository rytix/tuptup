/*
package xyz.rytix.roboTuptup.gui.components.scratch.core;

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
import xyz.rytix.roboTuptup.gui.interfaces.IComponent;
import xyz.rytix.roboTuptup.gui.interfaces.RightClickDraggable;
import xyz.rytix.roboTuptup.helper.TheObliteratorCustomFont;

public abstract class ScratchBlocoInstrucoesInternas extends ScratchBloco{
	protected final boolean podePorBlocosDentroInstrucao; // A instrução aceita blocos dentro dela, como por exemplo o "while"
	
	protected ScratchBlocoInstrucoesInternas proximoBlocoDentro; // Caso aceite blocos dentro da instrução, este é o próximo bloco dentro da instrução
	//protected ScratchBloco proximoBlocoInterno; // Caso seja uma instrução complexa como um se senão, é o proximo bloco (senão)
		
	private int signatureHeight = 0;
	private int bodyHeight = 0;
	
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
	
	public ScratchBlocoInstrucoesInternas returnAction(TileEntityBaseRobo base){
		if(pai != null && pai instanceof ScratchBlocoInstrucoesInternas){
			return ((ScratchBlocoInstrucoesInternas)pai).returnAction(base);
		}
		return null;
	}
	
	@Override
	public void draw(Tessellator tessellator) {
		VertexBuffer vertexBuffer = tessellator.getBuffer();
	    int left = getTrueLeft();
	    int top = getTrueTop();
	    
		GlStateManager.pushMatrix();
	    GlStateManager.pushAttrib();
        GlStateManager.disableTexture2D();
        
		DrawHelper.drawInstructionScratchBlock(tessellator, left, top, getWidth(), getHeight(), podePorBlocosAntesInstrucao, podePorBlocosAposInstrucao);
		for(ScratchBloco bloco: blocosNaAssinatura){
			bloco.draw(tessellator);
		}
		
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();	
		//
		VertexBuffer vertexBuffer = tessellator.getBuffer();
	    int left = getTrueLeft();
	    int top = getTrueTop();
	    
		GlStateManager.pushMatrix();
	    GlStateManager.pushAttrib();
        GlStateManager.disableTexture2D();
        
		DrawHelper.drawInstructionScratchBlock(tessellator, left, top, getWidth(), getHeight(), podePorBlocosAntesInstrucao, podePorBlocosAposInstrucao);
		for(ScratchBlocoInstrucoesInternas bloco: blocosNaAssinatura){
			bloco.draw(tessellator);
		}
		
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();		
	}
	
	@Override
	public IComponent getComponentOn(int mouseX, int mouseY){
		//???
		if(proximoBlocoDentro != null && proximoBlocoDentro.isMouseInside(mouseX, mouseY))
			return proximoBlocoDentro.getComponentOn(mouseX, mouseY);
		return this;
	}
	
	//Add New Blocos
	public void refreshAll(){
		//???
		int left = getLeft();
		int top = getTop();
		int height = 0; 
		for(ScratchBlocoInstrucoesInternas blocoNaAssinatura: blocosNaAssinatura){
			if(blocoNaAssinatura == null){
				continue;
			}
			blocoNaAssinatura.setLeft(left);
			left += blocoNaAssinatura.getWidth();
			if(height < blocoNaAssinatura.getHeight()){
				height = blocoNaAssinatura.getHeight();
			}
		}
		width = left - getLeft();
		if(height != getSignatureHeight()){
			setSignatureHeight(height);
			if(proximoBlocoDentro != null)
				proximoBlocoDentro.setTop(getSignatureHeight()+getTop());
			if(proximoBloco != null)
				proximoBloco.setTop(getHeight()+getTop());
		}
	}
	public boolean addBloco(ScratchBlocoInstrucoesInternas bloco, int mouseX, int mouseY){
		//Bloco (Bloco para adicionar)
		//This (Bloco que irá adicionar)
		//TODO interno
		return false;
	}
	//Getters
	
	public boolean isPodePorBlocosDentroInstrucao() {
		return podePorBlocosDentroInstrucao;
	}
	
	private ScratchBlocoInstrucoesInternas[] getAllScratchBlocks(){
		//???
		int blocosAssLenght = 0;
		if(blocosNaAssinatura != null)
			blocosAssLenght = blocosNaAssinatura.length;
		int i;
		ScratchBlocoInstrucoesInternas[] sbs = new ScratchBlocoInstrucoesInternas[blocosAssLenght + 2];
		for(i = 0; i < blocosAssLenght; i++){
			sbs[i] = blocosNaAssinatura[i];
		}
		sbs[i] = proximoBlocoDentro;
		i++;
		sbs[i] = proximoBloco;
		return sbs;
	}
	
	public void setPai(IComponent pai) {
		this.pai = pai;
	}
	
	public void setEhUmBlocoExemplo(boolean ehUmBlocoExemplo) {
		this.ehUmBlocoExemplo = ehUmBlocoExemplo;
	}
}
*/
