<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">

<title>统计分析</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyUI/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyUI/themes/icon.css">

<link rel="stylesheet" href="<%=path %>/kendo/styles/kendo.common.min.css" />
<link rel="stylesheet" href="<%=path %>/kendo/styles/kendo.default.min.css" />
<link rel="stylesheet" href="<%=path %>/kendo/styles/kendo.dataviz.min.css" />
<link rel="stylesheet" href="<%=path %>/kendo/styles/kendo.dataviz.default.min.css" />
<script type="text/javascript" src="<%=path%>/kendo/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/kendo/js/kendo.all.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery.easyui.min.js"></script>


<style type="text/css">
<!--
a:link {
 text-decoration: none;
}
a:visited {
 text-decoration: none;
}
a:hover {
 text-decoration: none;
}
a:active {
 text-decoration: none;
}
-->
</style>
<script>

</script>


</head>

<body bgcolor="white" class = "h2">
	<h2>数据分析报告列表</h2>
	<ul class="easyui-tree">
        <li>
            <span>一、学校概要数据</span>
            <ul>
                <li><span><a href="#">概要1学校基本情况</a></span></li>
				<li><span><a href="admin/statistic_degreeSpot.jsp">概要2 学位点概况</a></span></li>
				<li><span><a href="#">概要3 学校《本科教学质量报告》支撑数据指标比较</a></span></li>
            </ul>
        </li>
		
		 <li>
            <span>二、审核评估基本数据</span>
            <ul>
                <li>
                    <span>1 定位与目标</span>
                    <ul>
                        <li><span><a href="#">1.1 办学指导思想</a></span></li>
						<li><span><a href="admin/statistic_leaderagedegree.jsp">1.2 校领导年龄和学位结构</a></span></li>
						<li><span><a href="admin/statistic_ManagerStructure.jsp">1.3 校级教学管理人员结构</a></span></li>
						<li><span><a href="admin/statistic_TeachRevoResult.jsp">1.4 教育教学改革与成果</a></span></li>
						<li><span><a href="admin/statistic_ProfessionLayout.jsp">1.5 专业布局概览</a></span></li>
						<li><span><a href="admin/statistic_teachingUnitDisciplineInfo.jsp">1.6 教学单位学科专业概览</a></span></li>
						
                    </ul>
                </li>
               
            </ul>
			
			 <ul>
                <li>
                    <span>2 教师队伍</span>
                    <ul>
                        <li><span><a href="admin/statistic_StudentsAndTeachers.jsp">2.1 学校生师比及教师情况</a></span></li>
						<li><span><a href="admin/statistic_teacherStructure.jsp">2.2 教师队伍结构</a></span></li>
						<li><span><a href="admin/statistic_TeachingUnitTeachersAndUnder.jsp">2.3各教学单位教师与本科生情况</a></span></li>
						<li><span><a href="admin/statistic_MajorTeacherStructureTOP5LOW5.jsp">2.4 主讲教师本科授课情况</a></span></li>
						<li><span><a href="admin/statistic_ProfessorAndAssociateForUnderSub.jsp">2.5 教授、副教授讲授本科课程比例</a></span></li>
						<li><span><a href="admin/statistic_TeachersTrainingInfo.jsp">2.6 教师培养培训情况</a></span></li>
						<li><span><a href="admin/statistic_MajorHeaderInfo.jsp">2.7 专业带头人情况</a></span></li>
						<li><span><a href="admin/statistic_labStuffInfo.jsp">2.8 学校实验系列人员结构</a></span></li>
						<li><span><a href="admin/statistic_TeachersReformAndAchieve.jsp">2.9 教师教育教学改革与成果</a></span></li>
						
                    </ul>
                </li>
            </ul>
			
			<ul>
                <li>
                    <span>3 教学资源</span>
                    <ul>
                        <li><span><a href="#">3.1 教学经费投入情况</a></span></li>
						<li>
							<span><a href="#">3.2 教学基本设施情况</a></span>
							<ul>
								<li><span><a href="admin/statistic_TeachingHouse.jsp">3.2.1 教学行政用房情况</a></span></li>
								<li><span><a href="admin/statistic_TeachingEquipmentInfo.jsp">3.2.2教学、科研仪器设备情况</a></span></li>
								<li><span><a href="admin/statistic_bachelorexpplace.jsp">3.2.3本科实验、实习、实训场所情况</a></span></li>
								<li><span><a href="admin/statistic_networkbooks.jsp">3.2.4校园网、图书情况</a></span></li>
							</ul>
						</li>
						<li><span><a href="admin/statistic_MajorOverview.jsp">3.3 专业情况概览</a></span></li>
						<li><span><a href="admin/statistic_AdvantageMajorOverview.jsp">3.4 优势专业概览</a></span></li>
						<li><span><a href="admin/statistic_NewMajorOverview.jsp">3.5 新设专业概览</a></span></li>
						<li><span><a href="admin/statistic_StartClassOverview.jsp">3.6 各教学单位课程开设情况</a></span></li>
						<li><span><a href="admin/statistic_TopLowProPracticeTeach.jsp">3.7 各专业实践教学情况</a></span></li>
						<li><span><a href="admin/statistic_StartClassInfo.jsp">3.8 全校课程开设情况</a></span></li>
						<li><span><a href="admin/statistic_ExcellentCourseBuild.jsp">3.9 精品（优秀）课程（群）建设</a></span></li>
						<li><span><a href="admin/statistic_CoopEducationInfo.jsp">3.10 合作办学情况</a></span></li>
						
                    </ul>
                </li>
            </ul>
			
			<ul>
                <li>
                    <span>4 培养过程</span>
                    <ul>
                        <li><span><a href="admin/statistic_ProfessionalCreditStructure.jsp">4.1 专业培养方案学分结构</a></span></li>
						<li><span><a href="admin/statistic_RenCaiPattern.jsp">4.2人才培养模式创新区情况</a></span></li>
						<li><span><a href="admin/statistic_ExpTeachCenter.jsp">4.3 实验教学示范中心</a></span></li>
						<li><span><a href="admin/statistic_PartGraduateCombinedTrain.jsp">4.4 毕业综合训练情况</a></span></li>
						<li><span><a href="admin/statistic_schoolculturesituation.jsp">4.5 校园文化活动情况</a></span></li>
						<li><span><a href="admin/statistic_studentassociationsituation.jsp">4.6 学生社团情况</a></span></li>
						<li><span><a href="admin/statistic_studentexchangesituation.jsp">4.7学生国际交流情况</a></span></li>
						<li><span><a href="admin/statistic_teachresearchreform.jsp">4.8各教学单位教育教学研究与改革情况</a></span></li>				
                    </ul>
                </li>
            </ul>
			
			<ul>
                <li>
                    <span>5 学生发展</span>
                    <ul>
                        <li><span><a href="admin/statistic_StudentSourceSituation.jsp">5.1 生源情况</a></span></li>
						<li><span><a href="admin/statistic_UndergraduateAdmissionRegister.jsp">5.2 各专业（大类）本科生招生报到情况</a></span></li>
						<li><span><a href="admin/statistic_StudentManagerStructure.jsp">5.3 学生管理人员结构</a></span></li>
						<li><span><a href="admin/statistic_TeachingUnitManagerAndStudent.jsp">5.4 各教学单位学生管理人员与学生情况</a></span></li>
						<li><span><a href="admin/statistic_UndergraduateAwardLoanInfo.jsp">5.5 本科生奖贷补情况</a></span></li>
						<li><span><a href="admin/statistic_StuDevelopInfo.jsp">5.6 学生发展情况</a></span></li>
						<li><span><a href="admin/statistic_GraduationSituationInfo.jsp">5.7 各专业毕业生情况</a></span></li>
						<li><span><a href="admin/statistic_GraduatesEmploymentsDistribution.jsp">5.8 毕业生就业去向分布情况</a></span></li>
                    </ul>
                </li>
            </ul>
			
			<ul>
                <li>
                    <span>6 质量保障</span>
                    <ul>
                        <li><span><a href="admin/statistic_TeachingQualityInfo.jsp">6.1 评教信息</a></span></li>
						<li><span><a href="admin/statistic_TeachingQualityManagerStructure.jsp">6.2 教学质量管理队伍结构</a></span></li>
						<li><span><a href="admin/statistic_TeachingMangersReformAndAchieve.jsp">6.3 教学管理队伍教学研究情况</a></span></li>
                    </ul>
                </li>
            </ul>
			
        </li>
        
		
		 <li>
            <span>三、详细数据附表</span>
            <ul>
                <li><span><a href="admin/statistic_schoolleaderinfo.jsp">附表1 校领导情况</a></span></li>
				<li><span><a href="admin/statistic_schteachmanageinfo.jsp">附表2 校级教学管理人员基本信息</a></span></li>
				<li><span><a href="admin/statistic_FullTimeTeacher.jsp">附表3 各教学单位专任教师结构</a></span></li>
				<li><span><a href="admin/statistic_TeachingUnitsLeader.jsp">附表4 各教学单位专业带头人情况</a></span></li>
				<li><span><a href="admin/statistic_UnitPersonnelStructure.jsp">附表5 各教学单位实验系列职称人员结构</a></span></li>
				<li><span><a href="admin/statistic_MajorTeacherStructureFromFTTI.jsp">附表6 各专业授课教师结构</a></span></li>
				<li><span><a href="admin/statistic_MajorTeacherStructure.jsp">附表7 各专业授课教师授课情况</a></span></li>
				<li><span><a href="admin/statistic_MajorStuAndTeacher.jsp">附表8 各专业教师、学生情况概览</a></span></li>
				<li><span><a href="admin/statistic_CampusExperiPracPlace.jsp">附表9 校内实验实习实训场所情况</a></span></li>
				<li><span><a href="admin/statistic_ProPracticeTeach.jsp">附表10 各专业实践教学情况</a></span></li>
				<li><span><a href="admin/statistic_MajorTeachingInfo.jsp">附表11 各专业教学情况一览表</a></span></li>
				<li><span><a href="admin/statistic_GraduateCombinedTrain.jsp">附表12 毕业综合训练情况</a></span></li>
				<li><span><a href="admin/statistic_UndergraduateAdmission.jsp">附表13 各专业（大类）本科生招生情况</a></span></li>
				<li><span><a href="admin/statistic_GraduationSituationDetails.jsp">附表14 各专业毕业生情况</a></span></li>
            </ul>
        </li>
		
    </ul>

</body>
</html>


