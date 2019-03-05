var calendar;

var vue = new Vue({
	el: "#content",
	data: {
		calendar: {},
		grade: [],
		events: [],
		exist:false
	},
	methods: {
		go: function(date, type) {
			cal(date, type);
		}
	}
});

$.ajax({
	url: URL + "allEvent",
	success: function(data) {
		vue.events = data;
	}
});

function cal(str, type) {
	$.ajax({
		url: URL + "calendar",
		data: {
			str: str,
			type: type
		},
		success: function(data) {
			vue.calendar = data;
			calendar = $("#calendar");
		},
		error: function() {
			alert("连接超时");
		}
	});
}

cal(null, 1);

$(".auto").click(function() {
	$.ajax({
		url:URL+"exist/auto",
		success:function(e){
			vue.exist=e;
			if(!e){
				auto();
			}else{
				hint("课表已生成，请推送或者取消");
			}
		}
	});
});

function auto() {
	$.ajax({
		url: URL + "auto",
		data: {
			type: vue.calendar.type
		},
		success: function(data) {
			vue.exist=true;
			vue.calendar = data;
			calendar = $("#calendar");
		},
		error: function() {
			alert("连接超时");
		}
	});
}

$("#content").on("click",".cancelAuto",function(){
	$.ajax({
		url: URL + "cancelAdv",
		data: {
			type: vue.calendar.type,
			str: vue.calendar.dateStr
		},
		success: function(data) {
			vue.calendar = data;
			vue.exist=false;
			calendar = $("#calendar");
		},
		error: function() {
			alert("连接超时");
		}
	});	
});

function hint(txt) {
	$("#hint").text(txt);
	$("#hint").show();
	$("#hint").fadeOut(1200);
}

$.ajax({
	url:URL+"exist/auto",
	success:function(e){
		vue.exist=e;
	}
});
