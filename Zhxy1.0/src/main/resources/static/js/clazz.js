var id;
var val;
var cid;

var vue = new Vue({
	el: "#content",
	data: {
		notice: {},
		dynamic: {},
		next: [],
		pre: [],
		clazzInfo: {},
		clazz: null,
		plans: [],
		nHide:false
	}
});

queryClazz(1);

function queryClazz(i){
	$.ajax({
		url: URL + "page/allClazz",
		data:{
			page:i,
			size:7
		},success: function(e) {
			vue.clazzInfo = e;
		}
	});	
}

function queryNotice(i) {
	$.ajax({
		url: URL + "page/mynotice",
		data: {
			id:cid,
			page: i
		},
		success: function(e) {
			vue.notice = e;
		}
	});
}

$("#content").on("click", "#notice .next", function() {
	val = $(this).attr("value");
	$(".show-it").show();
	$(".notice-next").hide();
	$(".notice-pre").hide();
	id = setInterval(function() {
		if ($(".notice-next").length > 0) {
			clearInterval(id);
			$(".notice-next").show(400);
			$(".show-it").slideUp(400);
			setTimeout(function() {
				queryNotice(val);				
			}, 400);
		}
	}, 100);
	$.ajax({
		url: URL + "page/mynotice",
		data: {
			id:cid,
			page: val
		},
		success: function(e) {
			vue.next = e.list;
		}
	});
});

$("#content").on("click", "#notice .prev", function() {
	val = $(this).attr("value");
	$(".show-it").show();
	$(".notice-next").hide();
	$(".notice-pre").hide();
 	id = setInterval(function() {
		if ($(".notice-pre").length > 0) {
			clearInterval(id);
			$(".notice-pre").show(400);
			$(".show-it").slideUp(400);
			vue.nHide=true;
			setTimeout(function() {
				queryNotice(val);
			}, 400);
		}
	}, 100);
	$.ajax({
		url: URL + "page/mynotice",
		data: {
			id:cid,
			page: val
		},
		success: function(e) {
			vue.pre = e.list;
		}
	});
 });

$("#content").on("click","#clazz-div .page-btn",function(){
	var page=$(this).attr("value");
	$("#clazz-div table").fadeOut(300);
	$("#clazz-div table").fadeIn(300);
	setTimeout(function(){
		queryClazz(page);
	},300);
});

$("#content").on("click", ".info", function() {
	cid = $(this).attr("value");
	$.ajax({
		url: URL + "clazzInfo",
		data: {
			id: cid
		},
		success: function(e) {
			vue.clazz = e;
			$.ajax({
				url: URL + "clazzPlan",
				data: {
					id: cid
				},
				success: function(e) {
					queryNotice(1);
					$("#clazz-div").hide();
					$("#clazz-info").show();
					$("#clazz-info").addClass("cool");
					vue.plans = e;
				}
			});

		}
	});
});

var tid=setInterval(function(){
	if($("#huanqiu_div0")){
		$("#huanqiu_div0").remove();
		clearInterval(tid);
	}
},1000);

$("#content").on("click",".im-close",function(){
	$("#clazz-info").removeClass("cool");
	$("#clazz-info").addClass("low");
	setTimeout(function(){
		$("#clazz-div").show();		
	},2000);
});
