package cn.edu.xmu.enums;

//学科类别选项还没确定
public enum XueKeLeiBie {
	类别1(1),类别2(2);
	private int typeValue;

	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}

	private XueKeLeiBie(int typeValue) {
		this.typeValue = typeValue;
	}

}
