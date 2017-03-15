<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
        String basepath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2 class="contentTitle">uploadify多文件上传</h2>

<style type="text/css" media="screen">
.my-uploadify-button {
	background:none;
	border: none;
	text-shadow: none;
	border-radius:0;
}

.uploadify:hover .my-uploadify-button {
	background:none;
	border: none;
}

.fileQueue {
	width: 400px;
	height: 150px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
</style>
<script>
    videojs.options.flash.swf = "<%=basepath %>/video/video-js.swf";
  </script>
<div class="pageContent" style="margin: 0 10px" layoutH="50">
<video id="example_video_1" class="video-js vjs-default-skin" controls preload="none" width="640" height="264"
      poster="http://video-js.zencoder.com/oceans-clip.png"
      data-setup="{}">
    <source src="http://localhost/uploadfolder/lsp.mp4" type='video/mp4' />
    <source src="http://localhost/uploadfolder/lsp.webm" type='video/webm' />
    <source src="http://localhost/uploadfolder/lsp.ogv" type='video/ogg' />
    <track kind="captions" src="demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
    <track kind="subtitles" src="demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
  </video>
	<input id="testFileInput"  type="file" name="image" 
		uploaderOption="{
			swf:'<%=basepath %>/uploadify/scripts/uploadify.swf',
			uploader:'<%=basepath %>/upload/uploadfile',
			formData:{PHPSESSID:'xxx', ajax:1},
			buttonText:'请选择文件',
			fileSizeLimit:'36000KB',
			fileTypeDesc:'*.*;',
			fileTypeExts:'*.*;',
			auto:true,
			fileObjName:'file',
			multi:true,
			onUploadSuccess:uploadifySuccess,
			onQueueComplete:uploadifyQueueComplete
		}"
	/>

	<div class="divider"></div>

	<input id="testFileInput2" type="file" name="image2" 
		uploaderOption="{
			swf:'<%=basepath %>/uploadify/scripts/uploadify.swf',
			uploader:'<%=basepath %>/upload/uploadfile',
			formData:{PHPSESSID:'xxx', ajax:1},
			queueID:'fileQueue',
			fileObjName:'file',
			buttonImage:'<%=basepath %>/uploadify/img/add.jpg',
			buttonClass:'my-uploadify-button',
			width:102,
			auto:false
		}"
	/>
	
	<div id="fileQueue" class="fileQueue"></div>
	
	<input type="image" src="<%=basepath %>/uploadify/img/upload.jpg" onclick="$('#testFileInput2').uploadify('upload', '*');"/>
	<input type="image" src="<%=basepath %>/uploadify/img/cancel.jpg" onclick="$('#testFileInput2').uploadify('cancel', '*');"/>

  
	<div class="divider"></div>
	<p style="margin:10px"><a href="<%=basepath %>/chart/test/barchart.html" target="navTab">图表测试</a></p>
	
	<li><a class="icon" href="javascript:$.printBox('w_list_print')"><span>打印</span></a></li>
	<div id="w_list_print">
    //这里是你需要打印的部分
</div>
<textarea cols="160" rows="10">
uploaderOption: http://www.uploadify.com/documentation/
	auto            : true,               // Automatically upload files when added to the queue
	buttonClass     : '',                 // A class name to add to the browse button DOM object
	buttonCursor    : 'hand',             // The cursor to use with the browse button
	buttonImage     : null,               // (String or null) The path to an image to use for the Flash browse button if not using CSS to style the button
	buttonText      : 'SELECT FILES',     // The text to use for the browse button
	checkExisting   : false,              // The path to a server-side script that checks for existing files on the server
	debug           : false,              // Turn on swfUpload debugging mode
	fileObjName     : 'Filedata',         // The name of the file object to use in your server-side script
	fileSizeLimit   : 0,                  // The maximum size of an uploadable file in KB (Accepts units B KB MB GB if string, 0 for no limit)
	fileTypeDesc    : 'All Files',        // The description for file types in the browse dialog
	fileTypeExts    : '*.*',              // Allowed extensions in the browse dialog (server-side validation should also be used)
	height          : 30,                 // The height of the browse button
	itemTemplate    : false,              // The template for the file item in the queue
	method          : 'post',             // The method to use when sending files to the server-side upload script
	multi           : true,               // Allow multiple file selection in the browse dialog
	formData        : {},                 // An object with additional data to send to the server-side upload script with every file upload
	preventCaching  : true,               // Adds a random value to the Flash URL to prevent caching of it (conflicts with existing parameters)
	progressData    : 'percentage',       // ('percentage' or 'speed') Data to show in the queue item during a file upload
	queueID         : false,              // The ID of the DOM object to use as a file queue (without the #)
	queueSizeLimit  : 999,                // The maximum number of files that can be in the queue at one time
	removeCompleted : true,               // Remove queue items from the queue when they are done uploading
	removeTimeout   : 3,                  // The delay in seconds before removing a queue item if removeCompleted is set to true
	requeueErrors   : false,              // Keep errored files in the queue and keep trying to upload them
	successTimeout  : 30,                 // The number of seconds to wait for Flash to detect the server's response after the file has finished uploading
	uploadLimit     : 0,                  // The maximum number of files you can upload
	width           : 120,                // The width of the browse button
</textarea>
</div>
</body>
</html>