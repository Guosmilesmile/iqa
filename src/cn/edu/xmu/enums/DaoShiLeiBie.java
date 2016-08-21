package cn.edu.xmu.enums;

public enum DaoShiLeiBie {
	无(0),硕士导师(1),博士导师(2),博士硕士导师(3);
	private int typeValue;

	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}

	private DaoShiLeiBie(int typeValue) {
		this.typeValue = typeValue;
	}
	
	
}
