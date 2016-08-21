package cn.edu.xmu.enums;
/**
 * 地区
 * @author zhantu
 *
 */
public enum DiQu {
	境内(0), 境外_国外及港澳台(1);
	
	private int typeValue;

	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}

	private DiQu(int typeValue) {
		this.setTypeValue(typeValue);
	}
}
