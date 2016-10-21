package xyz.rytix.roboTuptup.gui.components;

import java.util.ArrayList;
import java.util.List;

import xyz.rytix.roboTuptup.gui.model.ScratchBloco;

public class ScrollPanelComponent {
	public boolean pressed;
	public int oldXMouse;
	public int oldYMouse;
	public int xScroll;
	public int yScroll;
	public boolean scrollingOnArea = false;

	public final int RELATIVE_LEFT;
	public final int RELATIVE_RIGHT;
	public final int RELATIVE_TOP;
	public final int RELATIVE_OPEN_GL_SCISSOR_TOP;
	public final int RELATIVE_BOTTOM;
	
	public final int WIDTH;
	public final int HEIGHT;
	
	public int left;
	public int right;
	public int top;
	public int openGLScissorTop;
	public int bottom;
	
	public final int SCROLL_WIDTH;
	public final int SCROLL_HEIGHT;	
	
	public ScrollPanelComponent(int relativeLeft, int relativeTop,
			int openGlScissorTop, int width, int height,
			int SCROLL_WIDTH, int SCROLL_HEIGHT, int guiLeft,
			int guiTop) 
	{
		this.RELATIVE_LEFT = relativeLeft;
		this.RELATIVE_RIGHT = relativeLeft+width;
		this.RELATIVE_TOP = relativeTop;
		this.RELATIVE_OPEN_GL_SCISSOR_TOP = openGlScissorTop;
		this.RELATIVE_BOTTOM = relativeTop+height;
		
		this.WIDTH = width;
		this.HEIGHT = height;
		
		this.SCROLL_WIDTH = SCROLL_WIDTH;
		this.SCROLL_HEIGHT = SCROLL_HEIGHT;
		
		this.pressed = false;
		this.xScroll = 0;
		this.yScroll = 0;
		this.scrollingOnArea = false;
		
		this.oldXMouse = 0;
		this.oldYMouse = 0;
		
		refreshGuiPosition(guiLeft, guiTop);
	}

	public void refreshGuiPosition(int guiLeft, int guiTop){
		left = RELATIVE_LEFT + guiLeft;
		right = RELATIVE_RIGHT + guiLeft;
		top =  RELATIVE_TOP + guiTop;
		openGLScissorTop = RELATIVE_OPEN_GL_SCISSOR_TOP + guiTop;
		bottom = RELATIVE_BOTTOM + guiTop;
	}
	
	public boolean isMouseInside(int mouseX, int mouseY){
		System.out.println(mouseX+" "+mouseY+" "+left+" "+top+" "+right+" "+bottom);
		if(mouseX >= left && mouseX <= right && mouseY >= top && mouseY <= bottom)
			return true;
		return false;
	}
	
	public void processScroll(int x,int y, boolean pressed){
		if(pressed){
			this.xScroll += (x - this.oldXMouse)*2;
			this.yScroll += (y - this.oldYMouse)*2;
			
			if(xScroll > 0){
				xScroll = 0;
			}else if(xScroll < -SCROLL_WIDTH + WIDTH){
				xScroll = -SCROLL_WIDTH + WIDTH;
			}
			if(yScroll > 0){
				yScroll = 0;
			}else if(yScroll < -SCROLL_HEIGHT + HEIGHT){
				yScroll = -SCROLL_HEIGHT + HEIGHT;
			}
		}
		this.oldXMouse = x;
		this.oldYMouse = y;
		this.pressed = pressed;
	}
}
