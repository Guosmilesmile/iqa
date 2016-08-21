package cn.edu.xmu.enums;
/**
 * 工作单位类别
 * @author zhantu
 *
 */
public enum GongZuoDanWeiLeiBie {
	行政单位(0), 科研单位(1), 高等学校(2), 其他事业单位(3), 企业单位(4), 部队(5), 其他单位(6);
	
	private int typeValue;

	private GongZuoDanWeiLeiBie(int typeValue) {
		this.setTypeValue(typeValue);
	}

	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}
}
