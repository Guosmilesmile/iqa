package cn.edu.xmu.enums;

public enum ZuiGaoXueWei {
	无学位(0),学士(1),硕士(2),博士(3);
	private int typeValue;

	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}

	private ZuiGaoXueWei(int typeValue) {
		this.typeValue = typeValue;
	}
}
