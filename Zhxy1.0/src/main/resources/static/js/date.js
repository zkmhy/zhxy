var calendar;

var vue = new Vue({
	el: "#content",
	data: {
		calendar: {},
		grade: [],
		events: []
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
	auto();
});

function auto() {
	$.ajax({
		url: URL + "auto",
		data: {
			type: vue.calendar.type
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

$(".cancelAuto").click(function() {
	$.ajax({
		url: URL + "cancelAdv",
		data: {
			type: vue.calendar.type,
			str: vue.calendar.dateStr
		},
		success: function(data) {
			vue.calendar = data;
			calendar = $("#calendar");
			$(".existAuto").hide();
		},
		error: function() {
			alert("连接超时");
		}
	});
});
