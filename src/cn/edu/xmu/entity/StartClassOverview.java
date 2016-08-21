package cn.edu.xmu.entity;

/**
 * 3.6 各教学单位课程开设情况
 * @author chunfeng
 *
 */
public class StartClassOverview implements Comparable<StartClassOverview>{
	    private int serialNumber;
	    private String departName; //单位
	    private Integer shuangyuTeacher; //开设双语课程教师数
	    private Float stuPerMajorclass; //专业课平均学生数
	    private Float hourPerTeacher; //教授、副教授人均授课学时数
	    
	    @Override
		public int compareTo(StartClassOverview arg0) {
			// TODO Auto-generated method stub
			return this.getDepartName().compareTo(arg0.getDepartName());
		}
	    
	    public StartClassOverview(int serialNumber, String departName, Integer shuangyuTeacher,
	    		Float stuPerMajorclass, Float hourPerTeacher){
	    	this.serialNumber = serialNumber;
		    this.departName = departName; //单位
		    this.shuangyuTeacher = shuangyuTeacher; //开设双语课程教师数
		    this.stuPerMajorclass = stuPerMajorclass; //专业课平均学生数
		    this.hourPerTeacher = hourPerTeacher;
	    }

		public int getSerialNumber() {
			return serialNumber;
		}

		public void setSerialNumber(int serialNumber) {
			this.serialNumber = serialNumber;
		}

		public String getDepartName() {
			return departName;
		}

		public void setDepartName(String departName) {
			this.departName = departName;
		}

		public Integer getShuangyuTeacher() {
			return shuangyuTeacher;
		}

		public void setShuangyuTeacher(Integer shuangyuTeacher) {
			this.shuangyuTeacher = shuangyuTeacher;
		}

		public Float getStuPerMajorclass() {
			return stuPerMajorclass;
		}

		public void setStuPerMajorclass(Float stuPerMajorclass) {
			this.stuPerMajorclass = stuPerMajorclass;
		}

		public Float getHourPerTeacher() {
			return hourPerTeacher;
		}

		public void setHourPerTeacher(Float hourPerTeacher) {
			this.hourPerTeacher = hourPerTeacher;
		}
	    
	    
}
