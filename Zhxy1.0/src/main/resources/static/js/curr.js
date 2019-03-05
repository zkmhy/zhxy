var cid;

var vue = new Vue({
	el: "#content",
	data: {
		lists: [],
		curr: null,
		majors: [],
		grades: [],
		teachers:[]
	}
});

$.ajax({
	url: URL + "queryMajor",
	success: function(e) {
		vue.majors = e;
	}
});

function currinfo(id){
	$.ajax({
		url: URL + "currinfo",
		data: {
			id: id
		},
		success: function(e) {
			vue.curr = e;
		}
	});
	$.ajax({
		url:URL+"teachers",
		data:{
			cid:id
		},success:function(e){
			vue.teachers=e;
		}
	});
}

$("#all").click(function() {
	$(this).parents("table").find(":checkbox").attr("checked", this.checked);
});

$("#content").on("click", ".info", function() {
	cid = $(this).attr("cid");
	$.ajax({
		url:URL+"allgrade",
		success:function(e){
			vue.grades=e;
		}
	});
	currinfo(cid);
	$("#curr-info").stop().slideDown(400);
	$("#curr-content").stop().slideUp(400);
});

$("#close").click(function() {
	$("#curr-info").stop().slideUp(400);
	$("#curr-content").stop().slideDown(400);
	delHide();
	editHide();
	plusHide();
	queryCurrs();
});

$("#content").on("click","#new-btn .cancel",function(){
	plusHide();
});

$("#content").on("click","#edit-btn .cancel",function(){
	editHide();
	var val=vue.curr;
	vue.curr=null;
	vue.curr=val;	
});

$("#section-add .btn-danger").click(function() {
	$("#section-add").stop().fadeOut(800);
	$("#curr-info").stop().fadeIn(400);
});

$("#content").on("mouseover", ".section", function() {
	var i = $(".section").length;
	if (i > 1)
		$(this).find(".section-icons").show();
});
$("#content").on("mouseout", ".section", function() {
	$(this).find(".section-icons").hide();
});
$("#content").on("click", ".section-close", function() {
	$(this).parents(".section").remove();;
});

$("#content").on("click","#new-btn .btn-success",function() {
	var list = [];
	$(".new").each(function() {
		if($(this).find(":text").val()){
			var name = $(this).find(":text").val();
			var hour = $(this).find("[type=number]").val();
			var section = {
				name: name,
				hour: hour,
				cid: cid
			};
			list.push(section);			
		}
	});	
 	$.ajax({
		url: URL + "addSection",
		type: "post",
		contentType: "application/json;charset=utf-8",
		data: JSON.stringify(list),
		success: function() {
			currinfo(cid);
		}
	});
	$(".new").remove();
	plusHide();
});

$("#remove-section").click(function() {
	var lists = [];
	$("#curr-info .sec :checked").each(function() {
		var obj = {
			id: this.value
		};
		lists.push(obj);
	});
	$(".curr-lists :checkbox").attr("checked",false);
	var curr = {
		id: cid,
		lists: lists
	}
	$.ajax({
		url: URL + "hasSec",
		type: "post",
		contentType: "application/json;charset=utf-8",
		data: JSON.stringify(lists),
		success: function(e) {
			if (e) {
				hint("gray", "先取消版本中的关联，才能删除");
			} else {
				$.ajax({
					url: URL + "deleteSec",
					type: "post",
					contentType: "application/json;charset=utf-8",
					data: JSON.stringify(curr),
					success: function() {
						currinfo(cid);
					}
				});
			}
		}
	});
});

$("#content").on("change", "#removeAll", function() {
	$(this).parent().nextAll().find(":checkbox").attr("checked", this.checked);
});

$("#remove-curr").click(function() {
	$.ajax({
		url: URL + "hasCurr",
		data: {
			list: cid
		},
		success: function(e) {
			if (e) {
				hint("gray", "先取消版本中的关联，才能删除");
			} else {
				$.ajax({
					url:URL+"deleteCurr",
					data:{
						list:cid
					},success:function(){
						$("#curr-info").hide();
						$("#curr-content").stop().slideDown(400);
						delHide();
						queryCurrs();						
					}
				});
			}
		}
	});
});

$("#major").change(function() {
	var val = this.value;
	if (val == "all") {
		vue.grades = [];
	} else {
		$.ajax({
			url: URL + "queryGrade",
			data: {
				mid: val
			},
			success: function(e) {
				vue.grades = e;
			}
		});
	}
});

$("#curr-header select").change(function() {
	queryCurrs();
});

function queryCurrs() {
	var mid = $("#major").val() == "all" ? null : $("#major").val();
	var gid = $("#grade").val() == "all" ? null : $("#grade").val();
	if (!mid) {
		gid = null;
	}
	$.ajax({
		url: URL + "currs",
		data: {
			mid: mid,
			gid: gid
		},
		success: function(e) {
			vue.lists = e;
		}
	});
}

