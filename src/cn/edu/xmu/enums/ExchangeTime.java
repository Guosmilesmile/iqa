package cn.edu.xmu.enums;

/**
 * 交流期限
 * @author chunfeng
 *
 */
public enum ExchangeTime {
	
	三个月以内(0), 三至六个月(1), 六至十二个月(2), 十二个月以上(3);
	
	private int typeValue;

	public int getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(int typeValue) {
		this.typeValue = typeValue;
	}

	private ExchangeTime(int typeValue) {
		this.typeValue = typeValue;
	}
}
