var mid=null;
var gid;
var vue=new Vue({
	el:"#content",
	data:{
		grades:[],
		majors:[],
		vers:[],
		bans:[],
		freeInfo:{
			list:[]
		},
		stuInfo:{
			list:[]
		},
		currs:[],
		clazz:{},
		stus:[],
		clazzName:null
	},methods:{
		stupage:function(i){
			queryStu(i);
		},freepage:function(i){
			frees(i);
		}
	}
});

$.ajax({
	url:URL+"gradelist",
	success:function(e){
		if(e.length>0){
			$("#div").fadeIn(300);
			vue.grades=e;
			gid=e[0].id;
			clazzName();
		}else{
			hint("暂无年级信息");
		}
	},error:function(){
		hint("服务器异常!!!");
	}
});

$("#content").on("click",".grade",function(){
	$(".grade").attr("checked",false);
	$(this).attr("checked",true);
	var val=$(this).next().text();
	var index=$(this).parent().find(".index").text();
	vue.grade=vue.grades[index];
	gid=this.value;
	if(val>0){
		$.ajax({
			url:URL+"allMajor",
			success:function(e){
				if(e.length>0){
					vue.majors=e;
					$("#major").stop().slideDown(500);			
					mid=e[0].id;
					clazzName();
				}else{
					hint("暂无专业信息");
					vue.majors=[];
					vue.clazzName=null;
				}
			}
		});
	}else{
		mid=null;
		clazzName();
		vue.majors=[];
		$("#major").stop().slideUp(300);		
	}
});

$("#content").on("click",".major",function(){
	mid=this.value;
	var index=$(this).next().text();
	vue.major=vue.majors[index];
	$(".major").attr("checked",false);
	$(this).attr("checked",true);
	clazzName();
});

$("#content").on("click",".btn-block",function(){
	if(check()){
		auto();
	}
/* 	if(vue.clazzName){
		var time=$("#time").val();
 		if(time){
		}else{
			hint("请选择开班时间");
		}
 	}else{
		hint("缺少专业，无法开班！！！");
	}		
 */});

$("#content").on("click",".im-close",function(){
	$("#info").stop().slideUp(350);
	$("#div").stop().slideDown(650);
});

$("#content").on("click","#ban",function(){
	var index=this.selectedIndex;
	if(vue.bans[index]){
		$("#bNum").text(vue.bans[index].bNum);
	}
});

$("#content").on("click",".buttons .btn",function(){
	$("#stus").fadeOut(230);
	$("#stus").show(500);
	var val=this.value;
	setTimeout(function(){
		listPage(val);
	},230);
});

$("#content").on("click","",function(){
	
});

function hint(str){
	$("#hint").text(str);
	$("#hint").stop().show();
	$("#hint").stop().fadeOut(1200);
}

function clazzName(){
	$.ajax({
		url:URL+"clazzName",
		data:{
			gid:gid,
			mid:mid
		},success:function(e){
			vue.clazzName=e;
		}
	});
}

function queryVersion(){
	$.ajax({
		url:URL+"restversion",
		success:function(e){
			vue.vers=e;
			queryBan();
		}
	});
}

function queryBan(){
	$.ajax({
		url:URL+"ban",
		success:function(e){
			vue.bans=e;
			autoClazz();
		}
	});
}

function autoClazz(){
	$.ajax({
		url:URL+"autoClazz",
		data:{
			gid:gid,
			mid:mid,
			date:$("#time").val()
		},success:function(e){
			vue.clazz=e;
			listPage(1);
			frees(1);
			queryStu(1);
			$("#div").stop().slideUp(350);
			$("#info").stop().slideDown(650);				
		}
	});
}

function listPage(i){
	$.ajax({
		url:URL+"listPage",
		data:{
			page:i
		},success:function(e){
			vue.stus=e;
			$("#stus").css("overflow","visible");
		}
	});
}

function frees(i){
	$.ajax({
		url:URL+"freeStu",
		data:{
			page:i,
			mid:mid
		},success:function(e){
			vue.freeInfo=e;
		}
	});
}

function queryStu(i){
	$.ajax({
		url:URL+"queryStu",
		data:{
			page:i,
			mid:mid
		},success:function(e){
			vue.stuInfo=e;
		}
	});
}

function auto(){
	queryVersion();
}

function check(){
	return true;
}

