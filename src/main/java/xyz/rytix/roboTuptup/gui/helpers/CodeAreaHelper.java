package xyz.rytix.roboTuptup.gui.helpers;

public class CodeAreaHelper {
	public int x;
	public int y;
	public boolean pressed;
	public int xScroll;
	public int yScroll;
	
	public static final int[] baseCoordsAndSizeCodeArea= {103,124,152,118};
	public static final int LARGURA = 500;
	public static final int ALTURA = 500;
	public static final int GROSSURA = 2;
		
	public CodeAreaHelper(int x,int y) {
		this.x = x;
		this.y = y;
		pressed = false;
	}
	
	public void processScroll(int x,int y, boolean pressed){
		if(pressed){
			this.xScroll += (x - this.x)*2;
			this.yScroll += (y - this.y)*2;
			
			if(xScroll > 0){
				xScroll = 0;
			}else if(xScroll < -LARGURA + baseCoordsAndSizeCodeArea[2]){
				xScroll = -LARGURA + baseCoordsAndSizeCodeArea[2];
			}
			if(yScroll > 0){
				yScroll = 0;
			}else if(yScroll < -ALTURA + baseCoordsAndSizeCodeArea[3]){
				yScroll = -ALTURA + baseCoordsAndSizeCodeArea[3];
			}
		}
		this.x = x;
		this.y = y;
		this.pressed = pressed;
	}
}
