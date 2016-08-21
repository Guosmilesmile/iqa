package cn.edu.xmu.enums;
/**
 * 
 * @author Sihan
 * 
 */

public enum XingBie {
	男(0), 女(1), 未说明(2);
	
	private int typeValue;
	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}

	private XingBie(int typeValue) {
		this.typeValue = typeValue;
	}
}
