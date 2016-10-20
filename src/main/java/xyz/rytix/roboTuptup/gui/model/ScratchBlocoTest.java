package xyz.rytix.roboTuptup.gui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ScratchBlocoTest extends ScratchBloco{


	public ScratchBlocoTest() {
		super(false, false, null);
		int[] temp = {0x0099CD,0,100,100,100,100,0,0,0};
		drawPoints = temp;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillWithDrawPoints(Stack<int[]> stack) {
		stack.add(drawPoints);
	}

}
