package rms;

import java.io.Serializable;

public class Type implements Serializable{
	private String Name;
	private int id;
	private int healthMultiplier;
	private int attackMultiplier;
	private int defenceMultiplier;
	private int recoveryMultiplier;
	
	public Type (String name, int hM, int aM, int dM, int rM) {
		this.Name = name;
		this.attackMultiplier = aM;
		this.defenceMultiplier = dM;
		this.healthMultiplier = hM;
		this.recoveryMultiplier = rM;
	}

}
