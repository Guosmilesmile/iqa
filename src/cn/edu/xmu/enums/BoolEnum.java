package cn.edu.xmu.enums;

public enum BoolEnum {
	否(0),是(1);
	private int typeValue;

	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}

	private BoolEnum(int typeValue) {
		this.typeValue = typeValue;
	}
}
