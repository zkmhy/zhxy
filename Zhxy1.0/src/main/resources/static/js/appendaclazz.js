var mid = null;
var gid;
var vid;
var edit = false;
var pageNum;

var vue = new Vue({
	el : "#content",
	data : {
		grades : [],
		majors : [],
		vers : [],
		bans : [],
		freeInfo : {
			list : []
		},
		stuInfo : {
			list : []
		},
		currs : [],
		clazz : {},
		stus : null,
		clazzName : null,
		plus:false,
		remove:false		
	},
	methods : {
		stupage : function(i) {
			$("#stuPond").fadeOut(300);
			$("#stuPond").fadeIn(500);
			setTimeout(function(){
				queryStu(i)
			},300);
		},
		freepage : function(i) {
			$("#freePond").fadeOut(300);
			$("#freePond").fadeIn(500);
			setTimeout(function(){
				frees(i);
			},300);
		}
	}
});

$.ajax({
	url : URL + "gradelist",
	success : function(e) {
		if (e.length > 0) {
			$("#div").fadeIn(300);
			vue.grades = e;
			gid = e[0].id;
			clazzName();
		} else {
			hint("暂无年级信息");
		}
	},
	error : function() {
		hint("服务器异常!!!");
	}
});

$("#content").on("click", ".grade", function() {
	$(".grade").attr("checked", false);
	$(this).attr("checked", true);
	var val = $(this).next().text();
	var index = $(this).parent().find(".index").text();
	gid = this.value;
	if (val > 0) {
		$.ajax({
			url : URL + "allMajor",
			success : function(e) {
				if (e.length > 0) {
					vue.majors = e;
					$("#major").stop().slideDown(500);
					mid = e[0].id;
					clazzName();
				} else {
					hint("暂无专业信息");
					vue.majors = [];
					vue.clazzName = null;
				}
			}
		});
	} else {
		mid = null;
		clazzName();
		vue.majors = [];
		$("#major").stop().slideUp(300);
	}
});

$("#content").on("click", ".major", function() {
	mid = this.value;
	var index = $(this).next().text();
	$(".major").attr("checked", false);
	$(this).attr("checked", true);
	clazzName();
});

$("#content").on("click", ".btn-block", function() {
	if (check()) {
		auto();
		clearInterval(tid);
	}
});
$("#content").on("click", ".im-close", function() {
	$("#info").stop().slideUp(350);
	$("#div").stop().slideDown(650);
	plusHide();
	delHide();
	editHide();
	tid=setInterval(function(){
		clazzTime();
	},2000);
});

$("#content").on("change", "#ban", function() {
	var index = this.selectedIndex;
	if (vue.bans[index]) {
		$("#bNum").text(vue.bans[index].bNum);
	}
});

$("#content").on("click", ".buttons .btn", function() {
	var val = this.value;
	if(pageNum!=val){
		$("#stus").fadeOut(300);
		$("#stus").fadeIn(500);
		setTimeout(function() {
			listPage(val);
		}, 300);
	}
});

$("#content").on("click", ".icons .im-remove2", function() {
	if (vue.remove) {
		delHide();
	} else {
		delShow();
		if (vue.plus) {
			plusHide();
		}
		if(edit){
			editHide();
		}
	}
});

$("#content").on("click", ".icons .im-plus", function() {
	if (vue.plus) {
		plusHide();
	} else {
		if (vue.remove) {
			delHide();
		}
		if(edit){
			editHide();
		}
		plusShow();
	}
});
$("#content").on("click", ".icons .im-quill", function() {
	if (edit) {
		editHide();
	} else {
		if (vue.remove) {
			delHide();
		}
		if(vue.plus){
			plusHide();
		}
		editShow();
	}
});

