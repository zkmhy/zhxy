$("body").on("click", ".selector", function() {
	var list = $(this).parent().find("div:visible");
	var i = list.length;
	if (i == 0) {
		$(this).siblings("div").slideDown(300);
	} else {
		$(this).siblings("div").slideUp(300);
	}
});

$(".check_event .time input").click(function() {
	var val = this.value;
	if (val != 0) {
		$(this).siblings("div").slideDown(200);
	} else {
		$(this).siblings("div").slideUp(200);
	}
});

$(".check_event .en-erase").click(function() {
	$(this).parents(".hidediv").hide();
	$(":checkbox").prop("checked",false);
	$(".check-grade div").slideUp();
});

$(".eventCancel").click(function() {
	$(this).parents(".hidediv").hide();
	$(":checkbox").prop("checked",false);
	$(".check-grade div").slideUp();
});

$("#content").on("change",":checkbox",function(){
	$(this).siblings("div").slideDown(300);
	$(this).siblings("div").children().slideDown(300);
	var check=this.checked;
	var val=this.getAttribute("fid");
	$(this).siblings("div").find(":checkbox").prop("checked",check);	
	var sib=$(this).parent().siblings("div").find(":checkbox");
	$.each(sib,function(){
		if(!this.checked){
			check=false;
		}
	});
	var prev=$(this).parent().prevAll(":checkbox");
	prev.prop("checked",check);
	if($(this).hasClass("clazz")){
		var grade=$(".grade");
		$.each(grade,function(){
			if(!this.checked){
				check=false;
			}
		});
		$(".allCheckbox").prop("checked",check);
	}
});

$(".affirm").click(function(){
	var list=[];
	var clazz=$(".clazz:checked");
	$.each(clazz,function(){
		list.push(this.value);
	});
	var e={
		id:eventid,
		idList:list,
		roomid:roomId,
		eventStr:eventStr,
		time:$(".radio_time:checked").val(),
		study:$(".isStudy").is(":checked")
	};
	$.ajax({
		url:url+"addEvent",
		method:"post",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(e),
		success:function(){
			$(".hidediv").hide(400);
			$.ajax({
				url: url + "calendar",
				data: {
					str: vue.calendar.dateStr,
					type: vue.calendar.type
				},
				success: function(data) {
					vue.calendar = JSON.parse(data);
					calendar = $("#calendar");
				},
				error: function() {
					alert("连接超时");
				}
			});			
		}
	});
});