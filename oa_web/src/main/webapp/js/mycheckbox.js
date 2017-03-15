/**
 * @author wei xin
 * @version 1.0
 */
 function addCheck(obj){
	   if(obj.checked){
		   if(checklist == ""){
			   checklist =obj.value;
		   }else{
			   checklist = checklist+","+obj.value;
		   }
	   }else{
		   var arraycheck = checklist.split(",");
		   checklist="";
		   for(var i=0;i<arraycheck.length;i++){
			   if(obj.value != arraycheck[i]){
				   if(checklist == ""){
					   checklist =arraycheck[i];
				   }else{
					   checklist = checklist+","+arraycheck[i];
				   }
			   }
		   }
	   }
   }
 
 function checkAll(obj){
	 if(obj.checked){
		 checkall=true;
	 }else{
		 checkall=false;
		 checklist="";
	 }
 }
 
 function removeCheck(){
	 checkall=false;
	 checklist="";
 }
 
 /**
  * 自动化模块选择处理
  */
 function AutoChose(){
	    var autoselect = document.getElementById("autoselect");
		var inobj = document.getElementById("modelurldiv");
		var modelurl = document.getElementById("modelurl");
		var pAutoselect = document.getElementById("pAutoselectdiv");
		var developcheckdiv = document.getElementById("developcheckdiv");
		if(autoselect.value ==0){
			inobj.style.display = "";
			pAutoselect.style.display = "none";
			developcheckdiv.style.display = "";
			//$("#pAuto option[value='false']").attr("selected", "selected");
		}else{
			inobj.style.display = "none";
			modelurl.value="";
			pAutoselect.style.display = "";
			developcheckdiv.style.display = "none";
			
		} 
	}
function changeCheck(){
	var pAuto =document.getElementById("pAuto");
	var autocheckdiv=document.getElementById("autocheckdiv");
	var isAutoselectdiv = document.getElementById("isAutoselectdiv");
	if(pAuto.value == "true"){
		autocheckdiv.style.display="";
		isAutoselectdiv.style.display="none";
	}else{
		autocheckdiv.style.display="none";
		isAutoselectdiv.style.display="";
	}
}
	function autoChange(){
		var pAutoCheck =document.getElementById("pAutoCheck");
		var checkRoleIddiv = document.getElementById("checkRoleIddiv");
		var checkRoleId = document.getElementById("checkRoleId");
		var checkRoleName = document.getElementById("checkRoleName");
		 var pAutoselectdiv = document.getElementById("pAutoselectdiv");
		if(pAutoCheck.value == 1){
			checkRoleIddiv.style.display="";
			$("#checkRoleName").attr("class","required");
			pAutoselectdiv.style.display="none";
		}else{
			checkRoleId.value="";
			checkRoleName.value="";
			checkRoleIddiv.style.display="none";
			$("#checkRoleName").attr("class","");
			pAutoselectdiv.style.display="";
		}
		
	}