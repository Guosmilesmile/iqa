package cn.edu.xmu.enums;

//学历选项还没确定
public enum XueLi {
	博士研究生(1),大学本科(2),硕士研究生(3),专科生及以下(4);
	private int typeValue;

	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}

	private XueLi(int typeValue) {
		this.typeValue = typeValue;
	}

}
