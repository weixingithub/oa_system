package org.oa_common.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * 解析xml
 * @author 江斌
 *
 */
public class XmlParser {
	 /**
     * 
     * 方法说明 根据输入流 获得map键值对
     * 
     * @param in 要解析的输入流
     * @return
     * @throws Exception
     */
    public static Map<String,Object> parseXml(InputStream in) throws Exception{
        //将解析结果存入HashMap中
        Map<String,Object> map=new HashMap<String,Object>();
        //读取输入流
        SAXReader reader=new SAXReader();
        Document document=reader.read(in);
        //得到xml根元素
        Element root=document.getRootElement();
        //得到所有子节点
        List<Element> elementList=root.elements();
        //便利所有子节点
        for(Element e:elementList){
            map.put(e.getName(), e.getText());
            System.out.println(e.getName()+":"+e.getText());
        }
        //释放资源
        in.close();
        in=null;
        return map;
    }
    /** 
     * 将xml字符串转换为JSON对象 
     * @param xmlFile xml字符串 
     * @return JSON对象 
     */  
    public JSON getJSONFromXml(String xmlString) {  
        XMLSerializer xmlSerializer = new XMLSerializer();  
        JSON json = xmlSerializer.read(xmlString);  
        return json;  
    }
}
