package cn.edu.xmu.enums;
/**
 * 管理人员类别
 */
public enum GuanLiRenYuanLeiBie {

学生管理人员(0), 教学管理人员(1), 教学质量监控人员(2), 就业管理人员(3);
	
	private int typeValue;

	private GuanLiRenYuanLeiBie(int typeValue) {
		this.setTypeValue(typeValue);
	}

	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}
}


