package com.controller.weibo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.weibo.WeiboService;

/**
 * 上传长微博信息
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/oa/wiboArticle")
public class WeiboArticleController {
	private WeiboService weiboService;
	@Autowired
	public void setWeiboService(WeiboService weiboService) {
		this.weiboService = weiboService;
	}
}
