$(function(){
	$.post("userJsonAction.action",null,function(data){
		alert(data);
	});
});