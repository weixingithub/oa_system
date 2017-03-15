package com.tool.util;

import java.util.List;

import org.oa_bean.OaModel;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2017年2月15日 上午10:49:57 
 * @version 1.0 
 */
public class AuthorityCommonUtil {
	 public static void initModelNodeId(List<OaModel> models){
         for(int i=0;i<models.size();i++){
        	 OACommon.modelMap.put(models.get(i).getModelUrl(), models.get(i).getNodeId());
         }
   }
}
