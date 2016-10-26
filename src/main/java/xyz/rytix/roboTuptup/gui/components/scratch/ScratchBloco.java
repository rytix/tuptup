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
import xyz.rytix.roboTuptup.gui.interfaces.RightClickDraggable;
import xyz.rytix.roboTuptup.gui.interfaces.ScratchDrawable;
import xyz.rytix.roboTuptup.helper.TheObliteratorCustomFont;

public abstract class ScratchBloco extends Component implements RightClickDraggable, ScratchDrawable{
	protected final boolean podePorBlocosAposInstrucao; // A instrução aceita blocos após ela (não é uma instrução final)
	protected final boolean podePorBlocosDentroInstrucao; // A instrução aceita blocos dentro dela, como por exemplo o "while"
	protected final boolean podePorBlocosAntesInstrucao; // A instrução aceita blocos antes da instrução
	protected final boolean ehUmBlocoExemplo;
	
	protected ScratchBloco pai; // Bloco anterior a este
	
	protected final Class[] blocosNaAssinaturaAceitaveis; // Assinatura é a parte aonde possui textos e aceita sub blocos
	protected ScratchBloco[] blocosNaAssinatura; // Blocos que estão na assinatura
	
	protected ScratchBloco proximoBlocoDentro; // Caso aceite blocos dentro da instrução, este é o próximo bloco dentro da instrução
	protected ScratchBloco proximoBlocoInterno; // Caso seja uma instrução complexa como um se senão, é o proximo bloco (senão)
	protected ScratchBloco proximoBloco; // Caso aceite blocos após a instrução, este é o próximo bloco
		
	private int oldMouseX = 0;
	private int oldMouseY = 0;
	
	public ScratchBloco(boolean podePorBlocosAposInstrucao, 
			boolean podePorBlocosDentroInstrucao,
			boolean podePorBlocosAntesInstrucao,
			boolean ehUmBlocoExemplo,
			Class[] blocosNaAssinaturaAceitaveis, 
			GuiBaseRoboTela gui,
			int left, int top) {
		super(gui);
		this.left = left;
		this.top = top;
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
        
		DrawHelper.drawInstructionScratchBlock(tessellator, left, top, getScratchThingWidth(), getScratchThingHeight(), podePorBlocosAntesInstrucao, podePorBlocosAposInstrucao);
		int leftBlocos = left;
		for(ScratchBloco bloco: blocosNaAssinatura){
			bloco.changeLeftTop(left, top);
			bloco.draw(tessellator);
			left += bloco.getScratchThingWidth();
		}
		
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();		
	}
	
	public void changeLeftTop(int left, int top){
		this.left = left;
		this.top = top;
	}
	
	@Override
	public int getScratchThingHeight() {
		int height = 0;
		int lenght = blocosNaAssinatura.length;
		for(ScratchDrawable d : blocosNaAssinatura){
			int sth = d.getScratchThingHeight();
			if(height < sth){
				height=sth;
			}
		}
		height += DrawHelper.TOP_BOTTOM_SPACE_BLOCK*2;
		return height;
	}
	@Override
	public void floatingBeforeDrop(RightClickDraggable component, int mouseX, int mouseY) {
		if(!(component instanceof ScratchBloco)){
			return;
		}
		ScratchBloco scratchComponent = (ScratchBloco) component;
		
		if(isOnAposInstrucao(scratchComponent, mouseX, mouseY)){
			System.out.println("AAA");
		}
		
	}
	
	@Override
	public void drop(RightClickDraggable component, int mouseX, int mouseY) {
		if(!(component instanceof ScratchBloco)){
			return;
		}
		ScratchBloco scratchComponent = (ScratchBloco) component;
		
	}
	
	public boolean isOnAposInstrucao (ScratchBloco sb, int mouseX,int mouseY){
		if(!podePorBlocosAposInstrucao || !sb.podePorBlocosAntesInstrucao)
			return false;
		if(mouseX > left + DrawHelper.LEFT_CONNECTOR && mouseX < left + DrawHelper.LEFT_CONNECTOR + DrawHelper.WIDTH_RECT_CONNECTOR + DrawHelper.WIDTH_TRIANGLE_CONNECTOR*2){
			if(mouseY > top - DrawHelper.HEIGHT_CONNECTOR && mouseY < top){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int getScratchThingWidth() {
		int width = 0;
		int lenght = blocosNaAssinatura.length;
		for(ScratchDrawable d : blocosNaAssinatura){
			width+=d.getScratchThingWidth();
		}
		width += DrawHelper.LEFT_RIGHT_SPACE_BLOCK*2;
		return width;
	}
	
	@Override
	public boolean isMouseInside(int mouseX, int mouseY) {
		if(mouseX > getTrueLeft() && mouseX < getTrueLeft() + getScratchThingWidth()){
			if(mouseY > getTrueTop() && mouseY < getTrueTop() + getScratchThingHeight()){
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
	
	public boolean addBlocoNaAssinatura(ScratchBloco bloco){
		int lenght = blocosNaAssinatura.length;
		
		for(int i = 0; i < lenght; i++){
			Class classeEsperada = blocosNaAssinaturaAceitaveis[i];
			
			if(blocosNaAssinatura[i] == null && bloco.getClass().isAssignableFrom(classeEsperada))
			{
				blocosNaAssinatura[i] = bloco;
				return true;
			}
		}
		return false;
	}
}
