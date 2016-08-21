package cn.edu.xmu.enums;

public enum ShiZiLeiBie {
	其他师资(0), 实验技术人员(1);
	private int typeValue;

	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}

	private ShiZiLeiBie(int typeValue) {
		this.typeValue = typeValue;
	}
}
