var url = "http://localhost:8080/ajax/";
var switching;
var map = new Map();
var rid;
var sdate;

var vue = new Vue({
	el: "#content",
	data: {
		map: [],
		room: []
	}
});

$.ajax({
	url: URL + "switching",
	success: function(data) {
		vue.map = data;
	},
	error: function() {
		alert("连接超时");
	}
});

function show(el) {
	var top = el.offset().top;
	var left = el.offset().left;
	var width = el.width();
	var height = el.height();
	$(".ab-ul").css("width", width);
	$(".ab-ul li").css("height", height);
	$(".ab-ul").css("top", top);
	$(".ab-ul").css("left", left);
	$(".ab-ul").slideDown(300);
	$(".cover").fadeIn(300);
}

$("body").on("click", ".ap .room", function() {
	switching = $(this);
	var cid = $(this).parents(".clazz").attr("cid");
	var ap = $(this).parents(".ap").attr("ap");
	sdate = $(this).parents(".date").attr("date");
	var study = $(this).hasClass("study");
	rid = $(this).attr("roomid");
	$.ajax({
		url: URL + "spare",
		data: {
			id: cid,
			date: sdate,
			study: study,
			ap: ap,
			map: JSON.stringify(map.get(sdate))
		},
		success: function(data) {
			vue.room = data;
		}
	});
	show($(this));
});

$(".cover").click(function() {
	$(".ab-ul").slideUp(300);
	$(this).fadeOut(300);
});

$(".ab-ul").on("click", ".li", function() {
	var id = $(this).attr("rid");
	$("#save").show(300);
	switching.text($(this).text());
	$(".ab-ul").slideUp(300);
	$(".cover").fadeOut(300);
	$.ajax({
		url: URL + "swRoom",
		data: {
			rid: rid,
			changeid: id,
			date: sdate
		}
	});
});
