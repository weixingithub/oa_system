package com.ireport.bean;
import java.util.ArrayList;
import java.util.List;
  
public class JavaBeanPerson {  
    private String name;    // 姓名  
    private String sex;        // 性别  
    private int age;        // 年龄  
    private String hometown;// 籍贯  
    private String phone;    // 电话号码  
      
    public JavaBeanPerson() {}
	
	public JavaBeanPerson(String name, String sex, int age, String hometown, String phone) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.hometown = hometown;
		this.phone = phone;
	}
	
	/**
	 * 姓名
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 姓名
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 性别
	 * @return
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 性别
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 年龄
	 * @return
	 */
	public int getAge() {
		return age;
	}

	/**
	 * 年龄
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * 籍贯
	 * @return
	 */
	public String getHometown() {
		return hometown;
	}

	/**
	 * 籍贯
	 * @param hometown
	 */
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	/**
	 * 电话号码
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 电话号码
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
      
    public static List<JavaBeanPerson> getList() {  
        List<JavaBeanPerson> list = new ArrayList<JavaBeanPerson>();  
        list.add(new JavaBeanPerson("江斌", "男", 22, "四川", "13829303938"));  
        list.add(new JavaBeanPerson("刘姣姣", "女", 33, "宝鸡", "1361717345"));  
        list.add(new JavaBeanPerson("李强", "男", 44, "榆林", "13812345678"));  
        list.add(new JavaBeanPerson("潘苗苗", "女", 28, "兰州", "1867878654"));  
        list.add(new JavaBeanPerson("庞兰", "女", 26, "咸阳", "1890909876"));  
        return list;  
    }  
}  