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

public abstract class ScratchBloco extends Component implements RightClickDraggable{
	protected final boolean podePorBlocosAntesInstrucao; // A instru��o aceita blocos antes da instru��o
	protected final boolean podePorBlocosAposInstrucao; // A instru��o aceita blocos ap�s ela (n�o � uma instru��o final)
	protected boolean ehUmBlocoExemplo;
	
	protected IComponent pai; // Component anterior a este
	
	protected final Class[] blocosNaAssinaturaAceitaveis; // Assinatura � a parte aonde possui textos e aceita sub blocos
	protected ScratchBloco[] blocosNaAssinatura; // Blocos que est�o na assinatura
	
	protected ScratchBloco proximoBloco; // Caso aceite blocos ap�s a instru��o, este � o pr�ximo bloco
		
	public ScratchBloco(GuiBaseRoboTela gui, int left, int top,
			Class[] blocosNaAssinaturaAceitaveis,
			boolean podePorBlocosAntesInstrucao,
			boolean podePorBlocosAposInstrucao, 
			boolean ehUmBlocoExemplo) {
		super(gui,left,top);
		this.podePorBlocosAposInstrucao = podePorBlocosAposInstrucao;
		this.podePorBlocosAntesInstrucao = podePorBlocosAntesInstrucao;
		this.ehUmBlocoExemplo = ehUmBlocoExemplo;
		this.blocosNaAssinaturaAceitaveis = blocosNaAssinaturaAceitaveis;
		if(blocosNaAssinaturaAceitaveis == null){
			this.blocosNaAssinatura = null;
		}else{
			this.blocosNaAssinatura = new ScratchBloco[blocosNaAssinaturaAceitaveis.length];
		}
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
	}
	
	public abstract ScratchBloco action(TileEntityBaseRobo base);
	
	@Override
	public void draggablePre(int mouseX, int mouseY) {}
	
	public ScratchBloco returnAction(TileEntityBaseRobo base){
		if(pai != null && pai instanceof ScratchBloco){
			return ((ScratchBloco)pai).returnAction(base);
		}
		return null;
	}
	
	public boolean isOnAposInstrucao (ScratchBloco sb, int mouseX,int mouseY){
		if(!podePorBlocosAposInstrucao || !sb.podePorBlocosAntesInstrucao)
			return false;
		if(mouseX > getLeft() + DrawHelper.LEFT_CONNECTOR && mouseX < getLeft() + DrawHelper.LEFT_CONNECTOR + DrawHelper.WIDTH_RECT_CONNECTOR + DrawHelper.WIDTH_TRIANGLE_CONNECTOR*2){
			if(mouseY > getTop() - DrawHelper.HEIGHT_CONNECTOR && mouseY < getTop()){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isMouseInside(int mouseX, int mouseY) {
		if(mouseX > getTrueLeft() && mouseX < getTrueLeft() + getWidth()){
			if(mouseY > getTrueTop() && mouseY < getTrueTop() + getHeight()){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void draggableAction(int mouseX, int mouseY) {
		setLeft(mouseX - gui.getGuiLeft());
		setTop(mouseY - gui.getGuiTop());
	}
	
	@Override
	public void draggablePos(int mouseX, int mouseY) {
		IComponent component = gui.getComponentOn(mouseX, mouseY);
		if(component != null){
			if(component instanceof ScrollPanelComponent){
				((ScrollPanelComponent)component).addComponent(this);
			}
			if(component instanceof ScratchBloco){
				if(!((ScratchBloco)component).addBloco(this, mouseX, mouseY)){
					gui.getPanelOn(mouseX, mouseY).addComponent(this);
				}
			}
		}
	}
	
	public abstract ScratchBloco createNewScratchBlock();
	
	@Override
	public RightClickDraggable getDraggableObject(int mouseX, int mouseY) {
		if(ehUmBlocoExemplo){
			return createNewScratchBlock();
		}
		return this;
	}
	
	@Override
	public IComponent getComponentOn(int mouseX, int mouseY){
		for(IComponent component: blocosNaAssinatura){
			if(component.isMouseInside(mouseX, mouseY)){
				return component.getComponentOn(mouseX, mouseY);
			}
		}
		if(proximoBloco != null && proximoBloco.isMouseInside(mouseX, mouseY))
			return proximoBloco.getComponentOn(mouseX, mouseY);
		return this;
	}
	
	//Add New Blocos
	public void refreshAll(){
		int left = getLeft();
		int top = getTop();
		int height = 0; 
		for(ScratchBloco blocoNaAssinatura: blocosNaAssinatura){
			if(blocoNaAssinatura == null){
				continue;
			}
			blocoNaAssinatura.setLeft(left);
			left += blocoNaAssinatura.getWidth();
			if(height < blocoNaAssinatura.getHeight()){
				height = blocoNaAssinatura.getHeight();
			}
		}
		setWidth(left - getLeft());
		if(height != getHeight()){
			setHeight(height);
			if(proximoBloco != null)
				proximoBloco.setTop(getHeight()+getTop());
		}
	}
	public boolean addBloco(ScratchBloco bloco, int mouseX, int mouseY){
		//Bloco (Bloco para adicionar)
		//This (Bloco que ir� adicionar)
		//TODO assinatura
		//TODO interno
		return false;
	}
	
	public boolean addBlocoNaAssinatura(ScratchBloco bloco, int index){
		int lenght = blocosNaAssinatura.length;
				
		if(blocosNaAssinatura[index] == null && bloco.getClass().isAssignableFrom(blocosNaAssinaturaAceitaveis[index]))
		{			
			blocosNaAssinatura[index] = bloco;
			refreshAll();
			if(pai != null && pai instanceof ScratchBloco){
				((ScratchBloco)pai).refreshAll();
			}
			return true;
		}
		return false;
	}
	//Getters and Setters
	public boolean isPodePorBlocosAposInstrucao() {
		return podePorBlocosAposInstrucao;
	}
	public boolean isPodePorBlocosAntesInstrucao() {
		return podePorBlocosAntesInstrucao;
	}
	protected ScratchBloco[] getAllScratchBlocks(){
		int blocosAssLenght = 0;
		if(blocosNaAssinatura != null)
			blocosAssLenght = blocosNaAssinatura.length;
		int i;
		ScratchBloco[] sbs = new ScratchBloco[blocosAssLenght + 2];
		for(i = 0; i < blocosAssLenght; i++){
			sbs[i] = blocosNaAssinatura[i];
		}
		sbs[i] = proximoBloco;
		return sbs;
	}
	
	@Override
	public void setLeft(int left) {
		int dif = left - this.getLeft();
		ScratchBloco[] sbs = getAllScratchBlocks();
		for(ScratchBloco sb: sbs){
			if(sb == null)
				continue;
			sb.setLeft(sb.getLeft() + dif);
		}
		super.setLeft(left);
	}
	
	@Override
	public void setTop(int top) {
		int dif = top - this.getTop();
		ScratchBloco[] sbs = getAllScratchBlocks();
		for(ScratchBloco sb: sbs){
			if(sb == null)
				continue;
			sb.setTop(sb.getTop() + dif);
		}
		super.setTop(top);
	}
	
	public void setPai(IComponent pai) {
		this.pai = pai;
	}
}
