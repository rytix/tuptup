package xyz.rytix.roboTuptup.gui.components.scratch;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class DrawHelper {
	public static Tessellator t;
	
	public static final int X_TRANSL_TOP_CONNECTOR = 10;
	public static final int HEIGHT_TOP_CONNECTOR = 4;
	public static final int WIDTH_TRIANGLE_TOP_CONNECTOR = 2;
	public static final int WIDTH_RECT_TOP_CONNECTOR = 6;
	public static void drawTopConnector(Tessellator t,int x,int y){
		x+=X_TRANSL_TOP_CONNECTOR;
		VertexBuffer vb = t.getBuffer();
		vb.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION);
		vb.pos((double)x+WIDTH_TRIANGLE_TOP_CONNECTOR, (double)y, 0.0D).endVertex();
		vb.pos((double)x, (double)y+HEIGHT_TOP_CONNECTOR, 0.0D).endVertex();
		vb.pos((double)x+WIDTH_TRIANGLE_TOP_CONNECTOR, (double)y+HEIGHT_TOP_CONNECTOR, 0.0D).endVertex();
		
		vb.pos((double)x+WIDTH_RECT_TOP_CONNECTOR+WIDTH_TRIANGLE_TOP_CONNECTOR, (double)y, 0.0D).endVertex();
		vb.pos((double)x+WIDTH_RECT_TOP_CONNECTOR+WIDTH_TRIANGLE_TOP_CONNECTOR, (double)y+HEIGHT_TOP_CONNECTOR, 0.0D).endVertex();
		vb.pos((double)x+WIDTH_RECT_TOP_CONNECTOR+WIDTH_TRIANGLE_TOP_CONNECTOR*2, (double)y+HEIGHT_TOP_CONNECTOR, 0.0D).endVertex();
		
		t.draw();
		vb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		
		vb.pos((double)x+WIDTH_TRIANGLE_TOP_CONNECTOR, (double)y+HEIGHT_TOP_CONNECTOR, 0.0D).endVertex();
		vb.pos((double)x+WIDTH_TRIANGLE_TOP_CONNECTOR+WIDTH_RECT_TOP_CONNECTOR, (double)y+HEIGHT_TOP_CONNECTOR, 0.0D).endVertex();
		vb.pos((double)x+WIDTH_TRIANGLE_TOP_CONNECTOR+WIDTH_RECT_TOP_CONNECTOR, (double)y, 0.0D).endVertex();
		vb.pos((double)x+WIDTH_TRIANGLE_TOP_CONNECTOR, (double)y, 0.0D).endVertex();
		
		t.draw();
	}
	
	public static final int HEIGHT_INST_BLOCK = 20;
	public static final int BASE_WIDTH_INST_BLOCK = 10;
	public static final int FRUFRU_INST_BLOCK = 2;
	public static void drawInstructionScratchBlock(Tessellator t,int x,int y, int contentWidth){
		y+=HEIGHT_TOP_CONNECTOR;
		int width = BASE_WIDTH_INST_BLOCK+contentWidth;
		VertexBuffer vb = t.getBuffer();
		vb.begin(GL11.GL_QUAD_STRIP, DefaultVertexFormats.POSITION);
		
		vb.pos((double)x, (double)y+FRUFRU_INST_BLOCK*2, 0.0D).endVertex();
		vb.pos((double)x, (double)y+HEIGHT_INST_BLOCK-FRUFRU_INST_BLOCK*2, 0.0D).endVertex();
		vb.pos((double)x+FRUFRU_INST_BLOCK, (double)y+FRUFRU_INST_BLOCK, 0.0D).endVertex();
		vb.pos((double)x+FRUFRU_INST_BLOCK, (double)y+HEIGHT_INST_BLOCK-FRUFRU_INST_BLOCK, 0.0D).endVertex();
		
		vb.pos((double)x+FRUFRU_INST_BLOCK*2, (double)y, 0.0D).endVertex();
		vb.pos((double)x+FRUFRU_INST_BLOCK*2, (double)y+HEIGHT_INST_BLOCK, 0.0D).endVertex();
		vb.pos((double)x+width, (double)y, 0.0D).endVertex();
		vb.pos((double)x+width, (double)y+HEIGHT_INST_BLOCK, 0.0D).endVertex();
		
		vb.pos((double)x+width+FRUFRU_INST_BLOCK, (double)y+FRUFRU_INST_BLOCK, 0.0D).endVertex();
		vb.pos((double)x+width+FRUFRU_INST_BLOCK, (double)y+HEIGHT_INST_BLOCK-FRUFRU_INST_BLOCK, 0.0D).endVertex();
		vb.pos((double)x+width+FRUFRU_INST_BLOCK*2, (double)y+FRUFRU_INST_BLOCK*2, 0.0D).endVertex();
		vb.pos((double)x+width+FRUFRU_INST_BLOCK*2, (double)y+HEIGHT_INST_BLOCK-FRUFRU_INST_BLOCK*2, 0.0D).endVertex();
		
		
		t.draw();

	}
	
}
