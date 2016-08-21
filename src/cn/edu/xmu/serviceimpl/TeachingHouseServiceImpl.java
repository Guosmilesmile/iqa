package cn.edu.xmu.serviceimpl;

import java.math.BigDecimal;
/**
 * 
 * @author xiaoping 3.2.1 教学行政用房情况 date 2015-8-3
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.PlayGroundDao;
import cn.edu.xmu.dao.RoomAreaofTeachingAdministrationDao;
import cn.edu.xmu.dao.SchActUseClassroomDao;
import cn.edu.xmu.dao.StudentCenterDao;
import cn.edu.xmu.dao.StudentNumberInfoDao;
import cn.edu.xmu.daoimpl.PlayGroundDaoImpl;
import cn.edu.xmu.daoimpl.RoomAreaofTeachingAdministrationDaoImpl;
import cn.edu.xmu.daoimpl.SchActUseClassroomDaoImpl;
import cn.edu.xmu.daoimpl.StudentCenterDaoImpl;
import cn.edu.xmu.daoimpl.StudentNumberInfoDaoImpl;
import cn.edu.xmu.entity.PlayGround;
import cn.edu.xmu.entity.RoomAreaofTeachingAdministration;
import cn.edu.xmu.entity.SchActUseClassroom;
import cn.edu.xmu.entity.StudentCenter;
import cn.edu.xmu.entity.StudentNumberInfo;
import cn.edu.xmu.entity.TeachingHouse;
import cn.edu.xmu.service.TeachingHouseService;
import cn.edu.xmu.table.GraduateAndDoctoralTable;
import cn.edu.xmu.table.ImportantStudyTable;
import cn.edu.xmu.table.MajorInfoTable;
import cn.edu.xmu.table.PlayGroundTable;
import cn.edu.xmu.table.RoomAreaofTeachingAdministrationTable;
import cn.edu.xmu.table.SchActUseClassroomTable;
import cn.edu.xmu.table.StudentCenterTable;
import cn.edu.xmu.table.StudentNumberInfoTable;

/**
 * 
 * @author xiaoping 3.2.1 教学行政用房情况 date 2015-8-3
 */
public class TeachingHouseServiceImpl implements TeachingHouseService
{

