package org.oa_bean.wechat;
/**
 * 接收到的微信xml实体类
 * @author pamchen-1
 *
 */
public class ReceiveXmlEntity {
	  /** 开发者微信号**/
	  private String ToUserName="";
	  /**发送方帐号（一个OpenID）**/
	  private String FromUserName="";
	  /** 消息创建时间 （整型）**/
	  private String CreateTime="";
	  /** 消息类型（
	   * text 【文本】，
	   * image【图片】，
	   * voice【语音】，
	   * video【小视频】，
	   * location【地理位置】，
	   * link【网络链接】
	   * ）
	   * 事件类型（
	   * event，【消息类型】
	   * ）
	   * **/
	  private String MsgType="";
	  /** 消息id，64位整型**/
	  private String MsgId="";
	  /**事件类型{
	   * subscribe(订阅)【扫描带参数二维码事件】，
	   * unsubscribe(取消订阅)【扫描带参数二维码事件】，
	   * 事件类型，subscribe
	   * 事件类型，SCAN
	   * 事件类型，LOCATION
	   * 事件类型，CLICK
	   * 事件类型，VIEW
	   * 
	   * } **/
	  private String Event="";
	  /**事件KEY值，qrscene_为前缀，后面为二维码的参数值 **/
	  private String EventKey="";
	  /**二维码的ticket，可用来换取二维码图片**/
	  private String Ticket="";
	  /**上报地理位置事件（地理位置纬度） **/
	  private String Latitude=""; 
	  /**上报地理位置事件（地理位置经度） **/
	  private String Longitude="";
	  /** **/
	  private String Precision="";
	  /** 图片链接（由系统生成）**/
	  private String PicUrl="";
	  /** 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。**/
	  private String MediaId="";
	  /** 消息标题**/
	  private String Title="";
	  /** 消息描述**/
	  private String Description="";
	  /** 消息链接**/
	  private String Url="";
	  /** 地理位置维度**/
	  private String Location_X="";
	  /** 地理位置经度**/
	  private String Location_Y="";
	  /** 地图缩放大小**/
	  private String Scale="";
	  /** 地理位置信息**/
	  private String Label="";
	  /** 文本消息内容**/
	  private String Content="";
	  /** 语音格式，如amr，speex等**/
	  private String Format="";
	  /** 语音识别结果，UTF8编码**/
	  private String Recognition="";
	  private String MenuId="";
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getPrecision() {
		return Precision;
	}
	public void setPrecision(String precision) {
		Precision = precision;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String locationX) {
		Location_X = locationX;
	}
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String locationY) {
		Location_Y = locationY;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	public String getRecognition() {
		return Recognition;
	}
	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
	public String getMenuId() {
		return MenuId;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	@Override
	public String toString() {
		return "ReceiveXmlEntity [Content=" + Content + ", CreateTime="
				+ CreateTime + ", Description=" + Description + ", Event="
				+ Event + ", EventKey=" + EventKey + ", Format=" + Format
				+ ", FromUserName=" + FromUserName + ", Label=" + Label
				+ ", Latitude=" + Latitude + ", Location_X=" + Location_X
				+ ", Location_Y=" + Location_Y + ", Longitude=" + Longitude
				+ ", MediaId=" + MediaId + ", MenuId=" + MenuId + ", MsgId="
				+ MsgId + ", MsgType=" + MsgType + ", PicUrl=" + PicUrl
				+ ", Precision=" + Precision + ", Recognition=" + Recognition
				+ ", Scale=" + Scale + ", Ticket=" + Ticket + ", Title="
				+ Title + ", ToUserName=" + ToUserName + ", Url=" + Url
				+ ", getContent()=" + getContent() + ", getCreateTime()="
				+ getCreateTime() + ", getDescription()=" + getDescription()
				+ ", getEvent()=" + getEvent() + ", getEventKey()="
				+ getEventKey() + ", getFormat()=" + getFormat()
				+ ", getFromUserName()=" + getFromUserName() + ", getLabel()="
				+ getLabel() + ", getLatitude()=" + getLatitude()
				+ ", getLocation_X()=" + getLocation_X() + ", getLocation_Y()="
				+ getLocation_Y() + ", getLongitude()=" + getLongitude()
				+ ", getMediaId()=" + getMediaId() + ", getMenuId()="
				+ getMenuId() + ", getMsgId()=" + getMsgId()
				+ ", getMsgType()=" + getMsgType() + ", getPicUrl()="
				+ getPicUrl() + ", getPrecision()=" + getPrecision()
				+ ", getRecognition()=" + getRecognition() + ", getScale()="
				+ getScale() + ", getTicket()=" + getTicket() + ", getTitle()="
				+ getTitle() + ", getToUserName()=" + getToUserName()
				+ ", getUrl()=" + getUrl() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}