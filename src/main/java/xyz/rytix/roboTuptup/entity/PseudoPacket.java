package xyz.rytix.roboTuptup.entity;

import java.util.List;

import xyz.rytix.roboTuptup.gui.components.scratch.core.ScratchBloco;

public class PseudoPacket {
	public final int x;
	public final int y;
	public final int z;
	public final List<ScratchBloco> blocos;
	public PseudoPacket(int x, int y, int z, List<ScratchBloco> blocos) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.blocos = blocos;
	}
	
}
