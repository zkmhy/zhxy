var vue=new Vue({
	el:"#content",
	data:{
		name:null,
		list:[],
		currs:[],
		check:null
	}
});

$.ajax({
	url:URL+"versionName",
	success:function(e){
		vue.name=e;
	}
});
$.ajax({
	url:URL+"allversion",
	success:function(e){
		vue.list=e;
	}
});
$.ajax({
	url:URL+"allCurr",
	success:function(e){
		vue.currs=e;
	}
});

$("#content").on("change",".auto_on",function(){
	$(".auto_off").attr("checked",false);
	$("#name").attr("readonly","true");
	this.checked=true;
	$("#name").val(vue.name);
});

$("#content").on("change",".auto_off",function(){
	$(".auto_on").attr("checked",false);
	$("#name").select();
	$("#name").removeAttr("readonly");
	this.checked=true;
});


$("#content").on("change",".ver",function(){
	$(".ver").attr("checked",false);
	this.checked=true;	
	var id=this.value;
	if(id=="null"){
		id=null;
	}
	$.ajax({
		url:URL+"allCurr",
		data:{
			vid:id
		},success:function(e){
			vue.currs=e;
		}
	});
	$.ajax({
		url:URL+"allCheck",
		data:{
			vid:id
		},success:function(e){
			vue.check=e;
			console.log(e);
		}
	});
});

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

$("#content").on("change", "#all-check", function() {
	$(".all-check").attr("checked", this.checked);
	$(".section-box").attr("checked",this.checked);
	if (this.checked) {
		$(".rest .hide-it").slideDown(600);
	} else {
		$(".rest .hide-it").slideUp(600);
	}
});

$(".btn-success").click(function(){
	var list=[];
	$(".section-box").each(function(){
		if(this.checked){
			list.push(this.value);
		}
	});
	var ver={
		name:$("#name").val(),
		list:list
	}
	$.ajax({
		url:URL+"addVer",
		type:"post",
		data:JSON.stringify(ver),
		contentType:"application/json;charset=utf-8",
		success:function(){
			$.ajax({
				url:URL+"versionName",
				success:function(e){
					vue.name=e;
				}
			});
			$.ajax({
				url:URL+"allversion",
				success:function(e){
					vue.list=e;
				}
			});
			$.ajax({
				url:URL+"allCurr",
				success:function(e){
					vue.currs=e;
				}
			});
			$(".ver").attr("checked",false);
			$("#default").attr("checked",true);
		}
	});
});