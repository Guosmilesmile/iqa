package cn.edu.xmu.enums;

public enum XueYuan {
	本校(0),境内外校(1),境外外校(2);
	private int typeValue;

	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}

	private XueYuan(int typeValue) {
		this.typeValue = typeValue;
	}
	
}
