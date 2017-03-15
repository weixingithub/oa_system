package org.oa_bean.message.receive.wechat;

import java.util.List;

public class Sub_button
{
    private String name;

    private List<String> sub_button;

    private String type;

    private String url;
    
    
    private String key;  

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setSub_button(List<String> sub_button){
        this.sub_button = sub_button;
    }
    public List<String> getSub_button(){
        return this.sub_button;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public String toString() {
		return "Sub_button [name=" + name + ", sub_button=" + sub_button
				+ ", type=" + type + ", url=" + url + ", key=" + key + "]";
	}
    
}