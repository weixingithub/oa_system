/**
 * oa平台中的自定义js
 */
//家庭成员
function familydata(){
	var trobjs=document.getElementById("familytable").getElementsByTagName("tbody")[0].getElementsByTagName("tr");
	var array=[];
    for(var i=0;i<trobjs.length;i++)
    {
    	var tdobjs = trobjs[i].getElementsByTagName("td");
    	var inputobj = tdobjs[0].getElementsByTagName("input");
    	var selobj = tdobjs[1].getElementsByTagName("select");
    	var inputa = tdobjs[2].getElementsByTagName("input");
    	var inputb = tdobjs[3].getElementsByTagName("input");
    	var person = new Object();
    	person.personId = inputobj[0].value;
    	person.personName= inputobj[1].value;
    	person.ralation = selobj[0].value;
    	person.personIdnumber = inputa[0].value;
    	person.personContact = inputb[0].value;
    	array.push(person);
    }
    if(array.length>0){
    	document.getElementById("familymemberjson").value=JSON.stringify(array);
    }else{
    	document.getElementById("familymemberjson").value="";
    }
}
//附件
function filedata(){
	var trobjs=document.getElementById("filetable").getElementsByTagName("tbody")[0].getElementsByTagName("tr");
	var array=[];
    for(var i=0;i<trobjs.length;i++)
    {
    	
    	var tdobjs = trobjs[i].getElementsByTagName("td");
    	var inputobj = tdobjs[0].getElementsByTagName("input");
    	var selobj = tdobjs[1].getElementsByTagName("select");
    	var inputa = tdobjs[2].getElementsByTagName("input");
		var inputb = tdobjs[3].getElementsByTagName("input");
		var fileMessage = new Object();
		fileMessage.fileNewName = inputobj[0].value;
		fileMessage.fileOldName = inputobj[1].value;
		fileMessage.fileType = selobj[0].value;
		fileMessage.fileSize = inputa[0].value;
		fileMessage.fileWebUrl = inputb[0].value;
		array.push(fileMessage);
    }
    if(array.length>0){
    	document.getElementById("filejson").value=JSON.stringify(array);
    }else{
    	document.getElementById("filejson").value="";
    }
}
function ajaxTodoTask(obj,event){
	  var $this=$(obj);
	  var title=$this.attr("title")||$this.text();
	  var tabid=$this.attr("rel")||"_blank";
	  var fresh=eval($this.attr("fresh")||"true");
	  var external=eval($this.attr("external")||"false");
	  var url=unescape($this.attr("href")).replaceTmById($(event.target).parents(".unitBox:first"));
	  DWZ.debug(url);
	  if(!url.isFinishedTm()){
	  alertMsg.error($this.attr("warn")||DWZ.msg("alertSelectMsg"));
	  return false;}
	  navTab.openTab(tabid,url,{title:title,fresh:fresh,external:external});
	  event.preventDefault();
   }
