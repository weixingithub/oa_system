package com.oa.interceptor;

import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.oa_bean.AuthorityMsg;
import org.oa_bean.SysUser;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tool.util.OACommon;

public class BaseInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@SuppressWarnings("unused")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		SysUser user = (SysUser)session.getAttribute(OACommon.LOGIN_USER);
		if(null == user){
			String url = request.getRequestURI();
			if(url.contains("/oa/sysuser/main")){
				String weburl = request.getContextPath();
				response.sendRedirect(weburl+"/login.jsp");
			}else{
				String weburl = request.getContextPath();
				response.sendRedirect(weburl+"/demo/common/ajaxTimeout.jsp");
			}
			return false;
		}else{
				if(!OACommon.SYSCREATOR.equals(user.getNodeId())){
					String modelurl = request.getRequestURI();
					Map<String,HashSet<String>> aumap = (Map<String, HashSet<String>>)session.getAttribute(OACommon.ROLEMODEL);
					if(aumap != null){
						HashSet<String> auset = aumap.get(modelurl);
						//判断是否有访问权限，并记录权限标识
						if(auset != null){
							AuthorityMsg am = new AuthorityMsg();
							if(!auset.contains(OACommon.VISITE)){
								response.sendRedirect("/login/noauthority");
								return false;
							}else{
								am.setVisit(true);
								am.setAdd(auset.contains(OACommon.ADD));
								am.setEditor(auset.contains(OACommon.EDITOR));
								am.setDelete(auset.contains(OACommon.DELETE));
								am.setDetail(auset.contains(OACommon.DETAIL));
								am.setConfigure(auset.contains(OACommon.CONFIGRE));
								String datatype=null;
								if(auset.contains(OACommon.VISITE_GROUP)){
									datatype = OACommon.VISITE_GROUP;
								}else if(auset.contains(OACommon.VISITE_USER)){
									datatype = OACommon.VISITE_USER;
								}else{
									datatype = OACommon.VISITE_SYS;
								}
								request.setAttribute("datatype", datatype);
							}
							session.setAttribute(OACommon.MODELFLAG, am);
						}
					}
				}else{
					AuthorityMsg am = new AuthorityMsg();
					am.setVisit(true);
					am.setAdd(true);
					am.setEditor(true);
					am.setDelete(true);
					am.setDetail(true);
					am.setConfigure(true);
					session.setAttribute(OACommon.MODELFLAG, am);
				}
			return true;
		}
	}

}