$("#content").on("click","#pond .btn-success",function(){
	var length=vue.clazz.allStudents.length;
	var list=[];
	$("#stuPond .stu:checked").each(function(){
		list.push(vue.stuInfo.list[this.value]);
	});
	$("#freePond .stu:checked").each(function(){
		list.push(vue.freeInfo.list[this.value]);
	});
	if(length+list.length>40){
		hint("开班人数超过四十，无法添加");
		return;
	}
	$("#pond :checked").attr("checked",false);
	$.ajax({
		url:URL+"stulistadd",
		type:"post",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(list),
		success:function(){
			queryClazz();
		}
	});
});

$("#content").on("click","#clazz-div .btn-danger",function(){
	var list=[];
	$("#clazz-div .stu:checked").each(function(){
		list.push(vue.stus[this.value]);
	});
	$("#clazz-div :checked").attr("checked",false);
	$.ajax({
		url:URL+"stulistdel",
		type:"post",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(list),
		success:function(){
			queryClazz();
		}
	});
});

$("#content").on("change",".all",function(){
	$(this).parents("tr").nextAll().find(":checkbox").attr("checked",this.checked);
});

$("#content").on("change",".stu",function(){
	if(this.checked){
		var check=true;
		$(this).parents("table").find(".stu").each(function(){
			if(!this.checked){
				check=false;
				return;
			}
		});
		$(this).parents("table").find(".all").attr("checked",check);		
	}else{
		$(this).parents("table").find(".all").attr("checked",false);
	}
});

$("#content").on("click","#ver",function(){
	vid=this.value;
	versionCurr();
});

$("#content").on("change",".teacher",function(){
	var index = this.selectedIndex;
	var i=$(this).parents("tr").find(".curr-info").attr("index");
	var avg=vue.currs[i].teacher[index].avg;
	$(this).parents("tr").find(".curr-info").text(avg);
});

$("#content").on("click",".btn-primary",function(){
	var length=vue.clazz.allStudents.length;
	var list=[];
	if(length>40){
		hint("开班人数超过40人，无法开班");
		return;
	}else if(length<25){
		hint("开班人数少于25人，无法开班");
		return;
	}
	vue.clazz.bid=$("#ban").val();
	vue.clazz.vid=$("#ver").val();
	$(".curr").each(function(){
		var cid=$(this).find(".curr-info").attr("cid");
		var tid=$(this).find(".teacher").val();
		var curr={
			id:cid,
			tid:tid
		}
		list.push(curr);
	});
	vue.clazz.tid=list[0].tid;
	vue.clazz.cid=list[0].id;
	vue.clazz.curriculums=list;
	$.ajax({
		type:"post",
		url:URL+"appendClazz",
		data:JSON.stringify(vue.clazz),
		contentType:"application/json;charset=utf-8",
		success:function(){
			location.href="clazz.to";
		}
	});
});

$("#content").on("change","#start",function(){
	if(!this.value){
		this.value=this.min;
	}
});

function hint(str) {
	$("#hint").text(str);
	$("#hint").stop().show();
	$("#hint").stop().fadeOut(900);
}

function clazzName() {
	$.ajax({
		url : URL + "clazzName",
		data : {
			gid : gid,
			mid : mid
		},
		success : function(e) {
			vue.clazzName = e;
		}
	});
}

function queryVersion() {
	$.ajax({
		url : URL + "restversion",
		success : function(e) {
			vue.vers = e;
			vid=e[0].id;
			queryBan();
			versionCurr();
		}
	});
}

function queryBan() {
	$.ajax({
		url : URL + "ban",
		success : function(e) {
			vue.bans = e;
			autoClazz();
		}
	});
}

function autoClazz() {
	$.ajax({
		url : URL + "autoClazz",
		data : {
			gid : gid,
			mid : mid,
			date : $("#time").val()
		},
		success : function(e) {
			vue.clazz = e;
			listPage(1);
			frees(1);
			queryStu(1);
			$("#div").stop().slideUp(350);
			$("#info").stop().slideDown(650);
		}
	});
}

function queryClazz(){
	$.ajax({
		url:URL+"queryAuto",
		success:function(e){
			vue.clazz=e;
			listPage(e.pagenum);
			frees(1);
			queryStu(1);
		}
	});
}

