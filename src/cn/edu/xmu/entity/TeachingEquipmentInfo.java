package cn.edu.xmu.entity;

/**
 * 3.2.2教学、科研仪器设备情况
 * @author yue
 *
 */
public class TeachingEquipmentInfo {
	
	private String title;//
	private Float equipmentAssetsSum;//教学、科研仪器设备 资产总值（万元）
	private Float equipmentAssetsStuAvg;//生均（万元）
	private Float newAssets;//当年新增（万元）
	private Float newAssetsPercent;//当年新增所占比例（%）
	private String college;//学院
	public TeachingEquipmentInfo(String title, Float equipmentAssetsSum, Float equipmentAssetsStuAvg, Float newAssets,
			Float newAssetsPercent, String college) {
		super();
		this.title = title;
		this.equipmentAssetsSum = equipmentAssetsSum;
		this.equipmentAssetsStuAvg = equipmentAssetsStuAvg;
		this.newAssets = newAssets;
		this.newAssetsPercent = newAssetsPercent;
		this.college = college;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Float getEquipmentAssetsSum() {
		return equipmentAssetsSum;
	}
	public void setEquipmentAssetsSum(Float equipmentAssetsSum) {
		this.equipmentAssetsSum = equipmentAssetsSum;
	}
	public Float getEquipmentAssetsStuAvg() {
		return equipmentAssetsStuAvg;
	}
	public void setEquipmentAssetsStuAvg(Float equipmentAssetsStuAvg) {
		this.equipmentAssetsStuAvg = equipmentAssetsStuAvg;
	}
	public Float getNewAssets() {
		return newAssets;
	}
	public void setNewAssets(Float newAssets) {
		this.newAssets = newAssets;
	}
	public Float getNewAssetsPercent() {
		return newAssetsPercent;
	}
	public void setNewAssetsPercent(Float newAssetsPercent) {
		this.newAssetsPercent = newAssetsPercent;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}

	
}
