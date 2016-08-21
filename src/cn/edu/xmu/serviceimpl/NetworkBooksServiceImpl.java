package cn.edu.xmu.serviceimpl;

/**
 * 
 * @author xiaoping 3.2.4校园网、图书情况 date 2015-8-5
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.dao.LibraryYearDao;
import cn.edu.xmu.dao.NewBooksthatYearDao;
import cn.edu.xmu.dao.SchoolNetDao;
import cn.edu.xmu.dao.StudentNumberInfoDao;
import cn.edu.xmu.daoimpl.LibraryYearDaoImpl;
import cn.edu.xmu.daoimpl.NewBooksthatYearDaoImpl;
import cn.edu.xmu.daoimpl.SchoolNetDaoimpl;
import cn.edu.xmu.daoimpl.StudentNumberInfoDaoImpl;
import cn.edu.xmu.entity.LibraryYear;
import cn.edu.xmu.entity.NetworkBooks;
import cn.edu.xmu.entity.NewBooksthatYear;
import cn.edu.xmu.entity.SchoolNet;
import cn.edu.xmu.entity.StudentNumberInfo;
import cn.edu.xmu.service.NetworkBooksService;
import cn.edu.xmu.table.LibraryYearTable;
import cn.edu.xmu.table.NewBooksthatYearTable;
import cn.edu.xmu.table.SchoolNetTable;
import cn.edu.xmu.table.StudentNumberInfoTable;

public class NetworkBooksServiceImpl implements NetworkBooksService
{

	@Override
	public List<NetworkBooks> get(Map<String, String> filter)
	{

		List<NetworkBooks> content = new ArrayList<>();
		LibraryYearDao lyd = new LibraryYearDaoImpl();
		NewBooksthatYearDao nbyd = new NewBooksthatYearDaoImpl();
		SchoolNetDao snd = new SchoolNetDaoimpl();
		StudentNumberInfoDao snid = new StudentNumberInfoDaoImpl();

		Map<String, String> lyFilter = new HashMap<>();
		Map<String, String> nbyFilter = new HashMap<>();
		Map<String, String> snFilter = new HashMap<>();
		Map<String, String> sniFilter = new HashMap<>();

		int mainband = 0; // 校园网主干带宽（Mbps）
		int outband = 0; // 校园网出口带宽（Mbps）
		int networkaccess = 0; // 网络接入信息点数量（个）
		int paperbooktotal = 0; // 纸质图书总量（册）
		int paperbookperstudent = 0; // 生均纸质图书（册）
		int ebooktotal = 0; // 电子图书数量（种）
		int ebookperstudent = 0; // 生均电子图书（种）
		int newpaperbookthatyear = 0; // 当年新增纸质图书（册）
		int newpaperbookthatyearperstudent = 0; // 生均年进纸质图书（册）
		int newebookthatyear = 0; // 当年新增电子图书（种）
		int bookcurrencythatyear = 0; // 当年图书流通量（本次）
		int paperjournaltotal = 0; // 纸质期刊数量（份）
		int paperjournaltype = 0; // 纸质期刊种类数（种）
		int ejournaltype = 0; // 电子期刊种类（种）
		int databasenumber = 0; // 数据库（个）

		String college = "";
		if (filter == null)
		{
			filter = new HashMap<>();
		} else if (filter.keySet().size() != 0)
		{
			for (Object object : filter.keySet())
			{
				String key = object.toString();
				String value = (String) filter.get(key);

				if (key.contains("college"))
				{
					college = value;
					filter.remove("college");
					lyFilter.put(LibraryYearTable.LY_COLLEGE, college);
					nbyFilter.put(NewBooksthatYearTable.NBY_COLLEGE, college);
					snFilter.put(SchoolNetTable.SN_COLLEGE, college);
					sniFilter.put(StudentNumberInfoTable.SNI_COLLEGE, college);

				}

				if (key.contains("deadline"))
				{
					filter.remove("deadline");
					lyFilter.put(LibraryYearTable.LY_DEADLINE, value);
					nbyFilter.put(NewBooksthatYearTable.NBY_DEADLINE, value);
					snFilter.put(SchoolNetTable.SN_DEADLINE, value);
					sniFilter.put(StudentNumberInfoTable.SNI_DEADLINE, value);

				}
			}
		}

		List<SchoolNet> snList = snd.getAllSchoolNet(0, 1, SchoolNetTable.SN_ID, "desc", snFilter);
		List<LibraryYear> lyList = lyd.getLibraryYear(0, 1, LibraryYearTable.LY_ID, "desc", lyFilter);
		List<NewBooksthatYear> nbyList = nbyd.getAllNewBooksthatYear(0, 1, NewBooksthatYearTable.NBY_ID, "desc",
				nbyFilter);

		int people = 1;
		List<StudentNumberInfo> sniList = snid.getStudentNumberInfos(0, 1, StudentNumberInfoTable.SNI_ID, "desc",
				sniFilter, null);
		if (sniList != null && sniList.size() > 0)
			people = sniList.get(0).getSni_ordiundergrastu();

		if (snList != null && snList.size() > 0)
		{
			mainband = (snList.get(0).getSn_backbonebandwidth() == null ? 0 : snList.get(0).getSn_backbonebandwidth());
			outband = (snList.get(0).getSn_exportbandwidth() == null ? 0 : snList.get(0).getSn_exportbandwidth());
			networkaccess = (snList.get(0).getSn_informationcount() == null ? 0
					: snList.get(0).getSn_informationcount());
		}

		if (lyList != null && lyList.size() > 0)
		{
			paperbooktotal = (lyList.get(0).getLy_paperbooknumber() == null ? 0
					: lyList.get(0).getLy_paperbooknumber());
			ebooktotal = (lyList.get(0).getLy_ebooknumber() == null ? 0 : lyList.get(0).getLy_ebooknumber());
			paperjournaltotal = (lyList.get(0).getLy_paperjournalnumber() == null ? 0
					: lyList.get(0).getLy_paperjournalnumber());
			paperjournaltype = (lyList.get(0).getLy_paperjournaltype() == null ? 0
					: lyList.get(0).getLy_paperjournaltype());
			ejournaltype = (lyList.get(0).getLy_ejournaltype() == null ? 0 : lyList.get(0).getLy_ejournaltype());
			databasenumber = (lyList.get(0).getLy_databasenumber() == null ? 0 : lyList.get(0).getLy_databasenumber());
		}

		paperbookperstudent = paperbooktotal / people;
		ebookperstudent = ebooktotal / people;

		if (nbyList != null && nbyList.size() > 0)
		{
			newpaperbookthatyear = nbyList.get(0).getNby_paperbooksnumber();
			newebookthatyear = nbyList.get(0).getNby_ebooksnumber();
			bookcurrencythatyear = nbyList.get(0).getNby_bookcirculation();
		}

		newpaperbookthatyearperstudent = newpaperbookthatyear / people;

		NetworkBooks school = new NetworkBooks("学校情况", mainband + "", outband + "", networkaccess + "",
				paperbooktotal + "", paperbookperstudent + "", ebooktotal + "", ebookperstudent + "",
				newpaperbookthatyear + "", newpaperbookthatyearperstudent + "", newebookthatyear + "",
				bookcurrencythatyear + "", paperjournaltotal + "", paperjournaltype + "", ejournaltype + "",
				databasenumber + "", college);

		NetworkBooks standard = new NetworkBooks();
		standard.setPaperbookperstudent("100册");
		standard.setNewpaperbookthatyearperstudent("4册");
		standard.setRowTitle("办学标准指标/合格标准");

		NetworkBooks changmo = new NetworkBooks();
		changmo.setRowTitle("常模值");

		content.add(school);
		content.add(standard);
		content.add(changmo);

		return content;

	}
}
