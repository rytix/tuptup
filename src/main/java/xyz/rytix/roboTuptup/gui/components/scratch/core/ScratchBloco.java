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
	protected final boolean podePorBlocosAntesInstrucao; // A instrução aceita blocos antes da instrução
	protected final boolean podePorBlocosAposInstrucao; // A instrução aceita blocos após ela (não é uma instrução final)
	protected boolean ehUmBlocoExemplo;
	
	protected IComponent pai; // Component anterior a este
	
	protected final Class[] blocosNaAssinaturaAceitaveis; // Assinatura é a parte aonde possui textos e aceita sub blocos
	protected ScratchBloco[] blocosNaAssinatura; // Blocos que estão na assinatura
	
	protected ScratchBloco proximoBloco; // Caso aceite blocos após a instrução, este é o próximo bloco
	
	public int color;
	public int borderColor;
		
	public ScratchBloco(GuiBaseRoboTela gui, int left, int top,
			Class[] blocosNaAssinaturaAceitaveis,
			boolean podePorBlocosAntesInstrucao,
			boolean podePorBlocosAposInstrucao, 
			boolean ehUmBlocoExemplo) {
		super(gui,left,top);
		this.color = 0xFFFFFFFF;
		this.borderColor = 0xFFFFFFFF;
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
	
	public ScratchBloco getProximo(){
		return this.proximoBloco;
	}
	
	@Override
	public void draw(Tessellator tessellator) {
		VertexBuffer vertexBuffer = tessellator.getBuffer();
	    int left = getTrueLeft();
	    int top = getTrueTop();
	    
		GlStateManager.pushMatrix();
	    GlStateManager.pushAttrib();
        GlStateManager.disableTexture2D();
        
		DrawHelper.drawInstructionScratchBlock(tessellator,color,borderColor, left, top, getWidth(), getHeight(), podePorBlocosAntesInstrucao, podePorBlocosAposInstrucao);
		
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();	
        
        ScratchBloco[] blocos = getAllScratchBlocks();
        
		for(ScratchBloco sb: blocos){
			if(sb != null)
				sb.draw(tessellator);
		}
	}
	
	public abstract ScratchBloco action(TileEntityBaseRobo base);
	
	@Override
	public void draggablePre(int mouseX, int mouseY) {
		IComponent pai = getPai();
		if(pai != null && pai instanceof ScratchBloco){
			((ScratchBloco)pai).removePaiVinculo(this);
		}
	}
	
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
		if(mouseX > getTrueLeft() && mouseX < getTrueLeft() + getMaxWidth()){
			if(mouseY > getTrueTop() && mouseY < getTrueTop() + getTotalHeight()){
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

	protected void setWidthHeight(){
		int right = getLeft();
		int top = getTop();
		int height = 5; 
		for(ScratchBloco blocoNaAssinatura: blocosNaAssinatura){
			if(blocoNaAssinatura == null){
				continue;
			}
			blocoNaAssinatura.setLeft(right);
			right += blocoNaAssinatura.getWidth();
			if(height < blocoNaAssinatura.getHeight()){
				height = blocoNaAssinatura.getHeight();
			}
		}
		setWidth(right - getLeft());
		if(height != getHeight()){
			setHeight(height);
		}
	}
	
	public void refreshAll(){
		setWidthHeight();
		if(proximoBloco != null){
			proximoBloco.setTop(getHeight()+getTop());
			proximoBloco.setLeft(getLeft());
		}
		if(pai != null && pai instanceof ScratchBloco){
			((ScratchBloco)pai).refreshAll();
		}
	}
	
	public boolean addBloco(ScratchBloco bloco, int mouseX, int mouseY){
		//Bloco (Bloco para adicionar)
		//This (Bloco que irá adicionar)
		//TODO assinatura
		if(ehUmBlocoExemplo){
			return false;
		}
		return setProximoBloco(bloco);
	}
	
	public boolean removePaiVinculo(ScratchBloco bloco){
		int length = blocosNaAssinatura.length;
		bloco.setPai(null);
		for(int i = 0; i < length; i++){
			if(blocosNaAssinatura[i] == bloco){
				blocosNaAssinatura[i] = null;
				refreshAll();
				return true;
			}
		}
		if(proximoBloco == bloco){
			proximoBloco = null;
			refreshAll();
			return true;
		}
		return false;
	}
	
	public boolean addBlocoNaAssinatura(ScratchBloco bloco, int index){
		int lenght = blocosNaAssinatura.length;
				
		if(blocosNaAssinatura[index] == null && bloco.getClass().isAssignableFrom(blocosNaAssinaturaAceitaveis[index]))
		{			
			blocosNaAssinatura[index] = bloco;
			refreshAll();
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
	
	public boolean setProximoBloco(ScratchBloco proximoBloco) {
		if(proximoBloco == null || this.proximoBloco == null){
			this.proximoBloco = proximoBloco;
			this.proximoBloco.setPai(this);
			refreshAll();
			return true;
		}
		return false;
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
	public IComponent getPai() {
		return pai;
	}
	
	public int getMaxWidth(){
		int widthProximos = getWidth();
		if(proximoBloco != null){
			widthProximos = proximoBloco.getMaxWidth();
		}
		return getWidth() > widthProximos ? getWidth() : widthProximos;
	}
	public int getTotalHeight(){
		if(proximoBloco == null){
			return getHeight();
		}else{
			return getHeight() + proximoBloco.getTotalHeight();
		}
	}
	@Override
	public void setGui(GuiBaseRoboTela gui) {
		ScratchBloco[] blocos = getAllScratchBlocks();
		for(ScratchBloco bloco : blocos){
			if(bloco != null)
				bloco.setGui(gui);
		}
		super.setGui(gui);
	}
	public boolean isEhUmBlocoExemplo() {
		return ehUmBlocoExemplo;
	}
}
