package cn.edu.xmu.enums;

public enum ExchangeLevel {
	院级(0),校级(1);
	private int typeValue;

	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}

	private ExchangeLevel(int typeValue) {
		this.typeValue = typeValue;
	}

}
