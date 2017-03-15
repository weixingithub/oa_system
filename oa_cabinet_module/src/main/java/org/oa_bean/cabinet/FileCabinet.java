package org.oa_bean.cabinet;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * @author  江斌
 * @description 文件管理实体类
 * @date 创建时间：2016年8月3日 上午10:37:13 
 * @version 1.0 
 */
@Entity
@Table(name="t_file")
public class FileCabinet {
	/** 文件管理**/
	private Integer id;
	private String fileLabel;//标签
	private String fileComment;//备注
	private String fileXml;//存储文件信息的xml
	private List<FileMessage> listFile ;
	private int fileCount; //文件个数
	@Id
    @GeneratedValue
    @Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="file_label")
	public String getFileLabel() {
		return fileLabel;
	}
	public void setFileLabel(String fileLabel) {
		this.fileLabel = fileLabel;
	}
	@Column(name="file_comment")
	public String getFileComment() {
		return fileComment;
	}
	public void setFileComment(String fileComment) {
		this.fileComment = fileComment;
	}
	@Column(name="file_xml",columnDefinition="TEXT")
	public String getFileXml() {
		return fileXml;
	}
	public void setFileXml(String fileXml) {
		this.fileXml = fileXml;
	}
	@Column(name="file_count")
	public int getFileCount() {
		return fileCount;
	}
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	@Transient
	public List<FileMessage> getListFile() {
		return listFile;
	}
	public void setListFile(List<FileMessage> listFile) {
		this.listFile = listFile;
	}
	public FileCabinet(Integer id, String fileLabel, List<FileMessage> listFile,int fileCount) {  
        super();  
        this.id = id;  
        this.fileLabel = fileLabel;  
        this.listFile = listFile; 
        this.fileCount = fileCount;  
    }  
	  
    public FileCabinet() {  
        super();  
    }
	@Override
	public String toString() {
		return "FileCabinet [id=" + id + ", fileLabel=" + fileLabel
				+ ", fileComment=" + fileComment + ", fileXml=" + fileXml
				+ ", listFile=" + listFile + ", fileCount=" + fileCount + "]";
	}  
    
}
