package org.oa_common.echart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonUtil;

/**
 * 柱形图
 * @author 江斌
 *
 */
public class EchartBar {
	/**
	 * 柱形图 
	 * @param json
	 * @return
	 * 数据格式 [{name:xxx,data:[{orgName:xxx,value:0},{orgName:xxx,value:0},{orgName:xxx,value:0},{orgName:xxx,value:0}]},
	 * 		   {name:xxx,data:[{orgName:xxx,value:0},{orgName:xxx,value:0},{orgName:xxx,value:0},{orgName:xxx,value:0}]}]
	 */
	public static String getEchertByBar(String json){
		String optionJson = null;
		Option option = new Option();
        option.tooltip().trigger(Trigger.axis);
        option.toolbox().show(true).feature(Tool.mark,
                Tool.dataView,
                new MagicType( Magic.bar, Magic.stack, Magic.tiled),
                Tool.restore,
                Tool.saveAsImage).padding(20);
        option.calculable(true);
        option.yAxis(new ValueAxis());
        try {
			JSONArray jsonArry = JSONArray.fromObject(json);
			List<String> nameList = new ArrayList<String>();
			List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();  
			for(int i=0;i<jsonArry.size();i++){
				JSONObject jsonObject = (JSONObject)jsonArry.get(i);
				String name = jsonObject.getString("name");
				JSONArray jsonArry1 =  (JSONArray)jsonObject.get("data");
				for(int j=0;j<jsonArry1.size();j++){
					JSONObject jsonObject1 = (JSONObject)jsonArry.get(j);
					String orgName = jsonObject1.getString("orgName");
					Integer value = jsonObject1.getInt("jsonObject1");
				}
				
				nameList.add(name);
			}
			optionJson =  GsonUtil.format(option);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return optionJson;
	}
	/**
	 * 柱形图 
	 * @param option
	 * @param object
	 * @return
	 */
	public static Option getEchertByBar(){
		Option option = new Option();
        option.tooltip().trigger(Trigger.axis);
        option.toolbox().show(true).feature(Tool.mark,
                Tool.dataView,
                new MagicType( Magic.bar, Magic.stack, Magic.tiled),
                Tool.restore,
                Tool.saveAsImage).padding(20);
        option.calculable(true);
        option.yAxis(new ValueAxis());
		return option;
	}
}
