var calendar;
var url = "http://localhost:8080/ajax/";

var vue = new Vue({
	el: "#content",
	data: {
		calendar: {},
		grade:[],
		events:[]
	},
	methods: {
		go: function(date, type) {
			cal(date, type);
		}
	}
});

$.ajax({
	url:url+"allEvent",
	success:function(data){
		vue.events=data;
	}
});

function cal(str, type) {
	$.ajax({
		url: url + "calendar",
		data: {
			str: str,
			type: type
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

cal(null, 1);

$(".auto").click(function() {
	auto();
});

function auto() {
	$.ajax({
		url: url + "auto",
		data: {
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

$(".cancelAuto").click(function() {
	$.ajax({
		url: url + "cancelAdv",
		data: {
			type: vue.calendar.type,
			str: vue.calendar.dateStr
		},
		success: function(data) {
			vue.calendar = JSON.parse(data);
			calendar = $("#calendar");
			$(".existAuto").hide();
		},
		error: function() {
			alert("连接超时");
		}
	});
});