package rms;

import java.io.Serializable;

public class Status implements Serializable{
	private int Id;
	private int unitId;
	private int HP;
	private int AP;
	private int DP;
	private int RP;
	private int rarity;
	
	public Status (int HP, int AP, int DP, int RP, int unitId, int rarity) {
		this.AP = AP;
		this.DP = DP;
		this.HP = HP;
		this.RP = RP;
		this.unitId = unitId;
		this.rarity = rarity;
	}
}
