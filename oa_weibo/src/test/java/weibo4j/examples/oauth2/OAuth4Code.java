package weibo4j.examples.oauth2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import weibo4j.Oauth;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

public class OAuth4Code {
	public static void main(String [] args) throws WeiboException, IOException{
		Oauth oauth = new Oauth();
		String client_ID ="3327712025";
		String client_SERCRET ="7b51b0e7512aece56276fe152f157f90";
		String redirect_URI = "http://155v52p428.iask.in/weibo/weiboPermit";
		BareBonesBrowserLaunch.openURL(oauth.authorize("code", client_ID, redirect_URI));
		System.out.println(oauth.authorize("code", client_ID, redirect_URI));
		System.out.print("Hit enter when it's done.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine();
		Log.logInfo("code: " + code);
		try{
			System.out.println(oauth.getAccessTokenByCode(code, client_ID, client_SERCRET, redirect_URI));
		} catch (WeiboException e) {
			if(401 == e.getStatusCode()){
				Log.logInfo("Unable to get the access token.");
			}else{
				e.printStackTrace();
			}
		}
	}
}
