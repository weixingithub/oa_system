package com.controller.wechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.oa_bean.wechat.ReceiveXmlEntity;
import org.oa_bean.wechat.Wechat;
import org.oa_bean.wechat.WechatToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.wechat.WechatService;
import com.dao.wechat.WechatTokenService;
import com.server.wechat.ResponseReceiveMessage;
import com.tool.ClientCommon;
import com.tool.ReceiveXmlWechat;
import com.tool.SignUtil;
@Controller
@RequestMapping("/wechat")
public class WechatController {
	private WechatService wechatService;
	private WechatTokenService tokenService;
	
	
	@Autowired
	public void setTokenService(WechatTokenService tokenService) {
		this.tokenService = tokenService;
	}
	public WechatService getWechatService() {
		return wechatService;
	}
	@Autowired
	public void setWechatService(WechatService wechatService) {
		this.wechatService = wechatService;
	}
	/**
	 * 微信平台进行的服务验证
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/wechatServlet",method={RequestMethod.GET})
	@ResponseBody
	public String getWechatServlet(HttpServletRequest request, HttpServletResponse response){
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		String token=ClientCommon.WECHAT_TOKEN;
		boolean falg = SignUtil.checkSignature(signature, timestamp, nonce, token);
		if (falg) {
			return echostr;
		}else{
			return "error";
		}
	}
	/**
	 * 微信客户端的服务请求
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/wechatServlet",method={RequestMethod.POST})
	@ResponseBody
	public String postWechatServlet(HttpServletRequest request, HttpServletResponse response){
		String result ="";
		StringBuffer sb = new StringBuffer();
		InputStream is;
		try {
			is = request.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String xml = sb.toString(); // 次即为接收到微信端发送过来的xml数据
		ReceiveXmlEntity xmlEntity = new ReceiveXmlWechat().getMsgEntity(xml,ReceiveXmlEntity.class.toString());
		Wechat wechat =  wechatService.getWechatByWeChatID(xmlEntity.getToUserName());
		if(wechat==null){
			result = ClientCommon.ErrorMessage(xmlEntity, 1);
		}else{
			//判断是否更新token
			WechatToken token = tokenService.updateTokenByTimeService(wechat.getWechatToken().getId(), wechat.getAppId(), wechat.getAppSecret());
			wechat.setWechatToken(token);
			// 调用核心业务类接收消息、处理消息
			//返回处理结果
			result =  new ResponseReceiveMessage().processRequest(xmlEntity, wechat);
		}
		// 响应消息
		return result;
	}
}
