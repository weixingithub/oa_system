package org.oa_bean.message.receive.wechat;

import java.util.List;

public class Button
{
    private String name;

    private List<Sub_button> sub_button;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setSub_button(List<Sub_button> sub_button){
        this.sub_button = sub_button;
    }
    public List<Sub_button> getSub_button(){
        return this.sub_button;
    }
	@Override
	public String toString() {
		return "Button [name=" + name + ", sub_button=" + sub_button + "]";
	}
    
}