function listPage(i) {
	pageNum=i;
	$.ajax({
		url : URL + "listPage",
		data : {
			page : i
		},
		success : function(e) {
			vue.stus = e;
			$("#stus").css("overflow", "visible");
		}
	});
}

function frees(i) {
	$.ajax({
		url : URL + "freeStu",
		data : {
			page : i,
			mid : mid
		},
		success : function(e) {
			vue.freeInfo = e;
		}
	});
}

function queryStu(i) {
	$.ajax({
		url : URL + "queryStu",
		data : {
			page : i,
			mid : mid
		},
		success : function(e) {
			vue.stuInfo = e;
		}
	});
}

function auto() {
	queryVersion();
}

function check() {
	var check=true;
	$.ajax({
		url:URL+"exist/stu",
		async:false,
		data:{
			mid:mid
		},success:function(e){
			if(!e){
				hint("学生信息缺失，无法开班");
				check=false
			}
		},error:function(){
			hint("服务器未给出响应");
		}
	});
	if(check){
		$.ajax({
			url:URL+"exist/ban",
			async:false,
			success:function(e){
				if(!e){
					hint("班主任信息缺失,无法开班");
					check=false;
				}
			}
		});
	}
	if(check){
		$.ajax({
			url:URL+"exist/ver",
			async:false,
			success:function(e){
				if(!e){
					hint("版本信息缺失,无法开班");
					check=false;
				}
			}
		});
	}
	if(check){
		$.ajax({
			url:URL+"exist/curr",
			data:{
				gid:gid,
				mid:mid
			},async:false,
			success:function(e){
				if(!e){
					hint("版本内不包含该年级专业的课程,无法开班");
					check=false;
				}
			}
		});
	}
	if(check){
		$.ajax({
			url:URL+"exist/teacher",
			async:false,
			data:{
				gid:gid,
				mid:mid
			},success:function(e){
				if(!e){
					hint("版本内有课程暂无老师可以上课,无法开班");
					check=false;
				}
			}
		});
	}
	return check;
}

function versionCurr(){
	$.ajax({
		url:URL+"versionCurriculum",
		data:{
			gid:gid,
			vid:vid,
			mid:mid
		},success:function(e){
			vue.currs=e;
		}
	});
}

function delHide() {
	$("#clazz-div .hide-it").hide();
	vue.remove = false;
}

function delShow() {
	$("#clazz-div .hide-it").show();
	vue.remove = true;
}

function plusShow() {
	$("#pond .hide-it").show();
	vue.plus = true;
}

function plusHide() {
	$("#pond .hide-it").hide();
	vue.plus = false
}

function editShow() {
	edit = true;
	$(".select").removeClass("sel");
	$(".select").removeAttr("disabled");
	$(".select").removeAttr("readonly");
}

function editHide() {
	edit = false;
	$(".select").addClass("sel");
	$(".select").attr("disabled","true");
	$(".select").attr("readonly","true");
}

clazzTime();

var tid=setInterval(function(){
	clazzTime();
},2000);

function clazzTime(){
	var curr_time = new Date();
	var strDate = curr_time.getFullYear()+"-";
	if(curr_time.getMonth()<9){
		strDate += "0"+(curr_time.getMonth()+1)+"-";
	}else{
		strDate += curr_time.getMonth()+1+"-";			
	}
	if(curr_time.getDate()<10){
		strDate += "0"+curr_time.getDate()+"T";
	}else{
		strDate += curr_time.getDate()+"T";
	}
	if(curr_time.getHours()<10){
		strDate += "0"+curr_time.getHours()+":";						
	}else{
		strDate += curr_time.getHours()+":";
	}			
	if(curr_time.getMinutes()<10){
		strDate += "0"+curr_time.getMinutes();
	}else{
		strDate += curr_time.getMinutes();
	}
	$("#time").val(strDate);
}
