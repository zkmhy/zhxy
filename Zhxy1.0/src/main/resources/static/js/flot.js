var findid;
var selectstr;
var td;
var eventid;
var roomId;
var eventStr;

$("body").on("mousedown", ".draggable", function() {
	var $div = $(this);
	var top = $div.offset().top;
	var left = $div.offset().left;
	document.onmousemove = function() {
		var x = event.pageX;
		var y = event.pageY;
		if ($div.hasClass("event"))
			$div.css("width", "100px");
		var tempTop = y - top - ($div.height() / 3);
		var tempLeft = x - left - ($div.width() / 3);
		$div.css("position", "relative");
		$div.css("z-index", "999");
		$div.css("top", tempTop);
		$div.css("left", tempLeft);
	}
});

$("body").on("mouseup", ".draggable", function() {
	document.onmousemove = false;
	var $div = $(this);
	var check = $(this).hasClass("event");
	var top = $div.offset().top;
	var left = $div.offset().left;
	var fTop = calendar.offset().top;
	var fLeft = calendar.offset().left;
	var height = calendar.height();
	var width = calendar.width();
	this.style.top = 0;
	this.style.left = 0;
	this.style.width = "100%";
	if (top < fTop || top > (fTop + height) || left < fLeft || left > (fLeft + width)) {
		return;
	}
	var tds = calendar.find("td");
	$.each(tds, function() {
		tdTop = $(this).offset().top;
		tdLeft = $(this).offset().left;
		tdHeight = $(this).height();
		tdWidth = $(this).width();
		if (top >= tdTop && top <= (tdTop + tdHeight) && left >= tdLeft && left <= (tdLeft + tdWidth)) {
			td = $(this);
			return false;
		}
	});
	eventStr=td.children().attr("date");
	if(eventStr>vue.calendar.max){
		$(".hidediv").show();
		$.ajax({
			url: URL + "grade",
			data:{
				date:eventStr
			},
			success: function(data) {
				vue.grade = data;
			}
		});
		eventid=$(this).attr("eventid");
		roomId=$(this).attr("rid");		
	}
});

$("body").on("click", ".find", function() {
	if (selectstr == null) {
		findid = $(this).attr("roomid");
		var selector = findid == null ? "teacherid" : "roomid";
		findid = findid == null ? $(this).attr("teacherid") : findid;
		selectstr = ".ap[" + selector + "!=" + findid + "]";
		$(selectstr).hide(560);
	} else {
		var id = $(this).attr("roomid");
		id = id == null ? $(this).attr("teacherid") : id;
		if (id == findid) {
			$(selectstr).show(560);
			selectstr = null;
		}
	}
});
