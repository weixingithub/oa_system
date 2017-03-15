package weibo4j.examples.timeline;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import weibo4j.Timeline;
import weibo4j.http.ImageItem;
import weibo4j.model.Status;

public class Upload {
	public static void main(String args[]) {
		try {
			try {
				byte[] content = readFileImage("D:\\EWorkspace\\oa_system\\oa_web\\src\\main\\webapp\\uploadfolder\\862b17cbdd86be141d3e0a2ef0b59ff3.jpg");
				System.out.println("content length:" + content.length);
				ImageItem pic = new ImageItem("pic", content);
				String s = java.net.URLEncoder.encode("测试图片", "utf-8");
				String access_token = "2.00LMCPIBbujMdD74c3c43efcsUZN7D";
				Timeline tm = new Timeline(access_token);
				Status status = tm.uploadStatus(s, pic);

				System.out.println("Successfully upload the status to ["
						+ status.getText() + "].");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} catch (Exception ioe) {
			System.out.println("Failed to read the system input.");
		}
	}

	public static byte[] readFileImage(String filename) throws IOException {
		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				new FileInputStream(filename));
		int len = bufferedInputStream.available();
		byte[] bytes = new byte[len];
		int r = bufferedInputStream.read(bytes);
		if (len != r) {
			bytes = null;
			throw new IOException("读取文件不正确");
		}
		bufferedInputStream.close();
		return bytes;
	}
}
