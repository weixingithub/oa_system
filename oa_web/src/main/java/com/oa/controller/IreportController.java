package com.oa.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ireport.bean.JavaBeanPerson;
import com.ireport.util.OaReportUtils;
import com.ireport.util.OaReportUtils.DocType;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年10月25日 上午9:19:11 
 * @version 1.0 
 */
@Controller
public class IreportController {
	/**
	 * 返回iReport报表视图
	 * @param model
	 * @return
	 * @throws ServletException 
	 * @throws IOException 
	 * @throws JRException 
	 */
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public void report(HttpServletRequest request,HttpServletResponse response) throws JRException, IOException, ServletException {
		String realPath = request.getSession().getServletContext().getRealPath("/");
		realPath = realPath.replaceAll("\\\\","/");
		// 报表数据源
		Map<String,Object> params = new HashMap<String,Object>();
		new OaReportUtils(request,response).servletExportDocument(DocType.PDF,realPath+"WEB-INF/jasper/oaireport.jasper", params, JavaBeanPerson.getList(),"report");
	}
}