$("#content").on("change", ".sec :checkbox", function() {
	var check = true;
	$(".sec :checkbox").each(function() {
		if (!this.checked) {
			check = false;
			return;
		}
	});
	$("#removeAll").attr("checked", check);
});

$("#content").on("change", ".tr :checkbox", function() {
	var check = true;
	$(".tr :checkbox").each(function() {
		if (!this.checked) {
			check = false;
			return;
		}
	});
	$("#all").attr("checked", check);
});

$("#remove-it").click(function() {
	if ($("#delete").css("display") != "none") {
		$("#delete").stop().hide();
		$("#curr-table :checkbox").stop().hide();
		$("#curr-table :checkbox").attr("checked",false);
	} else {
		$("#delete").stop().show(100);
		$("#curr-table :checkbox").stop().show(100);
	}
});

$("#delete").click(function() {
	var list = "";
	$("#curr-table .curr:checked").each(function() {
		list += "list=" + this.value + "&";
	});
	$.ajax({
		url: URL + "hasCurr",
		data: list,
		success: function(e) {
			if (e) {
				hint("gray", "先取消版本中的关联，才能删除");
			} else {
				$("#delete").stop().hide();
				$("#curr-table :checkbox").stop().hide();
				$("#curr-table :checkbox").attr("checked",false);
				$.ajax({
					url: URL + "deleteCurr",
					data: list,
					success: function(e) {
						hint("gray", "已删除");
						queryCurrs();
					}
				});
			}
		}
	});
});

function hint(color, txt) {
	$("#hint").css("background-color", color);
	$("#hint").text(txt);
	$("#hint").show();
	$("#hint").fadeOut(800);
}

$("#plus-curr").click(function(){	
	$("#curr-content").stop().slideUp(400);
	$("#curr-add").stop().slideDown(400);
	$.ajax({
		url:URL+"allgrade",
		success:function(e){
			vue.grades=e;
		}
	});
	$.ajax({
		url:URL+"teachers",
		data:{
			cid:0
		},success:function(e){
			vue.teachers=e;
		}
	});
});

$(".add-close").click(function(){
	close();
});

function close(){
	$("#curr-content").stop().slideDown(400);
	$("#curr-add").stop().slideUp(400);
	vue.grades=[];	
	queryCurrs();
}

$("#add-plus").click(function(){
	var el=($("#curr-add .sec").clone())[0];
	$(el).find("input").val(1);
	$(el).find(":text").val("");
	$("#curr-lists").append(el);
});

$("#content").on("mouseover", "#curr-lists .sec", function() {
	var i = $("#curr-lists .sec").length;
	if (i > 1)
		$(this).find(".btn").show();
});

$("#content").on("mouseout", "#curr-lists .sec", function() {
	$(this).find(".btn").hide();
});

$("#content").on("mouseover", "#new .sec", function() {
	var i = $("#new .sec").length;
	if (i > 1)
		$(this).find(".btn").show();
});

$("#content").on("mouseout", "#new .sec", function() {
	$(this).find(".btn").hide();
});

$("#content").on("click",".remove",function(){
	$(this).parent().remove();
});

$("#content").on("click","#new .btn",function(){
	$(this).parent().remove();
});

$("#curr-add .btn-success").click(function(){
	var trim = false;
	var list=[];
	var tid=[];
	$("#curr-add .teacher :checked").each(function(){
		tid.push(this.value);
	});
	$("#curr-add .info-header input").each(function() {
		if (this.value.trim() == "") {
			trim = true;
			$(this).focus();
			return;
		}
	});
	if (trim) {
		hint("red", "所填不能为空");
		return;
	}
	$("#curr-lists .sec").each(function(){
		var txt=$(this).find(":text").val();
		if(txt!=""){
			var section={
				name:txt,
				hour:$(this).find("[type=number]").val()
			};
			list.push(section);
		}
	});
 	var curr={
		name:$("#currname").val(),
		ename:$("#currename").val(),
		major:{
			id:$("#currmajor").val()
		},grade:{
			id:$("#currgrade").val()
		},lists:list,
		tid:tid
	};
 	$.ajax({
		url:URL+"addCurr",
		data:JSON.stringify(curr),
		type:"post",
		contentType:"application/json;charset=utf-8",
		success:function(){
			$("#curr-add input").val("");
			var el=($("#curr-add .sec").clone())[0];
			$(el).find("[type=number]").val(1);
			$("#curr-add .sec").remove();
			$("#curr-lists").append(el);			
			close();			
		}
	});
 });

$("#content").on("keyup","[type=number]",function(e){
	if(this.value>4){
		this.value=4;
	}
});

$("#content").on("blur","[type=number]",function(e){
	if(!this.value){
		this.value=1;
	}
});

$("#content").on("change","#curr-add input",function(){
	var num=0;
	var hour=0;
	$("#curr-add .sec").each(function(){
		if($(this).find(":text").val()){
			var val=$(this).find("[type=number]").val();
			val=val?val:1;
			num+=1;
			hour+=val;
		}
	});
	$("#curr-add .hnum").text(hour);
	$("#curr-add .snum").text(num);
});

