package com.controller.cabinet;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



import org.oa_bean.cabinet.FileCabinet;
import org.oa_bean.cabinet.FileMessage;
import org.oa_common.flie.FileTools;
import org.oa_common.xml.XmlSwitch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.cabinet.FileCabinetService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;

@Controller
@RequestMapping("/oa/fileCabinet")
public class FileCabinetController {
	
	private FileCabinetService fileCabinetService;
	
	public FileCabinetService getFileCabinetService() {
		return fileCabinetService;
	}
	@Autowired
	public void setFileCabinetService(FileCabinetService fileCabinetService) {
		this.fileCabinetService = fileCabinetService;
	}
	/**
	 * 更新文件
	 * @param request
	 * @param filejson
	 * @return
	 */
	@RequestMapping("/updateFileCabinet")
	@ResponseBody
	public Object updateFileCabinet(FileCabinet fileCabinet, String filejson,Integer fileCabinetId){
		 JsonBean  jsonbean = null;
			 try {
				if(!"".equals(filejson)&&filejson!=null){
					JSONArray filejsonarr = JSONArray.fromObject(filejson);
					 String xml ="";
					 List<FileMessage> listFile = new ArrayList<>();
					 for(int i=0;i<filejsonarr.size();i++){
						 JSONObject jsonObject = JSONObject.fromObject(filejsonarr.get(i));
						 FileMessage fileMessage =(FileMessage)JSONObject.toBean(jsonObject, FileMessage.class);
						 listFile.add(fileMessage);
					 }
					 fileCabinet.setListFile(listFile);
					 fileCabinet.setFileCount(listFile.size());
					 FileCabinet xmlfileCabinet = new FileCabinet();
					 xmlfileCabinet.setListFile(listFile);
					 xmlfileCabinet.setFileCount(listFile.size());
					 
					 
					 Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
					 classMap.put("fileCabinet", FileCabinet.class);
					 classMap.put("fileMessage", FileMessage.class);
					 xml = new XmlSwitch().ObjectToXml(classMap,xmlfileCabinet);
					 fileCabinet.setFileXml(xml);
				 }
				if(fileCabinetService.updateFileService(fileCabinet)){
					jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/population/findFamilyPage"),"","","","");
				}else{
					jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
				}
			} catch (Exception e) {
				 jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
			}
		 return jsonbean;
	}
	/**
	 * 预览文件
	 * @param request
	 * @param filejson
	 * @return
	 */
	@RequestMapping("/previewFile")
	public ModelAndView previewFile(String fileUrl){
		FileNameMap fileNameMap = URLConnection.getFileNameMap();    
	    String type = fileNameMap.getContentTypeFor(fileUrl);  
	    ModelAndView mv = new ModelAndView(getFileExt(type));
	    mv.addObject("fileUrl", fileUrl);
		return mv;
	}
	/**
	 * 删除文件
	 * @param fileName
	 * @param session
	 * @return
	 */
	@RequestMapping("/deleteFile")
	@ResponseBody
	public Object deleteFile(String objetcJson,Integer id ,HttpServletRequest request){
		 boolean falg = false;
		 HttpSession session = request.getSession();
			//物理路径
		 final String pathUrl = session.getServletContext().getRealPath("/") +"\\";
			//网络路径
		 final String netWorkUrl = "http://" + request.getServerName()+ ":" + request.getServerPort()+request.getContextPath()+"/";
		 
		 //获取需要删除的文件信息
		 JSONObject object = JSONObject.fromObject(objetcJson);
		 FileMessage fileMessage =(FileMessage)JSONObject.toBean(object, FileMessage.class);
		 int a = netWorkUrl.length();
		 int b = fileMessage.getFileWebUrl().length();
		 String filePath = fileMessage.getFileWebUrl().substring(a,b);
		 filePath = (pathUrl+filePath).replace("/", "\\");
		 
		 JsonBean  jsonbean = null;
		 FileCabinet fileCabinet= fileCabinetService.findFileCabinetByIdService(id);
		 String xml = fileCabinet.getFileXml();
		 // 查询数据库中存在的文件  ，如果是数据库中文件 则需要更新数据库
		 Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		 classMap.put("fileCabinet", FileCabinet.class);
		 classMap.put("fileMessage", FileMessage.class);
		 if(xml!=null && !"".equals(xml)){
			FileCabinet xmlfileCabinet = new FileCabinet();
			xmlfileCabinet = (FileCabinet) new XmlSwitch().XmlToObject(xml, classMap);
			fileCabinet.setListFile(xmlfileCabinet.getListFile());
			//比较数据库中文件和删除文件
			for(int i=0;i<fileCabinet.getListFile().size();i++){
				 if(fileCabinet.getListFile().get(i).getFileNewName().equals(fileMessage.getFileNewName()) ){
					 fileCabinet.getListFile().remove(i);
				 } 
			 }
			 FileCabinet newfileCabinet = new FileCabinet();
			 newfileCabinet.setListFile(fileCabinet.getListFile());
			 newfileCabinet.setFileCount(fileCabinet.getListFile().size());
			 xml = new XmlSwitch().ObjectToXml(classMap,xmlfileCabinet);
			 fileCabinet.setFileXml(xml);
			 //更新数据库信息
			fileCabinetService.updateFileService(fileCabinet);
		 } 
		 falg = FileTools.deleteFile(filePath);
		 if(falg){
			   jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","","","","");
		    }else{
		       jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		 }
		 return jsonbean;
	}
	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType
	 *            内容类型
	 * @return
	 */
	public static String getFileExt(String contentType) {
		String fileExt = "";
		if ("image/jpeg".equals(contentType))
			fileExt = "/filemanager/read_image";
		else if ("image/png".equals(contentType))
			fileExt = "/filemanager/read_image";
		else if ("audio/mpeg".equals(contentType))
			fileExt = "/filemanager/read_audio";
		else if ("audio/amr".equals(contentType))
			fileExt = "/filemanager/read_audio";
		else if ("video/mp4".equals(contentType))
			fileExt = "/filemanager/read_video";
		else if ("video/mpeg4".equals(contentType))
			fileExt = "/filemanager/read_video";
		return fileExt;
	}
}
