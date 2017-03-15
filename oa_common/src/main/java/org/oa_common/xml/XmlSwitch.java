package org.oa_common.xml;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 * 转换xml
 * @author 江斌
 *
 */
public class XmlSwitch {
   /** 
	 * 扩展xstream，使其支持CDATA块 
	 *  
	 * @date 2013-05-19 
	 */  
	private static  XStream xstream = new XStream(new DomDriver()); 
	 
	/**
	 * 实体对象转换xml
	 * @param object
	 * @param title
	 * @return
	 */
	public static String ObjectToXml(Object object ,String title) {  
	    xstream.alias(title, object.getClass()); 
	    return xstream.toXML(object);  
	}
	/**
	 * map 转换xml
	 * @param map
	 * @return
	 */
	public static String getXmlStr(Map<String, Object> map) {
		StringBuffer str=new StringBuffer();
		Set<String> set=map.keySet();
		Iterator<String> iterator=set.iterator();
		str.append("<xml>");
		while(iterator.hasNext()){
			String key=iterator.next();
			str.append("<"+key+">");
			str.append("<![CDATA["+map.get(key)+"]]>");
			str.append("</"+key+">");
		}
		str.append("</xml>");
		return str.toString();
	}
	/**
	 * 
	 * @param object
	 * @param title
	 * @param classMap
	 * @return
	 */
	public static String MapToXml(Object object,String title,Map<String, Class<?>> classMap) { 
		xstream.alias(title, object.getClass()); 
		for (Map.Entry<String, Class<?>> entry : classMap.entrySet()) { 
			xstream.alias(entry.getKey(), entry.getValue()); 
		}
	    return xstream.toXML(object);  
	}
	/**
	 * xml 转object
	 * @param xml
	 * @param classMap
	 * @return
	 */
	public static Object XmlToObject(String xml,Map<String, Class<?>> classMap) {  
		for (Map.Entry<String, Class<?>> entry : classMap.entrySet()) { 
			xstream.alias(entry.getKey(), entry.getValue()); 
		}
	    return xstream.fromXML(xml);  
	}
	/**
	 * xml 转object
	 * @param xml
	 * @param classMap
	 * @return
	 */
	public static String ObjectToXml(Map<String, Class<?>> classMap,Object object) {  
		for (Map.Entry<String, Class<?>> entry : classMap.entrySet()) { 
			xstream.alias(entry.getKey(), entry.getValue()); 
		}
	    return xstream.toXML(object);  
	}
    /**
     * json 转换xml
     * @param json
     * @return
     */
    public static String json2XML(String json){
        JSONObject jobj = JSONObject.fromObject(json);
        String xml =  new XMLSerializer().write(jobj);
        return xml;
    }
}
