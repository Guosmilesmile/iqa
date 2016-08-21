package cn.edu.xmu.enums;

public enum MenuType {
	MAIN(1), SUB(2);
	private int typeValue;

	private MenuType(int typeValue) {
		this.setTypeValue(typeValue);
	}

	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}
}
