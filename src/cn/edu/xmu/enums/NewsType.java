package cn.edu.xmu.enums;

public enum NewsType {
	本科评估(0), 课程评价(1), 学习调查(2), 教学状态(3), 课堂听课(4), 简介(5), 新闻(6), 教学研究(7);

	private int typeValue;

	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}

	private NewsType(int typeValue) {
		this.setTypeValue(typeValue);
	}

}
