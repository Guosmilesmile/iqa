package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * @author zhantu
 * 厦门大学普高招生各省份出档线高出本一线分值 实体类
 * date 2015-07-13
 */
public class GeneralHighEnrollmentfileLine {
	//ID
	private int ghel_id;
	//文史或理工
	private String ghel_type;
	//海南
	private Float ghel_hainan;
	//新疆
	private Float ghel_xinjiang;
	//西藏(少)
	private Float ghel_xizangshao;
	//云南
	private Float ghel_yunnan;
	//陕西(陕)
	private Float ghel_shanxishan;
	//天津
	private Float ghel_tianjin;
	//宁夏
	private Float ghel_ningxia;
	//贵州
	private Float ghel_guizhou;
	//辽宁
	private Float ghel_liaoning;
	//西藏(汉)
	private Float ghel_xizanghan;
	//吉林
	private Float ghel_jilin;
	//广西
	private Float ghel_guangxi;
	//浙江
	private Float ghel_zhejiang;
	//重庆
	private Float ghel_chongqing;
	//安徽
	private Float ghel_anhui;
	//黑龙江
	private Float ghel_heilongjiang;
	//江西
	private Float ghel_jiangxi;
	//四川
	private Float ghel_sichuan;
	//北京
	private Float ghel_beijing;
	//河南
	private Float ghel_henan;
	//湖南
	private Float ghel_hunan;
	//上海
	private Float ghel_shanghai;
	//福建
	private Float ghel_fujian;
	//山东
	private Float ghel_shandong;
	//河北
	private Float ghel_hebei;
	//湖北
	private Float ghel_hubei;
	//广东
	private Float ghel_guangdong;
	//江苏
	private Float ghel_jiangsu;
	//山西(晋)
	private Float ghel_shanxijin;
	//内蒙古
	private Float ghel_neimenggu;
	//青海
	private Float ghel_qinghai;
	//甘肃
	private Float ghel_gansu;
	//序列号
	private int ghel_serialnumber;
	//截止日期
	private Date ghel_deadline;
	//填报学院
	private String ghel_college;
	//审核意见
	private String ghel_comments;
	//记录是否存在空值
	private int isnull;
	
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public int getGhel_id() {
		return ghel_id;
	}
	public void setGhel_id(int ghel_id) {
		this.ghel_id = ghel_id;
	}
	public String getGhel_type() {
		return ghel_type;
	}
	public void setGhel_type(String ghel_type) {
		this.ghel_type = ghel_type;
	}
	public Float getGhel_hainan() {
		return ghel_hainan;
	}
	public void setGhel_hainan(Float ghel_hainan) {
		this.ghel_hainan = ghel_hainan;
	}
	public Float getGhel_xinjiang() {
		return ghel_xinjiang;
	}
	public void setGhel_xinjiang(Float ghel_xinjiang) {
		this.ghel_xinjiang = ghel_xinjiang;
	}
	public Float getGhel_xizangshao() {
		return ghel_xizangshao;
	}
	public void setGhel_xizangshao(Float ghel_xizangshao) {
		this.ghel_xizangshao = ghel_xizangshao;
	}
	public Float getGhel_yunnan() {
		return ghel_yunnan;
	}
	public void setGhel_yunnan(Float ghel_yunnan) {
		this.ghel_yunnan = ghel_yunnan;
	}
	public Float getGhel_shanxishan() {
		return ghel_shanxishan;
	}
	public void setGhel_shanxishan(Float ghel_shanxishan) {
		this.ghel_shanxishan = ghel_shanxishan;
	}
	public Float getGhel_tianjin() {
		return ghel_tianjin;
	}
	public void setGhel_tianjin(Float ghel_tianjin) {
		this.ghel_tianjin = ghel_tianjin;
	}
	public Float getGhel_ningxia() {
		return ghel_ningxia;
	}
	public void setGhel_ningxia(Float ghel_ningxia) {
		this.ghel_ningxia = ghel_ningxia;
	}
	public Float getGhel_guizhou() {
		return ghel_guizhou;
	}
	public void setGhel_guizhou(Float ghel_guizhou) {
		this.ghel_guizhou = ghel_guizhou;
	}
	public Float getGhel_liaoning() {
		return ghel_liaoning;
	}
	public void setGhel_liaoning(Float ghel_liaoning) {
		this.ghel_liaoning = ghel_liaoning;
	}
	public Float getGhel_xizanghan() {
		return ghel_xizanghan;
	}
	public void setGhel_xizanghan(Float ghel_xizanghan) {
		this.ghel_xizanghan = ghel_xizanghan;
	}
	public Float getGhel_jilin() {
		return ghel_jilin;
	}
	public void setGhel_jilin(Float ghel_jilin) {
		this.ghel_jilin = ghel_jilin;
	}
	public Float getGhel_guangxi() {
		return ghel_guangxi;
	}
	public void setGhel_guangxi(Float ghel_guangxi) {
		this.ghel_guangxi = ghel_guangxi;
	}
	public Float getGhel_zhejiang() {
		return ghel_zhejiang;
	}
	public void setGhel_zhejiang(Float ghel_zhejiang) {
		this.ghel_zhejiang = ghel_zhejiang;
	}
	public Float getGhel_chongqing() {
		return ghel_chongqing;
	}
	public void setGhel_chongqing(Float ghel_chongqing) {
		this.ghel_chongqing = ghel_chongqing;
	}
	public Float getGhel_anhui() {
		return ghel_anhui;
	}
	public void setGhel_anhui(Float ghel_anhui) {
		this.ghel_anhui = ghel_anhui;
	}
	public Float getGhel_heilongjiang() {
		return ghel_heilongjiang;
	}
	public void setGhel_heilongjiang(Float ghel_heilongjiang) {
		this.ghel_heilongjiang = ghel_heilongjiang;
	}
	public Float getGhel_jiangxi() {
		return ghel_jiangxi;
	}
	public void setGhel_jiangxi(Float ghel_jiangxi) {
		this.ghel_jiangxi = ghel_jiangxi;
	}
	public Float getGhel_sichuan() {
		return ghel_sichuan;
	}
	public void setGhel_sichuan(Float ghel_sichuan) {
		this.ghel_sichuan = ghel_sichuan;
	}
	public Float getGhel_beijing() {
		return ghel_beijing;
	}
	public void setGhel_beijing(Float ghel_beijing) {
		this.ghel_beijing = ghel_beijing;
	}
	public Float getGhel_henan() {
		return ghel_henan;
	}
	public void setGhel_henan(Float ghel_henan) {
		this.ghel_henan = ghel_henan;
	}
	public Float getGhel_hunan() {
		return ghel_hunan;
	}
	public void setGhel_hunan(Float ghel_hunan) {
		this.ghel_hunan = ghel_hunan;
	}
	public Float getGhel_shanghai() {
		return ghel_shanghai;
	}
	public void setGhel_shanghai(Float ghel_shanghai) {
		this.ghel_shanghai = ghel_shanghai;
	}
	public Float getGhel_fujian() {
		return ghel_fujian;
	}
	public void setGhel_fujian(Float ghel_fujian) {
		this.ghel_fujian = ghel_fujian;
	}
	public Float getGhel_shandong() {
		return ghel_shandong;
	}
	public void setGhel_shandong(Float ghel_shandong) {
		this.ghel_shandong = ghel_shandong;
	}
	public Float getGhel_hebei() {
		return ghel_hebei;
	}
	public void setGhel_hebei(Float ghel_hebei) {
		this.ghel_hebei = ghel_hebei;
	}
	public Float getGhel_hubei() {
		return ghel_hubei;
	}
	public void setGhel_hubei(Float ghel_hubei) {
		this.ghel_hubei = ghel_hubei;
	}
	public Float getGhel_guangdong() {
		return ghel_guangdong;
	}
	public void setGhel_guangdong(Float ghel_guangdong) {
		this.ghel_guangdong = ghel_guangdong;
	}
	public Float getGhel_jiangsu() {
		return ghel_jiangsu;
	}
	public void setGhel_jiangsu(Float ghel_jiangsu) {
		this.ghel_jiangsu = ghel_jiangsu;
	}
	public Float getGhel_shanxijin() {
		return ghel_shanxijin;
	}
	public void setGhel_shanxijin(Float ghel_shanxijin) {
		this.ghel_shanxijin = ghel_shanxijin;
	}
	public Float getGhel_neimenggu() {
		return ghel_neimenggu;
	}
	public void setGhel_neimenggu(Float ghel_neimenggu) {
		this.ghel_neimenggu = ghel_neimenggu;
	}
	public Float getGhel_qinghai() {
		return ghel_qinghai;
	}
	public void setGhel_qinghai(Float ghel_qinghai) {
		this.ghel_qinghai = ghel_qinghai;
	}
	public Float getGhel_gansu() {
		return ghel_gansu;
	}
	public void setGhel_gansu(Float ghel_gansu) {
		this.ghel_gansu = ghel_gansu;
	}
	public int getGhel_serialnumber() {
		return ghel_serialnumber;
	}
	public void setGhel_serialnumber(int ghel_serialnumber) {
		this.ghel_serialnumber = ghel_serialnumber;
	}
	public Date getGhel_deadline() {
		return ghel_deadline;
	}
	public void setGhel_deadline(Date ghel_deadline) {
		this.ghel_deadline = ghel_deadline;
	}
	public String getGhel_college() {
		return ghel_college;
	}
	public void setGhel_college(String ghel_college) {
		this.ghel_college = ghel_college;
	}
	public String getGhel_comments() {
		return ghel_comments;
	}
	public void setGhel_comments(String ghel_comments) {
		this.ghel_comments = ghel_comments;
	}
	public GeneralHighEnrollmentfileLine() {
		super();
	}
	public GeneralHighEnrollmentfileLine(int ghel_id, String ghel_type,
			Float ghel_hainan, Float ghel_xinjiang, Float ghel_xizangshao,
			Float ghel_yunnan, Float ghel_shanxishan, Float ghel_tianjin,
			Float ghel_ningxia, Float ghel_guizhou, Float ghel_liaoning,
			Float ghel_xizanghan, Float ghel_jilin, Float ghel_guangxi,
			Float ghel_zhejiang, Float ghel_chongqing, Float ghel_anhui,
			Float ghel_heilongjiang, Float ghel_jiangxi, Float ghel_sichuan,
			Float ghel_beijing, Float ghel_henan, Float ghel_hunan,
			Float ghel_shanghai, Float ghel_fujian, Float ghel_shandong,
			Float ghel_hebei, Float ghel_hubei, Float ghel_guangdong,
			Float ghel_jiangsu, Float ghel_shanxijin, Float ghel_neimenggu,
			Float ghel_qinghai, Float ghel_gansu, int ghel_serialnumber,
			Date ghel_deadline, String ghel_college, String ghel_comments, int isnull) {
		super();
		this.ghel_id = ghel_id;
		this.ghel_type = ghel_type;
		this.ghel_hainan = ghel_hainan;
		this.ghel_xinjiang = ghel_xinjiang;
		this.ghel_xizangshao = ghel_xizangshao;
		this.ghel_yunnan = ghel_yunnan;
		this.ghel_shanxishan = ghel_shanxishan;
		this.ghel_tianjin = ghel_tianjin;
		this.ghel_ningxia = ghel_ningxia;
		this.ghel_guizhou = ghel_guizhou;
		this.ghel_liaoning = ghel_liaoning;
		this.ghel_xizanghan = ghel_xizanghan;
		this.ghel_jilin = ghel_jilin;
		this.ghel_guangxi = ghel_guangxi;
		this.ghel_zhejiang = ghel_zhejiang;
		this.ghel_chongqing = ghel_chongqing;
		this.ghel_anhui = ghel_anhui;
		this.ghel_heilongjiang = ghel_heilongjiang;
		this.ghel_jiangxi = ghel_jiangxi;
		this.ghel_sichuan = ghel_sichuan;
		this.ghel_beijing = ghel_beijing;
		this.ghel_henan = ghel_henan;
		this.ghel_hunan = ghel_hunan;
		this.ghel_shanghai = ghel_shanghai;
		this.ghel_fujian = ghel_fujian;
		this.ghel_shandong = ghel_shandong;
		this.ghel_hebei = ghel_hebei;
		this.ghel_hubei = ghel_hubei;
		this.ghel_guangdong = ghel_guangdong;
		this.ghel_jiangsu = ghel_jiangsu;
		this.ghel_shanxijin = ghel_shanxijin;
		this.ghel_neimenggu = ghel_neimenggu;
		this.ghel_qinghai = ghel_qinghai;
		this.ghel_gansu = ghel_gansu;
		this.ghel_serialnumber = ghel_serialnumber;
		this.ghel_deadline = ghel_deadline;
		this.ghel_college = ghel_college;
		this.ghel_comments = ghel_comments;
		this.isnull = isnull;
	}
	public GeneralHighEnrollmentfileLine(String ghel_type, Float ghel_hainan,
			Float ghel_xinjiang, Float ghel_xizangshao, Float ghel_yunnan,
			Float ghel_shanxishan, Float ghel_tianjin, Float ghel_ningxia,
			Float ghel_guizhou, Float ghel_liaoning, Float ghel_xizanghan,
			Float ghel_jilin, Float ghel_guangxi, Float ghel_zhejiang,
			Float ghel_chongqing, Float ghel_anhui, Float ghel_heilongjiang,
			Float ghel_jiangxi, Float ghel_sichuan, Float ghel_beijing,
			Float ghel_henan, Float ghel_hunan, Float ghel_shanghai,
			Float ghel_fujian, Float ghel_shandong, Float ghel_hebei,
			Float ghel_hubei, Float ghel_guangdong, Float ghel_jiangsu,
			Float ghel_shanxijin, Float ghel_neimenggu, Float ghel_qinghai,
			Float ghel_gansu, int ghel_serialnumber, String ghel_college,
			String ghel_comments, int isnull) {
		super();
		this.ghel_type = ghel_type;
		this.ghel_hainan = ghel_hainan;
		this.ghel_xinjiang = ghel_xinjiang;
		this.ghel_xizangshao = ghel_xizangshao;
		this.ghel_yunnan = ghel_yunnan;
		this.ghel_shanxishan = ghel_shanxishan;
		this.ghel_tianjin = ghel_tianjin;
		this.ghel_ningxia = ghel_ningxia;
		this.ghel_guizhou = ghel_guizhou;
		this.ghel_liaoning = ghel_liaoning;
		this.ghel_xizanghan = ghel_xizanghan;
		this.ghel_jilin = ghel_jilin;
		this.ghel_guangxi = ghel_guangxi;
		this.ghel_zhejiang = ghel_zhejiang;
		this.ghel_chongqing = ghel_chongqing;
		this.ghel_anhui = ghel_anhui;
		this.ghel_heilongjiang = ghel_heilongjiang;
		this.ghel_jiangxi = ghel_jiangxi;
		this.ghel_sichuan = ghel_sichuan;
		this.ghel_beijing = ghel_beijing;
		this.ghel_henan = ghel_henan;
		this.ghel_hunan = ghel_hunan;
		this.ghel_shanghai = ghel_shanghai;
		this.ghel_fujian = ghel_fujian;
		this.ghel_shandong = ghel_shandong;
		this.ghel_hebei = ghel_hebei;
		this.ghel_hubei = ghel_hubei;
		this.ghel_guangdong = ghel_guangdong;
		this.ghel_jiangsu = ghel_jiangsu;
		this.ghel_shanxijin = ghel_shanxijin;
		this.ghel_neimenggu = ghel_neimenggu;
		this.ghel_qinghai = ghel_qinghai;
		this.ghel_gansu = ghel_gansu;
		this.ghel_serialnumber = ghel_serialnumber;
		this.ghel_college = ghel_college;
		this.ghel_comments = ghel_comments;
		this.isnull = isnull;
	}
	public GeneralHighEnrollmentfileLine(int ghel_id, String ghel_type,
			Float ghel_hainan, Float ghel_xinjiang, Float ghel_xizangshao,
			Float ghel_yunnan, Float ghel_shanxishan, Float ghel_tianjin,
			Float ghel_ningxia, Float ghel_guizhou, Float ghel_liaoning,
			Float ghel_xizanghan, Float ghel_jilin, Float ghel_guangxi,
			Float ghel_zhejiang, Float ghel_chongqing, Float ghel_anhui,
			Float ghel_heilongjiang, Float ghel_jiangxi, Float ghel_sichuan,
			Float ghel_beijing, Float ghel_henan, Float ghel_hunan,
			Float ghel_shanghai, Float ghel_fujian, Float ghel_shandong,
			Float ghel_hebei, Float ghel_hubei, Float ghel_guangdong,
			Float ghel_jiangsu, Float ghel_shanxijin, Float ghel_neimenggu,
			Float ghel_qinghai, Float ghel_gansu, int ghel_serialnumber,
			String ghel_comments, int isnull) {
		super();
		this.ghel_id = ghel_id;
		this.ghel_type = ghel_type;
		this.ghel_hainan = ghel_hainan;
		this.ghel_xinjiang = ghel_xinjiang;
		this.ghel_xizangshao = ghel_xizangshao;
		this.ghel_yunnan = ghel_yunnan;
		this.ghel_shanxishan = ghel_shanxishan;
		this.ghel_tianjin = ghel_tianjin;
		this.ghel_ningxia = ghel_ningxia;
		this.ghel_guizhou = ghel_guizhou;
		this.ghel_liaoning = ghel_liaoning;
		this.ghel_xizanghan = ghel_xizanghan;
		this.ghel_jilin = ghel_jilin;
		this.ghel_guangxi = ghel_guangxi;
		this.ghel_zhejiang = ghel_zhejiang;
		this.ghel_chongqing = ghel_chongqing;
		this.ghel_anhui = ghel_anhui;
		this.ghel_heilongjiang = ghel_heilongjiang;
		this.ghel_jiangxi = ghel_jiangxi;
		this.ghel_sichuan = ghel_sichuan;
		this.ghel_beijing = ghel_beijing;
		this.ghel_henan = ghel_henan;
		this.ghel_hunan = ghel_hunan;
		this.ghel_shanghai = ghel_shanghai;
		this.ghel_fujian = ghel_fujian;
		this.ghel_shandong = ghel_shandong;
		this.ghel_hebei = ghel_hebei;
		this.ghel_hubei = ghel_hubei;
		this.ghel_guangdong = ghel_guangdong;
		this.ghel_jiangsu = ghel_jiangsu;
		this.ghel_shanxijin = ghel_shanxijin;
		this.ghel_neimenggu = ghel_neimenggu;
		this.ghel_qinghai = ghel_qinghai;
		this.ghel_gansu = ghel_gansu;
		this.ghel_serialnumber = ghel_serialnumber;
		this.ghel_comments = ghel_comments;
		this.isnull = isnull;
	}
	public GeneralHighEnrollmentfileLine(int ghel_id, String ghel_type,
			Float ghel_hainan, Float ghel_xinjiang, Float ghel_xizangshao,
			Float ghel_yunnan, Float ghel_shanxishan, Float ghel_tianjin,
			Float ghel_ningxia, Float ghel_guizhou, Float ghel_liaoning,
			Float ghel_xizanghan, Float ghel_jilin, Float ghel_guangxi,
			Float ghel_zhejiang, Float ghel_chongqing, Float ghel_anhui,
			Float ghel_heilongjiang, Float ghel_jiangxi, Float ghel_sichuan,
			Float ghel_beijing, Float ghel_henan, Float ghel_hunan,
			Float ghel_shanghai, Float ghel_fujian, Float ghel_shandong,
			Float ghel_hebei, Float ghel_hubei, Float ghel_guangdong,
			Float ghel_jiangsu, Float ghel_shanxijin, Float ghel_neimenggu,
			Float ghel_qinghai, Float ghel_gansu, String ghel_comments, int isnull) {
		super();
		this.ghel_id = ghel_id;
		this.ghel_type = ghel_type;
		this.ghel_hainan = ghel_hainan;
		this.ghel_xinjiang = ghel_xinjiang;
		this.ghel_xizangshao = ghel_xizangshao;
		this.ghel_yunnan = ghel_yunnan;
		this.ghel_shanxishan = ghel_shanxishan;
		this.ghel_tianjin = ghel_tianjin;
		this.ghel_ningxia = ghel_ningxia;
		this.ghel_guizhou = ghel_guizhou;
		this.ghel_liaoning = ghel_liaoning;
		this.ghel_xizanghan = ghel_xizanghan;
		this.ghel_jilin = ghel_jilin;
		this.ghel_guangxi = ghel_guangxi;
		this.ghel_zhejiang = ghel_zhejiang;
		this.ghel_chongqing = ghel_chongqing;
		this.ghel_anhui = ghel_anhui;
		this.ghel_heilongjiang = ghel_heilongjiang;
		this.ghel_jiangxi = ghel_jiangxi;
		this.ghel_sichuan = ghel_sichuan;
		this.ghel_beijing = ghel_beijing;
		this.ghel_henan = ghel_henan;
		this.ghel_hunan = ghel_hunan;
		this.ghel_shanghai = ghel_shanghai;
		this.ghel_fujian = ghel_fujian;
		this.ghel_shandong = ghel_shandong;
		this.ghel_hebei = ghel_hebei;
		this.ghel_hubei = ghel_hubei;
		this.ghel_guangdong = ghel_guangdong;
		this.ghel_jiangsu = ghel_jiangsu;
		this.ghel_shanxijin = ghel_shanxijin;
		this.ghel_neimenggu = ghel_neimenggu;
		this.ghel_qinghai = ghel_qinghai;
		this.ghel_gansu = ghel_gansu;
		this.ghel_comments = ghel_comments;
		this.isnull = isnull;
	}
	public GeneralHighEnrollmentfileLine(String ghel_type, Float ghel_hainan,
			Float ghel_xinjiang, Float ghel_xizangshao, Float ghel_yunnan,
			Float ghel_shanxishan, Float ghel_tianjin, Float ghel_ningxia,
			Float ghel_guizhou, Float ghel_liaoning, Float ghel_xizanghan,
			Float ghel_jilin, Float ghel_guangxi, Float ghel_zhejiang,
			Float ghel_chongqing, Float ghel_anhui, Float ghel_heilongjiang,
			Float ghel_jiangxi, Float ghel_sichuan, Float ghel_beijing,
			Float ghel_henan, Float ghel_hunan, Float ghel_shanghai,
			Float ghel_fujian, Float ghel_shandong, Float ghel_hebei,
			Float ghel_hubei, Float ghel_guangdong, Float ghel_jiangsu,
			Float ghel_shanxijin, Float ghel_neimenggu, Float ghel_qinghai,
			Float ghel_gansu, String ghel_college, int isnull) {
		super();
		this.ghel_type = ghel_type;
		this.ghel_hainan = ghel_hainan;
		this.ghel_xinjiang = ghel_xinjiang;
		this.ghel_xizangshao = ghel_xizangshao;
		this.ghel_yunnan = ghel_yunnan;
		this.ghel_shanxishan = ghel_shanxishan;
		this.ghel_tianjin = ghel_tianjin;
		this.ghel_ningxia = ghel_ningxia;
		this.ghel_guizhou = ghel_guizhou;
		this.ghel_liaoning = ghel_liaoning;
		this.ghel_xizanghan = ghel_xizanghan;
		this.ghel_jilin = ghel_jilin;
		this.ghel_guangxi = ghel_guangxi;
		this.ghel_zhejiang = ghel_zhejiang;
		this.ghel_chongqing = ghel_chongqing;
		this.ghel_anhui = ghel_anhui;
		this.ghel_heilongjiang = ghel_heilongjiang;
		this.ghel_jiangxi = ghel_jiangxi;
		this.ghel_sichuan = ghel_sichuan;
		this.ghel_beijing = ghel_beijing;
		this.ghel_henan = ghel_henan;
		this.ghel_hunan = ghel_hunan;
		this.ghel_shanghai = ghel_shanghai;
		this.ghel_fujian = ghel_fujian;
		this.ghel_shandong = ghel_shandong;
		this.ghel_hebei = ghel_hebei;
		this.ghel_hubei = ghel_hubei;
		this.ghel_guangdong = ghel_guangdong;
		this.ghel_jiangsu = ghel_jiangsu;
		this.ghel_shanxijin = ghel_shanxijin;
		this.ghel_neimenggu = ghel_neimenggu;
		this.ghel_qinghai = ghel_qinghai;
		this.ghel_gansu = ghel_gansu;
		this.ghel_college = ghel_college;
		this.isnull = isnull;
	}
}
