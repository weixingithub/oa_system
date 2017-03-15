package com.oa.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.oa_bean.OaOrg;
import org.oa_bean.OaRole;
import org.oa_bean.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.SysUserService;
import com.tool.util.OACommon;

@Controller
@RequestMapping("/login")
public class LoginController {
	
    private SysUserService sysUserService;
    
    
    public SysUserService getSysUserService() {
		return sysUserService;
	}
    @Autowired
	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}
	/**
     * 登陆验证
     * @param request
     * @param user
     * @return
     * author weixin
     */
	@SuppressWarnings("unused")
	@RequestMapping(value ="/validation", method = RequestMethod.POST)
	@ResponseBody
	public  Object valLogin(HttpServletRequest request,SysUser sysUser){
		HttpSession session = request.getSession();
		Map<String,String> mapInfo = new HashMap<String,String>();
//		if(!sysUser.getVerifycode().equals(session.getAttribute("verifycode"))){
//			mapInfo.put("result", OACommon.CODE_ERROR);
//			return mapInfo;
//		}
		Map<String,Object> map = sysUserService.valSysUser(sysUser);
		if("true".equals(map.get("result"))){
			SysUser user = (SysUser)map.get("userInfo");
			session.setAttribute(OACommon.LOGIN_USER,user);
			if(!OACommon.SYSCREATOR.equals(user.getNodeId())){//判断是否为creator,非创建者分配权限
				List<OaOrg> oaOrgs = user.getOaOrg();
				List<Integer> oaOrglist = new ArrayList<Integer>();
				if(null != oaOrgs && oaOrgs.size()>0){
					for(OaOrg oaOrg : oaOrgs){
						oaOrglist.add(oaOrg.getOrgId());
					}
				}
				session.setAttribute(OACommon.GROUPID, oaOrglist);
				
				//处理删除机构时orgIds字段的更新
				String orgIds = user.getOrgIds();
				if(!"".equals(orgIds) && oaOrglist.size()<orgIds.split(",").length){
					StringBuffer borgids  = new StringBuffer();
					if(oaOrglist.size()>0){
						for(int i=0;i<oaOrglist.size();i++){
							borgids.append(oaOrglist.get(i));
							borgids.append(",");
						}
						borgids.deleteCharAt(borgids.length()-1);
						user.setOrgIds(borgids.toString());
					}else{
						user.setOrgIds("");
					}
					sysUserService.saveOrUpdateSysUserService(user,"uporgid");
				}
				
				List<Integer> oaRolelist = new ArrayList<Integer>();
				List<OaRole> oaRoles = user.getOaRole();
				if(null != oaRoles && oaRoles.size()>0){
					
					for(OaRole oaRole : oaRoles){
						oaRolelist.add(oaRole.getRoleId());
					}
				}
				
				session.setAttribute(OACommon.ROLEID, oaRolelist);
			}
		}
		mapInfo.put("result", (String)map.get("result"));
		return mapInfo;
	}
	/**
	 * session过期跳转登陆页面
	 * @param request
	 * @param errormessage
	 * @return
	 * author weixin
	 */
	@RequestMapping("/logout")
	public ModelAndView backLogin(HttpServletRequest request,String errormessage){
		ModelAndView mv = new ModelAndView("login");
		if("nobody".equals(errormessage)){
			mv.addObject("errormessage", OACommon.NOBODY);
		}else if("error".equals(errormessage)){
			mv.addObject("errormessage", OACommon.LOGIN_ERROR);
		}else if("codeerror".equals(errormessage)){
			mv.addObject("errormessage", OACommon.CODE_ERROR);
		}else{
			mv.addObject("errormessage", OACommon.SYS_ERROR);
		}
		return mv;
	}
	@RequestMapping("/noauthority")
	public ModelAndView noAuthority(){
		return new ModelAndView("noauthority");
	}
	/**
	 * 注销登陆
	 * @param request
	 * @return
	 * author weixin
	 */
	@RequestMapping("/cancel")
	public String logout(HttpServletRequest request){
		request.getSession().setAttribute(OACommon.LOGIN_USER, null);
		request.getSession().setAttribute(OACommon.GROUPID, null);
		request.getSession().invalidate();
		return "login";
	}
	/**
	 * 生成验证码
	 * @param req
	 * @param resp
	 * author weixin
	 */
	@RequestMapping("/makeverifyCode")
	public void createVerifyVode(HttpServletRequest req, HttpServletResponse resp){
		 // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(OACommon.width, OACommon.height,
                BufferedImage.TYPE_INT_RGB);
//      Graphics2D gd = buffImg.createGraphics();
        //Graphics2D gd = (Graphics2D) buffImg.getGraphics();
        Graphics gd = buffImg.getGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, OACommon.width, OACommon.height);
 
        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.BOLD, OACommon.fontHeight);
        // 设置字体。
        gd.setFont(font);
 
        // 画边框。
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, OACommon.width - 1, OACommon.height - 1);
 
        // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
        gd.setColor(Color.BLACK);
        for (int i = 0; i < 4; i++) {
            int x = random.nextInt(OACommon.width);
            int y = random.nextInt(OACommon.height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }
 
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;
 
        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < OACommon.codeCount; i++) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf(OACommon.codeSequence[random.nextInt(10)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
 
            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i + 1) * OACommon.xx, OACommon.codeY);
 
            // 将产生的四个随机数组合在一起。
            randomCode.append(code);
        }
        // 将四位数字的验证码保存到Session中。
        HttpSession session = req.getSession();
        session.setAttribute("verifycode", randomCode.toString());
 
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
 
        resp.setContentType("image/jpeg");
 
        // 将图像输出到Servlet输出流中。
        try{
        	 ServletOutputStream sos = resp.getOutputStream();
             ImageIO.write(buffImg, "jpeg", sos);
             sos.close();
        }catch(Exception e){
        	System.out.println("验证码生成失败");
        }
       
	} 
}