package org.oa_bean.cabinet;


public class FileMessage {
	private String fileOldName; //文件原名称
	private String fileNewName; //文件新名称
	private	String fileWebUrl; //保存文件WEB路径
	private long fileSize;//文件大小
	private long fileType;//文件类型信息
	
	public String getFileOldName() {
		return fileOldName;
	}
	public void setFileOldName(String fileOldName) {
		this.fileOldName = fileOldName;
	}
	public String getFileNewName() {
		return fileNewName;
	}
	public void setFileNewName(String fileNewName) {
		this.fileNewName = fileNewName;
	}
	 
	public String getFileWebUrl() {
		return fileWebUrl;
	}
	public void setFileWebUrl(String fileWebUrl) {
		this.fileWebUrl = fileWebUrl;
	}
	 
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public long getFileType() {
		return fileType;
	}
	public void setFileType(long fileType) {
		this.fileType = fileType;
	}
	
}
