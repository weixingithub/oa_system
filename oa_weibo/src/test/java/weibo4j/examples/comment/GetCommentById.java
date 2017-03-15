package weibo4j.examples.comment;

import weibo4j.Comments;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.CommentWapper;
import weibo4j.model.WeiboException;

public class GetCommentById {

	public static void main(String[] args) {
		String access_token ="2.00CLVpeGUpLnLEa00a672ec9wrTelB";
		String id = "4078280678609538";
		Comments cm = new Comments(access_token);
		try {
			CommentWapper comment = cm.getCommentById(id);
			
			Log.logInfo(comment.getComments().toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
