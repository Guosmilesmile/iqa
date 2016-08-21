package cn.edu.xmu.enums;

public enum ZhuanYeJiShuZhiCheng {
	教授(1),副教授(2),讲师(3),助教(4),其他正高级(5),其他副高级(6),其他中级(7),其他初级(8),未评级(9);
	private int typeValue;

	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}

	private ZhuanYeJiShuZhiCheng(int typeValue) {
		this.typeValue = typeValue;
	}
	
}
