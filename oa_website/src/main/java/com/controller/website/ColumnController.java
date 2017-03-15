package com.controller.website;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.oa_bean.website.ColumnObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.website.ColumnService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;
/**
 * 单个模块配置
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/oa/column")
public class ColumnController {
	private ColumnService columnService;
	@Autowired
	public void setColumnService(ColumnService columnService) {
		this.columnService = columnService;
	}
	/**
	 * 添加或编辑绑定的插件
	 * @return
	 */
	@RequestMapping(value = "/addAndUpdateColumn")
	@ResponseBody
	public Object addAndUpdateColumn(Integer modelId,String columnJson){
		JsonBean  jsonbean = null;
		JSONArray columnjsonarr = JSONArray.fromObject(columnJson);
		List<ColumnObj> listColumn = new ArrayList<ColumnObj>();
		for (int i=0;i<columnjsonarr.size();i++) {
			 ColumnObj column =(ColumnObj)JSONObject.toBean((JSONObject) columnjsonarr.get(i), ColumnObj.class);
			 listColumn.add(column);
		}
		if(columnService.addAndUpdateColumnObjService(listColumn, modelId)){
			jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","","closeCurrent","","");
		}else{
			 jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		 return jsonbean;
	}
	
}
