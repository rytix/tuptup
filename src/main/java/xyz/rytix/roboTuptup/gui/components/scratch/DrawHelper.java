
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
	
	private static void drawBottomConnector(Tessellator t,int color,int x,int y,int height){
		setColor(color);
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
	public static void drawInstructionScratchBlock(Tessellator t,int color, int borderColor, int x,int y, int contentWidth, int contentHeight, boolean podePorInstrucoesAntes, boolean podePorInstrucoesDepois){
		drawInstructionScratchBlock(t,color,borderColor,x,y, contentWidth, contentHeight, podePorInstrucoesAntes, podePorInstrucoesDepois, true,0);
	}

	public static void drawInstructionScratchBlock(Tessellator t,int color, int borderColor,int x,int y, int contentWidth, int contentHeight, boolean podePorInstrucoesAntes, boolean podePorInstrucoesDepois, boolean frufruLeft, int recuoLeftConnector){
		int width = contentWidth + LEFT_RIGHT_SPACE_BLOCK;
		contentHeight += TOP_BOTTOM_SPACE_BLOCK*2;
		VertexBuffer vb = t.getBuffer();
				
		setColor(color);
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
			drawBottomConnector(t,color, x, y, contentHeight);
		}
		drawBorder(t, borderColor, x, y+contentHeight, contentWidth);
		
	}
	
	private static void drawBorder(Tessellator t, int borderColor, int left, int top, int width, int contentHeight){
		VertexBuffer vb = t.getBuffer();
		
		setColor(borderColor);
		vb.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
		vb.pos((double)left+8, (double)top+width, 0.0D).endVertex();
		vb.pos((double)left+4, (double)top+width, 0.0D).endVertex();
		vb.pos((double)left+4, (double)top+contentHeight-4, 0.0D).endVertex();
		t.draw();
	}
	
	private static void drawBorder(Tessellator t, int borderColor, int left, int top, int width){
		VertexBuffer vb = t.getBuffer();
		
		setColor(borderColor);
		vb.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
		vb.pos((double)left+FRUFRU_INST_BLOCK*2, (double)top, 0.0D).endVertex();
		vb.pos((double)left+LEFT_CONNECTOR, (double)top, 0.0D).endVertex();
		vb.pos((double)left+LEFT_CONNECTOR+WIDTH_TRIANGLE_CONNECTOR, (double)top+HEIGHT_CONNECTOR, 0.0D).endVertex();
		vb.pos((double)left+LEFT_CONNECTOR+WIDTH_TRIANGLE_CONNECTOR+WIDTH_RECT_CONNECTOR, (double)top+HEIGHT_CONNECTOR, 0.0D).endVertex();
		vb.pos((double)left+LEFT_CONNECTOR+WIDTH_TRIANGLE_CONNECTOR*2+WIDTH_RECT_CONNECTOR, (double)top, 0.0D).endVertex();
		vb.pos((double)left+width+1, (double)top, 0.0D).endVertex();
		vb.pos((double)left+width+FRUFRU_INST_BLOCK*2, (double)top-FRUFRU_INST_BLOCK*2, 0.0D).endVertex();

		t.draw();
	}
	
	public static void drawInstructionScratchWithBlocksBlock(Tessellator t,int color, int borderColor,int x,int y, int contentSgnatureWidth, int contentSignatureHeight, int contentBodyHeight, boolean podePorInstrucoesAntes, boolean podePorInstrucoesDepois){
		VertexBuffer vb = t.getBuffer();
		drawInstructionScratchBlock(t,color,borderColor, x+GROSSURA_BORDA_INSTRUCAO_INTERNA, y, contentSgnatureWidth, contentSignatureHeight, podePorInstrucoesAntes, podePorInstrucoesDepois,false,GROSSURA_BORDA_INSTRUCAO_INTERNA);
		int totalHeight = contentSignatureHeight + contentBodyHeight;
		vb.begin(GL11.GL_QUAD_STRIP, DefaultVertexFormats.POSITION);
		setColor(color);
		
		vb.pos((double)x, (double)y, 0.0D).endVertex();
		vb.pos((double)x, (double)y+totalHeight, 0.0D).endVertex();
		vb.pos((double)x+GROSSURA_BORDA_INSTRUCAO_INTERNA, (double)y, 0.0D).endVertex();
		vb.pos((double)x+GROSSURA_BORDA_INSTRUCAO_INTERNA, (double)y+totalHeight, 0.0D).endVertex();
		
		vb.pos((double)x, (double)y+totalHeight-GROSSURA_BORDA_INSTRUCAO_INTERNA, 0.0D).endVertex();
		vb.pos((double)x, (double)y+totalHeight, 0.0D).endVertex();
		
		vb.pos((double)x+contentSgnatureWidth, (double)y+totalHeight-GROSSURA_BORDA_INSTRUCAO_INTERNA, 0.0D).endVertex();
		vb.pos((double)x+contentSgnatureWidth, (double)y+totalHeight, 0.0D).endVertex();
		
		vb.pos((double)x+contentSgnatureWidth+FRUFRU_INST_BLOCK*2+1, (double)y+totalHeight-GROSSURA_BORDA_INSTRUCAO_INTERNA, 0.0D).endVertex();
		vb.pos((double)x+contentSgnatureWidth, (double)y+totalHeight, 0.0D).endVertex();
		
		t.draw();
		drawBottomConnector(t,color,x,y,totalHeight);
		drawBorder(t, borderColor, x, y+totalHeight, contentSgnatureWidth);
		drawBorder(t, borderColor, x, y, contentSignatureHeight, totalHeight);
	}
	private static void setColor(int color){
		float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        
		GL11.glColor4f(f, f1, f2, f3);
	}
}
