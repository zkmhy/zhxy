var URL="http://localhost:8080/ajax/";

$("#iframeSec").hide();

$("#sideNav li ul").click(function(){
	$("#iframeSec").show();
	console.log($("#iframeSec iframe"));
	$("#iframeSec").css("height","10000px");
	$("#content").hide();
});