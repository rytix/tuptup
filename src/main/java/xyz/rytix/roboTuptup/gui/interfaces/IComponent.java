package xyz.rytix.roboTuptup.gui.interfaces;

import net.minecraft.client.renderer.Tessellator;
import xyz.rytix.roboTuptup.gui.GuiBaseRoboTela;

public interface IComponent {
	
	public IComponent getComponentOn(int mouseX,int mouseY);
	public boolean isMouseInside(int mouseX,int mouseY);
	public void draw(Tessellator tessellator);
	
	public int getTrueLeft();
	public int getTrueTop();
	
	public int getLeft();
	public void setLeft(int left);
	public int getTop();
	public void setTop(int top);
	
	public int getWidth();
	public int getHeight();
	public void setGui(GuiBaseRoboTela gui);
	
	//TODO getAllComponentsInside : List<IComponent>
}
