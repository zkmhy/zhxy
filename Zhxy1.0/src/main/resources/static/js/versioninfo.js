var model;
var vid;

var vue = new Vue({
	el: "#content",
	data: {
		majors: [],
		list: [],
		grades: [],
		currs: [],
		v: null
	},
	methods: {
		up: function() {
			$(".versioninfo").slideUp(400);
			$("#version").slideDown(400);
			model = "";
			$(".adddiv").hide();
			$(".info :checkbox").hide();
			$(".deletediv").hide();
		},
		del: function() {
			if (model == '删除模式') {
				$(".info :checkbox").hide();
				$(".deletediv").hide();
				$(".info :checkbox").attr("checked",false);
				$(".btn-all").text("全选");				
			} else {
				$(".deletediv").show();
				$(".info :checkbox").show();
				$(".adddiv").stop().fadeOut(400);
			}
			into("删除模式");
		}
	}
});

function query(i) {
	$.ajax({
		url: URL + "queryMajor",
		success: function(e) {
			vue.majors = e;
		}
	});
	$.ajax({
		url: URL + "version",
		data: {
			id: i
		},
		success: function(e) {
			vue.v = e;
		}
	});
}

function into(txt) {
	var state;
	if (txt == model) {
		state = "退出";
		model = "";
	} else {
		state = "进入";
		model = txt;
	}
	$(".hint").text(state + txt);
	$(".hint").stop().show();
	$(".hint").stop().fadeOut(800);
}

$.ajax({
	url: URL + "restversion",
	success: function(e) {
		vue.list = e;
	}
});

$("#version").on("click", ".update", function() {
	var id = $(this).attr("vid");
	vid = id;
	query(id);
	$("#version").slideUp(400);
	$(".versioninfo").slideDown(400);
});

$("#content").on("click", ".info :checkbox", function() {
	var check = this.checked;
	$(this).parent().nextAll().find(":checkbox").attr("checked", check);
	var lv = $(this).attr("grade");
	if (lv > 0) {
		var el = this;
		for (; lv > 0; lv--) {
			var list = $(el).parent().parent().parent().find("[grade=" + lv + "]");
			check = true;
			list.each(function() {
				if (!this.checked) {
					check = false;
					return;
				}
			});
			var p = $(el).parent().parent().parent().children()[0];
			$(p).find(":checkbox").attr("checked", check);
			el = $(p).find(":checkbox")[0];
		}
	}
	var checked = $(":checked");
	lists = [];
	checked.each(function() {
		if ($(this).hasClass("section")) {
			lists.push(this);
		}
	});
});

$("#content").on("change", "#major", function() {
	var val = this.value;
	if (val != "all") {
		$.ajax({
			url: URL + "queryGrade",
			data: {
				mid: val
			},
			success: function(e) {
				vue.grades = e;
				$("#grade").val("all");
			}
		});
	} else {
		vue.grades = [];
	}
});

$("#content").on("change", "select", function() {
	sections();
});

function sections() {
	var mid = $("#major").val() != "all" ? $("#major").val() : null;
	var gid = $("#grade").val() != "all" ? $("#grade").val() : null;
	if (!mid) {
		gid = null;
	}
	$.ajax({
		url: URL + "restCurr",
		data: {
			vid: vid,
			mid: mid,
			gid: gid
		},
		success: function(e) {
			vue.currs = e;
		}
	});
	$(".table :checkbox").attr("checked",false);
	$(".hide-it").hide();
}

$("#content").on("change", ".rest .toggle :checkbox", function() {
	var rest = $(this).parents(".rest");
	if (this.checked) {
		rest.find(".hide-it").slideDown(400);
	} else {
		rest.find(".hide-it").slideUp(400);
	}
	var check = true;
	$(".rest .toggle :checkbox").each(function() {
		if (!this.checked) {
			check = false;
			return;
		}
	});
	$("#all").attr("checked", check);
});

$("#content").on("change", ".all-check", function() {
	var rest = $(this).parents(".rest");
	rest.find(".section-box").attr("checked", this.checked);
	if (this.checked) {
		rest.find(":checkbox").attr("checked", this.checked);
		rest.find(".hide-it").slideDown(400);
	}
	var check = true;
	$(".all-check").each(function() {
		if (!this.checked) {
			check = false;
			return;
		}
	});
	$("#all-check").attr("checked", check);
});

$("#content").on("change", ".section-box", function() {
	var check = true;
	$(this).parents(".hide-it").find(".section-box").each(function() {
		if (!this.checked) {
			check = false;
			return;
		}
	});
	$(this).parents(".rest").find(".all-check").attr("checked", check);
});

$("#content").on("change", "#all", function() {
	if (this.checked) {
		$(".rest .hide-it").slideDown(600);
	} else {
		$(".rest .hide-it").slideUp(600);
	}
	$(".toggle :checkbox").attr("checked", this.checked);
});

$("#content").on("click", "#plus", function() {
	if (model == "增加模式") {
		$(".adddiv").stop().fadeOut(400);
	} else {
		sections();
		$(".info :checkbox").hide();
		$(".deletediv").hide();
		$(".adddiv").stop().fadeIn(400);
	}
	into("增加模式");
});

$("#content").on("change", "#all-check", function() {
	$(".all-check").attr("checked", this.checked);
	$(".section-box").attr("checked",this.checked);
});


$("#content").on("click", "#section-add", function() {
	var exist = false;
	var list = "vid=" + vid + "&";
	$(".section-box").each(function() {
		if (this.checked) {
			list += "list=" + this.value + "&";
		}
	});
	$(".section-box").each(function() {
		if (this.checked) {
			exist = true;
			return;
		}
	});
	if (exist) {
		$.ajax({
			url: URL + "updateVersion",
			data: list,
			success: function() {
				$.ajax({
					url: URL + "version",
					data: {
						id: vid
					},
					success: function(e) {
						vue.v = e;
						sections();
					}
				});
			}
		});
	}
	$(".adddiv").stop().fadeOut();
	model = "";
});

$("#content").on("click",".btn-all",function(){
	if($(this).text()=="全选"){
		$(".info :checkbox").attr("checked",true);
		$(this).text("取消全选");
	}else{
		$(".info :checkbox").attr("checked",false);
		$(this).text("全选");
	}
});

$("#content").on("click",".btn-danger",function(){
/* 	model="";
	$(".info :checkbox").hide();
	$(".deletediv").hide();
 */	var list="vid="+vid+"&";
	var curr="vid="+vid+"&";
	$(".curr").each(function(){
		if(this.checked){
			curr+="list="+this.value+"&";
		}
	});
	$(".section").each(function(){
		if(this.checked){
			list+="list="+this.value+"&";
		}		
	});
	$.ajax({
		url:URL+"verdeletesec",
		type:"post",
		data:list,
		success:function(){
			$.ajax({
				url:URL+"verdeletecurr",
				data:curr,
				success:function(){
					query(vid);
					sections();
				}
			});
		}
	});
	$(".info :checkbox").attr("checked",false);
	$(".btn-all").text("全选");
});