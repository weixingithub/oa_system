package org.oa_bean.message.receive.wechat;

import java.util.List;

public class Menu
{
    private List<Button> button;

    public void setButton(List<Button> button){
        this.button = button;
    }
    public List<Button> getButton(){
        return this.button;
    }
	@Override
	public String toString() {
		return "Menu [button=" + button + "]";
	}
    
}