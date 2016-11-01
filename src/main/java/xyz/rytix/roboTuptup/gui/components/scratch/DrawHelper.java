
package xyz.rytix.roboTuptup.gui.components.scratch;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class DrawHelper {
	public static final int LEFT_CONNECTOR = 10;
	public static final int HEIGHT_CONNECTOR = 3;
	public static final int WIDTH_TRIANGLE_CONNECTOR = 2;
	public static final int WIDTH_RECT_CONNECTOR = 4;
	
	public static final int TOP_BOTTOM_SPACE_BLOCK = 0;
	public static final int LEFT_RIGHT_SPACE_BLOCK = 1;
	public static final int FRUFRU_INST_BLOCK = 2;
	
	public static final int GROSSURA_BORDA_INSTRUCAO_INTERNA = 4;
	
	private static void drawBottomConnector(Tessellator t,int x,int y,int height){
		x+=LEFT_CONNECTOR;
		y+=height;
		VertexBuffer vb = t.getBuffer();
				
		vb.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION);
		vb.pos((double)x+WIDTH_TRIANGLE_CONNECTOR, (double)y, 0.0D).endVertex();
		vb.pos((double)x, (double)y, 0.0D).endVertex();
		vb.pos((double)x+WIDTH_TRIANGLE_CONNECTOR, (double)y+HEIGHT_CONNECTOR, 0.0D).endVertex();
	
		vb.pos((double)x+WIDTH_RECT_CONNECTOR+WIDTH_TRIANGLE_CONNECTOR, (double)y, 0.0D).endVertex();
		vb.pos((double)x+WIDTH_RECT_CONNECTOR+WIDTH_TRIANGLE_CONNECTOR, (double)y+HEIGHT_CONNECTOR, 0.0D).endVertex();
		vb.pos((double)x+WIDTH_RECT_CONNECTOR+WIDTH_TRIANGLE_CONNECTOR*2, (double)y, 0.0D).endVertex();
		
		t.draw();
		vb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		
		vb.pos((double)x+WIDTH_TRIANGLE_CONNECTOR, (double)y+HEIGHT_CONNECTOR, 0.0D).endVertex();
		vb.pos((double)x+WIDTH_TRIANGLE_CONNECTOR+WIDTH_RECT_CONNECTOR, (double)y+HEIGHT_CONNECTOR, 0.0D).endVertex();
		vb.pos((double)x+WIDTH_TRIANGLE_CONNECTOR+WIDTH_RECT_CONNECTOR, (double)y, 0.0D).endVertex();
		vb.pos((double)x+WIDTH_TRIANGLE_CONNECTOR, (double)y, 0.0D).endVertex();
		
		t.draw();
	}
	public static void drawInstructionScratchBlock(Tessellator t,int x,int y, int contentWidth, int contentHeight, boolean podePorInstrucoesAntes, boolean podePorInstrucoesDepois){
		drawInstructionScratchBlock(t,x,y, contentWidth, contentHeight, podePorInstrucoesAntes, podePorInstrucoesDepois, true,0);
	}

	public static void drawInstructionScratchBlock(Tessellator t,int x,int y, int contentWidth, int contentHeight, boolean podePorInstrucoesAntes, boolean podePorInstrucoesDepois, boolean frufruLeft, int recuoLeftConnector){
		int width = contentWidth + LEFT_RIGHT_SPACE_BLOCK;
		contentHeight += TOP_BOTTOM_SPACE_BLOCK*2;
		VertexBuffer vb = t.getBuffer();
				
		GL11.glColor4f(0/255.0F, 153/255.0F, 182/255.0F, 1.0F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		vb.begin(GL11.GL_QUAD_STRIP, DefaultVertexFormats.POSITION);
		if(frufruLeft){
		vb.pos((double)x, (double)y+FRUFRU_INST_BLOCK*2, 0.0D).endVertex();
		vb.pos((double)x, (double)y+contentHeight-FRUFRU_INST_BLOCK*2, 0.0D).endVertex();
		vb.pos((double)x+FRUFRU_INST_BLOCK, (double)y+FRUFRU_INST_BLOCK, 0.0D).endVertex();
		vb.pos((double)x+FRUFRU_INST_BLOCK, (double)y+contentHeight-FRUFRU_INST_BLOCK, 0.0D).endVertex();

		vb.pos((double)x+FRUFRU_INST_BLOCK*2, (double)y, 0.0D).endVertex();
		vb.pos((double)x+FRUFRU_INST_BLOCK*2, (double)y+contentHeight, 0.0D).endVertex();
		}else{
			vb.pos((double)x, (double)y, 0.0D).endVertex();
			vb.pos((double)x, (double)y+contentHeight, 0.0D).endVertex();
		}
		
		if(podePorInstrucoesAntes){
			int x2 = LEFT_CONNECTOR + x - recuoLeftConnector;
			int y2 = y;
			vb.pos((double)x2, (double)y, 0.0D).endVertex();
			vb.pos((double)x2, (double)y+contentHeight, 0.0D).endVertex();
			x2 += WIDTH_TRIANGLE_CONNECTOR;
			y2 += HEIGHT_CONNECTOR;
			vb.pos((double)x2, (double)y2, 0.0D).endVertex();
			vb.pos((double)x2, (double)y+contentHeight, 0.0D).endVertex();
			x2 += WIDTH_RECT_CONNECTOR;
			vb.pos((double)x2, (double)y2, 0.0D).endVertex();
			vb.pos((double)x2, (double)y+contentHeight, 0.0D).endVertex();
			x2 += WIDTH_TRIANGLE_CONNECTOR;
			y2 -= HEIGHT_CONNECTOR;
			vb.pos((double)x2, (double)y2, 0.0D).endVertex();
			vb.pos((double)x2, (double)y+contentHeight, 0.0D).endVertex();
		}
		
		vb.pos((double)x+width, (double)y, 0.0D).endVertex();
		vb.pos((double)x+width, (double)y+contentHeight, 0.0D).endVertex();
		
		vb.pos((double)x+width+FRUFRU_INST_BLOCK, (double)y+FRUFRU_INST_BLOCK, 0.0D).endVertex();
		vb.pos((double)x+width+FRUFRU_INST_BLOCK, (double)y+contentHeight-FRUFRU_INST_BLOCK, 0.0D).endVertex();
		vb.pos((double)x+width+FRUFRU_INST_BLOCK*2, (double)y+FRUFRU_INST_BLOCK*2, 0.0D).endVertex();
		vb.pos((double)x+width+FRUFRU_INST_BLOCK*2, (double)y+contentHeight-FRUFRU_INST_BLOCK*2, 0.0D).endVertex();
		
		
		t.draw();
		
		if(podePorInstrucoesDepois){
			drawBottomConnector(t, x, y, contentHeight);
		}
		
		GL11.glColor4f(0/255.0F, 124/255.0F, 205/255.0F, 1.0F);
		vb.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
		vb.pos((double)x+FRUFRU_INST_BLOCK*2, (double)y+contentHeight-1, 0.0D).endVertex();
		vb.pos((double)x+LEFT_CONNECTOR, (double)y+contentHeight-1, 0.0D).endVertex();
		vb.pos((double)x+LEFT_CONNECTOR+WIDTH_TRIANGLE_CONNECTOR, (double)y+contentHeight+HEIGHT_CONNECTOR-1, 0.0D).endVertex();
		vb.pos((double)x+LEFT_CONNECTOR+WIDTH_TRIANGLE_CONNECTOR+WIDTH_RECT_CONNECTOR, (double)y+contentHeight+HEIGHT_CONNECTOR-1, 0.0D).endVertex();
		vb.pos((double)x+LEFT_CONNECTOR+WIDTH_TRIANGLE_CONNECTOR*2+WIDTH_RECT_CONNECTOR, (double)y+contentHeight-1, 0.0D).endVertex();
		vb.pos((double)x+contentWidth+1, (double)y+contentHeight-1, 0.0D).endVertex();
		vb.pos((double)x+contentWidth+FRUFRU_INST_BLOCK*2, (double)y+contentHeight-FRUFRU_INST_BLOCK*2, 0.0D).endVertex();

		t.draw();
	}
	
	public static void drawInstructionScratchWithBlocksBlock(Tessellator t,int x,int y, int contentSgnatureWidth, int contentSignatureHeight, int contentBodyHeight, boolean podePorInstrucoesAntes, boolean podePorInstrucoesDepois){
		VertexBuffer vb = t.getBuffer();
		drawInstructionScratchBlock(t, x+GROSSURA_BORDA_INSTRUCAO_INTERNA, y, contentSgnatureWidth, contentSignatureHeight, podePorInstrucoesAntes, podePorInstrucoesDepois,false,GROSSURA_BORDA_INSTRUCAO_INTERNA);
		GL11.glColor4f(0/255.0F, 153/255.0F, 182/255.0F, 1.0F);
		int totalHeight = contentSignatureHeight + contentBodyHeight;
		vb.begin(GL11.GL_QUAD_STRIP, DefaultVertexFormats.POSITION);
		
		vb.pos((double)x, (double)y, 0.0D).endVertex();
		vb.pos((double)x, (double)y+totalHeight, 0.0D).endVertex();
		vb.pos((double)x+GROSSURA_BORDA_INSTRUCAO_INTERNA, (double)y, 0.0D).endVertex();
		vb.pos((double)x+GROSSURA_BORDA_INSTRUCAO_INTERNA, (double)y+totalHeight, 0.0D).endVertex();
		
		vb.pos((double)x, (double)y+totalHeight-GROSSURA_BORDA_INSTRUCAO_INTERNA, 0.0D).endVertex();
		vb.pos((double)x, (double)y+totalHeight, 0.0D).endVertex();
		
		vb.pos((double)x+contentSgnatureWidth, (double)y+totalHeight-GROSSURA_BORDA_INSTRUCAO_INTERNA, 0.0D).endVertex();
		vb.pos((double)x+contentSgnatureWidth, (double)y+totalHeight, 0.0D).endVertex();
		
		t.draw();
		drawBottomConnector(t,x,y,totalHeight);
	}
}