$("#content").on("keyup","#curr-add input",function(){
	var num=0;
	var hour=0;
	$("#curr-add .sec").each(function(){
		if($(this).find(":text").val()){
			var val=$(this).find("[type=number]").val();
			val=val?val:1;
			num++;
			hour+=val;
		}
	});
	$("#curr-add .hnum").text(hour);
	$("#curr-add .snum").text(num);
});

$("#content").on("keyup","#curr-info input",function(){
	var num=0;
	var hour=0;
	$("#curr-info .sec").each(function(){
		if($(this).find(":text").val()){
			var val=$(this).find("[type=number]").val();
			val=val?val:1;
			num++;
			hour+=val;
		}
	});
	$("#curr-info .hnum").val(hour);
	$("#curr-info .snum").val(Number(num));
});

$("#edit").click(function(){
	if(!$(".spe").attr("readonly")){
		editHide();
		var val=vue.curr;
		vue.curr=null;
		vue.curr=val;
		var list="id="+cid+"&";
		$("#curr-info .teacher :checked").each(function(){
			list+="list="+this.value+"&";
		});
		$.ajax({
			url:URL+"teacherCurr",
			data:list,
			success:function(e){
				vue.teachers=e;
			}
		});
	}else{
		editShow();
	}
	delHide();
	plusHide();
});

$("#remove").click(function() {
	$(".curr-lists :checkbox").toggle();
	$("#section-remove").toggle();
	var visible = $("#section-remove").is(":visible");
	var i=$("#curr-info .save").length;
	if (!visible) {
		delHide();
		return;
	}
	if(i==0){
		$("#removeAll").hide();
	}
	editHide();
	plusHide();
});

$("#plus").click(function() {
	var i=$(".new").length;
	if($("#new").css("display")!="none"){
		var el=($("#curr-add .sec").clone())[0];
		$(el).find("input").val(1);
		$(el).find(":text").val("");
		$(el).addClass("new");
		$("#new").append(el);
	}else{
		if(i==0){
			var el=($("#curr-add .sec").clone())[0];
			$(el).find("input").val(1);
			$(el).find(":text").val("");
			$(el).addClass("new");
			$("#new").append(el);
		}
	}
	delHide();
	editHide();
	plusShow();
	var height=$("#new-btn").offset().top;
	$("html,body").animate({scrollTop:height},800);
});

$("#content").on("click","#edit-btn .btn-success",function(){
	$.ajax({
		url:URL+"hasCurr",
		data:{
			list:cid
		},success:function(e){
			if(e){
				hint("gray","先取消版本中的关联，才能修改");
			}else{
				var list=[];
				$(".save").each(function(){
					var sec={
						id:$(this).attr("sid"),
						name:$(this).find(":text").val(),
						hour:$(this).find("[type=number]").val()
					}
					list.push(sec);
				});
				var curr={
					id:cid,
					name:$("#infoname").val(),
					ename:$("#infoename").val(),
					grade:{
						id:$("#infograde").val()
					},major:{
						id:$("#infomajor").val()
					},lists:list
				};
				$.ajax({
					url:URL+"updateCurr",
					type:"post",
					contentType:"application/json;charset=utf-8",
					data:JSON.stringify(curr),
					success:function(){
						editHide();
						currinfo(cid);
					}
				});				
			}
		}
	});
});

$(window).scroll(function(){
	var height=$("#curr-info").offset().top;
	var h=$(this).scrollTop();
	if(h>height){
		$("#goup img").show();
		$(".curr-icons").css({"position":"fixed","z-index":"99","right":"15px","top":"20px"});
	}else{
		$(".curr-icons").css("position",'');
	}
});


$("#section-remove .cancel").click(function(){
	delHide();
});

queryCurrs();

function delHide(){
	$(".curr-lists :checkbox").attr("checked", false);
	$(".curr-lists :checkbox").hide();
	$("#section-remove").hide();
}

function plusShow(){
	$("#new").show();
	$("#new-btn").show();	
}

function plusHide(){
	$("#new").hide();
	$("#new-btn").hide();	
}

function plusShow(){
	$("#new").show();
	$("#new-btn").show();	
}

function editShow(){
	$(".spe").css("border-bottom","1px solid black");
	$(".spe").removeAttr("readonly");
	$(".select").removeClass("sel");
	$(".select").removeAttr("disabled");
	$(".teacher input").removeAttr("disabled");
	$("#edit-btn").show();
	$("#infoname").select();
}

function editHide(){
	$(".spe").css("border","none");
	$(".spe").attr("readonly","true");
	$(".select").addClass("sel");
	$(".select").attr("disabled","true");
	$(".teacher input").attr("disabled","true");
	$("#edit-btn").hide();
	$("#infoname").blur();
}

$("#content").on("click","input",function(){
	$(this).select();
});