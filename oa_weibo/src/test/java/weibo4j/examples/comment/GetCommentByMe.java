package weibo4j.examples.comment;

import weibo4j.Comments;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.CommentWapper;
import weibo4j.model.WeiboException;

public class GetCommentByMe {

	public static void main(String[] args) {
		String access_token ="2.00CLVpeGUpLnLEa00a672ec9wrTelB";
		Comments cm = new Comments(access_token);
		try {
			CommentWapper comment = cm.getCommentByMe();
			Log.logInfo(comment.getComments().toString());
			//Log.logInfo(comment.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}
}
