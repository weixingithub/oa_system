package com.controller.cabinet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.oa_common.date.DateTools;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tool.util.OACommon;

@Controller
@RequestMapping("/upload")
public class UploadController {
	 /**
    * 文件上传
    * @param picHref
    * @param request
    * @return
    * @throws Exception
    * author weixin
    */
   @RequestMapping("/uploadfile")
   @ResponseBody
   public void uploadFile(HttpServletResponse response,HttpServletRequest request,@RequestParam(value="file", required=false) MultipartFile file) throws IOException{
	    OACommon oac= new OACommon();
	    String sep = System.getProperty("file.separator");  
	    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        byte[] bytes = file.getBytes();  
        System.out.println(file.getOriginalFilename()); 
        String uploadDir = request.getSession().getServletContext().getRealPath("/")+oac.getString("filepath","/config.properties")+sep+sf.format(new Date());
        File dirPath = new File(uploadDir);  
        if (!dirPath.exists()) {  
            dirPath.mkdirs();  
        }  
        File uploadedFile = new File(uploadDir + sep + new OACommon().rename(file.getOriginalFilename()));  
        FileCopyUtils.copy(bytes, uploadedFile);  
    }
   	/**
   	 * 上传附件管理
   	 * @param request
   	 * @param session
   	 * @param file
   	 * @param fileCabinetId
   	 * @param fileID
   	 * @return
   	 * @throws IOException
   	 */
	@RequestMapping(value = "/singleUploadFile",method={RequestMethod.POST})
	@ResponseBody
	public Object  singleUploadFile(HttpServletRequest request,HttpSession session,@RequestParam("file") MultipartFile file,Integer fileCabinetId,String fileID) throws IOException{
		OACommon oa = new OACommon();
		String dayString = DateTools.TimeString("yyyyMMdd");
		// 将图片按日期分开存放，方便管理
		final String path_segment = oa.getString("filepath","/config.properties")+"/"+fileID+"/"+dayString+"/";
		
		// 存放到web根目录下,如果日期目录不存在，则创建,
		// 注意 request.getRealPath("/") 已经标记为不推荐使用了.
		// 文件的物理路径
		final String filePathUrl = session.getServletContext().getRealPath("/") +"/"+ path_segment+"/";
		File dir = new File(filePathUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 以下是真正的上传部分
		// 取得原文件名
		String fileOldName = file.getOriginalFilename();
		long fileSize = file.getSize();
		// 按时间戳生成文件名
		String fileNewName = oa.rename(fileOldName);
		//获取文件类型
		String fileWebUrl = "http://" + request.getServerName() //服务器地址  
                + ":"   
                + request.getServerPort()       //端口号  
                + request.getContextPath()      //项目名称  
                +"/"+ path_segment+"/"+fileNewName;
		byte[] bytes = file.getBytes();  
		try {
			//IOUtils.copy(file.getInputStream(), new FileOutputStream(new File(dir, fileNewName)));
			 File uploadedFile = new File(filePathUrl + fileNewName);  
		     FileCopyUtils.copy(bytes, uploadedFile); 
		} catch (Exception e) {
			e.getMessage();
		}
		return "{\"fileNewName\":\"" + fileNewName + "\",\"fileWebUrl\":\"" + fileWebUrl + "\",\"fileSize\":\"" + fileSize + "\"}";
	}
	/**
	 * 上传图片
	 * @param request
	 * @param session
	 * @param file
	 * @param fileID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/singleUploadFilePicture",method={RequestMethod.POST})
	@ResponseBody
	public Object  singleUploadFilePicture(HttpServletRequest request,HttpSession session,@RequestParam("file") MultipartFile file,String fileID) throws IOException{
		OACommon oa = new OACommon();
		String dayString = DateTools.TimeString("yyyyMMdd");
		// 将图片按日期分开存放，方便管理
		final String path_segment = oa.getString("filepath",
				"/config.properties") + "/" + fileID + "/" + dayString + "/";

		// 存放到web根目录下,如果日期目录不存在，则创建,
		// 注意 request.getRealPath("/") 已经标记为不推荐使用了.
		// 文件的物理路径
		final String filePathUrl = session.getServletContext().getRealPath("/")
				+ "/" + path_segment + "/";
		File dir = new File(filePathUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 以下是真正的上传部分
		// 取得原文件名
		String fileOldName = file.getOriginalFilename();
		// 按时间戳生成文件名
		String fileNewName = oa.rename(fileOldName);
		// 获取文件类型
		// 以下是真正的上传部分
		String error = "0";
		byte[] bytes = file.getBytes();
		try {
			// IOUtils.copy(file.getInputStream(), new FileOutputStream(new
			// File(dir, fileNewName)));
			File uploadedFile = new File(filePathUrl + fileNewName);
			FileCopyUtils.copy(bytes, uploadedFile);
		} catch (Exception e) {
			e.getMessage();
			error = "-1";// 文件上传失败
		}
		String url = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath() // 项目名称
				+ "/" + path_segment + "/" + fileNewName;
		return "{\"err\":\"" + error + "\",\"msg\":\"" + url + "\"}";
	}
	/**
	 * xhEditor 图片上传后台显示
	 * @param request
	 * @param session
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/eidtorUploadFile",method={RequestMethod.POST})
	@ResponseBody
	public String  eidtorUploadFile(HttpServletRequest request,HttpSession session,@RequestParam("filedata") MultipartFile file) throws IOException{
		
		OACommon oa = new OACommon();
		String dayString = DateTools.TimeString("yyyyMMdd");
		// 将图片按日期分开存放，方便管理
		final String path_segment = oa.getString("filepath","/config.properties")+"/"+dayString;
		
		// 存放到web根目录下,如果日期目录不存在，则创建,
		// 注意 request.getRealPath("/") 已经标记为不推荐使用了.
		final String path = session.getServletContext().getRealPath("/") +"/"+ path_segment;
		
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 以下是真正的上传部分
		String error = "";
		// 取得原文件名
		String originName = file.getOriginalFilename();
		// 按时间戳生成文件名
		String fileName = oa.rename(originName);
		String flag= request.getParameter("flag");
		long size = file.getSize();
		if(flag.equals("img")){
			long pSize = 1024*1024*10;//设置文件限制大小
			if(size>pSize){
				error = "0";//文件过大
			}
		}
		if(error!="0"){
			try {
				IOUtils.copy(file.getInputStream(), new FileOutputStream(new File(dir, fileName)));
				IOUtils.closeQuietly(file.getInputStream());
			} catch (Exception e) {
				error = "1";//文件上传失败
				e.getMessage();
			}
		}
		// 将图片路径按xheditor要求的json格式字符串返回
		String url = "http://" + request.getServerName() //服务器地址  
                + ":"   
                + request.getServerPort()       //端口号  
                + request.getContextPath()      //项目名称  
                +"/"+ path_segment + "/" + fileName;	
		return "{\"err\":\"" + error + "\",\"msg\":\"" + url + "\"}";
	}
}
