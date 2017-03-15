package org.oa_bean.message.receive.wechat;
public class Root
{
    private Menu menu;

    public void setMenu(Menu menu){
        this.menu = menu;
    }
    public Menu getMenu(){
        return this.menu;
    }
	@Override
	public String toString() {
		return "Root [menu=" + menu + "]";
	}
    
}