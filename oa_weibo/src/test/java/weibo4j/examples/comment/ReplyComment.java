package weibo4j.examples.comment;

import weibo4j.Comments;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Comment;
import weibo4j.model.WeiboException;

public class ReplyComment {

	public static void main(String[] args) {
		String access_token ="2.00CLVpeGUpLnLEa00a672ec9wrTelB";
		String cid = "4078314869971144";
		String id = "4078280678609538";
		String comments = "一点也不好";
		Comments cm = new Comments(access_token);
		try {
			Comment com = cm.replyComment(cid, id, comments);
			Log.logInfo(com.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
