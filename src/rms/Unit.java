package rms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Unit implements Serializable{
	private String unitName;
	private int unitRarity;
	private int unitId;
	private int unitBBlvl;
	private int unitExp;
	private int unitLvl;
	private boolean unithasSBB;
	private boolean unithasUBB;
	private String unitTypeName;
	private Status unitStatus;
	private Type unitType;
	
	public Unit(String name, int exp, int bblvl, String type){
		this.unitName = name;
		this.unitExp = exp;
		this.unitBBlvl = bblvl;
		this.unitTypeName = type;
		this.unithasSBB = false;
		this.unithasUBB = false;	
		this.unitLvl = SetLevel(exp);
		List<Boolean> booleanResult = setBB(bblvl);
		this.unithasSBB = booleanResult.get(0);
		this.unithasUBB = booleanResult.get(1);
	}
	
	public String getName() {
		return unitName;
	}
	
	public int getExp() {
		return unitExp;
	}
	
	public int getBblvl() {
		return unitBBlvl;
	}
	
	public void setType(Type type) {
		this.unitType = type;
	}
	
	public Type getType() {
		return unitType;
	}
	
	public String getTypeName() {
		return unitTypeName;
	}
	
	public void setRarity (int rarity) {
		this.unitRarity = rarity;
	}
	
	public int rarity() {
		return unitRarity;
	}
	
	public void setId (int id) {
		this.unitId = id;
	}
	
	public int getId() {
		return unitId;
	}
	
	public void setStatus (Status status) {
		this.unitStatus = status;
	}
	
	public Status getStatus() {
		return unitStatus;
	}
	
	private int SetLevel(int exp) {
		return (exp / 100)+1;
	}
	
	private List<Boolean> setBB (int bbLvl) {
		List<Boolean> list=new ArrayList<Boolean>(Arrays.asList(new Boolean[2]));
		Collections.fill(list, Boolean.FALSE);
		int bb = bbLvl / 10;
		if (bb > 0) {
			list.add(0, true);
			if (bb > 1) {
				list.add(1,true);
			}
		}
		return list;
	}

}
