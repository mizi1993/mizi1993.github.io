$(function(){
	$("#message").text("hello");
	$("#username").bind("blur",function(){
		checkUser($(this).val());
	});
});
function checkUser(username){
	var parameter="{user.username:"+username+"}";
	$.post("userCheck.action",parameter,function(data){
		$("#message").text(data);
		$("#message").css("color","red");
	});
}