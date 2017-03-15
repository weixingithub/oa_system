package com.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.oa_bean.cabinet.FileCabinet;
import org.oa_bean.population.FamilyPlanning;
import org.oa_bean.population.LaborInsurance;
import org.oa_bean.population.Person;
import org.oa_bean.population.PopulationCivil;
import org.oa_bean.population.Stability;
import org.oa_common.date.DateTools;
import org.oa_common.excel.ExcelRead;

public class ExcelUtil {
	 //利用正则表达式验证手机号
   	public static final String REGEX_MOBILE = "^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$";
   	//正则表达式：验证身份证号
   	public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
	//读取解析后人口基本的数据
	public List<Person> excelByPerson(String filePath){
		List<Person> list = new ArrayList<Person>();
		try {
			ExcelRead.readExcel(new File(filePath));//调用解析方法
		    FileInputStream fin=new FileInputStream(filePath);  //文件流指向excel文件  
		    HSSFWorkbook workbook=new HSSFWorkbook(fin);//创建工作薄  
		    HSSFSheet sheet=workbook.getSheetAt(0);//得到工作表  
		    HSSFRow row=null;//对应excel的行  
		    HSSFCell cell=null;//对应excel的列
		    int totalRow=sheet.getLastRowNum();//得到excel的总记录条数 
			for(int i=1;i<=totalRow;i++){
				Person person=new Person();
				row=sheet.getRow(i);//获取行
				cell=row.getCell(0); //获取第一列
				if(row.getCell(0)!=null){
					person.setName(cell.getRichStringCellValue().toString());  
				}else{
					person.setName("");
				}
				cell=row.getCell(1); //获取第二列
				if(row.getCell(1)!=null){
					person.setOldname(cell.getRichStringCellValue().toString());  
				}else{
					person.setOldname("");  
				}
				cell=row.getCell(2);
				if(row.getCell(2)!=null){
					if(cell.getRichStringCellValue().toString().equals("男")){
						person.setSex("0");
					}else if(cell.getRichStringCellValue().toString().equals("女")){
						person.setSex("1");
					}
				}else if("".equals(cell.getRichStringCellValue().toString())){
					person.setSex("0");
				}
				
				cell=row.getCell(3);
				row.getCell(3).setCellType(cell.CELL_TYPE_STRING);//转换成String类型
				if(row.getCell(3)!=null){
					if(Pattern.matches(REGEX_ID_CARD,row.getCell(3).toString())){
						person.setIdnumber(row.getCell(3).getStringCellValue());
						System.out.println(row.getCell(3).getStringCellValue());
					}else{
						System.out.println(new Exception("第"+i+"行::身份证号错误！"));
						break;
					}
				}
				cell=row.getCell(4);//生日
				if(row.getCell(4)!=null){
					Date dates = row.getCell(4).getDateCellValue();
					SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd"); 
					String date1 = dff.format(dates);
					if(DateUtil.isCellDateFormatted(row.getCell(4))){// 判断单元格是否属于日期格式  
					     person.setBirthdate(date1);
					}
				}else if("".equals(cell.getRichStringCellValue().toString())){
					person.setBirthdate("");
				}
				cell=row.getCell(5);
				if(row.getCell(5)!=null){
					if(cell.getRichStringCellValue().toString().equals("汉族")){
						person.setNation("1");
					}else if(cell.getRichStringCellValue().toString().equals("少数民族")){
						person.setNation("2");
					}
				}else if("".equals(cell.getRichStringCellValue().toString())){
					person.setNation("");
				}
				cell=row.getCell(6);
				if(row.getCell(6)!=null){
					if(cell.getRichStringCellValue().toString().equals("A型")){
						person.setBloodtype("1");
					}else if(cell.getRichStringCellValue().toString().equals("B型")){
						person.setBloodtype("2");
					}else if(cell.getRichStringCellValue().toString().equals("AB型")){
						person.setBloodtype("3");
					}else if(cell.getRichStringCellValue().toString().equals("O型")){
						person.setBloodtype("4");
					}else if(cell.getRichStringCellValue().toString().equals("其它")){
						person.setBloodtype("5");
					}
				}else if("".equals(cell.getRichStringCellValue().toString())){
					person.setBloodtype("");
				}
				cell=row.getCell(7);
				if(row.getCell(7)!=null){
					  row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
			          person.setTall(row.getCell(7).getStringCellValue());
			     }
				cell=row.getCell(8);
				if(row.getCell(8)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						person.setCitywoman("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						person.setCitywoman("2");
					}
				}else if("".equals(cell.getRichStringCellValue().toString())){
					person.setCitywoman("");
				}
				cell=row.getCell(9);
				if(row.getCell(9)!=null){
					if(cell.getRichStringCellValue().toString().equals("农户")){
						person.setResidenttype("1");
					}else if(cell.getRichStringCellValue().toString().equals("非农户")){
						person.setResidenttype("2");
					}
				}else if("".equals(cell.getRichStringCellValue().toString())){
					person.setResidenttype("");
				}
				cell=row.getCell(10);
				if(row.getCell(10)!=null){
					if(cell.getRichStringCellValue().toString().equals("党员")){
						person.setPolitical("1");
					}else if(cell.getRichStringCellValue().toString().equals("团员")){
						person.setPolitical("2");
					}else if(cell.getRichStringCellValue().toString().equals("群众")){
						person.setPolitical("3");
					}else if(cell.getRichStringCellValue().toString().equals("其它")){
						person.setPolitical("4");
					}
				}else if("".equals(cell.getRichStringCellValue().toString())){
					person.setPolitical("");
				}
				cell=row.getCell(11);
				if(row.getCell(11)!=null){
					person.setRegistry_place(cell.getRichStringCellValue().toString());
				}else if("".equals(cell.getRichStringCellValue().toString())){
					person.setRegistry_place("");
				}
				cell=row.getCell(12);
				if(row.getCell(12)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						person.setJurisdiction("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						person.setJurisdiction("0");
					}
				}else if("".equals(cell.getRichStringCellValue().toString())){
					person.setJurisdiction("");
				}
				cell=row.getCell(13);
				if(row.getCell(13)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						person.setUniformIdentification("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						person.setUniformIdentification("0");
					}
				}else if("".equals(cell.getRichStringCellValue().toString())){
					person.setUniformIdentification("");
				}
				cell=row.getCell(14);
				if(row.getCell(14)!=null){
					person.setCurrent_address(cell.getRichStringCellValue().toString());
				}else if("".equals(cell.getRichStringCellValue().toString())){
					person.setCurrent_address("");
				}
				cell=row.getCell(15);
				if(row.getCell(15)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						person.setVillage_household("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						person.setVillage_household("0");
					}
				}else if("".equals(cell.getRichStringCellValue().toString())){
					person.setVillage_household("");
				}
				cell=row.getCell(16);
				if(row.getCell(16)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						person.setEconomicdistribution("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						person.setEconomicdistribution("0");
					}
				}else if("".equals(cell.getRichStringCellValue().toString())){
					person.setEconomicdistribution("");
				}
				cell=row.getCell(17);
				person.setOccupation(cell.getRichStringCellValue().toString());
				cell=row.getCell(18);
				person.setReligion(cell.getRichStringCellValue().toString());
				cell=row.getCell(19);
				if(row.getCell(19)!=null){
					if(cell.getRichStringCellValue().toString().equals("未婚")){
						person.setMarital_status("1");
					}else if(cell.getRichStringCellValue().toString().equals("已婚")){
						person.setMarital_status("2");
					}else if(cell.getRichStringCellValue().toString().equals("离婚")){
						person.setMarital_status("3");
					}else if(cell.getRichStringCellValue().toString().equals("丧偶")){
						person.setMarital_status("4");
					}
				}else if("".equals(cell.getRichStringCellValue().toString())){
					person.setMarital_status("");
				}
				cell=row.getCell(20);
				if(row.getCell(20)!=null){//手机格式验证
					row.getCell(20).setCellType(cell.CELL_TYPE_STRING);
					if(Pattern.matches(REGEX_MOBILE,row.getCell(20).toString())){
						person.setContact(row.getCell(20).getStringCellValue());
						System.out.println(row.getCell(20).getStringCellValue());
					}else{
						System.out.println( new Exception( "第"+i+"行:手机格式不正确"));
						break;
					}
				}
				cell=row.getCell(21);
				if(row.getCell(21)!=null){
					row.getCell(21).setCellType(cell.CELL_TYPE_STRING);
			        person.setContact(row.getCell(21).getStringCellValue());
				}
				String createTime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
				person.setCreatime(createTime);//添加创建时间
				person.setFamilyPlanning(new FamilyPlanning());//计生信息
				person.setPopulationCivil(new PopulationCivil());//民政信息
				person.setLaborInsurance(new LaborInsurance());//劳保信息
				person.setStability(new Stability());//综治信息
				person.setFileCabinet(new FileCabinet());//文件管理
				list.add(person);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
	/**
	 * 计生信息导入
	 * @param fileName
	 * @return
	 */
	public List<Person> excelByFamilyPlanning(String filePath){
		List<Person> list=new ArrayList<Person>();
		try {
			ExcelRead.readExcel(new File(filePath));//调用解析方法
			System.out.println(filePath);
		    FileInputStream fin=new FileInputStream(filePath);  //文件流指向excel文件  
		    HSSFWorkbook workbook=new HSSFWorkbook(fin);//创建工作薄  
		    HSSFSheet sheet=workbook.getSheetAt(0);//得到工作表  
		    HSSFRow row=null;//对应excel的行  
		    HSSFCell cell=null;//对应excel的列
		    Date dates=new Date();
		    SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		    int totalRow=sheet.getLastRowNum();//得到excel的总记录条数 
			for(int i=1;i<=totalRow;i++){
				FamilyPlanning familyPlanning=new FamilyPlanning();
				Person person=new Person();
				row=sheet.getRow(i);//获取行
				cell=row.getCell(0); //姓名
				if(row.getCell(0)!=null){
					person.setName(cell.getRichStringCellValue().toString());
				}
				cell=row.getCell(1);//身份证号
				if(row.getCell(1)!=null){
					row.getCell(1).setCellType(cell.CELL_TYPE_STRING);//转换成String类型
					if(Pattern.matches(REGEX_ID_CARD,row.getCell(1).toString())){
						person.setIdnumber(row.getCell(1).getStringCellValue());
						System.out.println(row.getCell(1).getStringCellValue());
					}else{
						System.out.println(new Exception("第"+i+"行::身份证号错误！"));
						break;
					}
				}
				
				cell=row.getCell(2);//婚育情况
				if(row.getCell(2)!=null){
					if(cell.getRichStringCellValue().toString().equals("未婚")){
						familyPlanning.setMarriagestatus("1");
					}else if(cell.getRichStringCellValue().toString().equals("初婚")){
						familyPlanning.setMarriagestatus("2");
					}else if(cell.getRichStringCellValue().toString().equals("离婚")){
						familyPlanning.setMarriagestatus("3");
					}else if(cell.getRichStringCellValue().toString().equals("再婚")){
						familyPlanning.setMarriagestatus("4");
					}else if(cell.getRichStringCellValue().toString().equals("三婚")){
						familyPlanning.setMarriagestatus("5");
					}
				}else{
					familyPlanning.setMarriagestatus("");
				}
				
				cell=row.getCell(3);//初婚时间
				if(row.getCell(3)!=null){
					 dates =row.getCell(3).getDateCellValue();
					 String date1 = dff.format(dates);
						if(DateUtil.isCellDateFormatted(row.getCell(3))){// 判断单元格是否属于日期格式  
						    familyPlanning.setMarriagetime(date1);
						}
				}else{
					familyPlanning.setMarriagetime("");
				}
				
				cell=row.getCell(4);//婚姻变动时间
				if(row.getCell(4)!=null){
					dates = row.getCell(4).getDateCellValue();
					String change = dff.format(dates);
					if(DateUtil.isCellDateFormatted(row.getCell(4))){// 判断单元格是否属于日期格式  
					    familyPlanning.setMarriagechangetime(change);
					}
				}else{
					familyPlanning.setMarriagechangetime("");
				}
				
				
				cell=row.getCell(5);//节育措施
				if(row.getCell(5)!=null){
					if(cell.getRichStringCellValue().toString().equals("上环")){
						familyPlanning.setContraceptive("1");
					}else if(cell.getRichStringCellValue().toString().equals("药具")){
						familyPlanning.setContraceptive("2");
					}else if(cell.getRichStringCellValue().toString().equals("结扎")){
						familyPlanning.setContraceptive("3");
					}else if(cell.getRichStringCellValue().toString().equals("皮理")){
						familyPlanning.setContraceptive("4");
					}else if(cell.getRichStringCellValue().toString().equals("其他")){
						familyPlanning.setContraceptive("5");
					}
				}else{
					familyPlanning.setContraceptive("");
				}
				
				cell=row.getCell(6);//节育时间
				if(row.getCell(6)!=null){
					dates = row.getCell(6).getDateCellValue();
					String contraception = dff.format(dates);
					if(DateUtil.isCellDateFormatted(row.getCell(6))){// 判断单元格是否属于日期格式  
					    familyPlanning.setContraceptiontime(contraception);
					}
				}else{
					familyPlanning.setContraceptiontime("");
				}
				
				cell=row.getCell(7);//是否领取独生子女证
				if(row.getCell(7)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						familyPlanning.setOnlychildstatus("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						familyPlanning.setOnlychildstatus("0");
					} 
				}else{
					familyPlanning.setOnlychildstatus("");
				}
				
				cell=row.getCell(8);//年审号(数字转换)
				if(row.getCell(8)!=null){
					row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
			        familyPlanning.setAnnualnumber(row.getCell(8).getStringCellValue());
				}
				
				cell=row.getCell(9);//是否有二胎指标
				if(row.getCell(9)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						familyPlanning.setTwotires("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						familyPlanning.setTwotires("0");
					}
				}else{
					familyPlanning.setTwotires("");
				}
				
				cell=row.getCell(10);//是否领取独生子女父母补助金
				if(row.getCell(10)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						familyPlanning.setChildgrants("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						familyPlanning.setChildgrants("0");
					}
				}else{
					familyPlanning.setChildgrants("");
				}
				
				cell=row.getCell(11);//独生子女补助金额
				if(row.getCell(11)!=null){
					 row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
			         familyPlanning.setChildsubsidyamount(row.getCell(11).getStringCellValue());
			    }
				
				cell=row.getCell(12);//独生金额领取时间
				if(row.getCell(12)!=null){
					dates = row.getCell(12).getDateCellValue();
					String onlyamount = dff.format(dates);
					if(DateUtil.isCellDateFormatted(row.getCell(12))){// 判断单元格是否属于日期格式  
					    familyPlanning.setOnlyamounttime(onlyamount);
					}
				}else{
					familyPlanning.setOnlyamounttime("");
				}
				
				cell=row.getCell(13);//是否办理生育登记
				if(row.getCell(13)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						familyPlanning.setBirthregistration("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						familyPlanning.setBirthregistration("0");
					}
				}else{
					familyPlanning.setBirthregistration("");
				} 
				
				cell=row.getCell(14);//是否办理三查
				if(row.getCell(14)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						familyPlanning.setRichardIII("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						familyPlanning.setRichardIII("0");
					}
				}else{
					familyPlanning.setRichardIII("");
				} 
				
				cell=row.getCell(15);//是否办理流动人口婚育证
				if(row.getCell(15)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						familyPlanning.setObstetricalcard("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						familyPlanning.setObstetricalcard("0");
					}
				}else{
					familyPlanning.setObstetricalcard("");
				} 
				
				cell=row.getCell(16);//独生子女证领取地
				if(row.getCell(16)!=null){
					familyPlanning.setOnlychildstatuslqd(cell.getRichStringCellValue().toString());
				}else{
					familyPlanning.setOnlychildstatuslqd("");
				}
				cell=row.getCell(17);//独生子女费领取地
				if(row.getCell(17)!=null){
					familyPlanning.setChildgrantslqd(cell.getRichStringCellValue().toString());
				}else{
					familyPlanning.setChildgrantslqd("");
				}
				cell=row.getCell(18);//是否领取保健费
				if(row.getCell(18)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						familyPlanning.setSubsidiesstate("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						familyPlanning.setSubsidiesstate("0");
					}
				}else{
					familyPlanning.setSubsidiesstate("");
				} 
				
				cell=row.getCell(19);//领取时间
				if(row.getCell(19)!=null){
					dates = row.getCell(19).getDateCellValue();
					String sidies = dff.format(dates);
					if(DateUtil.isCellDateFormatted(row.getCell(19))){// 判断单元格是否属于日期格式  
					    familyPlanning.setGetsubsidiestime(sidies);
					}
				}else{
					familyPlanning.setGetsubsidiestime("");
				}
				
				cell=row.getCell(20);//领取金额
				if(row.getCell(20)!=null){
					row.getCell(20).setCellType(Cell.CELL_TYPE_STRING);
			        familyPlanning.setMoney(row.getCell(20).getStringCellValue());
			    }
				
				cell=row.getCell(21);//社会抚养费
				if(row.getCell(21)!=null){
					row.getCell(21).setCellType(Cell.CELL_TYPE_STRING);
			        familyPlanning.setSocialcompensationfee(row.getCell(21).getStringCellValue());
			    }
				
				cell=row.getCell(22);//参加孕前优生人数
				if(row.getCell(22)!=null){
					row.getCell(22).setCellType(Cell.CELL_TYPE_STRING);
			        familyPlanning.setPregnantnumber(row.getCell(22).getStringCellValue());
			    }
				
				cell=row.getCell(23);//接受婴幼儿疫苗人数
				if(row.getCell(23)!=null){
					row.getCell(23).setCellType(Cell.CELL_TYPE_STRING);
			        familyPlanning.setVaccinenumber(row.getCell(23).getStringCellValue());
			    }
				
				person.setFamilyPlanning(familyPlanning);
				list.add(person);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
	/**
	 * 劳保信息导入
	 * @param fileName
	 * @return
	 */
	public List<Person> excelByLabour(String filePath){
		List<Person> list=new ArrayList<Person>();
		try {
			ExcelRead.readExcel(new File(filePath));//调用解析方法
			System.out.println(filePath);
		    FileInputStream fin=new FileInputStream(filePath);  //文件流指向excel文件  
		    HSSFWorkbook workbook=new HSSFWorkbook(fin);//创建工作薄  
		    HSSFSheet sheet=workbook.getSheetAt(0);//得到工作表  
		    HSSFRow row=null;//对应excel的行  
		    HSSFCell cell=null;//对应excel的列
		    int totalRow=sheet.getLastRowNum();//得到excel的总记录条数 
			for(int i=1;i<=totalRow;i++){
				LaborInsurance labour=new LaborInsurance();
				Person person=new Person();
				row=sheet.getRow(i);//获取行
				cell=row.getCell(0); //姓名
				if(row.getCell(0)!=null){
					person.setName(cell.getRichStringCellValue().toString());
				}
				cell=row.getCell(1);//身份证号
				if(row.getCell(1)!=null){
					row.getCell(1).setCellType(cell.CELL_TYPE_STRING);//转换成String类型
					if(Pattern.matches(REGEX_ID_CARD,row.getCell(1).toString())){
						person.setIdnumber(row.getCell(1).getStringCellValue());
						System.out.println(row.getCell(1).getStringCellValue());
					}else{
						System.out.println(new Exception("第"+i+"行:身份证号错误！"));
						break;
					}
				}
				
				cell=row.getCell(2);//养老保险
				if(row.getCell(2)!=null){
					if(cell.getRichStringCellValue().toString().equals("参保")){
						labour.setEndowmentinsurance("1");
					}else if(cell.getRichStringCellValue().toString().equals("未参保")){
						labour.setEndowmentinsurance("0");
					}
				}else{
					labour.setEndowmentinsurance("");
				}
				
				cell=row.getCell(3);//养老保险类型
				if(row.getCell(3)!=null){
					if(cell.getRichStringCellValue().toString().equals("职工养老保险")){
						labour.setPensiontype("1");
					}else if(cell.getRichStringCellValue().toString().equals("城镇医疗保险")){
						labour.setPensiontype("2");
					}else if(cell.getRichStringCellValue().toString().equals("商业保险")){
						labour.setPensiontype("3");
					}else if(cell.getRichStringCellValue().toString().equals("失地农民养老保险")){
						labour.setPensiontype("4");
					}
				}else{
					labour.setPensiontype("");
				}
				
				cell=row.getCell(4);//是否享受养老金返还
				if(row.getCell(4)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						labour.setPensionreturn("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						labour.setPensionreturn("0");
					}
				}else{
					labour.setPensionreturn("");
				}
				
				cell=row.getCell(5);//医疗保险
				if(row.getCell(5)!=null){
					if(cell.getRichStringCellValue().toString().equals("参保")){
						labour.setMedicalinsurance("1");
					}else if(cell.getRichStringCellValue().toString().equals("未参保")){
						labour.setMedicalinsurance("0");
					}
				}else{
					labour.setMedicalinsurance("");
				}
				
				cell=row.getCell(6);//医疗保险类型
				if(row.getCell(6)!=null){
					if(cell.getRichStringCellValue().toString().equals("职工医疗保险")){
						labour.setMedicaltype("1");
					}else if(cell.getRichStringCellValue().toString().equals("城镇居民医疗保险")){
						labour.setMedicaltype("2");
					}else if(cell.getRichStringCellValue().toString().equals("新农合")){
						labour.setMedicaltype("3");
					}else if(cell.getRichStringCellValue().toString().equals("商业保险")){
						labour.setMedicaltype("4");
					}
				}else{
					labour.setMedicaltype("");
				}
				
				cell=row.getCell(7);//是否享受国家低保
				if(row.getCell(7)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						labour.setNationallowances("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						labour.setNationallowances("0");
					}
				}else{
					labour.setNationallowances("");
				}	
				cell=row.getCell(8);//是否享受社保补贴
				if(row.getCell(8)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						labour.setSocialsubsidies("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						labour.setSocialsubsidies("0");
					}
				}else{
					labour.setSocialsubsidies("");
				}
				cell=row.getCell(9);//享受年限
				if(row.getCell(9)!=null){
					row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
			        labour.setEnjoymenttime(row.getCell(9).getStringCellValue());
				}else{
					labour.setEnjoymenttime("");
				}
				cell=row.getCell(10);//是否领取养老金
				if(row.getCell(10)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						labour.setRetirepension("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						labour.setRetirepension("0");
					}
				}else{
					labour.setRetirepension("");
				}
				cell=row.getCell(11);//领取地
				if(row.getCell(11)!=null){
					labour.setPensionlqd(cell.getRichStringCellValue().toString());	
				}else{
					labour.setPensionlqd("");	
				}
				cell=row.getCell(12);//就业状态
				if(row.getCell(12)!=null){
					if(cell.getRichStringCellValue().toString().equals("已就业")){
						labour.setEmploymentstatus("1");
					}else if(cell.getRichStringCellValue().toString().equals("未就业")){
						labour.setEmploymentstatus("2");
					}else if(cell.getRichStringCellValue().toString().equals("再就业")){
						labour.setEmploymentstatus("3");
					}
				}else{
					labour.setEmploymentstatus("");
				}
				cell=row.getCell(13);//就业性质
				if(row.getCell(13)!=null){
					if(cell.getRichStringCellValue().toString().equals("企事业单位")){
						labour.setEmploymentnature("1");
					}else if(cell.getRichStringCellValue().toString().equals("私营企业")){
						labour.setEmploymentnature("2");
					}else if(cell.getRichStringCellValue().toString().equals("灵活就业")){
						labour.setEmploymentnature("3");
					}else if(cell.getRichStringCellValue().toString().equals("公益岗位")){
						labour.setEmploymentnature("4");
					}else if(cell.getRichStringCellValue().toString().equals("退休")){
						labour.setEmploymentnature("5");
					}
				}else{
					labour.setEmploymentnature("");
				}
				cell=row.getCell(14);//就职单位名称
				if(row.getCell(14)!=null){
					labour.setOfficename(cell.getRichStringCellValue().toString());
				}else{
					labour.setOfficename("");
				}
				cell=row.getCell(15);//求职意向
				if(row.getCell(15)!=null){
					if(cell.getRichStringCellValue().toString().equals("自主创业")){
						labour.setJobintension("1");
					}else if(cell.getRichStringCellValue().toString().equals("灵活就业")){
						labour.setJobintension("2");
					}else if(cell.getRichStringCellValue().toString().equals("企事业单位")){
						labour.setJobintension("3");
					}else if(cell.getRichStringCellValue().toString().equals("暂不考虑就业")){
						labour.setJobintension("4");
					}else if(cell.getRichStringCellValue().toString().equals("其它")){
						labour.setJobintension("5");
					}
				}else{
					labour.setJobintension("");
				}
				cell=row.getCell(16);//是否办理就业失业登记
				if(row.getCell(16)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						labour.setUnemployregistration("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						labour.setUnemployregistration("0");
					}
				}else{
					labour.setUnemployregistration("");
				}
				cell=row.getCell(17);//备注
				if(row.getCell(17)!=null){
					labour.setRemark(cell.getRichStringCellValue().toString());
				}else{
					labour.setRemark("");
				}
				cell=row.getCell(18);//私营企业是否享受小额贷款
				if(row.getCell(18)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						labour.setEnjoypettyloan("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						labour.setEnjoypettyloan("0");
					}
				}else{
					labour.setEnjoypettyloan("");
				}
				person.setLaborInsurance(labour);//把劳保信息添至人口信息
				list.add(person);
			}
		} catch (IOException e) {
			e.printStackTrace();
			list = null;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	/**
	 * 民政信息导入
	 * @param fileName
	 * @return
	 */
	public List<Person> excelByCivil(String filePath){
		List<Person> list=new ArrayList<Person>();
		try {
			ExcelRead.readExcel(new File(filePath));//调用解析方法
			System.out.println(filePath);
		    FileInputStream fin=new FileInputStream(filePath);  //文件流指向excel文件  
		    HSSFWorkbook workbook=new HSSFWorkbook(fin);//创建工作薄  
		    HSSFSheet sheet=workbook.getSheetAt(0);//得到工作表  
		    HSSFRow row=null;//对应excel的行  
		    HSSFCell cell=null;//对应excel的列
		    Date dates=new Date();
		    SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		    int totalRow=sheet.getLastRowNum();//得到excel的总记录条数 
			for(int i=1;i<=totalRow;i++){
				PopulationCivil civil=new PopulationCivil();
				Person person=new Person();
				row=sheet.getRow(i);//获取行
				cell=row.getCell(0); //姓名
				if(row.getCell(0)!=null){
					person.setName(cell.getRichStringCellValue().toString());
				}
				cell=row.getCell(1);//身份证号
				if(row.getCell(1)!=null){
					row.getCell(1).setCellType(cell.CELL_TYPE_STRING);//转换成String类型
					if(Pattern.matches(REGEX_ID_CARD,row.getCell(1).toString())){
						person.setIdnumber(row.getCell(1).getStringCellValue());
						System.out.println(row.getCell(1).getStringCellValue());
					}else{
						System.out.println(new Exception("第"+i+"行:身份证号错误！"));
						break;
					}
				}
				
				cell=row.getCell(2);//是否残疾
				if(row.getCell(2)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						civil.setDisabled("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						civil.setDisabled("0");
					}
				}else{
					civil.setDisabled("");
				}
				cell=row.getCell(3);//残疾类型
				if(row.getCell(3)!=null){
					civil.setDeformitystate(cell.getRichStringCellValue().toString());
				}else{
					civil.setDeformitystate("");
				}
				cell=row.getCell(4);//残疾证号
				if(row.getCell(4)!=null){
					row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
			        civil.setDisabilitynumber(row.getCell(4).getStringCellValue());
				}else{
					civil.setDisabilitynumber("");
				}
				cell=row.getCell(5);//残疾级别
				if(row.getCell(5)!=null){
					if(cell.getRichStringCellValue().toString().equals("一级")){
						civil.setDisablitygrade("1");
					}else if(cell.getRichStringCellValue().toString().equals("二级")){
						civil.setDisablitygrade("2");
					}else if(cell.getRichStringCellValue().toString().equals("三级")){
						civil.setDisablitygrade("3");
					}else if(cell.getRichStringCellValue().toString().equals("四级")){
						civil.setDisablitygrade("4");
					}else if(cell.getRichStringCellValue().toString().equals("五级")){
						civil.setDisablitygrade("5");
					}else if(cell.getRichStringCellValue().toString().equals("六级")){
						civil.setDisablitygrade("6");
					}else if(cell.getRichStringCellValue().toString().equals("七级")){
						civil.setDisablitygrade("7");
					}else if(cell.getRichStringCellValue().toString().equals("八级")){
						civil.setDisablitygrade("8");
					}else if(cell.getRichStringCellValue().toString().equals("九级")){
						civil.setDisablitygrade("9");
					}else if(cell.getRichStringCellValue().toString().equals("十级")){
						civil.setDisablitygrade("10");
					}
				}else{
					civil.setDisablitygrade("");
				}
				cell=row.getCell(6);//是否孤残儿
				if(row.getCell(6)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						civil.setOrphanschildren("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						civil.setOrphanschildren("0");
					}
				}else{
					civil.setOrphanschildren("");
				}
				cell=row.getCell(7);//是否领取孤儿补助
				if(row.getCell(7)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						civil.setReceivegrantsorphan("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						civil.setReceivegrantsorphan("0");
					}
				}else{
					civil.setReceivegrantsorphan("");
				}
				cell=row.getCell(8);//孤儿补助金额
				if(row.getCell(8)!=null){
					row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
			        civil.setOrphanmoney(row.getCell(8).getStringCellValue());
				}else{
					 civil.setOrphanmoney("0");
				}
				cell=row.getCell(9);//是否享受残疾人津贴
				if(row.getCell(9)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						civil.setDisabilitybenefits("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						civil.setDisabilitybenefits("0");
					}
				}else{
					civil.setDisabilitybenefits("");
				}
				cell=row.getCell(10);//残疾人补助金额
				if(row.getCell(10)!=null){
					row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
			        civil.setDisabledmoney(row.getCell(10).getStringCellValue());
				}else{
					civil.setDisabledmoney("0");
				}
				cell=row.getCell(11);//是否现役军人
				if(row.getCell(11)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						civil.setActivearmy("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						civil.setActivearmy("0");
					}
				}else{
					civil.setActivearmy("");
				}
				cell=row.getCell(12);//是否为孤寡老人
				if(row.getCell(12)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						civil.setElderly("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						civil.setElderly("0");
					}
				}else{
					civil.setElderly("");
				}
				cell=row.getCell(13);//是否领取高龄津贴
				if(row.getCell(13)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						civil.setSubsidies("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						civil.setSubsidies("0");
					}
				}else{
					civil.setSubsidies("");
				}
				cell=row.getCell(14);//高龄津贴金额
				if(row.getCell(14)!=null){
					row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
			        civil.setSubsidiesmoney(row.getCell(14).getStringCellValue());
				}else{
					civil.setSubsidiesmoney("");
				}
				cell=row.getCell(15);//是否退伍军人
				if(row.getCell(15)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						civil.setVeteran("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						civil.setVeteran("0");
					}
				}else{
					civil.setVeteran("");
				}
				cell=row.getCell(16);//是否为空巢老人
				if(row.getCell(16)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						civil.setEmptynester("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						civil.setEmptynester("0");
					}
				}else{
					civil.setEmptynester("");
				}
				cell=row.getCell(17);//老红军
				if(row.getCell(17)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						civil.setOldarmy("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						civil.setOldarmy("0");
					}
				}else{
					civil.setOldarmy("");
				}
				cell=row.getCell(18);//复转干部
				if(row.getCell(18)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						civil.setRecadres("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						civil.setRecadres("0");
					}
				}else{
					civil.setRecadres("");
				}
				cell=row.getCell(19);//是否劳模
				if(row.getCell(19)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						civil.setModelworkers("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						civil.setModelworkers("0");
					}
				}else{
					civil.setModelworkers("");
				}
				cell=row.getCell(20);//参加老年体检人数
				if(row.getCell(20)!=null){
					row.getCell(20).setCellType(Cell.CELL_TYPE_STRING);
					civil.setCheckamount(row.getCell(20).getStringCellValue());
				}else{
					civil.setCheckamount("");
				}
				
				person.setPopulationCivil(civil);
				list.add(person);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
	
	/**
	 * 综治信息导入
	 * @param fileName
	 * @return
	 */
	public List<Person> excelByStability(String filePath){
		List<Person> list=new ArrayList<Person>();
		try {
			ExcelRead.readExcel(new File(filePath));//调用解析方法
			System.out.println(filePath);
		    FileInputStream fin=new FileInputStream(filePath);  //文件流指向excel文件  
		    HSSFWorkbook workbook=new HSSFWorkbook(fin);//创建工作薄  
		    HSSFSheet sheet=workbook.getSheetAt(0);//得到工作表  
		    HSSFRow row=null;//对应excel的行  
		    HSSFCell cell=null;//对应excel的列
		    Date dates=new Date();
		    SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd"); 
		    int totalRow=sheet.getLastRowNum();//得到excel的总记录条数 
			for(int i=1;i<=totalRow;i++){
				Stability stability=new Stability();
				Person person=new Person();
				row=sheet.getRow(i);//获取行
				cell=row.getCell(0); //姓名
				if(row.getCell(0)!=null){
					person.setName(cell.getRichStringCellValue().toString());
				}
				cell=row.getCell(1);//身份证号
				if(row.getCell(1)!=null){
					row.getCell(1).setCellType(cell.CELL_TYPE_STRING);//转换成String类型
					if(Pattern.matches(REGEX_ID_CARD,row.getCell(1).toString())){
						person.setIdnumber(row.getCell(1).getStringCellValue());
						System.out.println(row.getCell(1).getStringCellValue());
					}else{
						System.out.println(new Exception("第"+i+"行::身份证号错误！"));
						break;
					}
				}
				
				cell=row.getCell(2);//是否社区矫正人员
				if(row.getCell(2)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						stability.setCommunitycorrection("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						stability.setCommunitycorrection("0");
					}
				}else{
					stability.setCommunitycorrection("");
				}	
				cell=row.getCell(3);//社区矫正人员编号
				if(row.getCell(3)!=null){
					row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
					stability.setRectifyid(row.getCell(3).getStringCellValue());
				}else{
					stability.setRectifyid("");
				}
				cell=row.getCell(4);//原羁押场所
				if(row.getCell(4)!=null){
					stability.setCommunitycorrection(cell.getRichStringCellValue().toString());
				}else{
					stability.setCommunitycorrection("");
				}
				cell=row.getCell(5);//矫正类别
				if(row.getCell(5)!=null){
					if(cell.getRichStringCellValue().toString().equals("管制")){
						stability.setRectifytype("1");
					}else if(cell.getRichStringCellValue().toString().equals("缓刑")){
						stability.setRectifytype("2");
					}else if(cell.getRichStringCellValue().toString().equals("假释")){
						stability.setRectifytype("3");
					}else if(cell.getRichStringCellValue().toString().equals("暂予监外执行")){
						stability.setRectifytype("4");
					}else if(cell.getRichStringCellValue().toString().equals("剥夺政治权利")){
						stability.setRectifytype("5");
					}
				}else{
					stability.setRectifytype("");
				}
				cell=row.getCell(6);//矫正开始日期
				if(row.getCell(6)!=null){
					dates = row.getCell(6).getDateCellValue();
					String start = dff.format(dates);
					if(DateUtil.isCellDateFormatted(row.getCell(6))){// 判断单元格是否属于日期格式  
					   stability.setRectifystartdate(start);
					}
				}else{
					 stability.setRectifystartdate("");
				}
				cell=row.getCell(7);//矫正结束日期
				if(row.getCell(7)!=null){
					dates = row.getCell(7).getDateCellValue();
					String end = dff.format(dates);
					if(DateUtil.isCellDateFormatted(row.getCell(7))){// 判断单元格是否属于日期格式  
					   stability.setRectifyenddate(end);
					}
				}else{
					 stability.setRectifyenddate("");
				}
				cell=row.getCell(8);//是否非法集资人员
				if(row.getCell(8)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						stability.setIllegalfund("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						stability.setIllegalfund("0");
					}
				}else{
					stability.setIllegalfund("");
				}
				cell=row.getCell(9);//是否两劳释放人员
				if(row.getCell(9)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						stability.setReeducationreform("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						stability.setReeducationreform("0");
					}
				}else{
					stability.setReeducationreform("");
				}
				cell=row.getCell(10);//是否累惯犯
				if(row.getCell(10)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						stability.setRecidivist("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						stability.setRecidivist("0");
					}
				}else{
					stability.setRecidivist("");
				}
				cell=row.getCell(11);//“四史”情况
				if(row.getCell(11)!=null){
					if(cell.getRichStringCellValue().toString().equals("吸毒史")){
						stability.setFoursituation("1");
					}else if(cell.getRichStringCellValue().toString().equals("逃脱史")){
						stability.setFoursituation("2");
					}else if(cell.getRichStringCellValue().toString().equals("自杀史")){
						stability.setFoursituation("3");
					}else if(cell.getRichStringCellValue().toString().equals("袭警史")){
						stability.setFoursituation("4");
					}
				}else{
					stability.setFoursituation("");
				}
				cell=row.getCell(12);//“三涉”情况
				if(row.getCell(12)!=null){
					if(cell.getRichStringCellValue().toString().equals("涉毒")){
						stability.setThreesituation("1");
					}else if(cell.getRichStringCellValue().toString().equals("涉黑")){
						stability.setThreesituation("2");
					}else if(cell.getRichStringCellValue().toString().equals("涉枪")){
						stability.setThreesituation("3");
					}
				}else{
					stability.setThreesituation("");
				}
				cell=row.getCell(13);//是否重点上访人员
				if(row.getCell(13)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						stability.setFocuspetitioners("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						stability.setFocuspetitioners("0");
					}
				}else{
					stability.setFocuspetitioners("");
				}
				cell=row.getCell(14);//是否涉核人员
				if(row.getCell(14)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						stability.setNuclearpersonnel("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						stability.setNuclearpersonnel("0");
					}
				}else{
					stability.setNuclearpersonnel("");
				}
				cell=row.getCell(15);//是否参与邪教组织
				if(row.getCell(15)!=null){
					if(cell.getRichStringCellValue().toString().equals("是")){
						stability.setInvolvedInCults("1");
					}else if(cell.getRichStringCellValue().toString().equals("否")){
						stability.setInvolvedInCults("0");
					}
				}else{
					stability.setInvolvedInCults("");
				}
				person.setStability(stability);
				list.add(person);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
}