	@Override
	public List<TeachingHouse> get(Map<String, String> filter)
	{

		List<TeachingHouse> content = new ArrayList<>();

		RoomAreaofTeachingAdministrationDao ratad = new RoomAreaofTeachingAdministrationDaoImpl();
		SchActUseClassroomDao saucd = new SchActUseClassroomDaoImpl();
		StudentCenterDao scd = new StudentCenterDaoImpl();
		PlayGroundDao pgd = new PlayGroundDaoImpl();
		StudentNumberInfoDao snid = new StudentNumberInfoDaoImpl();

		Map<String, String> rataFilter = new HashMap<>();
		Map<String, String> saucFilter = new HashMap<>();
		Map<String, String> scFilter = new HashMap<>();
		Map<String, String> sniFilter = new HashMap<>();
		Map<String, String> pgFilter = new HashMap<>();

		String rowTitle;
		// 教学行政用房
		double areaTotal = 0; // 总面积(平方米)
		double asistHouse = 0; // 教学科研辅助用房(平方米)
		double classroomArea = 0; // 教学行政用房 教室(平方米)
		double libraryArea = 0; // 图书馆（平方米）
		double labArea = 0; // 实验室
		double researchArea = 0;// 专业科研用房
		double gymArea = 0;// 体育馆（平方米）
		double hallArea = 0;// 会堂（平方米）
		double adminArea = 0;// 行政用房（平方米）
		double avgArea = 0; // 生均教学行政用房面积（平方米/生）

		// 教室
		int classroomAmount = 0;// 数量（间）
		int englishComputerAmount = 0;// 其中：外语教学计算机机房（含语音室）（间）
		int multimediaAmount = 0;// 多媒体教室（间）
		int seatNumber = 0;// 座位数（个）
		int englishComputerNumber = 0;// 其中：外语教学计算机机房（含语音室）（个）
		int multimediaNumber = 0;// 多媒体教室（个）
		double perHundredNumber = 0;// 百名学生配多媒体教室和语音实验室座位数（个）

		// 体育馆
		double sportsArea = 0;// 面积（平方米）
		int sportsNumber = 0;// 体育馆数量（个）

		// 学生活动中心
		double studentsActivityArea = 0;// 面积（平方米）
		int studentsActitvityAmount = 0;// 数量（间）

		String college = "";
		if (filter == null)
		{
			filter = new HashMap<>();
		} else if (filter.keySet().size() != 0)
		{
			for (Object obj : filter.keySet())
			{
				String key = obj.toString();
				String value = filter.get(key);
				if (key.equals("college"))
				{
					college = value;
					filter.remove("college");
					rataFilter.put(RoomAreaofTeachingAdministrationTable.RATA_COLLEGE, college);
					saucFilter.put(SchActUseClassroomTable.SAUC_COLLEGE, college);
					scFilter.put(StudentCenterTable.SC_COLLEGE, college);
					sniFilter.put(StudentNumberInfoTable.SNI_COLLEGE, college);
					pgFilter.put(PlayGroundTable.PG_COLLEGE, college);

				}
				if (key.equals("deadline"))
				{
					filter.remove("deadline");
					rataFilter.put(RoomAreaofTeachingAdministrationTable.RATA_DEADLINE, value);
					saucFilter.put(SchActUseClassroomTable.SAUC_DEADLINE, value);
					scFilter.put(StudentCenterTable.SC_DEADLINE, value);
					sniFilter.put(StudentNumberInfoTable.SNI_DEADLINE, value);
					pgFilter.put(PlayGroundTable.PG_DEADLINE, value);

				}
			}
		}
		BigDecimal b;// 用于double的四舍五入
		int people = 1;
		List<StudentNumberInfo> sniList = snid.getStudentNumberInfos(0, snid.getStudentNumberInfoCount(sniFilter),
				StudentNumberInfoTable.SNI_ID, "desc", sniFilter, null);
		if (sniList != null && sniList.size() > 0)
			people = sniList.get(0).getSni_ordiundergrastu();// 获取普通本科生数

		List<RoomAreaofTeachingAdministration> rataList = ratad.getAllRoomAreaofTeachingAdministration(0,
				ratad.getRoomAreaofTeachingAdministrationCount(rataFilter),
				RoomAreaofTeachingAdministrationTable.RATA_COLLEGENAME, "asc", rataFilter);
		if (rataList != null && rataList.size() > 0)
		{
			for (int i = 0; i < rataList.size(); i++)
			{
				RoomAreaofTeachingAdministration tempRata = rataList.get(i);
				if (tempRata.getRata_collegename().equals("合计"))
				{
					areaTotal = tempRata.getRata_sum();
					asistHouse = tempRata.getRata_teachresearchauxiliary();
					classroomArea = tempRata.getRata_classroom();
					libraryArea = tempRata.getRata_library();
					labArea = tempRata.getRata_lab();
					researchArea = tempRata.getRata_privatescienresearch();
					gymArea = tempRata.getRata_gym();
					hallArea = tempRata.getRata_hall();
					adminArea = tempRata.getRata_administrationoffice();
					break;
				}
				areaTotal += tempRata.getRata_sum();
				asistHouse += tempRata.getRata_teachresearchauxiliary();
				classroomArea += tempRata.getRata_classroom();
				libraryArea += tempRata.getRata_library();
				labArea += tempRata.getRata_lab();
				researchArea += tempRata.getRata_privatescienresearch();
				gymArea += tempRata.getRata_gym();
				hallArea += tempRata.getRata_hall();
				adminArea += tempRata.getRata_administrationoffice();
			}
		}
		avgArea = adminArea / people;
		b = new BigDecimal(avgArea);
		avgArea = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		int voiceseat = 0;
		List<SchActUseClassroom> sauList = saucd.getSchActUseClassrooms(0, saucd.getSchActUseClassroomCount(saucFilter),
				SchActUseClassroomTable.SAUC_ID, "asc", saucFilter, null);
		if (sauList != null && sauList.size() > 0)
		{
			for (int i = 0; i < sauList.size(); i++)
			{
				SchActUseClassroom tempSau = sauList.get(i);
				if (tempSau.getSauc_region().equals("合计"))
				{
					classroomAmount = tempSau.getSauc_subtotalroom();
					englishComputerAmount = tempSau.getSauc_computerroom();
					multimediaAmount = tempSau.getSauc_multiroom();
					seatNumber = tempSau.getSauc_subtotalseat();
					englishComputerNumber = tempSau.getSauc_computerseat();
					multimediaNumber = tempSau.getSauc_multiseat();
					voiceseat = tempSau.getSauc_voiceseat();
					break;
				}
				if (!tempSau.getSauc_site().equals("小计"))
				{
					classroomAmount += tempSau.getSauc_subtotalroom();
					englishComputerAmount += tempSau.getSauc_computerroom();
					multimediaAmount += tempSau.getSauc_multiroom();
					seatNumber += tempSau.getSauc_subtotalseat();
					englishComputerNumber += tempSau.getSauc_computerseat();
					multimediaNumber += tempSau.getSauc_multiseat();
					voiceseat += tempSau.getSauc_voiceseat();
				}
			}
		}

		perHundredNumber = ((double) multimediaNumber + (double) voiceseat) / people * 100.0;// 百名学生配多媒体教室和语音实验室座位数（个）
		b = new BigDecimal(perHundredNumber);
		perHundredNumber = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		List<PlayGround> pgList = pgd.getAllPlayGround(0, pgd.getPlayGroundCount(pgFilter), PlayGroundTable.PG_CAMPUS,
				"asc", pgFilter);
		if (pgList != null && pgList.size() > 0)
		{
			for (int i = 0; i < pgList.size(); i++)
			{
				PlayGround tempPg = pgList.get(i);
				if (tempPg.getPg_campus().equals("合计"))
				{
					if (tempPg.getPg_totalarea() != null && !tempPg.getPg_totalarea().equals(""))
						sportsArea = Double.parseDouble(tempPg.getPg_totalarea());
					sportsNumber = tempPg.getPg_amount();
					break;
				}
				if (!tempPg.getPg_groundname().equals("小计"))
				{
					if (tempPg.getPg_totalarea() != null && !tempPg.getPg_totalarea().equals(""))
						sportsArea += Double.parseDouble(tempPg.getPg_totalarea());
					sportsNumber += tempPg.getPg_amount();
				}
			}
		}

		List<StudentCenter> scList = scd.getStudentCenter(0, scd.getStudentCenterCount(scFilter),
				StudentCenterTable.SC_PROJECT, "asc", scFilter, null);
		if (scList != null && scList.size() > 0)
		{
			for (int i = 0; i < scList.size(); i++)
			{
				StudentCenter tempSc = scList.get(i);
				if (tempSc.getSc_project().equals("合计"))
				{
					studentsActivityArea = tempSc.getSc_area();
					studentsActitvityAmount = tempSc.getSc_quantity();
					break;
				}
				studentsActivityArea += tempSc.getSc_area();
				studentsActitvityAmount += tempSc.getSc_quantity();
			}
		}

		TeachingHouse school = new TeachingHouse("学校情况", areaTotal + "", asistHouse + "", classroomArea + "",
				libraryArea + "", labArea + "", researchArea + "", gymArea + "", hallArea + "", adminArea + "",
				avgArea + "", classroomAmount + "", englishComputerAmount + "", multimediaAmount + "", seatNumber + "",
				englishComputerNumber + "", multimediaNumber + "", perHundredNumber + "", sportsArea + "",
				sportsNumber + "", studentsActivityArea + "", studentsActitvityAmount + "", college);
		school.setRowTitle("学校情况");

		TeachingHouse standard = new TeachingHouse();
		standard.setAvgArea("14平方米/生");
		standard.setPerHundredNumber("不少于7个");
		standard.setRowTitle("办学条件指标/合格标准");

		TeachingHouse changmo = new TeachingHouse();
		changmo.setRowTitle("常模值");

		content.add(school);
		content.add(standard);
		content.add(changmo);

		return content;

	}
}
