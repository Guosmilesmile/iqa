package cn.edu.xmu.entity;

/**
 * 
 * @author zsj
 * 6.1 评教信息
 */
public class TeachingQualityInfo {
	
	//项目
	private String tq_project;//相当于rowTitle
	//覆盖比例
	private float tq_coverpercent;
	//优秀
	private float tq_excellent;
	//良好
	private float tq_good;
	//中等
	private float tq_medium;
	//差
	private float tq_poor;
	//所属学院
	private String tq_college;
	public String getTq_project() {
		return tq_project;
	}
	public void setTq_project(String tq_project) {
		this.tq_project = tq_project;
	}
	public float getTq_coverpercent() {
		return tq_coverpercent;
	}
	public void setTq_coverpercent(float tq_coverpercent) {
		this.tq_coverpercent = tq_coverpercent;
	}
	public float getTq_excellent() {
		return tq_excellent;
	}
	public void setTq_excellent(float tq_excellent) {
		this.tq_excellent = tq_excellent;
	}
	public float getTq_good() {
		return tq_good;
	}
	public void setTq_good(float tq_good) {
		this.tq_good = tq_good;
	}
	public float getTq_medium() {
		return tq_medium;
	}
	public void setTq_medium(float tq_medium) {
		this.tq_medium = tq_medium;
	}
	public float getTq_poor() {
		return tq_poor;
	}
	public void setTq_poor(float tq_poor) {
		this.tq_poor = tq_poor;
	}
	public String getTq_college() {
		return tq_college;
	}
	public void setTq_college(String tq_college) {
		this.tq_college = tq_college;
	}
	public TeachingQualityInfo(String tq_project, float tq_coverpercent,
			float tq_excellent, float tq_good, float tq_medium, float tq_poor,
			String tq_college) {
		super();
		this.tq_project = tq_project;
		this.tq_coverpercent = tq_coverpercent;
		this.tq_excellent = tq_excellent;
		this.tq_good = tq_good;
		this.tq_medium = tq_medium;
		this.tq_poor = tq_poor;
		this.tq_college = tq_college;
	}

	
}
