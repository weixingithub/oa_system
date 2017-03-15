<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


      <%
          String pId=request.getParameter("tid");
          String pName=request.getParameter("n");
          String pLevel=request.getParameter("lv");
         
          if(pId==null || "".equals(pId)){
             pId = "0";
          }
          if(pLevel == null || "".equals(pLevel)){
              pLevel = "0";
          }
           if(pName == null){
              pName = "";
          }else{
             pName = pName+".";
          }
           StringBuffer sb = new StringBuffer("[");
          for(int i=1; i<5; i++){
               String nId = pId+i;
               String nName = pName+"n"+i;
               sb.append("{id:\"");
               sb.append(nId);
               sb.append("\", name:\"").append(nName).append("\", isParent:");
               boolean flag=(Integer.parseInt(pLevel)<2 && (i%2)!=0)?true:false;
               sb.append(flag);
               sb.append(",url:\"http://localhost:8080/oa_web/oa/user/findUserConditionPage\"");
               sb.append(",rel:\""+i+"\"");
               sb.append("}");
               if(i<4){
                  sb.append(",");
               }
          }
          sb.append("]");
          out.print(sb);
       %>
  
