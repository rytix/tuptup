package xyz.rytix.roboTuptup.gui.components.scratch;

import java.util.List;
import java.util.Stack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;
import xyz.rytix.roboTuptup.gui.components.Component;
import xyz.rytix.roboTuptup.gui.components.ScrollPanelComponent;
import xyz.rytix.roboTuptup.gui.interfaces.IComponent;
import xyz.rytix.roboTuptup.gui.interfaces.RightClickDraggable;
import xyz.rytix.roboTuptup.helper.TheObliteratorCustomFont;

public abstract class ScratchBloco extends Component implements RightClickDraggable{
	protected final boolean podePorBlocosAposInstrucao; // A instrução aceita blocos após ela (não é uma instrução final)
	protected final boolean podePorBlocosDentroInstrucao; // A instrução aceita blocos dentro dela, como por exemplo o "while"
	protected final boolean podePorBlocosAntesInstrucao; // A instrução aceita blocos antes da instrução
	protected final boolean ehUmBlocoExemplo;
	
	protected IComponent pai; // Component anterior a este
	
	protected final Class[] blocosNaAssinaturaAceitaveis; // Assinatura é a parte aonde possui textos e aceita sub blocos
	protected ScratchBloco[] blocosNaAssinatura; // Blocos que estão na assinatura
	
	protected ScratchBloco proximoBlocoDentro; // Caso aceite blocos dentro da instrução, este é o próximo bloco dentro da instrução
	//protected ScratchBloco proximoBlocoInterno; // Caso seja uma instrução complexa como um se senão, é o proximo bloco (senão)
	protected ScratchBloco proximoBloco; // Caso aceite blocos após a instrução, este é o próximo bloco
		
	private int oldMouseX = 0;
	private int oldMouseY = 0;
	
	private int signatureHeight = 0;
	
	public ScratchBloco(boolean podePorBlocosAposInstrucao, 
			boolean podePorBlocosDentroInstrucao,
			boolean podePorBlocosAntesInstrucao,
			boolean ehUmBlocoExemplo,
			Class[] blocosNaAssinaturaAceitaveis, 
			GuiBaseRoboTela gui,
			int left, int top) {
		super(gui);
		setLeft(left);
		setTop(top);
		this.podePorBlocosAposInstrucao = podePorBlocosAposInstrucao;
		this.podePorBlocosDentroInstrucao = podePorBlocosDentroInstrucao;
		this.podePorBlocosAntesInstrucao = podePorBlocosAntesInstrucao;
		this.ehUmBlocoExemplo = ehUmBlocoExemplo;
		this.blocosNaAssinaturaAceitaveis = blocosNaAssinaturaAceitaveis;
		if(blocosNaAssinaturaAceitaveis == null){
			this.blocosNaAssinatura = null;
		}else{
			this.blocosNaAssinatura = new ScratchBloco[blocosNaAssinaturaAceitaveis.length];
		}
	}	
	
	public abstract void action();
	
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
	public void draggablePre(int mouseX, int mouseY) {
		oldMouseX = mouseX;
		oldMouseY = mouseY;
	}
	
	@Override
	public void draggableAction(int mouseX, int mouseY) {
		setLeft(mouseX - GUI.getGuiLeft());
		setTop(mouseY - GUI.getGuiTop());
	}
	
	@Override
	public void draggablePos(int mouseX, int mouseY) {
		IComponent component = GUI.getComponentOn(mouseX, mouseY);
		if(component != null && component instanceof ScrollPanelComponent){
			((ScrollPanelComponent)component).addComponent(this);
		}
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
		if(proximoBlocoDentro != null && proximoBlocoDentro.isMouseInside(mouseX, mouseY))
			return proximoBlocoDentro.getComponentOn(mouseX, mouseY);
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
		width = left;
		if(height != getSignatureHeight()){
			setSignatureHeight(height);
			if(proximoBlocoDentro != null)
				proximoBlocoDentro.setTop(getSignatureHeight()+getTop());
			if(proximoBloco != null)
				proximoBloco.setTop(getHeight()+getTop());
		}
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
	//Getters
	public int getSignatureHeight() {
		return signatureHeight + DrawHelper.TOP_BOTTOM_SPACE_BLOCK;
	}
	public void setSignatureHeight(int signatureHeight) {
		this.signatureHeight = signatureHeight;
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
	@Override
	public int getHeight() {
		return height + signatureHeight;
	}
	@Override
	public int getWidth() {
		return width;
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
	
	private ScratchBloco[] getAllScratchBlocks(){
		int blocosAssLenght = 0;
		if(blocosNaAssinatura != null)
			blocosAssLenght = blocosNaAssinatura.length;
		int i;
		ScratchBloco[] sbs = new ScratchBloco[blocosAssLenght + 2];
		for(i = 0; i < blocosAssLenght; i++){
			sbs[i] = blocosNaAssinatura[i];
		}
		sbs[i] = proximoBlocoDentro;
		i++;
		sbs[i] = proximoBloco;
		return sbs;
	}
}
