package cn.edu.xmu.enums;

public enum WorkingState {
	当年离职(0),在职(1);
	private int typeValue;

	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}

	private WorkingState(int typeValue) {
		this.typeValue = typeValue;
	}

	
}
