package cn.edu.xmu.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.GraduationSituationDao;
import cn.edu.xmu.daoimpl.GraduationSituationDaoImpl;
import cn.edu.xmu.entity.GraduationSituationInfo;
import cn.edu.xmu.service.GraduationSituationInfoService;
import cn.edu.xmu.table.GraduateEmployAsMajorTable;
import cn.edu.xmu.table.GraduationSituationTable;
import cn.edu.xmu.util.JdbcUtils_DBCP;

public class GraduationSituationInfoServiceImpl implements GraduationSituationInfoService{

	private GraduationSituationDao gsDao = new GraduationSituationDaoImpl();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<GraduationSituationInfo> getGraduationSituationInfo(Map params) {
					int serialNumber = 0;
					Map paramsGraduationSituation = new HashMap<>();
					 if(params == null)
					 {
						 params = new HashMap<>();
					 }else if (params.keySet().size() != 0) {
						 for(Object object:params.keySet()){
							 String key = object.toString();
							 String value = (String) params.get(key);
								if (key.equals("college")) {
									params.remove("college");
									paramsGraduationSituation.put(GraduationSituationTable.GS_COLLEGE, value);
								}
								if (key.equals("deadline")) {
									params.remove("deadline");
									paramsGraduationSituation.put(GraduationSituationTable.GS_DEADLINE, value);
								}
						 }
						
					}
					 String sql = "select tmp.* from ( "
					 		+ " select * from " + GraduateEmployAsMajorTable.TABLE_NAME + ","
					 		+ GraduationSituationTable.TABLE_NAME + " where " 
					 		+ GraduateEmployAsMajorTable.GEAM_MAJORCODEINSCHOOL + " = " 
					 		+ GraduationSituationTable.GS_MAJORCODE ; 
					 if (!(paramsGraduationSituation == null || paramsGraduationSituation.keySet().size() == 0)) {
							for (Object object : paramsGraduationSituation.keySet()) {

								String key = object.toString();
								String value = paramsGraduationSituation.get(key).toString();
								sql += String.format(" and %s like  '%%%s%%' ", key, value);
							}
						}

						sql += " order by " + GraduateEmployAsMajorTable.GEAM_ID + " " + "asc" + " ) tmp limit "
								+ 0 + " ," + gsDao.getGraduationSituationCount(paramsGraduationSituation);

						System.out.println(sql);	
						
						List<GraduationSituationInfo> gsis = new ArrayList<GraduationSituationInfo>();
						Connection connection = null;
						try {
							connection = JdbcUtils_DBCP.getConnection();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						PreparedStatement pstmt = null;
						ResultSet resultSet = null;
						try {
							pstmt = connection.prepareStatement(sql);
							resultSet = pstmt.executeQuery();
							while (resultSet.next()) {
								//专业名称
								String majorName = resultSet.getString(GraduationSituationTable.GS_MAJOR);
								//应届毕业生数
								String graduationNumberTemp = resultSet.getString(GraduationSituationTable.GS_TOTAL);
								int graduationNumber = 0;
								if(graduationNumberTemp != null || !"".equals(graduationNumberTemp))
									graduationNumber = Integer.valueOf(graduationNumberTemp);
								//应届生中未按时毕业数  = 毕业班人数-应届毕业生数
								String ungraduatedNumberTemp = resultSet.getString(GraduationSituationTable.GS_GRADUATIONNUMBER) ;
								int ungraduatedNumber = 0 ;
								if(ungraduatedNumberTemp != null || !"".equals(ungraduatedNumberTemp))	
									ungraduatedNumber = Integer.valueOf(ungraduatedNumberTemp) - graduationNumber;
								//毕业率 = 应届毕业生数/毕业班人数
								double graduatedRate = 0;
								if(resultSet.getInt(GraduationSituationTable.GS_GRADUATIONNUMBER) != 0)
										graduatedRate = (double)graduationNumber/resultSet.getInt(GraduationSituationTable.GS_GRADUATIONNUMBER) * 100.0;
								//授予学位数
								String degreeNumberTemp = resultSet.getString(GraduationSituationTable.GS_DEGREEYES);
								int degreeNumber = 0;
								if(degreeNumberTemp != null || !"".equals(degreeNumberTemp))
									degreeNumber = Integer.valueOf(degreeNumberTemp);
								//学位授予率 = 授予学位数/已经毕业生数
								double degreeRate = 0;
								if(graduationNumber != 0)
										degreeRate = (double)degreeNumber/graduationNumber * 100.0;
								//应届毕业生就业人数
								String employasmajorNumberTemp = resultSet.getString(GraduateEmployAsMajorTable.GEAM_GRADUATENUM);
								int employasmajorNumber = 0;
								if(employasmajorNumberTemp != null || !"".equals(employasmajorNumberTemp))
									employasmajorNumber = Integer.valueOf(employasmajorNumberTemp);
								//初次就业率 = 应届毕业生就业数/应届毕业生数
								Double employasmajorRate = Double.valueOf("0");
								if(graduationNumber!=0)
									employasmajorRate =  (double)employasmajorNumber/graduationNumber *100.0;
								
								String college = resultSet.getString(GraduationSituationTable.GS_COLLEGE);
								
								GraduationSituationInfo gsi = new GraduationSituationInfo(++serialNumber,majorName, graduationNumber, 
										ungraduatedNumber, graduatedRate, degreeNumber, degreeRate, employasmajorNumber,
										employasmajorRate,college);
								gsis.add(gsi);
								
							}
							
						} catch (SQLException e) {
							e.printStackTrace();
							return null;
						} finally{
							JdbcUtils_DBCP.release(connection, pstmt, resultSet);
						}
						//按  初次就业率  从小到大排序
						Collections.sort(gsis);
						for(int i = 0 ; i<gsis.size(); i++)
						{
							gsis.get(i).setSerialNumber(i+1);
						}
						//获取  初次就业率 最低的十个专业
						if(gsis.size()<10)
							gsis = gsis.subList(0, gsis.size());
						else
							gsis = gsis.subList(0, 10);
						return gsis;
	}

}
