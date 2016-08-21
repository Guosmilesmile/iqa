package cn.edu.xmu.entity;

public class GraduationSituationDetails {
	
		//专业名称
		private String majorName;
		//应届毕业生数
		private int graduationNumber;
		//应届生中未按时毕业数
		private int ungraduatedNumber;
		//毕业率
		private double graduatedRate;
		//授予学位数
		private int degreeNumber;
		//学位授予率
		private double degreeRate;
		//应届毕业生就业人数
		private int employasmajorNumber;
		//初次就业率
		private double employasmajorRate;
		private String college;
		private int serialNumber;
		public GraduationSituationDetails(int serialNumber,String majorName, int graduationNumber, int ungraduatedNumber,
				double graduatedRate, int degreeNumber, double degreeRate, int employasmajorNumber,
				double employasmajorRate,String college) {
			super();
			this.serialNumber = serialNumber;
			this.majorName = majorName;
			this.graduationNumber = graduationNumber;
			this.ungraduatedNumber = ungraduatedNumber;
			this.graduatedRate = graduatedRate;
			this.degreeNumber = degreeNumber;
			this.degreeRate = degreeRate;
			this.employasmajorNumber = employasmajorNumber;
			this.employasmajorRate = employasmajorRate;
			this.college = college;
		}
		
		public int getSerialNumber() {
			return serialNumber;
		}

		public void setSerialNumber(int serialNumber) {
			this.serialNumber = serialNumber;
		}

		public String getMajorName() {
			return majorName;
		}
		public void setMajorName(String majorName) {
			this.majorName = majorName;
		}
		public int getGraduationNumber() {
			return graduationNumber;
		}
		public void setGraduationNumber(int graduationNumber) {
			this.graduationNumber = graduationNumber;
		}
		public int getUngraduatedNumber() {
			return ungraduatedNumber;
		}
		public void setUngraduatedNumber(int ungraduatedNumber) {
			this.ungraduatedNumber = ungraduatedNumber;
		}
		public double getGraduatedRate() {
			return graduatedRate;
		}
		public void setGraduatedRate(double graduatedRate) {
			this.graduatedRate = graduatedRate;
		}
		public int getDegreeNumber() {
			return degreeNumber;
		}
		public void setDegreeNumber(int degreeNumber) {
			this.degreeNumber = degreeNumber;
		}
		public double getDegreeRate() {
			return degreeRate;
		}
		public void setDegreeRate(double degreeRate) {
			this.degreeRate = degreeRate;
		}
		public int getEmployasmajorNumber() {
			return employasmajorNumber;
		}
		public void setEmployasmajorNumber(int employasmajorNumber) {
			this.employasmajorNumber = employasmajorNumber;
		}
		public double getEmployasmajorRate() {
			return employasmajorRate;
		}
		public void setEmployasmajorRate(double employasmajorRate) {
			this.employasmajorRate = employasmajorRate;
		}
		public String getCollege() {
			return college;
		}
		public void setCollege(String college) {
			this.college = college;
		}
		
		
}
