package org.oa_bean;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

public class Page<T> {
	  
      public static final String ASC="asc";
      public static final String DESC="desc";
      
      
      private  int pageNo =1;

	  private  int pageSize=-1;
      private String orderBy = null;
      private String order = null;
      private boolean autoCount = true;
      
      
      private List<T> result = Lists.newArrayList();
      private int totalCount = -1;
      
      public Page(){
    	  
      }
      
      public Page(int pageSize){
    	  this.pageSize = pageSize;
      }
      /**
       * 获取当前页的序号
       * @return
       */
      public int getPageNo() {
  		return pageNo;
  	 }
      /**
       * 设置当前页的序号
       * @param pageNo
       * @return
       */
  	  public void setPageNo(int pageNo) {
  		this.pageNo = pageNo;
  		if(pageNo<1){
  			this.pageNo = 1;
  		}
  	 }
  	  /**
  	   * 用于连续设置
  	   * @param thePageNo
  	   * @return
  	   */
  	 public Page<T> pageNo(int thePageNo){
  		 setPageNo(thePageNo);
  		 return this;
  	 }
  	 
  	/**
      * 获得每页的记录数量, 默认为-1.
      */
     public int getPageSize() {
         return pageSize;
     }

     /**
      * 设置每页的记录数量.
      */
     public void setPageSize(int pageSize) {
         this.pageSize = pageSize;
     }

     /**
      * 返回Page对象自身的setPageSize函数,可用于连续设置。
      */
     public Page<T> pageSize(int thePageSize) {
         setPageSize(thePageSize);
         return this;
     }

     /**
      * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
      */
     public int getFirst() {
         return ((pageNo - 1) * pageSize);
     }

     /**
      * 获得排序字段,无默认值. 多个排序字段时用','分隔.
      */
     public String getOrderBy() {
         return orderBy;
     }

     /**
      * 设置排序字段,多个排序字段时用','分隔.
      */
     public void setOrderBy(String orderBy) {
         this.orderBy = orderBy;
     }

     /**
      * 返回Page对象自身的setOrderBy函数,可用于连续设置。
      */
     public Page<T> orderBy(String theOrderBy) {
         setOrderBy(theOrderBy);
         return this;
     }

     /**
      * 获得排序方向, 无默认值.
      */
     public String getOrder() {
         return order;
     }

     /**
      * 设置排序方式向.
      *
      * @param order 可选值为desc或asc,多个排序字段时用','分隔.
      */
     public void setOrder(String order) {
         String lowcaseOrder = StringUtils.lowerCase(order);

         // 检查order字符串的合法值
         String[] orders = StringUtils.split(lowcaseOrder, ',');
         for (String orderStr : orders) {
             if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr)) {
                 throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
             }
         }

         this.order = lowcaseOrder;
     }

     /**
      * 返回Page对象自身的setOrder函数,可用于连续设置。
      */
     public Page<T> order(String theOrder) {
         setOrder(theOrder);
         return this;
     }

     /**
      * 是否已设置排序字段,无默认值.
      */
     public boolean isOrderBySetted() {
         return (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(order));
     }

     /**
      * 获得查询对象时是否先自动执行count查询获取总记录数, 默认为false.
      */
     public boolean isAutoCount() {
         return autoCount;
     }

     /**
      * 设置查询对象时是否自动先执行count查询获取总记录数.
      */
     public void setAutoCount(boolean autoCount) {
         this.autoCount = autoCount;
     }

     /**
      * 返回Page对象自身的setAutoCount函数,可用于连续设置。
      */
     public Page<T> autoCount(boolean theAutoCount) {
         setAutoCount(theAutoCount);
         return this;
     }

     // -- 访问查询结果函数 --//

     /**
      * 获得页内的记录列表.
      */
     public List<T> getResult() {
         return result;
     }

     /**
      * 设置页内的记录列表.
      */
     public void setResult(List<T> lists) {
         this.result = lists;
     }

     /**
      * 获得总记录数, 默认值为-1.
      */
     public long getTotalCount() {
         return totalCount;
     }

     /**
      * 设置总记录数.
      */
     public void setTotalCount(int totalCount) {
         this.totalCount = totalCount;
     }

     /**
      * 根据pageSize与totalCount计算总页数, 默认值为-1.
      */
     public long getTotalPages() {
         if (totalCount < 0) {
             return -1;
         }

         long count = totalCount / pageSize;
         if (totalCount % pageSize > 0) {
             count++;
         }
         return count;
     }

     /**
      * 是否还有下一页.
      */
     public boolean isHasNext() {
         return (pageNo + 1 <= getTotalPages());
     }

     /**
      * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
      */
     public int getNextPage() {
         if (isHasNext()) {
             return pageNo + 1;
         } else {
             return pageNo;
         }
     }

     /**
      * 是否还有上一页.
      */
     public boolean isHasPre() {
         return (pageNo - 1 >= 1);
     }

     /**
      * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
      */
     public int getPrePage() {
         if (isHasPre()) {
             return pageNo - 1;
         } else {
             return pageNo;
         }
     }
  	 
  	 
}
